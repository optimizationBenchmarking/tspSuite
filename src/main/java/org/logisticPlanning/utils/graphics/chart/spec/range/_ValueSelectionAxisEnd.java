package org.logisticPlanning.utils.graphics.chart.spec.range;

import org.logisticPlanning.utils.math.statistics.aggregates.ScalarAggregate;
import org.logisticPlanning.utils.utils.comparison.ComparisonUtils;

/**
 * An axis end which is a choice of a set of values.
 */
abstract class _ValueSelectionAxisEnd extends AggregatedAxisEnd {

  /** the the possible */
  final double[] m_values;

  /**
   * should the value be used directly if it was outside the range (
   * {@code true} ), or the closest extreme of the range/selection (
   * {@code false})?
   */
  final boolean m_useValueDirectlyIfOutsideRange;

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
  _ValueSelectionAxisEnd(final int dim, final EValueSelector sel,
      final ScalarAggregate perLineAggregate,
      final ScalarAggregate totalAggregate, final double[] values,
      final boolean useValueDirectlyIfOutsideRange) {
    super(dim, sel, perLineAggregate, totalAggregate);

    double d, prev;
    boolean r2;
    int i;

    if ((values == null) || (values.length <= 1)) {
      throw new IllegalArgumentException(
          "There must be at least two values."); //$NON-NLS-1$
    }

    prev = Double.NEGATIVE_INFINITY;
    for (i = 0; i < values.length; i++) {
      d = values[i];

      r2 = false;
      checkMe: for (;;) {
        if (d < prev) {
          throw new IllegalArgumentException(//
              "Selectable values must be sorted, but " + //$NON-NLS-1$
              d + " follows " + prev);//$NON-NLS-1$
        }
        if (!(ComparisonUtils.isFinite(d))) {
          throw new IllegalArgumentException(//
              "Selectable values must be finite, but encountered " + d); //$NON-NLS-1$
        }

        if (r2) {
          break checkMe;
        }
        if (AxisEnd._isLong(d)) {
          r2 = true;
          values[i] = d = Math.round(d);
        } else {
          break checkMe;
        }
      }

      prev = d;
    }

    this.m_values = values;

    this.m_useValueDirectlyIfOutsideRange = useValueDirectlyIfOutsideRange;
  }
}
