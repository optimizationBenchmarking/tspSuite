package test.junit.org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963;

import org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963.BABLittle1963Plus;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963.BABLittle1963
 * branch and bound algorithm} published by Little et al. in 1963.
 */
public class BABLittle1963PlusTest extends TSPAlgorithmSymmetricTest {

  /** create */
  public BABLittle1963PlusTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected BABLittle1963Plus createAlgorithm() {
    return new BABLittle1963Plus();
  }
}
