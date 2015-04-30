package test.junit.org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.mst.MSTHeuristic;

/**
 * the test of the
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963.BABLittle1963
 * branch and bound algorithm} published by Little et al. in 1963.
 * initialized with a
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.mst.MSTHeuristic
 * double-minimum spanning tree heuristic}.
 */
public class BABLittle1963MSTTest extends BABLittle1963Test {

  /** create */
  public BABLittle1963MSTTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected MSTHeuristic createInitAlgorithm() {
    return new MSTHeuristic();
  }
}
