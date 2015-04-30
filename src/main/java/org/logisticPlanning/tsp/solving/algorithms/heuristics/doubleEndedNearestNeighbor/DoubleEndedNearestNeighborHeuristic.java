package org.logisticPlanning.tsp.solving.algorithms.heuristics.doubleEndedNearestNeighbor;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.algorithms.heuristics.TSPHeuristicWithStartNode;
import org.logisticPlanning.tsp.solving.operators.permutation.creation.PermutationCreateCanonical;

/**
 * <p>
 * The double-ended nearest neighbor heuristic&nbsp;[<a
 * href="#cite_JMB1997TTSPACSILO" style="font-weight:bold">1</a>, <a
 * href="#cite_JMG2004EAOHFTS" style="font-weight:bold">2</a>] starts with
 * (random or fixed) node as initial partial tour. It then iteratively
 * finds the nearest neighboring nodes of each end of the tour and adds the
 * nearest (of the two) until the tour is completed (all nodes are
 * present).
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
 * </ol>
 */
public class DoubleEndedNearestNeighborHeuristic extends
    TSPHeuristicWithStartNode {
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
  DoubleEndedNearestNeighborHeuristic(final String name) {
    super(name + "Double-Ended Nearest Neighbor Heuristic"); //$NON-NLS-1$
  }

  /** instantiate */
  public DoubleEndedNearestNeighborHeuristic() {
    this(""); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final void solve(final ObjectiveFunction f,
      final Individual<int[]> dest, final int depot) {
    final int n, addB;
    final int[] temp;
    int aIdx, bIdx, rem, lastA, lastB, bestIdxA, bestA, bestLenA, bestIdxB, bestLenB, bestB, tempLen, tempNode, i;
    int[] res;
    long length;

    // allocate node list
    n = f.n();
    temp = this.m_tempList;
    PermutationCreateCanonical.makeCanonical(temp, n);

    // allocate result array
    alloc: {
      if (dest != null) {
        dest.clearEvaluation();
        res = dest.solution;
        if ((res != null) && (res.length == n)) {
          break alloc;
        }
      }
      res = new int[n];
    }

    length = 0l;

    aIdx = bIdx = 0;
    rem = addB = (n - 1);
    res[0] = lastA = lastB = depot;
    temp[depot - 1] = temp[rem];
    bestIdxA = bestIdxB = bestA = bestB = (-1);
    bestLenA = bestLenB = tempLen = Integer.MAX_VALUE;

    for (; rem > 0;) {

      // find the best end extensions
      for (i = rem; (--i) >= 0;) {
        tempNode = temp[i];

        // find the best node at end A
        if (bestA <= 0) {
          tempLen = f.distance(lastA, tempNode);
          if (tempLen <= bestLenA) {
            bestLenA = tempLen;
            bestA = tempNode;
            bestIdxA = i;
          }
        }

        // find the best node at end B (except in first round)
        if ((bestB <= 0) && (lastB != lastA)) {
          tempLen = f.distance(lastB, tempNode);
          if (tempLen <= bestLenB) {
            bestLenB = tempLen;
            bestB = tempNode;
            bestIdxB = i;
          }
        }
      }

      // ok, which end offers the better extension?
      if (bestLenA <= bestLenB) {// adding a node at end A seems to be
        // better
        aIdx++;// increase index
        res[aIdx] = lastA = bestA; // store node
        length += bestLenA;// add new edge's length to total length
        temp[bestIdxA] = temp[--rem];// make node unavailable for future
        // adding
        if (rem == bestIdxB) {// if the moved node is the best b option?
          bestIdxB = bestIdxA;// correct b option index
        }
        if (bestB == bestA) {// if the same node could be added at both
          // ends
          bestIdxB = bestB = (-1); // we also need to re-calculate b
          bestLenB = Integer.MAX_VALUE;
        }
        bestIdxA = bestA = (-1);// we need to re-calculate a
        bestLenA = Integer.MAX_VALUE;
      } else {// ok, best choice is end b
        bIdx = ((bIdx + addB) % n); // decrease index
        res[bIdx] = lastB = bestB; // store node
        length += bestLenB;// add new edge's length to total length
        temp[bestIdxB] = temp[--rem];// make node unavailable for future
        // adding
        if (rem == bestIdxA) {// if the moved node is the best a option?
          bestIdxA = bestIdxB;// correct a option index
        }
        if (bestB == bestA) {// if the same node could be added at both
          // ends
          bestIdxA = bestA = (-1);// we also need to re-calculate a
          bestLenA = Integer.MAX_VALUE;
        }
        bestIdxB = bestB = (-1);// we need to re-calculate b
        bestLenB = Integer.MAX_VALUE;
      }
    }

    // add distance of edge connecting both tour ends
    length += f.distance(lastA, lastB);

    f.registerFE(res, length);

    if (dest != null) { // return the result
      dest.solution = res;
      dest.tourLength = length;
      dest.producer = this;
    }

  }

  /** {@inheritDoc} */
  @Override
  public DoubleEndedNearestNeighborHeuristic clone() {
    DoubleEndedNearestNeighborHeuristic h;

    h = ((DoubleEndedNearestNeighborHeuristic) (super.clone()));
    h.m_tempList = null;

    return h;
  }

  /**
   * Perform the double-ended nearest neighbor heuristic.
   * 
   * @param args
   *          the command line arguments
   */
  public static void main(final String[] args) {
    TSPAlgorithmRunner.benchmark(Instance.SYMMETRIC_INSTANCES,
        DoubleEndedNearestNeighborHeuristic.class,//
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
