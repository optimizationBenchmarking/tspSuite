/**
 * </p> Heuristic approaches to the Traveling Salesman Problem (TSP). </p>
 * <h2 id="heuristicAlgorithmsList">
 * {@linkplain org.logisticPlanning.tsp.solving.algorithms.heuristics
 * Implemented Heuristics}</h2>
 * <p>
 * Heuristics are usually simple procedures that build one solution in a
 * constructive or iterative manner. The following algorithms from this
 * family have been implemented:
 * </p>
 * <ol>
 * <li>The
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.nearestNeighbor.NearestNeighborHeuristic
 * Nearest-Neighbor Heuristic}&nbsp;[<a href="#cite_RSL1977AAOSHFTTSP"
 * style="font-weight:bold">1</a>, <a href="#cite_RSSL2009AAOSHFTTSP"
 * style="font-weight:bold">2</a>, <a href="#cite_JMB1997TTSPACSILO"
 * style="font-weight:bold">3</a>, <a href="#cite_JMG2004EAOHFTS"
 * style="font-weight:bold">4</a>] starts at a given node and builds a tour
 * by iteratively adding the nearest node to it.</li>
 * <li>The
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.doubleEndedNearestNeighbor.DoubleEndedNearestNeighborHeuristic
 * Double-Ended Nearest-Neighbor Heuristic}&nbsp;[<a
 * href="#cite_JMB1997TTSPACSILO" style="font-weight:bold">3</a>, <a
 * href="#cite_JMG2004EAOHFTS" style="font-weight:bold">4</a>] starts a
 * given node and then iteratively builds a tour by adding the nearest
 * nodes to it. Different from the nearest-neighbor heuristic, it checks
 * both ends of the current tour for nearest neighbors.</li>
 * <li>The
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.edgeGreedy.EdgeGreedyHeuristic
 * Edge-Greedy Heuristic}&nbsp;[<a href="#cite_JMB1997TTSPACSILO"
 * style="font-weight:bold">3</a>, <a href="#cite_JMG2004EAOHFTS"
 * style="font-weight:bold">4</a>] constructs a tour by adding the cheapest
 * edge (that does not violate any validity constraint).</li>
 * <li>The
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.mst.MSTHeuristic
 * (Double) Minimum Spanning Tree Heuristic}&nbsp;[<a
 * href="#cite_J1930OJPMZDPOB" style="font-weight:bold">5</a>, <a
 * href="#cite_P1957SCNASG" style="font-weight:bold">6</a>] first computes
 * a <a href="https://en.wikipedia.org/wiki/Minimum_spanning_tree">minimum
 * spanning tree</a> (MST)&nbsp;[<a href="#cite_J2004MSTSPT"
 * style="font-weight:bold">7</a>] and then traces it from the root note to
 * form a tour by skipping any already visited nodes.</li>
 * <li>The
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic
 * Savings Heuristic}&nbsp;[<a href="#cite_CW1964SOVFACDTANODP"
 * style="font-weight:bold">8</a>, <a href="#cite_JMB1997TTSPACSILO"
 * style="font-weight:bold">3</a>, <a href="#cite_JMG2004EAOHFTS"
 * style="font-weight:bold">4</a>] starts by choosing a depot. For each of
 * the remaining {@code n-1} nodes, a tour from the depot to the node and
 * directly back is assumed. Iteratively, the cheapest way to combine tours
 * is sought and applied, until only one tour &ndash; the solution to the
 * TSP &dash; remains.</li>
 * </ol>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_RSL1977AAOSHFTTSP" />Daniel J. Rosenkrantz,
 * Richard E. Stearns, and&nbsp;Philip M. Lewis II: <span
 * style="font-weight:bold">&ldquo;An Analysis of Several Heuristics for
 * the Traveling Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">SIAM Journal on
 * Computing</span> 6(3):563&ndash;581, 1977; published by Philadelphia,
 * PA, USA: Society for Industrial and Applied Mathematics (SIAM).
 * doi:&nbsp;<a
 * href="http://dx.doi.org/10.1137/0206041">10.1137/0206041</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/00975397">0097-5397</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/10957111">1095-7111</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=SMJCAT"
 * >SMJCAT</a></div></li>
 * <li><div><span id="cite_RSSL2009AAOSHFTTSP" />Daniel J. Rosenkrantz,
 * Richard E. Stearns, and&nbsp;Philip M. Lewis II: <span
 * style="font-weight:bold">&ldquo;An Analysis of Several Heuristics for
 * the Traveling Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Fundamental Problems in
 * Computing: Essays in Honor of Professor Daniel J. Rosenkrantz</span>,
 * pages 45&ndash;69, S.S. Ravi and&nbsp;Sandeep K. Shukla, editors,
 * Dordrecht, Netherlands: Springer Netherlands. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781402096877"
 * >978-1-4020-9687-7</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/978-1-4020-9688-4_3"
 * >10.1007/978-1-4020-9688-4_3</a></div></li>
 * <li><div><span id="cite_JMB1997TTSPACSILO" /><a
 * href="https://en.wikipedia.org/wiki/David_S._Johnson">David Stifler
 * Johnson</a> and&nbsp;<a
 * href="https://www.amherst.edu/people/facstaff/lamcgeoch">Lyle A.
 * McGeoch</a>: <span style="font-weight:bold">&ldquo;The Traveling
 * Salesman Problem: A Case Study in Local Optimization,&rdquo;</span> in
 * <span style="font-style:italic;font-family:cursive;">Local Search in
 * Combinatorial Optimization</span>, pages 215&ndash;310, Emile H. L.
 * Aarts and&nbsp;<a
 * href="https://en.wikipedia.org/wiki/Jan_Karel_Lenstra">Jan Karel
 * Lenstra</a>, editors, Estimation, Simulation, and Control &#8211;
 * Wiley-Interscience Series in Discrete Mathematics and Optimization,
 * Princeton, NJ, USA: Princeton University Press and&nbsp;Chichester, West
 * Sussex, UK: Wiley Interscience. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0691115222">0691115222</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780691115221">9780691115221</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/45733970">45733970</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=NWghN9G7q9MC">NWghN9G7q9MC</a>.
 * <div>link: [<a
 * href="http://www.research.att.com/~dsj/papers/TSPchapter.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_JMG2004EAOHFTS" /><a
 * href="https://en.wikipedia.org/wiki/David_S._Johnson">David Stifler
 * Johnson</a> and&nbsp;<a
 * href="https://www.amherst.edu/people/facstaff/lamcgeoch">Lyle A.
 * McGeoch</a>: <span style="font-weight:bold">&ldquo;Experimental Analysis
 * of Heuristics for the STSP,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">The Traveling Salesman
 * Problem and its Variations</span>, chapter 369&ndash;443, pages
 * 369&ndash;443, <a href=
 * "http://www.rhul.ac.uk/computerscience/staffdirectory/ggutin/home.aspx"
 * >Gregory Z. Gutin</a> and&nbsp;<a
 * href="http://www.sfu.ca/~apunnen/">Abraham P. Punnen</a>, editors,
 * volume 12 of Combinatorial Optimization, Norwell, MA, USA: Kluwer
 * Academic Publishers. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0306482134">0-306-48213-4</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781402006647">978-1-4020
 * -0664-7</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/0-306-48213-4_9"
 * >10.1007/0-306-48213-4_9</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/55085594">55085594</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=TRYkPg_Xf20C">TRYkPg_Xf20C</a>.
 * <div>link: [<a
 * href="http://www2.research.att.com/~dsj/papers/stspchap.pdf">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.15.9438">
 * 10.1 .1.15.9438</a></div></div></li>
 * <li><div><span id="cite_J1930OJPMZDPOB" /><a
 * href="https://en.wikipedia.org/wiki/Vojt%C4%9Bch_Jarn%C3%ADk"
 * >Vojt&#283;ch Jarn&#237;k</a>: <span style="font-weight:bold">&ldquo;O
 * Jist&#233;m Probl&#233;mu Minim&#225;ln&#237;m: (Z Dopisu Panu O.
 * Bor&#367;skovi),&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Pr&#225;ce Moravsk&#233;
 * P&#345;&#237;rodov&#283;deck&#233; Spole&#269;nosti: Acta Societatis
 * Scientiarum Naturalium Moravia</span> 6:57&ndash;63, 1930; published by
 * Brno, Czechoslovakia: Moravsk&#225; P&#345;&#237;rodov&#277;deck&#225;
 * Spolec&#780;&#328;ost. OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/762243334">762243334</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=GOc3HAAACAAJ">GOc3HAAACAAJ</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=kCMpAQAAMAAJ">kCMpAQAAMAAJ</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/12110248">1211-0248</a></div></li>
 * <li><div><span id="cite_P1957SCNASG" /><a
 * href="https://en.wikipedia.org/wiki/Robert_C._Prim">Robert Clay
 * Prim</a>: <span style="font-weight:bold">&ldquo;Shortest Connection
 * Networks and Some Generalizations,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Bell System Technical
 * Journal</span> 36(6):1389&ndash;1401, November&nbsp;1957; published by
 * Berkeley Heights, NJ, USA: Bell Laboratories. ASIN:&nbsp;<a
 * href="http://www.amazon.com/dp/B004VJC9KA">B004VJC9KA</a>. <div>link:
 * [<a href=
 * "www.alcatel-lucent.com/bstj/vol36-1957/articles/bstj36-6-1389.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_J2004MSTSPT" /><a
 * href="http://www.me.utexas.edu/~orie/Jensen.html">Paul A. Jensen</a>:
 * <span style="font-style:italic;font-family:cursive;">&ldquo;Minimal
 * Spanning Tree/Shortest Path Tree,&rdquo;</span> (Website),
 * 2004&ndash;September&nbsp;11, 2010, Austin, TX, USA: University of
 * Texas, Mechanical Engineering Department. <div>link: [<a href=
 * "http://www.me.utexas.edu/~jensen/ORMM/methods/unit/network/subunits/mst_spt/index.html"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_CW1964SOVFACDTANODP" />G. Clarke and&nbsp;J. W.
 * Wright: <span style="font-weight:bold">&ldquo;Scheduling of Vehicles
 * from a Central Depot to a Number of Delivery Points,&rdquo;</span> in
 * <span style="font-style:italic;font-family:cursive;">Operations Research
 * (Oper. Res.)</span> 12(4):568&ndash;581, July&ndash;August 1964;
 * published by Linthicum, ML, USA: Institute for Operations Research and
 * the Management Sciences (INFORMS) and&nbsp;Cambridge, MA, USA: HighWire
 * Press (Stanford University). LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/66099702">66099702</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1287/opre.12.4.568"
 * >10.1287/opre.12.4.568</a>; JSTOR stable:&nbsp;<a
 * href="http://www.jstor.org/stable/167703">167703</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/2394608">2394608</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/0030364X">0030-364X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15265463">1526-5463</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=OPREAI"
 * >OPREAI</a>. <div>link: [<a href=
 * "http://read.pudn.com/downloads160/doc/fileformat/721736/Scheduling%20of%20vehicles%20from%20a%20central%20depot%20to%20a%20number%20of%20delivery%20points.pdf"
 * >1</a>]</div></div></li>
 * </ol>
 */
package org.logisticPlanning.tsp.solving.algorithms.heuristics;

