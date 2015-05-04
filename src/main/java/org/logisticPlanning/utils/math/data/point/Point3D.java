package org.logisticPlanning.utils.math.data.point;

import org.logisticPlanning.utils.math.data.collection.IDataCollection;

/**
 * A modifiable point holding a single 3-dimensional (x, y, and z) tuple.
 */
public class Point3D extends MutablePoint {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the index of the x-coordinate */
  public static final int COORD_X = 0;

  /** the index of the y-coordinate */
  public static final int COORD_Y = 1;

  /** the index of the z-coordinate */
  public static final int COORD_Z = 2;

  /** the x-value @serial serialized as {@code double} value */
  private double m_x;
  /** the y-value @serial serialized as {@code double} value */
  private double m_y;
  /** the z-value @serial serialized as {@code double} value */
  private double m_z;

  /**
   * create the 3-dimensional point
   *
   * @param x
   *          the value of the x-coordinate
   * @param y
   *          the value of the y-coordinate
   * @param z
   *          the value of the z-coordinate
   */
  public Point3D(final double x, final double y, final double z) {
    super();
    this.m_x = x;
    this.m_y = y;
    this.m_z = z;
  }

  /**
   * Create the 3-dimensional point with {@link java.lang.Double#NaN NaN}
   * values in all fields
   */
  public Point3D() {
    this(Double.NaN, Double.NaN, Double.NaN);
  }

  /** create the 3-dimensional point */
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

  /**
   * Get the z-value of this point
   *
   * @return the z-value of this point
   */
  public final double getZ() {
    return this.m_z;
  }

  /**
   * Set the z-value of this point
   *
   * @param z
   *          the new z-value for this point
   */
  public final void setZ(final double z) {
    this.m_z = z;
  }

  /** {@inheritDoc} */
  @Override
  public final double get(final int dimension) {

    switch (dimension) {
      case Point3D.COORD_X: {
        return this.m_x;
      }
      case Point3D.COORD_Y: {
        return this.m_y;
      }
      case Point3D.COORD_Z: {
        return this.m_z;
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
      case Point3D.COORD_X: {
        this.m_x = value;
        return;
      }
      case Point3D.COORD_Y: {
        this.m_y = value;
        return;
      }
      case Point3D.COORD_Z: {
        this.m_z = value;
        return;
      }
      default: {
        throw new IndexOutOfBoundsException(Point.DIMENSION_ERROR
            + dimension);
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public final int dimension() {
    return 3;
  }

  /** {@inheritDoc} */
  @Override
  public final Point3D clone() {
    return new Point3D(this.m_x, this.m_y, this.m_z);
  }

  /** {@inheritDoc} */
  @Override
  public final void toArray(final double[] dest, final int destStart) {
    dest[destStart] = this.m_x;
    dest[destStart + 1] = this.m_y;
    dest[destStart + 2] = this.m_z;
  }

  /** {@inheritDoc} */
  @Override
  public final void fromArray(final double[] src, final int srcStart) {
    this.m_x = src[srcStart];
    this.m_y = src[srcStart + 1];
    this.m_z = src[srcStart + 2];
  }

  /** {@inheritDoc} */
  @Override
  public final void assign(final Point p) {
    int i;

    i = p.dimension();
    if (i != 3) {
      throw new IndexOutOfBoundsException(Point.DIMENSION_ERROR + i);
    }

    this.m_x = p.get(Point3D.COORD_X);
    this.m_y = p.get(Point3D.COORD_Y);
    this.m_z = p.get(Point3D.COORD_Z);
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equalsPoint(final Point p) {
    return ((p != null) && (p.dimension() == 3)
        && //
        (Double.doubleToLongBits(this.m_x) == Double.doubleToLongBits(p
            .get(Point3D.COORD_X)))
            && //
            (Double.doubleToLongBits(this.m_y) == Double.doubleToLongBits(p
                .get(Point3D.COORD_Y))) && //
                (Double.doubleToLongBits(this.m_z) == Double.doubleToLongBits(p
                    .get(Point3D.COORD_Z))));
  }

  /**
   * Copy the values of another {@code Point3D} instance into this one
   *
   * @param p
   *          the {@code Point3D} instance to copy
   */
  public void assign(final Point3D p) {
    this.m_x = p.m_x;
    this.m_y = p.m_y;
    this.m_z = p.m_z;
  }

  /**
   * Does this point equal to another {@code Point3D} instance?
   *
   * @param p
   *          the other {@code Point3D} instance
   * @return {@code true} if and only if the point is identical to this
   *         point, {@code false} otherwise
   */
  public final boolean equalsPoint3D(final Point3D p) {
    return ((p != null)
        && //
        (Double.doubleToLongBits(this.m_x) == Double
        .doubleToLongBits(p.m_x))
        && //
        (Double.doubleToLongBits(this.m_y) == Double
        .doubleToLongBits(p.m_y)) && //
        (Double.doubleToLongBits(this.m_z) == Double.doubleToLongBits(p.m_z)));
  }

  /** {@inheritDoc} */
  @Override
  public final void fromDataCollection(final IDataCollection data,
      final int index) {
    int i;

    i = data.dimension();
    if (i != 3) {
      throw new IndexOutOfBoundsException(Point.DIMENSION_ERROR + i);
    }
    this.m_x = data.get(index, Point3D.COORD_X);
    this.m_y = data.get(index, Point3D.COORD_Y);
    this.m_z = data.get(index, Point3D.COORD_Z);
  }

  /** {@inheritDoc} */
  @Override
  public final void toStringBuilder(final StringBuilder sb) {
    sb.append('(');
    sb.append(this.m_x);
    sb.append(Point.NEXT_SEP);
    sb.append(this.m_y);
    sb.append(Point.NEXT_SEP);
    sb.append(this.m_z);
    sb.append(')');
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    final StringBuilder sb;
    sb = new StringBuilder(78);
    this.toStringBuilder(sb);
    return sb.toString();
  }
}
