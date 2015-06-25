package org.logisticPlanning.utils.math.functions.special;

import org.logisticPlanning.utils.math.functions.UnaryFunction;

/**
 * <p>
 * The Bessel function j1.
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
public final class BesselJ1 extends UnaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final BesselJ1 INSTANCE = new BesselJ1();

  /** instantiate */
  private BesselJ1() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1) {
    double ax1, y, z, x1x1;

    if ((ax1 = Math.abs(x1)) < 8.0d) {
      y = (x1 * x1);

      return (x1 * (72362614232.0d + (y * (-7895059235.0d + (y * (242396853.1d + (y * (-2972611.439d + (y * (15704.48260d + (y * (-30.16036606d))))))))))))
          / (144725228442.0d + (y * (2300535178.0d + (y * (18583304.74d + (y * (99447.43394d + (y * (376.9991397d + (y * 1.0d))))))))));
    }

    z = 8.0d / ax1;
    x1x1 = ax1 - 2.356194491d;
    y = z * z;

    y = Math.sqrt(0.636619772 / ax1)
        * ((Math.cos(x1x1) * ((1.0d + (y * (0.183105e-2d + (y * (-0.3516396496e-4d + (y * (0.2457520174e-5d + (y * (-0.240337019e-6d))))))))))) - (z
            * Math.sin(x1x1) * (0.04687499995d + (y * (-0.2002690873e-3d + (y * (0.8449199096e-5d + (y * (-0.88228987e-6d + (y * 0.105787412e-6d))))))))));

    if (x1 < 0.0d) {
      return -y;
    }
    return y;
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link BesselJ1#INSTANCE
   * BesselJ1.INSTANCE} for serialization, i.e., when the instance is
   * written with {@link java.io.ObjectOutputStream#writeObject(Object)}.
   *
   * @return the replacement instance (always {@link BesselJ1#INSTANCE
   *         BesselJ1.INSTANCE})
   */
  private final Object writeReplace() {
    return BesselJ1.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link BesselJ1#INSTANCE
   * BesselJ1.INSTANCE} after serialization, i.e., when the instance is
   * read with {@link java.io.ObjectInputStream#readObject()}.
   *
   * @return the replacement instance (always {@link BesselJ1#INSTANCE
   *         BesselJ1.INSTANCE})
   */
  private final Object readResolve() {
    return BesselJ1.INSTANCE;
  }

}
