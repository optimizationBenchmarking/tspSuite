package org.logisticPlanning.tsp.evaluation.modules.spec;

import java.io.IOException;

import org.logisticPlanning.tsp.evaluation.data.DataSet;
import org.logisticPlanning.tsp.evaluation.data.properties.LabelProperty;
import org.logisticPlanning.utils.document.spec.ELabelType;
import org.logisticPlanning.utils.document.spec.Element;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.Label;
import org.logisticPlanning.utils.document.spec.Section;
import org.logisticPlanning.utils.document.spec.SectionBody;
import org.logisticPlanning.utils.document.spec.SectionTitle;

/**
 * A concrete module.
 *
 *
 @author <em><a href="http://www.it-weise.de/">Thomas Weise</a></em>,
 *         Email:&nbsp;<a
 *         href="mailto:tweise@ustc.edu.cn">tweise@ustc.edu.cn</a>
 */
abstract class _ConcreteModule extends Module {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the label property */
  private final LabelProperty m_label;

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
  _ConcreteModule(final String name, final Module owner,
      final boolean active) {
    super(name, owner, active);
    this.m_label = new LabelProperty(ELabelType.SECTION);
  }

  /**
   * Does this module have content or is it just a host module for its
   * children? If this method returns {@code false},
   * {@code writeSectionTitle} and {@code writeSectionBody} may not be
   * called.
   *
   * @param data
   *          the data set
   * @return {@code true} if this module has contents, {@code false}
   *         otherwise
   */
  @Override
  boolean hasContent(final DataSet<?> data) {
    return (data != null);
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
  final Label _getLabel(final DataSet<?> data) {
    return this.m_label.get(data, null);
  }

  /**
   * Write this module as a section
   *
   * @param body
   *          the body
   * @param data
   *          the data
   * @throws IOException
   *           if io fails
   */
  final void _writeAsSection(final Element body, final DataSet<?> data)
      throws IOException {
    try (final Section sec = Section.section(body,//
        this.m_label.get(data, body.getDocument()))) {
      try (final SectionTitle title = sec.sectionTitle()) {
        this._writeSectionTitle(title, data);
      }
      try (final SectionBody sbody = sec.sectionBody()) {
        this._writeSectionBody(sbody, data);
        // this._runChildren(sbody, data);
      }
    }
  }

  /**
   * Will we put this as own section?
   *
   * @param data
   *          the data
   * @return {@code true} if the module will be printed as own section,
   *         {@code false} otherwise
   */
  private final boolean __putSection(final DataSet<?> data) {
    int c;

    if (this.hasContent(data)) {
      return true;
    }

    c = 0;
    for (final Module m : this.getActiveChildren()) {
      if (m.hasContent(data)) {
        if ((++c) > 1) {
          return true;
        }
      }
    }
    return false;
    // return (this.hasContent(data) || (this._childCount() > 1));
  }

  /** {@inheritDoc} */
  @Override
  void _run(final Element body, final DataSet<?> data) throws IOException {
    if (this.__putSection(data)) {
      this._writeAsSection(body, data);
    } else {
      this._runChildren(body, data);
    }
  }

  /** {@inheritDoc} */
  @Override
  void _initialize(final Header header, final DataSet<?> data)
      throws IOException {//
    if (this.__putSection(data)) {
      this.m_label.get(data, header.getDocument());
    }
    super._initialize(header, data);
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
  void _writeSectionTitle(final SectionTitle title, final DataSet<?> data)
      throws IOException {
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
  void _writeSectionBody(final SectionBody body, final DataSet<?> data)
      throws IOException {
    this._runChildren(body, data);
  }

}
