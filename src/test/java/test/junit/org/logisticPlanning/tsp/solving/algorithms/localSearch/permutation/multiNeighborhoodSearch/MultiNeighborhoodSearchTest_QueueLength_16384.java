package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch.MultiNeighborhoodSearch;

/**
 * the test of the multi neighborhood search with queue length 16384
 */
public class MultiNeighborhoodSearchTest_QueueLength_16384 extends
    MultiNeighborhoodSearchTest {

  /** create */
  public MultiNeighborhoodSearchTest_QueueLength_16384() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected MultiNeighborhoodSearch createAlgorithm() {
    final MultiNeighborhoodSearch ms;
    ms = super.createAlgorithm();
    ms.setMaxMoveAllocations(16384);
    return ms;
  }
}
