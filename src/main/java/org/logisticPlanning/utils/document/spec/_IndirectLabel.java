package org.logisticPlanning.utils.document.spec;

/**
 * the internal class for references that may change under your feet
 */
final class _IndirectLabel extends Label {

  /** the internal label */
  Label m_label;

  /**
   * create the reference
   * 
   * @param label
   *          the label
   */
  _IndirectLabel(final Label label) {
    super();
    this.m_label = label;
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    return ('\'' + String.valueOf(this.m_label));
  }
}
