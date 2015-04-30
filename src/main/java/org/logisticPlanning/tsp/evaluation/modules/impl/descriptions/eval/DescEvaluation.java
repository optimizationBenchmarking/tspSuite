package org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.eval;

import java.io.IOException;

import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.modules.spec.DescriptionModule;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.utils.document.spec.SectionTitle;

/**
 * The holder of the evaluation descriptions.
 */
public final class DescEvaluation extends DescriptionModule {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create!
   * 
   * @param owner
   *          the owner
   */
  public DescEvaluation(final Module owner) {
    super("Evaluation", owner, false); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @SuppressWarnings("unused")
  @Override
  protected void createChildModules() {
    super.createChildModules();
    new DescQualityMeasures(this);
    new DescERT(this);
    new DescECDF(this);
    new DescRanking(this);
    new DescAUC(this);
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSectionTitle(final SectionTitle title,
      final ExperimentSet data) throws IOException {
    title.write("Evaluation Methodology"); //$NON-NLS-1$
  }

}
