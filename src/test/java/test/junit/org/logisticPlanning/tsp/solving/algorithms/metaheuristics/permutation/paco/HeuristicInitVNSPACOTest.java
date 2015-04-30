package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco.HeuristicInitVNSPACO;

/**
 * the test of the heuristically initialized population-based ACO with
 * variable neighborhood search
 */
public class HeuristicInitVNSPACOTest extends HeuristicInitPACOTest {

  /** create */
  public HeuristicInitVNSPACOTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitVNSPACO createAlgorithm() {
    return new HeuristicInitVNSPACO();
  }
}
