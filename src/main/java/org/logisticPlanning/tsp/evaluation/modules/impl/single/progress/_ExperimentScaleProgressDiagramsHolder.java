package org.logisticPlanning.tsp.evaluation.modules.impl.single.progress;

import java.io.IOException;

import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.EPropertyType;
import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.data.properties.instance.ExperimentInstancesProperty;
import org.logisticPlanning.tsp.evaluation.data.properties.scale.AllScaleInstancesProperty;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.tsp.evaluation.modules.spec.SingleModule;
import org.logisticPlanning.utils.document.spec.SectionTitle;

/**
 * This evaluator module will print diagrams showing the progress of and
 * algorithm over a given time measure for a scale of benchmarks .
 */
final class _ExperimentScaleProgressDiagramsHolder extends SingleModule {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create!
   *
   * @param owner
   *          the macro's owner
   */
  _ExperimentScaleProgressDiagramsHolder(final Module owner) {
    super("experimentScaleProgress", owner, false); //$NON-NLS-1$
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

    for (final int scale : new int[] { 2, 10 }) {
      for (final Accessor axs : Accessor.TIME_MEASURES) {

        new _ExperimentScaleProgressDiagrams(this, axs,
            new AllScaleInstancesProperty<>(//
                EPropertyType.TEMPORARILY_STORED, //
                ExperimentInstancesProperty
                    .oneMustHave2PointsOfAccessor(axs),//
                scale),//
            ((scale == 2) && //
            Accessor.UNBIASED_TIME_MEASURES.contains(axs)
            // ((axs == Accessor.DE) || //
            // (axs == Accessor.FE) || //
            // (axs == Accessor.NORMALIZED_RUNTIME))
            ));
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  protected final void writeSectionTitle(final SectionTitle title,
      final Experiment data) throws IOException {
    title.write("Progress of "); //$NON-NLS-1$
    title.macroInvoke(this.getRoot().getShortName(data));
    title.write(" in Terms of Different Time Measures and Scales"); //$NON-NLS-1$
  }
}
