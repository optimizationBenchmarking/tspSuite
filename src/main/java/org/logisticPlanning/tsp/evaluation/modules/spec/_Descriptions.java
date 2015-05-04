package org.logisticPlanning.tsp.evaluation.modules.spec;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.logisticPlanning.tsp.evaluation.data.DataSet;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.eval.DescEvaluation;
import org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.tests.DescTests;
import org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.tsp.DescTSP;
import org.logisticPlanning.utils.document.spec.Element;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.Section;
import org.logisticPlanning.utils.document.spec.SectionBody;
import org.logisticPlanning.utils.document.spec.SectionTitle;

/**
 * The meta-module containing all the descriptions.
 */
class _Descriptions extends Module {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create
   *
   * @param owner
   *          the owning module
   */
  _Descriptions(final RootModule owner) {
    super("descriptions", owner, true); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @SuppressWarnings("unused")
  @Override
  void createChildModules() {
    new DescTSP(this);
    new DescEvaluation(this);
    new DescTests(this);
  }

  /** {@inheritDoc} */
  @Override
  final void _addChild(final Module child) {
    if (child instanceof DescriptionModule) {
      super._addChild(child);
    } else {
      this._childNotPermittedError(child);
    }
  }

  /** {@inheritDoc} */
  @Override
  final void _initialize(final Header header, final DataSet<?> data)
      throws IOException {//
    if (data instanceof ExperimentSet) {
      this._initializeChildren(header, data);
    } else {
      this._logModuleCanOnlyProcessWarning(ExperimentSet.class, data,
          "initialization");//$NON-NLS-1$
    }
  }

  /** {@inheritDoc} */
  @Override
  final void _run(final Element body, final DataSet<?> data)
      throws IOException {
    final int cc;
    final Logger log;

    if (data instanceof ExperimentSet) {

      cc = this.getActiveChildren().size();
      if (cc > 0) {
        if (cc > 1) {
          try (final Section sec = Section.section(body, null)) {
            try (final SectionTitle title = sec.sectionTitle()) {
              title.write("Background"); //$NON-NLS-1$
            }
            try (final SectionBody bdy = sec.sectionBody()) {
              this._runChildren(bdy, data);
            }
          }
        } else {
          this._runChildren(body, data);
        }
      } else {
        log = this.getLogger();
        if ((log != null) && (log.isLoggable(Level.INFO))) {
          log.info(//
              "No description module is active. Quitting description procedure without doing anything."); //$NON-NLS-1$
        }
      }
    } else {
      this._logModuleCanOnlyProcessWarning(ExperimentSet.class, data,
          "running");//$NON-NLS-1$
    }
  }

}
