package org.logisticPlanning.tsp.benchmarking.objective;

import org.logisticPlanning.utils.math.data.point.Point;

/**
 *
 <p>
 * Instances of this class are used by the
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction
 * objective functions} in order to hold the information of exactly one
 * measuring point. They represent one point of logging information.
 * </p>
 * <p>
 * A {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint log
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
 * normalized time measure} tries to mitigate this effect: Before
 * performing an experiment, a
 * {@link org.logisticPlanning.tsp.benchmarking.objective._SpeedBenchmark
 * standardized algorithm} (
 * <em>{@link org.logisticPlanning.tsp.solving.algorithms.heuristics.doubleEndedNearestNeighbor.DoubleEndedNearestNeighborHeuristic double-ended nearest neighbor heuristic}</em>
 * &nbsp;[<a href="#cite_JMB1997TTSPACSILO" style="font-weight:bold">1</a>,
 * <a href="#cite_JMG2004EAOHFTS" style="font-weight:bold">2</a>]) is
 * applied to the problem instance (in the same environment and with the
 * same settings) and the time it needs is measured. The actual runtime of
 * the algorithm that we want to benchmark is then divided (normalized) by
 * this factor. Since the standardized algorithm is also a TSP solver and
 * has the same complexity ( <code>O(n<sup>2</sup>)</code>) as many simple
 * heuristics, this normalization process leads to time measurements that
 * are reasonably well comparable over different platforms. Another
 * advantage of this metric is that it automatically &quot;scales&quot;
 * with the problem size. (This information is only available to the
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
 * style="font-weight:bold">3</a>] (where instead evaluations of a
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
 * <h2>References</h2>
 * <ol>
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
 * </ol>
 */
public class LogPoint extends Point implements Comparable<LogPoint> {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * the index of the distance evaluations, to be used in the
   * {@link #get(int)} method
   */
  public static final int DE_INDEX = 0;
  /**
   * the index of the function evaluations, to be used in the
   * {@link #get(int)} method
   */
  public static final int FE_INDEX = (LogPoint.DE_INDEX + 1);
  /** the index of the time, to be used in the {@link #get(int)} method */
  public static final int TIME_INDEX = (LogPoint.FE_INDEX + 1);
  /**
   * the index of the objective value, to be used in the {@link #get(int)}
   * method
   */
  public static final int F_INDEX = (LogPoint.TIME_INDEX + 1);

  /** the dimension of the log points */
  public static final int LOG_POINT_DIMENSION = (LogPoint.F_INDEX + 1);

  /** toString 1 */
  private static final char[] TS1 = { 'f', '=' };

  /** toString 2 */
  private static final char[] TS2 = { ',', ' ', 'f', 'e', '=' };

  /** toString 3 */
  private static final char[] TS3 = { ',', ' ', 'd', 'e', '=' };

  /** toString 4 */
  private static final char[] TS4 = { ',', ' ', 't', 'i', 'm', 'e', '=' };
  /** toString 5 */
  private static final char[] TS5 = { 'm', 's' };

  /**
   * the consumed distance evaluations (DEs)
   *
   * @serial a positive long value with the consumed DEs
   */
  long m_de;

  /**
   * the consumed function evaluations (FEs)
   *
   * @serial a positive long value with the consumed FEs
   */
  long m_fe;

  /**
   * the consumed/passed runtime in milliseconds
   *
   * @serial a positive-or-zero long value with the consumed runtime
   */
  long m_time;

  /**
   * the objective value
   *
   * @serial a positive long value holding the (best-so-far) tour length
   */
  long m_f;

  /**
   * the log point type
   *
   * @serial an integer holding a bitmask indicating the log point type
   */
  int m_type;

  /** create! */
  LogPoint() {
    super();
    this.initExtreme();
  }

  /**
   * create by copying
   *
   * @param copy
   *          the point to copy
   */
  LogPoint(final LogPoint copy) {
    super();
    this._assign(copy);
  }

  /** initialize the log point to extreme values */
  final void initExtreme() {
    this.m_de = 0l;
    this.m_fe = 0l;
    this.m_time = 0l;
    this.m_f = Long.MAX_VALUE;
    this.m_type = 0;
  }

  /**
   * Get the number of FEs that were consumed since the run started.
   *
   * @return the number of FEs that were consumed since the run started
   */
  public final long getConsumedFEs() {
    return this.m_fe;
  }

  /**
   * Get the number of DEs that were consumed since the run started.
   *
   * @return the number of DEs that were consumed since the run started
   */
  public final long getConsumedDEs() {
    return this.m_de;
  }

  /**
   * Get the best objective value discovered until now (where
   * &quot;now&quot; is identified as the point in time at which
   * {@link #getConsumedDEs()} DEs and {@link #getConsumedFEs()} were
   * consumed).
   *
   * @return the best objective value discovered until now
   */
  public final long getBestF() {
    return this.m_f;
  }

  /**
   * Get an estimate of the amount of runtime in milliseconds consumed
   * until the current point in time.
   *
   * @return the amount of milliseconds consumed.
   */
  public long getConsumedRuntime() {
    return this.m_time;
  }

  /**
   * assign this log point to the values from another log point
   *
   * @param p
   *          the log point to assign to.
   */
  final void _assign(final LogPoint p) {
    this.m_de = p.m_de;
    this.m_f = p.m_f;
    this.m_fe = p.m_fe;
    this.m_time = p.m_time;
    this.m_type = p.m_type;
  }

  /**
   * Make a copy of the current information of this log point. The copy
   * will hold the information accessible via {@link #getBestF()},
   * {@link #getConsumedDEs()}, {@link #getConsumedFEs()}, and
   * {@link #getConsumedRuntime()}. This copy will be immutable, meaning
   * that its contents will never change.
   *
   * @return A copy holding the current data of this log point.
   */
  @Override
  public LogPoint clone() {
    try {
      return ((LogPoint) (super.clone()));
    } catch (final Throwable tt) { // this should never happen
      return new LogPoint(this);
    }
  }

  /**
   * the internal version of the {@link #toStringBuilder(StringBuilder)}
   * method
   *
   * @param sb
   *          the string builder
   */
  void _toStringBuilder(final StringBuilder sb) {
    sb.append(LogPoint.TS1);
    sb.append(this.m_f);
    sb.append(LogPoint.TS2);
    sb.append(this.m_fe);
    sb.append(LogPoint.TS3);
    sb.append(this.m_de);
    sb.append(LogPoint.TS4);
    sb.append(this.m_time);
    sb.append(LogPoint.TS5);
  }

  /** {@inheritDoc} */
  @Override
  protected final void toStringBuilder(final StringBuilder sb) {
    sb.append('(');
    this._toStringBuilder(sb);
    sb.append(')');
  }

  /**
   * Does this point equal to another log point?
   *
   * @param p
   *          the other point
   * @return {@code true} if and only if the point is identical to this
   *         point, {@code false} otherwise
   */
  public final boolean equalsLogPoint(final LogPoint p) {
    return ((p.m_de == this.m_de) && //
        (p.m_fe == this.m_fe) && //
        (p.m_time == this.m_time) && //
        (p.m_f == this.m_f) && //
        (p.m_type == this.m_type));
  }

  /**
   * Does this point equal to another point?
   *
   * @param p
   *          the other point
   * @return {@code true} if and only if the point is identical to this
   *         point, {@code false} otherwise
   */
  private final boolean __equalsPoint(final Point p) {
    if (p.dimension() != LogPoint.LOG_POINT_DIMENSION) {
      return false;
    }

    return ((this.m_de == p.get(LogPoint.DE_INDEX)) && //
        (this.m_fe == p.get(LogPoint.FE_INDEX)) && //
        (this.m_time == p.get(LogPoint.TIME_INDEX)) && //
        (this.m_f == p.get(LogPoint.F_INDEX)));
  }

  /** {@inheritDoc} */
  @Override
  public boolean equalsPoint(final Point p) {
    if (p instanceof LogPoint) {
      return this.equalsLogPoint((LogPoint) p);
    }
    return this.__equalsPoint(p);
  }

  /** {@inheritDoc} */
  @Override
  public boolean equals(final Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof LogPoint) {
      return this.equalsLogPoint((LogPoint) o);
    }
    if (o instanceof Point) {
      return this.__equalsPoint((Point) o);
    }
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    long de;
    int i;

    de = this.m_de;
    if (de <= 0l) {
      return 0;
    }

    de *= (1l + this.m_fe);
    de *= (1l + this.m_f);
    de *= (1l + this.m_time);
    de *= (1l + this.m_type);

    i = ((int) (de & 0xffffffffl));
    if (i != 0) {
      return i;
    }

    i = ((int) (de >>> 32));
    if (i != 0) {
      return i;
    }

    return 1;
  }

  /** {@inheritDoc} */
  @Override
  public final int compareTo(final LogPoint p) {
    int i;

    if (p == this) {
      return 0;
    }
    if (p == null) {
      return (-1);
    }

    i = Long.compare(this.m_de, p.m_de);
    if (i != 0) {
      return i;
    }

    i = Long.compare(this.m_fe, p.m_fe);
    if (i != 0) {
      return i;
    }

    i = Long.compare(this.m_time, p.m_time);
    if (i != 0) {
      return i;
    }

    return Long.compare(this.m_f, p.m_f);
  }

  /**
   * Obtain the value associated with the given dimension of the log point.
   *
   * @param dimension
   *          one of
   *          {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#DE_INDEX
   *          DE_INDEX},
   *          {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#FE_INDEX
   *          FE_INDEX},
   *          {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#TIME_INDEX
   *          TIME_INDEX}, and
   *          {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#F_INDEX
   *          F_INDEX}
   * @return the value of the measure {@code dimension} stored in this log
   *         point
   */
  @Override
  public double get(final int dimension) {
    switch (dimension) {
      case DE_INDEX: {
        return this.m_de;
      }
      case FE_INDEX: {
        return this.m_fe;
      }
      case TIME_INDEX: {
        return this.m_time;
      }
      case F_INDEX: {
        return ((this.m_f < Long.MAX_VALUE) ? this.m_f
            : Double.POSITIVE_INFINITY);
      }
      default: {
        throw new IndexOutOfBoundsException(String.valueOf(dimension));
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public int dimension() {
    return LogPoint.LOG_POINT_DIMENSION;
  }

}
