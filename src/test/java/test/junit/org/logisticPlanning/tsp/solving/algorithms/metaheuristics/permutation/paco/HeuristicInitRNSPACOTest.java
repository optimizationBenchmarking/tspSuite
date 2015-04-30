package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco.HeuristicInitRNSPACO;

/**
 * the test of the heuristically initialized population-based ACO with
 * random neighborhood search
 */
public class HeuristicInitRNSPACOTest extends HeuristicInitPACOTest {

  /** create */
  public HeuristicInitRNSPACOTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitRNSPACO createAlgorithm() {
    return new HeuristicInitRNSPACO();
  }
}
