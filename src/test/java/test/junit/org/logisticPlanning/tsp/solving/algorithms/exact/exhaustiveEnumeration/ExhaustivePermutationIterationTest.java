package test.junit.org.logisticPlanning.tsp.solving.algorithms.exact.exhaustiveEnumeration;

import org.logisticPlanning.tsp.solving.algorithms.exact.exhaustiveEnumeration.ExhaustivePermutationIteration;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmAsymmetricTest;

/**
 * The test of the
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.exhaustiveEnumeration.ExhaustivePermutationIteration
 * exhaustive permutation iteration} algorithm.
 */
public class ExhaustivePermutationIterationTest extends
    TSPAlgorithmAsymmetricTest {

  /** create */
  public ExhaustivePermutationIterationTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected ExhaustivePermutationIteration createAlgorithm() {
    return new ExhaustivePermutationIteration();
  }
}
