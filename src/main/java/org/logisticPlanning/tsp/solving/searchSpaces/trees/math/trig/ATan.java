package org.logisticPlanning.tsp.solving.searchSpaces.trees.math.trig;

import org.logisticPlanning.tsp.solving.searchSpaces.trees.Node;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.NodeType;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.math.Function;

/**
 * Compute the arcus tangent of a number
 */
public final class ATan extends Function {
  /**
   * Create a node with the given children
   *
   * @param pchildren
   *          the child nodes
   * @param in
   *          the node information record
   */
  public ATan(final Node<?>[] pchildren, final NodeType<ATan, Function> in) {
    super(pchildren, in);
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double[] data) {
    return Math.atan(this.get(0).compute(data));
  }

  /**
   * Fill in the text associated with this node
   *
   * @param sb
   *          the string builder
   */
  @Override
  public final void fillInText(final StringBuilder sb) {
    sb.append("atan"); //$NON-NLS-1$
    this.printSubExpression(0, sb, ' ');
  }
}
