package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA.EAugmentationMethod;
import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA.HeuristicInitMultiNeighborhoodTemplateBasedEHBSA;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * The test of the
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA.HeuristicInitMultiNeighborhoodTemplateBasedEHBSA
 * template-based EHBSA} with heuristic initialization and
 * multi-neighborhood search.
 */
public class HeuristicInitMultiNeighborhoodTemplateBasedEHBSA_AugmentHistogram_Test
    extends TSPAlgorithmSymmetricTest {

  /** create */
  public HeuristicInitMultiNeighborhoodTemplateBasedEHBSA_AugmentHistogram_Test() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitMultiNeighborhoodTemplateBasedEHBSA createAlgorithm() {
    final HeuristicInitMultiNeighborhoodTemplateBasedEHBSA a;
    a = new HeuristicInitMultiNeighborhoodTemplateBasedEHBSA();
    a.setAugmentationMethod(EAugmentationMethod.AUGMENT_BY_HISTOGRAM);
    return a;
  }
}
