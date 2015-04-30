package org.logisticPlanning.utils.math.functions.benchmarks.aggregates;

import org.logisticPlanning.utils.math.statistics.aggregates.ScalarAggregate;
import org.logisticPlanning.utils.math.statistics.aggregates.StableSum;

/**
 * Weise's function 2
 */
public final class Weise_1 extends ScalarAggregate {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** @serial the internal stable sum */
  private final StableSum m_sum;

  /** @serial the count */
  private int m_c;

  /** instantiate */
  public Weise_1() {
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
    double d;

    i = (++this.m_c);
    d = Math.sin(Math.floor(value * i) / i);
    this.m_sum.visitDouble(d * d);
  }

  /** {@inheritDoc} */
  @Override
  public final double getResult() {
    return Math.min(1d, Math.max(0d, (this.m_sum.getResult() / this.m_c)));
  }
}
