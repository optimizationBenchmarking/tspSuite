package org.logisticPlanning.utils.math.data.collection;

import java.util.List;

import org.logisticPlanning.utils.math.data.point.Point;

/**
 * A view on a point list
 */
public final class SubListView extends _SubView {
  /** the list */
  private final List<Point> m_list;

  /**
   * Create a new point list view
   *
   * @param list
   *          the list
   * @param dims
   *          the dimensions
   */
  @SuppressWarnings("unchecked")
  public SubListView(final List<? extends Point> list, final int[] dims) {
    super();

    if ((dims == null) || (dims.length <= 0)) {
      throw new IllegalArgumentException(_SubView.DIM_ERROR);
    }

    this.m_dims = dims;
    this.m_list = ((List<Point>) (list));
  }

  /** {@inheritDoc} */
  @Override
  public final int size() {
    return this.m_list.size();
  }

  /** {@inheritDoc} */
  @Override
  public final double get(final int point, final int dimension) {
    return this.m_list.get(point).get(this.m_dims[dimension]);
  }
}
