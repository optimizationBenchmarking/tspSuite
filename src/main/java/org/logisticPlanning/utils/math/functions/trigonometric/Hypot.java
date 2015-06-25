package org.logisticPlanning.utils.math.functions.trigonometric;

import org.logisticPlanning.utils.math.functions.BinaryFunction;

/**
 * The hypot function
 */
public final class Hypot extends BinaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final Hypot INSTANCE = new Hypot();

  /** instantiate */
  private Hypot() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1, final double x2) {
    return Math.hypot(x1, x2);
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link Hypot#INSTANCE Hypot.INSTANCE} for
   * serialization, i.e., when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   *
   * @return the replacement instance (always {@link Hypot#INSTANCE
   *         Hypot.INSTANCE})
   */
  private final Object writeReplace() {
    return Hypot.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link Hypot#INSTANCE Hypot.INSTANCE}
   * after serialization, i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   *
   * @return the replacement instance (always {@link Hypot#INSTANCE
   *         Hypot.INSTANCE})
   */
  private final Object readResolve() {
    return Hypot.INSTANCE;
  }

}
