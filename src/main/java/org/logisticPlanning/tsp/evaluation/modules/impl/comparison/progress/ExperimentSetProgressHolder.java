package org.logisticPlanning.tsp.evaluation.modules.impl.comparison.progress;

import java.io.IOException;

import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.modules.spec.ComparisonModule;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.utils.document.spec.SectionTitle;

/**
 * This evaluator module will print diagrams showing the progress of the
 * algorithms over benchmark instance.
 */
public final class ExperimentSetProgressHolder extends ComparisonModule {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create!
   *
   * @param owner
   *          the macro's owner
   */
  public ExperimentSetProgressHolder(final Module owner) {
    super("experimentSetProgress", owner, false); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected final boolean hasContent(final ExperimentSet data) {
    return false;
  }

  /** {@inheritDoc} */
  @SuppressWarnings("unused")
  @Override
  protected final void createChildModules() {
    new _ExperimentSetProgressDiagrams(this, false);
    new _ExperimentSetScaleProgressDiagramsHolder(this);
    new _ExperimentSetInstanceProgressDiagramsHolder(this);
  }

  /** {@inheritDoc} */
  @Override
  protected final void writeSectionTitle(final SectionTitle title,
      final ExperimentSet data) throws IOException {
    title.write("Comparisonin Terms of Progress"); //$NON-NLS-1$
  }
}
