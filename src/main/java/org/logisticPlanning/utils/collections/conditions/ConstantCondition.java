package org.logisticPlanning.utils.collections.conditions;

import org.logisticPlanning.utils.utils.HashUtils;

/**
 * A condition that is constant.
 */
public final class ConstantCondition extends Condition<Object> {

  /** the {@code true} condition */
  public static final ConstantCondition TRUE = new ConstantCondition(true);

  /** the {@code false} condition */
  public static final ConstantCondition FALSE = new ConstantCondition(
      false);

  /** the value */
  private final boolean m_value;

  /** the hash code */
  private final int m_hc;

  /**
   * create
   * 
   * @param value
   *          the value
   */
  private ConstantCondition(final boolean value) {
    super();
    this.m_value = value;
    this.m_hc = HashUtils.combineHashes(//
        HashUtils.hashCode(this.m_value),//
        HashUtils.hashCode(this.getClass()));
  }

  /** {@inheritDoc} */
  @Override
  public final boolean check(final Object param) {
    return this.m_value;
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equals(final Object o) {
    return ((o == this) || ((o instanceof ConstantCondition) && (((ConstantCondition) o).m_value == this.m_value)));
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return this.m_hc;
  }
}
