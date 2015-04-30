package org.logisticPlanning.utils.math.functions.benchmarks.aggregates;

import org.logisticPlanning.utils.math.MathConstants;
import org.logisticPlanning.utils.math.statistics.aggregates.ScalarAggregate;
import org.logisticPlanning.utils.math.statistics.aggregates.StableSum;

/**
 * Ackley's function
 */
public final class Ackley extends ScalarAggregate {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** @serial the internal stable sum1 */
  private final StableSum m_sum1;

  /** @serial the internal stable sum2 */
  private final StableSum m_sum2;

  /** @serial the count */
  private int m_c;

  /** instantiate */
  public Ackley() {
    super();
    this.m_sum1 = new StableSum();
    this.m_sum2 = new StableSum();
  }

  /** {@inheritDoc} */
  @Override
  public final void reset() {
    this.m_sum1.reset();
    this.m_sum2.reset();
    this.m_c = 0;
  }

  /** {@inheritDoc} */
  @Override
  public final void visitDouble(final double value) {
    this.m_c++;
    this.m_sum1.visitDouble(value * value);
    this.m_sum2.visitDouble(Math.cos(MathConstants.TWO_PI * value));
  }

  /** {@inheritDoc} */
  @Override
  public final double getResult() {
    final double r, e2;
    final int count;
    double s;

    count = this.m_c;
    s = this.m_sum1.getResult();
    if (s == 0d) {
      r = 0d;
    } else {
      r = (20d - (20d * Math.exp(-0.2d * Math.sqrt(s / count))));
    }

    s = this.m_sum2.getResult();
    if (s == count) {
      return r;
    }
    e2 = Math.exp(s / count);

    return (r + (Math.E - e2));
  }

  // /**
  // * The protected ackley's function
  // *
  // * @param s1
  // * the first sum
  // * @param s2
  // * the second sum
  // * @param count
  // * the counter
  // * @return the result
  // */
  // private static final double protAckley(final double s1, final double
  // s2,
  // final double count) {
  // final double e1, e2;
  //
  // if (s1 == 0d) {
  // e1 = 1d;
  // } else {
  // e1 = Math.exp(-0.2d * Math.sqrt(s1 / count));
  // }
  //
  // if (s2 == count) {
  // e2 = Math.E;
  // } else {
  // e2 = Math.exp(s2 / count);
  // }
  //
  // return ((20d - (20d * e1)) + (Math.E - e2));
  // }
}
