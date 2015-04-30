package org.logisticPlanning.utils.collections.lists;

import org.logisticPlanning.utils.math.data.collection.IDataCollection;

/**
 * The base class for collections that can be viewed as functions.
 * 
 * @param <ET>
 *          the base type (normally {@link java.lang.Number})
 * @param <AT>
 *          the array type
 */
abstract class _BasicNumericList<ET, AT> extends BasicArrayList<ET, AT>
    implements IDataCollection {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiate the numeric list
   */
  _BasicNumericList() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final int dimension() {
    return 1;
  }

  /**
   * get the double at the given index
   * 
   * @param index
   *          the index
   * @return the double at that index
   */
  public abstract double getDouble(final int index);

  /** {@inheritDoc} */
  @Override
  public final double get(final int point, final int dimension) {
    if (dimension != 0) {
      throw new IllegalArgumentException("Invalid dimension: " + //$NON-NLS-1$
          dimension);
    }
    return 0;
  }
}
