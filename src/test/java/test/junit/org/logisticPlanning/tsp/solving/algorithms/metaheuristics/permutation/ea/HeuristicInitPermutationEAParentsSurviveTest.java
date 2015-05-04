package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea.HeuristicInitPermutationEA;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the heuristically initialized permutation EA
 */
public class HeuristicInitPermutationEAParentsSurviveTest extends
TSPAlgorithmSymmetricTest {

  /** create */
  public HeuristicInitPermutationEAParentsSurviveTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitPermutationEA createAlgorithm() {
    HeuristicInitPermutationEA res;
    res = new HeuristicInitPermutationEA();
    res.setParentsSurvive(true);
    return res;
  }
}
