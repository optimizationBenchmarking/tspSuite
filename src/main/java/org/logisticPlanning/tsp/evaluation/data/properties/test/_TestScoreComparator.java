package org.logisticPlanning.tsp.evaluation.data.properties.test;

import org.logisticPlanning.utils.utils.comparison.EComparison;
import org.logisticPlanning.utils.utils.comparison.PreciseComparator;

/**
 * the internal test score comparator
 */
final class _TestScoreComparator extends PreciseComparator<TestScore> {

  /** the comparator */
  static final _TestScoreComparator INSTANCE = new _TestScoreComparator();

  /** create */
  private _TestScoreComparator() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected final EComparison doPreciseCompare(final TestScore a,
      final TestScore b) {
    int r;

    r = Integer.compare(a.getWins(), b.getWins());
    if (r < 0) {
      return EComparison.GREATER;
    }
    if (r > 0) {
      return EComparison.LESS;
    }

    r = Integer.compare(a.getLosses(), b.getLosses());
    if (r < 0) {
      return EComparison.LESS;
    }
    if (r > 0) {
      return EComparison.GREATER;
    }

    r = Integer.compare(a.getEvens(), b.getEvens());
    if (r < 0) {
      return EComparison.LESS;
    }
    if (r > 0) {
      return EComparison.GREATER;
    }

    return EComparison.EQUAL;
  }
}
