package org.logisticPlanning.utils.math.functions.benchmarks.aggregates;

import org.logisticPlanning.utils.math.statistics.aggregates.ScalarAggregate;

/**
 * Step function
 */
public final class Step extends ScalarAggregate {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** @serial the internal sum */
  private long m_sum;

  /** instantiate */
  public Step() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final void reset() {
    this.m_sum = 0l;
  }

  /** {@inheritDoc} */
  @Override
  public final void visitDouble(final double value) {
    final long t;
    t = Math.round(Math.floor(value + 0.5d));
    this.m_sum += (t * t);
  }

  /** {@inheritDoc} */
  @Override
  public final double getResult() {
    return this.m_sum;
  }
}
