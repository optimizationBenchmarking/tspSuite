package org.logisticPlanning.utils.math.data.collection;

import org.logisticPlanning.utils.utils.EmptyUtils;

/**
 * A data collection view on a {@code double} array.
 */
public class ArrayDataCollectionView implements IDataCollection {

  /** the data */
  protected final double[] m_data;

  /** the size */
  protected final int m_size;

  /** the dimension */
  protected final int m_dim;

  /**
   * Instantiate
   * 
   * @param data
   *          the data
   * @param size
   *          the size
   * @param dim
   *          the dimension
   */
  public ArrayDataCollectionView(final double[] data, final int size,
      final int dim) {
    super();

    if (size < 0) {
      throw new IllegalArgumentException(//
          "Size must be larger or equal to zero, but is " + size);//$NON-NLS-1$
    }

    if (dim <= 0) {
      throw new IllegalArgumentException(//
          "Dimension must be larger than zero, but is " + dim);//$NON-NLS-1$
    }

    if ((size * dim) > data.length) {
      throw new IllegalArgumentException(//
          "Data array with length " + data.length + //$NON-NLS-1$
              " too short to facilitate " + size + //$NON-NLS-1$
              " points of dimension " + dim);//$NON-NLS-1$
    }

    this.m_data = ((size > 0) ? data : EmptyUtils.EMPTY_DOUBLES);
    this.m_size = size;
    this.m_dim = dim;
  }

  /** {@inheritDoc} */
  @Override
  public final int size() {
    return this.m_size;
  }

  /** {@inheritDoc} */
  @Override
  public final int dimension() {
    return this.m_dim;
  }

  /** {@inheritDoc} */
  @Override
  public final double get(final int point, final int dimension) {
    if (point < this.m_size) {
      if ((dimension >= 0) && (dimension < this.m_dim)) {
        return this.m_data[(point * this.m_dim) + dimension];
      }
      throw new IndexOutOfBoundsException(//
          "Dimension " + dimension //$NON-NLS-1$
              + " not allowed in " + this.m_dim + //$NON-NLS-1$
              "-dimensional collection."); //$NON-NLS-1$
    }
    throw new IndexOutOfBoundsException(//
        "Index " + point //$NON-NLS-1$
            + " not allowed in collection of length " + this.m_size); //$NON-NLS-1$
  }

}
