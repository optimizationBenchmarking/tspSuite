package org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.operators.UnaryOperator;
import org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationMultiNeighborhoodMutation;

/**
 *
 <h1>Branch and Bound for the TSP by Little et al., hybridized with <a
 * href="#localSearch">Multi-Neighborhood Search</a></h1>
 * <p>
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
 * The first major idea of this algorithm is to step-by-step divide the
 * search space into smaller and smaller subsets (here called
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
 * If we continue disecting the solution space like this, we will
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
 * consider during the upcoming branching, to infinity.
 * </p>
 * <p>
 * setInfinityEdges method will give all the edges that ought to be set to
 * infinity. Then we will reduce the matrix and choose the jump edge. After
 * the {@code jump edge} is chosen, we create two new nodes based on the
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
 * <h2>Modifications</h2>
 * <p id="localSearch">
 * As local search following the crossover, we apply a
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationMultiNeighborhoodMutation
 * Multi-Neighborhood Search} which is repeated until it finds a local
 * optimum with a different objective value than the parent solution has.
 * This operator is implemented in the class
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationMultiNeighborhoodMutation
 * PermutationMultiNeighborhoodMutation}, which, in turn, inherits most of
 * its behavior from the local search heuristic
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch.MultiNeighborhoodSearch
 * MultiNeighborhoodSearch} implementation.
 * </p>
 * <p>
 * The
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationMultiNeighborhoodMutation
 * Multi-Neighborhood Search} uses four different basic search operations
 * implemented in class
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch.MultiNeighborhoodSearch
 * MultiNeighborhoodSearch}:
 * </p>
 * <ol>
 * <li>reversing of a subsequence&nbsp;[<a
 * href="#cite_LKMUB1999GAFTTSPARORAO" style="font-weight:bold">4</a>, <a
 * href="#cite_H1975GA" style="font-weight:bold">5</a>, <a
 * href="#cite_G1987IPSKIGA" style="font-weight:bold">6</a>, <a
 * href="#cite_JAMGS1989OBSAAEEPIGP" style="font-weight:bold">7</a>, <a
 * href="#cite_KGV1983SA" style="font-weight:bold">8</a>, <a
 * href="#cite_ABCC1999FTITTFT" style="font-weight:bold">9</a>] (<a
 * href="https://en.wikipedia.org/wiki/2-opt">2-opt</a>&nbsp;[<a
 * href="#cite_C1958AMFSTSP" style="font-weight:bold">10</a>, <a
 * href="#cite_F1956TTSP" style="font-weight:bold">11</a>, <a
 * href="#cite_ABCC1999FTITTFT" style="font-weight:bold">9</a>], see also
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdateOperator
 * here}),</li>
 * <li>rotating a subsequence to the left&nbsp;[<a
 * href="#cite_LKMUB1999GAFTTSPARORAO" style="font-weight:bold">4</a>, <a
 * href="#cite_F1988AEATTTSP" style="font-weight:bold">12</a>, <a
 * href="#cite_M1996GADSEP" style="font-weight:bold">13</a>, <a
 * href="#cite_S1991SOUGA" style="font-weight:bold">14</a>] (one possible
 * 3-opt move, see also
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Rotate_Left
 * here}),</li>
 * <li>rotating a subsequence to the right&nbsp;[<a
 * href="#cite_LKMUB1999GAFTTSPARORAO" style="font-weight:bold">4</a>, <a
 * href="#cite_F1988AEATTTSP" style="font-weight:bold">12</a>, <a
 * href="#cite_M1996GADSEP" style="font-weight:bold">13</a>, <a
 * href="#cite_S1991SOUGA" style="font-weight:bold">14</a>] (one possible
 * 3-opt move, see also
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Rotate_Right
 * here}), and</li>
 * <li>swapping two nodes&nbsp;[<a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">4</a>, <a href="#cite_OSH1987ASOPCOOTTSP"
 * style="font-weight:bold">15</a>, <a href="#cite_M1996GADSEP"
 * style="font-weight:bold">13</a>, <a href="#cite_B1990TMTS"
 * style="font-weight:bold">16</a>, <a href="#cite_AAM1991HCOBSDEAPTAFTTSP"
 * style="font-weight:bold">17</a>, <a href="#cite_S1991SOUGA"
 * style="font-weight:bold">14</a>] (one possible 4-opt move, see also
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Swap
 * here})</li>
 * </ol>
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
 * <li><div><span id="cite_LKMUB1999GAFTTSPARORAO" /><a href=
 * "http://cig.fi.upm.es/index.php?option=com_content&amp;view=article&amp;id=78&amp;Itemid=111"
 * >Pedro Larra&#241;aga</a>, <a href=
 * "http://www.tilburguniversity.edu/nl/webwijs/show/&amp;uid=c.m.h.kuijpers?uid=c.m.h.kuijpers"
 * >Cindy M. H. Kuijpers</a>, Roberto H. Murga, <a
 * href="http://www.sc.ehu.es/ccwbayes/members/inaki.htm">I&#241;aki
 * Inza</a>, and&nbsp;Sejla Dizdarevic: <span
 * style="font-weight:bold">&ldquo;Genetic Algorithms for the Travelling
 * Salesman Problem: A Review of Representations and
 * Operators,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Journal of Artificial
 * Intelligence Research (JAIR)</span> 13(2):129&ndash;170,
 * April&nbsp;1999; published by El Segundo, CA, USA: AI Access Foundation,
 * Inc. and&nbsp;Menlo Park, CA, USA: AAAI Press. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1023/A:1006529012972"
 * >10.1023/A:1006529012972</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/110769757">11076-9757</a>.
 * <div>link: [<a href=
 * "http://www.dca.fee.unicamp.br/~gomide/courses/EA072/artigos/Genetic_Algorithm_TSPR_eview_Larranaga_1999.pdf"
 * >1</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.35.8882"
 * >10.1.1.35.8882</a></div></div></li>
 * <li><div><span id="cite_H1975GA" /><a
 * href="https://en.wikipedia.org/wiki/John_Henry_Holland">John Henry
 * Holland</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Adaptation in
 * Natural and Artificial Systems: An Introductory Analysis with
 * Applications to Biology, Control, and Artificial
 * Intelligence,&rdquo;</span> 1975, Ann Arbor, MI, USA: University of
 * Michigan Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0472084607">0-472-08460-7</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780472084609">978-0-472-
 * 08460-9</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/74078988">74078988</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/1531617">1531617</a> and&nbsp;<a
 * href="https://www.worldcat.org/oclc/266623839">266623839</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=JE5RAAAAMAAJ">JE5RAAAAMAAJ</a>,
 * <a
 * href="http://books.google.com/books?id=YE5RAAAAMAAJ">YE5RAAAAMAAJ</a>,
 * <a
 * href="http://books.google.com/books?id=Qk5RAAAAMAAJ">Qk5RAAAAMAAJ</a>,
 * <a
 * href="http://books.google.com/books?id=vk5RAAAAMAAJ">vk5RAAAAMAAJ</a>,
 * and&nbsp;<a
 * href="http://books.google.com/books?id=JOv3AQAACAAJ">JOv3AQAACAAJ</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=075982293">075982293</a></div></li>
 * <li><div><span id="cite_G1987IPSKIGA" /><a
 * href="http://www.biostat.pitt.edu/directory/bios/grefenstette.asp">John
 * J. Grefenstette</a>: <span style="font-weight:bold">&ldquo;Incorporating
 * Problem Specific Knowledge into Genetic Algorithms,&rdquo;</span> in
 * <span style="font-style:italic;font-family:cursive;">Genetic Algorithms
 * and Simulated Annealing</span>, pages 42&ndash;60, Lawrence Davis,
 * editor, Research Notes in Artificial Intelligence, London, UK: Pitman
 * and&nbsp;San Francisco, CA, USA: Morgan Kaufmann Publishers Inc..
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0934613443">0934613443</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780934613446">978-0934613446</a>;
 * LCCN:&nbsp;<a href="http://lccn.loc.gov/87021357">87021357</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/16355405">16355405</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=edfSSAAACAAJ">edfSSAAACAAJ
 * </a></div></li>
 * <li><div><span id="cite_JAMGS1989OBSAAEEPIGP" /><a
 * href="https://en.wikipedia.org/wiki/David_S._Johnson">David Stifler
 * Johnson</a>, <a href="http://faculty.washington.edu/aragon/">Cecilia R.
 * Aragon</a>, <a
 * href="https://www.amherst.edu/people/facstaff/lamcgeoch">Lyle A.
 * McGeoch</a>, and&nbsp;Catherine A. Schevon: <span
 * style="font-weight:bold">&ldquo;Optimization by Simulated Annealing: An
 * Experimental Evaluation. Part I, Graph Partitioning,&rdquo;</span> in
 * <span style="font-style:italic;font-family:cursive;">Operations Research
 * (Oper. Res.)</span> 37(6), November&ndash;December 1989; published by
 * Linthicum, ML, USA: Institute for Operations Research and the Management
 * Sciences (INFORMS) and&nbsp;Cambridge, MA, USA: HighWire Press (Stanford
 * University). LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/66099702">66099702</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1287/opre.37.6.865"
 * >10.1287/opre.37.6.865</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/2394608">2394608</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/0030364X">0030-364X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15265463">1526-5463</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=OPREAI"
 * >OPREAI</a></div></li>
 * <li><div><span id="cite_KGV1983SA" /><a
 * href="http://www.cs.huji.ac.il/~kirk/">Scott Kirkpatrick</a>, Charles
 * Daniel Gelatt, Jr., and&nbsp;<a
 * href="http://www.linkedin.com/in/mariovecchi">Mario P. Vecchi</a>: <span
 * style="font-weight:bold">&ldquo;Optimization by Simulated
 * Annealing,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Science Magazine</span>
 * 220(4598):671&ndash;680, May&nbsp;13, 1983; published by Washington, DC,
 * USA: American Association for the Advancement of Science (AAAS)
 * and&nbsp;Cambridge, MA, USA: HighWire Press (Stanford University).
 * doi:&nbsp;<a
 * href="http://dx.doi.org/10.1126/science.220.4598.671">10.1126/science
 * .220.4598.671</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/00368075">0036-8075</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/10959203">1095-9203</a>;
 * PubMed:&nbsp;<a
 * href="https://www.ncbi.nlm.nih.gov/pubmed/17813860">17813860</a>.
 * <div>links: [<a
 * href="http://fezzik.ucd.ie/msc/cscs/ga/kirkpatrick83optimization.pdf"
 * >1</a>], [<a
 * href="http://citeseer.ist.psu.edu/kirkpatrick83optimization.html"
 * >2</a>], and&nbsp;[<a href=
 * "http://home.gwu.edu/~stroud/classics/KirkpatrickGelattVecchi83.pdf"
 * >3</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.18.4175"
 * >10.1.1.18.4175</a></div></div></li>
 * <li><div><span id="cite_ABCC1999FTITTFT" /><a
 * href="http://www.research.att.com/people/Applegate_David_L">David Lee
 * Applegate</a>, <a href="http://www.caam.rice.edu/~bixby/">Robert E.
 * Bixby</a>, <a href="http://users.encs.concordia.ca/~chvatal/">Va&#353;ek
 * Chv&#225;tal</a>, and&nbsp;<a
 * href="http://www2.isye.gatech.edu/~wcook/">William John Cook</a>: <span
 * style="font-weight:bold">&ldquo;Finding Tours in the TSP: Finding
 * Tours,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;99885, 1999; published by Bonn, North Rhine-Westphalia,
 * Germany: Rheinische Friedrich-Wilhelms-Universit&#228;t Bonn. Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=CR6DNAAACAAJ">CR6DNAAACAAJ</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=gtgGaAEACAAJ">gtgGaAEACAAJ</a>.
 * <div>link: [<a
 * href="http://www.tsp.gatech.edu/methods/papers/lk_report.ps">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.43.2594">10.1
 * .1.43 .2594</a></div></div></li>
 * <li><div><span id="cite_C1958AMFSTSP" />G. A. Croes: <span
 * style="font-weight:bold">&ldquo;A Method for Solving Traveling-Salesman
 * Problems,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Operations Research
 * (Oper. Res.)</span> 6(6):791&ndash;812, November&ndash;December 1958;
 * published by Linthicum, ML, USA: Institute for Operations Research and
 * the Management Sciences (INFORMS) and&nbsp;Cambridge, MA, USA: HighWire
 * Press (Stanford University). LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/66099702">66099702</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1287/opre.6.6.791">10.1287/opre.6.6.791</a>;
 * JSTOR stable:&nbsp;<a
 * href="http://www.jstor.org/stable/167074">167074</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/2394608">2394608</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/0030364X">0030-364X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15265463">1526-5463</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=OPREAI"
 * >OPREAI</a></div></li>
 * <li><div><span id="cite_F1956TTSP" />Merrill M. Flood: <span
 * style="font-weight:bold">&ldquo;The Traveling-Salesman
 * Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Operations Research
 * (Oper. Res.)</span> 4(1):61&ndash;75, February&nbsp;1956; published by
 * Linthicum, ML, USA: Institute for Operations Research and the Management
 * Sciences (INFORMS) and&nbsp;Cambridge, MA, USA: HighWire Press (Stanford
 * University). LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/66099702">66099702</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1287/opre.4.1.61">10.1287/opre.4.1.61</a>;
 * OCLC:&nbsp;<a href="https://www.worldcat.org/oclc/2394608">2394608</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/0030364X">0030-364X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15265463">1526-5463</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=OPREAI"
 * >OPREAI</a></div></li>
 * <li><div><span id="cite_F1988AEATTTSP" /><a
 * href="http://www.natural-selection.com/people_dfogel.html">David B.
 * Fogel</a>: <span style="font-weight:bold">&ldquo;An Evolutionary
 * Approach to the Traveling Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Biological
 * Cybernetics</span> 60(2):139&ndash;144, December&nbsp;1988; published by
 * Berlin/Heidelberg: Springer-Verlag. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/BF00202901">10.1007/BF00202901</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03401200">0340-1200</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/14320770">1432-0770</a>. <div>link:
 * [<a
 * href="http://users.on.net/~jivlain/papers/4%20Fogel.pdf">1</a>]</div>
 * </div></li>
 * <li><div><span id="cite_M1996GADSEP" /><a
 * href="http://cs.adelaide.edu.au/~zbyszek/">Zbigniew Michalewicz</a>:
 * <span style="font-style:italic;font-family:cursive;">&ldquo;Genetic
 * Algorithms + Data Structures = Evolution Programs,&rdquo;</span> 1996,
 * Berlin, Germany: Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540606769">3-540-60676-9</a>, <a
 * href="https://www.worldcat.org/isbn/3540580905">3-540-58090-5</a>, <a
 * href
 * ="https://www.worldcat.org/isbn/9783540606765">978-3-540-60676-5</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783642082337">978-3-642-
 * 08233-7</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=vlhLAobsK68C">vlhLAobsK68C</a>;
 * ASIN:&nbsp;<a
 * href="http://www.amazon.com/dp/3540606769">3540606769</a></div></li>
 * <li><div><span id="cite_S1991SOUGA" />Gilbert Syswerda: <span
 * style="font-weight:bold">&ldquo;Schedule Optimization Using Genetic
 * Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Handbook of Genetic
 * Algorithms</span>, pages 332&ndash;349, Lawrence Davis, editor, VNR
 * Computer Library, Stamford, CT, USA: Thomson Publishing Group, Inc.
 * and&nbsp;New York, NY, USA: Van Nostrand Reinhold Co.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0442001738">0-442-00173-8</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780442001735">978-0-442-
 * 00173-5</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/90012823">90012823</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/300431834">300431834</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=vTG5PAAACAAJ">vTG5PAAACAAJ</a>;
 * ASIN:&nbsp;<a href="http://www.amazon.com/dp/0442001738">0442001738</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=01945077X">01945077X</a></div></li>
 * <li><div><span id="cite_OSH1987ASOPCOOTTSP" />I. M. Oliver, D. J. Smith,
 * and&nbsp;<a href="https://en.wikipedia.org/wiki/John_Henry_Holland">John
 * Henry Holland</a>: <span style="font-weight:bold">&ldquo;A Study of
 * Permutation Crossover Operators on the Traveling Salesman
 * Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the Second
 * International Conference on Genetic Algorithms and their Applications
 * (ICGA'87)</span>, July&nbsp;28&ndash;31, 1987, Cambridge, MA, USA:
 * Massachusetts Institute of Technology (MIT), pages 224&ndash;230, <a
 * href="http://www.biostat.pitt.edu/directory/bios/grefenstette.asp">John
 * J. Grefenstette</a>, editor, Mahwah, NJ, USA: Lawrence Erlbaum
 * Associates, Inc. (LEA). ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0805801588">0-8058-0158-8</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780805801583">978-0-8058
 * -0158-3</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/17252562">17252562</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=bvlQAAAAMAAJ">bvlQAAAAMAAJ
 * </a></div></li>
 * <li><div><span id="cite_B1990TMTS" /><a
 * href="http://www.cs.mun.ca/~banzhaf/">Wolfgang Banzhaf</a>: <span
 * style="font-weight:bold">&ldquo;The &#8220;Molecular&#8221; Traveling
 * Salesman,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Biological
 * Cybernetics</span> 64(1):7&ndash;14, November&nbsp;1990; published by
 * Berlin/Heidelberg: Springer-Verlag. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/BF00203625">10.1007/BF00203625</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03401200">0340-1200</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/14320770">1432-0770</a>. <div>link:
 * [<a href="https://web.cs.mun.ca/~banzhaf/papers/MolTravelSalesman.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_AAM1991HCOBSDEAPTAFTTSP" />Balamurali Krishna
 * Ambati, Jayakrishna Ambati, and&nbsp;Mazen Moein Mokhtar: <span
 * style="font-weight:bold">&ldquo;Heuristic Combinatorial Optimization by
 * Simulated Darwinian Evolution: A Polynomial Time Algorithm for the
 * Traveling Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Biological
 * Cybernetics</span> 65(1):31&ndash;35, May&nbsp;1991; published by
 * Berlin/Heidelberg: Springer-Verlag. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/BF00197287">10.1007/BF00197287</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03401200">0340-1200</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/14320770">1432-0770</a></div></li>
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
public class BABLittle1963MultiNeighborhoodSearch extends
    _HybridBABLittle1963 {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** create */
  public BABLittle1963MultiNeighborhoodSearch() {
    super(" with MultiNeighborhoodSearch"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  final UnaryOperator<int[]> createUnary() {
    return new PermutationMultiNeighborhoodMutation();
  }

  /**
   * The main method invoking the algorithm
   *
   * @param args
   *          the command line arguments
   */
  public static void main(final String args[]) {
    TSPAlgorithmRunner.benchmark(Instance.SYMMETRIC_INSTANCES,
        BABLittle1963MultiNeighborhoodSearch.class, args);
  }
}
