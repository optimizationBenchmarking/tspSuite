package org.logisticPlanning.utils.collections.basic;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.RandomAccess;

import org.logisticPlanning.utils.collections.conditions.Condition;
import org.logisticPlanning.utils.collections.lists.ArrayListView;

/**
 * A basic list.
 *
 * @param <ET>
 *          the element type
 */
public abstract class BasicList<ET> extends AbstractList<ET> implements
Serializable, RandomAccess {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiate the array list base
   */
  protected BasicList() {
    super();
  }

  /**
   * Select all elements that meet the condition. This method will not
   * modify the current list, it may even return the list itself. If some
   * of the elements do not meet the condition, a new list may be returned.
   * The items in the returned list will have always have the same order as
   * in the current list. No assumption can be made regarding whether the
   * returned list is modifiable or not.
   *
   * @param condition
   *          the condition
   * @return the list of these elements
   */
  @SuppressWarnings("unchecked")
  public AbstractList<ET> select(final Condition<? super ET> condition) {
    int i, j, s;
    ArrayList<ET> l;
    ET x;

    s = this.size();
    test: {
      for (i = s; (--i) >= 0;) {
        if (!(condition.check(this.get(i)))) {
          break test;
        }
      }

      return this;
    }

    l = new ArrayList<>(s);
    for (j = 0; j < i; j++) {
      x = this.get(j);
      if (condition.check(x)) {
        l.add(x);
      }
    }
    for (; (++j) < s;) {
      l.add(this.get(j));
    }

    return (l.isEmpty() ? ((AbstractList<ET>) (ArrayListView.EMPTY_LIST_VIEW))
        : l);
  }

  /**
   * Check whether there is any element that meets the condition
   *
   * @param condition
   *          the condition
   * @return {@code true} if there is any element that meets the condition,
   *         {@code false} otherwise
   */
  public boolean hasAny(final Condition<? super ET> condition) {
    for (final ET e : this) {
      if (condition.check(e)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Get the first element in the array list view
   *
   * @return the first element in the array list view
   */
  public ET first() {
    return this.get(0);
  }

  /**
   * Get the last element in the array list view
   *
   * @return the last element in the array list view
   */
  public ET last() {
    return this.get(this.size() - 1);
  }

  /**
   * Delete an element, without returning it. The order of the elements in
   * the list is preserved.
   *
   * @param index
   *          the index where the element needs to be returned
   */
  public void delete(final int index) {
    this.remove(index);
  }

  /**
   * Delete an element, without returning it. The order of the elements in
   * the list is <em>not</em> preserved.
   *
   * @param index
   *          the index where the element needs to be returned
   */
  public void deleteFast(final int index) {
    this.delete(index);
  }

  /** Sort this list */
  public void sort() {
    throw new UnsupportedOperationException();
  }
}
