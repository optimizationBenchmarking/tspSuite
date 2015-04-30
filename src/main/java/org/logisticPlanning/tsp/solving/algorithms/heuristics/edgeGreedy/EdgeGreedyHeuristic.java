package org.logisticPlanning.tsp.solving.algorithms.heuristics.edgeGreedy;

import java.io.PrintStream;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.algorithms.heuristics.TSPHeuristic;
import org.logisticPlanning.tsp.solving.utils.edge.PriorityEdge;
import org.logisticPlanning.tsp.solving.utils.edge.UndirectedPriorityEdgeNeighborList;
import org.logisticPlanning.tsp.solving.utils.path.EdgePathElement;
import org.logisticPlanning.tsp.solving.utils.path.UndirectedEdgePath;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;

/**
 * <p>
 * The edge-greedy heuristic&nbsp;[<a href="#cite_JMB1997TTSPACSILO"
 * style="font-weight:bold">1</a>, <a href="#cite_JMG2004EAOHFTS"
 * style="font-weight:bold">2</a>] for symmetric TSPs sorts all edges
 * according to their length and then step-by-step adds the shortest edges
 * to the (initially empty) solution, until a complete tour has been
 * produced. Of course, it can only add edges which do not cause any cycle.
 * <p>
 * <p>
 * In an algorithm implementation, this means that we find all edges (i,j)
 * that connect two of the {@code n} nodes in the TSP . These edges are
 * sorted according to {@code dist(i, 1)} . We will then choose {@code n-1}
 * such edges into an edge list, starting at those with the smallest values
 * of {@code dist(i,j)}. However, we only add those edges to the edge list
 * that do not create a cycle/loop with the already chosen ones. There must
 * be exactly {@code n-1} such (cheapest) edges, because then each of
 * {@code n} nodes is either connected to two edges (has degree {@code 2})
 * or is one of the two ends of the tour and is connected only to one edge
 * (has degree {@code 1}). This path can then be closed, by adding the edge
 * between its two end nodes. Then we have a complete tour.
 * </p>
 * <p>
 * The greedy heuristic never finds solutions worse than
 * {@code (1+log n)/2} times the global optimum&nbsp;[<a
 * href="#cite_OM1984WCAOTTSH" style="font-weight:bold">3</a>, <a
 * href="#cite_JMG2004EAOHFTS" style="font-weight:bold">2</a>, <a
 * href="#cite_JMB1997TTSPACSILO" style="font-weight:bold">1</a>] and thus
 * has a better upper bound than the
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic
 * savings heuristic}, but in practice seems to be outperformed by
 * it&nbsp;[<a href="#cite_JMG2004EAOHFTS" style="font-weight:bold">2</a>,
 * <a href="#cite_JMB1997TTSPACSILO" style="font-weight:bold">1</a>].
 * </p>
 * <p>
 * There is one problem with the edge-sorting approach: to sort all edges
 * means to sort {@code n*(n-1)/2} edges, which is in {@code O(n^2)}. In
 * other words, we would need to keep {@code 49995000} items in the list if
 * we have {@code n=10000} nodes. This will lead to too much memory
 * consumption for larger {@code n}. Therefore, we implement the savings
 * heuristic by using a bounded
 * {@link org.logisticPlanning.tsp.solving.utils.edge.UndirectedPriorityEdgeNeighborList
 * neighbor list}, as mentioned in&nbsp;[<a href="#cite_JMB1997TTSPACSILO"
 * style="font-weight:bold">1</a>, <a href="#cite_JMG2004EAOHFTS"
 * style="font-weight:bold">2</a>]. In other words, we create a
 * {@link org.logisticPlanning.tsp.solving.utils.edge.UndirectedPriorityEdgeNeighborList
 * list} of {@link #m_neighborListLength} nearest neighbors for each node
 * according to the heuristic {@code h} and only consider the edges in that
 * list for the heuristic. This reduces the memory consumption somewhat in
 * {@code O(n*k)} - with the trade-off of potentially worse results.
 * While&nbsp;[<a href="#cite_JMB1997TTSPACSILO"
 * style="font-weight:bold">1</a>] suggest to set {@code k} to something in
 * between {@code 15} and {@code 40}, we follow the advise in&nbsp;[<a
 * href="#cite_JMG2004EAOHFTS" style="font-weight:bold">2</a>] of setting
 * it to {@code 20} by default. However, this is a configuration parameter
 * and may be adapted to the user's liking.
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_JMB1997TTSPACSILO" /><a
 * href="https://en.wikipedia.org/wiki/David_S._Johnson">David Stifler
 * Johnson</a> and&nbsp;<a
 * href="https://www.amherst.edu/people/facstaff/lamcgeoch">Lyle A.
 * McGeoch</a>: <span style="font-weight:bold">&ldquo;The Traveling
 * Salesman Problem: A Case Study in Local Optimization,&rdquo;</span> in
 * <span style="font-style:italic;font-family:cursive;">Local Search in
 * Combinatorial Optimization</span>, pages 215&ndash;310, Emile H. L.
 * Aarts and&nbsp;<a
 * href="https://en.wikipedia.org/wiki/Jan_Karel_Lenstra">Jan Karel
 * Lenstra</a>, editors, Estimation, Simulation, and Control &#8211;
 * Wiley-Interscience Series in Discrete Mathematics and Optimization,
 * Princeton, NJ, USA: Princeton University Press and&nbsp;Chichester, West
 * Sussex, UK: Wiley Interscience. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0691115222">0691115222</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780691115221">9780691115221</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/45733970">45733970</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=NWghN9G7q9MC">NWghN9G7q9MC</a>.
 * <div>link: [<a
 * href="http://www.research.att.com/~dsj/papers/TSPchapter.pdf">1
 * </a>]</div></div></li>
 * <li><div><span id="cite_JMG2004EAOHFTS" /><a
 * href="https://en.wikipedia.org/wiki/David_S._Johnson">David Stifler
 * Johnson</a> and&nbsp;<a
 * href="https://www.amherst.edu/people/facstaff/lamcgeoch">Lyle A.
 * McGeoch</a>: <span style="font-weight:bold">&ldquo;Experimental Analysis
 * of Heuristics for the STSP,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">The Traveling Salesman
 * Problem and its Variations</span>, chapter 369&ndash;443, pages
 * 369&ndash;443, <a href=
 * "http://www.rhul.ac.uk/computerscience/staffdirectory/ggutin/home.aspx">
 * Gregory Z. Gutin</a> and&nbsp;<a
 * href="http://www.sfu.ca/~apunnen/">Abraham P. Punnen</a>, editors,
 * volume 12 of Combinatorial Optimization, Norwell, MA, USA: Kluwer
 * Academic Publishers. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0306482134">0-306-48213-4</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781402006647">978-1-4020
 * -0664-7</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/0-306-48213-4_9"
 * >10.1007/0-306-48213-4_9</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/55085594">55085594</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=TRYkPg_Xf20C">TRYkPg_Xf20C</a>.
 * <div>link: [<a
 * href="http://www2.research.att.com/~dsj/papers/stspchap.pdf">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.15.9438">10.1
 * .1.15 .9438</a></div></div></li>
 * <li><div><span id="cite_OM1984WCAOTTSH" />Hoon Liong Ong and&nbsp;J. B.
 * Moore: <span style="font-weight:bold">&ldquo;Worst-Case Analysis of Two
 * Travelling Salesman Heuristics,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Operations Research
 * Letters</span> 2(6):273&ndash;277, March&nbsp;1984; published by
 * Amsterdam, The Netherlands: Elsevier Science Publishers B.V..
 * doi:&nbsp;<a href="http://dx.doi.org/10.1016/0167-6377(84)90078-6"
 * >10.1016/0167-6377(84)90078-6</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/01676377">0167-6377</a></div></li>
 * </ol>
 */
public class EdgeGreedyHeuristic extends TSPHeuristic {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the default neighbor list length: {@value} */
  private static final int DEFAULT_NEIGHBOR_LIST_LENGTH = 20;

  /**
   * the neighbor list length parameter: {@value} , see
   * {@link #m_neighborListLength}
   */
  public static final String PARAM_NEIGHBOR_LIST_LENGTH = "edgeGreedyNeighborListLength";//$NON-NLS-1$

  /**
   * the tour augmentation parameter: {@value} , see
   * {@link #m_augmentByAddingFirstCandidate}
   */
  public static final String PARAM_AUGMENTATION = "edgeGreedyAugmentByFirstCandidate"; //$NON-NLS-1$

  /**
   * the neighbor list length
   * 
   * @serial serializable field
   */
  private int m_neighborListLength;

  /**
   * augment missing edges with the first fitting edges discovered?
   * 
   * @serial serializable field
   */
  private boolean m_augmentByAddingFirstCandidate;

  /** the edge list */
  private transient UndirectedPriorityEdgeNeighborList<PriorityEdge> m_edgeList;

  /** the path */
  private transient UndirectedEdgePath m_path;

  /** instantiate */
  public EdgeGreedyHeuristic() {
    super("Edge-Greedy Heuristic"); //$NON-NLS-1$
    this.m_neighborListLength = EdgeGreedyHeuristic.DEFAULT_NEIGHBOR_LIST_LENGTH;
    this.m_augmentByAddingFirstCandidate = false;
  }

  /** {@inheritDoc} */
  @Override
  public void configure(final Configuration config) {
    super.configure(config);

    this.m_neighborListLength = config.getInt(
        EdgeGreedyHeuristic.PARAM_NEIGHBOR_LIST_LENGTH, 0,
        Integer.MAX_VALUE, this.m_neighborListLength);
    this.m_augmentByAddingFirstCandidate = config.getBoolean(
        EdgeGreedyHeuristic.PARAM_AUGMENTATION,
        this.m_augmentByAddingFirstCandidate);
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable.printKey(EdgeGreedyHeuristic.PARAM_NEIGHBOR_LIST_LENGTH,
        ps);
    ps.println(this.m_neighborListLength);

    Configurable.printKey(EdgeGreedyHeuristic.PARAM_AUGMENTATION, ps);
    ps.println(this.m_augmentByAddingFirstCandidate);
  }

  /** {@inheritDoc} */
  @Override
  public void printParameters(final PrintStream ps) {
    super.printParameters(ps);

    Configurable.printKey(EdgeGreedyHeuristic.PARAM_NEIGHBOR_LIST_LENGTH,
        ps);
    ps.println("the length of the neighbor list (0 for maximum)"); //$NON-NLS-1$

    Configurable.printKey(EdgeGreedyHeuristic.PARAM_AUGMENTATION, ps);
    ps.println("tour augmentation (if neighbor list too short) by first candidate edge (true) or by best edge (false)"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final void solve(final ObjectiveFunction f,
      final Individual<int[]> dest) {
    final int n;
    int neighListLen, i, j, d, bestDist, needed;
    int[] res;
    UndirectedEdgePath path;
    EdgePathElement a, b, best;
    UndirectedPriorityEdgeNeighborList<PriorityEdge> arr;
    long length;

    n = f.n();

    // Initialize: clear destination record and allocate result array. This
    // is
    // just book-keeping and has nothing to do with the algorithm.
    allocateRes: {
      if (dest != null) {
        res = dest.solution;
        dest.clearEvaluation();
        if ((res != null) && (res.length == n)) {
          break allocateRes;
        }
      }
      res = new int[n];
    }
    // ok, done initialization

    // The neighbor list length: A node can have at most n-1 neighbors. As
    // we
    // count each edge at most once and edge AB = BA, this actually may
    // waste
    // some space if the neighbor list length goes closer to n, but this
    // should
    // be a rare case. If we set the neighbor list length shorter, this may
    // lead to strange effects, so we keep it as is, as this should be fine
    // for
    // the normal case. The variable this.m_neighborListLength has two
    // possible
    // meanings: if it is 0, we will use n-1 as neighbor list length, i.e.,
    // the
    // maximum possible length. This will render the algorithm infeasible
    // for
    // large n, as, e.g., n=10000 would lead to 10000*10000=100000000
    // entries
    // in the neighbor list, each needing at least 20 bytes of memory,
    // leading
    // to 1.8 GiB memory requirement for that list alone. The other,
    // recommended, way to set this.m_neighborListLength is to choose a
    // small
    // value, say around 20, which will lead to a moderate size edge list.
    neighListLen = n - 1;
    if (this.m_neighborListLength > 0) {
      neighListLen = Math.max(1,
          Math.min(neighListLen, this.m_neighborListLength));
    }

    // allocate the necessary data structures
    arr = this.m_edgeList;
    arr.clear();
    path = this.m_path;
    path.clear();

    // We now loop through all possible undirected edges and see if they
    // will
    // make it into the neighbor list. The decision criterion here is only
    // the
    // edge length dist(i,j), subject to minimization. For each node, only
    // the
    // best neighListLen edges are kept. This has complexity O(n^2 *
    // insertion), where insertion is the complexity of inserting an edge
    // into
    // the neighbor list. This complexity is somewhat in O(neighListLen +
    // log_2
    // neighListLen), so we can consider it a constant for small
    // neighListLen.
    for (i = 1; i < n; i++) {
      for (j = (i + 1); j <= n; j++) {
        arr.checkIn(i, j, f.distance(i, j));
      }
    }

    // Obtain the list of sorted edges and let us choose the edges that we
    // want
    // to use in the solution.

    // Basically, we want n-1 edges to build a non-cyclic path whose ends
    // then
    // can be connected to the root node n. This means that each node in
    // the
    // path can have at most degree 2 (i.e., 2 neighbors) and should, at
    // the
    // end, have at least degree 1. While we collect the n-2 edges, we can
    // also
    // compute the length of that path on the fly.
    length = 0l;
    needed = (n - 1);
    looper: for (final PriorityEdge edge : arr.getEdgeArray()) {
      if (edge == null) {
        // ok: the edge list was not complete. This may happen since it
        // will
        // contain each edge only once.
        break looper;
      }

      // let us see if this edge can be added, or if it would cause a
      // cycle
      if (path.tryAddToPath(edge)) {
        // ok, the edge was added - let's update the length
        length += edge.h;

        if ((--needed) <= 0) {// if we have n-2 edges...
          break looper;// ...we can quit!
        }
      }
    }

    // So, we have finished collecting edges, without creating any cycle --
    // but
    // did we get enough edges? It may be that the edge lists where to
    // short
    // to
    // find such a path.
    for (; needed > 0; needed--) {
      // OK, we did not get enough edges. In other words, the neighListLen
      // neighbor nodes we kept in the neighbor lists were not enough and
      // using
      // only the resulting edges has necessarily resulted in cycles.
      // Therefore, let's augment the path. This means we have to find
      // edges
      // that can be added to the edge set without causing cycles. We have
      // to
      // do this until we have n-1 edges in total. We have two policies
      // for
      // that: 1) use shortest edges we can find
      // (this.m_augmentByAddingFirstCandidate == false) or just use any
      // first
      // best edge we find (m_augmentByAddingFirstCandidate == true). The
      // former will cost more distance evaluations and take more time,
      // the
      // later will more likely result in worse solutions. In any case,
      // this
      // will lead to more distance evaluations, which we will have to
      // pay.

      // In this loop, we try to discover the remaining "needed" edges.
      // This
      // has complexity O(n^2), as we potentially need to check all
      // possible
      // edges again.

      // augmentation will always happen at one of the ends of the path
      a = path.getEnd();

      best = null; // we don't know which is the best node
      bestDist = Integer.MAX_VALUE; // set distance to max
      b = null; // candidate must be null for first iteration

      // loop over potential partners
      find: while ((b = path.findNextAugmentationCandidate(a, b)) != null) {

        // ok, the edge can potentially be added - so let's compute its
        // length
        d = f.distance(a.node, b.node);

        if ((best == null) || (d < bestDist)) {
          // This edge is a new shortest edge (or the first edge we
          // found)
          bestDist = d;
          best = b;

          if (this.m_augmentByAddingFirstCandidate) {
            // OK, we voted for just adding some edge, not
            // necessarily the
            // best, so let's quit here
            break find;
          }
        }
      }

      path.tryAddToPath(a, best); // add the edge - must always work
      length += bestDist; // update path length
    }

    // By now, we have n-1 edges which do not form a cycle. This means we
    // can
    // construct the solution. Our problem is that the edges are undirected
    // and
    // that we don't know how to bring them into the right order to get a
    // node
    // sequence (permutation) - so we have to do that now via the
    // UndirectedEdgePath utility.

    a = path.getEnd();// let's start at the end of the path
    needed = 0; // needed now will be the number of nodes already added

    // In this loop, let's traverse over the path
    do {
      res[needed++] = a.node;// add node a to path
      a = a.next();
    } while (a != null);

    length += f.distance(res[0], res[needed - 1]);// add distance back
    f.registerFE(res, length); // register solution quality

    // output the result if destination was provided
    if (dest != null) {
      dest.solution = res;
      dest.tourLength = length;
      dest.producer = this;
    }
  }

  /** {@inheritDoc} */
  @Override
  public EdgeGreedyHeuristic clone() {
    EdgeGreedyHeuristic h;

    h = ((EdgeGreedyHeuristic) (super.clone()));
    h.m_path = null;
    h.m_edgeList = null;

    return h;
  }

  /**
   * Perform the edge greedy heuristic.
   * 
   * @param args
   *          the command line arguments
   */
  public final static void main(final String[] args) {
    TSPAlgorithmRunner.benchmark(Instance.SYMMETRIC_INSTANCES,
        EdgeGreedyHeuristic.class,//
        args);
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    final int n;
    int neighListLen;

    super.beginRun(f);

    n = f.n();

    // The neighbor list length: A node can have at most n-1 neighbors. As
    // we
    // count each edge at most once and edge AB = BA, this actually may
    // waste
    // some space if the neighbor list length goes closer to n, but this
    // should
    // be a rare case. If we set the neighbor list length shorter, this may
    // lead to strange effects, so we keep it as is, as this should be fine
    // for
    // the normal case. The variable this.m_neighborListLength has two
    // possible
    // meanings: if it is 0, we will use n-1 as neighbor list length, i.e.,
    // the
    // maximum possible length. This will render the algorithm infeasible
    // for
    // large n, as, e.g., n=10000 would lead to 10000*10000=100000000
    // entries
    // in the neighbor list, each needing at least 20 bytes of memory,
    // leading
    // to 1.8 GiB memory requirement for that list alone. The other,
    // recommended, way to set this.m_neighborListLength is to choose a
    // small
    // value, say around 20, which will lead to a moderate size edge list.
    neighListLen = n - 1;
    if (this.m_neighborListLength > 0) {
      neighListLen = Math.max(1,
          Math.min(neighListLen, this.m_neighborListLength));
    }

    // allocate the necessary data structures
    this.m_edgeList = new UndirectedPriorityEdgeNeighborList<>(n,
        neighListLen);

    // We allocate the data structure for successively building a path
    this.m_path = new UndirectedEdgePath(n);
  }

  /** {@inheritDoc} */
  @Override
  public void endRun(final ObjectiveFunction f) {
    this.m_edgeList = null;
    this.m_path = null;
    super.endRun(f);
  }
}
