package org.logisticPlanning.tsp.solving.searchSpaces.trees.math.trig;

import org.logisticPlanning.tsp.solving.searchSpaces.trees.Node;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.NodeType;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.math.Function;

/**
 * Compute the sinus of a number
 */
public final class Sin extends Function {
  /**
   * Create a node with the given children
   * 
   * @param pchildren
   *          the child nodes
   * @param in
   *          the node information record
   */
  public Sin(final Node<?>[] pchildren, final NodeType<Sin, Function> in) {
    super(pchildren, in);
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double[] data) {
    return Math.sin(this.get(0).compute(data));
  }

  /**
   * Fill in the text associated with this node
   * 
   * @param sb
   *          the string builder
   */
  @Override
  public final void fillInText(final StringBuilder sb) {
    sb.append("sin"); //$NON-NLS-1$
    this.printSubExpression(0, sb, ' ');
  }

}
