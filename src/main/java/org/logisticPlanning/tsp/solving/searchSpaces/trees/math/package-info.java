/**
 * <p>
 * Classes for representing mathematical formulas as tree data structures
 * as it is commonly done in symbolic regression&nbsp;[<a
 * href="#cite_PLMP2008AFGTGP" style="font-weight:bold">1</a>, <a
 * href="#cite_KOZ1992GP" style="font-weight:bold">2</a>, <a
 * href="#cite_WGOEB" style="font-weight:bold">3</a>, <a
 * href="#cite_AB2000GPSR" style="font-weight:bold">4</a>, <a
 * href="#cite_LP2001GA" style="font-weight:bold">5</a>, <a
 * href="#cite_R1996GP" style="font-weight:bold">6</a>, <a
 * href="#cite_SOFTOB" style="font-weight:bold">7</a>]. One application of
 * this search space to the traveling salesman problem is investigated in
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.developmental.devUpdatingEA.DevUpdatingEA
 * developmental updating EA}, published as&nbsp;[<a
 * href="#cite_OWDC2013SADAFTSP" style="font-weight:bold">8</a>].
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_PLMP2008AFGTGP" /><a
 * href="http://cswww.essex.ac.uk/staff/poli/">Riccardo Poli</a>, <a
 * href="http://www0.cs.ucl.ac.uk/staff/W.Langdon/">William Benjamin
 * Langdon</a>, and&nbsp;<a
 * href="http://facultypages.morris.umn.edu/~mcphee/">Nicholas Freitag
 * McPhee</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;A Field Guide to
 * Genetic Programming,&rdquo;</span> March&nbsp;2008, London, UK: Lulu
 * Enterprises UK Ltd. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1409200736">1-4092-0073-6</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781409200734">978-1-4092
 * -0073-4</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/225855345">225855345</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=3PBrqNK5fFQC">3PBrqNK5fFQC</a>.
 * <div>links: [<a href=
 * "http://www.lulu.com/items/volume_63/2167000/2167025/2/print/book.pdf"
 * >1</a>] and&nbsp;[<a
 * href="http://www.gp-field-guide.org.uk/">2</a>]</div></div></li>
 * <li><div><span id="cite_KOZ1992GP" /><a
 * href="https://en.wikipedia.org/wiki/John_Koza">John R. Koza</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Genetic
 * Programming: On the Programming of Computers by Means of Natural
 * Selection,&rdquo;</span> December&nbsp;1992, Bradford Books, Cambridge,
 * MA, USA: MIT Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0262111705">0-262-11170-5</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780262111706">978-0-262-
 * 11170-6</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/92025785">92025785</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/245844858">245844858</a>, <a
 * href="https://www.worldcat.org/oclc/476715745">476715745</a>, <a
 * href="https://www.worldcat.org/oclc/312492070">312492070</a>, <a
 * href="https://www.worldcat.org/oclc/26263956">26263956</a>, <a
 * href="https://www.worldcat.org/oclc/610975642">610975642</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/oclc/473279968">473279968</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=Bhtxo60BV0EC">Bhtxo60BV0EC</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=Wd8TAAAACAAJ">Wd8TAAAACAAJ</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=110450795">110450795</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=152621075">152621075</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=282081046">282081046</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=191489522">191489522</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=237675633">237675633</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=246890320">246890320</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=49354836X">49354836X</a>, and&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=185924921">185924921</a></div></li>
 * <li><div><span id="cite_WGOEB" /><a
 * href="http://www.it-weise.de/">Thomas Weise</a> <span
 * style="color:gray">[&#27748;&#21355;&#24605;</span>]: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Global
 * Optimization Algorithms &#8210; Theory and Application,&rdquo;</span>
 * 2009, Germany: it-weise.de (self-published). <div>link: [<a
 * href="http://www.it-weise.de/projects/book.pdf">1</a>]</div></div></li>
 * <li><div><span id="cite_AB2000GPSR" />Douglas A. Augusto and&nbsp;Helio
 * Jose&#233; Correa Barbosa: <span
 * style="font-weight:bold">&ldquo;Symbolic Regression via Genetic
 * Programming,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the VI
 * Brazilian Symposium on Neural Networks (SBRN'00)</span>,
 * November&nbsp;22&ndash;25, 2000, Rio de Janeiro, RJ, Brazil, pages
 * 173&ndash;178, Felipe M. G. Fran&#231;a and&nbsp;Carlos H. C. Ribeiro,
 * editors, Washington, DC, USA: IEEE Computer Society. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0769508561">0-7695-0856-1</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780769508566">978-0-7695
 * -0856-6</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/SBRN.2000.889734"
 * >10.1109/SBRN.2000.889734</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/80493808">80493808</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=Q6d_KQAACAAJ">Q6d_KQAACAAJ</a>;
 * INSPEC Accession Number:&nbsp;6813089</div></li>
 * <li><div><span id="cite_LP2001GA" />Sean Luke and&nbsp;Liviu Panait:
 * <span style="font-weight:bold">&ldquo;A Survey and Comparison of Tree
 * Generation Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the
 * Genetic and Evolutionary Computation Conference (GECCO'01)</span>,
 * July&nbsp;7&ndash;11, 2001, San Francisco, CA, USA: Holiday Inn Golden
 * Gateway Hotel, pages 81&ndash;88, Lee Spector, <a
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
 * <div>links: [<a
 * href="http://citeseer.ist.psu.edu/luke01survey.html">1</a>] and&nbsp;[<a
 * href="http://www.cs.gmu.edu/~sean/papers/treegenalgs.pdf">2</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.28.4837">
 * 10.1 .1.28.4837</a></div></div></li>
 * <li><div><span id="cite_R1996GP" />Justinian P. Rosca: <span
 * style="font-weight:bold">&ldquo;Generality versus Size in Genetic
 * Programming,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the First
 * Annual Conference of Genetic Programming (GP'96)</span>,
 * July&nbsp;28&ndash;31, 1996, Stanford, CA, USA: Stanford University,
 * pages 381&ndash;387, <a
 * href="https://en.wikipedia.org/wiki/John_Koza">John R. Koza</a>, <a
 * href="http://www.davidegoldberg.com/de-goldberg.html">David Edward
 * Goldberg</a>, <a
 * href="http://www.natural-selection.com/people_dfogel.html">David B.
 * Fogel</a>, and&nbsp;Rick L. Riolo, editors, Complex Adaptive Systems,
 * Bradford Books, Cambridge, MA, USA: MIT Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0262611279">0-262-61127-9</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780262611275">978-0-262-
 * 61127-5</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/35580073">35580073</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=ndy0QgAACAAJ">ndy0QgAACAAJ</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/10894365">1089-4365</a>. <div>links:
 * [<a href="ftp://ftp.cs.rochester.edu/pub/u/rosca/gp/96.gp.ps.gz">1</a>]
 * and&nbsp;[<a href=
 * "http://www.cs.bham.ac.uk/~wbl/biblio/gp-html/rosca_1996_gVsGP.html"
 * >2</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.54.2363"
 * >10.1.1.54.2363</a></div></div></li>
 * <li><div><span id="cite_SOFTOB" />Christian Gagn&#233; and&nbsp;Marc
 * Parizeau: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Open
 * BEAGLE,&rdquo;</span> (Software), November&nbsp;29, 2007, Qu&#233;bec,
 * QC, Canada: Universit&#233; Laval, D&#233;partement de G&#233;nie
 * &#201;lectrique et de G&#233;nie Informatique, Laboratoire de Vision et
 * Syst&#232;mes Num&#233;riques (LVSN) and&nbsp;Orsay, France:
 * Universit&#233; Paris Sud, Institut National de Recherche en
 * Informatique et en Automatique (INRIA) Futurs, &#201;quipe TAO.
 * <div>link: [<a href="http://beagle.gel.ulaval.ca/">1</a>]</div></div></li>
 * <li><div><span id="cite_OWDC2013SADAFTSP" /><a
 * href="mailto:oyjmical@mail.ustc.edu.cn">Jin Ouyang</a> <span
 * style="color:gray">[&#27431;&#38451;&#26187;</span>], <a
 * href="http://www.it-weise.de/">Thomas Weise</a> <span
 * style="color:gray">[&#27748;&#21355;&#24605;</span>], <a
 * href="http://www.marmakoide.org/">Alexandre Devert</a>, and&nbsp;<a
 * href="http://www.swinburne.edu.my/iSECURES/index.php?do=rchiong">Raymond
 * Chiong</a>: <span style="font-weight:bold">&ldquo;SDGP: A Developmental
 * Approach for Traveling Salesman Problems,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the 2013
 * IEEE Symposium on Computational Intelligence in Production and Logistics
 * Systems (CIPLS'13)</span>, April&nbsp;15&ndash;19, 2013, Singapore:
 * Grand Copthorne Waterfront Hotel, pages 78&ndash;85, Los Alamitos, CA,
 * USA: IEEE Computer Society Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781467359054"
 * >978-1-4673-5905-4</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CIPLS.2013.6595203"
 * >10.1109/CIPLS.2013.6595203</a>; INSPEC Accession Number:&nbsp;13752116;
 * EI:&nbsp;20134116837899. <div>link: [<a
 * href="http://www.it-weise.de/documents/files/OWDC2013SADAFTSP.pdf"
 * >1</a>]</div></div></li>
 * </ol>
 */
package org.logisticPlanning.tsp.solving.searchSpaces.trees.math;

