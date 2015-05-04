package org.logisticPlanning.tsp.solving.searchSpaces.trees;

import java.lang.reflect.Constructor;
import java.util.Random;

/**
 * This node type is easy to use for all nodes which do not require some
 * explicit random initialization. They then can just be created via
 * reflection.
 *
 * @param <NT>
 *          the specific node type
 * @param <CT>
 *          the child node type (i.e., the general type where NT is an
 *          instance of)
 */
public class ReflectionNodeType<NT extends Node<CT>, CT extends Node<CT>>
extends NodeType<NT, CT> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the parameters */
  private final Object[] params;

  /** the constructor */
  private final Constructor<NT> constr;

  /**
   * Create a new node information record
   *
   * @param c
   *          the node class
   * @param ch
   *          the types of the possible chTypes
   */
  @SuppressWarnings("unchecked")
  public ReflectionNodeType(final Class<NT> c, final NodeTypeSet<CT>[] ch) {
    super(c.getSimpleName(), ch);

    Constructor<?>[] cs;
    Constructor<?> cx, cf;
    Class<?>[] p;
    int i, v;

    if (ch.length > 0) {
      this.params = new Object[] { null, this };
      v = 2;
    } else {
      this.params = new Object[] { this };
      v = 1;
    }

    cs = c.getConstructors();
    cf = null;
    if (cs != null) {
      looper: for (i = cs.length; (--i) >= 0;) {
        cx = cs[i];
        p = cx.getParameterTypes();
        if ((p != null) && (p.length == v)) {
          if (((v > 1) && (Node[].class.isAssignableFrom(p[0])))
              && NodeType.class.isAssignableFrom(p[v - 1])) {
            cf = cx;
            break looper;
          }
        }
      }
    }

    this.constr = ((Constructor<NT>) cf);
  }

  /**
   * Instantiate a node
   *
   * @param children
   *          a given set of children
   * @param r
   *          the randomizer
   * @return the new node
   */
  @Override
  public NT instantiate(final Node<?>[] children, final Random r) {
    Object[] x;

    x = this.params;
    if (x.length > 1) {
      this.params[0] = children;
    }
    try {
      return this.constr.newInstance(this.params);
    } catch (final Throwable t) {
      t.printStackTrace();
      return null;
    }
  }
}
