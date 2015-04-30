package org.logisticPlanning.utils.document.spec;

import org.logisticPlanning.utils.text.TextUtils;

/**
 * the abstract base class for all elements of the structured document
 * system
 */
abstract class _AbstractBase {

  /** create */
  _AbstractBase() {
    super();
  }

  /**
   * get the class name
   * 
   * @param h
   *          the handle
   * @return the name
   */
  static final String _name(final Object h) {
    return TextUtils.className((h != null) ? h.getClass() : null);
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return _AbstractBase._name(this);
  }
}
