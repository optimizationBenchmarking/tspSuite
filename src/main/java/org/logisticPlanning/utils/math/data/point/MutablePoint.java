package org.logisticPlanning.utils.math.data.point;

import org.logisticPlanning.utils.math.data.collection.IDataCollection;

/**
 * A point that can be modified.
 */
public abstract class MutablePoint extends Point {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** instantiate */
  protected MutablePoint() {
    super();
  }

  /**
   * Set the value at dimension {@code dimension}.
   * 
   * @param dimension
   *          the dimension
   * @param value
   *          the value
   */
  public abstract void set(final int dimension, final double value);

  /**
   * Copy the values of another point into this one
   * 
   * @param p
   *          the point to copy
   */
  public void assign(final Point p) {
    int i;

    i = p.dimension();
    if (i != this.dimension()) {
      throw new IllegalArgumentException(Point.DIMENSION_ERROR + i);
    }
    for (; (--i) >= 0;) {
      this.set(i, p.get(i));
    }
  }

  /**
   * load the data of this point from an array
   * 
   * @param src
   *          the source array
   * @param srcStart
   *          the starting index where the loading should begin
   */
  public void fromArray(final double[] src, final int srcStart) {
    int i, e;

    i = this.dimension();
    e = (srcStart + i);
    for (; (--i) >= 0;) {
      this.set(i, src[--e]);
    }
  }

  /**
   * Load the values of this point from a data collection
   * 
   * @param data
   *          the data collection
   * @param index
   *          the index
   */
  public void fromDataCollection(final IDataCollection data,
      final int index) {
    int i;

    i = data.dimension();
    if (i != this.dimension()) {
      throw new IllegalArgumentException(Point.DIMENSION_ERROR + i);
    }
    for (; (--i) >= 0;) {
      this.set(i, data.get(index, i));
    }
  }
}
