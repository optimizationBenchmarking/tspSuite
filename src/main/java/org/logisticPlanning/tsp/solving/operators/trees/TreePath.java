package org.logisticPlanning.tsp.solving.operators.trees;

import java.util.Random;

import org.logisticPlanning.tsp.solving.searchSpaces.trees.Node;

/**
 * This class allows us to select a path in a tree. When applying a
 * reproduction operation, it is not only necessary to find a tree node
 * within the tree, but also to find the complete path to this node. This
 * class allows us to perform such a selection. It also allows us to
 * replace the end node of such a path, hence creating a completely new
 * tree: Since genotypes must not directly be changed (because of possible
 * side-effects if an individual is selected more than once), all
 * modifications (such as the replacement of a node) will lead to the
 * creation of new trees.
 *
 * @param <NT>
 *          the node type
 */
public class TreePath<NT extends Node<NT>> {

  /** the path */
  private NT[] path;

  /** the path indexes */
  private int[] pathidx;

  /** the length */
  private int len;

  /** Create a tree path */
  @SuppressWarnings("unchecked")
  public TreePath() {
    super();
    this.path = ((NT[]) (new Node[16]));
    this.pathidx = new int[16];
  }

  /**
   * Get the path length
   *
   * @return the path length
   */
  public final int size() {
    return this.len;
  }

  /**
   * Get the element at the specified index
   *
   * @param index
   *          the index
   * @return the element
   */
  public final NT get(final int index) {
    return this.path[index];
  }

  /**
   * Get the index of the next child in the path
   *
   * @param index
   *          the index of the current parent
   * @return the index of the next child in the path
   */
  public final int getChildIndex(final int index) {
    return this.pathidx[index];
  }

  /**
   * Create a random path through the tree. Each node in the tree is
   * selected with exactly the same probability.
   *
   * @param node
   *          the node
   * @param r
   *          the randomizer
   */
  public final void randomPath(final NT node, final Random r) {
    int i, w, w2;
    NT cur, next;

    this.len = 0;
    cur = node;
    w = r.nextInt(node.getWeight());

    for (;;) {

      if (w <= 0) {
        this.addPath(cur, -1);
        return;
      }

      // iterate over the children
      innerLoop: for (i = cur.size(); (--i) >= 0;) {
        next = cur.get(i);

        w2 = next.getWeight();
        if (w2 >= w) {
          this.addPath(cur, i);
          cur = next;
          break innerLoop;
        }
        w -= w2;
      }

      // take account for the currently selected node
      w--;
    }
  }

  /**
   * An internal method used to add an element to the path.
   *
   * @param node
   *          the node
   * @param pi
   *          the parent index
   */
  @SuppressWarnings("unchecked")
  private final void addPath(final NT node, final int pi) {
    NT[] ppp;
    int l;
    int[] idx;

    ppp = this.path;
    idx = this.pathidx;
    l = this.len;

    if (l >= ppp.length) {
      ppp = (NT[]) (new Node[l << 1]);
      System.arraycopy(this.path, 0, ppp, 0, l);
      this.path = ppp;

      idx = new int[l << 1];
      System.arraycopy(this.pathidx, 0, idx, 0, l);
      this.pathidx = idx;
    }

    idx[l] = pi;
    ppp[l++] = node;

    this.len = l;
  }

  /**
   * Replace the end of this path with the new node. Since tree nodes are
   * immuatable, this will result in the creation of a completely new tree
   * and a new root node. The path is updated while this operation is
   * performed, i.e., it is still valid afterwards.
   *
   * @param newNode
   *          the new node
   * @return the result new root node of the path
   */
  public final NT replaceEnd(final NT newNode) {
    NT[] ppp;
    int l;
    int[] idx;
    NT x;

    l = this.len;
    ppp = this.path;
    idx = this.pathidx;
    x = newNode;

    l = this.len;
    ppp[--l] = x;
    for (; (--l) >= 0;) {
      ppp[l] = x = ppp[l].setChild(x, idx[l]);
    }

    return x;
  }
}
