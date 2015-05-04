package org.logisticPlanning.tsp.solving.utils.edge;

import java.util.Arrays;

/**
 * This class can manage a list of edges that are connected to each node.
 * The list may contain at most {@link #m_neighborListLength} edges per
 * node. These edges are evaluated based on their heuristic value {@code h}
 * . If the capacity for a given node is reached and a new edge is tried to
 * be checked in, it may only enter the nodes edge list if it has a better
 * (lower) heuristic value. Node indices must be one-based.
 *
 * @param <ET>
 *          the edge type
 */
public class UndirectedPriorityEdgeNeighborList<ET extends PriorityEdge> {

  /** the edges **/
  private final ET[] m_edges;

  /** the neighbor list length */
  private final int m_neighborListLength;

  /** the number of edges we have */
  private final int[] m_sizes;

  /**
   * <p>
   * Create a new priority neighbor list for undirected edges. Instances of
   * this class allow you to build a list of the at most
   * {@link #m_neighborListLength} best edges incident to each node and
   * obtain a globally sorted version of that list.
   * </p>
   *
   * @param n
   *          the number of nodes
   * @param neighborListLength
   *          the neighbor list length for each node
   */
  public UndirectedPriorityEdgeNeighborList(final int n,
      final int neighborListLength) {
    this.m_neighborListLength = neighborListLength;
    this.m_edges = this.createArray(n * neighborListLength);
    this.m_sizes = new int[n];
  }

  /**
   * get the number of nodes this neighbor list can manage
   *
   * @return the number of nodes this neighbor list can manage
   */
  public final int n() {
    return this.m_sizes.length;
  }

  /**
   * get the neighbor list length
   *
   * @return the neighbor list length
   */
  public final int getNeighborListLength() {
    return this.m_neighborListLength;
  }

  /** clear this edge list */
  public final void clear() {
    Arrays.fill(this.m_edges, null);
    Arrays.fill(this.m_sizes, 0);
  }

  /**
   * factory method to create a new edge
   *
   * @return the new edge
   */
  @SuppressWarnings("unchecked")
  protected ET createEdge() {
    return ((ET) (new PriorityEdge()));
  }

  /**
   * factory method to create a new edge array
   *
   * @param length
   *          the length of the new array
   * @return the new edge array
   */
  @SuppressWarnings("unchecked")
  protected ET[] createArray(final int length) {
    return ((ET[]) (new PriorityEdge[length]));
  }

  /**
   * <p>
   * Try to check in an edge. An
   * {@link org.logisticPlanning.tsp.solving.utils.edge.PriorityEdge edge}
   * in this context is an undirected edge with a priority. It is defined
   * by its two end nodes {@code a} and {@code b} and a priority {@code h}.
   * </p>
   * <p>
   * The lower the priority value {@code h}, the higher the chance of an
   * edge to actually enter the list: For each node, we keep at most
   * {@link #m_neighborListLength} neighboring edges and if that many edges
   * have been entered for a node, new edges for that node may only enter
   * if they have a lower {@code h} value than another edge of that node,
   * i.e., if they can lead to the deletion of another edge.
   * </p>
   * <p>
   * However, this function makes use of the feature that we have
   * {@link #m_neighborListLength} slots for each node, but undirected
   * edges. Here, each edge has two chances to enter the list, for each of
   * its incident nodes. Of course, we only store each edge at most once.
   * Hence, the effective capacity of the list may be up to twice as high.
   * </p>
   *
   * @param a
   *          the first end
   * @param b
   *          the second end
   * @param h
   *          the heuristic value
   * @return the edge object (if an edge was inserted), or {@code null} if
   *         {@code h} was too big
   */
  public final ET checkIn(final int a, final int b, final int h) {
    final int[] sizes;
    final ET[] edges;
    int y, slot, size, fromIndex, toIndex, low, high, mid;
    ET use, midEdge;

    sizes = this.m_sizes;
    edges = this.m_edges;

    // We will try to push this into both neighbor lists, the list for node
    // a
    // and the list for node b.
    outer: for (y = 2; y > 0; y--) {

      slot = (((y == 2) ? a : b) - 1);// choose one of the two
      // lists
      size = sizes[slot]; // how many nodes are in it?
      fromIndex = (slot * this.m_neighborListLength);

      if (size < this.m_neighborListLength) {// ok, we still have some
        // free
        // slots
        edges[fromIndex + (size++)] = use = this.createEdge();
        sizes[slot] = size;
        if (size >= this.m_neighborListLength) { // ok, we just filled
          // that
          // list
          // So far, that list was not sorted. It won't be sorted if
          // it is not
          // filled up, but this does not matter, as we will sort the
          // global
          // list anyway before popping edges. Anyway, now we will
          // sort it
          use.h = h; // assign h - necessary for sorting
          Arrays
          .sort(edges, fromIndex, fromIndex
              + this.m_neighborListLength,
              PriorityEdgeComparator.INSTANCE);
        }
      } else {
        // Ok, there was no free slot: try to find one by using binary
        // search.

        low = fromIndex;
        toIndex = (fromIndex + this.m_neighborListLength);
        high = (toIndex - 1);
        mid = high;

        // use binary search starting at the end of the list (mid=high)
        // since
        // we assume that there are many more edges than nls and that
        // most of
        // them will be longer than than the top nls edges. hence,
        // starting
        // at the end may save a few steps...
        search: {
          while (low <= high) {
            midEdge = edges[mid];
            if (midEdge.h < h) {
              low = (mid + 1);
            } else {
              if (midEdge.h > h) {
                high = (mid - 1);
              } else {
                break search;
              }
            }

            mid = ((low + high) >>> 1);
          }
        }

        if (low >= toIndex) {// the edge was too long (in heuristic
          // terms)
          continue outer;
        }

        // ok, there is at least one worse edge
        use = edges[toIndex - 1]; // we re-use the last (worst) edge in
        // the
        // list

        // and shift back the rest in a fast arraycopy move
        System.arraycopy(edges, low, edges, low + 1, toIndex - low - 1);

        edges[low] = use;// put the edge where it belongs
      }

      // store data
      use.a = a; // start of edge
      use.b = b; // end of edge
      use.h = h; // heuristic value

      // if we have inserted the edge, it makes no sense to insert it a
      // second time in the other neighbor list - we can safely return
      return use;
    }

    return null;
  }

  /**
   * <p>
   * Obtain the sorted array of edges. After invoking this operation, you
   * must not call {@link #checkIn(int, int, int)} anymore. This operation
   * will sort the internal array and thus, destroy all node-index/edge
   * relationships.
   * </p>
   * <p>
   * Hence, this method is called when we have completed compiling list of
   * neighboring edges. So let us sort them and get the best edges to the
   * front. Notice that each node's neighbor list is already sorted, so
   * sorting the overall list should be quite fast (see the documentation
   * of {@link java.util.Arrays#sort(Object[], java.util.Comparator)
   * Arrays.sort} ).
   * </p>
   * <p>
   * If some of the neighbor lists were not filled, there are {@code null}
   * elements at the end of the array.
   * </p>
   *
   * @return the sorted edge array
   */
  public final ET[] getEdgeArray() {
    Arrays.sort(this.m_edges, PriorityEdgeComparator.INSTANCE);
    return this.m_edges;
  }
}
