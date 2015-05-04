package org.logisticPlanning.utils.math.data.iteration;

import java.util.NoSuchElementException;

import org.logisticPlanning.utils.collections.basic.BasicIterator;
import org.logisticPlanning.utils.math.data.point.Point2D;

/**
 * An iterator that wraps a data collection.
 */
abstract class _PointIterator2D extends BasicIterator<Point2D> {
  /** The serial version uid */
  private static final long serialVersionUID = 1L;

  /** the next point to return */
  private Point2D m_next;

  /** instantiate */
  _PointIterator2D() {
    super();
  }

  /**
   * load the next point
   *
   * @return the next point, or {@code null} if none was found
   */
  abstract Point2D _loadNextPoint();

  /** {@inheritDoc} */
  @Override
  public final boolean hasNext() {
    return ((this.m_next != null) || //
        ((this.m_next = this._loadNextPoint()) != null));
  }

  /** {@inheritDoc} */
  @Override
  public final Point2D next() {
    Point2D n;

    n = this.m_next;
    if (n == null) {
      n = this._loadNextPoint();
    } else {
      this.m_next = null;
    }

    if (n == null) {
      throw new NoSuchElementException();
    }

    return n;
  }
}
