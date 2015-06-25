package org.logisticPlanning.tsp.solving.algorithms.heuristics.nearestNeighbor;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.algorithms.heuristics.TSPHeuristicWithStartNode;
import org.logisticPlanning.tsp.solving.operators.permutation.creation.PermutationCreateCanonical;

/**
 * <p>
 * The nearest neighbor heuristic&nbsp;[<a href="#cite_JMB1997TTSPACSILO"
 * style="font-weight:bold">1</a>, <a href="#cite_JMG2004EAOHFTS"
 * style="font-weight:bold">2</a>] starts with (random or fixed) node as
 * initial partial tour. It then iteratively finds the nearest neighboring
 * node at the end of the tour and adds it until the tour is completed (all
 * nodes are present).
 * </p>
 * <p>
 * The nearest neighbor heuristic will never produce results which are
 * worse than {@code (1+ceil(log n))/2}&nbsp;[<a
 * href="#cite_RSL1977AAOSHFTTSP" style="font-weight:bold">3</a>, <a
 * href="#cite_RSSL2009AAOSHFTTSP" style="font-weight:bold">4</a>, <a
 * href="#cite_JMB1997TTSPACSILO" style="font-weight:bold">1</a>, <a
 * href="#cite_JMG2004EAOHFTS" style="font-weight:bold">2</a>].
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
 * <li><div><span id="cite_RSL1977AAOSHFTTSP" />Daniel J. Rosenkrantz,
 * Richard E. Stearns, and&nbsp;Philip M. Lewis II: <span
 * style="font-weight:bold">&ldquo;An Analysis of Several Heuristics for
 * the Traveling Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">SIAM Journal on
 * Computing</span> 6(3):563&ndash;581, 1977; published by Philadelphia,
 * PA, USA: Society for Industrial and Applied Mathematics (SIAM).
 * doi:&nbsp;<a
 * href="http://dx.doi.org/10.1137/0206041">10.1137/0206041</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/00975397">0097-5397</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/10957111">1095-7111</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=SMJCAT">
 * SMJCAT</a></div></li>
 * <li><div><span id="cite_RSSL2009AAOSHFTTSP" />Daniel J. Rosenkrantz,
 * Richard E. Stearns, and&nbsp;Philip M. Lewis II: <span
 * style="font-weight:bold">&ldquo;An Analysis of Several Heuristics for
 * the Traveling Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Fundamental Problems in
 * Computing: Essays in Honor of Professor Daniel J. Rosenkrantz</span>,
 * pages 45&ndash;69, S.S. Ravi and&nbsp;Sandeep K. Shukla, editors,
 * Dordrecht, Netherlands: Springer Netherlands. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781402096877"
 * >978-1-4020-9687-7</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/978-1-4020-9688-4_3">10.1007/978-
 * 1-4020-9688-4_3</a></div></li>
 * </ol>
 */
public class NearestNeighborHeuristic extends TSPHeuristicWithStartNode {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the list of nodes */
  private transient int[] m_tempList;

  /**
   * instantiate
   *
   * @param name
   *          the name prefix
   */
  NearestNeighborHeuristic(final String name) {
    super(name + "Nearest Neighbor Heuristic"); //$NON-NLS-1$
  }

  /** instantiate */
  public NearestNeighborHeuristic() {
    this(""); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final void solve(final ObjectiveFunction f,
      final Individual<int[]> dest, final int depot) {
    final int n;
    int rem, lastA, bestIdxA, bestA, bestLenA, tempLen, tempNode, i;
    final int[] temp;
    int[] res;
    long length;

    // allocate node list
    n = f.n();
    temp = this.m_tempList;
    PermutationCreateCanonical.makeCanonical(temp, n);

    // allocate result array
    alloc: {
      if (dest != null) {
        res = dest.solution;
        dest.clearEvaluation();
        if ((res != null) && (res.length == n)) {
          break alloc;
        }
      }
      res = new int[n];
    }

    length = 0l;

    rem = (n - 1);
    res[rem] = lastA = depot;
    temp[depot - 1] = temp[rem];
    bestIdxA = bestA = (-1);
    bestLenA = tempLen = Integer.MAX_VALUE;

    for (; rem > 0;) {

      // find the best end extensions
      for (i = rem; (--i) >= 0;) {
        tempNode = temp[i];

        tempLen = f.distance(lastA, tempNode);
        if (tempLen <= bestLenA) {
          bestLenA = tempLen;
          bestA = tempNode;
          bestIdxA = i;
        }
      }

      // ok, which end offers the better extension?

      res[--rem] = lastA = bestA; // store node
      length += bestLenA;// add new edge's length to total length
      temp[bestIdxA] = temp[rem];// make node unavailable for future
      // adding
      bestLenA = Integer.MAX_VALUE;
    }

    // add distance of edge connecting both tour ends
    length += f.distance(lastA, res[n - 1]);

    f.registerFE(res, length);

    if (dest != null) { // return the result
      dest.solution = res;
      dest.tourLength = length;
      dest.producer = this;
    }

  }

  /** {@inheritDoc} */
  @Override
  public NearestNeighborHeuristic clone() {
    NearestNeighborHeuristic h;

    h = ((NearestNeighborHeuristic) (super.clone()));
    h.m_tempList = null;

    return h;
  }

  /**
   * Perform the nearest-neighbor heuristic.
   *
   * @param args
   *          the command line arguments
   */
  public static void main(final String[] args) {
    TSPAlgorithmRunner.benchmark(Instance.SYMMETRIC_INSTANCES,
        NearestNeighborHeuristic.class,//
        args);
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    super.beginRun(f);
    this.m_tempList = new int[f.n()];
  }

  /** {@inheritDoc} */
  @Override
  public void endRun(final ObjectiveFunction f) {
    this.m_tempList = null;
    super.endRun(f);
  }
}
