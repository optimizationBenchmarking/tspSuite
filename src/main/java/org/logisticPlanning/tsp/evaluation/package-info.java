/**
 * <p>
 * In this package, we provide classes and utilities to be used for
 * evaluating the collected benchmark results. The evaluation component of
 * the TSP Suite is composed of several
 * {@link org.logisticPlanning.tsp.evaluation.modules.spec.Module modules}.
 * Each module performs one specific type of evaluation and/or output text
 * generation. There are three basic groups of modules:
 * </p>
 * <ol>
 * <li>
 * {@link org.logisticPlanning.tsp.evaluation.modules.impl.descriptions
 * Description Modules} write some description about the experimental
 * procedure to the
 * {@link org.logisticPlanning.utils.document.spec.Document output}.</li>
 * <li>{@link org.logisticPlanning.tsp.evaluation.modules.impl.single
 * Single Experiment Modules} write some statistics or gathered information
 * about a single
 * {@link org.logisticPlanning.tsp.evaluation.data.Experiment experiment},
 * i.e., the data gathered when applying one
 * {@link org.logisticPlanning.utils.config.Configurable#configure(org.logisticPlanning.utils.config.Configuration)
 * configuration} of one
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithm algorithm} to the
 * benchmark cases, to the
 * {@link org.logisticPlanning.utils.document.spec.Document output}.</li>
 * <li>{@link org.logisticPlanning.tsp.evaluation.modules.impl.comparison
 * Comparison Modules} compare several (at least two) different
 * {@link org.logisticPlanning.tsp.evaluation.data.Experiment experiments}
 * (algorithms) according to some measure and writing the results to the
 * {@link org.logisticPlanning.utils.document.spec.Document output}.</li>
 * </ol>
 * <p>
 * Modules are grouped hierarchically, and each module or sub-tree of this
 * hierarchy can separately be turned on or off and
 * {@link org.logisticPlanning.utils.config.Configurable#configure(org.logisticPlanning.utils.config.Configuration)
 * configured} via the command line.
 * </p>
 * <h2>Related Work</h2>
 * <p>
 * There exists some related benchmarking and algorithm development
 * frameworks for other problem types. If you are interested in numerical
 * or continuous optimization instead of combinatorial problems (like the
 * TSP), we suggest that you should visit the <a
 * href="http://coco.gforge.inria.fr/">COCO</a> website, presenting an
 * approach for <em>COmparing Continuous Optimisers</em>&nbsp;[<a
 * href="#cite_COCO2012CCCO" style="font-weight:bold">1</a>, <a
 * href="#cite_HAFR2012RPBBOBES" style="font-weight:bold">2</a>, <a
 * href="#cite_AH2005PEOAALSEA" style="font-weight:bold">3</a>] and for
 * Satisfiability problems, you may wish to take a look into <a
 * href="http://ubcsat.dtompkins.com/">UBCSAT</a>&nbsp;[<a
 * href="#cite_TH2004UAIAEEFSAFSAMS" style="font-weight:bold">4</a>, <a
 * href="#cite_UBCSAT" style="font-weight:bold">5</a>, <a
 * href="#cite_T2010DLSFSDIAA" style="font-weight:bold">6</a>]. Maybe the
 * now defunct AOAB&nbsp;[<a href="#cite_WNT2010AOABAOAB"
 * style="font-weight:bold">7</a>] framework for numerical optimization, an
 * early work by us from which we took some good lessons for this project,
 * deserves a honorable mention, too.
 * </p>
 * <h2>Package Structure of TSP Suite</h2>
 * <p>
 * The packages in this {@link org.logisticPlanning software framework}
 * follow can be divided into two sets:
 * </p>
 * <ol>
 * <li>Packages with classes related to the Traveling Salesman Problem
 * (TSP) are contained in package {@link org.logisticPlanning.tsp}.</li>
 * <li>Packages with general utility classes are contained within package
 * {@link org.logisticPlanning.utils}.</li>
 * </ol>
 * <p>
 * The {@link org.logisticPlanning.tsp TSP}-related packages all serve the
 * <em>TSP Suite</em> idea of implementing, benchmarking, and evaluating
 * TSP solvers. Hence, they a divided into three sub-packages:
 * </p>
 * <ol>
 * <li>Package {@link org.logisticPlanning.tsp.solving} contains both base
 * classes for implementing
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithm TSP solvers} as
 * well as utility classes (such as
 * {@link org.logisticPlanning.tsp.solving.operators search operations},
 * {@link org.logisticPlanning.tsp.solving.gpm GPM}s,
 * {@link org.logisticPlanning.tsp.solving.searchSpaces search space}
 * -related data structures, and other
 * {@link org.logisticPlanning.tsp.solving.utils utilities and data
 * structures} like
 * {@link org.logisticPlanning.tsp.solving.utils.candidates candidate sets}
 * and {@link org.logisticPlanning.tsp.solving.utils.edge edge objects})
 * and a variety of actual
 * {@link org.logisticPlanning.tsp.solving.algorithms algorithm
 * implementations}. This package also contains the classes for
 * automatically
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithmRunner run
 * experiments} in a parallel and distributed fashion.</li>
 * <li>Package {@link org.logisticPlanning.tsp.benchmarking} contains the
 * utilities for benchmarking the algorithms: Classes for
 * {@link org.logisticPlanning.tsp.benchmarking.dist distance computation},
 * the {@link org.logisticPlanning.tsp.benchmarking.instances benchmark
 * instances}, and the
 * {@link org.logisticPlanning.tsp.benchmarking.objective Objective
 * function} classes.</li>
 * <li>The package {@link org.logisticPlanning.tsp.evaluation} holds all
 * the modules of the {@link org.logisticPlanning.tsp.evaluation.Evaluator
 * Evaluator} component that can load the
 * {@link org.logisticPlanning.tsp.evaluation.data data} generated from the
 * benchmark runs and uses it report the performance of a
 * {@link org.logisticPlanning.tsp.evaluation.modules.impl.single single
 * algorithms} or
 * {@link org.logisticPlanning.tsp.evaluation.modules.impl.comparison
 * compare different algorithms}.</li>
 * </ol>
 * <p>
 * The {@link org.logisticPlanning.utils utility package} contains a
 * variety of different tools, such as
 * </p>
 * <ol>
 * <li>{@link org.logisticPlanning.utils.collections collection classes}</li>
 * <li>The package {@link org.logisticPlanning.utils.config config}
 * provides {@link org.logisticPlanning.utils.config.Configuration classes}
 * that can parse command line parameters or configuration files and
 * provide them to {@link org.logisticPlanning.utils.config.Configurable
 * classes} that can be configured that way.</li>
 * <li>The package {@link org.logisticPlanning.utils.document document}
 * holds a complete {@link org.logisticPlanning.utils.document.spec API}
 * for generating documents and reports with figures, tables, literature
 * references, and equations which is them
 * {@link org.logisticPlanning.utils.document.impl implemented} for, e.g.,
 * {@link org.logisticPlanning.utils.document.impl.latex LaTeX} and
 * {@link org.logisticPlanning.utils.document.impl.xhtml XHTML}.</li>
 * <li>The package {@link org.logisticPlanning.utils.graphics graphics}
 * provides utility classes for graphics and
 * {@link org.logisticPlanning.utils.graphics.chart charts}.</li>
 * <li>In package {@link org.logisticPlanning.utils.io} provides utilities
 * for {@link org.logisticPlanning.utils.io.FileUtils file interaction} and
 * {@link org.logisticPlanning.utils.io.charIO text-based IO}.</li>
 * <li>Package {@link org.logisticPlanning.utils.math} provides a lot of
 * mathematical core functionality, ranging from basic
 * {@link org.logisticPlanning.utils.math.functions functions} to
 * {@link org.logisticPlanning.utils.math.statistics statistics}.</li>
 * <li>The {@link org.logisticPlanning.utils.text text package} has
 * {@link org.logisticPlanning.utils.text.TextUtils utilities} for creating
 * and parsing text as well as for
 * {@link org.logisticPlanning.utils.text.CharTransformer transforming
 * text} (in particular special characters) in certain formats such as
 * {@link org.logisticPlanning.utils.text.transformations.XMLCharTransformer
 * XML} or
 * {@link org.logisticPlanning.utils.text.transformations.LaTeXCharTransformer
 * LaTeX}.</li>
 * </ol>
 * <p>
 * This overall structure of &quot;source code&quot; packages is mirrored
 * with an increasing number <a href="http://junit.org/">JUnit</a> test
 * cases in package {@link test.junit}. The test cases for a class
 * {@code XYZ} in package {@code org.bla.blubb} can be found in package
 * {@code test.junit.org.bla.blubb} and usually should be named something
 * like {@code XYZTest}.
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_COCO2012CCCO" /><a
 * href="http://www.lri.fr/~hansen/">Nikolaus Hansen</a>, <a
 * href="http://www.lri.fr/~auger/">Anne Auger</a>, <a
 * href="https://tao.lri.fr/tiki-index.php?page=people">Raymond Ros</a>,
 * and&nbsp;Dimo Brockhoff: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;COCO (COmparing
 * Continuous Optimisers),&rdquo;</span> (Website), October&nbsp;17, 2012,
 * Orsay, France: Universit&#233; Paris Sud, Institut National de Recherche
 * en Informatique et en Automatique (INRIA) Futurs, &#201;quipe TAO.
 * <div>link: [<a
 * href="http://coco.gforge.inria.fr/doku.php?id=start">1</a>]</div></div></li>
 * <li><div><span id="cite_HAFR2012RPBBOBES" /><a
 * href="http://www.lri.fr/~hansen/">Nikolaus Hansen</a>, <a
 * href="http://www.lri.fr/~auger/">Anne Auger</a>, <a
 * href="http://www.researchgate.net/profile/Steffen_Finck/">Steffen
 * Finck</a>, and&nbsp;<a
 * href="https://tao.lri.fr/tiki-index.php?page=people">Raymond Ros</a>:
 * <span style="font-weight:bold">&ldquo;Real-Parameter Black-Box
 * Optimization Benchmarking: Experimental Setup,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * March&nbsp;24, 2012; published by Orsay, France: Universit&#233; Paris
 * Sud, Institut National de Recherche en Informatique et en Automatique
 * (INRIA) Futurs, &#201;quipe TAO. <div>link: [<a href=
 * "http://coco.lri.fr/BBOB-downloads/download11.05/bbobdocexperiment.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_AH2005PEOAALSEA" /><a
 * href="http://www.lri.fr/~auger/">Anne Auger</a> and&nbsp;<a
 * href="http://www.lri.fr/~hansen/">Nikolaus Hansen</a>: <span
 * style="font-weight:bold">&ldquo;Performance Evaluation of an Advanced
 * Local Search Evolutionary Algorithm,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the IEEE
 * Congress on Evolutionary Computation (CEC'05)</span>,
 * September&nbsp;2&ndash;5, 2005, Edinburgh, Scotland, UK, pages
 * 1777&ndash;1784, <a
 * href="http://www.macs.hw.ac.uk/staff-directory/david-wolfe-corne.htm"
 * >David Wolfe Corne</a>, <a
 * href="http://cs.adelaide.edu.au/~zbyszek/">Zbigniew Michalewicz</a>, <a
 * href="http://sc.snu.ac.kr/~rim/index.html">Robert Ian McKay</a>, <a
 * href="http://www.cs.vu.nl/~gusz/">&#193;goston E. Eiben</a> aka.
 * Gusz/Guszti, <a
 * href="http://www.natural-selection.com/people_dfogel.html">David B.
 * Fogel</a>, Carlos M. Fonseca, <a
 * href="https://www.ads.tuwien.ac.at/raidl/">G&#252;nther R. Raidl</a>, <a
 * href="http://vlab.ee.nus.edu.sg/~kctan/">Kay Chen Tan</a>, and&nbsp;Ali
 * M. S. Zalzala, editors, Piscataway, NJ, USA: IEEE Computer Society.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780393635">0-7803-9363-5</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780393639">978-0-7803
 * -9363-9</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CEC.2005.1554903"
 * >10.1109/CEC.2005.1554903</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/62773008">62773008</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=-QIVAAAACAAJ">-QIVAAAACAAJ</a>;
 * INSPEC Accession Number:&nbsp;8715465. <div>link: [<a
 * href="http://www.lri.fr/~hansen/cec2005localcmaes.pdf">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.71.6248">
 * 10.1 .1.71.6248</a></div></div></li>
 * <li><div><span id="cite_TH2004UAIAEEFSAFSAMS" /><a
 * href="https://cs.uwaterloo.ca/~dtompkin/">Dave Andrew Douglas
 * Tompkins</a> and&nbsp;<a href="http://www.cs.ubc.ca/~hoos/">Holger H.
 * Hoos</a>: <span style="font-weight:bold">&ldquo;UBCSAT: An
 * Implementation and Experimentation Environment for SLS Algorithms for
 * SAT and MAX-SAT,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Revised Selected Papers
 * from the Seventh International Conference on Theory and Applications of
 * Satisfiability Testing (SAT'04)</span>, May&nbsp;10&ndash;13, 2004,
 * Vancouver, BC, Canada, pages 306&ndash;320, <a
 * href="http://www.cs.ubc.ca/~hoos/">Holger H. Hoos</a> and&nbsp;<a
 * href="http://www.cs.sfu.ca/~mitchell/">David G. Mitchell</a>, editors,
 * volume 3542 of Lecture Notes in Computer Science (LNCS), Berlin,
 * Germany: Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540278290"
 * >978-3-540-27829-0</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/11527695_24">10.1007/11527695_24</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a>. <div>link:
 * [<a
 * href="http://ubcsat.dtompkins.com/downloads/sat04proc-ubcsat.pdf">1</
 * a>]</div></div></li>
 * <li><div><span id="cite_UBCSAT" /><a
 * href="https://cs.uwaterloo.ca/~dtompkin/">Dave Andrew Douglas
 * Tompkins</a> and&nbsp;<a href="http://www.cs.ubc.ca/~hoos/">Holger H.
 * Hoos</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;UBCSAT
 * ,&rdquo;</span> (Software), 2013, Vancouver, BC, Canada: University of
 * British Columbia. <div>link: [<a
 * href="http://ubcsat.dtompkins.com/">1</a>]</div></div></li>
 * <li><div><span id="cite_T2010DLSFSDIAA" /><a
 * href="https://cs.uwaterloo.ca/~dtompkin/">Dave Andrew Douglas
 * Tompkins</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Dynamic Local
 * Search for SAT: Design, Insights and Analysis,&rdquo;</span> PhD Thesis,
 * October&nbsp;2010, Vancouver, BC, Canada: University Of British
 * Columbia, Faculty of Graduate Studies, Computer Science. <div>link: [<a
 * href
 * ="http://www.cs.uwaterloo.ca/~dtompkin/papers/dadt-phd.pdf">1</a>]</div
 * ></div></li>
 * <li><div><span id="cite_WNT2010AOABAOAB" /><a
 * href="http://www.it-weise.de/">Thomas Weise</a> <span
 * style="color:gray">[&#27748;&#21355;&#24605;</span>], <a
 * href="mailto:newly@mail.ustc.edu.cn">Li Niu</a> <span
 * style="color:gray">[&#29275;&#21147;</span>], and&nbsp;<a
 * href="http://staff.ustc.edu.cn/~ketang/">Ke Tang</a> <span
 * style="color:gray">[&#21776;&#29634;</span>]: <span
 * style="font-weight:bold">&ldquo;AOAB &#8210; Automated Optimization
 * Algorithm Benchmarking,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Black Box Optimization
 * Benchmarking (BBOB'10); Companion Publication of the Genetic and
 * Evolutionary Computation Conference (GECCO'10 Companion)</span>,
 * July&nbsp;7, 2010, Portland, OR, USA: Portland Marriott Downtown
 * Waterfront Hotel, pages 1479&ndash;1486, New York, NY, USA: ACM Press.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781450300735">978-1-4503
 * -0073-5</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1145/1830761.1830763"
 * >10.1145/1830761.1830763</a>; SCI/WOS:&nbsp;WOS:000322071400001;
 * EI:&nbsp;20103513190754; further information: [<a
 * href="http://www.sigevo.org/gecco-2010/workshops.html#bbob">1</a>].
 * <div>links: [<a
 * href="http://www.it-weise.de/documents/files/WNT2010AOABAOAB.pdf">1</a>]
 * and&nbsp;[<a
 * href="http://home.ustc.edu.cn/~newly/newly.files/Academy/paper.pdf"
 * >2</a>]</div></div></li>
 * </ol>
 */
package org.logisticPlanning.tsp.evaluation;

