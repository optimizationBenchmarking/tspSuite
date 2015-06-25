package org.logisticPlanning.tsp.solving.algorithms.localSearch.satelliteList.LKW;

import java.io.PrintStream;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.TSPLocalSearchAlgorithm;
import org.logisticPlanning.tsp.solving.operators.permutation.creation.PermutationCreateCanonical;
import org.logisticPlanning.tsp.solving.utils.candidates.CandidateSet;
import org.logisticPlanning.tsp.solving.utils.satelliteList.UndoableSatelliteList;
import org.logisticPlanning.tsp.solving.utils.satelliteList.UndoableSatelliteNode;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * <p>
 * A interative deepening variant of the <a
 * href="http://en.wikipedia.org/wiki/Lin%E2%80%93Kernighan_heuristic"
 * >Lin-Kerninghan algorithm</a>&nbsp;[<a href="#cite_LK1973AEHAFTTSP"
 * style="font-weight:bold">1</a>, <a href="#cite_JMG2004EAOHFTS"
 * style="font-weight:bold">2</a>] which uses the
 * {@link org.logisticPlanning.tsp.solving.utils.satelliteList.SatelliteList
 * satellite list} representation. The algorithm apply the Lin-Kerninghan
 * approach recursively and performs backtracking in each recursion level.
 * The recursion depth is limited and the limit of depth may step-by-step
 * be increased in a randomized fashion if no improvements can be found. As
 * soon as an improvement is found, the depth limit is reset to its initial
 * value. If the depth cannot be increased anymore (and with a certain
 * probability if no improvement is found), the best solution discovered so
 * far is taken and a large fraction of it is shuffled and the depth limit
 * is reset.
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_LK1973AEHAFTTSP" />Shen Lin and&nbsp;Brian
 * Wilson Kernighan: <span style="font-weight:bold">&ldquo;An Effective
 * Heuristic Algorithm for the Traveling-Salesman Problem,&rdquo;</span> in
 * <span style="font-style:italic;font-family:cursive;">Operations Research
 * (Oper. Res.)</span> 21(2):498&ndash;516, March&ndash;April 1973;
 * published by Linthicum, ML, USA: Institute for Operations Research and
 * the Management Sciences (INFORMS) and&nbsp;Cambridge, MA, USA: HighWire
 * Press (Stanford University). LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/66099702">66099702</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1287/opre.21.2.498"
 * >10.1287/opre.21.2.498</a>; JSTOR stable:&nbsp;<a
 * href="http://www.jstor.org/stable/169020">169020</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/2394608">2394608</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/0030364X">0030-364X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15265463">1526-5463</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=OPREAI"
 * >OPREAI</a>. <div>link: [<a
 * href="https://en.wikipedia.org/wiki/Lin%E2%80%93Kernighan_heuristic"
 * >1</a>]</div></div></li>
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
 * </ol>
 *
 * @since TSP Suite/0.9.8
 */
public class LK_ID extends TSPLocalSearchAlgorithm<int[]> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * the default number of candidates
   *
   * @see #CONFIG_CANDIDATE_COUNT
   * @see #PARAM_CANDIDATE_COUNT
   * @see #m_candidateCount
   * @see #m_candidates
   */
  public static final int DEFAULT_CANDIDATE_COUNT = -1;

  /**
   * the parameter governing the number of nearest neighbors in the
   * candidate list to per node: {@value} , {@code -1} means use all nodes
   * as candidates
   *
   * @see #CONFIG_CANDIDATE_COUNT
   * @see #DEFAULT_CANDIDATE_COUNT
   * @see #m_candidateCount
   * @see #m_candidates
   */
  public static final String PARAM_CANDIDATE_COUNT = "lkidCandidateCount"; //$NON-NLS-1$

  /**
   * the actual size of the candidate list: {@value}
   *
   * @see #DEFAULT_CANDIDATE_COUNT
   * @see #PARAM_CANDIDATE_COUNT
   * @see #m_candidateCount
   * @see #m_candidates
   */
  public static final String CONFIG_CANDIDATE_COUNT = "lkidConfigUsedCandidateCount"; //$NON-NLS-1$

  /**
   * the start max depth for recursion: the algorithm may initially add and
   * remove at most <em>{@value}+1</em> edges.
   *
   * @see #PARAM_START_MAX_RECURSION_DEPTH
   * @see #m_startMaxDepth
   */
  public static final int DEFAULT_START_MAX_RECURSION_DEPTH = 1;

  /**
   * the parameter with the initial maximum recursion depth: {@value}
   *
   * @see #DEFAULT_START_MAX_RECURSION_DEPTH
   * @see #m_startMaxDepth
   */
  public static final String PARAM_START_MAX_RECURSION_DEPTH = "lkidStartMaxRecursionDepth";//$NON-NLS-1$

  /**
   * the maximum max depth for recursion: the algorithm may add and remove
   * at most <em>{@value}+1</em> edges.
   *
   * @see #PARAM_MAXIMUM_MAX_RECURSION_DEPTH
   * @see #m_maxMaxDepth
   */
  public static final int DEFAULT_MAX_MAX_RECURSION_DEPTH = 20;

  /**
   * the parameter with the maximum maximum recursion depth: {@value}
   *
   * @see #DEFAULT_MAX_MAX_RECURSION_DEPTH
   * @see #m_maxMaxDepth
   */
  public static final String PARAM_MAXIMUM_MAX_RECURSION_DEPTH = "lkidMaxMaxRecursionDepth";//$NON-NLS-1$

  /**
   * The probability to increase the search depth if no improvement could
   * be found.
   *
   * @see #PARAM_DEPTH_INCREASING_PROBABILITY
   * @see #m_depthIncreasingProbability
   */
  public static final double DEFAULT_DEPTH_INCREASING_PROBABILITY = 0.8d;

  /**
   * the parameter governing the depth increasing probability: {@value}
   *
   * @see #DEFAULT_DEPTH_INCREASING_PROBABILITY
   * @see #m_depthIncreasingProbability
   */
  public static final String PARAM_DEPTH_INCREASING_PROBABILITY = "lkidDepthIncreasingProbability";//$NON-NLS-1$

  /** the number of candidates */
  private int m_candidateCount;

  /** the starting max depth */
  private int m_startMaxDepth;

  /** the maximum max depth */
  private int m_maxMaxDepth;

  /** the probability to increase the depth */
  private double m_depthIncreasingProbability;

  /* The per-run data */

  /** the real maximum max depth for the current problem */
  private transient int m_realMaxMaxDepth;

  /** the real minimum max depth for the current problem */
  private transient int m_realMinMaxDepth;

  /** the nodes */
  private transient UndoableSatelliteList<UndoableSatelliteNode> m_nodes;

  /** the candidate set */
  private transient CandidateSet m_candidates;

  /** the objective function */
  private transient ObjectiveFunction m_f;

  /** the current max recursion depth */
  private transient int m_currentMaxDepth;

  /** the solution destination */
  private transient Individual<int[]> m_solution;
  /** the choices for a start */
  private transient int[] m_aStartChoices;
  /** the temporary solution permutation */
  private transient int[] m_tempSolution;

  /** the node sequence */
  private transient UndoableSatelliteNode m_a;

  /** the number of candidates */
  private transient int m_m;

  /** instantiate */
  public LK_ID() {
    super("Iterative Deepening Lin-Kerninghan Heuristic");//$NON-NLS-1$

    this.m_candidateCount = LK_ID.DEFAULT_CANDIDATE_COUNT;
    this.m_startMaxDepth = LK_ID.DEFAULT_START_MAX_RECURSION_DEPTH;
    this.m_maxMaxDepth = LK_ID.DEFAULT_MAX_MAX_RECURSION_DEPTH;
    this.m_depthIncreasingProbability = LK_ID.DEFAULT_DEPTH_INCREASING_PROBABILITY;
  }

  /** {@inheritDoc} */
  @Override
  public LK_ID clone() {
    LK_ID result;

    result = ((LK_ID) (super.clone()));
    result.__clear();
    return result;
  }

  /** clear this instance */
  private final void __clear() {
    this.m_candidates = null;
    this.m_nodes = null;
    this.m_candidates = null;
    this.m_f = null;
    this.m_currentMaxDepth = 0;
    this.m_solution = null;
    this.m_a = null;
    this.m_m = 0;
    this.m_aStartChoices = null;
  }

  /**
   * Perform the VNS
   *
   * @param args
   *          the command line arguments
   */
  public final static void main(final String[] args) {
    TSPAlgorithmRunner.benchmark(//
        Instance.SYMMETRIC_INSTANCES, LK_ID.class,//
        args);
  }

  /** {@inheritDoc} */
  @Override
  public void localSearch(final Individual<int[]> srcdst,
      final ObjectiveFunction f) {
    final int n; // the number of nodes in the problem
    final int[] aStartPerm; // the permutation
    final Randomizer r; // the randomizer
    UndoableSatelliteList<UndoableSatelliteNode> nodes; // the nodes
    int aStartChoices, aIdx, aVal;
    boolean foundImprovement;

    n = f.n();
    this.m_f = f;
    r = f.getRandom();

    this.m_solution = srcdst;

    // the permutation of start nodes
    aStartPerm = this.m_aStartChoices;

    // Transform the solution to an array of nodes holding a doubly linked
    // list.
    nodes = this.m_nodes;
    nodes.fromPath(srcdst.solution);

    // Set start recursion depth
    this.m_currentMaxDepth = this.m_realMinMaxDepth;

    // Now we enter the main loop of the search.
    mainLoop: while (!(f.shouldTerminate())) {// begin "main loop"
      foundImprovement = false;

      aStartChoices: for (aStartChoices = n; aStartChoices > 0;) {
        // we iterate over all possible start nodes "a"
        aIdx = r.nextInt(aStartChoices--);
        aVal = aStartPerm[aIdx];
        aStartPerm[aIdx] = aStartPerm[aStartChoices];
        aStartPerm[aStartChoices] = aVal;

        this.m_a = nodes.getNode(aVal);

        if (this.__recurse(0, this.m_a, 0l)) {
          foundImprovement = true; // we got an improvement!
          if (this.m_currentMaxDepth > this.m_realMinMaxDepth) {
            break aStartChoices; // we break only if we have
            // recursed deeper
          }
        }

        if (f.shouldTerminate()) {
          break mainLoop;
        }
      }

      // ok, one recursion was finished.
      if (foundImprovement) {
        // if we have recursed deeper, reset depth
        if (this.m_currentMaxDepth > this.m_realMinMaxDepth) {
          this.m_currentMaxDepth = this.m_realMinMaxDepth;
        }
      } else { // no improvement was found
        if ((r.nextDouble() >= this.m_depthIncreasingProbability) || //
            (this.m_currentMaxDepth >= this.m_realMaxMaxDepth)) {
          // OK, we quit the local search loop here and let the
          // mainLoop
          // procedure decide what to do next. If this algorithm is
          // used as
          // local search, it will probably obtain a copy of the best
          // solution,
          // perturb it, and again invoke this method here. If it is
          // used as
          // unary search operator, it may or may not exit.
          break mainLoop;
        }
        this.m_currentMaxDepth++; // increase the recursion depth
      }
    }// end "main loop"

    this.m_solution = null;
  }

  /**
   * recurse
   *
   * @param depth
   *          the depth
   * @param a
   *          the node a
   * @param gain
   *          the current gain
   * @return a flag indicating the outcome
   */
  private final boolean __recurse(final int depth,
      final UndoableSatelliteNode a, final long gain) {
    long bgain, cgain;
    int bChoice, cChoice;
    boolean closeArc;
    UndoableSatelliteNode b, c;

    closeArc = (depth >= this.m_currentMaxDepth);
    for (bChoice = 1; bChoice >= 0; bChoice--) {
      b = a.getNeighbor(bChoice);

      if ((b == null)
          || (b == a)//
          || (b.numberOfPendingAdditions() > 1)
          || (b.numberOfPendingDeletions() > 1)) {
        continue;
      }

      bgain = (gain + this.m_f.distance(a.id, b.id));
      for (cChoice = 1; cChoice <= this.m_m; cChoice++) {

        if (closeArc) {
          c = this.m_a;
        } else {
          c = this.m_nodes.getNode(this.m_candidates.getCandidate(b.id,
              cChoice));
          if (c == this.m_a) {
            continue;
          }
        }
        if ((c == null) || (c == a) || (c == b)//
            || (c.numberOfPendingAdditions() > 1) || //
            b.isRelated(c)) {
          continue;
        }

        cgain = bgain - this.m_f.distance(b.id, c.id);
        if (cgain <= 0l) {
          continue;
        }

        a.doDisconnect(b);
        b.doDisconnect(a);
        b.doConnect(c);
        c.doConnect(b);

        if (c == this.m_a) {
          if (this.m_nodes.toPath(this.m_tempSolution)) {
            this.m_solution.tourLength -= cgain;
            final int[] swap = this.m_solution.solution;
            this.m_solution.solution = this.m_tempSolution;
            this.m_tempSolution = swap;
            this.m_f.registerFE(this.m_solution.solution,
                this.m_solution.tourLength);
            c.commit();
            b.commit();
            return true;
          }
        } else {
          if (depth < this.m_currentMaxDepth) {
            if (c.numberOfPendingDeletions() < 2) {
              if (this.__recurse(depth + 1, c, cgain)) {
                c.commit();
                b.commit();
                return true;
              }
            }
          }
        }

        if (this.m_f.shouldTerminate()) {
          return false;
        }

        c.undoConnect(b);
        b.undoConnect(c);
        b.undoDisconnect(a);
        a.undoDisconnect(b);

        if (closeArc) {
          break;
        }
      }
    }

    return false;
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable.printKey(LK_ID.PARAM_CANDIDATE_COUNT, ps);
    ps.println(this.m_candidateCount);

    if (this.m_candidates != null) {
      Configurable.printKey(LK_ID.CONFIG_CANDIDATE_COUNT, ps);
      ps.println(this.m_candidates.m());
    }

    Configurable.printKey(LK_ID.PARAM_START_MAX_RECURSION_DEPTH, ps);
    ps.println(this.m_startMaxDepth);

    Configurable.printKey(LK_ID.PARAM_DEPTH_INCREASING_PROBABILITY, ps);
    ps.println(this.m_depthIncreasingProbability);

    Configurable.printKey(LK_ID.PARAM_MAXIMUM_MAX_RECURSION_DEPTH, ps);
    ps.println(this.m_maxMaxDepth);
  }

  /** {@inheritDoc} */
  @Override
  public void printParameters(final PrintStream ps) {
    super.printParameters(ps);

    Configurable.printKey(LK_ID.PARAM_CANDIDATE_COUNT, ps);
    ps.println("the number of nearest neighbors in the candidate sets per node"); //$NON-NLS-1$

    Configurable.printKey(LK_ID.PARAM_START_MAX_RECURSION_DEPTH, ps);
    ps.println("the start depth limit of the recursion"); //$NON-NLS-1$

    Configurable.printKey(LK_ID.PARAM_DEPTH_INCREASING_PROBABILITY, ps);
    ps.println("the probability to increase the recursion depth if no improvement is found"); //$NON-NLS-1$

    Configurable.printKey(LK_ID.PARAM_MAXIMUM_MAX_RECURSION_DEPTH, ps);
    ps.println("the final recursion depth limit"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public void configure(final Configuration config) {
    super.configure(config);

    this.m_candidateCount = config.getInt(LK_ID.PARAM_CANDIDATE_COUNT, -1,
        1000000, this.m_candidateCount);

    this.m_maxMaxDepth = config.getInt(
        LK_ID.PARAM_MAXIMUM_MAX_RECURSION_DEPTH, 1, 100000,
        this.m_maxMaxDepth);
    this.m_startMaxDepth = config.getInt(
        LK_ID.PARAM_START_MAX_RECURSION_DEPTH, 1, this.m_maxMaxDepth,
        this.m_startMaxDepth);
    this.m_depthIncreasingProbability = config.getDouble(
        LK_ID.PARAM_DEPTH_INCREASING_PROBABILITY, 0d, 1d,
        this.m_depthIncreasingProbability);
  }

  /**
   * Set the maximum max depth
   *
   * @param i
   *          the value
   */
  public final void setMaxMaxRecursionDepth(final int i) {
    if (i <= 0) {
      this.m_maxMaxDepth = LK_ID.DEFAULT_MAX_MAX_RECURSION_DEPTH;
    } else {
      this.m_maxMaxDepth = i;
    }
  }

  /**
   * Set the start max depth
   *
   * @param i
   *          the value
   */
  public final void setStartMaxRecursionDepth(final int i) {
    final int z;
    if (i <= 0) {
      z = LK_ID.DEFAULT_START_MAX_RECURSION_DEPTH;
    } else {
      z = i;
    }
    if (z > this.m_maxMaxDepth) {
      throw new IllegalArgumentException();
    }
    this.m_startMaxDepth = z;
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    final int n;

    super.beginRun(f);

    this.m_f = f;

    this.m_candidates = CandidateSet.allocate(f, this.m_candidateCount,
        null);
    this.m_m = this.m_candidates.m();

    n = f.n();
    this.m_tempSolution = new int[n];
    this.m_aStartChoices = PermutationCreateCanonical.canonical(n);
    this.m_nodes = new UndoableSatelliteList<>(n);

    // Set the recursion depth limits. We start with at least 2-opt.
    this.m_realMaxMaxDepth = Math.min((n - 2), this.m_maxMaxDepth);
    this.m_realMinMaxDepth = Math.min(Math.max(1, this.m_startMaxDepth),
        this.m_realMaxMaxDepth);
  }

  /** {@inheritDoc} */
  @Override
  public void endRun(final ObjectiveFunction f) {
    this.__clear();
    super.endRun(f);
  }
}
