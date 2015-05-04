package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco.HeuristicInitMultiNeighborhoodSearchPACO;

/**
 * the test of the heuristically initialized population-based ACO
 */
public class HeuristicInitMultiNeighborhoodSearchPACOTest_20_5 extends
    HeuristicInitMultiNeighborhoodSearchPACOTest {

  /** create */
  public HeuristicInitMultiNeighborhoodSearchPACOTest_20_5() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitMultiNeighborhoodSearchPACO createAlgorithm() {
    final HeuristicInitMultiNeighborhoodSearchPACO p;
    p = super.createAlgorithm();
    p.setAntCount(5);
    p.setPopulationSize(20);
    return p;
  }
}
