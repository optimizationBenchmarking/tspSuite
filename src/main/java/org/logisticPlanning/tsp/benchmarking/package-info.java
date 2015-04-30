/**
 * <p>
 * The code an benchmark instances for measuring the performance of a TSP
 * solver. The benchmark instances are taken from <a
 * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
 * >TSPLib</a>&nbsp;[<a href="#cite_DACO1995TSPLIB"
 * style="font-weight:bold">1</a>, <a href="#cite_R1995T9"
 * style="font-weight:bold">2</a>, <a href="#cite_R1991ATSPL"
 * style="font-weight:bold">3</a>, <a href="#cite_W2003ROCFTB"
 * style="font-weight:bold">4</a>] Efficient wrapper classes for fast
 * computation and good cache utilization are provided, as well as an
 * objective function class which performs in-memory logging.
 * </p>
 * <p>
 * The <a href="http://en.wikipedia.org/wiki/Travelling_salesman_problem">
 * Traveling Salesman Problem</a> (TSP) is one of the most well-studied
 * problems in combinatorial optimization. In a TSP, a set of cities and
 * the distances between them are given. The traveling salesman starts his
 * tour in one of the cities and has to visit all other cities before being
 * able to return home (to the city he started from). His goal is to visit
 * all cities as quickly as possible, i.e., to find the shortest possible
 * cyclic tour from his origin through all cities and back to his origin.
 * The travel distance between every two cities is known. The optimization
 * version of the TSP is <a href=
 * "http://en.wikipedia.org/wiki/Travelling_salesman_problem#Computational_complexity"
 * ><em>NP-hard</em></a>&nbsp;[<a href="#cite_GP2004TTSPAIV"
 * style="font-weight:bold">5</a>, <a href="#cite_G1985AGT"
 * style="font-weight:bold">6</a>, <a href="#cite_H2012TSPTMMP"
 * style="font-weight:bold">7</a>].
 * </p>
 * <p>
 * If the TSP is symmetric (and then called STSP), the cost function takes
 * on the same value when traveling from city {@code a} to city {@code b}
 * than when traveling from {@code b} to {@code a}. If the problem is
 * asymmetric (and then called ATST), the two values may differ.
 * </p>
 * <p>
 * TSPs have many applications&nbsp;[<a href="#cite_LRK1975SSAOTTSP"
 * style="font-weight:bold">8</a>, <a href="#cite_MSM2010TSPAOOAFASA"
 * style="font-weight:bold">9</a>], not only in logistics, but also in
 * other domains, such as drilling of printed circuit boards&nbsp;[<a
 * href="#cite_GJR1991OCOPADMACS" style="font-weight:bold">10</a>], X-Ray
 * crystallography&nbsp;[<a href="#cite_BS1989LTSPAFEIXRCAPROC"
 * style="font-weight:bold">11</a>], and computer wiring&nbsp;[<a
 * href="#cite_LRK1975SSAOTTSP" style="font-weight:bold">8</a>].
 * </p>
 * <h2>State-of-the-Art</h2>
 * <p>
 * To the best of our knowledge, three methods to solve TSPs seem to
 * perform best:
 * </p>
 * <ol>
 * <li><a
 * href="http://en.wikipedia.org/wiki/Lin%E2%80%93Kernighan_heuristic"
 * >Lin-Kerninghan</a>&nbsp;[<a href="#cite_LK1973AEHAFTTSP"
 * style="font-weight:bold">12</a>] based iterated local search
 * methods&nbsp;[<a href="#cite_ABCC1999FTITTFT"
 * style="font-weight:bold">13</a>], in particular Keld Helsgaun's
 * algorithm variant&nbsp;[<a href="#cite_H2012L"
 * style="font-weight:bold">14</a>, <a href="#cite_H2009GKOSFTLKTH"
 * style="font-weight:bold">15</a>, <a href="#cite_H1998AEIOTLKTSH"
 * style="font-weight:bold">16</a>] and its improvements&nbsp;[<a
 * href="#cite_NYYY2007IOAEHGFLSTSP" style="font-weight:bold">17</a>, <a
 * href="#cite_RGJM2007ITEOHLKHFTST" style="font-weight:bold">18</a>],</li>
 * <li>the Branch-and-Bound method by Zhang&nbsp;[<a
 * href="#cite_Z1999TAADFBABACSOTATSP" style="font-weight:bold">19</a>, <a
 * href="#cite_Z1993TBABACSOTATSP" style="font-weight:bold">20</a>], and</li>
 * <li>the Cutting-Plane approach implemented by Applegate et al.&nbsp;[<a
 * href="#cite_ABCC2001TCWDNCTTTP" style="font-weight:bold">21</a>, <a
 * href="#cite_ABCC2003ITDFJAFLTSP" style="font-weight:bold">22</a>, <a
 * href="#cite_W2011CTS" style="font-weight:bold">23</a>, <a
 * href="#cite_W2003ROCFTB" style="font-weight:bold">4</a>] in the
 * &quot;Concorde&quot; framework.</li>
 * </ol>
 * . <h2>History</h2>
 * <p>
 * The TSP was first introduced in the 1830s in a book&nbsp;[<a
 * href="#cite_V1832DHWESSUWEZTHUAZEUEGE" style="font-weight:bold">24</a>]
 * which discusses, well traveling salesmen, but without mathematical
 * treatment. It gained more interest in the 1950s and 60s, most notably
 * due to the work of Dantzig, Fulkerson, and Johnson&nbsp;[<a
 * href="#cite_DFJ1954SOALSTSP" style="font-weight:bold">25</a>] who solved
 * a problem with 49 cities in the USA by representing it as a integer
 * linear program and developing the cutting plane method.
 * </p>
 * <h2>Literature</h2>
 * <p>
 * There are a lot of great books focused on the TSP&nbsp;[<a
 * href="#cite_ABCC2006TTSPACS" style="font-weight:bold">26</a>, <a
 * href="#cite_G2008TSP" style="font-weight:bold">27</a>, <a
 * href="#cite_LLKS1985TTSPAGTOCO" style="font-weight:bold">28</a>, <a
 * href="#cite_GP2004TTSPAIV" style="font-weight:bold">5</a>, <a
 * href="#cite_D2010TSPTAA" style="font-weight:bold">29</a>, <a
 * href="#cite_C2011IPOTTSMATLOC" style="font-weight:bold">30</a>] and
 * websites&nbsp;[<a href="#cite_C2011TSP" style="font-weight:bold">31</a>,
 * <a href="#cite_C2008TTSP" style="font-weight:bold">32</a>, <a
 * href="#cite_S2011NSTSPP" style="font-weight:bold">33</a>, <a
 * href="#cite_W2011CTS" style="font-weight:bold">23</a>, <a
 * href="#cite_M1999TAIAAEOHA" style="font-weight:bold">34</a>]. There also
 * are many great software collections for it&nbsp;[<a
 * href="#cite_LP2004TS" style="font-weight:bold">35</a>, <a
 * href="#cite_LPB2010TS" style="font-weight:bold">36</a>, <a
 * href="#cite_H2012L" style="font-weight:bold">14</a>, <a
 * href="#cite_HCS2008TSBARTS" style="font-weight:bold">37</a>]. Important
 * surveys on different algorithms are given in&nbsp;[<a
 * href="#cite_SGLR2000ACONIHOTTSP" style="font-weight:bold">38</a>, <a
 * href="#cite_GGRVG1985GAFTT" style="font-weight:bold">39</a>, <a
 * href="#cite_JMG2004EAOHFTS" style="font-weight:bold">40</a>, <a
 * href="#cite_JGMCYZZ2004EAOHFTA" style="font-weight:bold">41</a>].
 * Furthermore, this family of problems has been tackled with many
 * different algorithms, such as
 * </p>
 * <ul>
 * <li>General Heuristic Methods&nbsp;[<a href="#cite_JMG2004EAOHFTS"
 * style="font-weight:bold">40</a>, <a href="#cite_JMB1997TTSPACSILO"
 * style="font-weight:bold">42</a>, <a href="#cite_N2003HOTTSP"
 * style="font-weight:bold">43</a>, <a href="#cite_K1956OTSSSOAGATTSP"
 * style="font-weight:bold">44</a>, <a href="#cite_ACR2003CLKFLTSP"
 * style="font-weight:bold">45</a>, <a href="#cite_NN2008CGAAGLSMBSTI"
 * style="font-weight:bold">46</a>, <a href="#cite_RS2012AEGIOAMSTSFLPI"
 * style="font-weight:bold">47</a>, <a href="#cite_H1998AEIOTLKTSH"
 * style="font-weight:bold">16</a>, <a href="#cite_CW1964SOVFACDTANODP"
 * style="font-weight:bold">48</a>],</li>
 * <li>Cutting Plane Methods&nbsp;[<a href="#cite_ABCC2003ITDFJAFLTSP"
 * style="font-weight:bold">22</a>, <a href="#cite_ABCC2001TCWDNCTTTP"
 * style="font-weight:bold">21</a>],</li>
 * <li>Branch-and-Bound Methods&nbsp;[<a href="#cite_HK1970TTSPAMST"
 * style="font-weight:bold">49</a>, <a href="#cite_LMSK1963AAFTTSP2"
 * style="font-weight:bold">50</a>, <a href="#cite_CT1980SNBABCFTATSP"
 * style="font-weight:bold">51</a>, <a href="#cite_Z1999TAADFBABACSOTATSP"
 * style="font-weight:bold">19</a>, <a href="#cite_Z1993TBABACSOTATSP"
 * style="font-weight:bold">20</a>],</li>
 * <li>Evolutionary Algorithms (EAs) and Genetic Algorithms (GAs)&nbsp;[<a
 * href="#cite_F1988AEATTTSP" style="font-weight:bold">52</a>, <a
 * href="#cite_M1996GADSEP" style="font-weight:bold">53</a>, <a
 * href="#cite_JSV1991PGAATTSP" style="font-weight:bold">54</a>, <a
 * href="#cite_WSF1989SPATSTGERO" style="font-weight:bold">55</a>, <a
 * href="#cite_GM1998IOOFTT" style="font-weight:bold">56</a>, <a
 * href="#cite_B2000GAATTSP" style="font-weight:bold">57</a>, <a
 * href="#cite_SD2006ARKGAFTGTSP" style="font-weight:bold">58</a>, <a
 * href="#cite_YWL2010AFEAFTSP" style="font-weight:bold">59</a>, <a
 * href="#cite_WY2004AHEAFTSP" style="font-weight:bold">60</a>, <a
 * href="#cite_YLYW2007AFEAFTSP" style="font-weight:bold">61</a>, <a
 * href="#cite_BW2007PEAFTTSP" style="font-weight:bold">62</a>, <a
 * href="#cite_T2009ESFTTSPBAPBEA" style="font-weight:bold">63</a>, <a
 * href="#cite_YC2011AEGAWFCMCFTSP" style="font-weight:bold">64</a>, <a
 * href="#cite_WHH2009TBOPCFTTSP" style="font-weight:bold">65</a>, <a
 * href="#cite_GPL2009GAPGAFFNOSTTTSP" style="font-weight:bold">66</a>, <a
 * href="#cite_NN2008CGAAGLSMBSTI" style="font-weight:bold">46</a>, <a
 * href="#cite_JM2002TMROGEACFTTDET" style="font-weight:bold">67</a>],</li>
 * <li>Memetic Algorithms (MAs)&nbsp;[<a href="#cite_BFM1999MA"
 * style="font-weight:bold">68</a>, <a href="#cite_MF2001MAFTTSP"
 * style="font-weight:bold">69</a>, <a href="#cite_BHP2001AHHFTTSP"
 * style="font-weight:bold">70</a>, <a href="#cite_WY2004AHEAFTSP"
 * style="font-weight:bold">60</a>],</li>
 * <li>Ant Colony Optimization (ACO)&nbsp;[<a href="#cite_DG1996ACFTTSP"
 * style="font-weight:bold">71</a>, <a href="#cite_GD1996ACO"
 * style="font-weight:bold">72</a>, <a href="#cite_DG1997ACOCTSP"
 * style="font-weight:bold">73</a>, <a href="#cite_SH1997MMASALSFTTSP"
 * style="font-weight:bold">74</a>, <a href="#cite_TTCY2010AFACOFTSP"
 * style="font-weight:bold">75</a>, <a
 * href="#cite_KYMYY2010APFZCOHGAFLSTSP" style="font-weight:bold">76</a>,
 * <a href="#cite_YC2008ANRGATTSP" style="font-weight:bold">77</a>, <a
 * href="#cite_FTSPL2007AGAFTGTSP" style="font-weight:bold">78</a>, <a
 * href="#cite_AW2012ITPOMMASOTTUSA" style="font-weight:bold">79</a>, <a
 * href="#cite_G2004AAISAMCE" style="font-weight:bold">80</a>, <a
 * href="#cite_GM2001ACODTSP" style="font-weight:bold">81</a>, <a
 * href="#cite_GMS2001ACODTSP" style="font-weight:bold">82</a>, <a
 * href="#cite_OHSRD2011ADAOTPBACOAFTTATQ" style="font-weight:bold">83</a>,
 * <a href="#cite_OHSRD2011ADAOTPBACOAFTTATQ2"
 * style="font-weight:bold">84</a>, <a href="#cite_LG2003DACOFT"
 * style="font-weight:bold">85</a>, <a href="#cite_Z2009RAOAACOAFTI"
 * style="font-weight:bold">86</a>],</li>
 * <li>Simulated Annealing&nbsp;[<a href="#cite_KGV1983SA"
 * style="font-weight:bold">87</a>, <a href="#cite_VC1985SA"
 * style="font-weight:bold">88</a>, <a href="#cite_LXL2009HSAABOACSFT"
 * style="font-weight:bold">89</a>, <a href="#cite_MGPO1989SAPSAATSAFTTSP"
 * style="font-weight:bold">90</a>],</li>
 * <li>Tabu Search&nbsp;[<a href="#cite_CG1997TSTSP"
 * style="font-weight:bold">91</a>, <a href="#cite_MGPO1989SAPSAATSAFTTSP"
 * style="font-weight:bold">90</a>],</li>
 * <li>Particle Swarm Algorithm (PSO)&nbsp;[<a
 * href="#cite_ZZC2007ANDPSOTSTSP" style="font-weight:bold">92</a>],</li>
 * <li>Estimation of Distribution Algorithms (EDAs)&nbsp;[<a
 * href="#cite_RDML2001STTSPWE" style="font-weight:bold">93</a>, <a
 * href="#cite_T2002PMBGAIPRDUEH" style="font-weight:bold">94</a>, <a
 * href="#cite_T2009POAEAOAPWMCP" style="font-weight:bold">95</a>, <a
 * href="#cite_T2009EOUPSIEHSAWDLS" style="font-weight:bold">96</a>, <a
 * href="#cite_TPG2006NHVEHACOPIPD" style="font-weight:bold">97</a>, <a
 * href="#cite_TPG2006EHBSWLSFSPP" style="font-weight:bold">98</a>],</li>
 * <li>Developmental Mappings in EAs&nbsp;[<a href="#cite_OWDC2013SADAFTSP"
 * style="font-weight:bold">99</a>], and</li>
 * <li>all sorts of hybrid algorithms&nbsp;[<a
 * href="#cite_CHZ2012APBGAWBMARHFTTSP" style="font-weight:bold">100</a>,
 * <a href="#cite_BHP2001AHHFTTSP" style="font-weight:bold">70</a>, <a
 * href="#cite_WY2004AHEAFTSP" style="font-weight:bold">60</a>, <a
 * href="#cite_YC2011AEGAWFCMCFTSP" style="font-weight:bold">64</a>, <a
 * href="#cite_I2010CGSCWLSFTTSP" style="font-weight:bold">101</a>, <a
 * href="#cite_PI2011AHHAFSTGTSP" style="font-weight:bold">102</a>, <a
 * href="#cite_LXL2009HSAABOACSFT" style="font-weight:bold">89</a>, <a
 * href="#cite_NYYY2007IOAEHGFLSTSP" style="font-weight:bold">17</a>].</li>
 * </ul>
 * <p>
 * Many theoretical works are available as well&nbsp;[<a
 * href="#cite_CEG2010GDPIFTSTSP" style="font-weight:bold">103</a>, <a
 * href="#cite_CEG2005CWDPIFT" style="font-weight:bold">104</a>, <a
 * href="#cite_CV2004OTHKRFTAASTSP" style="font-weight:bold">105</a>, <a
 * href="#cite_W1990AOTHKHFTTSP" style="font-weight:bold">106</a>]
 * </p>
 * <p>
 * An established set of benchmark cases for TSPs is the <a
 * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
 * >TSPLib</a>&nbsp;[<a href="#cite_DACO1995TSPLIB"
 * style="font-weight:bold">1</a>, <a href="#cite_R1995T9"
 * style="font-weight:bold">2</a>, <a href="#cite_R1991ATSPL"
 * style="font-weight:bold">3</a>, <a href="#cite_W2003ROCFTB"
 * style="font-weight:bold">4</a>, <a href="#cite_W2003ROCFTB"
 * style="font-weight:bold">4</a>], which we also use in our works.
 * </p>
 * <h2>Implementation Guide for TSP Algorithms</h2>
 * <p id="implementingAnAlgorithm">
 * The TSP algorithm evaluation framework provides automation for most of
 * the steps necessary to conduct an experiment. It will, for example,
 * automatically apply your algorithm to several benchmark sets,
 * automatically in parallel with as many CPUs you have available, and
 * store its results in files. In order to use these features, there are a
 * set of implementation requirements that must be followed. We discuss
 * them in the following.
 * </p>
 * <ol>
 * <li>
 * <p>
 * Your algorithm must be implemented as subclass of the class
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithm TSPAlgorithm} and
 * override the method
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithm#solve(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction)
 * solve(ObjectiveFunction)}. This method is where you must put your
 * algorithm implementation. It has no return value, since the
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction
 * ObjectiveFunction} will collect all information (including your solution
 * and the objective values) automatically. At the same time, the
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction
 * ObjectiveFunction} is also the source of all information for your
 * algorithm. It provides you, e.g., the the
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#n()
 * number} of cities in the TSP, the
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#distance(int, int)
 * distance} between two cities, and ways to evaluate the total distance of
 * a tour represented as
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#evaluate(int[])
 * city permutation} or
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#evaluateAdj(int[])
 * adjacency list}, amongst others.
 * </p>
 * <p>
 * Some example algorithm implementations can be found
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.hc.UpdatingPermutationHillClimber
 * here},
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.PermutationRNS
 * here}, and
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco.PACO
 * here}.
 * </p>
 * </li>
 * <li>
 * <p>
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithm TSPAlgorithm} has a
 * constructor which takes a {@link java.lang.String String} holding the
 * algorithm name as parameter. Your own algorithm class must implement a
 * public zero-parameter constructor. This constructor must call the
 * inherited constructor and supply the name. The name can contain spaces
 * and should be the same name that you would also use in a paper or other
 * document for your algorithm. For example &quot;
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.doubleEndedNearestNeighbor.DoubleEndedNearestNeighborHeuristic
 * Double-Ended Nearest Neighbor Heuristic}&quot; is such a name.
 * </p>
 * </li>
 * <li>
 * <p>
 * The benchmark environment supports multi-threading, i.e., it will
 * execute as many runs in parallel as possible (1 thread per CPU), in
 * order to speed up the experiment. However,
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithm algorithms} do not
 * need to be thread-safe (and
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction
 * objective functions} are not anyway). One instance of your algorithm
 * will always and only be accessed/executed by one single thread.
 * </p>
 * <p id="cloneAlgo">
 * This is possible by first creating one instance of your algorithm and
 * then {@link java.lang.Object#clone() cloning} it for each thread. On one
 * hand, you do not need to take care of synchronization (and we do not
 * waste runtime for such stuff). On the other hand, this also means that
 * you need to take good care of member variables and copy them by
 * overriding the {@link org.logisticPlanning.utils.NamedObject#clone()
 * clone method}. If your algorithm stores mutable objects as member
 * variables, then you must also clone them properly. Otherwise, the
 * instances of your algorithm that run in parallel will work on the same
 * data structures and produce corrupt results.
 * </p>
 * <p>
 * It is common that your algorithm may hold a member variable of type,
 * say, {@code double[]}, to avoid memory allocation in subsequent runs.
 * When cloning, this variable must be set to either {@code null} in the
 * clone or cloned as well. Otherwise, both algorithm instances (the
 * original and the clone) will point to the same array, use it in
 * parallel, and wreck havoc. Zounds! The same holds for any references to
 * objects or sub-algorithms that your method uses. As soon as these
 * objects are mutable or may hold variables that may change, you must
 * clone them as well.
 * </p>
 * <p>
 * Some examples for cloneable algorithms are given
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.hc.UpdatingPermutationHillClimber#clone()
 * here},
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch.MultiNeighborhoodSearch#clone()
 * here}, and
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco.PACO#clone()
 * here}.
 * </p>
 * </li>
 * <li>
 * <p>
 * Many algorithms have parameters. An
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.EA}
 * may have a
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.EA#setCrossoverRate(double)
 * crossover rate}, the
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco.PACO
 * population-based ACO} algorithm has an
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco.PACO#setAlpha(double)
 * alpha} and a
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco.PACO#setBeta(double)
 * beta} parameter, for instance. Our framework provides the ability of
 * setubg algorithm parameters via the command line or an ini-file and to
 * log these parameter values into the log files automatically. This is
 * very useful, as the algorithm settings should always be part of the
 * experiment documentation and this way, that is ensured automatically.
 * </p>
 * <p>
 * In order to make use of this facility, you should override the following
 * three methods, which are inherited from the class
 * {@link org.logisticPlanning.utils.config.Configurable Configurable}:
 * </p>
 * <ol>
 * <li>
 * {@link org.logisticPlanning.utils.config.Configurable#printParameters(java.io.PrintStream)
 * printParameters} should print &quot;parameter-name
 * parameter-description&quot; pairs to the {@link java.io.PrintStream
 * PrintStream} that it gets as parameter. The user can invoke the
 * benchmarker with the {@code -help} command line option and will then get
 * this information displayed. You must always call the
 * super-implementation of this method before executing own code.</li>
 * <li>The method
 * {@link org.logisticPlanning.utils.config.Configurable#printConfiguration(java.io.PrintStream)
 * printConfiguration}, too, gets a {@link java.io.PrintStream PrintStream}
 * as parameter. It prints the configuration of the object to that stream
 * in form of &quot;parameter-name parameter-value&quot; pairs. This method
 * is called by the benchmarking environment when the log files are
 * written. You must always call the super-implementation of this method
 * before executing own code.</li>
 * <li id="overrideConfigure">
 * {@link org.logisticPlanning.utils.config.Configurable#configure(org.logisticPlanning.utils.config.Configuration)
 * configure} receives an instance of the class
 * {@link org.logisticPlanning.utils.config.Configuration Configuration}.
 * This class has loaded all parameters from the command line (and possible
 * ini-files) and provides several convenient methods to access them, e.g.,
 * {@link org.logisticPlanning.utils.config.Configuration#getString(String, String)
 * getString},
 * {@link org.logisticPlanning.utils.config.Configuration#getFile(String, java.io.File)
 * getFile}, and
 * {@link org.logisticPlanning.utils.config.Configuration#getLong(String, long, long, long)
 * getLing}, each taking a parameter name, a default values (and possible
 * valid range limits) as parameter and returning a value of the type
 * implied by their name. You must always call the super-implementation of
 * this method before executing own code in order to set the parameters of
 * your algorithm.</li>
 * </ol>
 * <p>
 * Some examples for configurable algorithms are given
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.EA
 * here},
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch.MultiNeighborhoodSearch
 * here}, and
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco.PACO
 * here}.
 * </p>
 * </li>
 * <li>
 * <p>
 * Starting at version 0.9.8 of TSP Suite, we provide run-depending
 * initialization and finalization support for algorithms and algorithm
 * modules. The new class
 * {@link org.logisticPlanning.tsp.solving.TSPModule} is introduced as
 * base-class for all algorithm modules (such as
 * {@link org.logisticPlanning.tsp.solving.operators.Operator search
 * operators} or {@link org.logisticPlanning.tsp.solving.gpm.GPM
 * genotype-phenotype mappings}) and the
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithm TSP algorithms}
 * themselves. This new class provides two methods,
 * {@link org.logisticPlanning.tsp.solving.TSPModule#beginRun(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction)}
 * and
 * {@link org.logisticPlanning.tsp.solving.TSPModule#endRun(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction)}
 * which must be called before and after a run, respectively. Each
 * algorithm module must then invoke them recursively for all of its
 * sub-components. The new method
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithm#call(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction)}
 * now acts as a wrapper for
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithm#solve(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction)}
 * and invokes them. This allows for a more targeted allocation and
 * de-allocation of data structures for each run.
 * </p>
 * </li>
 * <li>
 * <p id="MyHeuristic">
 * The last step is to provide a {@code main} method which invokes the
 * benchmarking system on your algorithm class. Assume that your class has
 * the name {@code MyHeuristic}, usually it would contain a method
 * {@code main} as given <a href="#invokeMyHeuristic">below</a>,
 * </p>
 * <table border="1" id="invokeMyHeuristic" style="margin-left:auto;margin-right:auto">
 * <tr>
 * <td>
 * 
 * <pre class="altColor">
 * public static void main(final String[] args) {
 *  {@link org.logisticPlanning.tsp.solving.TSPAlgorithmRunner TSPAlgorithmRunner}.{@link org.logisticPlanning.tsp.solving.TSPAlgorithmRunner#benchmark(org.logisticPlanning.utils.collections.lists.ArrayListView, java.lang.Class, java.lang.String[]) benchmark}({@link org.logisticPlanning.tsp.benchmarking.instances.Instance#SYMMETRIC_INSTANCES Instance.SYMMETRIC_INSTANCES},
 *  MyHeuristic.class,
 *  args);
 *  }
 * </pre>
 * 
 * </td>
 * </tr>
 * <tr>
 * <td>The main method which invokes the benchmarking system to test the
 * algorithm {@code MyHeuristic} for all
 * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#SYMMETRIC_INSTANCES
 * symmetric} <a
 * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
 * >TSPLib</a>&nbsp;[<a href="#cite_DACO1995TSPLIB"
 * style="font-weight:bold">1</a>, <a href="#cite_R1995T9"
 * style="font-weight:bold">2</a>, <a href="#cite_R1991ATSPL"
 * style="font-weight:bold">3</a>, <a href="#cite_W2003ROCFTB"
 * style="font-weight:bold">4</a>] instances.</td>
 * </tr>
 * </table>
 * <p>
 * In the <a href="#invokeMyHeuristic">above example</a>, the
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithmRunner
 * TSPAlgorithmRunner} will automatically apply your algorithm to all
 * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#SYMMETRIC_INSTANCES
 * symmetric} <a
 * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
 * >TSPLib</a>&nbsp;[<a href="#cite_DACO1995TSPLIB"
 * style="font-weight:bold">1</a>, <a href="#cite_R1995T9"
 * style="font-weight:bold">2</a>, <a href="#cite_R1991ATSPL"
 * style="font-weight:bold">3</a>, <a href="#cite_W2003ROCFTB"
 * style="font-weight:bold">4</a>] instances. If your algorithm can solve
 * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#ASYMMETRIC_INSTANCES
 * asymmetric} TSPs, you can instead choose
 * <code>{@link org.logisticPlanning.tsp.benchmarking.instances.Instance#ALL_INSTANCES Instance.ALL_INSTANCES}</code>
 * as parameter of the method
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithmRunner#benchmark(org.logisticPlanning.utils.collections.lists.ArrayListView, java.lang.Class, java.lang.String[])
 * benchmark}. Then, your algorithm will be applied to all (symmetric and
 * asymmetric) instances of <a
 * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
 * >TSPLib</a>.
 * </p>
 * <p>
 * The second parameter of that method is the class of your algorithm
 * implementation, here {@code MyHeuristic} (which must be derived from
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithm TSPAlgorithm}), and
 * the third parameter is the array of command line arguments that has been
 * passed to your program (this will be used to construct the
 * {@link org.logisticPlanning.utils.config.Configuration Configuration}
 * instance to be handed to your algorithm's
 * {@link org.logisticPlanning.utils.config.Configurable#configure(org.logisticPlanning.utils.config.Configuration)
 * configure} method).
 * </p>
 * <p>
 * Once invoked, the
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithmRunner#benchmark(org.logisticPlanning.utils.collections.lists.ArrayListView, java.lang.Class, java.lang.String[])
 * benchmark} method will first instantiate your algorithm class (via the
 * zero-parameter, public constructor), then
 * {@link org.logisticPlanning.utils.config.Configuration Configuration}
 * instance to be handed to your algorithm's
 * {@link org.logisticPlanning.utils.config.Configurable#configure(org.logisticPlanning.utils.config.Configuration)
 * configure} it, and create one {@link java.lang.Object#clone() clone} of
 * the configured instance for each available processor. It then
 * step-by-step loads one
 * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance problem
 * instance} after the other and applies the algorithm. Each clone of your
 * algorithm will perform a separate run in a separate thread, receiving a
 * separate instance of
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction
 * ObjectiveFunction}. Once a run is completed, the log information is
 * written to the log file, along with your algorithm's
 * {@link org.logisticPlanning.utils.config.Configurable#printConfiguration(java.io.PrintStream)
 * configuration}.
 * </p>
 * </li>
 * <li id="testingTheAlgorithm">
 * <p>
 * Along with our benchmark code, we also provide a <a
 * href="https://en.wikipedia.org/wiki/Unit_testing">unit testing</a>
 * facility based on <a href="http://www.junit.org/">JUnit</a>&nbsp;[<a
 * href="#cite_B2009JPG" style="font-weight:bold">107</a>, <a
 * href="#cite_RS2005JRPMFPT" style="font-weight:bold">108</a>, <a
 * href="#cite_MH2004JIA" style="font-weight:bold">109</a>]. It is strongly
 * recommended that you run tests with your algorithm before doing real
 * experiments. Of course, our testing facility cannot decide whether your
 * algorithm is right or wrong, but it may be able to detect some
 * programming mistakes. (The current version cannot check if there are
 * concurrency-related errors, resulting from a missing or faulty cloning
 * method, see <a href="#cloneAlgo">here</a>).
 * </p>
 * <p>
 * You can easily create a unit test for your algorithm by extending the
 * class
 * {@link test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest
 * TSPAlgorithmSymmetricTest} if your algorithm can only solve
 * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#SYMMETRIC_INSTANCES
 * symmetric} TSP instances and
 * {@link test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest
 * TSPAlgorithmSymmetricTest} if it can also solve asymmetric instances. In
 * your new test, you only need to override the method
 * {@link test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest
 * createAlgorithm}. All what this method does is to return an instance of
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithm TSPAlgorithm} that
 * should be tested &ndash; in this case, an instance of your algorithm.
 * </p>
 * <p>
 * Assume that your algorithm is implemented as class {@code MyHeuristic}
 * in package {@code MyPackage}. If {@code MyHeuristic} is a solver for
 * symmetric TSPs, then the test class will look like the listing <a
 * href="#testMyHeuristic">below</a>. For solvers that also understand,
 * just replace {@code TSPAlgorithmSymmetricTest} with
 * {@code TSPAlgorithmAsymmetricTest}.
 * </p>
 * <table border="1" id="testMyHeuristic" style="margin-left:auto;margin-right:auto">
 * <tr>
 * <td>
 * 
 * <pre class="altColor">
 * package test.junit.{@code MyPackage}.{@code MyHeuristic};
 * 
 *  import {@code MyPackage}.{@code MyHeuristic};
 * 
 *  import {@link test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest};
 * 
 *  public class {@code MyHeuristic}Test extends {@link test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest TSPAlgorithmSymmetricTest} {
 * 
 *  public {@code MyHeuristic}Test() {
 *  super();
 *  }
 * 
 *  {@code @Override}
 *  protected {@code MyHeuristic} createAlgorithm() {
 *  return new {@code MyHeuristic}();
 *  }
 *  }
 * </pre>
 * 
 * </td>
 * </tr>
 * <tr>
 * <td>A unit test for algorithm {@code MyHeuristic}.</td>
 * </tr>
 * </table>
 * <p>
 * The unit test will apply the algorithm to several small scale problems
 * with different runtime settings. If the algorithm produces invalid
 * results in these tests, the tests will fail. You must never use an
 * algorithm that failed one of the tests in an experiment.
 * </p>
 * <p>
 * Examples for tests can be found
 * {@link test.junit.org.logisticPlanning.tsp.solving.algorithms.heuristics.mst.MSTHeuristicTest
 * here},
 * {@link test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco.PACOTest
 * here}, and
 * {@link test.junit.org.logisticPlanning.tsp.solving.algorithms.heuristics.edgeGreedy.EdgeGreedyHeuristicTest
 * here}.
 * </p>
 * </li>
 * </ol>
 * <p>
 * This way of making use of a multi-threaded environment, by doing as many
 * runs with separate copies of an algorithm, is more efficient than
 * starting several instances of the benchmarking process in parallel
 * (which still is possible). The reason is that the runs being performed
 * in parallel may share the same distance computers, as distance computers
 * are immutable and we therefore can save both memory and improve caching.
 * </p>
 * <h2>Running Experiments</h2>
 * <p id="runningExperiments">
 * So you have implemented <em>and tested</em> your algorithm (let's call
 * it again {@code MyHeuristic}). The <em>TSP Suite</em> system can
 * automatically run experiments with it. More precisely, it can
 * automatically apply the algorithm to the problem instance from the
 * well-known <a
 * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
 * >TSPLib</a>&nbsp;[<a href="#cite_DACO1995TSPLIB"
 * style="font-weight:bold">1</a>, <a href="#cite_R1995T9"
 * style="font-weight:bold">2</a>, <a href="#cite_R1991ATSPL"
 * style="font-weight:bold">3</a>, <a href="#cite_W2003ROCFTB"
 * style="font-weight:bold">4</a>] benchmark set. During such an
 * experiment, your algorithm will be applied to each of the symmetric
 * instances of this benchmark set (and also the asymmetric ones, if you
 * have specified that in the <a href="#invokeMyHeuristic">main</a> method)
 * for <code>30</code> independent trials taking up to one hour each (under
 * default settings).
 * </p>
 * <p>
 * The general way to conduct an experiment is as follows:
 * </p>
 * <ol>
 * <li id="makeAlgorithmJar">
 * <p>
 * Generate a {@code jar} archive including your algorithms code. Let's say
 * you called your archive {@code myHeuristicJar.jar}. If you are using <a
 * href="http://www.eclipse.org/">Eclipse</a>&nbsp;[<a href="#cite_ECLIPSE"
 * style="font-weight:bold">110</a>], you could do that as follows:
 * </p>
 * <ol>
 * <li>Right-click on your project in Eclipse's package view.</li>
 * <li>Choose point &quot;Export&uot; in the pop-up menu coming up.</li>
 * <li>In the dialog that emerges select &quot;JAR file&quot; (under folder
 * &quot;Java&quot;).</li>
 * <li>Click &quot;next&quot;</li>
 * <li>In the new dialog, make sure that your project is selected as well
 * as all <em>sources of the TSP Suite.</em></li>
 * <li>Check the boxes &quot;Export generated class files and
 * resources&quot; and &quot;Export Java source files and resources&quot;.</li>
 * <li>In input line &quot;JAR file,&quot; select a path and file name
 * where you want your {@code jar} to emerge (e.g., a new folder and
 * <code id="myHeuristicJar">myHeuristicJar.jar</code>). Make sure the file
 * name ends with {@code .jar}.</li>
 * <li>Click &quot;Next&quot;.</li>
 * <li>Click &quot;Next&quot; (again).</li>
 * <li>Next to the input line for &quot;Main class,&quot; click
 * &quot;Browse&quot;.</li>
 * <li>In the dialog that pops up, select your algorithm's class (
 * {@code MyHeuristic}) and click &quot;OK&quot;.</li>
 * <li>Click &quot;Finish&quot; (again).</li>
 * <li>Congratulations: you have created a single binary holding your
 * algorithm implementation. This binary holds not only your project's
 * compiled class files, but also the source code. We suggest that you
 * store it together with the benchmarking results that you will obtain
 * with our system, since it is basically a part of your experiment's
 * documentation.</li>
 * </ol>
 * </li>
 * <li>
 * <p>
 * You can now run the experiments with your program by executing the
 * {@code jar} archive from the command line with the command
 * {@code java -jar myHeuristicJar.jar}.
 * </p>
 * <ol>
 * <li>Please check the <a href="#jarCommandLine">command line
 * parameters</a> that you can supply to the benchmarking system.</li>
 * <li>If you have overridden the
 * {@link org.logisticPlanning.utils.config.Configurable#configure(org.logisticPlanning.utils.config.Configuration)
 * configure} method in your algorithm class in order to <a
 * href="#overrideConfigure">expose its parameters</a> to the command line,
 * well, you should pass them through the command line as well.</li>
 * <li>You should run the experiments on a powerful machine with many
 * processors that you do not need for other stuff for, say, a week, since
 * they will take quite some time to run.</li>
 * <li>
 * <p>
 * If you set many parameter values, you can store them in an configuration
 * file as {@code key=value} pairs. If the configuration file's name is
 * {@code myConfigFile.txt}, then you would pass the command line parameter
 * &quot;
 * <code>{@link org.logisticPlanning.utils.config.Configuration#PARAM_PROPERTY_FILE configFile}=myConfigFile.txt</code>
 * &quot;. Then, all parameters in {@code configFile.txt} will be treated
 * as if they were directly provided through the command line.
 * </p>
 * </li>
 * </ol>
 * </li>
 * </ol>
 * <p>
 * That's it. Now your experiments are running!
 * </p>
 * <h2>Parallel and Distributed Benchmarking</h2>
 * <p>
 * Please be aware that the experiments may take quite some time. Ideally,
 * you should run them on a strong computer with many processors that you
 * do not need to touch for about a week&hellip; &hellip;and after that,
 * you will have a nice set of log files. In order to speed up the process,
 * we provide parallelization and distribution support for experimentation.
 * </p>
 * <p>
 * Benchmarking is done by (potentially) multiple threads. Each thread does
 * the following: It iterates through the list of benchmarking instances
 * (from the <a
 * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
 * >TSPLib</a>). For each benchmark instance it will define an output
 * folder of the corresponding name inside the overall output folder &quot;
 * {@link org.logisticPlanning.tsp.benchmarking.objective.Benchmark#PARAM_DEST_DIR
 * outputDir}&quot;. It will now iterate through the runs that should be
 * done, from <code>1</code> to
 * {@link org.logisticPlanning.tsp.benchmarking.objective.Benchmark#PARAM_MAX_RUNS
 * maxRuns}, where <code>maxRuns</code> is 30, by default. Each combination
 * of benchmark instance and run index defines a unique path and file name.
 * The thread tries to create the file with the corresponding name with the
 * {@link java.io.File#createNewFile()} which reports <code>true</code> if
 * a new file was created and <code>false</code> if the file already
 * existed. If the thread managed to create the file as &quot;new&quot;, it
 * will immediately begin to perform the run and after the run is finished,
 * store its results into the file. If the file already existed, the thread
 * will just move to the next run index. If the run index reaches
 * <code>maxRuns</code>, it moves to the next benchmark instance.
 * </p>
 * <p>
 * This mechanism allows the most primitive and yet surprisingly robust way
 * to enable parallelization, distribution, and restarting of experiments.
 * For instance, amongst the threads in a single
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithmRunner process}, no
 * communication is necessary. Each thread will automatically find the next
 * run it should perform and no two threads may try to do the same work.
 * </p>
 * <p>
 * Distribution, e.g., when executing the experiments in a cluster, can be
 * achieved the same way: You only need to let their
 * {@link org.logisticPlanning.tsp.benchmarking.objective.Benchmark#PARAM_DEST_DIR
 * outputDir} parameters point to the same <em>shared</em> folder. This
 * way, it is possible to let 3 computers with 24 threads each work on the
 * same experiment. Since there are about 110 benchmarking instances for
 * symmetric problems, the total required runtime for a symmetric TSP
 * solver could be reduced to 110 * 30 * 1 hour divided by 3 * 24, i.e., to
 * about two days (unless the solver can solve some problems in less than
 * an hour, in which case the time would be less, too).
 * </p>
 * <p id="restart">
 * Restarting experiments is also easy because of this mechanism: A
 * completed run will have an associated log file of non-zero size. Since
 * the log information is only written once the run is completed (in order
 * to not bias the time measurements), all runs that have been started but
 * are incomplete will have log files of zero size. Thus, if your computer
 * crashes or something, just delete all zero-sized files and you can
 * restart the benchmarker. It will resume its work and not repeat work
 * that has already been done. Under <a
 * href="http://en.wikipedia.org/wiki/Linux">Linux</a>, you could do
 * something like
 * <code>find&nbsp;~/{@link org.logisticPlanning.tsp.benchmarking.objective.Benchmark#PARAM_DEST_DIR outputDir}/&nbsp;-type&nbsp;f&nbsp;-empty&nbsp;-delete</code>
 * or
 * <code>find&nbsp;~/{@link org.logisticPlanning.tsp.benchmarking.objective.Benchmark#PARAM_DEST_DIR outputDir}/&nbsp;-size&nbsp;&nbsp;0&nbsp;-print0&nbsp;|xargs&nbsp;-0&nbsp;rm</code>
 * , where
 * {@link org.logisticPlanning.tsp.benchmarking.objective.Benchmark#PARAM_DEST_DIR
 * outputDir} is the output folder you have defined.
 * </p>
 * <h2>Command Line Parameters of the Benchmarking Environment</h2>
 * <p id="jarCommandLine">
 * The benchmarking environment and running can be parameterized via
 * command line or config files. The following parameters are supported:
 * </p>
 * <ol>
 * <li>
 * <p>
 * If &quot;
 * <code>{@link org.logisticPlanning.tsp.solving.TSPAlgorithmRunner#PARAM_MAX_THREADS maxThreads}=nnn</code>
 * &quot; is supplied, then the benchmarker will use at most {@code nnn}
 * threads. Otherwise, it will use one thread per available processor.
 * </p>
 * </li>
 * <li>
 * <p>
 * If &quot;
 * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.Benchmark#PARAM_DEST_DIR outputDir}=dir</code>
 * &quot; is set, the output of the program, i.e., the log files, will be
 * written to the directory {@code dir}. Otherwise, they will be written to
 * the current directory. Generally, the benchmarking environment will
 * automatically create a directory structure which allows us to easily
 * distinguish symmetric and asymmetric TSPs and log files for different
 * problem instances.
 * </p>
 * </li>
 * <li>
 * <p>
 * If you pass the arguments &quot;
 * <code>-{@link org.logisticPlanning.tsp.solving.TSPAlgorithmRunner#PARAM_RUNNER_LOGGER algoLogger}="global";ALL</code>
 * &quot;, you will receive logging information about the benchmarking
 * progress.
 * </p>
 * </li>
 * <li>
 * <p>
 * You may pass in parameters like &quot;
 * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.CreatorInfo#PARAM_RESEARCHER_NAME researcherName}=&quot;first name last name&quot;</code>
 * &quot; to have your name in the log files (see class
 * {@link org.logisticPlanning.tsp.benchmarking.objective.CreatorInfo
 * CreatorInfo} for more author-related parameters).
 * </p>
 * </li>
 * <li>
 * <p>
 * If &quot;
 * <code>{@link org.logisticPlanning.tsp.solving.TSPAlgorithmRunner#PARAM_DET_INIT_CLASS initclass}=fullyQualifiedClassName</code>
 * &quot; is supplied, then the benchmarker will automatically instantiate
 * the class {@code fullyQualifiedClassName},
 * {@link org.logisticPlanning.utils.config.Configurable#configure(org.logisticPlanning.utils.config.Configuration)
 * configure} it, and apply it as
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#beginDeterministicInitialization(org.logisticPlanning.utils.NamedObject)
 * deterministic initialization procedure} to each instance before invoking
 * your algorithm. The result of this procedure is then available to your
 * algorithm via the
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#getDeterministicInitializationLogPoint()
 * initialization log point} and
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#getCopyOfBestAdj(int[])
 * getCopyOfBestAdj} or
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#getCopyOfBest(int[])
 * getCopyOfBest} at startup (the data provided by the latter two methods
 * will obviously change during the algorithm run). If this parameter is
 * not provided, no such deterministic initialization will be performed.
 * </p>
 * <p>
 * A deterministic initialization procedure is often useful to start your
 * algorithm with a reasonably good approximation of the solution. This is
 * especially often done in local search methods, that usually often invoke
 * a {@link org.logisticPlanning.tsp.solving.algorithms.heuristics
 * heuristic}. Due to this command line parameter, you do not need to
 * hard-code the choice of that algorithm in your program and instead
 * comfortably test it with different heuristics. Also, this allows us to
 * do some runtime optimization: As a deterministic initializer should
 * always return the same result, we need to compute that only once per
 * benchmark case and can re-use it for each run.
 * </p>
 * </li>
 * <li>
 * <p>
 * The parameter &quot;
 * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.Benchmark#PARAM_MAX_RUNS maxRuns}=nnn</code>
 * &quot; can be provided to set the number of runs to be performed for
 * each benchmark case. The
 * {@link org.logisticPlanning.tsp.benchmarking.objective.Benchmark#DEFAULT_MAX_RUNS
 * default value} is {@code 30}. I suggest to not change that value, i.e.,
 * to not provide this parameter.
 * </p>
 * </li>
 * <li>
 * <p>
 * The parameter &quot;
 * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.Benchmark#PARAM_MAX_FES maxFEs}=nnn</code>
 * &quot; can be provided to set the maximum number {@code nnn} of
 * {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedFEs()
 * function evaluations} to be performed for each benchmark run. The
 * default value is <code>100*n<sup>3</sup></code>, where {@code n} is the
 * number of cities of the benchmark cases. I suggest to not change that
 * value, i.e., to not provide this parameter.
 * </p>
 * </li>
 * <li>
 * <p>
 * The parameter &quot;
 * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.Benchmark#PARAM_MAX_DES maxDEs}=nnn</code>
 * &quot; can be provided to set the maximum number {@code nnn} of
 * {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedDEs()
 * distance evaluations} to be performed for each benchmark run. The
 * default value is <code>100*n<sup>4</sup></code>, where {@code n} is the
 * number of cities of the benchmark cases. I suggest to not change that
 * value, i.e., to not provide this parameter.
 * </p>
 * </li>
 * <li>
 * <p>
 * The parameter &quot;
 * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.Benchmark#PARAM_MAX_TIME maxTime}=nnn</code>
 * &quot; can be provided to set the maximum runtime, specified as maximum
 * number {@code nnn} of
 * {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedRuntime()
 * milliseconds} that a benchmark run may take. The default value is
 * {@link org.logisticPlanning.tsp.benchmarking.objective.Benchmark#DEFAULT_MAX_TIME_PER_RUN
 * 3600000}, i.e., one hour. I suggest to not change that value, i.e., to
 * not provide this parameter.
 * </p>
 * </li>
 * <li>
 * <p>
 * If you set many parameter values, you can store them in an configuration
 * file as {@code key=value} pairs. If the configuration file's name is
 * {@code myConfigFile.txt}, then you would pass the command line parameter
 * &quot;
 * <code>{@link org.logisticPlanning.utils.config.Configuration#PARAM_PROPERTY_FILE configFile}=myConfigFile.txt</code>
 * &quot;. Then, all parameters in {@code configFile.txt} will be treated
 * as if they were directly provided through the command line.
 * </p>
 * </li>
 * </ol>
 * <h2>Related Work</h2>
 * <p>
 * There exists some related benchmarking and algorithm development
 * frameworks for other problem types. If you are interested in numerical
 * or continuous optimization instead of combinatorial problems (like the
 * TSP), we suggest that you should visit the <a
 * href="http://coco.gforge.inria.fr/">COCO</a> website, presenting an
 * approach for <em>COmparing Continuous Optimisers</em>&nbsp;[<a
 * href="#cite_COCO2012CCCO" style="font-weight:bold">111</a>, <a
 * href="#cite_HAFR2012RPBBOBES" style="font-weight:bold">112</a>, <a
 * href="#cite_AH2005PEOAALSEA" style="font-weight:bold">113</a>] and for
 * Satisfiability problems, you may wish to take a look into <a
 * href="http://ubcsat.dtompkins.com/">UBCSAT</a>&nbsp;[<a
 * href="#cite_TH2004UAIAEEFSAFSAMS" style="font-weight:bold">114</a>, <a
 * href="#cite_UBCSAT" style="font-weight:bold">115</a>, <a
 * href="#cite_T2010DLSFSDIAA" style="font-weight:bold">116</a>]. Maybe the
 * now defunct AOAB&nbsp;[<a href="#cite_WNT2010AOABAOAB"
 * style="font-weight:bold">117</a>] framework for numerical optimization,
 * an early work by us from which we took some good lessons for this
 * project, deserves a honorable mention, too.
 * </p>
 * <h2>Package Structure of TSP Suite</h2>
 * <p>
 * The packages in this {@link org.logisticPlanning software framework}
 * follow can be divided into two sets:
 * </p>
 * <ol>
 * <li>Packages with classes related to the Traveling Salesman Problem
 * (TSP) are contained in package {@link org.logisticPlanning.tsp}.</li>
 * <li>Packages with general utility classes are contained within package
 * {@link org.logisticPlanning.utils}.</li>
 * </ol>
 * <p>
 * The {@link org.logisticPlanning.tsp TSP}-related packages all serve the
 * <em>TSP Suite</em> idea of implementing, benchmarking, and evaluating
 * TSP solvers. Hence, they a divided into three sub-packages:
 * </p>
 * <ol>
 * <li>Package {@link org.logisticPlanning.tsp.solving} contains both base
 * classes for implementing
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithm TSP solvers} as
 * well as utility classes (such as
 * {@link org.logisticPlanning.tsp.solving.operators search operations},
 * {@link org.logisticPlanning.tsp.solving.gpm GPM}s,
 * {@link org.logisticPlanning.tsp.solving.searchSpaces search space}
 * -related data structures, and other
 * {@link org.logisticPlanning.tsp.solving.utils utilities and data
 * structures} like
 * {@link org.logisticPlanning.tsp.solving.utils.candidates candidate sets}
 * and {@link org.logisticPlanning.tsp.solving.utils.edge edge objects})
 * and a variety of actual
 * {@link org.logisticPlanning.tsp.solving.algorithms algorithm
 * implementations}. This package also contains the classes for
 * automatically
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithmRunner run
 * experiments} in a parallel and distributed fashion.</li>
 * <li>Package {@link org.logisticPlanning.tsp.benchmarking} contains the
 * utilities for benchmarking the algorithms: Classes for
 * {@link org.logisticPlanning.tsp.benchmarking.dist distance computation},
 * the {@link org.logisticPlanning.tsp.benchmarking.instances benchmark
 * instances}, and the
 * {@link org.logisticPlanning.tsp.benchmarking.objective Objective
 * function} classes.</li>
 * <li>The package {@link org.logisticPlanning.tsp.evaluation} holds all
 * the modules of the {@link org.logisticPlanning.tsp.evaluation.Evaluator
 * Evaluator} component that can load the
 * {@link org.logisticPlanning.tsp.evaluation.data data} generated from the
 * benchmark runs and uses it report the performance of a
 * {@link org.logisticPlanning.tsp.evaluation.modules.impl.single single
 * algorithms} or
 * {@link org.logisticPlanning.tsp.evaluation.modules.impl.comparison
 * compare different algorithms}.</li>
 * </ol>
 * <p>
 * The {@link org.logisticPlanning.utils utility package} contains a
 * variety of different tools, such as
 * </p>
 * <ol>
 * <li>{@link org.logisticPlanning.utils.collections collection classes}</li>
 * <li>The package {@link org.logisticPlanning.utils.config config}
 * provides {@link org.logisticPlanning.utils.config.Configuration classes}
 * that can parse command line parameters or configuration files and
 * provide them to {@link org.logisticPlanning.utils.config.Configurable
 * classes} that can be configured that way.</li>
 * <li>The package {@link org.logisticPlanning.utils.document document}
 * holds a complete {@link org.logisticPlanning.utils.document.spec API}
 * for generating documents and reports with figures, tables, literature
 * references, and equations which is them
 * {@link org.logisticPlanning.utils.document.impl implemented} for, e.g.,
 * {@link org.logisticPlanning.utils.document.impl.latex LaTeX} and
 * {@link org.logisticPlanning.utils.document.impl.xhtml XHTML}.</li>
 * <li>The package {@link org.logisticPlanning.utils.graphics graphics}
 * provides utility classes for graphics and
 * {@link org.logisticPlanning.utils.graphics.chart charts}.</li>
 * <li>In package {@link org.logisticPlanning.utils.io} provides utilities
 * for {@link org.logisticPlanning.utils.io.FileUtils file interaction} and
 * {@link org.logisticPlanning.utils.io.charIO text-based IO}.</li>
 * <li>Package {@link org.logisticPlanning.utils.math} provides a lot of
 * mathematical core functionality, ranging from basic
 * {@link org.logisticPlanning.utils.math.functions functions} to
 * {@link org.logisticPlanning.utils.math.statistics statistics}.</li>
 * <li>The {@link org.logisticPlanning.utils.text text package} has
 * {@link org.logisticPlanning.utils.text.TextUtils utilities} for creating
 * and parsing text as well as for
 * {@link org.logisticPlanning.utils.text.CharTransformer transforming
 * text} (in particular special characters) in certain formats such as
 * {@link org.logisticPlanning.utils.text.transformations.XMLCharTransformer
 * XML} or
 * {@link org.logisticPlanning.utils.text.transformations.LaTeXCharTransformer
 * LaTeX}.</li>
 * </ol>
 * <p>
 * This overall structure of &quot;source code&quot; packages is mirrored
 * with an increasing number <a href="http://junit.org/">JUnit</a> test
 * cases in package {@link test.junit}. The test cases for a class
 * {@code XYZ} in package {@code org.bla.blubb} can be found in package
 * {@code test.junit.org.bla.blubb} and usually should be named something
 * like {@code XYZTest}.
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
 * <li><div><span id="cite_GP2004TTSPAIV" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;The Traveling
 * Salesman Problem and its Variations,&rdquo;</span> 2002&ndash;2004, <a
 * href=
 * "http://www.rhul.ac.uk/computerscience/staffdirectory/ggutin/home.aspx"
 * >Gregory Z. Gutin</a> and&nbsp;<a
 * href="http://www.sfu.ca/~apunnen/">Abraham P. Punnen</a>, editors,
 * volume 12 of Combinatorial Optimization, Norwell, MA, USA: Kluwer
 * Academic Publishers. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0306482134">0-306-48213-4</a>, <a
 * href="https://www.worldcat.org/isbn/1402006640">1-4020-0664-0</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781402006647">978-1-4020
 * -0664-7</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/b101971">10.1007/b101971</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/55085594">55085594</a>, <a
 * href="https://www.worldcat.org/oclc/818988416">818988416</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/oclc/803682928">803682928</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=TRYkPg_Xf20C">TRYkPg_Xf20C</a>,
 * <a
 * href="http://books.google.com/books?id=mQ5RxzsuOXAC">mQ5RxzsuOXAC</a>,
 * <a
 * href="http://books.google.com/books?id=5RuNx2TIbIcC">5RuNx2TIbIcC</a>,
 * and&nbsp;<a
 * href="http://books.google.com/books?id=pfRSPwAACAAJ">pfRSPwAACAAJ
 * </a></div></li>
 * <li><div><span id="cite_G1985AGT" /><a
 * href="http://www.dcs.kcl.ac.uk/staff/amg/">Alan Gibbons</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Algorithmic Graph
 * Theory,&rdquo;</span> 1985, Cambridge, UK: Cambridge University Press.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0521288819">0521288819</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780521288811">9780521288811</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=Be6t04pgggwC"
 * >Be6t04pgggwC</a></div></li>
 * <li><div><span id="cite_H2012TSPTMMP" /><a
 * href="http://www.nomachetejuggling.com/">Rod Hilton</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Traveling
 * Salesperson: The Most Misunderstood Problem,&rdquo;</span> (Website),
 * September&nbsp;14, 2012. <div>link: [<a href=
 * "http://www.nomachetejuggling.com/2012/09/14/traveling-salesman-the-most-misunderstood-problem/"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_LRK1975SSAOTTSP" /><a
 * href="https://en.wikipedia.org/wiki/Jan_Karel_Lenstra">Jan Karel
 * Lenstra</a> and&nbsp;<a
 * href="https://en.wikipedia.org/wiki/Alexander_Rinnooy_Kan">Alexander
 * Hendrik George Rinnooy Kan</a>: <span
 * style="font-weight:bold">&ldquo;Some Simple Applications of the
 * Travelling Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Operational Research
 * Quarterly</span> 26(4, Part 1):717&ndash;733, November&nbsp;1975;
 * published by Houndmills, Basingstoke, Hampshire, UK: Palgrave Macmillan
 * Ltd. and&nbsp;Birmingham, UK: Operations Research Society. JSTOR
 * stable:&nbsp;<a
 * href="http://www.jstor.org/stable/3008306">3008306</a></div></li>
 * <li><div><span id="cite_MSM2010TSPAOOAFASA" />Rajesh Matai, Surya
 * Prakash Singh, and&nbsp;Murari Lal Mittal: <span
 * style="font-weight:bold">&ldquo;Traveling Salesman Problem: An Overview
 * of Applications, Formulations, and Solution Approaches,&rdquo;</span> in
 * <span style="font-style:italic;font-family:cursive;">Traveling Salesman
 * Problem, Theory and Applications</span>, chapter 1&ndash;25, pages
 * 1&ndash;25, <a
 * href="http://mrl.cs.vsb.cz/people/davendra/index.html">Donald David
 * Davendra</a>, editor, Winchester, Hampshire, UK: InTech. ISBN:&nbsp;<a
 * href
 * ="https://www.worldcat.org/isbn/9789533074269">978-953-307-426-9</a>;
 * doi:&nbsp;<a href="http://dx.doi.org/10.5772/12909">10.5772/12909</a>.
 * <div>link: [<a href=
 * "http://cdn.intechopen.com/pdfs/12736/InTech-Traveling_salesman_problem_an_overview_of_applications_formulations_and_solution_approaches.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_GJR1991OCOPADMACS" />Martin Gr&#246;tschel,
 * Michael J&#252;nger, and&nbsp;<a
 * href="http://comopt.ifi.uni-heidelberg.de/people/reinelt/">Gerhard
 * Reinelt</a>: <span style="font-weight:bold">&ldquo;Optimal Control of
 * Plotting and Drilling Machines: A Case Study,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Zeitschrift f&#252;r
 * Operations Research (ZOR)</span> 35(1):61&ndash;84, January&nbsp;1,
 * 1991; published by Heidelberg, Germany: Physica-Verlag GmbH &amp; Co..
 * doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/BF01415960">10.1007/BF01415960</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03409422">0340-9422</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/14325217">1432-5217</a></div></li>
 * <li><div><span id="cite_BS1989LTSPAFEIXRCAPROC" />Robert G. Bland
 * and&nbsp;David F. Shallcross: <span
 * style="font-weight:bold">&ldquo;Large Travelling Salesman Problems
 * arising from Experiments in X-Ray Crystallography: A Preliminary Report
 * on Computation,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Operations Research
 * Letters</span> 8(3):125&ndash;128, June&nbsp;1989; published by
 * Amsterdam, The Netherlands: Elsevier Science Publishers B.V..
 * doi:&nbsp;<a
 * href="http://dx.doi.org/10.1016/0167-6377(89)90037-0">10.1016
 * /0167-6377(89)90037-0</a>; ISSN:&nbsp;<a
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
 * href ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.43.2594">
 * 10.1 .1.43.2594</a></div></div></li>
 * <li><div><span id="cite_H2012L" /><a
 * href="http://www.akira.ruc.dk/~keld/">Keld Helsgaun</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;LKH,&rdquo;</span>
 * (Software), November&nbsp;2012. <div>link: [<a
 * href="http://www.akira.ruc.dk/~keld/research/LKH/">1</a>]</div></div></li>
 * <li><div><span id="cite_H2009GKOSFTLKTH" /><a
 * href="http://www.akira.ruc.dk/~keld/">Keld Helsgaun</a>: <span
 * style="font-weight:bold">&ldquo;General k-opt Submoves for the
 * Lin&#8211;Kernighan TSP Heuristic,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Mathematical Programming
 * Computation</span> 1(2-3):119&ndash;163, October&nbsp;2009; published by
 * Berlin, Germany: Springer-Verlag GmbH and&nbsp;Philadelphia, PA, USA:
 * Mathematical Optimization Society (MOS). doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/s12532-009-0004-6"
 * >10.1007/s12532-009-0004-6</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/18672949">1867-2949</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/18672957">1867-2957</a></div></li>
 * <li><div><span id="cite_H1998AEIOTLKTSH" /><a
 * href="http://www.akira.ruc.dk/~keld/">Keld Helsgaun</a>: <span
 * style="font-weight:bold">&ldquo;An Effective Implementation of the
 * Lin-Kernighan Traveling Salesman Heuristic,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;81, 1998; published by Roskilde, Denmark: Roskilde
 * University, Department of Computer Science. Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=YxSiHAAACAAJ">YxSiHAAACAAJ</a>.
 * <div>link: [<a
 * href="www.akira.ruc.dk/~keld/research/LKH/LKH-2.0/DOC/LKH_REPORT.pdf"
 * >1</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.25.4908"
 * >10.1.1.25.4908</a></div></div></li>
 * <li><div><span id="cite_NYYY2007IOAEHGFLSTSP" />Hung Dinh Nguyen, Ikuo
 * Yoshihara, Kunihito Yamamori, and&nbsp;Moritoshi Yasunaga: <span
 * style="font-weight:bold">&ldquo;Implementation of an Effective Hybrid GA
 * for Large-Scale Traveling Salesman Problems,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">IEEE Transactions on
 * Systems, Man, and Cybernetics &#8211; Part B: Cybernetics</span>
 * 37(1):92&ndash;99, February&nbsp;2007; published by New York, NY, USA:
 * IEEE Systems, Man, and Cybernetics Society. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/TSMCB.2006.880136"
 * >10.1109/TSMCB.2006.880136</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/10834419">1083-4419</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=ITSCFI"
 * >ITSCFI</a>; PubMed:&nbsp;<a
 * href="https://www.ncbi.nlm.nih.gov/pubmed/17278563">17278563</a>; INSPEC
 * Accession Number:&nbsp;9299485</div></li>
 * <li><div><span id="cite_RGJM2007ITEOHLKHFTST" />Dirk Richter, Boris
 * Goldengorin, Gerold J&#228;ger, and&nbsp;Paul Molitor: <span
 * style="font-weight:bold">&ldquo;Improving the Efficiency of Helsgaun's
 * Lin-Kernighan Heuristic for the Symmetric TSP,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Revised Papers of the 4th
 * Workshop on Combinatorial and Algorithmic Aspects of Networking
 * (CAAN'07)</span>, August&nbsp;14, 2007, Halifax, NS, Canada, pages
 * 99&ndash;111, Jeannette Janssen and&nbsp;Pawe&#322; Pra&#322;at,
 * editors, volume 4852 of Lecture Notes in Computer Science (LNCS),
 * Berlin/Heidelberg: Springer-Verlag and&nbsp;Berlin, Germany:
 * Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540772936">3-540-77293-6</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540772941">978-3-540-
 * 77294-1</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/978-3-540-77294-1_10"
 * >10.1007/978-3-540-77294-1_10</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a>. <div>links:
 * [<a href=
 * "http://www.informatik.uni-kiel.de/~gej/publ/improved_helsgaun.pdf"
 * >1</a>] and&nbsp;[<a
 * href="http://www.informatik.uni-kiel.de/~gej/publ/halifax.pdf"
 * >2</a>]</div></div></li>
 * <li><div><span id="cite_Z1999TAADFBABACSOTATSP" /><a
 * href="http://www.cs.wustl.edu/~zhang/">Weixiong Zhang</a>: <span
 * style="font-weight:bold">&ldquo;Truncated and Anytime Depth-First Branch
 * and Bound: A Case Study on the Asymmetric Traveling Salesman
 * Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">AAAI Spring Symposium
 * Series: Search Techniques for Problem Solving Under Uncertainty and
 * Incomplete Information</span>, March&nbsp;1999, pages 148&ndash;155, <a
 * href="http://www.cs.wustl.edu/~zhang/">Weixiong Zhang</a> and&nbsp;Sven
 * K&#246;nig, editors, volume SS-99-07 of AAAI Technical Report, Menlo
 * Park, CA, USA: AAAI Press. <div>link: [<a href=
 * "https://www.aaai.org/Papers/Symposia/Spring/1999/SS-99-07/SS99-07-026.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_Z1993TBABACSOTATSP" /><a
 * href="http://www.cs.wustl.edu/~zhang/">Weixiong Zhang</a>: <span
 * style="font-weight:bold">&ldquo;Truncated Branch-and-Bound: A Case Study
 * on the Asymmetric Traveling Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the
 * AAAI-93 Spring Symposium on AI and NP-Hard Problems</span>,
 * March&nbsp;23&ndash;25, 1993, Stanford, CA, USA, pages 160&ndash;166,
 * Menlo Park, CA, USA: AAAI Press. <div>link: [<a
 * href="www.cs.wustl.edu/~zhang/publications/atsp-aaai93-symp.ps"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_ABCC2001TCWDNCTTTP" /><a
 * href="http://www.research.att.com/people/Applegate_David_L">David Lee
 * Applegate</a>, <a href="http://www.caam.rice.edu/~bixby/">Robert E.
 * Bixby</a>, <a href="http://users.encs.concordia.ca/~chvatal/">Va&#353;ek
 * Chv&#225;tal</a>, and&nbsp;<a
 * href="http://www2.isye.gatech.edu/~wcook/">William John Cook</a>: <span
 * style="font-weight:bold">&ldquo;TSP Cuts Which Do Not Conform to the
 * Template Paradigm,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Computational
 * Combinatorial Optimization, Optimal or Provably Near-Optimal
 * Solutions</span>, chapter 261&ndash;303, pages 261&ndash;303, Michael
 * J&#252;nger and&nbsp;Denis Naddef, editors, volume 2241 of Lecture Notes
 * in Computer Science (LNCS), Berlin, Germany: Springer-Verlag GmbH.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540428771">3-540-42877-1</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540428770">978-3-540-
 * 42877-0</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/3-540-45586-8_7"
 * >10.1007/3-540-45586-8_7</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=Vz5vh8Pyix8C">Vz5vh8Pyix8C</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a>;
 * ASIN:&nbsp;<a href="http://www.amazon.com/dp/3540428771">3540428771</a>.
 * <div>link: [<a href=
 * "http://pdf.aminer.org/000/083/712/tsp_cuts_which_do_not_conform_to_the_template_paradigm.pdf"
 * >1</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.127.9303"
 * >10.1.1.127.9303</a></div></div></li>
 * <li><div><span id="cite_ABCC2003ITDFJAFLTSP" /><a
 * href="http://www.research.att.com/people/Applegate_David_L">David Lee
 * Applegate</a>, <a href="http://www.caam.rice.edu/~bixby/">Robert E.
 * Bixby</a>, <a href="http://users.encs.concordia.ca/~chvatal/">Va&#353;ek
 * Chv&#225;tal</a>, and&nbsp;<a
 * href="http://www2.isye.gatech.edu/~wcook/">William John Cook</a>: <span
 * style="font-weight:bold">&ldquo;Implementing the
 * Dantzig-Fulkerson-Johnson Algorithm for Large Traveling Salesman
 * Problems,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Mathematical
 * Programming</span> 97(1-2):91&ndash;153, July&nbsp;2003; published by
 * Berlin/Heidelberg: Springer-Verlag. LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/74618643">74618643</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/s10107-003-0440-4"
 * >10.1007/s10107-003-0440-4</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/1585989">1585989</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/00255610">0025-5610</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/14364646">1436-4646</a>. <div>link:
 * [<a
 * href="http://www2.isye.gatech.edu/~wcook/papers/dfj_mathprog.pdf">1</
 * a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.14.9584"
 * >10.1.1.14.9584</a></div></div></li>
 * <li><div><span id="cite_W2011CTS" /><a
 * href="http://www2.isye.gatech.edu/~wcook/">William John Cook</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Concorde TSP
 * Solver,&rdquo;</span> (Website), December&nbsp;2011, Atlanta, GA, USA:
 * Georgia Institute of Technology, H. Milton Stewart School of Industrial
 * and Systems Engineering. <div>link: [<a
 * href="http://www.tsp.gatech.edu/concorde/">1</a>]</div></div></li>
 * <li><div><span id="cite_V1832DHWESSUWEZTHUAZEUEGE" />Bernhard Friedrich
 * Voigt: <span style="font-style:italic;font-family:cursive;">&ldquo;Der
 * Handlungsreisende &#8211; wie er sein soll und was er zu thun hat, um
 * Auftr&#228;ge zu erhalten und eines gl&#252;cklichen Erfolgs in seinen
 * Gesch&#228;ften gewi&#223; zu sein &#8211; von einem alten
 * Commis-Voyageur,&rdquo;</span> 1832, Ilmenau, Germany: Voigt.
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/258013333">258013333</a></div></li>
 * <li><div><span id="cite_DFJ1954SOALSTSP" /><a
 * href="https://en.wikipedia.org/wiki/George_Dantzig">George Bernard
 * Dantzig</a>, R. Fulkerson, and&nbsp;S. Johnson: <span
 * style="font-weight:bold">&ldquo;Solution of a Large-Scale
 * Traveling-Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Journal of the Operations
 * Research Society of America</span> 2(4):393&ndash;410,
 * November&nbsp;1954; published by Linthicum, ML, USA: Institute for
 * Operations Research and the Management Sciences (INFORMS). JSTOR
 * stable:&nbsp;<a href="http://www.jstor.org/stable/166695">166695</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/00963984">0096-3984</a>. <div>link:
 * [<a
 * href="http://www.iro.umontreal.ca/~gendron/IFT6551/LECTURES/TSP.pdf">
 * 1</a>]</div></div></li>
 * <li><div><span id="cite_ABCC2006TTSPACS" /><a
 * href="http://www.research.att.com/people/Applegate_David_L">David Lee
 * Applegate</a>, <a href="http://www.caam.rice.edu/~bixby/">Robert E.
 * Bixby</a>, <a href="http://users.encs.concordia.ca/~chvatal/">Va&#353;ek
 * Chv&#225;tal</a>, and&nbsp;<a
 * href="http://www2.isye.gatech.edu/~wcook/">William John Cook</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;The Traveling
 * Salesman Problem: A Computational Study,&rdquo;</span>
 * February&nbsp;2007, Princeton Series in Applied Mathematics, Princeton,
 * NJ, USA: Princeton University Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0691129932">0-691-12993-2</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780691129938">978-0-691-
 * 12993-8</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=nmF4rVNJMVsC">nmF4rVNJMVsC</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=vhsJbqomDuIC">vhsJbqomDuIC
 * </a></div></li>
 * <li><div><span id="cite_G2008TSP" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Traveling Salesman
 * Problem,&rdquo;</span> September&nbsp;2008, <a
 * href="http://www.stat.unipg.it/sigi/corsi.php?corso=49">Federico
 * Greco</a>, editor, Vienna, Austria: IN-TECH Education and Publishing.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/9789537619107">978-953-
 * 7619-10-7</a>. <div>links: [<a href=
 * "http://intechweb.org/downloadfinal.php?is=978-953-7619-10-7&amp;amp;type=B"
 * >1</a>] and&nbsp;[<a href=
 * "http://www.degraf.ufpr.br/docentes/paulo/publicacoes_arquivos/TravellingSalesmanProblem.pdf"
 * >2</a>]</div></div></li>
 * <li><div><span id="cite_LLKS1985TTSPAGTOCO" /><a
 * href="https://en.wikipedia.org/wiki/Eugene_Lawler">Eugene Leighton
 * (Gene) Lawler</a>, <a
 * href="https://en.wikipedia.org/wiki/Jan_Karel_Lenstra">Jan Karel
 * Lenstra</a>, <a
 * href="https://en.wikipedia.org/wiki/Alexander_Rinnooy_Kan">Alexander
 * Hendrik George Rinnooy Kan</a>, and&nbsp;<a
 * href="http://people.orie.cornell.edu/shmoys/">David B. Shmoys</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;The Traveling
 * Salesman Problem: A Guided Tour of Combinatorial
 * Optimization,&rdquo;</span> September&nbsp;1985, Estimation, Simulation,
 * and Control &#8211; Wiley-Interscience Series in Discrete Mathematics
 * and Optimization, Chichester, West Sussex, UK: Wiley Interscience.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0471904139">0-471-90413-9</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780471904137">978-0-471-
 * 90413-7</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/85003158">85003158</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/260186267">260186267</a>, <a
 * href="https://www.worldcat.org/oclc/11756468">11756468</a>, <a
 * href="https://www.worldcat.org/oclc/631997749">631997749</a>, <a
 * href="https://www.worldcat.org/oclc/468006920">468006920</a>, <a
 * href="https://www.worldcat.org/oclc/611882247">611882247</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/oclc/488801427">488801427</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=BXBGAAAAYAAJ">BXBGAAAAYAAJ</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=02528360X">02528360X</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=011848790">011848790</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=042459834">042459834</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=175957169">175957169</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=042637112">042637112</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=043364950">043364950</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=156527677">156527677</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=078586313">078586313</a>, and&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=190388226">190388226</a></div></li>
 * <li><div><span id="cite_D2010TSPTAA" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Traveling Salesman
 * Problem, Theory and Applications,&rdquo;</span> December&nbsp;30, 2010,
 * <a href="http://mrl.cs.vsb.cz/people/davendra/index.html">Donald David
 * Davendra</a>, editor, Winchester, Hampshire, UK: InTech. ISBN:&nbsp;<a
 * href
 * ="https://www.worldcat.org/isbn/9789533074269">978-953-307-426-9</a>;
 * doi:&nbsp;<a href="http://dx.doi.org/10.5772/547">10.5772/547</a>.
 * <div>links: [<a href=
 * "http://www.intechopen.com/books/traveling-salesman-problem-theory-and-applications"
 * >1</a>] and&nbsp;[<a href=
 * "http://www.degraf.ufpr.br/docentes/paulo/publicacoes_arquivos/Traveling_Salesman_Problem__Theory_and_Applications.pdf"
 * >2</a>]</div></div></li>
 * <li><div><span id="cite_C2011IPOTTSMATLOC" /><a
 * href="http://www2.isye.gatech.edu/~wcook/">William John Cook</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;In Pursuit of the
 * Traveling Salesman: Mathematics at the Limits of
 * Computation,&rdquo;</span> 2011, Princeton, NJ, USA: Princeton
 * University Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0691152705">0691152705</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780691152707">9780691152707</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=S3bxbr_-qhYC"
 * >S3bxbr_-qhYC</a></div></li>
 * <li><div><span id="cite_C2011TSP" /><a
 * href="http://www2.isye.gatech.edu/~wcook/">William John Cook</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Traveling Salesman
 * Problem,&rdquo;</span> (Website), September&nbsp;18, 2011, Atlanta, GA,
 * USA: Georgia Institute of Technology, College of Engineering, School of
 * Industrial and Systems Engineering, Operations Research. <div>link: [<a
 * href="http://www.tsp.gatech.edu/index.html">1</a>]</div></div></li>
 * <li><div><span id="cite_C2008TTSP" /><a
 * href="http://users.encs.concordia.ca/~chvatal/">Va&#353;ek
 * Chv&#225;tal</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;The Traveling
 * Salesman Problem,&rdquo;</span> (Website), October&nbsp;2008,
 * Montr&#233;al, QC, Canada: Concordia University. <div>link: [<a
 * href="http://users.encs.concordia.ca/~chvatal/tsp/tsp.html"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_S2011NSTSPP" /><a
 * href="https://www.andrew.cmu.edu/~neils/">Neil Simonetti</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Neil Simonetti's
 * Traveling Salesman Problem Page,&rdquo;</span> (Website), July&nbsp;21,
 * 2011. <div>link: [<a
 * href="http://www.andrew.cmu.edu/user/neils/tsp/index.html"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_M1999TAIAAEOHA" /><a
 * href="http://www-e.uni-magdeburg.de/mertens/">Stephan Mertens</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;TSP Algorithms in
 * Action: Animated Examples of Heuristic Algorithms,&rdquo;</span>
 * (Website), May&nbsp;10, 1999, Magdeburg, Germany:
 * Otto-von-Guericke-Universit&#228;t Magdeburg (OVGU). <div>link: [<a
 * href=
 * "http://www-e.uni-magdeburg.de/mertens/TSP/TSP.html">1</a>]</div></div></li>
 * <li><div><span id="cite_LP2004TS" /><a
 * href="http://www.or.deis.unibo.it/lodi.html">Andrea Lodi</a> and&nbsp;<a
 * href="http://www.sfu.ca/~apunnen/">Abraham P. Punnen</a>: <span
 * style="font-weight:bold">&ldquo;TSP Software,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">The Traveling Salesman
 * Problem and its Variations</span>, chapter 737&ndash;749, pages
 * 737&ndash;749, <a href=
 * "http://www.rhul.ac.uk/computerscience/staffdirectory/ggutin/home.aspx"
 * >Gregory Z. Gutin</a> and&nbsp;<a
 * href="http://www.sfu.ca/~apunnen/">Abraham P. Punnen</a>, editors,
 * volume 12 of Combinatorial Optimization, Norwell, MA, USA: Kluwer
 * Academic Publishers. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0306482134">0-306-48213-4</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781402006647">978-1-4020
 * -0664-7</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/0-306-48213-4_16"
 * >10.1007/0-306-48213-4_16</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/55085594">55085594</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=TRYkPg_Xf20C">TRYkPg_Xf20C</a>.
 * <div>link: [<a
 * href="http://www.or.deis.unibo.it/research_pages/tspsoft.html"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_LPB2010TS" /><a
 * href="http://www.or.deis.unibo.it/lodi.html">Andrea Lodi</a>, <a
 * href="http://www.sfu.ca/~apunnen/">Abraham P. Punnen</a>,
 * and&nbsp;Matteo Boccafoli: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;TSP
 * Software,&rdquo;</span> (Website), December&nbsp;16, 2010, Bol&#243;gna,
 * Emilia-Romagna, Italy: Universit&#224; degli Studi di Bologna,
 * Dipartimento di Elettronica, Informatica e Sistemistica (DEIS),
 * Operations Research Group (Ricerca Operativa). <div>link: [<a
 * href="http://www.or.deis.unibo.it/research_pages/tspsoft.html"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_HCS2008TSBARTS" />Chad Hurwitz and&nbsp;Robert
 * Craig: <span style="font-style:italic;font-family:cursive;">&ldquo;The
 * Stony Brook Algorithm Repository: TSP Solvers,&rdquo;</span> (Software),
 * July&nbsp;10, 2008, Steven Skiena, editor, Stony Brook, NY, USA: Stony
 * Brook University, Department of Computer Science. <div>link: [<a href=
 * "http://www.cs.sunysb.edu/~algorith/implement/tsp/implement.shtml"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_SGLR2000ACONIHOTTSP" /><a
 * href="http://iridia.ulb.ac.be/~stuetzle/">Thomas St&#252;tzle</a>,
 * Andreas Gr&#252;n, Sebastian Linke, and&nbsp;Marco R&#252;ttger: <span
 * style="font-weight:bold">&ldquo;A Comparison of Nature Inspired
 * Heuristics on the Traveling Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the 6th
 * International Conference on Parallel Problem Solving from Nature (PPSN
 * VI)</span>, September&nbsp;18&ndash;20, 2000, Paris, France, pages
 * 661&ndash;670, <a href="http://www.lri.fr/~marc/">Marc Schoenauer</a>,
 * <a href="http://www.iitk.ac.in/kangal/deb.shtml">Kalyanmoy Deb</a>, <a
 * href="http://ls11-www.cs.uni-dortmund.de/people/rudolph/">G&#252;nter
 * Rudolph</a>, <a href="http://www.cs.bham.ac.uk/~xin/">Xin Yao</a> <span
 * style="color:gray">[&#23002;&#26032;</span>], <a
 * href="http://evelyne.lutton.free.fr/">Evelyne Lutton</a>, Juan
 * Juli&#225;n Merelo-Guerv&#243;s, and&nbsp;<a
 * href="https://en.wikipedia.org/wiki/Hans-Paul_Schwefel">Hans-Paul
 * Schwefel</a>, editors, volume 1917/2000 of Lecture Notes in Computer
 * Science (LNCS), Berlin, Germany: Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540410562">3-540-41056-2</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540410560">978-3-540-
 * 41056-0</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/3-540-45356-3_65"
 * >10.1007/3-540-45356-3_65</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/313676512">313676512</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=gI26Cld2BY0C">gI26Cld2BY0C</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a>. <div>links:
 * [<a href=
 * "www.researchgate.net/publication/225270585_A_Comparison_of_Nature_Inspired_Heuristics_on_the_Traveling_Salesman_Problem/file/9fcfd50b1f8b843708.pdf"
 * >1</a>] and&nbsp;[<a
 * href="www.intellektik.informatik.tu-darmstadt.de/TR/2000/00-01.ps.gz"
 * >2</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.30.791"
 * >10.1.1.30.791</a></div></div></li>
 * <li><div><span id="cite_GGRVG1985GAFTT" /><a
 * href="http://www.biostat.pitt.edu/directory/bios/grefenstette.asp">John
 * J. Grefenstette</a>, Rajeev Gopal, <a href=
 * "http://academics.hamilton.edu/computer_science/brosmait/index.html"
 * >Brian J. Rosmaita</a>, and&nbsp;<a
 * href="http://www.cs.indiana.edu/~vgucht/">Dirk Van Gucht</a>: <span
 * style="font-weight:bold">&ldquo;Genetic Algorithms for the
 * TSP,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the 1st
 * International Conference on Genetic Algorithms and their Applications
 * (ICGA'85)</span>, June&nbsp;24&ndash;26, 1985, Pittsburgh, PA, USA:
 * Carnegy Mellon University (CMU), pages 160&ndash;168, <a
 * href="http://www.biostat.pitt.edu/directory/bios/grefenstette.asp">John
 * J. Grefenstette</a>, editor, Hillsdale, NJ, USA: Lawrence Erlbaum
 * Associates. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0805804269">0-8058-0426-9</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780805804263">978-0-8058
 * -0426-3</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/19702892">19702892</a></div></li>
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
 * <li><div><span id="cite_JGMCYZZ2004EAOHFTA" /><a
 * href="https://en.wikipedia.org/wiki/David_S._Johnson">David Stifler
 * Johnson</a>, <a href=
 * "http://www.rhul.ac.uk/computerscience/staffdirectory/ggutin/home.aspx"
 * >Gregory Z. Gutin</a>, <a
 * href="https://www.amherst.edu/people/facstaff/lamcgeoch">Lyle A.
 * McGeoch</a>, Anders Yeo, <a
 * href="http://www.cs.wustl.edu/~zhang/">Weixiong Zhang</a>,
 * and&nbsp;Alexei Zverovitch: <span
 * style="font-weight:bold">&ldquo;Experimental Analysis of Heuristics for
 * the ATSP,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">The Traveling Salesman
 * Problem and its Variations</span>, chapter 445&ndash;487, pages
 * 445&ndash;487, <a href=
 * "http://www.rhul.ac.uk/computerscience/staffdirectory/ggutin/home.aspx"
 * >Gregory Z. Gutin</a> and&nbsp;<a
 * href="http://www.sfu.ca/~apunnen/">Abraham P. Punnen</a>, editors,
 * volume 12 of Combinatorial Optimization, Norwell, MA, USA: Kluwer
 * Academic Publishers. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0306482134">0-306-48213-4</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781402006647">978-1-4020
 * -0664-7</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/0-306-48213-4_10"
 * >10.1007/0-306-48213-4_10</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/55085594">55085594</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=TRYkPg_Xf20C">TRYkPg_Xf20C</a>.
 * <div>link: [<a
 * href="http://www2.research.att.com/~dsj/papers/atspchap.pdf">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.139.4091">
 * 10.1.1.139.4091</a></div></div></li>
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
 * <li><div><span id="cite_N2003HOTTSP" />Christian Nilsson: <span
 * style="font-weight:bold">&ldquo;Heuristics for the Traveling Salesman
 * Problem,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * June&nbsp;30, 2003; published by Link&#246;ping, Sweden: Link&#246;ping
 * University. <div>link: [<a
 * href="https://www.ida.liu.se/~TDDB19/reports_2003/htsp.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_K1956OTSSSOAGATTSP" />Joseph. B. Kruskal, Jr.:
 * <span style="font-weight:bold">&ldquo;On the Shortest Spanning Subtree
 * of a Graph and the Traveling Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the
 * American Mathematical Society</span> 7(1):48&ndash;50,
 * February&nbsp;1956; published by Providence, RI, USA: American
 * Mathematical Society (AMS) Bookstore. JSTOR stable:&nbsp;<a
 * href="http://www.jstor.org/stable/2033241">2033241</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/10886826">1088-6826</a></div></li>
 * <li><div><span id="cite_ACR2003CLKFLTSP" /><a
 * href="http://www.research.att.com/people/Applegate_David_L">David Lee
 * Applegate</a>, <a href="http://www2.isye.gatech.edu/~wcook/">William
 * John Cook</a>, and&nbsp;Andr&#233; Rohe: <span
 * style="font-weight:bold">&ldquo;Chained Lin-Kernighan for Large
 * Traveling Salesman Problems,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">INFORMS Journal on
 * Computing (JOC)</span> 15(1):82&ndash;92, Winter&nbsp;2003; published by
 * Linthicum, ML, USA: Institute for Operations Research and the Management
 * Sciences (INFORMS) and&nbsp;Cambridge, MA, USA: HighWire Press (Stanford
 * University). ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/10919856">1091-9856</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15265528">1526-5528</a>. <div>links:
 * [<a href="http://www2.isye.gatech.edu/~wcook/papers/clk_ijoc.pdf">1</a>]
 * and&nbsp;[<a
 * href="http://joc.journal.informs.org/content/15/1/82.full.pdf">2</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.22.8342">
 * 10.1 .1.22.8342</a></div></div></li>
 * <li><div><span id="cite_NN2008CGAAGLSMBSTI" />Mehrdad Nojoumian
 * and&nbsp;Divya K. Nair: <span style="font-weight:bold">&ldquo;Comparing
 * Genetic Algorithm and Guided Local Search Methods by Symmetric TSP
 * Instances,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the
 * Genetic and Evolutionary Computation Conference (GECCO'08)</span>,
 * July&nbsp;12&ndash;16, 2008, Atlanta, GA, USA: Renaissance Atlanta Hotel
 * Downtown, pages 1131&ndash;1132, Maarten Keijzer, <a
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
 * href="http://dx.doi.org/10.1145/1389095.1389313"
 * >10.1145/1389095.1389313</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/299786110">299786110</a>; further
 * information: [<a href="http://www.sigevo.org/gecco-2008/">1</a>]</div></li>
 * <li><div><span id="cite_RS2012AEGIOAMSTSFLPI" />Kamil Rocki
 * and&nbsp;Reiji Suda: <span style="font-weight:bold">&ldquo;An Efficient
 * GPU Implementation of a Multi-Start TSP Solver for Large Problem
 * Instances,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Companion Material
 * Proceedings Genetic and Evolutionary Computation Conference
 * (GECCO'12)</span>, July&nbsp;7&ndash;11, 2012, Philadelphia, PA, USA:
 * Doubletree by Hilton Hotel Philadelphia Center City, pages
 * 1441&ndash;1442, Terence Soule and&nbsp;<a
 * href="http://www.epistasis.org/jason.html">Jason H. Moore</a>, editors,
 * New York, NY, USA: Association for Computing Machinery (ACM).
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781450311786">978-1-4503
 * -1178-6</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1145/2330784.2330978"
 * >10.1145/2330784.2330978</a></div></li>
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
 * <li><div><span id="cite_HK1970TTSPAMST" />Michael Held and&nbsp;<a
 * href="https://en.wikipedia.org/wiki/Richard_M._Karp">Richard Manning
 * Karp</a>: <span style="font-weight:bold">&ldquo;The Traveling Salesman
 * Problem and Minimum Spanning Trees,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Operations Research
 * (Oper. Res.)</span> 18(6):1138&ndash;1162, November&ndash;December 1970;
 * published by Linthicum, ML, USA: Institute for Operations Research and
 * the Management Sciences (INFORMS) and&nbsp;Cambridge, MA, USA: HighWire
 * Press (Stanford University). LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/66099702">66099702</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1287/opre.18.6.1138"
 * >10.1287/opre.18.6.1138</a>; JSTOR stable:&nbsp;<a
 * href="http://www.jstor.org/stable/169411">169411</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/2394608">2394608</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/0030364X">0030-364X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15265463">1526-5463</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=OPREAI"
 * >OPREAI</a>. <div>link: [<a
 * href="http://or.journal.informs.org/content/18/6/1138.full.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_LMSK1963AAFTTSP2" />John D. C. Little, <a
 * href="http://www-personal.umich.edu/~murty/">Katta G. Murty</a>, Dura W.
 * Sweeny, and&nbsp;Caroline Karel: <span
 * style="font-weight:bold">&ldquo;An Algorithm for the Traveling Salesman
 * Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Operations Research
 * (Oper. Res.)</span> 11(6):972&ndash;989, November&ndash;December 1963;
 * published by Linthicum, ML, USA: Institute for Operations Research and
 * the Management Sciences (INFORMS) and&nbsp;Cambridge, MA, USA: HighWire
 * Press (Stanford University). LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/66099702">66099702</a>; JSTOR stable:&nbsp;<a
 * href="http://www.jstor.org/stable/167836">167836</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/2394608">2394608</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/0030364X">0030-364X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15265463">1526-5463</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=OPREAI"
 * >OPREAI</a></div></li>
 * <li><div><span id="cite_CT1980SNBABCFTATSP" />Giorgio Carpaneto
 * and&nbsp;Paolo Toth: <span style="font-weight:bold">&ldquo;Some New
 * Branching and Bounding Criteria for the Asymmetric Travelling Salesman
 * Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Management Science</span>
 * 26(7):736&ndash;743, July&nbsp;1980; published by Linthicum, ML, USA:
 * Institute for Operations Research and the Management Sciences (INFORMS)
 * and&nbsp;Cambridge, MA, USA: HighWire Press (Stanford University).
 * doi:&nbsp;<a
 * href="http://dx.doi.org/10.1287/mnsc.26.7.736">10.1287/mnsc.26
 * .7.736</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/00251909">0025-1909</a></div></li>
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
 * <li><div><span id="cite_JSV1991PGAATTSP" />Prasanna Jog, Jung Y. Suh,
 * and&nbsp;<a href="http://www.cs.indiana.edu/~vgucht/">Dirk Van
 * Gucht</a>: <span style="font-weight:bold">&ldquo;Parallel Genetic
 * Algorithms Applied to the Traveling Salesman Problem,&rdquo;</span> in
 * <span style="font-style:italic;font-family:cursive;">SIAM Journal on
 * Optimization (SIOPT)</span> 1(4):515&ndash;529, 1991; published by
 * Philadelphia, PA, USA: Society for Industrial and Applied Mathematics
 * (SIAM). doi:&nbsp;<a
 * href="http://dx.doi.org/10.1137/0801031">10.1137/0801031</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/10526234">1052-6234</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/10957189">1095-7189</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=SJOPE8"
 * >SJOPE8</a></div></li>
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
 * href ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.94.7376">
 * 10.1 .1.94.7376</a></div></div></li>
 * <li><div><span id="cite_B2000GAATTSP" />Kylie Bryant: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Genetic Algorithms
 * and the Traveling Salesman Problem,&rdquo;</span> Master's Thesis,
 * December&nbsp;2000, Claremont, CA, USA: Harvey Mudd College (HMC),
 * Department of Mathematics. <div>link: [<a href=
 * "http://www.math.hmc.edu/seniorthesis/archives/2001/kbryant/kbryant-2001-thesis.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_SD2006ARKGAFTGTSP" />Lawrence V. Snyder
 * and&nbsp;Mark S. Daskin: <span style="font-weight:bold">&ldquo;A
 * Random-Key Genetic Algorithm for the Generalized Traveling Salesman
 * Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">European Journal of
 * Operational Research (EJOR)</span> 174(1):38&ndash;53, October&nbsp;1,
 * 2006; published by Amsterdam, The Netherlands: Elsevier Science
 * Publishers B.V. and&nbsp;Amsterdam, The Netherlands: North-Holland
 * Scientific Publishers Ltd.. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1016/j.ejor.2004.09.057"
 * >10.1016/j.ejor.2004.09.057</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03772217">0377-2217</a>. <div>links:
 * [<a href="http://www.lehigh.edu/~lvs2/Papers/gtsp_ejor.pdf">1</a>]
 * and&nbsp;[<a href="http://www.lehigh.edu/~lvs2/Papers/gtsp.pdf">2</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.87.6258">
 * 10.1 .1.87.6258</a>, <a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.1.9730"
 * >10.1.1.1.9730</a>, and&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.71.5810"
 * >10.1.1.71.5810</a></div></div></li>
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
 * <li><div><span id="cite_WY2004AHEAFTSP" />Christopher M. White
 * and&nbsp;<a href="http://www.ece.okstate.edu/faculty/yen/">Gary G.
 * Yen</a>: <span style="font-weight:bold">&ldquo;A Hybrid Evolutionary
 * Algorithm for Traveling Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the IEEE
 * Congress on Evolutionary Computation (CEC'04)</span>,
 * June&nbsp;20&ndash;23, 2004, Portland, OR, USA, pages 1473&ndash;1478,
 * Los Alamitos, CA, USA: IEEE Computer Society Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780385152">0-7803-8515-2</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780385153">978-0-7803
 * -8515-3</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CEC.2004.1331070"
 * >10.1109/CEC.2004.1331070</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=I8_5AAAACAAJ">I8_5AAAACAAJ</a>;
 * INSPEC Accession Number:&nbsp;8229101</div></li>
 * <li><div><span id="cite_YLYW2007AFEAFTSP" />Xue-song Yan, Han-min Liu,
 * Jia Yan, and&nbsp;Qing-hua Wu: <span style="font-weight:bold">&ldquo;A
 * Fast Evolutionary Algorithm for Traveling Salesman
 * Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the Third
 * International Conference on Advances in Natural Computation
 * (ICNC'07)</span>, August&nbsp;24&ndash;27, 2007, Haikou, Hainan, China,
 * pages 85&ndash;90, Jingsheng Lei, JingTao Yao, and&nbsp;Qingfu Zhang,
 * editors, Los Alamitos, CA, USA: IEEE Computer Society Press.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0769528759">0-7695-2875-9</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780769528755">978-0-7695
 * -2875-5</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/ICNC.2007.23">10.1109/ICNC.2007.23</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/166273050">166273050</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=oAIEHwAACAAJ">oAIEHwAACAAJ</a>;
 * INSPEC Accession Number:&nbsp;9874134</div></li>
 * <li><div><span id="cite_BW2007PEAFTTSP" />Wojciech Bo&#380;ejko
 * and&nbsp;Mieczys&#322;aw Wodecki: <span
 * style="font-weight:bold">&ldquo;Parallel Evolutionary Algorithm for the
 * Traveling Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Journal of Numerical
 * Analysis, Industrial and Applied Mathematics (JNAIAM)</span>
 * 2(3-4):129&ndash;137, October&nbsp;27, 2007; published by Athens,
 * Greece: European Society of Computational Methods in Sciences and
 * Engineering (ESCMSE). ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/17908140">1790-8140</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/17908159">1790-8159</a>; further
 * information: [<a href="http://www.jnaiam.org/">1</a>]. <div>link: [<a
 * href=
 * "http://www.jnaiam.org/new/uploads/files/fd9b874e0a955a726a455644949f9105.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_T2009ESFTTSPBAPBEA" />Madeleine Theile: <span
 * style="font-weight:bold">&ldquo;Exact Solutions to the Traveling
 * Salesperson Problem by a Population-Based Evolutionary
 * Algorithm,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the 9th
 * European Conference on Evolutionary Computation in Combinatorial
 * Optimization (EvoCOP'09)</span>, April&nbsp;15&ndash;17, 2009,
 * T&#252;bingen, Germany: Eberhard-Karls-Universit&#228;t T&#252;bingen,
 * Fakult&#228;t f&#252;r Informations- und Kognitionswissenschaften, pages
 * 145&ndash;155, <a href="http://www.lcc.uma.es/~ccottap/">Carlos
 * Cotta</a> and&nbsp;Peter Cowling, editors, volume 5482/2009 of
 * Theoretical Computer Science and General Issues (SL 1), Lecture Notes in
 * Computer Science (LNCS), Berlin, Germany: Springer-Verlag GmbH.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3642010083">3-642-01008-3</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783642010088">978-3-642-
 * 01008-8</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/978-3-642-01009-5_13"
 * >10.1007/978-3-642-01009-5_13</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a></div></li>
 * <li><div><span id="cite_YC2011AEGAWFCMCFTSP" />Jong-Won Yoon
 * and&nbsp;Sung-Bae Cho: <span style="font-weight:bold">&ldquo;An
 * Efficient Genetic Algorithm with Fuzzy C-Means Clustering for Traveling
 * Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the 12th
 * IEEE Congress on Evolutionary Computation (CEC'11)</span>,
 * June&nbsp;5&ndash;8, 2011, New Orleans, LA, USA, pages 1452&ndash;1456.
 * doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CEC.2011.5949786">10.1109/CEC
 * .2011.5949786</a>; INSPEC Accession Number:&nbsp;12175749</div></li>
 * <li><div><span id="cite_WHH2009TBOPCFTTSP" /><a
 * href="http://www.cs.colostate.edu/~whitley/">L. Darrell Whitley</a>,
 * Doug Hains, and&nbsp;Adele Howe: <span
 * style="font-weight:bold">&ldquo;Tunneling between Optima: Partition
 * Crossover for the Traveling Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the 11th
 * Annual Conference on Genetic and Evolutionary Computation
 * (GECCO'09)</span>, July&nbsp;8&ndash;12, 2009, Montr&#233;al, QC,
 * Canada: Delta Centre-Ville Hotel, pages 915&ndash;922, <a
 * href="http://wi.bwl.uni-mainz.de/rothlauf.html.de">Franz Rothlauf</a>,
 * <a href="https://www.ads.tuwien.ac.at/raidl/">G&#252;nther R. Raidl</a>,
 * <a href="http://web.iti.upv.es/~anna/">Anna Isabel
 * Esparcia-Alc&#225;zar</a>, <a
 * href="http://people.cs.nctu.edu.tw/~ypchen/">Ying-Ping Chen</a> <span
 * style="color:gray">[&#38515;&#31310;&#24179;</span>], Gabriela Ochoa,
 * Ender Ozcan, <a href="http://www.lri.fr/~marc/">Marc Schoenauer</a>, <a
 * href="http://www.lri.fr/~auger/">Anne Auger</a>, <a
 * href="https://homepages.fhv.at/hgb/">Hans-Georg Beyer</a>, <a
 * href="http://www.lri.fr/~hansen/">Nikolaus Hansen</a>, <a
 * href="http://www.researchgate.net/profile/Steffen_Finck/">Steffen
 * Finck</a>, <a
 * href="https://tao.lri.fr/tiki-index.php?page=people">Raymond Ros</a>, <a
 * href="http://www.cs.colostate.edu/~whitley/">L. Darrell Whitley</a>,
 * Garnett Wilson, Simon Harding, <a
 * href="http://www0.cs.ucl.ac.uk/staff/W.Langdon/">William Benjamin
 * Langdon</a>, Man Leung Wong, Laurence D. Merkle, Frank W. Moore, Sevan
 * G. Ficici, William Rand, Rick L. Riolo, Nawwaf Kharma, William R.
 * Buckley, <a href="http://www.elec.york.ac.uk/staff/jfm7.html">Julian
 * Francis Miller</a>, <a
 * href="http://www.cs.utexas.edu/~kstanley/">Kenneth Owen Stanley</a>,
 * Jaume Bacardit i Pe&#241;arroya, Will N. Browne, Jan Drugowitsch, Nicola
 * Beume, <a href="http://ls11-www.cs.uni-dortmund.de/people/preuss/">Mike
 * Preu&#223;</a>, Stephen Frederick Smith, <a
 * href="http://www.ce.unipr.it/people/cagnoni/">Stefano Cagnoni</a>,
 * Alexandru Floares, Aaron Baughman, <a
 * href="http://www.gustafsonresearch.com/publications.html">Steven Matt
 * Gustafson</a>, Maarten Keijzer, Arthur Kordon, and&nbsp;<a
 * href="http://www.cs.colby.edu/~congdon/">Clare Bates Congdon</a>,
 * editors, New York, NY, USA: Association for Computing Machinery (ACM).
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781605583259">978-1-60558
 * -325-9</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1145/1569901.1570026"
 * >10.1145/1569901.1570026</a>; further information: [<a
 * href="http://www.sigevo.org/gec-summit-2009/instructions-papers.html"
 * >1</a>]</div></li>
 * <li><div><span id="cite_GPL2009GAPGAFFNOSTTTSP" />Jonatan G&#243;mez,
 * Roberto Poveda, and&nbsp;Elizabeth Le&#243;n: <span
 * style="font-weight:bold">&ldquo;Grisland: A Parallel Genetic Algorithm
 * for Finding Near Optimal Solutions to the Traveling Salesman
 * Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the 11th
 * Annual Conference on Genetic and Evolutionary Computation
 * (GECCO'09)</span>, July&nbsp;8&ndash;12, 2009, Montr&#233;al, QC,
 * Canada: Delta Centre-Ville Hotel, pages 2035&ndash;2040, <a
 * href="http://wi.bwl.uni-mainz.de/rothlauf.html.de">Franz Rothlauf</a>,
 * <a href="https://www.ads.tuwien.ac.at/raidl/">G&#252;nther R. Raidl</a>,
 * <a href="http://web.iti.upv.es/~anna/">Anna Isabel
 * Esparcia-Alc&#225;zar</a>, <a
 * href="http://people.cs.nctu.edu.tw/~ypchen/">Ying-Ping Chen</a> <span
 * style="color:gray">[&#38515;&#31310;&#24179;</span>], Gabriela Ochoa,
 * Ender Ozcan, <a href="http://www.lri.fr/~marc/">Marc Schoenauer</a>, <a
 * href="http://www.lri.fr/~auger/">Anne Auger</a>, <a
 * href="https://homepages.fhv.at/hgb/">Hans-Georg Beyer</a>, <a
 * href="http://www.lri.fr/~hansen/">Nikolaus Hansen</a>, <a
 * href="http://www.researchgate.net/profile/Steffen_Finck/">Steffen
 * Finck</a>, <a
 * href="https://tao.lri.fr/tiki-index.php?page=people">Raymond Ros</a>, <a
 * href="http://www.cs.colostate.edu/~whitley/">L. Darrell Whitley</a>,
 * Garnett Wilson, Simon Harding, <a
 * href="http://www0.cs.ucl.ac.uk/staff/W.Langdon/">William Benjamin
 * Langdon</a>, Man Leung Wong, Laurence D. Merkle, Frank W. Moore, Sevan
 * G. Ficici, William Rand, Rick L. Riolo, Nawwaf Kharma, William R.
 * Buckley, <a href="http://www.elec.york.ac.uk/staff/jfm7.html">Julian
 * Francis Miller</a>, <a
 * href="http://www.cs.utexas.edu/~kstanley/">Kenneth Owen Stanley</a>,
 * Jaume Bacardit i Pe&#241;arroya, Will N. Browne, Jan Drugowitsch, Nicola
 * Beume, <a href="http://ls11-www.cs.uni-dortmund.de/people/preuss/">Mike
 * Preu&#223;</a>, Stephen Frederick Smith, <a
 * href="http://www.ce.unipr.it/people/cagnoni/">Stefano Cagnoni</a>,
 * Alexandru Floares, Aaron Baughman, <a
 * href="http://www.gustafsonresearch.com/publications.html">Steven Matt
 * Gustafson</a>, Maarten Keijzer, Arthur Kordon, and&nbsp;<a
 * href="http://www.cs.colby.edu/~congdon/">Clare Bates Congdon</a>,
 * editors, New York, NY, USA: Association for Computing Machinery (ACM).
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781605583259">978-1-60558
 * -325-9</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1145/1570256.1570272"
 * >10.1145/1570256.1570272</a>; further information: [<a
 * href="http://www.sigevo.org/gec-summit-2009/instructions-papers.html"
 * >1</a>]</div></li>
 * <li><div><span id="cite_JM2002TMROGEACFTTDET" />Kyomin Jung
 * and&nbsp;Byung-Ro Moon: <span style="font-weight:bold">&ldquo;Toward
 * Minimal Restriction of Genetic Encoding and Crossovers for the
 * Two-Dimensional Euclidean TSP,&rdquo;</span> 6(6):557&ndash;565,
 * December&nbsp;2002. INSPEC Accession Number:&nbsp;7484684. <div>link:
 * [<a href="10.1109/TEVC.2002.804321">1</a>]</div></div></li>
 * <li><div><span id="cite_BFM1999MA" />Luciana Buriol, Paulo M.
 * Fran&#231;a, and&nbsp;<a href=
 * "http://www.newcastle.edu.au/staff/research-profile/Pablo_Moscato/"
 * >Pablo Moscato</a>: <span style="font-weight:bold">&ldquo;A New Memetic
 * Algorithm for the Asymmetric Traveling Salesman Problem,&rdquo;</span>
 * in <span style="font-style:italic;font-family:cursive;">Journal of
 * Heuristics</span> 10(5):483&ndash;506, September&nbsp;2004; published by
 * Dordrecht, Netherlands: Springer Netherlands. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1023/B:HEUR.0000045321.59202.52"
 * >10.1023/B:HEUR.0000045321.59202.52</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/13811231">1381-1231</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15729397">1572-9397</a>. <div>links:
 * [<a href=
 * "http://www.springerlink.com/content/w617486q60mphg88/fulltext.pdf"
 * >1</a>] and&nbsp;[<a
 * href="http://citeseer.ist.psu.edu/544761.html">2</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.20.1660">
 * 10.1 .1.20.1660</a></div></div></li>
 * <li><div><span id="cite_MF2001MAFTTSP" /><a
 * href="http://dag.informatik.uni-kl.de/people/merz/">Peter Merz</a>
 * and&nbsp;<a href=
 * "http://www.uni-marburg.de/fb12/verteilte_systeme/mitarbeiter/webfreisleb"
 * >Bernd Freisleben</a>: <span style="font-weight:bold">&ldquo;Memetic
 * Algorithms for the Traveling Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Complex Systems</span>
 * 13(4):297&ndash;345, 2001; published by Champaign, IL, USA: Complex
 * Systems Publications, Inc.. ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/08912513">0891-2513</a>. <div>links:
 * [<a href="http://www.complex-systems.com/pdf/13-4-1.pdf">1</a>]
 * and&nbsp;[<a
 * href="http://agva.informatik.uni-kl.de/papers/cs-tsp.ps.gz">2</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href
 * ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.6.4205">10.1
 * .1.6.4205</a></div></div></li>
 * <li><div><span id="cite_BHP2001AHHFTTSP" />Ranieri Baraglia, Jos&#233;
 * Ignacio Hidalgo, and&nbsp;Raffaele Perego: <span
 * style="font-weight:bold">&ldquo;A Hybrid Heuristic for the Traveling
 * Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">IEEE Transactions on
 * Evolutionary Computation (IEEE-EC)</span> 5(6):613&ndash;622,
 * December&nbsp;2001; published by Washington, DC, USA: IEEE Computer
 * Society. LCCN:&nbsp;<a href="http://lccn.loc.gov/97648327">97648327</a>;
 * doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/4235.974843">10.1109/4235.974843</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/1089778X">1089-778X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/19410026">1941-0026</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=ITEVF5"
 * >ITEVF5</a>; INSPEC Accession Number:&nbsp;7156723; further information:
 * [<a href="http://www.ieee-cis.org/pubs/tec/">1</a>]</div></li>
 * <li><div><span id="cite_DG1996ACFTTSP" /><a
 * href="https://en.wikipedia.org/wiki/Marco_Dorigo">Marco Dorigo</a>
 * and&nbsp;<a href="http://www.idsia.ch/~luca/">Luca Maria
 * Gambardella</a>: <span style="font-weight:bold">&ldquo;Ant Colonies for
 * the Traveling Salesman Problem,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;TR/IRIDIA/1996-3, 1996; published by Brussels, Belgium:
 * Universit&#233; Libre de Bruxelles, Institut de Recherches
 * Interdisciplinaires et de D&#233;veloppements en Intelligence
 * Artificielle (IRIDIA). <div>link: [<a
 * href="http://www.idsia.ch/~luca/acs-bio97.pdf">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.54.7734">
 * 10.1 .1.54.7734</a></div></div></li>
 * <li><div><span id="cite_GD1996ACO" /><a
 * href="http://www.idsia.ch/~luca/">Luca Maria Gambardella</a> and&nbsp;<a
 * href="https://en.wikipedia.org/wiki/Marco_Dorigo">Marco Dorigo</a>:
 * <span style="font-weight:bold">&ldquo;Solving Symmetric and Asymmetric
 * TSPs by Ant Colonies,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of IEEE
 * International Conference on Evolutionary Computation (CEC'96)</span>,
 * May&nbsp;20&ndash;22, 1996, Nagoya, Aichi, Japan: Nagoya University,
 * Symposium &amp; Toyoda Auditorium, pages 622&ndash;627, Keisoku
 * Jid&#333; and&nbsp;Seigyo Gakkai, editors, Los Alamitos, CA, USA: IEEE
 * Computer Society Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780329023">0-7803-2902-3</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780329027">978-0-7803
 * -2902-7</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/ICEC.1996.542672"
 * >10.1109/ICEC.1996.542672</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/312788226">312788226</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=dNZVAAAAMAAJ">dNZVAAAAMAAJ</a>.
 * <div>links: [<a
 * href="http://citeseer.ist.psu.edu/gambardella96solving.html">1</a>]
 * and&nbsp;[<a href="http://www.idsia.ch/~luca/icec96-acs.pdf">2</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.40.4846">
 * 10.1 .1.40.4846</a></div></div></li>
 * <li><div><span id="cite_DG1997ACOCTSP" /><a
 * href="https://en.wikipedia.org/wiki/Marco_Dorigo">Marco Dorigo</a>
 * and&nbsp;<a href="http://www.idsia.ch/~luca/">Luca Maria
 * Gambardella</a>: <span style="font-weight:bold">&ldquo;Ant Colony
 * System: A Cooperative Learning Approach to the Traveling Salesman
 * Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">IEEE Transactions on
 * Evolutionary Computation (IEEE-EC)</span> 1(1):53&ndash;66,
 * April&nbsp;1997; published by Washington, DC, USA: IEEE Computer
 * Society. LCCN:&nbsp;<a href="http://lccn.loc.gov/97648327">97648327</a>;
 * doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/4235.585892">10.1109/4235.585892</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/1089778X">1089-778X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/19410026">1941-0026</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=ITEVF5"
 * >ITEVF5</a>; further information: [<a
 * href="http://www.ieee-cis.org/pubs/tec/">1</a>]. <div>link: [<a
 * href="http://www.idsia.ch/~luca/acs-ec97.pdf">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.49.7702">
 * 10.1 .1.49.7702</a></div></div></li>
 * <li><div><span id="cite_SH1997MMASALSFTTSP" /><a
 * href="http://iridia.ulb.ac.be/~stuetzle/">Thomas St&#252;tzle</a>
 * and&nbsp;<a href="http://www.cs.ubc.ca/~hoos/">Holger H. Hoos</a>: <span
 * style="font-weight:bold">&ldquo;MAX-MIN Ant System and Local Search for
 * the Traveling Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the IEEE
 * International Conference on Evolutionary Computation (CEC'97)</span>,
 * April&nbsp;13&ndash;16, 1997, Indianapolis, IN, USA, pages
 * 309&ndash;314, <a href="http://www.liacs.nl/~baeck/contact.htm">Thomas
 * B&#228;ck</a>, <a href="http://cs.adelaide.edu.au/~zbyszek/">Zbigniew
 * Michalewicz</a>, and&nbsp;<a href="http://www.cs.bham.ac.uk/~xin/">Xin
 * Yao</a> <span style="color:gray">[&#23002;&#26032;</span>], editors,
 * Piscataway, NJ, USA: IEEE Computer Society. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780339495">0-7803-3949-5</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780339491">978-0-7803
 * -3949-1</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/ICEC.1997.592327"
 * >10.1109/ICEC.1997.592327</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=6JZVAAAAMAAJ">6JZVAAAAMAAJ</a>;
 * INSPEC Accession Number:&nbsp;5573070. <div>link: [<a
 * href="http://www.gta.ufrj.br/ensino/cpe717-2011/stutzle97-icec.pdf"
 * >1</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.46.609"
 * >10.1.1.46.609</a></div></div></li>
 * <li><div><span id="cite_TTCY2010AFACOFTSP" />Shih-Pang Tseng, Chun-Wei
 * Tsai, Ming-Chao Chiang, and&nbsp;Chu-Sing Yang: <span
 * style="font-weight:bold">&ldquo;A Fast Ant Colony Optimization for
 * Traveling Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">11th IEEE Congress on
 * Evolutionary Computation (CEC'10); 2010 IEEE World Congress on
 * Computation Intelligence (WCCI'10)</span>, July&nbsp;18&ndash;23, 2010,
 * Barcelona, Catalonia, Spain: Centre de Convencions Internacional de
 * Barcelona, pages 1&ndash;6, Piscataway, NJ, USA: IEEE Computer Society.
 * doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CEC.2010.5586153">10.1109/CEC
 * .2010.5586153</a>; INSPEC Accession Number:&nbsp;11568024</div></li>
 * <li><div><span id="cite_KYMYY2010APFZCOHGAFLSTSP" />Masafumi Kuroda,
 * Kunihito Yamamori, Masaharu Munetomo, Moritoshi Yasunaga, and&nbsp;Ikuo
 * Yoshihara: <span style="font-weight:bold">&ldquo;A Proposal for Zoning
 * Crossover of Hybrid Genetic Algorithms for Large-Scale Traveling
 * Salesman Problems,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">11th IEEE Congress on
 * Evolutionary Computation (CEC'10); 2010 IEEE World Congress on
 * Computation Intelligence (WCCI'10)</span>, July&nbsp;18&ndash;23, 2010,
 * Barcelona, Catalonia, Spain: Centre de Convencions Internacional de
 * Barcelona, pages 1&ndash;6, Piscataway, NJ, USA: IEEE Computer Society.
 * doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CEC.2010.5586061">10.1109/CEC
 * .2010.5586061</a>; INSPEC Accession Number:&nbsp;11567904</div></li>
 * <li><div><span id="cite_YC2008ANRGATTSP" />Shiu Yin Yuen and&nbsp;Chi
 * Kin Chow: <span style="font-weight:bold">&ldquo;Applying Non-Revisiting
 * Genetic Algorithm to Traveling Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the IEEE
 * Congress on Evolutionary Computation (CEC'08); Computational
 * Intelligence: Research Frontiers &#8210; IEEE World Congress on
 * Computational Intelligence &#8210; Plenary/Invited Lectures
 * (WCCI)</span>, June&nbsp;1&ndash;6, 2008, Hong Kong (Xianggang), China:
 * Hong Kong Convention and Exhibition Centre, pages 2217&ndash;2224, <a
 * href="http://cs.adelaide.edu.au/~zbyszek/">Zbigniew Michalewicz</a>, <a
 * href="http://ai.cs.wayne.edu/ai/member%20webs/Reynolds/">Robert G.
 * Reynolds</a>, Jacek M. Zurada, <a
 * href="http://www.ece.okstate.edu/faculty/yen/">Gary G. Yen</a>,
 * and&nbsp;Jun Wang, editors, volume 5050/2008 of Theoretical Computer
 * Science and General Issues (SL 1), Lecture Notes in Computer Science
 * (LNCS), Piscataway, NJ, USA: IEEE Computer Society and&nbsp;Berlin,
 * Germany: Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1424418224">1-4244-1822-4</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781424418220">978-1-4244
 * -1822-0</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/2008927523">2008927523</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CEC.2008.4631093"
 * >10.1109/CEC.2008.4631093</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/244010459">244010459</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=W5HqHwAACAAJ">W5HqHwAACAAJ</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a>; INSPEC
 * Accession Number:&nbsp;10221935</div></li>
 * <li><div><span id="cite_FTSPL2007AGAFTGTSP" />M. Fatih Tasgetiren, <a
 * href="http://www3.ntu.edu.sg/home/epnsugan/">Ponnuthurai Nagaratnam
 * Suganthan</a>, Quan-Ke Pan, and&nbsp;Yun-Chia Liang: <span
 * style="font-weight:bold">&ldquo;A Genetic Algorithm for the Generalized
 * Traveling Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the IEEE
 * Congress on Evolutionary Computation (CEC'07)</span>,
 * September&nbsp;25&ndash;28, 2007, Singapore: Swiss&#244;tel The
 * Stamford, pages 2382&ndash;2389, Piscataway, NJ, USA: IEEE Computer
 * Society. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1424413397">1-4244-1339-7</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781424413393">978-1-4244
 * -1339-3</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CEC.2007.4424769"
 * >10.1109/CEC.2007.4424769</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/257656187">257656187</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=duo6GQAACAAJ">duo6GQAACAAJ</a>;
 * INSPEC Accession Number:&nbsp;9889239</div></li>
 * <li><div><span id="cite_AW2012ITPOMMASOTTUSA" />Ashraf M. Abdelbar
 * and&nbsp;Donald C. Wunsch II: <span
 * style="font-weight:bold">&ldquo;Improving the Performance of MAX-MIN Ant
 * System on the TSP using Stubborn Ants,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Companion Material
 * Proceedings Genetic and Evolutionary Computation Conference
 * (GECCO'12)</span>, July&nbsp;7&ndash;11, 2012, Philadelphia, PA, USA:
 * Doubletree by Hilton Hotel Philadelphia Center City, pages
 * 1395&ndash;1396, Terence Soule and&nbsp;<a
 * href="http://www.epistasis.org/jason.html">Jason H. Moore</a>, editors,
 * New York, NY, USA: Association for Computing Machinery (ACM).
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781450311786">978-1-4503
 * -1178-6</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1145/2330784.2330949"
 * >10.1145/2330784.2330949</a></div></li>
 * <li><div><span id="cite_G2004AAISAMCE" /><a
 * href="http://www.aifb.kit.edu/web/Michael_Guntsch/en">Michael
 * Guntsch</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Ant Algorithms in
 * Stochastic and Multi-Criteria Environments,&rdquo;</span> PhD Thesis,
 * January&nbsp;13, 2004, Karlsruhe, Germany: University of Karlsruhe
 * (Friedriciana), Department of Economics and Business Engineering
 * and&nbsp;Karlsruhe, Germany: University of Karlsruhe (Friedriciana),
 * Institute for Applied Computer Science and Formal Description Methods
 * (AIFB). Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=Lf1ztwAACAAJ">Lf1ztwAACAAJ</a>.
 * <div>links: [<a
 * href="http://www.lania.mx/~ccoello/EMOO/thesis_guntsch.pdf.gz">1</a>]
 * and&nbsp;[<a
 * href="http://digbib.ubka.uni-karlsruhe.de/volltexte/212004">2</a>];
 * urn:&nbsp;<a href=
 * "http://wm-urn.org/?urn=urn:nbn:de:swb:90-AAA2120045&amp;redirect=1"
 * >urn:nbn:de:swb:90-AAA2120045</a></div></div></li>
 * <li><div><span id="cite_GM2001ACODTSP" /><a
 * href="http://www.aifb.kit.edu/web/Michael_Guntsch/en">Michael
 * Guntsch</a> and&nbsp;<a href=
 * "http://pacosy.informatik.uni-leipzig.de/15-0-Prof+Dr+Martin+Middendorf.html"
 * >Martin Middendorf</a>: <span style="font-weight:bold">&ldquo;Pheromone
 * Modification Strategies for Ant Algorithms Applied to Dynamic
 * TSP,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Applications of
 * Evolutionary Computing, Proceedings of EvoWorkshops 2001: EvoCOP,
 * EvoFlight, EvoIASP, EvoLearn, and EvoSTIM (EvoWorkshops'01)</span>,
 * April&nbsp;18&ndash;20, 2001, Lake Como, Milan, Italy, pages
 * 213&ndash;222, Egbert J. W. Boers, Jens Gottlieb, Pier Luca Lanzi,
 * Robert Elliott Smith, <a
 * href="http://www.ce.unipr.it/people/cagnoni/">Stefano Cagnoni</a>, <a
 * href
 * ="http://www.soc.napier.ac.uk/~emmah/Prof_Emma_Hart/Welcome.html">Emma
 * Hart</a>, <a href="https://www.ads.tuwien.ac.at/raidl/">G&#252;nther R.
 * Raidl</a>, and&nbsp;Harald Tijink, editors, volume 2037/2001 of Lecture
 * Notes in Computer Science (LNCS), Berlin, Germany: Springer-Verlag GmbH.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540419209">3-540-41920-9</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540419204">978-3-540-
 * 41920-4</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/3-540-45365-2_22"
 * >10.1007/3-540-45365-2_22</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=EhROxow98NoC">EhROxow98NoC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a>. <div>link:
 * [<a href=
 * "http://pacosy.informatik.uni-leipzig.de/pv/Personen/middendorf/Papers/Papers/EvoCOP2001.Report.ps"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_GMS2001ACODTSP" /><a
 * href="http://www.aifb.kit.edu/web/Michael_Guntsch/en">Michael
 * Guntsch</a>, <a href=
 * "http://pacosy.informatik.uni-leipzig.de/15-0-Prof+Dr+Martin+Middendorf.html"
 * >Martin Middendorf</a>, and&nbsp;Hartmut Schmeck: <span
 * style="font-weight:bold">&ldquo;An Ant Colony Optimization Approach to
 * Dynamic TSP,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the
 * Genetic and Evolutionary Computation Conference (GECCO'01)</span>,
 * July&nbsp;7&ndash;11, 2001, San Francisco, CA, USA: Holiday Inn Golden
 * Gateway Hotel, pages 860&ndash;867, Lee Spector, <a
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
 * <div>links: [<a href="http://citeseer.ist.psu.edu/693476.html">1</a>]
 * and&nbsp;[<a href=
 * "http://pacosy.informatik.uni-leipzig.de/middendorf/Papers/GECCO2001-Pap.ps"
 * >2</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.58.1398"
 * >10.1.1.58.1398</a></div></div></li>
 * <li><div><span id="cite_OHSRD2011ADAOTPBACOAFTTATQ" />Sabrina M.
 * Oliveira, Mohamed Saifullah Hussin, <a
 * href="http://iridia.ulb.ac.be/~stuetzle/">Thomas St&#252;tzle</a>,
 * Andrea Roli, and&nbsp;<a
 * href="https://en.wikipedia.org/wiki/Marco_Dorigo">Marco Dorigo</a>:
 * <span style="font-weight:bold">&ldquo;A Detailed Analysis of the
 * Population-based Ant Colony Optimization Algorithm for the TSP and the
 * QAP,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;TR/IRIDIA/2011-006, February&nbsp;2011; published by
 * Brussels, Belgium: Universit&#233; Libre de Bruxelles, Institut de
 * Recherches Interdisciplinaires et de D&#233;veloppements en Intelligence
 * Artificielle (IRIDIA). ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/17813794">1781-3794</a>. <div>link:
 * [<a href=
 * "http://iridia.ulb.ac.be/IridiaTrSeries/rev/IridiaTr2011-006r001.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_OHSRD2011ADAOTPBACOAFTTATQ2" />Sabrina M.
 * Oliveira, Mohamed Saifullah Hussin, <a
 * href="http://iridia.ulb.ac.be/~stuetzle/">Thomas St&#252;tzle</a>,
 * Andrea Roli, and&nbsp;<a
 * href="https://en.wikipedia.org/wiki/Marco_Dorigo">Marco Dorigo</a>:
 * <span style="font-weight:bold">&ldquo;A Detailed Analysis of the
 * Population-based Ant Colony Optimization Algorithm for the TSP and the
 * QAP,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the
 * Genetic and Evolutionary Computation Conference (GECCO'11)</span>,
 * July&nbsp;12&ndash;16, 2011, Dublin, Ireland, pages 13&ndash;14.
 * doi:&nbsp;<a href="http://dx.doi.org/10.1145/2001858.2001866">10.1145/
 * 2001858.2001866</a>. <div>link: [<a
 * href="http://code.ulb.ac.be/dbfiles/OliHusStu-etal2011gecco.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_LG2003DACOFT" />Yong Li and&nbsp;Shihua Gong:
 * <span style="font-weight:bold">&ldquo;Dynamic Ant Colony Optimisation
 * for TSP,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">The International Journal
 * of Advanced Manufacturing Technology</span> 22(7-8):528&ndash;533,
 * November&nbsp;2003; published by London, UK: Springer-Verlag London
 * Limited. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/s00170-002-1478-9">
 * 10.1007/s00170-002-1478-9</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/02683768">0268-3768</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/14333015">1433-3015</a></div></li>
 * <li><div><span id="cite_Z2009RAOAACOAFTI" />Yuren Zhou: <span
 * style="font-weight:bold">&ldquo;Runtime Analysis of an Ant Colony
 * Optimization Algorithm for TSP Instances,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">IEEE Transactions on
 * Evolutionary Computation (IEEE-EC)</span> 13(5):1083&ndash;1092,
 * October&nbsp;2009; published by Washington, DC, USA: IEEE Computer
 * Society. LCCN:&nbsp;<a href="http://lccn.loc.gov/97648327">97648327</a>;
 * doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/TEVC.2009.2016570">10.1109/TEVC
 * .2009.2016570</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/1089778X">1089-778X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/19410026">1941-0026</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=ITEVF5"
 * >ITEVF5</a>; INSPEC Accession Number:&nbsp;10879793; further
 * information: [<a href="http://www.ieee-cis.org/pubs/tec/">1</a>]</div></li>
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
 * href="http://dx.doi.org/10.1126/science.220.4598.671">10.1126
 * /science.220.4598.671</a>; ISSN:&nbsp;<a
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
 * <li><div><span id="cite_VC1985SA" />Vladim&#237;r &#268;ern&#253; aka.
 * Vlado: <span style="font-weight:bold">&ldquo;Thermodynamical Approach to
 * the Traveling Salesman Problem: An Efficient Simulation
 * Algorithm,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Journal of Optimization
 * Theory and Applications</span> 45(1):41&ndash;51, January&nbsp;1985;
 * published by Dordrecht, Netherlands: Springer Netherlands and&nbsp;New
 * York, NY, USA: Plenum Press. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/BF00940812">10.1007/BF00940812</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/00223239">0022-3239</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15732878">1573-2878</a>. <div>link:
 * [<a
 * href="http://mkweb.bcgsc.ca/papers/cerny-travelingsalesman.pdf">1</a>
 * ]</div></div></li>
 * <li><div><span id="cite_LXL2009HSAABOACSFT" />Yi Liu, Shengwu Xiong,
 * and&nbsp;Hongbing Liu: <span style="font-weight:bold">&ldquo;Hybrid
 * Simulated Annealing Algorithm based on Adaptive Cooling Schedule for
 * TSP,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the First
 * ACM/SIGEVO Summit on Genetic and Evolutionary Computation
 * (GEC'09)</span>, June&nbsp;12&ndash;14, 2009, Shanghai, China: Hua-Ting
 * Hotel &amp; Towers, pages 895&ndash;898, Lihong Xu, <a
 * href="http://www.egr.msu.edu/~goodman/">Erik D. Goodman</a>,
 * and&nbsp;Yongsheng Ding, editors, New York, NY, USA: ACM Press.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781605583266">978-1-60558
 * -326-6</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1145/1543834.1543969"
 * >10.1145/1543834.1543969</a>; further information: [<a
 * href="http://www.sigevo.org/gec-summit-2009/">1</a>]</div></li>
 * <li><div><span id="cite_MGPO1989SAPSAATSAFTTSP" />Miroslaw Malek, Mohan
 * Guruswamy, Mihix Pandya, and&nbsp;Howard Owens: <span
 * style="font-weight:bold">&ldquo;Serial and Parallel Simulated Annealing
 * and Tabu Search Algorithms for the Traveling Salesman
 * Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Annals of Operations
 * Research</span> 21(1):59&ndash;84, December&nbsp;1989; published by
 * Dordrecht, Netherlands: Springer Netherlands and&nbsp;Amsterdam, The
 * Netherlands: J. C. Baltzer AG, Science Publishers. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/BF02022093">10.1007/BF02022093</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/02545330">0254-5330</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15729338">1572-9338</a>. <div>link:
 * [<a
 * href="https://www.ida.liu.se/~zebpe/heuristic/papers/parallel_alg.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_CG1997TSTSP" />Buyang Cao and&nbsp;<a
 * href="http://en.wikipedia.org/wiki/Fred_W._Glover">Fred W. Glover</a>:
 * <span style="font-weight:bold">&ldquo;Tabu Search and Ejection
 * Chains-Application to a Node Weighted Version of the
 * Cardinality-Constrained TSP,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Management Science</span>
 * 43(7):908&ndash;921, July&nbsp;1997; published by Linthicum, ML, USA:
 * Institute for Operations Research and the Management Sciences (INFORMS)
 * and&nbsp;Cambridge, MA, USA: HighWire Press (Stanford University).
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/00251909">0025-1909</a></div></li>
 * <li><div><span id="cite_ZZC2007ANDPSOTSTSP" />Wen-liang Zhong, Jun
 * Zhang, and&nbsp;Wei-neng Chen: <span style="font-weight:bold">&ldquo;A
 * Novel Discrete Particle Swarm Optimization to Solve Traveling Salesman
 * Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the IEEE
 * Congress on Evolutionary Computation (CEC'07)</span>,
 * September&nbsp;25&ndash;28, 2007, Singapore: Swiss&#244;tel The
 * Stamford, pages 3283&ndash;3287, Piscataway, NJ, USA: IEEE Computer
 * Society. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1424413397">1-4244-1339-7</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781424413393">978-1-4244
 * -1339-3</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/257656187">257656187</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=duo6GQAACAAJ">duo6GQAACAAJ
 * </a></div></li>
 * <li><div><span id="cite_RDML2001STTSPWE" />V&#237;ctor Robles Forcada,
 * Pedro De Miguel Anasagasti, and&nbsp;<a href=
 * "http://cig.fi.upm.es/index.php?option=com_content&amp;view=article&amp;id=78&amp;Itemid=111"
 * >Pedro Larra&#241;aga</a>: <span style="font-weight:bold">&ldquo;Solving
 * the Traveling Salesman Problem with EDAs,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Estimation of
 * Distribution Algorithms &#8210; A New Tool for Evolutionary
 * Computation</span>, chapter 211&ndash;229, pages 211&ndash;229, <a href=
 * "http://cig.fi.upm.es/index.php?option=com_content&amp;view=article&amp;id=78&amp;Itemid=111"
 * >Pedro Larra&#241;aga</a> and&nbsp;<a
 * href="http://www.sc.ehu.es/ccwbayes/members/jalozano/home/index.html"
 * >Jos&#233; Antonio Lozano</a>, editors, volume 2 of Genetic Algorithms
 * and Evolutionary Computation, Boston, MA, USA: Springer US
 * and&nbsp;Norwell, MA, USA: Kluwer Academic Publishers. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0792374665">0-7923-7466-5</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780792374664">978-0-7923
 * -7466-4</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/978-1-4615-1539-5_10"
 * >10.1007/978-1-4615-1539-5_10</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/47996547">47996547</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=o0llxS4u93wC">o0llxS4u93wC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/19320167">1932-0167</a></div></li>
 * <li><div><span id="cite_T2002PMBGAIPRDUEH" /><a
 * href="http://www2.hannan-u.ac.jp/~tsutsui/index-e.html">Shigeyoshi
 * Tsutsui</a> <span style="color:gray">[&#31570;&#20117;
 * &#33538;&#32681;</span>]: <span
 * style="font-weight:bold">&ldquo;Probabilistic Model-Building Genetic
 * Algorithms in Permutation Representation Domain using Edge
 * Histogram,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the 7th
 * International Conference on Parallel Problem Solving from Nature (PPSN
 * VII)</span>, September&nbsp;7&ndash;11, 2002, Granada, Spain, pages
 * 224&ndash;233, Juan Juli&#225;n Merelo-Guerv&#243;s, Panagiotis
 * Adamidis, <a href="https://homepages.fhv.at/hgb/">Hans-Georg Beyer</a>,
 * Jos&#233; Luis Fern&#225;ndez-Villaca&#241;as Mart&#237;n, and&nbsp;<a
 * href="https://en.wikipedia.org/wiki/Hans-Paul_Schwefel">Hans-Paul
 * Schwefel</a>, editors, volume 2439/2002 of Lecture Notes in Computer
 * Science (LNCS), Berlin, Germany: Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540441395">3-540-44139-5</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540441397">978-3-540-
 * 44139-7</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/3-540-45712-7_22"
 * >10.1007/3-540-45712-7_22</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/248499908">248499908</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=KD-WMBb4AhkC">KD-WMBb4AhkC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a>. <div>links:
 * [<a href="http://www2.hannan-u.ac.jp/~tsutsui/ps/ppsn2002.pdf">1</a>]
 * and&nbsp;[<a href=
 * "http://www.researchgate.net/publication/220701611_Probabilistic_Model-Building_Genetic_Algorithms_in_Permutation_Representation_Domain_Using_Edge_Histogram/file/32bfe50e4d8b855800.pdf"
 * >2</a>]</div></div></li>
 * <li><div><span id="cite_T2009POAEAOAPWMCP" /><a
 * href="http://www2.hannan-u.ac.jp/~tsutsui/index-e.html">Shigeyoshi
 * Tsutsui</a> <span style="color:gray">[&#31570;&#20117;
 * &#33538;&#32681;</span>]: <span
 * style="font-weight:bold">&ldquo;Parallelization of an Evolutionary
 * Algorithm on a Platform with Multi-Core Processors,&rdquo;</span> in
 * <span style="font-style:italic;font-family:cursive;">Artificial
 * Evolution: Revised Selected Papers from the 9th International
 * Conference, Evolution Artificielle (EA'09)</span>,
 * October&nbsp;26&ndash;28, 2009, Strasbourg, France, pages 61&ndash;73,
 * Pierre Collet, Nicolas Monmarch&#233;, Pierrick Legrand, <a
 * href="http://www.lri.fr/~marc/">Marc Schoenauer</a>, and&nbsp;<a
 * href="http://evelyne.lutton.free.fr/">Evelyne Lutton</a>, editors,
 * volume 5975 of Theoretical Computer Science and General Issues (SL 1),
 * Lecture Notes in Computer Science (LNCS), Berlin, Germany:
 * Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3642141552">3642141552</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783642141553">978-3-642-
 * 14155-3</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/978-3-642-14156-0_6"
 * >10.1007/978-3-642-14156-0_6</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a></div></li>
 * <li><div><span id="cite_T2009EOUPSIEHSAWDLS" /><a
 * href="http://www2.hannan-u.ac.jp/~tsutsui/index-e.html">Shigeyoshi
 * Tsutsui</a> <span style="color:gray">[&#31570;&#20117;
 * &#33538;&#32681;</span>]: <span style="font-weight:bold">&ldquo;Effect
 * of Using Partial Solutions in Edge Histogram Sampling Algorithms with
 * Different Local Searches,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the 2009
 * International Conference on Systems, Man, and Cybernetics
 * (SMC'09)</span>, October&nbsp;11&ndash;14, 2009, San Antonio, TX, USA,
 * pages 2137&ndash;2142, Piscataway, NJ, USA: IEEE Computer Society.
 * doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/ICSMC.2009.5346283">10.1109/
 * ICSMC.2009.5346283</a>; INSPEC Accession Number:&nbsp;11004410</div></li>
 * <li><div><span id="cite_TPG2006NHVEHACOPIPD" /><a
 * href="http://www2.hannan-u.ac.jp/~tsutsui/index-e.html">Shigeyoshi
 * Tsutsui</a> <span style="color:gray">[&#31570;&#20117;
 * &#33538;&#32681;</span>], <a
 * href="http://www.cs.umsl.edu/~pelikan/">Martin Pelikan</a>, and&nbsp;<a
 * href="http://www.davidegoldberg.com/de-goldberg.html">David Edward
 * Goldberg</a>: <span style="font-weight:bold">&ldquo;Node Histogram vs.
 * Edge Histogram: A Comparison of PMBGAs in Permutation
 * Domains,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;2006009, July&nbsp;2006; published by St. Louis, MO, USA:
 * Missouri Estimation of Distribution Algorithms Laboratory (MEDAL),
 * University of Missouri. <div>CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.82.1175"
 * >10.1.1.82.1175</a></div></div></li>
 * <li><div><span id="cite_TPG2006EHBSWLSFSPP" /><a
 * href="http://www2.hannan-u.ac.jp/~tsutsui/index-e.html">Shigeyoshi
 * Tsutsui</a> <span style="color:gray">[&#31570;&#20117;
 * &#33538;&#32681;</span>], <a
 * href="http://www.cs.umsl.edu/~pelikan/">Martin Pelikan</a>,
 * and&nbsp;Ashish Ghosh: <span style="font-weight:bold">&ldquo;Edge
 * Histogram Based Sampling with Local Search for Solving Permutation
 * Problems,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">The International Journal
 * of Hybrid Intelligent Systems (IJHIS)</span> 3(1):11&ndash;22, 2006;
 * published by Amsterdam, The Netherlands: IOS Press. ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/14485869">1448-5869</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/18758819">1875-8819</a>; further
 * information: [<a href=
 * "http://www.iospress.nl/journal/international-journal-of-hybrid-intelligent-systems/"
 * >1</a>]. <div>links: [<a href=
 * "http://library.isical.ac.in/jspui/bitstream/10263/2664/1/edge%20histogram.pdf"
 * >1</a>] and&nbsp;[<a href=
 * "http://iospress.metapress.com/content/a53cen4pxbwpa8lh/fulltext.pdf"
 * >2</a>]</div></div></li>
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
 * <li><div><span id="cite_CHZ2012APBGAWBMARHFTTSP" />Pei-Chann Chang <span
 * style="color:gray">[&#24352;&#30334;&#26632;</span>], Wei-Hsiu Huang
 * <span style="color:gray">[&#40644;&#20255;&#20462;</span>],
 * and&nbsp;Zhen-Zhen Zhang <span
 * style="color:gray">[&#24352;&#30495;&#30495;</span>]: <span
 * style="font-weight:bold">&ldquo;A Puzzle-Based Genetic Algorithm with
 * Block Mining and Recombination Heuristic for the Traveling Salesman
 * Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Journal of Computer
 * Science and Technology (JCST)</span> 27(5):937&ndash;949,
 * September&nbsp;5, 2012; published by Beijing, China: Chinese Academy of
 * Sciences (CAS), Berlin, Germany: Springer-Verlag GmbH, and&nbsp;Beijing,
 * China: Science in China Press (SCP). doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/s11390-012-1275-3"
 * >10.1007/s11390-012-1275-3</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/10009000">1000-9000</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/18604749">1860-4749</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=JCTEEM"
 * >JCTEEM</a>. <div>link: [<a
 * href="http://jcst.ict.ac.cn:8080/jcst/EN/10.1007/s11390-012-1275-3"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_I2010CGSCWLSFTTSP" />Serban Iordache: <span
 * style="font-weight:bold">&ldquo;Consultant-Guided Search Combined with
 * Local Search for the Traveling Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the
 * Genetic and Evolutionary Computation Conference (GECCO'10)</span>,
 * July&nbsp;7&ndash;11, 2010, Portland, OR, USA: Portland Marriott
 * Downtown Waterfront Hotel, pages 2087&ndash;2088, New York, NY, USA: ACM
 * Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/978450300728">978-4503-0072-8</a>;
 * doi:&nbsp;<a href="http://dx.doi.org/10.1145/1830761.1830875">10.1145/
 * 1830761.1830875</a>; further information: [<a
 * href="http://www.sigevo.org/gecco-2010/">1</a>]</div></li>
 * <li><div><span id="cite_PI2011AHHAFSTGTSP" />Petrica C. Pop
 * and&nbsp;Serban Iordache: <span style="font-weight:bold">&ldquo;A Hybrid
 * Heuristic Approach for Solving the Generalized Traveling Salesman
 * Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the
 * Genetic and Evolutionary Computation Conference (GECCO'11)</span>,
 * July&nbsp;12&ndash;16, 2011, Dublin, Ireland, pages 481&ndash;488.
 * doi:&nbsp;<a href="http://dx.doi.org/10.1145/2001576.2001643">10.1145/
 * 2001576.2001643</a></div></li>
 * <li><div><span id="cite_CEG2010GDPIFTSTSP" /><a
 * href="http://www2.isye.gatech.edu/~wcook/">William John Cook</a>, Daniel
 * G. Espinoza, and&nbsp;Marcos Goycoolea: <span
 * style="font-weight:bold">&ldquo;Generalized Domino-Parity Inequalities
 * for the Symmetric Traveling Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Mathematics of Operations
 * Research (MOR)</span> 35(2):479&ndash;493, May&nbsp;2010; published by
 * Linthicum, ML, USA: Institute for Operations Research and the Management
 * Sciences (INFORMS). doi:&nbsp;<a
 * href="http://dx.doi.org/10.1287/moor.1100.0451"
 * >10.1287/moor.1100.0451</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/15265471">1526-5471</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/0364765X">0364-765X</a>.
 * <div>CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.118.6484"
 * >10.1.1.118.6484</a></div></div></li>
 * <li><div><span id="cite_CEG2005CWDPIFT" /><a
 * href="http://www2.isye.gatech.edu/~wcook/">William John Cook</a>, Daniel
 * G. Espinoza, and&nbsp;Marcos Goycoolea: <span
 * style="font-weight:bold">&ldquo;Computing with Domino-Parity
 * Inequalities for the TSP,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * 2005; published by Atlanta, GA, USA: Georgia Institute of Technology,
 * Industrial and Systems Engineering. <div>links: [<a
 * href="http://www2.isye.gatech.edu/~wcook/papers/DP_paper.pdf">1</a>],
 * [<a
 * href="http://www.isye.gatech.edu/~despinoz/PApers/DP_paper.pdf">2</a>],
 * and&nbsp;[<a
 * href="http://www.dii.uchile.cl/~daespino/PApers/DP_paper.pdf"
 * >3</a>]</div></div></li>
 * <li><div><span id="cite_CV2004OTHKRFTAASTSP" />Robert D. Carr
 * and&nbsp;<a href="http://www.cc.gatech.edu/~vempala/">Santosh S.
 * Vempala</a>: <span style="font-weight:bold">&ldquo;On the Held-Karp
 * Relaxation for the Asymmetric and Symmetric Traveling Salesman
 * Problems,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Mathematical
 * Programming</span> 100(3):569&ndash;587, July&nbsp;2004; published by
 * Berlin/Heidelberg: Springer-Verlag. LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/74618643">74618643</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/s10107-004-0506-y"
 * >10.1007/s10107-004-0506-y</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/1585989">1585989</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/00255610">0025-5610</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/14364646">1436-4646</a>. <div>link:
 * [<a href="http://www.cc.gatech.edu/~vempala/papers/sodatsp.ps">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.64.7595">
 * 10.1 .1.64.7595</a></div></div></li>
 * <li><div><span id="cite_W1990AOTHKHFTTSP" /><a
 * href="http://www.orie.cornell.edu/people/profile.cfm?netid=dw36">David
 * Paul Williamson</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Analysis of the
 * Held-Karp Heuristic for the Traveling Salesman Problem,&rdquo;</span>
 * Master's Thesis, May&nbsp;11, 1990, Cambridge, MA, USA: Massachusetts
 * Institute of Technology (MIT), Department of Electrical Engineering and
 * Computer Science. volume 479 of MIT/LCS/TR, Cambridge, MA, USA:
 * Massachusetts Institute of Technology (MIT). OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/22135051">22135051</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=kIqePgAACAAJ">kIqePgAACAAJ</a>.
 * <div>link: [<a
 * href="http://people.orie.cornell.edu/dpw/papers/masters.pdf">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href
 * ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.18.2">10.1.1
 * .18.2</a></div></div></li>
 * <li><div><span id="cite_B2009JPG" /><a
 * href="http://en.wikipedia.org/wiki/Kent_Beck">Kent Beck</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;JUnit Pocket
 * Guide,&rdquo;</span> 2009, Sebastopol, CA, USA: O'Reilly Media, Inc..
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1449379028">1449379028</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781449379025">9781449379025</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=Ur_zMK0WQwIC"
 * >Ur_zMK0WQwIC</a></div></li>
 * <li><div><span id="cite_RS2005JRPMFPT" /><a
 * href="http://en.wikipedia.org/wiki/J._B._Rainsberger">Joe B.
 * Rainsberger</a> and&nbsp;<a
 * href="http://www.linkedin.com/in/sstirling">Scott Stirling</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Junit Recipes:
 * Practical Methods for Programmer Testing,&rdquo;</span> 2005, Manning
 * Pubs Co, Greenwich, CT, USA: Manning Publications Co.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1932394230">1932394230</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781932394238">9781932394238</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=5h7oDjuY5WYC"
 * >5h7oDjuY5WYC</a></div></li>
 * <li><div><span id="cite_MH2004JIA" />Vincent Massol and&nbsp;<a
 * href="http://www.linkedin.com/in/husted">Ted Husted</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Junit In
 * Action,&rdquo;</span> 2004, Greenwich, CT, USA: Manning Publications
 * Co.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/8177225383">8177225383</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9788177225389">9788177225389</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=P1mDmZUmje0C"
 * >P1mDmZUmje0C</a></div></li>
 * <li><div><span id="cite_ECLIPSE" /><span
 * style="font-style:italic;font-family:cursive;"
 * >&ldquo;Eclipse,&rdquo;</span> (Software), Ottawa, ON, Canada: Eclipse
 * Foundation. <div>link: [<a
 * href="http://www.eclipse.org/">1</a>]</div></div></li>
 * <li><div><span id="cite_COCO2012CCCO" /><a
 * href="http://www.lri.fr/~hansen/">Nikolaus Hansen</a>, <a
 * href="http://www.lri.fr/~auger/">Anne Auger</a>, <a
 * href="https://tao.lri.fr/tiki-index.php?page=people">Raymond Ros</a>,
 * and&nbsp;Dimo Brockhoff: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;COCO (COmparing
 * Continuous Optimisers),&rdquo;</span> (Website), October&nbsp;17, 2012,
 * Orsay, France: Universit&#233; Paris Sud, Institut National de Recherche
 * en Informatique et en Automatique (INRIA) Futurs, &#201;quipe TAO.
 * <div>link: [<a
 * href="http://coco.gforge.inria.fr/doku.php?id=start">1</a>]</div></div></li>
 * <li><div><span id="cite_HAFR2012RPBBOBES" /><a
 * href="http://www.lri.fr/~hansen/">Nikolaus Hansen</a>, <a
 * href="http://www.lri.fr/~auger/">Anne Auger</a>, <a
 * href="http://www.researchgate.net/profile/Steffen_Finck/">Steffen
 * Finck</a>, and&nbsp;<a
 * href="https://tao.lri.fr/tiki-index.php?page=people">Raymond Ros</a>:
 * <span style="font-weight:bold">&ldquo;Real-Parameter Black-Box
 * Optimization Benchmarking: Experimental Setup,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * March&nbsp;24, 2012; published by Orsay, France: Universit&#233; Paris
 * Sud, Institut National de Recherche en Informatique et en Automatique
 * (INRIA) Futurs, &#201;quipe TAO. <div>link: [<a href=
 * "http://coco.lri.fr/BBOB-downloads/download11.05/bbobdocexperiment.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_AH2005PEOAALSEA" /><a
 * href="http://www.lri.fr/~auger/">Anne Auger</a> and&nbsp;<a
 * href="http://www.lri.fr/~hansen/">Nikolaus Hansen</a>: <span
 * style="font-weight:bold">&ldquo;Performance Evaluation of an Advanced
 * Local Search Evolutionary Algorithm,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the IEEE
 * Congress on Evolutionary Computation (CEC'05)</span>,
 * September&nbsp;2&ndash;5, 2005, Edinburgh, Scotland, UK, pages
 * 1777&ndash;1784, <a
 * href="http://www.macs.hw.ac.uk/staff-directory/david-wolfe-corne.htm"
 * >David Wolfe Corne</a>, <a
 * href="http://cs.adelaide.edu.au/~zbyszek/">Zbigniew Michalewicz</a>, <a
 * href="http://sc.snu.ac.kr/~rim/index.html">Robert Ian McKay</a>, <a
 * href="http://www.cs.vu.nl/~gusz/">&#193;goston E. Eiben</a> aka.
 * Gusz/Guszti, <a
 * href="http://www.natural-selection.com/people_dfogel.html">David B.
 * Fogel</a>, Carlos M. Fonseca, <a
 * href="https://www.ads.tuwien.ac.at/raidl/">G&#252;nther R. Raidl</a>, <a
 * href="http://vlab.ee.nus.edu.sg/~kctan/">Kay Chen Tan</a>, and&nbsp;Ali
 * M. S. Zalzala, editors, Piscataway, NJ, USA: IEEE Computer Society.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780393635">0-7803-9363-5</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780393639">978-0-7803
 * -9363-9</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CEC.2005.1554903"
 * >10.1109/CEC.2005.1554903</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/62773008">62773008</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=-QIVAAAACAAJ">-QIVAAAACAAJ</a>;
 * INSPEC Accession Number:&nbsp;8715465. <div>link: [<a
 * href="http://www.lri.fr/~hansen/cec2005localcmaes.pdf">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.71.6248">
 * 10.1 .1.71.6248</a></div></div></li>
 * <li><div><span id="cite_TH2004UAIAEEFSAFSAMS" /><a
 * href="https://cs.uwaterloo.ca/~dtompkin/">Dave Andrew Douglas
 * Tompkins</a> and&nbsp;<a href="http://www.cs.ubc.ca/~hoos/">Holger H.
 * Hoos</a>: <span style="font-weight:bold">&ldquo;UBCSAT: An
 * Implementation and Experimentation Environment for SLS Algorithms for
 * SAT and MAX-SAT,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Revised Selected Papers
 * from the Seventh International Conference on Theory and Applications of
 * Satisfiability Testing (SAT'04)</span>, May&nbsp;10&ndash;13, 2004,
 * Vancouver, BC, Canada, pages 306&ndash;320, <a
 * href="http://www.cs.ubc.ca/~hoos/">Holger H. Hoos</a> and&nbsp;<a
 * href="http://www.cs.sfu.ca/~mitchell/">David G. Mitchell</a>, editors,
 * volume 3542 of Lecture Notes in Computer Science (LNCS), Berlin,
 * Germany: Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540278290"
 * >978-3-540-27829-0</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/11527695_24">10.1007/11527695_24</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a>. <div>link:
 * [<a
 * href="http://ubcsat.dtompkins.com/downloads/sat04proc-ubcsat.pdf">1</
 * a>]</div></div></li>
 * <li><div><span id="cite_UBCSAT" /><a
 * href="https://cs.uwaterloo.ca/~dtompkin/">Dave Andrew Douglas
 * Tompkins</a> and&nbsp;<a href="http://www.cs.ubc.ca/~hoos/">Holger H.
 * Hoos</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;UBCSAT
 * ,&rdquo;</span> (Software), 2013, Vancouver, BC, Canada: University of
 * British Columbia. <div>link: [<a
 * href="http://ubcsat.dtompkins.com/">1</a>]</div></div></li>
 * <li><div><span id="cite_T2010DLSFSDIAA" /><a
 * href="https://cs.uwaterloo.ca/~dtompkin/">Dave Andrew Douglas
 * Tompkins</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Dynamic Local
 * Search for SAT: Design, Insights and Analysis,&rdquo;</span> PhD Thesis,
 * October&nbsp;2010, Vancouver, BC, Canada: University Of British
 * Columbia, Faculty of Graduate Studies, Computer Science. <div>link: [<a
 * href
 * ="http://www.cs.uwaterloo.ca/~dtompkin/papers/dadt-phd.pdf">1</a>]</div
 * ></div></li>
 * <li><div><span id="cite_WNT2010AOABAOAB" /><a
 * href="http://www.it-weise.de/">Thomas Weise</a> <span
 * style="color:gray">[&#27748;&#21355;&#24605;</span>], <a
 * href="mailto:newly@mail.ustc.edu.cn">Li Niu</a> <span
 * style="color:gray">[&#29275;&#21147;</span>], and&nbsp;<a
 * href="http://staff.ustc.edu.cn/~ketang/">Ke Tang</a> <span
 * style="color:gray">[&#21776;&#29634;</span>]: <span
 * style="font-weight:bold">&ldquo;AOAB &#8210; Automated Optimization
 * Algorithm Benchmarking,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Black Box Optimization
 * Benchmarking (BBOB'10); Companion Publication of the Genetic and
 * Evolutionary Computation Conference (GECCO'10 Companion)</span>,
 * July&nbsp;7, 2010, Portland, OR, USA: Portland Marriott Downtown
 * Waterfront Hotel, pages 1479&ndash;1486, New York, NY, USA: ACM Press.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781450300735">978-1-4503
 * -0073-5</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1145/1830761.1830763"
 * >10.1145/1830761.1830763</a>; SCI/WOS:&nbsp;WOS:000322071400001;
 * EI:&nbsp;20103513190754; further information: [<a
 * href="http://www.sigevo.org/gecco-2010/workshops.html#bbob">1</a>].
 * <div>links: [<a
 * href="http://www.it-weise.de/documents/files/WNT2010AOABAOAB.pdf">1</a>]
 * and&nbsp;[<a
 * href="http://home.ustc.edu.cn/~newly/newly.files/Academy/paper.pdf"
 * >2</a>]</div></div></li>
 * </ol>
 */
package org.logisticPlanning.tsp.benchmarking;

