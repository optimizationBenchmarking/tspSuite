package org.logisticPlanning.utils.math.functions.special;

import org.logisticPlanning.utils.math.MathConstants;
import org.logisticPlanning.utils.math.functions.BinaryFunction;

/**
 * <p>
 * The the upper incomplete gamma function.
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
public final class GammaRegularizedP extends BinaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final GammaRegularizedP INSTANCE = new GammaRegularizedP();

  /** instantiate */
  private GammaRegularizedP() {
    super();
  }

  /**
   * Adapted from Apache Software Foundation (ASF)'s commons.math. Returns
   * the regularized gamma function P(a, x). The implementation of this
   * method is based on:
   * <ul>
   * <li><a
   * href="http://mathworld.wolfram.com/RegularizedGammaFunction.html">
   * Regularized Gamma CEC2012_Function</a>, equation (1).</li>
   * <li><a
   * href="http://mathworld.wolfram.com/IncompleteGammaFunction.html">
   * Incomplete Gamma CEC2012_Function</a>, equation (4).</li>
   * <li><a href=
   * "http://mathworld.wolfram.com/ConfluentHypergeometricFunctionoftheFirstKind.html"
   * > Confluent Hypergeometric CEC2012_Function of the First Kind</a>,
   * equation (1).</li>
   * </ul>
   * 
   * @param a
   *          the a parameter.
   * @param x
   *          the value.
   * @param epsilon
   *          When the absolute value of the nth item in the series is less
   *          than epsilon the approximation ceases to calculate further
   *          elements in the series.
   * @return the regularized gamma function P(a, x)
   */
  public static final double regularizedGammaP(final double a,
      final double x, final double epsilon) {
    double n, an, sum;

    if (Double.isNaN(a) || Double.isNaN(x) || (a <= 0.0d) || (x < 0.0d)) {
      return Double.NaN;
    }
    if (x == 0.0d) {
      return 0.0;
    }
    if (x >= (a + 1)) {
      return 1.0 - GammaRegularizedQ.regularizedGammaQ(a, x, epsilon);
    }
    // calculate series
    n = 0.0d; // current element index
    an = 1.0d / a; // n-th element in the series
    sum = an; // partial sum
    while ((Math.abs(an / sum) > epsilon)
        && (sum < Double.POSITIVE_INFINITY)) {
      // compute next element in the series
      n += 1d;
      an *= (x / (a + n));

      // update partial sum
      sum += an;
    }

    if (Double.isInfinite(sum)) {
      return 1.0d;
    }

    return (Math.exp(((-x) + (a * Math.log(x)))
        - GammaLn.INSTANCE.compute(a)) * sum);

  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1, final double x2) {
    return GammaRegularizedP.regularizedGammaP(x1, x2, MathConstants.EPS);
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link GammaRegularizedP#INSTANCE
   * GammaRegularizedP.INSTANCE} for serialization, i.e., when the instance
   * is written with {@link java.io.ObjectOutputStream#writeObject(Object)}
   * .
   * 
   * @return the replacement instance (always
   *         {@link GammaRegularizedP#INSTANCE GammaRegularizedP.INSTANCE})
   */
  private final Object writeReplace() {
    return GammaRegularizedP.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link GammaRegularizedP#INSTANCE
   * GammaRegularizedP.INSTANCE} after serialization, i.e., when the
   * instance is read with {@link java.io.ObjectInputStream#readObject()}.
   * 
   * @return the replacement instance (always
   *         {@link GammaRegularizedP#INSTANCE GammaRegularizedP.INSTANCE})
   */
  private final Object readResolve() {
    return GammaRegularizedP.INSTANCE;
  }

}
