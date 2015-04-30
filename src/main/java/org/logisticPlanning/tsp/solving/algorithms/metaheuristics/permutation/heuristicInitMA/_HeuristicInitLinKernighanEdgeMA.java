package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA;

import org.logisticPlanning.tsp.solving.operators.BinaryOperator;
import org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationEdgeCrossover;

/**
 * A Lin-Kernighan EA with edge crossover.
 */
class _HeuristicInitLinKernighanEdgeMA extends
    _HeuristicInitLinKernighanMA {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create the ea
   * 
   * @param name
   *          a string to be added to the base name
   */
  public _HeuristicInitLinKernighanEdgeMA(final String name) {
    super("with Edge Crossover" + //$NON-NLS-1$
        name);
  }

  /** {@inheritDoc} */
  @Override
  protected final BinaryOperator<int[]> createBinary() {
    return new PermutationEdgeCrossover();
  }
}
