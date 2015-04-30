package org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundHeldCarp;

import java.util.Comparator;

/** an internal comparator */
final class _NodeComparator implements Comparator<_Node> {

  /** the shared instance */
  static final _NodeComparator INSTANCE = new _NodeComparator();

  /** create */
  private _NodeComparator() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public int compare(final _Node a, final _Node b) {
    return Double.compare(a.m_lowerBound, b.m_lowerBound);
  }
}
