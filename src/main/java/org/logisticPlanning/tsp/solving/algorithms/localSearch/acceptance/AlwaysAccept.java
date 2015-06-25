package org.logisticPlanning.tsp.solving.algorithms.localSearch.acceptance;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.AcceptanceCriterion;

/**
 * Always accept the new solution.
 *
 * @since 0.9.8
 */
public final class AlwaysAccept extends AcceptanceCriterion {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final AlwaysAccept INSTANCE = new AlwaysAccept();

  /** create */
  public AlwaysAccept() {
    super("AlwaysAccept"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final boolean shouldAccept(final Individual<?> solution,
      final ObjectiveFunction f) {
    return true;
  }

  /**
   * Returns {@link #INSTANCE}.
   *
   * @return {@link #INSTANCE}
   */
  @Override
  public final AlwaysAccept clone() {
    return AlwaysAccept.INSTANCE;
  }
}
