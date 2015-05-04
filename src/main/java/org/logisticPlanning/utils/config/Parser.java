package org.logisticPlanning.utils.config;

/**
 * A parser takes an object, e.g., from the command line, and translates it
 * to an object
 *
 * @param <T>
 *          the return type
 */
public abstract class Parser<T> {

  /** value */
  static final String MIN_MAX = "Minimum allowed value must be less or equal to maximum allowed value, but value range is ("; //$NON-NLS-1$

  /** value too small */
  static final String MBLOET = "Value must be larger or equal to "; //$NON-NLS-1$

  /** value is */
  static final String BI = " but is "; //$NON-NLS-1$

  /** value too large */
  static final String MBSOET = "Value must be smaller or equal to "; //$NON-NLS-1$

  /** create */
  Parser() {
    super();
  }

  /**
   * Parse an object
   *
   * @param value
   *          the value
   * @return the parse result
   */
  public abstract T parse(final Object value);

  /**
   * get the object class
   *
   * @return the object class
   */
  public abstract Class<T> objectClass();
}
