package org.logisticPlanning.tsp.solving.searchSpaces.trees.math.arith;

import org.logisticPlanning.tsp.solving.searchSpaces.trees.Node;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.NodeType;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.math.Function;

/**
 * Compute the exponent of a given number
 */
public final class Exp extends Function {
  /**
   * Create a node with the given children
   *
   * @param pchildren
   *          the child nodes
   * @param in
   *          the node information record
   */
  public Exp(final Node<?>[] pchildren, final NodeType<Exp, Function> in) {
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
    sb.append("exp("); //$NON-NLS-1$
    this.get(0).fillInText(sb);
    sb.append(')');
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double[] data) {
    return Math.exp(this.get(0).compute(data));
  }
}
