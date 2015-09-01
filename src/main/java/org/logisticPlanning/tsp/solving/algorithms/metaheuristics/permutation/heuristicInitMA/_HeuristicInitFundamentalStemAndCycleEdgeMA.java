package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA;

import org.logisticPlanning.tsp.solving.operators.BinaryOperator;
import org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationEdgeCrossover;

/**
 *
 <p>
 * This class is an internal class. You should never load or instantiate
 * it. Use the classes derived from it.
 * </p>
 */
class _HeuristicInitFundamentalStemAndCycleEdgeMA extends
    _HeuristicInitFundamentalStemAndCycleMA {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create the ea
   *
   * @param name
   *          a string to be added to the base name
   */
  public _HeuristicInitFundamentalStemAndCycleEdgeMA(final String name) {
    super("with Edge Crossover" + //$NON-NLS-1$
        name);
  }

  /** {@inheritDoc} */
  @Override
  protected final BinaryOperator<int[]> createBinary() {
    return new PermutationEdgeCrossover();
  }
}