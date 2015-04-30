package org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.random;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;

/**
 * Apply the {@link RandomPermutationSampling} to a few instances only:
 * This class was used in an experiment where very many FEs are granted, so
 * that full experimentation was not feasible.
 */
public final class RandomPermutationSamplingOnSmallest6 {

  /**
   * Perform the random sampling algorithm
   * 
   * @param args
   *          the command line arguments
   */
  public final static void main(final String[] args) {
    TSPAlgorithmRunner.benchmark(Instance.SIX_SMALLEST_SCALE_SYMMETRIC,
        RandomPermutationSampling.class,//
        args);
  }

}
