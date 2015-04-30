package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.random;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.random.RandomPermutationWalk;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * The test of the
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.random.RandomPermutationWalk
 * random walk}.
 */
public class RandomPermutationWalkTest extends TSPAlgorithmSymmetricTest {

  /** create */
  public RandomPermutationWalkTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected RandomPermutationWalk createAlgorithm() {
    return new RandomPermutationWalk();
  }
}
