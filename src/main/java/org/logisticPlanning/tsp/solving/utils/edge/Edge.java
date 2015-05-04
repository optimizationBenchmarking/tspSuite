package org.logisticPlanning.tsp.solving.utils.edge;

import org.logisticPlanning.utils.utils.HashUtils;

/**
 * This class represents an edge. It holds a {@link #a start node id} and
 * an {@link #b end node id} and nothing else. The {@link #hashCode()} and
 * {@link #equals(Object)} routines are implemented as for
 * <em>directed</em> edges, i.e., {@code (a, b)} does not equal to
 * {@code (b, a)} and also has a different hash code.
 */
public class Edge {

  /** the node id a: one end of the edge */
  public int a;

  /** the node id b: the other end of the edge */
  public int b;

  /** instantiate */
  public Edge() {
    this(-1, -1);
  }

  /**
   * Create an edge with the given nodes.
   *
   * @param pa
   *          the first node
   * @param pb
   *          the second node
   */
  public Edge(final int pa, final int pb) {
    super();
    this.a = pa;
    this.b = pb;
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return '(' + Integer.toString(this.a) + ',' + Integer.toString(this.b)
        + ')';
  }

  /** {@inheritDoc} */
  @Override
  public boolean equals(final Object o) {
    final Edge e;
    if (o == this) {
      return true;
    }
    if (o instanceof Edge) {
      e = ((Edge) o);
      return ((e.a == this.a) && (e.b == this.b));
    }
    return false;
  }

  /**
   * Copy the data from edge {@code e} into this edge
   *
   * @param e
   *          the edge to copy
   */
  public void assign(final Edge e) {
    this.a = e.a;
    this.b = e.b;
  }

  /** {@inheritDoc} */
  @Override
  public int hashCode() {
    return HashUtils.combineHashes(this.a, this.b);
  }
}
