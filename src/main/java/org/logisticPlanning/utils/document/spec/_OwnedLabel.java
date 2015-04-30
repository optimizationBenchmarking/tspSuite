package org.logisticPlanning.utils.document.spec;

/**
 * the base owned label class
 */
abstract class _OwnedLabel extends Label {

  /** is the label owned? */
  boolean m_owned;

  /** create the reference */
  _OwnedLabel() {
    super();
  }
}
