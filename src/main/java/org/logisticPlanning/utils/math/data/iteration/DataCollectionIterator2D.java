package org.logisticPlanning.utils.math.data.iteration;

import org.logisticPlanning.utils.math.data.collection.IDataCollection;
import org.logisticPlanning.utils.math.data.point.Point;
import org.logisticPlanning.utils.math.data.point.Point2D;

/**
 * An iterator that wraps a data collection.
 */
public final class DataCollectionIterator2D extends _PointIterator2D {
  /** The serial version uid */
  private static final long serialVersionUID = 1L;

  /** the dimension error */
  private static final String DIM_ERROR = "Data dimension must be 2, but is "; //$NON-NLS-1$

  /** the left point */
  private final Point m_left;

  /** the right point */
  private final Point m_right;

  /** the data collection */
  private final IDataCollection m_data;

  /** the index */
  private int m_index;

  /** the size */
  private final int m_size;

  /** the variable to hold the next point loaded with this iterator */
  private final Point2D m_load;

  /**
   * instantiate
   *
   * @param left
   *          the left point
   * @param right
   *          the right point
   * @param data
   *          the data
   */
  public DataCollectionIterator2D(final Point left,
      final IDataCollection data, final Point right) {
    super();

    int i;

    if (left != null) {
      i = left.dimension();
      if (i != 2) {
        throw new IllegalArgumentException(
            DataCollectionIterator2D.DIM_ERROR + i);
      }
    }

    if (right != null) {
      i = right.dimension();
      if (i != 2) {
        throw new IllegalArgumentException(
            DataCollectionIterator2D.DIM_ERROR + i);
      }
    }

    if (data != null) {
      i = data.dimension();
      if (i != 2) {
        throw new IllegalArgumentException(
            DataCollectionIterator2D.DIM_ERROR + i);
      }
      this.m_size = data.size();
    } else {
      this.m_size = 0;
    }

    this.m_left = left;
    this.m_right = right;
    this.m_data = data;
    this.m_index = ((left != null) ? (-2) : (-1));

    if ((this.m_size <= 0) && (this.m_left == null)
        && (this.m_right == null)) {
      throw new IllegalArgumentException(//
          "Points must not be empty."); //$NON-NLS-1$
    }

    this.m_load = new Point2D();
  }

  /**
   * instantiate
   *
   * @param data
   *          the data
   */
  public DataCollectionIterator2D(final IDataCollection data) {
    this(null, data, null);
  }

  /** {@inheritDoc} */
  @Override
  final Point2D _loadNextPoint() {
    final int i;
    final Point2D p;

    i = (++this.m_index);

    p = this.m_load;

    // ok, there is a next point: load it into "next"
    if (i < 0) {// the point is the "left extremum"
      p.assign(this.m_left);
      return p;
    }

    if (i >= this.m_size) {// the point is the "right extremum"
      if (this.m_right != null) {
        if (i == this.m_size) {
          p.assign(this.m_right);
          return p;
        }
      }
      return null;
    }

    // the point is from the data collection
    p.fromDataCollection(this.m_data, i);
    return p;
  }
}
