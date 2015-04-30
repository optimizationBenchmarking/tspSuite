package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.satelliteList.LKW;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.satelliteList.LKW.LK_ID;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the LK_ID heuristic
 */
public class LK_IDTest extends TSPAlgorithmSymmetricTest {

  /** create */
  public LK_IDTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected LK_ID createAlgorithm() {
    return new LK_ID();
  }
}
