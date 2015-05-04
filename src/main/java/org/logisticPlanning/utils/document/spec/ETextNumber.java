package org.logisticPlanning.utils.document.spec;

/**
 * Different was to format a number as text.
 */
public enum ETextNumber {

  /**
   * the number is an ordinal number like {@code first}, {@code second},
   * {@code 100<sup>th</code>}
   */
  ORDINAL() {

  },

  /** the number is a plain number */
  TEXT() {

  };

  /**
   * transform a long number to a string
   *
   * @param number
   *          the number
   * @return the string
   */
  String _transformLong(final long number) {
    return String.valueOf(number);
  }

}
