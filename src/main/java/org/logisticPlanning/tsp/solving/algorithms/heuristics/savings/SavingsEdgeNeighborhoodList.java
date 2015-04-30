package org.logisticPlanning.tsp.solving.algorithms.heuristics.savings;

import org.logisticPlanning.tsp.solving.utils.edge.UndirectedPriorityEdgeNeighborList;

/**
 * This is an internal class that manages the neighbor lists for each node.
 */
public final class SavingsEdgeNeighborhoodList extends
    UndirectedPriorityEdgeNeighborList<SavingsEdge> {

  /**
   * create
   * 
   * @param n
   *          the node count
   * @param nls
   *          the neighbor list length
   */
  public SavingsEdgeNeighborhoodList(final int n, final int nls) {
    super(n, nls);
  }

  /** {@inheritDoc} */
  @Override
  protected final SavingsEdge createEdge() {
    return new SavingsEdge();
  }

  /** {@inheritDoc} */
  @Override
  protected final SavingsEdge[] createArray(final int length) {
    return new SavingsEdge[length];
  }

}
