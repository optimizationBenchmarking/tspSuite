package org.logisticPlanning.utils.math.data.point;

import java.io.Serializable;

import org.logisticPlanning.utils.math.data.collection.IDataCollection;
import org.logisticPlanning.utils.utils.HashUtils;
import org.logisticPlanning.utils.utils.comparison.ComparisonUtils;

/**
 * The base class for points. A point can have 1, 2, or 3 dimensions.
 */
public abstract class Point implements Serializable, Cloneable,
    IDataCollection {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the dimension is incorrect! */
  static final String DIMENSION_ERROR = "Dimension out of bounds: "; //$NON-NLS-1$

  /** the first separator */
  static final char[] FIRST_SEP = { '(' };
  /** the next separator */
  static final char[] NEXT_SEP = { ',', ' ' };

  /** instantiate */
  protected Point() {
    super();
  }

  /**
   * Get the value at dimension {@code dimension}.
   *
   * @param dimension
   *          the dimension
   * @return the value at that dimension
   */
  public abstract double get(final int dimension);

  /** {@inheritDoc} */
  @Override
  public Point clone() {
    try {
      return ((Point) (super.clone()));
    } catch (final RuntimeException r) {
      throw r;
    } catch (final Throwable t) {
      throw new RuntimeException(t);
    }
  }

  /**
   * Get the dimension of this point
   *
   * @return the dimension of this point
   */
  @Override
  public abstract int dimension();

  /** {@inheritDoc} */
  @Override
  public final int size() {
    return 1;
  }

  /** {@inheritDoc} */
  @Override
  public final double get(final int point, final int dimension) {
    if (point == 0) {
      return this.get(dimension);
    }
    throw new IndexOutOfBoundsException(//
        "Only index 0 is valid when accessing a point as data collection, but used was index " + point); //$NON-NLS-1$
  }

  /**
   * Write a textual representation of this data structure to a string
   * builder.
   *
   * @param sb
   *          the string builder
   */
  protected void toStringBuilder(final StringBuilder sb) {
    final int dimension;
    char[] sep;
    int i;

    sep = Point.FIRST_SEP;
    dimension = this.dimension();
    for (i = 0; i < dimension; i++) {
      sb.append(sep);
      sb.append(this.get(i));
      sep = Point.NEXT_SEP;
    }
    sb.append(')');
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    final StringBuilder sb;
    sb = new StringBuilder(this.dimension() * 26);
    this.toStringBuilder(sb);
    return sb.toString();
  }

  /** {@inheritDoc} */
  @Override
  public int hashCode() {
    int hc, i;

    hc = 0;
    for (i = this.dimension(); (--i) >= 0;) {
      hc = HashUtils.combineHashes(hc, HashUtils.hashCode(this.get(i)));
    }

    return hc;
  }

  /**
   * Does this point equal to another point?
   *
   * @param p
   *          the other point
   * @return {@code true} if and only if the point is identical to this
   *         point, {@code false} otherwise
   */
  public boolean equalsPoint(final Point p) {
    int i;

    if (p == null) {
      return false;
    }

    i = this.dimension();
    if (i != p.dimension()) {
      return false;
    }
    for (; (--i) >= 0;) {
      if (!(ComparisonUtils.equals(this.get(i), p.get(i)))) {
        return false;
      }
    }
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public boolean equals(final Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof Point) {
      return this.equalsPoint((Point) o);
    }
    return false;
  }

  /**
   * store the data of this point into the destination array
   *
   * @param dest
   *          the destination array
   * @param destStart
   *          the starting index where the copying should begin
   */
  public void toArray(final double[] dest, final int destStart) {
    int i, e;

    i = this.dimension();
    e = (destStart + i);
    for (; (--i) >= 0;) {
      dest[--e] = this.get(i);
    }
  }

  /**
   * Copy an existing point
   *
   * @param p
   *          the point
   * @return the copy of it
   */
  public static final Point copy(final Point p) {
    final double[] array;
    final int dim;

    dim = p.dimension();
    switch (dim) {
      case 1: {
        return new Point1D(p.get(0));
      }
      case 2: {
        return new Point2D(p.get(0), p.get(1));
      }
      case 3: {
        return new Point3D(p.get(0), p.get(1), p.get(2));
      }
      default: {
        array = new double[dim];
        p.toArray(array, 0);
        return new ArrayPoint(array);
      }
    }
  }
}
