package org.logisticPlanning.utils.math.data.collection;

/**
 * A part of a data collection
 */
public final class CollectionPart implements IDataCollection {

  /** the source collection */
  private final IDataCollection m_src;

  /** the start index */
  private final int m_start;

  /** the number of entries */
  private final int m_size;

  /**
   * Create the data collection part
   * 
   * @param src
   *          the source collection
   * @param start
   *          the start index
   * @param size
   *          the number of entries
   */
  public CollectionPart(final IDataCollection src, final int start,
      final int size) {
    super();

    if (src == null) {
      throw new IllegalArgumentException(//
          "Source collection must not be null."); //$NON-NLS-1$
    }
    if (start < 0) {
      throw new IllegalArgumentException(//
          "Start index must not be less than 0, but is " + start);//$NON-NLS-1$
    }

    if ((start + size) > src.size()) {
      throw new IllegalArgumentException(//
          "Start index " + start + //$NON-NLS-1$
              " + size " + size + //$NON-NLS-1$
              " exceeds list size " + src.size());//$NON-NLS-1$
    }

    this.m_src = src;
    this.m_start = start;
    this.m_size = size;

  }

  /** {@inheritDoc} */
  @Override
  public final int size() {
    return this.m_size;
  }

  /** {@inheritDoc} */
  @Override
  public final int dimension() {
    return this.m_src.dimension();
  }

  /** {@inheritDoc} */
  @Override
  public final double get(final int point, final int dimension) {
    if ((point >= this.m_size) || (point < 0)) {
      throw new IndexOutOfBoundsException("Index " + point //$NON-NLS-1$
          + " not allowed in collection of length " + this.m_size); //$NON-NLS-1$
    }
    return this.m_src.get(point + this.m_start, dimension);
  }

}
