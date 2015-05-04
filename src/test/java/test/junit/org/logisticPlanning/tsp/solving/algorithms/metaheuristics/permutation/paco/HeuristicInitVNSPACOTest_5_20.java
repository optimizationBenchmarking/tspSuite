package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco.HeuristicInitVNSPACO;

/**
 * the test of the heuristically initialized population-based ACO with
 * variable neighborhood search
 */
public class HeuristicInitVNSPACOTest_5_20 extends
HeuristicInitVNSPACOTest {

  /** create */
  public HeuristicInitVNSPACOTest_5_20() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitVNSPACO createAlgorithm() {
    final HeuristicInitVNSPACO p;
    p = super.createAlgorithm();
    p.setAntCount(20);
    p.setPopulationSize(5);
    return p;
  }
}
