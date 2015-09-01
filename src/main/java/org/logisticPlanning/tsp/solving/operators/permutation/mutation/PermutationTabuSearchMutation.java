package org.logisticPlanning.tsp.solving.operators.permutation.mutation;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.TSPLocalSearchBasedMutation;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.ts.TabuSearchMove;

/**
 * <p>
 * An mutator that performs a local search according to the
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.ts.TabuSearchMove
 * Multi-Neighborhood Search} and by default returns an individual which is
 * different from its parent. If the parent is already a local optimum, it
 * will be partially shuffled and the local search is applied to the
 * shuffled individual until it will result in a different solution.
 * </p>
 */
public class PermutationTabuSearchMutation extends
    TSPLocalSearchBasedMutation<int[]> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** create */
  public PermutationTabuSearchMutation() {
    super(new TabuSearchMove());
  }
}