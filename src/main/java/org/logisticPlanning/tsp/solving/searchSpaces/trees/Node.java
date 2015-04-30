package org.logisticPlanning.tsp.solving.searchSpaces.trees;

import java.util.Arrays;

/**
 * A class which represents tree nodes. Trees are the search spaces of
 * Standard Genetic Programming (SGP). The node class here could, however,
 * also be used as problem space (in SGP, search and problem space are the
 * same) for a different search space. Our node class here supports
 * strongly-typed GP. In other words, we can define which type of node is
 * allowed as child for which other node.
 * 
 * @param <CT>
 *          the child node type
 */
public class Node<CT extends Node<CT>> implements Cloneable {
  /** no children */
  public static final Node<?>[] EMPTY_CHILDREN = new Node[0];

  /** the children */
  Node<?>[] children;

  /** the node type record */
  private final NodeType<? extends CT, CT> type;

  /** the tree weight */
  private int weight;

  /** the maximum depth of all children */
  private int height;

  /**
   * Create a node with the given children
   * 
   * @param pchildren
   *          the child nodes
   * @param in
   *          the node type record
   */
  @SuppressWarnings("unchecked")
  public Node(final Node<?>[] pchildren,
      final NodeType<? extends CT, CT> in) {
    super();

    this.children = (((pchildren != null) && (pchildren.length > 0)) ? pchildren
        : ((CT[]) Node.EMPTY_CHILDREN));

    this.type = in;
    this.computeParams();
  }

  /**
   * Compute the fixed parameters of this node. This method is called
   * whenever a reproduced copy of the node is created or when a new node
   * is created with the constructor.
   */
  protected void computeParams() {
    int i, w, h;
    Node<?>[] x;
    Node<?> v;

    x = this.children;

    w = 1;
    h = 0;
    for (i = x.length; (--i) >= 0;) {
      v = x[i];
      w += v.weight;
      h = Math.max(h, v.height);
    }
    this.weight = w;
    this.height = (h + 1);
  }

  /**
   * Get the number of children
   * 
   * @return the number of children
   */
  public final int size() {
    return this.children.length;
  }

  /**
   * Get a specific child
   * 
   * @param idx
   *          the child index
   * @return the child at that index
   */
  @SuppressWarnings("unchecked")
  public final CT get(final int idx) {
    return ((CT) (this.children[idx]));
  }

  /**
   * Set the child at the given index and create a new tree with the child
   * at that index. As usual, we follow the paradigm that genotypes in the
   * search must not be modified. We just can create a modified copy of a
   * genotype. Hence, setting the child of a specific node object does not
   * change the node object itself, but results in a copy of the node
   * object where the child is set at the specific position.
   * 
   * @param child
   *          the child to be stored at the given index
   * @param idx
   *          the index where to insert the child
   * @return the new tree node with that child
   */
  public final CT setChild(final CT child, final int idx) {
    CT x;

    x = this.clone();
    x.children = x.children.clone();
    x.children[idx] = child;
    x.computeParams();
    return x;
  }

  /**
   * Clone this node
   * 
   * @return a copy of this node
   */
  @Override
  protected CT clone() {
    try {
      return ((CT) (super.clone()));
    } catch (final Throwable t) {
      return null;
    }
  }

  /**
   * Fill in the text associated with this node
   * 
   * @param sb
   *          the string builder
   */
  public void fillInText(final StringBuilder sb) {
    //
  }

  /**
   * Get the node type record associated with this node
   * 
   * @return the node type record associated with this node
   */
  public final NodeType<? extends CT, CT> getType() {
    return this.type;
  }

  /**
   * Get the string representation of this object, i.e., the name and
   * configuration.
   * 
   * @return the string version of this object
   */
  @Override
  public String toString() {
    StringBuilder sb;
    sb = new StringBuilder();
    this.fillInText(sb);
    return sb.toString();
  }

  /**
   * Obtain the 1+ total number of nodes in all sub-trees of this tree
   * 
   * @return the total number of nodes in this tree (including this node)
   */
  public final int getWeight() {
    return this.weight;
  }

  /**
   * Get the maximum depth of all child nodes
   * 
   * @return the maximum depth of all child nodes
   */
  public final int getHeight() {
    return this.height;
  }

  /**
   * Is this a terminal node?
   * 
   * @return true if this node is terminal, i.e., a leaf, false otherwise
   */
  public final boolean isTerminal() {
    return (this.weight <= 1);
  }

  /**
   * Compare with another object
   * 
   * @param o
   *          the other object
   * @return true if the objects are equal
   */
  @Override
  public boolean equals(final Object o) {
    if (o == null) {
      return false;
    }
    if (o == this) {
      return true;
    }
    if (o.getClass() == this.getClass()) {
      return Arrays.equals(this.children, ((Node<?>) o).children);
    }
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return super.hashCode();
  }
}
