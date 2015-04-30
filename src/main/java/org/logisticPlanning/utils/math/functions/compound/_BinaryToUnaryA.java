package org.logisticPlanning.utils.math.functions.compound;

import org.logisticPlanning.utils.math.functions.BinaryFunction;
import org.logisticPlanning.utils.math.functions.UnaryFunction;

/**
 * A transformation of a binary function to a unary function by replacing
 * the first argument with a constant.
 */
final class _BinaryToUnaryA extends ChainedBinary {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * the first argument of the binary function
   * 
   * @serial the double constant being the input of the first argument of
   *         the binary function
   */
  private final double m_a;
  /**
   * the second argument of the binary function
   * 
   * @serial the unary function being the second input
   */
  private final UnaryFunction m_b;
  /**
   * the binary function
   * 
   * @serial the unary function being the second input of the binary
   *         function
   */
  private final BinaryFunction m_f;

  /**
   * Create
   * 
   * @param a
   *          the double constant being the first argument of the binary
   *          function
   * @param b
   *          the unary function being the second argument
   * @param f
   *          the binary function
   */
  _BinaryToUnaryA(final double a, final UnaryFunction b,
      final BinaryFunction f) {
    super();

    if ((b == null) || (f == null)) {
      throw new IllegalArgumentException("Functions must not be null."); //$NON-NLS-1$
    }
    this.m_a = a;
    this.m_b = b;
    this.m_f = f;
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x0) {
    return this.m_f.compute(this.m_a, this.m_b.compute(x0));
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    return (((((this.m_f.toString() + '(') + this.m_a) + ',') + this.m_b
        .toString()) + ')');
  }
}
