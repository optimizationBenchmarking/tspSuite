package org.logisticPlanning.utils.math.functions.binary;

import org.logisticPlanning.utils.math.functions.UnaryFunction;

/**
 * The binary not.
 */
public final class BNot extends UnaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final BNot INSTANCE = new BNot();

  /** the forbidden constructor */
  private BNot() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final byte compute(final byte x1) {
    return ((byte) (~x1));
  }

  /** {@inheritDoc} */
  @Override
  public final short compute(final short x1) {
    return ((short) (~x1));
  }

  /** {@inheritDoc} */
  @Override
  public final int compute(final int x1) {
    return (~x1);
  }

  /** {@inheritDoc} */
  @Override
  public final long compute(final long x1) {
    return (~x1);
  }

  /** {@inheritDoc} */
  @Override
  public final float compute(final float x1) {
    return this.compute(((long) x1));
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1) {
    return this.compute(((long) x1));
  }

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link BNot#INSTANCE INSTANCE} for
   * serialization, i.e., when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   * 
   * @return the replacement instance (always {@link BNot#INSTANCE
   *         INSTANCE})
   */
  private final Object writeReplace() {
    return BNot.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link BNot#INSTANCE INSTANCE} after
   * serialization, i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   * 
   * @return the replacement instance (always {@link BNot#INSTANCE
   *         INSTANCE})
   */
  private final Object readResolve() {
    return BNot.INSTANCE;
  }
}
