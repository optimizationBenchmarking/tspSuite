package org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch;

import java.io.PrintStream;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.TSPLocalSearchAlgorithm;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;

/**
 * <p>
 * This local search algorithm improves a given tour by using multiple
 * neighborhoods at once. It tries to improve the tour until no directly
 * improving move is possible anymore and then will apply a special move to
 * escape from the local optimum.
 * </p>
 * <p>
 * The algorithm presented here works in the same way as the
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.PermutationRNS
 * PermutationRNSOpt}, i.e., it performs a local search and, if no
 * improving moves are possible anymore, it performs a local shuffle. The
 * difference to the
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.PermutationRNS
 * PermutationRNS} is that instead of conducting a Variable Neighborhood
 * Search (VNS)&nbsp;[<a href="#cite_HM2001VNSPAA"
 * style="font-weight:bold">1</a>, <a href="#cite_HMP2008VNSMAA"
 * style="font-weight:bold">2</a>, <a href="#cite_HMP2010VNSMAA"
 * style="font-weight:bold">3</a>, <a href="#cite_HMBP2010VNS"
 * style="font-weight:bold">4</a>], it searches the all neighborhoods of a
 * set of search operators simultaneously. This may be more runtime
 * efficient.
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
 * The reverse operation reverses a sub-sequence of nodes in between two
 * indices&nbsp;[<a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">5</a>, <a href="#cite_H1975GA"
 * style="font-weight:bold">6</a>, <a href="#cite_G1987IPSKIGA"
 * style="font-weight:bold">7</a>, <a href="#cite_JAMGS1989OBSAAEEPIGP"
 * style="font-weight:bold">8</a>, <a href="#cite_KGV1983SA"
 * style="font-weight:bold">9</a>, <a href="#cite_ABCC1999FTITTFT"
 * style="font-weight:bold">10</a>]. The structure of this operator is
 * further similar to the more complex inver-over operator from&nbsp;[<a
 * href="#cite_GM1998IOOFTT" style="font-weight:bold">11</a>, <a
 * href="#cite_YWL2010AFEAFTSP" style="font-weight:bold">12</a>].
 * <p>
 * <p>
 * It can be noticed that the inversion operation is basically the same as
 * the edge cutting and reconstruction used in <a
 * href="https://en.wikipedia.org/wiki/2-opt">2-opt</a>&nbsp;[<a
 * href="#cite_C1958AMFSTSP" style="font-weight:bold">13</a>, <a
 * href="#cite_F1956TTSP" style="font-weight:bold">14</a>, <a
 * href="#cite_ABCC1999FTITTFT" style="font-weight:bold">10</a>]: Assume a
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
 * The rotate left operation rotates a sub-sequence one step to the left.
 * This operator is equivalent to the insertion mutation operator defined
 * in&nbsp;[<a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">5</a>, <a href="#cite_F1988AEATTTSP"
 * style="font-weight:bold">15</a>, <a href="#cite_M1996GADSEP"
 * style="font-weight:bold">16</a>, <a href="#cite_S1991SOUGA"
 * style="font-weight:bold">17</a>].
 * </p>
 * <p>
 * Left rotation will break three edges and them replace by three new ones,
 * it is thus a special case of a {@code 3-opt} move.
 * </p>
 * </li>
 * <li>
 * <p>
 * The rotate right operation rotates a sub-sequence of the solution one
 * step to the right. This operator is equivalent to the insertion mutation
 * operator defined in&nbsp;[<a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">5</a>, <a href="#cite_F1988AEATTTSP"
 * style="font-weight:bold">15</a>, <a href="#cite_M1996GADSEP"
 * style="font-weight:bold">16</a>, <a href="#cite_S1991SOUGA"
 * style="font-weight:bold">17</a>].
 * </p>
 * <p>
 * Right rotation will break three edges and them replace by three new
 * ones, it is thus a special case of a {@code 3-opt} move.
 * </p>
 * </li>
 * <li>
 * <p>
 * The swap operation swaps two elements in a permutation&nbsp;[<a
 * href="#cite_LKMUB1999GAFTTSPARORAO" style="font-weight:bold">5</a>, <a
 * href="#cite_OSH1987ASOPCOOTTSP" style="font-weight:bold">18</a>, <a
 * href="#cite_M1996GADSEP" style="font-weight:bold">16</a>, <a
 * href="#cite_B1990TMTS" style="font-weight:bold">19</a>, <a
 * href="#cite_AAM1991HCOBSDEAPTAFTTSP" style="font-weight:bold">20</a>, <a
 * href="#cite_S1991SOUGA" style="font-weight:bold">17</a>]. This is maybe
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
 * style="font-weight:bold">1</a>, <a href="#cite_HMP2008VNSMAA"
 * style="font-weight:bold">2</a>, <a href="#cite_HMP2010VNSMAA"
 * style="font-weight:bold">3</a>, <a href="#cite_HMBP2010VNS"
 * style="font-weight:bold">4</a>], although this will only apply for the
 * way the move queue is processed and must not be mistaken with a true
 * VNS.
 * <h2>References</h2>
 * <ol>
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
 * </ol>
 */
public class MultiNeighborhoodSearch extends
    TSPLocalSearchAlgorithm<int[]> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the maximum length of the move queue: {@value} */
  public static final String PARAM_MAX_ALLOCATIONS = "maxMoveQueueLen"; //$NON-NLS-1$

  /** the move order: {@value} */
  public static final String PARAM_MOVE_ORDER = "moveOrder";//$NON-NLS-1$

  /** the default maximum number of moves: {@value} */
  private static final int DEFAULT_MAX_ALLOCATIONS = 1048576;

  /** the default move comparator */
  private static final EMoveComparator DEFAULT_MOVE_COMPARATOR = EMoveComparator.BEST_MOVE_FIRST;

  /**
   * the maximum number of moves to allocate
   * 
   * @serial serializable field
   */
  private int m_maxMoveAllocations;

  /**
   * the move order
   * 
   * @serial serializable field
   */
  private EMoveComparator m_cmp;

  /**
   * The distances between the nodes and their successors in the current
   * permutations. The contract is:
   * {@code m_distances[i]=this.m_f.dist(this.m_solution[i], this.m_solution[(i+1)%n])}
   */
  private transient int[] m_distances;

  /** the objective function to use */
  private transient ObjectiveFunction m_f;

  /** the queue of moves */
  private transient _Move m_queue;

  /** old swap moves that can be re-used */
  private transient _Move m_old;

  /** the number of allocated moves */
  private transient int m_moveAllocations;

  /** instantiate */
  public MultiNeighborhoodSearch() {
    super("Multiple-Neighborhood Search");//$NON-NLS-1$ 
    this.m_cmp = MultiNeighborhoodSearch.DEFAULT_MOVE_COMPARATOR;
    this.m_maxMoveAllocations = MultiNeighborhoodSearch.DEFAULT_MAX_ALLOCATIONS;
  }

  /** {@inheritDoc} */
  @Override
  public void localSearch(final Individual<int[]> srcdst,
      final ObjectiveFunction f) {
    final int n;
    final int[] dist;
    final EMoveComparator cmp;
    boolean lastWasIncomplete;
    int changed_range_i, changed_range_j;
    _Move move, predMove, cur, pred, best, predBest, next;

    n = f.n();

    // clear the queue
    this.disposeQueue();

    // compute the distances and initialize the internal distance list
    srcdst.tourLength = MultiNeighborhoodSearch.distanceAndLengthInit(f,
        n, srcdst.solution, this.m_distances);

    // initialize local variables
    changed_range_i = 0;
    changed_range_j = n;
    dist = this.m_distances;
    cmp = this.m_cmp;

    // fill the move queue, remember if this hit the allocation limit
    lastWasIncomplete = this.fillMoveQueue(0, n, srcdst.solution,
        this.m_distances);

    outer: for (;;) {
      move = this.m_queue;// move=best and first move in queue
      if (move == null) {// ok, queue is empty
        break outer;// quit
      }
      // fillMoveQueue ensures that the first move in the queue is best
      // move.
      predMove = null;// so there is no move before
      // predMove is always the last move before move or null if move is
      // the
      // first move in the queue

      // [changed_range_i,changed_range_j] hold the range that includes
      // all
      // changes that we made so far
      // This is useful when filling the queue after consuming all moves
      // since
      // new moves that do not intersect with
      // [changed_range_i,changed_range_j]
      // do not need to be checked:
      // They were checked in at least one prior local search / fill queue
      // run
      // and were not improving. As nothing outside
      // [changed_range_i,changed_range_j] has changed, these moves'
      // deltas
      // are
      // still the same (and therefore still >0).
      changed_range_i = Integer.MAX_VALUE;
      changed_range_j = Integer.MIN_VALUE;

      // In the inner loop, we process the queue: We always take the best
      // move
      // out of the queue and perform it. Performing a move may lead to
      // the
      // invalidation of other moves, which are purged from the queue.
      // Then
      // the
      // next best move is taken from the queue and executed. If the queue
      // is
      // empty, this loop ends and the outer loop is executed again to
      // fill
      // the
      // queue.
      inner: do {

        // delete the move from the queue
        if (predMove == null) {
          this.m_queue = move.m_next;
        } else {
          predMove.m_next = move.m_next;
        }

        // apply the move: change the solution and distance list
        move.apply(n, srcdst.solution, dist);
        srcdst.tourLength += move.m_delta; // modify the total tour
        // length
        // accordingly
        // we now have a new and better candidate solution: register
        f.registerFE(srcdst.solution, srcdst.tourLength);

        move.m_next = this.m_old; // dispose the move: hang it into the
        this.m_old = move;// queue for re-use
        if (f.shouldTerminate()) {// ok, we should terminate right now
          break outer;
        }

        // make [changed_range_i,changed_range_j] include the area
        // touched
        // by
        // the performed move: only this range is interesting when
        // filling
        // the
        // queue again at the next "outer" iteration
        changed_range_i = Math.min(changed_range_i, move.m_i);
        changed_range_j = Math.max(changed_range_j, move.m_j);

        cur = this.m_queue; // cur = beginning of queue
        if (cur == null) {
          break inner;// queue is empty: we need to fill the move list
          // again
        }

        // The following loop has two purposes:
        // 1. we need to delete all moves that intersect with "move" in
        // such a
        // way that their delta would have changed / they have been
        // invalidated
        // by "move"
        // 2. find the next best move to apply (and its successor)
        best = predBest = pred = null;
        while (cur != null) {
          // pred is the last non-deleted move before cur
          // best is the best move that was not deleted
          // predBest is the last non-deleted move before best
          next = cur.m_next; // iterate through the move queue

          // check if we need to delete the move
          if (cur.checkDeleteMove(move, n)) {
            // ok, the move has been invalidated and must be deleted
            // so we remove it from the queue
            if (pred == null) {
              this.m_queue = next;
            } else {
              pred.m_next = next;
            }
            // pred remains the same, as it was the last non-deleted
            // move

            // dispose of cur: hang it into the re-use queue
            cur.m_next = this.m_old;
            this.m_old = cur;
          } else {
            // good, the move can be kept

            if ((best == null) || (cmp.compare(cur, best) < 0)) {
              // oh, the move is the best move found so far!
              best = cur;
              predBest = pred;
            }

            // the move was not deleted, so it becomes the last
            // non-deleted
            // move
            pred = cur;
          }

          cur = next;// cur becomes cur.m_next
        }

        move = best; // the next move is the best move found in the
        // queue
        predMove = predBest; // and predMove the last non-deleted move
        // before
      } while (move != null);

      // ok, we have emptied the move queue. Now it is time to fill in
      // again.

      // lastWasIncomplete tells us whether all possible moves were
      // checked.
      // If it is false, we only need to check
      // [changed_range_i,changed_range_j]. Otherwise, we need to check
      // [0,n]
      lastWasIncomplete = this.fillMoveQueue(//
          (lastWasIncomplete ? 0 : changed_range_i),//
          (lastWasIncomplete ? n : changed_range_j), //
          srcdst.solution,//
          dist);
    }

    this.disposeQueue(); // dispose whatever move is still in the queue
  }

  /**
   * set the {@link EMoveComparator move comparator}
   * 
   * @param cmp
   *          the {@link EMoveComparator move comparator} to used
   */
  public final void setMoveComparator(final EMoveComparator cmp) {
    this.m_cmp = ((cmp != null) ? cmp
        : MultiNeighborhoodSearch.DEFAULT_MOVE_COMPARATOR);
  }

  /**
   * get the {@link EMoveComparator move comparator}
   * 
   * @return the {@link EMoveComparator move comparator} used
   */
  public final EMoveComparator getMoveComparator() {
    return this.m_cmp;
  }

  /**
   * Set the maximum number of move records to allocate and hold in memory
   * 
   * @param max
   *          the maximum number of move records to allocate and hold in
   *          memory
   */
  public final void setMaxMoveAllocations(final int max) {
    this.m_maxMoveAllocations = max;
  }

  /**
   * Get the maximum number of move records to allocate and hold in memory
   * 
   * @return max the maximum number of move records to allocate and hold in
   *         memory
   */
  public final int getMaxMoveAllocations() {
    return this.m_maxMoveAllocations;
  }

  /**
   * compute the total length of the solution and initialize the distance
   * array
   * 
   * @param f
   *          the objective function
   * @param n
   *          the number of nodes
   * @param sol
   *          the solution array
   * @param dist
   *          the distance array
   * @return the length of the tour
   */
  private static final long distanceAndLengthInit(
      final ObjectiveFunction f, final int n, final int[] sol,
      final int[] dist) {
    int curP, oldP;
    long total;
    int i, j;

    curP = sol[0];
    total = 0l;
    for (i = n; (--i) >= 0;) {
      oldP = curP;
      curP = sol[i];
      dist[i] = j = f.distance(curP, oldP);
      total += j;
    }

    return total;
  }

  /**
   * Return a new move record. This method first tries to recycle old moves
   * and will allocate a new move if the old queue is empty. However, if
   * the maximum number of move allocations has been reached, {@code null}
   * will be returned.
   * 
   * @return the new move, or {@code null} if the maximum number of move
   *         allocations has been exhausted
   */
  private final _Move allocate() {
    final _Move m;
    final int i;

    m = this.m_old;
    if (m != null) {
      this.m_old = m.m_next;
      return m;
    }

    i = this.m_moveAllocations;
    if (i < this.m_maxMoveAllocations) {
      this.m_moveAllocations = (i + 1);
      return new _Move();
    }

    return null;
  }

  /** dispose all moves in the move queue */
  private final void disposeQueue() {
    _Move cur, next;

    cur = this.m_queue; // cur = beginning of queue

    if (cur == null) {// queue is empty, we can leave directly
      return;
    }

    // moves are left in queue: dispose them
    dispose: for (;;) {// find the last move in queue
      next = cur.m_next;
      if (next == null) {
        break dispose;
      }
      cur = next;
    }

    // cur now is the last move in the move queue m_queue is the first
    cur.m_next = this.m_old; //
    this.m_old = this.m_queue;// put m_queue at beginning
    this.m_queue = null; // ensure that the queue is really empty
  }

  /**
   * enqueue a move
   * 
   * @param move
   *          the move to enqueue
   */
  private final void enqueue(final _Move move) {
    final _Move q;

    q = this.m_queue;
    if ((q == null) || ((this.m_cmp.compare(move, q) < 0))) {
      // move = new best: put at head of queue
      move.m_next = q;
      this.m_queue = move;
    } else {// move is not best: put at 2nd place, regardless how good it
      // is
      move.m_next = q.m_next;
      q.m_next = move;
    }
  }

  /**
   * <p>
   * Fill the queue of search moves that can improve the current solution.
   * </p>
   * <p>
   * This procedure makes use of a set of assumptions and pre-conditions.
   * </p>
   * <ol>
   * <li>The array {@code sol} contains the current candidate solution.</li>
   * <li>The {@code i}<sup>th</sup> element of the array
   * {@link #m_distances} contains the distance between the {@code i}
   * <sup>th</sup> element of {@code sol} and the {@code (i+1)%n}
   * <sup>th</sup> element. In other words, the element at index
   * {@code n-1} holds the distance of detour from the last node back to
   * the start.</li>
   * <li>The parameters {@code lastChangeStart} and {@code lastChangeEnd}
   * hold the inclusive indices where the last change to the candidate
   * solution took place.</li>
   * </ol>
   * <p>
   * When filling the queue, we only consider moves that at least touch (or
   * overlap, or are included in) the range
   * {@code [lastChangeStart,lastChangeEnd]}. As the rest of the
   * permutation was not changed, other moves would have already been
   * considered before and thus do not need to tested again.
   * </p>
   * <p>
   * This function will place the best move found at the head of the queue.
   * </p>
   * <p>
   * This function returns {@code true} if not all possible moves could be
   * enqueued due to the allocation limit {@link #m_maxMoveAllocations}.
   * This means that the next call to this method cannot rely on exhaustive
   * move testing, i.e., should always invoke it with the full index range.
   * If {@code false} is returned, all potential moves have been enqueued.
   * Then, only moves need to be investigated which intersect with the
   * changed region in the next call.
   * </p>
   * 
   * @param lastChangeStart
   *          the first index of the interesting range
   * @param lastChangeEnd
   *          the last index of the interesting range
   * @param sol
   *          the solution array
   * @param dists
   *          the distance array
   * @return {@code true} if not all possible moves could be checked due to
   *         the memory allocation limit {@link #m_maxMoveAllocations},
   *         {@code false} if all possible moves were checked
   */
  private final boolean fillMoveQueue(final int lastChangeStart,
      final int lastChangeEnd, final int[] sol, final int[] dists) {
    final ObjectiveFunction f;
    final int n, nm1;
    final int begin, end;
    int i, j, jm1, im1, delta;
    int sol_im1, sol_i, sol_ip1, sol_jm1, sol_j, sol_jp1;
    int D_im1_i, D_i_ip1, D_jm1_j, D_j_jp1, D_im1_j, D_i_jp1, D_im1_ip1, D_i_j, D_jm1_jp1, D_ip1_j, D_i_jm1;
    _Move move;

    f = this.m_f;
    n = f.n();
    nm1 = (n - 1);

    // Any move intersecting with the range [begin,end] is interesting.
    // The -1/+1 stem from the fact that when changing range [2,3], also
    // the
    // distance of the node at index 1 (to index 2) has changed.
    begin = (lastChangeStart - 1);
    end = (lastChangeEnd + 1);

    // iterate over all indices 0<j<n
    j = (n - 1);
    D_jm1_j = dists[j];
    sol_j = sol[0];
    sol_jm1 = sol[j];
    for (; j > 0; j = jm1) {
      jm1 = (j - 1); // as j>0, this can never wrap/become <0

      // initialize variables pi_j-1, pi_j, pi_j+1
      sol_jp1 = sol_j;
      sol_j = sol_jm1;
      sol_jm1 = sol[jm1];

      // load distances d(j-1,j), d(j,j+1)
      D_j_jp1 = D_jm1_j;
      D_jm1_j = dists[jm1];

      // iterate over all indices 0<=i<j
      D_im1_i = dists[j];

      sol_i = sol[j];
      i = (j - 1);
      sol_im1 = sol[i];
      inner: for (; i >= 0; i--) {

        im1 = ((i + nm1) % n);

        // initialize variables pi_i-1, pi_i, pi_i+1
        sol_ip1 = sol_i;
        sol_i = sol_im1;
        sol_im1 = sol[im1];

        // load distances d(i-1,i), d(i,i+1)
        D_i_ip1 = D_im1_i;
        D_im1_i = dists[im1];

        // check if the interesting range is intersected
        if ((i > end) || (j < begin)) {
          continue inner;
        }

        // the inversion specific distance requirements
        D_im1_j = f.distance(sol_im1, sol_j);
        D_i_jp1 = f.distance(sol_i, sol_jp1);

        if ((i <= 0) && (j >= nm1)) {
          continue inner; // no operation works at full-range
        }

        // check the reversal move
        delta = ((D_im1_j - D_im1_i) + (D_i_jp1 - D_j_jp1));

        if (delta < 0) { // ok, the move is promising: enqueue
          move = this.allocate(); // allocate the move
          if (move == null) {
            return true;// we hit the allocation limit, force exit
          }

          move.init_reverse(i, j, delta, D_im1_j, D_i_jp1);// init the
          // move
          this.enqueue(move);
        }

        if (j == (i + 1)) {
          // for swap, rotate left, and rotate right, only index
          // tuples
          // with
          // j!=((i+1)%n) are interesting
          continue inner;
        }

        // check the rotate left move
        D_im1_ip1 = f.distance(sol_im1, sol_ip1);
        D_i_j = f.distance(sol_i, sol_j);

        delta = ((D_im1_ip1 - D_im1_i) + //
            (D_i_j - D_i_ip1) + //
        (D_i_jp1 - D_j_jp1));

        if (delta < 0) {// move is promising:enqueue
          move = this.allocate(); // allocate the move
          if (move == null) {
            return true;// we hit the allocation limit, force exit
          }

          move.init_rotate_left(i, j, delta, D_im1_ip1, D_i_j, D_i_jp1);// init
          this.enqueue(move);
        }

        // check the rotate right move
        D_jm1_jp1 = f.distance(sol_jm1, sol_jp1);

        delta = ((D_im1_j - D_im1_i) + //
            (D_i_j - D_j_jp1) + //
        (D_jm1_jp1 - D_jm1_j));

        if (delta < 0) {// move is promising:enqueue
          move = this.allocate(); // allocate the move
          if (move == null) {
            return true;// we hit the allocation limit, force exit
          }

          move.init_rotate_right(i, j, delta, D_jm1_jp1, D_i_j, D_im1_j);// init
          this.enqueue(move);
        }

        if (j == ((i + 2) % n)) {
          // for swap, j!=((i+2)%n) are interesting
          continue inner;
        }

        // check the swap move
        D_i_jm1 = f.distance(sol_i, sol_jm1);
        D_ip1_j = f.distance(sol_ip1, sol_j);

        delta = ((D_im1_j - D_im1_i) + //
            (D_ip1_j - D_i_ip1) + //
            (D_i_jm1 - D_jm1_j) + //
        (D_i_jp1 - D_j_jp1));

        if (delta < 0) {// move is promising:enqueue
          move = this.allocate(); // allocate the move
          if (move == null) {
            return true;// we hit the allocation limit, force exit
          }

          move.init_swap(i, j, delta, D_im1_j, D_ip1_j, D_i_jm1, D_i_jp1);// init
          this.enqueue(move);
        }
      }
    }

    return false;
  }

  /** {@inheritDoc} */
  @Override
  public final MultiNeighborhoodSearch clone() {
    MultiNeighborhoodSearch m;

    m = ((MultiNeighborhoodSearch) (super.clone()));

    m.__clear();

    return m;
  }

  /** clear this manager */
  private final void __clear() {
    this.m_distances = null;
    this.m_f = null;
    this.m_old = null;
    this.m_queue = null;
    this.m_moveAllocations = 0;

  }

  /** {@inheritDoc} */
  @Override
  public final void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable.printKey(MultiNeighborhoodSearch.PARAM_MOVE_ORDER, ps);
    Configurable.printlnObject(this.m_cmp, ps);

    Configurable.printKey(MultiNeighborhoodSearch.PARAM_MAX_ALLOCATIONS,
        ps);
    ps.println(this.m_maxMoveAllocations);
  }

  /** {@inheritDoc} */
  @Override
  public final void printParameters(final PrintStream ps) {
    super.printParameters(ps);

    Configurable.printKey(MultiNeighborhoodSearch.PARAM_MOVE_ORDER, ps);
    ps.println(//
    "the order in which moves are enqueued and performed"); //$NON-NLS-1$

    Configurable.printKey(MultiNeighborhoodSearch.PARAM_MAX_ALLOCATIONS,
        ps);
    ps.println(//
    "the maximum move queue length (to prevent out-of-memory errors)"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final void configure(final Configuration config) {
    super.configure(config);

    this.m_cmp = config.getConstant(
        MultiNeighborhoodSearch.PARAM_MOVE_ORDER, EMoveComparator.class,
        EMoveComparator.class, this.m_cmp);

    this.m_maxMoveAllocations = config.getInt(
        MultiNeighborhoodSearch.PARAM_MAX_ALLOCATIONS, 1,
        Integer.MAX_VALUE, this.m_maxMoveAllocations);
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    final int n;

    super.beginRun(f);

    this.m_f = f;
    n = f.n();
    this.m_distances = new int[n];
  }

  /** {@inheritDoc} */
  @Override
  public void endRun(final ObjectiveFunction f) {
    try {
      this.__clear();
    } finally {
      super.endRun(f);
    }
  }

  /**
   * Perform the MNS
   * 
   * @param args
   *          the command line arguments
   */
  public final static void main(final String[] args) {
    TSPAlgorithmRunner.benchmark(//
        Instance.SYMMETRIC_INSTANCES, MultiNeighborhoodSearch.class,//
        args);
  }
}
