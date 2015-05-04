package org.logisticPlanning.tsp.solving.algorithms.heuristics.mst;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.algorithms.heuristics.TSPHeuristic;

/**
 * <p>
 * Here we implement the minimum spanning tree heuristic&nbsp;[<a
 * href="#cite_JMB1997TTSPACSILO" style="font-weight:bold">1</a>, <a
 * href="#cite_JMG2004EAOHFTS" style="font-weight:bold">2</a>] generates a
 * new candidate solution based on a (double) minimum spanning tree. The
 * idea of this algorithm is to first build a <a
 * href="https://en.wikipedia.org/wiki/Minimum_spanning_tree">minimum
 * spanning tree</a> (MST)&nbsp;[<a href="#cite_J2004MSTSPT"
 * style="font-weight:bold">3</a>] over the (fully-connected) graph. This
 * can be done in O(n^2) with <a
 * href="https://en.wikipedia.org/wiki/Prim%27s_algorithm">Prim's
 * algorithm</a>&nbsp;[<a href="#cite_J1930OJPMZDPOB"
 * style="font-weight:bold">4</a>, <a href="#cite_P1957SCNASG"
 * style="font-weight:bold">5</a>]. Then, starting at the root node (or any
 * other node) of that tree, we follow the edges of the MST and add the
 * corresponding nodes. Once we reached a dead end, we trace back the path
 * until we can find a new, unvisited edge (and hence, unvisited node).
 * This node is then the next one in the solution and we proceed as before,
 * following edges one by one.
 * </p>
 * <p>
 * If the triangle equation holds for the problem, the MST heuristic has
 * the worst case boundary {@code 2-(2/n)}&nbsp;[<a
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
 * <li><div><span id="cite_J2004MSTSPT" /><a
 * href="http://www.me.utexas.edu/~orie/Jensen.html">Paul A. Jensen</a>:
 * <span style="font-style:italic;font-family:cursive;">&ldquo;Minimal
 * Spanning Tree/Shortest Path Tree,&rdquo;</span> (Website),
 * 2004&ndash;September&nbsp;11, 2010, Austin, TX, USA: University of
 * Texas, Mechanical Engineering Department. <div>link: [<a href=
 * "http://www.me.utexas.edu/~jensen/ORMM/methods/unit/network/subunits/mst_spt/index.html"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_J1930OJPMZDPOB" /><a
 * href="https://en.wikipedia.org/wiki/Vojt%C4%9Bch_Jarn%C3%ADk"
 * >Vojt&#283;ch Jarn&#237;k</a>: <span style="font-weight:bold">&ldquo;O
 * Jist&#233;m Probl&#233;mu Minim&#225;ln&#237;m: (Z Dopisu Panu O.
 * Bor&#367;skovi),&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Pr&#225;ce Moravsk&#233;
 * P&#345;&#237;rodov&#283;deck&#233; Spole&#269;nosti: Acta Societatis
 * Scientiarum Naturalium Moravia</span> 6:57&ndash;63, 1930; published by
 * Brno, Czechoslovakia: Moravsk&#225; P&#345;&#237;rodov&#277;deck&#225;
 * Spolec&#780;&#328;ost. OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/762243334">762243334</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=GOc3HAAACAAJ">GOc3HAAACAAJ</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=kCMpAQAAMAAJ">kCMpAQAAMAAJ</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/12110248">1211-0248</a></div></li>
 * <li><div><span id="cite_P1957SCNASG" /><a
 * href="https://en.wikipedia.org/wiki/Robert_C._Prim">Robert Clay
 * Prim</a>: <span style="font-weight:bold">&ldquo;Shortest Connection
 * Networks and Some Generalizations,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Bell System Technical
 * Journal</span> 36(6):1389&ndash;1401, November&nbsp;1957; published by
 * Berkeley Heights, NJ, USA: Bell Laboratories. ASIN:&nbsp;<a
 * href="http://www.amazon.com/dp/B004VJC9KA">B004VJC9KA</a>. <div>link:
 * [<a href
 * ="www.alcatel-lucent.com/bstj/vol36-1957/articles/bstj36-6-1389.pdf"
 * >1</a> ]</div></div></li>
 * </ol>
 */
public final class MSTHeuristic extends TSPHeuristic {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** instantiate */
  public MSTHeuristic() {
    super("Minimum-Spanning-Tree Heuristic"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final void solve(final ObjectiveFunction f,
      final Individual<int[]> dest) {
    final int n;
    _NodeEntry root, best, next, cur, last;
    _NodeEntry[] todo;
    int[] perm;
    int i, j, bestI, d, p, getBestF;
    long totalD;

    n = f.n();
    // Initialize: clear destination record and allocate result array. This
    // is
    // just book-keeping and has nothing to do with the algorithm.
    perm = null;
    if (dest != null) {
      perm = dest.solution;
      dest.clearEvaluation();
      if ((perm != null) && (perm.length != n)) {
        perm = null;
      }
    }
    if (perm == null) {
      perm = new int[n];
    }
    // ok, done initialization

    i = n - 1;
    todo = new _NodeEntry[i];

    root = new _NodeEntry(n, -1, null);

    // enqueue all nodes into the heap based on their distance to the
    // starting
    // node
    for (; i > 0;) {
      next = new _NodeEntry(i, f.distance(root.m_node, i), root);
      todo[--i] = next;
    }

    // find the minimum spanning tree
    // each node now receives a linked list of children (child,
    // child.m_next,
    // child.m_next.m_next, ...)
    // and a link to its parent (m_parent) in the tree
    // as we have a fully connected graph, using a priority queue
    // O(E=V*V=V^2)
    // does not give an advantage compared to going over all nodes directly
    // O(V^2)

    next = best = root;
    for (i = todo.length; (--i) >= 0;) {
      getBestF = Integer.MAX_VALUE;
      bestI = -1;

      for (j = i; j >= 0; j--) {
        cur = todo[j];

        d = f.distance(next.m_node, cur.m_node);
        p = cur.m_priority;
        if (d < p) {
          cur.m_priority = p = d;
          cur.m_parent = next;
        }
        if (p <= getBestF) {
          bestI = j;
          best = cur;
          getBestF = p;
        }
      }

      todo[bestI] = todo[i];
      next = best;
      next.m_nextChild = next.m_parent.m_child;
      next.m_parent.m_child = next;
    }

    todo = null;
    // ok, we have MST

    // use the mst to construct a permutation

    // start at the root
    i = 0;
    perm[i++] = root.m_node;
    totalD = 0l;

    // start at the root of the tree at follow it
    for (last = cur = root; cur != null;) {

      next = cur.m_child;

      // is there such a child?
      if (next != null) {
        // add it to the permutation
        perm[i++] = next.m_node;

        cur.m_child = next.m_nextChild;

        // what is the distance to that child?
        if (next.m_parent == last) {
          // the parent is the current node: don't need to re-compute
          totalD += next.m_priority;
        } else {// otherwise, take shortcut
          totalD += f.distance(last.m_node, next.m_node);
        }

        last = cur = next;

        if (i >= n) {
          break;
        }
        continue;// next iteration
      }

      // no such child exists? go back one step
      cur = cur.m_parent;
    }

    totalD += f.distance(last.m_node, root.m_node);
    f.registerFE(perm, totalD);// register solution

    // output the result if destination was provided
    if (dest != null) {
      dest.solution = perm;
      dest.tourLength = totalD;
      dest.producer = this;
    }
  }

  /** {@inheritDoc} */
  @Override
  public final MSTHeuristic clone() {
    return this;
  }

  /**
   * Perform the MST heuristic
   *
   * @param args
   *          the command line arguments
   */
  public final static void main(final String[] args) {
    TSPAlgorithmRunner.benchmark(//
        Instance.SYMMETRIC_INSTANCES,//
        MSTHeuristic.class,//
        args);
  }
}
