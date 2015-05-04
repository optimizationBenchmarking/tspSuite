package org.logisticPlanning.utils.math.data.iteration;

import java.util.Iterator;

import org.logisticPlanning.utils.math.data.point.Point2D;

/**
 * <p>
 * This class allows us to iterate over the
 * {@link org.logisticPlanning.utils.math.data.point.Point2D 2D-points} of
 * an underlying source while skipping redundant points. A point is
 * considered as redundant if it is located on a straight line between two
 * other points which is parallel to either the x or y-axis. Such points
 * are detected with little overhead and some additional calculations.
 * </p>
 * <p>
 * The general contract is that the instances of
 * {@link org.logisticPlanning.utils.math.data.point.Point2D Point2D}
 * returned by this iterators {@link #next()} method are synthesized and
 * modified on the fly. You must never store references to these points, as
 * their contents change during the iteration process. If you need to
 * preserve their value, you can
 * {@link org.logisticPlanning.utils.math.data.point.Point2D#clone() clone}
 * them.
 * </p>
 */
public final class StraightReducingIterator2D extends
    _PointIteratorWrapper2D {
  /** The serial version uid */
  private static final long serialVersionUID = 1L;

  /** the last */
  private Point2D m_last;
  /** the cur */
  private Point2D m_cur;

  /** the next 1 */
  private Point2D m_next1;
  /** the next 2 */
  private Point2D m_next2;
  /** the next 3 */
  private Point2D m_next3;

  /** the number of stored points */
  private int m_fill;

  /**
   * instantiate
   *
   * @param source
   *          the source iterator
   */
  public StraightReducingIterator2D(final Iterator<Point2D> source) {
    super(source);

    this.m_last = new Point2D();
    this.m_cur = new Point2D();
    this.m_next1 = new Point2D();
    this.m_next2 = new Point2D();
    this.m_next3 = new Point2D();
    this.m_fill = 0;
  }

  /**
   * check if either {@code a<=b<=c} or {@code a>=b>=c}
   *
   * @param a
   *          the first point
   * @param b
   *          the second point
   * @param c
   *          the third point
   * @return {@code true} if {@code b} is between {@code a} and {@code c},
   *         {@code false} otherwise
   */
  private static final boolean __inBetween(final double a, final double b,
      final double c) {
    switch (_PointIteratorWrapper2D._compare(a, b)) {
      case 0: {
        return true;
      }
      case 1: {
        return (_PointIteratorWrapper2D._compare(b, c) >= 0);
      }
      default: {
        return (_PointIteratorWrapper2D._compare(b, c) <= 0);
      }
    }
  }

  /**
   * load and validate the next point
   *
   * @param last
   *          the last point
   * @return the next point, or {@code null} if none was found
   */
  private final Point2D __load(final Point2D last) {
    Point2D p;

    main: for (;;) {
      p = super._loadNextPoint();
      if (p == null) {
        return null;
      }
      if ((_PointIteratorWrapper2D._compare(p.getX(), last.getX()) == 0) && //
          (_PointIteratorWrapper2D._compare(p.getY(), last.getY()) == 0)) {
        continue main;
      }
      return p;
    }
  }

  /** {@inheritDoc} */
  @Override
  final Point2D _loadNextPoint() {

    // since we entered the step routine, at least one point has been
    // popped
    this.__pop();

    main: for (;;) {
      if (this.__fillQueue()) {
        return null;
      }

      // if we get here, there must be at least one point in the queue
      if (this.m_fill == 1) {
        // we can definitely use this point, since its different from
        // the one
        // before
        return this.m_cur;
      }

      // ok, we have at least two points ("cur", "next1") and maybe "last"
      // ("last" is filled with NaNs when this routine is called the first
      // time)
      // furthermore, this.m_fill >= 2
      if (this.__checkLine(true)) {
        continue main;
      }
      if (this.__checkLine(false)) {
        continue main;
      }

      // cur and last are not on the same vertical line
      return this.m_cur;
    }
  }

  /** remove a point from the queue */
  private final void __pop() {
    final Point2D last;
    last = this.m_cur;
    this.m_cur = this.m_next1;
    this.m_next1 = this.m_next2;
    this.m_next2 = this.m_next3;
    this.m_next3 = this.m_last;
    this.m_last = last;
    this.m_fill--;
  }

  /**
   * fill the queue
   *
   * @return {@code true} if the queue remains empty, {@code false} if
   *         there is at least one point
   */
  @SuppressWarnings({ "incomplete-switch", "fallthrough" })
  private final boolean __fillQueue() {
    Point2D load;

    loadSwitch: switch (this.m_fill) {
      case -1: {
        // the queue was empty anyway, so set its length to 0
        this.m_fill = 0;
      }

      case 0: {
        // the queue was empty or has become empty, load the "current"
        // point
        load = this.__load(this.m_last);
        if (load == null) {
          return true;
        }
        this.m_cur.assign(load);
        this.m_fill = 1;
      }

      case 1: {
        // there now is at least 1 point in the queue, load a second point
        load = this.__load(this.m_cur);
        if (load == null) {
          break loadSwitch;
        }
        this.m_next1.assign(load);
        this.m_fill = 2;
      }

      case 2: {
        // there now are at least 2 points in the queue, load a third point
        load = this.__load(this.m_next1);
        if (load == null) {
          break loadSwitch;
        }
        this.m_next2.assign(load);
        this.m_fill = 3;
      }
    }

    return false;
  }

  /**
   * Check if some points lie on a line along one of the two coordinate
   * axes and compactify the queue, if possible. There must be at least two
   * points in the queue.
   *
   * @param axis
   *          the axis
   * @return {@code true} if the points are on the same line, {@code false}
   *         otherwise
   */
  private final boolean __checkLine(final boolean axis) {
    final boolean otherAxis;
    Point2D load;
    double lastA, minB, maxB, b1, b2;
    boolean i, j;

    lastA = this.m_last.get(axis);

    if (_PointIteratorWrapper2D._compare(this.m_cur.get(axis), lastA) != 0) {
      return false;
    }

    // current point is on the same vertical line as the last point
    // but cannot be the same point, so it's y-coordinate must differ

    if (_PointIteratorWrapper2D._compare(this.m_next1.get(axis), lastA) != 0) {
      return false;
    }

    // all three points are on one line
    otherAxis = (!(axis));

    if ((this.m_fill >= 3)
        && (_PointIteratorWrapper2D
            ._compare(this.m_next2.get(axis), lastA) == 0)) {
      // we have four points, "last", "cur", "next1", and "next2"
      // and all three are on the same vertical line!
      // "last" is the point where the curve has reached the vertical line
      // we now need to find 1) the minimum y-coordinate of the line
      // 2) the maximum y-coordinate of the line
      // 3) the point where the curve leaves the line
      // all other points on this line can be omitted!

      b1 = this.m_last.get(otherAxis);
      b2 = this.m_cur.get(otherAxis);

      if (b1 < b2) {
        minB = b1;
        maxB = b2;
      } else {
        minB = b2;
        maxB = b1;
      }
      b2 = this.m_next1.get(otherAxis);
      if (b2 < minB) {
        minB = b1;
      }
      if (b2 > maxB) {
        maxB = b2;
      }
      b2 = this.m_next2.get(otherAxis);
      if (b2 < minB) {
        minB = b2;
      }
      if (b2 > maxB) {
        maxB = b2;
      }

      // we seek the end of the vertical line, eat up all points on the
      // line
      checkPoint4X: for (;;) {
        if (this.m_fill == 3) {
          load = this.__load(this.m_next2);
          if (load == null) {
            break checkPoint4X;
          }
          this.m_next3.assign(load);
          this.m_fill = 4;
        }
        if (_PointIteratorWrapper2D
            ._compare(this.m_next3.get(axis), lastA) != 0) {
          break checkPoint4X;
        }
        b2 = this.m_next3.get(otherAxis);
        if (b2 < minB) {
          minB = b2;
        }
        if (b2 > maxB) {
          maxB = b2;
        }
        this.m_fill = 3;
      }

      // now: b1=last.get(otherAxis) is the start y, which has already
      // been set
      // minY, maxY are the minimum and maximum y coordinates
      // b2 is the end-y coordinate
      // there are at least 3 points in the queue, if there is a fourth
      // point,
      // it's x-coordinate will be different

      i = StraightReducingIterator2D.__inBetween(b1, minB, b2);
      j = StraightReducingIterator2D.__inBetween(b1, maxB, b2);
      if (i && j) {
        // the point "last" was also the minimum or maximum point of the
        // vertical line and the last point of the vertical line in the
        // sequence was its
        // minimum or maximum as well,
        // so we can drop all other points except the current one
        this.m_cur.set(otherAxis, b2);

        if (this.m_fill == 4) {// preserve point after the line
          load = this.m_next1;
          this.m_next1 = this.m_next3;
          this.m_next3 = load;
          this.m_fill = 2;
        } else {
          this.m_fill = 1;
        }

        return true;
      }

      if (i || j) {
        // ok, either the minimum or maximum is contained in b1,b2 - we
        // can
        // remove one point
        if (i) {
          // the minimum y-coordinate lies between the start and end
          // point
          this.m_cur.set(otherAxis, maxB);
          this.m_next1.set(otherAxis, b2);
        } else {
          // the maximum y-coordinate lies between the start and end
          // point
          this.m_cur.set(otherAxis, minB);
          this.m_next1.set(otherAxis, b2);
        }

        if (this.m_fill == 4) {// preserve point after the line
          load = this.m_next2;
          this.m_next2 = this.m_next3;
          this.m_next3 = load;
          this.m_fill = 3;
        } else {
          this.m_fill = 2;
        }

        return true;
      }

      // b1, the minimum, the maximum, and b2 are all distinct points - we
      // can
      // remove none of them - it also makes no sense to try a further
      // reduction
      this.m_cur.set(otherAxis, minB);
      this.m_next1.set(otherAxis, maxB);
      this.m_next2.set(otherAxis, b2);
      return false;
    }

    // so there are three points in a vertical row
    if (StraightReducingIterator2D.__inBetween(this.m_last.get(otherAxis),
        this.m_cur.get(otherAxis), this.m_next1.get(otherAxis))) {
      // "cur" is on a vertical line with "last" and "next1" and its
      // y-coordinate is between these two's y-coordinate
      // so we delete point "cur", but we can use point "next1"
      this.__pop();
      return true;
    }

    // "cur" and "last" are on the same vertical line, but "next1" is not,
    // so we can use "cur" directly
    return false;
  }

}
