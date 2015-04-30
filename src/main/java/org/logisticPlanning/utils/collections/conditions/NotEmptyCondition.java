package org.logisticPlanning.utils.collections.conditions;

import java.util.Collection;

/**
 * A condition checking whether a collection is empty.
 */
public final class NotEmptyCondition extends Condition<Collection<?>> {

  /** the not-empty condition */
  public static final NotEmptyCondition INSTANCE = new NotEmptyCondition();

  /** create */
  private NotEmptyCondition() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final boolean check(final Collection<?> param) {
    return ((param != null) && (!(param.isEmpty())));
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equals(final Object o) {
    return (o instanceof NotEmptyCondition);
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return NotEmptyCondition.class.hashCode();
  }
}
