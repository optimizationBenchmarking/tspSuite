package org.logisticPlanning.tsp.solving.utils;

import java.util.Arrays;

import org.logisticPlanning.tsp.solving.operators.permutation.creation.PermutationCreateCanonical;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * This is an utility class that can keep track on nodes that have been
 * used in a permutation. A {@link NodeManager} manages a set of node IDs.
 * You can check in O(1) if a given ID is in the set / available and you
 * can delete node IDs in O(1).
 */
public final class NodeManager {

  /** the remaining node ids */
  private transient int[] m_nodes;

  /** the node positions */
  private transient int[] m_positions;

  /** the number of nodes */
  private transient int m_n;

  /** instantiate the node manager */
  public NodeManager() {
    super();
  }

  /**
   * initialize the node manager
   * 
   * @param n
   *          the number of nodes
   */
  public final void init(final int n) {
    int[] x;

    this.m_n = n;

    x = this.m_positions;
    if ((x == null) || (x.length < n)) {
      this.m_nodes = PermutationCreateCanonical.canonical(n);
      this.m_positions = x = new int[n];
    } else {
      PermutationCreateCanonical.makeCanonical(this.m_nodes, n);
    }
    PermutationCreateCanonical.makeCanonicalZero(x, n);
  }

  /**
   * initialize by providing a set of ids
   * 
   * @param ids
   *          the id array
   * @param start
   *          the start index
   * @param count
   *          the count
   */
  public final void init(final int[] ids, final int start, final int count) {
    final int n;
    int[] nodes, pos;
    int i, j, node;

    nodes = this.m_nodes;
    n = ids.length;
    if ((nodes == null) || (nodes.length < n)) {
      this.m_nodes = nodes = new int[n];
      this.m_positions = pos = new int[n];
    } else {
      pos = this.m_positions;
    }
    Arrays.fill(pos, 0, n, -1);

    this.m_n = count;
    for (i = start, j = count; (--j) >= 0; i++) {
      if (i >= n) {
        i = 0;
      }
      node = ids[i];
      nodes[j] = node;
      pos[node - 1] = j;
    }
  }

  /**
   * Delete a node by its id.
   * 
   * @param id
   *          the node's id
   */
  public final void deleteByID(final int id) {
    final int pos, replace, size, idx;
    final int[] positions, nodes;

    positions = this.m_positions;
    nodes = this.m_nodes;
    size = (--this.m_n);

    idx = (id - 1);
    pos = positions[idx];

    if (size > pos) {
      replace = nodes[size];
      nodes[pos] = replace;
      positions[replace - 1] = pos;
    }

    positions[idx] = (-1);
  }

  /**
   * Delete a the last node and return its id. This method depends on the
   * potentially random state of the node manager. It should best only be
   * used if only a single node is left in the node manager.
   * 
   * @return the id of the deleted node
   */
  public final int deleteLast() {
    final int /* pos, replace, */size, id;
    final int[] positions, nodes;

    positions = this.m_positions;
    nodes = this.m_nodes;
    size = (--this.m_n);

    id = nodes[size];

    // pos = positions[id];
    //
    // if (size > pos) {
    // replace = nodes[size];
    // nodes[pos] = replace;
    // positions[replace - 1] = pos;
    // }

    positions[id - 1] = (-1);
    return id;
  }

  /**
   * Check if the node has not yet been deleted
   * 
   * @param n
   *          the node's id
   * @return {@code true} if the node has not yet been deleted
   */
  public final boolean isIDAvailable(final int n) {
    return (this.m_positions[n - 1] >= 0);
  }

  /**
   * Choose a node randomly and delete and return it.
   * 
   * @param r
   *          the randomizer
   * @return the randomly chosen (and deleted) node
   */
  public final int deleteRandom(final Randomizer r) {
    final int replace, pos, id;
    int size;
    final int[] positions, nodes;

    size = this.m_n;
    pos = r.nextInt(size);
    this.m_n = (--size);

    positions = this.m_positions;
    nodes = this.m_nodes;

    id = nodes[pos];

    if (size > pos) {
      replace = nodes[size];
      nodes[pos] = replace;
      positions[replace - 1] = pos;
    }

    positions[id - 1] = (-1);

    return id;
  }

  /**
   * get the number of remaining nodes
   * 
   * @return the number of remaining nodes
   */
  public final int size() {
    return this.m_n;
  }

  /**
   * Is the node manager empty?
   * 
   * @return {@code true} if no more nodes can be obtained, {@code false}
   *         if there are still nodes
   */
  public final boolean isEmpty() {
    return (this.m_n <= 0);
  }

  /**
   * get the remaining node at the given index
   * 
   * @param i
   *          the node index
   * @return the node id
   */
  public final int getByIndex(final int i) {
    return this.m_nodes[i];
  }
}
