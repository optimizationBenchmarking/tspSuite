package org.logisticPlanning.tsp.evaluation.data.properties.limit;

import org.logisticPlanning.tsp.benchmarking.objective.DataPoint;
import org.logisticPlanning.tsp.evaluation.data.RunSet;

/**
 * A collection of data reflecting a state in the state of a run set at a
 * given relative objective value.
 */
final class _RelativeFLimitDataCollection extends _LimitDataCollection {

  /** the relative objective value */
  private final double m_relF;

  /**
   * the run set
   *
   * @param relF
   *          the relative objective value
   * @param rs
   *          the run set
   */
  _RelativeFLimitDataCollection(final RunSet rs, final double relF) {
    super(rs);
    this.m_relF = relF;
  }

  /** {@inheritDoc} */
  @Override
  public final double get(final int point, final int dimension) {
    final DataPoint p;
    p = this.m_rs.get(point).findRelBestF(this.m_relF);
    if (p == null) {
      return Double.POSITIVE_INFINITY;
    }
    return p.get(dimension);
  }

}
