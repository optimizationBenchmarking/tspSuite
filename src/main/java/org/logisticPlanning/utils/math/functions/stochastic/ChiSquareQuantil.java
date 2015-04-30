package org.logisticPlanning.utils.math.functions.stochastic;

import org.logisticPlanning.utils.math.functions.BinaryFunction;

/**
 * <p>
 * Compute the chi square quantil.
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
public final class ChiSquareQuantil extends BinaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final ChiSquareQuantil INSTANCE = new ChiSquareQuantil();

  /** instantiate */
  private ChiSquareQuantil() {
    super();
  }

  /**
   * Calculates the chi-square-quantil for the probability d with dof
   * degrees of freedom.
   * 
   * @param d
   *          the probability (often called alpha)
   * @param dof
   *          the degrees of freedom
   * @return the quantile chi^2 (d, dof) TODO: CHECK
   */
  public static final double chiSquareQuantil(final double d, final int dof) {
    double max, min, x;

    if (d <= 0.0) {
      return Double.POSITIVE_INFINITY;
    }
    if (d >= 1.0) {
      return 0.0;
    }

    max = 1E4d;
    min = 0.0;

    for (;;) {
      x = 0.5 * (max + min);

      if (ChiSquareCDF.chiSquareCDF(x, dof) <= d) {
        if (max == x) {
          return x;
        }
        max = x;
      } else {
        if (min == x) {
          return x;
        }
        min = x;
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1, final double x2) {
    return ChiSquareQuantil.chiSquareQuantil(x1, ((int) (Math.round(x2))));
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link ChiSquareQuantil#INSTANCE
   * ChiSquareQuantil.INSTANCE} for serialization, i.e., when the instance
   * is written with {@link java.io.ObjectOutputStream#writeObject(Object)}
   * .
   * 
   * @return the replacement instance (always
   *         {@link ChiSquareQuantil#INSTANCE ChiSquareQuantil.INSTANCE})
   */
  private final Object writeReplace() {
    return ChiSquareQuantil.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link ChiSquareQuantil#INSTANCE
   * ChiSquareQuantil.INSTANCE} after serialization, i.e., when the
   * instance is read with {@link java.io.ObjectInputStream#readObject()}.
   * 
   * @return the replacement instance (always
   *         {@link ChiSquareQuantil#INSTANCE ChiSquareQuantil.INSTANCE})
   */
  private final Object readResolve() {
    return ChiSquareQuantil.INSTANCE;
  }

}
