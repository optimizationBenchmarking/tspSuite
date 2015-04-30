package org.logisticPlanning.utils.config;

import java.net.URI;

/**
 * A parser takes an object and translates it to a uri
 */
public final class URIParser extends Parser<URI> {

  /** the string parser instance */
  public static final URIParser INSTANCE = new URIParser();

  /** create */
  private URIParser() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final URI parse(final Object value) {
    if (value instanceof URI) {
      return ((URI) value);
    }

    if (value instanceof String) {
      try {
        return new URI((String) value);
      } catch (final RuntimeException re) {
        throw re;
      } catch (final Throwable t) {
        throw new IllegalArgumentException(t);
      }
    }

    throw new _ClassError(value.getClass(), URI.class);
  }

  /** {@inheritDoc} */
  @Override
  public final Class<URI> objectClass() {
    return URI.class;
  }
}
