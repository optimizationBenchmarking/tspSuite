package org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch;

/**
 * <p>
 * The internal move class holds all information necessary for describing
 * and carrying out a search move.
 * </p>
 * <p>
 * Each direct search move supported by the
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch.MultiNeighborhoodSearch
 * MultiNeighborhoodSearch} is defined by four key values: A move modifies
 * a sub-sequence of a tour which ranges from (and including) an index
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch._Move#m_i
 * i} to (and including) an index
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch._Move#m_j
 * j}.
 * </p>
 * <p>
 * Applying the move will change the total tour length by
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch._Move#m_delta
 * delta} length units. In other words
 * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#evaluate(int[]) f(x')} = {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#evaluate(int[]) f(x)} + {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch._Move#m_delta delta}</code>
 * , if {@code x} is the original candidate solution and {@code x'} is
 * obtained by applying the move to {@code x}. Notice that
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch._Move#m_delta
 * delta} is always negative for any move that the
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch.MultiNeighborhoodSearch
 * MultiNeighborhoodSearch} will find and apply, because otherwise the tour
 * length would increase.
 * </p>
 * <p>
 * Finally, the type of move to be applied is characterized by the
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch._Move#m_type
 * type}-variable of the
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch._Move
 * move record}. The following move types are supported:
 * </p>
 * <ol>
 * <li>
 * <p>
 * The
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch._Move#TYPE_REVERSE
 * reverse} operation reverses a sub-sequence of nodes in between two
 * indices&nbsp;[<a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">1</a>, <a href="#cite_H1975GA"
 * style="font-weight:bold">2</a>, <a href="#cite_G1987IPSKIGA"
 * style="font-weight:bold">3</a>, <a href="#cite_JAMGS1989OBSAAEEPIGP"
 * style="font-weight:bold">4</a>, <a href="#cite_KGV1983SA"
 * style="font-weight:bold">5</a>, <a href="#cite_ABCC1999FTITTFT"
 * style="font-weight:bold">6</a>]. The structure of this operator is
 * further similar to the more complex inver-over operator from&nbsp;[<a
 * href="#cite_GM1998IOOFTT" style="font-weight:bold">7</a>, <a
 * href="#cite_YWL2010AFEAFTSP" style="font-weight:bold">8</a>].
 * <p>
 * <p>
 * It can be noticed that the inversion operation is basically the same as
 * the edge cutting and reconstruction used in <a
 * href="https://en.wikipedia.org/wiki/2-opt">2-opt</a>&nbsp;[<a
 * href="#cite_C1958AMFSTSP" style="font-weight:bold">9</a>, <a
 * href="#cite_F1956TTSP" style="font-weight:bold">10</a>, <a
 * href="#cite_ABCC1999FTITTFT" style="font-weight:bold">6</a>]: Assume a
 * solution to a 8-node TSP that looks as follows:
 * {@code (1-2-3-4-5-6-7-8)}, i.e., that is the
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.creation.PermutationCreateCanonical
 * canonical permutation}. In a possible 2-opt move, we may consider to
 * removing the edge connecting nodes {@code 2} and {@code 3} as well as
 * the edge connecting nodes {@code 6} and {@code 7} (
 * {@code (1-2-/-3-4-5-6-/-7-8)} ). After removing the two edges, there are
 * basically two segments (due to the circular nature of TSPs):
 * {@code (7-8-1-2)} and {@code 3-4-5-6)}. We cannot connect node {@code 3}
 * to node {@code 6} or node {@code 2} to node {@code 7}, since then we
 * would have two cyclic tours (instead of one). We can also not reconnect
 * node {@code 2} to node {@code 3}, since this would lead to the starting
 * situation. Hence, the only way to reconnect the two segments is by
 * connecting node {@code 2} to node {@code 6} and connecting node
 * {@code 3} to node {@code 7}. The resulting new tour is then
 * {@code (1-2-6-5-4-3-7-8)}. This, however, is exactly the same tour that
 * we would get when inverting the segment between nodes {@code 2} and
 * {@code 7} in the original tour:
 * {@code (1-2-|-3-4-5-6-|-7-8) = (1-2-6-5-4-3-7-8)}. In other words,
 * inversion is the same as a 2-opt move.
 * </p>
 * </li>
 * <li>
 * <p>
 * The
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch._Move#TYPE_ROT_LEFT
 * rotate left} operation rotates a sub-sequence one step to the left. This
 * operator is equivalent to the insertion mutation operator defined
 * in&nbsp;[<a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">1</a>, <a href="#cite_F1988AEATTTSP"
 * style="font-weight:bold">11</a>, <a href="#cite_M1996GADSEP"
 * style="font-weight:bold">12</a>, <a href="#cite_S1991SOUGA"
 * style="font-weight:bold">13</a>].
 * </p>
 * <p>
 * Left rotation will break three edges and them replace by three new ones,
 * it is thus a special case of a {@code 3-opt} move.
 * </p>
 * </li>
 * <li>
 * <p>
 * The
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch._Move#TYPE_ROT_RIGHT
 * rotate right} operation rotates a sub-sequence of the solution one step
 * to the right. This operator is equivalent to the insertion mutation
 * operator defined in&nbsp;[<a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">1</a>, <a href="#cite_F1988AEATTTSP"
 * style="font-weight:bold">11</a>, <a href="#cite_M1996GADSEP"
 * style="font-weight:bold">12</a>, <a href="#cite_S1991SOUGA"
 * style="font-weight:bold">13</a>].
 * </p>
 * <p>
 * Right rotation will break three edges and them replace by three new
 * ones, it is thus a special case of a {@code 3-opt} move.
 * </p>
 * </li>
 * <li>
 * <p>
 * The
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch._Move#TYPE_SWAP
 * swap} operation swaps two elements in a permutation&nbsp;[<a
 * href="#cite_LKMUB1999GAFTTSPARORAO" style="font-weight:bold">1</a>, <a
 * href="#cite_OSH1987ASOPCOOTTSP" style="font-weight:bold">14</a>, <a
 * href="#cite_M1996GADSEP" style="font-weight:bold">12</a>, <a
 * href="#cite_B1990TMTS" style="font-weight:bold">15</a>, <a
 * href="#cite_AAM1991HCOBSDEAPTAFTTSP" style="font-weight:bold">16</a>, <a
 * href="#cite_S1991SOUGA" style="font-weight:bold">13</a>]. This is maybe
 * the most common and simplest search operator that we can apply to a
 * permutation.
 * </p>
 * <p>
 * Swapping will break four edges and replace them by four new ones, it is
 * thus a special case of a {@code 4-opt} move.
 * </p>
 * </li>
 * </ol>
 * <p>
 * By setting the move comparison method, the
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch.MultiNeighborhoodSearch#localSearch(org.logisticPlanning.tsp.solving.Individual,org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction)
 * local search} algorithm in the
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch.MultiNeighborhoodSearch
 * MultiNeighborhoodSearch} can either behave like a
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch.EMoveComparator#BEST_MOVE_FIRST
 * best-first} search or a bit like a
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch.EMoveComparator#TYPE_FIRST_THEN_BEST_MOVE
 * Variable Neighborhood Search} (VNS)&nbsp;[<a href="#cite_HM2001VNSPAA"
 * style="font-weight:bold">17</a>, <a href="#cite_HMP2008VNSMAA"
 * style="font-weight:bold">18</a>, <a href="#cite_HMP2010VNSMAA"
 * style="font-weight:bold">19</a>, <a href="#cite_HMBP2010VNS"
 * style="font-weight:bold">20</a>], although this will only apply for the
 * way the move queue is processed and must not be mistaken with a true
 * VNS.
 * <h2>References</h2>
 * <ol>
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
 * <li><div><span id="cite_H1975GA" /><a
 * href="https://en.wikipedia.org/wiki/John_Henry_Holland">John Henry
 * Holland</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Adaptation in
 * Natural and Artificial Systems: An Introductory Analysis with
 * Applications to Biology, Control, and Artificial
 * Intelligence,&rdquo;</span> 1975, Ann Arbor, MI, USA: University of
 * Michigan Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0472084607">0-472-08460-7</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780472084609">978-0-472-
 * 08460-9</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/74078988">74078988</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/1531617">1531617</a> and&nbsp;<a
 * href="https://www.worldcat.org/oclc/266623839">266623839</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=JE5RAAAAMAAJ">JE5RAAAAMAAJ</a>,
 * <a
 * href="http://books.google.com/books?id=YE5RAAAAMAAJ">YE5RAAAAMAAJ</a>,
 * <a
 * href="http://books.google.com/books?id=Qk5RAAAAMAAJ">Qk5RAAAAMAAJ</a>,
 * <a
 * href="http://books.google.com/books?id=vk5RAAAAMAAJ">vk5RAAAAMAAJ</a>,
 * and&nbsp;<a
 * href="http://books.google.com/books?id=JOv3AQAACAAJ">JOv3AQAACAAJ</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=075982293">075982293</a></div></li>
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
 * <li><div><span id="cite_JAMGS1989OBSAAEEPIGP" /><a
 * href="https://en.wikipedia.org/wiki/David_S._Johnson">David Stifler
 * Johnson</a>, <a href="http://faculty.washington.edu/aragon/">Cecilia R.
 * Aragon</a>, <a
 * href="https://www.amherst.edu/people/facstaff/lamcgeoch">Lyle A.
 * McGeoch</a>, and&nbsp;Catherine A. Schevon: <span
 * style="font-weight:bold">&ldquo;Optimization by Simulated Annealing: An
 * Experimental Evaluation. Part I, Graph Partitioning,&rdquo;</span> in
 * <span style="font-style:italic;font-family:cursive;">Operations Research
 * (Oper. Res.)</span> 37(6), November&ndash;December 1989; published by
 * Linthicum, ML, USA: Institute for Operations Research and the Management
 * Sciences (INFORMS) and&nbsp;Cambridge, MA, USA: HighWire Press (Stanford
 * University). LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/66099702">66099702</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1287/opre.37.6.865"
 * >10.1287/opre.37.6.865</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/2394608">2394608</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/0030364X">0030-364X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15265463">1526-5463</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=OPREAI"
 * >OPREAI</a></div></li>
 * <li><div><span id="cite_KGV1983SA" /><a
 * href="http://www.cs.huji.ac.il/~kirk/">Scott Kirkpatrick</a>, Charles
 * Daniel Gelatt, Jr., and&nbsp;<a
 * href="http://www.linkedin.com/in/mariovecchi">Mario P. Vecchi</a>: <span
 * style="font-weight:bold">&ldquo;Optimization by Simulated
 * Annealing,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Science Magazine</span>
 * 220(4598):671&ndash;680, May&nbsp;13, 1983; published by Washington, DC,
 * USA: American Association for the Advancement of Science (AAAS)
 * and&nbsp;Cambridge, MA, USA: HighWire Press (Stanford University).
 * doi:&nbsp;<a
 * href="http://dx.doi.org/10.1126/science.220.4598.671">10.1126/science
 * .220.4598.671</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/00368075">0036-8075</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/10959203">1095-9203</a>;
 * PubMed:&nbsp;<a
 * href="https://www.ncbi.nlm.nih.gov/pubmed/17813860">17813860</a>.
 * <div>links: [<a
 * href="http://fezzik.ucd.ie/msc/cscs/ga/kirkpatrick83optimization.pdf"
 * >1</a>], [<a
 * href="http://citeseer.ist.psu.edu/kirkpatrick83optimization.html"
 * >2</a>], and&nbsp;[<a href=
 * "http://home.gwu.edu/~stroud/classics/KirkpatrickGelattVecchi83.pdf"
 * >3</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.18.4175"
 * >10.1.1.18.4175</a></div></div></li>
 * <li><div><span id="cite_ABCC1999FTITTFT" /><a
 * href="http://www.research.att.com/people/Applegate_David_L">David Lee
 * Applegate</a>, <a href="http://www.caam.rice.edu/~bixby/">Robert E.
 * Bixby</a>, <a href="http://users.encs.concordia.ca/~chvatal/">Va&#353;ek
 * Chv&#225;tal</a>, and&nbsp;<a
 * href="http://www2.isye.gatech.edu/~wcook/">William John Cook</a>: <span
 * style="font-weight:bold">&ldquo;Finding Tours in the TSP: Finding
 * Tours,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;99885, 1999; published by Bonn, North Rhine-Westphalia,
 * Germany: Rheinische Friedrich-Wilhelms-Universit&#228;t Bonn. Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=CR6DNAAACAAJ">CR6DNAAACAAJ</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=gtgGaAEACAAJ">gtgGaAEACAAJ</a>.
 * <div>link: [<a
 * href="http://www.tsp.gatech.edu/methods/papers/lk_report.ps">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.43.2594">10.1
 * .1.43 .2594</a></div></div></li>
 * <li><div><span id="cite_GM1998IOOFTT" />Guo Tao and&nbsp;<a
 * href="http://cs.adelaide.edu.au/~zbyszek/">Zbigniew Michalewicz</a>:
 * <span style="font-weight:bold">&ldquo;Inver-over Operator for the
 * TSP,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the 5th
 * International Conference on Parallel Problem Solving from Nature (PPSN
 * V)</span>, September&nbsp;27&ndash;30, 1998, Amsterdam, The Netherlands,
 * pages 803&ndash;812, <a href="http://www.cs.vu.nl/~gusz/">&#193;goston
 * E. Eiben</a> aka. Gusz/Guszti, <a
 * href="http://www.liacs.nl/~baeck/contact.htm">Thomas B&#228;ck</a>, <a
 * href="http://www.lri.fr/~marc/">Marc Schoenauer</a>, and&nbsp;<a
 * href="https://en.wikipedia.org/wiki/Hans-Paul_Schwefel">Hans-Paul
 * Schwefel</a>, editors, volume 1498/1998 of Lecture Notes in Computer
 * Science (LNCS), Berlin, Germany: Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540650784">3-540-65078-4</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540650782">978-3-540-
 * 65078-2</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/BFb0056922">10.1007/BFb0056922</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/39739752">39739752</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=HLeU_1TkwTsC">HLeU_1TkwTsC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a>. <div>links:
 * [<a href="http://cs.adelaide.edu.au/~zbyszek/Papers/p44.pdf">1</a>]
 * and&nbsp;[<a href="http://lisas.de/~hakan/file/p44.pdf">2</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.94.7376"
 * >10.1.1.94.7376</a></div></div></li>
 * <li><div><span id="cite_YWL2010AFEAFTSP" />Xue-song Yan, Qing-hua Wu,
 * and&nbsp;Hui Li: <span style="font-weight:bold">&ldquo;A Fast
 * Evolutionary Algorithm for Traveling Salesman Problem,&rdquo;</span> in
 * <span style="font-style:italic;font-family:cursive;">Traveling Salesman
 * Problem, Theory and Applications</span>, chapter 72&ndash;80, pages
 * 72&ndash;80, <a
 * href="http://mrl.cs.vsb.cz/people/davendra/index.html">Donald David
 * Davendra</a>, editor, Winchester, Hampshire, UK: InTech. ISBN:&nbsp;<a
 * href
 * ="https://www.worldcat.org/isbn/9789533074269">978-953-307-426-9</a>.
 * <div>link: [<a href=
 * "http://cdn.intechopen.com/pdfs/12404/InTech-A_fast_evolutionary_algorithm_for_traveling_salesman_problem.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_C1958AMFSTSP" />G. A. Croes: <span
 * style="font-weight:bold">&ldquo;A Method for Solving Traveling-Salesman
 * Problems,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Operations Research
 * (Oper. Res.)</span> 6(6):791&ndash;812, November&ndash;December 1958;
 * published by Linthicum, ML, USA: Institute for Operations Research and
 * the Management Sciences (INFORMS) and&nbsp;Cambridge, MA, USA: HighWire
 * Press (Stanford University). LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/66099702">66099702</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1287/opre.6.6.791">10.1287/opre.6.6.791</a>;
 * JSTOR stable:&nbsp;<a
 * href="http://www.jstor.org/stable/167074">167074</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/2394608">2394608</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/0030364X">0030-364X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15265463">1526-5463</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=OPREAI"
 * >OPREAI</a></div></li>
 * <li><div><span id="cite_F1956TTSP" />Merrill M. Flood: <span
 * style="font-weight:bold">&ldquo;The Traveling-Salesman
 * Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Operations Research
 * (Oper. Res.)</span> 4(1):61&ndash;75, February&nbsp;1956; published by
 * Linthicum, ML, USA: Institute for Operations Research and the Management
 * Sciences (INFORMS) and&nbsp;Cambridge, MA, USA: HighWire Press (Stanford
 * University). LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/66099702">66099702</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1287/opre.4.1.61">10.1287/opre.4.1.61</a>;
 * OCLC:&nbsp;<a href="https://www.worldcat.org/oclc/2394608">2394608</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/0030364X">0030-364X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15265463">1526-5463</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=OPREAI"
 * >OPREAI</a></div></li>
 * <li><div><span id="cite_F1988AEATTTSP" /><a
 * href="http://www.natural-selection.com/people_dfogel.html">David B.
 * Fogel</a>: <span style="font-weight:bold">&ldquo;An Evolutionary
 * Approach to the Traveling Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Biological
 * Cybernetics</span> 60(2):139&ndash;144, December&nbsp;1988; published by
 * Berlin/Heidelberg: Springer-Verlag. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/BF00202901">10.1007/BF00202901</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03401200">0340-1200</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/14320770">1432-0770</a>. <div>link:
 * [<a
 * href="http://users.on.net/~jivlain/papers/4%20Fogel.pdf">1</a>]</div>
 * </div></li>
 * <li><div><span id="cite_M1996GADSEP" /><a
 * href="http://cs.adelaide.edu.au/~zbyszek/">Zbigniew Michalewicz</a>:
 * <span style="font-style:italic;font-family:cursive;">&ldquo;Genetic
 * Algorithms + Data Structures = Evolution Programs,&rdquo;</span> 1996,
 * Berlin, Germany: Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540606769">3-540-60676-9</a>, <a
 * href="https://www.worldcat.org/isbn/3540580905">3-540-58090-5</a>, <a
 * href
 * ="https://www.worldcat.org/isbn/9783540606765">978-3-540-60676-5</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783642082337">978-3-642-
 * 08233-7</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=vlhLAobsK68C">vlhLAobsK68C</a>;
 * ASIN:&nbsp;<a
 * href="http://www.amazon.com/dp/3540606769">3540606769</a></div></li>
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
 * <li><div><span id="cite_B1990TMTS" /><a
 * href="http://www.cs.mun.ca/~banzhaf/">Wolfgang Banzhaf</a>: <span
 * style="font-weight:bold">&ldquo;The &#8220;Molecular&#8221; Traveling
 * Salesman,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Biological
 * Cybernetics</span> 64(1):7&ndash;14, November&nbsp;1990; published by
 * Berlin/Heidelberg: Springer-Verlag. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/BF00203625">10.1007/BF00203625</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03401200">0340-1200</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/14320770">1432-0770</a>. <div>link:
 * [<a href="https://web.cs.mun.ca/~banzhaf/papers/MolTravelSalesman.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_AAM1991HCOBSDEAPTAFTTSP" />Balamurali Krishna
 * Ambati, Jayakrishna Ambati, and&nbsp;Mazen Moein Mokhtar: <span
 * style="font-weight:bold">&ldquo;Heuristic Combinatorial Optimization by
 * Simulated Darwinian Evolution: A Polynomial Time Algorithm for the
 * Traveling Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Biological
 * Cybernetics</span> 65(1):31&ndash;35, May&nbsp;1991; published by
 * Berlin/Heidelberg: Springer-Verlag. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/BF00197287">10.1007/BF00197287</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03401200">0340-1200</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/14320770">1432-0770</a></div></li>
 * <li><div><span id="cite_HM2001VNSPAA" /><a
 * href="http://www.hec.ca/profs/pierre.hansen.html">Pierre Hansen</a>
 * and&nbsp;<a href=
 * "http://www.brunel.ac.uk/siscm/mathematical-sciences/people-in-maths/academic-staff/drnenadmladenovic"
 * >Nenad Mladenovi&#263;</a>: <span
 * style="font-weight:bold">&ldquo;Variable Neighborhood Search: Principles
 * and Applications,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">European Journal of
 * Operational Research (EJOR)</span> 130(3):449&ndash;467, May&nbsp;1,
 * 2001; published by Amsterdam, The Netherlands: Elsevier Science
 * Publishers B.V. and&nbsp;Amsterdam, The Netherlands: North-Holland
 * Scientific Publishers Ltd.. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1016/S0377-2217(00)00100-4">10.1016
 * /S0377-2217(00)00100-4</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03772217">0377-2217</a></div></li>
 * <li><div><span id="cite_HMP2008VNSMAA" /><a
 * href="http://www.hec.ca/profs/pierre.hansen.html">Pierre Hansen</a>, <a
 * href=
 * "http://www.brunel.ac.uk/siscm/mathematical-sciences/people-in-maths/academic-staff/drnenadmladenovic"
 * >Nenad Mladenovi&#263;</a>, and&nbsp;<a
 * href="http://jamoreno.webs.ull.es/">Jos&#233; Andr&#233;s Moreno
 * P&#233;rez</a>: <span style="font-weight:bold">&ldquo;Variable
 * Neighbourhood Search: Methods and Applications,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">4OR</span>
 * 6(4):319&ndash;360, December&nbsp;1, 2008; published by Berlin, Germany:
 * Springer-Verlag GmbH. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/s10288-008-0089-1">10.1007/s10288
 * -008-0089-1</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/16194500">1619-4500</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16142411">1614-2411</a></div></li>
 * <li><div><span id="cite_HMP2010VNSMAA" /><a
 * href="http://www.hec.ca/profs/pierre.hansen.html">Pierre Hansen</a>, <a
 * href=
 * "http://www.brunel.ac.uk/siscm/mathematical-sciences/people-in-maths/academic-staff/drnenadmladenovic"
 * >Nenad Mladenovi&#263;</a>, and&nbsp;<a
 * href="http://jamoreno.webs.ull.es/">Jos&#233; Andr&#233;s Moreno
 * P&#233;rez</a>: <span style="font-weight:bold">&ldquo;Variable
 * Neighbourhood Search: Methods and Applications,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Annals of Operations
 * Research</span> 175(1):367&ndash;407, March&nbsp;1, 2010; published by
 * Dordrecht, Netherlands: Springer Netherlands and&nbsp;Amsterdam, The
 * Netherlands: J. C. Baltzer AG, Science Publishers. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/s10479-009-0657-6"
 * >10.1007/s10479-009-0657-6</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/02545330">0254-5330</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15729338">1572-9338</a></div></li>
 * <li><div><span id="cite_HMBP2010VNS" /><a
 * href="http://www.hec.ca/profs/pierre.hansen.html">Pierre Hansen</a>, <a
 * href=
 * "http://www.brunel.ac.uk/siscm/mathematical-sciences/people-in-maths/academic-staff/drnenadmladenovic"
 * >Nenad Mladenovi&#263;</a>, <a
 * href="http://www.hec.ca/en/profs/jack.brimberg.html">Jack Brimberg</a>,
 * and&nbsp;<a href="http://jamoreno.webs.ull.es/">Jos&#233; Andr&#233;s
 * Moreno P&#233;rez</a>: <span style="font-weight:bold">&ldquo;Variable
 * Neighborhood Search,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Handbook of
 * Metaheuristics</span>, chapter 61&ndash;86, pages 61&ndash;86, <a
 * href="http://www.crt.umontreal.ca/~michelg/">Michel Gendrau</a>
 * and&nbsp;<a href="http://www.iro.umontreal.ca/~potvin/">Jean-Yves
 * Potvin</a>, editors, volume 146 of International Series in Operations
 * Research &amp; Management Science, Norwell, MA, USA: Kluwer Academic
 * Publishers, Dordrecht, Netherlands: Springer Netherlands,
 * and&nbsp;Boston, MA, USA: Springer US. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1461426901">1461426901</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781441916655">978-1-4419
 * -1665-5</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/978-1-4419-1665-5_3">10.1007/978-
 * 1-4419-1665-5_3</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=xMTS5dyDhwMC">xMTS5dyDhwMC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/08848289">0884-8289</a>;
 * ASIN:&nbsp;<a
 * href="http://www.amazon.com/dp/1461426901">1461426901</a></div></li>
 * </ol>
 */
final class _Move {

  /** the reverse move type */
  private static final int TYPE_REVERSE = 0;
  /** the rotate move type */
  private static final int TYPE_ROT_LEFT = 1;
  /** the rotate right type */
  private static final int TYPE_ROT_RIGHT = 2;

  /** the swap move type */
  private static final int TYPE_SWAP = 3;

  /** the move's names */
  private static final String[] NAMES = new String[] {
    "reverse(", "rot_left(", "rot_right(", //$NON-NLS-1$//$NON-NLS-3$//$NON-NLS-2$
    "swap(" //$NON-NLS-1$
  };

  /** the move's type */
  int m_type;

  /** the first index */
  int m_i;

  /** the second index */
  int m_j;

  /** the delta */
  int m_delta;

  /** the first additional distance */
  private int m_a;

  /** the second additional distance */
  private int m_b;

  /** the third additional distance */
  private int m_c;

  /** the fourth additional distance */
  private int m_d;

  /** the next move in the list */
  _Move m_next;

  /** create the move */
  _Move() {
    super();
  }

  /**
   * Initialize this move as a &quot;rotate left&quot; move. You can find a
   * similar move described in
   * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Rotate_Left}
   * .
   *
   * @param i
   *          the i
   * @param j
   *          the j
   * @param delta
   *          the delta
   * @param a
   *          the distance between the current elements at index
   *          {@code i-1} and {@code i+1}
   * @param b
   *          the distance between the current elements at index {@code i}
   *          and {@code j}
   * @param c
   *          the distance between the current elements at index {@code i}
   *          and {@code j+1}
   */
  final void init_rotate_left(final int i, final int j, final int delta,
      final int a, final int b, final int c) {
    this.m_type = _Move.TYPE_ROT_LEFT;
    this.m_i = i;
    this.m_j = j;
    this.m_delta = delta;
    this.m_a = a;
    this.m_b = b;
    this.m_c = c;
  }

  /**
   * Initialize this move as a &quot;rotate right&quot; move. You can find
   * a similar move described in
   * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Rotate_Right}
   * .
   *
   * @param i
   *          the i
   * @param j
   *          the j
   * @param delta
   *          the delta
   * @param a
   *          the distance between the current elements at index
   *          {@code i-1} and {@code i+1}
   * @param b
   *          the distance between the current elements at index {@code i}
   *          and {@code j}
   * @param c
   *          the distance between the current elements at index {@code i}
   *          and {@code j+1}
   */
  final void init_rotate_right(final int i, final int j, final int delta,
      final int a, final int b, final int c) {
    this.m_type = _Move.TYPE_ROT_RIGHT;
    this.m_i = i;
    this.m_j = j;
    this.m_delta = delta;
    this.m_a = a;
    this.m_b = b;
    this.m_c = c;
  }

  /**
   * Initialize this move as a &quot;swap&quot; move. You can find a
   * similar move described in
   * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Swap}
   *
   * @param i
   *          the i
   * @param j
   *          the j
   * @param delta
   *          the delta
   * @param D_im1_j
   *          the distance between the current elements at index
   *          {@code i-1} and {@code j}
   * @param D_ip1_j
   *          the distance between the current elements at index
   *          {@code i+1} and {@code j}
   * @param D_i_jm1
   *          the distance between the current elements at index {@code i}
   *          and {@code j-1}
   * @param D_i_jp1
   *          the distance between the current elements at index {@code i}
   *          and {@code j+1}
   */
  final void init_swap(final int i, final int j, final int delta,
      final int D_im1_j, final int D_ip1_j, final int D_i_jm1,
      final int D_i_jp1) {
    this.m_type = _Move.TYPE_SWAP;
    this.m_i = i;
    this.m_j = j;
    this.m_delta = delta;
    this.m_a = D_i_jm1;
    this.m_b = D_i_jp1;
    this.m_c = D_im1_j;
    this.m_d = D_ip1_j;
  }

  /**
   * Initialize this move as a &quot;reverse&quot; move. You can find a
   * similar move described in
   * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Reverse}
   *
   * @param i
   *          the i
   * @param j
   *          the j
   * @param delta
   *          the delta
   * @param D_im1_j
   *          the distance between the current elements at index
   *          {@code i-1} and {@code j}
   * @param D_i_jp1
   *          the distance between the current elements at index {@code i}
   *          and {@code j+1}
   */
  final void init_reverse(final int i, final int j, final int delta,
      final int D_im1_j, final int D_i_jp1) {
    this.m_type = _Move.TYPE_REVERSE;
    this.m_i = i;
    this.m_j = j;
    this.m_delta = delta;
    this.m_b = D_im1_j;
    this.m_a = D_i_jp1;
  }

  /**
   * <p>
   * This method has one &quot;external&quot; and one &quot;internal&quot;
   * purpose. For the outside, it applies this move and updates the
   * candidate solution and distance list.
   * </p>
   * <p>
   * Internally, it changes the meaning of the member variables
   * {@link #m_a} to {@link #m_d}. Until before this method, they hold
   * additional distance information that we use to update the distance
   * list. Now we change them to hold the indices that would cause
   * collisions with this move.
   * </p>
   * <p>
   * After this method was called, the queue of moves must be updated by
   * calling the {@link #checkDeleteMove(_Move, int)} methods of all
   * enqeued moves.
   * </p>
   *
   * @param n
   *          the number of nodes in the tsp instance
   * @param sol
   *          the solution array
   * @param dist
   *          the distance array
   */
  final void apply(final int n, final int[] sol, final int[] dist) {
    final int i, j, im1;
    int t, m, ii, jj;

    // perform the move

    i = this.m_i;
    j = this.m_j;
    im1 = (((i + n) - 1) % n);

    // Depending on the type of our move, we have different things to do
    switch (this.m_type) {

      case TYPE_REVERSE: {
        // In a reverse move, we turn around the tour segment in between
        // (and
        // including) indices i and j.
        for (ii = i, jj = j; ii < jj; ii++, jj--) {
          t = sol[ii];
          sol[ii] = sol[jj];
          sol[jj] = t;
        }

        for (ii = i, jj = (j - 1); ii < jj; ii++, jj--) {
          t = dist[ii];
          dist[ii] = dist[jj];
          dist[jj] = t;
        }

        dist[im1] = this.m_b;
        dist[j] = this.m_a;

        break;
      }

      case TYPE_ROT_LEFT: {
        // In a rotate left move, we rotate the segment in between (and
        // including) i and j one step to the left. The element at index i
        // would be shifted outside of the range, but since we do a
        // _rotate_
        // it
        // re-appears on index j. The element at index j has moved to j-1.
        t = sol[i];
        m = (j - i);
        System.arraycopy(sol, i + 1, sol, i, m);
        sol[j] = t;

        System.arraycopy(dist, i + 1, dist, i, m - 1);

        dist[im1] = this.m_a;
        dist[j - 1] = this.m_b;
        dist[j] = this.m_c;

        this.m_c = (i + 1); // The rotate left move has one more forbidden
        // coordinate: i+1
        break;
      }

      case TYPE_ROT_RIGHT: {
        // In a rotate right move, we rotate the segment in between (and
        // including) i and j one step to the right. The element at index j
        // would be shifted outside of the range, but since we do a
        // _rotate_
        // it
        // re-appears on index i. The element at index i has moved to i+1.
        t = sol[j];
        m = (j - i);
        System.arraycopy(sol, i, sol, i + 1, m);
        sol[i] = t;

        System.arraycopy(dist, i, dist, i + 1, m - 1);

        dist[im1] = this.m_c;
        dist[i] = this.m_b;
        dist[j] = this.m_a;

        this.m_c = (j - 1);// The rotate left move has one more forbidden
        // coordinate: j-1
        break;
      }

      default: {// TYPE_SWAP
        // In a swap move, we swap the elements at indices i and j.

        t = sol[i];
        sol[i] = sol[j];
        sol[j] = t;

        dist[im1] = this.m_c;
        dist[i] = this.m_d;

        t = (j - 1);
        dist[t] = this.m_a;
        dist[j] = this.m_b;

        this.m_c = t; // the swap move does not allow touching the
        this.m_d = (i + 1); // coordinates j-1 and i+1
      }
    }

    this.m_a = im1; // any move forbids touching the coordinates i-1
    this.m_b = ((j + 1) % n); // and j+1
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    return (((((((_Move.NAMES[this.m_type]) + //
        this.m_i) + ',') + this.m_j) + ')') + '=') + this.m_delta);
  }

  /**
   * <p>
   * Check whether a given move should be deleted if {@code move} was
   * performed, or whether it can still be used. In the latter case, this
   * function will also perform any necessary amendments.
   * </p>
   * <p>
   * Any move that collides with a performed move, i.e., whose proposed
   * tour change has become impossible since [@code move} was performed,
   * will return {@code true} here, which indicates that it should be
   * deleted. Moves that do not intersect or may otherwise be adapted to
   * still be valid will instead return {@code false}, which indicates that
   * they should remain in the move queue and can still be used.
   * </p>
   * <p>
   * Notice that this method requires that
   * {@link #apply(int, int[], int[])} was called first for {@code move},
   * as it accesses the internal variables of {@code move} holding
   * collision information.
   * </p>
   *
   * @param move
   *          the move
   * @param n
   *          the number of nodes
   * @return {@code true} if the move must be deleted
   */
  final boolean checkDeleteMove(final _Move move, final int n) {
    final int i, j, im1, jp1, type, moveType;
    int t;

    // First, we check if there are any collisions.
    // Each move leads to changes of the distances between a few, certain
    // nodes.
    // Thus, each move defines a set of nodes for which at least one of the
    // two
    // distances related to them change.
    // If the two sets of such nodes for two moves overlap, a collision
    // occurs.
    // If this move has a collision with another move, it cannot be
    // performed.
    // So here we check this.

    i = this.m_i;
    if (_Move.checkCollision(i, move)) {
      // Our i coordinate must not hit any forbidden coordinate of the
      // performed move, since the element at this coordinate would
      // definitely
      // change if we do our move.
      return true;
    }

    im1 = (((i - 1) + n) % n);
    if (_Move.checkCollision(im1, move)) {
      // Since our move will always change the element at its i
      // coordinate, the
      // distance between this node and the (definitely unchanged) node
      // before
      // will change, too
      return true;
    }

    j = this.m_j;
    if (_Move.checkCollision(j, move)) {
      // Our j coordinate must not hit any forbidden coordinate of the
      // performed move, since the element at this coordinate would
      // definitely
      // change if we do our move.
      return true;
    }

    jp1 = ((j + 1) % n);
    if (_Move.checkCollision(jp1, move)) {
      // Since our move will always change the element at its j
      // coordinate, the
      // distance between this node and the (definitely unchanged) node
      // thereafter will change, too
      return true;
    }

    type = this.m_type;

    if ((type == _Move.TYPE_ROT_LEFT) || (type == _Move.TYPE_SWAP)) {
      // For left rotation or a swap move, also block coordinate i+1
      if (_Move.checkCollision((i + 1), move)) {
        return true;
      }
    }

    if ((type == _Move.TYPE_ROT_RIGHT) || (type == _Move.TYPE_SWAP)) {
      // For right rotation or a swap move, also block coordinate k-1
      if (_Move.checkCollision((j - 1), move)) {
        return true;
      }
    }

    // OK, there was no collision. Now we need to check whether we can
    // transform the current move if it can be transformed such that it can
    // still be carried out.

    moveType = move.m_type;
    if (moveType == _Move.TYPE_SWAP) {
      // If the move was a swap move and there was no collision, then
      // everything is OK and we still can carry it out.
      return false;
    }

    if ((i >= move.m_i) && (i <= move.m_j)) {
      // Our i coordinate lies within the window of the performed move.

      // Depending on the type of the performed move, we need to take
      // different
      // actions.
      switch (moveType) {

        case TYPE_REVERSE: {
          // The performed move was a reversal
          if (type == _Move.TYPE_SWAP) {
            // If we are a swap move and our i coordinate lies in the
            // range of
            // the performed reversal, we need to reverse it as well and
            // swap
            // its two corresponding distance changes
            t = this.m_c;
            this.m_c = this.m_d;
            this.m_d = t;

            this.m_i = (move.m_j - (i - move.m_i));// reverse coordinate
            break;
          }

          // Our move is not swap, so it is either reverse, rol, or rot
          if (j >= move.m_j) {
            // This means that only fully contained intersecting moves
            // are
            // allowed, i.e., if our i coordinate is in the move range,
            // our j
            // coordinate must be so too. Otherwise, we cannot perform
            // our move
            // as the nodes next to its i-end have changed.
            return true;
          }

          // Our move is fully contained in the performed reversal, so we
          // reverse its coordinates: Notice reversed i is larger than
          // reversed
          // j and vice versa, so the two switch places.
          this.m_j = (move.m_j - (i - move.m_i));
          this.m_i = (move.m_j - (j - move.m_i));

          // Perform actions depending on the type of our move
          switch (type) {
            case TYPE_REVERSE: {
              // For reverse moves, the distances at the ends change
              t = this.m_a;
              this.m_a = this.m_b;
              this.m_b = t;
              return false;// We are done here, the move can be preserved.
            }
            case TYPE_ROT_LEFT: {
              // rol moves become ror moves
              this.m_type = _Move.TYPE_ROT_RIGHT;
              return false;// We are done here, the move can be preserved.
            }
            default /* case TYPE_ROT_RIGHT */: {
              // and ror moves become rol moves
              this.m_type = _Move.TYPE_ROT_LEFT;
              return false; // We are done here, the move can be
              // preserved.
            }
          }
        }

        case TYPE_ROT_LEFT: {
          // Our i coordinate intersects with the i coordinate of the
          // performed
          // rotate left move, but does not produce a collision. We
          // therefore
          // simply shift it one step to the left too, regardless what
          // move we
          // are planning to do.
          this.m_i = im1;
          break;
        }
        default /* case TYPE_ROT_RIGHT */: {
          // Our i coordinate intersects with the i coordinate of the
          // performed
          // rotate right move, but does not produce a collision. We
          // therefore
          // simply shift it one step to the right too, regardless what
          // move we
          // are planning to do.
          this.m_i = (i + 1);
          break;
        }
      }
    }
    // We are finished checking our move's i coordinate versus the range of
    // the
    // performed move. We now need to check our move's j coordinate.

    if ((j >= move.m_i) && (j <= move.m_j)) {
      // Our move's j coordinate intersects with the move's range, but
      // does not
      // produce a collision.
      switch (moveType) {

        case TYPE_REVERSE: {
          // The performed move was a reversal.

          if (type == _Move.TYPE_SWAP) {
            // If our move is a swap move, we have to reverse its j
            // coordinate
            // and swap the associated distances.
            t = this.m_a;
            this.m_a = this.m_b;
            this.m_b = t;
            this.m_j = (move.m_j - (j - move.m_i));
            break;
          }

          // We have an overlap of either reverse, rol, ror with a
          // performed
          // reverse -- this is not allowed. When checking our i
          // coordinate, we
          // already have signaled "delete" (true) for any non-swap move
          // intersecting with the performed reversal "from the left" AND
          // signaled false / performed the necessary changes for any
          // fully
          // included non-swap move. Thus, coming here means that our move
          // intersects with the performed reversal "from the right",
          // i.e., its
          // j coordinate is in the reversed range but its i coordinate is
          // not.
          // This is not allowed, as the nodes neighboring the j
          // coordinate
          // have changed. Thus we signal "delete".
          return true;
        }

        case TYPE_ROT_LEFT: {
          // If the performed move was a rotate-left, we, too, shift the j
          // coordinate one step to the left
          this.m_j = (j - 1);
          break;
        }

        default/* case TYPE_ROT_RIGHT */: {
          // If the performed move was a rotate-right, we, too, shift the
          // j
          // coordinate one step to the right.
          this.m_j = jp1;
          break;
        }
      }
    }

    // If we come here, the j coordinate did not intersect with the
    // performed
    // move or any necessary amendments of the i and j coordinates have
    // been
    // performed successful. We can signal "keep" (false) - if the move is
    // not
    // redundant. However, we are lucky: Because of the forbidden nodes of
    // each
    // move, the two indices cannot come too close to each other by
    // augmenting
    // a move. Thus, a move that was not redundant cannot become redundant.

    return false;
  }

  /**
   * Check for a collision: this method checks whether a given value
   * {@code v} occurs in the forbidden values stored in the record
   * {@code move} (according to {@link #apply(int, int[], int[])}). It will
   * return {@code true} if such a collision was found and {@code false}
   * otherwise.
   *
   * @param v
   *          the value
   * @param move
   *          the move
   * @return {@code true} on collision
   */
  @SuppressWarnings({ "fallthrough", "incomplete-switch" })
  private static final boolean checkCollision(final int v, final _Move move) {
    switch (move.m_type) {
      case TYPE_SWAP: {
        // swap moves use all four variables
        if (v == move.m_d) {
          return true;
        }
        // fall through to rotate
      }
      case TYPE_ROT_LEFT:
      case TYPE_ROT_RIGHT: {
        // rotate moves three variables
        if (v == move.m_c) {
          return true;
        }
        // fall through
      }
    }

    // all moves use at least four variables
    return ((v == move.m_i) || (v == move.m_j) || (v == move.m_a) || (v == move.m_b));
  }
}
