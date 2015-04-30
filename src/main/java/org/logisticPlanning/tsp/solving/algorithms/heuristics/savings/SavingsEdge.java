package org.logisticPlanning.tsp.solving.algorithms.heuristics.savings;

import org.logisticPlanning.tsp.solving.utils.edge.PriorityEdge;
import org.logisticPlanning.utils.utils.HashUtils;

/**
 * An internal class representing one undirected edge along with its
 * heuristic value and length.
 */
public final class SavingsEdge extends PriorityEdge {

  /** the distance: dist(a,b) */
  public int distance;

  /**
   * Create an edge with the given nodes, priority, and distance.
   * 
   * @param pa
   *          the first node
   * @param pb
   *          the second node
   * @param ph
   *          the priority value
   * @param pdistance
   *          the distance
   */
  public SavingsEdge(final int pa, final int pb, final int ph,
      final int pdistance) {
    super(pa, pb, ph);
    this.distance = pdistance;
  }

  /** instantiate */
  public SavingsEdge() {
    this(-1, -1, -1, -1);
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equals(final Object o) {
    final SavingsEdge e;
    if (o == this) {
      return true;
    }
    if (o instanceof SavingsEdge) {
      e = ((SavingsEdge) o);
      return ((e.a == this.a) && (e.b == this.b) && (e.h == this.h) && //
      (e.distance == this.distance));
    }
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return HashUtils.combineHashes(HashUtils.combineHashes(
        HashUtils.combineHashes(this.a, this.b), this.h), this.distance);
  }
}
