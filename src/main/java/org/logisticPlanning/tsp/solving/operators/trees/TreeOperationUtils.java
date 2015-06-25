package org.logisticPlanning.tsp.solving.operators.trees;

import org.logisticPlanning.tsp.solving.searchSpaces.trees.Node;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.NodeType;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.NodeTypeSet;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * Utility class for tree-based search operations
 */
public final class TreeOperationUtils {

  /**
   * Create a sub-tree of the specified size maximum depth. This function
   * facilitates both, an implementation of the full method Algorithm 31.1
   * and one of the grow method Algorithm 31.2, which can be chosen by
   * setting the parameter 'full' apropriately. It can be used to create
   * trees during the random population initialization phase or during
   * mutation steps.
   *
   * @param types
   *          the node types available for creating the tree
   * @param maxDepth
   *          the maximum depth of the tree
   * @param full
   *          Should we construct a sub-tree according to the full method?
   *          If full is false, grow is used.
   * @param r
   *          the random number generator
   * @return the new tree
   * @param <NT>
   *          the node type
   */
  @SuppressWarnings("unchecked")
  public static final <NT extends Node<NT>> NT createTree(
      final NodeTypeSet<NT> types, final int maxDepth, final boolean full,
      final Randomizer r) {
    NodeType<NT, NT> t;
    NT[] x;
    int i;

    t = null;
    if (maxDepth <= 1) {
      t = types.randomTerminalType(r);
    } else {
      if (full) {
        t = types.randomNonTerminalType(r);
      }
      if (t == null) {
        t = types.randomType(r);
      }
    }
    if (t.isTerminal()) {
      return types.randomTerminalType(r).instantiate(null, r);
    }

    i = t.getChildCount();
    x = ((NT[]) (new Node[i]));
    for (; (--i) >= 0;) {
      x[i] = TreeOperationUtils.createTree(t.getChildTypes(i),
          maxDepth - 1, full, r);
    }
    return t.instantiate(x, r);
  }

}
