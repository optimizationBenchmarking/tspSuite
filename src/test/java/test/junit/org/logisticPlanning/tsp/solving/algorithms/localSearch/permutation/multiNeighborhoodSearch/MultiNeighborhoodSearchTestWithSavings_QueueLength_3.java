package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch.MultiNeighborhoodSearch;

/**
 * the test multi neighborhood search with queue length 3 initialized with
 * the Savings heuristic
 */
public class MultiNeighborhoodSearchTestWithSavings_QueueLength_3 extends
MultiNeighborhoodSearchTestWithSavings {

  /** create */
  public MultiNeighborhoodSearchTestWithSavings_QueueLength_3() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected MultiNeighborhoodSearch createAlgorithm() {
    final MultiNeighborhoodSearch ms;
    ms = super.createAlgorithm();
    ms.setMaxMoveAllocations(3);
    return ms;
  }
}
