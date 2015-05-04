package test.junit.org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963;

import org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963.BABLittle1963MultiNeighborhoodSearch;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963.BABLittle1963
 * branch and bound algorithm} published by Little et al. in 1963, with
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch.MultiNeighborhoodSearch
 * multi-neighborhood search}.
 */
public class BABLittle1963MultiNeighborhoodSearchTest extends
    TSPAlgorithmSymmetricTest {

  /** create */
  public BABLittle1963MultiNeighborhoodSearchTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected BABLittle1963MultiNeighborhoodSearch createAlgorithm() {
    return new BABLittle1963MultiNeighborhoodSearch();
  }
}
