package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;

/**
 * The Lin-Kernighan based MA with Edge crossover.
 */
public final class HeuristicInitLinKernighanEdgeMA extends
    _HeuristicInitLinKernighanEdgeMA {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create the ea
   */
  public HeuristicInitLinKernighanEdgeMA() {
    super("");//$NON-NLS-1$
  }

  /**
   * Perform the heuristic init mns edge MA
   * 
   * @param args
   *          the command line arguments
   */
  public final static void main(final String[] args) {
    TSPAlgorithmRunner.benchmark(Instance.SYMMETRIC_INSTANCES,
        HeuristicInitLinKernighanEdgeMA.class,//
        args);
  }
}
