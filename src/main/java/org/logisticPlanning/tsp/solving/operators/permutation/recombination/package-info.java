/**
 * <p>
 * Some {@link org.logisticPlanning.tsp.solving.operators.BinaryOperator
 * recombination operators} for permutations. Such operators combine two
 * existing permutations to create a new one. In this package, we
 * implement:
 * </p>
 * <ol>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationAlternatingPositionCrossover
 * Alternating Position Crossover}&nbsp;[<a
 * href="#cite_LKPM1997DBNTOTMGWGA" style="font-weight:bold">1</a>, <a
 * href="#cite_LKMUB1999GAFTTSPARORAO" style="font-weight:bold">2</a>],</li>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationCycleCrossover
 * Cycle Crossover}&nbsp;[<a href="#cite_OSH1987ASOPCOOTTSP"
 * style="font-weight:bold">3</a>, <a href="#cite_SMMWW1991ACOGSO"
 * style="font-weight:bold">4</a>, <a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">2</a>, <a href="#cite_B2003UAOSOPDWAOMATTSP"
 * style="font-weight:bold">5</a>],</li>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationEdgeCrossover
 * Edge Crossover}&nbsp;[<a href="#cite_WSF1989SPATSTGERO"
 * style="font-weight:bold">6</a>, <a href="#cite_WSS1991TTSASSQSUGER"
 * style="font-weight:bold">7</a>, <a href="#cite_SMMWW1991ACOGSO"
 * style="font-weight:bold">4</a>, <a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">2</a>],</li>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationHeuristicCrossover
 * Heuristic Crossover}&nbsp;[<a href="#cite_G1987IPSKIGA"
 * style="font-weight:bold">8</a>, <a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">2</a>],</li>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationMaximalPreservativeCrossover
 * Maximal-Preservative Crossover}&nbsp;[<a href="#cite_MGSK1988EAICO"
 * style="font-weight:bold">9</a>, <a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">2</a>],</li>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationOrderCrossover
 * Order Crossover} (OX1)&nbsp;[<a href="#cite_D1985AAATED"
 * style="font-weight:bold">10</a>, <a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">2</a>],</li>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationOrderBasedCrossover
 * Order-Based Crossover} (OX2)&nbsp;[<a href="#cite_D1985AAATED"
 * style="font-weight:bold">10</a>, <a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">2</a>],</li>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationPartiallyMappedCrossover
 * Partially-mapped Crossover} (POX)&nbsp;[<a href="#cite_GLJ1985ALATT"
 * style="font-weight:bold">11</a>, <a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">2</a>],</li>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationPositionBasedCrossover
 * Position-based Crossover} (POX)&nbsp;[<a href="#cite_S1991SOUGA"
 * style="font-weight:bold">12</a>, <a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">2</a>], and</li>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationSavingsCrossover
 * Savings Crossover}.</li>
 * </ol>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_LKPM1997DBNTOTMGWGA" /><a href=
 * "http://cig.fi.upm.es/index.php?option=com_content&amp;view=article&amp;id=78&amp;Itemid=111"
 * >Pedro Larra&#241;aga</a>, <a href=
 * "http://www.tilburguniversity.edu/nl/webwijs/show/&amp;uid=c.m.h.kuijpers?uid=c.m.h.kuijpers"
 * >Cindy M. H. Kuijpers</a>, Mikel Poza, and&nbsp;Roberto H. Murga: <span
 * style="font-weight:bold">&ldquo;Decomposing Bayesian Networks:
 * Triangulation of the Moral Graph with Genetic Algorithms,&rdquo;</span>
 * in <span style="font-style:italic;font-family:cursive;">Statistics and
 * Computing</span> 7(1):19&ndash;34, March&nbsp;1, 1997; published by
 * Dordrecht, Netherlands: Springer Netherlands and&nbsp;Golden, CO, USA:
 * Samizdat Press. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1023/A:1018553211613"
 * >10.1023/A:1018553211613</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/09603174">0960-3174</a>.
 * <div>CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.54.1276"
 * >10.1.1.54.1276</a></div></div></li>
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
 * <li><div><span id="cite_SMMWW1991ACOGSO" />Timothy Starkweather, S.
 * McDaniel, Keith E. Mathias, <a
 * href="http://www.cs.colostate.edu/~whitley/">L. Darrell Whitley</a>,
 * and&nbsp;C. Whitley: <span style="font-weight:bold">&ldquo;A Comparison
 * of Genetic Sequencing Operators,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the Fourth
 * International Conference on Genetic Algorithms (ICGA'91)</span>,
 * July&nbsp;13&ndash;16, 1991, San Diego, CA, USA: University of
 * California (UCSD), pages 69&ndash;76, Richard K. Belew and&nbsp;Lashon
 * Bernard Booker, editors, San Francisco, CA, USA: Morgan Kaufmann
 * Publishers Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1558602089">1-55860-208-9</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781558602083">978-1-55860
 * -208-3</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/24132977">24132977</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=h9nYpwAACAAJ">h9nYpwAACAAJ</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=YvBQAAAAMAAJ">YvBQAAAAMAAJ</a>.
 * <div>CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.18.4329"
 * >10.1.1.18.4329</a></div></div></li>
 * <li><div><span id="cite_B2003UAOSOPDWAOMATTSP" /><a
 * href="http://people.binf.ku.dk/wb/">Wouter Boomsma</a>: <span
 * style="font-weight:bold">&ldquo;Using Adaptive Operator Scheduling on
 * Problem Domains with an Operator Manifold: Applications to the
 * Travelling Salesman Problem,&rdquo;</span> 2:1274&ndash;1279,
 * December&nbsp;8&ndash;13, 2003; edited by <a
 * href="http://seit.unsw.adfa.edu.au/staff/sites/rsarker/">Ruhul Amin
 * Sarker</a>, <a
 * href="http://ai.cs.wayne.edu/ai/member%20webs/Reynolds/">Robert G.
 * Reynolds</a>, <a
 * href="http://seit.unsw.adfa.edu.au/staff/sites/abbass/">Hussein Aly
 * Abbass Amein</a>, <a href="http://vlab.ee.nus.edu.sg/~kctan/">Kay Chen
 * Tan</a>, <a href="http://sc.snu.ac.kr/~rim/index.html">Robert Ian
 * McKay</a>, Daryl Leslie Essam, and&nbsp;Tom Gedeon; published by
 * Piscataway, NJ, USA: IEEE Computer Society. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780378040">0-7803-7804-0</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780378049">978-0-7803
 * -7804-9</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CEC.2003.1299815"
 * >10.1109/CEC.2003.1299815</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=CyBpAAAACAAJ">CyBpAAAACAAJ</a>;
 * INSPEC Accession Number:&nbsp;8088168. <div>link: [<a
 * href="http://people.binf.ku.dk/wb/TSPpaper.pdf">1</a>]</div></div></li>
 * <li><div><span id="cite_WSF1989SPATSTGERO" /><a
 * href="http://www.cs.colostate.edu/~whitley/">L. Darrell Whitley</a>,
 * Timothy Starkweather, and&nbsp;D'Ann Fuquay: <span
 * style="font-weight:bold">&ldquo;Scheduling Problems and Traveling
 * Salesman: The Genetic Edge Recombination Operator,&rdquo;</span> in
 * <span style="font-style:italic;font-family:cursive;">Proceedings of the
 * Third International Conference on Genetic Algorithms (ICGA'89)</span>,
 * June&nbsp;4&ndash;7, 1989, Fairfax, VA, USA: George Mason University
 * (GMU), pages 133&ndash;140, James David Schaffer, editor, San Francisco,
 * CA, USA: Morgan Kaufmann Publishers Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1558600663">1-55860-066-3</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781558600669">978-1-55860
 * -066-9</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/257885359">257885359</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=BPBQAAAAMAAJ">BPBQAAAAMAAJ
 * </a></div></li>
 * <li><div><span id="cite_WSS1991TTSASSQSUGER" /><a
 * href="http://www.cs.colostate.edu/~whitley/">L. Darrell Whitley</a>,
 * Timothy Starkweather, and&nbsp;Daniel Shaner: <span
 * style="font-weight:bold">&ldquo;The Travelling Salesman and Sequence
 * Scheduling: Quality Solutions using Genetic Edge
 * Recombination,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Handbook of Genetic
 * Algorithms</span>, pages 350&ndash;372, Lawrence Davis, editor, VNR
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
 * <li><div><span id="cite_MGSK1988EAICO" /><a
 * href="http://www.muehlenbein.org/">Heinz M&#252;hlenbein</a>, Martina
 * Gorges-Schleuter, and&nbsp;Ottmar Kr&#228;mer: <span
 * style="font-weight:bold">&ldquo;Evolution Algorithms in Combinatorial
 * Optimization,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Parallel Computing
 * &#8210; Systems &amp; Applications</span> 7(1):65&ndash;85,
 * April&nbsp;1988; published by Essex, UK: Elsevier Science Publishers
 * B.V.. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1016/0167-8191(88)90098-1">
 * 10.1016/0167-8191(88)90098-1</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/01678191">0167-8191</a></div></li>
 * <li><div><span id="cite_D1985AAATED" />Lawrence Davis: <span
 * style="font-weight:bold">&ldquo;Applying Adaptive Algorithms to
 * Epistatic Domains,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the 9th
 * International Joint Conference on Artificial Intelligence
 * (IJCIA'85)</span>, 1, August&nbsp;1985, Los Angeles, CA, USA, pages
 * 162&ndash;164, Aravind K. Joshi, editor, San Francisco, CA, USA: Morgan
 * Kaufmann Publishers Inc.. <div>link: [<a
 * href="http://ijcai.org/Past%20Proceedings/IJCAI-85-VOL1/PDF/029.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_GLJ1985ALATT" /><a
 * href="http://www.davidegoldberg.com/de-goldberg.html">David Edward
 * Goldberg</a> and&nbsp;Robert Lingle, Jr.: <span
 * style="font-weight:bold">&ldquo;Alleles, Loci and the TSP,&rdquo;</span>
 * in <span style="font-style:italic;font-family:cursive;">Proceedings of
 * the 1st International Conference on Genetic Algorithms and their
 * Applications (ICGA'85)</span>, June&nbsp;24&ndash;26, 1985, Pittsburgh,
 * PA, USA: Carnegy Mellon University (CMU), pages 154&ndash;159, <a
 * href="http://www.biostat.pitt.edu/directory/bios/grefenstette.asp">John
 * J. Grefenstette</a>, editor, Hillsdale, NJ, USA: Lawrence Erlbaum
 * Associates. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0805804269">0-8058-0426-9</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780805804263">978-0-8058
 * -0426-3</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/19702892">19702892</a></div></li>
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
 * </ol>
 */
package org.logisticPlanning.tsp.solving.operators.permutation.recombination;

