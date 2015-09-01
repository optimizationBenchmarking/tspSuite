package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.ejectionchain;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.ejectionchain.F_Sec;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/** The test of the F_Sec algorithm */
public class F_SecTest extends TSPAlgorithmSymmetricTest {
  /** create */
  public F_SecTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected F_Sec createAlgorithm() {
    return new F_Sec();
  }
}
