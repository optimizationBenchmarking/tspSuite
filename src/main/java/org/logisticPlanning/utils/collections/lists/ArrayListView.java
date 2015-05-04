package org.logisticPlanning.utils.collections.lists;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

import org.logisticPlanning.utils.collections.basic.BasicIterator;
import org.logisticPlanning.utils.collections.basic.BasicList;
import org.logisticPlanning.utils.collections.conditions.Condition;
import org.logisticPlanning.utils.collections.iterators.ArrayIterator;
import org.logisticPlanning.utils.utils.EmptyUtils;

/**
 * An immutable {@link java.util.List list} view on an array.
 *
 * @param <DT>
 *          the type
 */
public class ArrayListView<DT> extends BasicList<DT> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the data */
  protected final DT[] m_data;

  /** the empty list view */
  public static final ArrayListView<Object> EMPTY_LIST_VIEW = new ArrayListView<>(
      EmptyUtils.EMPTY_OBJECTS);

  /**
   * instantiate
   *
   * @param data
   *          the data of the set - will not be copied or cloned, but used
   *          directly
   */
  @SuppressWarnings("unchecked")
  protected ArrayListView(final DT[] data) {
    super();

    if (data == null) {
      throw new IllegalArgumentException("Data must not be null."); //$NON-NLS-1$
    }

    this.m_data = ((data.length <= 0) ? ((DT[]) (EmptyUtils.EMPTY_OBJECTS))
        : data);
  }

  /**
   * copy another list view
   *
   * @param copy
   *          the other list view
   */
  protected ArrayListView(final ArrayListView<? extends DT> copy) {
    this(copy.m_data);
  }

  /** {@inheritDoc} */
  @Override
  public final int size() {
    return this.m_data.length;
  }

  /** {@inheritDoc} */
  @Override
  public final DT get(final int index) {
    return this.m_data[index];
  }

  /** {@inheritDoc} */
  @Override
  @SuppressWarnings("unchecked")
  public final Iterator<DT> iterator() {
    return ((this.m_data.length <= 0) ? ((Iterator<DT>) (BasicIterator.EMPTY_ITERATOR))
        : new ArrayIterator<>(this.m_data));
  }

  /** {@inheritDoc} */
  @Override
  public final boolean isEmpty() {
    return (this.m_data.length <= 0);
  }

  /** {@inheritDoc} */
  @Override
  public boolean contains(final Object o) {
    return (this.indexOf(o) >= 0);
  }

  /** {@inheritDoc} */
  @Override
  public final Object[] toArray() {
    return this.m_data.clone();
  }

  /** {@inheritDoc} */
  @Override
  @SuppressWarnings("unchecked")
  public final <T> T[] toArray(final T[] a) {
    final T[] out;
    final int len;

    len = this.m_data.length;

    out = ((a.length >= len) ? a : //
      ((T[]) (java.lang.reflect.Array.newInstance(//
          a.getClass().getComponentType(), len))));
    System.arraycopy(this.m_data, 0, out, 0, len);
    if (len < out.length) {
      out[len] = null;
    }
    return out;
  }

  /** {@inheritDoc} */
  @Override
  public boolean containsAll(final Collection<?> c) {
    for (final Object x : c) {
      if (this.indexOf(x) < 0) {
        return false;
      }
    }
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public final boolean addAll(final Collection<? extends DT> c) {
    throw new UnsupportedOperationException();
  }

  /** {@inheritDoc} */
  @Override
  public final boolean addAll(final int index,
      final Collection<? extends DT> c) {
    throw new UnsupportedOperationException();
  }

  /** {@inheritDoc} */
  @Override
  public final boolean removeAll(final Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  /** {@inheritDoc} */
  @Override
  public final boolean retainAll(final Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  /** {@inheritDoc} */
  @Override
  public final void clear() {
    throw new UnsupportedOperationException();
  }

  /** {@inheritDoc} */
  @Override
  public final DT set(final int index, final DT element) {
    throw new UnsupportedOperationException();
  }

  /** {@inheritDoc} */
  @Override
  public final void add(final int index, final DT element) {
    throw new UnsupportedOperationException();
  }

  /** {@inheritDoc} */
  @Override
  public final boolean add(final DT element) {
    throw new UnsupportedOperationException();
  }

  /** {@inheritDoc} */
  @Override
  public final DT remove(final int index) {
    throw new UnsupportedOperationException();
  }

  /** {@inheritDoc} */
  @Override
  public int indexOf(final Object o) {
    final DT[] data;
    int idx;

    data = this.m_data;
    if (o == null) {
      for (idx = 0; idx < data.length; idx++) {
        if (data[idx] == null) {
          return idx;
        }
      }
      return (-1);
    }
    for (idx = 0; idx < data.length; idx++) {
      if (o.equals(data[idx])) {
        return idx;
      }
    }
    return (-1);
  }

  /** {@inheritDoc} */
  @Override
  public int lastIndexOf(final Object o) {
    final DT[] data;
    int idx;

    data = this.m_data;
    if (o == null) {
      for (idx = data.length; (--idx) >= 0;) {
        if (data[idx] == null) {
          return idx;
        }
      }
      return (-1);
    }
    for (idx = data.length; (--idx) >= 0;) {
      if (o.equals(data[idx])) {
        return idx;
      }
    }
    return (-1);
  }

  /** {@inheritDoc} */
  @Override
  public final ListIterator<DT> listIterator() {
    return new ArrayIterator<>(this.m_data);
  }

  /** {@inheritDoc} */
  @Override
  public final ListIterator<DT> listIterator(final int index) {
    return new ArrayIterator<>(this.m_data, this.m_data.length, index);
  }

  /** {@inheritDoc} */
  @Override
  protected final void removeRange(final int fromIndex, final int toIndex) {
    throw new UnsupportedOperationException();
  }

  /** {@inheritDoc} */
  @Override
  public final DT first() {
    return this.m_data[0];
  }

  /** {@inheritDoc} */
  @Override
  public final DT last() {
    return this.m_data[this.m_data.length - 1];
  }

  /**
   * Make an array list view from a given set of data
   *
   * @param data
   *          the data array to be wrapped into an array list view - will
   *          not be copied or cloned, but used directly
   * @return the array list view representing the data
   * @param <T>
   *          the type of the array elements
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public static final <T> ArrayListView<T> makeArrayListView(final T[] data) {
    return (((data != null) && (data.length > 0)) ? new ArrayListView(data)
    : ((ArrayListView) (ArrayListView.EMPTY_LIST_VIEW)));
  }

  /**
   * selec a set of elements final _SubArrayListBuf _select(final
   * Condition<? super DT> condition) { } /** {@inheritDoc}
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public ArrayListView<DT> select(final Condition<? super DT> condition) {
    DT[] data;
    DT x;
    int i, s, len;

    data = this.m_data;
    len = s = data.length;

    if (s <= 0) {
      return ((ArrayListView) (ArrayListView.EMPTY_LIST_VIEW));
    }

    check: {
      for (i = 0; i < len; i++) {
        if (condition.check(data[i])) {
          continue;
        }
        break check;
      }
      return this;
    }

    s = i;
    for (; (++i) < len;) {
      x = data[i];
      if (condition.check(x)) {
        if (data == this.m_data) {
          data = data.clone();
        }
        data[s++] = x;
      }
    }

    if (s <= 0) {
      return ((ArrayListView) (ArrayListView.EMPTY_LIST_VIEW));
    }

    return new ArrayListView<>(Arrays.copyOf(data, s));
  }

  /** {@inheritDoc} */
  @Override
  public final boolean hasAny(final Condition<? super DT> condition) {
    for (final DT x : this.m_data) {
      if (condition.check(x)) {
        return true;
      }
    }
    return false;
  }

}
