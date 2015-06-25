/**
 *
 <p>
 * A first Branch and Bound algorithm for Traveling Salesman Problems was
 * published in the paper
 * <em>An Algorithm for the Traveling Salesman Problem</em>&nbsp;[<a
 * href="#cite_LMSK1963AAFTTSP" style="font-weight:bold">1</a>] by Little
 * et al. in 1963. The idea of Branch and Bound algorithms itself was
 * originally proposed only three years earlier, by Ailsa Land and Alison
 * Doig in&nbsp;[<a href="#cite_LP1960AAMOSDPP"
 * style="font-weight:bold">2</a>]. Other sources on Branch and Bound
 * include the <a
 * href="http://en.wikipedia.org/wiki/Branch-and-bound">Wikipedia page</a>
 * and books like&nbsp;[<a href="#cite_M2012DAAOA"
 * style="font-weight:bold">3</a>].
 * </p>
 * <h2>Basic Idea of this Branch and Bound Algorithm</p>
 * <p>
 * The first major idea of this algorithm is to step-by-step divide
 * (branch) the search space into smaller and smaller subsets (here called
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963._Node
 * nodes}). For this purpose a
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963._Node
 * (sub)set} {@code s} of the search space is taken and a possible edge
 * {@code e} is chosen. The subset {@code s} is then divided into two
 * parts:
 * </p>
 * <ol>
 * <li>One containing all tours that contain (travel over) edge {@code e}
 * and</li>
 * <li>one containing all tours that do not travel over edge {@code e}.</li>
 * </ol>
 * <p>
 * Since any tour contains exactly
 * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#n() n}</code>
 * edges (where
 * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#n() n}</code>
 * is the number of cities in the TSP), after
 * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#n() n}</code>
 * branches of the first kind, we will have one valid solution. The
 * continued branching moves that we make during this progress somehow
 * resemble the iterative expansion of a tree, where the current solution
 * subset is a node and its child nodes are the branches departing from it.
 * If we continue dissecting the solution space like this, we will
 * eventually have seen all possible solutions and thus, found the optimum.
 * </p>
 * <p>
 * This procedure of repeated branching until we get tours alone would be
 * equivalent to an
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.exhaustiveEnumeration.ExhaustivePermutationIteration
 * exhaustive enumeration} of all possible solutions and, thus, would not
 * be very efficient.
 * </p>
 * <p>
 * The trick is the second major compound of the algorithm &mdash; the idea
 * of bounding: For each
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963._Node
 * (sub)set} {@code s} of the solutions, we can compute a lower bound of
 * the tour length. That is an approximation of the tour length that of the
 * shortest tour that this subset {@code s} contains. Being a lower bound,
 * it may underestimate this tour length, but it will never over-estimate
 * it.
 * </p>
 * <p>
 * If we now already have a tour {@code x} with tour length
 * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#evaluate(int[]) f(x)=z}</code>
 * , this would mean that we do never even need to look into any subset
 * {@code s} with a lower bound higher or equal to {@code z}! In other
 * words, we have good chances to throw away quite a few of the subsets of
 * the solution space. Every subset that we can throw away means less
 * solutions to check. As a result, we can be better than an exhaustive
 * enumeration <em>if</em> we can throw away solution subsets.
 * </p>
 * <p>
 * In this implementation here, we first check if the
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction
 * objective function} has already seen any solution. If so, the length of
 * the best solution created before starting the algorithm (maybe by an
 * initialization procedure or a constructive heuristic) is taken to check
 * the lower bounds of the subspaces in the first round of branches. If no
 * solution exists, we randomly create one and evaluate it. Of course, for
 * checking whether a subset of the solution space can be discarded, we
 * always use the length of the best tour we have discovered.
 * </p>
 * <p>
 * In the following, we discuss details of the algorithm, as defined
 * in&nbsp;[<a href="#cite_LMSK1963AAFTTSP"
 * style="font-weight:bold">1</a>], which mainly concern the questions:
 * </p>
 * <ol>
 * <li>How to choose the subspace to branch next?</li>
 * <li>How to choose the edge to be included/excluded into the new
 * branches?</li>
 * <li>How to compute the lower bounds?</li>
 * </ol> <h2>Algorithm Details</h2>
 * <p>
 * Any given matrix {@code C}, be it the original distance matrix or the
 * matrix derived from the previous node, is first reduced by finding the
 * minimal value of each row and then subtract it from each element of the
 * row. After dealing with all the rows, we find the minimal value of each
 * column and then subtract it from each element of the column. Adding all
 * the minimal values from each row and column together, we get a sum and
 * use {@code reduction} to denote it. we use {@code reduced matrix} to
 * denote the matrix that has already subtracted the minimal values of each
 * rows and columns and use {@code reducing matrix} to denote the process
 * described before.
 * </p>
 * <p>
 * Given a {@code reduced matrix}, we will find an edge to branch down
 * according to some criteria, which in this case is determined in terms of
 * a function that operates on the {@code C(k,l)}, which we denote by
 * {@code theta(k,l)}. We define {@code theta(k,l)} as the sum of the
 * smallest element in row {@code k} in {@code C}, and the smallest element
 * in column {@code l} in {@code C}, both omitting the {@code C(k,l)}. In
 * the {@code reduced matrix}, we iterate through all the element whose
 * value is {@code 0}, and compute all corresponding {@code theta(k,l)}. We
 * select edge {@code (k,l)} as the edge to branch if {@code theta(k,l)}
 * has the maximum value over our iteration. The process above is the
 * fundamental in this algorithm and we shall call it
 * {@code choosing a jump edge}.
 * </p>
 * <p>
 * Given an original matrix, we have a
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963._Node
 * node} corresponding to it. From this node, we start branching. Each node
 * formed after branching consists of an arrays of edges that is going to
 * be included in the solution and an array of edges that is going to be
 * excluded during the next branching. When given those two arrays, we are
 * able to construct the matrix corresponding to this node. Given any node,
 * we construct the corresponding matrix by crossing out all the rows and
 * columns that have been chosen in the
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963._Node#m_edges}
 * of this node, and then set the distance of edges, which we need not to
 * consider during the upcoming branching, to infinity. setInfinityEdges
 * method will give all the edges that ought to be set to infinity. Then we
 * will reduce the matrix and choose the jump edge. After the
 * {@code jump edge} is chosen, we create two new nodes based on the
 * branching node, one has the {@code jump edge} along with all the edges
 * in
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963._Node#m_edges}
 * in the branching node added to
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963._Node#m_edges}
 * of this node, the other has the {@code jump edge} along with all the
 * edges in
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963._Node#m_excludedEdges}
 * in the branching node added to
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963._Node#m_excludedEdges}
 * . We add the newly created node with the {@code jump edge} stored in the
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963._Node#m_excludedEdges}
 * into an array of node if the low bound of the node is smaller than the
 * existing minimal low bound, which is kept and updated when a solution is
 * found. The branching process proceed with newly created node that has
 * the {@code jump edge} inside
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963._Node#m_edges}
 * if the low bound of the node is smaller than the existing low bound. we
 * keep branching until we there is no node stored in the array for us to
 * branch further.
 * </p>
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
 * and&nbsp;[<a
 * href="https://github.com/karepker/little-tsp/blob/master/source.pdf"
 * >3</a>]</div></div></li>
 * <li><div><span id="cite_LP1960AAMOSDPP" />Ailsa H. Land and&nbsp;Alison
 * G. Doig: <span style="font-weight:bold">&ldquo;An Automatic Method of
 * Solving Discrete Programming Problems,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Econometrica &#8210;
 * Journal of the Econometric Society</span> 28(3):497&ndash;520,
 * July&nbsp;1960; published by Chichester, West Sussex, UK: Wiley
 * Interscience and&nbsp;Econometric Society. LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/sn97-23014">sn97-23014</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/35705710">35705710</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/00129682">0012-9682</a>. <div>link:
 * [<a
 * href="http://jmvidal.cse.sc.edu/library/land60a.pdf">1</a>]</div></div></li>
 * <li><div><span id="cite_M2012DAAOA" />I. Chandra Mohan: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Design and
 * Analysis of Algorithms,&rdquo;</span> 2008&ndash;2012, New Delhi, India:
 * PHI Learning Pvt. Ltd.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/8120345754">8120345754</a>, <a
 * href="https://www.worldcat.org/isbn/8120335171">8120335171</a>, <a
 * href="https://www.worldcat.org/isbn/9788120345751">9788120345751</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9788120335172">9788120335172</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=qg3snQEACAAJ">qg3snQEACAAJ</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=9Pz5K7s2YKgC">9Pz5K7s2YKgC</a>;
 * ASIN:&nbsp;<a href="http://www.amazon.com/dp/B008GBIO80">B008GBIO80</a>
 * and&nbsp;<a
 * href="http://www.amazon.com/dp/8120335171">8120335171</a></div></li>
 * </ol>
 *
 * @author <ul>
 *         <li>
 *         <em><a href="mailto:ljjy23@mail.ustc.edu.cn">Yan Jiang</a></em>
 *         [&#x6C5F;&#x708E;]</li>
 *         <li><em><a href="http://www.it-weise.de/">Thomas Weise</a></em>,
 *         Email:&nbsp;<a
 *         href="mailto:tweise@ustc.edu.cn">tweise@ustc.edu.cn</a> (role:
 *         supervisor of research work for the Science Master's degree)</li>
 *         </ul>
 */
package org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963;

