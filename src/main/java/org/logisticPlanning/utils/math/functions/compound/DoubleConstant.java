package org.logisticPlanning.utils.math.functions.compound;

import org.logisticPlanning.utils.math.functions.UnaryFunction;

/**
 * A constant function
 */
public class DoubleConstant extends UnaryFunction {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * the constant
   * 
   * @serial the double field with the constant value
   */
  private final double m_constant;

  /**
   * Create
   * 
   * @param constant
   *          the constant
   */
  private DoubleConstant(final double constant) {
    super();

    if ((constant != constant) || (constant <= Double.NEGATIVE_INFINITY)
        || (constant >= Double.POSITIVE_INFINITY)) {
      throw new IllegalArgumentException(
          "Constant must be finite, but is " + constant); //$NON-NLS-1$
    }

    this.m_constant = constant;
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x0) {
    return this.m_constant;
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return String.valueOf(this.m_constant);
  }

  /**
   * create a constant
   * 
   * @param constant
   *          the constant
   * @return the constant
   */
  public static final UnaryFunction constant(final double constant) {
    return new DoubleConstant(constant);
  }
}
