package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco.HeuristicInitMultiNeighborhoodSearchPACO;

/**
 * the test of the heuristically initialized population-based ACO
 */
public class HeuristicInitMultiNeighborhoodSearchPACOTest extends
    HeuristicInitPACOTest {

  /** create */
  public HeuristicInitMultiNeighborhoodSearchPACOTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitMultiNeighborhoodSearchPACO createAlgorithm() {
    return new HeuristicInitMultiNeighborhoodSearchPACO();
  }
}
