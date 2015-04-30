package org.logisticPlanning.utils.text;

/**
 * A collection of braces.
 */
public enum EBraces {

  /** parenthesis */
  PARENTHESES(TextUtils.PARENTHESES_A, TextUtils.PARENTHESES_B),
  /** brackets */
  BRACKETS(TextUtils.BRACKETS_A, TextUtils.BRACKETS_B),
  /** braces */
  BRACES(TextUtils.BRACES_A, TextUtils.BRACES_B),
  /** vertical */
  VERTICAL(TextUtils.VERTICAL_A, TextUtils.VERTICAL_B),
  /** with dash */
  BRACKETS_WITH_DASH(TextUtils.BRACKETS_WITH_DASH_A,
      TextUtils.BRACKETS_WITH_DASH_B),
  /** raised parentheses */
  RAISED_PARENTHESES(TextUtils.RAISED_PARENTHESES_A,
      TextUtils.RAISED_PARENTHESES_B),
  /** sunk parentheses */
  SUNK_PARENTHESES(TextUtils.SUNK_PARENTHESES_A,
      TextUtils.SUNK_PARENTHESES_B),
  /** chevron */
  CHEVRON(TextUtils.CHEVRON_A, TextUtils.CHEVRON_B),

  /** chinese black parentheses */
  CHINESE_BLACK_PARENTHESES(TextUtils.CHINESE_BLACK_PARENTHESES_A,
      TextUtils.CHINESE_BLACK_PARENTHESES_B),

  /** chinese convex parentheses */
  CHINESE_CONVEX(TextUtils.CHINESE_CONVEX_A, TextUtils.CHINESE_CONVEX_B),

  /** chinese white parentheses */
  CHINESE_WHITE_PARENTHESES(TextUtils.CHINESE_WHITE_PARENTHESES_A,
      TextUtils.CHINESE_WHITE_PARENTHESES_B),

  /** chinese double convex parentheses */
  CHINESE_DOUBLE_CONVEX(TextUtils.CHINESE_DOUBLE_CONVEX_A,
      TextUtils.CHINESE_DOUBLE_CONVEX_B),

  /** chinese double braces */
  CHINESE_DOUBLE_BRACES(TextUtils.CHINESE_DOUBLE_BRACES_A,
      TextUtils.CHINESE_DOUBLE_BRACES_B), ;

  /** the default brace to use */
  public static final EBraces DEFAULT = PARENTHESES;

  /** the curly brace */
  public static final EBraces CURLY_BRACES = BRACES;
  /** the square brackets */
  public static final EBraces SQUARE_BRACKETS = BRACKETS;
  /** the angle brackets */
  public static final EBraces ANGLE_BRACKETS = CHEVRON;

  /** the beginning char */
  private final char m_begin;

  /** the ending char */
  private final char m_end;

  /**
   * Create
   * 
   * @param begin
   *          the beginning character
   * @param end
   *          the ending character
   */
  private EBraces(final char begin, final char end) {
    this.m_begin = begin;
    this.m_end = end;
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
}
