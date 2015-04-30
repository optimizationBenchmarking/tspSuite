package test.junit.org.logisticPlanning.tsp.solving.algorithms.heuristics.mst;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.mst.MSTHeuristic;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * The test of the
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.mst.MSTHeuristic
 * MST heuristic}.
 */
public class MSTHeuristicTest extends TSPAlgorithmSymmetricTest {

  /** create */
  public MSTHeuristicTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected MSTHeuristic createAlgorithm() {
    return new MSTHeuristic();
  }
}
