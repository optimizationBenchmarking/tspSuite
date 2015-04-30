package org.logisticPlanning.tsp.benchmarking.instances;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer;
import org.logisticPlanning.utils.collections.lists.ArraySetView;
import org.logisticPlanning.utils.config.Configurable;

/**
 * <p>
 * The instance class holds information about one benchmark instance and
 * can load it from a resource. The benchmark instances currently used are
 * basically those from <a
 * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/">TSPLib
 * </a>&nbsp;[<a href="#cite_DACO1995TSPLIB"
 * style="font-weight:bold">1</a>, <a href="#cite_R1995T9"
 * style="font-weight:bold">2</a>, <a href="#cite_R1991ATSPL"
 * style="font-weight:bold">3</a>, <a href="#cite_W2003ROCFTB"
 * style="font-weight:bold">4</a>]. You can find more information about the
 * TSPLib in the file tsplib_doc.pdf included in the documentation folder
 * of this project (the same folder where also JavaDoc is included).
 * </p>
 * <p>
 * Each instance of this class can load a
 * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer} and
 * holds some default information such as the {@link #optimum() minimum
 * possible length} of any solution to the benchmark instance it
 * represents, whether the problem is {@link #symmetric() symmetric} or
 * not, and the {@link #n() number of nodes} in the TSP.
 * </p>
 * <p>
 * Instances are {@link java.lang.Comparable comparable}: When sorting an
 * array of Instance, those with smaller dimension will come first. Amongst
 * those with the same scale, those with the lexicographically smaller name
 * will come first.
 * </p>
 * <p>
 * Instances extend the class
 * {@link org.logisticPlanning.utils.config.Configurable Configurable}, but
 * they do not have any parameters that you can set. However, an instance
 * can write its setup (scale, etc) to a print stream via the method
 * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#printConfiguration(java.io.PrintStream)
 * printConfiguration}.
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_DACO1995TSPLIB" /><a
 * href="http://comopt.ifi.uni-heidelberg.de/people/reinelt/">Gerhard
 * Reinelt</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;
 * TSPLIB,&rdquo;</span> (Website), 1995, Heidelberg, Germany: Office
 * Research Group Discrete Optimization, Ruprecht Karls University of
 * Heidelberg. <div>link: [<a
 * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_R1995T9" /><a
 * href="http://comopt.ifi.uni-heidelberg.de/people/reinelt/">Gerhard
 * Reinelt</a>: <span style="font-weight:bold">&ldquo;TSPLIB
 * 95,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * 1995; published by Heidelberg, Germany: Universit&#228;t Heidelberg,
 * Institut f&#252;r Mathematik. <div>link: [<a
 * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/DOC.PS"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_R1991ATSPL" /><a
 * href="http://comopt.ifi.uni-heidelberg.de/people/reinelt/">Gerhard
 * Reinelt</a>: <span style="font-weight:bold">&ldquo;TSPLIB &#8212; A
 * Traveling Salesman Problem Library,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">ORSA Journal on
 * Computing</span> 3(4):376&ndash;384, Fall&nbsp;1991; published by
 * Operations Research Society of America (ORSA). doi:&nbsp;<a
 * href="http://dx.doi.org/10.1287/ijoc.3.4.376">10.1287/ijoc.3.4.376</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/60628815">60628815</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/08991499">0899-1499</a></div></li>
 * <li><div><span id="cite_W2003ROCFTB" /><a
 * href="http://www2.isye.gatech.edu/~wcook/">William John Cook</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Results of
 * Concorde for TSPLib Benchmark,&rdquo;</span> (Website),
 * December&nbsp;2003, Atlanta, GA, USA: Georgia Institute of Technology,
 * H. Milton Stewart School of Industrial and Systems Engineering.
 * <div>link: [<a
 * href="http://www.tsp.gatech.edu/concorde/benchmarks/bench99.html"
 * >1</a>]</div></div></li>
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
public final class Instance extends Configurable implements
    Comparable<Instance> {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  // Symmetric

  /**
   * The symmetric problem instance {@link #BURMA14 BURMA14} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 14 nodes} and a known optimal solution of
   * {@link #optimum() length 3323}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as geographical
   * distance based on a list of longitude-latitude coordinate pairs. 14
   * cities in Burma (Zaw Win).
   **/
  public static final Instance BURMA14 = new Instance(
      "burma14", 14, 3323l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #ULYSSES16 ULYSSES16} of <a
   * href=
   * "http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/">TSPLib</a>
   * has {@link #n() 16 nodes} and a known optimal solution of
   * {@link #optimum() length 6859}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as geographical
   * distance based on a list of longitude-latitude coordinate pairs.
   * Odyssey of Ulysses (Gr&ouml;tschel/Padberg).
   **/
  public static final Instance ULYSSES16 = new Instance(
      "ulysses16", 16, 6859l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #GR17 GR17} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 17 nodes} and a known optimal solution of
   * {@link #optimum() length 2085}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as lower diagonal
   * part of symmetric matrix in row-first representation. 17-city problem
   * (Gr&ouml;tschel).
   **/
  public static final Instance GR17 = new Instance(
      "gr17", 17, 2085l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #GR21 GR21} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 21 nodes} and a known optimal solution of
   * {@link #optimum() length 2707}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as lower diagonal
   * part of symmetric matrix in row-first representation. 21-city problem
   * (Gr&ouml;tschel).
   **/
  public static final Instance GR21 = new Instance(
      "gr21", 21, 2707l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #ULYSSES22 ULYSSES22} of <a
   * href=
   * "http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/">TSPLib</a>
   * has {@link #n() 22 nodes} and a known optimal solution of
   * {@link #optimum() length 7013}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as geographical
   * distance based on a list of longitude-latitude coordinate pairs.
   * Odyssey of Ulysses (Gr&ouml;tschel/Padberg).
   **/
  public static final Instance ULYSSES22 = new Instance(
      "ulysses22", 22, 7013l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #GR24 GR24} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 24 nodes} and a known optimal solution of
   * {@link #optimum() length 1272}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as lower diagonal
   * part of symmetric matrix in row-first representation. 24-city problem
   * (Gr&ouml;tschel).
   **/
  public static final Instance GR24 = new Instance(
      "gr24", 24, 1272l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #FRI26 FRI26} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 26 nodes} and a known optimal solution of
   * {@link #optimum() length 937}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as lower diagonal
   * part of symmetric matrix in row-first representation. 26 cities
   * (Fricker).
   **/
  public static final Instance FRI26 = new Instance(
      "fri26", 26, 937l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #BAYG29 BAYG29} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 29 nodes} and a known optimal solution of
   * {@link #optimum() length 1610}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as matrix with
   * top-row-first representation. 29 Cities in Bavaria, geographical
   * distances (Gr&ouml;tschel,J&uuml;nger,Reinelt).
   **/
  public static final Instance BAYG29 = new Instance(
      "bayg29", 29, 1610l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #BAYS29 BAYS29} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 29 nodes} and a known optimal solution of
   * {@link #optimum() length 2020}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as full distance
   * matrix. 29 cities in Bavaria, street distances
   * (Gr&ouml;tschel,J&uuml;nger,Reinelt).
   **/
  public static final Instance BAYS29 = new Instance(
      "bays29", 29, 2020l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #DANTZIG42 DANTZIG42} of <a
   * href=
   * "http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/">TSPLib</a>
   * has {@link #n() 42 nodes} and a known optimal solution of
   * {@link #optimum() length 699}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as lower diagonal
   * part of symmetric matrix in row-first representation. 42 cities
   * (Dantzig).
   **/
  public static final Instance DANTZIG42 = new Instance(
      "dantzig42", 42, 699l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #SWISS42 SWISS42} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 42 nodes} and a known optimal solution of
   * {@link #optimum() length 1273}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as full distance
   * matrix. 42 cities in Switzerland (Fricker).
   **/
  public static final Instance SWISS42 = new Instance(
      "swiss42", 42, 1273l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #ATT48 ATT48} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 48 nodes} and a known optimal solution of
   * {@link #optimum() length 10628}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as
   * pseudo-Euclidean distance based on a list of 2D coordinates. 48
   * capitals of the US (Padberg/Rinaldi). More information about this
   * instance can be found in&nbsp;[<a href="#cite_PR1987OOA5CSTSPBBAC"
   * style="font-weight:bold">5</a>].
   **/
  public static final Instance ATT48 = new Instance(
      "att48", 48, 10628l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #GR48 GR48} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 48 nodes} and a known optimal solution of
   * {@link #optimum() length 5046}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as lower diagonal
   * part of symmetric matrix in row-first representation. 48-city problem
   * (Gr&ouml;tschel).
   **/
  public static final Instance GR48 = new Instance(
      "gr48", 48, 5046l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #HK48 HK48} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 48 nodes} and a known optimal solution of
   * {@link #optimum() length 11461}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as lower diagonal
   * part of symmetric matrix in row-first representation. 48-city problem
   * (Held/Karp).
   **/
  public static final Instance HK48 = new Instance(
      "hk48", 48, 11461l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #EIL51 EIL51} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 51 nodes} and a known optimal solution of
   * {@link #optimum() length 426}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 51-city problem
   * (Christofides/Eilon).
   **/
  public static final Instance EIL51 = new Instance(
      "eil51", 51, 426l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #BERLIN52 BERLIN52} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 52 nodes} and a known optimal solution of
   * {@link #optimum() length 7542}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 52 locations in Berlin
   * (Gr&ouml;tschel).
   **/
  public static final Instance BERLIN52 = new Instance(
      "berlin52", 52, 7542l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #BRAZIL58 BRAZIL58} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 58 nodes} and a known optimal solution of
   * {@link #optimum() length 25395}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as matrix with
   * top-row-first representation. 58 cities in Brazil (Ferreira).
   **/
  public static final Instance BRAZIL58 = new Instance(
      "brazil58", 58, 25395l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #ST70 ST70} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 70 nodes} and a known optimal solution of
   * {@link #optimum() length 675}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 70-city problem
   * (Smith/Thompson).
   **/
  public static final Instance ST70 = new Instance(
      "st70", 70, 675l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #EIL76 EIL76} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 76 nodes} and a known optimal solution of
   * {@link #optimum() length 538}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 76-city problem
   * (Christofides/Eilon).
   **/
  public static final Instance EIL76 = new Instance(
      "eil76", 76, 538l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #PR76 PR76} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 76 nodes} and a known optimal solution of
   * {@link #optimum() length 108159}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 76-city problem
   * (Padberg/Rinaldi).
   **/
  public static final Instance PR76 = new Instance(
      "pr76", 76, 108159l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #GR96 GR96} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 96 nodes} and a known optimal solution of
   * {@link #optimum() length 55209}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as geographical
   * distance based on a list of longitude-latitude coordinate pairs.
   * Africa-Subproblem of 666-city TSP (Gr&ouml;tschel).
   **/
  public static final Instance GR96 = new Instance(
      "gr96", 96, 55209l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #RAT99 RAT99} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 99 nodes} and a known optimal solution of
   * {@link #optimum() length 1211}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. Rattled grid
   * (Pulleyblank).
   **/
  public static final Instance RAT99 = new Instance(
      "rat99", 99, 1211l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #KROA100 KROA100} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 100 nodes} and a known optimal solution of
   * {@link #optimum() length 21282}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 100-city problem A
   * (Krolak/Felts/Nelson).
   **/
  public static final Instance KROA100 = new Instance(
      "kroA100", 100, 21282l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #KROB100 KROB100} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 100 nodes} and a known optimal solution of
   * {@link #optimum() length 22141}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 100-city problem B
   * (Krolak/Felts/Nelson).
   **/
  public static final Instance KROB100 = new Instance(
      "kroB100", 100, 22141l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #KROC100 KROC100} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 100 nodes} and a known optimal solution of
   * {@link #optimum() length 20749}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 100-city problem C
   * (Krolak/Felts/Nelson).
   **/
  public static final Instance KROC100 = new Instance(
      "kroC100", 100, 20749l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #KROD100 KROD100} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 100 nodes} and a known optimal solution of
   * {@link #optimum() length 21294}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 100-city problem D
   * (Krolak/Felts/Nelson).
   **/
  public static final Instance KROD100 = new Instance(
      "kroD100", 100, 21294l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #KROE100 KROE100} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 100 nodes} and a known optimal solution of
   * {@link #optimum() length 22068}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 100-city problem E
   * (Krolak/Felts/Nelson).
   **/
  public static final Instance KROE100 = new Instance(
      "kroE100", 100, 22068l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #RD100 RD100} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 100 nodes} and a known optimal solution of
   * {@link #optimum() length 7910}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 100-city random TSP
   * (Reinelt).
   **/
  public static final Instance RD100 = new Instance(
      "rd100", 100, 7910l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #EIL101 EIL101} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 101 nodes} and a known optimal solution of
   * {@link #optimum() length 629}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 101-city problem
   * (Christofides/Eilon).
   **/
  public static final Instance EIL101 = new Instance(
      "eil101", 101, 629l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #LIN105 LIN105} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 105 nodes} and a known optimal solution of
   * {@link #optimum() length 14379}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 105-city problem
   * (Subproblem of lin318).
   **/
  public static final Instance LIN105 = new Instance(
      "lin105", 105, 14379l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #PR107 PR107} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 107 nodes} and a known optimal solution of
   * {@link #optimum() length 44303}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 107-city problem
   * (Padberg/Rinaldi).
   **/
  public static final Instance PR107 = new Instance(
      "pr107", 107, 44303l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #GR120 GR120} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 120 nodes} and a known optimal solution of
   * {@link #optimum() length 6942}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as lower diagonal
   * part of symmetric matrix in row-first representation. 120 cities in
   * Germany (Gr&ouml;tschel).
   **/
  public static final Instance GR120 = new Instance(
      "gr120", 120, 6942l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #PR124 PR124} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 124 nodes} and a known optimal solution of
   * {@link #optimum() length 59030}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 124-city problem
   * (Padberg/Rinaldi).
   **/
  public static final Instance PR124 = new Instance(
      "pr124", 124, 59030l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #BIER127 BIER127} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 127 nodes} and a known optimal solution of
   * {@link #optimum() length 118282}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 127 Bierg&auml;rten
   * (&quot;Beer Gardens&quot;, open-air beer restaurants) in Augsburg,
   * Germany (J&uuml;nger/Reinelt).
   **/
  public static final Instance BIER127 = new Instance(
      "bier127", 127, 118282l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #CH130 CH130} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 130 nodes} and a known optimal solution of
   * {@link #optimum() length 6110}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 130 city problem
   * (Churritz).
   **/
  public static final Instance CH130 = new Instance(
      "ch130", 130, 6110l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #PR136 PR136} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 136 nodes} and a known optimal solution of
   * {@link #optimum() length 96772}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 136-city problem
   * (Padberg/Rinaldi).
   **/
  public static final Instance PR136 = new Instance(
      "pr136", 136, 96772l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #GR137 GR137} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 137 nodes} and a known optimal solution of
   * {@link #optimum() length 69853}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as geographical
   * distance based on a list of longitude-latitude coordinate pairs.
   * America-Subproblem of 666-city TSP (Gr&ouml;tschel).
   **/
  public static final Instance GR137 = new Instance(
      "gr137", 137, 69853l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #PR144 PR144} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 144 nodes} and a known optimal solution of
   * {@link #optimum() length 58537}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 144-city problem
   * (Padberg/Rinaldi).
   **/
  public static final Instance PR144 = new Instance(
      "pr144", 144, 58537l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #CH150 CH150} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 150 nodes} and a known optimal solution of
   * {@link #optimum() length 6528}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 150 city Problem
   * (churritz).
   **/
  public static final Instance CH150 = new Instance(
      "ch150", 150, 6528l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #KROA150 KROA150} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 150 nodes} and a known optimal solution of
   * {@link #optimum() length 26524}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 150-city problem A
   * (Krolak/Felts/Nelson).
   **/
  public static final Instance KROA150 = new Instance(
      "kroA150", 150, 26524l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #KROB150 KROB150} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 150 nodes} and a known optimal solution of
   * {@link #optimum() length 26130}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 150-city problem B
   * (Krolak/Felts/Nelson).
   **/
  public static final Instance KROB150 = new Instance(
      "kroB150", 150, 26130l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #PR152 PR152} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 152 nodes} and a known optimal solution of
   * {@link #optimum() length 73682}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 152-city problem
   * (Padberg/Rinaldi).
   **/
  public static final Instance PR152 = new Instance(
      "pr152", 152, 73682l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #U159 U159} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 159 nodes} and a known optimal solution of
   * {@link #optimum() length 42080}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. Drilling problem
   * (Reinelt).
   **/
  public static final Instance U159 = new Instance(
      "u159", 159, 42080l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #SI175 SI175} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 175 nodes} and a known optimal solution of
   * {@link #optimum() length 21407}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as upper diagonal
   * part of symmetric matrix in row-first representation. TSP (M.
   * Hofmeister).
   **/
  public static final Instance SI175 = new Instance(
      "si175", 175, 21407l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #BRG180 BRG180} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 180 nodes} and a known optimal solution of
   * {@link #optimum() length 1950}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as matrix with
   * top-row-first representation. Bridge tournament problem (Rinaldi).
   **/
  public static final Instance BRG180 = new Instance(
      "brg180", 180, 1950l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #RAT195 RAT195} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 195 nodes} and a known optimal solution of
   * {@link #optimum() length 2323}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. Rattled grid
   * (Pulleyblank).
   **/
  public static final Instance RAT195 = new Instance(
      "rat195", 195, 2323l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #D198 D198} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 198 nodes} and a known optimal solution of
   * {@link #optimum() length 15780}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. Drilling problem
   * (Reinelt).
   **/
  public static final Instance D198 = new Instance(
      "d198", 198, 15780l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #KROA200 KROA200} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 200 nodes} and a known optimal solution of
   * {@link #optimum() length 29368}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 200-city problem A
   * (Krolak/Felts/Nelson).
   **/
  public static final Instance KROA200 = new Instance(
      "kroA200", 200, 29368l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #KROB200 KROB200} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 200 nodes} and a known optimal solution of
   * {@link #optimum() length 29437}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 200-city problem B
   * (Krolak/Felts/Nelson).
   **/
  public static final Instance KROB200 = new Instance(
      "kroB200", 200, 29437l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #GR202 GR202} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 202 nodes} and a known optimal solution of
   * {@link #optimum() length 40160}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as geographical
   * distance based on a list of longitude-latitude coordinate pairs.
   * Europe-Subproblem of 666-city TSP (Gr&ouml;tschel).
   **/
  public static final Instance GR202 = new Instance(
      "gr202", 202, 40160l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #TS225 TS225} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 225 nodes} and a known optimal solution of
   * {@link #optimum() length 126643}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 225-city problem
   * (J&uuml;nger,R&auml;cke,Tsch&ouml;cke).
   **/
  public static final Instance TS225 = new Instance(
      "ts225", 225, 126643l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #TSP225 TSP225} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 225 nodes} and a known optimal solution of
   * {@link #optimum() length 3916}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. A TSP problem (Reinelt).
   **/
  public static final Instance TSP225 = new Instance(
      "tsp225", 225, 3916l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #PR226 PR226} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 226 nodes} and a known optimal solution of
   * {@link #optimum() length 80369}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 226-city problem
   * (Padberg/Rinaldi).
   **/
  public static final Instance PR226 = new Instance(
      "pr226", 226, 80369l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #GR229 GR229} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 229 nodes} and a known optimal solution of
   * {@link #optimum() length 134602}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as geographical
   * distance based on a list of longitude-latitude coordinate pairs.
   * Asia/Australia-Subproblem of 666-city TSP (Gr&ouml;tschel).
   **/
  public static final Instance GR229 = new Instance(
      "gr229", 229, 134602l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #GIL262 GIL262} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 262 nodes} and a known optimal solution of
   * {@link #optimum() length 2378}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 262-city problem
   * (Gillet/Johnson).
   **/
  public static final Instance GIL262 = new Instance(
      "gil262", 262, 2378l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #PR264 PR264} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 264 nodes} and a known optimal solution of
   * {@link #optimum() length 49135}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 264-city problem
   * (Padberg/Rinaldi).
   **/
  public static final Instance PR264 = new Instance(
      "pr264", 264, 49135l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #A280 A280} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 280 nodes} and a known optimal solution of
   * {@link #optimum() length 2579}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. Drilling problem (Ludwig).
   **/
  public static final Instance A280 = new Instance(
      "a280", 280, 2579l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #PR299 PR299} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 299 nodes} and a known optimal solution of
   * {@link #optimum() length 48191}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 299-city problem
   * (Padberg/Rinaldi).
   **/
  public static final Instance PR299 = new Instance(
      "pr299", 299, 48191l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #LIN318 LIN318} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 318 nodes} and a known optimal solution of
   * {@link #optimum() length 42029}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. The problem is posed by
   * Lin and Kernighan as an open tour with fixed ends, but it can easily
   * be converted to a TSP&nbsp;[<a href="#cite_MOF1991LSMCFTTSP"
   * style="font-weight:bold">8</a>]. Padberg and Gr&ouml;tschel used a
   * combination of cutting-plane and branch-and-bound methods to find the
   * optimal tour for this problem&nbsp;[<a href="#cite_PG1985PC"
   * style="font-weight:bold">7</a>, <a href="#cite_MOF1991LSMCFTTSP"
   * style="font-weight:bold">8</a>]. More information about this instance
   * can be found in&nbsp;[<a href="#cite_LK1973AEHAFTTSP"
   * style="font-weight:bold">6</a>, <a href="#cite_PG1985PC"
   * style="font-weight:bold">7</a>, <a href="#cite_MOF1991LSMCFTTSP"
   * style="font-weight:bold">8</a>].
   **/
  public static final Instance LIN318 = new Instance(
      "lin318", 318, 42029l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #RD400 RD400} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 400 nodes} and a known optimal solution of
   * {@link #optimum() length 15281}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 400-city random TSP
   * (Reinelt).
   **/
  public static final Instance RD400 = new Instance(
      "rd400", 400, 15281l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #FL417 FL417} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 417 nodes} and a known optimal solution of
   * {@link #optimum() length 11861}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. Drilling problem
   * (Reinelt).
   **/
  public static final Instance FL417 = new Instance(
      "fl417", 417, 11861l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #GR431 GR431} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 431 nodes} and a known optimal solution of
   * {@link #optimum() length 171414}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as geographical
   * distance based on a list of longitude-latitude coordinate pairs.
   * Europe/Asia/Australia-Subproblem of 666-city TSP (Gr&ouml;tschel).
   **/
  public static final Instance GR431 = new Instance(
      "gr431", 431, 171414l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #PR439 PR439} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 439 nodes} and a known optimal solution of
   * {@link #optimum() length 107217}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 439-city problem
   * (Padberg/Rinaldi).
   **/
  public static final Instance PR439 = new Instance(
      "pr439", 439, 107217l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #PCB442 PCB442} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 442 nodes} and a known optimal solution of
   * {@link #optimum() length 50778}. The canonical tour (
   * {@code (1, 2, 3, ...)}) of this instance has the
   * {@link #canonicalTourLength() length 221440}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. Drilling problem
   * (Gr&ouml;tschel/J&uuml;nger/Reinelt).
   **/
  public static final Instance PCB442 = new Instance(
      "pcb442", 442, 50778l, false, true, 221440l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #D493 D493} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 493 nodes} and a known optimal solution of
   * {@link #optimum() length 35002}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. Drilling problem
   * (Reinelt).
   **/
  public static final Instance D493 = new Instance(
      "d493", 493, 35002l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #ATT532 ATT532} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 532 nodes} and a known optimal solution of
   * {@link #optimum() length 27686}. The canonical tour (
   * {@code (1, 2, 3, ...)}) of this instance has the
   * {@link #canonicalTourLength() length 309636}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as
   * pseudo-Euclidean distance based on a list of 2D coordinates. The
   * so-called AT&amp;T532 is 532 city problem solved to optimality by
   * Padberg and Rinaldi&nbsp;[<a href="#cite_PR1987OOA5CSTSPBBAC"
   * style="font-weight:bold">5</a>] using branch-and-cut methods&nbsp;[<a
   * href="#cite_PR1987OOA5CSTSPBBAC" style="font-weight:bold">5</a>, <a
   * href="#cite_MOF1991LSMCFTTSP" style="font-weight:bold">8</a>]. More
   * information about this instance can be found in&nbsp;[<a
   * href="#cite_MOF1991LSMCFTTSP" style="font-weight:bold">8</a>, <a
   * href="#cite_PR1987OOA5CSTSPBBAC" style="font-weight:bold">5</a>].
   **/
  public static final Instance ATT532 = new Instance(
      "att532", 532, 27686l, true, true, 309636l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #ALI535 ALI535} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 535 nodes} and a known optimal solution of
   * {@link #optimum() length 202310}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as geographical
   * distance based on a list of longitude-latitude coordinate pairs. 535
   * Airports around the globe (Padberg/Rinaldi). More information about
   * this instance can be found in&nbsp;[<a
   * href="#cite_PR1987OOA5CSTSPBBAC" style="font-weight:bold">5</a>].
   **/
  public static final Instance ALI535 = new Instance(
      "ali535", 535, 202310l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #SI535 SI535} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 535 nodes} and a known optimal solution of
   * {@link #optimum() length 48450}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as upper diagonal
   * part of symmetric matrix in row-first representation. TSP (M.
   * Hofmeister).
   **/
  public static final Instance SI535 = new Instance(
      "si535", 535, 48450l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #PA561 PA561} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 561 nodes} and a known optimal solution of
   * {@link #optimum() length 2763}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as lower diagonal
   * part of symmetric matrix in row-first representation. 561-city problem
   * (Kleinschmidt).
   **/
  public static final Instance PA561 = new Instance(
      "pa561", 561, 2763l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #U574 U574} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 574 nodes} and a known optimal solution of
   * {@link #optimum() length 36905}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. Drilling problem
   * (Reinelt).
   **/
  public static final Instance U574 = new Instance(
      "u574", 574, 36905l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #RAT575 RAT575} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 575 nodes} and a known optimal solution of
   * {@link #optimum() length 6773}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. Rattled grid
   * (Pulleyblank).
   **/
  public static final Instance RAT575 = new Instance(
      "rat575", 575, 6773l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #P654 P654} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 654 nodes} and a known optimal solution of
   * {@link #optimum() length 34643}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. Drilling problem
   * (Reinelt).
   **/
  public static final Instance P654 = new Instance(
      "p654", 654, 34643l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #D657 D657} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 657 nodes} and a known optimal solution of
   * {@link #optimum() length 48912}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. Drilling problem
   * (Reinelt).
   **/
  public static final Instance D657 = new Instance(
      "d657", 657, 48912l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #GR666 GR666} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 666 nodes} and a known optimal solution of
   * {@link #optimum() length 294358}. The canonical tour (
   * {@code (1, 2, 3, ...)}) of this instance has the
   * {@link #canonicalTourLength() length 423710}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as geographical
   * distance based on a list of longitude-latitude coordinate pairs. 666
   * cities around the world (Gr&ouml;tschel).
   **/
  public static final Instance GR666 = new Instance(
      "gr666", 666, 294358l, false, true, 423710l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #U724 U724} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 724 nodes} and a known optimal solution of
   * {@link #optimum() length 41910}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. Drilling problem
   * (Reinelt).
   **/
  public static final Instance U724 = new Instance(
      "u724", 724, 41910l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #RAT783 RAT783} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 783 nodes} and a known optimal solution of
   * {@link #optimum() length 8806}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. Rattled grid
   * (Pulleyblank): The city positions of this problem are obtained by
   * small, random displacements from a regular 27&times;29 lattice. This
   * problem has been solved to optimality by Cook et al.&nbsp;[<a
   * href="#cite_ACC1990LBFTTSP" style="font-weight:bold">9</a>, <a
   * href="#cite_MOF1991LSMCFTTSP" style="font-weight:bold">8</a>] More
   * information about this instance can be found in&nbsp;[<a
   * href="#cite_MOF1991LSMCFTTSP" style="font-weight:bold">8</a>, <a
   * href="#cite_ACC1990LBFTTSP" style="font-weight:bold">9</a>].
   **/
  public static final Instance RAT783 = new Instance(
      "rat783", 783, 8806l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #DSJ1000 DSJ1000} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 1000 nodes} and a known optimal solution
   * of {@link #optimum() length 18660188}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as rounded-up
   * Euclidean distance based on a list of 2D coordinates. Clustered random
   * problem (Johnson).
   **/
  public static final Instance DSJ1000 = new Instance(
      "dsj1000", 1000, 18660188l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #PR1002 PR1002} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 1002 nodes} and a known optimal solution
   * of {@link #optimum() length 259045}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 1002-city problem
   * (Padberg/Rinaldi)
   **/
  public static final Instance PR1002 = new Instance(
      "pr1002", 1002, 259045l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #SI1032 SI1032} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 1032 nodes} and a known optimal solution
   * of {@link #optimum() length 92650}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as upper diagonal
   * part of symmetric matrix in row-first representation. TSP (M.
   * Hofmeister).
   **/
  public static final Instance SI1032 = new Instance(
      "si1032", 1032, 92650l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #U1060 U1060} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 1060 nodes} and a known optimal solution
   * of {@link #optimum() length 224094}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. Drilling problem problem
   * (Reinelt).
   **/
  public static final Instance U1060 = new Instance(
      "u1060", 1060, 224094l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #VM1084 VM1084} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 1084 nodes} and a known optimal solution
   * of {@link #optimum() length 239297}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 1084-city problem
   * (Reinelt).
   **/
  public static final Instance VM1084 = new Instance(
      "vm1084", 1084, 239297l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #PCB1173 PCB1173} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 1173 nodes} and a known optimal solution
   * of {@link #optimum() length 56892}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. Drilling problem
   * (J&uuml;nger/Reinelt).
   **/
  public static final Instance PCB1173 = new Instance(
      "pcb1173", 1173, 56892l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #D1291 D1291} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 1291 nodes} and a known optimal solution
   * of {@link #optimum() length 50801}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. Drilling problem
   * (Reinelt).
   **/
  public static final Instance D1291 = new Instance(
      "d1291", 1291, 50801l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #RL1304 RL1304} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 1304 nodes} and a known optimal solution
   * of {@link #optimum() length 252948}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 1304-city TSP (Reinelt).
   **/
  public static final Instance RL1304 = new Instance(
      "rl1304", 1304, 252948l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #RL1323 RL1323} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 1323 nodes} and a known optimal solution
   * of {@link #optimum() length 270199}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 1323-city TSP (Reinelt).
   **/
  public static final Instance RL1323 = new Instance(
      "rl1323", 1323, 270199l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #NRW1379 NRW1379} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 1379 nodes} and a known optimal solution
   * of {@link #optimum() length 56638}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 1379 Orte in
   * Nordrhein-Westfalen (Bachem/Wottawa).
   **/
  public static final Instance NRW1379 = new Instance(
      "nrw1379", 1379, 56638l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #FL1400 FL1400} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 1400 nodes} and a known optimal solution
   * of {@link #optimum() length 20127}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. Drilling problem
   * (Reinelt).
   **/
  public static final Instance FL1400 = new Instance(
      "fl1400", 1400, 20127l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #U1432 U1432} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 1432 nodes} and a known optimal solution
   * of {@link #optimum() length 152970}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. Drilling problem
   * (Reinelt).
   **/
  public static final Instance U1432 = new Instance(
      "u1432", 1432, 152970l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #FL1577 FL1577} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 1577 nodes} and a known optimal solution
   * of {@link #optimum() length 22249}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. Drilling problem (Reinelt)
   **/
  public static final Instance FL1577 = new Instance(
      "fl1577", 1577, 22249l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #D1655 D1655} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 1655 nodes} and a known optimal solution
   * of {@link #optimum() length 62128}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. Drilling problem
   * (Reinelt).
   **/
  public static final Instance D1655 = new Instance(
      "d1655", 1655, 62128l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #VM1748 VM1748} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 1748 nodes} and a known optimal solution
   * of {@link #optimum() length 336556}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 1784-city problem
   * (Reinelt).
   **/
  public static final Instance VM1748 = new Instance(
      "vm1748", 1748, 336556l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #U1817 U1817} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 1817 nodes} and a known optimal solution
   * of {@link #optimum() length 57201}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. Drilling problem
   * (Reinelt).
   **/
  public static final Instance U1817 = new Instance(
      "u1817", 1817, 57201l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #RL1889 RL1889} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 1889 nodes} and a known optimal solution
   * of {@link #optimum() length 316536}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 1889-city TSP (Reinelt).
   **/
  public static final Instance RL1889 = new Instance(
      "rl1889", 1889, 316536l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #D2103 D2103} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 2103 nodes} and a known optimal solution
   * of {@link #optimum() length 80450}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. Drilling problem
   * (Reinelt).
   **/
  public static final Instance D2103 = new Instance(
      "d2103", 2103, 80450l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #U2152 U2152} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 2152 nodes} and a known optimal solution
   * of {@link #optimum() length 64253}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. Drilling problem
   * (Reinelt).
   **/
  public static final Instance U2152 = new Instance(
      "u2152", 2152, 64253l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #U2319 U2319} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 2319 nodes} and a known optimal solution
   * of {@link #optimum() length 234256}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. Drilling problem
   * (Reinelt).
   **/
  public static final Instance U2319 = new Instance(
      "u2319", 2319, 234256l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #PR2392 PR2392} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 2392 nodes} and a known optimal solution
   * of {@link #optimum() length 378032}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 2392-city problem
   * (Padberg/Rinaldi).
   **/
  public static final Instance PR2392 = new Instance(
      "pr2392", 2392, 378032l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #PCB3038 PCB3038} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 3038 nodes} and a known optimal solution
   * of {@link #optimum() length 137694}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. Drilling problem
   * (Junger/Reinelt).
   **/
  public static final Instance PCB3038 = new Instance(
      "pcb3038", 3038, 137694l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #FL3795 FL3795} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 3795 nodes} and a known optimal solution
   * of {@link #optimum() length 28772}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. Drilling problem
   * (Reinelt).
   **/
  public static final Instance FL3795 = new Instance(
      "fl3795", 3795, 28772l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #FNL4461 FNL4461} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 4461 nodes} and a known optimal solution
   * of {@link #optimum() length 182566}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. The new five provinces of
   * Germany, i.e., the former GDR (Eastern Germany) (Bachem/Wottawa).
   **/
  public static final Instance FNL4461 = new Instance(
      "fnl4461", 4461, 182566l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #RL5915 RL5915} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 5915 nodes} and a known optimal solution
   * of {@link #optimum() length 565530}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 5915-city TSP (Reinelt).
   **/
  public static final Instance RL5915 = new Instance(
      "rl5915", 5915, 565530l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #RL5934 RL5934} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 5934 nodes} and a known optimal solution
   * of {@link #optimum() length 556045}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 5934-city TSP (Reinelt).
   **/
  public static final Instance RL5934 = new Instance(
      "rl5934", 5934, 556045l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #PLA7397 PLA7397} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 7397 nodes} and a known optimal solution
   * of {@link #optimum() length 23260728}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as rounded-up
   * Euclidean distance based on a list of 2D coordinates. Programmed logic
   * array (Johnson).
   **/
  public static final Instance PLA7397 = new Instance(
      "pla7397", 7397, 23260728l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #RL11849 RL11849} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 11849 nodes} and a known optimal solution
   * of {@link #optimum() length 923288}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. 11849-city TSP (Reinelt).
   **/
  public static final Instance RL11849 = new Instance(
      "rl11849", 11849, 923288l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #USA13509 USA13509} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 13509 nodes} and a known optimal solution
   * of {@link #optimum() length 19982859}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. Cities with population at
   * least 500 in the continental US. Contributed by David Applegate and
   * Andre Rohe, based on the data set &quot;US.lat-long&quot; from the ftp
   * site <a href="ftp://ftp.cs.toronto.edu">ftp.cs.toronto.edu</a>. The
   * file US.lat-long.Z can be found in the directory /doc/geography.
   **/
  public static final Instance USA13509 = new Instance(
      "usa13509", 13509, 19982859l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #BRD14051 BRD14051} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 14051 nodes} and a known optimal solution
   * of {@link #optimum() length 469385}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. West-Germany in the
   * borders of 1989 (Bachem/Wottawa).
   **/
  public static final Instance BRD14051 = new Instance(
      "brd14051", 14051, 469385l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #D15112 D15112} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 15112 nodes} and a known optimal solution
   * of {@link #optimum() length 1573084}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. Germany-Problem (A.Rohe).
   **/
  public static final Instance D15112 = new Instance(
      "d15112", 15112, 1573084l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #D18512 D18512} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 18512 nodes} and a known optimal solution
   * of {@link #optimum() length 645238}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as Euclidean
   * distance based on a list of 2D coordinates. Germany (united with GDR,
   * the former East Germany) (Bachem/Wottawa).
   **/
  public static final Instance D18512 = new Instance(
      "d18512", 18512, 645238l, true, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #PLA33810 PLA33810} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 33810 nodes} and a known optimal solution
   * of {@link #optimum() length 66048945}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as rounded-up
   * Euclidean distance based on a list of 2D coordinates. Programmed logic
   * array (Johnson).
   **/
  public static final Instance PLA33810 = new Instance(
      "pla33810", 33810, 66048945l, false, true, -1l); //$NON-NLS-1$

  /**
   * The symmetric problem instance {@link #PLA85900 PLA85900} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 85900 nodes} and a known optimal solution
   * of {@link #optimum() length 142382641}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as rounded-up
   * Euclidean distance based on a list of 2D coordinates. Programmed logic
   * array (Johnson).
   **/
  public static final Instance PLA85900 = new Instance(
      "pla85900", 85900, 142382641l, false, true, -1l); //$NON-NLS-1$
  // Asymmetric Instances

  /**
   * The asymmetric problem instance {@link #BR17 BR17} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 17 nodes} and a known optimal solution of
   * {@link #optimum() length 39}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as full distance
   * matrix. 17 city problem (Repetto).
   **/
  public static final Instance BR17 = new Instance(
      "br17", 17, 39l, false, false, -1l); //$NON-NLS-1$

  /**
   * The asymmetric problem instance {@link #FTV33 FTV33} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 34 nodes} and a known optimal solution of
   * {@link #optimum() length 1286}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as full distance
   * matrix. Asymmetric TSP (Fischetti).
   **/
  public static final Instance FTV33 = new Instance(
      "ftv33", 34, 1286l, false, false, -1l); //$NON-NLS-1$

  /**
   * The asymmetric problem instance {@link #FTV35 FTV35} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 36 nodes} and a known optimal solution of
   * {@link #optimum() length 1473}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as full distance
   * matrix. Asymmetric TSP (Fischetti).
   **/
  public static final Instance FTV35 = new Instance(
      "ftv35", 36, 1473l, false, false, -1l); //$NON-NLS-1$

  /**
   * The asymmetric problem instance {@link #FTV38 FTV38} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 39 nodes} and a known optimal solution of
   * {@link #optimum() length 1530}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as full distance
   * matrix. Asymmetric TSP (Fischetti).
   **/
  public static final Instance FTV38 = new Instance(
      "ftv38", 39, 1530l, false, false, -1l); //$NON-NLS-1$

  /**
   * The asymmetric problem instance {@link #P43 P43} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 43 nodes} and a known optimal solution of
   * {@link #optimum() length 5620}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as full distance
   * matrix. Asymmetric TSP (Repetto,Pekny).
   **/
  public static final Instance P43 = new Instance(
      "p43", 43, 5620l, false, false, -1l); //$NON-NLS-1$

  /**
   * The asymmetric problem instance {@link #FTV44 FTV44} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 45 nodes} and a known optimal solution of
   * {@link #optimum() length 1613}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as full distance
   * matrix. Asymmetric TSP (Fischetti).
   **/
  public static final Instance FTV44 = new Instance(
      "ftv44", 45, 1613l, false, false, -1l); //$NON-NLS-1$

  /**
   * The asymmetric problem instance {@link #FTV47 FTV47} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 48 nodes} and a known optimal solution of
   * {@link #optimum() length 1776}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as full distance
   * matrix. Asymmetric TSP (Fischetti).
   **/
  public static final Instance FTV47 = new Instance(
      "ftv47", 48, 1776l, false, false, -1l); //$NON-NLS-1$

  /**
   * The asymmetric problem instance {@link #RY48P RY48P} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 48 nodes} and a known optimal solution of
   * {@link #optimum() length 14422}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as full distance
   * matrix. Asymmetric TSP (Fischetti).
   **/
  public static final Instance RY48P = new Instance(
      "ry48p", 48, 14422l, false, false, -1l); //$NON-NLS-1$

  /**
   * The asymmetric problem instance {@link #FT53 FT53} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 53 nodes} and a known optimal solution of
   * {@link #optimum() length 6905}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as full distance
   * matrix. Asymmetric TSP (Fischetti).
   **/
  public static final Instance FT53 = new Instance(
      "ft53", 53, 6905l, false, false, -1l); //$NON-NLS-1$

  /**
   * The asymmetric problem instance {@link #FTV55 FTV55} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 56 nodes} and a known optimal solution of
   * {@link #optimum() length 1608}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as full distance
   * matrix. Asymmetric TSP (Fischetti).
   **/
  public static final Instance FTV55 = new Instance(
      "ftv55", 56, 1608l, false, false, -1l); //$NON-NLS-1$

  /**
   * The asymmetric problem instance {@link #FTV64 FTV64} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 65 nodes} and a known optimal solution of
   * {@link #optimum() length 1839}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as full distance
   * matrix. Asymmetric TSP (Fischetti).
   **/
  public static final Instance FTV64 = new Instance(
      "ftv64", 65, 1839l, false, false, -1l); //$NON-NLS-1$

  /**
   * The asymmetric problem instance {@link #FT70 FT70} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 70 nodes} and a known optimal solution of
   * {@link #optimum() length 38673}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as full distance
   * matrix. Asymmetric TSP (Fischetti).
   **/
  public static final Instance FT70 = new Instance(
      "ft70", 70, 38673l, false, false, -1l); //$NON-NLS-1$

  /**
   * The asymmetric problem instance {@link #FTV70 FTV70} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 71 nodes} and a known optimal solution of
   * {@link #optimum() length 1950}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as full distance
   * matrix. Asymmetric TSP (Fischetti).
   **/
  public static final Instance FTV70 = new Instance(
      "ftv70", 71, 1950l, false, false, -1l); //$NON-NLS-1$

  /**
   * The asymmetric problem instance {@link #KRO124P KRO124P} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 100 nodes} and a known optimal solution of
   * {@link #optimum() length 36230}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as full distance
   * matrix. Asymmetric TSP (Fischetti).
   **/
  public static final Instance KRO124P = new Instance(
      "kro124p", 100, 36230l, false, false, -1l); //$NON-NLS-1$

  /**
   * The asymmetric problem instance {@link #FTV170 FTV170} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 171 nodes} and a known optimal solution of
   * {@link #optimum() length 2755}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as full distance
   * matrix. Asymmetric TSP (Fischetti).
   **/
  public static final Instance FTV170 = new Instance(
      "ftv170", 171, 2755l, false, false, -1l); //$NON-NLS-1$

  /**
   * The asymmetric problem instance {@link #RBG323 RBG323} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 323 nodes} and a known optimal solution of
   * {@link #optimum() length 1326}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as full distance
   * matrix. Stacker crane application (Ascheuer).
   **/
  public static final Instance RBG323 = new Instance(
      "rbg323", 323, 1326l, false, false, -1l); //$NON-NLS-1$

  /**
   * The asymmetric problem instance {@link #RBG358 RBG358} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 358 nodes} and a known optimal solution of
   * {@link #optimum() length 1163}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as full distance
   * matrix. Stacker crane application (Ascheuer).
   **/
  public static final Instance RBG358 = new Instance(
      "rbg358", 358, 1163l, false, false, -1l); //$NON-NLS-1$

  /**
   * The asymmetric problem instance {@link #RBG403 RBG403} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 403 nodes} and a known optimal solution of
   * {@link #optimum() length 2465}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as full distance
   * matrix. Stacker crane application (Ascheuer).
   **/
  public static final Instance RBG403 = new Instance(
      "rbg403", 403, 2465l, false, false, -1l); //$NON-NLS-1$

  /**
   * The asymmetric problem instance {@link #RBG443 RBG443} of <a
   * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
   * >TSPLib</a> has {@link #n() 443 nodes} and a known optimal solution of
   * {@link #optimum() length 2720}. The
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
   * distance information} in the instance file is given as full distance
   * matrix. Stacker crane application (Ascheuer).
   **/
  public static final Instance RBG443 = new Instance(
      "rbg443", 443, 2720l, false, false, -1l); //$NON-NLS-1$

  /**
   * the symmetric instances, ordered by dimension (smaller problems come
   * first)
   */
  public static final ArraySetView<Instance> SYMMETRIC_INSTANCES = ArraySetView
      .makeArraySetView(new Instance[] { Instance.BURMA14,
          Instance.ULYSSES16, Instance.GR17, Instance.GR21,
          Instance.ULYSSES22, Instance.GR24, Instance.FRI26,
          Instance.BAYG29, Instance.BAYS29, Instance.DANTZIG42,
          Instance.SWISS42, Instance.ATT48, Instance.GR48, Instance.HK48,
          Instance.EIL51, Instance.BERLIN52, Instance.BRAZIL58,
          Instance.ST70, Instance.EIL76, Instance.PR76, Instance.GR96,
          Instance.RAT99, Instance.KROA100, Instance.KROB100,
          Instance.KROC100, Instance.KROD100, Instance.KROE100,
          Instance.RD100, Instance.EIL101, Instance.LIN105,
          Instance.PR107, Instance.GR120, Instance.PR124,
          Instance.BIER127, Instance.CH130, Instance.PR136,
          Instance.GR137, Instance.PR144, Instance.CH150,
          Instance.KROA150, Instance.KROB150, Instance.PR152,
          Instance.U159, Instance.SI175, Instance.BRG180, Instance.RAT195,
          Instance.D198, Instance.KROA200, Instance.KROB200,
          Instance.GR202, Instance.TS225, Instance.TSP225, Instance.PR226,
          Instance.GR229, Instance.GIL262, Instance.PR264, Instance.A280,
          Instance.PR299, Instance.LIN318, Instance.RD400, Instance.FL417,
          Instance.GR431, Instance.PR439, Instance.PCB442, Instance.D493,
          Instance.ATT532, Instance.ALI535, Instance.SI535,
          Instance.PA561, Instance.U574, Instance.RAT575, Instance.P654,
          Instance.D657, Instance.GR666, Instance.U724, Instance.RAT783,
          Instance.DSJ1000, Instance.PR1002, Instance.SI1032,
          Instance.U1060, Instance.VM1084, Instance.PCB1173,
          Instance.D1291, Instance.RL1304, Instance.RL1323,
          Instance.NRW1379, Instance.FL1400, Instance.U1432,
          Instance.FL1577, Instance.D1655, Instance.VM1748,
          Instance.U1817, Instance.RL1889, Instance.D2103, Instance.U2152,
          Instance.U2319, Instance.PR2392, Instance.PCB3038,
          Instance.FL3795, Instance.FNL4461, Instance.RL5915,
          Instance.RL5934, Instance.PLA7397, Instance.RL11849,
          Instance.USA13509, Instance.BRD14051, Instance.D15112,
          Instance.D18512, Instance.PLA33810, Instance.PLA85900, }, true); // sorting
                                                                           // is
                                                                           // actually
                                                                           // not
  // necessary, but, well, to
  // be sure...

  /** a subset of the six smallest-scale symmetric instances */
  public static final ArraySetView<Instance> SIX_SMALLEST_SCALE_SYMMETRIC = ArraySetView
      .makeArraySetView(new Instance[] { Instance.BURMA14,
          Instance.ULYSSES16, Instance.GR17, Instance.GR21,
          Instance.ULYSSES22, Instance.GR24 }, true);// sorting is
  // actually not
  // necessary,
  // but, well, to
  // be sure...

  /**
   * the asymmetric instances, ordered by dimension (smaller problems come
   * first)
   */
  public static final ArraySetView<Instance> ASYMMETRIC_INSTANCES = ArraySetView
      .makeArraySetView(new Instance[] { Instance.BR17, Instance.FTV33,
          Instance.FTV35, Instance.FTV38, Instance.P43, Instance.FTV44,
          Instance.FTV47, Instance.RY48P, Instance.FT53, Instance.FTV55,
          Instance.FTV64, Instance.FT70, Instance.FTV70, Instance.KRO124P,
          Instance.FTV170, Instance.RBG323, Instance.RBG358,
          Instance.RBG403, Instance.RBG443, }, true); // sorting is
  // actually not
  // necessary,
  // but, well, to
  // be sure...

  /** the all instances, ordered by dimension (smaller problems come first) */
  public static final ArraySetView<Instance> ALL_INSTANCES = ArraySetView
      .makeArraySetView(new Instance[] { Instance.BURMA14,
          Instance.ULYSSES16, Instance.BR17, Instance.GR17, Instance.GR21,
          Instance.ULYSSES22, Instance.GR24, Instance.FRI26,
          Instance.BAYG29, Instance.BAYS29, Instance.FTV33,
          Instance.FTV35, Instance.FTV38, Instance.DANTZIG42,
          Instance.SWISS42, Instance.P43, Instance.FTV44, Instance.ATT48,
          Instance.FTV47, Instance.GR48, Instance.HK48, Instance.RY48P,
          Instance.EIL51, Instance.BERLIN52, Instance.FT53,
          Instance.FTV55, Instance.BRAZIL58, Instance.FTV64,
          Instance.FT70, Instance.ST70, Instance.FTV70, Instance.EIL76,
          Instance.PR76, Instance.GR96, Instance.RAT99, Instance.KRO124P,
          Instance.KROA100, Instance.KROB100, Instance.KROC100,
          Instance.KROD100, Instance.KROE100, Instance.RD100,
          Instance.EIL101, Instance.LIN105, Instance.PR107,
          Instance.GR120, Instance.PR124, Instance.BIER127,
          Instance.CH130, Instance.PR136, Instance.GR137, Instance.PR144,
          Instance.CH150, Instance.KROA150, Instance.KROB150,
          Instance.PR152, Instance.U159, Instance.FTV170, Instance.SI175,
          Instance.BRG180, Instance.RAT195, Instance.D198,
          Instance.KROA200, Instance.KROB200, Instance.GR202,
          Instance.TS225, Instance.TSP225, Instance.PR226, Instance.GR229,
          Instance.GIL262, Instance.PR264, Instance.A280, Instance.PR299,
          Instance.LIN318, Instance.RBG323, Instance.RBG358,
          Instance.RD400, Instance.RBG403, Instance.FL417, Instance.GR431,
          Instance.PR439, Instance.PCB442, Instance.RBG443, Instance.D493,
          Instance.ATT532, Instance.ALI535, Instance.SI535,
          Instance.PA561, Instance.U574, Instance.RAT575, Instance.P654,
          Instance.D657, Instance.GR666, Instance.U724, Instance.RAT783,
          Instance.DSJ1000, Instance.PR1002, Instance.SI1032,
          Instance.U1060, Instance.VM1084, Instance.PCB1173,
          Instance.D1291, Instance.RL1304, Instance.RL1323,
          Instance.NRW1379, Instance.FL1400, Instance.U1432,
          Instance.FL1577, Instance.D1655, Instance.VM1748,
          Instance.U1817, Instance.RL1889, Instance.D2103, Instance.U2152,
          Instance.U2319, Instance.PR2392, Instance.PCB3038,
          Instance.FL3795, Instance.FNL4461, Instance.RL5915,
          Instance.RL5934, Instance.PLA7397, Instance.RL11849,
          Instance.USA13509, Instance.BRD14051, Instance.D15112,
          Instance.D18512, Instance.PLA33810, Instance.PLA85900, }, true); // sorting
  // is
  // actually
  // not
  // necessary,
  // but,
  // well, to
  // be
  // sure...

  /** the dimension */
  private static final String INSTANCE_NAME = "instanceName"; //$NON-NLS-1$

  /** the dimension */
  private static final String DIMENSION = "dimension"; //$NON-NLS-1$

  /** the best possible objective value */
  private static final String KNOWN_OPTIMUM = "knownOptimumValue";//$NON-NLS-1$

  /** is the benchmark a symmetric or asymmetric tsp? */
  private static final String IS_SYMMETRIC = "isProblemSymmetric";//$NON-NLS-1$

  /** the length of the canonical tour */
  private static final String LENGTH_OF_CANONICAL_TOUR = "lengthOfCanonicalTour";//$NON-NLS-1$

  /**
   * the dimension, i.e., the number of cities
   * 
   * @serial the number of nodes in the TSP, a positive integer value
   */
  private final int m_n;

  /**
   * the best possible objective value
   * 
   * @serial a positive long value indicating the smallest possible tour
   *         length
   */
  private final long m_optimum;

  /**
   * the int hint: can we use integer distance calculation?
   * 
   * @serial a boolean value indicating whether ints can be used to
   *         calculate distances
   */
  private final boolean m_intHint;

  /**
   * is the benchmark a symmetric or asymmetric tsp?
   * 
   * @serial a boolean value which is true for symmetric and false for
   *         asymmetric problems
   */
  private final boolean m_symmetric;

  /**
   * the length of the canonical tour
   * 
   * @serial a positive long value with the length of the tour
   *         {@code 1,2,3...}, or {@code -1l} if the length is not given
   */
  private final long m_canonicalTour;

  /**
   * Create a new benchmark descriptor
   * 
   * @param n
   *          the dimension
   * @param optimum
   *          the best possible objective value
   * @param intHint
   *          the int hint
   * @param name
   *          the name
   * @param symmetric
   *          is the benchmark a symmetric or asymmetric tsp?
   * @param canonical
   *          the length of the canonical tour
   */
  private Instance(final String name, final int n, final long optimum,
      final boolean intHint, final boolean symmetric, final long canonical) {
    super(name);
    this.m_n = n;
    this.m_optimum = optimum;
    this.m_intHint = intHint;
    this.m_symmetric = symmetric;
    this.m_canonicalTour = canonical;
  }

  /**
   * Get the length of the canonical tour, or {@code -1} if the length is
   * not known
   * 
   * @return the length of the canonical tour, or {@code -1} if the length
   *         is not known
   */
  public final long canonicalTourLength() {
    return this.m_canonicalTour;
  }

  /**
   * get the dimension of the benchmark instance
   * 
   * @return the dimension
   */
  public final int n() {
    return this.m_n;
  }

  /**
   * the optimal distance value
   * 
   * @return the optimal distance value
   */
  public final long optimum() {
    return this.m_optimum;
  }

  /**
   * the integer hint
   * 
   * @return the integer hint
   */
  public final boolean intHint() {
    return this.m_intHint;
  }

  /**
   * is the benchmark symmetric
   * 
   * @return the benchmark symmetric
   */
  public final boolean symmetric() {
    return this.m_symmetric;
  }

  /**
   * load the benchmark case
   * 
   * @return the distance computer
   * @param matrixLimitDim
   *          the highest dimension for which coordinate lists will
   *          automatically be transformed to matrices.
   * @throws IOException
   *           the io exception if io fails
   */
  public final DistanceComputer load(final int matrixLimitDim)
      throws IOException {
    DistanceComputer c;
    try (InputStream is = Instance.class.getResourceAsStream(this.name()
        + (this.m_symmetric ? ".tsp" : ".atsp"))) {//$NON-NLS-1$//$NON-NLS-2$
      c = DistanceComputer.read(is, matrixLimitDim, this.m_intHint,
          this.m_symmetric);
      if (c.n() != this.m_n) {
        throw new IOException("dimensions incompatible!"); //$NON-NLS-1$
      }
      return c;
    }
  }

  /** {@inheritDoc} */
  @Override
  public final int compareTo(final Instance o) {
    final int cr;

    if (o == null) {
      return -1;
    }
    if (o == this) {
      return 0;
    }

    cr = Integer.compare(this.m_n, o.m_n);
    if (cr != 0) {
      return cr;
    }
    return String.CASE_INSENSITIVE_ORDER.compare(this.name(), o.name());
  }

  /** {@inheritDoc} */
  @Override
  public final void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable.printKey(Instance.INSTANCE_NAME, ps);
    ps.println(this.name());

    Configurable.printKey(Instance.DIMENSION, ps);
    ps.println(this.m_n);

    Configurable.printKey(Instance.KNOWN_OPTIMUM, ps);
    ps.println(this.m_optimum);

    Configurable.printKey(Instance.IS_SYMMETRIC, ps);
    ps.println(this.m_symmetric ? '1' : '0');

    if (this.m_canonicalTour > 0l) {
      Configurable.printKey(Instance.LENGTH_OF_CANONICAL_TOUR, ps);
      ps.println(this.m_canonicalTour);
    }
  }

  /**
   * Return this instance - as instances are immutable.
   * 
   * @return {@code this}
   */
  @Override
  public final Instance clone() {
    return this;
  }

  /**
   * Get the instance belonging to a given name
   * 
   * @param name
   *          the instance name
   * @return the instance
   */
  public static final Instance forName(final String name) {
    for (final Instance inst : Instance.ALL_INSTANCES) {
      if (inst.name().equalsIgnoreCase(name)) {
        return inst;
      }
    }
    return null;
  }
}
