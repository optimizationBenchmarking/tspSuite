package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco.HeuristicInitRNSPACO;

/**
 * the test of the heuristically initialized population-based ACO with
 * random neighborhood search
 */
public class HeuristicInitRNSPACOTest_5_20 extends
    HeuristicInitRNSPACOTest {

  /** create */
  public HeuristicInitRNSPACOTest_5_20() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitRNSPACO createAlgorithm() {
    final HeuristicInitRNSPACO p;
    p = super.createAlgorithm();
    p.setAntCount(20);
    p.setPopulationSize(5);
    return p;
  }
}
