package org.logisticPlanning.tsp.solving.operators.permutation.mutation;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.TSPLocalSearchBasedMutation;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.linKernighan.LinKernighan;

/**
 * A mutation operator based on lin-kernighan search.
 */
public class PermutationLinKernighanMutation extends
    TSPLocalSearchBasedMutation<int[]> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** create */
  public PermutationLinKernighanMutation() {
    super(new LinKernighan());
  }
}
