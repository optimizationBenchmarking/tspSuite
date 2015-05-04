package org.logisticPlanning.tsp.benchmarking.objective;

import org.logisticPlanning.utils.math.data.point.Point;

/**
 *
 <p>
 * A data point is basically a de-serialized
 * {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint
 * LogPoint}. Different from
 * {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint
 * LogPoint}, it also provides the relative objective value (
 * {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getBestF()
 * objective value} -
 * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#optimum()
 * optimum})/optimum, the {@link #getConsumedNormalizedRuntime() normalized
 * consumed runtime}, and information regarding why the log point was
 * collection. These information are not available to the optimization
 * processes, as such information would also not be known when solving an
 * unknown TSP problem. For evaluating the performance of an algorithm
 * after the run, they may be useful though.
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
 */
public final class DataPoint extends LogPoint {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * the index of the normalized objective value, to be used in the
   * {@link #get(int)} method
   */
  public static final int RELATIVE_F_INDEX = (LogPoint.LOG_POINT_DIMENSION);

  /**
   * the index of the normalized time, to be used in the {@link #get(int)}
   * method
   */
  public static final int NORMALIZED_TIME_INDEX = (DataPoint.RELATIVE_F_INDEX + 1);

  /** the dimension of the data points */
  public static final int DATA_POINT_DIMENSION = (DataPoint.NORMALIZED_TIME_INDEX + 1);

  /** toString 6 */
  private static final char[] TS6 = { ',', ' ', 'r', 'f', '=' };
  /** toString 7 */
  private static final char[] TS7 = { ',', ' ', 'n', 't', 'i', 'm', 'e',
  '=' };

  /**
   * the relative objective value, measured as
   * {@code (absolute objective value - optimum)/optimum}
   *
   * @serial a double with the relative objective value, a value greater or
   *         equal to {@code 0}
   */
  private double m_relF;

  /**
   * the normalized runtime
   *
   * @serial the normalized runtime: the runtime of the algorithm divided
   *         by the runtime of a
   *         {@link org.logisticPlanning.tsp.benchmarking.objective._SpeedBenchmark
   *         standard algorithm}, a positive double value
   */
  private double m_normTime;

  /**
   * Create a new data point with the following information:
   *
   * @param fes
   *          the consumed function evaluations
   * @param des
   *          the consumed distance evaluations
   * @param time
   *          the elapsed runtime
   * @param f
   *          the achieved objective function value
   * @param relF
   *          the relativized achieved objective value
   * @param normTime
   *          the normalized consumed time
   * @param reasonFE
   *          the point was generated because a specific number function
   *          evaluations has been performed
   * @param reasonDE
   *          the point was generated because a specific number distance
   *          evaluations has been performed
   * @param reasonF
   *          the point was generated because a objective value goal limit
   *          has been reached
   * @param reasonInitEnd
   *          the point was generated because the initialization procedure
   *          has ended
   * @param reasonAlgoEnd
   *          the point was generated because the algorithm has finished
   */
  public DataPoint(final long fes, final long des, final long time,
      final long f, final double relF, final double normTime,
      final boolean reasonFE, final boolean reasonDE,
      final boolean reasonF, final boolean reasonInitEnd,
      final boolean reasonAlgoEnd) {
    this();

    this.m_fe = fes;
    this.m_de = des;
    this.m_f = f;
    this.m_time = time;
    this.m_relF = ((fes <= 0l) ? Double.POSITIVE_INFINITY : relF);
    this.m_normTime = normTime;

    this.m_type = 0;
    if (reasonFE) {
      this.m_type |= ObjectiveFunction.TYPE_FLAG_FE;
    }
    if (reasonDE) {
      this.m_type |= ObjectiveFunction.TYPE_FLAG_DE;
    }
    if (reasonF) {
      this.m_type |= ObjectiveFunction.TYPE_FLAG_OBJECTIVE;
    }
    if (reasonInitEnd) {
      this.m_type |= ObjectiveFunction.TYPE_FLAG_INIT_END;
    }
    if (reasonAlgoEnd) {
      this.m_type |= ObjectiveFunction.TYPE_FLAG_END;
    }

    DataPoint.validateDataPoint(this);
  }

  /** the data point */
  DataPoint() {
    super();
  }

  /**
   * Returns whether this data point was collected before a single
   * candidate solution was evaluated, i.e.,
   * <code>{@link #getConsumedFEs()}&leq;0</code> ,
   * <code>{@link #getRelBestF()}&geq;{@link java.lang.Double#POSITIVE_INFINITY &#x221E;}</code>
   * , and
   * <code>{@link #getBestF()}&geq;{@link java.lang.Long#MAX_VALUE &#x221E;}</code>
   * . There is only one reason why this could have happened: The runtime
   * limit of the run was exhausted before a single solution was created. A
   * run where this is {@code true} will only hold a single log point, and
   * this single log point will have
   * <code>{@link #isPrematureTermination()}==true</code>.
   *
   * @return {@code true} if the log point signifies a prematurely
   *         terminated run, i.e., a run that did not even generate a
   *         single solution, {@code false} if it is a normal log point
   */
  public final boolean isPrematureTermination() {
    return (this.m_fe <= 0l);
  }

  /**
   * Get the relative objective value
   *
   * @return the relative objective value
   */
  public final double getRelBestF() {
    return this.m_relF;
  }

  /**
   * Get the normalized runtime.
   *
   * @return the normalized runtime
   */
  public final double getConsumedNormalizedRuntime() {
    return this.m_normTime;
  }

  /**
   * Was one of the reasons for logging this point that an objective
   * function threshold was surpassed?
   *
   * @return {@code true} if an objective function threshold was the reason
   *         for logging this point, {@code false} otherwise
   */
  public final boolean logReasonObjective() {
    return ((this.m_type & ObjectiveFunction.TYPE_FLAG_OBJECTIVE) != 0);
  }

  /**
   * Was one of the reasons for logging this point that an FE threshold was
   * surpassed?
   *
   * @return {@code true} if an FE threshold was the reason for logging
   *         this point, {@code false} otherwise
   */
  public final boolean logReasonFE() {
    return ((this.m_type & ObjectiveFunction.TYPE_FLAG_FE) != 0);
  }

  /**
   * Was one of the reasons for logging this point that an DE threshold was
   * surpassed?
   *
   * @return {@code true} if an DE threshold was the reason for logging
   *         this point, {@code false} otherwise
   */
  public final boolean logReasonDE() {
    return ((this.m_type & ObjectiveFunction.TYPE_FLAG_DE) != 0);
  }

  /**
   * Was one of the reasons for logging this point that the algorithm
   * terminated?
   *
   * @return {@code true} if algorithm termination was the reason for
   *         logging this point, {@code false} otherwise
   */
  public final boolean logReasonEnd() {
    return ((this.m_type & ObjectiveFunction.TYPE_FLAG_END) != 0);
  }

  /**
   * Was one of the reasons for logging this point that the initialization
   * algorithm terminated?
   *
   * @return {@code true} if initialization algorithm termination was the
   *         reason for logging this point, {@code false} otherwise
   */
  public final boolean logReasonInitEnd() {
    return ((this.m_type & ObjectiveFunction.TYPE_FLAG_INIT_END) != 0);
  }

  /**
   * As this object is immutable, cloning it will return the object itself.
   *
   * @return {@code this}
   */
  @Override
  public final DataPoint clone() {
    return this;
  }

  /**
   * Obtain the value associated with the given dimension of the data
   * point.
   *
   * @param dimension
   *          one of
   *          {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#DE_INDEX
   *          DE_INDEX},
   *          {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#FE_INDEX
   *          FE_INDEX},
   *          {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#TIME_INDEX
   *          TIME_INDEX},
   *          {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#F_INDEX
   *          F_INDEX},
   *          {@link org.logisticPlanning.tsp.benchmarking.objective.DataPoint#RELATIVE_F_INDEX
   *          RELATIVE_F_INDEX}, and
   *          {@link org.logisticPlanning.tsp.benchmarking.objective.DataPoint#NORMALIZED_TIME_INDEX
   *          NORMALIZED_TIME_INDEX}.
   * @return the value of the measure {@code dimension} stored in this data
   *         point
   */
  @Override
  public final double get(final int dimension) {
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
      case RELATIVE_F_INDEX: {
        return this.m_relF;
      }
      case NORMALIZED_TIME_INDEX: {
        return this.m_normTime;
      }
      default: {
        throw new IndexOutOfBoundsException(String.valueOf(dimension));
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public final int dimension() {
    return DataPoint.DATA_POINT_DIMENSION;
  }

  /** {@inheritDoc} */
  @Override
  final void _toStringBuilder(final StringBuilder sb) {
    super._toStringBuilder(sb);
    sb.append(DataPoint.TS6);
    sb.append(this.m_relF);
    sb.append(DataPoint.TS7);
    sb.append(this.m_normTime);
  }

  /**
   * Does this point equal to another data point?
   *
   * @param p
   *          the other point
   * @return {@code true} if and only if the point is identical to this
   *         point, {@code false} otherwise
   */
  private final boolean __equalsDataPoint(final DataPoint p) {
    return ((p.m_de == this.m_de) && //
        (p.m_fe == this.m_fe) && //
        (p.m_time == this.m_time) && //
        (p.m_f == this.m_f) && //
        (p.m_type == this.m_type) && //
        (Double.doubleToLongBits(this.m_relF) == //
        Double.doubleToLongBits(p.m_relF)) && //
        (Double.doubleToLongBits(this.m_normTime) == //
        Double.doubleToLongBits(p.m_normTime)));
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
        (this.m_f == p.get(LogPoint.F_INDEX)) && //
        (Double.doubleToLongBits(this.m_relF) == //
        Double.doubleToLongBits(p.get(DataPoint.RELATIVE_F_INDEX))) && //
        (Double.doubleToLongBits(this.m_normTime) == //
        Double.doubleToLongBits(DataPoint.NORMALIZED_TIME_INDEX)));
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equalsPoint(final Point p) {
    if (p instanceof DataPoint) {
      return this.__equalsDataPoint((DataPoint) p);
    }
    return this.__equalsPoint(p);
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equals(final Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof DataPoint) {
      return this.__equalsDataPoint((DataPoint) o);
    }
    if (o instanceof Point) {
      return this.__equalsPoint((Point) o);
    }
    return false;
  }

  /**
   * Validate a
   * {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint log
   * point} : check if it is sane and could have occurred in this way in a
   * run. If the log point is a
   * {@link org.logisticPlanning.tsp.benchmarking.objective.DataPoint data
   * point}, the additional fields will also be validated.
   *
   * @param point
   *          the log point
   * @throws IllegalArgumentException
   *           if the log point contains an error
   */
  public static final void validateLogPoint(final LogPoint point) {
    if (point instanceof DataPoint) {
      DataPoint.validateDataPoint((DataPoint) point);
    } else {
      DataPoint.__validateLogPoint(point);
    }
  }

  /**
   * Validate a
   * {@link org.logisticPlanning.tsp.benchmarking.objective.DataPoint data
   * point}.
   *
   * @param point
   *          the log point
   * @throws IllegalArgumentException
   *           if the data point contains an error
   */
  public static final void validateDataPoint(final DataPoint point) {
    DataPoint.__validateLogPoint(point);

    if (point.m_relF < 0d) {
      throw new IllegalArgumentException(//
          "Relative objective value, i.e., relative deviation from global optimum, can never be less than zero, but is " + point.m_relF); //$NON-NLS-1$
    }
    if ((point.m_relF >= Long.MAX_VALUE) && (point.m_fe > 0l)) {
      throw new IllegalArgumentException(//
          "If at least one objective function has been performed, the best _relative_ objective value must be better than Long.MAX_VALUE. However, the fe index is "//$NON-NLS-1$
          + point.m_fe + " an the objective value is " + point.m_relF); //$NON-NLS-1$
    }

    if ((point.m_fe <= 0l) && (point.m_relF < Double.POSITIVE_INFINITY)) {
      throw new IllegalArgumentException(//
          "No function evaluation was performed, so the best known relative objective value must be infinite. However, it is "//$NON-NLS-1$
          + point.m_relF);
    }

    if (point.m_normTime < 0d) {
      throw new IllegalArgumentException(//
          "Normalized runtime can never be less than zero, but is " + point.m_normTime); //$NON-NLS-1$
    }
  }

  /**
   * Validate a
   * {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint log
   * point} : check if it is sane and could have occurred in this way in a
   * run.
   *
   * @param point
   *          the log point
   * @return {@code true} if the point belongs to a prematurely terminated
   *         run, {@code false} otherwise
   * @throws IllegalArgumentException
   *           if the log point contains an error
   */
  private static final boolean __validateLogPoint(final LogPoint point) {
    boolean prematureTermination;

    if (point.m_type == 0) {
      throw new IllegalArgumentException(//
          "There must be a reason for the log points existence, but none is specified in point " + point); //$NON-NLS-1$
    }

    prematureTermination = false;
    if (point.m_fe <= 0l) {
      if (point.m_fe >= 0l) {
        if ((point.m_type & ObjectiveFunction.TYPE_FLAG_END) != 0) {
          prematureTermination = true;
        } else {
          throw new IllegalArgumentException(//
              "Function evaluation index of a log point can only be 0 if the algorithm has terminated (before completing 1 FE)."); //$NON-NLS-1$
        }
      } else {
        throw new IllegalArgumentException(//
            "Function evaluation index cannot be negative, but is " + point.m_fe); //$NON-NLS-1$
      }
    }

    if (point.m_de <= 0l) {
      if (point.m_de >= 0l) {
        if ((point.m_type & ObjectiveFunction.TYPE_FLAG_END) != 0) {
          if (point.m_fe > 0l) {
            throw new IllegalArgumentException(//
                "Function evaluation index cannot be greater than zeo if distance evaluation index is 0, but is " + point.m_fe); //$NON-NLS-1$
          }
          prematureTermination = true;
        } else {
          throw new IllegalArgumentException(//
              "Distance evaluation index of a log point can only be 0 if the algorithm has terminated (before completing 1 FE)."); //$NON-NLS-1$
        }
      } else {
        throw new IllegalArgumentException(//
            "Distance evaluation index cannot be negative, but is " + point.m_de); //$NON-NLS-1$
      }
    }

    if (point.m_time < 0l) {
      throw new IllegalArgumentException(//
          "Consumed runtime can never be less than zero, but is " + point.m_time); //$NON-NLS-1$
    }

    if (point.m_time > 40000000000l) {
      throw new IllegalArgumentException(//
          "Consumed runtime (measured in milliseconds) is suspiciously high: " + //$NON-NLS-1$
          point.m_time
          + ", which is approximately " + (point.m_time / 31557600000l) + //$NON-NLS-1$
          "years... I will not allow you this ^_^");//$NON-NLS-1$
    }

    if (point.m_f <= 0l) {
      throw new IllegalArgumentException(//
          "Objective value, i.e., total travel distance, can never be less than 1 but is " + point.m_f); //$NON-NLS-1$
    }
    if ((point.m_f >= Long.MAX_VALUE) && (point.m_fe > 0l)) {
      throw new IllegalArgumentException(//
          "If at least one objective function has been performed, the best known objective value must be better than Long.MAX_VALUE. However, the fe index is "//$NON-NLS-1$
          + point.m_fe + " an the objective value is " + point.m_f); //$NON-NLS-1$
    }

    if (prematureTermination) {
      if (point.m_f < Long.MAX_VALUE) {
        throw new IllegalArgumentException(//
            "The algorithm has terminated without performing a single function evaluation, so the absolute travel distance must be Long.MAX_VALUE, but is " + point.m_f); //$NON-NLS-1$
      }
    }

    return prematureTermination;
  }

  /**
   * Check if a given log point {@code after} can follow a log point
   * {@code before} in a single run. Both points will also be validated.
   *
   * @param before
   *          the log point before (or {@code null} if not given)
   * @param after
   *          the log point after (must not be {@code null})
   */
  public static final void checkSequence(final LogPoint before,
      final LogPoint after) {

    if ((before instanceof DataPoint) && (after instanceof DataPoint)) {
      DataPoint.checkSequence(((DataPoint) before), ((DataPoint) after));
    }

    DataPoint.__validateLogPoint(after);
    if (before != null) {
      DataPoint.__validateLogPoint(before);
    } else {
      return;
    }

    DataPoint.__checkSequence(before, after);
  }

  /**
   * Check if a given log point {@code after} can follow a log point
   * {@code before} in a single run
   *
   * @param before
   *          the log point before (must not be {@code null})
   * @param after
   *          the log point after (must not be {@code null})
   */
  private static final void __checkSequence(final LogPoint before,
      final LogPoint after) {

    if (before.m_de > after.m_de) {
      throw new IllegalArgumentException(//
          "Number of distance evaluations cannot decrease, but " + //$NON-NLS-1$
          after.m_de + " follows " + before.m_de);//$NON-NLS-1$
    }

    if (before.m_fe > after.m_fe) {
      throw new IllegalArgumentException(//
          "Number of function evaluations cannot decrease, but " + //$NON-NLS-1$
          after.m_fe + " follows " + before.m_fe);//$NON-NLS-1$
    }

    if (before.m_time > after.m_time) {
      throw new IllegalArgumentException(//
          "Passed runtime cannot decrease, but " + //$NON-NLS-1$
          after.m_time + " follows " + before.m_time);//$NON-NLS-1$
    }

    if (before.m_f < after.m_f) {
      throw new IllegalArgumentException(//
          "Objective value of best known solution can never increase, but " + //$NON-NLS-1$
          after.m_f + " follows " + before.m_f);//$NON-NLS-1$
    }

    if ((before.m_type & ObjectiveFunction.TYPE_FLAG_END) != 0) {
      throw new IllegalArgumentException(//
          "No log point can follow a log point with log reason 'end'.");//$NON-NLS-1$
    }

    if (((before.m_type & ObjectiveFunction.TYPE_FLAG_INIT_END) != 0) && //
        ((after.m_type & ObjectiveFunction.TYPE_FLAG_INIT_END) != 0)) {
      throw new IllegalArgumentException(//
          "No two log points can have the reason 'init end'.");//$NON-NLS-1$
    }

    if (((after.m_type & ObjectiveFunction.TYPE_FLAG_OBJECTIVE) != 0) && //
        (after.m_f >= before.m_f)) {
      throw new IllegalArgumentException(//
          "If the log reason is 'objective value limit reached', then the objective value must have improved - but both values are " //$NON-NLS-1$
          + before.m_f);
    }

    if (((after.m_type & ObjectiveFunction.TYPE_FLAG_DE) != 0) && //
        (after.m_de <= before.m_de)) {
      throw new IllegalArgumentException(//
          "If the log reason is 'distance evaluation limit reached', then the number of distance evaluations must have increased - but both values are " //$NON-NLS-1$
          + before.m_de);
    }

    if (((after.m_type & ObjectiveFunction.TYPE_FLAG_FE) != 0) && //
        (after.m_fe <= before.m_fe)) {
      throw new IllegalArgumentException(//
          "If the log reason is 'objective function evaluation limit reached', then the number of objective evaluations must have increased - but both values are " //$NON-NLS-1$
          + before.m_fe);
    }

    if ((after.m_type & ObjectiveFunction.TYPE_FLAG_END) == 0) {
      if ((after.m_de <= before.m_de) && (after.m_fe <= before.m_fe)
          && (after.m_f >= before.m_f)) {
        throw new IllegalArgumentException(//
            "If the log reason of the second point is not 'algorithm termination', then there must be another reason, i.e., either the number of distance or objective function evaluations must be larger or the objective function must be better than the first point. However, the points are " //$NON-NLS-1$
            + before.toString() + " and " + after.toString());//$NON-NLS-1$
      }
    }
  }

  /**
   * Check if a given data point {@code after} and follow a log point
   * {@code before} in a single run. Both points will also be validated.
   *
   * @param before
   *          the log point before (or {@code null} if not given)
   * @param after
   *          the log point after (must not be {@code null})
   */
  public static final void checkSequence(final DataPoint before,
      final DataPoint after) {

    DataPoint.validateDataPoint(after);

    if (before != null) {
      DataPoint.validateDataPoint(before);
    } else {
      return;
    }

    DataPoint.__checkSequence(before, after);

    if (before.m_relF < after.m_relF) {
      throw new IllegalArgumentException(//
          "Quality of best solutions can never decrease, but relative quality " + //$NON-NLS-1$
          after.m_relF + " follows " + before.m_relF);//$NON-NLS-1$
    }
    if ((after.m_f < before.m_f) && (after.m_relF >= before.m_relF)) {
      throw new IllegalArgumentException(//
          ((((((("If there is an improvement in objective value, there also must be an improvement in relative objective value, but the objective values are (" + //$NON-NLS-1$
              before.m_f) + ',') + after.m_relF) + ") and the relative values are (") + //$NON-NLS-1$
              before.m_relF) + ',') + after.m_relF) + ')');
    }

    if (before.m_normTime > after.m_normTime) {
      throw new IllegalArgumentException(//
          "Passed (normalized) runtime cannot decrease, but " + //$NON-NLS-1$
          after.m_normTime + " follows " + before.m_normTime);//$NON-NLS-1$
    }
    if ((after.m_time > before.m_time)
        && (after.m_normTime <= before.m_normTime)) {
      throw new IllegalArgumentException(//
          ((((((("If there is an increase in consumed runtime, there also must be an increase in normalized consumed runtime, but the runtime values are (" + //$NON-NLS-1$
              before.m_time) + ',') + after.m_time) + ") and the normalized runtime values are (") + //$NON-NLS-1$
              before.m_normTime) + ',') + after.m_normTime) + ')');
    }
  }
}
