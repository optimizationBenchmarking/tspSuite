package org.logisticPlanning.utils.math.functions.combinatoric;

import org.logisticPlanning.utils.math.functions.BinaryFunction;

/**
 * The lcm (least common multiple) function
 */
public final class LCM extends BinaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final LCM INSTANCE = new LCM();

  /** instantiate */
  private LCM() {
    super();
  }

  /**
   * compute the least common multiple
   *
   * @param a
   *          the n
   * @param b
   *          the k
   * @return the coefficient
   */
  @Override
  public final int compute(final int a, final int b) {
    int x, y, t;

    x = Math.abs(a);
    y = Math.abs(b);

    if (x > y) {
      t = x;
      x = y;
      y = t;
    }

    return Math.abs((x / GCD.INSTANCE.compute(x, y)) * y);
  }

  /**
   * compute the least common multiple
   *
   * @param a
   *          the n
   * @param b
   *          the k
   * @return the coefficient
   */
  @Override
  public final long compute(final long a, final long b) {
    long x, y, t;

    x = Math.abs(a);
    y = Math.abs(b);

    if (x > y) {
      t = x;
      x = y;
      y = t;
    }

    return Math.abs((x / GCD.INSTANCE.compute(x, y)) * y);
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1, final double x2) {
    return LCM.INSTANCE.compute(Math.round(x1), Math.round(x2));
  }

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link LCM#INSTANCE INSTANCE} for
   * serialization, i.e., when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   *
   * @return the replacement instance (always {@link LCM#INSTANCE INSTANCE}
   *         )
   */
  private final Object writeReplace() {
    return LCM.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link LCM#INSTANCE INSTANCE} after
   * serialization, i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   *
   * @return the replacement instance (always {@link LCM#INSTANCE INSTANCE}
   *         )
   */
  private final Object readResolve() {
    return LCM.INSTANCE;
  }
}
