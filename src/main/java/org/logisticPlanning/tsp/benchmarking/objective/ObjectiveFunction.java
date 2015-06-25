package org.logisticPlanning.tsp.benchmarking.objective;

import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer;
import org.logisticPlanning.tsp.solving.utils.RepresentationUtils;
import org.logisticPlanning.tsp.solving.utils.SolutionValidator;
import org.logisticPlanning.utils.NamedObject;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * <p>
 * The objective function is the core information source for an
 * optimization algorithm solving a TSP. It is mainly a wrapper around <a
 * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
 * >TSPLib</a>&nbsp;[<a href="#cite_DACO1995TSPLIB"
 * style="font-weight:bold">1</a>, <a href="#cite_R1995T9"
 * style="font-weight:bold">2</a>, <a href="#cite_R1991ATSPL"
 * style="font-weight:bold">3</a>, <a href="#cite_W2003ROCFTB"
 * style="font-weight:bold">4</a>] that provides in-memory logging and
 * information about the search process. We log information in order to
 * enable an evaluation similar to what is done in the BBOB Workshop
 * regarding numerical optimization&nbsp;[<a href="#cite_HAFR2012RPBBOBES"
 * style="font-weight:bold">5</a>, <a href="#cite_NAFR2010RPBBOB2ES"
 * style="font-weight:bold">6</a>, <a href="#cite_HAFR2009RPBBOB2ES"
 * style="font-weight:bold">7</a>, <a href="#cite_AH2005PEOAALSEA"
 * style="font-weight:bold">8</a>].
 * </p>
 * <p>
 * This class not only provides information such as the problem dimension (
 * {@link #n()}), the distance between nodes ({@link #distance(int, int)}),
 * and tour lengths ({@link #evaluate(int[])}, {@link #evaluateAdj(int[])}
 * ), it also provides the random number generator ({@link #getRandom()})
 * for the optimization algorithm. Also, it can be queried about
 * information regarding the current run, such as the best solution found
 * so far ( {@link #getCopyOfBest(int[])}, {@link #getCopyOfBestAdj(int[])}
 * ) and the corresponding objective value, FEs, and DEs via
 * {@link #getCurrentLogPoint()} . Furthermore, it serves as termination
 * criterion: an optimization process can run until
 * {@link #shouldTerminate()} becomes {@code true}.
 * </p>
 * <h2>Supported Representation</h2>
 * <p>
 * Solutions to the TSP can be represented with different data
 * structures&nbsp;[<a href="#cite_FJMGO1995DSFTS"
 * style="font-weight:bold">9</a>, <a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">10</a>, <a href="#cite_JMG2004EAOHFTS"
 * style="font-weight:bold">11</a>, <a href="#cite_OR2006TSLANDSFTSP"
 * style="font-weight:bold">12</a>], each of which having distinctive
 * advantages and drawbacks. The
 * <em><a href="http://www.logisticPlanning.org/tsp">TSP Suite</a></em> can
 * deal with different representations for the candidate solutions of a
 * TSP:
 * </p>
 * <ol>
 * <li id="pathRepresentation">
 * <p>
 * The <em>path representation</em>&nbsp;[<a
 * href="#cite_LKMUB1999GAFTTSPARORAO" style="font-weight:bold">10</a>, <a
 * href="#cite_P1996GAFTTSP" style="font-weight:bold">13</a>] is probably
 * the most natural representation of a tour. Here, a tour is represented
 * as a permutation of the numbers from {@code 1} to {@code n}. If city
 * {@code i} is the {@code j}<sup>th</sup> element of the list, then this
 * city {@code i} is the {@code j}<sup>th</sup> city to be visited. Hence,
 * the tour
 * <code>3&rarr;2&rarr;4&rarr;1&rarr;7&rarr;5&rarr;8&rarr;6&rarr;3</code>
 * is simply represented by <code>new int[] {3, 2, 4, 1, 7, 5, 8, 6}</code>
 * &nbsp;[<a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">10</a>, <a href="#cite_P1996GAFTTSP"
 * style="font-weight:bold">13</a>].
 * </p>
 * <p id="pathNormalForm">
 * There are multiple permutations that encode for the same tour in path
 * representation. In order to ease visual comparison of tours, the
 * {@link org.logisticPlanning.tsp.solving.utils.RepresentationUtils#appendPathInNormalForm(int[],java.lang.Appendable)
 * Normal Form} for path representation was defined. Here, the first
 * element is node {@code 1} and then the neighboring node with the lowest
 * index follows.
 * </p>
 * <p>
 * The methods associated with this representation are
 * {@link #evaluate(int[])}, {@link #registerFE(int[], long)},
 * {@link #registerFEs(long, int[], long)}, and
 * {@link #getCopyOfBest(int[])}.
 * </p>
 * </li>
 * <li id="adjacencyRepresentation">
 * <p>
 * In the <em>adjacency representation</em>&nbsp;[<a
 * href="#cite_GGRVG1985GAFTT" style="font-weight:bold">14</a>, <a
 * href="#cite_LKMUB1999GAFTTSPARORAO" style="font-weight:bold">10</a>, <a
 * href="#cite_P1996GAFTTSP" style="font-weight:bold">13</a>] a tour is
 * also represented as permutation of the numbers from {@code 1} to
 * {@code n}. City {@code j} is listed in position {@code i} if, and only
 * if, the tour directly leads from city {@code i} to city {@code j}. Thus,
 * the list <code>new int[] {3, 5, 7, 6, 4, 8, 2, 1}</code> represents the
 * tour
 * <code>1&rarr;3&rarr;7&rarr;2&rarr;5&rarr;4&rarr;6&rarr;8&rarr;1</code>.
 * While each possible permutation is a valid solution in <a
 * href="#pathRepresentation"><em>path representation</em></a>, this is not
 * true for the adjacency representation. The permutation {@code (1, 3, 2)}
 * , for instance, is not a valid adjacency list, since it would mean
 * &quot;after node 1 go to node 1&quot; (cycle of length 1) and
 * &quot;after node 2 go to node 3, after node 3 go to node 2&quot; (cycle
 * of length 2).
 * </p>
 * <p>
 * The methods associated with this representation are
 * {@link #evaluateAdj(int[])}, {@link #registerFEAdj(int[], long)},
 * {@link #registerFEsAdj(long, int[], long)}, and
 * {@link #getCopyOfBestAdj(int[])}.
 * </p>
 * <p>
 * Although a tour in adjacency representation here is stored in an array
 * of {@code int}, we could consider this representation as
 * <em>linked list</em> (a singly linked list, not a doubly linked one).
 * Effectively, we could begin at any city {@code i} which would mark the
 * start of the tour. At index {@code i-1} in the array, we would find the
 * next city {@code j} to visit. This could be interpreted as link or
 * pointer, since we would then look at index {@code j-1} in the array to
 * get the next-next city, and so on.
 * </p>
 * <li id="satelliteList">
 * <p>
 * {@link org.logisticPlanning.tsp.solving.utils.satelliteList.SatelliteList
 * Satellite lists}&nbsp;[<a href="#cite_OR2006TSLANDSFTSP"
 * style="font-weight:bold">12</a>, <a href="#cite_ORG2005TSLARDLL"
 * style="font-weight:bold">15</a>] are a clever data structure for
 * representing tours for symmetric TSPs. Compared to the path and
 * adjacency representation, the have the advantage that the reversal of a
 * segment of the tour can be achieved in <em>O(1)</em> instead of
 * <em>O(n)</em>. You can find them implemented in the utilities package
 * {@link org.logisticPlanning.tsp.solving.utils.satelliteList satellite},
 * together with corresponding conversion routines.
 * <p>
 * </p> {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
 * distance computers} does not support this data structure directly. </p></li>
 * <li id="edgeRepresentation">
 * <p>
 * We furthermore introduce the class
 * {@link org.logisticPlanning.tsp.solving.utils.edge.Edge} to represent a
 * directed or undirected edge.
 * {@link org.logisticPlanning.tsp.solving.utils.edge.Edge Edge} therefore
 * has two public member variables
 * {@link org.logisticPlanning.tsp.solving.utils.edge.Edge#a a} and
 * {@link org.logisticPlanning.tsp.solving.utils.edge.Edge#b b} indicating
 * its start an end nodes. Thus, another way to represent a solution is as
 * a set of such edge objects. Such a set can be stored in an array or in
 * one of Java's {@link java.util.Collection collection} objects, e.g., a
 * {@link java.util.Set set} or {@link java.util.List list}.
 * </p>
 * <p>
 * The {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
 * distance computers} do not provide specialized support methods to
 * evaluate solutions in this representation nor does the
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction
 * objective function} have dedicated evaluation methods for them. Such
 * specialized methods would just make these classes bigger without
 * performance gain.
 * </p>
 * <p>
 * However, the
 * {@link org.logisticPlanning.tsp.solving.utils.RepresentationUtils}
 * sports several <a href="#edgeListMethods">conversion routines</a> as of
 * version {@code 0.9.8}.
 * </p>
 * </li>
 * </ol>
 * <p>
 * Elements in either representation can be converted by using the
 * following static methods:
 * </p>
 * <ol>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.utils.RepresentationUtils#adjacencyListToPath(int[], int[])
 * adjacencyListToPath} converts a solution from <a
 * href="#adjacencyRepresentation"><em>adjacency representation</em></a> to
 * <a href="#pathRepresentation"><em>path representation</em></a>.</li>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.utils.RepresentationUtils#pathToAdjacencyList(int[], int[])
 * pathToAdjacencyList} converts a solution from <a
 * href="#pathRepresentation"> <em>path representation</em></a> to <a
 * href="#adjacencyRepresentation"> <em>adjacency representation</em></a>.</li>
 * <li id="edgeListMethods">
 * {@link org.logisticPlanning.tsp.solving.utils.RepresentationUtils#pathToEdges(int[],org.logisticPlanning.tsp.solving.utils.edge.Edge[])
 * pathToDirectedEdges} converts a solution in <a
 * href="#pathRepresentation"> <em>path representation</em></a> to a set
 * (array) of edges.</li>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.utils.RepresentationUtils#adjacencyListToEdges(int[],org.logisticPlanning.tsp.solving.utils.edge.Edge[])
 * adjacencyListToDirectedEdges} converts a solution in <a
 * href="#adjacencyRepresentation"><em>adjacency representation</em></a> to
 * a set (array) of edges.</li>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.utils.RepresentationUtils#undirectedEdgesToAdjacencyList(java.lang.Iterable,int[])
 * undirectedEdgesToAdjacencyList(Iterable, int[])} converts a
 * {@link java.lang.Iterable iterable} set or list of potentially
 * undirected edges into a solution in <a href="#adjacencyRepresentation">
 * <em>adjacency representation</em></a>.</li>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.utils.RepresentationUtils#directedEdgesToAdjacencyList(java.lang.Iterable,int[])
 * directedEdgesToAdjacencyList(Iterable, int[])} converts a
 * {@link java.lang.Iterable iterable} set or list of directed edges into a
 * solution in <a href="#adjacencyRepresentation">
 * <em>adjacency representation</em></a>.</li>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.utils.RepresentationUtils#undirectedEdgesToAdjacencyList(org.logisticPlanning.tsp.solving.utils.edge.Edge[],int[])
 * undirectedEdgesToAdjacencyList(Edge[], int[])} converts an array of
 * potentially undirected edges into a solution in <a
 * href="#adjacencyRepresentation"><em>adjacency representation</em></a>.</li>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.utils.RepresentationUtils#directedEdgesToAdjacencyList(org.logisticPlanning.tsp.solving.utils.edge.Edge[],int[])
 * directedEdgesToAdjacencyList(Edge[], int[])} converts an array of
 * directed edges into a solution in <a href="#adjacencyRepresentation">
 * <em>adjacency representation</em></a>.</li>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.utils.satelliteList.SatelliteList#fromPath(int[])
 * fromPath(int[])} of
 * {@link org.logisticPlanning.tsp.solving.utils.satelliteList.SatelliteList}
 * loads the contents of an integer array holding a solution in <a
 * href="#pathRepresentation"><em>path representation</em></a> into a
 * satellite list instance.</li>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.utils.satelliteList.SatelliteList#toPath(int[])
 * toPath(int[])} of class
 * {@link org.logisticPlanning.tsp.solving.utils.satelliteList.SatelliteList}
 * translates the contents of a satellite list to the <a
 * href="#pathRepresentation"><em>path representation</em></a>. While on
 * it, it also checks if the translation results in a feasible path. (There
 * may be infeasible satellite lists).</li>
 * </ol>
 * <p>
 * Regardless in which format tours are represented by the optimization
 * algorithm applied to a TSP, the
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction
 * objective function} stores a copy of the best tour in <a
 * href="#pathRepresentation"><em>path representation</em></a> internally.
 * You can access this tour in path representation via
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#getCopyOfBest(int[])
 * getCopyOfBest(int[])} and in <a href="#adjacencyRepresentation">
 * <em>adjacency representation</em></a> via
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#getCopyOfBestAdj(int[])
 * getCopyOfBestAdj(int[])}. The best solution found will be stored in the
 * log files in path representation in log section
 * {@value org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#BEST_SOLUTION}
 * . It will be written in <a href="#pathNormalForm">normal form</a> via
 * the method
 * {@link org.logisticPlanning.tsp.solving.utils.RepresentationUtils#appendPathInNormalForm(int[],java.lang.Appendable)
 * appendPathInNormalForm}.
 * </p>
 * <h2>Performing Experimental Runs</h2>
 * <p>
 * Before starting a run, it is necessary to call
 * {@link #beginRun(NamedObject)} and after a run has finished, you must
 * call {@link #endRun()}. The reason is that this class also manages
 * in-memory logging information which will be stored to a log file once
 * the run completes. Due to this architecture and by pre-allocating any
 * memory needed for logging, the objective function can do automated
 * logging without any I/O or memory allocation during the run, i.e.,
 * without any impact on the runtime of the optimization method.
 * </p>
 * <p>
 * Whenever a complete candidate solution has been constructed, its
 * objective value can be determined by calling {@link #evaluate(int[])}
 * (or {@link #evaluateAdj(int[])} for solutions in <a
 * href="#adjacencyRepresentation"><em>adjacency representation</em></a>).
 * Alternatively, in many TSP-solving algorithms, solutions are constructed
 * by updating existing solutions. During such a process, the objective
 * value of the new solution can often be determined with only a few node
 * distance computations (see {@link #distance(int, int)}). In such a case,
 * completely evaluating a permutation is not necessary. Instead, you can
 * call {@link #registerFE(int[], long)} (or
 * {@link #registerFEAdj(int[], long)} for solutions in <a
 * href="#adjacencyRepresentation"> <em>adjacency representation</em></a>)
 * and pass in the solution and its objective value. This will then update
 * the internal logging information accordingly.
 * </p>
 * <h2>Time Measures</h2>
 * <p>
 * The objective function provides a set of time measures for the algorithm
 * via the accessible <a href="#informationAvailable">log points</a> and
 * collects corresponding information for post-processing and evaluation. A
 * {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint log
 * point} stores information about the best solution quality (in terms of
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#evaluate(int[])
 * tour length}) that an optimization algorithm has achieved until a given
 * point in time. There are four measures that define the term &quot;point
 * in time&quot; which are supported by our benchmark environment:
 * </p>
 * <ol>
 * <li>
 * <p>
 * The
 * {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedRuntime()
 * actual time} that has passed in milliseconds. This is the most natural
 * measure, but it also has some drawbacks. First, the time can only be
 * measured efficiently up to a certain precision. Second, different
 * computers may execute the algorithm at different speeds, meaning that an
 * algorithm may seem to be good just because it was executed on a fast
 * machine. Third, not only the machine itself determines the speed of the
 * algorithm, but also stuff like the settings of the benchmark
 * environment. For example, for some problems the
 * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
 * distance information} of the problem instance may be represented as
 * distance matrix (if the available memory permits it and the problem
 * dimension is reasonably small) and sometimes it may be represented as
 * coordinate list and distances are calculated on the fly. All in all, the
 * runtime measured will strongly depend on the machine and runtime
 * environment under which the experiment was carried out.
 * </p>
 * </li>
 * <li id="logPointNormalizedRuntime">
 * <p>
 * <b>Not available at runtime:</b>&nbsp;The
 * {@link org.logisticPlanning.tsp.benchmarking.objective.DataPoint#getConsumedNormalizedRuntime()
 * normalized time measure} (see the <a
 * href="#runtimeNormalization">section on runtime normalization</a>) tries
 * to mitigate this effect: Before performing an experiment, a
 * {@link org.logisticPlanning.tsp.benchmarking.objective._SpeedBenchmark
 * standardized algorithm} (
 * <em>{@link org.logisticPlanning.tsp.solving.algorithms.heuristics.doubleEndedNearestNeighbor.DoubleEndedNearestNeighborHeuristic double-ended nearest neighbor heuristic}</em>
 * &nbsp;[<a href="#cite_JMB1997TTSPACSILO"
 * style="font-weight:bold">16</a>, <a href="#cite_JMG2004EAOHFTS"
 * style="font-weight:bold">11</a>]) is applied to the problem instance (in
 * the same environment and with the same settings) and the time it needs
 * is measured. The actual runtime of the algorithm that we want to
 * benchmark is then divided (normalized) by this factor. Since the
 * standardized algorithm is also a TSP solver and has the same complexity
 * ( <code>O(n<sup>2</sup>)</code>) as many simple heuristics, this
 * normalization process leads to time measurements that are reasonably
 * well comparable over different platforms. Another advantage of this
 * metric is that it automatically &quot;scales&quot; with the problem
 * size. (This information is only available to the
 * {@link org.logisticPlanning.tsp.benchmarking.objective.DataPoint
 * post-processing} and not online for the running algorithm.)
 * </p>
 * </li>
 * <li>
 * <p>
 * The
 * {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedFEs()
 * total number} of solutions constructed so far, i.e., the number of
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#evaluate(int[])
 * objective function evaluations} (FEs) used up by the algorithm to get
 * the solution. This metric is independent of the speed of the computer,
 * which is both good (algorithms executed on fast computers have no
 * a-priori advantage) and bad (we do not know how much overhead
 * calculations an algorithm performs in order to construct a solution
 * before evaluating it). The latter means that algorithms may need a very
 * different amount of time to perform one function evaluation even on the
 * same computer. Obviously, we do not want to have algorithms that can
 * find good solutions with few FEs and in short time.
 * </p>
 * </li>
 * <li>
 * <p>
 * The
 * {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedDEs()
 * total number} of calls to the
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#distance(int, int)
 * distance function} which computes the distance between two points/cities
 * (DEs). Normally, we assume that evaluating one candidate solution takes
 * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#n() n}</code>
 * DEs, where <code>n</code> is the number of nodes or cities in a TSP.
 * However, that may not be true. For example, if we have a candidate
 * solution (node permutation) of a given tour length and want to
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdateOperator
 * modify} it, way maybe do not need to re-compute all the distances in the
 * solution but instead, only a few (
 * <code>{@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdateOperator#delta(int[], org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer, int, int) O(1)}</code>
 * ) distances.
 * </p>
 * <p>
 * This metric therefore provides some form of measure for the overhead per
 * function evaluation. Furthermore, if we assume that computing the
 * distance between two nodes is costly &ndash; as it may be in a
 * real-world application &ndash; the DE metric gives us a very good
 * estimate of the runtime an algorithm needs to achieve a certain
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#evaluate(int[])
 * solution quality}. The distance evaluations were also considered as
 * runtime measure in&nbsp;[<a href="#cite_B1990EATSP"
 * style="font-weight:bold">17</a>] (where instead evaluations of a
 * geometric analysis were used, which in general TSPs is not available).
 * </p>
 * <p>
 * The drawback of this approach is, however, that some algorithms
 * temporarily store distance and thus invoke the distance measure less
 * often. These algorithms then look very fast and may have an unreasonable
 * advantage. For proper benchmarking, we do not allow copying the distance
 * matrix, but it is often reasonable to allow the algorithms to store
 * {@code O(n)} or {@code O(n log n)} distances. If we would not allow
 * this, this would often force us to intentionally implement algorithms
 * inefficiently, which gives them an unfair disadvantage. The decision on
 * what distances are allowed to cache and which are not is a hard
 * decision.
 * </p>
 * </li>
 * </ol>
 * <h2>Normalized Runtime</h2>
 * <p id="runtimeNormalization">
 * An algorithm is a abstract set of rules describing how a problem should
 * be solved. A program is the implementation of the algorithm for a given
 * platform, say, Java. The runtime of the program depends on the
 * algorithm's structure, but also on the peculiarities of the platform and
 * on the structure and dynamics of the runtime environment it is executed
 * in. The latter includes the speed of the processor, the operating
 * system, the JVM version, the available memory. In our benchmarking
 * environment, additional factors such as the internal, hidden data
 * structure used for representing the
 * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
 * distance measure} also have an impact on the algorithm speed. Just
 * measuring the runtime in milliseconds therefore will not necessarily
 * give us a general, good impression on the algorithm's performance.
 * </p>
 * <p>
 * The goal thus is to make the measured
 * {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedRuntime()}
 * a meaningful quantity. Therefore, it measures how much time a
 * standardized algorithm {@code ALGO} needs to solve the given benchmark
 * instance. This time is then used to <a
 * href="#runtimeNormalization">normalize<a> the measured time of any other
 * algorithm (for exactly that instance, with exactly that settings and
 * distance metric). If we divide the runtime a given program needs to
 * solve a given problem instance (or the runtimes stored in its
 * {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint log
 * points}) with the runtime that the standardized algorithm needs for the
 * same instance, we get a useful time measure that can be compared over
 * different platforms. This approach is similar to the one in the DIMACS
 * challenge on TSPs&nbsp;[<a href="#cite_JMG20088DICTTSP"
 * style="font-weight:bold">18</a>, <a href="#cite_JMG2004EAOHFTS"
 * style="font-weight:bold">11</a>], but is different in that it will be
 * performed for each instance directly before the optimization runs and
 * using the same settings (such as distance information representations).
 * </p>
 * <p>
 * The &quot;standardized&quot; algorithm (implemented in class
 * {@link org.logisticPlanning.tsp.benchmarking.objective._SpeedBenchmark
 * _SpeedBenchmark}) we use to perform that measurement is the
 * <em>{@link org.logisticPlanning.tsp.solving.algorithms.heuristics.doubleEndedNearestNeighbor.DoubleEndedNearestNeighborHeuristic double-ended nearest neighbor heuristic}</em>
 * &nbsp;[<a href="#cite_JMB1997TTSPACSILO"
 * style="font-weight:bold">16</a>, <a href="#cite_JMG2004EAOHFTS"
 * style="font-weight:bold">11</a>]. This algorithm is TSP solver, so its
 * structure and operations should be somewhat similar to other TSP
 * solvers. It is furthermore deterministic, relatively simple, and has a
 * complexity of <code>O(n<sup>2</sup>)</code>, which is the same
 * complexity as most simple
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics
 * heuristics} have.
 * </p>
 * <p>
 * The time measurement for the run of {@code ALGO} works as follows:
 * </p>
 * <ol>
 * <li>For {@code i} from {@code 1} to {@code 10} do:</li>
 * <ol>
 * <li>A
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#_beginRun(org.logisticPlanning.utils.NamedObject, boolean)
 * new run is started} for {@code f}.</li>
 * <li>{@code ALGO} is
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithm#solve(ObjectiveFunction)
 * invoked} for the objective function {@code f}.</li>
 * <li>{@code N[i]} be the number of times {@code ALGO} was invoked so far
 * in this run. If {@code N[i]} is less than {@code N[j] : 0<j<i}, go back
 * to 1.2. This is done to reduce the number of
 * {@link java.lang.System#currentTimeMillis() system calls} caused by the
 * following step:</li>
 * <li>The consumed runtime {@code T[i]} (elapsed since point 1.1) is
 * measured after {@code ALGO} has finished by using
 * {@link java.lang.System#currentTimeMillis()}.</li>
 * <li>If {@code T[i]} is less than 30ms, go back to point 1.2, i.e.,
 * invoke {@code ALGO} again. This step is necessary since for some
 * instances / fast computers, the runtime measured for one invocation may
 * be zero and/or to short to be measured precisely and thus, useless for
 * normalization.</li>
 * <li>End the run and
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#_clear()
 * clear} all internal data of the objective function.</li>
 * <li>{@code N[i]} be the number of times {@code ALGO} was invoked, then
 * the normalization factor {@code F[i] = T[i]/N[i]}.</li>
 * <li>
 * </ol>
 * <li>As performance measure, return the smallest of the 10 values
 * {@code F[1] ... F[10]}. The smallest value instead of the mean here
 * makes sense, as {@code ALGO} is strictly deterministic. The smallest
 * value thus is the one where &quot;outside&quot; influences such as
 * scheduling of the OS, garbage collection in the VM, etc., had the least
 * impact. As we cannot predict these factors anyway, we want to take the
 * speed measurement closest to the real speed of the computer.</li> </ol>
 * <p>
 * The exact time measurement procedure is defined as <a
 * href="#timeMeasurementAlgo">follows</a>:
 * </p>
 * <table border="1" id="timeMeasurementAlgo" style="margin-left:auto;margin-right:auto">
 * <tr>
 * <td>
 *
 * <pre class="altColor">
 * static final double _timeBenchmarkRun(final ObjectiveFunction f) {
 *   final _SpeedBenchmark instance;
 *   long time, startTime;
 *   double factor, bestFactor;
 *   int runs, calls, minCalls;
 * 
 *   bestFactor = Double.POSITIVE_INFINITY;
 *   instance = _SpeedBenchmark.instance();
 *   minCalls = -1;
 * 
 *   for (runs = 10; (--runs) &gt;= 0;) {
 * 
 *     calls = 0;
 *     f._beginRun(instance, false);
 *     startTime = System.currentTimeMillis();
 * 
 *     loopy: for (;;) {
 *       instance.solve(f);
 *       if ((++calls) &gt;= minCalls) {
 *         time = (System.currentTimeMillis() - startTime);
 *         if (time &gt; 30l) {
 *           break loopy;
 *         }
 *       }
 *     }
 *     f._clear();
 * 
 *     if (calls &gt;= minCalls) {
 *       minCalls = calls;
 *     }
 *     factor = (time / ((double) calls));
 *     if (factor &lt; bestFactor) {
 *       bestFactor = factor;
 *     }
 *   }
 * 
 *   return bestFactor;
 * }
 * </pre>
 *
 * </td>
 * </tr>
 * <tr>
 * <td>The procedure used to measure the performance of the <a
 * href="#timeBenchmarkAlgo">standardized algorithm</a> in order to obtain
 * a useful normalization factor.</td>
 * </tr>
 * </table>
 * <p>
 * The algorithm (
 * <em>{@link org.logisticPlanning.tsp.solving.algorithms.heuristics.doubleEndedNearestNeighbor.DoubleEndedNearestNeighborHeuristic double-ended nearest neighbor heuristic}</em>
 * &nbsp;[<a href="#cite_JMB1997TTSPACSILO"
 * style="font-weight:bold">16</a>, <a href="#cite_JMG2004EAOHFTS"
 * style="font-weight:bold">11</a>]) used for measuring the benchmarks is
 * given as:
 * </p>
 * <table border="1" id="timeBenchmarkAlgo" style="margin-left:auto;margin-right:auto">
 * <tr>
 * <td>
 *
 * <pre class="altColor">
 * public final void solve(final ObjectiveFunction f) {
 *   final int n, addB;
 *   int aIdx, bIdx, rem, lastA, lastB, bestIdxA, bestA, bestLenA, bestIdxB, bestLenB, bestB, tempLen, tempNode, i;
 *   int[] temp, res;
 *   long length;
 * 
 *   // allocate node list
 *   n = f.n();
 *   temp = new int[n];
 *   for (aIdx = 0; aIdx &lt; n;) {
 *     temp[aIdx] = (++aIdx);
 *   }
 * 
 *   // allocate result array
 *   res = new int[n];
 * 
 *   length = 0l;
 * 
 *   aIdx = bIdx = 0;
 *   rem = addB = (n - 1);
 *   res[0] = lastA = lastB = 1;
 *   temp[0] = temp[rem];
 *   bestIdxA = bestIdxB = bestA = bestB = (-1);
 *   bestLenA = bestLenB = tempLen = Integer.MAX_VALUE;
 * 
 *   for (; rem &gt; 0;) {
 * 
 *     // find the best end extensions
 *     for (i = rem; (--i) &gt;= 0;) {
 *       tempNode = temp[i];
 * 
 *       // find the best node at end A
 *       if (bestA &lt;= 0) {
 *         tempLen = f.distance(lastA, tempNode);
 *         if (tempLen &lt;= bestLenA) {
 *           bestLenA = tempLen;
 *           bestA = tempNode;
 *           bestIdxA = i;
 *         }
 *       }
 * 
 *       // find the best node at end B (except in first round)
 *       if ((bestB &lt;= 0) &amp;&amp; (lastB != lastA)) {
 *         tempLen = f.distance(lastB, tempNode);
 *         if (tempLen &lt;= bestLenB) {
 *           bestLenB = tempLen;
 *           bestB = tempNode;
 *           bestIdxB = i;
 *         }
 *       }
 *     }
 * 
 *     // ok, which end offers the better extension?
 *     if (bestLenA &lt;= bestLenB) { // adding a node at end A seems to be
 *                                 // better
 *       aIdx++; // increase index
 *       res[aIdx] = lastA = bestA; // store node
 *       length += bestLenA; // add new edge's length to total length
 *       temp[bestIdxA] = temp[--rem]; // make node unavailable for future
 *       // adding
 *       if (rem == bestIdxB) { // if the moved node is the best b option?
 *         bestIdxB = bestIdxA; // correct b option index
 *       }
 *       if (bestB == bestA) { // if the same node could be added at both
 *         // ends
 *         bestIdxB = bestB = (-1); // we also need to re-calculate b
 *         bestLenB = Integer.MAX_VALUE;
 *       }
 *       bestIdxA = bestA = (-1); // we need to re-calculate a
 *       bestLenA = Integer.MAX_VALUE;
 *     } else { // ok, best choice is end b
 *       bIdx = ((bIdx + addB) % n); // decrease index
 *       res[bIdx] = lastB = bestB; // store node
 *       length += bestLenB; // add new edge's length to total length
 *       temp[bestIdxB] = temp[--rem]; // make node unavailable for future
 *       // adding
 *       if (rem == bestIdxA) { // if the moved node is the best a option?
 *         bestIdxA = bestIdxB; // correct a option index
 *       }
 *       if (bestB == bestA) { // if the same node could be added at both
 *         // ends
 *         bestIdxA = bestA = (-1); // we also need to re-calculate a
 *         bestLenA = Integer.MAX_VALUE;
 *       }
 *       bestIdxB = bestB = (-1); // we need to re-calculate b
 *       bestLenB = Integer.MAX_VALUE;
 *     }
 *   }
 * 
 *   // add distance of edge connecting both tour ends
 *   length += f.distance(lastA, lastB);
 *   f.registerFE(res, length); // register result
 * }
 * </pre>
 *
 * </td>
 * </tr>
 * <tr>
 * <td>The simple double-ended nearest neighbor heuristic used to get a
 * time normalization factor.</td>
 * </tr>
 * </table>
 * <p>
 * The Java Virtual Machine may either run the program code in an
 * interpreted way or (or later) compile it to machine code using a
 * Just-In-Time (JIT) compiler&nbsp;[<a href="#cite_S2003JPT"
 * style="font-weight:bold">19</a>, <a href="#cite_O2010DGUJCAO"
 * style="font-weight:bold">20</a>]. At any point in time, it may perform
 * online optimization to speed up the benchmark's algorithm, such as
 * switching from interpreted to compiled code, inlining and unwinding
 * loops, etc. The compilation by the JIT usually takes place after a few
 * thousand invocations, if it takes place. Therefore, the first few
 * executions of the algorithm may need a significantly different time than
 * the latter runs. In order to mitigate this problem and to enforce the
 * optimizations to happen before the experiment, we will call the
 * benchmarking function here 200000 times on a small problem instance (
 * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BURMA14
 * BURMA14}).
 * </p>
 * <p>
 * Since we perform this speed benchmark for any instance of the objective
 * function, it can also be used to <a
 * href="#runtimeNormalization">normalize<a> away impacts of the way the
 * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
 * distance information} of the problem is represented (distance matrix?
 * coordinate list?) as well as impact on the problem scale.
 * </p>
 * <p>
 * The
 * <em>{@link org.logisticPlanning.tsp.solving.algorithms.heuristics.doubleEndedNearestNeighbor.DoubleEndedNearestNeighborHeuristic double-ended nearest neighbor heuristic}</em>
 * &nbsp;[<a href="#cite_JMB1997TTSPACSILO"
 * style="font-weight:bold">16</a>, <a href="#cite_JMG2004EAOHFTS"
 * style="font-weight:bold">11</a>] used for obtaining the normalization
 * factor proceeds as follows: It starts with node {@code 1} as initial
 * partial tour. It then iteratively finds the nearest neighboring nodes of
 * each end of the tour and adds the nearest (of the two) until the tour is
 * completed (all nodes are present).
 * </p>
 * <h2 id="detInit">Deterministic Initialization Processes</h2>
 * <p>
 * The system allows you to have one deterministic initialization process.
 * Sometimes you may use a simple, deterministic heuristic to generate an
 * initial solution. As this algorithm always will behave exactly the same,
 * it does not make any sense to perform it for each run of a given
 * benchmark again. Instead, we can run it one time and then re-use the
 * recorded log points and information in each run. For this purpose, the
 * objective function implementation has an initialization facility. While
 * each run is embedded into {@link #beginRun(NamedObject)} and
 * {@link #endRun()} calls, the initialization procedure starts with
 * {@link #beginDeterministicInitialization(NamedObject)} and is ended with
 * {@link #endDeterministicInitialization()}. In between these two method
 * calls, you can use exactly the same functions for computing distances
 * and for evaluating candidate solutions that you also use in
 * &quot;normal&quot; optimization. All information recorded will be
 * preserved. If you start a new run after this initialization procedure,
 * the logged information will be pre-pended to whatever your algorithm
 * does and the runtime consumed by initialization will be added as well
 * (and subtracted from the maximum runtime you grant your algorithm, if
 * you specify such a limit). Besides saving time by not performing the
 * same thing again and again for each run (if you would embed the whole
 * initialization into the run), using this facility will also lead to
 * additional entries in the log file, specifying the name, class, and
 * configuration of the initialization algorithm, the runtime/DEs/FEs it
 * has consumed, and the best solution and objective function value it
 * discovered. If you start a new initialization procedure, it will
 * overwrite the information from the previous one, so you can only have
 * one initialization procedure and any point in time, but still may
 * perform runs with different ones consecutively.
 * </p>
 * <h2 id="informationAvailable">Information Available to the Running
 * Algorithm</h2>
 * <p>
 * The objective function provides three
 * {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint log
 * points} that are changing over time:
 * </p>
 * <ol>
 * <li>
 * <p>
 * The {@link #getCurrentLogPoint() current log point} represents the
 * current state of the search, including the currently exhausted amount of
 * {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedDEs()
 * DEs},
 * {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedFEs()
 * FEs}, and
 * {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedRuntime()
 * runtime}, as well as the
 * {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getBestF()
 * best objective value} found so far. This log point is constantly updated
 * and available both during the initialization as well as the actual
 * optimization phase.
 * </p>
 * <p>
 * The
 * {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getBestF()
 * objective value} presented here corresponds to the solution copy that
 * can be obtained via {@link #getCopyOfBest(int[])} in <a
 * href="#pathRepresentation"> <em>path representation</em></a> and via
 * {@link #getCopyOfBestAdj(int[])} in <a href="#adjacencyRepresentation">
 * <em>adjacency representation</em></a>.
 * </p>
 * <p>
 * If a <a href="#detInit">deterministic initialization</a> procedure has
 * been performed, the values in this log point will also include the
 * results from the initialization during the actual algorithm run (but not
 * while the initialization is ongoing). This means that the presented
 * amount of
 * {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedDEs()
 * DEs}, for instance, will include those from the initialization
 * procedure.
 * </p>
 * </li>
 * <li>
 * <p>
 * The {@link #getLastImprovementLogPoint() last improvement log point}
 * reflects the situation when the last improvement of the objective value
 * was achieved. Like the {@link #getCurrentLogPoint() current log point},
 * the values in this log point are always updated on the fly, with
 * exception of the consumed
 * {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedRuntime()
 * runtime}, which will only be updated when a new mandatory log threshold
 * is crossed.
 * </p>
 * <p>
 * The
 * {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getBestF()
 * objective value} presented here corresponds to the solution copy that
 * can be obtained via {@link #getCopyOfBest(int[])}
 * </p>
 * <p>
 * If a <a href="#detInit">deterministic initialization</a> procedure has
 * been performed, the values in this log point will also include the
 * results from the initialization during the actual algorithm run (but not
 * while the initialization is ongoing). This means that the presented
 * amount of
 * {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedDEs()
 * DEs}, for instance, will include those from the initialization
 * procedure.
 * </p>
 * </li>
 * <li>
 * <p>
 * The {@link #getDeterministicInitializationLogPoint() initialization log
 * point} corresponds to the situation after the deterministic
 * initialization procedure. It will be set at most once, when and only
 * when such an initialization procedure has finished. After and before
 * that, it will not change.
 * </p>
 * </li>
 * </ol>
 * <h2 style="color:red">Not Thread Safe!</h2>
 * <p>
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction
 * Objective functions} are not thread-safe: one function must be used only
 * in one single thread. However, you can create arbitrarily many such
 * functions, one for each thread you want to use, for instance, from one
 * {@link org.logisticPlanning.tsp.benchmarking.objective.Benchmark
 * Benchmark}.
 * </p>
 * <p id="threadData">
 * The {@link org.logisticPlanning.tsp.benchmarking.objective.Benchmark
 * benchmarking API} of this package in general is thread-safe: You can use
 * instance of
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction
 * Benchmark} and generate an instance of
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction
 * objective function} for each thread that you are using. This has the
 * advantage that data such as the distance matrix or the list of points to
 * log can be shared, which reduces both the memory finger print and
 * improves caching speed. If you have multiple processors or processors
 * with hyper threading, you can make the maximum use of this capability
 * by, e.g., performing multiple runs in parallel. For this purpose, all
 * modules of your algorithm must be thread-save. A simple trick for this
 * is to make all configuration parameters and the algorithm itself
 * {@link java.lang.Cloneable cloneable}. Then, you can perform a run of
 * the algorithm with a clone in each thread.
 * </p>
 * <p>
 * The log files generated by this class can be read in with the
 * {@link org.logisticPlanning.tsp.evaluation.Evaluator evaluator} utility
 * that comes along with this software.
 * </p>
 * <p>
 * You can find more information about the TSPLib&nbsp;[<a
 * href="#cite_DACO1995TSPLIB" style="font-weight:bold">1</a>, <a
 * href="#cite_R1995T9" style="font-weight:bold">2</a>, <a
 * href="#cite_R1991ATSPL" style="font-weight:bold">3</a>, <a
 * href="#cite_W2003ROCFTB" style="font-weight:bold">4</a>], the
 * benchmarking data set we use, in the file tsplib_doc.pdf included in the
 * documentation folder of this project (the same folder where also JavaDoc
 * is included).
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
 *     {@link org.logisticPlanning.tsp.solving.TSPAlgorithmRunner TSPAlgorithmRunner}.{@link org.logisticPlanning.tsp.solving.TSPAlgorithmRunner#benchmark(org.logisticPlanning.utils.collections.lists.ArrayListView, java.lang.Class, java.lang.String[]) benchmark}({@link org.logisticPlanning.tsp.benchmarking.instances.Instance#SYMMETRIC_INSTANCES Instance.SYMMETRIC_INSTANCES},
 *         MyHeuristic.class,
 *         args);
 *   }
 * </pre>
 *
 * </td>
 * </tr>
 * <tr>
 * <td>The main method which invokes the benchmarking system to test the
 * algorithm {@code MyHeuristic} for all
 * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#SYMMETRIC_INSTANCES
 * symmetric} <a
 * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/">TSPLib
 * </a>&nbsp;[<a href="#cite_DACO1995TSPLIB"
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
 * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/">TSPLib
 * </a>&nbsp;[<a href="#cite_DACO1995TSPLIB"
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
 * href="#cite_B2009JPG" style="font-weight:bold">21</a>, <a
 * href="#cite_RS2005JRPMFPT" style="font-weight:bold">22</a>, <a
 * href="#cite_MH2004JIA" style="font-weight:bold">23</a>]. It is strongly
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
 * import {@code MyPackage}.{@code MyHeuristic};
 * 
 * import {@link test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest};
 * 
 * public class {@code MyHeuristic}Test extends {@link test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest TSPAlgorithmSymmetricTest} {
 * 
 *   public {@code MyHeuristic}Test() {
 *     super();
 *   }
 * 
 *   {@code @Override}
 *   protected {@code MyHeuristic} createAlgorithm() {
 *     return new {@code MyHeuristic}();
 *   }
 * }
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
 * style="font-weight:bold">24</a>], you could do that as follows:
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
 * <li><div><span id="cite_NAFR2010RPBBOB2ES" /><a
 * href="http://www.lri.fr/~hansen/">Nikolaus Hansen</a>, <a
 * href="http://www.lri.fr/~auger/">Anne Auger</a>, <a
 * href="http://www.researchgate.net/profile/Steffen_Finck/">Steffen
 * Finck</a>, and&nbsp;<a
 * href="https://tao.lri.fr/tiki-index.php?page=people">Raymond Ros</a>:
 * <span style="font-weight:bold">&ldquo;Real-Parameter Black-Box
 * Optimization Benchmarking 2010: Experimental Setup,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;7215, March&nbsp;9, 2010; published by Institut National de
 * Recherche en Informatique et en Automatique (INRIA). <div>link: [<a
 * href="http://hal.inria.fr/docs/00/46/24/81/PDF/RR-7215.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_HAFR2009RPBBOB2ES" /><a
 * href="http://www.lri.fr/~hansen/">Nikolaus Hansen</a>, <a
 * href="http://www.lri.fr/~auger/">Anne Auger</a>, <a
 * href="http://www.researchgate.net/profile/Steffen_Finck/">Steffen
 * Finck</a>, and&nbsp;<a
 * href="https://tao.lri.fr/tiki-index.php?page=people">Raymond Ros</a>:
 * <span style="font-weight:bold">&ldquo;Real-Parameter Black-Box
 * Optimization Benchmarking 2009: Experimental Setup,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;RR-6828, October&nbsp;16, 2009; published by Institut
 * National de Recherche en Informatique et en Automatique (INRIA).
 * <div>links: [<a
 * href="http://hal.archives-ouvertes.fr/inria-00362649/en/">1</a>], [<a
 * href="http://hal.inria.fr/inria-00362649/en">2</a>], and&nbsp;[<a
 * href="http://coco.gforge.inria.fr/doku.php?id=bbob-2009"
 * >3</a>]</div></div></li>
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
 * href="http://dx.doi.org/10.1109/CEC.2005.1554903">10.1109/CEC.2005
 * .1554903</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/62773008">62773008</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=-QIVAAAACAAJ">-QIVAAAACAAJ</a>;
 * INSPEC Accession Number:&nbsp;8715465. <div>link: [<a
 * href="http://www.lri.fr/~hansen/cec2005localcmaes.pdf">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.71.6248">10.1
 * .1.71 .6248</a></div></div></li>
 * <li><div><span id="cite_FJMGO1995DSFTS" /><a
 * href="http://en.wikipedia.org/wiki/Michael_Fredman">Michael L.
 * Fredman</a>, <a
 * href="https://en.wikipedia.org/wiki/David_S._Johnson">David Stifler
 * Johnson</a>, <a
 * href="https://www.amherst.edu/people/facstaff/lamcgeoch">Lyle A.
 * McGeoch</a>, and&nbsp;Gerard Ostheimer: <span
 * style="font-weight:bold">&ldquo;Data Structures for Traveling
 * Salesmen,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Journal of
 * Algorithms</span> 18(3):432&ndash;479, May&nbsp;1995; published by
 * Amsterdam, The Netherlands: Elsevier Science Publishers B.V..
 * doi:&nbsp;<a
 * href="http://dx.doi.org/10.1006/jagm.1995.1018">10.1006/jagm
 * .1995.1018</a>. <div>link: [<a
 * href="ftp://dimacs.rutgers.edu/pub/dsj/temp/data.ps">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.71.170">10.1
 * .1.71 .170</a></div></div></li>
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
 * <li><div><span id="cite_JMG2004EAOHFTS" /><a
 * href="https://en.wikipedia.org/wiki/David_S._Johnson">David Stifler
 * Johnson</a> and&nbsp;<a
 * href="https://www.amherst.edu/people/facstaff/lamcgeoch">Lyle A.
 * McGeoch</a>: <span style="font-weight:bold">&ldquo;Experimental Analysis
 * of Heuristics for the STSP,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">The Traveling Salesman
 * Problem and its Variations</span>, chapter 369&ndash;443, pages
 * 369&ndash;443, <a href=
 * "http://www.rhul.ac.uk/computerscience/staffdirectory/ggutin/home.aspx">
 * Gregory Z. Gutin</a> and&nbsp;<a
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
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.15.9438">10.1
 * .1.15 .9438</a></div></div></li>
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
 * <li><div><span id="cite_P1996GAFTTSP" /><a
 * href="http://www.iro.umontreal.ca/~potvin/">Jean-Yves Potvin</a>: <span
 * style="font-weight:bold">&ldquo;Genetic Algorithms for the Traveling
 * Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Annals of Operations
 * Research</span> 63(3):337&ndash;370, June&nbsp;1, 1996; published by
 * Dordrecht, Netherlands: Springer Netherlands and&nbsp;Amsterdam, The
 * Netherlands: J. C. Baltzer AG, Science Publishers. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/BF02125403">10.1007/BF02125403</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/02545330">0254-5330</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15729338">1572-9338</a>.
 * <div>CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a href
 * ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.93.2179"
 * >10.1.1.93 .2179</a></div></div></li>
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
 * href="http://dx.doi.org/10.1007/3-211-27389-1_131">10.1007/3-211-
 * 27389-1_131</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=FvMHsHo8DJcC">FvMHsHo8DJcC</a>;
 * further information: [<a href="http://icannga05.dei.uc.pt/">1</a>]</div>
 * </li>
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
 * href="http://www.research.att.com/~dsj/papers/TSPchapter.pdf">1
 * </a>]</div></div></li>
 * <li><div><span id="cite_B1990EATSP" /><a href=
 * "http://www.avaya.com/usa/avaya-labs/people/jon-bentleyresearch-scientist"
 * >Jon Louis Bentley</a>: <span
 * style="font-weight:bold">&ldquo;Experiments on Traveling Salesman
 * Heuristics,&rdquo;</span> pages 91&ndash;99, January&nbsp;22&ndash;24,
 * 1990; edited by <a
 * href="https://en.wikipedia.org/wiki/David_S._Johnson">David Stifler
 * Johnson</a>; published by Philadelphia, PA, USA: Society for Industrial
 * and Applied Mathematics (SIAM). ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0898712513">0-89871-251-3</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780898712513">978-0-89871
 * -251-3</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=v8pQAAAAYAAJ">v8pQAAAAYAAJ</a>;
 * ASIN:&nbsp;<a href="http://www.amazon.com/dp/0898712513">0898712513</a>.
 * <div>link: [<a
 * href="http://dl.acm.org/citation.cfm?id=320176.320186">1</
 * a>]</div></div></li>
 * <li><div><span id="cite_JMG20088DICTTSP" /><a
 * href="https://en.wikipedia.org/wiki/David_S._Johnson">David Stifler
 * Johnson</a> and&nbsp;<a
 * href="https://www.amherst.edu/people/facstaff/lamcgeoch">Lyle A.
 * McGeoch</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;8th DIMACS
 * Implementation Challenge: The Traveling Salesman Problem,&rdquo;</span>
 * (Website), December&nbsp;12, 2008, Florham Park, NJ, USA: AT&amp;T Labs
 * Research &#8212; Leading Invention, Driving Innovation. <div>link: [<a
 * href="http://www2.research.att.com/~dsj/chtsp/">1</a>]</div></div></li>
 * <li><div><span id="cite_S2003JPT" />Jack Shirazi: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Java Performance
 * Tuning,&rdquo;</span> 2003, Java Series, Sebastopol, CA, USA: O'Reilly
 * &amp; Associates, Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0596003773">0596003773</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780596003777">9780596003777</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=iPHtCfZQyqQC"
 * >iPHtCfZQyqQC</a></div></li>
 * <li><div><span id="cite_O2010DGUJCAO" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Diagnostics Guide:
 * Understanding JIT Compilation and Optimizations,&rdquo;</span>
 * (Website), February&nbsp;1, 2010, Redwood Shores, CA, USA: Oracle
 * Corporation. <div>link: [<a href=
 * "http://docs.oracle.com/cd/E13150_01/jrockit_jvm/jrockit/geninfo/diagnos/underst_jit.html"
 * >1</a>]</div></div></li>
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
 * </ol>
 */
public final class ObjectiveFunction extends DistanceComputer {

  /** the string indicating the begin of a comment: {@value} */
  public static final String COMMENT_START = "//";//$NON-NLS-1$

  /**
   * the empty line: an empty line only contains the comment begin mark:
   * {@value #COMMENT_START}
   */
  private static final char[] EMPTY_LINE = ObjectiveFunction.COMMENT_START
      .toCharArray();

  /**
   * the identifier to begin the section in the log files which holds the
   * logged information: {@value}
   */
  public static final String LOG_DATA_SECTION = "LOG_DATA_SECTION"; //$NON-NLS-1$

  /**
   * the identifier to begin the section in the log files which holds the
   * infos about the system: {@value}
   */
  public static final String SYSTEM_DATA_SECTION = "SYSTEM_DATA_SECTION"; //$NON-NLS-1$

  /**
   * the <a href="#runtimeNormalization">runtime normalization factor</a>:
   * * * {@value}
   */
  public static final String RUNTIME_NORMALIZATION_FACTOR = "runtimeNormalizationFactor"; //$NON-NLS-1$

  /** the comments in the log file header */
  private static final char[][] HEADER_COMMENTS = new char[][] { //
      { '/', '/', ' ', 'T', 'h', 'i', 's', ' ', 'l', 'o', 'g', ' ', 'f',
          'i', 'l', 'e', ' ', 'h', 'a', 's', ' ', 'b', 'e', 'e', 'n', ' ',
          'g', 'e', 'n', 'e', 'r', 'a', 't', 'e', 'd', ' ', 'w', 'i', 't',
          'h', ' ', 't', 'h', 'e', ' ', '"', 'T', 'S', 'P', ' ', 'S', 'u',
          'i', 't', 'e', '"', ' ', 'b', 'e', 'n', 'c', 'h', 'm', 'a', 'r',
          'k', ' ', 'f', 'a', 'c', 'i', 'l', 'i', 't', 'y', ' ', 'f', 'r',
          'o', 'm', ' ', 'h', 't', 't', 'p', ':', '/', '/', 'w', 'w', 'w',
          '.', 'l', 'o', 'g', 'i', 's', 't', 'i', 'c', 'P', 'l', 'a', 'n',
          'n', 'i', 'n', 'g', '.', 'o', 'r', 'g', '/', 't', 's', 'p' }, //
      { '/', '/', ' ', 'P', 'r', 'o', 'j', 'e', 'c', 't', ' ', 'v', 'e',
          'r', 's', 'i', 'o', 'n', ' ', '0', '.', '9', '.', '8', ',', ' ',
          'b', 'u', 'i', 'l', 't', ' ', 'o', 'n', ' ', '2', '0', '1', '5',
          '-', '0', '4', '-', '2', '1', ' ', '0', '6', ':', '4', '5', ':',
          '0', '2', ' ', 'G', 'M', 'T', '+', '0', '8', '0', '0' }, //
      ObjectiveFunction.EMPTY_LINE, //
      ObjectiveFunction.EMPTY_LINE, //
      ObjectiveFunction.LOG_DATA_SECTION.toCharArray(), //
      { '/', '/', ' ', 'F', 'o', 'r', 'm', 'a', 't', ':', ' ', 'F', 'E',
          ' ', 'D', 'E', ' ', 't', 'i', 'm', 'e', ' ', 'n', 't', 'i', 'm',
          'e', ' ', 'b', 'e', 's', 't', '_', 'F', ' ', 'b', 'e', 's', 't',
          '_', 'F', '_', 'f', 'r', 'a', 'c', ' ', 't', 'y', 'p', 'e' }, //
      { '/', '/', ' ', 'W', 'h', 'e', 'r', 'e', ':', ' ', '"', 'F', 'E',
          '"', ' ', 'i', 's', ' ', 't', 'h', 'e', ' ', 'n', 'u', 'm', 'b',
          'e', 'r', ' ', 'o', 'f', ' ', 'f', 'u', 'l', 'l', 'y', ' ', 'c',
          'o', 'n', 's', 't', 'r', 'u', 'c', 't', 'e', 'd', ' ', 'c', 'a',
          'n', 'd', 'i', 'd', 'a', 't', 'e', ' ', 's', 'o', 'l', 'u', 't',
          'i', 'o', 'n', 's', '.' }, //
      { '/', '/', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '"', 'D', 'E',
          '"', ' ', 'i', 's', ' ', 't', 'h', 'e', ' ', 'n', 'u', 'm', 'b',
          'e', 'r', ' ', 'o', 'f', ' ', 'c', 'a', 'l', 'l', 's', ' ', 't',
          'o', ' ', 't', 'h', 'e', ' ', 'd', 'i', 's', 't', 'a', 'n', 'c',
          'e', ' ', 'f', 'u', 'n', 'c', 't', 'i', 'o', 'n', ' ', '(', 'b',
          'e', 't', 'w', 'e', 'e', 'n', ' ', 't', 'w', 'o', ' ', 'n', 'o',
          'd', 'e', 's', ')', '.' }, //
      { '/', '/', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '"', 't', 'i',
          'm', 'e', '"', ' ', 'i', 's', ' ', 't', 'h', 'e', ' ', 'r', 'u',
          'n', 't', 'i', 'm', 'e', ' ', 'i', 'n', ' ', 'm', 'i', 'l', 'l',
          'i', 's', 'e', 'c', 'o', 'n', 'd', 's', ' ', 't', 'h', 'a', 't',
          ' ', 'h', 'a', 's', ' ', 'p', 'a', 's', 's', 'e', 'd', ' ', 'u',
          'n', 't', 'i', 'l', ' ', 't', 'h', 'e', ' ', 'l', 'o', 'g', ' ',
          'p', 'o', 'i', 'n', 't', ' ', 'w', 'a', 's', ' ', 'r', 'e', 'a',
          'c', 'h', 'e', 'd', '.' }, //
      ("//        \"ntime\" is the normalized runtime that has passed until the log point was reached, i.e., time/FACT where FACT is the runtime that a standardized algorithm (double-ended nearest neighbor heuristic) needs to solve the same problem (see field " + //$NON-NLS-1$
          ObjectiveFunction.RUNTIME_NORMALIZATION_FACTOR
          + " in section " + ObjectiveFunction.SYSTEM_DATA_SECTION + ')').toCharArray(),//$NON-NLS-1$
      { '/', '/', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '"', 'b', 'e',
          's', 't', '_', 'F', '"', ' ', 'i', 's', ' ', 't', 'h', 'e', ' ',
          'o', 'b', 'j', 'e', 'c', 't', 'i', 'v', 'e', ' ', 'v', 'a', 'l',
          'u', 'e', ' ', 'o', 'f', ' ', 't', 'h', 'e', ' ', 'b', 'e', 's',
          't', ' ', 'c', 'a', 'n', 'd', 'i', 'd', 'a', 't', 'e', ' ', 's',
          'o', 'l', 'u', 't', 'i', 'o', 'n', ' ', 'd', 'i', 's', 'c', 'o',
          'v', 'e', 'r', 'e', 'd', ' ', 'u', 'n', 't', 'i', 'l', ' ', 't',
          'h', 'a', 't', ' ', 'p', 'o', 'i', 'n', 't', '.' }, //
      { '/', '/', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '"', 'b', 'e',
          's', 't', '_', 'F', '_', 'f', 'r', 'a', 'c', '"', ' ', 'e', 'q',
          'u', 'a', 'l', 's', ' ', 't', 'o', ' ', '(', 'b', 'e', 's', 't',
          '_', 'F', '-', 'o', 'p', 't', 'i', 'm', 'u', 'm', '_', 'F', ')',
          '/', 'o', 'p', 't', 'i', 'm', 'u', 'm', '_', 'F', ',', ' ', 'w',
          'h', 'e', 'r', 'e', ' ', '"', 'o', 'p', 't', 'i', 'm', 'u', 'm',
          '_', 'F', '"', ' ', 'i', 's', ' ', 't', 'h', 'e', ' ', 'o', 'b',
          'j', 'e', 'c', 't', 'i', 'v', 'e', ' ', 'v', 'a', 'l', 'u', 'e',
          ' ', 'o', 'f', ' ', 't', 'h', 'e', ' ', 'k', 'n', 'o', 'w', 'n',
          ' ', 'g', 'l', 'o', 'b', 'a', 'l', ' ', 'o', 'p', 't', 'i', 'm',
          'u', 'm', '.' }, //
      { '/', '/', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '"', 't', 'y',
          'p', 'e', '"', ' ', 'i', 's', ' ', 't', 'h', 'e', ' ', 'c', 'a',
          'u', 's', 'e', ' ', 'o', 'f', ' ', 't', 'h', 'e', ' ', 'l', 'o',
          'g', ' ', 'p', 'o', 'i', 'n', 't', ' ', 'c', 'r', 'e', 'a', 't',
          'i', 'o', 'n', ',', ' ', 'a', ' ', 'c', 'o', 'm', 'b', 'i', 'n',
          'a', 't', 'i', 'o', 'n', ' ', 'o', 'f', ' ', 'o', '=', 'q', 'u',
          'a', 'l', 'i', 't', 'y', ' ', 't', 'h', 'r', 'e', 's', 'h', 'o',
          'l', 'd', ' ', 'r', 'e', 'a', 'c', 'h', 'e', 'd', ',', ' ', 'f',
          '=', 'f', 'e', ' ', 't', 'h', 'r', 'e', 's', 'h', 'o', 'l', 'd',
          ' ', 'r', 'e', 'a', 'c', 'h', 'e', 'd', ',', ' ', 'd', '=', 'd',
          'e', ' ', 't', 'h', 'r', 'e', 's', 'h', 'o', 'l', 'd', ' ', 'r',
          'e', 'a', 'c', 'h', 'e', 'd', ',', ' ', 'i', '=', 'e', 'n', 'd',
          ' ', 'o', 'f', ' ', 'i', 'n', 'i', 't', 'i', 'a', 'l', 'i', 'z',
          'a', 't', 'i', 'o', 'n', ',', ' ', 'e', '=', 'e', 'n', 'd', ' ',
          'o', 'f', ' ', 'r', 'u', 'n', '.' }, //
      ObjectiveFunction.EMPTY_LINE };

  /** the string indicating that the best solution follows: {@value} */
  public static final String BEST_SOLUTION = "BEST_SOLUTION";//$NON-NLS-1$

  /** the comments to be printed before the best solution */
  private static final char[][] SOLUTION_COMMENTS = new char[][] {
      ObjectiveFunction.EMPTY_LINE, //
      ObjectiveFunction.EMPTY_LINE, //
      { '/', '/', ' ', 'T', 'h', 'e', ' ', 'b', 'e', 's', 't', ' ', 's',
          'o', 'l', 'u', 't', 'i', 'o', 'n', ' ', 'd', 'i', 's', 'c', 'o',
          'v', 'e', 'r', 'e', 'd', ' ', 'b', 'y', ' ', 't', 'h', 'e', ' ',
          'a', 'l', 'g', 'o', 'r', 'i', 't', 'h', 'm', ' ', '(', 'o', 'r',
          ' ', 'o', 'n', 'e', ' ', 'o', 'f', ' ', 't', 'h', 'e', 'm', ',',
          ' ', 'i', 'f', ' ', 'm', 'u', 'l', 't', 'i', 'p', 'l', 'e', ' ',
          's', 'o', 'l', 'u', 't', 'i', 'o', 'n', 's', ' ', 'o', 'f', ' ',
          't', 'h', 'e', ' ', 's', 'a', 'm', 'e', ' ', 'q', 'u', 'a', 'l',
          'i', 't', 'y', ' ', 'h', 'a', 'v', 'e', ' ', 'b', 'e', 'e', 'n',
          ' ', 'd', 'i', 's', 'c', 'o', 'v', 'e', 'r', 'e', 'd', ')', '.' }, };

  /**
   * the identifier of the section in the log files which holds the
   * algorithm information
   */
  public static final String ALGORITHM_DATA_SECTION = "ALGORITHM_DATA_SECTION"; //$NON-NLS-1$

  /** the string indicating that algorithm class follows: {@value} */
  public static final String ALGORITHM_CLASS = "algorithmClass"; //$NON-NLS-1$

  /** the string indicating that the algorithm name follows: {@value} */
  public static final String ALGORITHM_NAME = "algorithmName"; //$NON-NLS-1$

  /** the comments to be printed before the algorithm information section */
  private static final char[][] ALGORITHM_INFORMATION = new char[][] { //
      ObjectiveFunction.EMPTY_LINE, //
      ObjectiveFunction.EMPTY_LINE, //
      { '/', '/', ' ', 'T', 'h', 'e', ' ', 'c', 'o', 'n', 'f', 'i', 'g',
          'u', 'r', 'a', 't', 'i', 'o', 'n', ' ', 'o', 'f', ' ', 't', 'h',
          'e', ' ', 'a', 'l', 'g', 'o', 'r', 'i', 't', 'h', 'm', ' ', 't',
          'h', 'a', 't', ' ', 'w', 'a', 's', ' ', 'u', 's', 'e', 'd', ' ',
          't', 'o', ' ', 'o', 'b', 't', 'a', 'i', 'n', ' ', 't', 'h', 'e',
          's', 'e', ' ', 'r', 'e', 's', 'u', 'l', 't', 's', '.' }, //
      ObjectiveFunction.ALGORITHM_DATA_SECTION.toCharArray(), };

  /**
   * the identifier beginning the section in the log files which holds the
   * infos about the deterministic initializer: {@value}
   */
  public static final String DETERMINISTIC_INITIALIZATION_SECTION = "DETERMINISTIC_INITIALIZATION_SECTION"; //$NON-NLS-1$

  /**
   * the string indicating that the class of the initialization algorithm
   * follows: {@value}
   */
  public static final String INITIALIZER_CLASS = "initializerClass"; //$NON-NLS-1$
  /**
   * the string indicating that the name of the initialization algorithm
   * follows: {@value}
   */
  public static final String INITIALIZER_NAME = "initializerName"; //$NON-NLS-1$

  /** the comments right before the initializer information section */
  private static final char[][] INITIALIZER_INFORMATION = new char[][] { //
      ObjectiveFunction.EMPTY_LINE, //
      ObjectiveFunction.EMPTY_LINE, //
      { '/', '/', ' ', 'T', 'h', 'e', ' ', 'T', 'S', 'P', ' ', 'L', 'i',
          'b', 'r', 'a', 'r', 'y', ' ', 'f', 'r', 'a', 'm', 'e', 'w', 'o',
          'r', 'k', ' ', 'a', 'l', 'l', 'o', 'w', 's', ' ', 'y', 'o', 'u',
          ' ', 't', 'o', ' ', 'u', 's', 'e', ' ', 'o', 'n', 'e', ' ', 'd',
          'e', 't', 'e', 'r', 'm', 'i', 'n', 'i', 's', 't', 'i', 'c', ' ',
          'i', 'n', 'i', 't', 'i', 'a', 'l', 'i', 'z', 'a', 't', 'i', 'o',
          'n', ' ', 'p', 'r', 'o', 'c', 'e', 'd', 'u', 'r', 'e', ' ', 'b',
          'e', 'f', 'o', 'r', 'e', ' ', 'a', 'p', 'p', 'l', 'y', 'i', 'n',
          'g', ' ', 't', 'h', 'e', ' ', '"', 'r', 'e', 'a', 'l', '"', ' ',
          'a', 'l', 'g', 'o', 'r', 'i', 't', 'h', 'm', ' ', '(', 'w', 'h',
          'o', 's', 'e', ' ', 'c', 'o', 'n', 'f', 'i', 'g', 'u', 'r', 'a',
          't', 'i', 'o', 'n', ' ', 'i', 's', ' ', 'd', 'e', 's', 'c', 'r',
          'i', 'b', 'e', 'd', ' ', 'a', 'b', 'o', 'v', 'e', ')', ' ', 't',
          'o', ' ', 't', 'h', 'e', ' ', 'p', 'r', 'o', 'b', 'l', 'e', 'm',
          '.' }, //
      { '/', '/', ' ', 'S', 'u', 'c', 'h', ' ', 'a', ' ', 'p', 'r', 'o',
          'c', 'e', 'd', 'u', 'r', 'e', ' ', 'i', 's', ' ', 'd', 'e', 't',
          'e', 'r', 'm', 'i', 'n', 'i', 's', 't', 'i', 'c', ',', ' ', 'i',
          '.', 'e', '.', ',', ' ', 'a', 'l', 'w', 'a', 'y', 's', ' ', 'h',
          'a', 's', ' ', 't', 'h', 'e', ' ', 's', 'a', 'm', 'e', ' ', 'r',
          'e', 's', 'u', 'l', 't', ' ', 'a', 'n', 'd', ' ', 't', 'h', 'e',
          'r', 'e', 'f', 'o', 'r', 'e', ' ', 'n', 'e', 'e', 'd', 's', ' ',
          't', 'o', ' ', 'o', 'n', 'l', 'y', ' ', 'b', 'e', ' ', 'p', 'e',
          'r', 'f', 'o', 'r', 'm', 'e', 'd', ' ', 'o', 'n', 'c', 'e', ' ',
          'f', 'o', 'r', ' ', 'a', ' ', 'b', 'e', 'n', 'c', 'h', 'm', 'a',
          'r', 'k', ' ', 'i', 'n', 's', 't', 'a', 'n', 'c', 'e', '.' }, //
      { '/', '/', ' ', 'I', 't', 's', ' ', 'c', 'o', 'r', 'r', 'e', 's',
          'p', 'o', 'n', 'd', 'i', 'n', 'g', ' ', 'l', 'o', 'g', ' ', 'p',
          'o', 'i', 'n', 't', 's', ' ', 'a', 'r', 'e', ' ', 't', 'h', 'e',
          'n', ' ', 'c', 'a', 'c', 'h', 'e', 'd', ' ', 'a', 'n', 'd', ' ',
          'a', 'u', 't', 'o', 'm', 'a', 't', 'i', 'c', 'a', 'l', 'l', 'y',
          ' ', 'p', 'r', 'e', '-', 'p', 'e', 'n', 'd', 'e', 'd', ' ', 't',
          'o', ' ', 't', 'h', 'e', ' ', 'l', 'o', 'g', 'g', 'i', 'n', 'g',
          ' ', 'i', 'n', 'f', 'o', 'r', 'm', 'a', 't', 'i', 'o', 'n', ' ',
          'c', 'r', 'e', 'a', 't', 'e', 'd', ' ', 'b', 'y', ' ', 'e', 'a',
          'c', 'h', ' ', 'r', 'u', 'n', ' ', 'o', 'f', ' ', 't', 'h', 'e',
          ' ', '"', 'r', 'e', 'a', 'l', '"', ' ', 'a', 'l', 'g', 'o', 'r',
          'i', 't', 'h', 'm', '.' }, //
      { '/', '/', ' ', 'I', 't', 's', ' ', 'r', 'e', 's', 'u', 'l', 't',
          's', ' ', 'a', 'r', 'e', ' ', 'a', 'v', 'a', 'i', 'l', 'a', 'b',
          'l', 'e', ' ', 't', 'o', ' ', 't', 'h', 'e', ' ', '"', 'r', 'e',
          'a', 'l', '"', ' ', 'a', 'l', 'g', 'o', 'r', 'i', 't', 'h', 'm',
          '.' }, //
      { '/', '/', ' ', 'I', 't', 's', ' ', 'r', 'u', 'n', 't', 'i', 'm',
          'e', ',', ' ', 'a', 's', ' ', 'w', 'e', 'l', 'l', ' ', 'a', 's',
          ' ', 'i', 't', 's', ' ', 'c', 'o', 'n', 's', 'u', 'm', 'e', 'd',
          ' ', 'D', 'E', 's', ' ', 'a', 'n', 'd', ' ', 'F', 'E', 's', ',',
          ' ', 'a', 'r', 'e', ' ', 'a', 'd', 'd', 'e', 'd', ' ', 't', 'o',
          ' ', 't', 'h', 'e', ' ', 't', 'o', 't', 'a', 'l', ' ', 'r', 'u',
          'n', 't', 'i', 'm', 'e', ' ', '(', 'D', 'E', 's', ',', ' ', 'F',
          'E', 's', ')', ' ', 'o', 'f', ' ', 't', 'h', 'e', ' ', '"', 'r',
          'e', 'a', 'l', '"', ' ', 'a', 'l', 'g', 'o', 'r', 'i', 't', 'h',
          'm', '.' }, //
      { '/', '/', ' ', 'H', 'e', 'r', 'e', ' ', 'w', 'e', ' ', 'p', 'r',
          'o', 'v', 'i', 'd', 'e', ' ', 'i', 'n', 'f', 'o', 'r', 'm', 'a',
          't', 'i', 'o', 'n', ' ', 'a', 'b', 'o', 'u', 't', ' ', 't', 'h',
          'i', 's', ' ', 'i', 'n', 'i', 't', 'i', 'a', 'l', 'i', 'z', 'a',
          't', 'i', 'o', 'n', ' ', 'p', 'h', 'a', 's', 'e', '.' }, //
      ObjectiveFunction.DETERMINISTIC_INITIALIZATION_SECTION.toCharArray(), };

  /** the string used to end sections: {@value} */
  public static final String SECTION_END = "SECTION_END";//$NON-NLS-1$

  /** the first string at the log file start */
  private static final char[] LOG_FILE_START_A = { '/', '/', ' ', 'T',
      'h', 'i', 's', ' ', 'f', 'i', 'l', 'e', ' ', 'r', 'e', 'p', 'r',
      'e', 's', 'e', 'n', 't', 's', ' ', 'a', ' ', 'r', 'u', 'n', ' ',
      'o', 'f', ' ', 't', 'h', 'e', ' ' };

  /** the second string at the log file start */
  private static final char[] LOG_FILE_START_A2 = { ' ', 'i', 'n', 'i',
      't', 'i', 'a', 'l', 'i', 'z', 'e', 'd', ' ', 'w', 'i', 't', 'h',
      ' ', 't', 'h', 'e', ' ' };

  /** the third string at the log file start */
  private static final char[] LOG_FILE_START_B = { ' ', 'a', 'p', 'p',
      'l', 'i', 'e', 'd', ' ', 't', 'o', ' ', 't', 'h', 'e', ' ' };

  /** another string at the the log file start */
  private static final char[] LOG_FILE_START_X = { '/', '/', ' ', 'T',
      'h', 'i', 's', ' ', 'f', 'i', 'l', 'e', ' ', 'r', 'e', 'p', 'r',
      'e', 's', 'e', 'n', 't', 's', ' ', 'a', ' ', 'r', 'u', 'n', ' ',
      'o', 'f', ' ', 'a', ' ', 's', 'o', 'l', 'v', 'e', 'r', ' ', 'f',
      'o', 'r', ' ', 't', 'h', 'e', ' ' };

  /** another string at the the log file start */
  private static final char[] LOG_FILE_START_C = { ' ', 'b', 'e', 'n',
      'c', 'h', 'm', 'a', 'r', 'k', ' ', 'i', 'n', 's', 't', 'a', 'n',
      'c', 'e', ' ', 'o', 'f', ' ' };

  /** another string at the the log file start */
  private static final char[] LOG_FILE_START_DA = { 't', 'h', 'e', ' ',
      's', 'y', 'm', 'm', 'e', 't', 'r', 'i', 'c', ' ', 'T', 'r', 'a',
      'v', 'e', 'l', 'i', 'n', 'g', ' ', 'S', 'a', 'l', 'e', 's', 'm',
      'a', 'n', ' ', 'P', 'r', 'o', 'b', 'l', 'e', 'm', ' ', '(', 'T',
      'S', 'P', ')', '.' };

  /** another string at the the log file start */
  private static final char[] LOG_FILE_START_DB = { 't', 'h', 'e', ' ',
      'a', 's', 'y', 'm', 'm', 'e', 't', 'r', 'i', 'c', ' ', 'T', 'r',
      'a', 'v', 'e', 'l', 'i', 'n', 'g', ' ', 'S', 'a', 'l', 'e', 's',
      'm', 'a', 'n', ' ', 'P', 'r', 'o', 'b', 'l', 'e', 'm', ' ', '(',
      'A', 'T', 'S', 'P', ')', '.' };

  /**
   * the string indicating that the solution of the initialization
   * procedure follows: {@value}
   */
  public static final String INITIALIZATION_SOLUTION = "INITIALIZATION_SOLUTION";//$NON-NLS-1$

  /** the FEs spent for initialization: {@value} */
  public static final String INITIALIZATION_FES = "initFEs";//$NON-NLS-1$

  /** the DEs spent for initialization: {@value} */
  public static final String INITIALIZATION_DES = "initDES";//$NON-NLS-1$

  /** the best solution found in initialization: {@value} */
  public static final String INITIALIZATION_F = "initBestF";//$NON-NLS-1$

  /** the runtime in milli seconds spent for initialization: {@value} */
  public static final String INITIALIZATION_RUNTIME = "initRunTime";//$NON-NLS-1$

  /** the random seed used for initialization: {@value} */
  public static final String INITIALIZATION_RAND_SEED = "initRandSeed";//$NON-NLS-1$

  /** the start time of the initialization routine: {@value} */
  public static final String INITIALIZATION_START_TIME = "initStartTime";//$NON-NLS-1$

  /** the end time of the initialization routine: {@value} */
  public static final String INITIALIZATION_END_TIME = "initEndTime";//$NON-NLS-1$

  /**
   * the identifier beginning the section in the log files which holds the
   * infos about the benchmark and setup: {@value}
   */
  public static final String BENCHMARK_INFORMATION_SECTION = "BENCHMARK_INFORMATION_SECTION"; //$NON-NLS-1$

  /** the comments at the benchmark information section */
  private static final char[][] BENCHMARK_INFORMATION = new char[][] { //
      ObjectiveFunction.EMPTY_LINE, //
      ObjectiveFunction.EMPTY_LINE, //
      { '/', '/', ' ', 'S', 'o', 'm', 'e', ' ', 'g', 'e', 'n', 'e', 'r',
          'a', 'l', ' ', 'i', 'n', 'f', 'o', 'r', 'm', 'a', 't', 'i', 'o',
          'n', ' ', 'a', 'b', 'o', 'u', 't', ' ', 't', 'h', 'e', ' ', 'b',
          'e', 'n', 'c', 'h', 'm', 'a', 'r', 'k', ' ', 'i', 'n', 's', 't',
          'a', 'n', 'c', 'e', ' ', 'a', 'n', 'd', ' ', 'e', 'x', 'p', 'e',
          'r', 'i', 'm', 'e', 'n', 't', ' ', 's', 'e', 't', 'u', 'p', '.' }, //
      ObjectiveFunction.BENCHMARK_INFORMATION_SECTION.toCharArray(), };

  /** the random seed: {@value} */
  public static final String RAND_SEED = "randSeed";//$NON-NLS-1$
  /** the start date: {@value} */
  public static final String START_DATE = "startTime";//$NON-NLS-1$
  /** the end date: {@value} */
  public static final String END_DATE = "endTime";//$NON-NLS-1$
  /** maximum runtime: {@value} */
  public static final String RUN_TIME = "runTime";//$NON-NLS-1$

  /** a prefix for the system properties: {@value} */
  public static final String SYS_PROP_PREFIX = "sysProp_";//$NON-NLS-1$

  /** a prefix for the environment variables: {@value} */
  public static final String ENV_VAR_PREFIX = "envVar_";//$NON-NLS-1$

  /** the comments before the system information section */
  private static final char[][] SYSTEM_INFORMATION = new char[][] { //
      ObjectiveFunction.EMPTY_LINE, //
      ObjectiveFunction.EMPTY_LINE, //
      { '/', '/', ' ', 'I', 'n', ' ', 't', 'h', 'i', 's', ' ', 's', 'e',
          'c', 't', 'i', 'o', 'n', ',', ' ', 'y', 'o', 'u', ' ', 'c', 'a',
          'n', ' ', 'f', 'i', 'n', 'd', ' ', 'g', 'e', 'n', 'e', 'r', 'a',
          'l', ' ', 'i', 'n', 'f', 'o', 'r', 'm', 'a', 't', 'i', 'o', 'n',
          ' ', 'a', 'b', 'o', 'u', 't', ' ', 't', 'h', 'e', ' ', 's', 'y',
          's', 't', 'e', 'm', ' ', 'o', 'n', ' ', 'w', 'h', 'i', 'c', 'h',
          ' ', 't', 'h', 'i', 's', ' ', 'l', 'o', 'g', ' ', 'f', 'i', 'l',
          'e', ' ', 'w', 'a', 's', ' ', 'g', 'e', 'n', 'e', 'r', 'a', 't',
          'e', 'd', ',', ' ', 'o', 'n', ' ', 'w', 'h', 'i', 'c', 'h', ' ',
          't', 'h', 'e', ' ', 'r', 'u', 'n', ' ', 'w', 'a', 's', ' ', 'p',
          'e', 'r', 'f', 'o', 'r', 'm', 'e', 'd', '.' }, //
      ObjectiveFunction.SYSTEM_DATA_SECTION.toCharArray(), };

  /** the system information properties to be copied to the logs */
  private static final String[] SYS_INFOS = new String[] {
      "file.encoding",//$NON-NLS-1$

      "java.class.path",//$NON-NLS-1$
      "java.class.version",//$NON-NLS-1$

      "java.version",//$NON-NLS-1$
      "java.vendor",//$NON-NLS-1$
      "java.vendor.url",//$NON-NLS-1$

      "java.home",//$NON-NLS-1$

      "java.library.path",//$NON-NLS-1$
      "java.runtime.name",//$NON-NLS-1$
      "java.runtime.version",//$NON-NLS-1$

      "java.specification.name",//$NON-NLS-1$
      "java.specification.vendor",//$NON-NLS-1$
      "java.specification.version",//$NON-NLS-1$

      "java.vm.name",//$NON-NLS-1$
      "java.vm.version",//$NON-NLS-1$
      "java.vm.vendor",//$NON-NLS-1$
      "java.vm.info",//$NON-NLS-1$

      "java.vm.specification.name",//$NON-NLS-1$
      "java.vm.specification.version",//$NON-NLS-1$
      "java.vm.specification.vendor",//$NON-NLS-1$

      "java.compile",//$NON-NLS-1$
      "java.compiler",//$NON-NLS-1$

      "sun.arch.data.model",//$NON-NLS-1$
      "sun.cpu.endian",//$NON-NLS-1$
      "sun.cpu.isalist",//$NON-NLS-1$
      "sun.desktop",//$NON-NLS-1$
      "sun.io.unicode.encoding",//$NON-NLS-1$
      "sun.java.launcher",//$NON-NLS-1$
      "sun.jnu.encoding",//$NON-NLS-1$
      "sun.management.compiler",//$NON-NLS-1$

      "os.arch",//$NON-NLS-1$
      "os.name",//$NON-NLS-1$
      "os.version",//$NON-NLS-1$

      "user.country",//$NON-NLS-1$
      "user.language",//$NON-NLS-1$
  };

  /** the environment variables to be copied to the logs */
  private static final String[] ENV_INFOS = new String[] {
      "PROCESSOR_IDENTIFIER",//$NON-NLS-1$
      "PROCESSOR_ARCHITECTURE",//$NON-NLS-1$
      "PROCESSOR_LEVEL",//$NON-NLS-1$
      "PROCESSOR_REVISION",//$NON-NLS-1$
      "ARCHS",//$NON-NLS-1$
      "ARCHS_STANDARD_32_64_BIT",//$NON-NLS-1$
      "ARCHS_STANDARD_32_BIT",//$NON-NLS-1$
      "ARCHS_UNIVERSAL_IPHONE_OS",//$NON-NLS-1$
      "AVAILABLE_PLATFORMS",//$NON-NLS-1$
      "NATIVE_ARCH",//$NON-NLS-1$
      "NATIVE_ARCH_32_BIT",//$NON-NLS-1$
      "NATIVE_ARCH_64_BIT",//$NON-NLS-1$
      "NATIVE_ARCH_ACTUAL",//$NON-NLS-1$
      "PLATFORM_NAME",//$NON-NLS-1$
      "PLATFORM_PREFERRED_ARCH",//$NON-NLS-1$
      "PLATFORM_PRODUCT_BUILD_VERSION",//$NON-NLS-1$

      "OS",//$NON-NLS-1$
      "OSTYPE",//$NON-NLS-1$
      "MACHTYPE",//$NON-NLS-1$
      "MACH_O_TYPE",//$NON-NLS-1$
      "MAC_OS_X_PRODUCT_BUILD_VERSION",//$NON-NLS-1$
      "MAC_OS_X_VERSION_ACTUAL",//$NON-NLS-1$
      "MAC_OS_X_VERSION_MAJOR",//$NON-NLS-1$
      "MAC_OS_X_VERSION_MINOR",//$NON-NLS-1$
      "HOSTTYPE",//$NON-NLS-1$

      "SYSTEMROOT",//$NON-NLS-1$
      "WINDIR",//$NON-NLS-1$
      "windir",//$NON-NLS-1$

      "SHELL",//$NON-NLS-1$
      "BASH_VERSION",//$NON-NLS-1$
      "POSIXLY_CORRECT",//$NON-NLS-1$
      "THREADLIB",//$NON-NLS-1$

      "LANG",//$NON-NLS-1$

  };

  /** the number of processors: {@value} */
  public static final String PROCESSORS = "availableProcessors";//$NON-NLS-1$

  /** the amount of free memory: {@value} */
  public static final String FREE_MEMORY = "freeMemory";//$NON-NLS-1$

  /** the max memory: {@value} */
  public static final String MAX_MEMORY = "maxMemory";//$NON-NLS-1$

  /** the total memory: {@value} */
  public static final String TOTAL_MEMORY = "totalMemory";//$NON-NLS-1$

  /**
   * the beginning of the section with information about the algorithm
   * creator and experimentor: {@value}
   */
  public static final String CREATOR_INFORMATION_SECTION = "CREATOR_INFORMATION_SECTION";//$NON-NLS-1$

  /** the comments right before the information regarding the creator */
  private static final char[][] CREATOR_INFORMATION = new char[][] { //
      ObjectiveFunction.EMPTY_LINE, //
      ObjectiveFunction.EMPTY_LINE, //
      { '/', '/', ' ', 'I', 'n', ' ', 't', 'h', 'i', 's', ' ', 's', 'e',
          'c', 't', 'i', 'o', 'n', ',', ' ', 'y', 'o', 'u', ' ', 'c', 'a',
          'n', ' ', 'f', 'i', 'n', 'd', ' ', 'g', 'e', 'n', 'e', 'r', 'a',
          'l', ' ', 'i', 'n', 'f', 'o', 'r', 'm', 'a', 't', 'i', 'o', 'n',
          ' ', 'a', 'b', 'o', 'u', 't', ' ', 't', 'h', 'e', ' ', 'r', 'e',
          's', 'e', 'a', 'r', 'c', 'h', 'e', 'r', ' ', 'a', 'n', 'd', '/',
          'o', 'r', ' ', 'g', 'r', 'o', 'u', 'p', ' ', 'w', 'h', 'o', ' ',
          'c', 'r', 'e', 'a', 't', 'e', 'd', ' ', 't', 'h', 'e', ' ', 'l',
          'o', 'g', ' ', 'f', 'i', 'l', 'e', '.' }, //
      ObjectiveFunction.CREATOR_INFORMATION_SECTION.toCharArray(), };

  /**
   * this section holds an exception that might have occured during an
   * initialization procedure: {@value}
   */
  public static final String INITIALIZATION_EXCEPTION_SECTION = "EXCEPTION_DURING_INITIALIZATION_SECTION"; //$NON-NLS-1$

  /** the comments before the initialization exception section */
  private static final char[][] INITIALIZATION_EXCEPTION = new char[][] { //
      ObjectiveFunction.EMPTY_LINE, //
      ObjectiveFunction.EMPTY_LINE, //
      { '/', '/', ' ', 'T', 'h', 'i', 's', ' ', 's', 'e', 'c', 't', 'i',
          'o', 'n', ' ', 'w', 'a', 's', ' ', 'a', 'd', 'd', 'e', 'd', ' ',
          's', 'i', 'n', 'c', 'e', ' ', 'a', 'n', ' ', 'e', 'x', 'c', 'e',
          'p', 't', 'i', 'o', 'n', ' ', 'w', 'a', 's', ' ', 'c', 'a', 'u',
          'g', 'h', 't', ' ', 'd', 'u', 'r', 'i', 'n', 'g', ' ', 't', 'h',
          'e', ' ', 'i', 'n', 'i', 't', 'i', 'a', 'l', 'i', 'z', 'a', 't',
          'i', 'o', 'n', ' ', 'p', 'r', 'o', 'c', 'e', 'd', 'u', 'r', 'e',
          '.' }, //
      { '/', '/', ' ', 'W', 'A', 'R', 'N', 'I', 'N', 'G', ':', ' ', 'I',
          'n', 'i', 't', 'i', 'a', 'l', 'i', 'z', 'a', 't', 'i', 'o', 'n',
          ' ', 'd', 'i', 'd', ' ', 'N', 'O', 'T', ' ', 'c', 'o', 'm', 'p',
          'l', 'e', 't', 'e', ' ', 's', 'u', 'c', 'c', 'e', 's', 's', 'f',
          'u', 'l', 'l', 'y', '!' }, //
      { '/', '/', ' ', 'H', 'e', 'r', 'e', ' ', 'y', 'o', 'u', ' ', 'c',
          'a', 'n', ' ', 'f', 'i', 'n', 'd', ' ', 'i', 'n', 'f', 'o', 'r',
          'm', 'a', 't', 'i', 'o', 'n', ' ', 'a', 'b', 'o', 'u', 't', ' ',
          't', 'h', 'a', 't', ' ', 'e', 'x', 'c', 'e', 'p', 't', 'i', 'o',
          'n', '.' }, //
      ObjectiveFunction.INITIALIZATION_EXCEPTION_SECTION.toCharArray(), };

  /**
   * this section holds an exception that might have occured during an the
   * algorithm run procedure: {@value}
   */
  public static final String ALGORITHM_EXCEPTION_SECTION = "EXCEPTION_DURING_ALGORITHM_SECTION"; //$NON-NLS-1$

  /** the comments before the algorithm exception section */
  private static final char[][] ALGORITHM_EXCEPTION = new char[][] { //
      ObjectiveFunction.EMPTY_LINE, //
      ObjectiveFunction.EMPTY_LINE, //
      { '/', '/', ' ', 'T', 'h', 'i', 's', ' ', 's', 'e', 'c', 't', 'i',
          'o', 'n', ' ', 'w', 'a', 's', ' ', 'a', 'd', 'd', 'e', 'd', ' ',
          's', 'i', 'n', 'c', 'e', ' ', 'a', 'n', ' ', 'e', 'x', 'c', 'e',
          'p', 't', 'i', 'o', 'n', ' ', 'w', 'a', 's', ' ', 'c', 'a', 'u',
          'g', 'h', 't', ' ', 'd', 'u', 'r', 'i', 'n', 'g', ' ', 't', 'h',
          'e', ' ', 'a', 'l', 'g', 'o', 'r', 'i', 't', 'h', 'm', ' ', 'r',
          'u', 'n', '.' }, //
      { '/', '/', ' ', 'W', 'A', 'R', 'N', 'I', 'N', 'G', ':', ' ', 'T',
          'h', 'e', ' ', 'a', 'l', 'g', 'o', 'r', 'i', 't', 'h', 'm', ' ',
          'r', 'u', 'n', ' ', 'd', 'i', 'd', ' ', 'N', 'O', 'T', ' ', 'c',
          'o', 'm', 'p', 'l', 'e', 't', 'e', ' ', 's', 'u', 'c', 'c', 'e',
          's', 's', 'f', 'u', 'l', 'l', 'y', '!' }, //
      { '/', '/', ' ', 'H', 'e', 'r', 'e', ' ', 'y', 'o', 'u', ' ', 'c',
          'a', 'n', ' ', 'f', 'i', 'n', 'd', ' ', 'i', 'n', 'f', 'o', 'r',
          'm', 'a', 't', 'i', 'o', 'n', ' ', 'a', 'b', 'o', 'u', 't', ' ',
          't', 'h', 'a', 't', ' ', 'e', 'x', 'c', 'e', 'p', 't', 'i', 'o',
          'n', '.' }, //
      ObjectiveFunction.ALGORITHM_EXCEPTION_SECTION.toCharArray(), };

  /** nothing is going on: {@value} */
  private static final int STATE_NOTHING = 0;

  /** we are in a run: {@value} */
  private static final int STATE_IN_RUN = 1;

  /** we are in an initialization procedure: {@value} */
  private static final int STATE_IN_INIT = 2;

  /**
   * the log point was collected because it crossed an objective value
   * limit: * {@value}
   */
  static final int TYPE_FLAG_OBJECTIVE = 1;

  /** the log point was collected because it crossed a DE limit: {@value} */
  static final int TYPE_FLAG_DE = 2;

  /** the log point was collected because it crossed an FE limit: {@value} */
  static final int TYPE_FLAG_FE = 4;

  /**
   * the log point was collected because it was after an initialization
   * procedure: {@value}
   */
  static final int TYPE_FLAG_INIT_END = 8;

  /**
   * the log point was collected because it was at the end of the
   * algorithm: * * {@value}
   */
  static final int TYPE_FLAG_END = 16;

  /**
   * the character indicating that the log point was collected because it
   * crossed an objective value limit: {@value}
   */
  public static final char TYPE_FLAG_OBJECTIVE_CHAR = 'o';

  /**
   * the character indicating that the log point was collected because it
   * crossed a DE limit: {@value}
   */
  public static final char TYPE_FLAG_DE_CHAR = 'd';

  /**
   * the character indicating that the log point was collected because it
   * crossed an FE limit: {@value}
   */
  public static final char TYPE_FLAG_FE_CHAR = 'f';

  /**
   * the character indicating that the log point was collected because it
   * was after an initialization procedure: {@value}
   */
  public static final char TYPE_FLAG_INIT_END_CHAR = 'i';

  /**
   * the character indicating that the log point was collected because it
   * was at the end of the algorithm: {@value}
   */
  public static final char TYPE_FLAG_END_CHAR = 'e';

  /**
   * symmetric: the benchmark instance represents a
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#symmetric()
   * symmetric} TSP
   *
   * @serial a boolean value indicating whether the problem is symmetric (
   *         {@code true}) or not ({@code false})
   */
  private final boolean m_symmetric;

  /** the internal state */
  private transient int m_state;

  /**
   * the benchmark object
   *
   * @serial the non-null handle to the benchmark instance
   */
  private final Benchmark m_benchmark;

  /**
   * the maximum function evaluations (FEs), used in
   * {@link #shouldTerminate()} which becomes {@code true} once this many
   * FEs have been performed
   *
   * @serial a {@code long} value (&gt;0) indicating the maximum number of
   *         allowed function evaluations (FEs)
   */
  private final long m_maxFEs;

  /**
   * the maximum distance evaluations (DE)s, used in
   * {@link #shouldTerminate()} which becomes {@code true} once this many
   * DEs have been performed
   *
   * @serial a {@code long} value (&gt;0) indicating the maximum number of
   *         allowed distance evaluations (DEs)
   */
  private final long m_maxDEs;

  /**
   * the best possible objective value, used in {@link #shouldTerminate()}
   * which becomes {@code true} once a solution (tour) with this length is
   * found
   *
   * @serial a {@code long} value (&gt;0) holding the objective value of
   *         the globally optimal tour
   */
  private final long m_optimum;

  /**
   * the distance computer: this internal object holds the distance
   * computation module used by the objective function. The distance
   * computer could be a symmetric or asymmetric distance matrix or be
   * based on a coordinate list, depending on the size of the problem and
   * the parameter
   * {@link org.logisticPlanning.tsp.benchmarking.objective.Benchmark#m_limitDim}
   * .
   *
   * @serial the non-null instance of the distance computer
   */
  private final DistanceComputer m_dist;

  /**
   * the pre-allocated array with log points
   *
   * @serial serializable field
   */
  private final LogPoint[] m_log;

  /**
   * the current log length
   *
   * @serial the number of points in the log array
   */
  private int m_logSize;

  /**
   * the values to log: thresholds for the objective value/ tour length at
   * which log points will be taken
   *
   * @serial an array of {@code long} values
   */
  private final long[] m_valuesToLog;

  /**
   * thresholds for the FEs and DEs where log points should be taken
   *
   * @serial an array of {@code long} values
   */
  private final long[] m_FEsDEsToLog;

  /**
   * A temporary file used to tell other, concurrent instances of the
   * benchmarking environment that we are currently performing a given run
   * of the given benchmarking instance and, hence, preventing them from
   * doing the same, i.e., from performing too many runs.
   */
  private transient File m_file;

  /**
   * the next Function evaluation where a log point should be taken
   *
   * @serial a {@code long} value larger or equal to {@code 0}
   */
  private long m_nextFE;

  /**
   * the index in {@link #m_FEsDEsToLog} for the next threshold FE
   *
   * @serial an integer index larger or equal to 0
   */
  private int m_feIdx;

  /**
   * the current log point: this variable provides information about the
   * current state of the search.
   *
   * @serial a non-null instance of LogPoint
   */
  private final LogPoint m_currentLP;

  /**
   * the log point after the initialization process
   *
   * @serial an instance of log point
   */
  private final LogPoint m_initLP;

  /**
   * the log point of the last improvement
   *
   * @serial a non-null instance of LogPoint
   */
  private final LogPoint m_lastImprovementLP;

  /**
   * the index in {@link #m_FEsDEsToLog} for the next threshold DE
   *
   * @serial an integer index larger or equal to 0
   */
  private int m_deIdx;

  /**
   * the next distance evaluation where a log point should be taken
   *
   * @serial a {@code long} value larger or equal to {@code 0}
   */
  private long m_nextDE;

  /**
   * the index in {@link #m_valuesToLog} for the next threshold objective
   * value
   *
   * @serial an integer index larger or equal to 0
   */
  private int m_fIdx;

  /**
   * the next objective value where a log point should be taken
   *
   * @serial a {@code long} value larger or equal to {@code 0}
   */
  private long m_nextF;

  /**
   * should we terminate?
   *
   * @serial a {@code boolean} value indicating whether termination is
   *         necessary
   */
  volatile boolean m_terminate;

  /**
   * The seed of the random number generation {@link #m_r}.
   *
   * @serial a {@code long} holding the seed of the random number generator
   */
  private long m_randSeed;

  /**
   * the internal randomizer, seeded with {@link #m_randSeed}
   *
   * @serial a non-null instance of the random number generator
   */
  private final Randomizer m_r;

  /**
   * the start time, obtained from
   * {@link java.lang.System#currentTimeMillis()}
   *
   * @serial a {@code long} time value
   */
  private long m_startTime;

  /**
   * the end time, obtained from
   * {@link java.lang.System#currentTimeMillis()}
   *
   * @serial a {@code long} time value
   */
  long m_endTime;

  /**
   * the best candidate solution found in <a
   * href="#pathRepresentation">path representation</a>.
   *
   * @serial an integer array
   */
  private final int[] m_bestX;

  /**
   * Should we take a log point when the next FE or DE boundary is reached?
   * This is only necessary if an improvement was made.
   *
   * @serial a boolean value indicating whether or not to take a log point
   *         when reaching the next FE or DE boundary
   */
  private boolean m_shouldLog;

  /**
   * The calendar instance used to format times.
   *
   * @serial a non-null Calendar instance
   */
  private final Calendar m_calendar;

  /**
   * did the objective value improve?
   *
   * @serial a boolean becoming {@code true} if the objective value has
   *         improved
   */
  private boolean m_fimproved;

  /**
   * the algorithm object
   *
   * @serial the algorithm object instance
   */
  private NamedObject m_algorithm;

  /**
   * the (optional) deterministic initializer
   *
   * @serial the initialization algorithm object instance
   */
  private NamedObject m_initializer;

  /**
   * the log size after initialization
   *
   * @serial an integer holding the number of log points after
   *         initialization
   */
  private int m_i_logSize;

  /**
   * the function value index after initialization
   *
   * @serial an integer holding the objective value index after
   *         initialization
   */
  private int m_i_fIdx;

  /**
   * the next function value after initialization
   *
   * @serial a long with the next objective function threshold after
   *         initialization
   */
  private long m_i_nextF;

  /**
   * the de index after initialization
   *
   * @serial an int with the next de index after initialization
   */
  private int m_i_deIdx;

  /**
   * the next de after initialization
   *
   * @serial the next de after initialization
   */
  private long m_i_nextDE;

  /**
   * the FE index after initialization
   *
   * @serial the FE index after initialization
   */
  private int m_i_feIdx;

  /**
   * the next FE after initialization
   *
   * @serial the next FE after initialization
   */
  private long m_i_nextFE;

  /**
   * the best candidate solution after initialization
   *
   * @serial in int array
   */
  private final int[] m_i_bestX;

  /** the start time of the initialization process */
  private long m_i_startTime;

  /**
   * the end time of the initialization process, obtained from
   * {@link java.lang.System#currentTimeMillis()}
   *
   * @serial a {@code long} time value
   */
  private long m_i_endTime;

  /**
   * the random seed of the initialization process, obtained from
   * {@link java.lang.System#currentTimeMillis()}
   *
   * @serial a {@code long} time value
   */
  private long m_i_randSeed;

  /**
   * the time offset to be added to all runtime information, obtained from
   * {@link java.lang.System#currentTimeMillis()}
   *
   * @serial a {@code long} time value
   */
  private long m_time_offset;

  /**
   * the factor used to <a href="#timeNormalization">normalize</a> the
   * runtime measure
   *
   * @serial a positive double value
   */
  private final double m_timeNormalizationFactor;

  /**
   * the creator information record
   *
   * @serial an instance of the creator init
   */
  private CreatorInfo m_creator;

  /**
   * the initialization exception
   *
   * @serial any potential error captured during initialization
   */
  private Throwable m_exceptionDuringInit;

  /**
   * the exception that was caught during the algorithm run
   *
   * @serial any potential error captured during algorithm execution
   */
  private Throwable m_exceptionDuringRun;

  /** a linked list link */
  volatile transient ObjectiveFunction m_next;

  /**
   * create the objective function
   *
   * @param benchmark
   *          the benchmark instance to which this objective function
   *          belongs
   */
  ObjectiveFunction(final Benchmark benchmark) {
    super(benchmark.m_instance.n());

    int i;

    this.m_symmetric = benchmark.m_instance.symmetric();

    // setup benchmark related data
    this.m_benchmark = benchmark;
    this.m_maxDEs = benchmark.m_maxDEs;
    this.m_maxFEs = benchmark.m_maxFEs;
    this.m_optimum = benchmark.m_instance.optimum();
    this.m_dist = benchmark.m_dist;

    this.m_valuesToLog = benchmark.m_valuesToLog;
    this.m_FEsDEsToLog = benchmark.m_FEsDEsToLog;

    this.m_r = new Randomizer();

    // allocate memory for logging information
    i = (this.m_valuesToLog.length + (2 * this.m_FEsDEsToLog.length) + 10);
    this.m_log = new LogPoint[i];
    for (; (--i) >= 0;) {
      this.m_log[i] = new LogPoint();
    }

    i = this.n();
    this.m_bestX = new int[i];
    this.m_i_bestX = new int[i];
    this.m_calendar = new GregorianCalendar();

    this.m_state = ObjectiveFunction.STATE_NOTHING;

    this.m_nextF = Long.MAX_VALUE;
    this.m_i_nextF = Long.MAX_VALUE;

    this.m_currentLP = new _CurrentLogPoint(this);
    this.m_lastImprovementLP = new LogPoint();
    this.m_initLP = new LogPoint();

    // compute the time normalization factor
    this.m_timeNormalizationFactor = _SpeedBenchmark
        ._timeBenchmarkRun(this);
  }

  /**
   * Store an exception that has occured during the initialization
   * procedure for logging. Warning: Only the first exception passed to
   * this method will be remembered.
   *
   * @param t
   *          the exception
   */
  public final void setExceptionDuringInitalization(final Throwable t) {
    if (this.m_exceptionDuringInit == null) {
      this.m_exceptionDuringInit = t;
    }
  }

  /**
   * Store an exception that has occured during the optimization algorithm
   * run for logging. Warning: Only the first exception passed to this
   * method will be remembered.
   *
   * @param t
   *          the exception
   */
  public final void setExceptionDuringRun(final Throwable t) {
    if (this.m_exceptionDuringRun == null) {
      this.m_exceptionDuringRun = t;
    }
  }

  /**
   * <p>
   * Obtain an instance of
   * {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint
   * LogPoint} that always represents the state of the search when the last
   * improvement of the objective value happened. This instance will
   * automatically be updated as the search proceeds, whenever a new,
   * better objective value is encountered. You can access the function or
   * distance evaluations consumed until that last improvement was
   * discovered or the objective value of that improvement.
   * </p>
   * <p>
   * Warning: The runtime provided by
   * {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedRuntime()
   * getConsumedRuntime()} of this instance will only be a rough estimate:
   * it will actually reflect the runtime measured when the last log point
   * was reached. This is done in order to reduce the number of system
   * calls (calls to {@link java.lang.System#currentTimeMillis()
   * currentTimeMillis()}).
   * </p>
   *
   * @see org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedDEs()
   * @see org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedFEs()
   * @see org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getBestF()
   * @see org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedRuntime()
   * @return the number of performed function evaluations (FEs)
   */
  public final LogPoint getLastImprovementLogPoint() {
    return this.m_lastImprovementLP;
  }

  /**
   * Obtain an instance of
   * {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint
   * LogPoint} that represents the state of the search after the
   * deterministic initialization was applied. If no such procedure was
   * applied, the log point will provide no useful information.
   *
   * @see #beginDeterministicInitialization(NamedObject)
   * @see #endDeterministicInitialization()
   * @see #deleteDeterministicInitialization()
   * @see org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedDEs()
   * @see org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedFEs()
   * @see org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getBestF()
   * @see org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedRuntime()
   * @return the number of performed function evaluations (FEs)
   */
  public final LogPoint getDeterministicInitializationLogPoint() {
    return this.m_initLP;
  }

  /**
   * Obtain an instance of
   * {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint
   * LogPoint} that always represents the current state of the search. This
   * instance will automatically be updated as the search proceeds. You can
   * access the function or distance evaluations consumed until the current
   * point in time or the best objective value known so far. Accessing the
   * runtime passed via
   * {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedRuntime()}
   * from this instance, however, will be very time consuming as it will
   * lead to a call to a system call and to checking of the termination
   * criterion.
   *
   * @see org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedDEs()
   * @see org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedFEs()
   * @see org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getBestF()
   * @see org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedRuntime()
   * @return the number of performed function evaluations (FEs)
   */
  public final LogPoint getCurrentLogPoint() {
    return this.m_currentLP;
  }

  /**
   * Begin a new run with algorithm {@code algorithm}. The algorithm can
   * now use the objective function's data, such as
   * {@link #distance(int, int)}, {@link #getRandom()},
   * {@link #evaluate(int[])}, etc., and its performance will automatically
   * be measured and logged in memory. Once the run has {@link #endRun()
   * ended} (and {@link #endRun()} is called), the logged information will
   * be written to a log file.
   *
   * @param algorithm
   *          the algorithm object
   * @see #endRun()
   */
  public final void beginRun(final NamedObject algorithm) {
    this._beginRun(algorithm, true);
  }

  /**
   * Begin a new run: this method allows us to skip making a file, as is
   * necessary for the speed benchmark.
   *
   * @param algorithm
   *          the algorithm name
   * @param makeFile
   *          should we make a temporary file?
   */
  @SuppressWarnings("incomplete-switch")
  final void _beginRun(final NamedObject algorithm, final boolean makeFile) {

    switch (this.m_state) {
      case ObjectiveFunction.STATE_IN_RUN: {
        throw new IllegalStateException(
            "Cannot begin run, another run is still ongoing."); //$NON-NLS-1$
      }
      case ObjectiveFunction.STATE_IN_INIT: {
        throw new IllegalStateException(
            "Cannot begin run, some deterministic initialization procedure is still ongoing."); //$NON-NLS-1$
      }
    }

    this.m_state = ObjectiveFunction.STATE_IN_RUN;

    this.m_exceptionDuringRun = null;

    this.m_algorithm = algorithm;
    if (makeFile) {
      this.m_file = this.m_benchmark._getFile(ObjectiveFunction
          .__makeName(this.m_algorithm, this.m_initializer));
    }

    this.__begin(this.m_initializer != null);
  }

  /**
   * Make a directory or file name prefix based on a combination of the
   * name of the {@link #m_initializer deterministic initialization
   * heuristic} and the {@link #m_algorithm optimization algorithm}.
   *
   * @param algo
   *          the algorithm configuration of the algorithm, or {@code null}
   * @param init
   *          the algorithm configuration of the deterministic
   *          initialization algorithm, or {@code null}
   * @return the name or {@code null} if no reasonable name exists
   */
  private static final String __makeName(final NamedObject algo,
      final NamedObject init) {
    String s, t;

    s = null;
    if (algo != null) {
      s = ObjectiveFunction.__sanitizeName(algo);
    }

    if (init != null) {
      t = ("InitWith" + ObjectiveFunction.__sanitizeName(init)); //$NON-NLS-1$
      if (s != null) {
        s = s + t;
      } else {
        s = t;
      }
    }

    if (s == null) {
      // this should never be reached
      return "UnnamedAlgorithmCombination"; //$NON-NLS-1$
    }
    return s;
  }

  /**
   * This function takes a name and removes spaces and control characters
   * and is used in {@link #__makeName(NamedObject, NamedObject)} to
   * generate a viable filename or directory.
   *
   * @param named
   *          the named object
   * @return the sanitized name
   */
  private static final String __sanitizeName(final NamedObject named) {
    final StringBuilder sb;
    final String name, fallback;
    final int l;
    char ch;
    boolean lastNotGood;
    int i;

    if (named != null) {
      name = named.name();
      if (name != null) {
        l = name.length();
        if (l > 0) {
          sb = new StringBuilder(l);
          lastNotGood = true;
          for (i = 0; i < l; i++) {
            ch = name.charAt(i);
            if (((ch >= '0') && (ch <= '9')) || //
                ((ch >= 'a') && (ch <= 'z')) || //
                ((ch >= 'A') && (ch <= 'Z'))) {
              if (lastNotGood) {
                ch = (Character.toUpperCase(ch));
              }
              sb.append(ch);
              lastNotGood = false;
            } else {
              lastNotGood = true;
            }
          }

          if (sb.length() > 0) {
            return sb.toString();
          }

          // fallback: translate control and other characters as well
          // this code should never be reached, but, well, whatever
          fallback = org.logisticPlanning.utils.text.TextUtils.prepare(//
              org.logisticPlanning.utils.text.transformations.NormalCharTransformer.INSTANCE
                  .transform(name));
          if (fallback != null) {
            return fallback;
          }
        }
      }
    }

    // ok, we could not create any useful name, so emty will do
    return "empty"; //$NON-NLS-1$
  }

  /**
   * begin a new deterministic initialization procedure
   *
   * @param algorithm
   *          the deterministic initialization procedure
   */
  @SuppressWarnings("incomplete-switch")
  public final void beginDeterministicInitialization(
      final NamedObject algorithm) {

    if (algorithm == null) {
      throw new IllegalArgumentException(
          "No algorithm configuration provided."); //$NON-NLS-1$
    }

    switch (this.m_state) {
      case ObjectiveFunction.STATE_IN_RUN: {
        throw new IllegalStateException(
            "Cannot begin deterministic initialization, some run is still ongoing."); //$NON-NLS-1$
      }

      case ObjectiveFunction.STATE_IN_INIT: {
        throw new IllegalStateException(
            "Cannot begin deterministic initialization, a deterministic initialization procedure is still ongoing."); //$NON-NLS-1$
      }
    }

    this.m_state = ObjectiveFunction.STATE_IN_INIT;
    this.m_initializer = algorithm;

    this.m_exceptionDuringInit = null;

    this.__begin(false);
  }

  /**
   * Delete all data and in-memory log entries generated by any
   * deterministic initialization procedure.
   *
   * @see #beginDeterministicInitialization(NamedObject)
   * @see #endDeterministicInitialization()
   */
  public final void deleteDeterministicInitialization() {

    if (this.m_state != ObjectiveFunction.STATE_NOTHING) {
      throw new IllegalStateException(
          "The current state does not permit deleting initialization data."); //$NON-NLS-1$
    }

    this.m_i_logSize = 0;

    this.m_currentLP.initExtreme();
    this.m_initLP.initExtreme();
    this.m_lastImprovementLP.initExtreme();

    this.m_i_fIdx = 0;
    this.m_i_nextF = Long.MAX_VALUE;

    this.m_i_deIdx = 0;
    this.m_i_nextDE = 0l;

    this.m_i_feIdx = 0;
    this.m_i_nextFE = 0l;

    this.m_i_randSeed = 0l;

    this.m_initializer = null;
  }

  /**
   * begin a run or initialization procedure
   *
   * @param useInit
   *          use the results from the initialization procedure (or not)
   */
  private final void __begin(final boolean useInit) {
    if (useInit) {
      this.m_logSize = this.m_i_logSize;

      this.m_currentLP._assign(this.m_initLP);
      this.m_lastImprovementLP._assign(this.m_initLP);

      this.m_fIdx = this.m_i_fIdx;
      this.m_nextF = this.m_i_nextF;

      this.m_deIdx = this.m_i_deIdx;
      this.m_nextDE = this.m_i_nextDE;

      this.m_feIdx = this.m_i_feIdx;
      this.m_nextFE = this.m_i_nextFE;

      System.arraycopy(this.m_i_bestX, 0, this.m_bestX, //
          0, this.m_bestX.length);
    } else {
      this.m_logSize = 0;
      this.m_time_offset = 0l;

      this.m_currentLP.initExtreme();
      this.m_lastImprovementLP.initExtreme();

      this.m_fIdx = (this.m_valuesToLog.length - 1);
      this.m_nextF = this.m_valuesToLog[this.m_fIdx];

      this.m_deIdx = (this.m_FEsDEsToLog.length - 1);
      this.m_nextDE = (this.m_FEsDEsToLog[this.m_deIdx]);

      this.m_feIdx = (this.m_FEsDEsToLog.length - 1);
      this.m_nextFE = this.m_FEsDEsToLog[this.m_feIdx];
    }

    this.m_terminate = false;
    this.m_shouldLog = false;
    this.m_fimproved = false;

    this.m_r.setSeed(this.m_randSeed = this.m_r.nextLong());

    this.m_startTime = System.currentTimeMillis();
    this.m_endTime = (this.m_startTime + this.m_benchmark.m_maxTime);

    if (this.m_endTime < this.m_startTime) {
      this.m_endTime = Long.MAX_VALUE;
    } else {
      if (useInit) {
        this.m_endTime -= this.m_initLP.m_time;
      }
    }

    _TerminationThread._enqueue(this);
  }

  /** the deterministic initialization procedure has finished */
  public final void endDeterministicInitialization() {
    final LogPoint cur, lp;
    final long t;
    int ls;

    this.m_terminate = true;
    _TerminationThread._dequeue(this);

    if (this.m_state != ObjectiveFunction.STATE_IN_INIT) {
      throw new IllegalStateException(
          "Cannot end initialization procedure, as none was started."); //$NON-NLS-1$
    }
    try {
      cur = this.m_currentLP;

      // update the time variables
      t = System.currentTimeMillis();
      cur.m_time = ((t - this.m_startTime));

      ls = this.m_logSize;
      shouldWeLog: {
        if (ls > 0) {
          lp = this.m_log[ls - 1];
          if ((lp.m_de >= cur.m_de) && (lp.m_fe >= cur.m_fe)
              && (lp.m_f <= cur.m_f)) {
            break shouldWeLog;
          }
        }

        // store a log point
        this.m_log[ls++]._assign(cur);
        this.m_logSize = ls;
        cur.m_type = 0;
      }

      if (ls > 0) {
        this.m_log[ls - 1].m_type |= ObjectiveFunction.TYPE_FLAG_INIT_END;
      }

      this.m_i_logSize = ls;

      this.m_initLP._assign(this.m_currentLP);
      if ((this.m_initLP.m_fe <= this.m_lastImprovementLP.m_fe)
          && (this.m_initLP.m_de <= this.m_lastImprovementLP.m_de)) {
        this.m_lastImprovementLP.m_time = this.m_initLP.m_time;
      }

      this.m_i_fIdx = this.m_fIdx;
      this.m_i_nextF = this.m_nextF;

      this.m_i_deIdx = this.m_deIdx;
      this.m_i_nextDE = this.m_nextDE;

      this.m_i_feIdx = this.m_feIdx;
      this.m_i_nextFE = this.m_nextFE;

      this.m_i_randSeed = this.m_randSeed;
      this.m_i_startTime = this.m_startTime;

      System.arraycopy(this.m_bestX, 0, this.m_i_bestX, //
          0, this.m_bestX.length);

      this.m_time_offset = this.m_initLP.m_time;
      this.m_i_endTime = (this.m_i_startTime + this.m_time_offset);
    } finally {
      this.m_terminate = true;
      this.m_state = ObjectiveFunction.STATE_NOTHING;
    }
  }

  /**
   * This method must be called when one run has finished. It will flush
   * the in-memory log data to the output file.
   */
  public final void endRun() {
    final long opt, t, fm, tm, mm;
    final double optm;
    final Runtime rt;
    final LogPoint cur, lp;
    int i, ls, type;
    LogPoint p;

    this.m_terminate = true;
    _TerminationThread._dequeue(this);

    if (this.m_state != ObjectiveFunction.STATE_IN_RUN) {
      throw new IllegalStateException(
          "Cannot end run, as no run was started."); //$NON-NLS-1$
    }
    try {
      cur = this.m_currentLP;

      // update the time variables
      t = System.currentTimeMillis();
      cur.m_time = ((t - this.m_startTime) + this.m_time_offset);

      ls = this.m_logSize;
      shouldWeLog: {
        if (ls > 0) {
          lp = this.m_log[ls - 1];
          if ((lp.m_de >= cur.m_de) && (lp.m_fe >= cur.m_fe)
              && (lp.m_f <= cur.m_f)) {
            break shouldWeLog;
          }
        }

        // store a log point
        this.m_log[ls++]._assign(cur);
        this.m_logSize = ls;
        cur.m_type = 0;
      }

      if (ls > 0) {
        this.m_log[ls - 1].m_type |= ObjectiveFunction.TYPE_FLAG_END;
      }

      opt = this.m_optimum;
      try {

        try (PrintStream ps = new PrintStream(this.m_file, "UTF-8")) { //$NON-NLS-1$

          // print the headline comment
          if (this.m_algorithm != null) {
            ps.print(ObjectiveFunction.LOG_FILE_START_A);
            ps.print(this.m_algorithm.name());
            if (this.m_initializer != null) {
              ps.print(ObjectiveFunction.LOG_FILE_START_A2);
              ps.print(this.m_initializer.name());
            }
            ps.print(ObjectiveFunction.LOG_FILE_START_B);
          } else {
            ps.print(ObjectiveFunction.LOG_FILE_START_X);
          }

          ps.print(this.m_benchmark.m_instance.name());
          ps.print(ObjectiveFunction.LOG_FILE_START_C);
          if (this.m_benchmark.m_instance.symmetric()) {
            ps.print(ObjectiveFunction.LOG_FILE_START_DA);
          } else {
            ps.print(ObjectiveFunction.LOG_FILE_START_DB);
          }
          ps.println();

          // print some general comments and start the log data
          // section
          for (final char[] chs : ObjectiveFunction.HEADER_COMMENTS) {
            ps.println(chs);
          }

          // print the log data: FE DE Time Normalized_Time F
          // Rel_Error Reason
          optm = (1d / opt);
          for (i = 0; i < ls; i++) {
            p = this.m_log[i];
            ps.print(p.m_fe);
            ps.print('\t');
            ps.print(p.m_de);
            ps.print('\t');
            ps.print(p.m_time);
            ps.print('\t');
            ps.print(p.m_time / this.m_timeNormalizationFactor);
            ps.print('\t');
            ps.print(p.m_f);
            ps.print('\t');
            ps.print((p.m_f - opt) * optm);

            type = p.m_type;
            if (type != 0) {
              ps.print('\t');
              if ((type & ObjectiveFunction.TYPE_FLAG_OBJECTIVE) != 0) {
                ps.print(ObjectiveFunction.TYPE_FLAG_OBJECTIVE_CHAR);
              }
              if ((type & ObjectiveFunction.TYPE_FLAG_FE) != 0) {
                ps.print(ObjectiveFunction.TYPE_FLAG_FE_CHAR);
              }
              if ((type & ObjectiveFunction.TYPE_FLAG_DE) != 0) {
                ps.print(ObjectiveFunction.TYPE_FLAG_DE_CHAR);
              }
              if ((type & ObjectiveFunction.TYPE_FLAG_INIT_END) != 0) {
                ps.print(ObjectiveFunction.TYPE_FLAG_INIT_END_CHAR);
              }
              if ((type & ObjectiveFunction.TYPE_FLAG_END) != 0) {
                ps.print(ObjectiveFunction.TYPE_FLAG_END_CHAR);
              }
            }
            ps.println();
          }
          ps.println(ObjectiveFunction.SECTION_END);

          // print best solution discovered, if at least 1 FE was
          // performed
          if ((ls > 0) && (this.m_log[ls - 1].m_fe > 0)) {
            // print the comments regarding the solution and print
            // the solution
            for (final char[] chs : ObjectiveFunction.SOLUTION_COMMENTS) {
              ps.println(chs);
            }

            // print result if at least one fe was performed
            Configurable.printKey(ObjectiveFunction.BEST_SOLUTION, ps);
            RepresentationUtils.appendPathInNormalForm(this.m_bestX, ps);
            ps.println();
            SolutionValidator.validatePath(this.m_bestX,
                this.m_currentLP.m_f, this.m_dist, ps);
            ps.println();
          }

          // print information about the algorithm
          if (this.m_algorithm != null) {
            for (final char[] chs : ObjectiveFunction.ALGORITHM_INFORMATION) {
              ps.println(chs);
            }
            Configurable.printKey(ObjectiveFunction.ALGORITHM_CLASS, ps);
            Configurable.printlnClass(this.m_algorithm.getClass(), ps);
            Configurable.printKey(ObjectiveFunction.ALGORITHM_NAME, ps);
            ps.println(this.m_algorithm.name());

            if (this.m_algorithm instanceof Configurable) {
              ((Configurable) (this.m_algorithm)).printConfiguration(ps);
            }
          }
          ps.println(ObjectiveFunction.SECTION_END);

          // print some information on the benchmark
          for (final char[] chs : ObjectiveFunction.BENCHMARK_INFORMATION) {
            ps.println(chs);
          }
          this.m_benchmark.printConfiguration(ps);

          Configurable.printKey(ObjectiveFunction.RAND_SEED, ps);
          ps.println(this.m_randSeed);

          Configurable.printKey(ObjectiveFunction.START_DATE, ps);
          this.m_calendar.setTimeInMillis(this.m_startTime);
          Configurable.printlnTimestamp(this.m_calendar, ps);

          Configurable.printKey(ObjectiveFunction.END_DATE, ps);
          this.m_calendar.setTimeInMillis(t);
          Configurable.printlnTimestamp(this.m_calendar, ps);

          Configurable.printKey(ObjectiveFunction.RUN_TIME, ps);
          ps.println(this.m_currentLP.m_time);
          ps.println(ObjectiveFunction.SECTION_END);

          // if an initialization algorithm was used, print
          // corresponding data
          if (this.m_initializer != null) {
            for (final char[] chs : ObjectiveFunction.INITIALIZER_INFORMATION) {
              ps.println(chs);
            }

            Configurable.printKey(ObjectiveFunction.INITIALIZER_CLASS, ps);
            Configurable.printlnClass(this.m_initializer.getClass(), ps);
            Configurable.printKey(ObjectiveFunction.INITIALIZER_NAME, ps);
            ps.println(this.m_initializer.name());

            Configurable.printKey(ObjectiveFunction.INITIALIZATION_F, ps);
            ps.println(this.m_initLP.m_f);

            Configurable
                .printKey(ObjectiveFunction.INITIALIZATION_DES, ps);
            ps.println(this.m_initLP.m_de);

            Configurable
                .printKey(ObjectiveFunction.INITIALIZATION_FES, ps);
            ps.println(this.m_initLP.m_fe);

            Configurable.printKey(
                ObjectiveFunction.INITIALIZATION_RUNTIME, ps);
            ps.println(this.m_initLP.m_time);

            Configurable.printKey(
                ObjectiveFunction.INITIALIZATION_RAND_SEED, ps);
            ps.println(this.m_i_randSeed);

            Configurable.printKey(
                ObjectiveFunction.INITIALIZATION_START_TIME, ps);
            this.m_calendar.setTimeInMillis(this.m_i_startTime);
            Configurable.printlnTimestamp(this.m_calendar, ps);

            Configurable.printKey(
                ObjectiveFunction.INITIALIZATION_END_TIME, ps);
            this.m_calendar.setTimeInMillis(this.m_i_endTime);
            Configurable.printlnTimestamp(this.m_calendar, ps);

            if (this.m_initializer instanceof Configurable) {
              ((Configurable) (this.m_initializer)).printConfiguration(ps);
            }

            ps.println(ObjectiveFunction.SECTION_END);

            // if initializer did perform at least 1 FE, print its
            // result too
            if (this.m_initLP.m_fe > 0l) {
              // print initializer solution, if available
              ps.println(ObjectiveFunction.COMMENT_START);
              Configurable.printKey(
                  ObjectiveFunction.INITIALIZATION_SOLUTION, ps);
              RepresentationUtils.appendPathInNormalForm(this.m_i_bestX,
                  ps);
              ps.println();
              SolutionValidator.validatePath(this.m_i_bestX,
                  this.m_initLP.m_f, this.m_dist, ps);
              ps.println();
            }
          }

          // print potential exception of the initializer
          if (this.m_exceptionDuringInit != null) {
            ObjectiveFunction.__printException(this.m_exceptionDuringInit,
                ObjectiveFunction.INITIALIZATION_EXCEPTION, ps);
          }

          // print potential exception of the algorithm
          if (this.m_exceptionDuringRun != null) {
            ObjectiveFunction.__printException(this.m_exceptionDuringRun,
                ObjectiveFunction.ALGORITHM_EXCEPTION, ps);
            this.m_exceptionDuringRun = null;
          }

          // now let's print system information, such as OS, java
          // version, etc
          for (final char[] chs : ObjectiveFunction.SYSTEM_INFORMATION) {
            ps.println(chs);
          }

          Configurable.printKey(
              ObjectiveFunction.RUNTIME_NORMALIZATION_FACTOR, ps);
          ps.println(this.m_timeNormalizationFactor);

          for (final String s : ObjectiveFunction.SYS_INFOS) {
            final String v = System.getProperty(s);
            if (v != null) {
              Configurable.printKey(ObjectiveFunction.SYS_PROP_PREFIX + s,
                  ps);
              ps.println(v);
            }
          }

          for (final String s : ObjectiveFunction.ENV_INFOS) {
            final String v = System.getenv(s);
            if (v != null) {
              Configurable.printKey(ObjectiveFunction.ENV_VAR_PREFIX + s,
                  ps);
              ps.println(v);
            }
          }

          // print infos such as number of processors and memory
          rt = Runtime.getRuntime();
          tm = rt.totalMemory();
          fm = rt.freeMemory();
          mm = rt.maxMemory();
          Configurable.printKey(ObjectiveFunction.PROCESSORS, ps);
          ps.println(rt.availableProcessors());

          Configurable.printKey(ObjectiveFunction.FREE_MEMORY, ps);
          ps.println(fm);

          Configurable.printKey(ObjectiveFunction.MAX_MEMORY, ps);
          ps.println(mm);

          Configurable.printKey(ObjectiveFunction.TOTAL_MEMORY, ps);
          ps.println(tm);

          ps.println(ObjectiveFunction.SECTION_END);

          // print information about the person/group who did this
          // experiment
          if ((this.m_creator != null) && (!(this.m_creator.isEmpty()))) {
            for (final char[] chs : ObjectiveFunction.CREATOR_INFORMATION) {
              ps.println(chs);
            }
            this.m_creator.printConfiguration(ps);
            ps.println(ObjectiveFunction.SECTION_END);
          }
        }
      } catch (final Throwable txt) {
        throw new RuntimeException(txt);
      }
    } finally {
      this.m_terminate = true;
      this.m_logSize = 0;
      this.m_state = ObjectiveFunction.STATE_NOTHING;
    }
  }

  /**
   * Clear: delete all information stored in this objective function,
   * without creating any log. This function is only for use within
   * {@link org.logisticPlanning.tsp.benchmarking.objective._SpeedBenchmark#_timeBenchmarkRun(ObjectiveFunction)}
   * .
   */
  final void _clear() {
    this.m_terminate = true;

    this.m_currentLP.initExtreme();
    this.m_lastImprovementLP.initExtreme();
    this.m_initLP.initExtreme();

    this.m_state = ObjectiveFunction.STATE_NOTHING;
    this.m_logSize = 0;
    this.m_file = null;

    this.m_feIdx = (this.m_FEsDEsToLog.length - 1);
    this.m_nextFE = this.m_FEsDEsToLog[this.m_feIdx];

    this.m_deIdx = (this.m_FEsDEsToLog.length - 1);
    this.m_nextDE = (this.m_FEsDEsToLog[this.m_deIdx]);

    this.m_fIdx = (this.m_valuesToLog.length - 1);
    this.m_nextF = this.m_valuesToLog[this.m_fIdx];

    this.m_randSeed = 0l;
    this.m_startTime = 0l;
    this.m_endTime = 0l;
    this.m_shouldLog = false;
    this.m_fimproved = false;
    this.m_algorithm = null;
    this.m_initializer = null;

    this.m_i_logSize = 0;
    this.m_i_fIdx = 0;
    this.m_i_nextF = 0l;
    this.m_i_deIdx = 0;
    this.m_i_nextDE = 0l;
    this.m_i_feIdx = 0;
    this.m_i_nextFE = 0l;
    this.m_i_startTime = 0l;
    this.m_i_endTime = 0l;
    this.m_i_randSeed = 0l;

    this.m_time_offset = 0l;

    this.m_exceptionDuringInit = null;
    this.m_exceptionDuringRun = null;
  }

  /**
   * print an exception to the log
   *
   * @param t
   *          the exception
   * @param prefix
   *          the prefix
   * @param ps
   *          the print stream
   */
  private static final void __printException(final Throwable t,
      final char[][] prefix, final PrintStream ps) {
    for (final char[] chs : prefix) {
      ps.println(chs);
    }
    t.printStackTrace(ps);
    ps.println(ObjectiveFunction.SECTION_END);
  }

  /**
   * Set the creator information to be written to each log file
   *
   * @param info
   *          the creator information, or {@code null} if no such info
   *          should be written
   */
  public final void setCreatorInfo(final CreatorInfo info) {
    this.m_creator = info;
  }

  /**
   * Get the creator information which is written to each log file
   *
   * @return the creator information record
   */
  public final CreatorInfo getCreatorInfo() {
    return this.m_creator;
  }

  /** {@inheritDoc} */
  @Override
  public final int distance(final int i, final int j) {
    final long de;
    final LogPoint cur;

    cur = this.m_currentLP;
    de = (++cur.m_de);

    // check if we reached a DE check point
    if (de >= this.m_nextDE) {

      // if so, this is a reason for logging, but the logging can only
      // take
      // place in the __register function, as here we do not have an
      // objective
      // function value
      this.m_shouldLog = true;
      cur.m_type |= ObjectiveFunction.TYPE_FLAG_DE;

      // find the next DE check point
      do {
        if ((--this.m_deIdx) < 0) {
          // ok, no more check points: we should terminate
          this.m_terminate = true;
          break;
        }
        this.m_nextDE = this.m_FEsDEsToLog[this.m_deIdx];
      } while (de >= this.m_nextDE);

      // as the maximum DE will also be a check point, we check if we
      // reached
      // it. we do not need to check this outside of this if, because, as
      // said,
      // it will be a check point DE
      if (de >= this.m_maxDEs) {
        this.m_terminate = true;
      }
    }

    return this.m_dist.distance(i, j);
  }

  /**
   * <p>
   * Evaluate a given candidate solution in <a
   * href="#pathRepresentation">path representation</a>, i.e., calculate
   * the total length of the circular tour represented by the sequence of
   * nodes given in array {@code nodes}. This will lead to the registration
   * of {@code 1 FE} and {@code n DEs}, where {@code n=nodes.length} is
   * both, the number of nodes {@link #n()} in the TSP as well as the
   * length of the permutation (the array holding the path).
   * </p>
   * <p>
   * If the new solution {@code nodes} is better than the best solution
   * discovered so far, it will be copied into an internal variable and
   * become accessible via {@link #getCopyOfBest(int[])}.
   * </p>
   *
   * @param nodes
   *          the candidate solution to be evaluated
   * @return the total resulting tour length of {@code nodes}
   * @see #registerFE(int[], long)
   * @see #registerFEs(long, int[], long)
   * @see #getCopyOfBest(int[])
   */
  @Override
  public final long evaluate(final int[] nodes) {
    final long f;

    f = this.m_dist.evaluate(nodes);
    this.__register(1l, nodes, f, true, false);
    return f;
  }

  /**
   * <p>
   * Register that a new candidate solution in <a
   * href="#pathRepresentation"> <em>path representation</em></a> is now
   * known and that a function evaluation has been performed without
   * calling {@link #evaluate(int[])}. {@code 1 FE} will be registered, but
   * no {@code DE}. It is assumed that the distance evaluations necessary
   * to obtain the objective value (tour length) {@code f} have been
   * performed by explicit calls to {@link #distance(int, int)}.
   * </p>
   * <p>
   * If the candidate solution is better than the best solution found so
   * far in this run, this function returns {@code true}. In that case, the
   * solution is copied into an internal memory record and
   * {@link #getCopyOfBest(int[])} will return a copy of it. If the
   * registered solution is not better than the best solution known so far,
   * the return value is {@code false}.
   * </p>
   *
   * @param nodes
   *          the candidate solution. If set to {@code null} the internal
   *          best solution will not be updated. Use only if you know that
   *          no update can occur.
   * @param f
   *          its objective value
   * @return {@code true} if the registered solution was an improvement
   *         compared to the currently best known solution (and hence was
   *         copied into the internal memory), {@code false} if the
   *         registered solution was no improvement.
   * @see #evaluate(int[])
   * @see #registerFEs(long, int[], long)
   * @see #getCopyOfBest(int[])
   */
  public final boolean registerFE(final int[] nodes, final long f) {
    return this.__register(1l, nodes, f, false, false);
  }

  /**
   * <p>
   * Register that a new candidate solution in <a
   * href="#pathRepresentation"> <em>path representation</em></a> is now
   * known and that a given number of function evaluations has been
   * performed without calling {@link #evaluate(int[])}. {@code fes FEs}
   * will be registered, but no {@code DE}. It is assumed that the distance
   * evaluations necessary to obtain the objective value (tour length)
   * {@code f} have been performed by explicit calls to
   * {@link #distance(int, int)}.
   * </p>
   * <p>
   * If the candidate solution is better than the best solution found so
   * far in this run, this function returns {@code true}. In that case, the
   * solution is copied into an internal memory record and
   * {@link #getCopyOfBest(int[])} will return a copy of it. If the
   * registered solution is not better than the best solution known so far,
   * the return value is {@code false}.
   * </p>
   *
   * @param fes
   *          the number of function evaluation that have (implicitly) been
   *          performed
   * @param nodes
   *          the candidate solution. If set to {@code null} the internal
   *          best solution will not be updated. Use only if you know that
   *          no update can occur.
   * @param f
   *          its objective value
   * @return {@code true} if the registered solution was an improvement
   *         compared to the currently best known solution (and hence was
   *         copied into the internal memory), {@code false} if the
   *         registered solution was no improvement.
   * @see #evaluate(int[])
   * @see #registerFE(int[], long)
   * @see #getCopyOfBest(int[])
   */
  public final boolean registerFEs(final long fes, final int[] nodes,
      final long f) {
    return this.__register(fes, nodes, f, false, false);
  }

  /**
   * <p>
   * Evaluate a given candidate solution in <a
   * href="#adjacencyRepresentation"><em>adjacency representation</em>
   * </a>&nbsp;[<a href="#cite_GGRVG1985GAFTT"
   * style="font-weight:bold">14</a>, <a
   * href="#cite_LKMUB1999GAFTTSPARORAO" style="font-weight:bold">10</a>],
   * i.e., calculate the total length of the circular tour represented by
   * the <a href="#adjacencyRepresentation">
   * <em>adjacency representation</em></a> {@code adjacencyList}. This will
   * lead to the registration of {@code 1 FE} and {@code n DEs}, where
   * {@code n=adjacencyList.length} is both, the number of nodes
   * {@link #n()} in the TSP as well as the length of the permutation (the
   * array with the adjacency list).
   * </p>
   * <p>
   * If the new solution {@code adjacencyList} is better than the best
   * solution discovered so far, it will be copied into an internal
   * variable and become accessible via {@link #getCopyOfBestAdj(int[])}.
   * </p>
   *
   * @param adjacencyList
   *          the adjacency list representing the candidate solution to be
   *          evaluated
   * @return the total resulting tour length of {@code adjacencyList}
   * @see #registerFEAdj(int[], long)
   * @see #registerFEsAdj(long, int[], long)
   * @see #getCopyOfBestAdj(int[])
   */
  @Override
  public final long evaluateAdj(final int[] adjacencyList) {
    final long f;

    f = this.m_dist.evaluateAdj(adjacencyList);
    this.__register(1l, adjacencyList, f, true, true);
    return f;
  }

  /**
   * <p>
   * Register that a new candidate solution in <a
   * href="#adjacencyRepresentation"><em>adjacency representation</em>
   * </a>&nbsp;[<a href="#cite_GGRVG1985GAFTT"
   * style="font-weight:bold">14</a>, <a
   * href="#cite_LKMUB1999GAFTTSPARORAO" style="font-weight:bold">10</a>]
   * is now known and that a function evaluation has been performed without
   * calling {@link #evaluateAdj(int[])}. {@code 1 FE} will be registered,
   * but no {@code DE}. It is assumed that the distance evaluations
   * necessary to obtain the objective value (tour length) {@code f} have
   * been performed by explicit calls to {@link #distance(int, int)}.
   * </p>
   * <p>
   * If the candidate solution represented by the <a
   * href="#adjacencyRepresentation"><em>adjacency representation</em></a>
   * of the solution {@code adjacencyList} is better than the best solution
   * found so far in this run, this function returns {@code true}. In that
   * case, the solution is copied into an internal memory record and
   * {@link #getCopyOfBest(int[])} will return a copy of it. If the
   * registered solution is not better than the best solution known so far,
   * the return value is {@code false}.
   * </p>
   *
   * @param adjacencyList
   *          the candidate solution in <a href="#adjacencyRepresentation">
   *          <em>adjacency representation</em></a>. If set to {@code null}
   *          the internal best solution will not be updated. Use only if
   *          you know that no update can occur.
   * @param f
   *          its objective value
   * @return {@code true} if the registered solution was an improvement
   *         compared to the currently best known solution (and hence was
   *         copied into the internal memory), {@code false} if the
   *         registered solution was no improvement.
   * @see #evaluateAdj(int[])
   * @see #registerFEsAdj(long, int[], long)
   * @see #getCopyOfBestAdj(int[])
   */
  public final boolean registerFEAdj(final int[] adjacencyList,
      final long f) {
    return this.__register(1l, adjacencyList, f, false, true);
  }

  /**
   * <p>
   * Register that a new candidate solution in <a
   * href="#adjacencyRepresentation"><em>adjacency representation</em>
   * </a>&nbsp;[<a href="#cite_GGRVG1985GAFTT"
   * style="font-weight:bold">14</a>, <a
   * href="#cite_LKMUB1999GAFTTSPARORAO" style="font-weight:bold">10</a>]
   * is now known and that a given number of function evaluations has been
   * performed without calling {@link #evaluate(int[])}. {@code fes FEs}
   * will be registered, but no {@code DE}. It is assumed that the distance
   * evaluations necessary to obtain the objective value (tour length)
   * {@code f} have been performed by explicit calls to
   * {@link #distance(int, int)}.
   * </p>
   * <p>
   * If the candidate solution {@code adjacencyList} in <a
   * href="#adjacencyRepresentation"><em>adjacency representation</em></a>
   * is better than the best solution found so far in this run, this
   * function returns {@code true}. In that case, the solution is copied
   * into an internal memory record and {@link #getCopyOfBest(int[])} will
   * return a copy of it. If the registered solution is not better than the
   * best solution known so far, the return value is {@code false}.
   * </p>
   *
   * @param fes
   *          the number of function evaluation that have (implicitly) been
   *          performed
   * @param adjacencyList
   *          the candidate solution in <a href="#adjacencyRepresentation">
   *          <em>adjacency representation</em></a>. If set to {@code null}
   *          the internal best solution will not be updated. Use only if
   *          you know that no update can occur.
   * @param f
   *          its objective value
   * @return {@code true} if the registered solution was an improvement
   *         compared to the currently best known solution (and hence was
   *         copied into the internal memory), {@code false} if the
   *         registered solution was no improvement.
   * @see #evaluateAdj(int[])
   * @see #registerFEAdj(int[], long)
   * @see #getCopyOfBestAdj(int[])
   */
  public final boolean registerFEsAdj(final long fes,
      final int[] adjacencyList, final long f) {
    return this.__register(fes, adjacencyList, f, false, true);
  }

  /**
   * This is an internal function used to register the candidate solution
   * along with its objective value. It will update the intneral state
   * information and log points. If the candidate solution is better than
   * the best solution found so far in this run, this function returns
   * {@code true}. In that case, the solution is copied into an internal
   * memory record and {@link #getCopyOfBest(int[])} will return a copy of
   * it. If the registered solution is not better than the best solution
   * known so far, the return value is {@code false}.
   *
   * @param nodes
   *          the candidate solution. Can be set to {@code null} if the
   *          solution cannot be better than the current one or if the new
   *          solution was not actually realized or you get an exception.
   *          Only set to {@code null} if you are sure about what you are
   *          doing.
   * @param f
   *          its objective value
   * @param fes
   *          the number function evaluations to register (normally
   *          {@code 1l})
   * @param countDEs
   *          count the equivalent number of distance evaluations for the
   *          {@code fes}
   * @param adj
   *          {@code true} if {@code nodes} is <a
   *          href="#adjacencyRepresentation">
   *          <em>adjacency representation</em></a>&nbsp;[<a
   *          href="#cite_GGRVG1985GAFTT" style="font-weight:bold">14</a>,
   *          <a href="#cite_LKMUB1999GAFTTSPARORAO"
   *          style="font-weight:bold">10</a>], {@code false} if it is in
   *          <a href="#pathRepresentation"><em>path representation</em>
   *          </a>
   * @return {@code true} if the registered solution was an improvement
   *         compared to the currently best known solution (and hence was
   *         copied into the internal memory), {@code false} if the
   *         registered solution was no improvement.
   */
  private final boolean __register(final long fes, final int[] nodes,
      final long f, final boolean countDEs, final boolean adj) {
    final long fe, de, t;
    final LogPoint cur;
    final boolean improved;

    cur = this.m_currentLP;

    // did the objective function value improve?
    improved = (f < cur.m_f);
    if (improved) {

      // then let us remember this improvement and copy the solution
      this.m_fimproved = true;
      cur.m_f = f;
      if (nodes != null) {
        if (adj) {
          RepresentationUtils.adjacencyListToPath(nodes, this.m_bestX);
        } else {
          System.arraycopy(nodes, 0, this.m_bestX, 0, this.m_bestX.length);
        }
      }

      // did we pass an objective value threshold?
      if (f <= this.m_nextF) {
        this.m_shouldLog = true;
        cur.m_type |= ObjectiveFunction.TYPE_FLAG_OBJECTIVE;

        // find the next threshold
        do {
          if ((--this.m_fIdx) < 0) {
            // if there is no such threshold, we should quit
            this.m_terminate = true;
            break;
          }
          this.m_nextF = this.m_valuesToLog[this.m_fIdx];
        } while (f <= this.m_nextF);
      }

      // did we reach the optimum? then we should quit
      if (f <= this.m_optimum) {
        this.m_terminate = true;
      }
    }

    // count the function evaluations
    fe = (cur.m_fe += fes);
    if (fe >= this.m_nextFE) {// did we pass a check point?
      this.m_shouldLog = true;
      cur.m_type |= ObjectiveFunction.TYPE_FLAG_FE;

      // ok, let's get the next check point
      do {
        if ((--this.m_feIdx) < 0) {// no next check point? quit!
          this.m_terminate = true;
          break;
        }
        this.m_nextFE = this.m_FEsDEsToLog[this.m_feIdx];
      } while (fe >= this.m_nextFE);

      // the maximum FE limit will always be a check point, so we only
      // need to
      // check it here
      if (fe >= this.m_maxFEs) {
        this.m_terminate = true;
      }
    }

    // should we count the DEs?
    if (countDEs) {
      // if so, we add the DEs
      de = (cur.m_de += (fes * this.m_n));

      // did we pass a check point?
      if (de >= this.m_nextDE) {
        this.m_shouldLog = true;
        cur.m_type |= ObjectiveFunction.TYPE_FLAG_DE;

        // find the next check point
        do {
          if ((--this.m_deIdx) < 0) {// no next check point? quit!
            this.m_terminate = true;
            break;
          }
          this.m_nextDE = this.m_FEsDEsToLog[this.m_deIdx];
        } while (de >= this.m_nextDE);

        // the maximum DE limit will always be a check point, so we only
        // need
        // to check it here
        if (de >= this.m_maxDEs) {
          this.m_terminate = true;
        }
      }
    }

    // we did not log but found an improvement: update internal point
    if (this.m_fimproved) {
      if (this.m_shouldLog) {// but we only log if the function value also
        // has
        // improved

        // update the time variables
        t = System.currentTimeMillis();
        cur.m_time = ((t - this.m_startTime) + this.m_time_offset);
        if (t >= this.m_endTime) { // did we exceed the end time?
          this.m_terminate = true;
        }

        // store a log point
        this.m_shouldLog = false;
        this.m_fimproved = false;
        this.m_log[this.m_logSize++]._assign(cur);
        cur.m_type = 0;
      }

      if (improved) {
        this.m_lastImprovementLP._assign(cur);
      }
    }

    return improved;
  }

  /**
   * Get a copy of the best candidate solution discovered in this run in <a
   * href="#pathRepresentation"><em>path representation</em></a>. This is
   * the solution whose objective value (i.e., tour length) equals to
   * <code>{@link #getCurrentLogPoint() getCurrentLogPoint()}.{#link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getBestF()  getBestF()}</code>
   * as well as
   * <code>{@link #getLastImprovementLogPoint() getLastImprovementLogPoint()}.{#link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getBestF() getBestF()}</code>
   * .
   *
   * @param nodes
   *          the destination array
   * @throws IllegalStateException
   *           if no solution has been sampled so far
   * @see #getCurrentLogPoint()
   * @see #getCopyOfBestAdj(int[])
   */
  public final void getCopyOfBest(final int[] nodes) {
    if (this.m_currentLP.m_fe > 0l) {
      System.arraycopy(this.m_bestX, 0, nodes, 0, this.m_bestX.length);
    } else {
      throw new IllegalStateException(//
          "No solution has been generated so far."); //$NON-NLS-1$
    }
  }

  /**
   * Get a copy of the best candidate solution discovered in this run in <a
   * href="#adjacencyRepresentation"><em>adjacency representation</em>
   * </a>&nbsp;[<a href="#cite_GGRVG1985GAFTT"
   * style="font-weight:bold">14</a>, <a
   * href="#cite_LKMUB1999GAFTTSPARORAO" style="font-weight:bold">10</a>].
   * This is the solution whose objective value (i.e., tour length) equals
   * to
   * <code>{@link #getCurrentLogPoint() getCurrentLogPoint()}.{#link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getBestF()  getBestF()}</code>
   * as well as
   * <code>{@link #getLastImprovementLogPoint() getLastImprovementLogPoint()}.{#link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getBestF() getBestF()}</code>
   * .
   *
   * @param adjacencyList
   *          the destination array to receive the solution in <a
   *          href="#adjacencyRepresentation">
   *          <em>adjacency representation</em></a>
   * @throws IllegalStateException
   *           if no solution has been sampled so far
   * @see #getCurrentLogPoint()
   * @see #getCopyOfBest(int[])
   */
  public final void getCopyOfBestAdj(final int[] adjacencyList) {
    if (this.m_currentLP.m_fe > 0l) {
      RepresentationUtils.pathToAdjacencyList(this.m_bestX, adjacencyList);
    } else {
      throw new IllegalStateException(//
          "No solution has been generated so far."); //$NON-NLS-1$
    }
  }

  /**
   * Return the instance of
   * {@link org.logisticPlanning.utils.math.random.Randomizer} to be used
   * by the optimization algorithm. This must be the only source of random
   * numbers to be used.
   *
   * @return the instance of
   *         {@link org.logisticPlanning.utils.math.random.Randomizer} to
   *         be used by the optimization algorithm
   */
  public final Randomizer getRandom() {
    return this.m_r;
  }

  /**
   * <p>
   * Should the optimization algorithm working on this objective function
   * terminate? It is required that you check whether this function becomes
   * {@code true} in any non-trivial loop of your optimization algorithm.
   * It may not be necessary to check it inside a loop that basically
   * traverses a candidate solution, but you should check
   * {@link #shouldTerminate()} in anything that has {@code O(nÂ²)}
   * complexity. As soon as {@link #shouldTerminate()} becomes {@code true}
   * , your algorithm should stop and return and cease creating new
   * solutions.
   * </p>
   * <p>
   * Runs of the optimization algorithms in TSP Suite have a limited time
   * budged (by default
   * {@value org.logisticPlanning.tsp.benchmarking.objective.Benchmark#DEFAULT_MAX_TIME_PER_RUN}
   * milliseconds, but also a limited budged of
   * {@link org.logisticPlanning.tsp.benchmarking.objective.Benchmark#PARAM_MAX_DES
   * DEs}) and
   * {@link org.logisticPlanning.tsp.benchmarking.objective.Benchmark#PARAM_MAX_FES
   * FEs}). Once these are exhausted, {@link #shouldTerminate()} becomes
   * {@code true}.
   * </p>
   * <p>
   * It will also become {@code true} if the optimization algorithm has
   * found the global optimum. This is a unique termination criterion
   * arising from the benchmarking situation where the global optima are
   * known. After a solution with the globally optimal tour length has been
   * discovered, the system would not collect further
   * {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint log
   * points} anymore, so it makes no sense to let the algorithm run any
   * further and waste computational time. Of course, in a real
   * application, the optimum would be unknown and such a termination
   * criterion would not exist. Since the algorithm does not know the
   * reason why {@link #shouldTerminate()} becomes {@code true} and is
   * commanded to stop as soon as it detects this, this issue plays no role
   * here.
   * </p>
   * <p>
   * Finally, an algorithm can also set {@link #shouldTerminate()} to
   * {@code true} by itself, by calling {@link #terminate()}. This may make
   * sense if it finds that it may not be able to achieve any further
   * improvement. {@link #shouldTerminate()} thus removes the need to have
   * any self-defined termination criterion or flag variables in your
   * algorithm.
   * </p>
   *
   * @return {@code true} if the run is completed and the algorithm can
   *         stop now, {@code false} if the algorithm should keep exploring
   *         new solutions
   * @see #terminate()
   */
  public final boolean shouldTerminate() {
    return this.m_terminate;
  }

  /**
   * With this method, the user/algorithm can tell the objective function
   * to set the terminate flag to {@code true}. After calling this method,
   * {@link #shouldTerminate()} will return {@code true}. This may make
   * sense in situations where the algorithm knows that it cannot make any
   * progress anymore. If an algorithm consists of complex nested loops or
   * recursion, using {@link #terminate()} removes the need of a member
   * variable, dedicated flag, or parameter in the algorithm class. Notive,
   * however, that there are other reasons for which
   * {@link #shouldTerminate()} can become {@code true} as well, such as
   * exhausting the computational budget of the experiment.
   *
   * @see #shouldTerminate()
   */
  public final void terminate() {
    this.m_terminate = true;
  }

  /**
   * Is the problem symmetric?
   *
   * @return {@code true} if the problem is symmetric, {@code false}
   *         otherwise
   */
  public final boolean symmetric() {
    return this.m_symmetric;
  }

  /** {@inheritDoc} */
  @Override
  public final void print(final PrintWriter out) {
    this.m_dist.print(out);
  }

  /** update the currently consumed runtime */
  final void _updateConsumedTime() {
    final long t;

    if (this.m_state != ObjectiveFunction.STATE_NOTHING) {

      // update the time variables
      t = System.currentTimeMillis();
      this.m_currentLP.m_time = ((t - this.m_startTime) + this.m_time_offset);
      if (t >= this.m_endTime) { // did we exceed the end time?
        this.m_terminate = true;
      }
    }
  }
}
