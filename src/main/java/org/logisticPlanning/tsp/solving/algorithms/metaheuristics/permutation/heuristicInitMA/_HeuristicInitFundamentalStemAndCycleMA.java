package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA;

import org.logisticPlanning.tsp.solving.operators.UnaryOperator;
import org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationFundamentalStemAndCycleMutation;

/**
 *
 <p>
 * This class is an internal class. You should never load or instantiate
 * it. Use the classes derived from it.
 * </p>
 */
abstract class _HeuristicInitFundamentalStemAndCycleMA extends
    PermutationHeuristicInitMA {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create the ea
   *
   * @param name
   *          a string to be added to the base name
   */
  public _HeuristicInitFundamentalStemAndCycleMA(final String name) {
    super(//
        "Permutation-based heuristic-initialized FundamentalStemAndCycle Memetic Algorithm "//$NON-NLS-1$
            + name);
  }

  /** {@inheritDoc} */
  @Override
  protected final UnaryOperator<int[]> createUnary() {
    return new PermutationFundamentalStemAndCycleMutation();
  }
}