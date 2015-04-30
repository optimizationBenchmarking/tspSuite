package org.logisticPlanning.utils.math.units;

import org.logisticPlanning.utils.math.functions.combinatoric.GCD;

/**
 * The internal class for fractions to be used when building the unit
 * conversion table.
 */
final class _Frac {

  /** the up */
  private final long m_up;

  /** the down */
  private final long m_down;

  /**
   * create
   * 
   * @param up
   *          the up
   * @param down
   *          the down
   */
  private _Frac(final long up, final long down) {
    super();
    this.m_up = up;
    this.m_down = down;
  }

  /**
   * get the double value
   * 
   * @param f
   *          the frac
   * @return the double value
   */
  static final double _double(final _Frac f) {
    if (f == null) {
      return 0d;
    }
    return ((double) (f.m_up)) / ((double) (f.m_down));
  }

  /**
   * make a new fraction
   * 
   * @param up
   *          the up
   * @param down
   *          the down
   * @return the new fraction
   */
  static final _Frac _make(final long up, final long down) {
    long gcd;

    gcd = GCD.INSTANCE.compute(up, down);

    if (gcd != 1) {
      return new _Frac((up / gcd), (down / gcd));
    }
    return new _Frac(up, down);
  }

  /**
   * invert a fraction
   * 
   * @param a
   *          the fraction
   * @return the inverse
   */
  static final _Frac _inv(final _Frac a) {
    return new _Frac(a.m_down, a.m_up);
  }

  /**
   * multiply
   * 
   * @param a_up
   *          the a_up
   * @param a_down
   *          the a_down
   * @param b_up
   *          the b_up
   * @param b_down
   *          the b_down
   * @return the result, or {@code null} if a loss of precision would have
   *         happened
   */
  private static final _Frac __mul(final long a_up, final long a_down,
      final long b_up, final long b_down) {
    final long gcd_a_up_b_down, gcd_a_down_b_up, new_up, new_down;
    long m1, m2;

    if ((a_up <= 0l) || (b_up <= 0l)) {
      return null;
    }

    gcd_a_up_b_down = GCD.INSTANCE.compute(a_up, b_down);
    gcd_a_down_b_up = GCD.INSTANCE.compute(a_down, b_up);

    m1 = (a_up / gcd_a_up_b_down);
    m2 = (b_up / gcd_a_down_b_up);
    if (m1 == 1l) {
      new_up = m2;
    } else {
      if (m2 == 1l) {
        new_up = m1;
      } else {
        if (m1 > (Long.MAX_VALUE / m2)) {
          return null;
        }
        new_up = (m1 * m2);
        // if ((new_up <= m1) || (new_up <= m2)) {
        // return null;
        // }
      }
    }

    m1 = (a_down / gcd_a_down_b_up);
    m2 = (b_down / gcd_a_up_b_down);
    if (m1 == 1l) {
      new_down = m2;
    } else {
      if (m2 == 1l) {
        new_down = m1;
      } else {
        if (m1 > (Long.MAX_VALUE / m2)) {
          return null;
        }
        new_down = (m1 * m2);
        // if ((new_down <= m1) || (new_down <= m2)) {
        // return null;
        // }
      }
    }

    return _Frac._make(new_up, new_down);
  }

  /**
   * multiply two fractions
   * 
   * @param a
   *          the first fraction
   * @param b
   *          the second fraction
   * @return the multiplication result
   */
  static final _Frac _mul(final _Frac a, final _Frac b) {
    return _Frac.__mul(a.m_up, a.m_down, b.m_up, b.m_down);
  }

  /**
   * divide two fractions
   * 
   * @param a
   *          the first fraction
   * @param b
   *          the second fraction
   * @return the division result
   */
  static final _Frac _div(final _Frac a, final _Frac b) {
    return _Frac.__mul(a.m_up, a.m_down, b.m_down, b.m_up);
  }
}
