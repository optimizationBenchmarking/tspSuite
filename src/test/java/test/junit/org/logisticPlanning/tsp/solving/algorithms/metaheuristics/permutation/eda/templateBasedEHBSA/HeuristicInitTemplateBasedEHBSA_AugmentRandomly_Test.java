package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA.EAugmentationMethod;
import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA.HeuristicInitTemplateBasedEHBSA;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * The test of the
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA.HeuristicInitTemplateBasedEHBSA
 * template-based EHBSA} with heuristic initialization.
 */
public class HeuristicInitTemplateBasedEHBSA_AugmentRandomly_Test extends
    TSPAlgorithmSymmetricTest {

  /** create */
  public HeuristicInitTemplateBasedEHBSA_AugmentRandomly_Test() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitTemplateBasedEHBSA createAlgorithm() {
    final HeuristicInitTemplateBasedEHBSA a;
    a = new HeuristicInitTemplateBasedEHBSA();
    a.setAugmentationMethod(EAugmentationMethod.AUGMENT_RANDOMLY);
    return a;
  }
}
