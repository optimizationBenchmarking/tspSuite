package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.satelliteList.LKW;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.satelliteList.LKW.LK_ID;

/**
 * the test of the LK_ID heuristic with the minimum spanning tree
 */
public class LK_IDTestWithMST_1_1 extends LK_IDTestWithMST {

  /** create */
  public LK_IDTestWithMST_1_1() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected LK_ID createAlgorithm() {
    LK_ID res;
    res = super.createAlgorithm();
    res.setMaxMaxRecursionDepth(1);
    res.setStartMaxRecursionDepth(1);
    return res;
  }
}
