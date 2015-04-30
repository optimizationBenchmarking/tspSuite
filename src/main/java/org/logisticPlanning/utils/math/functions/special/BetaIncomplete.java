package org.logisticPlanning.utils.math.functions.special;

import org.logisticPlanning.utils.math.MathConstants;
import org.logisticPlanning.utils.math.functions.TernaryFunction;

/**
 * <p>
 * The incomplete Beta function.
 * </p>
 * <h2>Inspiration</h2>
 * <p>
 * Some of the more complex mathematical routines in this package, such as
 * this class may have been inspired by &ndash; or derived from freely
 * available open source code in the internet (although this &quot;function
 * object&quot; idea is by a unique &quot;invention&quot; by myself).
 * Unfortunately, this has been a quite some time ago for personal use, so
 * I (<a href="mailto:tweise@gmx.de">Thomas Weise</a>) do not remember
 * anymore from where the original stems.
 * </p>
 * <p>
 * Also, most of the code has undergone quite a few revisions,
 * refactorings, and modifications, so it often is quite different from the
 * potential originals.
 * <p>
 * <p>
 * I vaguely remember that some code may have been inspired by the <a
 * href="https://commons.apache.org/proper/commons-math/">The Apache
 * Commons Mathematics Library</a>&nbsp;[<a href="#cite_A2013CMTACML"
 * style="font-weight:bold">1</a>] which is currently is licensed under <a
 * href="https://www.apache.org/licenses/">Apache License 2.0</a> which is
 * GPL compatible &ndash; but again, I am not sure anymore, it's too long
 * ago.
 * </p>
 * <p>
 * If you know the original source of this code or find a very similar
 * package, please let me know so that I can properly cite it. If you feel
 * that this code here somehow infringes your copyright, please let me know
 * as well and I will cease putting it online or replace it with different
 * code.<br/>
 * Many thanks.<br/>
 * Thomas.
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_A2013CMTACML" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Commons Math: The
 * Apache Commons Mathematics Library,&rdquo;</span> (Website),
 * April&nbsp;7, 2013, Forest Hill, MD, USA: Apache Software Foundation.
 * <div>link: [<a
 * href="https://commons.apache.org/proper/commons-math/">1</
 * a>]</div></div></li>
 * </ol>
 */
public final class BetaIncomplete extends TernaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final BetaIncomplete INSTANCE = new BetaIncomplete();

  /** The maximum value where gamma(x) returns an exact result. */
  private static final double MAX_GAMMA;

  /** First helper. */
  private static final double ICG_BIG;

  /** Second helper. */
  private static final double ICG_BIGINV;

  static {
    double d, s, m;
    d = 1.0;
    s = 1E3;

    ICG_BIG = (2.0d * MathConstants.EPS);
    ICG_BIGINV = (1.0d / BetaIncomplete.ICG_BIG);

    while (d != s) {
      m = (d + s) * 0.5;
      if (Double.isInfinite(Gamma.INSTANCE.compute(m))) {
        s = m - (MathConstants.EPS * m);
      } else {
        d = m + (MathConstants.EPS * m);
      }
    }

    MAX_GAMMA = d;
  }

  /** instantiate */
  private BetaIncomplete() {
    super();
  }

  /**
   * Continued fraction expansion #1 for incomplete beta integral.
   * 
   * @param a
   *          double value
   * @param b
   *          double value
   * @param x
   *          double value
   * @return The Incomplete Pow CEC2012_Function evaluated from zero to xx.
   */
  private static final double __incbcf(final double a, final double b,
      final double x) {
    double xk, pk, pkm1, pkm2, qk, qkm1, qkm2, k1, k2, k3, k4, k5, k6, k7, k8, r, t, ans, thresh;

    int n;

    k1 = a;
    k2 = (a + b);
    k3 = a;
    k4 = (a + 1.0d);
    k5 = 1.0d;
    k6 = (b - 1.0d);
    k7 = k4;
    k8 = (a + 2.0);

    pkm2 = 0.0d;
    qkm2 = 1.0d;
    pkm1 = 1.0d;
    qkm1 = 1.0d;
    ans = 1.0d;
    r = 1.0;
    n = 0;

    thresh = 3.0 * MathConstants.EPS;

    do {
      xk = -(x * k1 * k2) / (k3 * k4);
      pk = pkm1 + (pkm2 * xk);
      qk = qkm1 + (qkm2 * xk);
      pkm2 = pkm1;
      pkm1 = pk;
      qkm2 = qkm1;
      qkm1 = qk;

      xk = (x * k5 * k6) / (k7 * k8);
      pk = pkm1 + (pkm2 * xk);
      qk = qkm1 + (qkm2 * xk);
      pkm2 = pkm1;
      pkm1 = pk;
      qkm2 = qkm1;
      qkm1 = qk;

      if (qk != 0) {
        r = pk / qk;
      }

      if (r != 0) {
        t = Math.abs((ans - r) / r);
        ans = r;
      } else {
        t = 1.0d;
      }

      if (t < thresh) {
        return ans;
      }

      k1 += 1.0d;
      k2 += 1.0d;
      k3 += 2.0d;
      k4 += 2.0d;
      k5 += 1.0d;
      k6 -= 1.0d;
      k7 += 2.0d;
      k8 += 2.0d;

      if ((Math.abs(qk) + Math.abs(pk)) > BetaIncomplete.ICG_BIG) {
        pkm2 *= BetaIncomplete.ICG_BIGINV;
        pkm1 *= BetaIncomplete.ICG_BIGINV;
        qkm2 *= BetaIncomplete.ICG_BIGINV;
        qkm1 *= BetaIncomplete.ICG_BIGINV;
      }

      if ((Math.abs(qk) < BetaIncomplete.ICG_BIG)
          || (Math.abs(pk) < BetaIncomplete.ICG_BIGINV)) {
        pkm2 *= BetaIncomplete.ICG_BIG;
        pkm1 *= BetaIncomplete.ICG_BIG;
        qkm2 *= BetaIncomplete.ICG_BIG;
        qkm1 *= BetaIncomplete.ICG_BIG;
      }
    } while ((++n) < 300);

    return ans;
  }

  /**
   * Continued fraction expansion #2 for incomplete beta integral
   * 
   * @param a
   *          double value
   * @param b
   *          double value
   * @param x
   *          double value
   * @return The Incomplete Pow CEC2012_Function evaluated from zero to xx.
   */
  private static final double __incbd(final double a, final double b,
      final double x) {
    double xk, pk, pkm1, pkm2, qk, qkm1, qkm2, k1, k2, k3, k4, k5, k6, k7, k8, r, t, ans, z, thresh;
    int n;

    k1 = a;
    k2 = b - 1.0d;
    k3 = a;
    k4 = a + 1.0d;
    k5 = 1.0d;
    k6 = a + b;
    k7 = a + 1.0d;
    k8 = a + 2.0d;

    pkm2 = 0.0;
    qkm2 = 1.0;
    pkm1 = 1.0;
    qkm1 = 1.0;
    z = x / (1.0d - x);
    ans = 1.0;
    r = 1.0;
    n = 0;

    thresh = 3.0 * MathConstants.EPS;

    do {
      xk = -(z * k1 * k2) / (k3 * k4);
      pk = pkm1 + (pkm2 * xk);
      qk = qkm1 + (qkm2 * xk);
      pkm2 = pkm1;
      pkm1 = pk;
      qkm2 = qkm1;
      qkm1 = qk;

      xk = (z * k5 * k6) / (k7 * k8);
      pk = pkm1 + (pkm2 * xk);
      qk = qkm1 + (qkm2 * xk);
      pkm2 = pkm1;
      pkm1 = pk;
      qkm2 = qkm1;
      qkm1 = qk;

      if (qk != 0) {
        r = pk / qk;
      }

      if (r != 0) {
        t = Math.abs((ans - r) / r);
        ans = r;
      } else {
        t = 1.0d;
      }

      if (t < thresh) {
        return ans;
      }

      k1 += 1.0;
      k2 -= 1.0;
      k3 += 2.0;
      k4 += 2.0;
      k5 += 1.0;
      k6 += 1.0;
      k7 += 2.0;
      k8 += 2.0;

      if ((Math.abs(qk) + Math.abs(pk)) > BetaIncomplete.ICG_BIG) {
        pkm2 *= BetaIncomplete.ICG_BIGINV;
        pkm1 *= BetaIncomplete.ICG_BIGINV;
        qkm2 *= BetaIncomplete.ICG_BIGINV;
        qkm1 *= BetaIncomplete.ICG_BIGINV;
      }

      if ((Math.abs(qk) < BetaIncomplete.ICG_BIGINV)
          || (Math.abs(pk) < BetaIncomplete.ICG_BIGINV)) {
        pkm2 *= BetaIncomplete.ICG_BIG;
        pkm1 *= BetaIncomplete.ICG_BIG;
        qkm2 *= BetaIncomplete.ICG_BIG;
        qkm1 *= BetaIncomplete.ICG_BIG;
      }
    } while (++n < 300);

    return ans;
  }

  /**
   * Power series for incomplete beta integral. Use when b*x is small and x
   * not too close to 1.
   * 
   * @param a
   *          double value
   * @param b
   *          double value
   * @param x
   *          double value
   * @return The Incomplete Pow CEC2012_Function evaluated from zero to xx.
   */
  private static final double __pseries(final double a, final double b,
      final double x) {
    double s, t, u, v, n, t1, z, ai;

    ai = (1.0d / a);
    u = (1.0d - b) * x;
    v = u / (a + 1.0);
    t1 = v;
    t = u;
    n = 2.0d;
    s = 0.0d;
    z = MathConstants.EPS * ai;

    while (Math.abs(v) > z) {
      u = ((n - b) * x) / n;
      t *= u;
      v = t / (a + n);
      s += v;
      n += 1.0d;
    }

    s += t1;
    s += ai;

    u = a * Math.log(x);

    if (((a + b) < BetaIncomplete.MAX_GAMMA)
        && (Math.abs(u) < MathConstants.LN_MAX_DOUBLE)) {
      t = Gamma.INSTANCE.compute(a + b)
          / (Gamma.INSTANCE.compute(a) * Gamma.INSTANCE.compute(b));
      s = s * t * Math.pow(x, a);
    } else {
      t = (GammaLn.INSTANCE.compute(a + b) - GammaLn.INSTANCE.compute(a) - GammaLn.INSTANCE
          .compute(b)) + u + Math.log(s);

      if (t < MathConstants.LN_MIN_DOUBLE) {
        s = 0.0d;
      } else {
        s = Math.exp(t);
      }
    }

    return s;
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1, final double x2,
      final double x3) {
    double a, b, t, x, xc, w, y;
    boolean flag;

    flag = false;

    if (((x2 * x3) <= 1.0d) && (x3 <= 0.95)) {
      return BetaIncomplete.__pseries(x1, x2, x3);
    }

    w = (1.0d - x3);

    if (x3 > (x1 / (x1 + x2))) {
      flag = true;
      a = x2;
      b = x1;
      xc = x3;
      x = w;
    } else {
      a = x1;
      b = x2;
      xc = w;
      x = x3;
    }

    if (flag && ((b * x) <= 1.0) && (x <= 0.95)) {
      t = BetaIncomplete.__pseries(a, b, x);

      if (t <= MathConstants.EPS) {
        t = 1.0d - MathConstants.EPS;
      } else {
        t = 1.0 - t;
      }

      return t;
    }

    y = (x * ((a + b) - 2.0d)) - (a - 1.0);

    if (y < 0.0d) {
      w = BetaIncomplete.__incbcf(a, b, x);
    } else {
      w = BetaIncomplete.__incbd(a, b, x) / xc;
    }

    y = a * Math.log(x);
    t = b * Math.log(xc);

    if (((a + b) < BetaIncomplete.MAX_GAMMA)
        && (Math.abs(y) < MathConstants.LN_MAX_DOUBLE)
        && (Math.abs(t) < MathConstants.LN_MAX_DOUBLE)) {
      t = Math.pow(xc, b);
      t *= Math.pow(x, a);
      t /= a;
      t *= w;
      t *= Gamma.INSTANCE.compute(a + b)
          / (Gamma.INSTANCE.compute(a) * Gamma.INSTANCE.compute(b));

      if (flag) {
        if (t <= MathConstants.EPS) {
          t = 1.0d - MathConstants.EPS;
        } else {
          t = 1.0 - t;
        }
      }
      return t;
    }

    y += (t + GammaLn.INSTANCE.compute(a + b))
        - GammaLn.INSTANCE.compute(a) - Gamma.INSTANCE.compute(b);
    y += Math.log(w / a);

    if (y < MathConstants.LN_MIN_DOUBLE) {
      t = 0.0;
    } else {
      t = Math.exp(y);
    }

    if (flag) {
      if (t <= MathConstants.EPS) {
        t = 1.0d - MathConstants.EPS;
      } else {
        t = 1.0d - t;
      }
    }

    return t;

  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link BetaIncomplete#INSTANCE
   * BetaIncomplete.INSTANCE} for serialization, i.e., when the instance is
   * written with {@link java.io.ObjectOutputStream#writeObject(Object)}.
   * 
   * @return the replacement instance (always
   *         {@link BetaIncomplete#INSTANCE BetaIncomplete.INSTANCE})
   */
  private final Object writeReplace() {
    return BetaIncomplete.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link BetaIncomplete#INSTANCE
   * BetaIncomplete.INSTANCE} after serialization, i.e., when the instance
   * is read with {@link java.io.ObjectInputStream#readObject()}.
   * 
   * @return the replacement instance (always
   *         {@link BetaIncomplete#INSTANCE BetaIncomplete.INSTANCE})
   */
  private final Object readResolve() {
    return BetaIncomplete.INSTANCE;
  }

}
