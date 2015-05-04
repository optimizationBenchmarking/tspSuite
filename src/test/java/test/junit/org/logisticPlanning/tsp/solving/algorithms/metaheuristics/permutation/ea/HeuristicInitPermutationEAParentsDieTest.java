package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea.HeuristicInitPermutationEA;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the heuristically initialized permutation EA
 */
public class HeuristicInitPermutationEAParentsDieTest extends
TSPAlgorithmSymmetricTest {

  /** create */
  public HeuristicInitPermutationEAParentsDieTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitPermutationEA createAlgorithm() {
    HeuristicInitPermutationEA res;
    res = new HeuristicInitPermutationEA();
    res.setParentsSurvive(false);
    return res;
  }
}
