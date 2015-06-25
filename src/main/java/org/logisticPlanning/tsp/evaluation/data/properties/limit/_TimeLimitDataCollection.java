package org.logisticPlanning.tsp.evaluation.data.properties.limit;

import org.logisticPlanning.tsp.benchmarking.objective.DataPoint;
import org.logisticPlanning.tsp.evaluation.data.RunSet;

/**
 * A collection of data reflecting a state in the state of a run set at a
 * given time.
 */
final class _TimeLimitDataCollection extends _LimitDataCollection {

  /** the time */
  private final long m_time;

  /**
   * the run set
   *
   * @param time
   *          the time limit
   * @param rs
   *          the run set
   */
  _TimeLimitDataCollection(final RunSet rs, final long time) {
    super(rs);
    this.m_time = time;
  }

  /** {@inheritDoc} */
  @Override
  public final double get(final int point, final int dimension) {
    final DataPoint p;
    p = this.m_rs.get(point).findRuntime(this.m_time);
    if (p == null) {
      return Double.POSITIVE_INFINITY;
    }
    return p.get(dimension);
  }

}
