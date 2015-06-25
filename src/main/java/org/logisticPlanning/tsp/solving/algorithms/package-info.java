/**
 *
 In this package, as port of TSP Suite, we provide a comprehensive set of
 * implemented algorithms for solving the Traveling Salesman Problem (TSP).
 * <h2 id="algorithmList">{@linkplain org.logisticPlanning.tsp.solving
 * Included Algorithm Implementations}</h2>
 * <p>
 * In package {@link org.logisticPlanning.tsp.solving.algorithms}, you can
 * find a variety of implementations of standard and experimental TSP
 * solvers that ship with TSP Suite. In this section, we will provide a
 * short list of these algorithms and links to their documentation. We
 * divide the algorithms into four classes:
 * </p>
 * <ol>
 * <li>
 {@link org.logisticPlanning.tsp.solving.algorithms.exact exact
 * methods}, that always return the correct result, if given enough
 * runtime,</li>
 * <li>
 {@link org.logisticPlanning.tsp.solving.algorithms.heuristics
 * heuristics}, which are quick to generate a so-so solution (but this
 * solution will be, well, so-so),</li>
 * <li>
 {@link org.logisticPlanning.tsp.solving.algorithms.localSearch local
 * search methods}&nbsp;[<a href="#cite_HS2005SLSFAA"
 * style="font-weight:bold">1</a>], which try to iteratively improve a
 * single solution, and</li>
 * <li>
 {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics
 * metaheuristics}, global search methods that can maintain a set or
 * population of solutions and trade in the speed of local searches for the
 * hope of finding better results in the long run. Technically, some local
 * search algorithms are also metaheuristics.</li>
 * </ol>
 * <p>
 * The following list of implemented algorithms is structured in exactly
 * these categories of algorithms, which in turn can be divided into more
 * sub-categories, regarding, e.g., the data structures used for
 * representing the solutions.
 * </p>
 * <h3 id="exactAlgorithmsList">
 * {@linkplain org.logisticPlanning.tsp.solving.algorithms.exact
 * Implemented Exact Methods}</h3>
 * <p>
 * Exact optimization methods guarantee to find the globally optimal
 * solution, if given enough time. The following members of this algorithm
 * family have been implemented:
 * </p>
 * <ol>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.exhaustiveEnumeration.ExhaustivePermutationIteration
 * Exhaustive Enumeration} tries to enumerate all possible permutations by
 * using <a href=
 * "http://en.wikipedia.org/wiki/Steinhaus-Johnson-Trotter_algorithm#Even.27s_speedup"
 * >Even's Speedup</a>&nbsp;[<a href="#cite_E1973AC"
 * style="font-weight:bold">2</a>] to a <a href=
 * "http://en.wikipedia.org/wiki/Steinhaus-Johnson-Trotter_algorithm"
 * >Steinhaus-Johnson-Trotter Algorithm</a>&nbsp;[<a
 * href="#cite_J1963GOPBAT" style="font-weight:bold">3</a>, <a
 * href="#cite_S1977PGM" style="font-weight:bold">4</a>].</li>
 * <li>In package
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963
 * branchAndBoundLittle1963}, we implement the
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963.BABLittle1963
 * Branch and Bound} algorithm for traveling salesman as proposed by Little
 * et al.&nbsp;[<a href="#cite_LMSK1963AAFTTSP"
 * style="font-weight:bold">5</a>].</li>
 * <li>We also provide an adaptation of a
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundHeldCarp.BAB_HK
 * Branch-and-Bound algorithm} using Held-Karp boundaries that we found in
 * the internet&nbsp;[<a href="#cite_CI2011OTA"
 * style="font-weight:bold">6</a>]&hellip;</li>
 * </ol>
 * <h3 id="heuristicAlgorithmsList">
 * {@linkplain org.logisticPlanning.tsp.solving.algorithms.heuristics
 * Implemented Heuristics}</h3>
 * <p>
 * Heuristics are usually simple procedures that build one solution in a
 * constructive or iterative manner. The following algorithms from this
 * family have been implemented:
 * </p>
 * <ol>
 * <li>The
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.nearestNeighbor.NearestNeighborHeuristic
 * Nearest-Neighbor Heuristic}&nbsp;[<a href="#cite_RSL1977AAOSHFTTSP"
 * style="font-weight:bold">7</a>, <a href="#cite_RSSL2009AAOSHFTTSP"
 * style="font-weight:bold">8</a>, <a href="#cite_JMB1997TTSPACSILO"
 * style="font-weight:bold">9</a>, <a href="#cite_JMG2004EAOHFTS"
 * style="font-weight:bold">10</a>] starts at a given node and builds a
 * tour by iteratively adding the nearest node to it.</li>
 * <li>The
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.doubleEndedNearestNeighbor.DoubleEndedNearestNeighborHeuristic
 * Double-Ended Nearest-Neighbor Heuristic}&nbsp;[<a
 * href="#cite_JMB1997TTSPACSILO" style="font-weight:bold">9</a>, <a
 * href="#cite_JMG2004EAOHFTS" style="font-weight:bold">10</a>] starts a
 * given node and then iteratively builds a tour by adding the nearest
 * nodes to it. Different from the nearest-neighbor heuristic, it checks
 * both ends of the current tour for nearest neighbors.</li>
 * <li>The
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.edgeGreedy.EdgeGreedyHeuristic
 * Edge-Greedy Heuristic}&nbsp;[<a href="#cite_JMB1997TTSPACSILO"
 * style="font-weight:bold">9</a>, <a href="#cite_JMG2004EAOHFTS"
 * style="font-weight:bold">10</a>] constructs a tour by adding the
 * cheapest edge (that does not violate any validity constraint).</li>
 * <li>The
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.mst.MSTHeuristic
 * (Double) Minimum Spanning Tree Heuristic}&nbsp;[<a
 * href="#cite_J1930OJPMZDPOB" style="font-weight:bold">11</a>, <a
 * href="#cite_P1957SCNASG" style="font-weight:bold">12</a>] first computes
 * a <a href="https://en.wikipedia.org/wiki/Minimum_spanning_tree">minimum
 * spanning tree</a> (MST)&nbsp;[<a href="#cite_J2004MSTSPT"
 * style="font-weight:bold">13</a>] and then traces it from the root note
 * to form a tour by skipping any already visited nodes.</li>
 * <li>The
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic
 * Savings Heuristic}&nbsp;[<a href="#cite_CW1964SOVFACDTANODP"
 * style="font-weight:bold">14</a>, <a href="#cite_JMB1997TTSPACSILO"
 * style="font-weight:bold">9</a>, <a href="#cite_JMG2004EAOHFTS"
 * style="font-weight:bold">10</a>] starts by choosing a depot. For each of
 * the remaining {@code n-1} nodes, a tour from the depot to the node and
 * directly back is assumed. Iteratively, the cheapest way to combine tours
 * is sought and applied, until only one tour &ndash; the solution to the
 * TSP &dash; remains.</li>
 * </ol>
 * <h3 id="localSearchAlgorithmsList">
 * {@linkplain org.logisticPlanning.tsp.solving.algorithms.localSearch
 * Implemented Local Search Methods}</h3>
 * <p>
 * <a href="http://en.wikipedia.org/wiki/Local_search_%28optimization%29">
 * Local search algorithms</a> are algorithms that maintain a single
 * solution and try to refine it by iteratively applying modifications.
 * Most of the local search methods that we consider belong to the class of
 * Stochastic Local Search (SLS)&nbsp;[<a href="#cite_HS2005SLSFAA"
 * style="font-weight:bold">1</a>] algorithms, i.e., they are randomized.
 * </p>
 * <h4 id="permutationLocalSearchAlgorithmsList">
 * {@linkplain org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation
 * Implemented Local Search Algorithms}</h4>
 * <p>
 * We implemented the following Local Search algorithms that work directly
 * on permutation-based representations:
 * </p>
 * <ol>
 * <li>A
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.random.RandomPermutationWalk
 * Random Walk}&nbsp;[<a href="#cite_P1905TPOTRW"
 * style="font-weight:bold">15</a>, <a href="#cite_F1968AITPTAIA"
 * style="font-weight:bold">16</a>, <a href="#cite_H1995RWARERW"
 * style="font-weight:bold">17</a>, <a href="#cite_WR2007RWTASA"
 * style="font-weight:bold">18</a>] starts with a random permutation and in
 * each iteration, it randomly modifies it without any consideration
 * whether the result is good or bad.</li>
 * <li>A
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.random.RandomPermutationSampling
 * Random Sampling} generates a completely new and entirely random
 * permutation in each iteration.</li>
 * <li>A
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.hc.UpdatingPermutationHillClimber
 * Hill Climbing} algorithm&nbsp;[<a href="#cite_RN2002AI"
 * style="font-weight:bold">19</a>, <a href="#cite_JCS2003HC"
 * style="font-weight:bold">20</a>, <a href="#cite_DHS2000HC"
 * style="font-weight:bold">21</a>] starts with a random solution and in
 * each step modifies it slightly. If the new solution is better, it is
 * kept. Otherwise, it is thrown away and we continue with old solution.</li>
 * <li>A
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.PermutationVNS
 * Variable Neighborhood Search} (VNS)&nbsp;[<a href="#cite_HM2001VNSPAA"
 * style="font-weight:bold">22</a>, <a href="#cite_HMP2008VNSMAA"
 * style="font-weight:bold">23</a>, <a href="#cite_HMP2010VNSMAA"
 * style="font-weight:bold">24</a>, <a href="#cite_HMBP2010VNS"
 * style="font-weight:bold">25</a>] is a local search method that performs
 * a search based on one search operator until no improvement can be
 * achieved anymore. At this point in time, it switches to another search
 * operation (i.e., a different neighborhood) and continues the search. We
 * provide also a modified version of this algorithm, called
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.PermutationRNS
 * Random Neighborhood Search} (RNS), that switches neighborhoods randomly
 * instead of following the VNS concept.</li>
 * <li>Our
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch.MultiNeighborhoodSearch
 * Multi-Neighborhood Search} (MNS) is a local search method that searches
 * in several different neighborhoods at once. This is possible since
 * testing the utility of several different search moves may require
 * similar data and independent search moves can be queued during one
 * flight over a permutation and then be processed one after the other.</li>
 * </ol>
 * <h4 id="satelliteListLocalSearchAlgorithmsList">
 * {@linkplain org.logisticPlanning.tsp.solving.algorithms.localSearch.satelliteList
 * Implemented Local Search Algorithms using the Satellite List
 * Representation}</h4>
 * <p>
 * The
 * {@link org.logisticPlanning.tsp.solving.utils.satelliteList.SatelliteList
 * Satellite list}&nbsp;[<a href="#cite_OR2006TSLANDSFTSP"
 * style="font-weight:bold">26</a>, <a href="#cite_ORG2005TSLARDLL"
 * style="font-weight:bold">27</a>] is a clever representations for
 * solutions to TSPs. It allows for basic operations such as segment
 * reversal to be conducted in <em>O(1)</em>. A Satellite List can be
 * translated
 * {@link org.logisticPlanning.tsp.solving.utils.satelliteList.SatelliteList#toPath(int[])
 * to} and
 * {@link org.logisticPlanning.tsp.solving.utils.satelliteList.SatelliteList#fromPath(int[])
 * from} the path or adjacency representation in <em>O(n)</em>. The
 * following algorithms use this data structure:
 * </p>
 * <ol>
 * <li>In package
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.satelliteList.LKW}
 * , we implement some <a
 * href="http://en.wikipedia.org/wiki/Lin%E2%80%93Kernighan_heuristic"
 * >Lin-Kerninghan algorithm</a>&nbsp;[<a href="#cite_LK1973AEHAFTTSP"
 * style="font-weight:bold">28</a>, <a href="#cite_JMG2004EAOHFTS"
 * style="font-weight:bold">10</a>] based on the
 * {@link org.logisticPlanning.tsp.solving.utils.satelliteList.SatelliteList
 * Satellite list} representation.</li>
 * </ol>
 * <h3 id="metaheuristicAlgorithmsList">
 * {@linkplain org.logisticPlanning.tsp.solving.algorithms.metaheuristics
 * Implemented Metaheuristics}</h3>
 * <p>
 * Metaheuristics are optimization general methods that iteratively improve
 * or refine solutions. They often work on sets (populations) of solutions.
 * We implemented the following algorithms from this family:
 * </p>
 * <h4 id="generalMetaheuristicsList">
 * {@linkplain org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general
 * General Metaheuristics}</h4>
 * <p>
 * General metaheuristics are algorithms that work on arbitrary
 * representations. We implemented the following approaches:
 * </p>
 * <ol>
 * <li>The
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.EA
 * EA} is an <a href="http://en.wikipedia.org/wiki/Evolutionary_algorithm">
 * evolutionary algorithm</a> (EA)&nbsp;[<a href="#cite_WGOEB"
 * style="font-weight:bold">29</a>, <a href="#cite_CWM2011VOEAFRWA"
 * style="font-weight:bold">30</a>, <a href="#cite_DJ2006ECAUA"
 * style="font-weight:bold">31</a>, <a href="#cite_BFM1997EA"
 * style="font-weight:bold">32</a>, <a href="#cite_SMY2002EO"
 * style="font-weight:bold">33</a>] working with a (&#956;+&#955;) or
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
 * style="font-weight:bold">34</a>, <a href="#cite_D1859EV2"
 * style="font-weight:bold">35</a>].</li>
 * </ol>
 * <h4 id="permutationMetaheuristicsList">
 * {@linkplain org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation
 * Implemented Permutation-based Metaheuristics}</h4>
 * <p>
 * Metaheuristics that work directly on permutation-based representations
 * form one of the main classes of algorithms that our framework provides.
 * We implemented the following approaches:
 * </p>
 * <ol>
 * <li>The
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea.PermutationEA
 * Permutation-based EA} is an <a
 * href="http://en.wikipedia.org/wiki/Evolutionary_algorithm">evolutionary
 * algorithm</a> (EA)&nbsp;[<a href="#cite_WGOEB"
 * style="font-weight:bold">29</a>, <a href="#cite_CWM2011VOEAFRWA"
 * style="font-weight:bold">30</a>, <a href="#cite_DJ2006ECAUA"
 * style="font-weight:bold">31</a>, <a href="#cite_BFM1997EA"
 * style="font-weight:bold">32</a>, <a href="#cite_SMY2002EO"
 * style="font-weight:bold">33</a>] based on our general
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.EA
 * EA implementation} that works on a set of permutations and iteratively
 * refines and combines them. It also exists in a
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea.HeuristicInitPermutationEA
 * variant} that is initialized with heuristic methods.</li>
 * <li>A family of heuristically-initialized
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.PermutationHeuristicInitMA
 * Memetic Algorithms} (MA)&nbsp;[<a href="#cite_M1989MA"
 * style="font-weight:bold">36</a>, <a href="#cite_M2002MA"
 * style="font-weight:bold">37</a>, <a href="#cite_MC2003AGITMA"
 * style="font-weight:bold">38</a>, <a href="#cite_ES2003HWOTMA"
 * style="font-weight:bold">39</a>, <a href="#cite_HKS2005RAIMA"
 * style="font-weight:bold">40</a>, <a href="#cite_DM2004MA"
 * style="font-weight:bold">41</a>, <a href="#cite_RS1994FMA"
 * style="font-weight:bold">42</a>] is given in package
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA}
 * . All of these algorithms are basically evolutionary algorithms that are
 * extended with a local search, be it
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.PermutationVNS
 * VNS}&nbsp;[<a href="#cite_HM2001VNSPAA" style="font-weight:bold">22</a>,
 * <a href="#cite_HMP2008VNSMAA" style="font-weight:bold">23</a>, <a
 * href="#cite_HMP2010VNSMAA" style="font-weight:bold">24</a>, <a
 * href="#cite_HMBP2010VNS" style="font-weight:bold">25</a>] or our new
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch.MultiNeighborhoodSearch
 * Multi-Neighborhood Search}. Some of these algorithms are combined with
 * special selection schemes such as
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.selection.FitnessUniformSelection
 * FUSS}&nbsp;[<a href="#cite_H2006FUO" style="font-weight:bold">43</a>, <a
 * href="#cite_LHK2004TVFUS" style="font-weight:bold">44</a>, <a
 * href="#cite_H2002FUSTPGD" style="font-weight:bold">45</a>, <a
 * href="#cite_LHK2004TVFUS2" style="font-weight:bold">46</a>] or fitness
 * assignment procedures like
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.fitness.FFA
 * FFA}&nbsp;[<a href="#cite_WWTWDY2014FFA"
 * style="font-weight:bold">47</a>].</li>
 * <li>A much simpler
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ma.PermutationMA
 * Memetic Algorithm}&nbsp;[<a href="#cite_M1989MA"
 * style="font-weight:bold">36</a>, <a href="#cite_M2002MA"
 * style="font-weight:bold">37</a>, <a href="#cite_MC2003AGITMA"
 * style="font-weight:bold">38</a>, <a href="#cite_ES2003HWOTMA"
 * style="font-weight:bold">39</a>, <a href="#cite_HKS2005RAIMA"
 * style="font-weight:bold">40</a>, <a href="#cite_DM2004MA"
 * style="font-weight:bold">41</a>, <a href="#cite_RS1994FMA"
 * style="font-weight:bold">42</a>] variant performs a hill-climbing like
 * local search for a number of steps which depends on the problem scale
 * for each new candidate solution resulting from a reproduction operation
 * of the underlying EA.</li>
 * <li>The
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco.PACO
 * Population-based Ant Colony Optimization} algorithm (pACOs)&nbsp;[<a
 * href="#cite_G2004AAISAMCE" style="font-weight:bold">48</a>, <a
 * href="#cite_GM2002APBAFA" style="font-weight:bold">49</a>, <a
 * href="#cite_GM2002APBATDOP" style="font-weight:bold">50</a>] simulates
 * the movement of ants through the cities of the TSP to construct
 * solutions. A set (population) of best (or newest) is maintained that
 * defines the pheromone trail and ants are more likely to follow paths
 * with strong pheromones. In package
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco}
 * you can find a set of different variants of this algorithm, e.g.,
 * heuristically initialized ones and combinations with different local
 * search methods.</li>
 * <li>The
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA.TemplateBasedEHBSA
 * template-based Edge Histogram Sampling Algorithm}&nbsp;[<a
 * href="#cite_T2002PMBGAIPRDUEH" style="font-weight:bold">51</a>, <a
 * href="#cite_T2009POAEAOAPWMCP" style="font-weight:bold">52</a>] is an
 * Estimation of Distribution Algorithm&nbsp;[<a
 * href="#cite_LL2001EODAANTFEC" style="font-weight:bold">53</a>, <a
 * href="#cite_LLIB2006TANEC" style="font-weight:bold">54</a>] that tries
 * to approximate the distribution of edges in the ideal solution by
 * building a histogram of them in good solutions and iterative sampling
 * and refining of this model. Several different variants of this
 * algorithm, also in combination with local search methods, are provided
 * in package
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA}
 * .</li>
 * </ol>
 * <h4 id="developmentalMetaheuristicsList">
 * {@linkplain org.logisticPlanning.tsp.solving.algorithms.metaheuristics.developmental
 * Developmental Metaheuristics}</h4>
 * <p>
 * Metaheuristics that use <em>indirect representations</em>&nbsp;[<a
 * href="#cite_DWT2011ASOSRFEOOGS" style="font-weight:bold">55</a>, <a
 * href="#cite_BK1999TWTGDACOEFAEDP" style="font-weight:bold">56</a>]:
 * Here, the genotypes (internal data structures processed by the search
 * operations) are significantly different from the phenotypes (the data
 * structures of the candidate solutions, in our case, tours) and there is
 * a non-trivial, iterative {@link org.logisticPlanning.tsp.solving.gpm
 * process} (called {@link org.logisticPlanning.tsp.solving.gpm
 * genotype-phenotype mapping}) translating from genotypes to phenotypes,
 * usually employing feedback from an environment. We implemented the
 * following approaches:
 * </p>
 * <ol>
 * <li>The
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.developmental.devUpdatingEA.DevUpdatingEA
 * Developmental Updating EA} defined in&nbsp;[<a
 * href="#cite_OWDC2013SADAFTSP" style="font-weight:bold">57</a>] uses
 * {@link org.logisticPlanning.tsp.solving.searchSpaces.trees.math.Function
 * mathematical formulas} (
 * {@link org.logisticPlanning.tsp.solving.searchSpaces.trees.Node trees})
 * as genotypes that tell the
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.developmental.devUpdatingEA.DevUpdatingGPM
 * GPM} which change to a given solution (tour) should be applied.</li>
 * </ol>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_HS2005SLSFAA" /><a
 * href="http://www.cs.ubc.ca/~hoos/">Holger H. Hoos</a> and&nbsp;<a
 * href="http://iridia.ulb.ac.be/~stuetzle/">Thomas St&#252;tzle</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Stochastic Local
 * Search: Foundations and Applications,&rdquo;</span> 2005, The Morgan
 * Kaufmann Series in Artificial Intelligence, San Francisco, CA, USA:
 * Morgan Kaufmann Publishers Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1558608729">1558608729</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781558608726">978-1558608726</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=3HAedXnC49IC">3HAedXnC49IC</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=DyzhHMOhI-8C">DyzhHMOhI-8C</a>;
 * ASIN:&nbsp;<a
 * href="http://www.amazon.com/dp/1558608729">1558608729</a></div></li>
 * <li><div><span id="cite_E1973AC" /><a
 * href="http://en.wikipedia.org/wiki/Shimon_Even">Shimon Even</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Algorithmic
 * Combinatorics,&rdquo;</span> 1973, New York, NY, USA: Macmillan
 * Publishers Co.. OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/589026">589026</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=AcE-AAAAIAAJ">AcE-AAAAIAAJ</a>;
 * ASIN:&nbsp;<a href="http://www.amazon.com/dp/B00393TCP4">B00393TCP4</a>
 * and&nbsp;<a
 * href="http://www.amazon.com/dp/B000NZSJ8M">B000NZSJ8M</a></div></li>
 * <li><div><span id="cite_J1963GOPBAT" /><a
 * href="http://en.wikipedia.org/wiki/Selmer_M._Johnson">Selmer Martin
 * Johnson</a>: <span style="font-weight:bold">&ldquo;Generation of
 * Permutations by Adjacent Transposition,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Mathematics of
 * Computation</span> 17(83):282&ndash;285, July&nbsp;1963; published by
 * Providence, RI, USA: American Mathematical Society (AMS). doi:&nbsp;<a
 * href
 * ="http://dx.doi.org/10.1090/S0025-5718-1963-0159764-2">10.1090/S0025-
 * 5718-1963-0159764-2</a>; JSTOR stable:&nbsp;<a
 * href="http://www.jstor.org/stable/2003846">2003846</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/00255718">0025-5718</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/10886842">1088-6842</a></div></li>
 * <li><div><span id="cite_S1977PGM" /><a
 * href="http://www.cs.princeton.edu/~rs/">Robert Sedgewick</a>: <span
 * style="font-weight:bold">&ldquo;Permutation Generation
 * Methods,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">ACM Computing Surveys
 * (CSUR)</span> 9(2):137&ndash;164, June&nbsp;1977; published by New York,
 * NY, USA: ACM Press. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1145/356689.356692"
 * >10.1145/356689.356692</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03600300">0360-0300</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=CMSVAN"
 * >CMSVAN</a></div></li>
 * <li><div><span id="cite_LMSK1963AAFTTSP" />John D. C. Little, <a
 * href="http://www-personal.umich.edu/~murty/">Katta G. Murty</a>, Dura W.
 * Sweeny, and&nbsp;Caroline Karel: <span
 * style="font-weight:bold">&ldquo;An Algorithm for the Traveling Salesman
 * Problem,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;07-63, March&nbsp;1, 1963; published by Cambridge, MA, USA:
 * Massachusetts Institute of Technology (MIT), Sloan School of Management.
 * <div>links: [<a href=
 * "http://dspace.mit.edu/bitstream/handle/1721.1/46828/algorithmfortrav00litt.pdf"
 * >1</a>], [<a href="http://hdl.handle.net/1721.1/46828">2</a>],
 * and&nbsp;[<a
 * href="https://github.com/karepker/little-tsp/blob/master/source.pdf"
 * >3</a>]</div></div></li>
 * <li><div><span id="cite_CI2011OTA" /><a
 * href="http://stackoverflow.com/users/908076/comestibles">comestibles</a>
 * and&nbsp;<a
 * href="http://stackoverflow.com/users/270287/ivlad">IVlad</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Optimized TSP
 * Algorithms,&rdquo;</span> (Website), August&nbsp;23, 2011, New York, NY,
 * USA: Stack Exchange Inc., stackoverflow. <div>link: [<a href=
 * "http://stackoverflow.com/questions/7159259/optimized-tsp-algorithms"
 * >1</a>]</div></div></li>
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
 * <li><div><span id="cite_P1905TPOTRW" />Karl Pearson: <span
 * style="font-weight:bold">&ldquo;The Problem of the Random
 * Walk,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Nature</span> 72:294,
 * July&nbsp;27, 1905. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1038/072294b0">10.1038/072294b0</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/00280836">0028-0836</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/14764687">1476-4687</a></div></li>
 * <li><div><span id="cite_F1968AITPTAIA" />William Feller: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;An Introduction to
 * Probability Theory and Its Applications, Volume 1,&rdquo;</span> 1968,
 * Wiley Series in Probability and Mathematical Statistics &#8210; Applied
 * Probability and Statistics Section Series, Chichester, West Sussex, UK:
 * Wiley Interscience. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0471257087">0471257087</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780471257080">978-0471257080</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=TkfeSAAACAAJ">TkfeSAAACAAJ</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=E9WLSAAACAAJ">E9WLSAAACAAJ
 * </a></div></li>
 * <li><div><span id="cite_H1995RWARERW" />Barry D. Hughes: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Random Walks and
 * Random Environments: Volume 1: Random Walks,&rdquo;</span> May&nbsp;16,
 * 1995, New York, NY, USA: Oxford University Press, Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0198537883">0-19-853788-3</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780198537885">978-0-19-853788
 * -5</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=QhOen_t0LeQC"
 * >QhOen_t0LeQC</a></div></li>
 * <li><div><span id="cite_WR2007RWTASA" /><a
 * href="mscl.cit.nih.gov/homepages/ghw/">George H. Weiss</a>
 * and&nbsp;Robert J. Rubin: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Random Walks:
 * Theory and Selected Applications,&rdquo;</span> March&nbsp;14, 2007, <a
 * href="http://en.wikipedia.org/wiki/Ilya_Prigogine">Ilya Romanovich
 * Prigogine</a> and&nbsp;<a
 * href="http://en.wikipedia.org/wiki/Stuart_A._Rice">Stuart Alan Rice</a>,
 * editors, volume 52 of Advances in Chemical Physics, Hoboken, NJ, USA:
 * John Wiley &amp; Sons, Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780471868453">9780471868453</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780470142769">9780470142769</a>;
 * doi:&nbsp;<a href="http://dx.doi.org/10.1002/9780470142769.ch5">10.1002/
 * 9780470142769.ch5</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/19344791">1934-4791</a></div></li>
 * <li><div><span id="cite_RN2002AI" />Stuart J. Russell and&nbsp;Peter
 * Norvig: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Artificial
 * Intelligence: A Modern Approach (AIMA),&rdquo;</span> December&nbsp;20,
 * 2002, Upper Saddle River, NJ, USA: Prentice Hall International Inc.
 * and&nbsp;Upper Saddle River, NJ, USA: Pearson Education. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0137903952">0-13-790395-2</a>, <a
 * href="https://www.worldcat.org/isbn/0130803022">0-13-080302-2</a>, <a
 * href="https://www.worldcat.org/isbn/8120323823">8120323823</a>, <a
 * href="https://www.worldcat.org/isbn/9780137903955"
 * >978-0-13-790395-5</a>, <a
 * href="https://www.worldcat.org/isbn/9780130803023"
 * >978-0-13-080302-3</a>, and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9788120323827">9788120323827</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=5mfMAQAACAAJ">5mfMAQAACAAJ</a>,
 * <a
 * href="http://books.google.com/books?id=DvqIIwAACAAJ">DvqIIwAACAAJ</a>,
 * <a
 * href="http://books.google.com/books?id=5WfMAQAACAAJ">5WfMAQAACAAJ</a>,
 * and&nbsp;<a
 * href="http://books.google.com/books?id=0GldGwAACAAJ">0GldGwAACAAJ
 * </a></div></li>
 * <li><div><span id="cite_JCS2003HC" />James C. Spall: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Introduction to
 * Stochastic Search and Optimization,&rdquo;</span> June&nbsp;2003,
 * Estimation, Simulation, and Control &#8211; Wiley-Interscience Series in
 * Discrete Mathematics and Optimization, Chichester, West Sussex, UK:
 * Wiley Interscience. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0471330523">0-471-33052-3</a>, <a
 * href="https://www.worldcat.org/isbn/0471722138">0-471-72213-8</a>, <a
 * href
 * ="https://www.worldcat.org/isbn/9780471330523">978-0-471-33052-3</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780471722137">978-0-471-
 * 72213-7</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/2002038049">2002038049</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/51235522">51235522</a>, <a
 * href="https://www.worldcat.org/oclc/637018778">637018778</a>, <a
 * href="https://www.worldcat.org/oclc/474647590">474647590</a>,
 * and&nbsp;<a href="https://www.worldcat.org/oclc/50773216">50773216</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=f66OIvvkKnAC"
 * >f66OIvvkKnAC</a></div></li>
 * <li><div><span id="cite_DHS2000HC" />Richard O. Duda, Peter Elliot Hart,
 * and&nbsp;David G. Stork: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Pattern
 * Classification,&rdquo;</span> November&nbsp;2000, Estimation,
 * Simulation, and Control &#8211; Wiley-Interscience Series in Discrete
 * Mathematics and Optimization, Chichester, West Sussex, UK: Wiley
 * Interscience. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0471056693">0-471-05669-3</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780471056690">978-0-471-
 * 05669-0</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/99029981">99029981</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/154744650">154744650</a>, <a
 * href="https://www.worldcat.org/oclc/474918353">474918353</a>,
 * and&nbsp;<a href="https://www.worldcat.org/oclc/41347061">41347061</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=YoxQAAAAMAAJ">YoxQAAAAMAAJ</a>,
 * <a
 * href="http://books.google.com/books?id=hyQgQAAACAAJ">hyQgQAAACAAJ</a>,
 * and&nbsp;<a
 * href="http://books.google.com/books?id=o3I8PgAACAAJ">o3I8PgAACAAJ</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=303957670">303957670</a></div></li>
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
 * href="http://dx.doi.org/10.1016/S0377-2217(00)00100-4"
 * >10.1016/S0377-2217(00)00100-4</a>; ISSN:&nbsp;<a
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
 * href="http://dx.doi.org/10.1007/s10288-008-0089-1"
 * >10.1007/s10288-008-0089-1</a>; ISSN:&nbsp;<a
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
 * href="http://dx.doi.org/10.1007/978-1-4419-1665-5_3"
 * >10.1007/978-1-4419-1665-5_3</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=xMTS5dyDhwMC">xMTS5dyDhwMC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/08848289">0884-8289</a>;
 * ASIN:&nbsp;<a
 * href="http://www.amazon.com/dp/1461426901">1461426901</a></div></li>
 * <li><div><span id="cite_OR2006TSLANDSFTSP" /><a
 * href="http://www.researchgate.net/profile/Colin_Osterman/">Colin
 * Osterman</a> and&nbsp;<a
 * href="http://faculty.bus.olemiss.edu/crego/">C&#233;sar Rego</a>: <span
 * style="font-weight:bold">&ldquo;The Satellite List and New Data
 * Structures for Traveling Salesman Problems,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;HCES-06-03, March&nbsp;2006; published by University, MS,
 * USA: University of Mississippi, School of Business Administration,
 * Hearin Center for Enterprise Science. <div>links: [<a href=
 * "http://www.akira.ruc.dk/~keld/teaching/algoritmedesign_f08/Artikler/02/Osterman03.pdf"
 * >1</a>] and&nbsp;[<a href=
 * "http://faculty.bus.olemiss.edu/crego/papers/HCES-06-03%20Paper%20Revised.pdf"
 * >2</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.135.4730"
 * >10.1.1.135.4730</a></div></div></li>
 * <li><div><span id="cite_ORG2005TSLARDLL" /><a
 * href="http://www.researchgate.net/profile/Colin_Osterman/">Colin
 * Osterman</a>, <a href="http://faculty.bus.olemiss.edu/crego/">C&#233;sar
 * Rego</a>, and&nbsp;<a href=
 * "http://www.researchgate.net/profile/Dorabela_Gamboa/publications/"
 * >Dorabela Gamboa</a>: <span style="font-weight:bold">&ldquo;The
 * Satellite List: A Reversible Doubly-Linked List,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the 7th
 * International Conference on Adaptive and Natural Computing Algorithms
 * (ICANNGA'05)</span>, March&nbsp;21&ndash;23, 2005, Coimbra, Portugal:
 * University of Coimbra, pages 542&ndash;545, <a
 * href="https://www.cisuc.uc.pt/people/show/2020">Bernardete Ribeiro</a>,
 * Rudolf F. Albrecht, <a
 * href="http://www.fri.uni-lj.si/en/andrej-dobnikar/">Andrej Dobnikar</a>,
 * David W. Pearson, and&nbsp;Nigel C. Steele, editors, Vienna, Austria:
 * Springer Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783211273890"
 * >978-3-211-27389-0</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/3-211-27389-1_131"
 * >10.1007/3-211-27389-1_131</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=FvMHsHo8DJcC">FvMHsHo8DJcC</a>;
 * further information: [<a href="http://icannga05.dei.uc.pt/">1</a>]</div>
 * </li>
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
 * <li><div><span id="cite_M1989MA" /><a href=
 * "http://www.newcastle.edu.au/staff/research-profile/Pablo_Moscato/"
 * >Pablo Moscato</a>: <span style="font-weight:bold">&ldquo;On Evolution,
 * Search, Optimization, Genetic Algorithms and Martial Arts: Towards
 * Memetic Algorithms,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;C3P 826, 1989; published by Pasadena, CA, USA: California
 * Institute of Technology (Caltech), Caltech Concurrent Computation
 * Program (C3P). <div>links: [<a
 * href="http://www.densis.fee.unicamp.br/~moscato/papers/bigone.ps">1</a>]
 * and&nbsp;[<a href=
 * "http://www.each.usp.br/sarajane/SubPaginas/arquivos_aulas_IA/memetic.pdf"
 * >2</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.27.9474"
 * >10.1.1.27.9474</a></div></div></li>
 * <li><div><span id="cite_M2002MA" /><a href=
 * "http://www.newcastle.edu.au/staff/research-profile/Pablo_Moscato/"
 * >Pablo Moscato</a>: <span style="font-weight:bold">&ldquo;Memetic
 * Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Handbook of Applied
 * Optimization</span>, chapter 157&ndash;167, pages 157&ndash;167, Panos
 * M. Pardalos and&nbsp;Mauricio G.C. Resende, editors, New York, NY, USA:
 * Oxford University Press, Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0195125940">0-19-512594-0</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780195125948">978-0-19-512594
 * -8</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/45532495">45532495</a></div></li>
 * <li><div><span id="cite_MC2003AGITMA" /><a href=
 * "http://www.newcastle.edu.au/staff/research-profile/Pablo_Moscato/"
 * >Pablo Moscato</a> and&nbsp;<a
 * href="http://www.lcc.uma.es/~ccottap/">Carlos Cotta</a>: <span
 * style="font-weight:bold">&ldquo;A Gentle Introduction to Memetic
 * Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Handbook of
 * Metaheuristics</span>, chapter 105&ndash;144, pages 105&ndash;144, <a
 * href="http://en.wikipedia.org/wiki/Fred_W._Glover">Fred W. Glover</a>
 * and&nbsp;<a href=
 * "http://www.ucdenver.edu/academics/colleges/business/Faculty-Research/FacultyDirectory/Pages/Gary-Kochenberger.aspx"
 * >Gary A. Kochenberger</a>, editors, volume 57 of International Series in
 * Operations Research &amp; Management Science, Norwell, MA, USA: Kluwer
 * Academic Publishers, Dordrecht, Netherlands: Springer Netherlands,
 * and&nbsp;Boston, MA, USA: Springer US. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1402072635">1-4020-7263-5</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780306480560">978-0-306-
 * 48056-0</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/0-306-48056-5_5"
 * >10.1007/0-306-48056-5_5</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=O_10T_KeqOgC">O_10T_KeqOgC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/08848289">0884-8289</a>. <div>link:
 * [<a
 * href="http://www.lcc.uma.es/~ccottap/papers/handbook03memetic.pdf">1<
 * /a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.77.5300"
 * >10.1.1.77.5300</a></div></div></li>
 * <li><div><span id="cite_ES2003HWOTMA" /><a
 * href="http://www.cs.vu.nl/~gusz/">&#193;goston E. Eiben</a> aka.
 * Gusz/Guszti and&nbsp;<a href="http://www.cems.uwe.ac.uk/~jsmith/">James
 * E. Smith</a>: <span style="font-weight:bold">&ldquo;Hybridisation with
 * other Techniques: Memetic Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Introduction to
 * Evolutionary Computing</span>, chapter 173&ndash;188, pages
 * 173&ndash;188, Natural Computing Series, New York, NY, USA: Springer New
 * York. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540401849">3540401849</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540401841">978-3540401841</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=7IOE5VIpFpwC">7IOE5VIpFpwC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/16197127">1619-7127</a></div></li>
 * <li><div><span id="cite_HKS2005RAIMA" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Recent Advances in
 * Memetic Algorithms,&rdquo;</span> 2005, <a
 * href="http://www.cs.sandia.gov/~wehart/Main/Home.html">William Eugene
 * Hart</a>, <a href="http://www.cs.nott.ac.uk/~nxk/">Natalio
 * Krasnogor</a>, and&nbsp;<a
 * href="http://www.cems.uwe.ac.uk/~jsmith/">James E. Smith</a>, editors,
 * volume 166/2005 of Studies in Fuzziness and Soft Computing, Berlin,
 * Germany: Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540229043">3-540-22904-3</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540229049">978-3-540-
 * 22904-9</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/2004111139">2004111139</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/3-540-32363-5"
 * >10.1007/3-540-32363-5</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/318297267">318297267</a> and&nbsp;<a
 * href="https://www.worldcat.org/oclc/56697114">56697114</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=LYf7YW4DmkUC">LYf7YW4DmkUC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/14349922">1434-9922</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/18600808">1860-0808</a></div></li>
 * <li><div><span id="cite_DM2004MA" />Jason Digalakis
 * and&nbsp;Konstantinos Margaritis: <span
 * style="font-weight:bold">&ldquo;Performance Comparison of Memetic
 * Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Journal of Applied
 * Mathematics and Computation</span> 158:237&ndash;252, October&nbsp;2004;
 * published by Essex, UK: Elsevier Science Publishers B.V.. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1016/j.amc.2003.08.115">10.1016/j.amc.2003.08
 * .115</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/00963003">0096-3003</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=AMHCBQ"
 * >AMHCBQ</a>. <div>links: [<a
 * href="http://citeseer.ist.psu.edu/458892.html">1</a>], [<a href=
 * "http://www.complexity.org.au/ci/draft/draft/digala02/digala02s.pdf"
 * >2</a>], and&nbsp;[<a
 * href="http://www.complexity.org.au/ci/vol10/digala02/digala02s.pdf"
 * >3</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.21.5495"
 * >10.1.1.21.5495</a></div></div></li>
 * <li><div><span id="cite_RS1994FMA" /><a
 * href="http://users.breathe.com/njr/">Nicholas J. Radcliffe</a>
 * and&nbsp;<a href="http://www.linkedin.com/in/patricksurry">Patrick David
 * Surry</a>: <span style="font-weight:bold">&ldquo;Formal Memetic
 * Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the
 * Workshop on Artificial Intelligence and Simulation of Behaviour,
 * International Workshop on Evolutionary Computing, Selected Papers
 * (AISB'94)</span>, April&nbsp;11&ndash;13, 1994, Leeds, UK, pages
 * 1&ndash;16, Terence Claus Fogarty, editor, volume 865/1994 of Lecture
 * Notes in Computer Science (LNCS), Berlin, Germany: Springer-Verlag GmbH
 * and&nbsp;Chichester, West Sussex, UK: Society for the Study of
 * Artificial Intelligence and the Simulation of Behaviour (SSAISB).
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540584838">3-540-58483-8</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540584834">978-3-540-
 * 58483-4</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/3-540-58483-8_1"
 * >10.1007/3-540-58483-8_1</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=59512478X">59512478X</a>.
 * <div>CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.38.9885"
 * >10.1.1.38.9885</a></div></div></li>
 * <li><div><span id="cite_H2006FUO" /><a
 * href="http://www.hutter1.net/">Marcus Hutter</a> and&nbsp;<a
 * href="http://www.vetta.org/">Shane Legg</a>: <span
 * style="font-weight:bold">&ldquo;Fitness Uniform
 * Optimization,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">IEEE Transactions on
 * Evolutionary Computation (IEEE-EC)</span> 10(5):568&ndash;589,
 * October&nbsp;2006; published by Washington, DC, USA: IEEE Computer
 * Society. LCCN:&nbsp;<a href="http://lccn.loc.gov/97648327">97648327</a>;
 * doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/TEVC.2005.863127">10.1109/TEVC
 * .2005.863127</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/1089778X">1089-778X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/19410026">1941-0026</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=ITEVF5"
 * >ITEVF5</a>; INSPEC Accession Number:&nbsp;9101740; further information:
 * [<a href="http://www.ieee-cis.org/pubs/tec/">1</a>]. <div>links: [<a
 * href
 * ="http://www.vetta.org/documents/FitnessUniformOptimization.pdf">1</a>]
 * and&nbsp;[<a
 * href="http://www.idsia.ch/idsiareport/IDSIA-16-06.pdf">2</a>];
 * arxiv:&nbsp;<a
 * href="http://arxiv.org/abs/cs/0610126v1">cs/0610126v1</a>;
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.137.2999">
 * 10.1.1.137.2999</a></div></div></li>
 * <li><div><span id="cite_LHK2004TVFUS" /><a
 * href="http://www.vetta.org/">Shane Legg</a>, <a
 * href="http://www.hutter1.net/">Marcus Hutter</a>, and&nbsp;Akshat Kumar:
 * <span style="font-weight:bold">&ldquo;Tournament versus Fitness Uniform
 * Selection,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;IDSIA-04-04, March&nbsp;4, 2004; published by Manno-Lugano,
 * Switzerland: Dalle Molle Institute for Artificial Intelligence (IDSIA),
 * University of Lugano, Faculty of Informatics / University of Applied
 * Sciences of Southern Switzerland (SUPSI), Department of Innovative
 * Technologies <span style="color:gray">[Istituto Dalle Molle di Studi
 * sull'Intelligenza Artificiale</span>]. <div>link: [<a
 * href="http://www.idsia.ch/idsiareport/IDSIA-04-04.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_H2002FUSTPGD" /><a
 * href="http://www.hutter1.net/">Marcus Hutter</a>: <span
 * style="font-weight:bold">&ldquo;Fitness Uniform Selection to Preserve
 * Genetic Diversity,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the IEEE
 * Congress on Evolutionary Computation (CEC'02); 2002 IEEE World Congress
 * on Computation Intelligence (WCCI'02)</span>, 1-2, May&nbsp;12&ndash;17,
 * 2002, Honolulu, HI, USA: Hilton Hawaiian Village Hotel (Beach Resort
 * &amp; Spa), pages 783&ndash;788, <a
 * href="http://www.natural-selection.com/people_dfogel.html">David B.
 * Fogel</a>, Mohamed A. El-Sharkawi, <a
 * href="http://www.cs.bham.ac.uk/~xin/">Xin Yao</a> <span
 * style="color:gray">[&#23002;&#26032;</span>], Hitoshi Iba, Paul Marrow,
 * and&nbsp;Mark Shackleton, editors, Los Alamitos, CA, USA: IEEE Computer
 * Society Press and&nbsp;Piscataway, NJ, USA: IEEE Computer Society.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780372824">0-7803-7282-4</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780372825">978-0-7803
 * -7282-5</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CEC.2002.1007025"
 * >10.1109/CEC.2002.1007025</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/181357364">181357364</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=-ptVAAAAMAAJ">-ptVAAAAMAAJ</a>;
 * INSPEC Accession Number:&nbsp;7336007. <div>links: [<a
 * href="http://www.hutter1.net/ai/pfuss.pdf">1</a>], [<a
 * href="http://www.hutter1.net/ai/pfuss.ps">2</a>], [<a
 * href="http://www.hutter1.net/ai/pfuss.tar">3</a>], and&nbsp;[<a
 * href="ftp://ftp.idsia.ch/pub/techrep/IDSIA-01-01.ps.gz">4</a>];
 * arxiv:&nbsp;<a href="http://arxiv.org/abs/cs/0103015">cs/0103015</a>;
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.106.9784">
 * 10.1.1.106.9784</a></div></div></li>
 * <li><div><span id="cite_LHK2004TVFUS2" /><a
 * href="http://www.vetta.org/">Shane Legg</a>, <a
 * href="http://www.hutter1.net/">Marcus Hutter</a>, and&nbsp;Akshat Kumar:
 * <span style="font-weight:bold">&ldquo;Tournament versus Fitness Uniform
 * Selection,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the IEEE
 * Congress on Evolutionary Computation (CEC'04)</span>,
 * June&nbsp;20&ndash;23, 2004, Portland, OR, USA, pages 2144&ndash;2151,
 * Los Alamitos, CA, USA: IEEE Computer Society Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780385152">0-7803-8515-2</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780385153">978-0-7803
 * -8515-3</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CEC.2004.1331162"
 * >10.1109/CEC.2004.1331162</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=I8_5AAAACAAJ">I8_5AAAACAAJ</a>;
 * INSPEC Accession Number:&nbsp;8229180. <div>links: [<a
 * href="http://www.hutter1.net/ai/fussexp.pdf">1</a>], [<a
 * href="http://www.hutter1.net/ai/fussexp.ps">2</a>], and&nbsp;[<a
 * href="http://www.hutter1.net/ai/fussexp.zip">3</a>]; source code: [<a
 * href="http://www.hutter1.net/ai/fussdd.cpp">1</a>], [<a
 * href="http://www.hutter1.net/ai/fussdd.h">2</a>], [<a
 * href="http://www.hutter1.net/ai/fusstsp.cpp">3</a>], and&nbsp;[<a
 * href="http://www.hutter1.net/ai/fusstsp.h">4</a>]; arxiv:&nbsp;<a
 * href="http://arxiv.org/abs/cs/0403038v1">cs/0403038v1</a>;
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.71.2663">
 * 10.1 .1.71.2663</a></div></div></li>
 * <li><div><span id="cite_WWTWDY2014FFA" /><a
 * href="http://www.it-weise.de/">Thomas Weise</a> <span
 * style="color:gray">[&#27748;&#21355;&#24605;</span>], <a
 * href="mailto:mingxu@mail.ustc.edu.cn">Mingxu Wan</a> <span
 * style="color:gray">[&#19975;&#26126;&#32490;</span>], <a
 * href="http://staff.ustc.edu.cn/~ketang/">Ke Tang</a> <span
 * style="color:gray">[&#21776;&#29634;</span>], <a
 * href="http://mail.ustc.edu.cn/~wuyou308/">Pu Wang</a> <span
 * style="color:gray">[&#29579;&#29854;</span>], <a
 * href="http://www.marmakoide.org/">Alexandre Devert</a>, and&nbsp;<a
 * href="http://www.cs.bham.ac.uk/~xin/">Xin Yao</a> <span
 * style="color:gray">[&#23002;&#26032;</span>]: <span
 * style="font-weight:bold">&ldquo;Frequency Fitness
 * Assignment,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">IEEE Transactions on
 * Evolutionary Computation (IEEE-EC)</span> 18(2):226&ndash;243,
 * April&nbsp;2014; published by Washington, DC, USA: IEEE Computer
 * Society. LCCN:&nbsp;<a href="http://lccn.loc.gov/97648327">97648327</a>;
 * doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/TEVC.2013.2251885">10.1109/TEVC
 * .2013.2251885</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/1089778X">1089-778X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/19410026">1941-0026</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=ITEVF5"
 * >ITEVF5</a>; INSPEC Accession Number:&nbsp;14196623;
 * SCI/WOS:&nbsp;WOS:000334166600006; EI:&nbsp;20141517565962; further
 * information: [<a href="http://www.ieee-cis.org/pubs/tec/">1</a>].
 * <div>links: [<a
 * href="http://www.it-weise.de/documents/files/WWTWDY2014FFA.pdf">1</a>]
 * and&nbsp;[<a
 * href="http://www.cs.bham.ac.uk/~xin/papers/tevc2013FFA.pdf">
 * 2</a>]</div></div></li>
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
 * <li><div><span id="cite_GM2002APBAFA" /><a
 * href="http://www.aifb.kit.edu/web/Michael_Guntsch/en">Michael
 * Guntsch</a> and&nbsp;<a href=
 * "http://pacosy.informatik.uni-leipzig.de/15-0-Prof+Dr+Martin+Middendorf.html"
 * >Martin Middendorf</a>: <span style="font-weight:bold">&ldquo;A
 * Population Based Approach for ACO,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Applications of
 * Evolutionary Computing, Proceedings of EvoWorkshops 2002: EvoCOP,
 * EvoIASP, EvoSTIM/EvoPLAN (EvoWorkshops'02)</span>, April&nbsp;2&ndash;4,
 * 2002, Kinsale, Ireland, pages 72&ndash;81, <a
 * href="http://www.ce.unipr.it/people/cagnoni/">Stefano Cagnoni</a>, Jens
 * Gottlieb, <a
 * href="http://www.soc.napier.ac.uk/~emmah/Prof_Emma_Hart/Welcome.html"
 * >Emma Hart</a>, <a href=
 * "http://pacosy.informatik.uni-leipzig.de/15-0-Prof+Dr+Martin+Middendorf.html"
 * >Martin Middendorf</a>, and&nbsp;<a
 * href="https://www.ads.tuwien.ac.at/raidl/">G&#252;nther R. Raidl</a>,
 * editors, volume 2279 of Lecture Notes in Computer Science (LNCS),
 * Berlin, Germany: Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540434321">3-540-43432-1</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540434320">978-3-540-
 * 43432-0</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/3-540-46004-7_8"
 * >10.1007/3-540-46004-7_8</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a>.
 * <div>CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.13.2514"
 * >10.1.1.13.2514</a></div></div></li>
 * <li><div><span id="cite_GM2002APBATDOP" /><a
 * href="http://www.aifb.kit.edu/web/Michael_Guntsch/en">Michael
 * Guntsch</a> and&nbsp;<a href=
 * "http://pacosy.informatik.uni-leipzig.de/15-0-Prof+Dr+Martin+Middendorf.html"
 * >Martin Middendorf</a>: <span style="font-weight:bold">&ldquo;Applying
 * Population Based ACO to Dynamic Optimization Problems,&rdquo;</span> in
 * <span style="font-style:italic;font-family:cursive;">From Ant Colonies
 * to Artificial Ants &#8210; Proceedings of the Third International
 * Workshop on Ant Colony Optimization (ANTS'02)</span>,
 * September&nbsp;12&ndash;14, 2002, Brussels, Belgium, pages
 * 111&ndash;122, <a
 * href="https://en.wikipedia.org/wiki/Marco_Dorigo">Marco Dorigo</a>, <a
 * href="http://www.idsia.ch/~gianni/">Gianni A. Di Caro</a>,
 * and&nbsp;Michael Samples, editors, volume 2463/2002 of Lecture Notes in
 * Computer Science (LNCS), Berlin, Germany: Springer-Verlag GmbH.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540441468">3-540-44146-8</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540441465">978-3-540-
 * 44146-5</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/3-540-45724-0_10"
 * >10.1007/3-540-45724-0_10</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a>.
 * <div>CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.12.6580"
 * >10.1.1.12.6580</a></div></div></li>
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
 * <li><div><span id="cite_LL2001EODAANTFEC" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Estimation of
 * Distribution Algorithms &#8210; A New Tool for Evolutionary
 * Computation,&rdquo;</span> 2001, <a href=
 * "http://cig.fi.upm.es/index.php?option=com_content&amp;view=article&amp;id=78&amp;Itemid=111"
 * >Pedro Larra&#241;aga</a> and&nbsp;<a
 * href="http://www.sc.ehu.es/ccwbayes/members/jalozano/home/index.html"
 * >Jos&#233; Antonio Lozano</a>, editors, volume 2 of Genetic Algorithms
 * and Evolutionary Computation, Boston, MA, USA: Springer US
 * and&nbsp;Norwell, MA, USA: Kluwer Academic Publishers. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0792374665">0-7923-7466-5</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780792374664">978-0-7923
 * -7466-4</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/47996547">47996547</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=o0llxS4u93wC">o0llxS4u93wC</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=kyU74gxp1rsC">kyU74gxp1rsC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/19320167">1932-0167</a></div></li>
 * <li><div><span id="cite_LLIB2006TANEC" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Towards a New
 * Evolutionary Computation &#8210; Advances on Estimation of Distribution
 * Algorithms,&rdquo;</span> 2006, <a
 * href="http://www.sc.ehu.es/ccwbayes/members/jalozano/home/index.html"
 * >Jos&#233; Antonio Lozano</a>, <a href=
 * "http://cig.fi.upm.es/index.php?option=com_content&amp;view=article&amp;id=78&amp;Itemid=111"
 * >Pedro Larra&#241;aga</a>, <a
 * href="http://www.sc.ehu.es/ccwbayes/members/inaki.htm">I&#241;aki
 * Inza</a>, and&nbsp;<a href="http://www.sc.ehu.es/acwbecae/">Endika
 * Bengoetxea</a>, editors, volume 192/2006 of Studies in Fuzziness and
 * Soft Computing, Berlin, Germany: Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540290060">3-540-29006-0</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540290063">978-3-540-
 * 29006-3</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/2005932568">2005932568</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/11007937">10.1007/11007937</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/63763942">63763942</a>, <a
 * href="https://www.worldcat.org/oclc/318299594">318299594</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/oclc/181473672">181473672</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=0dku9OKxl6AC">0dku9OKxl6AC</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=r0UrGB8y2V0C">r0UrGB8y2V0C</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/14349922">1434-9922</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/18600808">1860-0808</a></div></li>
 * <li><div><span id="cite_DWT2011ASOSRFEOOGS" /><a
 * href="http://www.marmakoide.org/">Alexandre Devert</a>, <a
 * href="http://www.it-weise.de/">Thomas Weise</a> <span
 * style="color:gray">[&#27748;&#21355;&#24605;</span>], and&nbsp;<a
 * href="http://staff.ustc.edu.cn/~ketang/">Ke Tang</a> <span
 * style="color:gray">[&#21776;&#29634;</span>]: <span
 * style="font-weight:bold">&ldquo;A Study on Scalable Representations for
 * Evolutionary Optimization of Ground Structures,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Evolutionary
 * Computation</span> 20(3):453&ndash;472, Fall&nbsp;2012; published by
 * Cambridge, MA, USA: MIT Press. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1162/EVCO_a_00054">10.1162/EVCO_a_00054</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/44169764">44169764</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/10636560">1063-6560</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15309304">1530-9304</a>;
 * PubMed:&nbsp;<a
 * href="https://www.ncbi.nlm.nih.gov/pubmed/22004002">22004002</a>;
 * SCI/WOS:&nbsp;WOS:000306767200005. <div>links: [<a href=
 * "http://www.marmakoide.org/download/publications/devweita-ecj-preprint.pdf"
 * >1</a>] and&nbsp;[<a
 * href="http://www.it-weise.de/documents/files/DWT2011ASOSRFEOOGS.pdf"
 * >2</a>]</div></div></li>
 * <li><div><span id="cite_BK1999TWTGDACOEFAEDP" /><a
 * href="http://peterjbentley.com/">Peter John Bentley</a> and&nbsp;<a
 * href="http://iianalytics.com/iia-faculty/sanjeev-kumar-2/">Sanjeev P.
 * Kumar</a>: <span style="font-weight:bold">&ldquo;The Ways to Grow
 * Designs: A Comparison of Embryogenies for an Evolutionary Design
 * Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the
 * Genetic and Evolutionary Computation Conference (GECCO'99)</span>,
 * July&nbsp;13&ndash;17, 1999, Orlando, FL, USA, pages 35&ndash;43, <a
 * href="http://www.cs.mun.ca/~banzhaf/">Wolfgang Banzhaf</a>, Jason M.
 * Daida, <a href="http://www.cs.vu.nl/~gusz/">&#193;goston E. Eiben</a>
 * aka. Gusz/Guszti, Max H. Garzon, Vasant Honavar, Mark J. Jakiela,
 * and&nbsp;Robert Elliott Smith, editors, San Francisco, CA, USA: Morgan
 * Kaufmann Publishers Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1558606114">1-55860-611-4</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781558606111">978-1-55860
 * -611-1</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/59333111">59333111</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=-1vTAAAACAAJ">-1vTAAAACAAJ</a>.
 * <div>link: [<a
 * href="http://www.cs.ucl.ac.uk/staff/ucacpjb/BEKUC1.pdf">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.51.1345">
 * 10.1 .1.51.1345</a></div></div></li>
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
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_HS2005SLSFAA" /><a
 * href="http://www.cs.ubc.ca/~hoos/">Holger H. Hoos</a> and&nbsp;<a
 * href="http://iridia.ulb.ac.be/~stuetzle/">Thomas St&#252;tzle</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Stochastic Local
 * Search: Foundations and Applications,&rdquo;</span> 2005, The Morgan
 * Kaufmann Series in Artificial Intelligence, San Francisco, CA, USA:
 * Morgan Kaufmann Publishers Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1558608729">1558608729</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781558608726">978-1558608726</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=3HAedXnC49IC">3HAedXnC49IC</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=DyzhHMOhI-8C">DyzhHMOhI-8C</a>;
 * ASIN:&nbsp;<a
 * href="http://www.amazon.com/dp/1558608729">1558608729</a></div></li>
 * <li><div><span id="cite_E1973AC" /><a
 * href="http://en.wikipedia.org/wiki/Shimon_Even">Shimon Even</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Algorithmic
 * Combinatorics,&rdquo;</span> 1973, New York, NY, USA: Macmillan
 * Publishers Co.. OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/589026">589026</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=AcE-AAAAIAAJ">AcE-AAAAIAAJ</a>;
 * ASIN:&nbsp;<a href="http://www.amazon.com/dp/B00393TCP4">B00393TCP4</a>
 * and&nbsp;<a
 * href="http://www.amazon.com/dp/B000NZSJ8M">B000NZSJ8M</a></div></li>
 * <li><div><span id="cite_J1963GOPBAT" /><a
 * href="http://en.wikipedia.org/wiki/Selmer_M._Johnson">Selmer Martin
 * Johnson</a>: <span style="font-weight:bold">&ldquo;Generation of
 * Permutations by Adjacent Transposition,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Mathematics of
 * Computation</span> 17(83):282&ndash;285, July&nbsp;1963; published by
 * Providence, RI, USA: American Mathematical Society (AMS). doi:&nbsp;<a
 * href
 * ="http://dx.doi.org/10.1090/S0025-5718-1963-0159764-2">10.1090/S0025-
 * 5718-1963-0159764-2</a>; JSTOR stable:&nbsp;<a
 * href="http://www.jstor.org/stable/2003846">2003846</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/00255718">0025-5718</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/10886842">1088-6842</a></div></li>
 * <li><div><span id="cite_S1977PGM" /><a
 * href="http://www.cs.princeton.edu/~rs/">Robert Sedgewick</a>: <span
 * style="font-weight:bold">&ldquo;Permutation Generation
 * Methods,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">ACM Computing Surveys
 * (CSUR)</span> 9(2):137&ndash;164, June&nbsp;1977; published by New York,
 * NY, USA: ACM Press. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1145/356689.356692"
 * >10.1145/356689.356692</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03600300">0360-0300</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=CMSVAN"
 * >CMSVAN</a></div></li>
 * <li><div><span id="cite_LMSK1963AAFTTSP" />John D. C. Little, <a
 * href="http://www-personal.umich.edu/~murty/">Katta G. Murty</a>, Dura W.
 * Sweeny, and&nbsp;Caroline Karel: <span
 * style="font-weight:bold">&ldquo;An Algorithm for the Traveling Salesman
 * Problem,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;07-63, March&nbsp;1, 1963; published by Cambridge, MA, USA:
 * Massachusetts Institute of Technology (MIT), Sloan School of Management.
 * <div>links: [<a href=
 * "http://dspace.mit.edu/bitstream/handle/1721.1/46828/algorithmfortrav00litt.pdf"
 * >1</a>], [<a href="http://hdl.handle.net/1721.1/46828">2</a>],
 * and&nbsp;[<a
 * href="https://github.com/karepker/little-tsp/blob/master/source.pdf"
 * >3</a>]</div></div></li>
 * <li><div><span id="cite_CI2011OTA" /><a
 * href="http://stackoverflow.com/users/908076/comestibles">comestibles</a>
 * and&nbsp;<a
 * href="http://stackoverflow.com/users/270287/ivlad">IVlad</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Optimized TSP
 * Algorithms,&rdquo;</span> (Website), August&nbsp;23, 2011, New York, NY,
 * USA: Stack Exchange Inc., stackoverflow. <div>link: [<a href=
 * "http://stackoverflow.com/questions/7159259/optimized-tsp-algorithms"
 * >1</a>]</div></div></li>
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
 * <li><div><span id="cite_P1905TPOTRW" />Karl Pearson: <span
 * style="font-weight:bold">&ldquo;The Problem of the Random
 * Walk,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Nature</span> 72:294,
 * July&nbsp;27, 1905. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1038/072294b0">10.1038/072294b0</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/00280836">0028-0836</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/14764687">1476-4687</a></div></li>
 * <li><div><span id="cite_F1968AITPTAIA" />William Feller: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;An Introduction to
 * Probability Theory and Its Applications, Volume 1,&rdquo;</span> 1968,
 * Wiley Series in Probability and Mathematical Statistics &#8210; Applied
 * Probability and Statistics Section Series, Chichester, West Sussex, UK:
 * Wiley Interscience. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0471257087">0471257087</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780471257080">978-0471257080</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=TkfeSAAACAAJ">TkfeSAAACAAJ</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=E9WLSAAACAAJ">E9WLSAAACAAJ
 * </a></div></li>
 * <li><div><span id="cite_H1995RWARERW" />Barry D. Hughes: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Random Walks and
 * Random Environments: Volume 1: Random Walks,&rdquo;</span> May&nbsp;16,
 * 1995, New York, NY, USA: Oxford University Press, Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0198537883">0-19-853788-3</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780198537885">978-0-19-853788
 * -5</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=QhOen_t0LeQC"
 * >QhOen_t0LeQC</a></div></li>
 * <li><div><span id="cite_WR2007RWTASA" /><a
 * href="mscl.cit.nih.gov/homepages/ghw/">George H. Weiss</a>
 * and&nbsp;Robert J. Rubin: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Random Walks:
 * Theory and Selected Applications,&rdquo;</span> March&nbsp;14, 2007, <a
 * href="http://en.wikipedia.org/wiki/Ilya_Prigogine">Ilya Romanovich
 * Prigogine</a> and&nbsp;<a
 * href="http://en.wikipedia.org/wiki/Stuart_A._Rice">Stuart Alan Rice</a>,
 * editors, volume 52 of Advances in Chemical Physics, Hoboken, NJ, USA:
 * John Wiley &amp; Sons, Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780471868453">9780471868453</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780470142769">9780470142769</a>;
 * doi:&nbsp;<a href="http://dx.doi.org/10.1002/9780470142769.ch5">10.1002/
 * 9780470142769.ch5</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/19344791">1934-4791</a></div></li>
 * <li><div><span id="cite_RN2002AI" />Stuart J. Russell and&nbsp;Peter
 * Norvig: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Artificial
 * Intelligence: A Modern Approach (AIMA),&rdquo;</span> December&nbsp;20,
 * 2002, Upper Saddle River, NJ, USA: Prentice Hall International Inc.
 * and&nbsp;Upper Saddle River, NJ, USA: Pearson Education. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0137903952">0-13-790395-2</a>, <a
 * href="https://www.worldcat.org/isbn/0130803022">0-13-080302-2</a>, <a
 * href="https://www.worldcat.org/isbn/8120323823">8120323823</a>, <a
 * href="https://www.worldcat.org/isbn/9780137903955"
 * >978-0-13-790395-5</a>, <a
 * href="https://www.worldcat.org/isbn/9780130803023"
 * >978-0-13-080302-3</a>, and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9788120323827">9788120323827</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=5mfMAQAACAAJ">5mfMAQAACAAJ</a>,
 * <a
 * href="http://books.google.com/books?id=DvqIIwAACAAJ">DvqIIwAACAAJ</a>,
 * <a
 * href="http://books.google.com/books?id=5WfMAQAACAAJ">5WfMAQAACAAJ</a>,
 * and&nbsp;<a
 * href="http://books.google.com/books?id=0GldGwAACAAJ">0GldGwAACAAJ
 * </a></div></li>
 * <li><div><span id="cite_JCS2003HC" />James C. Spall: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Introduction to
 * Stochastic Search and Optimization,&rdquo;</span> June&nbsp;2003,
 * Estimation, Simulation, and Control &#8211; Wiley-Interscience Series in
 * Discrete Mathematics and Optimization, Chichester, West Sussex, UK:
 * Wiley Interscience. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0471330523">0-471-33052-3</a>, <a
 * href="https://www.worldcat.org/isbn/0471722138">0-471-72213-8</a>, <a
 * href
 * ="https://www.worldcat.org/isbn/9780471330523">978-0-471-33052-3</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780471722137">978-0-471-
 * 72213-7</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/2002038049">2002038049</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/51235522">51235522</a>, <a
 * href="https://www.worldcat.org/oclc/637018778">637018778</a>, <a
 * href="https://www.worldcat.org/oclc/474647590">474647590</a>,
 * and&nbsp;<a href="https://www.worldcat.org/oclc/50773216">50773216</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=f66OIvvkKnAC"
 * >f66OIvvkKnAC</a></div></li>
 * <li><div><span id="cite_DHS2000HC" />Richard O. Duda, Peter Elliot Hart,
 * and&nbsp;David G. Stork: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Pattern
 * Classification,&rdquo;</span> November&nbsp;2000, Estimation,
 * Simulation, and Control &#8211; Wiley-Interscience Series in Discrete
 * Mathematics and Optimization, Chichester, West Sussex, UK: Wiley
 * Interscience. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0471056693">0-471-05669-3</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780471056690">978-0-471-
 * 05669-0</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/99029981">99029981</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/154744650">154744650</a>, <a
 * href="https://www.worldcat.org/oclc/474918353">474918353</a>,
 * and&nbsp;<a href="https://www.worldcat.org/oclc/41347061">41347061</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=YoxQAAAAMAAJ">YoxQAAAAMAAJ</a>,
 * <a
 * href="http://books.google.com/books?id=hyQgQAAACAAJ">hyQgQAAACAAJ</a>,
 * and&nbsp;<a
 * href="http://books.google.com/books?id=o3I8PgAACAAJ">o3I8PgAACAAJ</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=303957670">303957670</a></div></li>
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
 * href="http://dx.doi.org/10.1016/S0377-2217(00)00100-4"
 * >10.1016/S0377-2217(00)00100-4</a>; ISSN:&nbsp;<a
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
 * href="http://dx.doi.org/10.1007/s10288-008-0089-1"
 * >10.1007/s10288-008-0089-1</a>; ISSN:&nbsp;<a
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
 * href="http://dx.doi.org/10.1007/978-1-4419-1665-5_3"
 * >10.1007/978-1-4419-1665-5_3</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=xMTS5dyDhwMC">xMTS5dyDhwMC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/08848289">0884-8289</a>;
 * ASIN:&nbsp;<a
 * href="http://www.amazon.com/dp/1461426901">1461426901</a></div></li>
 * <li><div><span id="cite_OR2006TSLANDSFTSP" /><a
 * href="http://www.researchgate.net/profile/Colin_Osterman/">Colin
 * Osterman</a> and&nbsp;<a
 * href="http://faculty.bus.olemiss.edu/crego/">C&#233;sar Rego</a>: <span
 * style="font-weight:bold">&ldquo;The Satellite List and New Data
 * Structures for Traveling Salesman Problems,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;HCES-06-03, March&nbsp;2006; published by University, MS,
 * USA: University of Mississippi, School of Business Administration,
 * Hearin Center for Enterprise Science. <div>links: [<a href=
 * "http://www.akira.ruc.dk/~keld/teaching/algoritmedesign_f08/Artikler/02/Osterman03.pdf"
 * >1</a>] and&nbsp;[<a href=
 * "http://faculty.bus.olemiss.edu/crego/papers/HCES-06-03%20Paper%20Revised.pdf"
 * >2</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.135.4730"
 * >10.1.1.135.4730</a></div></div></li>
 * <li><div><span id="cite_ORG2005TSLARDLL" /><a
 * href="http://www.researchgate.net/profile/Colin_Osterman/">Colin
 * Osterman</a>, <a href="http://faculty.bus.olemiss.edu/crego/">C&#233;sar
 * Rego</a>, and&nbsp;<a href=
 * "http://www.researchgate.net/profile/Dorabela_Gamboa/publications/"
 * >Dorabela Gamboa</a>: <span style="font-weight:bold">&ldquo;The
 * Satellite List: A Reversible Doubly-Linked List,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the 7th
 * International Conference on Adaptive and Natural Computing Algorithms
 * (ICANNGA'05)</span>, March&nbsp;21&ndash;23, 2005, Coimbra, Portugal:
 * University of Coimbra, pages 542&ndash;545, <a
 * href="https://www.cisuc.uc.pt/people/show/2020">Bernardete Ribeiro</a>,
 * Rudolf F. Albrecht, <a
 * href="http://www.fri.uni-lj.si/en/andrej-dobnikar/">Andrej Dobnikar</a>,
 * David W. Pearson, and&nbsp;Nigel C. Steele, editors, Vienna, Austria:
 * Springer Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783211273890"
 * >978-3-211-27389-0</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/3-211-27389-1_131"
 * >10.1007/3-211-27389-1_131</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=FvMHsHo8DJcC">FvMHsHo8DJcC</a>;
 * further information: [<a href="http://icannga05.dei.uc.pt/">1</a>]</div>
 * </li>
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
 * <li><div><span id="cite_M1989MA" /><a href=
 * "http://www.newcastle.edu.au/staff/research-profile/Pablo_Moscato/"
 * >Pablo Moscato</a>: <span style="font-weight:bold">&ldquo;On Evolution,
 * Search, Optimization, Genetic Algorithms and Martial Arts: Towards
 * Memetic Algorithms,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;C3P 826, 1989; published by Pasadena, CA, USA: California
 * Institute of Technology (Caltech), Caltech Concurrent Computation
 * Program (C3P). <div>links: [<a
 * href="http://www.densis.fee.unicamp.br/~moscato/papers/bigone.ps">1</a>]
 * and&nbsp;[<a href=
 * "http://www.each.usp.br/sarajane/SubPaginas/arquivos_aulas_IA/memetic.pdf"
 * >2</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.27.9474"
 * >10.1.1.27.9474</a></div></div></li>
 * <li><div><span id="cite_M2002MA" /><a href=
 * "http://www.newcastle.edu.au/staff/research-profile/Pablo_Moscato/"
 * >Pablo Moscato</a>: <span style="font-weight:bold">&ldquo;Memetic
 * Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Handbook of Applied
 * Optimization</span>, chapter 157&ndash;167, pages 157&ndash;167, Panos
 * M. Pardalos and&nbsp;Mauricio G.C. Resende, editors, New York, NY, USA:
 * Oxford University Press, Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0195125940">0-19-512594-0</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780195125948">978-0-19-512594
 * -8</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/45532495">45532495</a></div></li>
 * <li><div><span id="cite_MC2003AGITMA" /><a href=
 * "http://www.newcastle.edu.au/staff/research-profile/Pablo_Moscato/"
 * >Pablo Moscato</a> and&nbsp;<a
 * href="http://www.lcc.uma.es/~ccottap/">Carlos Cotta</a>: <span
 * style="font-weight:bold">&ldquo;A Gentle Introduction to Memetic
 * Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Handbook of
 * Metaheuristics</span>, chapter 105&ndash;144, pages 105&ndash;144, <a
 * href="http://en.wikipedia.org/wiki/Fred_W._Glover">Fred W. Glover</a>
 * and&nbsp;<a href=
 * "http://www.ucdenver.edu/academics/colleges/business/Faculty-Research/FacultyDirectory/Pages/Gary-Kochenberger.aspx"
 * >Gary A. Kochenberger</a>, editors, volume 57 of International Series in
 * Operations Research &amp; Management Science, Norwell, MA, USA: Kluwer
 * Academic Publishers, Dordrecht, Netherlands: Springer Netherlands,
 * and&nbsp;Boston, MA, USA: Springer US. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1402072635">1-4020-7263-5</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780306480560">978-0-306-
 * 48056-0</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/0-306-48056-5_5"
 * >10.1007/0-306-48056-5_5</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=O_10T_KeqOgC">O_10T_KeqOgC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/08848289">0884-8289</a>. <div>link:
 * [<a
 * href="http://www.lcc.uma.es/~ccottap/papers/handbook03memetic.pdf">1<
 * /a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.77.5300"
 * >10.1.1.77.5300</a></div></div></li>
 * <li><div><span id="cite_ES2003HWOTMA" /><a
 * href="http://www.cs.vu.nl/~gusz/">&#193;goston E. Eiben</a> aka.
 * Gusz/Guszti and&nbsp;<a href="http://www.cems.uwe.ac.uk/~jsmith/">James
 * E. Smith</a>: <span style="font-weight:bold">&ldquo;Hybridisation with
 * other Techniques: Memetic Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Introduction to
 * Evolutionary Computing</span>, chapter 173&ndash;188, pages
 * 173&ndash;188, Natural Computing Series, New York, NY, USA: Springer New
 * York. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540401849">3540401849</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540401841">978-3540401841</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=7IOE5VIpFpwC">7IOE5VIpFpwC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/16197127">1619-7127</a></div></li>
 * <li><div><span id="cite_HKS2005RAIMA" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Recent Advances in
 * Memetic Algorithms,&rdquo;</span> 2005, <a
 * href="http://www.cs.sandia.gov/~wehart/Main/Home.html">William Eugene
 * Hart</a>, <a href="http://www.cs.nott.ac.uk/~nxk/">Natalio
 * Krasnogor</a>, and&nbsp;<a
 * href="http://www.cems.uwe.ac.uk/~jsmith/">James E. Smith</a>, editors,
 * volume 166/2005 of Studies in Fuzziness and Soft Computing, Berlin,
 * Germany: Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540229043">3-540-22904-3</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540229049">978-3-540-
 * 22904-9</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/2004111139">2004111139</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/3-540-32363-5"
 * >10.1007/3-540-32363-5</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/318297267">318297267</a> and&nbsp;<a
 * href="https://www.worldcat.org/oclc/56697114">56697114</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=LYf7YW4DmkUC">LYf7YW4DmkUC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/14349922">1434-9922</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/18600808">1860-0808</a></div></li>
 * <li><div><span id="cite_DM2004MA" />Jason Digalakis
 * and&nbsp;Konstantinos Margaritis: <span
 * style="font-weight:bold">&ldquo;Performance Comparison of Memetic
 * Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Journal of Applied
 * Mathematics and Computation</span> 158:237&ndash;252, October&nbsp;2004;
 * published by Essex, UK: Elsevier Science Publishers B.V.. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1016/j.amc.2003.08.115">10.1016/j.amc.2003.08
 * .115</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/00963003">0096-3003</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=AMHCBQ"
 * >AMHCBQ</a>. <div>links: [<a
 * href="http://citeseer.ist.psu.edu/458892.html">1</a>], [<a href=
 * "http://www.complexity.org.au/ci/draft/draft/digala02/digala02s.pdf"
 * >2</a>], and&nbsp;[<a
 * href="http://www.complexity.org.au/ci/vol10/digala02/digala02s.pdf"
 * >3</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.21.5495"
 * >10.1.1.21.5495</a></div></div></li>
 * <li><div><span id="cite_RS1994FMA" /><a
 * href="http://users.breathe.com/njr/">Nicholas J. Radcliffe</a>
 * and&nbsp;<a href="http://www.linkedin.com/in/patricksurry">Patrick David
 * Surry</a>: <span style="font-weight:bold">&ldquo;Formal Memetic
 * Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the
 * Workshop on Artificial Intelligence and Simulation of Behaviour,
 * International Workshop on Evolutionary Computing, Selected Papers
 * (AISB'94)</span>, April&nbsp;11&ndash;13, 1994, Leeds, UK, pages
 * 1&ndash;16, Terence Claus Fogarty, editor, volume 865/1994 of Lecture
 * Notes in Computer Science (LNCS), Berlin, Germany: Springer-Verlag GmbH
 * and&nbsp;Chichester, West Sussex, UK: Society for the Study of
 * Artificial Intelligence and the Simulation of Behaviour (SSAISB).
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540584838">3-540-58483-8</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540584834">978-3-540-
 * 58483-4</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/3-540-58483-8_1"
 * >10.1007/3-540-58483-8_1</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=59512478X">59512478X</a>.
 * <div>CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.38.9885"
 * >10.1.1.38.9885</a></div></div></li>
 * <li><div><span id="cite_H2006FUO" /><a
 * href="http://www.hutter1.net/">Marcus Hutter</a> and&nbsp;<a
 * href="http://www.vetta.org/">Shane Legg</a>: <span
 * style="font-weight:bold">&ldquo;Fitness Uniform
 * Optimization,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">IEEE Transactions on
 * Evolutionary Computation (IEEE-EC)</span> 10(5):568&ndash;589,
 * October&nbsp;2006; published by Washington, DC, USA: IEEE Computer
 * Society. LCCN:&nbsp;<a href="http://lccn.loc.gov/97648327">97648327</a>;
 * doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/TEVC.2005.863127">10.1109/TEVC
 * .2005.863127</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/1089778X">1089-778X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/19410026">1941-0026</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=ITEVF5"
 * >ITEVF5</a>; INSPEC Accession Number:&nbsp;9101740; further information:
 * [<a href="http://www.ieee-cis.org/pubs/tec/">1</a>]. <div>links: [<a
 * href
 * ="http://www.vetta.org/documents/FitnessUniformOptimization.pdf">1</a>]
 * and&nbsp;[<a
 * href="http://www.idsia.ch/idsiareport/IDSIA-16-06.pdf">2</a>];
 * arxiv:&nbsp;<a
 * href="http://arxiv.org/abs/cs/0610126v1">cs/0610126v1</a>;
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.137.2999">
 * 10.1.1.137.2999</a></div></div></li>
 * <li><div><span id="cite_LHK2004TVFUS" /><a
 * href="http://www.vetta.org/">Shane Legg</a>, <a
 * href="http://www.hutter1.net/">Marcus Hutter</a>, and&nbsp;Akshat Kumar:
 * <span style="font-weight:bold">&ldquo;Tournament versus Fitness Uniform
 * Selection,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;IDSIA-04-04, March&nbsp;4, 2004; published by Manno-Lugano,
 * Switzerland: Dalle Molle Institute for Artificial Intelligence (IDSIA),
 * University of Lugano, Faculty of Informatics / University of Applied
 * Sciences of Southern Switzerland (SUPSI), Department of Innovative
 * Technologies <span style="color:gray">[Istituto Dalle Molle di Studi
 * sull'Intelligenza Artificiale</span>]. <div>link: [<a
 * href="http://www.idsia.ch/idsiareport/IDSIA-04-04.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_H2002FUSTPGD" /><a
 * href="http://www.hutter1.net/">Marcus Hutter</a>: <span
 * style="font-weight:bold">&ldquo;Fitness Uniform Selection to Preserve
 * Genetic Diversity,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the IEEE
 * Congress on Evolutionary Computation (CEC'02); 2002 IEEE World Congress
 * on Computation Intelligence (WCCI'02)</span>, 1-2, May&nbsp;12&ndash;17,
 * 2002, Honolulu, HI, USA: Hilton Hawaiian Village Hotel (Beach Resort
 * &amp; Spa), pages 783&ndash;788, <a
 * href="http://www.natural-selection.com/people_dfogel.html">David B.
 * Fogel</a>, Mohamed A. El-Sharkawi, <a
 * href="http://www.cs.bham.ac.uk/~xin/">Xin Yao</a> <span
 * style="color:gray">[&#23002;&#26032;</span>], Hitoshi Iba, Paul Marrow,
 * and&nbsp;Mark Shackleton, editors, Los Alamitos, CA, USA: IEEE Computer
 * Society Press and&nbsp;Piscataway, NJ, USA: IEEE Computer Society.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780372824">0-7803-7282-4</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780372825">978-0-7803
 * -7282-5</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CEC.2002.1007025"
 * >10.1109/CEC.2002.1007025</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/181357364">181357364</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=-ptVAAAAMAAJ">-ptVAAAAMAAJ</a>;
 * INSPEC Accession Number:&nbsp;7336007. <div>links: [<a
 * href="http://www.hutter1.net/ai/pfuss.pdf">1</a>], [<a
 * href="http://www.hutter1.net/ai/pfuss.ps">2</a>], [<a
 * href="http://www.hutter1.net/ai/pfuss.tar">3</a>], and&nbsp;[<a
 * href="ftp://ftp.idsia.ch/pub/techrep/IDSIA-01-01.ps.gz">4</a>];
 * arxiv:&nbsp;<a href="http://arxiv.org/abs/cs/0103015">cs/0103015</a>;
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.106.9784">
 * 10.1.1.106.9784</a></div></div></li>
 * <li><div><span id="cite_LHK2004TVFUS2" /><a
 * href="http://www.vetta.org/">Shane Legg</a>, <a
 * href="http://www.hutter1.net/">Marcus Hutter</a>, and&nbsp;Akshat Kumar:
 * <span style="font-weight:bold">&ldquo;Tournament versus Fitness Uniform
 * Selection,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the IEEE
 * Congress on Evolutionary Computation (CEC'04)</span>,
 * June&nbsp;20&ndash;23, 2004, Portland, OR, USA, pages 2144&ndash;2151,
 * Los Alamitos, CA, USA: IEEE Computer Society Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780385152">0-7803-8515-2</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780385153">978-0-7803
 * -8515-3</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CEC.2004.1331162"
 * >10.1109/CEC.2004.1331162</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=I8_5AAAACAAJ">I8_5AAAACAAJ</a>;
 * INSPEC Accession Number:&nbsp;8229180. <div>links: [<a
 * href="http://www.hutter1.net/ai/fussexp.pdf">1</a>], [<a
 * href="http://www.hutter1.net/ai/fussexp.ps">2</a>], and&nbsp;[<a
 * href="http://www.hutter1.net/ai/fussexp.zip">3</a>]; source code: [<a
 * href="http://www.hutter1.net/ai/fussdd.cpp">1</a>], [<a
 * href="http://www.hutter1.net/ai/fussdd.h">2</a>], [<a
 * href="http://www.hutter1.net/ai/fusstsp.cpp">3</a>], and&nbsp;[<a
 * href="http://www.hutter1.net/ai/fusstsp.h">4</a>]; arxiv:&nbsp;<a
 * href="http://arxiv.org/abs/cs/0403038v1">cs/0403038v1</a>;
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.71.2663">
 * 10.1 .1.71.2663</a></div></div></li>
 * <li><div><span id="cite_WWTWDY2014FFA" /><a
 * href="http://www.it-weise.de/">Thomas Weise</a> <span
 * style="color:gray">[&#27748;&#21355;&#24605;</span>], <a
 * href="mailto:mingxu@mail.ustc.edu.cn">Mingxu Wan</a> <span
 * style="color:gray">[&#19975;&#26126;&#32490;</span>], <a
 * href="http://staff.ustc.edu.cn/~ketang/">Ke Tang</a> <span
 * style="color:gray">[&#21776;&#29634;</span>], <a
 * href="http://mail.ustc.edu.cn/~wuyou308/">Pu Wang</a> <span
 * style="color:gray">[&#29579;&#29854;</span>], <a
 * href="http://www.marmakoide.org/">Alexandre Devert</a>, and&nbsp;<a
 * href="http://www.cs.bham.ac.uk/~xin/">Xin Yao</a> <span
 * style="color:gray">[&#23002;&#26032;</span>]: <span
 * style="font-weight:bold">&ldquo;Frequency Fitness
 * Assignment,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">IEEE Transactions on
 * Evolutionary Computation (IEEE-EC)</span> 18(2):226&ndash;243,
 * April&nbsp;2014; published by Washington, DC, USA: IEEE Computer
 * Society. LCCN:&nbsp;<a href="http://lccn.loc.gov/97648327">97648327</a>;
 * doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/TEVC.2013.2251885">10.1109/TEVC
 * .2013.2251885</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/1089778X">1089-778X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/19410026">1941-0026</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=ITEVF5"
 * >ITEVF5</a>; INSPEC Accession Number:&nbsp;14196623;
 * SCI/WOS:&nbsp;WOS:000334166600006; EI:&nbsp;20141517565962; further
 * information: [<a href="http://www.ieee-cis.org/pubs/tec/">1</a>].
 * <div>links: [<a
 * href="http://www.it-weise.de/documents/files/WWTWDY2014FFA.pdf">1</a>]
 * and&nbsp;[<a
 * href="http://www.cs.bham.ac.uk/~xin/papers/tevc2013FFA.pdf">
 * 2</a>]</div></div></li>
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
 * <li><div><span id="cite_GM2002APBAFA" /><a
 * href="http://www.aifb.kit.edu/web/Michael_Guntsch/en">Michael
 * Guntsch</a> and&nbsp;<a href=
 * "http://pacosy.informatik.uni-leipzig.de/15-0-Prof+Dr+Martin+Middendorf.html"
 * >Martin Middendorf</a>: <span style="font-weight:bold">&ldquo;A
 * Population Based Approach for ACO,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Applications of
 * Evolutionary Computing, Proceedings of EvoWorkshops 2002: EvoCOP,
 * EvoIASP, EvoSTIM/EvoPLAN (EvoWorkshops'02)</span>, April&nbsp;2&ndash;4,
 * 2002, Kinsale, Ireland, pages 72&ndash;81, <a
 * href="http://www.ce.unipr.it/people/cagnoni/">Stefano Cagnoni</a>, Jens
 * Gottlieb, <a
 * href="http://www.soc.napier.ac.uk/~emmah/Prof_Emma_Hart/Welcome.html"
 * >Emma Hart</a>, <a href=
 * "http://pacosy.informatik.uni-leipzig.de/15-0-Prof+Dr+Martin+Middendorf.html"
 * >Martin Middendorf</a>, and&nbsp;<a
 * href="https://www.ads.tuwien.ac.at/raidl/">G&#252;nther R. Raidl</a>,
 * editors, volume 2279 of Lecture Notes in Computer Science (LNCS),
 * Berlin, Germany: Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540434321">3-540-43432-1</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540434320">978-3-540-
 * 43432-0</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/3-540-46004-7_8"
 * >10.1007/3-540-46004-7_8</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a>.
 * <div>CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.13.2514"
 * >10.1.1.13.2514</a></div></div></li>
 * <li><div><span id="cite_GM2002APBATDOP" /><a
 * href="http://www.aifb.kit.edu/web/Michael_Guntsch/en">Michael
 * Guntsch</a> and&nbsp;<a href=
 * "http://pacosy.informatik.uni-leipzig.de/15-0-Prof+Dr+Martin+Middendorf.html"
 * >Martin Middendorf</a>: <span style="font-weight:bold">&ldquo;Applying
 * Population Based ACO to Dynamic Optimization Problems,&rdquo;</span> in
 * <span style="font-style:italic;font-family:cursive;">From Ant Colonies
 * to Artificial Ants &#8210; Proceedings of the Third International
 * Workshop on Ant Colony Optimization (ANTS'02)</span>,
 * September&nbsp;12&ndash;14, 2002, Brussels, Belgium, pages
 * 111&ndash;122, <a
 * href="https://en.wikipedia.org/wiki/Marco_Dorigo">Marco Dorigo</a>, <a
 * href="http://www.idsia.ch/~gianni/">Gianni A. Di Caro</a>,
 * and&nbsp;Michael Samples, editors, volume 2463/2002 of Lecture Notes in
 * Computer Science (LNCS), Berlin, Germany: Springer-Verlag GmbH.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540441468">3-540-44146-8</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540441465">978-3-540-
 * 44146-5</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/3-540-45724-0_10"
 * >10.1007/3-540-45724-0_10</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a>.
 * <div>CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.12.6580"
 * >10.1.1.12.6580</a></div></div></li>
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
 * <li><div><span id="cite_LL2001EODAANTFEC" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Estimation of
 * Distribution Algorithms &#8210; A New Tool for Evolutionary
 * Computation,&rdquo;</span> 2001, <a href=
 * "http://cig.fi.upm.es/index.php?option=com_content&amp;view=article&amp;id=78&amp;Itemid=111"
 * >Pedro Larra&#241;aga</a> and&nbsp;<a
 * href="http://www.sc.ehu.es/ccwbayes/members/jalozano/home/index.html"
 * >Jos&#233; Antonio Lozano</a>, editors, volume 2 of Genetic Algorithms
 * and Evolutionary Computation, Boston, MA, USA: Springer US
 * and&nbsp;Norwell, MA, USA: Kluwer Academic Publishers. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0792374665">0-7923-7466-5</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780792374664">978-0-7923
 * -7466-4</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/47996547">47996547</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=o0llxS4u93wC">o0llxS4u93wC</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=kyU74gxp1rsC">kyU74gxp1rsC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/19320167">1932-0167</a></div></li>
 * <li><div><span id="cite_LLIB2006TANEC" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Towards a New
 * Evolutionary Computation &#8210; Advances on Estimation of Distribution
 * Algorithms,&rdquo;</span> 2006, <a
 * href="http://www.sc.ehu.es/ccwbayes/members/jalozano/home/index.html"
 * >Jos&#233; Antonio Lozano</a>, <a href=
 * "http://cig.fi.upm.es/index.php?option=com_content&amp;view=article&amp;id=78&amp;Itemid=111"
 * >Pedro Larra&#241;aga</a>, <a
 * href="http://www.sc.ehu.es/ccwbayes/members/inaki.htm">I&#241;aki
 * Inza</a>, and&nbsp;<a href="http://www.sc.ehu.es/acwbecae/">Endika
 * Bengoetxea</a>, editors, volume 192/2006 of Studies in Fuzziness and
 * Soft Computing, Berlin, Germany: Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540290060">3-540-29006-0</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540290063">978-3-540-
 * 29006-3</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/2005932568">2005932568</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/11007937">10.1007/11007937</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/63763942">63763942</a>, <a
 * href="https://www.worldcat.org/oclc/318299594">318299594</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/oclc/181473672">181473672</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=0dku9OKxl6AC">0dku9OKxl6AC</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=r0UrGB8y2V0C">r0UrGB8y2V0C</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/14349922">1434-9922</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/18600808">1860-0808</a></div></li>
 * <li><div><span id="cite_DWT2011ASOSRFEOOGS" /><a
 * href="http://www.marmakoide.org/">Alexandre Devert</a>, <a
 * href="http://www.it-weise.de/">Thomas Weise</a> <span
 * style="color:gray">[&#27748;&#21355;&#24605;</span>], and&nbsp;<a
 * href="http://staff.ustc.edu.cn/~ketang/">Ke Tang</a> <span
 * style="color:gray">[&#21776;&#29634;</span>]: <span
 * style="font-weight:bold">&ldquo;A Study on Scalable Representations for
 * Evolutionary Optimization of Ground Structures,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Evolutionary
 * Computation</span> 20(3):453&ndash;472, Fall&nbsp;2012; published by
 * Cambridge, MA, USA: MIT Press. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1162/EVCO_a_00054">10.1162/EVCO_a_00054</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/44169764">44169764</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/10636560">1063-6560</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15309304">1530-9304</a>;
 * PubMed:&nbsp;<a
 * href="https://www.ncbi.nlm.nih.gov/pubmed/22004002">22004002</a>;
 * SCI/WOS:&nbsp;WOS:000306767200005. <div>links: [<a href=
 * "http://www.marmakoide.org/download/publications/devweita-ecj-preprint.pdf"
 * >1</a>] and&nbsp;[<a
 * href="http://www.it-weise.de/documents/files/DWT2011ASOSRFEOOGS.pdf"
 * >2</a>]</div></div></li>
 * <li><div><span id="cite_BK1999TWTGDACOEFAEDP" /><a
 * href="http://peterjbentley.com/">Peter John Bentley</a> and&nbsp;<a
 * href="http://iianalytics.com/iia-faculty/sanjeev-kumar-2/">Sanjeev P.
 * Kumar</a>: <span style="font-weight:bold">&ldquo;The Ways to Grow
 * Designs: A Comparison of Embryogenies for an Evolutionary Design
 * Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the
 * Genetic and Evolutionary Computation Conference (GECCO'99)</span>,
 * July&nbsp;13&ndash;17, 1999, Orlando, FL, USA, pages 35&ndash;43, <a
 * href="http://www.cs.mun.ca/~banzhaf/">Wolfgang Banzhaf</a>, Jason M.
 * Daida, <a href="http://www.cs.vu.nl/~gusz/">&#193;goston E. Eiben</a>
 * aka. Gusz/Guszti, Max H. Garzon, Vasant Honavar, Mark J. Jakiela,
 * and&nbsp;Robert Elliott Smith, editors, San Francisco, CA, USA: Morgan
 * Kaufmann Publishers Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1558606114">1-55860-611-4</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781558606111">978-1-55860
 * -611-1</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/59333111">59333111</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=-1vTAAAACAAJ">-1vTAAAACAAJ</a>.
 * <div>link: [<a
 * href="http://www.cs.ucl.ac.uk/staff/ucacpjb/BEKUC1.pdf">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.51.1345">
 * 10.1 .1.51.1345</a></div></div></li>
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
package org.logisticPlanning.tsp.solving.algorithms;

