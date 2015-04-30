package org.logisticPlanning.utils.graphics.chart.spec.range;

import org.logisticPlanning.utils.graphics.chart.spec.Line2D;
import org.logisticPlanning.utils.math.data.collection.IDataCollection;
import org.logisticPlanning.utils.math.data.collection.ReverseCollection;
import org.logisticPlanning.utils.math.data.iteration.DataCollectionIterator2D;
import org.logisticPlanning.utils.math.data.point.Point2D;
import org.logisticPlanning.utils.utils.comparison.ComparisonUtils;

/**
 * The class that automatically computes axis ranges.
 */
public final class AxisRange2D extends _AxisRange2DDef {

  /** the x-values */
  private transient double[] m_x;

  /** the y-value */
  private transient double[] m_y;

  /** the end has been calculated */
  private transient boolean m_known;

  /**
   * create
   * 
   * @param minX
   *          the minimum-x end
   * @param maxX
   *          the maximum-x end
   * @param autoExpandX
   *          automatically expand x-range if it is collapsed to zero range
   * @param minY
   *          the minimum-y end
   * @param maxY
   *          the maximum-y end
   * @param autoExpandY
   *          automatically expand Y-range if it is collapsed to zero range
   */
  AxisRange2D(final AxisEnd minX, final AxisEnd maxX,
      final boolean autoExpandX, final AxisEnd minY, final AxisEnd maxY,
      final boolean autoExpandY) {
    super(minX, maxX, autoExpandX, minY, maxY, autoExpandY);
    this.m_known = false;
  }

  /**
   * Register a line builder 2D
   * 
   * @param builder
   *          the line builder
   */
  public void registerLine2D(final Line2D builder) {
    final boolean minX, maxX, minY, maxY;
    final IDataCollection data;
    DataCollectionIterator2D it;
    Point2D next;
    boolean minX2, maxX2, minY2, maxY2;

    minX = this.m_minX.beginData();
    try {
      maxX = this.m_maxX.beginData();
      try {
        minY = this.m_minY.beginData();
        try {
          maxY = this.m_maxY.beginData();
          try {

            if (minX || maxX || minY || maxY) {
              this.m_known = false;

              data = builder.getData();
              it = new DataCollectionIterator2D(builder.getLeftEnd(),
                  data, builder.getRightEnd());
              minX2 = minX;
              maxX2 = maxX;
              minY2 = minY;
              maxY2 = maxY;
              while ((minX2 || maxX2 || minY2 || maxY2) && it.hasNext()) {
                next = it.next();

                if (minX2) {
                  if (!(this.m_minX.registerPoint(next))) {
                    minX2 = false;
                  }
                }
                if (maxX2) {
                  if (!(this.m_maxX.registerPoint(next))) {
                    maxX2 = false;
                  }
                }
                if (minY2) {
                  if (!(this.m_minY.registerPoint(next))) {
                    minY2 = false;
                  }
                }
                if (maxY2) {
                  if (!(this.m_maxY.registerPoint(next))) {
                    maxY2 = false;
                  }
                }
              }

              it = new DataCollectionIterator2D(builder.getRightEnd(),
                  ((data != null) ? new ReverseCollection(data) : null),
                  builder.getLeftEnd());
              minX2 = minX;
              maxX2 = maxX;
              minY2 = minY;
              maxY2 = maxY;
              while ((minX2 || maxX2 || minY2 || maxY2) && it.hasNext()) {
                next = it.next();

                if (minX2) {
                  if (!(this.m_minX.registerPoint(next))) {
                    minX2 = false;
                  }
                }
                if (maxX2) {
                  if (!(this.m_maxX.registerPoint(next))) {
                    maxX2 = false;
                  }
                }
                if (minY2) {
                  if (!(this.m_minY.registerPoint(next))) {
                    minY2 = false;
                  }
                }
                if (maxY2) {
                  if (!(this.m_maxY.registerPoint(next))) {
                    maxY2 = false;
                  }
                }
              }

            }

          } finally {
            this.m_maxY.endData();
          }
        } finally {
          this.m_minY.endData();
        }

      } finally {
        this.m_maxX.endData();
      }
    } finally {
      this.m_minX.endData();
    }
  }

  /**
   * calculate the axis ends
   * 
   * @param min
   *          the minimum
   * @param max
   *          the maximum
   * @param expand
   *          should we expand
   * @param x
   *          {@code true}: x-coordinates, {@code false}: y-coordinates
   * @param dest
   *          the destination
   */
  private static final void __calc(final double min, final double max,
      final boolean expand, final boolean x, final double[] dest) {
    double mi, ma;

    if (expand) {
      mi = Math.min(min, max);
      ma = Math.max(max, min);

      if (mi >= ma) {
        if (mi == 0d) {
          ma = 1d;
        }

        if (mi >= ma) {
          if (ComparisonUtils.isInteger(mi)
              && ComparisonUtils.isInteger(ma)) {
            if (ma > 0d) {
              ma += 1d;
            } else {
              if (mi < 0d) {
                mi -= 1d;
              }
            }
          }
        }

        if (mi >= ma) {
          mi = Math.nextAfter(mi, Double.NEGATIVE_INFINITY);
          ma = Math.nextUp(ma);
        }
      }
    } else {
      mi = min;
      ma = max;
    }

    if ((mi >= ma) || (!(ComparisonUtils.isFinite(mi)))
        || (!(ComparisonUtils.isFinite(ma)))) {
      throw new IllegalStateException(
          ((((((x ? 'X' : 'Y') + "-axis range is empty or undefined: [") + //$NON-NLS-1$
          mi) + ',') + ma) + ']'));
    }

    dest[0] = mi;
    dest[1] = ma;
  }

  /** calculate the axis ends */
  private final void __calc() {
    if (this.m_known && (this.m_x != null) && (this.m_y != null)) {
      return;
    }

    if (this.m_x == null) {
      this.m_x = new double[2];
    }

    AxisRange2D.__calc(this.m_minX.getEnd(), this.m_maxX.getEnd(),
        this.m_autoExpandX, true, this.m_x);

    if (this.m_y == null) {
      this.m_y = new double[2];
    }

    AxisRange2D.__calc(this.m_minY.getEnd(), this.m_maxY.getEnd(),
        this.m_autoExpandY, false, this.m_y);

    this.m_known = true;
  }

  /**
   * Obtain the minimum value of the x-axis
   * 
   * @return the minimum value of the x-axis
   */
  public final double getMinimumX() {
    this.__calc();
    return this.m_x[0];
  }

  /**
   * Obtain the maximum value of the x-axis
   * 
   * @return the maximum value of the x-axis
   */
  public final double getMaximumX() {
    this.__calc();
    return this.m_x[1];
  }

  /**
   * Obtain the minimum value of the y-axis
   * 
   * @return the minimum value of the y-axis
   */
  public final double getMinimumY() {
    this.__calc();
    return this.m_y[0];
  }

  /**
   * Obtain the maximum value of the y-axis
   * 
   * @return the maximum value of the y-axis
   */
  public final double getMaximumY() {
    this.__calc();
    return this.m_y[1];
  }

  // /** reset all axis range computations */
  // public void reset() {
  // this.m_miX = this.m_maX = this.m_miY = this.m_maY = Double.NaN;
  // this.m_known = false;
  // this.m_minX.reset();
  // this.m_maxX.reset();
  // this.m_minY.reset();
  // this.m_maxY.reset();
  // }

}
