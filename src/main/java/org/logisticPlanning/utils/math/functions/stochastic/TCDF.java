package org.logisticPlanning.utils.math.functions.stochastic;

import org.logisticPlanning.utils.math.functions.BinaryFunction;
import org.logisticPlanning.utils.math.functions.special.BetaRegularized;

/**
 * <p>
 * The CDF of the t-distribution, as defined in&nbsp;[<a
 * href="#cite_A2013CMTACML" style="font-weight:bold">1</a>], with the
 * difference that the approximate degrees of freedom are rounded after
 * instead of using the real-valued approximations directly, them as
 * suggested in&nbsp;[<a href="#cite_SE20061STITIAAC"
 * style="font-weight:bold">2</a>].
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
 * <li><div><span id="cite_SE20061STITIAAC" />David C. Stone and&nbsp;Jon
 * Ellis: <span style="font-style:italic;font-family:cursive;">&ldquo;Stats
 * Tutorial &#8211; Intro to Instrumental Analysis and
 * Calibration,&rdquo;</span> (Website), August&nbsp;23, 2006, Toronto, ON,
 * Canada: University of Toronto (UofT), Department of Chemistry.
 * <div>link: [<a href=
 * "http://www.chem.utoronto.ca/coursenotes/analsci/StatsTutorial/index.html"
 * >1</a>]</div></div></li>
 * </ol>
 */
public final class TCDF extends BinaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final TCDF INSTANCE = new TCDF();

  /** instantiate */
  private TCDF() {
    super();
  }

  /**
   * Returns the CDF of the t-distribution
   * 
   * @param dof
   *          degrees of freedom
   * @param x
   *          x-value
   * @return The sum of the first k terms of the t distribution.
   */

  public static final double tCDF(final long dof, final double x) {
    final double t;

    if (x <= Double.NEGATIVE_INFINITY) {
      return 0d;
    }
    if (x >= Double.POSITIVE_INFINITY) {
      return 1d;
    }
    if (x == 0d) {
      return 0.5d;
    }

    t = BetaRegularized.INSTANCE.compute(//
        (dof / (dof + (x * x))), (0.5d * dof), 0.5d);

    if (x < 0d) {
      return (0.5d * t);
    }

    return (1d - (0.5d * t));
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1, final double x2) {
    return TCDF.tCDF(Math.round(x1), x2);
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link TCDF#INSTANCE TCDF.INSTANCE} for
   * serialization, i.e., when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   * 
   * @return the replacement instance (always {@link TCDF#INSTANCE
   *         TCDF.INSTANCE})
   */
  private final Object writeReplace() {
    return TCDF.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link TCDF#INSTANCE TCDF.INSTANCE} after
   * serialization, i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   * 
   * @return the replacement instance (always {@link TCDF#INSTANCE
   *         TCDF.INSTANCE})
   */
  private final Object readResolve() {
    return TCDF.INSTANCE;
  }

}
