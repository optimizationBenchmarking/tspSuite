package org.logisticPlanning.tsp.evaluation.data.conditions;

import org.logisticPlanning.tsp.evaluation.data.Run;
import org.logisticPlanning.utils.collections.conditions.Condition;

/**
 * A condition that is {@code true} if a run was not prematurely
 * terminated.
 */
public class RunNotPrematurelyTerminated extends Condition<Run> {

  /** create */
  public static final RunNotPrematurelyTerminated INSTANCE = new RunNotPrematurelyTerminated();

  /** create */
  private RunNotPrematurelyTerminated() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final boolean check(final Run param) {
    if (param == null) {
      return false;
    }
    if (param.isEmpty()) {
      return false;
    }
    return (!(param.first().isPrematureTermination()));
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equals(final Object o) {
    return (o instanceof RunNotPrematurelyTerminated);
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return RunNotPrematurelyTerminated.class.hashCode();
  }

}
