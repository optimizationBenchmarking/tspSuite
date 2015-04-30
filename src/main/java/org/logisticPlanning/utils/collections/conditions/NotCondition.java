package org.logisticPlanning.utils.collections.conditions;

import org.logisticPlanning.utils.utils.HashUtils;

/**
 * The negation of a condition
 * 
 * @param <T>
 *          the element type this condition applies to.
 */
public final class NotCondition<T> extends Condition<T> {

  /** the condition */
  private final Condition<? super T> m_c;
  /** the hash code */
  private final int m_hc;

  /**
   * The condition
   * 
   * @param c
   *          the condition
   */
  public NotCondition(final Condition<? super T> c) {
    super();

    if (c == null) {
      throw new IllegalArgumentException();
    }

    this.m_c = c;
    this.m_hc = HashUtils.combineHashes(//
        HashUtils.hashCode(this.m_c),//
        HashUtils.hashCode(this.getClass()));
  }

  /** {@inheritDoc} */
  @Override
  public final boolean check(final T param) {
    return (!(this.m_c.check(param)));
  }

  /** {@inheritDoc} */
  @SuppressWarnings("rawtypes")
  @Override
  public final boolean equals(final Object o) {
    final NotCondition c;

    if (o == this) {
      return true;
    }

    if (o instanceof NotCondition) {
      c = ((NotCondition) o);
      return ((this.m_hc == c.m_hc) && this.m_c.equals(c.m_c));
    }
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return this.m_hc;
  }

}
