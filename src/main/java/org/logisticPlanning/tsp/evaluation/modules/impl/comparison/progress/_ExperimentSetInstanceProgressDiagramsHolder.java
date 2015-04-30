package org.logisticPlanning.tsp.evaluation.modules.impl.comparison.progress;

import java.io.IOException;

import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.modules.spec.ComparisonModule;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.utils.document.spec.SectionTitle;

/**
 * This evaluator module will print diagrams showing the progress of and
 * algorithm over a given time measure for a given benchmark instance.
 */
final class _ExperimentSetInstanceProgressDiagramsHolder extends
    ComparisonModule {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create!
   * 
   * @param owner
   *          the macro's owner
   */
  _ExperimentSetInstanceProgressDiagramsHolder(final Module owner) {
    super("experimentSetInstanceProgress", owner, false); //$NON-NLS-1$
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
    for (final Accessor axs : Accessor.TIME_MEASURES) {
      new _ExperimentSetInstanceProgressDiagrams(this, axs,//
          Accessor.UNBIASED_TIME_MEASURES.contains(axs)
      // ((axs == Accessor.DE) || //
      // (axs == Accessor.FE) || //
      // (axs == Accessor.NORMALIZED_RUNTIME))
      );
    }
  }

  /** {@inheritDoc} */
  @Override
  protected final void writeSectionTitle(final SectionTitle title,
      final ExperimentSet data) throws IOException {
    title.write("Progress of in Terms of Different Time Measures"); //$NON-NLS-1$
  }
}
