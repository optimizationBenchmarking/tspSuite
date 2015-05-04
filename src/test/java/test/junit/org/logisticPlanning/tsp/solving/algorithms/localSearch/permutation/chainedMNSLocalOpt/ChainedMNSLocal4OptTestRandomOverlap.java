package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.chainedMNSLocalOpt;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.chainedMNSLocalOpt.ChainedMNSLocalNOpt;
import org.logisticPlanning.tsp.solving.operators.permutation.localOpt.ExhaustivelyEnumeratingLocal4Optimizer;

/**
 * the test of the local 4-opt algorithm
 */
public class ChainedMNSLocal4OptTestRandomOverlap extends
ChainedMNSLocalNOptTestRandomOverlap {

  /** create */
  public ChainedMNSLocal4OptTestRandomOverlap() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected ChainedMNSLocalNOpt createAlgorithm() {
    final ChainedMNSLocalNOpt algo;

    algo = super.createAlgorithm();
    algo.setLocalOptimizer(new ExhaustivelyEnumeratingLocal4Optimizer());
    return algo;
  }
}
