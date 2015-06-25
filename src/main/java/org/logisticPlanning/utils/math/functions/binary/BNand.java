package org.logisticPlanning.utils.math.functions.binary;

import org.logisticPlanning.utils.math.functions.BinaryFunction;

/**
 * The binary nand.
 */
public final class BNand extends BinaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final BNand INSTANCE = new BNand();

  /** the forbidden constructor */
  private BNand() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final byte compute(final byte x1, final byte x2) {
    return ((byte) (~(x1 & x2)));
  }

  /** {@inheritDoc} */
  @Override
  public final short compute(final short x1, final short x2) {
    return ((short) (~(x1 & x2)));
  }

  /** {@inheritDoc} */
  @Override
  public final int compute(final int x1, final int x2) {
    return (~(x1 & x2));
  }

  /** {@inheritDoc} */
  @Override
  public final long compute(final long x1, final long x2) {
    return (~(x1 & x2));
  }

  /** {@inheritDoc} */
  @Override
  public final float compute(final float x1, final float x2) {
    return this.compute(((long) x1), ((long) x2));
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1, final double x2) {
    return this.compute(((long) x1), ((long) x2));
  }

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link BNand#INSTANCE INSTANCE} for
   * serialization, i.e., when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   *
   * @return the replacement instance (always {@link BNand#INSTANCE
   *         INSTANCE})
   */
  private final Object writeReplace() {
    return BNand.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link BNand#INSTANCE INSTANCE} after
   * serialization, i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   *
   * @return the replacement instance (always {@link BNand#INSTANCE
   *         INSTANCE})
   */
  private final Object readResolve() {
    return BNand.INSTANCE;
  }
}
