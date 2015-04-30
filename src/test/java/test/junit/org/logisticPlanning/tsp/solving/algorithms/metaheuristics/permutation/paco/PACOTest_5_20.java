package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco.PACO;

/**
 * the test of the
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco.PACO
 * population-based ACO}.
 */
public class PACOTest_5_20 extends PACOTest {

  /** create */
  public PACOTest_5_20() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PACO createAlgorithm() {
    final PACO p;

    p = super.createAlgorithm();
    p.setAntCount(20);
    p.setPopulationSize(5);
    return p;
  }
}
