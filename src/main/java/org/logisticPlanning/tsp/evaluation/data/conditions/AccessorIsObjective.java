package org.logisticPlanning.tsp.evaluation.data.conditions;

import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.utils.collections.conditions.Condition;

/**
 * A condition that is {@code true} if an accessor represents objective
 * values.
 */
public class AccessorIsObjective extends Condition<Accessor> {

  /** the globally shared static instance */
  public static final AccessorIsObjective INSTANCE = new AccessorIsObjective();

  /** create */
  private AccessorIsObjective() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final boolean check(final Accessor param) {
    return ((param != null) && (param.isObjective()));
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equals(final Object o) {
    return (o instanceof AccessorIsObjective);
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return AccessorIsObjective.class.hashCode();
  }

}
