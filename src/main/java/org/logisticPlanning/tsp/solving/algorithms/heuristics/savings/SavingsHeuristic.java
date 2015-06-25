package org.logisticPlanning.tsp.solving.algorithms.heuristics.savings;

import java.io.PrintStream;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.algorithms.heuristics.TSPHeuristicWithStartNode;
import org.logisticPlanning.tsp.solving.utils.path.EdgePathElement;
import org.logisticPlanning.tsp.solving.utils.path.UndirectedEdgePath;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;

/**
 * <p>
 * The savings heuristic&nbsp;[<a href="#cite_CW1964SOVFACDTANODP"
 * style="font-weight:bold">1</a>, <a href="#cite_JMB1997TTSPACSILO"
 * style="font-weight:bold">2</a>, <a href="#cite_JMG2004EAOHFTS"
 * style="font-weight:bold">3</a>] was developed by Clarke and
 * Wright&nbsp;[<a href="#cite_CW1964SOVFACDTANODP"
 * style="font-weight:bold">1</a>] for a single-depot capacitated vehicle
 * routing problem (VRP). It can be applied to the Traveling Salesman
 * Problem as well, since this problem is basically a VRP with infinite
 * capacities, if we randomly select one node to be the depot&nbsp;[<a
 * href="#cite_JMB1997TTSPACSILO" style="font-weight:bold">2</a>, <a
 * href="#cite_JMG2004EAOHFTS" style="font-weight:bold">3</a>]. We
 * implement it for symmetric TSPs. The idea of this heuristic is as
 * follows: We first randomly choose a node to be the root/depot. This
 * choice can either always be node {@code 1} (if
 * <code>{@link #isUsingRandomDepot()}==false</code>) or a truly randomly
 * picked node (if <code>{@link #isUsingRandomDepot()}==true</code>). We
 * from now on refer to that node as the {@code depot}. Then, we imagine
 * one single tour for each one of the {@code n-1} remaining nodes. Each
 * such tour starts at the {@code depot}, visits the node, and returns to
 * the {@code depot}. We now iteratively try to find the cheapest ways to
 * combine two tours. This is done by finding cheap edges that can directly
 * connect the end nodes of two tours by replacing one trip to the depot in
 * each. We can find at most {@code n-2} such edges without creating a
 * cycle and the solution to the TSP is complete.
 * <p>
 * <p>
 * In an algorithm implementation, this means that we find all edges (i,j)
 * that connect two of the {@code n-1} nodes that are not the {@code depot}
 * node, where {@code n} is the total number of nodes. These edges are
 * sorted according to
 * {@code h(i,j) = dist(i,j) - dist(i, depot) - dist(j, depot)}, where
 * {@code dist(i,j)} corresponds to the
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#distance(int, int)
 * distance} function provided by the
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction
 * objective function}. We will then choose {@code n-2} such edges into an
 * edge list, starting at those with the smallest values of {@code h(i,j)}.
 * However, we only add those edges to the edge list that do not create a
 * cycle/loop with the already chosen ones. There must be exactly
 * {@code n-2} such (cheapest) edges, because then each of {@code n-1}
 * non-depot nodes is either connected to two edges (has degree {@code 2})
 * or is one of the two ends of the tour and is connected only to one edge
 * (has degree {@code 1}). This path can then be connected to the depot, by
 * adding the two edges from its end to the {@code depot} node {@code 1}.
 * Then we have a complete tour.
 * </p>
 * <p>
 * Basically, the savings heuristic works similar to the
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.edgeGreedy.EdgeGreedyHeuristic
 * edge-greedy heuristic}. Although empirically it often performs
 * better&nbsp;[<a href="#cite_JMG2004EAOHFTS"
 * style="font-weight:bold">3</a>, <a href="#cite_JMB1997TTSPACSILO"
 * style="font-weight:bold">2</a>], it has a worse worst-case solution
 * quality. The savings heuristic never finds solutions worse than
 * {@code 1+log n} times the global optimum&nbsp;[<a
 * href="#cite_OM1984WCAOTTSH" style="font-weight:bold">4</a>, <a
 * href="#cite_F1979WCAOAFTSP" style="font-weight:bold">5</a>, <a
 * href="#cite_JMG2004EAOHFTS" style="font-weight:bold">3</a>, <a
 * href="#cite_JMB1997TTSPACSILO" style="font-weight:bold">2</a>].
 * </p>
 * <p>
 * There is one problem with the edge sorting method, though: To sort all
 * edges which are not incident to the depot means to sort
 * {@code (n-1)*(n-2)/2} edges, which is in {@code O(n^2)}. In other words,
 * we would need to keep {@code 49985001} items in the list if we have
 * {@code n=10000} nodes. This will lead to too much memory consumption for
 * larger {@code n}. Therefore, we implement the savings heuristic by using
 * a bounded
 * {@link org.logisticPlanning.tsp.solving.utils.edge.UndirectedPriorityEdgeNeighborList
 * neighbor list}, as mentioned in&nbsp;[<a href="#cite_JMB1997TTSPACSILO"
 * style="font-weight:bold">2</a>, <a href="#cite_JMG2004EAOHFTS"
 * style="font-weight:bold">3</a>]. In other words, we create a
 * {@link org.logisticPlanning.tsp.solving.utils.edge.UndirectedPriorityEdgeNeighborList
 * list} of {@link #m_neighborListLength} nearest neighbors for each node
 * according to the heuristic {@code h} and only consider the edges in that
 * list for the heuristic. This reduces the memory consumption somewhat in
 * {@code O(n*k)} - with the trade-off of potentially worse results.
 * While&nbsp;[<a href="#cite_JMB1997TTSPACSILO"
 * style="font-weight:bold">2</a>] suggest to set {@code k} to something in
 * between {@code 15} and {@code 40}, we follow the advise in&nbsp;[<a
 * href="#cite_JMG2004EAOHFTS" style="font-weight:bold">3</a>] of setting
 * it to {@code 20} by default. However, this is a configuration parameter
 * and may be adapted to the user's liking.
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_CW1964SOVFACDTANODP" />G. Clarke and&nbsp;J. W.
 * Wright: <span style="font-weight:bold">&ldquo;Scheduling of Vehicles
 * from a Central Depot to a Number of Delivery Points,&rdquo;</span> in
 * <span style="font-style:italic;font-family:cursive;">Operations Research
 * (Oper. Res.)</span> 12(4):568&ndash;581, July&ndash;August 1964;
 * published by Linthicum, ML, USA: Institute for Operations Research and
 * the Management Sciences (INFORMS) and&nbsp;Cambridge, MA, USA: HighWire
 * Press (Stanford University). LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/66099702">66099702</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1287/opre.12.4.568"
 * >10.1287/opre.12.4.568</a>; JSTOR stable:&nbsp;<a
 * href="http://www.jstor.org/stable/167703">167703</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/2394608">2394608</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/0030364X">0030-364X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15265463">1526-5463</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=OPREAI"
 * >OPREAI</a>. <div>link: [<a href=
 * "http://read.pudn.com/downloads160/doc/fileformat/721736/Scheduling%20of%20vehicles%20from%20a%20central%20depot%20to%20a%20number%20of%20delivery%20points.pdf"
 * >1</a>]</div></div></li>
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
 * <li><div><span id="cite_F1979WCAOAFTSP" /><a
 * href="http://www.math.cmu.edu/~af1p/">Alan M. Frieze</a>: <span
 * style="font-weight:bold">&ldquo;Worst-Case Analysis of Algorithms for
 * Travelling Salesman Problems,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Operations
 * Research-Verfahren (Methods of Operations Research)</span>
 * 32:93&ndash;112, 1979; edited by Rudolf Henn; published by Meisenheim am
 * Glan, Germany: Hain. Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=vxoIAQAAIAAJ">vxoIAQAAIAAJ</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/0173752X">0173-752X</a>. <div>link:
 * [<a href="http://www.math.cmu.edu/~af1p/Texfiles/GreedyTSP.pdf">1</
 * a>]</div></div></li>
 * </ol>
 */
public class SavingsHeuristic extends TSPHeuristicWithStartNode {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the default neighbor list length: {@value} */
  private static final int DEFAULT_NEIGHBOR_LIST_LENGTH = 20;

  /**
   * the neighbor list length parameter: {@value} , see
   * {@link #m_neighborListLength}
   */
  public static final String PARAM_NEIGHBOR_LIST_LENGTH = "savingsNeighborListLength";//$NON-NLS-1$

  /**
   * the tour augmentation parameter: {@value} , see
   * {@link #m_augmentByAddingFirstCandidate}
   */
  public static final String PARAM_AUGMENTATION = "savingsAugmentByFirstCandidate"; //$NON-NLS-1$

  /**
   * the neighbor list length
   *
   * @serial serializable field
   */
  private int m_neighborListLength;

  /**
   * augment missing edges with the first fitting edge discovered?
   *
   * @serial serializable field
   */
  public boolean m_augmentByAddingFirstCandidate;

  /** the root distances */
  private transient int[] m_rootDist;

  /** the edge list */
  private transient SavingsEdgeNeighborhoodList m_edgeList;

  /** the path */
  private transient UndirectedEdgePath m_path;

  /**
   * instantiate
   *
   * @param name
   *          the name
   */
  SavingsHeuristic(final String name) {
    super(name + "Savings Heuristic"); //$NON-NLS-1$
    this.m_neighborListLength = SavingsHeuristic.DEFAULT_NEIGHBOR_LIST_LENGTH;
    this.m_augmentByAddingFirstCandidate = false;
  }

  /** instantiate */
  public SavingsHeuristic() {
    this(""); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public void configure(final Configuration config) {
    super.configure(config);

    this.m_neighborListLength = config.getInt(
        SavingsHeuristic.PARAM_NEIGHBOR_LIST_LENGTH, 0, Integer.MAX_VALUE,
        this.m_neighborListLength);

    this.m_augmentByAddingFirstCandidate = config.getBoolean(
        SavingsHeuristic.PARAM_AUGMENTATION,
        this.m_augmentByAddingFirstCandidate);
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable.printKey(SavingsHeuristic.PARAM_NEIGHBOR_LIST_LENGTH, ps);
    ps.println(this.m_neighborListLength);

    Configurable.printKey(SavingsHeuristic.PARAM_AUGMENTATION, ps);
    ps.println(this.m_augmentByAddingFirstCandidate);
  }

  /** {@inheritDoc} */
  @Override
  public void printParameters(final PrintStream ps) {
    super.printParameters(ps);

    Configurable.printKey(SavingsHeuristic.PARAM_NEIGHBOR_LIST_LENGTH, ps);
    ps.println("the length of the neighbor list (0 for maximum)"); //$NON-NLS-1$

    Configurable.printKey(SavingsHeuristic.PARAM_AUGMENTATION, ps);
    ps.println("tour augmentation (if neighbor list too short) by first candidate edge (true) or by best edge (false)"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final void solve(final ObjectiveFunction f,
      final Individual<int[]> dest, final int depot) {
    final int n;
    int neighListLen, i, j, iO, jO, rdi, d, bestDist, heur, bestHeur, startH, needed;
    int[] res, rootDist;
    UndirectedEdgePath path;
    EdgePathElement a, b, best;
    SavingsEdgeNeighborhoodList arr;
    SavingsEdge temp;
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
    // ok, done initialization of destination data structures

    // The neighbor list length: A node cannot have the depot as neighbor
    // nor
    // itself, so it may have at most n-2 neighbors. As we count each edge
    // at
    // most once and edge AB = BA, this actually may waste some space if
    // the
    // neighbor list length goes closer to n, but this should be a rare
    // case.
    // If we set the neighbor list length shorter, this may lead to strange
    // effects, so we keep it as is, as this should be fine for the normal
    // case. The variable this.m_neighborListLength has two possible
    // meanings: if it is 0, we will use n-2 as neighbor list length, i.e.,
    // the
    // maximum possible length. This will render the algorithm infeasible
    // for
    // large n, as, e.g., n=10000 would lead to 10000*10000=100000000
    // entries
    // in the neighbor list, each needing at least 20 bytes of memory,
    // leading
    // to 1.8 GiB memory requirement for that list alone. The other,
    // recommended, way to set this.m_neighborListLength is to choose a
    // small value, say around 20, which will lead to a moderate size edge
    // list.
    neighListLen = n - 2;
    if (this.m_neighborListLength > 0) {
      neighListLen = Math.max(1,
          Math.min(neighListLen, this.m_neighborListLength));
    }

    // Here we allocate all necessary data structures. As this object will
    // keep
    // data structure instances in memory for re-use, we have to check if
    // the
    // structures we have fit to the problem (and, if not, allocate new
    // ones).
    i = (n - 1);
    rootDist = this.m_rootDist;
    path = this.m_path;
    path.clear();

    arr = this.m_edgeList;
    arr.clear();

    // Now we cache the distances to the root node "depot".
    // Normally, we will not do this in the logisticPlanning.org TSP
    // benchmarking environment, as we want to count each distance
    // evaluation.
    // However, since we have to cache (3*neighListLen*(n-1)) distances
    // anyway
    // (due to the neighbor lists), we can also cache the (n-1) root
    // distances
    // as well - as it does not change the runtime behavior much.

    for (i = 1; i < depot; i++) {
      rootDist[i - 1] = f.distance(depot, i);
    }
    for (i++; i <= n; i++) {
      rootDist[i - 2] = f.distance(depot, i);
    }

    // We now loop through all possible undirected edges (i,j) and see if
    // they
    // will make it into the neighbor list. The decision criterion here is:
    // dist(i,j) - dist(i, 1) - dist(j, 1), subject to minimization. For
    // each
    // node, only the best neighListLen edges are kept. This has complexity
    // O(n^2 * insertion), where insertion is the complexity of inserting
    // an
    // edge into the neighbor list. This complexity is somewhat in
    // O(neighListLen + log_2 neighListLen), so we can consider it a
    // constant
    // for small neighListLen.
    for (i = 1; i < n; i++) {
      if (i == depot) {
        continue;
      }
      iO = ((i > depot) ? (i - 1) : i);
      rdi = rootDist[iO - 1];
      for (j = (i + 1); j <= n; j++) {
        if (j == depot) {
          continue;
        }
        d = f.distance(i, j);
        jO = ((j > depot) ? (j - 1) : j);
        temp = arr.checkIn(iO, jO, d - rdi - rootDist[jO - 1]);
        if (temp != null) {
          temp.distance = d;
        }
      }
    }

    // Obtain the list of sorted edges and let us choose the edges that we
    // want
    // to use in the solution.

    // Basically, we want n-2 edges to build a non-cyclic path whose ends
    // then
    // can be connected to the root node n. This means that each node in
    // the
    // path can have at most degree 2 (i.e., 2 neighbors) and should, at
    // the
    // end, have at least degree 1. While we collect the n-2 edges, we can
    // also
    // compute the length of that path on the fly.
    length = 0l;
    needed = (n - 2);
    looper: for (final SavingsEdge edge : arr.getEdgeArray()) {
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
        length += edge.distance;

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
      // do this until we have n-2 edges in total. We have two policies
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
      startH = rootDist[a.node - 1];

      best = null; // we don't know which is the best node
      bestDist = bestHeur = Integer.MAX_VALUE; // set heuristic/dist to
      // max
      b = null; // candidate must be null for first iteration

      // loop over potential partners
      find: while ((b = path.findNextAugmentationCandidate(a, b)) != null) {

        // ok, the edge can potentially be added - so let's compute its
        // length
        d = f.distance((a.node >= depot) ? (a.node + 1) : a.node,//
            (b.node >= depot) ? (b.node + 1) : b.node);
        heur = (d - startH - rootDist[b.node - 1]);// get heuristic
        // value
        if ((best == null) || (heur < bestHeur)) {
          // This edge is a new shortest edge (or the first edge we
          // found)
          bestHeur = heur;
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

    // By now, we have n-2 edges which do not form a cycle. This means we
    // can
    // construct the solution. Our problem is that the edges are undirected
    // and
    // that we don't know how to bring them into the right order to get a
    // node
    // sequence (permutation) - so we have to do that now via the
    // UndirectedEdgePath utility..

    a = path.getEnd(); // let's start at the end of the path

    res[0] = depot;// Our tour starts at node 1, the depot
    length += rootDist[a.node - 1]; // we add the length to the first node
    needed = 1; // needed now will be the number of nodes already added

    // In this loop, let's traverse over the path
    do {
      i = a.node;
      res[needed++] = (i >= depot) ? (i + 1) : i;// add node a to path
      a = a.next();
    } while (a != null);

    // add distance back to the root (node 1)
    length += rootDist[i - 1];
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
  public SavingsHeuristic clone() {
    SavingsHeuristic h;

    h = ((SavingsHeuristic) (super.clone()));
    h.m_edgeList = null;
    h.m_path = null;
    h.m_rootDist = null;

    return h;
  }

  /**
   * Perform the savings heuristic.
   *
   * @param args
   *          the command line arguments
   */
  public static void main(final String[] args) {
    TSPAlgorithmRunner.benchmark(Instance.SYMMETRIC_INSTANCES,
        SavingsHeuristic.class,//
        args);
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    final int n, i;
    int neighListLen;

    super.beginRun(f);

    n = f.n();

    // The neighbor list length: A node cannot have the depot as neighbor
    // nor
    // itself, so it may have at most n-2 neighbors. As we count each edge
    // at
    // most once and edge AB = BA, this actually may waste some space if
    // the
    // neighbor list length goes closer to n, but this should be a rare
    // case.
    // If we set the neighbor list length shorter, this may lead to strange
    // effects, so we keep it as is, as this should be fine for the normal
    // case. The variable this.m_neighborListLength has two possible
    // meanings: if it is 0, we will use n-2 as neighbor list length, i.e.,
    // the
    // maximum possible length. This will render the algorithm infeasible
    // for
    // large n, as, e.g., n=10000 would lead to 10000*10000=100000000
    // entries
    // in the neighbor list, each needing at least 20 bytes of memory,
    // leading
    // to 1.8 GiB memory requirement for that list alone. The other,
    // recommended, way to set this.m_neighborListLength is to choose a
    // small value, say around 20, which will lead to a moderate size edge
    // list.
    neighListLen = n - 2;
    if (this.m_neighborListLength > 0) {
      neighListLen = Math.max(1,
          Math.min(neighListLen, this.m_neighborListLength));
    }

    // Here we allocate all necessary data structures. As this object will
    // keep
    // data structure instances in memory for re-use, we have to check if
    // the
    // structures we have fit to the problem (and, if not, allocate new
    // ones).
    i = (n - 1);

    this.m_rootDist = new int[i];
    this.m_path = new UndirectedEdgePath(i);

    // The SavingsEdgeNeighborhoodList is an internal class that keeps
    // track
    // of the edges. It manages updating the edge list.
    this.m_edgeList = new SavingsEdgeNeighborhoodList(n, neighListLen);

  }

  /** {@inheritDoc} */
  @Override
  public void endRun(final ObjectiveFunction f) {
    this.m_rootDist = null;
    this.m_path = null;
    this.m_edgeList = null;
    super.endRun(f);
  }
}
