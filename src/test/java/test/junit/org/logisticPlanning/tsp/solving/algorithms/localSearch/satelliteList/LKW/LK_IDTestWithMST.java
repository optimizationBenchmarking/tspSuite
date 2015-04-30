package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.satelliteList.LKW;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.mst.MSTHeuristic;

/**
 * the test of the LK_ID heuristic with the minimum spanning tree
 */
public class LK_IDTestWithMST extends LK_IDTest {

  /** create */
  public LK_IDTestWithMST() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected MSTHeuristic createInitAlgorithm() {
    return new MSTHeuristic();
  }
}
