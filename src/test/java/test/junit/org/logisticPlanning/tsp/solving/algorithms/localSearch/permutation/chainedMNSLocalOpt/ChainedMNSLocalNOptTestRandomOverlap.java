package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.chainedMNSLocalOpt;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.chainedMNSLocalOpt.ChainedMNSLocalNOpt;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the local n-opt algorithm
 */
public class ChainedMNSLocalNOptTestRandomOverlap extends
TSPAlgorithmSymmetricTest {

  /** create */
  public ChainedMNSLocalNOptTestRandomOverlap() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected ChainedMNSLocalNOpt createAlgorithm() {
    ChainedMNSLocalNOpt ret;

    ret = new ChainedMNSLocalNOpt();
    ret.setRandomOverlap(true);
    return ret;
  }
}
