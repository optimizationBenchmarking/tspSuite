package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.random;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.random.RandomPermutationSampling;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmAsymmetricTest;

/**
 * the test of the random sampling of permutations
 */
public class RandomPermutationSamplingTest extends
    TSPAlgorithmAsymmetricTest {

  /** create */
  public RandomPermutationSamplingTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected RandomPermutationSampling createAlgorithm() {
    return new RandomPermutationSampling();
  }
}
