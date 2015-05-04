package org.logisticPlanning.utils.math.data.collection;

import java.util.List;

import org.logisticPlanning.utils.math.data.point.Point;

/**
 * A view on a point list
 */
public final class ListCollection implements IDataCollection {
  /** the list */
  private final List<Point> m_list;

  /** the dimension */
  private final int m_dim;

  /**
   * Create a new point list view
   *
   * @param list
   *          the list
   * @param dim
   *          the dimension
   */
  @SuppressWarnings("unchecked")
  public ListCollection(final List<? extends Point> list, final int dim) {
    super();

    if (dim <= 0) {
      throw new IllegalArgumentException(//
          "Dimension must be larger than 0."); //$NON-NLS-1$
    }

    this.m_dim = dim;
    this.m_list = ((List<Point>) (list));
  }

  /** {@inheritDoc} */
  @Override
  public final int dimension() {
    return this.m_dim;
  }

  /** {@inheritDoc} */
  @Override
  public final int size() {
    return this.m_list.size();
  }

  /** {@inheritDoc} */
  @Override
  public final double get(final int point, final int dimension) {
    return this.m_list.get(point).get(dimension);
  }
}
