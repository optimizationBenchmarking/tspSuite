package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.linKernighan;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.linKernighan.LinKernighan;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the lin-kernighan search of permutations
 */
public class LinKernighanTest extends TSPAlgorithmSymmetricTest {

  /** create */
  public LinKernighanTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected LinKernighan createAlgorithm() {
    return new LinKernighan();
  }
}
