package org.logisticPlanning.utils.math.data.collection;

/**
 * A reverse collection
 */
public final class ReverseCollection implements IDataCollection {
  /** the source collection */
  private final IDataCollection m_src;

  /**
   * Create reverse collection view
   *
   * @param src
   *          the source collection
   */
  public ReverseCollection(final IDataCollection src) {
    super();

    if (src == null) {
      throw new IllegalArgumentException(//
          "Collection must not be null."); //$NON-NLS-1$
    }

    this.m_src = src;
  }

  /** {@inheritDoc} */
  @Override
  public final int dimension() {
    return this.m_src.dimension();
  }

  /** {@inheritDoc} */
  @Override
  public final int size() {
    return this.m_src.size();
  }

  /** {@inheritDoc} */
  @Override
  public final double get(final int point, final int dimension) {
    return this.m_src.get((this.m_src.size() - point - 1), dimension);
  }
}
