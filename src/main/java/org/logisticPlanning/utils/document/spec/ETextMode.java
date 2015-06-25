package org.logisticPlanning.utils.document.spec;

/**
 * the text mode for calculating text features
 */
public enum ETextMode {

  /** normal text */
  NORMAL_TEXT(11f),
  /** text constituting a figure caption */
  FIGURE_CAPTION(10f),
  /** text constituting a subfigure caption */
  SUBFIGURE_CAPTION(9f),
  /** text constituting a table caption */
  TABLE_CAPTION(10f);

  /** the default height in pt */
  final float m_defaultSizePt;

  /**
   * create
   *
   * @param s
   *          the default size
   */
  ETextMode(final float s) {
    this.m_defaultSizePt = s;
  }
}
