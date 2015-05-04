package org.logisticPlanning.utils.math.functions.stochastic;

import org.logisticPlanning.utils.math.functions.UnaryFunction;

/**
 * <p>
 * The inverted CDF of the normal distribution.
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
public final class NormalInvCDF extends UnaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final NormalInvCDF INSTANCE = new NormalInvCDF();

  /**
   * A helper value for the computation of the Inverse Normal Cummulative
   * Distribution.
   */
  private static final double ICDF_P_LOW = 0.02425D;

  /**
   * A helper value for the computation of the Inverse Normal Cummulative
   * Distribution.
   */
  private static final double ICDF_P_HIGH = 1.0D - NormalInvCDF.ICDF_P_LOW;

  /**
   * Coefficients A in rational approximations of the Inverse Normal
   * Cummulative Distribution.
   */
  private static final double ICDF_A[] = { -3.969683028665376e+01,
      2.209460984245205e+02, -2.759285104469687e+02,
      1.383577518672690e+02, -3.066479806614716e+01, 2.506628277459239e+00 };

  /**
   * Coefficients B in rational approximations of the Inverse Normal
   * Cummulative Distribution.
   */
  private static final double ICDF_B[] = { -5.447609879822406e+01,
      1.615858368580409e+02, -1.556989798598866e+02,
      6.680131188771972e+01, -1.328068155288572e+01 };

  /**
   * Coefficients C in rational approximations of the Inverse Normal
   * Cummulative Distribution.
   */
  private static final double ICDF_C[] = { -7.784894002430293e-03,
      -3.223964580411365e-01, -2.400758277161838e+00,
      -2.549732539343734e+00, 4.374664141464968e+00, 2.938163982698783e+00 };

  /**
   * Coefficients D in rational approximations of the Inverse Normal
   * Cummulative Distribution.
   */
  private static final double ICDF_D[] = { 7.784695709041462e-03,
      3.224671290700398e-01, 2.445134137142996e+00, 3.754408661907416e+00 };

  /** instantiate */
  private NormalInvCDF() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1) {

    double z, r;

    z = 0;

    if (x1 <= 0.0d) {
      return Double.NEGATIVE_INFINITY;
    }
    if (x1 >= 1.0d) {
      return Double.POSITIVE_INFINITY;
    }
    if (x1 != x1) {
      return Double.NaN;
    }
    if (x1 < NormalInvCDF.ICDF_P_LOW) {
      z = Math.sqrt(-2.0d * Math.log(x1));
      z = ((((((((((NormalInvCDF.ICDF_C[0] * z) + NormalInvCDF.ICDF_C[1]) * z) + NormalInvCDF.ICDF_C[2]) * z) + NormalInvCDF.ICDF_C[3]) * z) + NormalInvCDF.ICDF_C[4]) * z) + NormalInvCDF.ICDF_C[5])
          / ((((((((NormalInvCDF.ICDF_D[0] * z) + NormalInvCDF.ICDF_D[1]) * z) + NormalInvCDF.ICDF_D[2]) * z) + NormalInvCDF.ICDF_D[3]) * z) + 1.0);
    } else
      if (NormalInvCDF.ICDF_P_HIGH < x1) {
        z = Math.sqrt(-2.0 * Math.log(1 - x1));
        z = -((((((((((NormalInvCDF.ICDF_C[0] * z) + NormalInvCDF.ICDF_C[1]) * z) + NormalInvCDF.ICDF_C[2]) * z) + NormalInvCDF.ICDF_C[3]) * z) + NormalInvCDF.ICDF_C[4]) * z) + NormalInvCDF.ICDF_C[5])
            / ((((((((NormalInvCDF.ICDF_D[0] * z) + NormalInvCDF.ICDF_D[1]) * z) + NormalInvCDF.ICDF_D[2]) * z) + NormalInvCDF.ICDF_D[3]) * z) + 1.0d);
      } else {
        z = (x1 - 0.5d);
        r = (z * z);
        z = (((((((((((NormalInvCDF.ICDF_A[0] * r) + NormalInvCDF.ICDF_A[1]) * r) + NormalInvCDF.ICDF_A[2]) * r) + NormalInvCDF.ICDF_A[3]) * r) + NormalInvCDF.ICDF_A[4]) * r) + NormalInvCDF.ICDF_A[5]) * z)
            / ((((((((((NormalInvCDF.ICDF_B[0] * r) + NormalInvCDF.ICDF_B[1]) * r) + NormalInvCDF.ICDF_B[2]) * r) + NormalInvCDF.ICDF_B[3]) * r) + NormalInvCDF.ICDF_B[4]) * r) + 1.0d);
      }

    /*
     * TODO if((x1 > 0) && (x1 < 1)) { r = 0.5D erfc(-z/Math.sqrt(2.0D)) -
     * x1; x1 = z Math.sqrt(2.0DMath.PI) Math.exp((zz)/2.0D); z = z -
     * x1/(1.0D + zd/2.0D); }
     */
    return z;
  }

  /** {@inheritDoc} */
  @Override
  public final NormalCDF invertFor(final int index) {
    return NormalCDF.INSTANCE;
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link NormalInvCDF#INSTANCE
   * NormalInvCDF.INSTANCE} for serialization, i.e., when the instance is
   * written with {@link java.io.ObjectOutputStream#writeObject(Object)}.
   *
   * @return the replacement instance (always {@link NormalInvCDF#INSTANCE
   *         NormalInvCDF.INSTANCE})
   */
  private final Object writeReplace() {
    return NormalInvCDF.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link NormalInvCDF#INSTANCE
   * NormalInvCDF.INSTANCE} after serialization, i.e., when the instance is
   * read with {@link java.io.ObjectInputStream#readObject()}.
   *
   * @return the replacement instance (always {@link NormalInvCDF#INSTANCE
   *         NormalInvCDF.INSTANCE})
   */
  private final Object readResolve() {
    return NormalInvCDF.INSTANCE;
  }

}
