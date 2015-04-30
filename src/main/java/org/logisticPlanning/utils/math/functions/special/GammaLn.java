package org.logisticPlanning.utils.math.functions.special;

import org.logisticPlanning.utils.math.MathConstants;
import org.logisticPlanning.utils.math.functions.UnaryFunction;

/**
 * <p>
 * The logarithm of the gamma function.
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
public final class GammaLn extends UnaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final GammaLn INSTANCE = new GammaLn();

  /** Lanczos coefficients */
  private static final double[] LANCZOS = { 0.99999999999999709182d,
      57.156235665862923517d, -59.597960355475491248d,
      14.136097974741747174d, -0.49191381609762019978d,
      0.33994649984811888699e-4d, 0.46523628927048575665e-4d,
      -0.98374475304879564677e-4d, 0.15808870322491248884e-3d,
      -0.21026444172410488319e-3d, 0.21743961811521264320e-3d,
      -0.16431810653676389022e-3d, 0.84418223983852743293e-4d,
      -0.26190838401581408670e-4d, 0.36899182659531622704e-5d };

  /** instantiate */
  private GammaLn() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1) {
    double tmp, g, sum;
    int i;

    if (Double.isNaN(x1) || (x1 <= 0.0)) {
      return Double.NaN;
    }
    g = (607.0d / 128.0d);
    sum = 0.0d;
    for (i = GammaLn.LANCZOS.length; (--i) > 0;) {
      sum += (GammaLn.LANCZOS[i] / (x1 + i));
    }
    sum += GammaLn.LANCZOS[0];

    tmp = x1 + g + 0.5d;
    return (((x1 + 0.5d) * Math.log(tmp)) - tmp)
        + MathConstants.HALF_LN_2_PI + Math.log(sum / x1);
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link GammaLn#INSTANCE GammaLn.INSTANCE}
   * for serialization, i.e., when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   * 
   * @return the replacement instance (always {@link GammaLn#INSTANCE
   *         GammaLn.INSTANCE})
   */
  private final Object writeReplace() {
    return GammaLn.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link GammaLn#INSTANCE GammaLn.INSTANCE}
   * after serialization, i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   * 
   * @return the replacement instance (always {@link GammaLn#INSTANCE
   *         GammaLn.INSTANCE})
   */
  private final Object readResolve() {
    return GammaLn.INSTANCE;
  }

}
