package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco.HeuristicInitLinKernighanPACO;

/**
 * the test of the heuristically initialized population-based ACO with
 * Lin-Kernighan search
 */
public class HeuristicInitLinKernighanPACOTest extends
HeuristicInitPACOTest {

  /** create */
  public HeuristicInitLinKernighanPACOTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitLinKernighanPACO createAlgorithm() {
    return new HeuristicInitLinKernighanPACO();
  }
}
