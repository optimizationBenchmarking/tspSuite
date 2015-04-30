package org.logisticPlanning.utils.document.spec;

/**
 * the reference type: the thing we're referencing
 */
public enum ELabelType {

  /** section */
  SECTION("Sections"), //$NON-NLS-1$

  /** equations */
  EQUATION("Equations"), //$NON-NLS-1$

  /** tables */
  TABLE("Tables"), //$NON-NLS-1$

  /** figure */
  FIGURE("Figures"), //$NON-NLS-1$

  /** sub-figures */
  SUB_FIGURE("Figures"); //$NON-NLS-1$

  /** the single text */
  private final String m_single;

  /** the multiple text */
  private final String m_multiple;

  /**
   * create the reference type
   * 
   * @param m
   *          the multiple text
   */
  private ELabelType(final String m) {
    this.m_single = m.substring(0, m.length() - 1);
    this.m_multiple = m;
  }

  /**
   * get the string to be used when referencing single elements of this
   * type
   * 
   * @return the string to be used when referencing single elements of this
   *         type
   */
  public final String single() {
    return this.m_single;
  }

  /**
   * get the string to be used when referencing multiple elements of this
   * type
   * 
   * @return the string to be used when referencing multiple elements of
   *         this type
   */
  public final String multiple() {
    return this.m_multiple;
  }
}
