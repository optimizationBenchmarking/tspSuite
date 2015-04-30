package org.logisticPlanning.utils.document.spec;

import java.util.ArrayList;

/**
 * a multi label
 */
final class _MultiLabel extends _OwnedLabel {

  /** the labels */
  final ArrayList<SingleLabel> m_labels;

  /**
   * Create a multi-label
   * 
   * @param first
   *          the first label
   */
  _MultiLabel(final SingleLabel first) {
    super();
    this.m_labels = new ArrayList<>();
    this.m_labels.add(first);
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    return String.valueOf(this.m_labels);
  }
}
