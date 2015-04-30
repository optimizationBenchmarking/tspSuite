package org.logisticPlanning.tsp.solving.searchSpaces.trees.math.basic;

import org.logisticPlanning.tsp.solving.searchSpaces.trees.math.Function;

/**
 * A variable returns the value at a specific index of the data
 */
public final class Variable extends Function {
  /** the variable index */
  private final int m_index;

  /**
   * Create a variable
   * 
   * @param in
   *          the node information record
   * @param idx
   *          the index
   */
  public Variable(final VariableType in, final int idx) {
    super(null, in);
    this.m_index = idx;
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double[] data) {
    return data[this.m_index];
  }

  /**
   * Fill in the text associated with this node
   * 
   * @param sb
   *          the string builder
   */
  @Override
  public final void fillInText(final StringBuilder sb) {
    sb.append('x');
    sb.append('[');
    sb.append(this.m_index);
    sb.append(']');
  }
}
