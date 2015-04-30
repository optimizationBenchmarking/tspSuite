package org.logisticPlanning.utils.math.functions.numeric;

import org.logisticPlanning.utils.math.functions.BinaryFunction;

/**
 * A method for rounding up the result of a division.
 */
public final class CeilDiv extends BinaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final CeilDiv INSTANCE = new CeilDiv();

  /** the forbidden constructor */
  private CeilDiv() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final byte compute(final byte x1, final byte x2) {
    int res1, res2;

    if (x2 == ((byte) (0))) {
      if (x1 == ((byte) (0))) {
        return ((byte) (0));
      }
      if (x1 >= ((byte) (0))) {
        return (java.lang.Byte.MAX_VALUE);
      }
      return (java.lang.Byte.MIN_VALUE);
    }

    res1 = (x1 / x2);
    res2 = (res1 * x2);
    if (res2 >= x1) {
      return ((byte) (res1));
    }

    return ((byte) (res1 + 1));
  }

  /** {@inheritDoc} */
  @Override
  public final short compute(final short x1, final short x2) {
    int res1, res2;

    if (x2 == ((short) (0))) {
      if (x1 == ((short) (0))) {
        return ((short) (0));
      }
      if (x1 >= ((short) (0))) {
        return (java.lang.Short.MAX_VALUE);
      }
      return (java.lang.Short.MIN_VALUE);
    }

    res1 = (x1 / x2);
    res2 = (res1 * x2);
    if (res2 >= x1) {
      return ((short) (res1));
    }

    return ((short) (res1 + 1));
  }

  /** {@inheritDoc} */
  @Override
  public final int compute(final int x1, final int x2) {
    int res1, res2;

    if (x2 == (0)) {
      if (x1 == (0)) {
        return (0);
      }
      if (x1 >= (0)) {
        return (java.lang.Integer.MAX_VALUE);
      }
      return (java.lang.Integer.MIN_VALUE);
    }

    res1 = (x1 / x2);
    res2 = (res1 * x2);
    if (res2 >= x1) {
      return (res1);
    }

    return (res1 + 1);
  }

  /** {@inheritDoc} */
  @Override
  public final long compute(final long x1, final long x2) {
    long res1, res2;

    if (x2 == (0l)) {
      if (x1 == (0l)) {
        return (0l);
      }
      if (x1 >= (0l)) {
        return (java.lang.Long.MAX_VALUE);
      }
      return (java.lang.Long.MIN_VALUE);
    }

    res1 = (x1 / x2);
    res2 = (res1 * x2);
    if (res2 >= x1) {
      return (res1);
    }

    return (res1 + 1);
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1, final double x2) {
    return Math.ceil(x1 / x2);
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link CeilDiv#INSTANCE INSTANCE} for
   * serialization, i.e., when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   * 
   * @return the replacement instance (always {@link CeilDiv#INSTANCE
   *         INSTANCE})
   */
  private final Object writeReplace() {
    return CeilDiv.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link CeilDiv#INSTANCE INSTANCE} after
   * serialization, i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   * 
   * @return the replacement instance (always {@link CeilDiv#INSTANCE
   *         INSTANCE})
   */
  private final Object readResolve() {
    return CeilDiv.INSTANCE;
  }

}
