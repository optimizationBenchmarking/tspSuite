package test.junit.org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963;

import org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963.BABLittle1963RNS;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963.BABLittle1963
 * branch and bound algorithm} published by Little et al. in 1963. with
 * RNS.
 */
public class BABLittle1963RNSTest extends TSPAlgorithmSymmetricTest {

  /** create */
  public BABLittle1963RNSTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected BABLittle1963RNS createAlgorithm() {
    return new BABLittle1963RNS();
  }
}
