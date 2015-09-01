package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;

/**
 * A memetic algorithm combined with Tabu Search.
 */
public final class HeuristicInitTabuSearchEdgeMA extends
    _HeuristicInitTabuSearchEdgeMA {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** create the ma */
  public HeuristicInitTabuSearchEdgeMA() {
    super("");//$NON-NLS-1$
  }

  /**
   * Perform the heuristic init tabu edge MA
   *
   * @param args
   *          the command line arguments
   */
  public final static void main(final String[] args) {
    TSPAlgorithmRunner.benchmark(Instance.SYMMETRIC_INSTANCES,
        HeuristicInitTabuSearchEdgeMA.class,//
        args);
  }
}