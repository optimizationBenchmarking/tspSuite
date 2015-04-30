package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco.HeuristicInitPACO;

/**
 * the test of the heuristically initialized population-based ACO
 */
public class HeuristicInitPACOTest_20_5 extends HeuristicInitPACOTest {

  /** create */
  public HeuristicInitPACOTest_20_5() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitPACO createAlgorithm() {
    final HeuristicInitPACO p;
    p = super.createAlgorithm();
    p.setAntCount(5);
    p.setPopulationSize(20);
    return p;
  }
}
