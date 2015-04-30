package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco.PACO;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmAsymmetricTest;

/**
 * The test of the
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco.PACO
 * population-based ACO}.
 */
public class PACOTest extends TSPAlgorithmAsymmetricTest {

  /** create */
  public PACOTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PACO createAlgorithm() {
    return new PACO();
  }
}
