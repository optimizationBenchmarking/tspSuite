package org.logisticPlanning.utils.math.functions.benchmarks.aggregates;

import org.logisticPlanning.utils.math.statistics.aggregates.ScalarAggregate;
import org.logisticPlanning.utils.math.statistics.aggregates.StableSum;

/**
 * Griewank function
 */
public final class Griewank extends ScalarAggregate {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** @serial the internal stable sum */
  private final StableSum m_sum;

  /** @serial the product */
  private double m_prod;

  /** @serial the count */
  private int m_i;

  /** instantiate */
  public Griewank() {
    super();
    this.m_sum = new StableSum();
    this.m_prod = 1d;
  }

  /** {@inheritDoc} */
  @Override
  public final void reset() {
    this.m_sum.reset();
    this.m_prod = 1d;
    this.m_i = 0;
  }

  /** {@inheritDoc} */
  @Override
  public final void visitDouble(final double value) {
    this.m_prod *= Math.cos(value / Math.sqrt(++this.m_i));
    this.m_sum.visitDouble(value * value);
  }

  /** {@inheritDoc} */
  @Override
  public final double getResult() {
    return (1d + ((this.m_sum.getResult() / 4000d) - this.m_prod));
  }

}
