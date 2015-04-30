package org.logisticPlanning.utils.math.statistics.aggregates;

import org.logisticPlanning.utils.collections.lists.ArraySetView;
import org.logisticPlanning.utils.math.statistics.EStatisticParameter;
import org.logisticPlanning.utils.math.statistics.IStatisticPoint;

/**
 * A class to compute a sum of elements. This class numerically stable and
 * also has a conservative behavior in terms of overflow: If you add, for
 * instance,
 * <code>{@link java.lang.Double#MAX_VALUE}+{@link java.lang.Double#MAX_VALUE}</code>
 * , you would normally get
 * <code>{@link java.lang.Double#POSITIVE_INFINITY}</code> since it is an
 * overflow. The sum here will instead produce a
 * <code>{@link java.lang.Double#NaN}</code>. I believe this behavior is
 * more suitable as {@link java.lang.Double#NaN} can be interpreted as
 * &quot;result is undefined&quot;, whereas infinity, be it
 * {@link java.lang.Double#POSITIVE_INFINITY} or
 * {@link java.lang.Double#NEGATIVE_INFINITY}, may have a meaningful
 * interpretation in a context &ndash; which may be misleading if it stems
 * from a simple overflow.
 */
public class StableSum extends ScalarAggregate implements IStatisticPoint {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** did we encounter positive infinity ? */
  private static final int STATE_POS_INF = 1;
  /** did we encounter negative infinity ? */
  private static final int STATE_NEG_INF = (StableSum.STATE_POS_INF << 1);
  /** did we encounter nan? */
  private static final int STATE_NAN = (StableSum.STATE_NEG_INF << 1);

  /** the list of supported statistic parameters */
  private static final ArraySetView<EStatisticParameter> LIST = //
  EStatisticParameter.makeList(EStatisticParameter.SUM);

  /** the sum */
  private double m_sum;

  /** the compensation */
  private double m_compensation;

  /** the state */
  private int m_state;

  /** instantiate */
  public StableSum() {
    super();
  }

  /**
   * Visit a given {@code double}, i.e., add it to the stable sum.
   * 
   * @param value
   *          the value to add
   */
  @Override
  public void visitDouble(final double value) {
    double y, t;

    if (value == 0d) {
      return;
    }

    if (value >= Double.POSITIVE_INFINITY) {
      this.m_state |= StableSum.STATE_POS_INF;
      return;
    }

    if (value <= Double.NEGATIVE_INFINITY) {
      this.m_state |= StableSum.STATE_NEG_INF;
      return;
    }

    if (value != value) {
      this.m_state |= StableSum.STATE_NAN;
      return;
    }

    y = (value - this.m_compensation);
    t = (this.m_sum + y);
    this.m_compensation = ((t - this.m_sum) - y);
    this.m_sum = t;
  }

  /**
   * Visit a given {@code long}, i.e., add it to the stable sum.
   * 
   * @param value
   *          the value to add
   */
  @Override
  public void visitLong(final long value) {
    double y, t;

    if (value == 0l) {
      return;
    }

    y = (value - this.m_compensation);
    t = (this.m_sum + y);
    this.m_compensation = ((t - this.m_sum) - y);
    this.m_sum = t;
  }

  /**
   * Visit a given {@code int}, i.e., add it to the stable sum.
   * 
   * @param value
   *          the value to add
   */
  @Override
  public void visitInt(final int value) {
    double y, t;

    if (value == 0) {
      return;
    }

    y = (value - this.m_compensation);
    t = (this.m_sum + y);
    this.m_compensation = ((t - this.m_sum) - y);
    this.m_sum = t;
  }

  /**
   * Obtain the current running sum.
   * 
   * @return the current running sum
   */
  @Override
  public double getResult() {
    final int state;
    final double sum;

    state = this.m_state;

    if (state >= StableSum.STATE_NAN) {
      // all combinations with STATE_NAN must be greater or equal to
      // STATE_NAN
      // result must be Double.NaN
      return Double.NaN;
    }

    if ((state & StableSum.STATE_POS_INF) != 0) {
      // we encountered positive infinity. if we also visited negative
      // infinity, the result is nan. otherwise, it is positive infinity
      return (((state & StableSum.STATE_NEG_INF) != 0) ? Double.NaN
          : Double.POSITIVE_INFINITY);
    }
    if ((state & StableSum.STATE_NEG_INF) != 0) {
      // we encountered negative infinity and not positive infinity
      return Double.NEGATIVE_INFINITY;
    }

    sum = this.m_sum;

    if ((sum >= Double.POSITIVE_INFINITY)
        || (sum <= Double.NEGATIVE_INFINITY) || (sum != sum)) {
      // we encountered no infinite values, thus we had an overflow.
      // better
      // return NaN
      return Double.NaN;
    }

    return this.m_sum;
  }

  /** reset the sum to 0 */
  @Override
  public void reset() {
    this.m_sum = 0d;
    this.m_compensation = 0d;
    this.m_state = 0;
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return ((EStatisticParameter.SUM.getShortName() + '=') + this
        .getResult());
  }

  /** {@inheritDoc} */
  @Override
  public ArraySetView<EStatisticParameter> getParameters() {
    return StableSum.LIST;
  }

  /** {@inheritDoc} */
  @Override
  public double getParameter(final EStatisticParameter param) {
    if (param == EStatisticParameter.SUM) {
      return this.getResult();
    }
    throw new UnsupportedOperationException(String.valueOf(param));
  }
}
