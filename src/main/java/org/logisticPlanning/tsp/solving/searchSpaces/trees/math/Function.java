package org.logisticPlanning.tsp.solving.searchSpaces.trees.math;

import org.logisticPlanning.tsp.solving.searchSpaces.trees.Node;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.NodeType;

/**
 * An base class for mathematical functions
 */
public class Function extends Node<Function> {

  /**
   * Create a node with the given children
   *
   * @param pchildren
   *          the child nodes
   * @param in
   *          the node information record
   */
  public Function(final Node<?>[] pchildren,
      final NodeType<? extends Function, Function> in) {
    super(pchildren, in);
  }

  /**
   * Print a sub-expression to the given string builder
   *
   * @param idx
   *          the sub-expression index
   * @param sb
   *          the string builder
   * @param prefix
   *          the alternative prefix
   */
  protected final void printSubExpression(final int idx,
      final StringBuilder sb, final char prefix) {
    Function a;
    boolean b;

    a = this.get(idx);
    b = (a.size() > 0);
    if (b) {
      sb.append('(');
    } else {
      if (prefix != '\0') {
        sb.append(prefix);
      }
    }
    a.fillInText(sb);
    if (b) {
      sb.append(')');
    }
  }

  /**
   * Print a sub-expression to the given string builder
   *
   * @param idx
   *          the sub-expression index
   * @param sb
   *          the string builder
   */
  protected final void printSubExpression(final int idx,
      final StringBuilder sb) {
    this.printSubExpression(idx, sb, '\0');
  }

  /**
   * compute the function result
   *
   * @param data
   *          the data array
   * @return the return value
   */
  public double compute(final double[] data) {
    return 0d;
  }
}
