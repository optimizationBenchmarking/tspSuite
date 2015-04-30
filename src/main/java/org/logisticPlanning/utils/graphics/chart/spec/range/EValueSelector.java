package org.logisticPlanning.utils.graphics.chart.spec.range;

/**
 * the boundary choice
 */
public enum EValueSelector {

  /**
   * the first value of a point which is completely finite should be
   * aggregated
   */
  FINITE_POINT,

  /**
   * the first finite value of the specified coordinate a point should be
   * aggregated, regardless whether the others are finite or not
   */
  FINITE_VALUE,

  /** any input value is ok */
  ANY;
}
