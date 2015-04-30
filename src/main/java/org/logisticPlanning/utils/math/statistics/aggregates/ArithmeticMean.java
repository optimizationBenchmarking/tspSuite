package org.logisticPlanning.utils.math.statistics.aggregates;

import org.logisticPlanning.utils.collections.lists.ArraySetView;
import org.logisticPlanning.utils.math.statistics.EStatisticParameter;

/**
 * A class to compute a stable mean. It works like a stable sum, with the
 * addition of counting the number of elements. For performance reasons, it
 * is implemented as separate class instead of extending
 * {@link org.logisticPlanning.utils.math.statistics.aggregates.StableSum}.
 */
public final class ArithmeticMean extends StableSum {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the list of supported statistic parameters */
  private static final ArraySetView<EStatisticParameter> LIST = //
  EStatisticParameter.makeList(EStatisticParameter.ARITHMETIC_MEAN,
      EStatisticParameter.COUNT, EStatisticParameter.SUM);

  /** the count */
  private long m_count;

  /** instantiate */
  public ArithmeticMean() {
    super();
  }

  /**
   * Visit a given {@code double}, i.e., incorporate it into the mean
   * 
   * @param value
   *          the value to add
   */
  @Override
  public final void visitDouble(final double value) {
    super.visitDouble(value);
    this.m_count++;
  }

  /**
   * Visit a given {@code long}, i.e., incorporate it into the mean
   * 
   * @param value
   *          the value to add
   */
  @Override
  public final void visitLong(final long value) {
    super.visitLong(value);
    this.m_count++;
  }

  /**
   * Visit a given {@code int}, i.e., incorporate it into the mean
   * 
   * @param value
   *          the value to add
   */
  @Override
  public final void visitInt(final int value) {
    super.visitInt(value);
    this.m_count++;
  }

  /**
   * Obtain the current running mean.
   * 
   * @return the current running mean
   */
  @Override
  public final double getResult() {
    final long l;
    final double s;

    l = this.m_count;
    if (l <= 0l) {
      return Double.NaN;
    }
    s = super.getResult();
    if (l == 1l) {
      return s;
    }
    return (s / l); // infinities or NaN will remain the same
  }

  /** reset the mean */
  @Override
  public final void reset() {
    super.reset();
    this.m_count = 0l;
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    return ((EStatisticParameter.ARITHMETIC_MEAN.getShortName() + '=') + //
    this.getResult());
  }

  /** {@inheritDoc} */
  @Override
  public final ArraySetView<EStatisticParameter> getParameters() {
    return ArithmeticMean.LIST;
  }

  /** {@inheritDoc} */
  @Override
  public final double getParameter(final EStatisticParameter param) {
    switch (param) {
      case ARITHMETIC_MEAN: {
        return this.getResult();
      }
      case COUNT: {
        return this.m_count;
      }
      case SUM: {
        return super.getResult();
      }
      default: {
        throw new UnsupportedOperationException(String.valueOf(param));
      }
    }
  }
}
