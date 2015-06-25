package org.logisticPlanning.utils.math.functions.compound;

import org.logisticPlanning.utils.math.functions.BinaryFunction;
import org.logisticPlanning.utils.math.functions.UnaryFunction;

/**
 * A binary function turned to an unary function that has one parameter as
 * constant and is thus turned into an unary function.
 */
public abstract class ChainedBinary extends UnaryFunction {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** Instantiate the base class for unary functions */
  ChainedBinary() {
    super();
  }

  /**
   * Chain a unary function through another binary one
   *
   * @param a
   *          the double constant being the first argument of the binary
   *          function
   * @param b
   *          the unary function being the second argument
   * @param f
   *          the binary function
   * @return the chained function
   */
  public static final UnaryFunction chain(final double a,
      final UnaryFunction b, final BinaryFunction f) {
    return new _BinaryToUnaryA(a, b, f);
  }

  /**
   * Chain a unary function through another binary one
   *
   * @param a
   *          the unary function being the first argument
   * @param b
   *          the double constant being the second argument of the binary
   *          function
   * @param f
   *          the binary function
   * @return the chained function
   */
  public static final UnaryFunction chain(final UnaryFunction a,
      final double b, final BinaryFunction f) {
    return new _BinaryToUnaryB(a, b, f);
  }
}
