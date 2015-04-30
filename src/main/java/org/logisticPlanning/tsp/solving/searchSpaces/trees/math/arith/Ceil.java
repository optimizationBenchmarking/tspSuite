package org.logisticPlanning.tsp.solving.searchSpaces.trees.math.arith;

import org.logisticPlanning.tsp.solving.searchSpaces.trees.Node;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.NodeType;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.math.Function;

/**
 * Ceil a given number
 */
public final class Ceil extends Function {
  /**
   * Create a node with the given children
   * 
   * @param pchildren
   *          the child nodes
   * @param in
   *          the node information record
   */
  public Ceil(final Node<?>[] pchildren, final NodeType<Ceil, Function> in) {
    super(pchildren, in);
  }

  /**
   * Fill in the text associated with this node
   * 
   * @param sb
   *          the string builder
   */
  @Override
  public final void fillInText(final StringBuilder sb) {
    sb.append("ceil"); //$NON-NLS-1$
    this.printSubExpression(0, sb, ' ');
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double[] data) {
    return Math.ceil(this.get(0).compute(data));
  }
}
