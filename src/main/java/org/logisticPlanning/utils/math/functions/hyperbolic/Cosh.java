package org.logisticPlanning.utils.math.functions.hyperbolic;

import org.logisticPlanning.utils.math.functions.UnaryFunction;

/**
 * The cosh function
 */
public final class Cosh extends UnaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final Cosh INSTANCE = new Cosh();

  /** instantiate */
  private Cosh() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1) {
    return Math.cosh(x1);
  }

  /** {@inheritDoc} */
  @Override
  public final ACosh invertFor(final int index) {
    return ACosh.INSTANCE;
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link Cosh#INSTANCE Cosh.INSTANCE} for
   * serialization, i.e., when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   * 
   * @return the replacement instance (always {@link Cosh#INSTANCE
   *         Cosh.INSTANCE})
   */
  private final Object writeReplace() {
    return Cosh.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link Cosh#INSTANCE Cosh.INSTANCE} after
   * serialization, i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   * 
   * @return the replacement instance (always {@link Cosh#INSTANCE
   *         Cosh.INSTANCE})
   */
  private final Object readResolve() {
    return Cosh.INSTANCE;
  }

}
