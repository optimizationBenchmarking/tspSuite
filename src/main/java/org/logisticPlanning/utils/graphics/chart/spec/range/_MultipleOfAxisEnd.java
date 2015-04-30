package org.logisticPlanning.utils.graphics.chart.spec.range;

import org.logisticPlanning.utils.math.statistics.aggregates.ScalarAggregate;
import org.logisticPlanning.utils.utils.comparison.ComparisonUtils;

/**
 * An axis end which is a multiple of a given step
 */
abstract class _MultipleOfAxisEnd extends AggregatedAxisEnd {

  /** the step */
  final double m_step;

  /**
   * create
   * 
   * @param dim
   *          the dimension
   * @param sel
   *          the step selector
   * @param perLineAggregate
   *          the per-line aggregate
   * @param totalAggregate
   *          the total aggregate
   * @param step
   *          the step
   */
  _MultipleOfAxisEnd(final int dim, final EValueSelector sel,
      final ScalarAggregate perLineAggregate,
      final ScalarAggregate totalAggregate, final double step) {
    super(dim, sel, perLineAggregate, totalAggregate);

    if ((!(ComparisonUtils.isFinite(step))) || (step <= 0d)) {
      throw new IllegalArgumentException("Invalid step size: " + step); //$NON-NLS-1$
    }
    this.m_step = step;
  }
}
