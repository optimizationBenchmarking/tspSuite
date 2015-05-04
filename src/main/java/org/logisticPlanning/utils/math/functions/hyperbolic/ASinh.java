package org.logisticPlanning.utils.math.functions.hyperbolic;

import org.logisticPlanning.utils.math.functions.UnaryFunction;

/**
 * The asinh function
 */
public final class ASinh extends UnaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final ASinh INSTANCE = new ASinh();

  /** instantiate */
  private ASinh() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1) {
    final boolean negative;
    final double absAsinh, a, a2;

    if (x1 < 0d) {
      negative = true;
      a = (-x1);
    } else {
      negative = false;
      a = x1;
    }

    a2 = (a * a);
    if (a > 0.167d) {
      absAsinh = Math.log(Math.sqrt(a2 + 1d) + a);
    } else {
      if (a > 0.097d) {
        absAsinh = a
            * (1d - ((a2 * ((1d / 3.0d) - ((a2
                * ((1d / 5.0d) - ((a2
                    * ((1d / 7.0d) - ((a2
                        * ((1d / 9.0d) - ((a2
                            * ((1.0d / 11.0d) - ((a2
                                * ((1.0d / 13.0d) - ((a2
                                    * ((1.0d / 15.0d) - ((a2
                                        * (1.0d / 17.0d) * 15.0d) / 16.0d)) * 13.0d) / 14.0d)) * 11.0d) / 12.0d)) * 9.0d) / 10.0d)) * 7.0d) / 8.0d)) * 5.0d) / 6.0d)) * 3.0d) / 4.0d))) / 2.0d));
      } else {
        if (a > 0.036d) {
          absAsinh = a
              * (1d - ((a2 * ((1d / 3.0d) - ((a2
                  * ((1d / 5.0d) - ((a2
                      * ((1d / 7.0d) - ((a2
                          * ((1d / 9.0d) - ((a2
                              * ((1.0d / 11.0d) - ((a2 * (1.0d / 13.0d) * 11.0d) / 12.0d)) * 9.0d) / 10.0d)) * 7.0d) / 8.0d)) * 5.0d) / 6.0d)) * 3.0d) / 4.0d))) / 2.0d));
        } else {
          if (a > 0.0036d) {
            absAsinh = a
                * (1d - ((a2 * ((1d / 3.0d) - ((a2
                    * ((1d / 5.0d) - ((a2
                        * ((1d / 7.0d) - ((a2 * (1d / 9.0d) * 7.0d) / 8.0d)) * 5.0d) / 6.0d)) * 3.0d) / 4.0d))) / 2.0d));
          } else {
            absAsinh = a
                * (1d - ((a2 * ((1d / 3.0d) - ((a2 * (1d / 5.0d) * 3.0d) / 4.0d))) / 2.0d));
          }
        }
      }
    }

    return (negative ? (-absAsinh) : (absAsinh));
  }

  /** {@inheritDoc} */
  @Override
  public final Sinh invertFor(final int index) {
    return Sinh.INSTANCE;
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link ASinh#INSTANCE ASinh.INSTANCE} for
   * serialization, i.e., when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   *
   * @return the replacement instance (always {@link ASinh#INSTANCE
   *         ASinh.INSTANCE})
   */
  private final Object writeReplace() {
    return ASinh.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link ASinh#INSTANCE ASinh.INSTANCE}
   * after serialization, i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   *
   * @return the replacement instance (always {@link ASinh#INSTANCE
   *         ASinh.INSTANCE})
   */
  private final Object readResolve() {
    return ASinh.INSTANCE;
  }

}
