package org.logisticPlanning.utils.graphics.chart.spec.range;

import org.logisticPlanning.utils.math.statistics.aggregates.ScalarAggregate;

/**
 * An axis end which is a multiple of a given step
 */
public final class MultipleOfAxisBigEnd extends _MultipleOfAxisEnd {

  /**
   * create
   *
   * @param dim
   *          the dimension
   * @param step
   *          the step
   */
  public MultipleOfAxisBigEnd(final int dim, final double step) {
    this(dim, null, null, null, step);
  }

  /**
   * create
   *
   * @param dim
   *          the dimension
   * @param sel
   *          the step selector
   * @param step
   *          the step
   */
  public MultipleOfAxisBigEnd(final int dim, final EValueSelector sel,
      final double step) {
    this(dim, sel, null, null, step);
  }

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
  public MultipleOfAxisBigEnd(final int dim, final EValueSelector sel,
      final ScalarAggregate perLineAggregate,
      final ScalarAggregate totalAggregate, final double step) {
    super(dim, sel, AggregatedAxisEnd
        ._defaultBigPerLineAggregate(perLineAggregate), AggregatedAxisEnd
        ._defaultBigTotalAggregate(totalAggregate), step);
  }

  /** {@inheritDoc} */
  @Override
  protected final double calcEnd(final double aggregate) {
    final double d;

    if (Math.abs(aggregate) <= 0d) {
      return 0d;
    }

    d = (this.m_step * Math.ceil(aggregate / this.m_step));

    if (AxisEnd._isLong(this.m_step)) {
      if ((d >= Long.MIN_VALUE) && (d <= Long.MAX_VALUE)) {
        return Math.round(d);
      }
    }

    return d;
  }
}
