package org.logisticPlanning.tsp.solving.utils.path;

/**
 * An element of an undirected edge path. Each such element represents a
 * node and may be connected to up to two other nodes.
 */
public final class EdgePathElement {

  /** the node id */
  public final int node;

  /** the first neighbor of the node */
  EdgePathElement m_a;

  /** the second neighbor of the node */
  EdgePathElement m_b;

  /** the other end of the path */
  EdgePathElement m_otherEnd;

  /** has this node already been visited? (when constructing the solution) */
  boolean m_visited;

  /**
   * create
   * 
   * @param nodeID
   *          the node id
   */
  public EdgePathElement(final int nodeID) {
    super();
    this.node = nodeID;
    this.m_otherEnd = this; // point the other end to this! (probably
    // unnecessary, but why not)
  }

  /** clear this path element */
  public final void clear() {
    this.m_otherEnd = this;
    this.m_a = null;
    this.m_b = null;
    this.m_visited = false;
  }

  /**
   * Get the next element of the path, or {@code null} if the end was
   * reached. This method must only be used when serializing the
   * constructed path to a permutation (candidate solution), as it will
   * modify the internal state of the path.
   * 
   * @return the next element of the path, or {@code null} if the end was
   *         reached
   */
  public final EdgePathElement next() {
    EdgePathElement e;

    this.m_visited = true;
    e = this.m_a;
    if (e.m_visited) {
      e = this.m_b;
      if (e == null) {
        return null;
      }
    }
    return e;
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    StringBuilder sb;

    sb = new StringBuilder(50);
    sb.append(this.node);
    if (this.m_a != null) {
      sb.append(" -> ("); //$NON-NLS-1$
      sb.append(this.m_a.node);
      if (this.m_b != null) {
        sb.append(", ");//$NON-NLS-1$
        sb.append(this.m_b.node);
      }
      sb.append(')');
    }
    return sb.toString();
  }
}
