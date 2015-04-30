package org.logisticPlanning.utils.collections.conditions;

import java.util.Collection;

import org.logisticPlanning.utils.utils.HashUtils;

/**
 * A collective condition is a condition which is checked on the elements
 * of a collection.
 * 
 * @param <T>
 *          the element type this condition applies to.
 */
public final class CollectiveCondition<T> extends
    Condition<Collection<? extends T>> {

  /** the condition */
  private final Condition<? super T> m_condition;

  /**
   * are the condition results combined with <em>and</em> ({@code true}) or
   * <em>or</em> ({@code false})?
   */
  private final boolean m_and;

  /** the hash code */
  private final int m_hc;

  /**
   * Create a condition that is applied to the contents of a collection. If
   * the condition is an {@code and} condition, it will be {@code true} if
   * and only if no element of a collection violates the sub-condition. If
   * the condition is an {@code or} condition, it will be {@code true} if
   * and only if at least one element in a collection meets the
   * sub-condition.
   * 
   * @param condition
   *          condition
   * @param and
   *          are the condition results combined with <em>and</em> (
   *          {@code true} ) or <em>or</em> ({@code false})?
   */
  public CollectiveCondition(final Condition<? super T> condition,
      final boolean and) {
    super();
    this.m_condition = condition;
    this.m_and = and;
    this.m_hc = HashUtils.combineHashes(//
        HashUtils.hashCode(this.m_and),//
        HashUtils.combineHashes(//
            HashUtils.hashCode(this.m_condition),//
            HashUtils.hashCode(this.getClass())));
  }

  /** {@inheritDoc} */
  @Override
  public final boolean check(final Collection<? extends T> param) {
    final Condition<? super T> c;
    final boolean and;
    boolean res;

    c = this.m_condition;
    and = this.m_and;
    for (final T x : param) {
      res = c.check(x);
      if (res ^ and) {
        return res;
      }
    }

    return (and ? true : (param.isEmpty()));
  }

  /** {@inheritDoc} */
  @SuppressWarnings("rawtypes")
  @Override
  public final boolean equals(final Object o) {
    final CollectiveCondition c;
    if (o == this) {
      return true;
    }

    if (o instanceof CollectiveCondition) {
      c = ((CollectiveCondition) o);
      return ((this.m_and == c.m_and) && //
      (this.m_condition.equals(c.m_condition)));
    }
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return this.m_hc;
  }

}
