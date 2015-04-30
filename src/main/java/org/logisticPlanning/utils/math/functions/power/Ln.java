package org.logisticPlanning.utils.math.functions.power;

import org.logisticPlanning.utils.math.functions.UnaryFunction;

/**
 * The ln function
 */
public final class Ln extends UnaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final Ln INSTANCE = new Ln();

  /** instantiate */
  private Ln() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1) {
    return Math.log(x1);
  }

  /** {@inheritDoc} */
  @Override
  public final Exp invertFor(final int index) {
    return Exp.INSTANCE;
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link Ln#INSTANCE Ln.INSTANCE} for
   * serialization, i.e., when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   * 
   * @return the replacement instance (always {@link Ln#INSTANCE
   *         Ln.INSTANCE})
   */
  private final Object writeReplace() {
    return Ln.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link Ln#INSTANCE Ln.INSTANCE} after
   * serialization, i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   * 
   * @return the replacement instance (always {@link Ln#INSTANCE
   *         Ln.INSTANCE})
   */
  private final Object readResolve() {
    return Ln.INSTANCE;
  }

}
