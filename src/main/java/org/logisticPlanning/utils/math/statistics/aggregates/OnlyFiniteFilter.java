package org.logisticPlanning.utils.math.statistics.aggregates;

import org.logisticPlanning.utils.utils.comparison.ComparisonUtils;

/**
 * A filtered aggregate that aggregates only finite, non-nan doubles.
 */
public final class OnlyFiniteFilter extends FilteredAggregate {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * instantiate
   *
   * @param agg
   *          the aggregate
   */
  public OnlyFiniteFilter(final Aggregate agg) {
    super(agg);
  }

  /** {@inheritDoc} */
  @Override
  protected final boolean isAllowed(final double value) {
    return ComparisonUtils.isFinite(value);
  }
}
