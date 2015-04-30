package org.logisticPlanning.utils.math.functions.benchmarks.aggregates;

import org.logisticPlanning.utils.math.statistics.aggregates.ScalarAggregate;
import org.logisticPlanning.utils.math.statistics.aggregates.StableSum;

/**
 * Rosenbrock's function
 */
public final class Rosenbrock extends ScalarAggregate {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** @serial the internal stable sum1 */
  private final StableSum m_sum1;
  /** @serial the internal stable sum2 */
  private final StableSum m_sum2;

  /** @serial the last value */
  private double m_last;

  /** @serial do we have a last value ? */
  private boolean m_hasLast;

  /** instantiate */
  public Rosenbrock() {
    super();
    this.m_sum1 = new StableSum();
    this.m_sum2 = new StableSum();
  }

  /** {@inheritDoc} */
  @Override
  public final void reset() {
    this.m_sum1.reset();
    this.m_sum2.reset();
    this.m_last = 0d;
    this.m_hasLast = false;
  }

  /** {@inheritDoc} */
  @Override
  public final void visitDouble(final double value) {
    final double l;
    double d;

    if (this.m_hasLast) {
      l = this.m_last;

      d = (1d - l);
      this.m_sum1.visitDouble(d * d);

      d = (value - (l * l));
      this.m_sum2.visitDouble(d * d);
    }

    this.m_last = value;
    this.m_hasLast = true;
  }

  /** {@inheritDoc} */
  @Override
  public final double getResult() {
    return (this.m_sum1.getResult() + ((100d * (this.m_sum2.getResult()))));
  }
}
