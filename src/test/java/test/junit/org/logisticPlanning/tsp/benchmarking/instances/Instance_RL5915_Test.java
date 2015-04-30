package test.junit.org.logisticPlanning.tsp.benchmarking.instances;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;

/**
 * Test the TSP instance
 * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RL5915}
 * from <a
 * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/">TSPLib
 * </a>. We test both, loaded distance computers which are raw (i.e.,
 * potentially based on coordinate lists), and those which use matrices
 * backing the results. <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_PR1987OOA5CSTSPBBAC" />Manfred W. Padberg
 * and&nbsp;Giovanni Rinaldi: <span
 * style="font-weight:bold">&ldquo;Optimization of a 532-City Symmetric
 * Traveling Salesman Problem by Branch and Cut,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Operations Research
 * Letters</span> 6(1):1&ndash;7, March&nbsp;1987; published by Amsterdam,
 * The Netherlands: Elsevier Science Publishers B.V.. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1016/0167-6377(87)90002-2"
 * >10.1016/0167-6377(87)90002-2</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/01676377">0167-6377</a></div></li>
 * <li><div><span id="cite_LK1973AEHAFTTSP" />Shen Lin and&nbsp;Brian
 * Wilson Kernighan: <span style="font-weight:bold">&ldquo;An Effective
 * Heuristic Algorithm for the Traveling-Salesman Problem,&rdquo;</span> in
 * <span style="font-style:italic;font-family:cursive;">Operations Research
 * (Oper. Res.)</span> 21(2):498&ndash;516, March&ndash;April 1973;
 * published by Linthicum, ML, USA: Institute for Operations Research and
 * the Management Sciences (INFORMS) and&nbsp;Cambridge, MA, USA: HighWire
 * Press (Stanford University). LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/66099702">66099702</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1287/opre.21.2.498"
 * >10.1287/opre.21.2.498</a>; JSTOR stable:&nbsp;<a
 * href="http://www.jstor.org/stable/169020">169020</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/2394608">2394608</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/0030364X">0030-364X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15265463">1526-5463</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=OPREAI"
 * >OPREAI</a>. <div>link: [<a
 * href="https://en.wikipedia.org/wiki/Lin%E2%80%93Kernighan_heuristic"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_PG1985PC" />Manfred W. Padberg and&nbsp;Martin
 * Gr&#246;tschel: <span style="font-weight:bold">&ldquo;Polyhedral
 * Computations,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">The Traveling Salesman
 * Problem: A Guided Tour of Combinatorial Optimization</span>, chapter
 * 307&ndash;360, pages 307&ndash;360, Estimation, Simulation, and Control
 * &#8211; Wiley-Interscience Series in Discrete Mathematics and
 * Optimization, Chichester, West Sussex, UK: Wiley Interscience.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0471904139">0-471-90413-9</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780471904137">978-0-471-
 * 90413-7</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/85003158">85003158</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/260186267">260186267</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=BXBGAAAAYAAJ">BXBGAAAAYAAJ</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=02528360X">02528360X</a>. <div>link:
 * [<a href=
 * "http://www.zib.de/groetschel/pubnew/paper/padberggroetschel1985.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_MOF1991LSMCFTTSP" /><a
 * href="http://lptms.u-psud.fr/membres/martino/index_e.html">Olivier C.
 * Martin</a>, Steve W. Otto, and&nbsp;Edward W. Felten: <span
 * style="font-weight:bold">&ldquo;Large-Step Markov Chains for the
 * Traveling Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Complex Systems</span>
 * 5(3):299&ndash;326, 1991; published by Champaign, IL, USA: Complex
 * Systems Publications, Inc.. ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/08912513">0891-2513</a>. <div>links:
 * [<a href="http://www.complex-systems.com/pdf/05-3-3.pdf">1</a>]
 * and&nbsp;[<a href=
 * "http://www.cs.ubc.ca/labs/beta/Courses/CPSC532D-03/Resources/MarOttFel91.pdf"
 * >2</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.56.9897"
 * >10.1.1.56.9897</a></div></div></li>
 * <li><div><span id="cite_ACC1990LBFTTSP" /><a
 * href="http://www.research.att.com/people/Applegate_David_L">David Lee
 * Applegate</a>, <a
 * href="http://users.encs.concordia.ca/~chvatal/">Va&#353;ek
 * Chv&#225;tal</a>, and&nbsp;<a
 * href="http://www2.isye.gatech.edu/~wcook/">William John Cook</a>: <span
 * style="font-weight:bold">&ldquo;Lower Bounds for the Traveling Salesman
 * Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">CRPC Workshop on the
 * Traveling Salesman Problem (TSP'90)</span>, April&nbsp;1990, Houston,
 * TX, USA: Rice University, The Center for Research on Parallel
 * Computation at Rice University (CRPC), <a
 * href="http://www.caam.rice.edu/~bixby/">Robert E. Bixby</a>, editor,
 * volume CRPC-TR90547 of Center for Research on Parallel Computation
 * (CRPC) Technical Report</div></li>
 * </ol>
 */
public class Instance_RL5915_Test extends _InstanceTest {

  /**
   * test the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RL5915}
   */
  public Instance_RL5915_Test() {
    super(Instance.RL5915);
  }
}
