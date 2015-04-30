package org.logisticPlanning.utils.math.functions.trigonometric;

import org.logisticPlanning.utils.math.functions.UnaryFunction;

/**
 * The sin function
 */
public final class Sin extends UnaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final Sin INSTANCE = new Sin();

  /** instantiate */
  private Sin() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1) {
    return Math.sin(x1);
  }

  /** {@inheritDoc} */
  @Override
  public final ASin invertFor(final int index) {
    return ASin.INSTANCE;
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link Sin#INSTANCE Sin.INSTANCE} for
   * serialization, i.e., when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   * 
   * @return the replacement instance (always {@link Sin#INSTANCE
   *         Sin.INSTANCE})
   */
  private final Object writeReplace() {
    return Sin.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link Sin#INSTANCE Sin.INSTANCE} after
   * serialization, i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   * 
   * @return the replacement instance (always {@link Sin#INSTANCE
   *         Sin.INSTANCE})
   */
  private final Object readResolve() {
    return Sin.INSTANCE;
  }

}
