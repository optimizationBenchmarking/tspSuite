package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationLinKernighanMutation;

/**
 * A P-ACO algorithm with Lin-Kernighan mutation.
 */
public class HeuristicInitLinKernighanPACO extends
    _HeuristicInitLocalSearchPACO {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * instantiate
   */
  public HeuristicInitLinKernighanPACO() {
    super("Lin-Kernighan ");//$NON-NLS-1$ 
  }

  /** {@inheritDoc} */
  @Override
  protected final PermutationLinKernighanMutation createLocalSearch() {
    return new PermutationLinKernighanMutation();
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
        Instance.SYMMETRIC_INSTANCES, HeuristicInitLinKernighanPACO.class,//
        args);
  }
}
