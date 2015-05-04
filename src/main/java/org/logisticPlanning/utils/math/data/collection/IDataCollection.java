package org.logisticPlanning.utils.math.data.collection;

/**
 * a point view
 */
public interface IDataCollection {

  /**
   * get the number of points in this view
   *
   * @return the number of points in this view
   */
  public abstract int size();

  /**
   * get the dimension of this view
   *
   * @return the dimension
   */
  public abstract int dimension();

  /**
   * Get the value of the given dimension of the point index
   *
   * @param point
   *          the point
   * @param dimension
   *          the dimension
   * @return the value of the point and dimension
   */
  public abstract double get(final int point, final int dimension);
}
