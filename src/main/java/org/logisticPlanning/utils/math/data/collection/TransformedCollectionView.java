package org.logisticPlanning.utils.math.data.collection;

import org.logisticPlanning.utils.math.functions.UnaryFunction;

/**
 * a transformed collection view
 */
public final class TransformedCollectionView implements IDataCollection {

  /** the source collection */
  private final IDataCollection m_source;

  /** the transformation */
  private final UnaryFunction[] m_transform;

  /**
   * create
   *
   * @param source
   *          the source collection
   * @param transform
   *          the transformation
   */
  public TransformedCollectionView(final IDataCollection source,
      final UnaryFunction[] transform) {
    super();

    if (transform.length > source.dimension()) {
      throw new IllegalArgumentException(
          "Transformation dimension is " + transform.length + //$NON-NLS-1$
          " but must not be greater than collection dimension which is " //$NON-NLS-1$
          + source.dimension());
    }
    this.m_source = source;
    this.m_transform = transform;
  }

  /** {@inheritDoc} */
  @Override
  public final int size() {
    return this.m_source.size();
  }

  /** {@inheritDoc} */
  @Override
  public final int dimension() {
    return this.m_transform.length;
  }

  /** {@inheritDoc} */
  @Override
  public final double get(final int point, final int dimension) {
    return this.m_transform[dimension].compute(//
        this.m_source.get(point, dimension));
  }
}
