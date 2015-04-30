package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * A labeled element with multiple labels.
 */
abstract class _MultiLabeledElement extends _LabeledElement<_MultiLabel> {

  /**
   * create the document element
   * 
   * @param owner
   *          the owning element
   * @param label
   *          the label
   * @throws IOException
   *           if io fails
   */
  _MultiLabeledElement(final Element owner, final Label label)
      throws IOException {
    super(owner, label);

    final _MultiLabel lbl;

    lbl = this.m_label;
    if (lbl != null) {
      if (lbl.m_labels.get(0).getType() != this.getLabelType()) {
        throw new IllegalArgumentException();
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  protected SingleLabel createSingleLabel(final ELabelType type,
      final String key) throws IOException {
    return super.createSingleLabel(type, key);
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSingleLabel(final SingleLabel label,
      final String info) throws IOException {
    super.writeSingleLabel(label, info);
  }

  /** {@inheritDoc} */
  @Override
  final _MultiLabel unpackLabel(final Label label) throws IOException {
    final _MultiLabel ml;
    final _IndirectLabel il;

    if (label == null) {// no label used
      return null;
    }

    if (label == Label.AUTO_LABEL) {// ok, generate a label on the fly
      return new _MultiLabel(this.createSingleLabel(this.getLabelType(),
          this.m_document.autoName()));
    }

    if (label instanceof _MultiLabel) {// a multi label ready for use
      return ((_MultiLabel) label);
    }

    if (label instanceof SingleLabel) {// a single label ready for use
      return new _MultiLabel((SingleLabel) label);
    }

    if (label instanceof _IndirectLabel) {// an indirect label
      // either contains a multi-label, so we can use it directly
      // or has a single-label, so we just wrap it in a multi-label
      il = ((_IndirectLabel) label);
      ml = this.unpackLabel(il.m_label);
      il.m_label = ml;
      return ml;
    }

    throw new IllegalArgumentException();
  }
}
