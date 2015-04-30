package org.logisticPlanning.utils.math.functions.benchmarks.aggregates;

import org.logisticPlanning.utils.math.statistics.aggregates.ScalarAggregate;
import org.logisticPlanning.utils.math.statistics.aggregates.StableSum;

/**
 * The n-dimensional Schaffer function
 */
public final class Schaffer extends ScalarAggregate {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** @serial the internal stable sum */
  private final StableSum m_sum;

  /** @serial the counter */
  private int m_c;

  /** @serial the last double */
  private double m_last;

  /** instantiate */
  public Schaffer() {
    super();
    this.m_sum = new StableSum();
  }

  /** {@inheritDoc} */
  @Override
  public final void reset() {
    this.m_sum.reset();
    this.m_c = 0;
    this.m_last = 0d;
  }

  /** {@inheritDoc} */
  @Override
  public final void visitDouble(final double value) {
    final double d, s, ss, v;

    d = (value * value);
    if ((this.m_c++) > 0) {
      s = (d + this.m_last);

      ss = Math.sin(Math.sqrt(s));
      v = ((0.001d * s) + 1d);

      this.m_sum.visitDouble(((ss * ss) - 0.5d) / (v * v));
    }
    this.m_last = d;
  }

  /** {@inheritDoc} */
  @Override
  public final double getResult() {
    return (this.m_sum.getResult() + (0.5d * (this.m_c - 1)));
  }
}
