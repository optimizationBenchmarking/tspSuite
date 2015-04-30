package org.logisticPlanning.utils.math.functions.special;

import org.logisticPlanning.utils.math.functions.UnaryFunction;

/**
 * <p>
 * Compute the Bessel function of the second kind, of order 1 of the
 * argument.
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
public final class BesselY1 extends UnaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final BesselY1 INSTANCE = new BesselY1();

  /** instantiate */
  private BesselY1() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1) {
    double y, z, xx;

    if (x1 < 8.0d) {
      y = (x1 * x1);
      return ((x1 * (-0.4900604943e13d + (y * (0.1275274390e13d + (y * (-0.5153438139e11d + (y * (0.7349264551e9d + (y * (-0.4237922726e7d + (y * 0.8511937935e4d))))))))))) / (0.2499580570e14d + (y * (0.4244419664e12d + (y * (0.3733650367e10d + (y * (0.2245904002e8d + (y * (0.1020426050e6d + (y * (0.3549632885e3d + y))))))))))))
          + (0.636619772d * ((BesselJ1.INSTANCE.compute(x1) * Math.log(x1)) - (1.0 / x1)));
    }

    z = 8.0d / x1;
    y = z * z;
    xx = x1 - 2.356194491d;

    return Math.sqrt(0.636619772d / x1)
        * ((Math.sin(xx) * (1.0d + (y * (0.183105e-2d + (y * (-0.3516396496e-4d + (y * (0.2457520174e-5d + (y * (-0.240337019e-6d)))))))))) + (z
            * Math.cos(xx) * (0.04687499995d + (y * (-0.2002690873e-3d + (y * (0.8449199096e-5d + (y * (-0.88228987e-6d + (y * 0.105787412e-6d))))))))));
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link BesselY1#INSTANCE
   * BesselY1.INSTANCE} for serialization, i.e., when the instance is
   * written with {@link java.io.ObjectOutputStream#writeObject(Object)}.
   * 
   * @return the replacement instance (always {@link BesselY1#INSTANCE
   *         BesselY1.INSTANCE})
   */
  private final Object writeReplace() {
    return BesselY1.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link BesselY1#INSTANCE
   * BesselY1.INSTANCE} after serialization, i.e., when the instance is
   * read with {@link java.io.ObjectInputStream#readObject()}.
   * 
   * @return the replacement instance (always {@link BesselY1#INSTANCE
   *         BesselY1.INSTANCE})
   */
  private final Object readResolve() {
    return BesselY1.INSTANCE;
  }

}
