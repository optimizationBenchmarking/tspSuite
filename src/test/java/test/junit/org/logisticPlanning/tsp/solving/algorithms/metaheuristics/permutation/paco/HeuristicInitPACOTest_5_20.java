package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco.HeuristicInitPACO;

/**
 * the test of the heuristically initialized population-based ACO
 */
public class HeuristicInitPACOTest_5_20 extends HeuristicInitPACOTest {

  /** create */
  public HeuristicInitPACOTest_5_20() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitPACO createAlgorithm() {
    final HeuristicInitPACO p;
    p = super.createAlgorithm();
    p.setAntCount(20);
    p.setPopulationSize(5);
    return p;
  }
}
