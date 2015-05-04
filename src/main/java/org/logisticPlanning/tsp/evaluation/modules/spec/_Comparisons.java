package org.logisticPlanning.tsp.evaluation.modules.spec;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.logisticPlanning.tsp.evaluation.data.DataSet;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.modules.impl.comparison.ecdf.ExperimentSetECDFHolder;
import org.logisticPlanning.tsp.evaluation.modules.impl.comparison.ert.ExperimentSetERTHolder;
import org.logisticPlanning.tsp.evaluation.modules.impl.comparison.globalRanking.GlobalRankingSummary;
import org.logisticPlanning.tsp.evaluation.modules.impl.comparison.progress.ExperimentSetProgressHolder;
import org.logisticPlanning.tsp.evaluation.modules.impl.comparison.test.TestComparisonsHolder;
import org.logisticPlanning.utils.document.spec.Element;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.Section;
import org.logisticPlanning.utils.document.spec.SectionBody;
import org.logisticPlanning.utils.document.spec.SectionTitle;

/**
 * The meta-module containing all the comparisons.
 */
final class _Comparisons extends Module {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create
   *
   * @param owner
   *          the owning module
   */
  _Comparisons(final RootModule owner) {
    super("comparisons", owner, true); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @SuppressWarnings("unused")
  @Override
  final void createChildModules() {
    new ExperimentSetECDFHolder(this);
    new ExperimentSetERTHolder(this);
    new ExperimentSetProgressHolder(this);
    new TestComparisonsHolder(this);
    new GlobalRankingSummary(this);
  }

  /** {@inheritDoc} */
  @Override
  final void _addChild(final Module child) {
    if (child instanceof ComparisonModule) {
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
      if (data.size() > 0) {
        this._initializeChildren(header, data);
      } else {
        this.__logToSmall(data, "initialization");//$NON-NLS-1$
      }
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
      if (data.size() > 0) {

        cc = this.getActiveChildren().size();
        if (cc > 0) {
          if (cc > 1) {
            try (final Section sec = Section.section(body, null)) {
              try (final SectionTitle title = sec.sectionTitle()) {
                title.write("Comparison of Experimental Results"); //$NON-NLS-1$
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
            "No comparison module is active. Quitting comparison procedure without doing anything."); //$NON-NLS-1$
          }
        }
      } else {
        this.__logToSmall(data, "running");//$NON-NLS-1$
      }
    } else {
      this._logModuleCanOnlyProcessWarning(ExperimentSet.class, data,
          "running");//$NON-NLS-1$
    }
  }

  /**
   * log that there is not enough data
   *
   * @param data
   *          the data
   * @param what
   *          the what
   */
  private final void __logToSmall(final DataSet<?> data, final String what) {
    final Logger log;

    log = this.getLogger();
    if ((log != null) && (log.isLoggable(Level.INFO))) {
      log.info(//
      "Comparison module needs at least two experiments in order to performa a comparison, but the experiment set only contains " //$NON-NLS-1$
          + data.size() + ". Module quits '" + what + //$NON-NLS-1$
          "' without doing anything."); //$NON-NLS-1$
    }
  }

}
