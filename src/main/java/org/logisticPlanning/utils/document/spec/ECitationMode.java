package org.logisticPlanning.utils.document.spec;

/**
 * the citation mode
 */
public enum ECitationMode {
  /** cite by id, i.e., prepend a non-breaking space */
  BY_ID_IN_SENTENCE(false, false),

  /** cite by id at the sentence start: use upper case, where appropriate */
  BY_ID_AT_SENTENCE_START(true, false),

  /** city by authors */
  BY_AUTHORS_IN_SENTENCE(false, true),

  /**
   * city by authors at sentence start, i.e., write this letter in upper
   * case
   */
  BY_AUTHORS_AT_SENTENCE_START(true, true);

  /** at sentence start */
  private final boolean m_atStart;

  /** print author names */
  private final boolean m_printAuth;

  /**
   * create
   *
   * @param atStart
   *          at sentence start
   * @param printAuth
   *          print author names
   */
  private ECitationMode(final boolean atStart, final boolean printAuth) {
    this.m_atStart = atStart;
    this.m_printAuth = printAuth;
  }

  /**
   * Is this reference at the sentence start
   *
   * @return {@code true} if this reference is at the sentence start,
   *         {@code false} otherwise
   */
  public final boolean isAtStart() {
    return this.m_atStart;
  }

  /**
   * Print the author names?
   *
   * @return {@code true} if author names should be printed, {@code false}
   *         if the id should be printed
   */
  public final boolean printAuthors() {
    return this.m_printAuth;
  }
}
