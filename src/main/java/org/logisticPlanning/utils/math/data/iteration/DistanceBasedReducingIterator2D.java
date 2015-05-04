package org.logisticPlanning.utils.math.data.iteration;

import java.util.Iterator;

import org.logisticPlanning.utils.math.data.point.Point2D;

/**
 * <p>
 * An iterator that reduces the number of points by omitting those which
 * are too close to each other.
 * </p>
 */
public final class DistanceBasedReducingIterator2D extends
    _PointIteratorWrapper2D {
  /** The serial version uid */
  private static final long serialVersionUID = 1L;

  /** the minimum distance */
  private final double m_minDist;

  /** the last x */
  private double m_lastX;

  /** the last y */
  private double m_lastY;

  /** the scaling factor for x-coordinates */
  private final double m_scaleX;

  /** the scaling factor for y-coordinates */
  private final double m_scaleY;

  /** only true from the second point onward */
  private boolean m_notFirst;

  /**
   * instantiate
   *
   * @param minDist
   *          the minimum distance
   * @param source
   *          the source iterator
   */
  public DistanceBasedReducingIterator2D(final Iterator<Point2D> source,
      final double minDist) {
    this(source, minDist, 1d, 1d);
  }

  /**
   * instantiate
   *
   * @param minDist
   *          the minimum distance
   * @param source
   *          the source iterator
   * @param scaleX
   *          the x-scale
   * @param scaleY
   *          the y-scale
   */
  public DistanceBasedReducingIterator2D(final Iterator<Point2D> source,
      final double minDist, final double scaleX, final double scaleY) {
    super(source);

    if ((minDist <= 0d) || (minDist >= Double.POSITIVE_INFINITY)
        || (minDist != minDist)) {
      throw new IllegalArgumentException(//
          "Minimum distance cannot be " + minDist); //$NON-NLS-1$
    }

    this.m_minDist = (minDist * minDist);
    if (this.m_minDist >= Double.POSITIVE_INFINITY) {
      throw new IllegalArgumentException(//
          "Square of minimum distance cannot be infinite, but it is, because minimum distance is set to " + minDist); //$NON-NLS-1$
    }

    if (scaleX <= 0d) {
      throw new IllegalArgumentException(//
          "Scaling factor must be > 0, but x-scale is " + scaleX); //$NON-NLS-1$
    }
    if (scaleY <= 0d) {
      throw new IllegalArgumentException(//
          "Scaling factor must be > 0, but y-scale is " + scaleY); //$NON-NLS-1$
    }

    this.m_scaleX = scaleX;
    this.m_scaleY = scaleY;

    this.m_lastX = this.m_lastY = Double.NaN;
  }

  /** {@inheritDoc} */
  @Override
  final Point2D _loadNextPoint() {
    Point2D p;
    double lastX, lastY, x, y, x1, x2;

    p = super._loadNextPoint();

    if (p == null) {
      return null;
    }

    x = p.getX();
    y = p.getY();

    if (this.m_notFirst) {
      lastX = this.m_lastX;
      lastY = this.m_lastY;

      looper: for (;;) {

        x1 = ((x - lastX) * this.m_scaleX);
        x2 = ((y - lastY) * this.m_scaleY);
        if (((x1 * x1) + (x2 * x2)) >= this.m_minDist) {
          break looper;
        }

        p = super._loadNextPoint();
        if (p == null) {
          break looper;
        }

        x = p.getX();
        y = p.getY();
      }
    } else {
      this.m_notFirst = true;
    }

    // hypot is the squared distance, that's why we squared the minimum
    // distance in the constructor

    // never omit the very last point in the iteration, regardless how
    // close
    // it
    // is to the rest
    this.m_lastX = x;
    this.m_lastY = y;
    return ((p != null) ? p : new Point2D(x, y));
  }

}
