package org.logisticPlanning.utils.utils.comparison;

/**
 * Some utilities for comparing, especially for numerical stuff.
 */
public final class ComparisonUtils {

  /**
   * A comparator that compares objects which are instances of comparable.
   * It basically does the same as {@link #compare(Comparable, Comparable)}
   */
  @SuppressWarnings("rawtypes")
  public static final PreciseComparator<Comparable> COMPARABLE_COMPARATOR = _ComparableComparator.INSTANCE;

  /**
   * Compare one {@code double} to another one. This method sets
   * {@code 0d == -0d}, although
   * <code>{@link java.lang.Double#doubleToLongBits(double) Double.doubleToLongBits(0d)==0}</code>
   * while
   * <code>{@link java.lang.Double#doubleToLongBits(double) Double.doubleToLongBits(-0d)==-9223372036854775808}</code>
   * . <code>equals(0d, -0d) == true</code>. {@link java.lang.Double#NaN}
   * values are treated as equal to each other.
   *
   * @param a
   *          the first {@code double}
   * @param b
   *          the second {@code double}
   * @return {@code -1} if {@code a<b}, {@code 0} if {@code a equals b},
   *         {@code 1} if {@code a>b}
   */
  public static final int compare(final double a, final double b) {
    final boolean aNaN, bNaN;

    if (a < b) {
      return (-1);
    }
    if (a > b) {
      return 1;
    }
    if (a == b) {
      return 0;
    }

    aNaN = (a != a);
    bNaN = (b != b);
    if (aNaN && bNaN) {
      return 0;
    }
    if (aNaN) {
      return 1;
    }
    if (bNaN) {
      return (-1);
    }

    throw new IllegalStateException(//
        ("Impossible error occured: compare " //$NON-NLS-1$
        + (a + " (" + (Double.doubleToRawLongBits(a) + //$NON-NLS-1$
        (") with " + (b + //$NON-NLS-1$
        (" (" + Double.doubleToRawLongBits(b))))))) + ')');//$NON-NLS-1$
  }

  /**
   * Does one {@code double} equal another one. This method sets
   * {@code 0d == -0d}, while
   * <code>{@link java.lang.Double#doubleToLongBits(double) Double.doubleToLongBits(0d)==0}</code>
   * while
   * <code>{@link java.lang.Double#doubleToLongBits(double) Double.doubleToLongBits(-0d)==-9223372036854775808}</code>
   * . <code>equals(0d, -0d) == true</code>.
   *
   * @param a
   *          the first {@code double}
   * @param b
   *          the second {@code double}
   * @return {@code true} if they are equal, {@code false} otherwise
   */
  public static final boolean equals(final double a, final double b) {
    return (ComparisonUtils.compare(a, b) == 0);
  }

  // /**
  // * Does one {@code float} equal another one. This method takes account
  // of
  // the
  // * fact that {@code 0f == -0f} but
  // * <code>{@link java.lang.Float#floatToIntBits(float)
  // Float.floatToIntBits(0f)==0}</code>
  // * while
  // * <code>{@link java.lang.Float#floatToIntBits(float)
  // Float.floatToIntBits(-0f)==-2147483648}</code>
  // * . <code>equals(0f, -0f) == true</code>.
  // *
  // * @param a
  // * the first {@code float}
  // * @param b
  // * the second {@code float}
  // * @return {@code true} if they are equal, {@code false} otherwise
  // */
  // public static final boolean equals(final float a, final float b) {
  // return ((a == b) || //
  // (Float.floatToIntBits(a) == Float.floatToIntBits(b)));
  // }

  /**
   * Does one object equal another one
   *
   * @param a
   *          the first object
   * @param b
   *          the second object
   * @return {@code true} if they are equal, {@code false} otherwise
   */
  public static final boolean equals(final Object a, final Object b) {
    if (a == b) {
      return true;
    }
    if (a == null) {
      return false;
    }
    if (b == null) {
      return false;
    }
    return a.equals(b);
  }

  /**
   * Compare one object equal another one
   *
   * @param a
   *          the first object
   * @param b
   *          the second object
   * @return the comparison result
   * @param <T>
   *          the type
   */
  public static final <T extends Comparable<T>> int compare(final T a,
      final T b) {
    return ComparisonUtils._compare(a, b);
  }

  /**
   * Compare one object equal another one
   *
   * @param a
   *          the first object
   * @param b
   *          the second object
   * @return the comparison result
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  static final int _compare(final Comparable a, final Comparable b) {
    if (a == b) {
      return 0;
    }
    if (a == null) {
      return 1;
    }
    if (b == null) {
      return (-1);
    }
    return a.compareTo(b);
  }

  /**
   * Check if a given value is zero or not. This method returns
   * {@code true} for {@code d == 0d} and {@code d == -0d}.
   *
   * @param d
   *          the double
   * @return true if the value is zero, false if not
   */
  public static final boolean isZero(final double d) {
    return (d == 0d);
  }

  /**
   * Check if a given value is integer or not. This method returns
   * {@code true} if and only if {@code d} is exactly (not just
   * approximately!) an integer.
   *
   * @param d
   *          the double
   * @return true if the value is integer, false if not
   */
  public static final boolean isInteger(final double d) {
    return ComparisonUtils.isZero(d - Math.rint(d));
  }

  /**
   * <p>
   * Check if there are no more than {@code maxValuesInBetween} values
   * between {@code double} {@code a} and {@code b}.
   * {@code maxValuesInBetween<0} means that the values must be equal to
   * match the condition, {@code maxValuesInBetween==0} means that
   * <code>{@link java.lang.Math#nextUp(double) Math.nextUp(a)}>=b</code>
   * if {@code a<b} (and vice versa otherwise) to match the condition,
   * {@code maxValuesInBetween==0} means that
   * <code>{@link java.lang.Math#nextUp(double) Math.nextUp(Math.nextUp(a))}>=b</code>
   * if {@code a<b} (and vice versa otherwise) to match the condition, and
   * so on.
   * </p>
   * <p>
   * If one of the two arguments is infinite and different from the other,
   * the result is {@code false}. If any of the arguments is
   * {@link java.lang.Double#NaN NaN}, the result is {@code false}.
   * </p>
   * <p>
   * <em>Warning:</em> supplying large values of {@code maxValuesInBetween}
   * will make the computation really slow.
   * </p>
   *
   * @param a
   *          the first double
   * @param b
   *          the second double
   * @param maxValuesInBetween
   *          the maximum values in between
   * @return {@code true} if there are no more {@code maxValuesInBetween}
   *         values between {@code double} {@code a} and {@code b},
   *         {@code  false} otherwise
   */
  public static final boolean isClose(final double a, final double b,
      final int maxValuesInBetween) {
    final double y;
    double x;
    int i;

    if (a < b) {
      if (a <= Double.NEGATIVE_INFINITY) {
        return false;
      }
      if (b >= Double.POSITIVE_INFINITY) {
        return false;
      }
      x = a;
      y = b;
    } else {
      if (a == b) {
        return true;
      }

      if ((b != b) || (b <= Double.NEGATIVE_INFINITY)) {
        return false;
      }
      if ((a != a) || (a >= Double.POSITIVE_INFINITY)) {
        return false;
      }

      x = b;
      y = a;
    }

    for (i = maxValuesInBetween; (i--) >= 0;) {
      x = Math.nextUp(x);
      if (x >= y) {
        return true;
      }
    }

    return false;
  }

  /**
   * Check whether a given {@code double} value is finite
   *
   * @param d
   *          the double value
   * @return {@code true} if {@code value} is not
   *         {@link java.lang.Double#NaN} and larger than
   *         {@link java.lang.Double#NEGATIVE_INFINITY} and smaller than
   *         {@link java.lang.Double#POSITIVE_INFINITY},
   */
  public static final boolean isFinite(final double d) {
    return (/* (d==d)&& */(d > Double.NEGATIVE_INFINITY) && (d < Double.POSITIVE_INFINITY));
  }
}
