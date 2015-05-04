package org.logisticPlanning.utils.math.functions.compound;

import org.logisticPlanning.utils.math.functions.UnaryFunction;

/**
 * A fixed scale
 */
public class FixedScale extends UnaryFunction {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * the forwarding function
   *
   * @serial the unary function to be scaled
   */
  private final UnaryFunction m_f;

  /**
   * the scale
   *
   * @serial the double value with the scale
   */
  private final double m_scale;

  /**
   * Create
   *
   * @param f
   *          the function
   * @param scale
   *          the scale
   */
  private FixedScale(final UnaryFunction f, final double scale) {
    super();

    if (f == null) {
      throw new IllegalArgumentException("Function must not be null."); //$NON-NLS-1$
    }
    if ((scale != scale) || (scale <= Double.NEGATIVE_INFINITY)
        || (scale >= Double.POSITIVE_INFINITY)) {
      throw new IllegalArgumentException(
          "Scale must be finite, but is " + scale); //$NON-NLS-1$
    }

    this.m_f = f;
    this.m_scale = scale;
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x0) {
    return (this.m_scale * this.m_f.compute(x0));
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return (((('(' + this.m_f.toString()) + '*') + this.m_scale) + ')');
  }

  /**
   * create a scaled version of a given unary function
   *
   * @param f
   *          the function
   * @param scale
   *          the scale
   * @return the scaled version
   */
  public static final UnaryFunction scale(final UnaryFunction f,
      final double scale) {
    if (scale == 1d) {
      return f;
    }
    if (scale == 0d) {
      return DoubleConstant.constant(0d);
    }
    return new FixedScale(f, scale);
  }
}
