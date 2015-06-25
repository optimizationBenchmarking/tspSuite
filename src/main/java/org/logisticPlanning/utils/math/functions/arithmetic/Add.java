package org.logisticPlanning.utils.math.functions.arithmetic;

import org.logisticPlanning.utils.math.functions.BinaryFunction;

/**
 * The {@code "+"} function
 */
public final class Add extends BinaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final Add INSTANCE = new Add();

  /** instantiate */
  private Add() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final byte compute(final byte x0, final byte x1) {
    return ((byte) (x0 + x1));
  }

  /** {@inheritDoc} */
  @Override
  public final short compute(final short x0, final short x1) {
    return ((short) (x0 + x1));
  }

  /** {@inheritDoc} */
  @Override
  public final int compute(final int x0, final int x1) {
    return (x0 + x1);
  }

  /** {@inheritDoc} */
  @Override
  public final long compute(final long x0, final long x1) {
    return (x0 + x1);
  }

  /** {@inheritDoc} */
  @Override
  public final float compute(final float x0, final float x1) {
    return (x0 + x1);
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x0, final double x1) {
    return (x0 + x1);
  }

  /** {@inheritDoc} */
  @Override
  public final BinaryFunction invertFor(final int index) {
    if (index == 1) {
      return SubBA.INSTANCE;
    }
    return Sub.INSTANCE;
  }

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link Add#INSTANCE INSTANCE} for
   * serialization, i.e., when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   *
   * @return the replacement instance (always {@link Add#INSTANCE INSTANCE}
   *         )
   */
  private final Object writeReplace() {
    return Add.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link Add#INSTANCE INSTANCE} after
   * serialization, i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   *
   * @return the replacement instance (always {@link Add#INSTANCE INSTANCE}
   *         )
   */
  private final Object readResolve() {
    return Add.INSTANCE;
  }
}
