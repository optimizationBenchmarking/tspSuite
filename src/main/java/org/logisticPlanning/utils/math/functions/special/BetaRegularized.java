package org.logisticPlanning.utils.math.functions.special;

import org.logisticPlanning.utils.math.ContinuedFraction;
import org.logisticPlanning.utils.math.MathConstants;
import org.logisticPlanning.utils.math.functions.TernaryFunction;

/**
 * <p>
 * The regularized Beta function.
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
public final class BetaRegularized extends TernaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final BetaRegularized INSTANCE = new BetaRegularized();

  /** the internally used fraction instance */
  private static final BetaFraction FRAC = new BetaFraction();

  /** instantiate */
  private BetaRegularized() {
    super();
  }

  /**
   * Returns the regularized beta function I(x, a, b). The implementation
   * of this method is based on:
   * <ul>
   * <li><a
   * href="http://mathworld.wolfram.com/RegularizedBetaFunction.html">
   * Regularized Pow CEC2012_Function</a>.</li>
   * <li><a href="http://functions.wolfram.com/06.21.10.0001.01">
   * Regularized Pow CEC2012_Function</a>.</li>
   * </ul>
   * 
   * @param x
   *          the value.
   * @param a
   *          the a parameter.
   * @param b
   *          the b parameter.
   * @param epsilon
   *          When the absolute value of the nth item in the series is less
   *          than epsilon the approximation ceases to calculate further
   *          elements in the series.
   * @return the regularized beta function I(x, a, b)
   */
  public static final double regularizedBeta(final double x,
      final double a, final double b, final double epsilon) {

    if (Double.isNaN(x) || Double.isNaN(a) || Double.isNaN(b) || (x < 0d)
        || (x > 1d) || (a <= 0.0d) || (b <= 0.0d)) {
      return Double.NaN;
    }

    if (x > ((a + 1.0d) / (a + b + 2.0d))) {
      return (1.0d - BetaRegularized.regularizedBeta(1.0d - x, b, a,
          epsilon));
    }

    return (Math.exp(((a * Math.log(x)) + (b * Math.log(1.0 - x)))
        - Math.log(a) - BetaLn.INSTANCE.compute(a, b)) * 1.0)
        / BetaRegularized.FRAC.evaluate(x, a, b, epsilon);
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1, final double x2,
      final double x3) {
    return BetaRegularized.regularizedBeta(x1, x2, x3, MathConstants.EPS);
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link BetaRegularized#INSTANCE
   * BetaRegularized.INSTANCE} for serialization, i.e., when the instance
   * is written with {@link java.io.ObjectOutputStream#writeObject(Object)}
   * .
   * 
   * @return the replacement instance (always
   *         {@link BetaRegularized#INSTANCE BetaRegularized.INSTANCE})
   */
  private final Object writeReplace() {
    return BetaRegularized.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link BetaRegularized#INSTANCE
   * BetaRegularized.INSTANCE} after serialization, i.e., when the instance
   * is read with {@link java.io.ObjectInputStream#readObject()}.
   * 
   * @return the replacement instance (always
   *         {@link BetaRegularized#INSTANCE BetaRegularized.INSTANCE})
   */
  private final Object readResolve() {
    return BetaRegularized.INSTANCE;
  }

  /** the internal beta fraction */
  private static final class BetaFraction extends ContinuedFraction {

    /** instantiate */
    protected BetaFraction() {
      super();
    }

    /** {@inheritDoc} */
    @Override
    public final double getA(final int n, final double x, final double a,
        final double b) {
      return 1d;
    }

    /** {@inheritDoc} */
    @Override
    public final double getB(final int n, final double x, final double a,
        final double b) {
      double m;

      if ((n % 2) == 0) { // even
        m = (n >>> 1);
        return (m * (b - m) * x) / (((a + (2 * m)) - 1) * (a + (2 * m)));
      }

      m = ((n - 1) >>> 1);
      return -((a + m) * (a + b + m) * x)
          / ((a + (2 * m)) * (a + (2 * m) + 1.0));
    }

  }
}
