package org.logisticPlanning.utils.math.data.iteration;

import java.util.Iterator;

import org.logisticPlanning.utils.math.data.point.Point2D;

/**
 * This iterator has a window (minX, maxX), (minY, maxY) and reads points
 * from an underlying iterator. If a sub-sequence of the points read is
 * outside of the window, then only the first and maybe the last point of
 * the outside sequence is returned. The last point of the sequence outside
 * is only returned if there is a latter point which is inside the sequence
 * again.
 */
public final class BoundsReducingIterator2D extends
    _PointIteratorWrapper2D {
  /** The serial version uid */
  private static final long serialVersionUID = 1L;

  /** the minimum x */
  private final double m_minX;
  /** the maximum x */
  private final double m_maxX;
  /** the minimum y */
  private final double m_minY;
  /** the maximum y */
  private final double m_maxY;

  /** the state */
  private int m_state;
  /** the queue 1 */
  private final Point2D m_q1;
  /** the queue 2 */
  private final Point2D m_q2;
  /** the queue 3 */
  private Point2D m_next;

  /**
   * instantiate
   *
   * @param source
   *          the source iterator
   * @param minX
   *          the minimum x
   * @param maxX
   *          the maximum x
   * @param minY
   *          the minimum y
   * @param maxY
   *          the maximum y
   */
  public BoundsReducingIterator2D(final Iterator<Point2D> source,
      final double minX, final double maxX, final double minY,
      final double maxY) {
    super(source);

    if (minX >= maxX) {
      throw new IllegalArgumentException("x-range empty: " + minX + //$NON-NLS-1$
          ":" + maxX); //$NON-NLS-1$
    }

    if (minY >= maxY) {
      throw new IllegalArgumentException("y-range empty: " + minY + //$NON-NLS-1$
          ":" + maxY); //$NON-NLS-1$
    }

    if ((minX != minX) || (maxX != maxX) || (minY != minY)
        || (maxY != maxY)) {
      throw new IllegalArgumentException("encountered NaN: " + minX + //$NON-NLS-1$
          ", " + maxX + ", " + //$NON-NLS-1$//$NON-NLS-2$
          minY + ", " + maxY); //$NON-NLS-1$
    }

    this.m_minX = minX;
    this.m_maxX = maxX;
    this.m_minY = minY;
    this.m_maxY = maxY;
    this.m_q1 = new Point2D();
    this.m_q2 = new Point2D();
  }

  /**
   * check if a point is inside the bounds
   *
   * @param p
   *          the point
   * @return {@code true} if it is inside the boundaries, {@code false}
   *         otherwise
   */
  private final boolean __check(final Point2D p) {
    double d;
    return (((d = p.getX()) >= this.m_minX) && (d <= this.m_maxX) && //
        ((d = p.getY()) >= this.m_minY) && (d <= this.m_maxY));
  }

  /** {@inheritDoc} */
  @SuppressWarnings("fallthrough")
  @Override
  final Point2D _loadNextPoint() {
    Point2D p;
    boolean notFirst;

    notFirst = true;

    outer: for (;;) {
      switch (this.m_state) {

        case 0: {
          // ok, we reach this method for the first time: fall through to
          // next
          // case
          notFirst = false;
          this.m_state = 1;
        }

        case 1: {
          // queue is empty, we are either at the start of the sequence or
          // inside the range
          p = super._loadNextPoint();
          if ((p == null) || (this.__check(p))) {
            // either we are at the end or have a good point -> return
            // it
            return p;
          }

          // point is outside range and we are not at the end: enqueue it
          this.m_q1.assign(p);
          this.m_state = 2;

          for (;;) {
            // there now is at least one point in the queue (in q1) that
            // is
            // outside of the range

            // load next point
            p = super._loadNextPoint();

            if (p == null) {// we are at the end
              if (notFirst && (this.m_state == 3)) {
                // we are not at the beginning of the sequence, so
                // we now only
                // need to return q1
                this.m_state = 2;
              }
              // swith again: lands either at label 2, 3, or 4
              continue outer;
            }

            // p is not null
            if (this.__check(p)) {
              // p is valid! so this means we must return the enqueued
              // points
              // and then p
              this.m_next = p; // don't need to copy p, can return it
              // directly
              // later

              if (notFirst) {
                // not at start of sequence: two choices:
                // 1) only q1 is in queue: state==2 -> state=5 ->
                // return q1,
                // then next
                // 2) q1, q2 in queue: state==3 -> state=6 -> return
                // q1, then
                // q2, then next
                this.m_state += 3;
              } else {
                // the sequence begins with outside points q1, q2:
                // we can
                // ignore q1 and only return q2
                this.m_state = ((this.m_state << 1) + 1);
              }
              continue outer;
            }

            // p is outside of range: store it in q2, continue reading
            this.m_q2.assign(p);
            this.m_state = 3;
          }
        }

        case 2: {
          // return q1 -> queue is empty -> continue filling queue
          this.m_state = 1;
          return this.m_q1;
        }

        case 3: {
          // return q1 -> q2 -> queue is empty -> continue filling queue
          this.m_state = 4;
          return this.m_q1;
        }

        case 4: {
          // return q2 -> queue is empty -> continue filling queue
          this.m_state = 1;
          return this.m_q2;
        }

        case 5: {// return q1 -> next -> continue filling queue
          this.m_state = 8;
          return this.m_q1;
        }

        case 6: {// return q1 -> q2 -> next -> continue filling queue
          this.m_state = 7;
          return this.m_q1;
        }

        case 7: {// return q2 -> next -> continue filling queue
          this.m_state = 8;
          return this.m_q2;
        }

        default: {// 8: return next -> continue filling queue
          this.m_state = 1;
          return this.m_next;
        }
      }
    }
  }
}
