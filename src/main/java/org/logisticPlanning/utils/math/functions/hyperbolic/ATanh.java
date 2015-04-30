package org.logisticPlanning.utils.math.functions.hyperbolic;

import org.logisticPlanning.utils.math.functions.UnaryFunction;

/**
 * The atanh function
 */
public final class ATanh extends UnaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final ATanh INSTANCE = new ATanh();

  /** instantiate */
  private ATanh() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1) {
    final boolean negative;
    final double absAtanh, a, a2;

    if (x1 < 0d) {
      negative = true;
      a = (-x1);
    } else {
      negative = false;
      a = x1;
    }

    if (a > 0.15d) {
      absAtanh = 0.5 * Math.log((1d + a) / (1d - a));
    } else {
      a2 = a * a;
      if (a > 0.087d) {
        absAtanh = a
            * (1d + (a2 * ((1.0d / 3.0d) + (a2 * ((1.0d / 5.0d) + (a2 * ((1.0d / 7.0d) + (a2 * ((1.0d / 9.0d) + (a2 * ((1.0d / 11.0d) + (a2 * ((1.0d / 13.0d) + (a2 * ((1.0d / 15.0d) + (a2 * (1.0d / 17.0d)))))))))))))))));
      } else {
        if (a > 0.031d) {
          absAtanh = a
              * (1d + (a2 * ((1.0d / 3.0d) + (a2 * ((1.0d / 5.0d) + (a2 * ((1.0d / 7.0d) + (a2 * ((1.0d / 9.0d) + (a2 * ((1.0d / 11.0d) + (a2 * (1.0d / 13.0d)))))))))))));
        } else {
          if (a > 0.003d) {
            absAtanh = a
                * (1d + (a2 * ((1.0d / 3.0d) + (a2 * ((1.0d / 5.0d) + (a2 * ((1.0d / 7.0d) + (a2 * (1.0d / 9.0d)))))))));
          } else {
            absAtanh = a
                * (1d + (a2 * ((1.0d / 3.0d) + (a2 * (1.0d / 5.0d)))));
          }
        }
      }
    }

    return (negative ? (-absAtanh) : absAtanh);
  }

  /** {@inheritDoc} */
  @Override
  public final Tanh invertFor(final int index) {
    return Tanh.INSTANCE;
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link ATanh#INSTANCE ATanh.INSTANCE} for
   * serialization, i.e., when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   * 
   * @return the replacement instance (always {@link ATanh#INSTANCE
   *         ATanh.INSTANCE})
   */
  private final Object writeReplace() {
    return ATanh.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link ATanh#INSTANCE ATanh.INSTANCE}
   * after serialization, i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   * 
   * @return the replacement instance (always {@link ATanh#INSTANCE
   *         ATanh.INSTANCE})
   */
  private final Object readResolve() {
    return ATanh.INSTANCE;
  }

}
