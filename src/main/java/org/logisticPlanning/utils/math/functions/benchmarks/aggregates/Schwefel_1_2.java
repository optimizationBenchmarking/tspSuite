package org.logisticPlanning.utils.math.functions.benchmarks.aggregates;

import org.logisticPlanning.utils.math.statistics.aggregates.ScalarAggregate;
import org.logisticPlanning.utils.math.statistics.aggregates.StableSum;

/**
 * Schwefel's function 1.2
 */
public final class Schwefel_1_2 extends ScalarAggregate {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** @serial the internal stable sum1 */
  private final StableSum m_sum1;

  /** @serial the internal stable sum2 */
  private final StableSum m_sum2;

  /** instantiate */
  public Schwefel_1_2() {
    super();
    this.m_sum1 = new StableSum();
    this.m_sum2 = new StableSum();
  }

  /** {@inheritDoc} */
  @Override
  public final void reset() {
    this.m_sum1.reset();
    this.m_sum2.reset();
  }

  /** {@inheritDoc} */
  @Override
  public final void visitDouble(final double value) {
    final double t;

    this.m_sum1.visitDouble(value);
    t = this.m_sum1.getResult();
    this.m_sum2.visitDouble(t * t);
  }

  /** {@inheritDoc} */
  @Override
  public final double getResult() {
    return this.m_sum2.getResult();
  }
}
