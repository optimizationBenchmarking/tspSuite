package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch.MultiNeighborhoodSearch;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the multi neighborhood search of permutations
 */
public class MultiNeighborhoodSearchTest extends TSPAlgorithmSymmetricTest {

  /** create */
  public MultiNeighborhoodSearchTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected MultiNeighborhoodSearch createAlgorithm() {
    return new MultiNeighborhoodSearch();
  }
}
