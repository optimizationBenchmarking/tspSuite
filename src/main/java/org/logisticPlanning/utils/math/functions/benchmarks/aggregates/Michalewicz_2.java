package org.logisticPlanning.utils.math.functions.benchmarks.aggregates;

import org.logisticPlanning.utils.math.statistics.aggregates.ScalarAggregate;
import org.logisticPlanning.utils.math.statistics.aggregates.StableSum;

/**
 * the second Michalewicz function
 */
public final class Michalewicz_2 extends ScalarAggregate {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** @serial the internal stable sum */
  private final StableSum m_sum;

  /** @serial the current dimension index */
  private int m_i;

  /** instantiate */
  public Michalewicz_2() {
    super();
    this.m_sum = new StableSum();
  }

  /** {@inheritDoc} */
  @Override
  public final void reset() {
    this.m_sum.reset();
    this.m_i = 0;
  }

  /** {@inheritDoc} */
  @Override
  public final void visitDouble(final double value) {
    this.m_sum.visitDouble(//
        Math.sin(value) * //
        Math.sin(((++this.m_i) * value * value) / Math.PI));
  }

  /** {@inheritDoc} */
  @Override
  public final double getResult() {
    return this.m_sum.getResult();
  }
}
