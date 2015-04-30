package org.logisticPlanning.tsp.solving.searchSpaces.trees.math.arith;

import org.logisticPlanning.tsp.solving.searchSpaces.trees.Node;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.NodeType;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.math.Function;

/**
 * Frac a given number
 */
public final class Frac extends Function {
  /**
   * Create a node with the given children
   * 
   * @param pchildren
   *          the child nodes
   * @param in
   *          the node information record
   */
  public Frac(final Node<?>[] pchildren, final NodeType<Frac, Function> in) {
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
    sb.append("frac"); //$NON-NLS-1$
    this.printSubExpression(0, sb, ' ');
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double[] data) {
    final double d;

    d = (this.get(0).compute(data));
    return (d - Math.floor(d));
  }
}
