package org.logisticPlanning.tsp.solving.searchSpaces.trees.math.trig;

import org.logisticPlanning.tsp.solving.searchSpaces.trees.Node;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.NodeType;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.math.Function;

/**
 * Compute the tangent of a number
 */
public final class Tan extends Function {

  /** the modulo */
  private static final double MOD = (0.5d * Math.PI);

  /**
   * Create a node with the given children
   * 
   * @param pchildren
   *          the child nodes
   * @param in
   *          the node information record
   */
  public Tan(final Node<?>[] pchildren, final NodeType<Tan, Function> in) {
    super(pchildren, in);
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double[] data) {
    return Math.tan(this.get(0).compute(data) % Tan.MOD);
  }

  /**
   * Fill in the text associated with this node
   * 
   * @param sb
   *          the string builder
   */
  @Override
  public final void fillInText(final StringBuilder sb) {
    sb.append("tan"); //$NON-NLS-1$
    this.printSubExpression(0, sb, ' ');
  }

}
