package org.logisticPlanning.utils.document.spec.bib;

/**
 * The month
 */
public enum EBibMonth {

  /** january */
  JANUARY(31, "jan", "January"), //$NON-NLS-1$//$NON-NLS-2$

  /** february */
  FEBRUARY(29, "feb", "February"), //$NON-NLS-1$//$NON-NLS-2$

  /** march */
  MARCH(31, "mar", "March"), //$NON-NLS-1$//$NON-NLS-2$

  /** april */
  APRIL(30, "apr", "April"), //$NON-NLS-1$//$NON-NLS-2$

  /** may */
  MAY(31, "may", "May"), //$NON-NLS-1$//$NON-NLS-2$

  /** JUNE */
  JUNE(30, "jun", "June"), //$NON-NLS-1$//$NON-NLS-2$

  /** july */
  JULY(31, "jul", "July"), //$NON-NLS-1$//$NON-NLS-2$

  /** august */
  AUGUST(31, "aug", "August"), //$NON-NLS-1$//$NON-NLS-2$

  /** september */
  SEPTEMBER(30, "sep", "September"), //$NON-NLS-1$//$NON-NLS-2$

  /** october */
  OCTOBER(31, "oct", "October"), //$NON-NLS-1$//$NON-NLS-2$

  /** november */
  NOVEMBER(30, "nov", "November"), //$NON-NLS-1$//$NON-NLS-2$

  /** december */
  DECEMBER(31, "dec", "December"); //$NON-NLS-1$//$NON-NLS-2$

  /** the maximum days */
  final int m_days;

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the months */
  static final EBibMonth[] MONTHS = EBibMonth.values();

  /** the shortcut */
  final String m_short;

  /** the shortcut */
  private final String m_full;

  /**
   * create
   *
   * @param sh
   *          the short name
   * @param lo
   *          the long name
   * @param days
   *          the number of days
   */
  EBibMonth(final int days, final String sh, final String lo) {
    this.m_days = days;
    this.m_short = sh;
    this.m_full = lo;
  }

  /**
   * Get the short name
   *
   * @return the short name
   */
  public final String getShortName() {
    return this.m_short;
  }

  /**
   * Get the long name
   *
   * @return the long name
   */
  public final String getLongName() {
    return this.m_full;
  }
}
