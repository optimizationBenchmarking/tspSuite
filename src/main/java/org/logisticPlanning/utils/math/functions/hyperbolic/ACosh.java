package org.logisticPlanning.utils.math.functions.hyperbolic;

import org.logisticPlanning.utils.math.functions.UnaryFunction;

/**
 * The acosh function
 */
public final class ACosh extends UnaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final ACosh INSTANCE = new ACosh();

  /** instantiate */
  private ACosh() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1) {
    return Math.log(x1 + Math.sqrt((x1 * x1) - 1d));
  }

  /** {@inheritDoc} */
  @Override
  public final Cosh invertFor(final int index) {
    return Cosh.INSTANCE;
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link ACosh#INSTANCE ACosh.INSTANCE} for
   * serialization, i.e., when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   *
   * @return the replacement instance (always {@link ACosh#INSTANCE
   *         ACosh.INSTANCE})
   */
  private final Object writeReplace() {
    return ACosh.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link ACosh#INSTANCE ACosh.INSTANCE}
   * after serialization, i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   *
   * @return the replacement instance (always {@link ACosh#INSTANCE
   *         ACosh.INSTANCE})
   */
  private final Object readResolve() {
    return ACosh.INSTANCE;
  }

}
