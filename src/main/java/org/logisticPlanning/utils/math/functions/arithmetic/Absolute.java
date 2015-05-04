package org.logisticPlanning.utils.math.functions.arithmetic;

import org.logisticPlanning.utils.math.functions.UnaryFunction;

/**
 * The {@code abs} function returns {@code |x|}.
 */
public final class Absolute extends UnaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final Absolute INSTANCE = new Absolute();

  /** instantiate */
  private Absolute() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final byte compute(final byte x0) {
    return ((byte) (Math.abs(x0)));
  }

  /** {@inheritDoc} */
  @Override
  public final short compute(final short x0) {
    return ((short) (Math.abs(x0)));
  }

  /** {@inheritDoc} */
  @Override
  public final int compute(final int x0) {
    return (Math.abs(x0));
  }

  /** {@inheritDoc} */
  @Override
  public final long compute(final long x0) {
    return (Math.abs(x0));
  }

  /** {@inheritDoc} */
  @Override
  public final float compute(final float x0) {
    return (Math.abs(x0));
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x0) {
    return (Math.abs(x0));
  }

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link Absolute#INSTANCE INSTANCE} for
   * serialization, i.e., when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   *
   * @return the replacement instance (always {@link Absolute#INSTANCE
   *         INSTANCE})
   */
  private final Object writeReplace() {
    return Absolute.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link Absolute#INSTANCE INSTANCE} after
   * serialization, i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   *
   * @return the replacement instance (always {@link Absolute#INSTANCE
   *         INSTANCE})
   */
  private final Object readResolve() {
    return Absolute.INSTANCE;
  }
}
