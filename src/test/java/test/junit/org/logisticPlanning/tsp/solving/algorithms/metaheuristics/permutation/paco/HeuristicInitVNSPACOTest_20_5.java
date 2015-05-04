package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco.HeuristicInitVNSPACO;

/**
 * the test of the heuristically initialized population-based ACO with
 * variable neighborhood search
 */
public class HeuristicInitVNSPACOTest_20_5 extends
HeuristicInitVNSPACOTest {

  /** create */
  public HeuristicInitVNSPACOTest_20_5() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitVNSPACO createAlgorithm() {
    final HeuristicInitVNSPACO p;
    p = super.createAlgorithm();
    p.setAntCount(5);
    p.setPopulationSize(20);
    return p;
  }
}
