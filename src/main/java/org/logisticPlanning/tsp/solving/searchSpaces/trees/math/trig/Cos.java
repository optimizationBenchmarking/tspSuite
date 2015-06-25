package org.logisticPlanning.tsp.solving.searchSpaces.trees.math.trig;

import org.logisticPlanning.tsp.solving.searchSpaces.trees.Node;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.NodeType;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.math.Function;

/**
 * Compute the cosine of a number
 */
public final class Cos extends Function {
  /**
   * Create a node with the given children
   *
   * @param pchildren
   *          the child nodes
   * @param in
   *          the node information record
   */
  public Cos(final Node<?>[] pchildren, final NodeType<Cos, Function> in) {
    super(pchildren, in);
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double[] data) {
    return Math.cos(this.get(0).compute(data));
  }

  /**
   * Fill in the text associated with this node
   *
   * @param sb
   *          the string builder
   */
  @Override
  public final void fillInText(final StringBuilder sb) {
    sb.append("cos"); //$NON-NLS-1$
    this.printSubExpression(0, sb, ' ');
  }
}
