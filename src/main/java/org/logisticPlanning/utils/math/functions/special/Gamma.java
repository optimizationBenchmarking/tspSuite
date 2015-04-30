package org.logisticPlanning.utils.math.functions.special;

import org.logisticPlanning.utils.math.MathConstants;
import org.logisticPlanning.utils.math.functions.UnaryFunction;
import org.logisticPlanning.utils.utils.comparison.ComparisonUtils;

/**
 * <p>
 * The gamma function.
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
public final class Gamma extends UnaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final Gamma INSTANCE = new Gamma();

  /** The calculus constant GAMMA_A. */
  private static final double GAMMA_A = (MathConstants.SQRT_2_PI * 1.000000000190015d);

  /** The calculus constant GAMMA_B. */
  private static final double GAMMA_B = (MathConstants.SQRT_2_PI * 76.18009172947146d);

  /** The calculus constant GAMMA_C. */
  private static final double GAMMA_C = (MathConstants.SQRT_2_PI * -86.50532032941677d);

  /** The calculus constant GAMMA_D. */
  private static final double GAMMA_D = (MathConstants.SQRT_2_PI * 24.01409824083091d);

  /** The calculus constant GAMMA_E. */
  private static final double GAMMA_E = (MathConstants.SQRT_2_PI * -1.231739572450155d);

  /** The calculus constant GAMMA_F. */
  private static final double GAMMA_F = (MathConstants.SQRT_2_PI * 0.1208650973866179E-2d);

  /** The calculus constant GAMMA_G. */
  private static final double GAMMA_G = (MathConstants.SQRT_2_PI * -0.5395239384953E-5d);

  /** instantiate */
  private Gamma() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1) {
    double d;
    boolean b;

    b = (x1 <= 0.5d);
    d = (b ? (1.0d - x1) : x1);
    d = Math.exp((Math.log(d + 5.5d) * (d + 0.5d)) - (d + 5.5d))
        * ((Gamma.GAMMA_A + (Gamma.GAMMA_B / (d + 1.0d))
            + (Gamma.GAMMA_C / (d + 2.0d)) + (Gamma.GAMMA_D / (d + 3.0d))
            + (Gamma.GAMMA_E / (d + 4.0d)) + (Gamma.GAMMA_F / (d + 5.0d)) + (Gamma.GAMMA_G / (d + 6.0d))) / d);
    if (b) {
      d = ((Math.PI / d) / Math.sin(Math.PI * x1));
    }

    if (ComparisonUtils.isInteger(x1)) {
      return Math.rint(d);
    }
    return d;
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link Gamma#INSTANCE Gamma.INSTANCE} for
   * serialization, i.e., when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   * 
   * @return the replacement instance (always {@link Gamma#INSTANCE
   *         Gamma.INSTANCE})
   */
  private final Object writeReplace() {
    return Gamma.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link Gamma#INSTANCE Gamma.INSTANCE}
   * after serialization, i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   * 
   * @return the replacement instance (always {@link Gamma#INSTANCE
   *         Gamma.INSTANCE})
   */
  private final Object readResolve() {
    return Gamma.INSTANCE;
  }

}
