package org.logisticPlanning.utils.math.functions.stochastic;

import org.logisticPlanning.utils.math.MathConstants;
import org.logisticPlanning.utils.math.functions.BinaryFunction;

/**
 * <p>
 * Compute the chi square distribution.
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
public final class ChiSquareCDF extends BinaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final ChiSquareCDF INSTANCE = new ChiSquareCDF();

  /** instantiate */
  private ChiSquareCDF() {
    super();
  }

  /**
   * Computes the cumulative of the chi square distribution which is the
   * probability of d.
   *
   * @param x
   *          the x-value
   * @param dof
   *          the degrees of freedom
   * @return the probability of x TODO: CHECK
   */
  public static final double chiSquareCDF(final double x, final int dof) {
    double a, y, s, e, c, z, xx;
    boolean even;

    if ((x <= 0.0d) || (dof < 1)) {
      return 0.0d;// TODO: was 1, but 1 makes no sense here...
    }

    xx = x;
    a = 0.5 * xx;
    even = (dof & 1) == 0;

    if (dof > 1) {
      y = Math.exp(-a);
    } else {
      y = 0.0;
    }

    s = (even ? y : (2.0 * NormalCDF.INSTANCE.compute(-Math.sqrt(xx))));

    if (dof > 2) {
      xx = 0.5 * (dof - 1.0);
      z = (even ? 1.0 : 0.5);

      if (a > (-MathConstants.LN_EPS)) {
        e = (even ? 0.0 : MathConstants.LN_SQRT_PI);
        c = Math.log(a);

        while (z <= xx) {
          e += Math.log(z);
          s += Math.exp((c * z) - a - e);
          z += 1.0;
        }

        return s;
      }

      e = (even ? 1.0 : (MathConstants.INV_SQRT_PI / Math.sqrt(a)));
      c = 0.0;

      while (z <= xx) {
        e *= (a / z);
        c += e;
        z += 1.0;
      }
      return (c * y) + s;
    }

    return s;
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1, final double x2) {
    return ChiSquareCDF.chiSquareCDF(x1, ((int) (Math.round(x2))));
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link ChiSquareCDF#INSTANCE
   * ChiSquareCDF.INSTANCE} for serialization, i.e., when the instance is
   * written with {@link java.io.ObjectOutputStream#writeObject(Object)}.
   *
   * @return the replacement instance (always {@link ChiSquareCDF#INSTANCE
   *         ChiSquareCDF.INSTANCE})
   */
  private final Object writeReplace() {
    return ChiSquareCDF.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link ChiSquareCDF#INSTANCE
   * ChiSquareCDF.INSTANCE} after serialization, i.e., when the instance is
   * read with {@link java.io.ObjectInputStream#readObject()}.
   *
   * @return the replacement instance (always {@link ChiSquareCDF#INSTANCE
   *         ChiSquareCDF.INSTANCE})
   */
  private final Object readResolve() {
    return ChiSquareCDF.INSTANCE;
  }

}
