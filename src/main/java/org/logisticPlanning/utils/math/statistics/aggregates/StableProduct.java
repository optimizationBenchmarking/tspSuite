package org.logisticPlanning.utils.math.statistics.aggregates;

/**
 * A class to compute a product of elements. This class has a conservative
 * behavior in terms of overflow: it will return
 * {@link java.lang.Double#NaN NaN} in case of overflows.
 */
public class StableProduct extends ScalarAggregate {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** did we encounter any finite number ? */
  private static final int STATE_FINITE = 1;
  /** did we encounter any finite number ? */
  private static final int STATE_ZERO = (StableProduct.STATE_FINITE << 1);
  /** did we encounter an infinity ? */
  private static final int STATE_INF = (StableProduct.STATE_ZERO << 1);
  /** did we encounter nan? */
  private static final int STATE_NAN = (StableProduct.STATE_INF << 1);

  /** the prod */
  private double m_prod;

  /** the state */
  private int m_state;

  /** instantiate */
  public StableProduct() {
    super();
    this.m_prod = 1d;
  }

  /**
   * Visit a given {@code double}, i.e., add it to the stable sum.
   *
   * @param value
   *          the value to add
   */
  @Override
  public void visitDouble(final double value) {
    double d;

    if (value == 0d) {
      this.m_state |= StableProduct.STATE_ZERO;
      return;
    }

    setInfinity: {
      checkInfinity: {
        if (value >= Double.POSITIVE_INFINITY) {
          d = 1d;
          break checkInfinity;
        }
        if (value <= Double.NEGATIVE_INFINITY) {
          d = (-1d);
          break checkInfinity;
        }
        break setInfinity;
      }

      if ((this.m_state & StableProduct.STATE_INF) != 0) {
        // ok, we already have infinity before, so m_prod is either -1,
        // 0, or 1
        this.m_prod *= d;
        return;
      }

      // no infinity yet
      if ((this.m_state & StableProduct.STATE_FINITE) != 0) {
        // ok, we multiplied finite numbers
        if (this.m_prod > 0d) {
          this.m_prod = d;
        } else {
          if (this.m_prod < 0d) {
            this.m_prod = (-d);
          } else {
            // else: underflow to zero has happened, mark as NaN
            this.m_state |= StableProduct.STATE_NAN;
          }
        }
      } else {// infinity is the first thing we encounter
        this.m_prod = d;
      }
      this.m_state |= StableProduct.STATE_INF;
      return;
    }

    if (value != value) {
      this.m_state |= StableProduct.STATE_NAN;
      return;
    }

    if ((this.m_state & StableProduct.STATE_INF) != 0) {
      // if we already encountered infinity, only the sign is important,
      // the
      // rest can be ignored to avoid additional over- or underflow
      if (value < 0d) {
        this.m_prod = (-this.m_prod);
      }
    } else {
      // no infinity yet: we can multiply, which may lead to overflow or
      // underflow
      this.m_prod *= value;
    }
    this.m_state |= StableProduct.STATE_FINITE;
  }

  /**
   * Obtain the current running sum.
   *
   * @return the current running sum
   */
  @Override
  public double getResult() {
    final int state;

    state = this.m_state;

    if (state == 0) {// nothing was multiplied
      return Double.NaN;
    }

    if (state >= StableProduct.STATE_NAN) {
      // all combinations with STATE_NAN must be greater or equal to
      // STATE_NAN
      // result must be Double.NaN
      return Double.NaN;
    }

    if (state >= StableProduct.STATE_INF) {
      if (((state & StableProduct.STATE_ZERO) != 0) || // infinity * 0 =
          // undefined
          (this.m_prod == 0d)) { // check for underflow
        return Double.NaN;
      }
      return ((this.m_prod > 0) ? Double.POSITIVE_INFINITY
          : Double.NEGATIVE_INFINITY);
    }

    if ((state & StableProduct.STATE_ZERO) != 0) {
      // encountered at least 1 zero and only finite mulitpliers =>
      // result=0
      return 0d;
    }

    if ((this.m_prod == 0d) || // underflow
        (this.m_prod >= Double.POSITIVE_INFINITY) || // overflow
        (this.m_prod <= Double.NEGATIVE_INFINITY)) {// overflow
      return Double.NaN;// numerical error...
    }

    return this.m_prod;
  }

  /** reset the sum to 0 */
  @Override
  public void reset() {
    this.m_prod = 1d;
    this.m_state = 0;
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return ("prod=" + this.getResult()); //$NON-NLS-1$
  }
}
