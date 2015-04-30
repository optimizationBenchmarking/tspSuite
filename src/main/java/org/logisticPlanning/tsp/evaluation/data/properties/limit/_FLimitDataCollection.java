package org.logisticPlanning.tsp.evaluation.data.properties.limit;

import org.logisticPlanning.tsp.benchmarking.objective.DataPoint;
import org.logisticPlanning.tsp.evaluation.data.RunSet;

/**
 * A collection of data reflecting a state in the state of a run set at a
 * given objective function evaluation.
 */
final class _FLimitDataCollection extends _LimitDataCollection {

  /** the f */
  private final long m_f;

  /**
   * the run set
   * 
   * @param f
   *          the f limit
   * @param rs
   *          the run set
   */
  _FLimitDataCollection(final RunSet rs, final long f) {
    super(rs);
    this.m_f = f;
  }

  /** {@inheritDoc} */
  @Override
  public final double get(final int point, final int dimension) {
    final DataPoint p;
    p = this.m_rs.get(point).findBestF(this.m_f);
    if (p == null) {
      return Double.POSITIVE_INFINITY;
    }
    return p.get(dimension);
  }

}
