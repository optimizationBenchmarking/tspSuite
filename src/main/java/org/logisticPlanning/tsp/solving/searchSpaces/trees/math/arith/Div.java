package org.logisticPlanning.tsp.solving.searchSpaces.trees.math.arith;

import org.logisticPlanning.tsp.solving.searchSpaces.trees.Node;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.NodeType;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.math.Function;

/**
 * Divide two numbers
 */
public final class Div extends Function {
  /**
   * Create a node with the given children
   *
   * @param pchildren
   *          the child nodes
   * @param in
   *          the node information record
   */
  public Div(final Node<?>[] pchildren, final NodeType<Div, Function> in) {
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
    this.printSubExpression(0, sb);
    sb.append('/');
    this.printSubExpression(1, sb);
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double[] data) {
    final double a, b;

    a = this.get(0).compute(data);
    b = this.get(1).compute(data);
    if (b == 0d) {
      return a;
    }
    return (a / b);
  }
}
