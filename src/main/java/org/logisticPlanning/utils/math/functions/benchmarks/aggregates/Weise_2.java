package org.logisticPlanning.utils.math.functions.benchmarks.aggregates;

import org.logisticPlanning.utils.math.statistics.aggregates.ScalarAggregate;
import org.logisticPlanning.utils.math.statistics.aggregates.StableSum;

/**
 * Weise's function 3
 */
public final class Weise_2 extends ScalarAggregate {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** @serial the internal stable sum */
  private final StableSum m_sum;

  /** @serial the count */
  private int m_c;

  /** instantiate */
  public Weise_2() {
    super();
    this.m_sum = new StableSum();
  }

  /** {@inheritDoc} */
  @Override
  public final void reset() {
    this.m_sum.reset();
    this.m_c = 0;
  }

  /** {@inheritDoc} */
  @Override
  public final void visitDouble(final double value) {
    final int i;
    double a, b;

    i = (++this.m_c);
    a = (1d / i);
    b = (value * value);
    if (b > a) {
      b = 1d;
    }
    this.m_sum.visitDouble(b);
  }

  /** {@inheritDoc} */
  @Override
  public final double getResult() {
    return Math.min(1d, Math.max(0d, (this.m_sum.getResult() / this.m_c)));
  }
}
