package org.logisticPlanning.utils.graphics.chart.spec.range;

import org.logisticPlanning.utils.math.data.point.Point;
import org.logisticPlanning.utils.utils.comparison.ComparisonUtils;

/**
 * the end of an axis
 */
public abstract class AxisEnd implements Cloneable {

  /** create */
  protected AxisEnd() {
    //
  }

  /**
   * Is any data needed to determine the axis end, e.g., for
   * {@link #registerPoint(Point)}?
   *
   * @return {@code true} if data is needed, {@code false} otherwise
   */
  public boolean beginData() {
    return false;
  }

  /** we end a data iteration */
  public void endData() {
    //
  }

  /**
   * Register a given point.
   *
   * @param p
   *          the point
   * @return {@code true} if further points are needed, {@code false} if
   *         not
   */
  public boolean registerPoint(final Point p) {
    return false;
  }

  /**
   * Calculate the axis end value
   *
   * @return the axis end value
   */
  protected abstract double calcEnd();

  /**
   * check if the value is approximately an integer or not
   *
   * @param d
   *          the value to check
   * @return {@code true} if the value can be transformed to a {@code long}
   *         without loss of information, {@code false} otherwise
   */
  static final boolean _isLong(final double d) {
    return ((d >= Long.MIN_VALUE) && (d <= Long.MAX_VALUE) && //
        ComparisonUtils.isClose(d, Math.rint(d), 3));
  }

  /**
   * Get the end of the axis
   *
   * @return the end of axis
   */
  public final double getEnd() {
    final double d;

    d = this.calcEnd();
    if (ComparisonUtils.isFinite(d)) {
      if (AxisEnd._isLong(d)) {
        return Math.round(d);
      }
      return d;
    }
    throw new IllegalStateException("Axis end must be finite, but is " + d); //$NON-NLS-1$
  }

  // /**
  // * Reset any computation
  // */
  // public void reset() {
  // //
  // }

  /** {@inheritDoc} */
  @Override
  public AxisEnd clone() {
    try {
      return ((AxisEnd) (super.clone()));
    } catch (final Throwable t) {
      throw new RuntimeException(t);
    }
  }
}
