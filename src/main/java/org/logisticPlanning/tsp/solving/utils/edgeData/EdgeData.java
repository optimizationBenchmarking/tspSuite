package org.logisticPlanning.tsp.solving.utils.edgeData;

import java.io.Serializable;

/**
 * <p>
 * The base class for stuff that holds per-edge data.
 * </p>
 *
 *
 @author <em><a href="http://www.it-weise.de/">Thomas Weise</a></em>,
 *         Email:&nbsp;<a
 *         href="mailto:tweise@ustc.edu.cn">tweise@ustc.edu.cn</a>
 */
public abstract class EdgeData implements Serializable {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the number of nodes */
  protected final int m_n;

  /**
   * create
   *
   * @param n
   *          the number of nodes
   */
  protected EdgeData(final int n) {
    super();
    this.m_n = n;
  }

  /**
   * Get the number of nodes this data record is good for
   *
   * @return the number of nodes
   */
  public final int n() {
    return this.m_n;
  }

  /**
   * Is this edge data instance symmetric?
   *
   * @return {@code true} if the data map is symmetric, {@code false}
   *         otherwise
   */
  public abstract boolean isSymmetric();

  /**
   * Clear all data: set all edge values to {@code 0}.
   */
  public abstract void clear();

}
