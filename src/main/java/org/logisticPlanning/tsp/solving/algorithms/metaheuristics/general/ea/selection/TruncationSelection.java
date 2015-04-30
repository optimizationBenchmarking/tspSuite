package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.selection;

import java.util.Arrays;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.IndividualFitnessComparator;
import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.SelectionAlgorithm;

/**
 * <p>
 * The truncation selection algorithm is the most basic selection algorithm
 * commonly used in Evolution Strategies (ESes)&nbsp;[<a
 * href="#cite_BS2002ES" style="font-weight:bold">1</a>, <a
 * href="#cite_B2001ES" style="font-weight:bold">2</a>, <a
 * href="#cite_BHS1991ES" style="font-weight:bold">3</a>, <a
 * href="#cite_HB1998HHGEC" style="font-weight:bold">4</a>, <a
 * href="#cite_TB1996EA" style="font-weight:bold">5</a>, <a
 * href="#cite_R1965ES" style="font-weight:bold">6</a>, <a
 * href="#cite_S1965KYASDEFIDS" style="font-weight:bold">7</a>, <a
 * href="#cite_S1968EOEZDT1" style="font-weight:bold">8</a>, <a
 * href="#cite_R1973ES" style="font-weight:bold">9</a>, <a
 * href="#cite_S1975EUNO" style="font-weight:bold">10</a>, <a
 * href="#cite_S1981NOOCMES" style="font-weight:bold">11</a>, <a
 * href="#cite_R1994ES" style="font-weight:bold">12</a>, <a
 * href="#cite_S1993ES" style="font-weight:bold">13</a>]. Given a
 * population {@code pop} of {@code pop.length} ((or, e.g.,
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.EA#getLambda()
 * &#955;})) individuals, truncation selection chooses the
 * {@code mate.length} best ones (or
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.EA#getMu()
 * &#956;}) of them into the mating pool {@code mate}.
 * </p>
 * <p>
 * This selection scheme is implied when <code>(&#956;+&#955;)</code>- or
 * <code>(&#956;,&#955;)</code>-Evolution Strategies are mentioned. Much
 * research analyzing this selection scheme has been contributed&nbsp;[<a
 * href="#cite_B1995GCMFTAMLS" style="font-weight:bold">14</a>, <a
 * href="#cite_LHE2008TSBPPDFCSIGA" style="font-weight:bold">15</a>, <a
 * href="#cite_TG1994CMOGASS" style="font-weight:bold">16</a>, <a
 * href="#cite_MG1996GASSATVEON" style="font-weight:bold">17</a>, <a
 * href="#cite_GD1990EAA" style="font-weight:bold">18</a>, <a
 * href="#cite_BT1995EA" style="font-weight:bold">19</a>, <a
 * href="#cite_BT1996EA" style="font-weight:bold">20</a>, <a
 * href="#cite_CDC1996AOSAAMCA" style="font-weight:bold">21</a>, <a
 * href="#cite_RPB1999GDIGASS" style="font-weight:bold">22</a>]. Truncation
 * Selection is also known as Threshold Selection&nbsp;[<a
 * href="#cite_LHE2008TSBPPDFCSIGA" style="font-weight:bold">15</a>].
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_BS2002ES" /><a
 * href="https://homepages.fhv.at/hgb/">Hans-Georg Beyer</a> and&nbsp;<a
 * href="https://en.wikipedia.org/wiki/Hans-Paul_Schwefel">Hans-Paul
 * Schwefel</a>: <span style="font-weight:bold">&ldquo;Evolution Strategies
 * &#8210; A Comprehensive Introduction,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Natural Computing: An
 * International Journal</span> 1(1):3&ndash;52, March&nbsp;2002; published
 * by Norwell, MA, USA: Kluwer Academic Publishers and&nbsp;Dordrecht,
 * Netherlands: Springer Netherlands. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1023/A:1015059928466"
 * >10.1023/A:1015059928466</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/15677818">1567-7818</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15729796">1572-9796</a>. <div>link:
 * [<a href="http://www.cs.bham.ac.uk/~pxt/NIL/es.pdf">1</a>]</div></div></li>
 * <li><div><span id="cite_B2001ES" /><a
 * href="https://homepages.fhv.at/hgb/">Hans-Georg Beyer</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;The Theory of
 * Evolution Strategies,&rdquo;</span> May&nbsp;27, 2001, Natural Computing
 * Series, New York, NY, USA: Springer New York. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540672974">3-540-67297-4</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540672975">978-3-540-
 * 67297-5</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/2001020620">2001020620</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/46394059">46394059</a> and&nbsp;<a
 * href="https://www.worldcat.org/oclc/247928112">247928112</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=8tbInLufkTMC">8tbInLufkTMC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/16197127">1619-7127</a></div></li>
 * <li><div><span id="cite_BHS1991ES" /><a
 * href="http://www.liacs.nl/~baeck/contact.htm">Thomas B&#228;ck</a>,
 * Frank Hoffmeister, and&nbsp;<a
 * href="https://en.wikipedia.org/wiki/Hans-Paul_Schwefel">Hans-Paul
 * Schwefel</a>: <span style="font-weight:bold">&ldquo;A Survey of
 * Evolution Strategies,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the Fourth
 * International Conference on Genetic Algorithms (ICGA'91)</span>,
 * July&nbsp;13&ndash;16, 1991, San Diego, CA, USA: University of
 * California (UCSD), pages 2&ndash;9, Richard K. Belew and&nbsp;Lashon
 * Bernard Booker, editors, San Francisco, CA, USA: Morgan Kaufmann
 * Publishers Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1558602089">1-55860-208-9</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781558602083">978-1-55860
 * -208-3</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/24132977">24132977</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=YvBQAAAAMAAJ">YvBQAAAAMAAJ</a>.
 * <div>links: [<a href="http://citeseer.ist.psu.edu/19412.html">1</a>],
 * [<a
 * href="http://130.203.133.121:8080/viewdoc/summary?doi=10.1.1.42.3375"
 * >2</a>], [<a href=
 * "http://bi.snu.ac.kr/Info/EC/A%20Survey%20of%20Evolution%20Strategies.pdf"
 * >3</a>], and&nbsp;[<a
 * href="http://www6.uniovi.es/pub/EC/GA/papers/icga91.ps.gz">4</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.42.3375">10.1
 * .1.42 .3375</a></div></div></li>
 * <li><div><span id="cite_HB1998HHGEC" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Hitch-Hiker's
 * Guide to Evolutionary Computation: A List of Frequently Asked Questions
 * (FAQ) (HHGT),&rdquo;</span> (Website), March&nbsp;29, 2000, J&#246;rg
 * Heitk&#246;tter and&nbsp;David Beasley, editors, Santa F&#233;, NM, USA:
 * ENCORE (The EvolutioNary Computation REpository Network). <div>links:
 * [<a href="http://www.cse.dmu.ac.uk/~rij/gafaq/top.htm">1</a>], [<a
 * href="http://alife.santafe.edu/~joke/encore/www/">2</a>], [<a
 * href="http://www.etsimo.uniovi.es/ftp/pub/EC/FAQ/www/">3</a>],
 * and&nbsp;[<a
 * href="http://www.aip.de/~ast/EvolCompFAQ/">4</a>]</div></div></li>
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
 * <li><div><span id="cite_R1965ES" /><a
 * href="https://en.wikipedia.org/wiki/Ingo_Rechenberg">Ingo
 * Rechenberg</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Cybernetic
 * Solution Path of an Experimental Problem,&rdquo;</span>
 * August&nbsp;1965, Farnborough, Hampshire, UK: Royal Aircraft
 * Establishment</div></li>
 * <li><div><span id="cite_S1965KYASDEFIDS" /><a
 * href="https://en.wikipedia.org/wiki/Hans-Paul_Schwefel">Hans-Paul
 * Schwefel</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Kybernetische
 * Evolution als Strategie der exprimentellen Forschung in der
 * Str&#246;mungstechnik,&rdquo;</span> Master's Thesis, 1965, Berlin,
 * Germany: Technische Universit&#228;t Berlin</div></li>
 * <li><div><span id="cite_S1968EOEZDT1" /><a
 * href="https://en.wikipedia.org/wiki/Hans-Paul_Schwefel">Hans-Paul
 * Schwefel</a>: <span style="font-weight:bold">&ldquo;Experimentelle
 * Optimierung einer Zweiphasend&#252;se Teil I.,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;35, 1968; published by Berlin, Germany: AEG Research
 * Institute</div></li>
 * <li><div><span id="cite_R1973ES" /><a
 * href="https://en.wikipedia.org/wiki/Ingo_Rechenberg">Ingo
 * Rechenberg</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo
 * ;Evolutionsstrategie: Optimierung technischer Systeme nach Prinzipien
 * der biologischen Evolution,&rdquo;</span> PhD Thesis, 1971&ndash;1973,
 * Berlin, Germany: Technische Universit&#228;t Berlin. volume 15 of
 * Problemata, Stuttgart, Germany: Friedrick Frommann Verlag and&nbsp;Bad
 * Cannstadt, Stuttgart, Baden-W&#252;rttemberg, Germany: Frommann-Holzboog
 * Verlag. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3772803741">3-7728-0374-1</a>, <a
 * href="https://www.worldcat.org/isbn/3772803733">3-7728-0373-3</a>, <a
 * href
 * ="https://www.worldcat.org/isbn/9783772803741">978-3-7728-0374-1</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783772803734">978-3-7728
 * -0373-4</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/74320689">74320689</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/9020616">9020616</a>, <a
 * href="https://www.worldcat.org/oclc/500569005">500569005</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/oclc/462818122">462818122</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=QcNNGQAACAAJ">QcNNGQAACAAJ</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=024852090">024852090</a></div></li>
 * <li><div><span id="cite_S1975EUNO" /><a
 * href="https://en.wikipedia.org/wiki/Hans-Paul_Schwefel">Hans-Paul
 * Schwefel</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo
 * ;Evolutionsstrategie und numerische Optimierung,&rdquo;</span> PhD
 * Thesis, 1975, Berlin, Germany: Technische Universit&#228;t Berlin,
 * Institut f&#252;r Me&#223;- und Regelungstechnik, Institut f&#252;r
 * Biologie und Anthropologie. OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/251655294">251655294</a> and&nbsp;<a
 * href="https://www.worldcat.org/oclc/52361662">52361662</a>; PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=156923181">156923181</a></div></li>
 * <li><div><span id="cite_S1981NOOCMES" /><a
 * href="https://en.wikipedia.org/wiki/Hans-Paul_Schwefel">Hans-Paul
 * Schwefel</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Numerical
 * Optimization of Computer Models,&rdquo;</span> 1981, New York, NY, USA:
 * John Wiley &amp; Sons Ltd.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0471099880">0-471-09988-0</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780471099888">978-0-471-
 * 09988-8</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/81173223">81173223</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/301251708">301251708</a> and&nbsp;<a
 * href="https://www.worldcat.org/oclc/8011455">8011455</a>; PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=01489498X">01489498X</a></div></li>
 * <li><div><span id="cite_R1994ES" /><a
 * href="https://en.wikipedia.org/wiki/Ingo_Rechenberg">Ingo
 * Rechenberg</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo
 * ;Evolutionsstrategie '94,&rdquo;</span> 1994, volume 1 of Werkstatt
 * Bionik und Evolutionstechnik, Bad Cannstadt, Stuttgart,
 * Baden-W&#252;rttemberg, Germany: Frommann-Holzboog Verlag. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3772816428">3-7728-1642-8</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783772816420">978-3-772-
 * 81642-0</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/75424354">75424354</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=savAAAACAAJ">savAAAACAAJ</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=153251220">153251220</a></div></li>
 * <li><div><span id="cite_S1993ES" /><a
 * href="https://en.wikipedia.org/wiki/Hans-Paul_Schwefel">Hans-Paul
 * Schwefel</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Evolution and
 * Optimum Seeking,&rdquo;</span> 1995, Sixth-Generation Computer
 * Technology Series, New York, NY, USA: John Wiley &amp; Sons Ltd..
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0471571482">0-471-57148-2</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780471571483">978-0-471-
 * 57148-3</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/94022270">94022270</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/222866514">222866514</a> and&nbsp;<a
 * href="https://www.worldcat.org/oclc/30701094">30701094</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=dfNQAAAAMAAJ">dfNQAAAAMAAJ</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=194661636">194661636</a></div></li>
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
 * </ol>
 */
public final class TruncationSelection extends SelectionAlgorithm {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final TruncationSelection INSTANCE = new TruncationSelection();

  /**
   * the truncation selection algorithm's constructor: private, use
   * {@link #INSTANCE}
   */
  private TruncationSelection() {
    super("truncationSelection"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final void select(final Individual<?>[] pop,
      final Individual<?>[] mate, final ObjectiveFunction f) {
    Arrays.sort(pop, IndividualFitnessComparator.INSTANCE);
    System.arraycopy(pop, 0, mate, 0, mate.length);
  }

  /** {@inheritDoc} */
  @Override
  public final TruncationSelection clone() {
    return TruncationSelection.INSTANCE;
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link TruncationSelection#INSTANCE
   * TruncationSelection.INSTANCE} for serialization, i.e., when the
   * instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   * 
   * @return the replacement instance (always
   *         {@link TruncationSelection#INSTANCE
   *         TruncationSelection.INSTANCE} )
   */
  private final Object writeReplace() {
    return TruncationSelection.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link TruncationSelection#INSTANCE
   * TruncationSelection.INSTANCE} after serialization, i.e., when the
   * instance is read with {@link java.io.ObjectInputStream#readObject()}.
   * 
   * @return the replacement instance (always
   *         {@link TruncationSelection#INSTANCE
   *         TruncationSelection.INSTANCE} )
   */
  private final Object readResolve() {
    return TruncationSelection.INSTANCE;
  }

}
