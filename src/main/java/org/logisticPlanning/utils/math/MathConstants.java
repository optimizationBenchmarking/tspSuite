package org.logisticPlanning.utils.math;

/**
 * A set of common mathematical constants
 */
public final class MathConstants {

  /** the zero double */
  public static final Double DOUBLE_ZERO = Double.valueOf(0d);

  /** the one double */
  public static final Double DOUBLE_ONE = Double.valueOf(1d);

  /** the nan */
  public static final Double DOUBLE_NAN = Double.valueOf(Double.NaN);

  /** two pi */
  public static final double TWO_PI = 6.283185307179586476925286766559d;
  // (Math.PI + Math.PI);

  /** The natural logarithm of 10 */
  public static final double LN_10 = Math.log(10.0d);

  /** The natural logarithm of 2 */
  public static final double LN_2 = Math.log(2.0d);

  /** The square root of pi */
  public static final double SQRT_PI = Math.sqrt(Math.PI);

  /** The natural logarithm of the square root of pi */
  public static final double LOG_SQRT_PI = Math.log(MathConstants.SQRT_PI);

  /** 1.0 / square root of pi */
  public static final double INV_SQRT_PI = (1.0d / MathConstants.SQRT_PI);

  /** The square root of 2.0 */
  public static final double SQRT_2 = Math.sqrt(2.0d);

  /** The square root of 2*pi. */
  public static final double SQRT_2_PI = (MathConstants.SQRT_2 * MathConstants.SQRT_PI);

  /** The natural logarithm of the square root of pi. */
  public static final double LN_SQRT_PI = Math.log(MathConstants.SQRT_PI);
  /** Avoid repeated computation of log of 2 PI in logGamma */
  public static final double HALF_LN_2_PI = (0.5d * Math
      .log(2.0d * Math.PI));

  /**
   * Euler's (Mascheroni's) constant.
   */
  public static final double EULER_CONSTANT = 0.57721566490153286060651209008240243104215933593992359880576723488486772677766467093694706329174674951463144724980708248096050401448654283622417399764492353625350033374293733773767394279259525824709491600873520394816567d;

  /**
   * The smallest difference of two numbers, if they differ smaller, they
   * are considered as equal
   */
  public static final double EPS = Math.pow(2.0d, -52.0d);

  /** The natural logarithm of EPS */
  public static final double LN_EPS = Math.log(MathConstants.EPS);

  /**
   * The natural logarithm of Double.MAX_VALUE, or, in other words, the
   * biggest value where <code>Math.exp(x)</code> produces an exact result.
   *
   * @see Double#MAX_VALUE
   */
  public static final double LN_MAX_DOUBLE = Math.log(Double.MAX_VALUE);

  /**
   * The natural logarithm of Double.MIN_VALUE, or, in other words, the
   * smallest value where <code>Math.exp(x)</code> produces an exact
   * result.
   *
   * @see Double#MIN_VALUE
   */
  public static final double LN_MIN_DOUBLE = Math.log(Double.MIN_VALUE);

  /**
   * multiplying a number {@code s} by {@link #GOLDEN_RATIO} will give you
   * the longer part of it if it is cut in the golden ratio
   */
  public static final double GOLDEN_RATIO = (0.5d * (Math.sqrt(5d) - 1d));

  /** forbidden */
  private MathConstants() {
    throw new UnsupportedOperationException();
  }
}
