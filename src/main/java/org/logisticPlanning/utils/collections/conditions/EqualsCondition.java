package org.logisticPlanning.utils.collections.conditions;

import org.logisticPlanning.utils.utils.HashUtils;
import org.logisticPlanning.utils.utils.comparison.ComparisonUtils;

/**
 * A condition checking whether an object equals to another one.
 */
public final class EqualsCondition extends Condition<Object> {

  /** the object */
  private final Object m_obj;

  /** the hash code */
  private final int m_hc;

  /**
   * create
   * 
   * @param obj
   *          the object
   */
  public EqualsCondition(final Object obj) {
    super();
    this.m_obj = obj;
    this.m_hc = HashUtils.combineHashes(//
        HashUtils.hashCode(this.getClass()),//
        HashUtils.hashCode(obj));
  }

  /** {@inheritDoc} */
  @Override
  public final boolean check(final Object param) {
    return ComparisonUtils.equals(param, this.m_obj);
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equals(final Object o) {
    return ((this == o) || ((o instanceof EqualsCondition) && ComparisonUtils
        .equals(this.m_obj, ((EqualsCondition) o).m_obj)));
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return this.m_hc;
  }
}
