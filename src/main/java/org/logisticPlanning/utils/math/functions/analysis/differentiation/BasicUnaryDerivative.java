package org.logisticPlanning.utils.math.functions.analysis.differentiation;

import org.logisticPlanning.utils.math.functions.UnaryFunction;

/**
 * The base class for derivatives of an unary function.
 */
class BasicUnaryDerivative extends UnaryFunction {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** @serial the function */
  final UnaryFunction m_f;

  /**
   * Create
   *
   * @param f
   *          the function
   */
  public BasicUnaryDerivative(final UnaryFunction f) {
    super();
    this.m_f = f;
  }
}
