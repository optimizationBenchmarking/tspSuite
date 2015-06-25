package org.logisticPlanning.utils.math.functions.arithmetic;

import org.logisticPlanning.utils.math.functions.UnaryFunction;

/**
 * The identity function
 */
public final class Identity extends UnaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final Identity INSTANCE = new Identity();

  /** instantiate */
  private Identity() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final byte compute(final byte x0) {
    return x0;
  }

  /** {@inheritDoc} */
  @Override
  public final short compute(final short x0) {
    return x0;
  }

  /** {@inheritDoc} */
  @Override
  public final int compute(final int x0) {
    return x0;
  }

  /** {@inheritDoc} */
  @Override
  public final long compute(final long x0) {
    return x0;
  }

  /** {@inheritDoc} */
  @Override
  public final float compute(final float x0) {
    return x0;
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x0) {
    return x0;
  }

  /** {@inheritDoc} */
  @Override
  public final Identity invertFor(final int index) {
    return this;
  }

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link Identity#INSTANCE INSTANCE} for
   * serialization, i.e., when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   *
   * @return the replacement instance (always {@link Identity#INSTANCE
   *         INSTANCE})
   */
  private final Object writeReplace() {
    return Identity.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link Identity#INSTANCE INSTANCE} after
   * serialization, i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   *
   * @return the replacement instance (always {@link Identity#INSTANCE
   *         INSTANCE})
   */
  private final Object readResolve() {
    return Identity.INSTANCE;
  }
}
