package org.logisticPlanning.utils.math.data.collection;

/**
 * A view on a point view
 */
public final class SubCollectionView extends _SubView {
  /** the view */
  private final IDataCollection m_view;

  /**
   * Create a new point view view
   *
   * @param view
   *          the view
   * @param dims
   *          the dimensions
   */
  public SubCollectionView(final IDataCollection view, final int[] dims) {
    super();

    final SubCollectionView plv;

    if ((dims == null) || (dims.length <= 0)) {
      throw new IllegalArgumentException(_SubView.DIM_ERROR);
    }

    if (view instanceof SubCollectionView) {
      plv = ((SubCollectionView) view);
      this.m_view = plv.m_view;
      this.m_dims = SubCollectionView.__unpackDimensions(dims, plv.m_dims);
    } else {
      this.m_view = view;
      this.m_dims = dims;
    }
  }

  /** {@inheritDoc} */
  @Override
  public final int size() {
    return this.m_view.size();
  }

  /** {@inheritDoc} */
  @Override
  public final double get(final int point, final int dimension) {
    return this.m_view.get(point, this.m_dims[dimension]);
  }

  /**
   * unpack the dimensions
   *
   * @param subDims
   *          the sub dimensions
   * @param dims
   *          the dimensions
   * @return the unpacked dimensions
   */
  private static final int[] __unpackDimensions(final int[] subDims,
      final int[] dims) {
    final int[] res;
    int i, j;
    boolean dif;

    i = subDims.length;
    res = new int[i];
    dif = true;
    for (; (--i) >= 0;) {
      j = subDims[i];
      if ((res[i] = dims[j]) != j) {
        dif = false;
      }
    }

    if (dif) {
      return subDims;
    }
    return res;
  }
}
