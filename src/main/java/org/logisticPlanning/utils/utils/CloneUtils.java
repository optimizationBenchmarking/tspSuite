package org.logisticPlanning.utils.utils;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.util.regex.Pattern;

import org.logisticPlanning.utils.io.ByteArrayIOStream;

/**
 * A static utility class for cloning objects.
 *
 * @since 0.9.8
 */
public final class CloneUtils {

  /**
   * Try to make a deep copy of a given object. This class tries several
   * different "official" methods to clone a class, but no dirty reflection
   * field copy stuff.
   * <ol>
   * <li>If the object is a primitive array, we try to use the primitive
   * array clone method.</li>
   * <li>If the object implements {@link java.lang.Cloneable} and has a
   * public {@link java.lang.Object#clone()} method, we try to invoke this
   * method via reflection.</li>
   * <li>If the object is {@link java.io.Serializable serializable}, we try
   * to serialize it to a stream in memory and the deserialize it to get a
   * new instance.</li>
   * <ol>
   *
   * @param object
   *          the object
   * @return a deep copy of the object, or the object itself if it is an
   *         instance of an immutable class, or {@code null} if
   *         {@code object==null}
   * @param <T>
   *          the type of the object to clone
   * @throws IllegalArgumentException
   *           if the cloning has failed
   */
  @SuppressWarnings("unchecked")
  public static final <T> T deepClone(final T object)
      throws IllegalArgumentException {
    final Class<T> clazz;
    final Method m;

    if (object == null) {
      return null;
    }

    clazz = (Class<T>) (object.getClass());
    if (clazz.isArray()) {
      // First, we check for arrays. Arrays can be cloned efficiently.

      if (object instanceof int[]) {
        int[] array;
        array = ((int[]) object);
        if (array.length <= 0) {
          array = EmptyUtils.EMPTY_INTS;
        } else {
          array = array.clone();
        }
        return ((T) array);
      }
      if (object instanceof double[]) {
        double[] array;
        array = ((double[]) object);
        if (array.length <= 0) {
          array = EmptyUtils.EMPTY_DOUBLES;
        } else {
          array = array.clone();
        }
        return ((T) array);
      }
      if (object instanceof long[]) {
        long[] array;
        array = ((long[]) object);
        if (array.length <= 0) {
          array = EmptyUtils.EMPTY_LONGS;
        } else {
          array = array.clone();
        }
        return ((T) array);
      }
      if (object instanceof boolean[]) {
        boolean[] array;
        array = ((boolean[]) object);
        if (array.length <= 0) {
          array = EmptyUtils.EMPTY_BOOLEANS;
        } else {
          array = array.clone();
        }
        return ((T) array);
      }
      if (object instanceof char[]) {
        char[] array;
        array = ((char[]) object);
        if (array.length <= 0) {
          array = EmptyUtils.EMPTY_CHARS;
        } else {
          array = array.clone();
        }
        return ((T) array);
      }
      if (object instanceof byte[]) {
        byte[] array;
        array = ((byte[]) object);
        if (array.length <= 0) {
          array = EmptyUtils.EMPTY_BYTES;
        } else {
          array = array.clone();
        }
        return ((T) array);
      }
      if (object instanceof float[]) {
        float[] array;
        array = ((float[]) object);
        if (array.length <= 0) {
          array = EmptyUtils.EMPTY_FLOATS;
        } else {
          array = array.clone();
        }
        return ((T) array);
      }
      if (object instanceof short[]) {
        short[] array;
        array = ((short[]) object);
        if (array.length <= 0) {
          array = EmptyUtils.EMPTY_SHORTS;
        } else {
          array = array.clone();
        }
        return ((T) array);
      }

      Object[] array;
      array = ((Object[]) object).clone();
      for (int i = array.length; (--i) >= 0;) {
        array[i] = CloneUtils.deepClone(array[i]);
      }
      return ((T) array);
    }

    // ok, we do not have an array - check for objects that are immutable
    if ((clazz == String.class) || //
        (clazz == URI.class) || //
        (clazz == URL.class) || //
        (clazz == Integer.class) || //
        (clazz == Double.class) || //
        (clazz == Long.class) || //
        (clazz == Boolean.class) || //
        (clazz == Character.class) || //
        (clazz == Byte.class) || //
        (clazz == Float.class) || //
        (clazz == Short.class) || //
        (clazz == Void.class) || //
        (clazz == BigDecimal.class) || //
        (clazz == BigInteger.class) || //
        (clazz == Pattern.class)) {
      return object;
    }

    // maybe we can clone the object directly?
    if (object instanceof Cloneable) {
      try {// let's see if it has a public clone method
        m = clazz.getMethod("clone"); //$NON-NLS-1$
        if (m != null) {
          try {
            return ((T) (m.invoke(object)));
          } catch (final OutOfMemoryError ome) {
            throw ome;
          } catch (final Throwable t) {
            throw new IllegalArgumentException(t);
          }
        }
      } catch (final NoSuchMethodException nsme) {
        // this may happend and would be OK
      }
    }

    // if the object is serializable, we can try to serialize and
    // deserialize
    // it
    if (object instanceof Serializable) {

      try {
        try (final ByteArrayIOStream bos = new ByteArrayIOStream()) {
          try (final ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(object);
          }
          try (final ByteArrayInputStream ibs = bos.asInput()) {
            try (final ObjectInputStream ois = new ObjectInputStream(ibs)) {
              return ((T) (ois.readObject()));
            }
          }
        }
      } catch (final OutOfMemoryError ome) {
        throw ome;
      } catch (final Throwable t) {
        throw new IllegalArgumentException(t);
      }
    }

    // OK, if we get here, its finito: The object is not a primitive array
    // nor is it immutable. It is also not public cloneable and cannot be
    // serialized. This means we are out of "clean" and "official" ways to
    // copy the code. Thus, let's simply fail with an exception at the
    // bottom
    // of this method.

    throw new IllegalArgumentException("Object cannot be cloned."); //$NON-NLS-1$
  }

  /** don't instantiate a static class... */
  private CloneUtils() {
    throw new UnsupportedOperationException();
  }
}
