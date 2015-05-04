package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA;

import java.io.PrintStream;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.TSPModule;
import org.logisticPlanning.tsp.solving.algorithms.heuristics.TSPHeuristic;
import org.logisticPlanning.tsp.solving.algorithms.heuristics.TSPHeuristicWithStartNode;
import org.logisticPlanning.tsp.solving.algorithms.heuristics.doubleEndedNearestNeighbor.DoubleEndedNearestNeighborHeuristic;
import org.logisticPlanning.tsp.solving.algorithms.heuristics.edgeGreedy.EdgeGreedyHeuristic;
import org.logisticPlanning.tsp.solving.algorithms.heuristics.mst.MSTHeuristic;
import org.logisticPlanning.tsp.solving.algorithms.heuristics.nearestNeighbor.NearestNeighborHeuristic;
import org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic;
import org.logisticPlanning.tsp.solving.utils.NodeManager;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 *
 <p>
 * This is a version of the
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA.TemplateBasedEHBSA
 * template-based EHBSA} where the first population (and the model) is
 * initialized heuristically.
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
 * </ol>
 */
public class HeuristicInitTemplateBasedEHBSA extends TemplateBasedEHBSA {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the one-time initializers: {@value} */
  public static final String PARAM_INIT_ONCE = "ehbsaOneTimeInitializers"; //$NON-NLS-1$

  /** the one-time rest: {@value} */
  public static final String PARAM_INIT_MULTI = "ehbsaMultiInitializers"; //$NON-NLS-1$

  /** the heuristics which are used exactly once */
  @SuppressWarnings("unchecked")
  private static final Class<? extends TSPHeuristic>[] INIT_ONCE = new Class[] {
    MSTHeuristic.class, EdgeGreedyHeuristic.class };

  /** the heuristics which are used exactly once */
  @SuppressWarnings("unchecked")
  private static final Class<? extends TSPHeuristicWithStartNode>[] INIT_MULTI = new Class[] {
    SavingsHeuristic.class, DoubleEndedNearestNeighborHeuristic.class,
    NearestNeighborHeuristic.class };

  /**
   * the heuristics used for initialization
   *
   * @serial an array of non-null instances
   */
  private TSPHeuristic[] m_initOnce;

  /**
   * the multi-times heuristic
   *
   * @serial an array of non-null instances
   */
  private TSPHeuristicWithStartNode[] m_initMulti;

  /** create */
  public HeuristicInitTemplateBasedEHBSA() {
    this(null);
  }

  /**
   * create
   *
   * @param name
   *          the name
   */
  public HeuristicInitTemplateBasedEHBSA(final String name) {
    super("HeuristicInit" + //$NON-NLS-1$
        ((name != null) ? name : "")); //$NON-NLS-1$

    int i;

    this.m_initOnce = new TSPHeuristic[i = HeuristicInitTemplateBasedEHBSA.INIT_ONCE.length];
    for (; (--i) >= 0;) {
      try {
        this.m_initOnce[i] = HeuristicInitTemplateBasedEHBSA.INIT_ONCE[i]
            .newInstance();
      } catch (final Throwable t) {
        throw new RuntimeException(t);
      }
    }

    this.m_initMulti = new TSPHeuristicWithStartNode[i = HeuristicInitTemplateBasedEHBSA.INIT_MULTI.length];
    for (; (--i) >= 0;) {
      try {
        this.m_initMulti[i] = HeuristicInitTemplateBasedEHBSA.INIT_MULTI[i]
            .newInstance();
      } catch (final Throwable t) {
        throw new RuntimeException(t);
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  protected boolean createFirstGeneration(final ObjectiveFunction f,
      final TemplateIndividual[] dest) {
    int i;
    final int n;
    final Randomizer r;
    final NodeManager m;
    TemplateIndividual indi;

    m = this.m_nodes;
    n = f.n();
    m.init(n);
    r = f.getRandom();

    for (i = dest.length; (--i) >= 0;) {

      indi = new TemplateIndividual();
      if (i >= this.m_initOnce.length) {
        if (m.size() <= 0) {
          m.init(n);
        }
        this.m_initMulti[r.nextInt(this.m_initMulti.length)].solve(f,
            indi, m.deleteRandom(r));
      } else {
        this.m_initOnce[i].solve(f, indi);
      }

      if (f.shouldTerminate()) {
        return true;
      }

      this.refineIndividual(f, indi);
      dest[i] = indi;

      if (f.shouldTerminate()) {
        return true;
      }
    }

    return true;
  }

  /** {@inheritDoc} */
  @Override
  public HeuristicInitTemplateBasedEHBSA clone() {
    HeuristicInitTemplateBasedEHBSA cfg;
    int i;

    cfg = ((HeuristicInitTemplateBasedEHBSA) (super.clone()));

    cfg.m_initOnce = cfg.m_initOnce.clone();
    for (i = cfg.m_initOnce.length; (--i) >= 0;) {
      cfg.m_initOnce[i] = ((TSPHeuristic) (cfg.m_initOnce[i].clone()));
    }

    cfg.m_initMulti = cfg.m_initMulti.clone();
    for (i = cfg.m_initMulti.length; (--i) >= 0;) {
      cfg.m_initMulti[i] = ((TSPHeuristicWithStartNode) (cfg.m_initMulti[i]
          .clone()));
    }

    return cfg;
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable.printKey(HeuristicInitTemplateBasedEHBSA.PARAM_INIT_ONCE,
        ps);
    Configurable.printlnObject(this.m_initOnce, ps);

    Configurable.printKey(
        HeuristicInitTemplateBasedEHBSA.PARAM_INIT_MULTI, ps);
    Configurable.printlnObject(this.m_initMulti, ps);
  }

  /** {@inheritDoc} */
  @Override
  public void printParameters(final PrintStream ps) {
    super.printParameters(ps);

    for (final TSPHeuristic o : this.m_initOnce) {
      o.printParameters(ps);
    }

    for (final TSPHeuristic m : this.m_initMulti) {
      m.printParameters(ps);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void configure(final Configuration config) {
    super.configure(config);

    for (final TSPHeuristic o : this.m_initOnce) {
      o.configure(config);
    }

    for (final TSPHeuristic m : this.m_initMulti) {
      m.configure(config);
    }
  }

  /**
   * Perform the template-based EHBSA with heuristic initialization
   *
   * @param args
   *          the command line arguments
   */
  public static void main(final String[] args) {
    TSPAlgorithmRunner.benchmark(Instance.SYMMETRIC_INSTANCES,
        HeuristicInitTemplateBasedEHBSA.class,//
        args);
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    super.beginRun(f);
    TSPModule.invokeBeginRun(f, this.m_initOnce);
    TSPModule.invokeBeginRun(f, this.m_initMulti);
  }

  /** {@inheritDoc} */
  @Override
  public void endRun(final ObjectiveFunction f) {
    try {
      TSPModule.invokeEndRun(f, this.m_initMulti);
    } finally {
      try {
        TSPModule.invokeEndRun(f, this.m_initOnce);
      } finally {
        super.endRun(f);
      }
    }
  }
}
