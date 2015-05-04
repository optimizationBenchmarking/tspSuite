package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.chainedMNSLocalOpt;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.chainedMNSLocalOpt.ChainedMNSLocalNOpt;
import org.logisticPlanning.tsp.solving.operators.permutation.localOpt.ExhaustivelyEnumeratingLocal6Optimizer;

/**
 * the test of the local 6-opt algorithm
 */
public class ChainedMNSLocal6OptTestRandomOverlap extends
ChainedMNSLocalNOptTestRandomOverlap {

  /** create */
  public ChainedMNSLocal6OptTestRandomOverlap() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected ChainedMNSLocalNOpt createAlgorithm() {
    final ChainedMNSLocalNOpt algo;

    algo = super.createAlgorithm();
    algo.setLocalOptimizer(new ExhaustivelyEnumeratingLocal6Optimizer());
    return algo;
  }
}
