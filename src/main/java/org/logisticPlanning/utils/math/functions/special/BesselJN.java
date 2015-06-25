package org.logisticPlanning.utils.math.functions.special;

import org.logisticPlanning.utils.math.functions.BinaryFunction;

/**
 * <p>
 * The Bessel function jn.
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
public final class BesselJN extends BinaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final BesselJN INSTANCE = new BesselJN();

  /** The bessel acc value. */
  private static final double BESSEL_ACC = 40.0d;

  /** The bessel big value. */
  private static final double BESSEL_BIGNO = 1.0e+10d;

  /** The bessek small value. */
  private static final double BESSEL_BIGNI = 1.0e-10d;

  /** instantiate */
  private BesselJN() {
    super();
  }

  /**
   * Compute the Bessel function of order n of the argument.
   *
   * @param n
   *          integer order
   * @param x
   *          a double value
   * @return The Bessel function of order n of the argument. TODO: CHECK
   */
  public static final double besslJN(final int n, final double x) {
    int j, m;
    double ax, bj, bjm, bjp, sum, tox, ans;
    boolean jsum;

    if (n == 0) {
      return BesselJ0.INSTANCE.compute(x);
    }
    if (n == 1) {
      return BesselJ1.INSTANCE.compute(x);
    }

    ax = Math.abs(x);

    if (ax <= 0.0d) {
      return 0.0d;
    }

    tox = (2.0 / ax);

    if (ax > n) {
      bjm = BesselJ0.INSTANCE.compute(ax);
      bj = BesselJ1.INSTANCE.compute(ax);

      for (j = 1; j < n; j++) {
        bjp = (j * tox * bj) - bjm;
        bjm = bj;
        bj = bjp;
      }
      ans = bj;
    } else {
      m = (((n + ((int) (Math.sqrt(BesselJN.BESSEL_ACC * n)))) >> 1)) << 1;
      jsum = false;
      bjp = ans = sum = 0.0d;
      bj = 1.0d;

      for (j = m; j > 0; j--) {
        bjm = (j * tox * bj) - bjp;
        bjp = bj;
        bj = bjm;

        if (Math.abs(bj) > BesselJN.BESSEL_BIGNO) {
          bj *= BesselJN.BESSEL_BIGNI;
          bjp *= BesselJN.BESSEL_BIGNI;
          ans *= BesselJN.BESSEL_BIGNI;
          sum *= BesselJN.BESSEL_BIGNI;
        }

        if (jsum) {
          sum += bj;
        }

        jsum = !jsum;
        if (j == n) {
          ans = bjp;
        }
      }

      sum = ((sum + sum) - bj);
      ans /= sum;
    }
    return (((x < 0.0d) && ((n % 2) == 1)) ? (-ans) : ans);
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1, final double x2) {
    return BesselJN.besslJN(((int) (Math.round(x1))), x2);
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link BesselJN#INSTANCE
   * BesselJN.INSTANCE} for serialization, i.e., when the instance is
   * written with {@link java.io.ObjectOutputStream#writeObject(Object)}.
   *
   * @return the replacement instance (always {@link BesselJN#INSTANCE
   *         BesselJN.INSTANCE})
   */
  private final Object writeReplace() {
    return BesselJN.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link BesselJN#INSTANCE
   * BesselJN.INSTANCE} after serialization, i.e., when the instance is
   * read with {@link java.io.ObjectInputStream#readObject()}.
   *
   * @return the replacement instance (always {@link BesselJN#INSTANCE
   *         BesselJN.INSTANCE})
   */
  private final Object readResolve() {
    return BesselJN.INSTANCE;
  }

}
