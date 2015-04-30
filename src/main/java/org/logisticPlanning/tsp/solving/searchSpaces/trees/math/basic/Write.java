package org.logisticPlanning.tsp.solving.searchSpaces.trees.math.basic;

import org.logisticPlanning.tsp.solving.searchSpaces.trees.Node;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.math.Function;

/**
 * A memory write operation
 */
public final class Write extends Function {
  /** the variable index */
  private final int m_index;

  /**
   * Create a write operation
   * 
   * @param pchildren
   *          the child nodes *
   * @param in
   *          the node information record
   * @param idx
   *          the index
   */
  public Write(final Node<?>[] pchildren, final WriteType in, final int idx) {
    super(pchildren, in);
    this.m_index = idx;
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double[] data) {
    return data[this.m_index] = this.get(0).compute(data);
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
    sb.append('=');
    this.printSubExpression(0, sb, '\0');
  }

  /**
   * Compare with another object
   * 
   * @param o
   *          the other object
   * @return true if the objects are equal
   */
  @Override
  public final boolean equals(final Object o) {
    Write w;

    if (o == this) {
      return true;
    }
    if (!(o instanceof Write)) {
      return false;
    }

    w = ((Write) o);

    return ((w.m_index == this.m_index) && (w.get(0).equals(this.get(0))));
  }
}
