package org.logisticPlanning.utils.math.functions.hyperbolic;

import org.logisticPlanning.utils.math.functions.UnaryFunction;

/**
 * The tanh function
 */
public final class Tanh extends UnaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final Tanh INSTANCE = new Tanh();

  /** instantiate */
  private Tanh() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1) {
    return Math.tanh(x1);
  }

  /** {@inheritDoc} */
  @Override
  public final ATanh invertFor(final int index) {
    return ATanh.INSTANCE;
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link Tanh#INSTANCE Tanh.INSTANCE} for
   * serialization, i.e., when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   *
   * @return the replacement instance (always {@link Tanh#INSTANCE
   *         Tanh.INSTANCE})
   */
  private final Object writeReplace() {
    return Tanh.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link Tanh#INSTANCE Tanh.INSTANCE} after
   * serialization, i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   *
   * @return the replacement instance (always {@link Tanh#INSTANCE
   *         Tanh.INSTANCE})
   */
  private final Object readResolve() {
    return Tanh.INSTANCE;
  }

}
