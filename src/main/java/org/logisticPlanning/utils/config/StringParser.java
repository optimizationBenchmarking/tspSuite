package org.logisticPlanning.utils.config;

/**
 * A parser takes an object and translates it to a string
 */
public final class StringParser extends Parser<String> {

  /** the string parser instance */
  public static final StringParser INSTANCE = new StringParser();

  /** create */
  private StringParser() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final String parse(final Object value) {
    if (value instanceof String) {
      return ((String) value);
    }

    throw new _ClassError(value.getClass(), String.class);
  }

  /** {@inheritDoc} */
  @Override
  public final Class<String> objectClass() {
    return String.class;
  }
}
