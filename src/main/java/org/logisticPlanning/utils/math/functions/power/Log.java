package org.logisticPlanning.utils.math.functions.power;

import org.logisticPlanning.utils.math.functions.BinaryFunction;

/**
 * The log function computes the logarithm of {@code x2} to the base
 * {@code x1}.
 */
public final class Log extends BinaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final Log INSTANCE = new Log();

  /** instantiate */
  private Log() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1, final double x2) {
    final double d;

    if (x1 == 10d) {
      return Math.log10(x2);
    }

    d = Math.log(x2);
    if (x1 == Math.E) {
      return d;
    }
    return (d / Math.log(x1));
  }

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link Log#INSTANCE INSTANCE} for
   * serialization, i.e., when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   * 
   * @return the replacement instance (always {@link Log#INSTANCE INSTANCE}
   *         )
   */
  private final Object writeReplace() {
    return Log.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link Log#INSTANCE INSTANCE} after
   * serialization, i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   * 
   * @return the replacement instance (always {@link Log#INSTANCE INSTANCE}
   *         )
   */
  private final Object readResolve() {
    return Log.INSTANCE;
  }
}
