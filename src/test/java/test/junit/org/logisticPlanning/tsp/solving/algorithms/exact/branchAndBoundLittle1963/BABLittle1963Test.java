package test.junit.org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963;

import org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963.BABLittle1963;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963.BABLittle1963
 * branch and bound algorithm} published by Little et al. in 1963.
 */
public class BABLittle1963Test extends TSPAlgorithmSymmetricTest {

  /** create */
  public BABLittle1963Test() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected BABLittle1963 createAlgorithm() {
    return new BABLittle1963();
  }
}
