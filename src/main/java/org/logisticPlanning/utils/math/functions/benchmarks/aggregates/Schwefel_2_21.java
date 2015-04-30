package org.logisticPlanning.utils.math.functions.benchmarks.aggregates;

import org.logisticPlanning.utils.math.statistics.aggregates.ScalarAggregate;

/**
 * Schwefel's function 2.21
 */
public final class Schwefel_2_21 extends ScalarAggregate {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** @serial the internal maximum */
  private double m_max;

  /** instantiate */
  public Schwefel_2_21() {
    super();
    this.m_max = Double.NEGATIVE_INFINITY;
  }

  /** {@inheritDoc} */
  @Override
  public final void reset() {
    this.m_max = Double.NEGATIVE_INFINITY;
  }

  /** {@inheritDoc} */
  @Override
  public final void visitDouble(final double value) {
    final double d;

    d = Math.abs(value);
    if (d > this.m_max) {
      this.m_max = d;
    }
  }

  /** {@inheritDoc} */
  @Override
  public final double getResult() {
    return this.m_max;
  }
}
