package org.logisticPlanning.tsp.evaluation.modules.spec;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.logisticPlanning.tsp.evaluation.data.DataSet;
import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.modules.impl.single.SingleSetups;
import org.logisticPlanning.tsp.evaluation.modules.impl.single.ert.ExperimentERTHolder;
import org.logisticPlanning.tsp.evaluation.modules.impl.single.litComp.LiteratureComparison;
import org.logisticPlanning.tsp.evaluation.modules.impl.single.progress.ExperimentProgressHolder;
import org.logisticPlanning.utils.document.spec.Element;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.Section;
import org.logisticPlanning.utils.document.spec.SectionBody;
import org.logisticPlanning.utils.document.spec.SectionTitle;

/**
 * The meta-module containing all the single modules.
 */
final class _Single extends Module {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create
   *
   * @param owner
   *          the owning module
   */
  _Single(final RootModule owner) {
    super("single", owner, true); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @SuppressWarnings("unused")
  @Override
  final void createChildModules() {
    new SingleSetups(this, true);
    new ExperimentERTHolder(this);
    new ExperimentProgressHolder(this);
    new LiteratureComparison(this, true);
  }

  /** {@inheritDoc} */
  @Override
  final void _addChild(final Module child) {
    if (child instanceof SingleModule) {
      super._addChild(child);
    } else {
      this._childNotPermittedError(child);
    }
  }

  /** {@inheritDoc} */
  @Override
  final void _initialize(final Header header, final DataSet<?> data)
      throws IOException {//
    final int size;
    final ExperimentSet es;
    int i;

    if (data instanceof ExperimentSet) {
      size = data.size();
      if (size > 0) {
        es = ((ExperimentSet) data);
        for (i = 0; i < size; i++) {
          this._initializeChildren(header, es.get(i));
        }
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
    final int childCount, size;
    final Logger log;
    final ExperimentSet es;

    if (data instanceof ExperimentSet) {
      size = data.size();
      if (size > 0) {

        childCount = this.getActiveChildren().size();

        if (childCount > 0) {
          es = ((ExperimentSet) data);

          if (size > 1) {
            try (final Section sec = Section.section(body, null)) {
              try (final SectionTitle title = sec.sectionTitle()) {
                title.write("Single Experiment Results"); //$NON-NLS-1$
              }
              try (final SectionBody bdy = sec.sectionBody()) {
                this.__writeSingle(bdy, es);
              }
            }
          } else {
            if (childCount <= 1) {
              this._runChildren(body, es.get(0));
            } else {
              this.__writeSingle(body, es);
            }
          }

        } else {
          log = this.getLogger();
          if ((log != null) && (log.isLoggable(Level.INFO))) {
            log.info(//
                "No single evaluator is active. Quitting single evaluation procedure without doing anything."); //$NON-NLS-1$
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
   * Write the single sections, one section per experiment
   *
   * @param body
   *          the body
   * @param ds
   *          the data set (experiment set)
   * @throws IOException
   *           if io fails
   */
  private final void __writeSingle(final Element body,
      final ExperimentSet ds) throws IOException {
    final int size;
    Experiment ex;
    int i;

    size = ds.size();
    for (i = 0; i < size; i++) {
      ex = ds.get(i);
      try (final Section sec = Section.section(body, null)) {
        try (final SectionTitle title = sec.sectionTitle()) {
          title.macroInvoke(this.m_root.getShortName(ex));
        }
        try (final SectionBody bdy = sec.sectionBody()) {
          this._runChildren(bdy, ex);
        }
      }
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
          "Single module needs at least one experiment in order to do its work, but the experiment set only contains " //$NON-NLS-1$
          + data.size() + ". Module quits '" + what + //$NON-NLS-1$
          "' without doing anything."); //$NON-NLS-1$
    }
  }

}
