package org.logisticPlanning.utils.math.data.collection;

/**
 * A view on a point list
 */
abstract class _SubView implements IDataCollection {

  /** the dimension error */
  static final String DIM_ERROR = "Dimensions must not be empty or null."; //$NON-NLS-1$

  /** the dimensions */
  int[] m_dims;

  /**
   * Create a new point list view
   */
  _SubView() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final int dimension() {
    return this.m_dims.length;
  }

}
