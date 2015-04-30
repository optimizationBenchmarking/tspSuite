package org.logisticPlanning.tsp.evaluation.data.properties.limit;

import org.logisticPlanning.tsp.evaluation.data.Run;
import org.logisticPlanning.tsp.evaluation.data.RunSet;

/**
 * A collection of data reflecting a state in the end of a run.
 */
final class _EndOfRunLimitDataCollection extends _LimitDataCollection {

  /**
   * the run set
   * 
   * @param rs
   *          the run set
   */
  _EndOfRunLimitDataCollection(final RunSet rs) {
    super(rs);
  }

  /** {@inheritDoc} */
  @Override
  public final double get(final int point, final int dimension) {
    final Run r;
    final int s;

    r = this.m_rs.get(point);
    s = r.size();
    if (s <= 0) {
      return Double.POSITIVE_INFINITY;
    }
    return r.get((s - 1), dimension);
  }

}
