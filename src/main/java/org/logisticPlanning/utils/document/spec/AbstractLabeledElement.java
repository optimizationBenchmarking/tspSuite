package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The labeled element element.
 */
public abstract class AbstractLabeledElement extends
    _LabeledElement<SingleLabel> {

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
  AbstractLabeledElement(final Element owner, final Label label)
      throws IOException {
    super(owner, label);

    final SingleLabel lbl;

    lbl = this.m_label;
    if (lbl != null) {
      if (lbl.getType() != this.getLabelType()) {
        throw new IllegalArgumentException(" Label type is " + //$NON-NLS-1$
            lbl.getType() + " but should be " + //$NON-NLS-1$
            this.getLabelType());
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  protected SingleLabel createSingleLabel(final ELabelType type,
      final String key) throws IOException {
    return this.m_owner.createSingleLabel(type, key);
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSingleLabel(final SingleLabel label,
      final String info) throws IOException {
    this.m_owner.writeSingleLabel(label, info);
  }

  /** {@inheritDoc} */
  @Override
  final SingleLabel unpackLabel(final Label label) throws IOException {
    SingleLabel ret;
    final _MultiLabel ml;
    final int i;

    if (label == null) {// no label used
      return null;
    }

    if (label == Label.AUTO_LABEL) {// ok, generate a label on the fly
      return this.createSingleLabel(this.getLabelType(),
          this.m_document.autoName());
    }

    if (label instanceof SingleLabel) {// a single label ready for use
      return ((SingleLabel) label);
    }

    if (label instanceof _MultiLabel) {// a multi label ready for use
      ml = ((_MultiLabel) label);
      i = ml.m_labels.size();
      if ((i > 0) && (!((ret = ml.m_labels.get(i - 1)).m_owned))) {
        return ret;
      }

      ret = this.createSingleLabel(this.getLabelType(),
          this.m_document.autoName());
      ml.m_labels.add(ret);

      return ret;
    }

    if (label instanceof _IndirectLabel) {// an indirect label
      // either contains a single label, so we can use it directly
      // or has a multi-label, so we just add one label to it, if
      // necessary
      return this.unpackLabel(((_IndirectLabel) label).m_label);
    }

    throw new IllegalArgumentException();
  }

  /** {@inheritDoc} */
  @Override
  public SingleLabel getLabel() {
    return this.m_label;
  }
}
