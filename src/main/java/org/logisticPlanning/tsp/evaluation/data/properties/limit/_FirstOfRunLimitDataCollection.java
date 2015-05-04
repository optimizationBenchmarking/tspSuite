package org.logisticPlanning.tsp.evaluation.data.properties.limit;

import org.logisticPlanning.tsp.evaluation.data.Run;
import org.logisticPlanning.tsp.evaluation.data.RunSet;

/**
 * A collection of data reflecting a state in the start of a run.
 */
final class _FirstOfRunLimitDataCollection extends _LimitDataCollection {

  /**
   * the run set
   *
   * @param rs
   *          the run set
   */
  _FirstOfRunLimitDataCollection(final RunSet rs) {
    super(rs);
  }

  /** {@inheritDoc} */
  @Override
  public final double get(final int point, final int dimension) {
    final Run r;

    r = this.m_rs.get(point);
    if (r.size() <= 0) {
      return Double.POSITIVE_INFINITY;
    }
    return r.get(0, dimension);
  }

}
