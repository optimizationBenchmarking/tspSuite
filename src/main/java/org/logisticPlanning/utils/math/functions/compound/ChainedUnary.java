package org.logisticPlanning.utils.math.functions.compound;

import org.logisticPlanning.utils.math.functions.UnaryFunction;
import org.logisticPlanning.utils.math.functions.arithmetic.Identity;

/**
 * A unary function that computes the result of another unary function when
 * applied to an inner unary function
 */
public class ChainedUnary extends UnaryFunction {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the inner function */
  private final UnaryFunction m_inner;
  /** the outer function */
  private final UnaryFunction m_outer;

  /**
   * Create
   *
   * @param inner
   *          the inner function
   * @param outer
   *          the outer function
   */
  private ChainedUnary(final UnaryFunction inner, final UnaryFunction outer) {
    super();

    if ((inner == null) || (outer == null)) {
      throw new IllegalArgumentException("Function must not be null."); //$NON-NLS-1$
    }
    this.m_inner = inner;
    this.m_outer = outer;
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x0) {
    return this.m_outer.compute(this.m_inner.compute(x0));
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return (this.m_outer.toString() + '(' + this.m_inner.toString() + ')');
  }

  /**
   * Chain a function through another one
   *
   * @param inner
   *          the inner function
   * @param outer
   *          the outer function
   * @return the chained function
   */
  public static final UnaryFunction chain(final UnaryFunction inner,
      final UnaryFunction outer) {
    if (inner instanceof Identity) {
      return outer;
    }
    if (outer instanceof Identity) {
      return inner;
    }
    return new ChainedUnary(inner, outer);
  }
}
