package org.logisticPlanning.tsp.solving.searchSpaces.trees.math.arith;

import org.logisticPlanning.tsp.solving.searchSpaces.trees.Node;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.NodeType;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.math.Function;

/**
 * Round a given number
 */
public final class Round extends Function {
  /**
   * Create a node with the given children
   *
   * @param pchildren
   *          the child nodes
   * @param in
   *          the node information record
   */
  public Round(final Node<?>[] pchildren,
      final NodeType<Round, Function> in) {
    super(pchildren, in);
  }

  /**
   * Fill in the text associated with this node
   *
   * @param sb
   *          the string builder
   */
  @Override
  public void fillInText(final StringBuilder sb) {
    sb.append("round"); //$NON-NLS-1$
    this.printSubExpression(0, sb, ' ');
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double[] data) {
    return Math.rint(this.get(0).compute(data));
  }
}
