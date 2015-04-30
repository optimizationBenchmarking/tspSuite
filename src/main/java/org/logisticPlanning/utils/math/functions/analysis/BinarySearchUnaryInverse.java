package org.logisticPlanning.utils.math.functions.analysis;

import org.logisticPlanning.utils.math.functions.UnaryFunction;

/**
 * A simple unary function inverter based on binary search. Will only work
 * on steady, monotonously rising (or falling functions).
 */
public final class BinarySearchUnaryInverse extends UnaryFunction {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** @serial the function */
  private final UnaryFunction m_f;

  /** @serial the maximum x value */
  private final double m_maxX;

  /** @serial the minimum x value */
  private final double m_minX;

  /** @serial the maximum input value */
  private final double m_upperLimitY;

  /** @serial what to return when the maximum input is exceeded */
  private final double m_upperLimitYExcess;

  /** @serial the minimum input value */
  private final double m_lowerLimitY;

  /** @serial what to return when the minimum input is exceeded */
  private final double m_lowerLimitYExcess;

  /** @serial is the function falling or rising? */
  private final boolean m_falling;

  /**
   * Create the inverse
   * 
   * @param f
   *          the function
   * @param minX
   *          the minimum x value
   * @param maxX
   *          the maximum x value
   * @param lowerLimitY
   *          the minimum input value
   * @param lowerLimitYExcess
   *          what to return when the minimum input is exceeded
   * @param upperLimitY
   *          the maximum input value
   * @param upperLimitYExcess
   *          what to return when the maximum input is exceeded
   * @param falling
   *          is the function falling or rising?
   */
  public BinarySearchUnaryInverse(final UnaryFunction f,
      final double minX, final double maxX, final double lowerLimitY,
      final double lowerLimitYExcess, final double upperLimitY,
      final double upperLimitYExcess, final boolean falling) {
    super();
    this.m_f = f;

    this.m_minX = minX;
    this.m_maxX = maxX;
    this.m_lowerLimitY = lowerLimitY;
    this.m_lowerLimitYExcess = lowerLimitYExcess;
    this.m_upperLimitY = upperLimitY;
    this.m_upperLimitYExcess = upperLimitYExcess;
    this.m_falling = falling;
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    return ('(' + this.m_f.toString() + ")^-1"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1) {
    double lower, upper, mid, midVal, testA, testB, testC, testD;
    final double lowerY, upperY;
    final UnaryFunction f;
    final boolean falling;
    final long keyBits;
    long midBits;
    boolean setLower, hasRes;

    lowerY = this.m_lowerLimitY;
    upperY = this.m_upperLimitY;
    lower = this.m_minX;
    upper = this.m_maxX;
    falling = this.m_falling;

    if (x1 <= lowerY) {
      if (x1 >= lowerY) {
        return (falling ? upper : lower);
      }
      return this.m_lowerLimitYExcess;
    }
    if (x1 >= upperY) {
      if (x1 <= upperY) {
        return (falling ? lower : upper);
      }
      return this.m_upperLimitYExcess;
    }

    f = this.m_f;
    keyBits = Double.doubleToLongBits(x1);
    while (lower < upper) {
      mid = (0.5d * (lower + upper));
      midVal = f.compute(mid);
      hasRes = false;
      if (midVal < x1) {
        setLower = (!(falling));
      } else {
        if (midVal > x1) {
          setLower = falling;
        } else {
          if (midVal == x1) {
            hasRes = true;
            setLower = false;
          } else {
            midBits = Double.doubleToLongBits(midVal);
            if (midBits == keyBits) {
              hasRes = true;
              setLower = false;
            } else {
              if (midBits < keyBits) {
                setLower = (!falling);
              } else {
                setLower = falling;
              }
            }
          }
        }
      }

      if (hasRes) {
        testA = Math.rint(mid);
        if ((testA > lower) && (testA < upper) && (f.compute(testA) == x1)) {
          return testA;
        }
        testB = Math.ceil(mid);
        if ((testB != testA) && (testB > lower) && (testB < upper)
            && (f.compute(testB) == x1)) {
          return testB;
        }
        testC = Math.floor(mid);
        if ((testC != testA) && (testC != testB) && (testC > lower)
            && (testC < upper) && (f.compute(testC) == x1)) {
          return testC;
        }
        testD = (0.5d * (testB + testC));
        if ((testD != testA) && (testD != testB) && (testD != testA)
            && (testD > lower) && (testD < upper)
            && (f.compute(testD) == x1)) {
          return testD;
        }
        return mid;
      }

      if (setLower) {
        if (mid == lower) {
          lower = Math.nextUp(lower);
        } else {
          lower = mid;
        }
      } else {
        if (mid == upper) {
          upper = Math.nextAfter(upper, Double.NEGATIVE_INFINITY);
        } else {
          upper = mid;
        }
      }
    }
    return lower;
  }

  /** {@inheritDoc} */
  @Override
  public final UnaryFunction invertFor(final int index) {
    return this.m_f;
  }
}
