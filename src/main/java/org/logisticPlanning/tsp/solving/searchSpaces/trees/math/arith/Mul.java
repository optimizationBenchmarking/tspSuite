package org.logisticPlanning.tsp.solving.searchSpaces.trees.math.arith;

import org.logisticPlanning.tsp.solving.searchSpaces.trees.Node;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.NodeType;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.math.Function;

/**
 * Multiply two numbers
 */
public final class Mul extends Function {
  /**
   * Create a node with the given children
   * 
   * @param pchildren
   *          the child nodes
   * @param in
   *          the node information record
   */
  public Mul(final Node<?>[] pchildren, final NodeType<Mul, Function> in) {
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
    sb.append('*');
    this.printSubExpression(1, sb);
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double[] data) {
    return (this.get(0).compute(data) * this.get(1).compute(data));
  }
}
