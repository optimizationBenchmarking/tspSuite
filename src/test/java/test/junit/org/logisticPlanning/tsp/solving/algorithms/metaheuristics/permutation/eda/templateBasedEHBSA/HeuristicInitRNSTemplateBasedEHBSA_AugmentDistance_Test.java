package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA.EAugmentationMethod;
import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA.HeuristicInitRNSTemplateBasedEHBSA;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * The test of the
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA.HeuristicInitRNSTemplateBasedEHBSA
 * template-based EHBSA} with heuristic initialization and RNS.
 */
public class HeuristicInitRNSTemplateBasedEHBSA_AugmentDistance_Test
    extends TSPAlgorithmSymmetricTest {

  /** create */
  public HeuristicInitRNSTemplateBasedEHBSA_AugmentDistance_Test() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitRNSTemplateBasedEHBSA createAlgorithm() {
    final HeuristicInitRNSTemplateBasedEHBSA a;
    a = new HeuristicInitRNSTemplateBasedEHBSA();
    a.setAugmentationMethod(EAugmentationMethod.AUGMENT_BY_DISTANCE);
    return a;
  }
}
