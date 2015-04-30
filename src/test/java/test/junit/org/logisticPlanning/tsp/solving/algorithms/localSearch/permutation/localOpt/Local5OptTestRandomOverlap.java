package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.localOpt;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.localOpt.LocalNOpt;
import org.logisticPlanning.tsp.solving.operators.permutation.localOpt.ExhaustivelyEnumeratingLocal5Optimizer;

/**
 * the test of the local 5-opt algorithm
 */
public class Local5OptTestRandomOverlap extends LocalNOptTestRandomOverlap {

  /** create */
  public Local5OptTestRandomOverlap() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected LocalNOpt createAlgorithm() {
    final LocalNOpt algo;

    algo = super.createAlgorithm();
    algo.setLocalOptimizer(new ExhaustivelyEnumeratingLocal5Optimizer());
    return algo;
  }
}
