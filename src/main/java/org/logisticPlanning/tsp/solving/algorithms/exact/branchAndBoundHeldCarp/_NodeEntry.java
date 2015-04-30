package org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundHeldCarp;

/**
 * <p>
 * This class is an internal class. Please do not instantiate it, use it by
 * yourself, or otherwise meddle with it.
 * </p>
 * <p>
 * This is an internal class which is used to construct the one tree. MST
 * Heuristic} .
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
  int m_node;

  /** the priority */
  double m_costWithPi;

  /** the distance corresponding to the priority */
  int m_dist;

  /** are we not done? */
  boolean m_ready;

  /** Instantiate the node entry */
  _NodeEntry() {
    super();
  }

  /** clear this nodes data */
  final void clear() {
    this.m_nextChild = null;
    this.m_child = null;
    this.m_parent = null;
    this.m_dist = Integer.MAX_VALUE;
    this.m_ready = true;
    this.m_costWithPi = Double.POSITIVE_INFINITY;
  }
}
