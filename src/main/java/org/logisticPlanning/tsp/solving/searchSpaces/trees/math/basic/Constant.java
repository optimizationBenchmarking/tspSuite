package org.logisticPlanning.tsp.solving.searchSpaces.trees.math.basic;

import org.logisticPlanning.tsp.solving.searchSpaces.trees.math.Function;

/**
 * A constant always returns a specific value
 */
public final class Constant extends Function {
  /** the constant value */
  private final double m_value;

  /**
   * Create a constant
   *
   * @param in
   *          the constant type
   * @param val
   *          the value
   */
  public Constant(final ConstantType in, final double val) {
    super(null, in);
    this.m_value = val;
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double[] data) {
    return this.m_value;
  }

  /**
   * Fill in the text associated with this node
   *
   * @param sb
   *          the string builder
   */
  @Override
  public final void fillInText(final StringBuilder sb) {
    sb.append(this.m_value);
  }
}
