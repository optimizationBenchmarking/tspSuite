package org.logisticPlanning.utils.math.functions.combinatoric;

import org.logisticPlanning.utils.math.functions.UnaryFunction;

/**
 * The logarithm of the factorial function
 */
public final class FactorialLog extends UnaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final FactorialLog INSTANCE = new FactorialLog();

  /** the factorials logarithms */
  static final double[] FACTORIAL_LOGS;

  static {
    int i;
    long d;

    i = Factorial.FACTORIALS.length;
    FACTORIAL_LOGS = new double[i];
    for (; (--i) >= 0;) {
      d = Factorial.FACTORIALS[i];
      if (d > 1l) {
        FactorialLog.FACTORIAL_LOGS[i] = Math.log(d);
      } else {
        FactorialLog.FACTORIAL_LOGS[i] = 0d;
      }
    }

  }

  /** instantiate */
  private FactorialLog() {
    super();
  }

  /**
   * precisely compute the factorial log
   *
   * @param start
   *          the start
   * @param end
   *          the end
   * @return the value
   */
  private static final double factorialLogI(final int start, final int end) {
    int mid;

    if (start >= end) {
      return Math.log(start);
    }

    mid = ((start + end) >>> 1);
    return (FactorialLog.factorialLogI(start, mid) + //
        FactorialLog.factorialLogI(mid + 1, end));
  }

  /**
   * precisely compute the factorial log
   *
   * @param start
   *          the start
   * @param end
   *          the end
   * @return the value
   */
  private static final double factorialLogL(final long start,
      final long end) {
    long mid;

    if (start >= end) {
      return Math.log(start);
    }

    mid = ((start + end) >>> 1);
    return (FactorialLog.factorialLogL(start, mid) + //
        FactorialLog.factorialLogL(mid + 1, end));
  }

  /**
   * compute the factorial of a number i
   *
   * @param i
   *          the number
   * @return the factorial of <code>i</Coe
   */
  public static final double factorialLog(final long i) {
    double d;
    int v;

    if (i <= 1l) {
      return 0d;
    }
    if (i < FactorialLog.FACTORIAL_LOGS.length) {
      return FactorialLog.FACTORIAL_LOGS[(int) i];
    }

    if (i > Integer.MAX_VALUE) {
      d = FactorialLog.factorialLogL((Integer.MAX_VALUE + 1l), i);
      v = Integer.MAX_VALUE;
    } else {
      d = 0d;
      v = ((int) i);
    }

    return (d + FactorialLog.factorialLogI(2, v));
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1) {
    return FactorialLog.factorialLog(Math.round(x1));
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link FactorialLog#INSTANCE
   * FactorialLog.INSTANCE} for serialization, i.e., when the instance is
   * written with {@link java.io.ObjectOutputStream#writeObject(Object)}.
   *
   * @return the replacement instance (always {@link FactorialLog#INSTANCE
   *         FactorialLog.INSTANCE})
   */
  private final Object writeReplace() {
    return FactorialLog.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link FactorialLog#INSTANCE
   * FactorialLog.INSTANCE} after serialization, i.e., when the instance is
   * read with {@link java.io.ObjectInputStream#readObject()}.
   *
   * @return the replacement instance (always {@link FactorialLog#INSTANCE
   *         FactorialLog.INSTANCE})
   */
  private final Object readResolve() {
    return FactorialLog.INSTANCE;
  }

}
