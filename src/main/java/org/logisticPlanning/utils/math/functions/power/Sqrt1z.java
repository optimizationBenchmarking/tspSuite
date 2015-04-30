package org.logisticPlanning.utils.math.functions.power;

import org.logisticPlanning.utils.math.functions.UnaryFunction;

/**
 * The sqrt(1-x*x) function
 */
public final class Sqrt1z extends UnaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final Sqrt1z INSTANCE = new Sqrt1z();

  /** instantiate */
  private Sqrt1z() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1) {
    return Math.sqrt(1d - (x1 * x1));
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link Sqrt1z#INSTANCE Sqrt1z.INSTANCE}
   * for serialization, i.e., when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   * 
   * @return the replacement instance (always {@link Sqrt1z#INSTANCE
   *         Sqrt1z.INSTANCE})
   */
  private final Object writeReplace() {
    return Sqrt1z.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link Sqrt1z#INSTANCE Sqrt1z.INSTANCE}
   * after serialization, i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   * 
   * @return the replacement instance (always {@link Sqrt1z#INSTANCE
   *         Sqrt1z.INSTANCE})
   */
  private final Object readResolve() {
    return Sqrt1z.INSTANCE;
  }

}
