package org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.chainedMNSLocalOpt;

import java.io.PrintStream;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.TSPLocalSearchAlgorithm;
import org.logisticPlanning.tsp.solving.operators.permutation.localOpt.ExhaustivelyEnumeratingLocal7Optimizer;
import org.logisticPlanning.tsp.solving.operators.permutation.localOpt.LocalOptimizer;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * <p>
 * This local search algorithm improves a given tour by using multiple
 * neighborhoods at once. It tries to improve the tour until no directly
 * improving move is possible anymore and then will apply a special move to
 * escape from the local optimum. Different from basic MNS, it uses a local
 * n-opt operator to complement its search.
 * </p>
 */
public class ChainedMNSLocalNOpt extends TSPLocalSearchAlgorithm<int[]> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the maximum length of the move queue: {@value} */
  public static final String PARAM_MAX_ALLOCATIONS = "maxMoveQueueLen"; //$NON-NLS-1$

  /** the move order: {@value} */
  public static final String PARAM_MOVE_ORDER = "moveOrder";//$NON-NLS-1$

  /** the default maximum number of moves: {@value} */
  private static final int DEFAULT_MAX_ALLOCATIONS = 1048576;

  /** the default move comparator */
  private static final EMoveComparator DEFAULT_MOVE_COMPARATOR = EMoveComparator.BEST_MOVE_FIRST;

  /** the n-optimizer length */
  public static final String PARAM_N_OPT = "nOpt"; //$NON-NLS-1$
  /** the random overlap parameter */
  public static final String PARAM_RANDOM_OVERLAP = "randomOverlap"; //$NON-NLS-1$

  /**
   * the maximum number of moves to allocate
   * 
   * @serial serializable field
   */
  private int m_maxMoveAllocations;

  /**
   * the move order
   * 
   * @serial serializable field
   */
  private EMoveComparator m_cmp;

  /**
   * The distances between the nodes and their successors in the current
   * permutations. The contract is:
   * {@code m_distances[i]=this.m_f.dist(this.m_solution[i], this.m_solution[(i+1)%n])}
   */
  private transient int[] m_distances;

  /** the objective function to use */
  private transient ObjectiveFunction m_f;

  /** the queue of moves */
  private transient _Move m_queue;

  /** old swap moves that can be re-used */
  private transient _Move m_old;

  /** the number of allocated moves */
  private transient int m_moveAllocations;

  /** should we use overlaps? */
  private boolean m_randomOverlap;

  /** the operation to be used */
  private LocalOptimizer m_opt;

  /** the order in which the nodes are processed */
  private transient int[] m_currentAllowed;
  /** the order in which the nodes are processed */
  private transient int[] m_nextAllowed;
  /** the order in which the nodes are processed */
  private transient boolean[] m_nextAllowedBits;

  /** instantiate */
  public ChainedMNSLocalNOpt() {
    super("Multiple-Neighborhood Search with Local N-Opt");//$NON-NLS-1$
    this.m_cmp = ChainedMNSLocalNOpt.DEFAULT_MOVE_COMPARATOR;
    this.m_maxMoveAllocations = ChainedMNSLocalNOpt.DEFAULT_MAX_ALLOCATIONS;
    this.m_opt = new ExhaustivelyEnumeratingLocal7Optimizer();
    this.m_randomOverlap = true;
  }

  /** {@inheritDoc} */
  @Override
  public void localSearch(final Individual<int[]> srcdst,
      final ObjectiveFunction f) {
    long orig, origAfterNOpt;

    origAfterNOpt = (Long.MAX_VALUE);
    for (;;) {
      orig = srcdst.tourLength;
      this.__mns(srcdst, f);
      if (f.shouldTerminate()) {
        return;
      }
      if (srcdst.tourLength < origAfterNOpt) {
        this.__localNOpt(srcdst, f);
        if (f.shouldTerminate()) {
          return;
        }
      }
      origAfterNOpt = srcdst.tourLength;
      if (orig <= origAfterNOpt) {
        break;
      }
    }
  }

  /**
   * Perform the multi-neighborhood search
   * 
   * @param f
   *          the objective function
   * @param srcdst
   *          the individual record to work on
   */
  private final void __mns(final Individual<int[]> srcdst,
      final ObjectiveFunction f) {
    final int n;
    final int[] dist;
    final EMoveComparator cmp;
    boolean lastWasIncomplete;
    int changed_range_i, changed_range_j;
    _Move move, predMove, cur, pred, best, predBest, next;

    n = f.n();

    // clear the queue
    this.disposeQueue();

    // compute the distances and initialize the internal distance list
    srcdst.tourLength = ChainedMNSLocalNOpt.distanceAndLengthInit(f, n,
        srcdst.solution, this.m_distances);

    // initialize local variables
    changed_range_i = 0;
    changed_range_j = n;
    dist = this.m_distances;
    cmp = this.m_cmp;

    // fill the move queue, remember if this hit the allocation limit
    lastWasIncomplete = this.fillMoveQueue(0, n, srcdst.solution,
        this.m_distances);

    outer: for (;;) {
      move = this.m_queue;// move=best and first move in queue
      if (move == null) {// ok, queue is empty
        break outer;// quit
      }
      // fillMoveQueue ensures that the first move in the queue is best
      // move.
      predMove = null;// so there is no move before
      // predMove is always the last move before move or null if move is
      // the
      // first move in the queue

      // [changed_range_i,changed_range_j] hold the range that includes
      // all
      // changes that we made so far
      // This is useful when filling the queue after consuming all moves
      // since
      // new moves that do not intersect with
      // [changed_range_i,changed_range_j]
      // do not need to be checked:
      // They were checked in at least one prior local search / fill queue
      // run
      // and were not improving. As nothing outside
      // [changed_range_i,changed_range_j] has changed, these moves'
      // deltas
      // are
      // still the same (and therefore still >0).
      changed_range_i = Integer.MAX_VALUE;
      changed_range_j = Integer.MIN_VALUE;

      // In the inner loop, we process the queue: We always take the best
      // move
      // out of the queue and perform it. Performing a move may lead to
      // the
      // invalidation of other moves, which are purged from the queue.
      // Then
      // the
      // next best move is taken from the queue and executed. If the queue
      // is
      // empty, this loop ends and the outer loop is executed again to
      // fill
      // the
      // queue.
      inner: do {

        // delete the move from the queue
        if (predMove == null) {
          this.m_queue = move.m_next;
        } else {
          predMove.m_next = move.m_next;
        }

        // apply the move: change the solution and distance list
        move.apply(n, srcdst.solution, dist);
        srcdst.tourLength += move.m_delta; // modify the total tour
        // length
        // accordingly
        // we now have a new and better candidate solution: register
        f.registerFE(srcdst.solution, srcdst.tourLength);

        move.m_next = this.m_old; // dispose the move: hang it into the
        this.m_old = move;// queue for re-use
        if (f.shouldTerminate()) {// ok, we should terminate right now
          break outer;
        }

        // make [changed_range_i,changed_range_j] include the area
        // touched
        // by
        // the performed move: only this range is interesting when
        // filling
        // the
        // queue again at the next "outer" iteration
        changed_range_i = Math.min(changed_range_i, move.m_i);
        changed_range_j = Math.max(changed_range_j, move.m_j);

        cur = this.m_queue; // cur = beginning of queue
        if (cur == null) {
          break inner;// queue is empty: we need to fill the move list
          // again
        }

        // The following loop has two purposes:
        // 1. we need to delete all moves that intersect with "move" in
        // such a
        // way that their delta would have changed / they have been
        // invalidated
        // by "move"
        // 2. find the next best move to apply (and its successor)
        best = predBest = pred = null;
        while (cur != null) {
          // pred is the last non-deleted move before cur
          // best is the best move that was not deleted
          // predBest is the last non-deleted move before best
          next = cur.m_next; // iterate through the move queue

          // check if we need to delete the move
          if (cur.checkDeleteMove(move, n)) {
            // ok, the move has been invalidated and must be deleted
            // so we remove it from the queue
            if (pred == null) {
              this.m_queue = next;
            } else {
              pred.m_next = next;
            }
            // pred remains the same, as it was the last non-deleted
            // move

            // dispose of cur: hang it into the re-use queue
            cur.m_next = this.m_old;
            this.m_old = cur;
          } else {
            // good, the move can be kept

            if ((best == null) || (cmp.compare(cur, best) < 0)) {
              // oh, the move is the best move found so far!
              best = cur;
              predBest = pred;
            }

            // the move was not deleted, so it becomes the last
            // non-deleted
            // move
            pred = cur;
          }

          cur = next;// cur becomes cur.m_next
        }

        move = best; // the next move is the best move found in the
        // queue
        predMove = predBest; // and predMove the last non-deleted move
        // before
      } while (move != null);

      // ok, we have emptied the move queue. Now it is time to fill in
      // again.

      // lastWasIncomplete tells us whether all possible moves were
      // checked.
      // If it is false, we only need to check
      // [changed_range_i,changed_range_j]. Otherwise, we need to check
      // [0,n]
      lastWasIncomplete = this.fillMoveQueue(//
          (lastWasIncomplete ? 0 : changed_range_i),//
          (lastWasIncomplete ? n : changed_range_j), //
          srcdst.solution,//
          dist);
    }

    this.disposeQueue(); // dispose whatever move is still in the queue
  }

  /**
   * Perform the local n-opt search
   * 
   * @param f
   *          the objective function
   * @param srcdst
   *          the individual record to work on
   */
  private final void __localNOpt(final Individual<int[]> srcdst,
      final ObjectiveFunction f) {
    final Randomizer rand;
    final int n, len, lenM1;
    final LocalOptimizer opt;
    final boolean overlap;
    int[] currentAllowed, nextAllowed, tempI;
    int i, j, nextAllowedCount, currentAllowedCount;
    boolean[] nextAllowedBits;
    long delta;

    rand = f.getRandom();
    nextAllowed = this.m_nextAllowed;
    nextAllowedBits = this.m_nextAllowedBits;
    opt = this.m_opt;

    n = f.n();
    len = opt.getSubPathLength();
    lenM1 = (len - 1);
    nextAllowedCount = (n / lenM1);
    if ((nextAllowedCount * lenM1) < n) {
      nextAllowedCount++;
    }
    j = rand.nextInt(n);
    for (i = nextAllowedCount; (--i) >= 0;) {
      nextAllowed[i] = j;
      j += lenM1;
      if (j >= n) {
        j = 0;
      }
    }

    currentAllowed = this.m_currentAllowed;
    overlap = this.m_randomOverlap;

    while (nextAllowedCount > 0) {
      tempI = currentAllowed;
      currentAllowed = nextAllowed;
      nextAllowed = tempI;

      currentAllowedCount = nextAllowedCount;
      for (; (--nextAllowedCount) >= 0;) {
        nextAllowedBits[currentAllowed[nextAllowedCount]] = false;
      }
      nextAllowedCount = 0;

      while (currentAllowedCount > 0) {
        i = rand.nextInt(currentAllowedCount);
        j = currentAllowed[i];
        currentAllowed[i] = currentAllowed[--currentAllowedCount];

        if (!nextAllowedBits[j]) {
          delta = opt.apply(srcdst.solution, j, f);
          if (delta < 0L) {
            srcdst.tourLength += delta;
            f.registerFE(srcdst.solution, srcdst.tourLength);

            i = (j - lenM1);
            if (i < 0) {
              i += n;
            }
            if (!nextAllowedBits[i]) {
              nextAllowedBits[i] = true;
              nextAllowed[nextAllowedCount++] = i;
            }

            i = (j + lenM1);
            if (i >= n) {
              i -= n;
            }
            if (!nextAllowedBits[i]) {
              nextAllowedBits[i] = true;
              nextAllowed[nextAllowedCount++] = i;
            }

            // try a randomized speed-up if there are large overlaps
            if (overlap) {
              i = (j + (rand.nextInt(len) - (len >>> 1)));
              if (i < 0) {
                i += n;
              } else {
                if (i >= n) {
                  i -= n;
                }
              }
              if (!nextAllowedBits[i]) {
                nextAllowedBits[i] = true;
                nextAllowed[nextAllowedCount++] = i;
              }
            }

          }
          if (f.shouldTerminate()) {
            return;
          }
        }
      }

    }
  }

  /**
   * Set the local optimizer
   * 
   * @param opt
   *          the local optimizer
   */
  public final void setLocalOptimizer(final LocalOptimizer opt) {
    this.m_opt = opt;
  }

  /**
   * Should we also test randomly overlapping patches?
   * 
   * @param overlap
   *          {@code true} if we also should test randomly overlapping
   *          patches
   */
  public final void setRandomOverlap(final boolean overlap) {
    this.m_randomOverlap = overlap;
  }

  /**
   * set the {@link EMoveComparator move comparator}
   * 
   * @param cmp
   *          the {@link EMoveComparator move comparator} to used
   */
  public final void setMoveComparator(final EMoveComparator cmp) {
    this.m_cmp = ((cmp != null) ? cmp
        : ChainedMNSLocalNOpt.DEFAULT_MOVE_COMPARATOR);
  }

  /**
   * get the {@link EMoveComparator move comparator}
   * 
   * @return the {@link EMoveComparator move comparator} used
   */
  public final EMoveComparator getMoveComparator() {
    return this.m_cmp;
  }

  /**
   * Set the maximum number of move records to allocate and hold in memory
   * 
   * @param max
   *          the maximum number of move records to allocate and hold in
   *          memory
   */
  public final void setMaxMoveAllocations(final int max) {
    this.m_maxMoveAllocations = max;
  }

  /**
   * Get the maximum number of move records to allocate and hold in memory
   * 
   * @return max the maximum number of move records to allocate and hold in
   *         memory
   */
  public final int getMaxMoveAllocations() {
    return this.m_maxMoveAllocations;
  }

  /**
   * compute the total length of the solution and initialize the distance
   * array
   * 
   * @param f
   *          the objective function
   * @param n
   *          the number of nodes
   * @param sol
   *          the solution array
   * @param dist
   *          the distance array
   * @return the length of the tour
   */
  private static final long distanceAndLengthInit(
      final ObjectiveFunction f, final int n, final int[] sol,
      final int[] dist) {
    int curP, oldP;
    long total;
    int i, j;

    curP = sol[0];
    total = 0l;
    for (i = n; (--i) >= 0;) {
      oldP = curP;
      curP = sol[i];
      dist[i] = j = f.distance(curP, oldP);
      total += j;
    }

    return total;
  }

  /**
   * Return a new move record. This method first tries to recycle old moves
   * and will allocate a new move if the old queue is empty. However, if
   * the maximum number of move allocations has been reached, {@code null}
   * will be returned.
   * 
   * @return the new move, or {@code null} if the maximum number of move
   *         allocations has been exhausted
   */
  private final _Move allocate() {
    final _Move m;
    final int i;

    m = this.m_old;
    if (m != null) {
      this.m_old = m.m_next;
      return m;
    }

    i = this.m_moveAllocations;
    if (i < this.m_maxMoveAllocations) {
      this.m_moveAllocations = (i + 1);
      return new _Move();
    }

    return null;
  }

  /** dispose all moves in the move queue */
  private final void disposeQueue() {
    _Move cur, next;

    cur = this.m_queue; // cur = beginning of queue

    if (cur == null) {// queue is empty, we can leave directly
      return;
    }

    // moves are left in queue: dispose them
    dispose: for (;;) {// find the last move in queue
      next = cur.m_next;
      if (next == null) {
        break dispose;
      }
      cur = next;
    }

    // cur now is the last move in the move queue m_queue is the first
    cur.m_next = this.m_old; //
    this.m_old = this.m_queue;// put m_queue at beginning
    this.m_queue = null; // ensure that the queue is really empty
  }

  /**
   * enqueue a move
   * 
   * @param move
   *          the move to enqueue
   */
  private final void enqueue(final _Move move) {
    final _Move q;

    q = this.m_queue;
    if ((q == null) || ((this.m_cmp.compare(move, q) < 0))) {
      // move = new best: put at head of queue
      move.m_next = q;
      this.m_queue = move;
    } else {// move is not best: put at 2nd place, regardless how good it
      // is
      move.m_next = q.m_next;
      q.m_next = move;
    }
  }

  /**
   * <p>
   * Fill the queue of search moves that can improve the current solution.
   * </p>
   * <p>
   * This procedure makes use of a set of assumptions and pre-conditions.
   * </p>
   * <ol>
   * <li>The array {@code sol} contains the current candidate solution.</li>
   * <li>The {@code i}<sup>th</sup> element of the array
   * {@link #m_distances} contains the distance between the {@code i}
   * <sup>th</sup> element of {@code sol} and the {@code (i+1)%n}
   * <sup>th</sup> element. In other words, the element at index
   * {@code n-1} holds the distance of detour from the last node back to
   * the start.</li>
   * <li>The parameters {@code lastChangeStart} and {@code lastChangeEnd}
   * hold the inclusive indices where the last change to the candidate
   * solution took place.</li>
   * </ol>
   * <p>
   * When filling the queue, we only consider moves that at least touch (or
   * overlap, or are included in) the range
   * {@code [lastChangeStart,lastChangeEnd]}. As the rest of the
   * permutation was not changed, other moves would have already been
   * considered before and thus do not need to tested again.
   * </p>
   * <p>
   * This function will place the best move found at the head of the queue.
   * </p>
   * <p>
   * This function returns {@code true} if not all possible moves could be
   * enqueued due to the allocation limit {@link #m_maxMoveAllocations}.
   * This means that the next call to this method cannot rely on exhaustive
   * move testing, i.e., should always invoke it with the full index range.
   * If {@code false} is returned, all potential moves have been enqueued.
   * Then, only moves need to be investigated which intersect with the
   * changed region in the next call.
   * </p>
   * 
   * @param lastChangeStart
   *          the first index of the interesting range
   * @param lastChangeEnd
   *          the last index of the interesting range
   * @param sol
   *          the solution array
   * @param dists
   *          the distance array
   * @return {@code true} if not all possible moves could be checked due to
   *         the memory allocation limit {@link #m_maxMoveAllocations},
   *         {@code false} if all possible moves were checked
   */
  private final boolean fillMoveQueue(final int lastChangeStart,
      final int lastChangeEnd, final int[] sol, final int[] dists) {
    final ObjectiveFunction f;
    final int n, nm1, nopt;
    final int begin, end;
    int i, j, jm1, im1, delta;
    int sol_im1, sol_i, sol_ip1, sol_jm1, sol_j, sol_jp1;
    int D_im1_i, D_i_ip1, D_jm1_j, D_j_jp1, D_im1_j, D_i_jp1, D_im1_ip1, D_i_j, D_jm1_jp1, D_ip1_j, D_i_jm1;
    _Move move;

    f = this.m_f;
    n = f.n();
    nm1 = (n - 1);
    nopt = this.m_opt.getSubPathLength();

    // Any move intersecting with the range [begin,end] is interesting.
    // The -1/+1 stem from the fact that when changing range [2,3], also
    // the
    // distance of the node at index 1 (to index 2) has changed.
    begin = (lastChangeStart - 1);
    end = (lastChangeEnd + 1);

    // iterate over all indices 0<j<n
    j = (n - 1);
    D_jm1_j = dists[j];
    sol_j = sol[0];
    sol_jm1 = sol[j];
    for (; j > 0; j = jm1) {
      jm1 = (j - 1); // as j>0, this can never wrap/become <0

      // initialize variables pi_j-1, pi_j, pi_j+1
      sol_jp1 = sol_j;
      sol_j = sol_jm1;
      sol_jm1 = sol[jm1];

      // load distances d(j-1,j), d(j,j+1)
      D_j_jp1 = D_jm1_j;
      D_jm1_j = dists[jm1];

      // XXX: This is the difference to pure mns.
      // If we use MNS together with an exhaustive local optimizer for
      // sequences of length nopt, then we only need to consider larger
      // moves. I.e. if the start index is j, then any element at index j,
      // j-1, j-2, ..., j-nopt+1 would already be covered by the local
      // optimizer.
      i = (j - nopt);
      if (i < 0) {
        return false;
      }

      D_im1_i = dists[i];
      sol_i = sol[i + 1];
      sol_im1 = sol[i];

      // iterate over all indices 0<=i<=j-nopt
      inner: for (; i >= 0; i--) {

        im1 = ((i + nm1) % n);

        // initialize variables pi_i-1, pi_i, pi_i+1
        sol_ip1 = sol_i;
        sol_i = sol_im1;
        sol_im1 = sol[im1];

        // load distances d(i-1,i), d(i,i+1)
        D_i_ip1 = D_im1_i;
        D_im1_i = dists[im1];

        // check if the interesting range is intersected
        if ((i > end) || (j < begin)) {
          continue inner;
        }

        // the inversion specific distance requirements
        D_im1_j = f.distance(sol_im1, sol_j);
        D_i_jp1 = f.distance(sol_i, sol_jp1);

        if ((i <= 0) && (j >= nm1)) {
          continue inner; // no operation works at full-range
        }

        // check the reversal move
        delta = ((D_im1_j - D_im1_i) + (D_i_jp1 - D_j_jp1));

        if (delta < 0) { // ok, the move is promising: enqueue
          move = this.allocate(); // allocate the move
          if (move == null) {
            return true;// we hit the allocation limit, force exit
          }

          move.init_reverse(i, j, delta, D_im1_j, D_i_jp1);// init the
          // move
          this.enqueue(move);
        }

        if (j == (i + 1)) {
          // for swap, rotate left, and rotate right, only index
          // tuples
          // with
          // j!=((i+1)%n) are interesting
          continue inner;
        }

        // check the rotate left move
        D_im1_ip1 = f.distance(sol_im1, sol_ip1);
        D_i_j = f.distance(sol_i, sol_j);

        delta = ((D_im1_ip1 - D_im1_i) + //
            (D_i_j - D_i_ip1) + //
        (D_i_jp1 - D_j_jp1));

        if (delta < 0) {// move is promising:enqueue
          move = this.allocate(); // allocate the move
          if (move == null) {
            return true;// we hit the allocation limit, force exit
          }

          move.init_rotate_left(i, j, delta, D_im1_ip1, D_i_j, D_i_jp1);// init
          this.enqueue(move);
        }

        // check the rotate right move
        D_jm1_jp1 = f.distance(sol_jm1, sol_jp1);

        delta = ((D_im1_j - D_im1_i) + //
            (D_i_j - D_j_jp1) + //
        (D_jm1_jp1 - D_jm1_j));

        if (delta < 0) {// move is promising:enqueue
          move = this.allocate(); // allocate the move
          if (move == null) {
            return true;// we hit the allocation limit, force exit
          }

          move.init_rotate_right(i, j, delta, D_jm1_jp1, D_i_j, D_im1_j);// init
          this.enqueue(move);
        }

        if (j == ((i + 2) % n)) {
          // for swap, j!=((i+2)%n) are interesting
          continue inner;
        }

        // check the swap move
        D_i_jm1 = f.distance(sol_i, sol_jm1);
        D_ip1_j = f.distance(sol_ip1, sol_j);

        delta = ((D_im1_j - D_im1_i) + //
            (D_ip1_j - D_i_ip1) + //
            (D_i_jm1 - D_jm1_j) + //
        (D_i_jp1 - D_j_jp1));

        if (delta < 0) {// move is promising:enqueue
          move = this.allocate(); // allocate the move
          if (move == null) {
            return true;// we hit the allocation limit, force exit
          }

          move.init_swap(i, j, delta, D_im1_j, D_ip1_j, D_i_jm1, D_i_jp1);// init
          this.enqueue(move);
        }
      }
    }

    return false;
  }

  /** {@inheritDoc} */
  @Override
  public final ChainedMNSLocalNOpt clone() {
    ChainedMNSLocalNOpt m;

    m = ((ChainedMNSLocalNOpt) (super.clone()));
    m.m_opt = m.m_opt.clone();
    m.__clear();

    return m;
  }

  /** clear this manager */
  private final void __clear() {
    this.m_distances = null;
    this.m_f = null;
    this.m_old = null;
    this.m_queue = null;
    this.m_moveAllocations = 0;
    this.m_currentAllowed = null;
    this.m_nextAllowed = null;
    this.m_nextAllowedBits = null;

  }

  /** {@inheritDoc} */
  @Override
  public final void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable.printKey(ChainedMNSLocalNOpt.PARAM_MOVE_ORDER, ps);
    Configurable.printlnObject(this.m_cmp, ps);

    Configurable.printKey(ChainedMNSLocalNOpt.PARAM_MAX_ALLOCATIONS, ps);
    ps.println(this.m_maxMoveAllocations);

    Configurable.printKey(ChainedMNSLocalNOpt.PARAM_N_OPT, ps);
    Configurable.printlnObject(this.m_opt, ps);

    Configurable.printKey(ChainedMNSLocalNOpt.PARAM_RANDOM_OVERLAP, ps);
    ps.println(this.m_randomOverlap);
  }

  /** {@inheritDoc} */
  @Override
  public final void printParameters(final PrintStream ps) {
    super.printParameters(ps);

    Configurable.printKey(ChainedMNSLocalNOpt.PARAM_MOVE_ORDER, ps);
    ps.println(//
    "the order in which moves are enqueued and performed"); //$NON-NLS-1$

    Configurable.printKey(ChainedMNSLocalNOpt.PARAM_MAX_ALLOCATIONS, ps);
    ps.println(//
    "the maximum move queue length (to prevent out-of-memory errors)"); //$NON-NLS-1$

    Configurable.printKey(ChainedMNSLocalNOpt.PARAM_N_OPT, ps);
    ps.println("The local n-optimizer to use."); //$NON-NLS-1$

    Configurable.printKey(ChainedMNSLocalNOpt.PARAM_RANDOM_OVERLAP, ps);
    ps.println("Test randomly overlapping or only minimally overlapping patches?");//$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final void configure(final Configuration config) {
    super.configure(config);

    this.m_cmp = config.getConstant(ChainedMNSLocalNOpt.PARAM_MOVE_ORDER,
        EMoveComparator.class, EMoveComparator.class, this.m_cmp);

    this.m_maxMoveAllocations = config.getInt(
        ChainedMNSLocalNOpt.PARAM_MAX_ALLOCATIONS, 1, Integer.MAX_VALUE,
        this.m_maxMoveAllocations);

    this.setLocalOptimizer(config.getInstance(
        ChainedMNSLocalNOpt.PARAM_N_OPT, LocalOptimizer.class,
        ExhaustivelyEnumeratingLocal7Optimizer.class, this.m_opt));
    this.setRandomOverlap(config.getBoolean(
        ChainedMNSLocalNOpt.PARAM_RANDOM_OVERLAP, this.m_randomOverlap));
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    final int n;

    super.beginRun(f);

    this.m_f = f;
    n = f.n();
    this.m_currentAllowed = this.m_distances = new int[n];
    // this.m_currentAllowed = new int[n];
    this.m_nextAllowed = new int[n];
    this.m_nextAllowedBits = new boolean[n];
  }

  /** {@inheritDoc} */
  @Override
  public void endRun(final ObjectiveFunction f) {
    try {
      this.__clear();
    } finally {
      super.endRun(f);
    }
  }

  /**
   * Perform the MNS chained with Local Optimization
   * 
   * @param args
   *          the command line arguments
   */
  public final static void main(final String[] args) {
    TSPAlgorithmRunner.benchmark(//
        Instance.SYMMETRIC_INSTANCES, ChainedMNSLocalNOpt.class,//
        args);
  }
}
