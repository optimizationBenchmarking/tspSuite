package org.logisticPlanning.utils.text;

/**
 * A collection of quotes.
 */
public enum EQuotes {

  /** double quotes */
  DEFAULT_DOUBLE(TextUtils.DEFAULT_DOUBLE_A, TextUtils.DEFAULT_DOUBLE_B, 2),

  /** single quotes */
  DEFAULT_SINGLE(TextUtils.DEFAULT_SINGLE_A, TextUtils.DEFAULT_SINGLE_B, 1),

  /** triple quotes */
  DEFAULT_TRIPLE(TextUtils.DEFAULT_TRIPLE_A, TextUtils.DEFAULT_TRIPLE_B, 3),

  /** alternative single quotes */
  ALT_SINGLE(TextUtils.ALT_SINGLE_A, TextUtils.ALT_SINGLE_B, 1),

  /** alternative double quotes */
  ALT_DOUBLE(TextUtils.ALT_DOUBLE_A, TextUtils.ALT_DOUBLE_B, 2),

  /** primitive double quotes */
  PRIMITIVE_DOUBLE(TextUtils.PRIMITIVE_DOUBLE, TextUtils.PRIMITIVE_DOUBLE,
      2),

      /** primitive single quotes */
      PRIMITIVE_SINGLE(TextUtils.PRIMITIVE_SINGLE, TextUtils.PRIMITIVE_SINGLE,
          1),

          /** double angle quotes */
          ANGLE_DOUBLE(TextUtils.ANGLE_DOUBLE_A, TextUtils.ANGLE_DOUBLE_B, 2),

          /** single angle quotes */
          ANGLE_SINGLE(TextUtils.ANGLE_SINGLE_A, TextUtils.ANGLE_SINGLE_B, 1),

          /** single Chinese angle quotes */
          CHINESE_ANGLE_SINGLE(TextUtils.CHINESE_ANGLE_SINGLE_A,
              TextUtils.CHINESE_ANGLE_SINGLE_B, 1),

              /** double Chinese angle quotes */
              CHINESE_ANGLE_DOUBLE(TextUtils.CHINESE_ANGLE_DOUBLE_A,
                  TextUtils.CHINESE_ANGLE_DOUBLE_B, 2),

                  /** single Chinese bracket quotes */
                  CHINESE_BRACKET_SINGLE(TextUtils.CHINESE_BRACKET_SINGLE_A,
                      TextUtils.CHINESE_BRACKET_SINGLE_B, 1),

                      /** double Chinese bracket quotes */
                      CHINESE_BRACKET_DOUBLE(TextUtils.CHINESE_BRACKET_DOUBLE_A,
                          TextUtils.CHINESE_BRACKET_DOUBLE_B, 2),

                          /** double angle quotes */
                          CHINESE_DOUBLE(TextUtils.CHINESE_DOUBLE_A, TextUtils.CHINESE_DOUBLE_B, 2),

                          /** accents misused as single quotes */
                          ACCENT_SINGLE(TextUtils.ACCENT_SINGLE_A, TextUtils.ACCENT_SINGLE_B, 2),

                          /** single bottom-top */
                          BOTTOM_TOP_SINGLE(TextUtils.SINGLE_BOTTOM_A, TextUtils.DEFAULT_SINGLE_B,
                              1),

                              /** double bottom-top */
                              BOTTOM_TOP_DOUBLE(TextUtils.DOUBLE_BOTTOM_A, TextUtils.DEFAULT_DOUBLE_B,
                                  2),

                                  /** primitive single angle quotes */
                                  PRIMITIVE_ANGLE_SINGLE(TextUtils.PRIMITIVE_ANGLE_SINGLE_A,
                                      TextUtils.PRIMITIVE_ANGLE_SINGLE_B, 1),

                                      /** single dingbat quotes */
                                      DINGBAT_SINGLE(TextUtils.DINGBAT_SINGLE_A, TextUtils.DINGBAT_SINGLE_B, 1),

                                      /** double dingbat quotes */
                                      DINGBAT_DOUBLE(TextUtils.DINGBAT_DOUBLE_A, TextUtils.DINGBAT_DOUBLE_B, 2), ;

  /** the default quotes to use */
  public static final EQuotes DEFAULT = DEFAULT_DOUBLE;

  /** the beginning char */
  private final char m_begin;

  /** the ending char */
  private final char m_end;

  /** the dash count of the quote */
  private final int m_dashCount;

  /**
   * Create
   *
   * @param begin
   *          the beginning character
   * @param end
   *          the ending character
   * @param dashCount
   *          the dash count of the quote
   */
  private EQuotes(final char begin, final char end, final int dashCount) {
    this.m_begin = begin;
    this.m_end = end;
    this.m_dashCount = dashCount;
  }

  /**
   * Get the beginning char
   *
   * @return the beginning char
   */
  public final char getBegin() {
    return this.m_begin;
  }

  /**
   * Get the end char
   *
   * @return the end char
   */
  public final char getEnd() {
    return this.m_end;
  }

  /**
   * Get the dash count of the quote
   *
   * @return the dash count of the quote
   */
  public final int getDashCount() {
    return this.m_dashCount;
  }
}
