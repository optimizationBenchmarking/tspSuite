package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationSAMutation;

/**
 * A simulated annealing-based hybrid PACO algorithm.
 *
 * @author Jiahui Liu, jl4161@columbia.edu
 */
public class HeuristicInitSAPACO extends _HeuristicInitLocalSearchPACO {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * instantiate
   */
  public HeuristicInitSAPACO() {
    super("Simulated Annealing ");//$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected final PermutationSAMutation createLocalSearch() {
    return new PermutationSAMutation();
  }

  /**
   * Perform the population-based ACO
   *
   * @param args
   *          the command line arguments
   */
  public static void main(final String[] args) {
    TSPAlgorithmRunner.benchmark(
    //
        Instance.SYMMETRIC_INSTANCES, HeuristicInitSAPACO.class,//
        args);
  }
}