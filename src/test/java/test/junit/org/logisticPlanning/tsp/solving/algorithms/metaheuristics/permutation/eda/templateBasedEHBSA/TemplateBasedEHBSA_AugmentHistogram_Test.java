package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA.EAugmentationMethod;
import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA.TemplateBasedEHBSA;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmAsymmetricTest;

/**
 * The test of the
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA.TemplateBasedEHBSA
 * template-based EHBSA}.
 */
public class TemplateBasedEHBSA_AugmentHistogram_Test extends
TSPAlgorithmAsymmetricTest {

  /** create */
  public TemplateBasedEHBSA_AugmentHistogram_Test() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected TemplateBasedEHBSA createAlgorithm() {
    final TemplateBasedEHBSA a;
    a = new TemplateBasedEHBSA();
    a.setAugmentationMethod(EAugmentationMethod.AUGMENT_BY_HISTOGRAM);
    return a;
  }
}
