package org.logisticPlanning.utils.graphics.chart.spec.range;

import java.util.Arrays;

import org.logisticPlanning.utils.math.statistics.aggregates.ScalarAggregate;

/**
 * An axis end which is a choice of a set of values.
 */
public class ValueSelectionAxisSmallEnd extends _ValueSelectionAxisEnd {

  /**
   * create
   *
   * @param dim
   *          the dimension
   * @param values
   *          the values to choose from
   */
  public ValueSelectionAxisSmallEnd(final int dim, final double[] values) {
    this(dim, null, values);
  }

  /**
   * create
   *
   * @param dim
   *          the dimension
   * @param sel
   *          the step selector
   * @param values
   *          the values to choose from
   */
  public ValueSelectionAxisSmallEnd(final int dim,
      final EValueSelector sel, final double[] values) {
    this(dim, sel, null, null, values, false);
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
   * @param values
   *          the values to choose from
   * @param useValueDirectlyIfOutsideRange
   *          should the value be used directly if it was outside the range
   *          ( {@code true}), or the closest extreme of the
   *          range/selection ( {@code false})?
   */
  public ValueSelectionAxisSmallEnd(final int dim,
      final EValueSelector sel, final ScalarAggregate perLineAggregate,
      final ScalarAggregate totalAggregate, final double[] values,
      final boolean useValueDirectlyIfOutsideRange) {
    super(dim,
        sel, //
        AggregatedAxisEnd._defaultSmallPerLineAggregate(perLineAggregate), //
        AggregatedAxisEnd._defaultSmallTotalAggregate(totalAggregate),
        values, useValueDirectlyIfOutsideRange);
  }

  /** {@inheritDoc} */
  @Override
  protected final double calcEnd(final double aggregate) {
    final double[] v;
    int idx;

    v = this.m_values;
    idx = Arrays.binarySearch(v, aggregate);
    if (idx >= 0) {
      return v[idx];
    }

    idx = -(idx + 1);
    if (idx < 0) {
      if (this.m_useValueDirectlyIfOutsideRange) {
        if ((aggregate == aggregate)
            && (aggregate > Double.NEGATIVE_INFINITY)
            && (aggregate < Double.POSITIVE_INFINITY)) {
          return aggregate;
        }
      }
      idx = 0;
    } else {
      if (idx >= v.length) {
        idx = (v.length - 1);
      }
    }
    return v[idx];
  }
}
