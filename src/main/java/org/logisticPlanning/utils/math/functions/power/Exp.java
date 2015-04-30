package org.logisticPlanning.utils.math.functions.power;

import org.logisticPlanning.utils.math.functions.UnaryFunction;

/**
 * The exp function
 */
public final class Exp extends UnaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final Exp INSTANCE = new Exp();

  /** instantiate */
  private Exp() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1) {
    return Math.exp(x1);
  }

  /** {@inheritDoc} */
  @Override
  public final Ln invertFor(final int index) {
    return Ln.INSTANCE;
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link Exp#INSTANCE Exp.INSTANCE} for
   * serialization, i.e., when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   * 
   * @return the replacement instance (always {@link Exp#INSTANCE
   *         Exp.INSTANCE})
   */
  private final Object writeReplace() {
    return Exp.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link Exp#INSTANCE Exp.INSTANCE} after
   * serialization, i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   * 
   * @return the replacement instance (always {@link Exp#INSTANCE
   *         Exp.INSTANCE})
   */
  private final Object readResolve() {
    return Exp.INSTANCE;
  }

}
