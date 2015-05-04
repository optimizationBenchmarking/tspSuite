package org.logisticPlanning.utils.math.data.iteration;

import java.util.Iterator;

import org.logisticPlanning.utils.math.data.point.Point2D;

/**
 * a line iterator
 */
public final class StairsKeepLeftIterator2D extends
_PointIteratorWrapper2D {
  /** The serial version uid */
  private static final long serialVersionUID = 1L;

  /** the loaded point */
  private final Point2D m_load2;

  /** the next point */
  private Point2D m_next;

  /** is this the first point ever returned? */
  private boolean m_first;

  /**
   * instantiate
   *
   * @param source
   *          the source iterator
   */
  public StairsKeepLeftIterator2D(final Iterator<Point2D> source) {
    super(source);
    this.m_load2 = new Point2D();
    this.m_first = true;
  }

  /** {@inheritDoc} */
  @Override
  final Point2D _loadNextPoint() {
    Point2D n;

    n = this.m_next;
    if (n != null) {
      this.m_next = null;
      this.m_load2.setY(n.getY());
      return n;
    }

    n = super._loadNextPoint();
    if (n == null) {
      return null;
    }

    if (this.m_first) {
      this.m_first = false;
      this.m_load2.setY(n.getY());
      return n;
    }

    this.m_next = n;
    this.m_load2.setX(n.getX());
    return this.m_load2;
  }

}
