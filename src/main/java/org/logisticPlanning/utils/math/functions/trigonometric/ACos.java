package org.logisticPlanning.utils.math.functions.trigonometric;

import org.logisticPlanning.utils.math.functions.UnaryFunction;

/**
 * The acos function
 */
public final class ACos extends UnaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final ACos INSTANCE = new ACos();

  /** instantiate */
  private ACos() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1) {
    return Math.acos(x1);
  }

  /** {@inheritDoc} */
  @Override
  public final Cos invertFor(final int index) {
    return Cos.INSTANCE;
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link ACos#INSTANCE ACos.INSTANCE} for
   * serialization, i.e., when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   *
   * @return the replacement instance (always {@link ACos#INSTANCE
   *         ACos.INSTANCE})
   */
  private final Object writeReplace() {
    return ACos.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link ACos#INSTANCE ACos.INSTANCE} after
   * serialization, i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   *
   * @return the replacement instance (always {@link ACos#INSTANCE
   *         ACos.INSTANCE})
   */
  private final Object readResolve() {
    return ACos.INSTANCE;
  }

}
