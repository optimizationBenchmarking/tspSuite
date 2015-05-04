package org.logisticPlanning.utils.math.functions.numeric;

import org.logisticPlanning.utils.math.functions.BinaryFunction;

/**
 * Safely increments its parameter.
 */
public final class SafeInc extends BinaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final SafeInc INSTANCE = new SafeInc();

  /** the forbidden constructor */
  private SafeInc() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final byte compute(final byte x1, final byte x2) {
    final int res;

    res = (x1 + x2);
    return ((res > (java.lang.Byte.MAX_VALUE)) ? (java.lang.Byte.MAX_VALUE)
        : ((byte) res));
  }

  /** {@inheritDoc} */
  @Override
  public final short compute(final short x1, final short x2) {
    final int res;

    res = (x1 + x2);
    return ((res > (java.lang.Short.MAX_VALUE)) ? (java.lang.Short.MAX_VALUE)
        : ((short) res));
  }

  /** {@inheritDoc} */
  @Override
  public final int compute(final int x1, final int x2) {
    final int res;

    res = (x1 + x2);
    if (res < x1) {
      return (java.lang.Integer.MAX_VALUE);
    }
    return res;
  }

  /** {@inheritDoc} */
  @Override
  public final long compute(final long x1, final long x2) {
    final long res;

    res = (x1 + x2);
    if (res < x1) {
      return (java.lang.Long.MAX_VALUE);
    }
    return res;
  }

  /** {@inheritDoc} */
  @Override
  public final float compute(final float x1, final float x2) {
    final float res;

    res = (x1 + x2);
    if (res < x1) {
      return (java.lang.Float.MAX_VALUE);
    }
    return res;
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1, final double x2) {
    final double res;

    res = (x1 + x2);
    if (res < x1) {
      return (java.lang.Double.MAX_VALUE);
    }
    return res;
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link SafeInc#INSTANCE INSTANCE} for
   * serialization, i.e., when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   *
   * @return the replacement instance (always {@link SafeInc#INSTANCE
   *         INSTANCE})
   */
  private final Object writeReplace() {
    return SafeInc.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link SafeInc#INSTANCE INSTANCE} after
   * serialization, i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   *
   * @return the replacement instance (always {@link SafeInc#INSTANCE
   *         INSTANCE})
   */
  private final Object readResolve() {
    return SafeInc.INSTANCE;
  }

}
