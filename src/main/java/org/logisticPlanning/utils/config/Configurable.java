package org.logisticPlanning.utils.config;

import java.io.PrintStream;
import java.util.Calendar;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.logisticPlanning.utils.NamedObject;

/**
 * <p>
 * A configurable object is a named object that can be configured with an
 * instance of {@link org.logisticPlanning.utils.config.Configuration}.
 * </p>
 * <p>
 * Such objects should generally support a save way of making deep copies
 * via their {@link org.logisticPlanning.utils.NamedObject#clone() clone()}
 * method if they have any state information that may be modified outside
 * of their
 * {@link org.logisticPlanning.utils.config.Configurable#configure(Configuration)
 * configure(Configurable)} method. The idea is that one instance is
 * configured, but an arbitrary number of copied instances will potentially
 * be used to run experiments in parallel. This holds for both, classes of
 * the solver package as well as classes of the benchmarking package.
 * </p>
 */
public class Configurable extends NamedObject {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the null string */
  private static final String NULL = String.valueOf((Object) null);

  /** an internal flag object */
  private static final Object PRESENT = new Object();

  /** the parameter identifying a request for help: {@value} */
  public static final String PARAM_HELP = "help"; //$NON-NLS-1$

  /**
   * instantiate
   * 
   * @param name
   *          the name
   */
  protected Configurable(final String name) {
    super(name);
  }

  /**
   * Configure this object. This method accesses an instance of
   * {@link org.logisticPlanning.utils.config.Configuration} to initialize
   * and set up the parameters of this object.
   * {@link org.logisticPlanning.utils.config.Configuration Configuration}
   * is basically a fancy, typed, case-insensitive hash map. It can be
   * filled either manually/from your source code, or by parsing the
   * command line arguments, or from a configuration file, or all of the
   * above. Parameter names are strings and used as keys into this hash map
   * and the values are, well, the parameter values. When overriding this
   * method, you must always call the super method.
   * 
   * @param config
   *          the configuration to use
   */
  public void configure(final Configuration config) {
    //
  }

  /**
   * <p>
   * Print the configuration of this object. This configuration may present
   * more parameters than those that can be be conigured via
   * {@link #configure(Configuration)}.
   * </p>
   * <p>
   * This method can assume to be at the beginning of a new line. Each
   * parameter tuple should end with a newline character (via
   * {@link java.io.PrintStream#println() println} and friends).
   * </p>
   * 
   * @param ps
   *          the print stream to print to
   */
  public void printConfiguration(final PrintStream ps) {
    //
  }

  /**
   * Print a list of parameters that this configurable object has.This
   * method can assume to be at the beginning of a new line. Each parameter
   * tuple should end with a newline character (via
   * {@link java.io.PrintStream#println() println} and friends).
   * 
   * @param ps
   *          the print stream to print to
   */
  public void printParameters(final PrintStream ps) {
    //
  }

  /**
   * print a separator between a parameter key and its value
   * 
   * @param key
   *          the key to print
   * @param ps
   *          the print stream
   */
  public static final void printKey(final String key, final PrintStream ps) {
    ps.print(key);
    ps.print(':');
    ps.print('\t');
  }

  /**
   * Print an object. If the object is an instance of {@code Configurable},
   * its {@link #printConfiguration(PrintStream)} method will be called as
   * well.
   * 
   * @param object
   *          the object to be printed
   * @param ps
   *          the object
   */
  public static final void printlnObject(final Object object,
      final PrintStream ps) {
    IdentityHashMap<Configurable, Object> al;

    al = new IdentityHashMap<>();

    Configurable._printObject(object, ps, al);
    ps.println();

    if (object instanceof Configurable) {
      al.put(((Configurable) object), Configurable.PRESENT);
    }

    for (final Configurable t : al.keySet()) {
      t.printConfiguration(ps);
    }
  }

  /**
   * Print an object
   * 
   * @param object
   *          the object to be printed
   * @param ps
   *          the object
   * @param todo
   *          the list of objects to do
   */
  private static final void _printObject(final Object object,
      final PrintStream ps,
      final IdentityHashMap<Configurable, Object> todo) {
    final Class<?> objectClazz, instanceClazz;
    boolean first;

    if (object == null) {
      ps.print(Configurable.NULL);
      return;
    }

    objectClazz = object.getClass();
    if (objectClazz != null) {
      if (objectClazz.isArray()) {
        instanceClazz = objectClazz.getComponentType();
        if (instanceClazz != null) {
          if (instanceClazz == byte.class) {
            ps.print('[');
            first = true;
            for (final byte oo : ((byte[]) object)) {
              if (first) {
                first = false;
              } else {
                ps.print(',');
                ps.print(' ');
              }
              ps.print(oo);
            }
            ps.print(']');
            return;
          }
          if (instanceClazz == short.class) {
            ps.print('[');
            first = true;
            for (final short oo : ((short[]) object)) {
              if (first) {
                first = false;
              } else {
                ps.print(',');
                ps.print(' ');
              }
              ps.print(oo);
            }
            ps.print(']');
            return;
          }
          if (instanceClazz == int.class) {
            ps.print('[');
            first = true;
            for (final int oo : ((int[]) object)) {
              if (first) {
                first = false;
              } else {
                ps.print(',');
                ps.print(' ');
              }
              ps.print(oo);
            }
            ps.print(']');
            return;
          }
          if (instanceClazz == long.class) {
            ps.print('[');
            first = true;
            for (final long oo : ((long[]) object)) {
              if (first) {
                first = false;
              } else {
                ps.print(',');
                ps.print(' ');
              }
              ps.print(oo);
            }
            ps.print(']');
            return;
          }
          if (instanceClazz == float.class) {
            ps.print('[');
            first = true;
            for (final float oo : ((float[]) object)) {
              if (first) {
                first = false;
              } else {
                ps.print(',');
                ps.print(' ');
              }
              ps.print(oo);
            }
            ps.print(']');
            return;
          }
          if (instanceClazz == double.class) {
            ps.print('[');
            first = true;
            for (final double oo : ((double[]) object)) {
              if (first) {
                first = false;
              } else {
                ps.print(',');
                ps.print(' ');
              }
              ps.print(oo);
            }
            ps.print(']');
            return;
          }
          if (instanceClazz == char.class) {
            ps.print('[');
            first = true;
            for (final char oo : ((char[]) object)) {
              if (first) {
                first = false;
              } else {
                ps.print(',');
                ps.print(' ');
              }
              ps.print(oo);
            }
            ps.print(']');
            return;
          }
          if (instanceClazz == boolean.class) {
            ps.print('[');
            first = true;
            for (final boolean oo : ((boolean[]) object)) {
              if (first) {
                first = false;
              } else {
                ps.print(',');
                ps.print(' ');
              }
              ps.print(oo);
            }
            ps.print(']');
            return;
          }
          if (instanceClazz == java.lang.String.class) {
            ps.print('[');
            first = true;
            for (final java.lang.String oo : ((java.lang.String[]) object)) {
              if (first) {
                first = false;
              } else {
                ps.print(',');
                ps.print(' ');
              }
              ps.print(oo);
            }
            ps.print(']');
            return;
          }

          if (!(instanceClazz.isPrimitive())) {
            ps.print('[');
            first = true;
            for (final java.lang.Object oo : ((java.lang.Object[]) object)) {
              if (first) {
                first = false;
              } else {
                ps.print(',');
                ps.print(' ');
              }
              Configurable._printObject(oo, ps, todo);
            }
            ps.print(']');
            return;
          }
        }
      }
    }

    if (object instanceof String) {
      ps.print((String) object);
      return;
    }

    if (object instanceof Calendar) {
      Configurable._printTimestamp(((Calendar) object), ps);
    } else {
      if (object instanceof Class) {
        Configurable._printClass(((Class<?>) (object)), ps);
      } else {
        if (object instanceof Throwable) {
          ((Throwable) object).printStackTrace(ps);
        } else {
          if (object instanceof Iterable) {
            ps.print('[');
            first = true;
            for (final java.lang.Object oo : ((Iterable<?>) object)) {
              if (first) {
                first = false;
              } else {
                ps.print(',');
                ps.print(' ');
              }
              Configurable._printObject(oo, ps, todo);
            }
            ps.print(']');
          } else {
            if (object instanceof Map) {
              ps.print('{');
              first = true;
              for (final Entry<?, ?> e : (((Map<?, ?>) object).entrySet())) {
                if (first) {
                  first = false;
                } else {
                  ps.print(',');
                  ps.print(' ');
                }
                Configurable._printObject(e.getKey(), ps, todo);
                ps.print('=');
                Configurable._printObject(e.getValue(), ps, todo);
              }
              ps.print('}');
            } else {
              ps.print(object.toString());

              if (object instanceof Configurable) {
                todo.put((Configurable) object, Configurable.PRESENT);
              }
            }
          }
        }
      }
    }

    if (objectClazz != null) {
      ps.print(' ');
      ps.print('(');
      Configurable._printClass(objectClazz, ps);
      ps.print(')');
    }
  }

  /**
   * print {@code null}
   * 
   * @param ps
   *          the print stream
   */
  public static final void printlnNull(final PrintStream ps) {
    ps.println(Configurable.NULL);
  }

  /**
   * print a class
   * 
   * @param clazz
   *          the class
   * @param ps
   *          the print stream
   */
  public static final void printlnClass(final Class<?> clazz,
      final PrintStream ps) {
    Configurable._printClass(clazz, ps);
    ps.println();
  }

  /**
   * print a class
   * 
   * @param clazz
   *          the class
   * @param ps
   *          the print stream
   */
  private static final void _printClass(final Class<?> clazz,
      final PrintStream ps) {
    String t;

    if (clazz == null) {
      ps.print(Configurable.NULL);
      return;
    }

    t = null;
    try {
      t = clazz.getCanonicalName();
    } catch (final Throwable x) {
      //
    }
    if (t == null) {
      try {
        t = clazz.getName();
      } catch (final Throwable x) {
        //
      }
    }
    if (t == null) {
      try {
        t = clazz.getSimpleName();
      } catch (final Throwable x) {
        //
      }
    }

    if (t != null) {
      ps.print(t);
    } else {
      ps.print(Configurable.NULL);
    }
  }

  /**
   * write a time stamp
   * 
   * @param c
   *          the calendar
   * @param ps
   *          the print stream
   */
  public static void printlnTimestamp(final Calendar c,
      final PrintStream ps) {
    Configurable._printTimestamp(c, ps);
    ps.println();
  }

  /**
   * write a time stamp
   * 
   * @param c
   *          the calendar
   * @param ps
   *          the print stream
   */
  private static void _printTimestamp(final Calendar c,
      final PrintStream ps) {
    int i, z;

    i = c.get(Calendar.YEAR);
    if (i < 1000) {
      ps.print('0');
    }
    if (i < 100) {
      ps.print('0');
    }
    if (i < 10) {
      ps.print('0');
    }
    ps.print(i);
    ps.print('-');

    i = (c.get(Calendar.MONTH) + 1);
    if (i < 10) {
      ps.print('0');
    }
    ps.print(i);
    ps.print('-');

    i = c.get(Calendar.DAY_OF_MONTH);
    if (i < 10) {
      ps.print('0');
    }
    ps.print(i);
    ps.print('-');

    i = c.get(Calendar.HOUR_OF_DAY);
    if (i < 10) {
      ps.print('0');
    }
    ps.print(i);
    ps.print(':');

    i = c.get(Calendar.MINUTE);
    if (i < 10) {
      ps.print('0');
    }
    ps.print(i);
    ps.print(':');

    i = c.get(Calendar.SECOND);
    if (i < 10) {
      ps.print('0');
    }
    ps.print(i);
    ps.print('_');

    i = c.get(Calendar.MILLISECOND);
    if (i < 100) {
      ps.print('0');
    }
    if (i < 10) {
      ps.print('0');
    }
    ps.print(i);
    ps.print('_');

    i = (c.get(Calendar.ZONE_OFFSET) + c.get(Calendar.DST_OFFSET)) / 60000;
    if (i < 0) {
      ps.print('-');
      i = (-i);
    } else {
      ps.print('+');
    }

    z = (i / 60);
    if (z < 10) {
      ps.print('0');
    }
    ps.print(z);
    ps.print(':');

    z = (i % 60);
    if (z < 10) {
      ps.print('0');
    }
    ps.print(z);
  }

  /**
   * Write the configuration of this object to a logger under the log-level
   * {@link java.util.logging.Level#CONFIG CONFIG}. This method basically
   * runs {@link #printConfiguration(PrintStream)} and pipes the output as
   * a string into the logger as a {@link java.util.logging.Level#CONFIG}
   * entry via the {@link java.util.logging.Logger#info(String)} method. It
   * prepends this object's
   * {@link org.logisticPlanning.utils.NamedObject#name() name} to the log
   * entry.
   * 
   * @param log
   *          the logger
   */
  public final void logConfiguration(final java.util.logging.Logger log) {
    this.logConfiguration(java.util.logging.Level.CONFIG, log);
  }

  /**
   * Write the configuration of this object to a logger for a given
   * {@link java.util.logging.Level log level}. This method basically runs
   * {@link #printConfiguration(PrintStream)} and pipes the output as a
   * string into the logger as a {@link java.util.logging.Level#CONFIG}
   * entry via the {@link java.util.logging.Logger#info(String)} method. It
   * prepends this object's
   * {@link org.logisticPlanning.utils.NamedObject#name() name} to the log
   * entry.
   * 
   * @param level
   *          the log level to use
   * @param log
   *          the logger
   */
  public final void logConfiguration(final java.util.logging.Level level,
      final java.util.logging.Logger log) {
    final String encoding, text;
    byte[] bytes;

    if ((log != null) && (level != null) && (log.isLoggable(level))) {
      encoding = org.logisticPlanning.utils.io.EEncoding.UTF_8
          .getJavaName();
      try {
        try (final java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream()) {
          try (final java.io.PrintStream ps = new java.io.PrintStream(bos,
              true, encoding)) {
            ps.println(this.name());
            this.printConfiguration(ps);
          }

          bytes = bos.toByteArray();
        }

        text = org.logisticPlanning.utils.text.TextUtils.prepare(//
            new String(bytes, encoding));
        bytes = null;
        log.log(level, text);
      } catch (final RuntimeException r) {
        throw r;
      } catch (final Throwable t) {
        throw new RuntimeException(t);
      }
    }
  }
}
