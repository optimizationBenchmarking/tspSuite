package org.logisticPlanning.utils.math.data.iteration;

import java.util.Iterator;

import org.logisticPlanning.utils.math.data.point.Point2D;

/**
 * This iterator goes through a set of points with monotonously increasing
 * or decreasing x-coordinates. If two identical points follow each other,
 * only one of them is returned.
 */
class _PointIteratorWrapper2D extends _PointIterator2D {
  /** The serial version uid */
  private static final long serialVersionUID = 1L;

  /** the source iterator */
  private final Iterator<Point2D> m_source;

  /** the last x-coordinate */
  private double m_lastX;

  /** the last y-coordinate */
  private double m_lastY;

  /**
   * the direction: -2: begin, -1 for falling x-values, 0 for unknown, 1
   * for rising x-values
   */
  private int m_dir;

  /**
   * instantiate
   *
   * @param source
   *          the source iterator
   */
  _PointIteratorWrapper2D(final Iterator<Point2D> source) {
    super();
    if (source == null) {
      throw new IllegalArgumentException(//
          "Source iterator must not be null."); //$NON-NLS-1$
    }
    this.m_source = source;
    this.m_lastX = this.m_lastY = Double.NaN;
    this.m_dir = -2;
  }

  /** {@inheritDoc} */
  @Override
  Point2D _loadNextPoint() {
    Point2D p;
    double d;
    int cr;

    while (this.m_source.hasNext()) {
      p = this.m_source.next();

      d = p.getX();
      if (this.m_dir < (-1)) {
        this.m_dir = 0;
        cr = (-1);
      } else {
        cr = _PointIteratorWrapper2D._compare(this.m_lastX, d);
        if ((cr != 0) && (cr != this.m_dir)) {
          if (this.m_dir == 0) {
            this.m_dir = cr;
          } else {
            throw new IllegalStateException(
                //
                "X-coordinates must be monotonously increasing or decreasing, but were first " + //$NON-NLS-1$
                ((cr < 0) ? "increasing and then decreasing, since " : //$NON-NLS-1$
                    "decreasing and then increasing, since ")//$NON-NLS-1$
                    + this.m_lastX + " was followed by " + d);//$NON-NLS-1$
          }
        }
      }
      this.m_lastX = d;

      if ((d != d) || ((d = p.getY()) != d)) {
        throw new IllegalStateException(//
            "Points must not be NaN, but encountered " + p);//$NON-NLS-1$
      }

      if ((cr == 0)
          && (_PointIteratorWrapper2D._compare(this.m_lastY, d) == 0)) {
        continue;
      }
      this.m_lastY = d;
      return p;
    }
    return null;
  }

  /**
   * a special preview routine
   *
   * @return {@code true} if there is a next element, {@code false}
   *         otherwise
   */
  final boolean _hasNext() {
    return (this.m_source.hasNext());
  }

  /**
   * Compare two doubles: considers doubles which are extremely close to
   * each other (neighbors and second neighbors) as the same.
   *
   * @param a
   *          the first double
   * @param b
   *          the second double
   * @return the result
   */
  static final int _compare(final double a, final double b) {
    if (a == b) {
      return 0;
    }
    if (a < b) {
      if (Math.nextUp(Math.nextUp(a)) >= b) {
        return 0;
      }
      return (-1);
    }

    if (Math.nextUp(Math.nextUp(b)) >= a) {
      return 0;
    }
    return 1;
  }

}
