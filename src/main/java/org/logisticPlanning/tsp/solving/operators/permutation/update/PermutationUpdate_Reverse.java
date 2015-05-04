package org.logisticPlanning.tsp.solving.operators.permutation.update;

import org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer;

/**
 * <p>
 * The reversal operation reverses a sub-sequence of nodes in between two
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
 * </p>
 * <p>
 * In&nbsp;[<a href="#cite_OWDC2013SADAFTSP"
 * style="font-weight:bold">9</a>] we define this operation as follows: Be
 * <code>x=(1-2-3-4-5-6-7-8-9)</code> a candidate solution to a TSP. Assume
 * that we invert the segment between (but not including) nodes
 * <code> 2</code> and <code>8</code>, i.e., everything from nodes
 * <code>3</code> to <code>7</code>. This means that we do
 * {@link #update(int[], int, int) y=update(x, 2, 6)} (due to the
 * zero-based indices in arrays). This operation, i.e.,
 * <code>(1-2-|-<span style="font-weight:bold">3</span>-4-5-6-<span style="font-weight:bold">7</span>-|-8-9)</code>
 * , will result in a new candidate solution
 * <code>y=(1-2-<span style="text-decoration:underline"><span style="font-weight:bold">7</span>-6-5-4-<span style="font-weight:bold">3</span></span>-8-9)</code>
 * .
 * </p>
 * <p>
 * The total
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#evaluate(int[])
 * tour length} of <code>x</code>, obtained with the
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction
 * f.evaluate(x)} method of the class
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction}
 * can now be used to compute the total length of the new solution
 * <code>y</code> with only a few distance evaluations, by calling
 * {@link #delta(int[], DistanceComputer, int, int) delta(x, f, 2, 6)} as
 * follows (again, as given in&nbsp;[<a href="#cite_OWDC2013SADAFTSP"
 * style="font-weight:bold">9</a>]):
 * <code>f(y)=f(x)+delta(x, f, 2, 6)</code> where
 * <code>delta(x, f, 2, 6)=f.distance(2,7)+f.distance(3,8)-f.distance(2,3)-f.distance(7,8)</code>
 * , where
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#distance(int, int)
 * <code>distance</code>} is the distance function of the
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction
 * objective function}. In other words, instead of re-evaluating the whole
 * solution, we just need four distance computations.
 * </p>
 * <p>
 * It can be noticed that the inversion operation is basically the same as
 * the edge cutting and reconstruction used in <a
 * href="https://en.wikipedia.org/wiki/2-opt">2-opt</a>&nbsp;[<a
 * href="#cite_C1958AMFSTSP" style="font-weight:bold">10</a>, <a
 * href="#cite_F1956TTSP" style="font-weight:bold">11</a>, <a
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
 * href="http://dx.doi.org/10.1109/CIPLS.2013.6595203">10.1109/CIPLS
 * .2013.6595203</a>; INSPEC Accession Number:&nbsp;13752116;
 * EI:&nbsp;20134116837899. <div>link: [<a
 * href="http://www.it-weise.de/documents/files/OWDC2013SADAFTSP.pdf"
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
 * </ol>
 */
public final class PermutationUpdate_Reverse extends
PermutationUpdateOperator {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance of the inversion operator */
  public static final PermutationUpdate_Reverse INSTANCE = new PermutationUpdate_Reverse();

  /** the globally shared complementary function */
  public static final ComplementUpdateOperator COMPLEMENT = new ComplementUpdateOperator(
      PermutationUpdate_Reverse.INSTANCE);

  /** invert */
  private PermutationUpdate_Reverse() {
    super("inversion"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final int delta(final int[] perm, final DistanceComputer f,
      final int a, final int b) {
    final int l, m1;
    final int B, E, A, F, AB, EF, AE, BF;

    l = perm.length;
    m1 = (l - 1);

    if (((((b - a) + l) % l) == m1) || (a == b)) {
      return PermutationUpdateOperator.NO_EFFECT;
    }

    A = perm[(a + m1) % l];
    B = perm[a];
    E = perm[b];
    F = perm[(b + 1) % l];

    BF = f.distance(B, F);
    AE = f.distance(A, E);
    AB = f.distance(A, B);
    EF = f.distance(E, F);

    return (BF + AE) - (AB + EF);
  }

  /** {@inheritDoc} */
  @Override
  public final void update(final int[] perm, final int a, final int b) {
    final int len;
    int i, t, x, y;

    len = perm.length;
    for (i = ((((b - a) + len) % len) >>> 1); i >= 0; i--) {
      x = ((a + i) % len);
      y = (((b - i) + len) % len);
      t = perm[x];
      perm[x] = perm[y];
      perm[y] = t;
    }
  }
}
