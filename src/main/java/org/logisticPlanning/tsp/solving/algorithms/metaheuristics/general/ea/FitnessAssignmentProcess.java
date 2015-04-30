package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.TSPModule;

/**
 * <p>
 * This is the base class for all fitness assignment processes.
 * Traditionally, in EAs,the fitness is the same as the objective function
 * value. The
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction
 * objective function} which returns the total round-trip length of a tour
 * represented as node permutation. This value is subject to minimization.
 * However, we also may consider other metrics for optimization: For
 * instance, we could try to punish very similar tours. In an EA, we hope
 * to have a diverse population&nbsp;[<a href="#cite_PBJ2007OTIOPPOGD"
 * style="font-weight:bold">1</a>, <a href="#cite_OGC1991TSNATPOD"
 * style="font-weight:bold">2</a>, <a href="#cite_R1995GA"
 * style="font-weight:bold">3</a>, <a href="#cite_R1996GA"
 * style="font-weight:bold">4</a>, <a href="#cite_BBM1993AOOGAP1F"
 * style="font-weight:bold">5</a>, <a href="#cite_BT2002EA"
 * style="font-weight:bold">6</a>, <a href="#cite_BT2002EAMIDEA"
 * style="font-weight:bold">7</a>, <a href="#cite_LTDZ2001EA"
 * style="font-weight:bold">8</a>, <a href="#cite_M2004MOEA"
 * style="font-weight:bold">9</a>, <a href="#cite_SSSZ2004EAIM"
 * style="font-weight:bold">10</a>, <a href="#cite_SD2006O"
 * style="font-weight:bold">11</a>, <a href="#cite_DJWP2001RBAPDUMOM"
 * style="font-weight:bold">12</a>, <a href="#cite_G2004AAODIGP"
 * style="font-weight:bold">13</a>, <a href="#cite_GEBK2004PDACGIGP"
 * style="font-weight:bold">14</a>, <a href="#cite_BGKK2003IIDIGPBAAOTEOF"
 * style="font-weight:bold">15</a>, <a href="#cite_BGK2004DIGPAAOMACF"
 * style="font-weight:bold">16</a>, <a href="#cite_BB2002LGPECD"
 * style="font-weight:bold">17</a>]. Then, we may introduce a
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.FitnessAssignmentProcess
 * Fitness Assignment Process} that adds some value to the tour length
 * depending on how similar a given tour is to other tours in the
 * population, for example. If we do not want to do this and use the
 * objective value directly as fitness value, we apply the
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.fitness.FitnessIsObjectiveValue
 * FitnessIsObjectiveValue} process.
 * </p>
 * <p>
 * Starting at version 0.9.8 of TSP Suite, we provide run-depending
 * initialization and finalization support for algorithms and algorithm
 * modules. The new class
 * {@link org.logisticPlanning.tsp.solving.TSPModule} is introduced as
 * base-class for all algorithm modules (such as
 * {@link org.logisticPlanning.tsp.solving.operators.Operator search
 * operators} or {@link org.logisticPlanning.tsp.solving.gpm.GPM
 * genotype-phenotype mappings}) and the
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithm TSP algorithms}
 * themselves. This new class provides two methods,
 * {@link org.logisticPlanning.tsp.solving.TSPModule#beginRun(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction)}
 * and
 * {@link org.logisticPlanning.tsp.solving.TSPModule#endRun(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction)}
 * which must be called before and after a run, respectively. Each
 * algorithm module must then invoke them recursively for all of its
 * sub-components. The new method
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithm#call(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction)}
 * now acts as a wrapper for
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithm#solve(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction)}
 * and invokes them. This allows for a more targeted allocation and
 * de-allocation of data structures for each run.
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_PBJ2007OTIOPPOGD" /><a
 * href="http://www.aifb.kit.edu/web/Ingo_Paenke/en">Ingo Paenke</a>, <a
 * href="http://www.wbs.ac.uk/about/person/juergen-branke/">J&#252;rgen
 * Branke</a>, and&nbsp;Yaochu Jin <span
 * style="color:gray">[&#37329;&#32768;&#21021;</span>]: <span
 * style="font-weight:bold">&ldquo;On the Influence of Phenotype Plasticity
 * on Genotype Diversity,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">The First IEEE Symposium
 * on Foundations of Computational Intelligence (FOCI'07)</span>,
 * April&nbsp;1&ndash;5, 2007, Honolulu, HI, USA: Hilton Hawaiian Village
 * Hotel (Beach Resort &amp; Spa), pages 33&ndash;40, Jerry M. Mendel,
 * Takashi Omari, and&nbsp;<a href="http://www.cs.bham.ac.uk/~xin/">Xin
 * Yao</a> <span style="color:gray">[&#23002;&#26032;</span>], editors,
 * Piscataway, NJ, USA: IEEE Computer Society. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1424407036">1424407036</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781424407033">9781424407033</a>;
 * doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/FOCI.2007.372144">10.1109/FOCI.2007
 * .372144</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=-8kaOAAACAAJ">-8kaOAAACAAJ</a>.
 * <div>links: [<a href="http://www.soft-computing.de/S001P005.pdf">1</a>]
 * and&nbsp;[<a
 * href="http://www.honda-ri.org/intern/Publications/PN%2052-06">2</
 * a>]</div></div></li>
 * <li><div><span id="cite_OGC1991TSNATPOD" />Christopher K. Oei, <a
 * href="http://www.davidegoldberg.com/de-goldberg.html">David Edward
 * Goldberg</a>, and&nbsp;Shau-Jin Chang: <span
 * style="font-weight:bold">&ldquo;Tournament Selection, Niching, and the
 * Preservation of Diversity,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;91011, December&nbsp;1991; published by Urbana-Champaign,
 * IL, USA: University of Illinois at Urbana-Champaign, Department of
 * Computer Science, Department of General Engineering, Illinois Genetic
 * Algorithms Laboratory (IlliGAL). <div>link: [<a
 * href="http://www.illigal.uiuc.edu/pub/papers/IlliGALs/91011.ps.Z"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_R1995GA" />Simon Ronald: <span
 * style="font-weight:bold">&ldquo;Preventing Diversity Loss in a Routing
 * Genetic Algorithm with Hash Tagging,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Complexity
 * International</span> 2(2), April&nbsp;1995; published by Victoria,
 * Australia: Monash University, Faculty of Information Technology.
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/13200682">1320-0682</a>. <div>link:
 * [<a href="http://www.complexity.org.au/ci/vol02/sr_hash/">1</a>]</div></
 * div></li>
 * <li><div><span id="cite_R1996GA" />Simon Ronald: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Genetic Algorithms
 * and Permutation-Encoded Problems. Diversity Preservation and a Study of
 * Multimodality,&rdquo;</span> PhD Thesis, 1996, Mawson Lakes, SA,
 * Australia: University of South Australia (UniSA), Department of Computer
 * and Information Science</div></li>
 * <li><div><span id="cite_BBM1993AOOGAP1F" />David Beasley, <a
 * href="http://www.cems.uwe.ac.uk/~lbull/">David R. Bull</a>,
 * and&nbsp;Ralph R. Martin: <span style="font-weight:bold">&ldquo;An
 * Overview of Genetic Algorithms: Part 1. Fundamentals,&rdquo;</span> in
 * <span style="font-style:italic;font-family:cursive;">University
 * Computing</span> 15(2):58&ndash;69, 1993; published by Cambridge, MA,
 * USA: Inter-University Committee on Computing. <div>link: [<a
 * href="http://ralph.cs.cf.ac.uk/papers/GAs/ga_overview1.pdf">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.61.3619">10.1
 * .1.61 .3619</a> and&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.44.3757"
 * >10.1.1.44.3757</a></div></div></li>
 * <li><div><span id="cite_BT2002EA" />Peter A. N. Bosman and&nbsp;Dirk
 * Thierens: <span style="font-weight:bold">&ldquo;Multi-Objective
 * Optimization with Diversity Preserving Mixture-based Iterated Density
 * Estimation Evolutionary Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">International Journal
 * Approximate Reasoning</span> 31(2):259&ndash;289, November&nbsp;2002;
 * published by Essex, UK: Elsevier Science Publishers B.V.. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1016/S0888-613X(02)00090-7"
 * >10.1016/S0888-613X(02)00090-7</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/0888613X">0888-613X</a></div></li>
 * <li><div><span id="cite_BT2002EAMIDEA" />Peter A. N. Bosman
 * and&nbsp;Dirk Thierens: <span style="font-weight:bold">&ldquo;A Thorough
 * Documentation of Obtained Results on Real-Valued Continuous And
 * Combinatorial Multi-Objective Optimization Problems Using Diversity
 * Preserving Mixture-Based Iterated Density Estimation Evolutionary
 * Algorithms,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;UU-CS-2002-052, December&nbsp;2002; published by Utrecht,
 * The Netherlands: Utrecht University, Institute of Information and
 * Computing Sciences. <div>link: [<a
 * href="http://www.cs.uu.nl/research/techreps/repo/CS-2002/2002-052.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_LTDZ2001EA" /><a
 * href="http://www.ifor.math.ethz.ch/staff/laumanns">Marco Laumanns</a>,
 * <a
 * href="http://www.lamsade.dauphine.fr/mcda/biblio/Author/THIELE-L.html"
 * >Lothar Thiele</a>, <a
 * href="http://www.iitk.ac.in/kangal/deb.shtml">Kalyanmoy Deb</a>,
 * and&nbsp;Eckart Zitzler: <span style="font-weight:bold">&ldquo;On the
 * Convergence and Diversity-Preservation Properties of Multi-Objective
 * Evolutionary Algorithms,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;108, June&nbsp;13, 2001; published by Kanpur, Uttar Pradesh,
 * India: Kanpur Genetic Algorithms Laboratory (KanGAL), Department of
 * Mechanical Engineering, Indian Institute of Technology Kanpur (IIT)
 * and&nbsp;Z&#252;rich, Switzerland: Eidgen&#246;ssische Technische
 * Hochschule (ETH) Z&#252;rich, Department of Electrical Engineering,
 * Computer Engineering and Networks Laboratory (TIK). <div>links: [<a
 * href="http://e-collection.ethbib.ethz.ch/view/eth:24717">1</a>]
 * and&nbsp;[<a
 * href="http://citeseer.ist.psu.edu/laumanns01convergence.html">2</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.28.6480">10.1
 * .1.28 .6480</a></div></div></li>
 * <li><div><span id="cite_M2004MOEA" />Sanaz Mostaghim: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Multi-objective
 * Evolutionary Algorithms: Data structures, Convergence and,
 * Diversity,&rdquo;</span> PhD Thesis, February&nbsp;2005, Paderborn,
 * Germany: Universit&#228;t Paderborn, Fakult&#228;t f&#252;r
 * Elektrotechnik, Informatik und Mathematik. Aachen, North
 * Rhine-Westphalia, Germany: Shaker Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3832236619">3-8322-3661-9</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783832236618">978-3-8322
 * -3661-8</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/62900366">62900366</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=HoVLAAAACAAJ">HoVLAAAACAAJ</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=pJpeOQAACAAJ">pJpeOQAACAAJ
 * </a></div></li>
 * <li><div><span id="cite_SSSZ2004EAIM" />Christian Spieth, Felix
 * Streichert, Nora Speer, and&nbsp;Andreas Zell: <span
 * style="font-weight:bold">&ldquo;Utilizing an Island Model for EA to
 * Preserve Solution Diversity for Inferring Gene Regulatory
 * Networks,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the IEEE
 * Congress on Evolutionary Computation (CEC'04)</span>,
 * June&nbsp;20&ndash;23, 2004, Portland, OR, USA, pages 146&ndash;151, Los
 * Alamitos, CA, USA: IEEE Computer Society Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780385152">0-7803-8515-2</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780385153">978-0-7803
 * -8515-3</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CEC.2004.1330850">10.1109/CEC.2004
 * .1330850</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=I8_5AAAACAAJ">I8_5AAAACAAJ</a>.
 * <div>link: [<a href=
 * "http://www-ra.informatik.uni-tuebingen.de/software/JCell/publications.html"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_SD2006O" />Gulshan Singh and&nbsp;<a
 * href="http://www.iitk.ac.in/kangal/deb.shtml">Kalyanmoy Deb</a>: <span
 * style="font-weight:bold">&ldquo;Comparison of Multi-Modal Optimization
 * Algorithms based on Evolutionary Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the 8th
 * Annual Conference on Genetic and Evolutionary Computation
 * (GECCO'06)</span>, July&nbsp;8&ndash;12, 2006, Seattle, WA, USA:
 * Renaissance Seattle Hotel, pages 1305&ndash;1312, Maarten Keijzer
 * and&nbsp;Mike Cattolico, editors, New York, NY, USA: ACM Press.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1595931864">1-59593-186-4</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781595931863">978-1-59593
 * -186-3</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1145/1143997.1144200"
 * >10.1145/1143997.1144200</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/71335687">71335687</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=6y1pAAAACAAJ">6y1pAAAACAAJ</a>.
 * <div>link: [<a href=
 * "https://ceprofs.civil.tamu.edu/ezechman/CVEN689/References/singh_deb_2006.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_DJWP2001RBAPDUMOM" />Edwin D. de Jong, Richard
 * A. Watson, and&nbsp;<a
 * href="http://pages.cs.brandeis.edu/~pollack/">Jordan B. Pollack</a>:
 * <span style="font-weight:bold">&ldquo;Reducing Bloat and Promoting
 * Diversity using Multi-Objective Methods,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the
 * Genetic and Evolutionary Computation Conference (GECCO'01)</span>,
 * July&nbsp;7&ndash;11, 2001, San Francisco, CA, USA: Holiday Inn Golden
 * Gateway Hotel, pages 11&ndash;18, Lee Spector, <a
 * href="http://www.egr.msu.edu/~goodman/">Erik D. Goodman</a>, Annie S.
 * Wu, <a href="http://www0.cs.ucl.ac.uk/staff/W.Langdon/">William Benjamin
 * Langdon</a>, Hans-Michael Voigt, Mitsuo Gen, Sandip Sen, <a
 * href="https://en.wikipedia.org/wiki/Marco_Dorigo">Marco Dorigo</a>,
 * Shahram Pezeshk, Max H. Garzon, and&nbsp;<a
 * href="http://www.asap.cs.nott.ac.uk/external/atr/people/ekb.shtml"
 * >Edmund K. Burke</a>, editors, San Francisco, CA, USA: Morgan Kaufmann
 * Publishers Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1558607749">1-55860-774-9</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781558607743">978-155860-774-3</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=81QKAAAACAAJ">81QKAAAACAAJ</a>.
 * <div>links: [<a href="http://citeseer.ist.psu.edu/440305.html">1</a>],
 * [<a href
 * ="http://www.cs.bham.ac.uk/~wbl/biblio/gp-html/jong_2001_gecco.html"
 * >2</a>], and&nbsp;[<a
 * href="http://www.demo.cs.brandeis.edu/papers/rbpd_gecco01.ps.gz">3</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.28.4549">10.1
 * .1.28 .4549</a></div></div></li>
 * <li><div><span id="cite_G2004AAODIGP" /><a
 * href="http://www.gustafsonresearch.com/publications.html">Steven Matt
 * Gustafson</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;An Analysis of
 * Diversity in Genetic Programming,&rdquo;</span> PhD Thesis,
 * February&nbsp;2004, Nottingham, UK: University of Nottingham, School of
 * Computer Science &amp; Information Technology. <div>links: [<a
 * href="http://www.gustafsonresearch.com/thesis_html/index.html">1</a>]
 * and&nbsp;[<a
 * href="http://citeseer.ist.psu.edu/gustafson04analysis.html">2</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.2.9746">10.1
 * .1.2 .9746</a></div></div></li>
 * <li><div><span id="cite_GEBK2004PDACGIGP" /><a
 * href="http://www.gustafsonresearch.com/publications.html">Steven Matt
 * Gustafson</a>, <a
 * href="http://keg.cs.aston.ac.uk/stfDtls/stfDtls.php?id=22">Anik&#243;
 * Ek&#225;rt</a>, <a
 * href="http://www.asap.cs.nott.ac.uk/external/atr/people/ekb.shtml"
 * >Edmund K. Burke</a>, and&nbsp;<a href=
 * "https://www.nottingham.ac.uk/computerscience/people/graham.kendall"
 * >Graham Kendall</a>: <span style="font-weight:bold">&ldquo;Problem
 * Difficulty and Code Growth in Genetic Programming,&rdquo;</span> in
 * <span style="font-style:italic;font-family:cursive;">Genetic Programming
 * and Evolvable Machines</span> 5(3):271&ndash;290, September&nbsp;2004;
 * published by Dordrecht, Netherlands: Springer Netherlands
 * and&nbsp;Norwell, MA, USA: Kluwer Academic Publishers. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1023/B:GENP.0000030194.98244.e3"
 * >10.1023/B:GENP.0000030194.98244.e3</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/13892576">1389-2576</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15737632">1573-7632</a>. <div>links:
 * [<a href=
 * "http://www.gustafsonresearch.com/research/publications/gustafson-gpem2004.pdf"
 * >1</a>] and&nbsp;[<a
 * href="http://citeseer.ist.psu.edu/730858.html">2</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.59.6659">10.1
 * .1.59 .6659</a></div></div></li>
 * <li><div><span id="cite_BGKK2003IIDIGPBAAOTEOF" /><a
 * href="http://www.asap.cs.nott.ac.uk/external/atr/people/ekb.shtml"
 * >Edmund K. Burke</a>, <a
 * href="http://www.gustafsonresearch.com/publications.html">Steven Matt
 * Gustafson</a>, <a href=
 * "https://www.nottingham.ac.uk/computerscience/people/graham.kendall"
 * >Graham Kendall</a>, and&nbsp;<a
 * href="http://www.cs.nott.ac.uk/~nxk/">Natalio Krasnogor</a>: <span
 * style="font-weight:bold">&ldquo;Is Increasing Diversity in Genetic
 * Programming Beneficial? An Analysis of the Effects on
 * Fitness,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the IEEE
 * Congress on Evolutionary Computation (CEC'03)</span>,
 * December&nbsp;8&ndash;13, 2003, Canberra, Australia, pages
 * 1398&ndash;1405, <a
 * href="http://seit.unsw.adfa.edu.au/staff/sites/rsarker/">Ruhul Amin
 * Sarker</a>, <a
 * href="http://ai.cs.wayne.edu/ai/member%20webs/Reynolds/">Robert G.
 * Reynolds</a>, <a
 * href="http://seit.unsw.adfa.edu.au/staff/sites/abbass/">Hussein Aly
 * Abbass Amein</a>, <a href="http://vlab.ee.nus.edu.sg/~kctan/">Kay Chen
 * Tan</a>, <a href="http://sc.snu.ac.kr/~rim/index.html">Robert Ian
 * McKay</a>, Daryl Leslie Essam, and&nbsp;Tom Gedeon, editors, Piscataway,
 * NJ, USA: IEEE Computer Society. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780378040">0-7803-7804-0</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780378049">978-0-7803
 * -7804-9</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CEC.2003.1299834">10.1109/CEC.2003
 * .1299834</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=CyBpAAAACAAJ">CyBpAAAACAAJ</a>.
 * <div>links: [<a href=
 * "http://www.gustafsonresearch.com/research/publications/cec-2003.pdf"
 * >1</a>] and&nbsp;[<a
 * href="http://citeseer.ist.psu.edu/700607.html">2</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.1.1929">10.1
 * .1.1 .1929</a></div></div></li>
 * <li><div><span id="cite_BGK2004DIGPAAOMACF" /><a
 * href="http://www.asap.cs.nott.ac.uk/external/atr/people/ekb.shtml"
 * >Edmund K. Burke</a>, <a
 * href="http://www.gustafsonresearch.com/publications.html">Steven Matt
 * Gustafson</a>, and&nbsp;<a href=
 * "https://www.nottingham.ac.uk/computerscience/people/graham.kendall"
 * >Graham Kendall</a>: <span style="font-weight:bold">&ldquo;Diversity in
 * Genetic Programming: An Analysis of Measures and Correlation with
 * Fitness,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">IEEE Transactions on
 * Evolutionary Computation (IEEE-EC)</span> 8(1):47&ndash;62,
 * February&nbsp;2004; edited by <a
 * href="http://www.cs.bham.ac.uk/~xin/">Xin Yao</a> <span
 * style="color:gray">[&#23002;&#26032;</span>]; published by Washington,
 * DC, USA: IEEE Computer Society. LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/97648327">97648327</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/TEVC.2003.819263"
 * >10.1109/TEVC.2003.819263</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/1089778X">1089-778X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/19410026">1941-0026</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=ITEVF5">
 * ITEVF5</a>; further information: [<a
 * href="http://www.ieee-cis.org/pubs/tec/">1</a>]. <div>links: [<a href=
 * "http://www.gustafsonresearch.com/research/publications/gustafson-ieee2004.pdf"
 * >1</a>] and&nbsp;[<a
 * href="http://citeseer.ist.psu.edu/burke04diversity.html">2</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.59.6663">10.1
 * .1.59 .6663</a></div></div></li>
 * <li><div><span id="cite_BB2002LGPECD" />Markus F. Brameier and&nbsp;<a
 * href="http://www.cs.mun.ca/~banzhaf/">Wolfgang Banzhaf</a>: <span
 * style="font-weight:bold">&ldquo;Explicit Control of Diversity and
 * Effective Variation Distance in Linear Genetic
 * Programming,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the 5th
 * European Conference on Genetic Programming (EuroGP'02)</span>,
 * April&nbsp;3&ndash;5, 2002, Kinsale, Ireland, pages 37&ndash;49, <a
 * href="http://www.sci.uidaho.edu/biosci/faculty/Foster.htm">James A.
 * Foster</a>, <a href="http://evelyne.lutton.free.fr/">Evelyne Lutton</a>,
 * <a href="http://www.elec.york.ac.uk/staff/jfm7.html">Julian Francis
 * Miller</a>, <a href="http://www.csis.ul.ie/staff/ConorRyan/">Conor
 * Ryan</a>, and&nbsp;Andrea G. B. Tettamanzi, editors, volume 2278/2002 of
 * Lecture Notes in Computer Science (LNCS), Berlin, Germany:
 * Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540433783">3-540-43378-3</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540433781">978-3-540-
 * 43378-1</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/3-540-45984-7_4"
 * >10.1007/3-540-45984-7_4</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/314133163">314133163</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=RpNQAAAAMAAJ">RpNQAAAAMAAJ</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a>. <div>links:
 * [<a href="http://citeseer.ist.psu.edu/552561.html">1</a>], [<a
 * href="http://www.cs.mun.ca/~banzhaf/papers/eurogp02_dist.pdf">2</a>],
 * and&nbsp;[<a href=
 * "http://eldorado.uni-dortmund.de:8080/bitstream/2003/5419/1/123.pdf"
 * >3</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.11.5531"
 * >10.1.1.11.5531</a></div></div></li>
 * </ol>
 */
public class FitnessAssignmentProcess extends TSPModule {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * the fitness assignment algorithm constructor
   * 
   * @param name
   *          the algorithm's name
   */
  protected FitnessAssignmentProcess(final String name) {
    super(name);
  }

  /**
   * assign the fitness
   * 
   * @param pop
   *          the population holding the individuals to select from
   * @param f
   *          the objective function
   */
  public void assignFitness(final Individual<?>[] pop,
      final ObjectiveFunction f) {
    throw new UnsupportedOperationException();
  }
}
