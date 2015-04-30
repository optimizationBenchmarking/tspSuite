package org.logisticPlanning.tsp.benchmarking.objective;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.solving.TSPAlgorithm;

/**
 * <p>
 * This class is an internal class. Please do not instantiate it, use it by
 * yourself, or otherwise meddle with it. This class provides a way to
 * measure the speed of the computer / the performance available to the
 * calling thread.
 * </p>
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
 * instance. This time is then used to
 * {@link org.logisticPlanning.tsp.benchmarking.objective.DataPoint#getConsumedNormalizedRuntime()
 * normalize} the measured time of any other algorithm (for exactly that
 * instance, with exactly that settings and distance metric). If we divide
 * the runtime a given program needs to solve a given problem instance (or
 * the runtimes stored in its
 * {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint log
 * points}) with the runtime that the standardized algorithm needs for the
 * same instance, we get a useful time measure that can be compared over
 * different platforms. This approach is similar to the one in the DIMACS
 * challenge on TSPs&nbsp;[<a href="#cite_JMG20088DICTTSP"
 * style="font-weight:bold">1</a>, <a href="#cite_JMG2004EAOHFTS"
 * style="font-weight:bold">2</a>], but is different in that it will be
 * performed for each instance directly before the optimization runs and
 * using the same settings (such as distance information representations).
 * </p>
 * <p>
 * The &quot;standardized&quot; algorithm (implemented in this class here)
 * we use to perform that measurement is the
 * <em>{@link org.logisticPlanning.tsp.solving.algorithms.heuristics.doubleEndedNearestNeighbor.DoubleEndedNearestNeighborHeuristic double-ended nearest neighbor heuristic}</em>
 * &nbsp;[<a href="#cite_JMB1997TTSPACSILO" style="font-weight:bold">3</a>,
 * <a href="#cite_JMG2004EAOHFTS" style="font-weight:bold">2</a>]. This
 * algorithm is TSP solver, so its structure and operations should be
 * somewhat similar to other TSP solvers. It is furthermore deterministic,
 * relatively simple, and has a complexity of <code>O(n<sup>2</sup>)</code>
 * , which is the same complexity as most simple
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
 * &nbsp;[<a href="#cite_JMB1997TTSPACSILO" style="font-weight:bold">3</a>,
 * <a href="#cite_JMG2004EAOHFTS" style="font-weight:bold">2</a>]) used for
 * measuring the benchmarks is given as:
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
 * style="font-weight:bold">4</a>, <a href="#cite_O2010DGUJCAO"
 * style="font-weight:bold">5</a>]. At any point in time, it may perform
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
 * function, it can also be used to
 * {@link org.logisticPlanning.tsp.benchmarking.objective.DataPoint#getConsumedNormalizedRuntime()
 * normalize} away impacts of the way the
 * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
 * distance information} of the problem is represented (distance matrix?
 * coordinate list?) as well as impact on the problem scale.
 * </p>
 * <p>
 * The
 * <em>{@link org.logisticPlanning.tsp.solving.algorithms.heuristics.doubleEndedNearestNeighbor.DoubleEndedNearestNeighborHeuristic double-ended nearest neighbor heuristic}</em>
 * &nbsp;[<a href="#cite_JMB1997TTSPACSILO" style="font-weight:bold">3</a>,
 * <a href="#cite_JMG2004EAOHFTS" style="font-weight:bold">2</a>] used for
 * obtaining the normalization factor proceeds as follows: It starts with
 * node {@code 1} as initial partial tour. It then iteratively finds the
 * nearest neighboring nodes of each end of the tour and adds the nearest
 * (of the two) until the tour is completed (all nodes are present).
 * </p>
 * <h2>References</h2>
 * <ol>
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
 * </ol>
 */
final class _SpeedBenchmark extends TSPAlgorithm {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the object used for locking */
  private static final Object LOCK = new Object();

  /** the globally shared, internally hidden instance */
  private static _SpeedBenchmark INSTANCE;

  /** instantiate */
  private _SpeedBenchmark() {
    super("Double-Ended Nearest Neighbor Heuristic for Speed Test"); //$NON-NLS-1$
  }

  /**
   * <p>
   * Get the instance.
   * </p>
   * <p>
   * The Java Virtual Machine may either run the program code in an
   * interpreted way or (or later) compile it to machine code using a
   * Just-In-Time (JIT) compiler&nbsp;[<a href="#cite_S2003JPT"
   * style="font-weight:bold">4</a>, <a href="#cite_O2010DGUJCAO"
   * style="font-weight:bold">5</a>]. At any point in time, it may perform
   * online optimization to speed up the benchmark's algorithm, such as
   * switching from interpreted to compiled code, inlining and unwinding
   * loops, etc. The compilation by the JIT usually takes place after a few
   * thousand invocations, if it takes place. Therefore, the first few
   * executions of the algorithm may need a significantly different time
   * than the latter runs. In order to mitigate this problem and to enforce
   * the optimizations to happen before the experiment, we will call the
   * benchmarking function here 200000 times on a small problem instance (
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BURMA14
   * BURMA14}).
   * </p>
   * 
   * @return the instance
   */
  private static final _SpeedBenchmark instance() {
    final Benchmark b;
    final ObjectiveFunction f;
    int i;

    synchronized (_SpeedBenchmark.LOCK) {
      if (_SpeedBenchmark.INSTANCE == null) {
        _SpeedBenchmark.INSTANCE = new _SpeedBenchmark();

        b = new Benchmark(Instance.BURMA14);
        b.setMaxDEs(Long.MAX_VALUE);
        b.setMaxFEs(Long.MAX_VALUE);
        b.setMaxRuntime(Long.MAX_VALUE);
        b._init(false);
        f = new ObjectiveFunction(b);

        f._beginRun(_SpeedBenchmark.INSTANCE, false);
        for (i = 200000; (--i) >= 0;) {
          _SpeedBenchmark.INSTANCE.solve(f);
        }
        f._clear();
      }

      return _SpeedBenchmark.INSTANCE;
    }
  }

  /**
   * The
   * <em>{@link org.logisticPlanning.tsp.solving.algorithms.heuristics.doubleEndedNearestNeighbor.DoubleEndedNearestNeighborHeuristic double-ended nearest neighbor heuristic}</em>
   * &nbsp;[<a href="#cite_JMB1997TTSPACSILO"
   * style="font-weight:bold">3</a>, <a href="#cite_JMG2004EAOHFTS"
   * style="font-weight:bold">2</a>] used to measure the performance of the
   * system on which we perform the experiments.
   * 
   * @param f
   *          the objective function
   */
  @Override
  public final void solve(final ObjectiveFunction f) {
    final int n, addB;
    int aIdx, bIdx, rem, lastA, lastB, bestIdxA, bestA, bestLenA, bestIdxB, bestLenB, bestB, tempLen, tempNode, i;
    int[] temp, res;
    long length;

    // allocate node list
    n = f.n();
    temp = new int[n];
    for (aIdx = 0; aIdx < n;) {
      temp[aIdx] = (++aIdx);
    }

    // allocate result array
    res = new int[n];

    length = 0l;

    aIdx = bIdx = 0;
    rem = addB = (n - 1);
    res[0] = lastA = lastB = 1;
    temp[0] = temp[rem];
    bestIdxA = bestIdxB = bestA = bestB = (-1);
    bestLenA = bestLenB = tempLen = Integer.MAX_VALUE;

    for (; rem > 0;) {

      // find the best end extensions
      for (i = rem; (--i) >= 0;) {
        tempNode = temp[i];

        // find the best node at end A
        if (bestA <= 0) {
          tempLen = f.distance(lastA, tempNode);
          if (tempLen <= bestLenA) {
            bestLenA = tempLen;
            bestA = tempNode;
            bestIdxA = i;
          }
        }

        // find the best node at end B (except in first round)
        if ((bestB <= 0) && (lastB != lastA)) {
          tempLen = f.distance(lastB, tempNode);
          if (tempLen <= bestLenB) {
            bestLenB = tempLen;
            bestB = tempNode;
            bestIdxB = i;
          }
        }
      }

      // ok, which end offers the better extension?
      if (bestLenA <= bestLenB) { // adding a node at end A seems to be
        // better
        aIdx++; // increase index
        res[aIdx] = lastA = bestA; // store node
        length += bestLenA; // add new edge's length to total length
        temp[bestIdxA] = temp[--rem]; // make node unavailable for
        // future adding
        if (rem == bestIdxB) { // if the moved node is the best b
          // option?
          bestIdxB = bestIdxA; // correct b option index
        }
        if (bestB == bestA) { // if the same node could be added at both
          // ends
          bestIdxB = bestB = (-1); // we also need to re-calculate b
          bestLenB = Integer.MAX_VALUE;
        }
        bestIdxA = bestA = (-1); // we need to re-calculate a
        bestLenA = Integer.MAX_VALUE;
      } else { // ok, best choice is end b
        bIdx = ((bIdx + addB) % n); // decrease index
        res[bIdx] = lastB = bestB; // store node
        length += bestLenB; // add new edge's length to total length
        temp[bestIdxB] = temp[--rem]; // make node unavailable for
        // future adding
        if (rem == bestIdxA) { // if the moved node is the best a
          // option?
          bestIdxA = bestIdxB; // correct a option index
        }
        if (bestB == bestA) { // if the same node could be added at both
          // ends
          bestIdxA = bestA = (-1); // we also need to re-calculate a
          bestLenA = Integer.MAX_VALUE;
        }
        bestIdxB = bestB = (-1); // we need to re-calculate b
        bestLenB = Integer.MAX_VALUE;
      }
    }

    // add distance of edge connecting both tour ends
    length += f.distance(lastA, lastB);
    f.registerFE(res, length); // register result
  }

  /**
   * Perform the time benchmark run one time
   * 
   * @param f
   *          the objective function
   * @return the result
   */
  static final double _timeBenchmarkRun(final ObjectiveFunction f) {
    final _SpeedBenchmark instance;
    long time, startTime;
    double factor, bestFactor;
    int runs, calls, minCalls;

    bestFactor = Double.POSITIVE_INFINITY;
    instance = _SpeedBenchmark.instance();
    minCalls = -1;

    for (runs = 10; (--runs) >= 0;) {

      calls = 0;
      f._beginRun(instance, false);
      startTime = System.currentTimeMillis();

      loopy: for (;;) {
        instance.solve(f);
        if ((++calls) >= minCalls) {
          time = (System.currentTimeMillis() - startTime);
          if (time > 30l) {
            break loopy;
          }
        }
      }
      f._clear();

      if (calls >= minCalls) {
        minCalls = calls;
      }
      factor = (time / ((double) calls));
      if (factor < bestFactor) {
        bestFactor = factor;
      }
    }

    return bestFactor;
  }

}
