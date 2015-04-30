package org.logisticPlanning.utils.collections.conditions;

/**
 * A condition checking whether an object is not null.
 */
public final class NotNullCondition extends Condition<Object> {

  /** the not-empty condition */
  public static final NotNullCondition INSTANCE = new NotNullCondition();

  /** create */
  private NotNullCondition() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final boolean check(final Object param) {
    return (param != null);
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equals(final Object o) {
    return (o instanceof NotNullCondition);
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return NotNullCondition.class.hashCode();
  }
}
