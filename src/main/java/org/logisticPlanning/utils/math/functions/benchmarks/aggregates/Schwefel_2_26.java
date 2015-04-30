package org.logisticPlanning.utils.math.functions.benchmarks.aggregates;

import org.logisticPlanning.utils.math.statistics.aggregates.ScalarAggregate;
import org.logisticPlanning.utils.math.statistics.aggregates.StableSum;

/**
 * Schwefel's function 2.26
 */
public final class Schwefel_2_26 extends ScalarAggregate {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** @serial the internal stable sum */
  private final StableSum m_sum;

  /** instantiate */
  public Schwefel_2_26() {
    super();
    this.m_sum = new StableSum();
  }

  /** {@inheritDoc} */
  @Override
  public final void reset() {
    this.m_sum.reset();
  }

  /** {@inheritDoc} */
  @Override
  public final void visitDouble(final double value) {
    this.m_sum.visitDouble(value * Math.sin(Math.sqrt(Math.abs(value))));
  }

  /** {@inheritDoc} */
  @Override
  public final double getResult() {
    return -this.m_sum.getResult();
  }
}
