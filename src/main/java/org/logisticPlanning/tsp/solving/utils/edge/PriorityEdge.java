package org.logisticPlanning.tsp.solving.utils.edge;

import org.logisticPlanning.utils.utils.HashUtils;

/**
 * An {@link org.logisticPlanning.tsp.solving.utils.edge.Edge edge} with a
 * heuristic or priority value.
 */
public class PriorityEdge extends Edge {

  /** the edge's heuristic value */
  public int h;

  /**
   * Create an edge with the given nodes and priority.
   *
   * @param pa
   *          the first node
   * @param pb
   *          the second node
   * @param ph
   *          the priority value
   */
  public PriorityEdge(final int pa, final int pb, final int ph) {
    super(pa, pb);
    this.h = ph;
  }

  /** instantiate */
  public PriorityEdge() {
    this(-1, -1, -1);
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return "h(" + Integer.toString(this.a) //$NON-NLS-1$
        + ',' + Integer.toString(this.b) + //
        ")=" + Integer.toString(this.h); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public boolean equals(final Object o) {
    final PriorityEdge e;
    if (o == this) {
      return true;
    }
    if (o instanceof PriorityEdge) {
      e = ((PriorityEdge) o);
      return ((e.a == this.a) && (e.b == this.b) && (e.h == this.h));
    }
    return false;
  }

  /**
   * Copy the data from priority edge {@code e} into this edge
   *
   * @param e
   *          the edge to copy
   */
  public void assign(final PriorityEdge e) {
    this.a = e.a;
    this.b = e.b;
    this.h = e.h;
  }

  /** {@inheritDoc} */
  @Override
  public void assign(final Edge e) {
    if (e instanceof PriorityEdge) {
      this.assign((PriorityEdge) e);
    } else {
      this.a = e.a;
      this.b = e.b;
      this.h = 0;
    }
  }

  /** {@inheritDoc} */
  @Override
  public int hashCode() {
    return HashUtils.combineHashes(
        HashUtils.combineHashes(this.a, this.b), this.h);
  }
}
