package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.ejectionchain;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.ejectionchain.P_Sec;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/** The test of the P_Sec algorithm */
public class P_SecTest extends TSPAlgorithmSymmetricTest {

  /** create */
  public P_SecTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected P_Sec createAlgorithm() {
    return new P_Sec();
  }

}
