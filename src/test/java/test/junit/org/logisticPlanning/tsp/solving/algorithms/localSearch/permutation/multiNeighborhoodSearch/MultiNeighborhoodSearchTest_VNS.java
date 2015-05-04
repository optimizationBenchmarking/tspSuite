package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch.EMoveComparator;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch.MultiNeighborhoodSearch;

/**
 * the test of the multi neighborhood search using moves of a given type
 * first, i.e., behaving somewhat like a VNS
 */
public class MultiNeighborhoodSearchTest_VNS extends
MultiNeighborhoodSearchTest {

  /** create */
  public MultiNeighborhoodSearchTest_VNS() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected MultiNeighborhoodSearch createAlgorithm() {
    MultiNeighborhoodSearch m;

    m = super.createAlgorithm();
    m.setMoveComparator(EMoveComparator.TYPE_FIRST_THEN_BEST_MOVE);
    return m;
  }
}
