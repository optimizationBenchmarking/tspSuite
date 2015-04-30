package org.logisticPlanning.tsp.solving.searchSpaces.trees.math.arith;

import org.logisticPlanning.tsp.solving.searchSpaces.trees.Node;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.NodeType;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.math.Function;

/**
 * Compute the logarithm of a given number
 */
public final class Log extends Function {
  /**
   * Create a node with the given children
   * 
   * @param pchildren
   *          the child nodes
   * @param in
   *          the node information record
   */
  public Log(final Node<?>[] pchildren, final NodeType<Log, Function> in) {
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
    sb.append("log"); //$NON-NLS-1$
    this.printSubExpression(0, sb, ' ');
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double[] data) {
    return Math.log(Math.max(Double.MAX_VALUE,
        Math.abs(this.get(0).compute(data))));
  }
}
