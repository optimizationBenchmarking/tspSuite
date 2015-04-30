package org.logisticPlanning.utils.config;

import org.logisticPlanning.utils.utils.ReflectionUtils;

/**
 * A parser takes an object and translates it to a class
 * 
 * @param <T>
 *          the instance type
 */
public final class ClassParser<T> extends Parser<Class<T>> {

  /** the base class */
  private final Class<T> m_base;

  /**
   * create
   * 
   * @param base
   *          the base class
   */
  public ClassParser(final Class<T> base) {
    super();
    this.m_base = base;
    if (base == null) {
      throw new IllegalArgumentException(//
          "Base class must not be null."); //$NON-NLS-1$
    }
  }

  /** {@inheritDoc} */
  @Override
  @SuppressWarnings("unchecked")
  public final Class<T> parse(final Object value) {
    final Class<T> res;

    if (value instanceof Class) {
      res = ((Class<T>) value);
      if (this.m_base.isAssignableFrom(res)) {
        return res;
      }

      throw new _ClassError(value.getClass(), res, Class.class,
          this.m_base);
    }

    if (value instanceof String) {
      try {
        return ReflectionUtils.getClass(((String) value), this.m_base);
      } catch (final RuntimeException re) {
        throw re;
      } catch (final Throwable tt) {
        throw new IllegalArgumentException(tt);
      }
    }

    throw new _ClassError(value.getClass(), Class.class);
  }

  /** {@inheritDoc} */
  @Override
  @SuppressWarnings("unchecked")
  public final Class<Class<T>> objectClass() {
    return ((Class<Class<T>>) ((Object) (Class.class)));
  }
}
