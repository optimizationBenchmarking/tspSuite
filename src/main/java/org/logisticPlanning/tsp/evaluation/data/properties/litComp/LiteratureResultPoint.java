package org.logisticPlanning.tsp.evaluation.data.properties.litComp;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.utils.math.statistics.EStatisticParameter;

/**
 * the literature result
 */
public class LiteratureResultPoint implements
    Comparable<LiteratureResultPoint> {

  /** the result point set */
  LiteratureResultPointSet m_set;

  /** the instance */
  final Instance m_inst;

  /** the time dimension */
  final Accessor m_timeDim;

  /** the data point */
  final ResPoint m_data;

  /** the result dimension */
  final Accessor m_resultDim;

  /** the result statistics */
  final EStatisticParameter m_resultStatistic;

  /**
   * the literature result
   *
   * @param inst
   *          the instance
   * @param timeDim
   *          the time dimension, must be one of
   *          {@link org.logisticPlanning.tsp.evaluation.data.Accessor#DE},
   *          {@link org.logisticPlanning.tsp.evaluation.data.Accessor#FE},
   *          {@link org.logisticPlanning.tsp.evaluation.data.Accessor#RUNTIME}
   *          , or or
   *          {@link org.logisticPlanning.tsp.evaluation.data.Accessor#NORMALIZED_RUNTIME}
   * @param timeVal
   *          the time value
   * @param resultDim
   *          the result dimension, must be one of
   *          {@link org.logisticPlanning.tsp.evaluation.data.Accessor#F}
   *          or
   *          {@link org.logisticPlanning.tsp.evaluation.data.Accessor#F_RELATIVE}
   * @param resultVal
   *          the result value
   * @param resultStatistic
   *          the result statistic
   */
  public LiteratureResultPoint(final Instance inst,
      final Accessor timeDim, final double timeVal,
      final Accessor resultDim, final double resultVal,
      final EStatisticParameter resultStatistic) {
    super();

    if (inst == null) {
      throw new IllegalArgumentException(//
          "Instance must not be null."); //$NON-NLS-1$
    }

    if (timeDim.isObjective()) {
      throw new IllegalArgumentException(
          "Time dimension must actually (surprise) be a time dimension, but is '" + //$NON-NLS-1$
              timeDim + '\'');
    }

    if (!(resultDim.isObjective())) {
      throw new IllegalArgumentException(
          "The result dimension must actually an result/objective dimension, but is '" + //$NON-NLS-1$
              resultDim + '\'');
    }

    if (!(resultStatistic.isValue())) {
      throw new IllegalArgumentException(
          "Only value parameters are allowed, but the result was said to be "//$NON-NLS-1$
              + resultStatistic);
    }

    if (resultDim == Accessor.F) {
      if (resultVal < inst.optimum()) {
        throw new IllegalArgumentException(//
            "Provided objective value " + resultVal + //$NON-NLS-1$
                " ("//$NON-NLS-1$
                + resultStatistic.getShortName()
                + ") is less than globally optimal objective value "//$NON-NLS-1$
                + inst.optimum() + " of " + inst.name());//$NON-NLS-1$
      }
    } else {
      if (resultDim == Accessor.F_RELATIVE) {
        if (resultVal < 0d) {
          throw new IllegalArgumentException(//
              "Provided relative error " + resultVal + //$NON-NLS-1$
                  " (" + resultStatistic.getShortName() + //$NON-NLS-1$
                  ") is less than 0."); //$NON-NLS-1$
        }
      }
    }

    this.m_inst = inst;
    this.m_timeDim = timeDim;
    this.m_data = new ResPoint(timeVal, resultVal);
    this.m_resultDim = resultDim;
    this.m_resultStatistic = resultStatistic;
  }

  /**
   * Get the time dimension. Will be one of
   * {@link org.logisticPlanning.tsp.evaluation.data.Accessor#DE},
   * {@link org.logisticPlanning.tsp.evaluation.data.Accessor#FE},
   * {@link org.logisticPlanning.tsp.evaluation.data.Accessor#RUNTIME} , or
   * or
   * {@link org.logisticPlanning.tsp.evaluation.data.Accessor#NORMALIZED_RUNTIME}
   * .
   *
   * @return the time dimension
   */
  public final Accessor getTimeDimension() {
    return this.m_timeDim;
  }

  /**
   * Get the time and quality value
   *
   * @return the time and quality value
   */
  public final ResPoint getData() {
    return this.m_data;
  }

  /**
   * Get the result dimension, must be either
   * {@link org.logisticPlanning.tsp.evaluation.data.Accessor#F} or
   * {@link org.logisticPlanning.tsp.evaluation.data.Accessor#F_RELATIVE} .
   *
   * @return the result dimension
   */
  public final Accessor getResultDimension() {
    return this.m_resultDim;
  }

  /**
   * Get the problem instance
   *
   * @return the problem instance
   */
  public final Instance getInstance() {
    return this.m_inst;
  }

  /**
   * Obtain the result statistic parameter
   *
   * @return the result statistic parameter
   */
  public final EStatisticParameter getResultStatistic() {
    return this.m_resultStatistic;
  }

  /**
   * Get the result set
   *
   * @return the result set owning this result point
   */
  public final LiteratureResultPointSet getResultSet() {
    return this.m_set;
  }

  /** {@inheritDoc} */
  @Override
  public final int compareTo(final LiteratureResultPoint o) {
    int r;

    if (o == this) {
      return 0;
    }
    if (o == null) {
      return (-1);
    }

    r = this.m_inst.compareTo(o.m_inst);
    if (r != 0) {
      return r;
    }

    r = this.m_timeDim.compareTo(o.m_timeDim);
    if (r != 0) {
      return r;
    }

    r = Double.compare(this.m_data.m_time, o.m_data.m_time);
    if (r != 0) {
      return r;
    }

    r = this.m_resultDim.compareTo(o.m_resultDim);
    if (r != 0) {
      return r;
    }

    r = Double.compare(this.m_data.m_quality, o.m_data.m_quality);
    if (r != 0) {
      return r;
    }

    r = this.m_resultStatistic.compareTo(o.m_resultStatistic);
    if (r != 0) {
      return r;
    }

    if (this.m_set == null) {
      if (o.m_set == null) {
        return 0;
      }
      return o.m_set.compareTo(null);
    }
    r = this.m_set.compareTo(o.m_set);
    if (r != 0) {
      return r;
    }

    throw new IllegalStateException("Same result point occurs twice: " //$NON-NLS-1$
        + this);
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    final StringBuilder sb;

    sb = new StringBuilder(256);
    sb.append(this.m_inst.name());
    sb.append(':');
    sb.append(' ');
    sb.append(this.m_timeDim.getShortName());
    sb.append('=');
    sb.append(this.m_data.m_time);
    sb.append(' ');
    sb.append('-');
    sb.append('-');
    sb.append('>');
    sb.append(' ');
    sb.append(this.m_resultStatistic.getShortName());
    sb.append('(');
    sb.append(this.m_resultDim.getShortName());
    sb.append(')');
    sb.append('=');
    sb.append(this.m_data.m_quality);
    return sb.toString();
  }
}
