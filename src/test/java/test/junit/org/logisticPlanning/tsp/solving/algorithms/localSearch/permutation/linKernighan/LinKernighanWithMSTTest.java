package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.linKernighan;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.mst.MSTHeuristic;

/**
 * the test of the lin-kernighan search initialized with the MST heuristic
 */
public class LinKernighanWithMSTTest extends LinKernighanTest {

  /** create */
  public LinKernighanWithMSTTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected MSTHeuristic createInitAlgorithm() {
    return new MSTHeuristic();
  }
}
