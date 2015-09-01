package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationFundamentalStemAndCycleMutation;

/**
 * A version of the {@link PACO heuristically-initialized Population-based
 * ACO} that uses a stem-and-cycle-based local search.
 */
public class HeuristicInitFundamentalStemAndCyclePACO extends
    _HeuristicInitLocalSearchPACO {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * instantiate
   */
  public HeuristicInitFundamentalStemAndCyclePACO() {
    super("FundamentalStemAndCycle");//$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected final PermutationFundamentalStemAndCycleMutation createLocalSearch() {
    return new PermutationFundamentalStemAndCycleMutation();
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
        Instance.SYMMETRIC_INSTANCES,
        HeuristicInitFundamentalStemAndCyclePACO.class,//
        args);
  }
}