package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco.HeuristicInitPACO;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the heuristically initialized population-based ACO
 */
public class HeuristicInitPACOTest extends TSPAlgorithmSymmetricTest {

  /** create */
  public HeuristicInitPACOTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitPACO createAlgorithm() {
    return new HeuristicInitPACO();
  }
}
