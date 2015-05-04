package org.logisticPlanning.utils.math.data.point;

import org.logisticPlanning.utils.math.data.collection.IDataCollection;

/**
 * A modifiable point holding a single 2-dimensional (x and y) tuple.
 */
public class Point2D extends MutablePoint {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the index of the x-coordinate */
  public static final int COORD_X = 0;

  /** the index of the y-coordinate */
  public static final int COORD_Y = 1;

  /** the x-value @serial serialized as {@code double} value */
  private double m_x;
  /** the y-value @serial serialized as {@code double} value */
  private double m_y;

  /**
   * create the 2-dimensional point
   *
   * @param x
   *          the value of the x-coordinate
   * @param y
   *          the value of the y-coordinate
   */
  public Point2D(final double x, final double y) {
    super();
    this.m_x = x;
    this.m_y = y;
  }

  /**
   * Create the 2-dimensional point with {@link java.lang.Double#NaN NaN}
   * values in all fields
   */
  public Point2D() {
    this(Double.NaN, Double.NaN);
  }

  /** create the 2-dimensional point */
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

  /**
   * Get the y-value of this point
   *
   * @return the y-value of this point
   */
  public final double getY() {
    return this.m_y;
  }

  /**
   * Set the y-value of this point
   *
   * @param y
   *          the new y-value for this point
   */
  public final void setY(final double y) {
    this.m_y = y;
  }

  /** {@inheritDoc} */
  @Override
  public final double get(final int dimension) {

    switch (dimension) {
      case Point2D.COORD_X: {
        return this.m_x;
      }
      case Point2D.COORD_Y: {
        return this.m_y;
      }
      default: {
        throw new IndexOutOfBoundsException(Point.DIMENSION_ERROR
            + dimension);
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void set(final int dimension, final double value) {

    switch (dimension) {
      case Point2D.COORD_X: {
        this.m_x = value;
        return;
      }
      case Point2D.COORD_Y: {
        this.m_y = value;
        return;
      }
      default: {
        throw new IndexOutOfBoundsException(Point.DIMENSION_ERROR
            + dimension);
      }
    }
  }

  /**
   * Get one of the two coordinate values: the x-coordinate if {@code x} is
   * {@code true}, and the y-coordinate if {@code x} is {@code false}. This
   * is a quicker version of {@link #get(int)} since it bypasses the
   * dimension check.
   *
   * @param x
   *          should we use the x-coordinate ({@code x==true}) or the
   *          y-coordinate ({@code x==false})
   * @return the value of that coordinate
   */
  public final double get(final boolean x) {
    return (x ? (this.m_x) : (this.m_y));
  }

  /**
   * Set one of the two coordinate values: the x-coordinate if {@code x} is
   * {@code true}, and the y-coordinate if {@code x} is {@code false}. This
   * is a quicker version of {@link #get(int)} since it bypasses the
   * dimension check.
   *
   * @param x
   *          should we use the x-coordinate ({@code x==true}) or the
   *          y-coordinate ({@code x==false})
   * @param value
   *          the new value of that coordinate
   */
  public final void set(final boolean x, final double value) {
    if (x) {
      this.m_x = value;
    } else {
      this.m_y = value;
    }
  }

  /** {@inheritDoc} */
  @Override
  public final int dimension() {
    return 2;
  }

  /** {@inheritDoc} */
  @Override
  public final Point2D clone() {
    return new Point2D(this.m_x, this.m_y);
  }

  /** {@inheritDoc} */
  @Override
  public final void toArray(final double[] dest, final int destStart) {
    dest[destStart] = this.m_x;
    dest[destStart + 1] = this.m_y;
  }

  /** {@inheritDoc} */
  @Override
  public final void fromArray(final double[] src, final int srcStart) {
    this.m_x = src[srcStart];
    this.m_y = src[srcStart + 1];
  }

  /** {@inheritDoc} */
  @Override
  public final void assign(final Point p) {
    int i;

    i = p.dimension();
    if (i != 2) {
      throw new IndexOutOfBoundsException(Point.DIMENSION_ERROR + i);
    }

    this.m_x = p.get(Point2D.COORD_X);
    this.m_y = p.get(Point2D.COORD_Y);
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equalsPoint(final Point p) {
    return ((p != null)
        && (p.dimension() == 2)
        && //
        (Double.doubleToLongBits(this.m_x) == Double.doubleToLongBits(p
            .get(Point2D.COORD_X))) && //
            (Double.doubleToLongBits(this.m_y) == Double.doubleToLongBits(p
                .get(Point2D.COORD_Y))));
  }

  /**
   * Copy the values of another {@code Point2D} instance into this one
   *
   * @param p
   *          the {@code Point2D} instance to copy
   */
  public void assign(final Point2D p) {
    this.m_x = p.m_x;
    this.m_y = p.m_y;
  }

  /**
   * Does this point equal to another {@code Point2D} instance?
   *
   * @param p
   *          the other {@code Point2D} instance
   * @return {@code true} if and only if the point is identical to this
   *         point, {@code false} otherwise
   */
  public final boolean equalsPoint2D(final Point2D p) {
    return ((p != null) && //
        (Double.doubleToLongBits(this.m_x) == Double
        .doubleToLongBits(p.m_x)) && //
        (Double.doubleToLongBits(this.m_y) == Double.doubleToLongBits(p.m_y)));
  }

  /** {@inheritDoc} */
  @Override
  public final void fromDataCollection(final IDataCollection data,
      final int index) {
    int i;

    i = data.dimension();
    if (i != 2) {
      throw new IndexOutOfBoundsException(Point.DIMENSION_ERROR + i);
    }
    this.m_x = data.get(index, Point2D.COORD_X);
    this.m_y = data.get(index, Point2D.COORD_Y);
  }

  /** {@inheritDoc} */
  @Override
  public final void toStringBuilder(final StringBuilder sb) {
    sb.append('(');
    sb.append(this.m_x);
    sb.append(Point.NEXT_SEP);
    sb.append(this.m_y);
    sb.append(')');
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    final StringBuilder sb;
    sb = new StringBuilder(52);
    this.toStringBuilder(sb);
    return sb.toString();
  }
}
