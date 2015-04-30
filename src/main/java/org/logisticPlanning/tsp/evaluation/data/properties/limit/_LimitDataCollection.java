package org.logisticPlanning.tsp.evaluation.data.properties.limit;

import org.logisticPlanning.tsp.benchmarking.objective.DataPoint;
import org.logisticPlanning.tsp.evaluation.data.RunSet;
import org.logisticPlanning.utils.math.data.collection.IDataCollection;

/**
 * A collection of data reflecting a state in the state of a run set.
 */
public abstract class _LimitDataCollection implements IDataCollection {

  /** the run set */
  final RunSet m_rs;

  /**
   * the run set
   * 
   * @param rs
   *          the run set
   */
  _LimitDataCollection(final RunSet rs) {
    super();
    this.m_rs = rs;
  }

  /** {@inheritDoc} */
  @Override
  public final int size() {
    return this.m_rs.size();
  }

  /** {@inheritDoc} */
  @Override
  public final int dimension() {
    return DataPoint.DATA_POINT_DIMENSION;
  }

  /** {@inheritDoc} */
  @Override
  public abstract double get(final int point, final int dimension);

}
