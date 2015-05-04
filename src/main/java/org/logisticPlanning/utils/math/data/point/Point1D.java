package org.logisticPlanning.utils.math.data.point;

import org.logisticPlanning.utils.math.data.collection.IDataCollection;

/**
 * A modifiable point holding a single 1-dimensional (x) tuple.
 */
public class Point1D extends MutablePoint {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the index of the x-coordinate */
  public static final int COORD_X = 0;

  /** the x-value @serial serialized as {@code double} value */
  private double m_x;

  /**
   * create the 1-dimensional point
   *
   * @param x
   *          the value of the x-coordinate
   */
  public Point1D(final double x) {
    super();
    this.m_x = x;
  }

  /**
   * Create the 1-dimensional point with {@link java.lang.Double#NaN NaN}
   * values in all fields
   */
  public Point1D() {
    this(Double.NaN);
  }

  /** create the 1-dimensional point */
  /**
   * Get the x-value of this point
   *
   * @return the x-value of this point
   */
  public final double getX() {
    return this.m_x;
  }

  /**
   * Set the x-value of this point
   *
   * @param x
   *          the new x-value for this point
   */
  public final void setX(final double x) {
    this.m_x = x;
  }

  /** {@inheritDoc} */
  @Override
  public final double get(final int dimension) {
    if (dimension != Point1D.COORD_X) {
      throw new IndexOutOfBoundsException(Point.DIMENSION_ERROR
          + dimension);
    }
    return this.m_x;
  }

  /** {@inheritDoc} */
  @Override
  public final void set(final int dimension, final double value) {
    if (dimension != Point1D.COORD_X) {
      throw new IndexOutOfBoundsException(Point.DIMENSION_ERROR
          + dimension);
    }
    this.m_x = value;
  }

  /** {@inheritDoc} */
  @Override
  public final int dimension() {
    return 1;
  }

  /** {@inheritDoc} */
  @Override
  public final Point1D clone() {
    return new Point1D(this.m_x);
  }

  /** {@inheritDoc} */
  @Override
  public final void toArray(final double[] dest, final int destStart) {
    dest[destStart] = this.m_x;
  }

  /** {@inheritDoc} */
  @Override
  public final void fromArray(final double[] src, final int srcStart) {
    this.m_x = src[srcStart];
  }

  /** {@inheritDoc} */
  @Override
  public final void assign(final Point p) {
    int i;

    i = p.dimension();
    if (i != 1) {
      throw new IndexOutOfBoundsException(Point.DIMENSION_ERROR + i);
    }

    this.m_x = p.get(Point1D.COORD_X);
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equalsPoint(final Point p) {
    return ((p != null) && (p.dimension() == 1) && //
    (Double.doubleToLongBits(this.m_x) == Double.doubleToLongBits(p
        .get(Point1D.COORD_X))));
  }

  /**
   * Copy the values of another {@code Point1D} instance into this one
   *
   * @param p
   *          the {@code Point1D} instance to copy
   */
  public void assign(final Point1D p) {
    this.m_x = p.m_x;
  }

  /**
   * Does this point equal to another {@code Point1D} instance?
   *
   * @param p
   *          the other {@code Point1D} instance
   * @return {@code true} if and only if the point is identical to this
   *         point, {@code false} otherwise
   */
  public final boolean equalsPoint1D(final Point1D p) {
    return ((p != null) && //
    (Double.doubleToLongBits(this.m_x) == Double.doubleToLongBits(p.m_x)));
  }

  /** {@inheritDoc} */
  @Override
  public final void fromDataCollection(final IDataCollection data,
      final int index) {
    int i;

    i = data.dimension();
    if (i != 1) {
      throw new IndexOutOfBoundsException(Point.DIMENSION_ERROR + i);
    }
    this.m_x = data.get(index, Point1D.COORD_X);
  }

  /** {@inheritDoc} */
  @Override
  public final void toStringBuilder(final StringBuilder sb) {
    sb.append('(');
    sb.append(this.m_x);
    sb.append(')');
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    final StringBuilder sb;
    sb = new StringBuilder(26);
    this.toStringBuilder(sb);
    return sb.toString();
  }
}
