package org.logisticPlanning.tsp.solving.operators.permutation.mutation;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.TSPLocalSearchBasedMutation;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.fundamentalStemAndCycle.FundamentalStemAndCycle;

/**
 * A mutator based on the fundamental stem-and-cycle structure.
 */
public class PermutationFundamentalStemAndCycleMutation extends
    TSPLocalSearchBasedMutation<int[]> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** create */
  public PermutationFundamentalStemAndCycleMutation() {
    super(new FundamentalStemAndCycle());
  }
}