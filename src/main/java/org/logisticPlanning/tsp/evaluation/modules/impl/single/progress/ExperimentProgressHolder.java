package org.logisticPlanning.tsp.evaluation.modules.impl.single.progress;

import java.io.IOException;

import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.tsp.evaluation.modules.spec.SingleModule;
import org.logisticPlanning.utils.document.spec.SectionTitle;

/**
 * This evaluator module will print diagrams showing the progress of an
 * algorithm from different perspectives.
 */
public final class ExperimentProgressHolder extends SingleModule {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create!
   * 
   * @param owner
   *          the macro's owner
   */
  public ExperimentProgressHolder(final Module owner) {
    super("experimentProgress", owner, false); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected final boolean hasContent(final Experiment data) {
    return false;
  }

  /** {@inheritDoc} */
  @SuppressWarnings("unused")
  @Override
  protected final void createChildModules() {
    new _ExperimentProgressDiagrams(this, false);
    new _ExperimentScaleProgressDiagramsHolder(this);
    new _ExperimentInstanceProgressDiagramsHolder(this);
  }

  /** {@inheritDoc} */
  @Override
  protected final void writeSectionTitle(final SectionTitle title,
      final Experiment data) throws IOException {
    title.write("Progress of "); //$NON-NLS-1$
    title.macroInvoke(this.getRoot().getShortName(data));
  }
}
