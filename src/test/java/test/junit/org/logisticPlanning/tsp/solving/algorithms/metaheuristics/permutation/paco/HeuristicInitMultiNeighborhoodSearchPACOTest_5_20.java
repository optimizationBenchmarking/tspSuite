package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco.HeuristicInitMultiNeighborhoodSearchPACO;

/**
 * the test of the heuristically initialized population-based ACO
 */
public class HeuristicInitMultiNeighborhoodSearchPACOTest_5_20 extends
    HeuristicInitMultiNeighborhoodSearchPACOTest {

  /** create */
  public HeuristicInitMultiNeighborhoodSearchPACOTest_5_20() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitMultiNeighborhoodSearchPACO createAlgorithm() {
    final HeuristicInitMultiNeighborhoodSearchPACO p;
    p = super.createAlgorithm();
    p.setAntCount(20);
    p.setPopulationSize(5);
    return p;
  }
}
