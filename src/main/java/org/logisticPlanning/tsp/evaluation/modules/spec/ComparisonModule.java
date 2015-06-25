package org.logisticPlanning.tsp.evaluation.modules.spec;

import java.io.IOException;

import org.logisticPlanning.tsp.evaluation.data.DataSet;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.utils.document.spec.Element;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.Label;
import org.logisticPlanning.utils.document.spec.SectionBody;
import org.logisticPlanning.utils.document.spec.SectionTitle;

/**
 * A module that evaluates an experiment set and compares different
 * algorithms.
 */
public class ComparisonModule extends _ConcreteModule {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create
   *
   * @param name
   *          the evaluator name
   * @param owner
   *          the owning module
   * @param active
   *          is this module initially active or not?
   */
  protected ComparisonModule(final String name, final Module owner,
      final boolean active) {
    super(name, owner, active);

    if (!((owner instanceof ComparisonModule) || (owner instanceof _Comparisons))) {
      throw new IllegalArgumentException(//
          "Instance of class '" + owner.getClass() + //$NON-NLS-1$
              "' not permitted as owner of instance of '" + //$NON-NLS-1$
              this.getClass() + "'.");//$NON-NLS-1$
    }
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

  /**
   * Get the label via which this module's section for the data set
   * {@code data} can be referenced, or {@code null} if this module has no
   * section dedicated to the specified data set.
   *
   * @param data
   *          the data set
   * @return the label
   */
  public final Label getLabel(final ExperimentSet data) {
    return this._getLabel(data);
  }

  /**
   * Write this module's section title
   *
   * @param title
   *          the title
   * @param data
   *          the data
   * @throws IOException
   *           if io fails
   */
  protected void writeSectionTitle(final SectionTitle title,
      final ExperimentSet data) throws IOException {
    //
  }

  /**
   * Write this module's section body
   *
   * @param body
   *          the body
   * @param data
   *          the data
   * @throws IOException
   *           if io fails
   */
  protected void writeSectionBody(final SectionBody body,
      final ExperimentSet data) throws IOException {
    super._writeSectionBody(body, data);
  }

  /** {@inheritDoc} */
  @Override
  final void _writeSectionTitle(final SectionTitle title,
      final DataSet<?> data) throws IOException {
    if (data instanceof ExperimentSet) {
      this.writeSectionTitle(title, ((ExperimentSet) data));
    } else {
      this._logModuleCanOnlyProcessWarning(ExperimentSet.class, data,
          "writing section title");//$NON-NLS-1$
    }
  }

  /** {@inheritDoc} */
  @Override
  final void _writeSectionBody(final SectionBody body,
      final DataSet<?> data) throws IOException {
    if (data instanceof ExperimentSet) {
      this.writeSectionBody(body, ((ExperimentSet) data));
    } else {
      this._logModuleCanOnlyProcessWarning(ExperimentSet.class, data,
          "writing section body");//$NON-NLS-1$
    }
  }

  /** {@inheritDoc} */
  @Override
  final boolean hasContent(final DataSet<?> data) {
    if (data instanceof ExperimentSet) {
      return this.hasContent((ExperimentSet) data);
    }
    this._logModuleCanOnlyProcessWarning(ExperimentSet.class, data,
        "checking content");//$NON-NLS-1$
    return false;
  }

  /**
   * Does this module have any printable contents for a given data set?
   *
   * @param data
   *          the data set
   * @return {@code true} if the module has printable contents,
   *         {@code false} otherwise
   */
  protected boolean hasContent(final ExperimentSet data) {
    return true;
  }

  /**
   * Initialize this module on a data set.
   *
   * @param header
   *          the header of the document
   * @param data
   *          the data element
   * @throws IOException
   *           if io fails
   */
  protected void initialize(final Header header, final ExperimentSet data)
      throws IOException {//
    super._initialize(header, data);
  }

  /** {@inheritDoc} */
  @Override
  final void _initialize(final Header header, final DataSet<?> data)
      throws IOException {
    if (data instanceof ExperimentSet) {
      this.initialize(header, ((ExperimentSet) data));
    } else {
      this._logModuleCanOnlyProcessWarning(ExperimentSet.class, data,
          "initialization");//$NON-NLS-1$
    }
  }

  /** {@inheritDoc} */
  @Override
  final void _run(final Element body, final DataSet<?> data)
      throws IOException {
    if (data instanceof ExperimentSet) {
      super._run(body, data);
    } else {
      this._logModuleCanOnlyProcessWarning(ExperimentSet.class, data,
          "running");//$NON-NLS-1$
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void createChildModules() {
    super.createChildModules();
  }
}
