package org.logisticPlanning.tsp.solving.searchSpaces.trees.math.ctrl;

import org.logisticPlanning.tsp.solving.searchSpaces.trees.Node;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.NodeType;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.math.Function;

/**
 * If-Then-Else
 */
public final class IfThenElse extends Function {
  /**
   * Create a node with the given children
   * 
   * @param pchildren
   *          the child nodes
   * @param in
   *          the node information record
   */
  public IfThenElse(final Node<?>[] pchildren,
      final NodeType<IfThenElse, Function> in) {
    super(pchildren, in);
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double[] data) {
    if (this.get(0).compute(data) > 0d) {
      return this.get(1).compute(data);
    }
    return this.get(2).compute(data);
  }

  /**
   * Fill in the text associated with this node
   * 
   * @param sb
   *          the string builder
   */
  @Override
  public final void fillInText(final StringBuilder sb) {
    sb.append("if "); //$NON-NLS-1$
    this.printSubExpression(0, sb, '\0');
    sb.append(">0 then ");//$NON-NLS-1$
    this.printSubExpression(1, sb, '\0');
    sb.append(" else ");//$NON-NLS-1$
    this.printSubExpression(2, sb, '\0');
  }
}
