package org.logisticPlanning.tsp.evaluation.data.properties.limit;

import org.logisticPlanning.tsp.benchmarking.objective.DataPoint;
import org.logisticPlanning.tsp.evaluation.data.RunSet;

/**
 * A collection of data reflecting a state in the state of a run set at a
 * given distance evaluation.
 */
final class _DELimitDataCollection extends _LimitDataCollection {

  /** the de */
  private final long m_de;

  /**
   * the run set
   * 
   * @param de
   *          the de limit
   * @param rs
   *          the run set
   */
  _DELimitDataCollection(final RunSet rs, final long de) {
    super(rs);
    this.m_de = de;
  }

  /** {@inheritDoc} */
  @Override
  public final double get(final int point, final int dimension) {
    final DataPoint p;
    p = this.m_rs.get(point).findDE(this.m_de);
    if (p == null) {
      return Double.POSITIVE_INFINITY;
    }
    return p.get(dimension);
  }

}
