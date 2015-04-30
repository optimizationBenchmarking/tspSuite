package org.logisticPlanning.utils.math.functions.benchmarks.aggregates;

import org.logisticPlanning.utils.math.statistics.aggregates.ScalarAggregate;
import org.logisticPlanning.utils.math.statistics.aggregates.StableSum;

/**
 * Rosenbrock's function
 */
public final class Weise_3 extends ScalarAggregate {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** @serial the internal stable sum */
  private final StableSum m_sum;

  /** @serial the last value */
  private double m_last;

  /** @serial do we have a last value ? */
  private boolean m_hasLast;

  /** instantiate */
  public Weise_3() {
    super();
    this.m_sum = new StableSum();
  }

  /** {@inheritDoc} */
  @Override
  public final void reset() {
    this.m_sum.reset();
    this.m_last = 0d;
    this.m_hasLast = false;
  }

  /** {@inheritDoc} */
  @Override
  public final void visitDouble(final double value) {
    final double calc;

    if (this.m_hasLast) {
      calc = (this.m_last + value);
      this.m_sum.visitDouble(calc * calc);
    }

    this.m_last = value;
    this.m_hasLast = true;
  }

  /** {@inheritDoc} */
  @Override
  public final double getResult() {
    double d, s;

    d = this.m_sum.getResult();
    if (d <= 0d) {
      return 0d;
    }
    s = Math.sin(Math.log(d));
    return (d + (s * s));
  }
}
