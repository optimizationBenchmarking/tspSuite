/**
 * <p>
 * General metaheuristic approaches.
 * <h2 id="generalMetaheuristicsList">
 * {@linkplain org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general
 * General Metaheuristics}</h2>
 * <p>
 * General metaheuristics are algorithms that work on arbitrary
 * representations. We implemented the following approaches:
 * </p>
 * <ol>
 * <li>The
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.EA
 * EA} is an <a href="http://en.wikipedia.org/wiki/Evolutionary_algorithm">
 * evolutionary algorithm</a> (EA)&nbsp;[<a href="#cite_WGOEB"
 * style="font-weight:bold">1</a>, <a href="#cite_CWM2011VOEAFRWA"
 * style="font-weight:bold">2</a>, <a href="#cite_DJ2006ECAUA"
 * style="font-weight:bold">3</a>, <a href="#cite_BFM1997EA"
 * style="font-weight:bold">4</a>, <a href="#cite_SMY2002EO"
 * style="font-weight:bold">5</a>] working with a (&#956;+&#955;) or
 * (&#956;,&#955;) population strategy that can be combined with different
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.selection
 * selection algorithms} and
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.fitness
 * fitness assignment processes} or
 * {@link org.logisticPlanning.tsp.solving.gpm genotype-phenotype mappings}
 * . An EA works on a set of solutions (population) that iteratively
 * undergo {@link org.logisticPlanning.tsp.solving.operators.UnaryOperator
 * mutation},
 * {@link org.logisticPlanning.tsp.solving.operators.BinaryOperator
 * crossover}, and
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.selection
 * selection} &nbsp; like biological individuals and populations do
 * according to Darwin's Evolution theory&nbsp;[<a href="#cite_D1859EV"
 * style="font-weight:bold">6</a>, <a href="#cite_D1859EV2"
 * style="font-weight:bold">7</a>].</li>
 * </ol>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_WGOEB" /><a
 * href="http://www.it-weise.de/">Thomas Weise</a> <span
 * style="color:gray">[&#27748;&#21355;&#24605;</span>]: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Global
 * Optimization Algorithms &#8210; Theory and Application,&rdquo;</span>
 * 2009, Germany: it-weise.de (self-published). <div>link: [<a
 * href="http://www.it-weise.de/projects/book.pdf">1</a>]</div></div></li>
 * <li><div><span id="cite_CWM2011VOEAFRWA" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Variants of
 * Evolutionary Algorithms for Real-World Applications,&rdquo;</span>
 * September&nbsp;30, 2011&ndash;2012, <a
 * href="http://www.swinburne.edu.my/iSECURES/index.php?do=rchiong">Raymond
 * Chiong</a>, <a href="http://www.it-weise.de/">Thomas Weise</a> <span
 * style="color:gray">[&#27748;&#21355;&#24605;</span>], and&nbsp;<a
 * href="http://cs.adelaide.edu.au/~zbyszek/">Zbigniew Michalewicz</a>,
 * editors, Berlin/Heidelberg: Springer-Verlag. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783642234231">978-3-642-23423-1</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783642234248">978-3-642-
 * 23424-8</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/2011935740">2011935740</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/978-3-642-23424-8"
 * >10.1007/978-3-642-23424-8</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=B2ONePP40MEC">B2ONePP40MEC</a>;
 * further information: [<a
 * href="http://www.it-weise.de/documents/files/ea-app-book/index.html"
 * >1</a>]</div></li>
 * <li><div><span id="cite_DJ2006ECAUA" /><a
 * href="http://cs.gmu.edu/~kdejong/">Kenneth Alan De Jong</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Evolutionary
 * Computation: A Unified Approach,&rdquo;</span> February&nbsp;2006,
 * volume 4 of Complex Adaptive Systems, Bradford Books, Cambridge, MA,
 * USA: MIT Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/8120330021">8120330021</a>, <a
 * href="https://www.worldcat.org/isbn/0262041944">0262041944</a>, <a
 * href="https://www.worldcat.org/isbn/9780262041942">978-0262041942</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9788120330023">978-8120330023</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/276452339">276452339</a>, <a
 * href="https://www.worldcat.org/oclc/46500047">46500047</a>, and&nbsp;<a
 * href="https://www.worldcat.org/oclc/182530408">182530408</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=OIRQAAAAMAAJ">OIRQAAAAMAAJ</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/10894365">1089-4365</a>. <div>link:
 * [<a href=
 * "http://www.inf.ufg.br/~telma/topicoscomputacaoevolutiva/EvolutionaryComputation.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_BFM1997EA" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Handbook of
 * Evolutionary Computation,&rdquo;</span> January&nbsp;1, 1997, <a
 * href="http://www.liacs.nl/~baeck/contact.htm">Thomas B&#228;ck</a>, <a
 * href="http://www.natural-selection.com/people_dfogel.html">David B.
 * Fogel</a>, and&nbsp;<a
 * href="http://cs.adelaide.edu.au/~zbyszek/">Zbigniew Michalewicz</a>,
 * editors, Computational Intelligence Library, New York, NY, USA: Oxford
 * University Press, Inc., Dirac House, Temple Back, Bristol, UK: Institute
 * of Physics Publishing Ltd. (IOP), and&nbsp;Boca Raton, FL, USA: CRC
 * Press, Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0750308958">0-7503-0895-8</a>, <a
 * href="https://www.worldcat.org/isbn/0750303921">0-7503-0392-1</a>, <a
 * href
 * ="https://www.worldcat.org/isbn/9780750308953">978-0-7503-0895-3</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780750303927">978-0-7503
 * -0392-7</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/97004461">97004461</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/327018351">327018351</a>, <a
 * href="https://www.worldcat.org/oclc/173074676">173074676</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/oclc/327018351">327018351</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=n5nuiIZvmpAC">n5nuiIZvmpAC</a>,
 * <a
 * href="http://books.google.com/books?id=neKNGAAACAAJ">neKNGAAACAAJ</a>,
 * and&nbsp;<a
 * href="http://books.google.com/books?id=kgqGQgAACAAJ">kgqGQgAACAAJ</a>;
 * PPN:&nbsp;<a href="http://gso.gbv.de/PPNSET?PPN=224708430">224708430</a>
 * and&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=364589272">364589272</a></div></li>
 * <li><div><span id="cite_SMY2002EO" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Evolutionary
 * Optimization,&rdquo;</span> 2002, <a
 * href="http://seit.unsw.adfa.edu.au/staff/sites/rsarker/">Ruhul Amin
 * Sarker</a>, <a href=
 * "http://www.canberra.edu.au/faculties/ise/research/staff/masoud-mohammadian"
 * >Masoud Mohammadian</a>, and&nbsp;<a
 * href="http://www.cs.bham.ac.uk/~xin/">Xin Yao</a> <span
 * style="color:gray">[&#23002;&#26032;</span>], editors, volume 48 of
 * International Series in Operations Research &amp; Management Science,
 * Norwell, MA, USA: Kluwer Academic Publishers, Dordrecht, Netherlands:
 * Springer Netherlands, and&nbsp;Boston, MA, USA: Springer US.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0306480417">0-306-48041-7</a>, <a
 * href="https://www.worldcat.org/isbn/0792376544">0-7923-7654-4</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780792376545">978-0-7923
 * -7654-5</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/b101816">10.1007/b101816</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/08848289">0884-8289</a></div></li>
 * <li><div><span id="cite_D1859EV" />Charles Darwin: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;On the Origin of
 * Species by Means of Natural Selection, or the Preservation of Favoured
 * Races in the Struggle for Life,&rdquo;</span> November&nbsp;24, 1859,
 * London, UK: John Murray. Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=6gcpNAAACAAJ">6gcpNAAACAAJ</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=nNQNAAAAYAAJ">nNQNAAAAYAAJ</a>.
 * <div>link: [<a
 * href="http://www.gutenberg.org/etext/1228">1</a>]</div></div></li>
 * <li><div><span id="cite_D1859EV2" />Charles Darwin: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;On the Origin of
 * Species,&rdquo;</span> May&nbsp;1998, Gillian Beer, editor, Oxford
 * World's Classics, New York, NY, USA: Oxford University Press, Inc..
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/019283438X">0-19-283438-X</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780192834386">978-0-19-283438
 * -6</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/39117382">39117382</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=LDrPI52uFQsC">LDrPI52uFQsC
 * </a></div></li>
 * </ol>
 */
package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general;

