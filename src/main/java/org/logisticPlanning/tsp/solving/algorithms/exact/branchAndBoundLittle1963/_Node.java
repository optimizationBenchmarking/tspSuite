package org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963;

/**
 *
 <p>
 * This class is used an internal class to represent the {@code node} used
 * in the Branch and Bound algorithm published in the paper
 * <em>An Algorithm for the Traveling Salesman Problem</em>&nbsp;[<a
 * href="#cite_LMSK1963AAFTTSP" style="font-weight:bold">1</a>] by Little
 * et al. in 1963. A node is made up of a specific permutation of m_edges
 * that we have found so far , which we are going to include in the
 * solution if we can derive a solution from this node, and a bunch of
 * m_edges to be excluded when we examine which edge to be considered next.
 * If the node contains {@code m_n} m_edges we found that can be included
 * in the solution, then this node is a terminal node then a solution is
 * found. A original matrix is also a node whose {@link #m_edges} and
 * <b>m_excludedEdges<b> variables have no m_edges at all.
 * <p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_LMSK1963AAFTTSP" />John D. C. Little, <a
 * href="http://www-personal.umich.edu/~murty/">Katta G. Murty</a>, Dura W.
 * Sweeny, and&nbsp;Caroline Karel: <span
 * style="font-weight:bold">&ldquo;An Algorithm for the Traveling Salesman
 * Problem,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;07-63, March&nbsp;1, 1963; published by Cambridge, MA, USA:
 * Massachusetts Institute of Technology (MIT), Sloan School of Management.
 * <div>links: [<a href=
 * "http://dspace.mit.edu/bitstream/handle/1721.1/46828/algorithmfortrav00litt.pdf"
 * >1</a>], [<a href="http://hdl.handle.net/1721.1/46828">2</a>],
 * and&nbsp;[<a href
 * ="https://github.com/karepker/little-tsp/blob/master/source.pdf"
 * >3</a>]</div ></div></li>
 * </ol>
 *
 * @author <ul>
 *         <li><em><a href="mailto:ljjy23@mail.ustc.edu.cn">Yan Jiang</a>
 *         </em> [&#x6C5F;&#x708E;]</li><li><em><a
 *         href="http://www.it-weise.de/">Thomas Weise</a></em>,
 *         Email:&nbsp;<a
 *         href="mailto:tweise@ustc.edu.cn">tweise@ustc.edu.cn</a> (role:
 *         supervisor of research work for the Science Master's degree)
 *         </li>
 *         </ul>
 */
final class _Node {

  /** create */
  _Node() {
    super();
  }

  /**
   * This array is used to denote the m_edges found so far by the Branch
   * and Bound algorithm in {@code this} node. An edge is represented by
   * two integers, one of which is the starting city and the other is the
   * destination city. In this array, we represent each edge by two
   * elements inside it with element having even index as the starting city
   * and the element having odd index as the destination city. If
   * {@code this} node is an solution to an TSP problem, then it ought to
   * have 2n elements representing {@code m_n} distinct m_edges.
   */
  transient int[] m_edges;

  /**
   * This array is used to denote the m_edges that should not be taken into
   * consideration during the next branch, which starts from {@code this}
   * node. When branching down from this node, as for a specific edge,
   * which is chosen from all available m_edges left behind, we either
   * select it or don't select it. The edge will be written into the
   * {@link #m_edges} variable and hence form a new node if it is selected,
   * otherwise the edge will be written into <b>m_excludedEdges<b> and form
   * a new node.
   */
  transient int[] m_excludedEdges;

  /**
   * The m_nodeLowBound of {@code this} node gives us the minimum length of
   * any solution if we choose to branch down from {@code this} node.
   */
  transient long m_nodeLowBound;

  /**
   * If we have found {@code m_n} m_edges, we found a solution of the
   * question. By checking the last elements of the {@link #m_edges}, we
   * can determine whether this node is a terminal node. A terminal node is
   * a node having an effective m_answer.
   *
   * @param instanceSize
   *          the instance size
   * @return {@code true} if the node represents a solution, {@code false}
   *         otherwise
   */
  boolean _isSolution(final int instanceSize) {
    return (this.m_edges.length == (2 * instanceSize))
        && (this.m_edges[(2 * instanceSize) - 1] > 0);
  }

}
