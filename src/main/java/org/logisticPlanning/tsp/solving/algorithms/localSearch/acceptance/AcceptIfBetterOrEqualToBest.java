package org.logisticPlanning.tsp.solving.algorithms.localSearch.acceptance;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.AcceptanceCriterion;

/**
 * Accept a solution if it is better or equal to the best currently known
 * solution.
 *
 * @since 0.9.8
 */
public final class AcceptIfBetterOrEqualToBest extends AcceptanceCriterion {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final AcceptIfBetterOrEqualToBest INSTANCE = new AcceptIfBetterOrEqualToBest();

  /** create */
  public AcceptIfBetterOrEqualToBest() {
    super("AcceptIfBetterOrEqualToBest"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final boolean shouldAccept(final Individual<?> solution,
      final ObjectiveFunction f) {
    return (solution.tourLength <= f.getCurrentLogPoint().getBestF());
  }

  /**
   * Returns {@link #INSTANCE}.
   *
   * @return {@link #INSTANCE}
   */
  @Override
  public final AcceptIfBetterOrEqualToBest clone() {
    return AcceptIfBetterOrEqualToBest.INSTANCE;
  }
}
