package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch.MultiNeighborhoodSearch;

/**
 * the test of the multi neighborhood search with queue length 1
 * initialized with the Savings heuristic
 */
public class MultiNeighborhoodSearchTestWithSavings_QueueLength_1 extends
    MultiNeighborhoodSearchTestWithSavings {

  /** create */
  public MultiNeighborhoodSearchTestWithSavings_QueueLength_1() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected MultiNeighborhoodSearch createAlgorithm() {
    final MultiNeighborhoodSearch ms;
    ms = super.createAlgorithm();
    ms.setMaxMoveAllocations(1);
    return ms;
  }
}
