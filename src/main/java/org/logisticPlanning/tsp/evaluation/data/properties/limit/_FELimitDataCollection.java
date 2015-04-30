package org.logisticPlanning.tsp.evaluation.data.properties.limit;

import org.logisticPlanning.tsp.benchmarking.objective.DataPoint;
import org.logisticPlanning.tsp.evaluation.data.RunSet;

/**
 * A collection of data reflecting a state in the state of a run set at a
 * given function evaluation.
 */
final class _FELimitDataCollection extends _LimitDataCollection {

  /** the fe */
  private final long m_fe;

  /**
   * the run set
   * 
   * @param fe
   *          the fe limit
   * @param rs
   *          the run set
   */
  _FELimitDataCollection(final RunSet rs, final long fe) {
    super(rs);
    this.m_fe = fe;
  }

  /** {@inheritDoc} */
  @Override
  public final double get(final int point, final int dimension) {
    final DataPoint p;
    p = this.m_rs.get(point).findFE(this.m_fe);
    if (p == null) {
      return Double.POSITIVE_INFINITY;
    }
    return p.get(dimension);
  }

}
