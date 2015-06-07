package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.simulatedAnnealing;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.simulatedAnnealing.SimulatedAnnealing;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/** A test for the SA algorithm */
public class SimulatedAnnealingTest extends TSPAlgorithmSymmetricTest {
  /** creat */
  public SimulatedAnnealingTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected SimulatedAnnealing createAlgorithm() {
    return new SimulatedAnnealing();
  }
}