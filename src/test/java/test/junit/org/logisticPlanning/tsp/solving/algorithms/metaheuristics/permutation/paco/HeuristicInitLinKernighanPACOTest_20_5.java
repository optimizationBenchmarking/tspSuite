package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco.HeuristicInitLinKernighanPACO;

/**
 * the test of the heuristically initialized population-based ACO with
 * Lin-Kernighan search
 */
public class HeuristicInitLinKernighanPACOTest_20_5 extends
    HeuristicInitLinKernighanPACOTest {

  /** create */
  public HeuristicInitLinKernighanPACOTest_20_5() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitLinKernighanPACO createAlgorithm() {
    final HeuristicInitLinKernighanPACO p;
    p = super.createAlgorithm();
    p.setAntCount(5);
    p.setPopulationSize(20);
    return p;
  }
}
