package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;

/** A Memetic Algorithm (MA) that uses a stem-and-cycle-based local search. */
public final class HeuristicInitFundamentalStemAndCycleEdgeMA extends
    _HeuristicInitFundamentalStemAndCycleEdgeMA {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create the ea
   */
  public HeuristicInitFundamentalStemAndCycleEdgeMA() {
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
        HeuristicInitFundamentalStemAndCycleEdgeMA.class,//
        args);
  }
}