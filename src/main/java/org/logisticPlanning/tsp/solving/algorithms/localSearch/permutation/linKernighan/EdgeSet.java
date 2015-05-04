package org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.linKernighan;

import java.util.Arrays;

/**
 * An edge set allowing for O(1) adding and deleting of edges.
 */
public class EdgeSet {

  /** the number of edges currently stored */
  int count;

  /** the data */
  public int[][] data;

  /** the number of nodes */
  private final int n;

  /** a temporary storage for visited nodes */
  private final boolean[] visited;

  /**
   * Create the edge set
   *
   * @param maxEdgesPerNode
   *          the maximum edges per node
   * @param nn
   *          the total number of nodes
   */
  EdgeSet(final int maxEdgesPerNode, final int nn) {
    super();
    this.count = 0;
    this.n = nn;
    this.data = new int[maxEdgesPerNode][nn];
    this.visited = new boolean[nn];
  }

  /**
   * Get the edges incident to a given node
   *
   * @param a
   *          the node
   * @param out
   *          the array to receive the edges
   * @return the number of edges stored in the array
   */
  final int getEdges(final int a, final int[] out) {
    int e, ai;

    e = 0;

    ai = (a - 1);
    for (final int[] ar : this.data) {
      if (ar[ai] != 0) {
        out[e++] = a;
        out[e++] = ar[ai];
      }
    }

    // Arrays.fill(out, e, out.length, 0);
    return (e >>> 1);
  }

  /**
   * Add an edge
   *
   * @param a
   *          the first node of the edge
   * @param b
   *          the second node of the edge
   */
  final void addEdge(final int a, final int b) {
    int x;

    this.count++;

    x = (a - 1);
    for (final int[] arr : this.data) {
      if (arr[x] == 0) {
        arr[x] = b;
        break;
      }
    }

    x = (b - 1);
    for (final int[] arr : this.data) {
      if (arr[x] == 0) {
        arr[x] = a;
        break;
      }
    }
  }

  /**
   * Delete an edge if it exists
   *
   * @param a
   *          the first node of the edge
   * @param b
   *          the second node of the edge
   * @return {@code true} if and only if the edge existed in the edge set
   */
  final boolean deleteEdgeIfExists(final int a, final int b) {
    int x;
    boolean f;

    f = false;
    x = (a - 1);
    for (final int[] arr : this.data) {
      if (arr[x] == b) {
        arr[x] = 0;
        f = true;
        break;
      }
    }

    if (f) {
      x = (b - 1);
      for (final int[] arr : this.data) {
        if (arr[x] == a) {
          arr[x] = 0;
          this.count--;
          return true;
        }
      }
    }

    return false;
  }

  /**
   * Check if a given edge exists in the edge set
   *
   * @param a
   *          the first node of the edge
   * @param b
   *          the second node of the edge
   * @return {@code true} if and only if the edge exists
   */
  final boolean hasEdge(final int a, final int b) {
    int ai;

    // if the a == 0 and b == 0 we just return true to show the edge is not
    // ok
    // to add or deleted
    if ((a <= 0) || (a > this.n)) {
      return true;
    }

    ai = (a - 1);
    // System.out.println(ai);
    for (final int[] edges : this.data) {
      if (edges[ai] == b) {
        return true;
      }
    }

    return false;
  }

  /**
   * Load a candidate solution in path represent into this edge set
   *
   * @param sol
   *          the solution
   */
  final void fromPathRepresentation(final int[] sol) {
    int last, i;
    int[][] edges;

    edges = this.data;
    last = (sol[sol.length - 1]);
    for (final int cur : sol) {
      edges[0][cur - 1] = last;
      edges[1][last - 1] = cur;
      last = cur;
    }

    this.count = sol.length;

    for (i = edges.length; (--i) > 1;) {
      Arrays.fill(edges[i], 0);
    }
  }

  /**
   * Transform the edge set to a candidate solution if it represents a tour
   *
   * @param dest
   *          the destination individual
   * @return {@code true} if and only if the edge set represents a tour
   */
  final boolean toPathRepresentation(final int[] dest) {
    final boolean[] visi;
    int i, ai, current;

    if (this.count != dest.length) {
      return false;
    } // !=

    visi = this.visited;
    Arrays.fill(visi, false);

    current = 1;
    dest[0] = 1;
    i = 1;
    while (i < dest.length) {
      ai = (current - 1);
      visi[ai] = true;
      inner: {
        for (final int[] arr : this.data) {
          current = arr[ai];
          if ((dest.length >= current) && (current > 0)
              && (!this.visited[current - 1])) {
            break inner;
          }
        }
        return false;
      }
      dest[i++] = current;
    }
    return true;
  }

  /**
   * copy an edge set
   *
   * @param src
   *          the edge set
   */
  final void copyOf(final EdgeSet src) {
    this.count = src.count;
    // copy data
    // System.out.println(this.data.length);
    for (int i = 0; i < this.data.length; i++) {
      System.arraycopy(src.data[i], 0, this.data[i], 0,
          this.data[i].length);
    }

  }

  /** clear the edge set */
  final void clear() {
    this.count = 0;
    for (final int[] d : this.data) {
      Arrays.fill(d, 0);
    }
  }

  // /** print the edge set */
  // final void printEdgeSet() {
  // for(int i=0;i<this.data.length;i++){
  // System.out.println("");
  // for(int j=0;j<this.data[i].length;j++){
  // System.out.print(this.data[i][j]+",");
  // }
  // }
  // System.out.println("");
  // }

}
