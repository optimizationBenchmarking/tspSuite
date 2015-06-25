package org.logisticPlanning.utils.math.functions.combinatoric;

import org.logisticPlanning.utils.math.functions.BinaryFunction;

/**
 * The gcd function
 */
public final class GCD extends BinaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final GCD INSTANCE = new GCD();

  /** instantiate */
  private GCD() {
    super();
  }

  /**
   * compute the greatest common divisor
   *
   * @param a
   *          the n
   * @param b
   *          the k
   * @return the coefficient
   */
  @Override
  public final int compute(final int a, final int b) {
    int u, v, r;

    u = Math.abs(a);
    v = Math.abs(b);

    r = (0);

    while (true) {
      if (v <= 0l) {
        return u;
      }
      r = (u % v);
      u = v;
      v = r;
    }
  }

  /**
   * compute the greatest common divisor
   *
   * @param a
   *          the n
   * @param b
   *          the k
   * @return the coefficient
   */
  @Override
  public final long compute(final long a, final long b) {
    long u, v, r;

    u = Math.abs(a);
    v = Math.abs(b);

    r = (0l);

    while (true) {
      if (v <= 0l) {
        return u;
      }
      r = (u % v);
      u = v;
      v = r;
    }
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1, final double x2) {
    return GCD.INSTANCE.compute(Math.round(x1), Math.round(x2));
  }

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link GCD#INSTANCE INSTANCE} for
   * serialization, i.e., when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   *
   * @return the replacement instance (always {@link GCD#INSTANCE INSTANCE}
   *         )
   */
  private final Object writeReplace() {
    return GCD.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link GCD#INSTANCE INSTANCE} after
   * serialization, i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   *
   * @return the replacement instance (always {@link GCD#INSTANCE INSTANCE}
   *         )
   */
  private final Object readResolve() {
    return GCD.INSTANCE;
  }
}
