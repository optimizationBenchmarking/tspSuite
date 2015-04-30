package org.logisticPlanning.tsp.solving.operators.permutation.recombination;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsEdge;
import org.logisticPlanning.tsp.solving.operators.BinaryOperator;
import org.logisticPlanning.tsp.solving.utils.path.EdgePathElement;
import org.logisticPlanning.tsp.solving.utils.path.UndirectedEdgePath;

/**
 * <p>
 * A crossover operator which is based on the
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic
 * savings heuristic}. The savings heuristic&nbsp;[<a
 * href="#cite_CW1964SOVFACDTANODP" style="font-weight:bold">1</a>, <a
 * href="#cite_JMB1997TTSPACSILO" style="font-weight:bold">2</a>, <a
 * href="#cite_JMG2004EAOHFTS" style="font-weight:bold">3</a>] is a
 * heuristic for building tours. It starts by choosing a {@code depot}
 * node. We then consider separate tours for each other node, i.e., two
 * edges for each node {@code i}: an edge from the {@code depot} to
 * {@code i} and an edge back from {@code i} to the {@code depot}. Then,
 * iteratively, such tours are merged by replacing one edge to the depot in
 * two tours with a direct edge connection their non-depot ends. This is
 * done again and again until we have a complete solution. The choice which
 * connecting edge is to be added next is made according to a heuristic
 * {@code h(i,j) = dist(i,j) - dist(i, depot) - dist(j, depot)} , where
 * {@code dist(i,j)} corresponds to the
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#distance(int, int)
 * distance} function provided by the
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction
 * objective function}.
 * </p>
 * <p>
 * Normally, all available edges are sorted according to {@code h} and the
 * popped starting with the one with lowest heuristic value, which is added
 * if it does not create a cycle. Here, however, we only add the edges that
 * are already present in the two parent tours.
 * </p>
 * <p>
 * Both, for the normal heuristic as well a this crossover operator, if
 * bounded neighbor lists are used, there is a possibility that no complete
 * tour can be built this way, as adding some edges may cause cycles. In
 * the crossover operator, the maximum number of edges incident to a node
 * (and hence, the neighbor list length) can at most be 4. If a complete
 * tour could not be created, we augment it by adding the first edge we
 * find which does not cause a cycle to one of the tour's ends. Here, we
 * consider all possible edges, not just those present in the paren tours.
 * </p>
 * <p>
 * More information on the
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic
 * savings heuristic} can be found
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic
 * here}.
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
 * </ol>
 */
public class PermutationSavingsCrossover extends BinaryOperator<int[]> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the edge list */
  private transient SavingsEdge[] m_edgeList;

  /** the path */
  private transient UndirectedEdgePath m_path;

  /** the root distances */
  private transient int[] m_rootDist;

  /** instantiate the class */
  public PermutationSavingsCrossover() {
    super("PermutationSavingsCrossover"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final void recombine(final Individual<int[]> dest,
      final ObjectiveFunction f, final Individual<int[]> parent1,
      final Individual<int[]> parent2) {

    final int n, depot;
    int i, k, d, bestDist, heur, bestHeur, startH, needed;
    int[] res, rootDist, parent;
    UndirectedEdgePath path;
    EdgePathElement a, b, best;
    SavingsEdge[] arr;
    SavingsEdge temp;
    long length;

    n = f.n();
    depot = (f.getRandom().nextInt(n) + 1);

    // Initialize: clear destination record and allocate result array. This
    // is
    // just book-keeping and has nothing to do with the algorithm.
    dest.clearEvaluation();
    res = dest.solution;
    if ((res == null) || (res.length != n)) {
      dest.solution = res = new int[n];
    }
    // ok, done initialization of destination data structures

    // Here we allocate all necessary data structures. As this object will
    // keep
    // data structure instances in memory for re-use, we have to check if
    // the
    // structures we have fit to the problem (and, if not, allocate new
    // ones).
    rootDist = this.m_rootDist;
    arr = this.m_edgeList;
    path = this.m_path;
    path.clear();

    // Now we cache the distances to the root node "depot".
    for (i = 1; i < depot; i++) {
      rootDist[i - 1] = f.distance(depot, i);
    }
    for (i++; i <= n; i++) {
      rootDist[i - 2] = f.distance(depot, i);
    }

    // We now loop through all undirected edges (i,j) in the parents and
    // move
    // all of them into the neighbor list. The list may contain duplicates,
    // but
    // this does not matter.
    needed = 0;
    for (k = 2; k > 0; k--) {
      parent = ((k == 1) ? parent1 : parent2).solution;
      i = parent[n - 1];
      for (final int end : parent) {
        if ((end != depot) && (i != depot)) {
          temp = arr[needed++];
          temp.a = ((i > depot) ? (i - 1) : i);
          temp.b = ((end > depot) ? (end - 1) : end);
          temp.distance = f.distance(i, end);
          temp.h = (temp.distance - rootDist[temp.a - 1] - rootDist[temp.b - 1]);
        }
        i = end;
      }
    }

    if (needed != arr.length) {
      throw new IllegalStateException();
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
    looper: for (final SavingsEdge edge : arr) {
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
      // OK, we did not get enough edges. In other words, the 4
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
      while ((b = path.findNextAugmentationCandidate(a, b)) != null) {

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
    dest.tourLength = length;
    dest.producer = this;
  }

  /** {@inheritDoc} */
  @Override
  public final PermutationSavingsCrossover clone() {
    PermutationSavingsCrossover res;

    res = ((PermutationSavingsCrossover) (super.clone()));
    res.m_edgeList = null;
    res.m_path = null;
    res.m_rootDist = null;

    return res;
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    final int n;
    final SavingsEdge[] arr;
    int i;

    super.beginRun(f);

    n = f.n();

    // Here we allocate all necessary data structures. As this object will
    // keep
    // data structure instances in memory for re-use, we have to check if
    // the
    // structures we have fit to the problem (and, if not, allocate new
    // ones).
    i = (n - 1);

    this.m_rootDist = new int[i];
    this.m_path = new UndirectedEdgePath(i);

    // each of the n-1 nodes has 1 undirected edge in each of the two
    // parent
    // permutations
    i = ((i - 1) << 1);
    this.m_edgeList = arr = new SavingsEdge[i];
    for (; (--i) >= 0;) {
      arr[i] = new SavingsEdge();
    }
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
