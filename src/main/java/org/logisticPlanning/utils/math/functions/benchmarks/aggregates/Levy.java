package org.logisticPlanning.utils.math.functions.benchmarks.aggregates;

import org.logisticPlanning.utils.math.statistics.aggregates.ScalarAggregate;
import org.logisticPlanning.utils.math.statistics.aggregates.StableSum;

/**
 * LevyOld's function
 */
public final class Levy extends ScalarAggregate {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** @serial the internal stable sum */
  private final StableSum m_sum;

  /** @serial is this the first iteration? */
  private boolean m_first;

  /** @serial the last value */
  private double m_last;

  /** instantiate */
  public Levy() {
    super();
    this.m_sum = new StableSum();
  }

  /** {@inheritDoc} */
  @Override
  public final void reset() {
    this.m_sum.reset();
    this.m_first = true;
    this.m_last = 0d;
  }

  /** {@inheritDoc} */
  @Override
  public final void visitDouble(final double value) {
    double d, e;

    if (this.m_first) {
      d = Math.sin(Math.PI * (1d + (0.25d * (value - 1d))));
      this.m_sum.visitDouble(d * d);
      this.m_first = false;
    } else {
      d = (0.25d * (this.m_last - 1d));
      e = Math.sin(1d + (Math.PI * (1d + d)));
      this.m_sum.visitDouble(d * d * (1d + (10d * e * e)));
    }

    this.m_last = value;
  }

  /** {@inheritDoc} */
  @Override
  public final double getResult() {
    double d, e;

    if (!(this.m_first)) {
      e = this.m_last;
      d = (0.25d * (e - 1d));
      e = (Math.PI * e);
      e = Math.sin(e + e);
      e = (d * d * (1d + (e * e)));
    } else {
      e = 0d;
    }

    return (this.m_sum.getResult() + e);
  }
}
