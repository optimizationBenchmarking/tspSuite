package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.satelliteList.LKW;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.satelliteList.LKW.LK_ID;

/**
 * the test of the LK_ID heuristic with the minimum spanning tree
 */
public class LK_IDTestWithMST_2_2 extends LK_IDTestWithMST {

  /** create */
  public LK_IDTestWithMST_2_2() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected LK_ID createAlgorithm() {
    LK_ID res;
    res = super.createAlgorithm();
    res.setMaxMaxRecursionDepth(2);
    res.setStartMaxRecursionDepth(2);
    return res;
  }
}
