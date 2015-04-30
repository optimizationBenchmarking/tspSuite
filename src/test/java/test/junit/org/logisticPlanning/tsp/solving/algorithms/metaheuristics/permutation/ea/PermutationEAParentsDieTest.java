package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea.PermutationEA;

/**
 * the test of the permutation EA
 */
public class PermutationEAParentsDieTest extends
    PermutationEAParentsSurviveTest {

  /** create */
  public PermutationEAParentsDieTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationEA createAlgorithm() {
    PermutationEA res;

    res = super.createAlgorithm();
    res.setParentsSurvive(false);
    return res;
  }
}
