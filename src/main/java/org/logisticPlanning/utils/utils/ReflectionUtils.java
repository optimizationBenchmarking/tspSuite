package org.logisticPlanning.utils.utils;

import java.lang.reflect.Field;

import org.logisticPlanning.utils.text.TextUtils;

/**
 * Some utilities for performing reflection.
 */
public final class ReflectionUtils {

  /**
   * We use these packages to look for classes. I know, that's bad policy,
   * but is quite nice for finding constants etc.
   */
  private static final String[] DEFAULT_PACKAGES = new String[] { null,//
      "java.lang.",//$NON-NLS-1$
      "java.util.", //$NON-NLS-1$
      "java.io.",//$NON-NLS-1$
      "java.math.",//$NON-NLS-1$
      "java.net.",//$NON-NLS-1$
  };

  /**
   * Try to obtain a class-class.
   *
   * @param name
   *          the string identifying the class
   * @param targetBase
   *          the target base class, or {@code null} if not needed
   * @return the constant, or {@code null} if it was {@code null} or is
   *         otherwise inaccessible without meeting any of the
   *         exception-throwing conditions
   * @param <C>
   *          the target base type
   * @throws LinkageError
   *           if the linkage fails
   * @throws ExceptionInInitializerError
   *           if the initialization provoked by this method fails
   * @throws ClassNotFoundException
   *           if the class is not found
   * @throws ClassCastException
   *           if the object is not null and is not assignable to the type
   *           T.
   */
  @SuppressWarnings("unchecked")
  public static final <C> Class<C> getClass(final String name,
      final Class<C> targetBase) throws LinkageError,
      ExceptionInInitializerError, ClassNotFoundException,
      ClassCastException {
    Class<?> c;
    final String n;
    Throwable error;

    n = TextUtils.prepare(name);
    if (n == null) {
      return null;
    }

    error = null;
    for (final String t : ReflectionUtils.DEFAULT_PACKAGES) {
      try {
        c = Class.forName((t == null) ? n : (t + n));
        if (c != null) {

          if (targetBase == null) {
            return ((Class<C>) c);
          }
          if (targetBase.isAssignableFrom(c)) {
            return ((Class<C>) c);
          }
          if (error == null) {
            error = new ClassCastException(
                "Cannot assign an instance of " + //$NON-NLS-1$
                    c.getCanonicalName() + " to a variable of type " + //$NON-NLS-1$
                    targetBase.getCanonicalName());
          }
        }
      } catch (final Throwable err) {
        if (error == null) {
          error = err;
        }
      }
    }

    if (error instanceof ClassCastException) {
      throw ((ClassCastException) error);
    }

    if (error instanceof LinkageError) {
      throw ((LinkageError) error);
    }

    if (error instanceof ExceptionInInitializerError) {
      throw ((ExceptionInInitializerError) error);
    }

    if (error instanceof ClassNotFoundException) {
      throw ((ClassNotFoundException) error);
    }

    throw new ClassNotFoundException(n, error);
  }

  /**
   * Try to obtain the value of a static constant. This method will try to
   * divide the identifier passed in into a class name and a constant/field
   * name.
   *
   * @param identifier
   *          the string identifying the class and constant
   * @param target
   *          the target class
   * @return the constant, or {@code null} if it was {@code null} or is
   *         otherwise inaccessible without meeting any of the
   *         exception-throwing conditions
   * @param <T>
   *          the target type
   * @throws LinkageError
   *           if the linkage fails
   * @throws ExceptionInInitializerError
   *           if the initialization provoked by this method fails
   * @throws ClassNotFoundException
   *           if the class is not found
   * @throws NoSuchFieldException
   *           if a field with the specified name is not found.
   * @throws SecurityException
   *           if security does not permit what we are doing
   * @throws IllegalAccessException
   *           if the field is enforcing Java language access control and
   *           the underlying field is inaccessible.
   * @throws IllegalArgumentException
   *           if the specified object is not an instance of the class or
   *           interface declaring the underlying field (or a subclass or
   *           implementor thereof).
   * @throws NullPointerException
   *           if the field is not static
   * @throws ClassCastException
   *           if the object is not null and is not assignable to the type
   *           T.
   */
  public static final <T> T getStaticConstant(final String identifier,
      final Class<T> target) throws LinkageError,
      ExceptionInInitializerError, ClassNotFoundException,
      NoSuchFieldException, SecurityException, IllegalAccessException,
      IllegalArgumentException, NullPointerException, ClassCastException {
    int idx;
    String n;
    Class<?> c;

    idx = identifier.lastIndexOf('#');
    if (idx < 0) {
      idx = identifier.lastIndexOf('.');
    }

    if (idx < 0) {
      return null;
    }

    n = TextUtils.prepare(identifier.substring(0, idx));
    if (n == null) {
      return null;
    }

    c = ReflectionUtils.getClass(n, null);

    if (c == null) {
      return null;
    }

    n = TextUtils.prepare(identifier.substring(idx + 1));
    if (n == null) {
      return null;
    }

    return ReflectionUtils.getStaticConstant(c, n, target);
  }

  /**
   * Try to obtain the value of a static constant.
   *
   * @param clazz
   *          the class which contains the constant
   * @param constant
   *          the name of the constant
   * @param target
   *          the target class: the returned object must be an instance of
   *          this type, otherwise, we will get a
   *          {@link java.lang.ClassCastException}
   * @return the constant, or {@code null} if it was {@code null} or is
   *         otherwise inaccessible without meeting any of the
   *         exception-throwing conditions
   * @param <T>
   *          the target type
   * @throws ExceptionInInitializerError
   *           if the initialization provoked by this method fails
   * @throws NoSuchFieldException
   *           if a field with the specified name is not found.
   * @throws SecurityException
   *           if security does not permit what we are doing
   * @throws IllegalAccessException
   *           if the field is enforcing Java language access control and
   *           the underlying field is inaccessible.
   * @throws IllegalArgumentException
   *           if the specified object is not an instance of the class or
   *           interface declaring the underlying field (or a subclass or
   *           implementor thereof).
   * @throws NullPointerException
   *           if the field is not static
   * @throws ClassCastException
   *           if the object is not null and is not assignable to the type
   *           T.
   */
  public static final <T> T getStaticConstant(final Class<?> clazz,
      final String constant, final Class<T> target)
      throws ExceptionInInitializerError, NoSuchFieldException,
      SecurityException, IllegalAccessException, IllegalArgumentException,
      NullPointerException, ClassCastException {
    final String n;
    final Field f;
    final Object t;

    if (clazz == null) {
      return null;
    }

    n = TextUtils.prepare(constant);
    if (n == null) {
      return null;
    }

    f = clazz.getField(n);
    if (f == null) {
      return null;
    }

    t = f.get(null);

    if (t == null) {
      return null;
    }

    return target.cast(t);
  }
}
