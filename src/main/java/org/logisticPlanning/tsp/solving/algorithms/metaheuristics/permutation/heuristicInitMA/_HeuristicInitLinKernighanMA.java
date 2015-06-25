package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA;

import org.logisticPlanning.tsp.solving.operators.UnaryOperator;
import org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationLinKernighanMutation;

/**
 * A base class for MAs with Lin-Kernighan search.
 */
abstract class _HeuristicInitLinKernighanMA extends
    PermutationHeuristicInitMA {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create the ea
   *
   * @param name
   *          a string to be added to the base name
   */
  public _HeuristicInitLinKernighanMA(final String name) {
    super(
        "Permutation-based heuristic-initialized Lin-Kernighan Memetic Algorithm " + //$NON-NLS-1$
            name);
  }

  /** {@inheritDoc} */
  @Override
  protected final UnaryOperator<int[]> createUnary() {
    return new PermutationLinKernighanMutation();
  }

}
