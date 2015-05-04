package org.logisticPlanning.utils.math.functions.combinatoric;

import org.logisticPlanning.utils.math.functions.BinaryFunction;
import org.logisticPlanning.utils.math.functions.special.Beta;
import org.logisticPlanning.utils.math.functions.special.GammaLn;
import org.logisticPlanning.utils.utils.comparison.ComparisonUtils;

/**
 * <p>
 * The binomial coefficient function.
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
public final class BinomialCoefficient extends BinaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final BinomialCoefficient INSTANCE = new BinomialCoefficient();

  /** instantiate */
  private BinomialCoefficient() {
    super();
  }

  /**
   * compute the binomial coefficient
   *
   * @param n
   *          the n
   * @param k
   *          the k
   * @return the coefficient
   */
  @Override
  public final long compute(final long n, final long k) {
    if ((k < 0l) || (k > n) || (n < 0l)) {
      return 0l;
    }

    if ((k <= 0l) || (k >= n)) {
      return 1l;
    }

    if ((k <= 1l) || (k >= (n - 1l))) {
      return n;
    }

    return BinomialCoefficient.internalBinomial(n, k);
  }

  /**
   * compute the binomial coefficient
   *
   * @param n
   *          the n
   * @param k
   *          the k
   * @return the coefficient, or {@code -1l} on overflow
   */
  private static final long internalBinomial(final long n, final long k) {
    long r, d, v, rn, g;
    final long kk;
    final int ni, ki;

    if (n < Factorial.FACTORIALS.length) {
      ni = ((int) n);
      ki = ((int) k);
      return ((Factorial.FACTORIALS[ni] / Factorial.FACTORIALS[ki]) / //
          Factorial.FACTORIALS[ni - ki]);
    }

    g = (n >>> 1);
    if (k > g) {
      kk = (n - k);
    } else {
      kk = k;
    }

    r = 1l;
    v = n;
    for (d = 1l; d <= kk; d++) {
      rn = ((r * v) / d);

      // overflow handling
      if (rn <= r) {
        g = GCD.INSTANCE.compute(r, d);
        rn = (((r / g) * v) / (d / g));
        if (rn <= r) {
          g = GCD.INSTANCE.compute(v, d);
          rn = ((r * (v / g)) / (d / g));
          if (rn <= r) {
            return (-1l);
          }
        }
      }
      v--;
      r = rn;
    }

    return r;
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1, final double x2) {
    final double a, b, c, nmk, ra, rb;
    final long ln, lk, lr, nmkl;

    if ((x2 < 0d) || (x2 > x1) || (x1 < 0d)) {
      return 0d;
    }

    if ((x2 <= 0d) || (x2 >= x1)) {
      return 1d;
    }

    if ((x2 <= 1d) || (x2 >= (x1 - 1d))) {
      return x1;
    }

    if ((x2 < Long.MAX_VALUE) && (x1 < Long.MAX_VALUE)) {
      lk = Math.round(x2);
      ln = Math.round(x1);
      lr = BinomialCoefficient.internalBinomial(ln, lk);
      if (lr >= 0l) {
        return lr;
      }

      nmkl = (ln - lk);
      a = (x2 * (Beta.INSTANCE.compute(x2, nmkl + 1l)));
      b = (nmkl * (Beta.INSTANCE.compute(x2 + 1l, nmkl)));
    } else {
      nmk = (x1 - x2);
      a = (x2 * (Beta.INSTANCE.compute(x2, nmk + 1d)));
      b = (nmk * (Beta.INSTANCE.compute(x2 + 1d, nmk)));
    }

    ra = (1d / a);
    rb = (1d / b);
    if (Double.isInfinite(ra) || Double.isNaN(ra)) {
      if (Double.isInfinite(rb) || Double.isNaN(rb)) {
        return Math.exp(GammaLn.INSTANCE.compute(x1 + 1d)
            - GammaLn.INSTANCE.compute(x2 + 1d)
            - GammaLn.INSTANCE.compute((x1 - x2) + 1d));
      }

      return rb;
    }
    if (Double.isInfinite(rb) || Double.isNaN(rb)) {
      return ra;
    }

    if (ComparisonUtils.equals(ra, rb)) {
      return ra;
    }

    c = ((0.5d / a) + (0.5d / b));
    if (Double.isInfinite(c) || Double.isNaN(c)) {
      return ra;
    }
    return c;
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link BinomialCoefficient#INSTANCE
   * BinomialCoefficient.INSTANCE} for serialization, i.e., when the
   * instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   *
   * @return the replacement instance (always
   *         {@link BinomialCoefficient#INSTANCE
   *         BinomialCoefficient.INSTANCE} )
   */
  private final Object writeReplace() {
    return BinomialCoefficient.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link BinomialCoefficient#INSTANCE
   * BinomialCoefficient.INSTANCE} after serialization, i.e., when the
   * instance is read with {@link java.io.ObjectInputStream#readObject()}.
   *
   * @return the replacement instance (always
   *         {@link BinomialCoefficient#INSTANCE
   *         BinomialCoefficient.INSTANCE} )
   */
  private final Object readResolve() {
    return BinomialCoefficient.INSTANCE;
  }

}
