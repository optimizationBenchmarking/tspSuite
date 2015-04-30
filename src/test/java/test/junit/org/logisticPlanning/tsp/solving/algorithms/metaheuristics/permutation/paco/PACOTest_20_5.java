package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco.PACO;

/**
 * the test of the
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco.PACO
 * population-based ACO}
 */
public class PACOTest_20_5 extends PACOTest {

  /** create */
  public PACOTest_20_5() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PACO createAlgorithm() {
    final PACO p;

    p = super.createAlgorithm();
    p.setAntCount(5);
    p.setPopulationSize(20);
    return p;
  }
}
