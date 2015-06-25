package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.operators.UnaryOperator;
import org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationRNSMutation;

/**
 *
 <p>
 * This is a version of the
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA.TemplateBasedEHBSA
 * template-based EHBSA} where the first population (and the model) is
 * initialized heuristically and each individual is refined with a
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.PermutationRNS
 * random-neighborhood search}.
 * </p>
 * <p>
 * The Edge-Histogram based Sampling Algorithm (EHBSAs) is a probabilistic
 * model-building genetic algorithms (PMBGAs)&nbsp;[<a
 * href="#cite_PGL2002ASOOBBAUPM" style="font-weight:bold">1</a>, <a
 * href="#cite_T2006NHVEHACOPMBGAIPD" style="font-weight:bold">2</a>],
 * i.e., Estimation of Distribution Algorithms (EDA)&nbsp;[<a
 * href="#cite_LL2001EODAANTFEC" style="font-weight:bold">3</a>, <a
 * href="#cite_LLIB2006TANEC" style="font-weight:bold">4</a>] proposed by
 * Tsutsui&nbsp;[<a href="#cite_T2002PMBGAIPRDUEH"
 * style="font-weight:bold">5</a>, <a href="#cite_T2009POAEAOAPWMCP"
 * style="font-weight:bold">6</a>] for permutation domains where edges are
 * important, such as Traveling Salesman Problems (TSPs). A similar
 * algorithm for Quadratic Assignment Problems has been presented
 * in&nbsp;[<a href="#cite_TF2012IOHBSAWAESWC"
 * style="font-weight:bold">7</a>].
 * </p>
 * <p>
 * This algorithm here has the following features:
 * </p>
 * <ol>
 * <li>The model used in this type of EDA is a edge histogram. This
 * histogram counts, for each edge, how often it appears in a set of
 * selected solutions. The probability of being added to a new solution
 * (being &quot;sampled&quot;) of an edge is then roughly proportional to
 * this value.</li>
 * <li>The algorithm implemented in this package is a template-based EHBSA
 * (defined as eEHBSA in&nbsp;[<a href="#cite_T2009POAEAOAPWMCP"
 * style="font-weight:bold">6</a>]). When a new solution (sample) is
 * created, first a part is copied from a parent (template) solution. The
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA.ETemplateMethod
 * remainder} is then filled by sampling the edge histogram model. After
 * copying some nodes from the template, we begin &quot;sampling&quot; the
 * model at the node at the end of the copied sequence. This node is called
 * &quot;source&quot; All so-far unused candidate nodes (see <a
 * href="#_candidate">below</a>) are considered as next
 * &quot;destination&quot; to follow that source. The chance of a node
 * {@code i} being picked is proportional to
 * <code>bias+histogram(source,i)</code>, where bias is a small added value
 * preventing premature convergence.</li>
 * <li>The template-based EHBSA maintains a population a bit similar to the
 * way Differential Evolution (DE)&nbsp;[<a href="#cite_PSL2005DE"
 * style="font-weight:bold">8</a>, <a href="#cite_SP1995DE"
 * style="font-weight:bold">9</a>, <a href="#cite_S1996DEFO"
 * style="font-weight:bold">10</a>] does: From each template, exactly one
 * offspring solution (sample) is created. After all templates have been
 * sampled once, the samples compete with their direct parents. If a sample
 * is better than its corresponding template, it will replace the template
 * in the template set. This means also that the edges of the template are
 * &quote;removed&quot; from the edge histogram and the edges of the sample
 * will be &quot;added&quot; to it.</li>
 * <li id="_candidate">The model sampling procedure is an iterative
 * process. At each iteration, there is a current node {@code source} and
 * the question is which next node ( {@code destination}) should be added.
 * The EHBSA therefore maintains a candidate set, a set that contains, for
 * each node {@code i}, the <code>s</code> nearest neighbors. Usually, only
 * the nodes that are in the candidate set of node {@code source} may be
 * chosen as next {@code destination}. However, it may be that all such
 * nodes have already been visited, i.e., that the candidate set is empty.
 * In this case, an
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA.EAugmentationMethod
 * augmentation} has to be performed.</li>
 * <li>The
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA.EAugmentationMethod#AUGMENT_BY_HISTOGRAM
 * augmentation} method performed in the original version of the EHBSA
 * chooses the unvisited node with the highest histogram value, i.e., adds
 * the edge that is found in the most templates. This
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA.EAugmentationMethod#AUGMENT_BY_HISTOGRAM
 * procedure} has the advantage that it makes use of as much of the model /
 * histogram information as possible. It has the disadvantage that it
 * requires us to hold a full edge histogram matrix in memory, and the size
 * of that matrix is in <code>O(n<sup>2</sup>)</code>, which will cause a
 * problem for larger-scale algorithms. Therefore, we added some new
 * augmentation approaches, as described in the documentation of the
 * enumeration
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA.EAugmentationMethod}
 * and <a href="#_newAugmentation">below</a>.</li>
 * <li>The following extensions have been implemented in this algorithm
 * version compared to the vanilla approach from&nbsp;[<a
 * href="#cite_T2009POAEAOAPWMCP" style="font-weight:bold">6</a>]:
 * <ol>
 * <li>Two new
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA.EAugmentationMethod
 * augmentation} methods have been introduced in order to reduce the memory
 * footprint of the EHBSA:
 * <ol>
 * <li>Instead of using the node with highest histogram value if
 * augmentation is necessary, we can choose the
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA.EAugmentationMethod#AUGMENT_BY_DISTANCE
 * nearest node} as next step. This means that for augmentation, no
 * histogram information is necessary. This, in turn, means that histogram
 * information is only necessary for nodes in the candidate set. This means
 * that we need less memory, since only histogram values related to
 * candidate edges need to be stored. Given a candidate set with {@code m}
 * neighbors per node, this reduces the memory requirement to
 * <code>O(m*n)</code> instead of <code>O(n<sup>2</sup>)</code>. More
 * technically, we can potentially use an instance of the less memory
 * consuming class
 * {@link org.logisticPlanning.tsp.solving.utils.candidates.CandidateEdgeNumber
 * CandidateEdgeNumber} instead of a direct implementation of
 * {@link org.logisticPlanning.tsp.solving.utils.edgeData.EdgeNumber
 * EdgeNumber} . (This choice is automated by the method
 * {@link org.logisticPlanning.tsp.solving.utils.candidates.CandidateEdgeNumber#allocate(int, boolean, long, long, boolean, org.logisticPlanning.tsp.solving.utils.candidates.CandidateSet, org.logisticPlanning.tsp.solving.utils.edgeData.EdgeNumber)
 * allocate} which weights memory consumption vs. loss of speed.) The
 * disadvantages of this method are that it makes less use of histogram
 * information, requires higher runtime (access to model more complex), and
 * more distance evaluations.</li>
 * <li>We can reduce the number of distance evaluations by just choosing
 * nodes
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA.EAugmentationMethod#AUGMENT_RANDOMLY
 * randomly} as next step. This retains the good memory features of the
 * previous method, needs less runtime, but may lead to less optimal
 * augmentation.</li>
 * </ol>
 * </li>
 * <li id="heurInit">
 * <p>
 * The population in the first generation of this algorithm version here is
 * initialized using heuristic procedures such as
 * </p>
 * <ol>
 * <li>the
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.mst.MSTHeuristic
 * double minimum spanning tree method},</li>
 * <li>the
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.edgeGreedy.EdgeGreedyHeuristic
 * edge greedy method},</li>
 * <li>the
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic
 * savings heuristic},</li>
 * <li>the
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.doubleEndedNearestNeighbor.DoubleEndedNearestNeighborHeuristic
 * double-ended nearest neighbor heuristic}, and</li>
 * <li>the
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.nearestNeighbor.NearestNeighborHeuristic
 * nearest neighbor heuristic}.</li>
 * </ol>
 * <p>
 * The first two heuristics each contribute one solution to the initial
 * population. The latter three heuristics are instances of
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.TSPHeuristicWithStartNode}
 * , which can potentially produce up to {@code n} different solutions in
 * {@code n}-node TSPs. Therefore, we use these to fill up the population
 * and try to invoke them with different, random initial nodes. This should
 * lead to initial population which is diverse and still contains
 * reasonably good solutions.
 * </p>
 * <p>
 * Additionally, directly after an individual is created with any of the
 * above heuristics, it is refined with a local search.
 * </p>
 * </li>
 * <li id="localSearch">
 * <p>
 * This algorithm version here refines every solution (also those from the
 * heuristic initialization) with a local search method. As local search
 * method, we apply a
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationRNSMutation
 * Random Neighborhood Search} (RNS), which is a Hill Climbing method
 * similar to Variable Neighborhood Search (VNS)&nbsp;[<a
 * href="#cite_HM2001VNSPAA" style="font-weight:bold">11</a>, <a
 * href="#cite_HMP2008VNSMAA" style="font-weight:bold">12</a>, <a
 * href="#cite_HMP2010VNSMAA" style="font-weight:bold">13</a>], except that
 * it randomizes neighborhood selection entirely. This algorithm is
 * repeated until it finds a local optimum with a different objective value
 * than the parent solution has. This operator is implemented in the class
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationRNSMutation
 * PermutationRNSMutation}, which, in turn, inherits most of its behavior
 * from the
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.PermutationRNS
 * PermutationRNS} algorithm implementation.
 * </p>
 * <p>
 * The
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationRNSMutation
 * Random Neighborhood Search} uses four different basic search operations
 * as implemented in package
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.update}:
 * </p>
 * <ol>
 * <li>reversing of a subsequence&nbsp;[<a
 * href="#cite_LKMUB1999GAFTTSPARORAO" style="font-weight:bold">14</a>, <a
 * href="#cite_H1975GA" style="font-weight:bold">15</a>, <a
 * href="#cite_G1987IPSKIGA" style="font-weight:bold">16</a>, <a
 * href="#cite_JAMGS1989OBSAAEEPIGP" style="font-weight:bold">17</a>, <a
 * href="#cite_KGV1983SA" style="font-weight:bold">18</a>, <a
 * href="#cite_ABCC1999FTITTFT" style="font-weight:bold">19</a>] (<a
 * href="https://en.wikipedia.org/wiki/2-opt">2-opt</a>&nbsp;[<a
 * href="#cite_C1958AMFSTSP" style="font-weight:bold">20</a>, <a
 * href="#cite_F1956TTSP" style="font-weight:bold">21</a>, <a
 * href="#cite_ABCC1999FTITTFT" style="font-weight:bold">19</a>], see also
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdateOperator
 * here}),</li>
 * <li>rotating a subsequence to the left&nbsp;[<a
 * href="#cite_LKMUB1999GAFTTSPARORAO" style="font-weight:bold">14</a>, <a
 * href="#cite_F1988AEATTTSP" style="font-weight:bold">22</a>, <a
 * href="#cite_M1996GADSEP" style="font-weight:bold">23</a>, <a
 * href="#cite_S1991SOUGA" style="font-weight:bold">24</a>] (one possible
 * 3-opt move, see also
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Rotate_Left
 * here}),</li>
 * <li>rotating a subsequence to the right&nbsp;[<a
 * href="#cite_LKMUB1999GAFTTSPARORAO" style="font-weight:bold">14</a>, <a
 * href="#cite_F1988AEATTTSP" style="font-weight:bold">22</a>, <a
 * href="#cite_M1996GADSEP" style="font-weight:bold">23</a>, <a
 * href="#cite_S1991SOUGA" style="font-weight:bold">24</a>] (one possible
 * 3-opt move, see also
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Rotate_Right
 * here}), and</li>
 * <li>swapping two nodes&nbsp;[<a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">14</a>, <a href="#cite_OSH1987ASOPCOOTTSP"
 * style="font-weight:bold">25</a>, <a href="#cite_M1996GADSEP"
 * style="font-weight:bold">23</a>, <a href="#cite_B1990TMTS"
 * style="font-weight:bold">26</a>, <a href="#cite_AAM1991HCOBSDEAPTAFTTSP"
 * style="font-weight:bold">27</a>, <a href="#cite_S1991SOUGA"
 * style="font-weight:bold">24</a>] (one possible 4-opt move, see also
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Swap
 * here})</li>
 * </ol>
 * </li>
 * </ol>
 * </li>
 * </ol>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_PGL2002ASOOBBAUPM" /><a
 * href="http://www.cs.umsl.edu/~pelikan/">Martin Pelikan</a>, <a
 * href="http://www.davidegoldberg.com/de-goldberg.html">David Edward
 * Goldberg</a>, and&nbsp;<a href="http://w3.ualg.pt/~flobo/">Fernando G.
 * Lobo</a>: <span style="font-weight:bold">&ldquo;A Survey of Optimization
 * by Building and Using Probabilistic Models,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Computational
 * Optimization and Applications</span> 21(1):5&ndash;20,
 * January&nbsp;2002; published by Norwell, MA, USA: Kluwer Academic
 * Publishers and&nbsp;Dordrecht, Netherlands: Springer Netherlands.
 * doi:&nbsp;<a href="http://dx.doi.org/10.1023/A:1013500812258">10.1023/A:
 * 1013500812258</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/09266003">0926-6003</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15732894">1573-2894</a>. <div>links:
 * [<a href="http://w3.ualg.pt/~flobo/papers/pmbga-survey.pdf">1</a>]
 * and&nbsp;[<a
 * href="http://www.fernandolobo.info/p/pmbga-survey.pdf">2</a>
 * ]</div></div></li>
 * <li><div><span id="cite_T2006NHVEHACOPMBGAIPD" /><a
 * href="http://www2.hannan-u.ac.jp/~tsutsui/index-e.html">Shigeyoshi
 * Tsutsui</a> <span style="color:gray">[&#31570;&#20117;
 * &#33538;&#32681;</span>]: <span style="font-weight:bold">&ldquo;Node
 * Histogram vs. Edge Histogram: A Comparison of Probabilistic
 * Model-Building Genetic Algorithms in Permutation Domains,&rdquo;</span>
 * in <span style="font-style:italic;font-family:cursive;">Proceedings of
 * the IEEE Congress on Evolutionary Computation (CEC'06); 2006 IEEE World
 * Congress on Computation Intelligence (WCCI'06)</span>,
 * July&nbsp;16&ndash;21, 2006, Vancouver, BC, Canada: Sheraton Vancouver
 * Wall Centre Hotel, pages 1939&ndash;1946, <a
 * href="http://www.ece.okstate.edu/faculty/yen/">Gary G. Yen</a>, <a
 * href="http://dces.essex.ac.uk/staff/sml/">Simon M. Lucas</a>, <a
 * href="http://www.natural-selection.com/people_gfogel.html">Gary B.
 * Fogel</a>, <a href=
 * "https://www.nottingham.ac.uk/computerscience/people/graham.kendall">
 * Graham Kendall</a>, <a
 * href="http://www.welisa.uni-rostock.de/staff/ralf-salomon/">Ralf
 * Salomon</a>, Byoung-Tak Zhang, <a
 * href="http://delta.cs.cinvestav.mx/~ccoello/">Carlos Artemio Coello
 * Coello</a>, and&nbsp;<a href="https://notendur.hi.is/~tpr/">Thomas
 * Philip Runarsson</a>, editors, Piscataway, NJ, USA: IEEE Computer
 * Society. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780394879">0-7803-9487-9</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780394872">978-0-7803
 * -9487-2</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CEC.2006.1688544">10.1109/CEC.2006
 * .1688544</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/181016874">181016874</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=ZP7RPQAACAAJ">ZP7RPQAACAAJ</a>;
 * INSPEC Accession Number:&nbsp;9723660</div></li>
 * <li><div><span id="cite_LL2001EODAANTFEC" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Estimation of
 * Distribution Algorithms &#8210; A New Tool for Evolutionary
 * Computation,&rdquo;</span> 2001, <a href=
 * "http://cig.fi.upm.es/index.php?option=com_content&amp;view=article&amp;id=78&amp;Itemid=111"
 * >Pedro Larra&#241;aga</a> and&nbsp;<a
 * href="http://www.sc.ehu.es/ccwbayes/members/jalozano/home/index.html"
 * >Jos&#233; Antonio Lozano</a>, editors, volume 2 of Genetic Algorithms
 * and Evolutionary Computation, Boston, MA, USA: Springer US
 * and&nbsp;Norwell, MA, USA: Kluwer Academic Publishers. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0792374665">0-7923-7466-5</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780792374664">978-0-7923
 * -7466-4</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/47996547">47996547</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=o0llxS4u93wC">o0llxS4u93wC</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=kyU74gxp1rsC">kyU74gxp1rsC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/19320167">1932-0167</a></div></li>
 * <li><div><span id="cite_LLIB2006TANEC" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Towards a New
 * Evolutionary Computation &#8210; Advances on Estimation of Distribution
 * Algorithms,&rdquo;</span> 2006, <a
 * href="http://www.sc.ehu.es/ccwbayes/members/jalozano/home/index.html"
 * >Jos&#233; Antonio Lozano</a>, <a href=
 * "http://cig.fi.upm.es/index.php?option=com_content&amp;view=article&amp;id=78&amp;Itemid=111"
 * >Pedro Larra&#241;aga</a>, <a
 * href="http://www.sc.ehu.es/ccwbayes/members/inaki.htm">I&#241;aki
 * Inza</a>, and&nbsp;<a href="http://www.sc.ehu.es/acwbecae/">Endika
 * Bengoetxea</a>, editors, volume 192/2006 of Studies in Fuzziness and
 * Soft Computing, Berlin, Germany: Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540290060">3-540-29006-0</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540290063">978-3-540-
 * 29006-3</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/2005932568">2005932568</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/11007937">10.1007/11007937</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/63763942">63763942</a>, <a
 * href="https://www.worldcat.org/oclc/318299594">318299594</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/oclc/181473672">181473672</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=0dku9OKxl6AC">0dku9OKxl6AC</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=r0UrGB8y2V0C">r0UrGB8y2V0C</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/14349922">1434-9922</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/18600808">1860-0808</a></div></li>
 * <li><div><span id="cite_T2002PMBGAIPRDUEH" /><a
 * href="http://www2.hannan-u.ac.jp/~tsutsui/index-e.html">Shigeyoshi
 * Tsutsui</a> <span style="color:gray">[&#31570;&#20117;
 * &#33538;&#32681;</span>]: <span
 * style="font-weight:bold">&ldquo;Probabilistic Model-Building Genetic
 * Algorithms in Permutation Representation Domain using Edge
 * Histogram,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the 7th
 * International Conference on Parallel Problem Solving from Nature (PPSN
 * VII)</span>, September&nbsp;7&ndash;11, 2002, Granada, Spain, pages
 * 224&ndash;233, Juan Juli&#225;n Merelo-Guerv&#243;s, Panagiotis
 * Adamidis, <a href="https://homepages.fhv.at/hgb/">Hans-Georg Beyer</a>,
 * Jos&#233; Luis Fern&#225;ndez-Villaca&#241;as Mart&#237;n, and&nbsp;<a
 * href="https://en.wikipedia.org/wiki/Hans-Paul_Schwefel">Hans-Paul
 * Schwefel</a>, editors, volume 2439/2002 of Lecture Notes in Computer
 * Science (LNCS), Berlin, Germany: Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540441395">3-540-44139-5</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540441397">978-3-540-
 * 44139-7</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/3-540-45712-7_22">10.1007/3-540-45712
 * -7_22</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/248499908">248499908</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=KD-WMBb4AhkC">KD-WMBb4AhkC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a>. <div>links:
 * [<a href="http://www2.hannan-u.ac.jp/~tsutsui/ps/ppsn2002.pdf">1</a>]
 * and&nbsp;[<a href=
 * "http://www.researchgate.net/publication/220701611_Probabilistic_Model-Building_Genetic_Algorithms_in_Permutation_Representation_Domain_Using_Edge_Histogram/file/32bfe50e4d8b855800.pdf"
 * >2</a>]</div></div></li>
 * <li><div><span id="cite_T2009POAEAOAPWMCP" /><a
 * href="http://www2.hannan-u.ac.jp/~tsutsui/index-e.html">Shigeyoshi
 * Tsutsui</a> <span style="color:gray">[&#31570;&#20117;
 * &#33538;&#32681;</span>]: <span
 * style="font-weight:bold">&ldquo;Parallelization of an Evolutionary
 * Algorithm on a Platform with Multi-Core Processors,&rdquo;</span> in
 * <span style="font-style:italic;font-family:cursive;">Artificial
 * Evolution: Revised Selected Papers from the 9th International
 * Conference, Evolution Artificielle (EA'09)</span>,
 * October&nbsp;26&ndash;28, 2009, Strasbourg, France, pages 61&ndash;73,
 * Pierre Collet, Nicolas Monmarch&#233;, Pierrick Legrand, <a
 * href="http://www.lri.fr/~marc/">Marc Schoenauer</a>, and&nbsp;<a
 * href="http://evelyne.lutton.free.fr/">Evelyne Lutton</a>, editors,
 * volume 5975 of Theoretical Computer Science and General Issues (SL 1),
 * Lecture Notes in Computer Science (LNCS), Berlin, Germany:
 * Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3642141552">3642141552</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783642141553">978-3-642-
 * 14155-3</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/978-3-642-14156-0_6">10.1007/978-
 * 3-642-14156-0_6</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a></div></li>
 * <li><div><span id="cite_TF2012IOHBSAWAESWC" /><a
 * href="http://www2.hannan-u.ac.jp/~tsutsui/index-e.html">Shigeyoshi
 * Tsutsui</a> <span style="color:gray">[&#31570;&#20117;
 * &#33538;&#32681;</span>] and&nbsp;<a href=
 * "http://kyoindb.acs.osakafu-u.ac.jp/profile/out.ERkVJRmfbA-tSQ2EAeXJTQ==.html"
 * >Noriyuki Fujimoto</a> <span style="color:gray">[&#34276;&#26412;
 * &#20856;&#24184;</span>]: <span
 * style="font-weight:bold">&ldquo;Implementation of Histogram Based
 * Sampling Algorithm within an EDA Scheme with CUDA,&rdquo;</span> in
 * <span style="font-style:italic;font-family:cursive;">Proceedings of the
 * 2012 IEEE Congress on Evolutionary Computation (CEC'12); The 2012 IEEE
 * World Congress on Computational Intelligence (WCCI'12)</span>,
 * June&nbsp;10&ndash;15, 2012, Brisbane, QLD, Australia: International
 * Convention Centre, pages 1&ndash;8. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781467315104"
 * >978-1-4673-1510-4</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CEC.2012.6256444">10.1109/CEC.2012
 * .6256444</a>; EI:&nbsp;12910273</div></li>
 * <li><div><span id="cite_PSL2005DE" />Kenneth V. Price, Rainer M. Storn,
 * and&nbsp;Jouni A. Lampinen: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Differential
 * Evolution &#8210; A Practical Approach to Global
 * Optimization,&rdquo;</span> 2005, Natural Computing Series, Basel,
 * Switzerland: Birkh&#228;user Verlag and&nbsp;New York, NY, USA: Springer
 * New York. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540209506">3-540-20950-6</a>, <a
 * href="https://www.worldcat.org/isbn/3540313060">3-540-31306-0</a>, <a
 * href
 * ="https://www.worldcat.org/isbn/9783540209508">978-3-540-20950-8</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540313069">978-3-540-
 * 31306-9</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/63127211">63127211</a>, <a
 * href="https://www.worldcat.org/oclc/63381529">63381529</a>, and&nbsp;<a
 * href="https://www.worldcat.org/oclc/318292603">318292603</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=S67vX-KqVqUC">S67vX-KqVqUC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/16197127">1619-7127</a></div></li>
 * <li><div><span id="cite_SP1995DE" />Rainer M. Storn and&nbsp;Kenneth V.
 * Price: <span style="font-weight:bold">&ldquo;Differential Evolution
 * &#8210; A Simple and Efficient Adaptive Scheme for Global Optimization
 * over Continuous Spaces,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;TR-95-012, March&nbsp;1995; published by Berkeley, CA, USA:
 * International Computer Science Institute (ICSI), University of
 * California. <div>links: [<a
 * href="http://citeseer.ist.psu.edu/182432.html">1</a>] and&nbsp;[<a
 * href="http://http.icsi.berkeley.edu/~storn/TR-95-012.pdf">2</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.1.9696">10.1
 * .1.1 .9696</a></div></div></li>
 * <li><div><span id="cite_S1996DEFO" />Rainer M. Storn: <span
 * style="font-weight:bold">&ldquo;On the Usage of Differential Evolution
 * for Function Optimization,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the
 * Biennial Conference of the North American Fuzzy Information Processing
 * Society (NAFIPS'96)</span>, June&nbsp;19&ndash;22, 1996, Berkeley, CA,
 * USA, pages 519&ndash;523, Michael H. Smith, M. Lee, James M. Keller,
 * and&nbsp;John Yen, editors, Piscataway, NJ, USA: IEEE Computer Society.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780332253">0-7803-3225-3</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780332256">978-0-7803
 * -3225-6</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/NAFIPS.1996.534789">10.1109/NAFIPS
 * .1996.534789</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/35188367">35188367</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=X9SOJgAACAAJ">X9SOJgAACAAJ</a>;
 * INSPEC Accession Number:&nbsp;5358034. <div>links: [<a
 * href="https://eprints.kfupm.edu.sa/55484/1/55484.pdf">1</a>]
 * and&nbsp;[<a
 * href="www.icsi.berkeley.edu/~storn/bisc1.ps.gz">2</a>]</div></div></li>
 * <li><div><span id="cite_HM2001VNSPAA" /><a
 * href="http://www.hec.ca/profs/pierre.hansen.html">Pierre Hansen</a>
 * and&nbsp;<a href=
 * "http://www.brunel.ac.uk/siscm/mathematical-sciences/people-in-maths/academic-staff/drnenadmladenovic"
 * >Nenad Mladenovi&#263;</a>: <span
 * style="font-weight:bold">&ldquo;Variable Neighborhood Search: Principles
 * and Applications,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">European Journal of
 * Operational Research (EJOR)</span> 130(3):449&ndash;467, May&nbsp;1,
 * 2001; published by Amsterdam, The Netherlands: Elsevier Science
 * Publishers B.V. and&nbsp;Amsterdam, The Netherlands: North-Holland
 * Scientific Publishers Ltd.. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1016/S0377-2217(00)00100-4">10.1016
 * /S0377-2217(00)00100-4</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03772217">0377-2217</a></div></li>
 * <li><div><span id="cite_HMP2008VNSMAA" /><a
 * href="http://www.hec.ca/profs/pierre.hansen.html">Pierre Hansen</a>, <a
 * href=
 * "http://www.brunel.ac.uk/siscm/mathematical-sciences/people-in-maths/academic-staff/drnenadmladenovic"
 * >Nenad Mladenovi&#263;</a>, and&nbsp;<a
 * href="http://jamoreno.webs.ull.es/">Jos&#233; Andr&#233;s Moreno
 * P&#233;rez</a>: <span style="font-weight:bold">&ldquo;Variable
 * Neighbourhood Search: Methods and Applications,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">4OR</span>
 * 6(4):319&ndash;360, December&nbsp;1, 2008; published by Berlin, Germany:
 * Springer-Verlag GmbH. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/s10288-008-0089-1">10.1007/s10288
 * -008-0089-1</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/16194500">1619-4500</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16142411">1614-2411</a></div></li>
 * <li><div><span id="cite_HMP2010VNSMAA" /><a
 * href="http://www.hec.ca/profs/pierre.hansen.html">Pierre Hansen</a>, <a
 * href=
 * "http://www.brunel.ac.uk/siscm/mathematical-sciences/people-in-maths/academic-staff/drnenadmladenovic"
 * >Nenad Mladenovi&#263;</a>, and&nbsp;<a
 * href="http://jamoreno.webs.ull.es/">Jos&#233; Andr&#233;s Moreno
 * P&#233;rez</a>: <span style="font-weight:bold">&ldquo;Variable
 * Neighbourhood Search: Methods and Applications,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Annals of Operations
 * Research</span> 175(1):367&ndash;407, March&nbsp;1, 2010; published by
 * Dordrecht, Netherlands: Springer Netherlands and&nbsp;Amsterdam, The
 * Netherlands: J. C. Baltzer AG, Science Publishers. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/s10479-009-0657-6"
 * >10.1007/s10479-009-0657-6</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/02545330">0254-5330</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15729338">1572-9338</a></div></li>
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
 */
public class HeuristicInitRNSTemplateBasedEHBSA extends
    _HeuristicInitRefiningTemplateBasedEHBSA {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** create */
  public HeuristicInitRNSTemplateBasedEHBSA() {
    super("RNS"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  final UnaryOperator<int[]> createUnary() {
    return new PermutationRNSMutation();
  }

  /**
   * Perform the template-based EHBSA with heuristic initialization and RNS
   * local search
   *
   * @param args
   *          the command line arguments
   */
  public static void main(final String[] args) {
    TSPAlgorithmRunner.benchmark(Instance.SYMMETRIC_INSTANCES,
        HeuristicInitRNSTemplateBasedEHBSA.class,//
        args);
  }
}
