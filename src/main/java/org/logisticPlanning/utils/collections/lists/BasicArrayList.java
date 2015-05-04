package org.logisticPlanning.utils.collections.lists;

import java.util.AbstractList;

import org.logisticPlanning.utils.collections.basic.BasicList;

/**
 * The base class for array-based collections.
 *
 * @param <ET>
 *          the element type
 * @param <AT>
 *          the array type
 */
public abstract class BasicArrayList<ET, AT> extends BasicList<ET>
implements Cloneable {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the default size for empty lists */
  static final int DEFAULT_LIST_SIZE = 16;

  /** @serial the data array */
  protected AT m_data;

  /** @serial the list size */
  protected int m_size;

  /**
   * Instantiate the array list base
   */
  BasicArrayList() {
    super();
  }

  /**
   * Ensure that there are at least {@code size} free elements starting at
   * {@code index}.
   *
   * @param size
   *          the size
   * @param index
   *          the index
   * @return the internal data array {@link #m_data}. It might be different
   *         from before
   */
  protected abstract AT ensureFree(int size, int index);

  /** {@inheritDoc} */
  @Override
  public void delete(final int index) {
    final AT data;
    int s;

    BasicArrayList.checkIndex(index, (s = this.m_size));

    this.m_size = (--s);
    data = this.m_data;
    if (index < s) {
      System.arraycopy(data, (index + 1), data, index, (s - index));
    }
  }

  /**
   * Get a copy the array with the internal data
   *
   * @return the copied array
   */
  public abstract AT toDataArray();

  /** {@inheritDoc} */
  @Override
  public void deleteFast(final int index) {
    int s;

    BasicArrayList.checkIndex(0, (s = this.m_size));
    this.m_size = (--s);
    System.arraycopy(this.m_data, s, this.m_data, index, 1);
  }

  /** {@inheritDoc } */
  @Override
  public void clear() {
    this.m_size = 0;
  }

  /** {@inheritDoc} */
  @Override
  public final int size() {
    return this.m_size;
  }

  /** {@inheritDoc } */
  @Override
  public ET remove(final int index) {
    ET v;
    int s, i;

    BasicArrayList.checkIndex(index, (s = this.m_size));
    v = this.get(index);

    i = (index + 1);
    if (s > i) {
      System.arraycopy(this.m_data, i, this.m_data, index, s - i);
    }
    this.m_size = (s - 1);
    this.modCount++;

    return v;
  }

  /** {@inheritDoc} */
  @Override
  @SuppressWarnings("unchecked")
  public AbstractList<ET> clone() {
    BasicArrayList<ET, AT> r;

    try {
      r = ((BasicArrayList<ET, AT>) (super.clone()));
    } catch (final CloneNotSupportedException cnse) {
      throw new RuntimeException(cnse);
    }

    r.m_data = r.toDataArray();
    r.modCount = 0;

    return r;
  }

  /**
   * check an index
   *
   * @param index
   *          the index
   * @param size
   *          the size
   */
  static final void checkIndex(final int index, final int size) {
    if ((index < 0) || (index >= size)) {
      throw new IndexOutOfBoundsException(//
          "Index " + index + //$NON-NLS-1$
          " is not valid for a list with length " + size); //$NON-NLS-1$
    }
  }

}
