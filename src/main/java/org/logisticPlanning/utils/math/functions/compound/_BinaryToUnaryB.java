package org.logisticPlanning.utils.math.functions.compound;

import org.logisticPlanning.utils.math.functions.BinaryFunction;
import org.logisticPlanning.utils.math.functions.UnaryFunction;

/**
 * A transformation of a binary function to a unary function by replacing
 * the second argument with a constant.
 */
final class _BinaryToUnaryB extends ChainedBinary {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * the first argument of the binary function
   *
   * @serial the unary function being the first input
   */
  private final UnaryFunction m_a;
  /**
   * the second argument of the binary function
   *
   * @serial the double constant being the second of the first argument of
   *         the binary function
   */
  private final double m_b;

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
   *          the unary function being the first argument
   * @param b
   *          the double constant being the second argument of the binary
   *          function
   * @param f
   *          the binary function
   */
  _BinaryToUnaryB(final UnaryFunction a, final double b,
      final BinaryFunction f) {
    super();

    if ((a == null) || (f == null)) {
      throw new IllegalArgumentException("Functions must not be null."); //$NON-NLS-1$
    }
    this.m_a = a;
    this.m_b = b;
    this.m_f = f;
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x0) {
    return this.m_f.compute(this.m_a.compute(x0), this.m_b);
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    return (((((this.m_f.toString() + '(') + this.m_a.toString()) + ',') + this.m_a) + ')');
  }
}
