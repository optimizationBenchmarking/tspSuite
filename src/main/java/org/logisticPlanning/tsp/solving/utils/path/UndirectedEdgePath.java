package org.logisticPlanning.tsp.solving.utils.path;

import org.logisticPlanning.tsp.solving.utils.edge.Edge;

/**
 * An undirected edge path is a path composed of undirected edge path
 * elements
 */
public class UndirectedEdgePath {

  /** the path elements */
  private final EdgePathElement[] m_elements;

  /** the end element */
  private EdgePathElement m_end;

  /**
   * Create a new undirected edge path builder.
   *
   * @param n
   *          the number of nodes
   */
  public UndirectedEdgePath(final int n) {
    super();
    final EdgePathElement[] e;
    int i;

    this.m_elements = e = new EdgePathElement[n];
    for (i = n; (--i) >= 0;) {
      e[i] = new EdgePathElement(i + 1);
    }
    this.m_end = e[0];
  }

  /** clear this edge path */
  public final void clear() {
    for (final EdgePathElement e : this.m_elements) {
      e.clear();
    }
    this.m_end = this.m_elements[0];
  }

  /**
   * get one of the ends of the path
   *
   * @return one of the ends of the path
   */
  public final EdgePathElement getEnd() {
    return this.m_end;
  }

  /**
   * Try to add an edge to a path
   *
   * @param edge
   *          the edge to be added
   * @return {@code true} if the edge was added, {@code false} if not
   */
  public final boolean tryAddToPath(final Edge edge) {
    return this.tryAddToPath(this.m_elements[edge.a - 1],//
        this.m_elements[edge.b - 1]);
  }

  /**
   * Try to add an edge to a path
   *
   * @param a
   *          the first path element that we want to connect
   * @param b
   *          the second one that we want to connect
   * @return {@code true} if the edge was added, {@code false} if not
   */
  public final boolean tryAddToPath(final EdgePathElement a,
      final EdgePathElement b) {

    // we will now check if we can add the edge: an edge can be added if it
    // will not result in a node having a degree higher than 2 (a not can
    // have at most 2 incoming edges) AND if it will not lead to a cycle
    if (a.m_a == null) {
      // a has degree 0, i.e., no neighbor

      if (b.m_a == null) {
        // b also has degree 0, i.e., also no neighbor
        // ok, we could insert this link: both nodes have degree 0

        // so link them
        a.m_a = b; // a now has degree 1
        a.m_otherEnd = b;
        b.m_a = a; // b now has degree 1
        b.m_otherEnd = a;
        this.m_end = a;// a is our new path end
        return true;
      }

      // now we know: a has degree 0 and b has degree >= 1

      if (b.m_b != null) {// b has already degree 2: cannot use this edge
        return false;
      }

      // ok, a has degree 0 and b has degree 1, we can link them together:
      // if a has degree 0, no loop/cycle can occur by adding it to any
      // path which includes b

      a.m_a = b; // a now has degree 1
      a.m_otherEnd = b.m_otherEnd;
      b.m_b = a; // b now has degree 2
      b.m_otherEnd.m_otherEnd = a;
      this.m_end = a;// a is our new path end
      return true;
    }

    // a has at least degree 1, as a.m_a != null
    if (a.m_b != null) {// a has already degree 2: cannot use this edge
      return false;
    }

    // so we know: a has degree 1 (as it has exactly one neighbor)

    if (b.m_a == null) {
      // a has degree 1 and b has degree 0: no cycle can occur if we add b
      // to
      // any path containing a

      a.m_b = b; // a now has degree 2
      a.m_otherEnd.m_otherEnd = b;// was a, now is b
      b.m_a = a; // be now has degree 1
      this.m_end = b.m_otherEnd = a.m_otherEnd;// set the new end
      return true;
    }

    if (b.m_b != null) {// b has degree 2: cannot use this edge
      return false;
    }

    // b has degree 1
    if (b == a.m_otherEnd) {// we would get a cycle!
      return false;
    }

    // ok, no cycle possible

    // we can close the connection
    a.m_b = b;// a now has degree 2
    a.m_otherEnd.m_otherEnd = b.m_otherEnd;
    b.m_b = a;// b now has degree 2
    this.m_end = b.m_otherEnd.m_otherEnd = a.m_otherEnd;// set the new end
    // neither a nor b can take part in any other connection, so their
    // m_otherEnd variables do not matter anymore
    return true;
  }

  /**
   * Check if we can add an edge to the path.
   *
   * @param a
   *          the first path element that we want to connect
   * @param b
   *          the second one that we want to connect
   * @return {@code true} if the edge between them can be added,
   *         {@code false} otherwise
   */
  private final boolean testAddToPath(final EdgePathElement a,
      final EdgePathElement b) {

    // we will now check if we can add the edge: an edge can be added if it
    // will not result in a node having a degree higher than 2 (a not can
    // have at most 2 incoming edges) AND if it will not lead to a cycle
    if (a.m_a == null) {
      // a has degree 0, i.e., no neighbor

      if (b.m_a == null) {
        // b also has degree 0, i.e., also no neighbor

        return true;// ok, we could insert this link: both nodes have
        // degree 0
      }

      // now we know: a has degree 0 and b has degree >= 1

      if (b.m_b != null) {// b has already degree 2: cannot use this edge
        return false;
      }

      // ok, a has degree 0 and b has degree 1, we can link them together:
      // if a has degree 0, no loop/cycle can occur by adding it to any
      // path which includes b
      return true;
    }

    // a has at least degree 1, as a.m_a != null
    if (a.m_b != null) {// a has already degree 2: cannot use this edge
      return false;
    }

    // so we know: a has degree 1 (as it has exactly one neighbor)

    if (b.m_a == null) {
      // a has degree 1 and b has degree 0: no cycle can occur if we add b
      // to
      // any path containing a
      return true;
    }

    if (b.m_b != null) {// b has degree 2: cannot use this edge
      return false;
    }

    // b has degree 1
    if (b == a.m_otherEnd) {// we would get a cycle!
      return false;
    }

    return true; // ok, no cycle possible
  }

  /**
   * find (another) candidate to augment this edge path
   *
   * @param end
   *          the end that should be augmented
   * @param last
   *          the last candidate
   * @return the next candidate, or {@code null} if none found
   */
  public final EdgePathElement findNextAugmentationCandidate(
      final EdgePathElement end, final EdgePathElement last) {
    int start;
    final EdgePathElement[] path;
    EdgePathElement p;

    path = this.m_elements;
    start = ((last != null) ? (last.node - 1) : path.length);

    for (; (--start) >= 0;) {
      p = path[start];
      if (p == end) {
        continue;
      }

      if (this.testAddToPath(end, p)) {
        return p;
      }
    }

    return null;
  }
}
