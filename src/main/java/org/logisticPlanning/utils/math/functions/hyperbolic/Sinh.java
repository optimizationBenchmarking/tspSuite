package org.logisticPlanning.utils.math.functions.hyperbolic;

import org.logisticPlanning.utils.math.functions.UnaryFunction;

/**
 * The sinh function
 */
public final class Sinh extends UnaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final Sinh INSTANCE = new Sinh();

  /** instantiate */
  private Sinh() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1) {
    return Math.sinh(x1);
  }

  /** {@inheritDoc} */
  @Override
  public final ASinh invertFor(final int index) {
    return ASinh.INSTANCE;
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link Sinh#INSTANCE Sinh.INSTANCE} for
   * serialization, i.e., when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   *
   * @return the replacement instance (always {@link Sinh#INSTANCE
   *         Sinh.INSTANCE})
   */
  private final Object writeReplace() {
    return Sinh.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link Sinh#INSTANCE Sinh.INSTANCE} after
   * serialization, i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   *
   * @return the replacement instance (always {@link Sinh#INSTANCE
   *         Sinh.INSTANCE})
   */
  private final Object readResolve() {
    return Sinh.INSTANCE;
  }

}
