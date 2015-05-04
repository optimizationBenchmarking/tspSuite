package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The labeled element element.
 *
 * @param <LT>
 *          the label type
 */
abstract class _LabeledElement<LT extends _OwnedLabel> extends Element {

  /** the label */
  final LT m_label;

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
  _LabeledElement(final Element owner, final Label label)
      throws IOException {
    super(owner);

    final LT lbl;

    this.m_label = lbl = this.unpackLabel(label);

    if (lbl != null) {
      if (lbl.m_owned) {
        throw new IllegalStateException(//
            "The label '" + label + //$NON-NLS-1$
            "' has already been put somewhere else and cannot be used here!"); //$NON-NLS-1$
      }
      lbl.m_owned = true;
    }
  }

  /**
   * this method is used to create and set the internal label to be used
   *
   * @param label
   *          the input label
   * @return the label to be used
   * @throws IOException
   *           if io fails
   */
  abstract LT unpackLabel(final Label label) throws IOException;

  /**
   * Get the label type
   *
   * @return the label type
   */
  abstract ELabelType getLabelType();

  /**
   * get the label
   *
   * @return the label
   */
  public Label getLabel() {
    return this.m_label;
  }
}
