package org.logisticPlanning.tsp.evaluation.data.properties.limit;

import org.logisticPlanning.tsp.benchmarking.objective.DataPoint;
import org.logisticPlanning.tsp.evaluation.data.RunSet;

/**
 * A collection of data reflecting a state in the state of a run set at a
 * given normalized runtime limit.
 */
final class _NormalizedTimeLimitDataCollection extends
_LimitDataCollection {

  /** the normalized runtime */
  private final double m_normTime;

  /**
   * the run set
   *
   * @param normTime
   *          the normalized runtime
   * @param rs
   *          the run set
   */
  _NormalizedTimeLimitDataCollection(final RunSet rs, final double normTime) {
    super(rs);
    this.m_normTime = normTime;
  }

  /** {@inheritDoc} */
  @Override
  public final double get(final int point, final int dimension) {
    final DataPoint p;
    p = this.m_rs.get(point).findNormalizedRuntime(this.m_normTime);
    if (p == null) {
      return Double.POSITIVE_INFINITY;
    }
    return p.get(dimension);
  }

}
