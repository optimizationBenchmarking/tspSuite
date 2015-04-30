package test.junit.org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963;

import org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963.BABLittle1963VNS;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963.BABLittle1963
 * branch and bound algorithm} published by Little et al. in 1963. with
 * VNS.
 */
public class BABLittle1963VNSTest extends TSPAlgorithmSymmetricTest {

  /** create */
  public BABLittle1963VNSTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected BABLittle1963VNS createAlgorithm() {
    return new BABLittle1963VNS();
  }
}
