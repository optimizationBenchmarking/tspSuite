package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA;

import java.io.PrintStream;
import java.util.Arrays;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.TSPAlgorithm;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.operators.permutation.creation.PermutationCreateUniform;
import org.logisticPlanning.tsp.solving.utils.NodeManager;
import org.logisticPlanning.tsp.solving.utils.candidates.CandidateSet;
import org.logisticPlanning.tsp.solving.utils.edgeData.EdgeNumber;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;
import org.logisticPlanning.utils.math.random.Randomizer;
import org.logisticPlanning.utils.math.statistics.aggregates.StableSum;

/**
 *
 <p>
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
 * <li>The model used in this type of EDA is a
 * {@link #m_edgeHistogramModel edge histogram}. This histogram counts, for
 * each edge, how often it appears in a set of {@link #m_templates selected
 * solutions}. The probability of being added to a new solution (being
 * &quot;
 * {@link #sample(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction, int[], int[])
 * sampled}&quot;) of an edge is then roughly proportional to this value.</li>
 * <li>The algorithm implemented in this package is a template-based EHBSA
 * (defined as eEHBSA in&nbsp;[<a href="#cite_T2009POAEAOAPWMCP"
 * style="font-weight:bold">6</a>]). When a new solution (
 * {@link #m_samples sample}) is created, first a part is copied from a
 * parent ( {@link #m_templates template}) solution. The
 * {@link #calculateSampleLength(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction)
 * remainder} is then filled by sampling the edge histogram model. After
 * copying some nodes from the template, we begin &quot;sampling&quot; the
 * model at the node at the end of the copied sequence. This node is called
 * &quot;source&quot; All so-far unused {@link #m_candidateSet candidate}
 * nodes (see <a href="#_candidate">below</a>) are considered as next
 * &quot;destination&quot; to follow that source. The chance of a node
 * {@code i} being picked is proportional to
 * <code>{@link #m_bias bias}+{@link #m_edgeHistogramModel histogram}(source,i)</code>
 * , where {@link #m_bias bias} is a small added value preventing premature
 * convergence.</li>
 * <li>The template-based EHBSA maintains a population a bit similar to the
 * way Differential Evolution (DE)&nbsp;[<a href="#cite_PSL2005DE"
 * style="font-weight:bold">8</a>, <a href="#cite_SP1995DE"
 * style="font-weight:bold">9</a>, <a href="#cite_S1996DEFO"
 * style="font-weight:bold">10</a>] does: From each template, exactly one
 * offspring solution (sample) is
 * {@link #sample(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction, int[], int[])
 * created}. After all templates have been sampled once, the samples
 * compete with their direct parents. If a sample is better than its
 * corresponding template, it will replace the template in the template
 * set. This means also that the edges of the template are &quote;
 * {@link #subFromModel(int[]) removed}&quot; from the edge histogram and
 * the edges of the sample will be &quot;{@link #addToModel(int[]) add}
 * &quot; to it.</li>
 * <li id="_candidate">The model
 * {@link #sample(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction, int[], int[])
 * sampling} procedure is an iterative process. At each iteration, there is
 * a current node {@code source} and the question is which next node (
 * {@code destination}) should be added. The EHBSA therefore maintains a
 * {@link #m_candidateSet candidate set}, a set that contains, for each
 * node {@code i}, the <code>{@link #m_candidateSetSize s}</code> nearest
 * neighbors. Usually, only the nodes that are in the candidate set of node
 * {@code source} may be chosen as next {@code destination}. However, it
 * may be that all such nodes have already been visited, i.e., that the
 * candidate set is empty. In this case, an
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA.EAugmentationMethod
 * augmentation} has to be performed.</li>
 * <li>The
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA.EAugmentationMethod#AUGMENT_BY_HISTOGRAM
 * augmentation} method performed in the original version of the EHBSA
 * chooses the unvisited node with the highest
 * {@link #m_edgeHistogramModel histogram} value, i.e., adds the edge that
 * is found in the most {@link #m_templates templates}. This
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
 *
 * @author <ul>
 *         <li>
 *         <em><a href="http://www2.hannan-u.ac.jp/~tsutsui/index-e.html">Shigeyoshi Tsutsui</a></em>
 *         [&#x7B52;&#x4E95; &#x8302;&#x7FA9;], Email:&nbsp;<a
 *         href="mailto:tsutsui@hannan-u.ac.jp">tsutsui@hannan-u.ac.jp</a></li>
 *         <li><em><a href="http://www.it-weise.de/">Thomas Weise</a></em>,
 *         Email:&nbsp;<a
 *         href="mailto:tweise@ustc.edu.cn">tweise@ustc.edu.cn</a> (role:
 *         adaption to benchmarking framework)</li>
 *         </ul>
 */
public class TemplateBasedEHBSA extends TSPAlgorithm {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the gamma parameter: {@value} */
  public static final String PARAM_GAMMA = "ehbsaGamma"; //$NON-NLS-1$
  /** a fixed population size: {@value} */
  public static final String PARAM_BASIC_POP_SIZE = "ehbsaBasicPopulationSize"; //$NON-NLS-1$

  /**
   * if no {@link #PARAM_BASIC_POP_SIZE fixed population size} is
   * specified, the corresponding multiple of this factor with the problem
   * dimension is added to the population size: {@value}
   */
  public static final String PARAM_POP_SIZE_FACTOR = "ehbsaPopulationSizeFactor"; //$NON-NLS-1$
  /** the histogram order bias: {@value} */
  public static final String PARAM_HISTOGRAM_ORDER_BIAS = "ehbsaHistogramOrderBias";//$NON-NLS-1$
  /** the candidate set size: {@value} */
  public static final String PARAM_CANDIDATE_SET_SIZE = "ehbsaCandidateSetSize"; //$NON-NLS-1$
  /** the template method: {@value} */
  public static final String PARAM_TEMPLATE_METHOD = "ehbsaTemplateMethod"; //$NON-NLS-1$
  /** the augmentation method: {@value} */
  public static final String PARAM_AUGMENTATION_METHOD = "ehbsaAugmentationMethod"; //$NON-NLS-1$

  /** the used number of templates: {@value} */
  public static final String INFO_TEMPLATE_COUNT = "ehbsaTemplateCount"; //$NON-NLS-1$
  /** the used number of samples: {@value} */
  public static final String INFO_SAMPLE_COUNT = "ehbsaSampleCount"; //$NON-NLS-1$

  /** the used model class: {@value} */
  public static final String INFO_MODEL = "ehbsaUsedModel"; //$NON-NLS-1$
  /** the used bias: {@value} */
  public static final String INFO_BIAS = "ehbsaUsedBias"; //$NON-NLS-1$
  /** the used candidate set class: {@value} */
  public static final String INFO_CANDIDATE_SET = "ehbsaUsedCandidateSetClass"; //$NON-NLS-1$
  /** the used number of performed generations: {@value} */
  public static final String INFO_COMPLETED_GENERATIONS = "ehbsaCompletedGenerations"; //$NON-NLS-1$

  /** the default gamma: {@value} */
  public static final double DEFAULT_GAMMA = 0.5d;
  /** the default fixed population size: {@value} */
  public static final int DEFAULT_FIXED_POPULATION_SIZE = 0;
  /** the default population size factor: {@value} */
  public static final double DEFAULT_POPULATION_SIZE_FACTOR = 2d;
  /** the default histogram order bias: {@value} */
  public static final double DEFAULT_HISTOGRAM_ORDER_BIAS = 0.05d;
  /** the default candidate set size: {@value} */
  public static final int DEFAULT_CANDIDATE_SET_SIZE = 20;
  /** the default template method */
  public static final ETemplateMethod DEFAULT_TEMPLATE_METHOD = ETemplateMethod.CUT;
  /** the default augmentation method */
  public static final EAugmentationMethod DEFAULT_AUGMENTATION_METHOD = EAugmentationMethod.AUGMENT_BY_HISTOGRAM;

  /**
   * the gamma value is a parameter to the
   * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA.ETemplateMethod
   * function} computing how much of a new sample should come from the
   * model and how much should come from a template, see
   * {@link #m_templateMethod} and {@link #PARAM_GAMMA}
   *
   * @serial a {@code double} value
   */
  private double m_gamma;

  /**
   * the population size factor which is multiplied with the
   * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#n()
   * problem dimension} and added to the {@link #m_basicPopulationSize
   * basic population size}
   *
   * @serial a {@code double} value
   */
  private double m_populationSizeFactor;

  /**
   * the problem-independent basic population size, which is added to
   * <code>{@link #m_populationSizeFactor}*{@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#n() n}</code>
   * , see {@link #PARAM_BASIC_POP_SIZE} @serial serial field
   */
  private int m_basicPopulationSize;

  /**
   * the histogram order bias, a value used to compute a &quot;
   * {@link #m_bias histogram offset}&quot; added to the histogram value of
   * each edge in order to prevent premature convergence, see
   * {@link #PARAM_HISTOGRAM_ORDER_BIAS}
   *
   * @serial a {@code double} value
   */
  private double m_histogramOrderBias;

  /**
   * the candidate set size: we choose the edge to sample only from the
   * edges to the nearest {@link #m_candidateSetSize} neighbors, see
   * {@link #PARAM_CANDIDATE_SET_SIZE} @serial serial field
   */
  private int m_candidateSetSize;

  /**
   * the template method deciding how many elements of a sample should come
   * from the model (the rest is copied from a template), see
   * {@link #PARAM_TEMPLATE_METHOD} and
   * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA.ETemplateMethod}
   *
   * @serial a non-null instance
   */
  private ETemplateMethod m_templateMethod;

  /**
   * the augmentation method: what do we do if all nodes in the candidate
   * set of the current node have already been visited? (see
   * {@link #PARAM_AUGMENTATION_METHOD}
   *
   * @serial a non-null instance
   */
  private EAugmentationMethod m_augmentationMethod;

  /** the edge history matrix */
  private transient EdgeNumber m_edgeHistogramModel; // estimate value

  /**
   * the bias, i.e., an offset added to the histogram values of each edge
   * to prevent premature convergence, see {@link #INFO_BIAS} and
   * {@link #m_histogramOrderBias}
   */
  private transient double m_bias;

  /** the candidate list */
  private transient CandidateSet m_candidateSet;

  /** This is the set of templates */
  private transient TemplateIndividual[] m_templates;

  /** The set of samples */
  private transient TemplateIndividual[] m_samples;

  /** the node manager */
  transient NodeManager m_nodes;

  /** the model weights */
  private transient double[] m_modelWeights;

  /** the candidates */
  private transient int[] m_candidates;

  /** the sum */
  private transient StableSum m_sum;

  /** the number of completed generations */
  private transient int m_completedGenerations;

  /** create */
  public TemplateBasedEHBSA() {
    this(null);
  }

  /**
   * create
   *
   * @param name
   *          the name
   */
  public TemplateBasedEHBSA(final String name) {
    super(((name != null) ? name : "") + //$NON-NLS-1$
        "TemplateBasedEHBSA"); //$NON-NLS-1$
    this.m_gamma = TemplateBasedEHBSA.DEFAULT_GAMMA;
    this.m_basicPopulationSize = TemplateBasedEHBSA.DEFAULT_FIXED_POPULATION_SIZE;
    this.m_populationSizeFactor = TemplateBasedEHBSA.DEFAULT_POPULATION_SIZE_FACTOR;
    this.m_histogramOrderBias = TemplateBasedEHBSA.DEFAULT_HISTOGRAM_ORDER_BIAS;
    this.m_candidateSetSize = TemplateBasedEHBSA.DEFAULT_CANDIDATE_SET_SIZE;
    this.m_templateMethod = TemplateBasedEHBSA.DEFAULT_TEMPLATE_METHOD;
    this.m_augmentationMethod = TemplateBasedEHBSA.DEFAULT_AUGMENTATION_METHOD;
    this.m_bias = Double.NaN;
    this.m_completedGenerations = (-1);
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable.printKey(TemplateBasedEHBSA.PARAM_CANDIDATE_SET_SIZE, ps);
    ps.println(this.m_candidateSetSize);

    Configurable.printKey(TemplateBasedEHBSA.PARAM_GAMMA, ps);
    ps.println(this.m_gamma);

    Configurable.printKey(TemplateBasedEHBSA.PARAM_BASIC_POP_SIZE, ps);
    ps.println(this.m_basicPopulationSize);

    Configurable.printKey(TemplateBasedEHBSA.PARAM_POP_SIZE_FACTOR, ps);
    ps.println(this.m_populationSizeFactor);

    Configurable.printKey(TemplateBasedEHBSA.PARAM_HISTOGRAM_ORDER_BIAS,
        ps);
    ps.println(this.m_histogramOrderBias);

    Configurable.printKey(TemplateBasedEHBSA.PARAM_TEMPLATE_METHOD, ps);
    Configurable.printlnObject(this.m_templateMethod, ps);

    Configurable
        .printKey(TemplateBasedEHBSA.PARAM_AUGMENTATION_METHOD, ps);
    Configurable.printlnObject(this.m_augmentationMethod, ps);

    if (this.m_templates != null) {
      Configurable.printKey(TemplateBasedEHBSA.INFO_TEMPLATE_COUNT, ps);
      ps.println(this.m_templates.length);
    }
    if (this.m_samples != null) {
      Configurable.printKey(TemplateBasedEHBSA.INFO_SAMPLE_COUNT, ps);
      ps.println(this.m_samples.length);
    }

    if (!(Double.isNaN(this.m_bias))) {
      Configurable.printKey(TemplateBasedEHBSA.INFO_BIAS, ps);
      ps.println(this.m_bias);
    }
    if (this.m_edgeHistogramModel != null) {
      Configurable.printKey(TemplateBasedEHBSA.INFO_MODEL, ps);
      Configurable.printlnClass(this.m_edgeHistogramModel.getClass(), ps);
    }
    if (this.m_candidateSet != null) {
      Configurable.printKey(TemplateBasedEHBSA.INFO_CANDIDATE_SET, ps);
      Configurable.printlnClass(this.m_candidateSet.getClass(), ps);
    }
    if (this.m_completedGenerations >= 0) {
      Configurable.printKey(TemplateBasedEHBSA.INFO_COMPLETED_GENERATIONS,
          ps);
      ps.println(this.m_completedGenerations);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void printParameters(final PrintStream ps) {
    super.printParameters(ps);

    Configurable.printKey(TemplateBasedEHBSA.PARAM_CANDIDATE_SET_SIZE, ps);
    ps.println("the candidate set size"); //$NON-NLS-1$

    Configurable.printKey(TemplateBasedEHBSA.PARAM_GAMMA, ps);
    ps.println("the gamma parameter"); //$NON-NLS-1$

    Configurable.printKey(TemplateBasedEHBSA.PARAM_BASIC_POP_SIZE, ps);
    ps.println("the fixed population size"); //$NON-NLS-1$

    Configurable.printKey(TemplateBasedEHBSA.PARAM_POP_SIZE_FACTOR, ps);
    ps.println("the multiplied with problem dimension to get population size"); //$NON-NLS-1$

    Configurable.printKey(TemplateBasedEHBSA.PARAM_HISTOGRAM_ORDER_BIAS,
        ps);
    ps.println("the histogram order bias"); //$NON-NLS-1$

    Configurable.printKey(TemplateBasedEHBSA.PARAM_TEMPLATE_METHOD, ps);
    ps.println("the template method"); //$NON-NLS-1$

    Configurable
        .printKey(TemplateBasedEHBSA.PARAM_AUGMENTATION_METHOD, ps);
    ps.println("the augmentation method"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public void configure(final Configuration config) {
    super.configure(config);

    this.m_gamma = config.getDouble(TemplateBasedEHBSA.PARAM_GAMMA, 0d,
        1d, this.m_gamma);

    this.m_basicPopulationSize = config.getInt(
        TemplateBasedEHBSA.PARAM_BASIC_POP_SIZE, 0, 100000,
        this.m_basicPopulationSize);

    this.m_populationSizeFactor = config.getDouble(
        TemplateBasedEHBSA.PARAM_POP_SIZE_FACTOR, 0d, 100d,
        this.m_populationSizeFactor);

    this.m_histogramOrderBias = config.getDouble(
        TemplateBasedEHBSA.PARAM_HISTOGRAM_ORDER_BIAS, 0d, 1d,
        this.m_histogramOrderBias);

    this.m_candidateSetSize = config.getInt(
        TemplateBasedEHBSA.PARAM_CANDIDATE_SET_SIZE, 0, Integer.MAX_VALUE,
        this.m_candidateSetSize);

    this.m_templateMethod = config.getConstant(
        TemplateBasedEHBSA.PARAM_TEMPLATE_METHOD, ETemplateMethod.class,
        ETemplateMethod.class, this.m_templateMethod);

    this.setAugmentationMethod(config.getConstant(
        TemplateBasedEHBSA.PARAM_AUGMENTATION_METHOD,
        EAugmentationMethod.class, EAugmentationMethod.class,
        this.getAugmentationMethod()));
  }

  /** {@inheritDoc} */
  @Override
  public TemplateBasedEHBSA clone() {
    TemplateBasedEHBSA copy;

    copy = ((TemplateBasedEHBSA) (super.clone()));
    this.__clear();
    copy.m_completedGenerations = (-1);
    return copy;
  }

  /** clear all memory and data */
  private final void __clear() {
    this.m_edgeHistogramModel = null;
    this.m_templates = null;
    this.m_samples = null;
    this.m_modelWeights = null;
    this.m_nodes = null;
    this.m_candidates = null;
    this.m_candidateSet = null;
    this.m_sum = null;
  }

  /**
   * Get the augmentation method
   *
   * @return the augmentation method
   */
  public final EAugmentationMethod getAugmentationMethod() {
    return this.m_augmentationMethod;
  }

  /**
   * Set the augmentation method
   *
   * @param method
   *          the augmentation method
   */
  public final void setAugmentationMethod(final EAugmentationMethod method) {
    this.m_augmentationMethod = ((method != null) ? method
        : TemplateBasedEHBSA.DEFAULT_AUGMENTATION_METHOD);
  }

  /**
   * <p>
   * This method is called to generate the first generation of individuals.
   * It receives an array {@code dest} to be filled with individual
   * records. This method can be overridden to use
   * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.TSPHeuristic
   * heuristics} to generate the solutions instead.
   * </p>
   * <p>
   * If the method returns {@code true}, this first generation is used to
   * initialize the {@link #m_edgeHistogramModel edge histogram model}.
   * </p>
   * <p>
   * The default implementation here fills the population with random
   * individuals. It returns {@code false}, since it makes no sense to
   * initialize the model with randomly chosen edges. This way, the
   * {@link #m_edgeHistogramModel model} stays &quot;empty&quot; too, i.e.,
   * all edges have the same probability of being sampled. However, this
   * default implementation still calls
   * {@link #refineIndividual(ObjectiveFunction, TemplateIndividual)} on
   * each generated solution, so if you override
   * {@link #refineIndividual(ObjectiveFunction, TemplateIndividual)} to
   * perform some local search, you should also override this method to
   * return {@code true} instead.
   * </p>
   *
   * @param f
   *          the objective function
   * @param dest
   *          the destination array
   * @return {@code true} if the created individuals should enter the
   *         model, {@code false} otherwise -- purely randomly created
   *         individuals should not enter the model, individuals created by
   *         a local search or heuristic should
   */
  protected boolean createFirstGeneration(final ObjectiveFunction f,
      final TemplateIndividual[] dest) {
    final int n;
    final Randomizer r;
    int i;
    TemplateIndividual ind;

    n = f.n();
    r = f.getRandom();
    for (i = dest.length; (--i) >= 0;) {
      dest[i] = ind = new TemplateIndividual();
      ind.solution = PermutationCreateUniform.create(n, r);
      ind.tourLength = f.evaluate(ind.solution);
      this.refineIndividual(f, ind);
    }

    return false;
  }

  /**
   * This method refines a given individual which must already hold a valid
   * solution. It may apply a local search. (In this default
   * implementation, it does not).
   *
   * @param ind
   *          the individual to be refined
   * @param f
   *          the objective function
   */
  protected void refineIndividual(final ObjectiveFunction f,
      final TemplateIndividual ind) {
    //
  }

  /** {@inheritDoc} */
  @Override
  public final void solve(final ObjectiveFunction f) {
    final TemplateIndividual[] templates, samples;
    final int n;
    TemplateIndividual sample, template;
    final boolean addToModel;
    int i;

    n = f.n();
    templates = this.m_templates;
    samples = this.m_samples;

    // create the first generation
    addToModel = this.createFirstGeneration(f, templates);
    if (f.shouldTerminate()) {// we may already have exhausted our runtime
      // here
      return;
    }

    if (addToModel) {
      // true was returned, so we have to add the individuals to the model
      for (final TemplateIndividual ind : templates) {
        this.addToModel(ind.solution);
        ind.isPartOfModel = true;
      }
    }

    // The algorithm has properly started up, now perform the main loop
    for (;;) {
      this.m_completedGenerations++;

      // sample the new generation
      for (i = samples.length; (--i) >= 0;) {
        if (f.shouldTerminate()) {// should we quit?
          return;
        }

        sample = samples[i];
        if (sample == null) {
          samples[i] = sample = new TemplateIndividual();
          sample.solution = new int[n];
        }

        // sample the individual from the template
        this.sample(f, sample.solution, templates[i].solution);
        sample.tourLength = f.evaluate(sample.solution);

        // refine the individual: maybe invoke a local search
        this.refineIndividual(f, sample);
      }

      // update the model
      for (i = samples.length; (--i) >= 0;) {
        sample = samples[i];
        template = templates[i];

        // is the sample better than its template?
        if (sample.tourLength < template.tourLength) {
          // yes: replace the template
          templates[i] = sample;
          samples[i] = null;

          if (template.isPartOfModel) {
            this.subFromModel(template.solution);
          }
          // enter the new template into the model
          this.addToModel(sample.solution);
          sample.isPartOfModel = true;
        }
      }
    }
  }

  /**
   * Add an solution to the model: increase the histogram counter for each
   * edge in the solution.
   *
   * @param sol
   *          the solution to be added.
   */
  private final void addToModel(final int[] sol) {
    final EdgeNumber ehm;
    int last;

    ehm = this.m_edgeHistogramModel;

    last = sol[sol.length - 1];
    for (final int i : sol) {
      ehm.inc(last, i);
      last = i;
    }
  }

  /**
   * Subtract a solution from the model: decrease the histogram counter for
   * each edge in the solution.
   *
   * @param sol
   *          the solution to be subtracted.
   */
  private final void subFromModel(final int[] sol) {
    final EdgeNumber ehm;
    int last;

    ehm = this.m_edgeHistogramModel;

    last = sol[sol.length - 1];
    for (final int i : sol) {
      ehm.dec(last, i);
      last = i;
    }
  }

  /**
   * sample a new solution from the model, using a template
   *
   * @param f
   *          the objective function
   * @param dest
   *          the destination array
   * @param template
   *          the template array
   */
  private final void sample(final ObjectiveFunction f, final int[] dest,
      final int[] template) {
    final int n, sampleLength, copyLength, copyStart;
    final Randomizer r;
    final CandidateSet candidateSet;
    final NodeManager manager;
    final EdgeNumber model;
    final double[] weights;
    final int[] candidates;
    final double bias;
    final StableSum sum;

    int destIdx, i, sourceNode, destinationNode, candidateNode, candidateCount;

    n = f.n();
    r = f.getRandom();

    sampleLength = this.calculateSampleLength(f);
    copyLength = (n - sampleLength);
    copyStart = r.nextInt(n);

    // First, we copy the portion of the template that was randomly chosen
    // to
    // be preserved to the destination array. This portion will land at the
    // beginning of the array dest. (This is different from the original
    // version of the algorithm)
    // We use the efficient System.arraycopy to copy quickly.
    i = (copyStart + copyLength);
    if (i > n) { // deal with "wrapping" around at the permutation ends...
      destIdx = (n - copyStart);
      System.arraycopy(template, copyStart, dest, 0, destIdx);
      i -= n;
      System.arraycopy(template, 0, dest, destIdx, i);
    } else {
      System.arraycopy(template, copyStart, dest, 0, copyLength);
    }
    // i now holds the first element in template that was not copied
    destIdx = copyLength;

    manager = this.m_nodes;
    // Mark the remaining (not copied) sampleLength nodes as available in
    // the
    // node manager. The node manager allows deletion of nodes and checking
    // if
    // nodes are available in O(1).
    // Also: set the source node as last visited node.
    if (destIdx <= 0) {
      // in case that nothing was copied, begin at a random node
      manager.init(n);
      dest[destIdx++] = sourceNode = manager.deleteRandom(r);
    } else {// otherwise, initialize properly and set sourceNode to last
      // node.
      manager.init(template, i, sampleLength);
      sourceNode = dest[destIdx - 1];
    }

    candidateSet = this.m_candidateSet;
    bias = this.m_bias;
    weights = this.m_modelWeights;
    candidates = this.m_candidates;
    model = this.m_edgeHistogramModel;
    sum = this.m_sum;
    destinationNode = (-1);

    for (; destIdx < n; destIdx++) {

      // We now pick up all nodes from the candidate set that could be
      // chosen
      // as next destination
      candidateCount = 0;

      // We use a stable sum to add up the histogram values. Why? Because
      // it is
      // almost as same as fast and, well, numerical stable.
      sum.reset();

      // We use the class CandidateSet which generates a simple/very fast
      // proxy
      // candidate set if the candidate set size is larger or equal to
      // n-1.
      // This means, different from the original implementation, we do not
      // need
      // to distinguish between having a reduced candidate set and being
      // able
      // to use all nodes here.
      for (i = candidateSet.m(); i > 0; i--) {
        // For the given node sourceNode, check all candidate nodes

        candidateNode = candidateSet.getCandidate(sourceNode, i);
        if (manager.isIDAvailable(candidateNode)) {
          // ok, the candidate may potentially be used
          candidates[candidateCount] = candidateNode;

          sum.visitDouble(bias);// add the bias and histogram value to
          // sum
          sum.visitDouble(model.getDouble(sourceNode, candidateNode));
          weights[candidateCount] = sum.getResult(); // store sum
          candidateCount++;
        }
      }

      // The arrays "candidates" and "weights" now contain
      // "candidateCount"
      // elements.
      // The elements in weights are the (rising) sums of histogram values

      if (candidateCount > 0) {// There was at least one viable candidate
        // edge
        // The chance of being picked of a node "i" is proportional to
        // (bias +
        // histogram(sourceNode, i)). Since weights is an ordered list,
        // we can
        // do binary search on it, which is faster than running through
        // it
        // linearly.
        weights[candidateCount - 1] = Double.POSITIVE_INFINITY;
        destinationNode = Arrays.binarySearch(weights, 0, candidateCount,//
            (r.nextDouble() * sum.getResult()));

        if (destinationNode < 0) {// binarySearch may return a negative
          // index
          destinationNode = candidates[-(destinationNode + 1)];
        } else {// this is unlikely: found the exact element
          destinationNode = candidates[destinationNode];
        }
      } else {
        // Augmentation: All the nodes in the candidate set are
        // already taken! In this case, we take the available node with
        // the
        // highest edge histogram value (default, AUGMENT_BY_HISTOGRAM)
        // or the
        // closest one (AUGMENT_BY_DISTANCE).
        // Btw: We can never get here if "no" candidate set is used
        // (then
        // candidateSet is a proxy to all available nodes)
        destinationNode = this.m_augmentationMethod.augment(sourceNode,
            manager, model, f);
      }

      // store the sampled node and make it the source of next edge
      dest[destIdx] = sourceNode = destinationNode;
      manager.deleteByID(destinationNode);
    }
  }

  /**
   * Calculate the sample length. There are different
   * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA.ETemplateMethod
   * methods} to calculate how many elements of the samples should be
   * generated from the model. (The rest will be copied from the
   * templates.)
   *
   * @param f
   *          the objective function
   * @return the sample length
   */
  private final int calculateSampleLength(final ObjectiveFunction f) {
    final int n, L;

    n = f.n();

    if (this.m_gamma >= 1.0d) {
      return (n - 1);
    }

    L = this.m_templateMethod.calculateSampleLength(n, this.m_gamma,
        f.getRandom());

    if (L < 2) {
      return 2;
    }
    if (L >= n) {
      return (n - 1);
    }

    return L;
  }

  /**
   * Perform the template-based EHBSA
   *
   * @param args
   *          the command line arguments
   */
  public static void main(final String[] args) {
    TSPAlgorithmRunner.benchmark(Instance.ALL_INSTANCES,
        TemplateBasedEHBSA.class,//
        args);
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    final int n;
    final boolean sym;
    int ps;

    super.beginRun(f);

    this.m_completedGenerations = 0;

    n = f.n();
    sym = f.symmetric();

    ps = this.m_basicPopulationSize;
    ps += ((int) (0.5d + (n * this.m_populationSizeFactor)));
    ps = Math.max(2, ps);

    // There are two array: m_templates holds the templates which serve as
    // parents for the next generation. m_samples holds the individuals
    // freshly
    // sampled.
    this.m_templates = new TemplateIndividual[ps];
    this.m_samples = new TemplateIndividual[ps];

    // Initialize the candidate set holding the {@link #m_candidateSetSize}
    // nearest neighboring nodes for each node.
    this.m_candidateSet = CandidateSet.allocate(f,
        this.m_candidateSetSize, null);

    // Allocate the edge histogram model, built from ps individuals. As
    // each
    // edge can occur at most once in a solution, the histogram values are
    // between 0 and ps. The class EdgeNumber will automatically pick the
    // most
    // suitable data structure for representing our model: If, say, our
    // problem
    // is symmetric (sym==true) and ps<=127, it will choose an
    // upper-triangular
    // matrix stored in a byte array. If ps is larger than 127 but <=
    // 32767,
    // it
    // will use short[] instead and if sym==false, it will use a full
    // matrix
    // (without the diagonal elements) instead. If the augmentation method
    // permits it, the backing store will only store data for the candidate
    // nodes to save memory - this is necessary for large-scale problems,
    // but
    // slows down computation.
    this.m_edgeHistogramModel = this.m_augmentationMethod.allocate(n, sym,
        0, ps, false, this.m_candidateSet, null);

    // Compute the bias used to ensure that all edges always have a
    // non-zero
    // sampling probability.
    if (sym) {
      this.m_bias = (((2.0d * ps) / (n - 1)) * this.m_histogramOrderBias);
    } else {
      this.m_bias = ((ps / ((double) (n - 1))) * this.m_histogramOrderBias);
    }

    // The node manager is used to keep track of which nodes can be
    // inserted
    // in
    // a solution.
    this.m_nodes = new NodeManager();

    // The m_modelWeights and m_candidates arrays will hold the node
    // probabilities and nodes during the sampling
    this.m_modelWeights = new double[n];
    this.m_candidates = new int[n];

    // A simple/fast stable sum to add up the histogram values during
    // sampling
    this.m_sum = new StableSum();
  }

  /** {@inheritDoc} */
  @Override
  public void endRun(final ObjectiveFunction f) {
    this.__clear();
    super.endRun(f);
  }
}
