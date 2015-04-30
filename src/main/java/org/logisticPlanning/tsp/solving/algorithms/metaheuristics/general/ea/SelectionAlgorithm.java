package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.TSPModule;

/**
 * <p>
 * This is the base class for all selection algorithms. In
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.EA
 * Evolutionary Algorithms} (EAs), selection algorithms are used to choose
 * which individuals in the population can enter the mating pool, i.e., can
 * become parents of the next generation&nbsp;[<a href="#cite_WGOEB"
 * style="font-weight:bold">1</a>, <a href="#cite_TB1996EA"
 * style="font-weight:bold">2</a>, <a href="#cite_LP2002GP"
 * style="font-weight:bold">3</a>, <a href="#cite_BT1995EA"
 * style="font-weight:bold">4</a>, <a href="#cite_BT1996EA"
 * style="font-weight:bold">5</a>, <a href="#cite_M1998GA"
 * style="font-weight:bold">6</a>].
 * </p>
 * <p>
 * The choice of a selection algorithm has major impact on the performance
 * of an EA. Therefore, much research has been contributed to different
 * selection schemes, such as
 * </p>
 * <ol>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.selection.TruncationSelection
 * Truncation Selection}&nbsp;[<a href="#cite_B1995GCMFTAMLS"
 * style="font-weight:bold">7</a>, <a href="#cite_LHE2008TSBPPDFCSIGA"
 * style="font-weight:bold">8</a>, <a href="#cite_TG1994CMOGASS"
 * style="font-weight:bold">9</a>, <a href="#cite_MG1996GASSATVEON"
 * style="font-weight:bold">10</a>, <a href="#cite_GD1990EAA"
 * style="font-weight:bold">11</a>, <a href="#cite_BT1995EA"
 * style="font-weight:bold">4</a>, <a href="#cite_BT1996EA"
 * style="font-weight:bold">5</a>, <a href="#cite_CDC1996AOSAAMCA"
 * style="font-weight:bold">12</a>, <a href="#cite_RPB1999GDIGASS"
 * style="font-weight:bold">13</a>]</li>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.selection.TournamentSelection
 * Tournament Selection}&nbsp;[<a href="#cite_B1995GCMFTAMLS"
 * style="font-weight:bold">7</a>, <a href="#cite_AM2004HTSGAFTVRPWTW"
 * style="font-weight:bold">14</a>, <a href="#cite_BSG2002TSIXCS"
 * style="font-weight:bold">15</a>, <a href="#cite_HH2008EOPTAPRS"
 * style="font-weight:bold">16</a>, <a href="#cite_LSKPJ2008SPAORWTSIGA"
 * style="font-weight:bold">17</a>, <a href="#cite_TG1994CMOGASS"
 * style="font-weight:bold">9</a>, <a href="#cite_MG1995GATS"
 * style="font-weight:bold">18</a>, <a href="#cite_MG1995GATSATEON"
 * style="font-weight:bold">19</a>, <a href="#cite_MG1996GASSATVEON"
 * style="font-weight:bold">10</a>, <a href="#cite_ECC2003UTESSAMATSFGO"
 * style="font-weight:bold">20</a>, <a href="#cite_G1990ANOBTSFGAAPOSA"
 * style="font-weight:bold">21</a>, <a href="#cite_OGC1991TSNATPOD"
 * style="font-weight:bold">22</a>, <a href="#cite_BT1995EA"
 * style="font-weight:bold">4</a>, <a href="#cite_BT1996EA"
 * style="font-weight:bold">5</a>, <a href="#cite_GD1990EAA"
 * style="font-weight:bold">11</a>, <a href="#cite_SG2001TS"
 * style="font-weight:bold">23</a>, <a href="#cite_BT1995GATS"
 * style="font-weight:bold">24</a>, <a href="#cite_LHK2004TVFUS"
 * style="font-weight:bold">25</a>, <a href="#cite_LHK2004TVFUS2"
 * style="font-weight:bold">26</a>, <a href="#cite_H2002FUSTPGD"
 * style="font-weight:bold">27</a>, <a href="#cite_CDC1996AOSAAMCA"
 * style="font-weight:bold">12</a>, <a href="#cite_ZHZG2005RWSGA"
 * style="font-weight:bold">28</a>, <a href="#cite_X2009AAOSIGP"
 * style="font-weight:bold">29</a>]</li>
 * <li>Fitness Proportionate (Roulette Wheel) Selection&nbsp;[<a
 * href="#cite_B1987RBAIITSA" style="font-weight:bold">30</a>, <a
 * href="#cite_TG1994CMOGASS" style="font-weight:bold">9</a>, <a
 * href="#cite_MG1996GASSATVEON" style="font-weight:bold">10</a>, <a
 * href="#cite_W1989EA" style="font-weight:bold">31</a>, <a
 * href="#cite_GD1990EAA" style="font-weight:bold">11</a>, <a
 * href="#cite_BT1995EA" style="font-weight:bold">4</a>, <a
 * href="#cite_BT1996EA" style="font-weight:bold">5</a>, <a
 * href="#cite_CDC1996AOSAAMCA" style="font-weight:bold">12</a>, <a
 * href="#cite_ZHZG2005RWSGA" style="font-weight:bold">28</a>, <a
 * href="#cite_XZ1998RWSGA" style="font-weight:bold">32</a>]</li>
 * <li>Rank-based Selection&nbsp;[<a href="#cite_HH2008EOPTAPRS"
 * style="font-weight:bold">16</a>, <a href="#cite_MG1996GASSATVEON"
 * style="font-weight:bold">10</a>, <a href="#cite_W1989EA"
 * style="font-weight:bold">31</a>, <a href="#cite_GD1990EAA"
 * style="font-weight:bold">11</a>, <a href="#cite_BT1995EA"
 * style="font-weight:bold">4</a>, <a href="#cite_BT1996EA"
 * style="font-weight:bold">5</a>, <a href="#cite_CDC1996AOSAAMCA"
 * style="font-weight:bold">12</a>]</li>
 * <li>Local/Spatial Selection Schemes&nbsp;[<a href="#cite_CJ1991SIMPGA"
 * style="font-weight:bold">33</a>]</li>
 * <li>Fitness Uniform Selection (FUSS) and ~Deletion (FUDS)&nbsp;[<a
 * href="#cite_LHK2004TVFUS" style="font-weight:bold">25</a>, <a
 * href="#cite_LHK2004TVFUS2" style="font-weight:bold">26</a>, <a
 * href="#cite_H2002FUSTPGD" style="font-weight:bold">27</a>]</li>
 * <li>Adaptive Selection Schemes&nbsp;[<a href="#cite_B1985ASMFGA"
 * style="font-weight:bold">34</a>]</li>
 * </ol>
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
 * <li><div><span id="cite_WGOEB" /><a
 * href="http://www.it-weise.de/">Thomas Weise</a> <span
 * style="color:gray">[&#27748;&#21355;&#24605;</span>]: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Global
 * Optimization Algorithms &#8210; Theory and Application,&rdquo;</span>
 * 2009, Germany: it-weise.de (self-published). <div>link: [<a
 * href="http://www.it-weise.de/projects/book.pdf">1</a>]</div></div></li>
 * <li><div><span id="cite_TB1996EA" /><a
 * href="http://www.liacs.nl/~baeck/contact.htm">Thomas B&#228;ck</a>:
 * <span style="font-style:italic;font-family:cursive;">&ldquo;Evolutionary
 * Algorithms in Theory and Practice: Evolution Strategies, Evolutionary
 * Programming, Genetic Algorithms,&rdquo;</span> January&nbsp;1996, New
 * York, NY, USA: Oxford University Press, Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0195099710">0-19-509971-0</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780195099713">978-0-19-509971
 * -3</a>; LCCN:&nbsp;<a href="http://lccn.loc.gov/95013506">95013506</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/246886085">246886085</a> and&nbsp;<a
 * href="https://www.worldcat.org/oclc/32350061">32350061</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=EaN7kvl5coYC">EaN7kvl5coYC</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=6MOqAAAACAAJ">6MOqAAAACAAJ</a>;
 * PPN:&nbsp;<a href="http://gso.gbv.de/PPNSET?PPN=185320007">185320007</a>
 * and&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=27885365X">27885365X</a></div></li>
 * <li><div><span id="cite_LP2002GP" /><a
 * href="http://www0.cs.ucl.ac.uk/staff/W.Langdon/">William Benjamin
 * Langdon</a> and&nbsp;<a
 * href="http://cswww.essex.ac.uk/staff/poli/">Riccardo Poli</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Foundations of
 * Genetic Programming,&rdquo;</span> January&nbsp;2002, Berlin, Germany:
 * Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540424512">3-540-42451-2</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540424512">978-3-540-
 * 42451-2</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/2001049394">2001049394</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/491544616">491544616</a>, <a
 * href="https://www.worldcat.org/oclc/248023954">248023954</a>,
 * and&nbsp;<a href="https://www.worldcat.org/oclc/47717957">47717957</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=PZli0s95Jd0C">PZli0s95Jd0C</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=334151872">334151872</a>. <div>partly
 * available: [<a
 * href="http://www.cs.ucl.ac.uk/staff/W.Langdon/FOGP/">1</a>]</div></div></li>
 * <li><div><span id="cite_BT1995EA" />Tobias Blickle and&nbsp;<a
 * href="http://www.lamsade.dauphine.fr/mcda/biblio/Author/THIELE-L.html"
 * >Lothar Thiele</a>: <span style="font-weight:bold">&ldquo;A Comparison
 * of Selection Schemes Used in Genetic Algorithms,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;11, December&nbsp;1995; published by Z&#252;rich,
 * Switzerland: Eidgen&#246;ssische Technische Hochschule (ETH)
 * Z&#252;rich, Department of Electrical Engineering, Computer Engineering
 * and Networks Laboratory (TIK). <div>links: [<a href=
 * "http://www.handshake.de/user/blickle/publications/tik-report11_v2.ps"
 * >1</a>], [<a
 * href="ftp://ftp.tik.ee.ethz.ch/pub/publications/TIK-Report11.ps">2</a>],
 * and&nbsp;[<a
 * href="http://www4.hcmut.edu.vn/~hthoang/dktm/Selection.pdf">3</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.11.509">10.1
 * .1.11 .509</a></div></div></li>
 * <li><div><span id="cite_BT1996EA" />Tobias Blickle and&nbsp;<a
 * href="http://www.lamsade.dauphine.fr/mcda/biblio/Author/THIELE-L.html"
 * >Lothar Thiele</a>: <span style="font-weight:bold">&ldquo;A Comparison
 * of Selection Schemes used in Evolutionary Algorithms,&rdquo;</span> in
 * <span style="font-style:italic;font-family:cursive;">Evolutionary
 * Computation</span> 4(4):361&ndash;394, Winter&nbsp;1996; published by
 * Cambridge, MA, USA: MIT Press. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1162/evco.1996.4.4.361"
 * >10.1162/evco.1996.4.4.361</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/44169764">44169764</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/10636560">1063-6560</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15309304">1530-9304</a>. <div>links:
 * [<a href="http://citeseer.ist.psu.edu/blickle97comparison.html">1</a>]
 * and&nbsp;[<a
 * href="http://www.handshake.de/user/blickle/publications/ECfinal.ps"
 * >2</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.15.9584"
 * >10.1.1.15 .9584</a></div></div></li>
 * <li><div><span id="cite_M1998GA" /><a
 * href="http://web.cecs.pdx.edu/~mm/">Melanie Mitchell</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;An Introduction to
 * Genetic Algorithms,&rdquo;</span> February&nbsp;1998, Complex Adaptive
 * Systems, Bradford Books, Cambridge, MA, USA: MIT Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0262631857">0-262-63185-7</a>, <a
 * href="https://www.worldcat.org/isbn/0262133164">0-262-13316-4</a>, <a
 * href
 * ="https://www.worldcat.org/isbn/9780262631853">978-0-262-63185-3</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780262133166">978-0-262-
 * 13316-6</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/95024489">95024489</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/256769418">256769418</a> and&nbsp;<a
 * href="https://www.worldcat.org/oclc/44708663">44708663</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=0eznlz0TF-IC">0eznlz0TF-IC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/10894365">1089-4365</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=240449282">240449282</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=557262135">557262135</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=269703497">269703497</a>, and&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=083660852">083660852</a></div></li>
 * <li><div><span id="cite_B1995GCMFTAMLS" /><a
 * href="http://www.liacs.nl/~baeck/contact.htm">Thomas B&#228;ck</a>:
 * <span style="font-weight:bold">&ldquo;Generalized Convergence Models for
 * Tournament- and $(&#956;,&#955;)$-Selection,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the Sixth
 * International Conference on Genetic Algorithms (ICGA'95)</span>,
 * July&nbsp;15&ndash;19, 1995, Pittsburgh, PA, USA: University of
 * Pittsburgh, pages 2&ndash;8, Larry J. Eshelman, editor, San Francisco,
 * CA, USA: Morgan Kaufmann Publishers Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1558603700">1-55860-370-0</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781558603707">9781558603707</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=9xRRAAAAMAAJ">9xRRAAAAMAAJ</a>.
 * <div>link: [<a href="http://citeseer.ist.psu.edu/1463.html">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.56.5969">10.1
 * .1.56 .5969</a></div></div></li>
 * <li><div><span id="cite_LHE2008TSBPPDFCSIGA" />J&#246;rg L&#228;ssig,
 * Karl Heinz Hoffmann, and&nbsp;Mihaela En&#259;chescu: <span
 * style="font-weight:bold">&ldquo;Threshold Selecting: Best Possible
 * Probability Distribution for Crossover Selection in Genetic
 * Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the
 * Genetic and Evolutionary Computation Conference (GECCO'08)</span>,
 * July&nbsp;12&ndash;16, 2008, Atlanta, GA, USA: Renaissance Atlanta Hotel
 * Downtown, pages 2181&ndash;2185, Maarten Keijzer, <a
 * href="http://web.soccerlab.polymtl.ca/~antoniol/">Giuliano Antoniol</a>,
 * <a href="http://www.cs.colby.edu/~congdon/">Clare Bates Congdon</a>, <a
 * href="http://www.iitk.ac.in/kangal/deb.shtml">Kalyanmoy Deb</a>, <a
 * href="http://www.mpi-inf.mpg.de/~doerr/">Benjamin Doerr</a>, <a
 * href="http://www.lri.fr/~hansen/">Nikolaus Hansen</a>, <a
 * href="http://www.cceb.upenn.edu/faculty/index.php?id=172">John H.
 * Holmes</a>, Gregory S. Hornby, Daniel Howard, James Kennedy, <a
 * href="http://iianalytics.com/iia-faculty/sanjeev-kumar-2/">Sanjeev P.
 * Kumar</a>, <a href="http://w3.ualg.pt/~flobo/">Fernando G. Lobo</a>, <a
 * href="http://www.elec.york.ac.uk/staff/jfm7.html">Julian Francis
 * Miller</a>, <a href="http://www.epistasis.org/jason.html">Jason H.
 * Moore</a>, <a href="http://cs.adelaide.edu.au/~frank/">Frank
 * Neumann</a>, <a href="http://www.cs.umsl.edu/~pelikan/">Martin
 * Pelikan</a>, <a href="http://pages.cs.brandeis.edu/~pollack/">Jordan B.
 * Pollack</a>, <a href="http://www.kumarasastry.com/">Kumara Sastry</a>,
 * <a href="http://www.cs.utexas.edu/~kstanley/">Kenneth Owen Stanley</a>,
 * <a href="http://www-robotics.jpl.nasa.gov/people/Adrian_Stoica/">Adrian
 * Stoica</a>, <a href="http://www.lifl.fr/~talbi/">El-Ghazali Talbi</a>,
 * and&nbsp;<a href="http://en.wikipedia.org/wiki/Ingo_Wegener">Ingo
 * Wegener</a>, editors, New York, NY, USA: ACM Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1605581313">1-60558-131-3</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781605581316">978-1-60558
 * -131-6</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1145/1388969.1389044"
 * >10.1145/1388969.1389044</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/299786110">299786110</a>; further
 * information: [<a href="http://www.sigevo.org/gecco-2008/">1</a>]</div></li>
 * <li><div><span id="cite_TG1994CMOGASS" />Dirk Thierens and&nbsp;<a
 * href="http://www.davidegoldberg.com/de-goldberg.html">David Edward
 * Goldberg</a>: <span style="font-weight:bold">&ldquo;Convergence Models
 * of Genetic Algorithm Selection Schemes,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the Third
 * Conference on Parallel Problem Solving from Nature; International
 * Conference on Evolutionary Computation (PPSN III)</span>,
 * October&nbsp;9&ndash;14, 1994, Jerusalem, Israel, pages 119&ndash;129,
 * Yuval Davidor, <a
 * href="https://en.wikipedia.org/wiki/Hans-Paul_Schwefel">Hans-Paul
 * Schwefel</a>, and&nbsp;Reinhard M&#228;nner, editors, volume 866/1994 of
 * Lecture Notes in Computer Science (LNCS), Berlin, Germany:
 * Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540584846">3-540-58484-6</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540584841">978-3-540-
 * 58484-1</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/3-540-58484-6_256">10.1007/3-540-
 * 58484-6_256</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/31132760">31132760</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=n2lXlC5mv68C">n2lXlC5mv68C</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a>. <div>links:
 * [<a href=
 * "http://www.cs.uu.nl/groups/DSS/publications/convergence/convMdl.ps"
 * >1</a>] and&nbsp;[<a
 * href="http://www.csie.ntu.edu.tw/~b91069/PDF/convMdl.pdf"
 * >2</a>]</div></div></li>
 * <li><div><span id="cite_MG1996GASSATVEON" />Brad L. Miller and&nbsp;<a
 * href="http://www.davidegoldberg.com/de-goldberg.html">David Edward
 * Goldberg</a>: <span style="font-weight:bold">&ldquo;Genetic Algorithms,
 * Selection Schemes, and the Varying Effects of Noise,&rdquo;</span> in
 * <span style="font-style:italic;font-family:cursive;">Evolutionary
 * Computation</span> 4(2):113&ndash;131, Summer&nbsp;1996; edited by <a
 * href="http://www.lri.fr/~marc/">Marc Schoenauer</a>; published by
 * Cambridge, MA, USA: MIT Press. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1162/evco.1996.4.2.113"
 * >10.1162/evco.1996.4.2.113</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/44169764">44169764</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/10636560">1063-6560</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15309304">1530-9304</a>.
 * <div>CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a href
 * ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.31.3449"
 * >10.1.1.31 .3449</a> and&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.31.3449"
 * >10.1.1.31.3449</a></div></div></li>
 * <li><div><span id="cite_GD1990EAA" /><a
 * href="http://www.davidegoldberg.com/de-goldberg.html">David Edward
 * Goldberg</a> and&nbsp;<a
 * href="http://www.iitk.ac.in/kangal/deb.shtml">Kalyanmoy Deb</a>: <span
 * style="font-weight:bold">&ldquo;A Comparative Analysis of Selection
 * Schemes Used in Genetic Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the First
 * Workshop on Foundations of Genetic Algorithms (FOGA'90)</span>,
 * July&nbsp;15&ndash;18, 1990, Bloomington, IN, USA: Indiana University,
 * Bloomington Campus, pages 69&ndash;93, Bruce M. Spatz and&nbsp;Gregory
 * J. E. Rawlins, editors, San Francisco, CA, USA: Morgan Kaufmann
 * Publishers Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1558601708">1-55860-170-8</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781558601703">978-1-55860
 * -170-3</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/311490755">311490755</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=Df12yLrlUZYC">Df12yLrlUZYC</a>.
 * <div>link: [<a
 * href="http://www.cse.unr.edu/~sushil/class/gas/papers/Select.pdf"
 * >1</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.101.9494"
 * >10.1.1 .101.9494</a></div></div></li>
 * <li><div><span id="cite_CDC1996AOSAAMCA" />Uday Kumar Chakraborty, <a
 * href="http://www.iitk.ac.in/kangal/deb.shtml">Kalyanmoy Deb</a>,
 * and&nbsp;Mandira Chakraborty: <span
 * style="font-weight:bold">&ldquo;Analysis of Selection Algorithms: A
 * Markov Chain Approach,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Evolutionary
 * Computation</span> 4(2):133&ndash;167, Summer&nbsp;1996; published by
 * Cambridge, MA, USA: MIT Press. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1162/evco.1996.4.2.133"
 * >10.1162/evco.1996.4.2.133</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/44169764">44169764</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/10636560">1063-6560</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15309304">1530-9304</a></div></li>
 * <li><div><span id="cite_RPB1999GDIGASS" />Alex Rogers and&nbsp;Adam
 * Pr&#252;gel-Bennett: <span style="font-weight:bold">&ldquo;Genetic Drift
 * in Genetic Algorithm Selection Schemes,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">IEEE Transactions on
 * Evolutionary Computation (IEEE-EC)</span> 3(4):298&ndash;303,
 * November&nbsp;1999; published by Washington, DC, USA: IEEE Computer
 * Society. LCCN:&nbsp;<a href="http://lccn.loc.gov/97648327">97648327</a>;
 * doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/4235.797972">10.1109/4235.797972</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/1089778X">1089-778X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/19410026">1941-0026</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=ITEVF5"
 * >ITEVF5</a>; INSPEC Accession Number:&nbsp;6411652; further information:
 * [<a href="http://www.ieee-cis.org/pubs/tec/">1</a>]. <div>link: [<a
 * href="http://eprints.ecs.soton.ac.uk/688/1/IEEE.pdf">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.26.9956">10.1
 * .1.26 .9956</a></div></div></li>
 * <li><div><span id="cite_AM2004HTSGAFTVRPWTW" />Guilherme Bastos
 * Alvarenga and&nbsp;Geraldo Robson Mateus: <span
 * style="font-weight:bold">&ldquo;Hierarchical Tournament Selection
 * Genetic Algorithm for the Vehicle Routing Problem with Time
 * Windows,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Fourth International
 * Conference on Hybrid Intelligent Systems (HIS'04)</span>,
 * December&nbsp;5&ndash;8, 2004, Kitakyushu, Japan, pages 410&ndash;415,
 * Masumi Ishikawa, editor, Piscataway, NJ, USA: IEEE Computer Society.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0769522912">0-7695-2291-2</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780769522913">978-0-7695
 * -2291-3</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/ICHIS.2004.53">
 * 10.1109/ICHIS.2004.53</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/423969575">423969575</a>; INSPEC
 * Accession Number:&nbsp;8470883</div></li>
 * <li><div><span id="cite_BSG2002TSIXCS" />Martin V. Butz, <a
 * href="http://www.kumarasastry.com/">Kumara Sastry</a>, and&nbsp;<a
 * href="http://www.davidegoldberg.com/de-goldberg.html">David Edward
 * Goldberg</a>: <span style="font-weight:bold">&ldquo;Tournament Selection
 * in XCS,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;2002020, July&nbsp;2002; published by Urbana-Champaign, IL,
 * USA: University of Illinois at Urbana-Champaign, Department of Computer
 * Science, Department of General Engineering, Illinois Genetic Algorithms
 * Laboratory (IlliGAL). <div>links: [<a
 * href="http://citeseer.ist.psu.edu/534844.html">1</a>], [<a
 * href="http://www.illigal.uiuc.edu/pub/papers/IlliGALs/2002020.ps.Z"
 * >2</a>], and&nbsp;[<a
 * href="ftp://ftp-illigal.ge.uiuc.edu/pub/papers/IlliGALs/2002020.ps.Z"
 * >3</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.19.1850"
 * >10.1.1.19 .1850</a></div></div></li>
 * <li><div><span id="cite_HH2008EOPTAPRS" />Kassel Hingee and&nbsp;<a
 * href="http://www.hutter1.net/">Marcus Hutter</a>: <span
 * style="font-weight:bold">&ldquo;Equivalence of Probabilistic Tournament
 * and Polynomial Ranking Selection,&rdquo;</span> pages 564&ndash;571,
 * June&nbsp;1&ndash;6, 2008; edited by <a
 * href="http://cs.adelaide.edu.au/~zbyszek/">Zbigniew Michalewicz</a>, <a
 * href="http://ai.cs.wayne.edu/ai/member%20webs/Reynolds/">Robert G.
 * Reynolds</a>, Jacek M. Zurada, <a
 * href="http://www.ece.okstate.edu/faculty/yen/">Gary G. Yen</a>,
 * and&nbsp;Jun Wang; published by Piscataway, NJ, USA: IEEE Computer
 * Society and&nbsp;Berlin, Germany: Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1424418224">1-4244-1822-4</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781424418220">978-1-4244
 * -1822-0</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/2008927523">2008927523</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/244010459">244010459</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=W5HqHwAACAAJ">W5HqHwAACAAJ</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a>. <div>link:
 * [<a href="http://arxiv.org/abs/0803.2925v1">1</a>]</div></div></li>
 * <li><div><span id="cite_LSKPJ2008SPAORWTSIGA" />S. Lee, S. Soak, K. Kim,
 * H. Park, and&nbsp;M. Jeon: <span
 * style="font-weight:bold">&ldquo;Statistical Properties Analysis of Real
 * World Tournament Selection in Genetic Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Applied Intelligence
 * &#8210; The International Journal of Artificial Intelligence, Neural
 * Networks, and Complex Problem-Solving Technologies</span>
 * 28(2):195&ndash;205, April&nbsp;2008; published by Dordrecht,
 * Netherlands: Springer Netherlands. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/s10489-007-0062-2">10.1007/s10489
 * -007-0062-2</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/0924669X">0924-669X</a></div></li>
 * <li><div><span id="cite_MG1995GATS" />Brad L. Miller and&nbsp;<a
 * href="http://www.davidegoldberg.com/de-goldberg.html">David Edward
 * Goldberg</a>: <span style="font-weight:bold">&ldquo;Genetic Algorithms,
 * Tournament Selection, and the Effects of Noise,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;95006, July&nbsp;1995; published by Urbana-Champaign, IL,
 * USA: University of Illinois at Urbana-Champaign, Department of Computer
 * Science, Department of General Engineering, Illinois Genetic Algorithms
 * Laboratory (IlliGAL). <div>link: [<a
 * href="http://www.illigal.uiuc.edu/pub/papers/IlliGALs/95006.ps.Z"
 * >1</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.30.6625"
 * >10.1.1.30 .6625</a></div></div></li>
 * <li><div><span id="cite_MG1995GATSATEON" />Brad L. Miller and&nbsp;<a
 * href="http://www.davidegoldberg.com/de-goldberg.html">David Edward
 * Goldberg</a>: <span style="font-weight:bold">&ldquo;Genetic Algorithms,
 * Tournament Selection, and the Effects of Noise,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Complex Systems</span>
 * 9(3):193&ndash;212, 1995; published by Champaign, IL, USA: Complex
 * Systems Publications, Inc.. ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/08912513">0891-2513</a>. <div>link:
 * [<a href="www.complex-systems.com/pdf/09-3-2.pdf">1</a>]</div></div></li>
 * <li><div><span id="cite_ECC2003UTESSAMATSFGO" />Efr&#233;n Mezura-Montes
 * and&nbsp;<a href="http://delta.cs.cinvestav.mx/~ccoello/">Carlos Artemio
 * Coello Coello</a>: <span style="font-weight:bold">&ldquo;Using the
 * Evolution Strategies' Self-Adaptation Mechanism and Tournament Selection
 * for Global Optimization,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Intelligent Engineering
 * Systems through Artificial Neural Networks &#8210; Smart Engineering
 * System Design: Neural Networks, Fuzzy Logic, EP, Complex Systems and
 * Artificial Life &#8210; Proceedings of the Artificial Neural Networks in
 * Engineering Conference (ANNIE'03)</span>, 13, November&nbsp;2&ndash;5,
 * 2003, St. Louis, MO, USA, pages 373&ndash;378, Cihan H. Dagli, Anna L.
 * Buczak, Joydeep Ghosh, Mark J. Embrechts, and&nbsp;Okan Erson, editors,
 * St. Louis, MO, USA: American Society of Mechanical Engineers (ASME).
 * <div>link: [<a
 * href="http://www.lania.mx/~emezura/documentos/annie03.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_G1990ANOBTSFGAAPOSA" /><a
 * href="http://www.davidegoldberg.com/de-goldberg.html">David Edward
 * Goldberg</a>: <span style="font-weight:bold">&ldquo;A Note on Boltzmann
 * Tournament Selection for Genetic Algorithms and Population-Oriented
 * Simulated Annealing,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Complex Systems</span>
 * 4(4):445&ndash;460, 1990; published by Champaign, IL, USA: Complex
 * Systems Publications, Inc.. ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/08912513">0891-2513</a>. <div>link:
 * [<a href="www.complex-systems.com/pdf/04-4-5.pdf">1</a>]</div></div></li>
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
 * <li><div><span id="cite_SG2001TS" /><a
 * href="http://www.kumarasastry.com/">Kumara Sastry</a> and&nbsp;<a
 * href="http://www.davidegoldberg.com/de-goldberg.html">David Edward
 * Goldberg</a>: <span style="font-weight:bold">&ldquo;Modeling Tournament
 * Selection With Replacement Using Apparent Added Noise,&rdquo;</span> in
 * <span style="font-style:italic;font-family:cursive;">Proceedings of the
 * Genetic and Evolutionary Computation Conference (GECCO'01)</span>,
 * July&nbsp;7&ndash;11, 2001, San Francisco, CA, USA: Holiday Inn Golden
 * Gateway Hotel, pages 781, Lee Spector, <a
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
 * <div>CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a href
 * ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.16.786"
 * >10.1.1.16 .786</a></div></div></li>
 * <li><div><span id="cite_BT1995GATS" />Tobias Blickle and&nbsp;<a
 * href="http://www.lamsade.dauphine.fr/mcda/biblio/Author/THIELE-L.html"
 * >Lothar Thiele</a>: <span style="font-weight:bold">&ldquo;A Mathematical
 * Analysis of Tournament Selection,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the Sixth
 * International Conference on Genetic Algorithms (ICGA'95)</span>,
 * July&nbsp;15&ndash;19, 1995, Pittsburgh, PA, USA: University of
 * Pittsburgh, pages 9&ndash;16, Larry J. Eshelman, editor, San Francisco,
 * CA, USA: Morgan Kaufmann Publishers Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1558603700">1-55860-370-0</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781558603707">9781558603707</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=9xRRAAAAMAAJ">9xRRAAAAMAAJ</a>.
 * <div>links: [<a
 * href="http://citeseer.ist.psu.edu/blickle95mathematical.html">1</a>]
 * and&nbsp;[<a
 * href="http://www.handshake.de/user/blickle/publications/tournament.ps"
 * >2</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.52.9907"
 * >10.1.1.52.9907</a></div></div></li>
 * <li><div><span id="cite_LHK2004TVFUS" /><a
 * href="http://www.vetta.org/">Shane Legg</a>, <a
 * href="http://www.hutter1.net/">Marcus Hutter</a>, and&nbsp;Akshat Kumar:
 * <span style="font-weight:bold">&ldquo;Tournament versus Fitness Uniform
 * Selection,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;IDSIA-04-04, March&nbsp;4, 2004; published by Manno-Lugano,
 * Switzerland: Dalle Molle Institute for Artificial Intelligence (IDSIA),
 * University of Lugano, Faculty of Informatics / University of Applied
 * Sciences of Southern Switzerland (SUPSI), Department of Innovative
 * Technologies <span style="color:gray">[Istituto Dalle Molle di Studi
 * sull'Intelligenza Artificiale</span>]. <div>link: [<a
 * href="http://www.idsia.ch/idsiareport/IDSIA-04-04.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_LHK2004TVFUS2" /><a
 * href="http://www.vetta.org/">Shane Legg</a>, <a
 * href="http://www.hutter1.net/">Marcus Hutter</a>, and&nbsp;Akshat Kumar:
 * <span style="font-weight:bold">&ldquo;Tournament versus Fitness Uniform
 * Selection,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the IEEE
 * Congress on Evolutionary Computation (CEC'04)</span>,
 * June&nbsp;20&ndash;23, 2004, Portland, OR, USA, pages 2144&ndash;2151,
 * Los Alamitos, CA, USA: IEEE Computer Society Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780385152">0-7803-8515-2</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780385153">978-0-7803
 * -8515-3</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CEC.2004.1331162">10.1109/CEC.2004
 * .1331162</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=I8_5AAAACAAJ">I8_5AAAACAAJ</a>;
 * INSPEC Accession Number:&nbsp;8229180. <div>links: [<a
 * href="http://www.hutter1.net/ai/fussexp.pdf">1</a>], [<a
 * href="http://www.hutter1.net/ai/fussexp.ps">2</a>], and&nbsp;[<a
 * href="http://www.hutter1.net/ai/fussexp.zip">3</a>]; source code: [<a
 * href="http://www.hutter1.net/ai/fussdd.cpp">1</a>], [<a
 * href="http://www.hutter1.net/ai/fussdd.h">2</a>], [<a
 * href="http://www.hutter1.net/ai/fusstsp.cpp">3</a>], and&nbsp;[<a
 * href="http://www.hutter1.net/ai/fusstsp.h">4</a>]; arxiv:&nbsp;<a
 * href="http://arxiv.org/abs/cs/0403038v1">cs/0403038v1</a>;
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.71.2663">10.1
 * .1.71 .2663</a></div></div></li>
 * <li><div><span id="cite_H2002FUSTPGD" /><a
 * href="http://www.hutter1.net/">Marcus Hutter</a>: <span
 * style="font-weight:bold">&ldquo;Fitness Uniform Selection to Preserve
 * Genetic Diversity,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the IEEE
 * Congress on Evolutionary Computation (CEC'02); 2002 IEEE World Congress
 * on Computation Intelligence (WCCI'02)</span>, 1-2, May&nbsp;12&ndash;17,
 * 2002, Honolulu, HI, USA: Hilton Hawaiian Village Hotel (Beach Resort
 * &amp; Spa), pages 783&ndash;788, <a
 * href="http://www.natural-selection.com/people_dfogel.html">David B.
 * Fogel</a>, Mohamed A. El-Sharkawi, <a
 * href="http://www.cs.bham.ac.uk/~xin/">Xin Yao</a> <span
 * style="color:gray">[&#23002;&#26032;</span>], Hitoshi Iba, Paul Marrow,
 * and&nbsp;Mark Shackleton, editors, Los Alamitos, CA, USA: IEEE Computer
 * Society Press and&nbsp;Piscataway, NJ, USA: IEEE Computer Society.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780372824">0-7803-7282-4</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780372825">978-0-7803
 * -7282-5</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CEC.2002.1007025">10.1109/CEC.2002
 * .1007025</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/181357364">181357364</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=-ptVAAAAMAAJ">-ptVAAAAMAAJ</a>;
 * INSPEC Accession Number:&nbsp;7336007. <div>links: [<a
 * href="http://www.hutter1.net/ai/pfuss.pdf">1</a>], [<a
 * href="http://www.hutter1.net/ai/pfuss.ps">2</a>], [<a
 * href="http://www.hutter1.net/ai/pfuss.tar">3</a>], and&nbsp;[<a
 * href="ftp://ftp.idsia.ch/pub/techrep/IDSIA-01-01.ps.gz">4</a>];
 * arxiv:&nbsp;<a href="http://arxiv.org/abs/cs/0103015">cs/0103015</a>;
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.106.9784">
 * 10.1.1 .106.9784</a></div></div></li>
 * <li><div><span id="cite_ZHZG2005RWSGA" />Jinghui Zhong, Xiaomin Hu, Jun
 * Zhang, and&nbsp;Min Gu: <span style="font-weight:bold">&ldquo;Comparison
 * of Performance between Different Selection Strategies on Simple Genetic
 * Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the
 * International Conference on Computational Intelligence for Modelling,
 * Control and Automation and International Conference on Intelligent
 * Agents, Web Technologies and Internet Commerce Vol-2
 * (CIMCA-IAWTIC'06)</span>, 02, November&nbsp;28&ndash;December&nbsp;1,
 * 2006, Sydney, NSW, Australia, pages 1115&ndash;1121, <a href=
 * "http://www.canberra.edu.au/faculties/ise/research/staff/masoud-mohammadian"
 * >Masoud Mohammadian</a>, <a
 * href="http://en.wikipedia.org/wiki/Lotfi_A._Zadeh">Lotfi A. Zadeh</a>,
 * and&nbsp;Stephen Grossberg, editors, Los Alamitos, CA, USA: IEEE
 * Computer Society Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0769527310">0-7695-2731-0</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781424431281">978-1-424-
 * 43128-1</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CIMCA.2005.1631619">10.1109/CIMCA
 * .2005.1631619</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/85810664">85810664</a>; PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=593426258">593426258</a>; INSPEC
 * Accession Number:&nbsp;9109532. <div>link: [<a
 * href="http://www.ee.cityu.edu.hk/~jzhang/papers/zhongjinghui06.pdf"
 * >1</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.140.3747"
 * >10.1.1 .140.3747</a></div></div></li>
 * <li><div><span id="cite_X2009AAOSIGP" />Huayang Xie: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;An Analysis of
 * Selection in Genetic Programming,&rdquo;</span> PhD Thesis, 2009,
 * Wellington, New Zealand: Victoria University of Wellington. <div>link:
 * [<a href=
 * "http://researcharchive.vuw.ac.nz/bitstream/handle/10063/837/thesis.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_B1987RBAIITSA" />James E. Baker: <span
 * style="font-weight:bold">&ldquo;Reducing Bias and Inefficiency in the
 * Selection Algorithm,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the Second
 * International Conference on Genetic Algorithms and their Applications
 * (ICGA'87)</span>, July&nbsp;28&ndash;31, 1987, Cambridge, MA, USA:
 * Massachusetts Institute of Technology (MIT), pages 14&ndash;21, <a
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
 * <li><div><span id="cite_W1989EA" /><a
 * href="http://www.cs.colostate.edu/~whitley/">L. Darrell Whitley</a>:
 * <span style="font-weight:bold">&ldquo;The GENITOR Algorithm and
 * Selection Pressure: Why Rank-Based Allocation of Reproductive Trials is
 * Best,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the Third
 * International Conference on Genetic Algorithms (ICGA'89)</span>,
 * June&nbsp;4&ndash;7, 1989, Fairfax, VA, USA: George Mason University
 * (GMU), pages 116&ndash;121, James David Schaffer, editor, San Francisco,
 * CA, USA: Morgan Kaufmann Publishers Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1558600663">1-55860-066-3</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781558600669">978-1-55860
 * -066-9</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/257885359">257885359</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=BPBQAAAAMAAJ">BPBQAAAAMAAJ</a>.
 * <div>links: [<a href="http://citeseer.ist.psu.edu/531140.html">1</a>]
 * and&nbsp;[<a
 * href="http://www.cs.colostate.edu/~genitor/1989/ranking89.ps.gz">2</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.18.8195">10.1
 * .1.18 .8195</a></div></div></li>
 * <li><div><span id="cite_XZ1998RWSGA" />Li Xiaobo and&nbsp;Lin Zhenghui:
 * <span style="font-weight:bold">&ldquo;Recursive Implementation of
 * Roulette Wheel Selection for Genetic Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Journal of Shanghai
 * Jiaotong University</span> 32(4):89&ndash;91, April&nbsp;1998; published
 * by Beijing, China: Tsinghua Tongfang Knowledge Network Technology Co.,
 * Ltd. (TTKN), China Academic Journal Electronig Publishing House (CNKI).
 * <div>link: [<a
 * href="http://en.cnki.com.cn/Article_en/CJFDTOTAL-SHJT804.019.htm"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_CJ1991SIMPGA" />Robert J. Collins and&nbsp;David
 * R. Jefferson: <span style="font-weight:bold">&ldquo;Selection in
 * Massively Parallel Genetic Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the Fourth
 * International Conference on Genetic Algorithms (ICGA'91)</span>,
 * July&nbsp;13&ndash;16, 1991, San Diego, CA, USA: University of
 * California (UCSD), pages 249&ndash;256, Richard K. Belew and&nbsp;Lashon
 * Bernard Booker, editors, San Francisco, CA, USA: Morgan Kaufmann
 * Publishers Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1558602089">1-55860-208-9</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781558602083">978-1-55860
 * -208-3</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/24132977">24132977</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=YvBQAAAAMAAJ">YvBQAAAAMAAJ</a>.
 * <div>CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a href
 * ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.38.9252"
 * >10.1.1.38 .9252</a></div></div></li>
 * <li><div><span id="cite_B1985ASMFGA" />James E. Baker: <span
 * style="font-weight:bold">&ldquo;Adaptive Selection Methods for Genetic
 * Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the 1st
 * International Conference on Genetic Algorithms and their Applications
 * (ICGA'85)</span>, June&nbsp;24&ndash;26, 1985, Pittsburgh, PA, USA:
 * Carnegy Mellon University (CMU), pages 101&ndash;111, <a
 * href="http://www.biostat.pitt.edu/directory/bios/grefenstette.asp">John
 * J. Grefenstette</a>, editor, Hillsdale, NJ, USA: Lawrence Erlbaum
 * Associates. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0805804269">0-8058-0426-9</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780805804263">978-0-8058
 * -0426-3</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/19702892">19702892</a></div></li>
 * </ol>
 */
public class SelectionAlgorithm extends TSPModule {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * the selection algorithm constructor
   * 
   * @param name
   *          the algorithm's name
   */
  protected SelectionAlgorithm(final String name) {
    super(name);
  }

  /**
   * <p>
   * Select {@code mate.length} individuals from the population {@code pop}
   * (holding {@code pop.length} valid individuals) into the mating pool
   * {@code mate}. The general contract of this method is that
   * {@code mate.length<=pop.length}.
   * </p>
   * <p>
   * During the selection process, the contents of {@code pop} may be
   * re-arranged or sorted.
   * </p>
   * 
   * @param pop
   *          the population holding the individuals to select from
   * @param mate
   *          the mating pool: the destination array to be filled with
   *          selected individuals
   * @param f
   *          the objective function
   */
  public void select(final Individual<?>[] pop,
      final Individual<?>[] mate, final ObjectiveFunction f) {
    throw new UnsupportedOperationException();
  }
}
