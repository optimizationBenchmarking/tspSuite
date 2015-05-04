package org.logisticPlanning.utils.math.functions.power;

import org.logisticPlanning.utils.math.functions.UnaryFunction;

/**
 * The sqrt function
 */
public final class Sqrt extends UnaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final Sqrt INSTANCE = new Sqrt();

  /** instantiate */
  private Sqrt() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1) {
    return Math.sqrt(x1);
  }

  /** {@inheritDoc} */
  @Override
  public final Sqr invertFor(final int index) {
    return Sqr.INSTANCE;
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link Sqrt#INSTANCE Sqrt.INSTANCE} for
   * serialization, i.e., when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   *
   * @return the replacement instance (always {@link Sqrt#INSTANCE
   *         Sqrt.INSTANCE})
   */
  private final Object writeReplace() {
    return Sqrt.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link Sqrt#INSTANCE Sqrt.INSTANCE} after
   * serialization, i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   *
   * @return the replacement instance (always {@link Sqrt#INSTANCE
   *         Sqrt.INSTANCE})
   */
  private final Object readResolve() {
    return Sqrt.INSTANCE;
  }

}
