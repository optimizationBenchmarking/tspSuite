package org.logisticPlanning.utils.collections.basic;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The base class for iterators that unites the behavior of both, the
 * {@link java.util.Iterator} and {@link java.util.Enumeration} interface.
 *
 * @param <T>
 *          the type to iterate about
 */
public class BasicIterator<T> implements Iterator<T>, Enumeration<T>,
    Serializable {

  /** The serial version uid */
  private static final long serialVersionUID = 1L;

  /** an iterator iterating over nothing */
  public static final BasicIterator<Object> EMPTY_ITERATOR = new BasicIterator<>();

  /**
   * Instantiate the iterator base
   */
  protected BasicIterator() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final boolean hasMoreElements() {
    return this.hasNext();
  }

  /** {@inheritDoc} */
  @Override
  public final T nextElement() {
    return this.next();
  }

  /** {@inheritDoc} */
  @Override
  public boolean hasNext() {
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public T next() {
    throw new NoSuchElementException();
  }

  /** {@inheritDoc} */
  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }
}
