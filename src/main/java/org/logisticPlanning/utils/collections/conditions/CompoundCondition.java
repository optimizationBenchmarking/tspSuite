package org.logisticPlanning.utils.collections.conditions;

import org.logisticPlanning.utils.math.functions.logic.BinaryBooleanFunction;
import org.logisticPlanning.utils.utils.HashUtils;

/**
 * A combination of two conditions.
 *
 * @param <T>
 *          the element type this condition applies to.
 */
public final class CompoundCondition<T> extends Condition<T> {

  /** a the combination function */
  private final BinaryBooleanFunction m_f;
  /** the first condition */
  private final Condition<? super T> m_a;
  /** the second condition */
  private final Condition<? super T> m_b;
  /** the hash code */
  private final int m_hc;

  /**
   * The condition
   *
   * @param f
   *          the combining function
   * @param a
   *          the first condition
   * @param b
   *          the second condition
   */
  public CompoundCondition(final BinaryBooleanFunction f,
      final Condition<? super T> a, final Condition<? super T> b) {
    super();

    if ((f == null) || (a == null) || (b == null)) {
      throw new IllegalArgumentException();
    }

    this.m_f = f;
    this.m_a = a;
    this.m_b = b;
    this.m_hc = HashUtils.combineHashes(//
        HashUtils.combineHashes(//
            HashUtils.hashCode(this.m_f),//
            HashUtils.hashCode(this.m_a)),//
            HashUtils.combineHashes(//
                HashUtils.hashCode(this.m_b),//
                HashUtils.hashCode(this.getClass())));//
  }

  /** {@inheritDoc} */
  @Override
  public final boolean check(final T param) {
    final boolean a;

    a = this.m_a.check(param);
    switch (this.m_f.lazyResultForFirstParam(a)) {
      case (-1): {
        return false;
      }
      case 1: {
        return true;
      }
      default: {
        return this.m_f.compute(a, this.m_b.check(param));
      }
    }
  }

  /** {@inheritDoc} */
  @SuppressWarnings("rawtypes")
  @Override
  public final boolean equals(final Object o) {
    final CompoundCondition c;

    if (o == this) {
      return true;
    }

    if (o instanceof CompoundCondition) {
      c = ((CompoundCondition) o);
      return ((this.m_hc == c.m_hc) && this.m_f.equals(c.m_f)
          && this.m_a.equals(c.m_a) && this.m_b.equals(c.m_b));
    }
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return this.m_hc;
  }

}
