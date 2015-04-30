package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;

/**
 * Apply the {@link PermutationEA} to a few instances only: This class was
 * used in an experiment where very many FEs are granted, so that full
 * experimentation was not feasible.
 */
public class PermutationEAOnSmallest6 {

  /**
   * Apply the evolutionary algorithm to all symmetric TSPLib instances.
   * 
   * @param args
   *          the command line arguments
   */
  public static void main(final String[] args) {

    TSPAlgorithmRunner.benchmark(Instance.SIX_SMALLEST_SCALE_SYMMETRIC,//
        PermutationEA.class,//
        args);
  }
}
