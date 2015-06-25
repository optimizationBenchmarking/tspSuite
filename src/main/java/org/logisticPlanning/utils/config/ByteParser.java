package org.logisticPlanning.utils.config;

import org.logisticPlanning.utils.text.TextUtils;

/**
 * A parser takes an object and translates it to a {@link java.lang.Byte}
 * instance. All numerical parameter types are compatible, so the parser
 * actually just returns instances of {@link java.lang.Number} instead of
 * {@link java.lang.Byte}. This allows you to query an {@code int}eger
 * after querying a {@code long}.
 */
public final class ByteParser extends Parser<java.lang.Number> {

  /** the minimum allowed value */
  private final byte m_min;
  /** the maximum allowed value */
  private final byte m_max;

  /**
   * Create an {@code ByteParser} instance.
   *
   * @param min
   *          the minimum allowed value, a
   *          {@link java.lang.IllegalArgumentException
   *          IllegalArgumentException} will be thrown if this constraint
   *          is violated
   * @param max
   *          the maximum allowed value, a
   *          {@link java.lang.IllegalArgumentException
   *          IllegalArgumentException} will be thrown if this constraint
   *          is violated
   */
  public ByteParser(final byte min, final byte max) {
    super();

    if (max < min) {
      throw new IllegalArgumentException((((Parser.MIN_MAX + //
          min) + ',') + max) + ']');
    }

    this.m_min = min;
    this.m_max = max;
  }

  /** {@inheritDoc} */
  @Override
  public final java.lang.Number parse(final Object value) {
    final byte number;
    final java.lang.Number ret;

    check: {
      find: {
        if (value instanceof java.lang.Number) {
          ret = ((java.lang.Number) value);
          number = ret.byteValue();
          break find;
        }

        if (value instanceof String) {
          number = TextUtils.parseByte((String) value);
        } else {
          break check;
        }

        ret = java.lang.Byte.valueOf(number);
      } // end of find

      if (number < this.m_min) {
        throw new IllegalArgumentException((((Parser.MBLOET + //
            this.m_min) + Parser.BI) + number) + '.'); //
      }

      if (number > this.m_max) {
        throw new IllegalArgumentException((((Parser.MBSOET + //
            this.m_max) + Parser.BI) + number) + '.'); //
      }

      return ret;
    } // end of check

    throw new _ClassError(value.getClass(), java.lang.Byte.class);
  }

  /** {@inheritDoc} */
  @Override
  public final Class<java.lang.Number> objectClass() {
    return java.lang.Number.class;
  }
}
