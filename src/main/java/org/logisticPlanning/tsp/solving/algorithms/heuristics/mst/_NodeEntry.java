package org.logisticPlanning.tsp.solving.algorithms.heuristics.mst;

/**
 * <p>
 * This class is an internal class. Please do not instantiate it, use it by
 * yourself, or otherwise meddle with it.
 * </p>
 * <p>
 * This is an internal class which is used to construct the minimum
 * spanning tree (MST) needed for the
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.mst.MSTHeuristic
 * MST Heuristic} .
 * </p>
 */
final class _NodeEntry {

  /** the next entry */
  _NodeEntry m_nextChild;

  /** the child entry */
  _NodeEntry m_child;

  /** the parent entry */
  _NodeEntry m_parent;

  /** the data element */
  final int m_node;

  /** the priority */
  int m_priority;

  /**
   * Instantiate the node entry
   *
   * @param id
   *          the node id
   * @param priority
   *          the node priority
   * @param parent
   *          the node's parent
   */
  _NodeEntry(final int id, final int priority, final _NodeEntry parent) {
    super();
    this.m_node = id;
    this.m_priority = priority;
    this.m_parent = parent;
  }
}
