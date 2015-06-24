package org.logisticPlanning.tsp.solving.operators.permutation.mutation;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.TSPLocalSearchBasedMutation;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.simulatedAnnealing.SimulatedAnnealing;

/**
 * A mutation operator based on Simulated Annealing search.
 *
 * @author Jiahui Liu, jl4161@columbia.edu
 */
public class PermutationSAMutation extends
    TSPLocalSearchBasedMutation<int[]> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** create */
  public PermutationSAMutation() {
    super(new SimulatedAnnealing());
  }
}
