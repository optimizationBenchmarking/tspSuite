package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.chainedMNSLocalOpt;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.chainedMNSLocalOpt.ChainedMNSLocalNOpt;
import org.logisticPlanning.tsp.solving.operators.permutation.localOpt.ExhaustivelyEnumeratingLocal3Optimizer;

/**
 * the test of the local 3-opt algorithm
 */
public class ChainedMNSLocal3OptTestNoRandomOverlap extends
ChainedMNSLocalNOptTestNoRandomOverlap {

  /** create */
  public ChainedMNSLocal3OptTestNoRandomOverlap() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected ChainedMNSLocalNOpt createAlgorithm() {
    final ChainedMNSLocalNOpt algo;

    algo = super.createAlgorithm();
    algo.setLocalOptimizer(new ExhaustivelyEnumeratingLocal3Optimizer());
    return algo;
  }
}
