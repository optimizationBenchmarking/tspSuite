package org.logisticPlanning.utils.collections.basic;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * The base class for iterators that unites the behavior of both, the
 * {@link java.util.Iterator} and {@link java.util.Enumeration} interface.
 *
 * @param <T>
 *          the type to iterate about
 */
public class BasicListIterator<T> extends BasicIterator<T> implements
    ListIterator<T> {
  /** a list iterator iterating over nothing */
  public static final BasicListIterator<Object> EMPTY_LIST_ITERATOR = new BasicListIterator<>();

  /** The serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiate the iterator base
   */
  protected BasicListIterator() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public boolean hasPrevious() {
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public T previous() {
    throw new NoSuchElementException();
  }

  /** {@inheritDoc} */
  @Override
  public int nextIndex() {
    return 0;
  }

  /** {@inheritDoc} */
  @Override
  public int previousIndex() {
    return -1;
  }

  /** {@inheritDoc} */
  @Override
  public void set(final T e) {
    throw new UnsupportedOperationException();
  }

  /** {@inheritDoc} */
  @Override
  public void add(final T e) {
    throw new UnsupportedOperationException();
  }
}
