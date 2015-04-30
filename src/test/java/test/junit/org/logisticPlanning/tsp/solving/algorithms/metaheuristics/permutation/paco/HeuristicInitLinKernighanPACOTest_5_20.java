package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco.HeuristicInitLinKernighanPACO;

/**
 * the test of the heuristically initialized population-based ACO with
 * Lin-Kernighan search
 */
public class HeuristicInitLinKernighanPACOTest_5_20 extends
    HeuristicInitLinKernighanPACOTest {

  /** create */
  public HeuristicInitLinKernighanPACOTest_5_20() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitLinKernighanPACO createAlgorithm() {
    final HeuristicInitLinKernighanPACO p;
    p = super.createAlgorithm();
    p.setAntCount(20);
    p.setPopulationSize(5);
    return p;
  }
}
