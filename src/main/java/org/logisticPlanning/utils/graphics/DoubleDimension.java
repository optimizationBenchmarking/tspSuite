package org.logisticPlanning.utils.graphics;

import java.awt.geom.Dimension2D;

/**
 * A immutable dimension based on {@code double}s
 */
public class DoubleDimension extends Dimension2D {

  /** the empty dimension */
  public static final DoubleDimension EMPTY = new DoubleDimension(0d, 0d);

  /** the width */
  private final double m_width;

  /** the height */
  private final double m_height;

  /**
   * create a new double dimension
   * 
   * @param width
   *          the width
   * @param height
   *          the height
   */
  public DoubleDimension(final double width, final double height) {
    super();
    if ((width < 0d) || Double.isNaN(width) || Double.isInfinite(width)) {
      throw new IllegalArgumentException("Width " + width + //$NON-NLS-1$
          " is invalid."); //$NON-NLS-1$
    }
    if ((height < 0d) || Double.isNaN(height) || Double.isInfinite(height)) {
      throw new IllegalArgumentException("Height " + height + //$NON-NLS-1$
          " is invalid."); //$NON-NLS-1$
    }
    this.m_width = width;
    this.m_height = height;
  }

  /**
   * create a new double dimension
   * 
   * @param dim
   *          the dimension
   */
  public DoubleDimension(final Dimension2D dim) {
    this(dim.getWidth(), dim.getHeight());
  }

  /** {@inheritDoc} */
  @Override
  public final double getWidth() {
    return this.m_width;
  }

  /** {@inheritDoc} */
  @Override
  public final double getHeight() {
    return this.m_height;
  }

  /** {@inheritDoc} */
  @Override
  public void setSize(final double width, final double height) {
    throw new UnsupportedOperationException();
  }

  /**
   * Obtain a copy of this object - and since this object is immutable, we
   * return it itself.
   * 
   * @return this object itself
   */
  @Override
  public final DoubleDimension clone() {
    return this;
  }

  /**
   * Check if this dimension object contains another dimension object
   * 
   * @param dim
   *          the other dimension
   * @return {@code true} if a rectangle of the size defined by this object
   *         could entirely contain a rectangle of the size defined by
   *         {@code dim}
   */
  public final boolean contains(final Dimension2D dim) {
    return ((dim.getWidth() <= this.m_width) && (dim.getHeight() <= this.m_height));
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return '(' + String.valueOf(this.m_width) + ','
        + String.valueOf(this.m_height) + ')';
  }
}
