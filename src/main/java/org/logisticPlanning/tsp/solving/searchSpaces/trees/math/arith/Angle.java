package org.logisticPlanning.tsp.solving.searchSpaces.trees.math.arith;

import org.logisticPlanning.tsp.solving.searchSpaces.trees.Node;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.NodeType;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.math.Function;

/**
 * Subtract two numbers
 */
public final class Angle extends Function {
  /**
   * Create a node with the given children
   * 
   * @param pchildren
   *          the child nodes
   * @param in
   *          the node information record
   */
  public Angle(final Node<?>[] pchildren,
      final NodeType<Angle, Function> in) {
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
    sb.append("angle("); //$NON-NLS-1$
    this.printSubExpression(0, sb);
    sb.append(',');
    this.printSubExpression(1, sb);
    sb.append(')');
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double[] data) {
    return Math
        .atan2(this.get(0).compute(data), this.get(1).compute(data));
  }
}
