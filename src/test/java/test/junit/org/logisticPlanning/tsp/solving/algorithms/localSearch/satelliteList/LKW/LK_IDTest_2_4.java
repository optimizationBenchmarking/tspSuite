package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.satelliteList.LKW;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.satelliteList.LKW.LK_ID;

/**
 * the test of the LK_ID heuristic
 */
public class LK_IDTest_2_4 extends LK_IDTest {

  /** create */
  public LK_IDTest_2_4() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected LK_ID createAlgorithm() {
    LK_ID res;
    res = super.createAlgorithm();
    res.setMaxMaxRecursionDepth(4);
    res.setStartMaxRecursionDepth(2);
    return res;
  }
}
