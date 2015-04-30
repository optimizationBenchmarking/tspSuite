package org.logisticPlanning.utils.math.functions.benchmarks.aggregates;

import org.logisticPlanning.utils.math.statistics.aggregates.ScalarAggregate;
import org.logisticPlanning.utils.math.statistics.aggregates.StableSum;

/**
 * Sphere function
 */
public final class Sphere extends ScalarAggregate {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** @serial the internal stable sum */
  private final StableSum m_sum;

  /** instantiate */
  public Sphere() {
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
    this.m_sum.visitDouble(value * value);
  }

  /** {@inheritDoc} */
  @Override
  public final double getResult() {
    return this.m_sum.getResult();
  }
}
