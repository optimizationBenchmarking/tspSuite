package org.logisticPlanning.utils.math.data.point;

import org.logisticPlanning.utils.utils.EmptyUtils;

/**
 * a point accessing data from an array
 */
public class ArrayPoint extends MutablePoint {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the array */
  private final double[] m_array;

  /**
   * create
   *
   * @param array
   *          the array
   */
  public ArrayPoint(final double[] array) {
    super();
    this.m_array = ((array.length <= 0) ? EmptyUtils.EMPTY_DOUBLES : array);
  }

  /** {@inheritDoc} */
  @Override
  public final Point clone() {
    final double[] old;

    old = this.m_array;

    switch (old.length) {
      case 1: {
        return new Point1D(old[0]);
      }
      case 2: {
        return new Point2D(old[0], old[1]);
      }
      case 3: {
        return new Point3D(old[0], old[1], old[2]);
      }
      default: {
        return new ArrayPoint(old.clone());
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void toArray(final double[] dest, final int destStart) {
    System
        .arraycopy(this.m_array, 0, dest, destStart, this.m_array.length);
  }

  /** {@inheritDoc} */
  @Override
  public final double get(final int dimension) {
    return this.m_array[dimension];
  }

  /** {@inheritDoc} */
  @Override
  public final void set(final int dimension, final double value) {
    this.m_array[dimension] = value;
  }

  /** {@inheritDoc} */
  @Override
  public final void fromArray(final double[] src, final int srcStart) {
    System.arraycopy(src, srcStart, this.m_array, 0, this.m_array.length);
  }

  /** {@inheritDoc} */
  @Override
  public final int dimension() {
    return this.m_array.length;
  }

}
