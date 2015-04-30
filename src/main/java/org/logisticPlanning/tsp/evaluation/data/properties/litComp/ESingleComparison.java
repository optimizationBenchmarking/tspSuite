package org.logisticPlanning.tsp.evaluation.data.properties.litComp;

/**
 * This constant denotes the outcomes of a single comparison
 */
public enum ESingleComparison {

  /** the algorithm is better */
  BETTER("better", //$NON-NLS-1$
      "is faster and provides a better result", //$NON-NLS-1$
      "is faster and provides better results", //$NON-NLS-1$
      null),

  /** the algorithm is same */
  SIMILAR(
      "similar", //$NON-NLS-1$
      "needs about the same time to achieve about the same result quality",//$NON-NLS-1$
      null, null),

  /** the algorithm is slower */
  SLOWER("slower", //$NON-NLS-1$
      "needs longer to get result of same quality", //$NON-NLS-1$
      "needs longer to get results of same quality", //$NON-NLS-1$
      null),

  /** the algorithm not comparable */
  NOT_COMPARABLE(
      "not comparable", //$NON-NLS-1$
      "does not achieve result of same quality, but was granted lower computational budged", //$NON-NLS-1$
      "does not achieve results of same quality, but was granted lower computational budged", //$NON-NLS-1$
      null),

  /** the algorithm is worse */
  WORSE(
      "worse", //$NON-NLS-1$
      "does not achieve similar quality result, even if given similar or more computational budged", //$NON-NLS-1$
      "does not achieve similar quality results, even if given similar or more computational budged", //$NON-NLS-1$
      null),

  /** the algorithm is maybe better */
  MAYBE_BETTER(BETTER),
  /** the algorithm is maybe similar */
  MAYBE_SIMILAR(SIMILAR),

  /** the algorithm is maybe slower */
  MAYBE_SLOWER(SLOWER),

  /** the algorithm is not comparable */
  MAYBE_NOT_COMPARABLE(NOT_COMPARABLE),

  /** the algorithm is probably */
  MAYBE_WORSE(WORSE);

  /** the list of all comparisons */
  static final ESingleComparison[] ALL = ESingleComparison.values();
  /** the list of all comparisons */
  static final ESingleComparison[] MAYBE = { MAYBE_BETTER, MAYBE_SIMILAR,
      MAYBE_SLOWER, MAYBE_NOT_COMPARABLE, MAYBE_WORSE };

  /** the short name */
  private final String m_short;

  /** the long name */
  private final String m_long;

  /** the long name in plural */
  private final String m_longPlural;

  /** the maybe */
  private final boolean m_maybe;

  /** the other */
  private ESingleComparison m_other;

  /**
   * Create a new single comparison
   * 
   * @param srt
   *          the short name
   * @param lng
   *          the long name
   * @param longPlural
   *          the plural of the long name
   * @param other
   *          the other
   */
  private ESingleComparison(final String srt, final String lng,
      final String longPlural, final ESingleComparison other) {
    this.m_short = srt;
    this.m_long = ((lng == null) ? this.m_short : lng);
    this.m_longPlural = ((longPlural != null) ? longPlural : this.m_long);
    if (other != null) {
      this.m_maybe = true;
      this.m_other = other;
      other.m_other = this;
    } else {
      this.m_maybe = false;
    }
  }

  /**
   * Create a new single comparison
   * 
   * @param other
   *          the single comparison to "maybe"
   */
  private ESingleComparison(final ESingleComparison other) {
    this(
        "maybe " + other.m_short, //$NON-NLS-1$
        other.m_long
            + ", but the runtime measure is the a clock time and thus values are not comparable (at least by a machine without further information)", //$NON-NLS-1$
        other.m_longPlural
            + ", but runtime measures are clock times and not comparable (at least by a machine without further information)", //$NON-NLS-1$
        other);
  }

  /**
   * get the short name
   * 
   * @return the short name
   */
  public final String getShortName() {
    return this.m_short;
  }

  /**
   * get the long name
   * 
   * @param plural
   *          the plural of the long name
   * @return the long name
   */
  public final String getLongName(final boolean plural) {
    return (plural ? this.m_longPlural : this.m_long);
  }

  /**
   * is this a maybe?
   * 
   * @return {@code true} if this result is a potential result due to
   *         runtime differences, @code false} otherwise
   */
  public final boolean isMaybe() {
    return this.m_maybe;
  }

  /**
   * The comparison result that would have been returned if the used time
   * measure was not a real/clock time if this measure is a step index, or
   * the other way around otherwise
   * 
   * @return the result
   */
  public ESingleComparison getOther() {
    return this.m_other;
  }
}
