package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA.EAugmentationMethod;
import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA.HeuristicInitVNSTemplateBasedEHBSA;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * The test of the
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA.HeuristicInitVNSTemplateBasedEHBSA
 * template-based EHBSA} with heuristic initialization and VNS.
 */
public class HeuristicInitVNSTemplateBasedEHBSA_AugmentRandomly_Test
extends TSPAlgorithmSymmetricTest {

  /** create */
  public HeuristicInitVNSTemplateBasedEHBSA_AugmentRandomly_Test() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitVNSTemplateBasedEHBSA createAlgorithm() {
    final HeuristicInitVNSTemplateBasedEHBSA a;
    a = new HeuristicInitVNSTemplateBasedEHBSA();
    a.setAugmentationMethod(EAugmentationMethod.AUGMENT_RANDOMLY);
    return a;
  }
}
