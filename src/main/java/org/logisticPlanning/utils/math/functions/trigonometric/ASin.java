package org.logisticPlanning.utils.math.functions.trigonometric;

import org.logisticPlanning.utils.math.functions.UnaryFunction;

/**
 * The asin function
 */
public final class ASin extends UnaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final ASin INSTANCE = new ASin();

  /** instantiate */
  private ASin() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1) {
    return Math.asin(x1);
  }

  /** {@inheritDoc} */
  @Override
  public final Sin invertFor(final int index) {
    return Sin.INSTANCE;
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link ASin#INSTANCE ASin.INSTANCE} for
   * serialization, i.e., when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   *
   * @return the replacement instance (always {@link ASin#INSTANCE
   *         ASin.INSTANCE})
   */
  private final Object writeReplace() {
    return ASin.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link ASin#INSTANCE ASin.INSTANCE} after
   * serialization, i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   *
   * @return the replacement instance (always {@link ASin#INSTANCE
   *         ASin.INSTANCE})
   */
  private final Object readResolve() {
    return ASin.INSTANCE;
  }

}
