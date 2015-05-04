package org.logisticPlanning.utils.collections.lists;

/**
 * An implementation of the {@link java.util.ArrayList} functionality
 * backed by an array of Boolean (true/false) (boolean) values. This
 * representation provides the full functionality of a
 * {@link java.util.List}, but uses the space-efficient primitive data
 * array storage. It has additional methods to speed up the access of
 * {@code long} values.
 */
public class BooleanList extends
BasicArrayList<java.lang.Boolean, boolean[]> {
  /** The serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * instantiate the {@code BooleanList}
   */
  public BooleanList() {
    this(-1);
  }

  /**
   * Instantiate the {@code BooleanList}
   *
   * @param capacity
   *          the number of valid elements in the data array
   */
  public BooleanList(final int capacity) {
    super();
    this.m_data = ((capacity == 0) ? org.logisticPlanning.utils.utils.EmptyUtils.EMPTY_BOOLEANS
        : //
          new boolean[(capacity > 0) ? capacity
              : BasicArrayList.DEFAULT_LIST_SIZE]);
  }

  /** {@inheritDoc} */
  @Override
  public final java.lang.Boolean get(final int index) {
    BasicArrayList.checkIndex(index, this.m_size);
    return (java.lang.Boolean.valueOf(this.m_data[index]));
  }

  /** {@inheritDoc} */
  @Override
  public final java.lang.Boolean set(final int index,
      final java.lang.Boolean element) {
    final java.lang.Boolean old;

    BasicArrayList.checkIndex(index, this.m_size);
    old = java.lang.Boolean.valueOf(this.m_data[index]);
    this.m_data[index] = ((element).booleanValue());
    this.modCount++;
    return old;
  }

  /** {@inheritDoc} */
  @Override
  public final void add(final int index, final java.lang.Boolean element) {
    this.add(index, ((element).booleanValue()));
  }

  /** {@inheritDoc} */
  @Override
  public final boolean add(final java.lang.Boolean element) {
    this.add(((element).booleanValue()));
    return true;
  }

  /**
   * Add/insert the Boolean (true/false) (boolean) at the given index
   *
   * @param index
   *          the index
   * @param element
   *          the boolean to be added
   */
  public final void add(final int index, final boolean element) {
    final boolean[] data;
    final int s;

    BasicArrayList.checkIndex(index, (s = (this.m_size + 1)));
    data = this.ensureFree(1, index);
    data[index] = element;
    this.m_size = s;
    this.modCount++;
  }

  /**
   * Add/insert the Boolean (true/false) (boolean) at the end of the list
   *
   * @param element
   *          the boolean to be added
   */
  public final void add(final boolean element) {
    final boolean[] data;
    final int index;

    index = this.m_size;
    data = this.ensureFree(1, index);
    data[index] = element;
    this.m_size = (index + 1);
    this.modCount++;
  }

  /** {@inheritDoc } */
  @Override
  public final void delete(final int index) {
    final boolean[] data;
    int s;

    BasicArrayList.checkIndex(index, (s = this.m_size));
    this.m_size = (--s);
    data = this.m_data;
    if (index < s) {
      System.arraycopy(data, index + 1, data, index, s - index);
    }
    this.modCount++;
  }

  /** {@inheritDoc } */
  @Override
  public final void deleteFast(final int index) {
    final boolean[] data;
    int s;

    BasicArrayList.checkIndex(index, (s = this.m_size));
    this.m_size = (--s);
    data = this.m_data;
    data[index] = data[s];
    this.modCount++;
  }

  /**
   * fill this list with a given value
   *
   * @param value
   *          the value
   */
  public final void fill(final boolean value) {
    java.util.Arrays.fill(this.m_data, 0, this.m_size, value);
    this.modCount++;
  }

  /**
   * Get the {@code boolean} at index {@code index}
   *
   * @param index
   *          the index
   * @return the {@code boolean} at that index
   */
  public final boolean getBoolean(final int index) {
    BasicArrayList.checkIndex(index, this.m_size);
    return this.m_data[index];
  }

  /**
   * Ensure that there are at least {@code size} free {@code boolean}s
   * starting at {@code index}.
   *
   * @param size
   *          the size
   * @param index
   *          the index
   * @return the internal data array {@link #m_data}. It might be different
   *         from before
   */
  @Override
  protected final boolean[] ensureFree(final int size, final int index) {
    int grow;
    final int oldSize, newSize;
    boolean[] data;

    oldSize = this.m_size;
    newSize = (oldSize + size);

    data = this.m_data;
    if (data.length >= newSize) {
      if (index < oldSize) {
        System.arraycopy(data, index, data, (index + size),
            (oldSize - index));
      }
      return data;
    }

    grow = (newSize << 1);
    if (grow < newSize) {
      grow = newSize;
    }

    data = new boolean[grow];
    if (index < oldSize) {
      System.arraycopy(this.m_data, 0, data, 0, index);
      System.arraycopy(this.m_data, index, data, (index + size),//
          (oldSize - index));
    } else {
      System.arraycopy(this.m_data, 0, data, 0, oldSize);
    }
    this.m_data = data;

    return data;
  }

  /** {@inheritDoc} */
  @Override
  public final boolean[] toDataArray() {
    final boolean[] data, ret;
    final int s;

    s = this.m_size;
    if (s <= 0) {
      return org.logisticPlanning.utils.utils.EmptyUtils.EMPTY_BOOLEANS;
    }

    data = this.m_data;
    if (s >= data.length) {
      return data.clone();
    }

    ret = new boolean[s];
    System.arraycopy(data, 0, ret, 0, s);

    return ret;
  }

  /**
   * copy the list's data to an array
   *
   * @param array
   *          the array
   * @param data
   *          the data
   * @param size
   *          the size
   */
  private static final void __toArray(final Object[] array,
      final boolean[] data, final int size) {
    int i;

    for (i = size; (--i) >= 0;) {
      array[i] = java.lang.Boolean.valueOf(data[i]);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final Object[] toArray() {
    final Object[] array;

    array = new Object[this.m_size];
    BooleanList.__toArray(array, this.m_data, this.m_size);

    return array;
  }

  /** {@inheritDoc} */
  @Override
  @SuppressWarnings("unchecked")
  public final <T> T[] toArray(final T[] a) {
    final T[] arr;
    final int size;

    size = this.m_size;

    if (a.length >= size) {
      arr = a;
      if (a.length > size) {
        arr[size] = null;
      }
    } else {
      arr = ((T[]) (java.lang.reflect.Array.newInstance(a.getClass()
          .getComponentType(), size)));
    }
    BooleanList.__toArray(arr, this.m_data, size);

    return arr;
  }

  /** {@inheritDoc} */
  @Override
  public void sort() {
    final boolean[] data;
    final int s;
    int i, tc;

    data = this.m_data;
    s = this.m_size;
    tc = 0;
    for (i = s; (--i) >= 0;) {
      if (data[i]) {
        tc++;
      }
    }
    tc = (s - tc);
    java.util.Arrays.fill(data, 0, tc, false);
    java.util.Arrays.fill(data, tc, s, true);

    this.modCount++;
  }
}
