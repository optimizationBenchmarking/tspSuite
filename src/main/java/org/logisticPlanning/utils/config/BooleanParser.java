package org.logisticPlanning.utils.config;

import java.util.concurrent.atomic.AtomicBoolean;

import org.logisticPlanning.utils.text.TextUtils;

/**
 * A parser takes an object and translates it to a boolean
 */
public final class BooleanParser extends Parser<Boolean> {

  /** the string parser instance */
  public static final BooleanParser INSTANCE = new BooleanParser();

  /** create */
  private BooleanParser() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final Boolean parse(final Object value) {

    if (value instanceof Boolean) {
      return ((Boolean) value);
    }

    if (value instanceof String) {
      return Boolean.valueOf(TextUtils.parseBoolean((String) value));
    }

    if (value instanceof AtomicBoolean) {
      return Boolean.valueOf(((AtomicBoolean) value).get());
    }

    throw new _ClassError(value.getClass(), Boolean.class);
  }

  /** {@inheritDoc} */
  @Override
  public final Class<Boolean> objectClass() {
    return Boolean.class;
  }
}
