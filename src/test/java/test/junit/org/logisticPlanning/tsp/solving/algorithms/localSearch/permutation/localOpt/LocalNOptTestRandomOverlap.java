package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.localOpt;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.localOpt.LocalNOpt;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the local n-opt algorithm
 */
public class LocalNOptTestRandomOverlap extends TSPAlgorithmSymmetricTest {

  /** create */
  public LocalNOptTestRandomOverlap() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected LocalNOpt createAlgorithm() {
    LocalNOpt ret;

    ret = new LocalNOpt();
    ret.setRandomOverlap(true);
    return ret;
  }
}
