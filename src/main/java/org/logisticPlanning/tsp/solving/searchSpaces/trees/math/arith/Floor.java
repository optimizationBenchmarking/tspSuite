package org.logisticPlanning.tsp.solving.searchSpaces.trees.math.arith;

import org.logisticPlanning.tsp.solving.searchSpaces.trees.Node;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.NodeType;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.math.Function;

/**
 * Floor a given number
 */
public final class Floor extends Function {
  /**
   * Create a node with the given children
   * 
   * @param pchildren
   *          the child nodes
   * @param in
   *          the node information record
   */
  public Floor(final Node<?>[] pchildren,
      final NodeType<Floor, Function> in) {
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
    sb.append("floor"); //$NON-NLS-1$
    this.printSubExpression(0, sb, ' ');
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double[] data) {
    return Math.floor(this.get(0).compute(data));
  }
}
