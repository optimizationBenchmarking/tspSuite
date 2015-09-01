package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationTabuSearchMutation;

/**
 * <p>
 * A version of the {@link PACO heuristically-initialized Population-based
 * ACO} that uses a Tabu Search for refining its candidate solutions.
 */
public class HeuristicInitTabuSearchPACO extends
    _HeuristicInitLocalSearchPACO {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * instantiate
   */
  public HeuristicInitTabuSearchPACO() {
    super("Tabu Search ");//$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected final PermutationTabuSearchMutation createLocalSearch() {
    return new PermutationTabuSearchMutation();
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
        Instance.SYMMETRIC_INSTANCES, HeuristicInitTabuSearchPACO.class,//
        args);
  }
}