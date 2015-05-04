package org.logisticPlanning.utils.math.functions.special;

import org.logisticPlanning.utils.math.functions.UnaryFunction;

/**
 * <p>
 * The Bessel function j0.
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
public final class BesselJ0 extends UnaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final BesselJ0 INSTANCE = new BesselJ0();

  /** instantiate */
  private BesselJ0() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1) {
    double ax1, y, z, x1x1;

    if ((ax1 = Math.abs(x1)) < 8.0d) {
      y = (x1 * x1);

      return (57568490574.0d + (y * (-13362590354.0d + (y * (651619640.7d + (y * (-11214424.18 + (y * (77392.33017 + (y * (-184.9052456)))))))))))
          / (57568490411.0d + (y * (1029532985.0d + (y * (9494680.718d + (y * (59272.64853d + (y * (267.8532712d + (y * 1.0))))))))));
    }

    z = 8.0d / ax1;
    y = z * z;
    x1x1 = ax1 - 0.785398164d;

    return Math.sqrt(0.636619772d / ax1)
        * ((Math.cos(x1x1) * (1.0d + (y * (-0.1098628627e-2d + (y * (0.2734510407e-4d + (y * (-0.2073370639e-5d + (y * 0.2093887211e-6d))))))))) - (z
            * Math.sin(x1x1) * (-0.1562499995e-1d + (y * (0.1430488765e-3d + (y * (-0.6911147651e-5d + (y * (0.7621095161e-6d - (y * 0.934935152e-7d))))))))));
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link BesselJ0#INSTANCE
   * BesselJ0.INSTANCE} for serialization, i.e., when the instance is
   * written with {@link java.io.ObjectOutputStream#writeObject(Object)}.
   *
   * @return the replacement instance (always {@link BesselJ0#INSTANCE
   *         BesselJ0.INSTANCE})
   */
  private final Object writeReplace() {
    return BesselJ0.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link BesselJ0#INSTANCE
   * BesselJ0.INSTANCE} after serialization, i.e., when the instance is
   * read with {@link java.io.ObjectInputStream#readObject()}.
   *
   * @return the replacement instance (always {@link BesselJ0#INSTANCE
   *         BesselJ0.INSTANCE})
   */
  private final Object readResolve() {
    return BesselJ0.INSTANCE;
  }

}
