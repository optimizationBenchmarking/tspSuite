package org.logisticPlanning.tsp.solving.searchSpaces.trees.math.arith;

import org.logisticPlanning.tsp.solving.searchSpaces.trees.Node;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.NodeType;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.math.Function;

/**
 * Compute the absolute value of a given number
 */
public final class Abs extends Function {
  /**
   * Create a node with the given children
   *
   * @param pchildren
   *          the child nodes
   * @param in
   *          the node information record
   */
  public Abs(final Node<?>[] pchildren, final NodeType<Abs, Function> in) {
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
    sb.append('|');
    this.get(0).fillInText(sb);
    sb.append('|');
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double[] data) {
    return Math.abs(this.get(0).compute(data));
  }
}
