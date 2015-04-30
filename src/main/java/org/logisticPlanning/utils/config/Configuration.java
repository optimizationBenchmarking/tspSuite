package org.logisticPlanning.utils.config;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import org.logisticPlanning.utils.io.FileUtils;
import org.logisticPlanning.utils.text.TextUtils;
import org.logisticPlanning.utils.utils.ReflectionUtils;

/**
 * <p>
 * A class representing command line parameters and configurations. This
 * class provides high-level access to command line parameters and allows
 * you to read different values from them.
 * </p>
 */
public final class Configuration {

  /** the parameter identifying a configuration file: {@value} */
  public static final String PARAM_PROPERTY_FILE = "configFile"; //$NON-NLS-1$

  /** A value string which cannot occur */
  public static final String IMPOSSIBLE_VALUE_STRING = " \t\f\n\r"; //$NON-NLS-1$

  /** the configuration data */
  private final HashMap<String, Object> m_data;

  /** create */
  public Configuration() {
    super();
    this.m_data = new HashMap<>();
  }

  /**
   * get an instance of the type {@code T} stored under the given
   * {@code key}
   * 
   * @param key
   *          the key
   * @param parser
   *          the parser used to obtain the instance
   * @param def
   *          the default instance
   * @return the result
   * @param <T>
   *          the type
   */
  public final <T> T get(final String key, final Parser<T> parser,
      final T def) {
    final String k;
    final Object v;
    final T obj;

    k = Configuration.__checkKey(key);
    synchronized (this.m_data) {
      v = this.m_data.get(k);
      if (v == null) {
        return def;
      }
      obj = parser.parse(v);
      if (obj != v) {
        this.m_data.put(k, v);
      }
      return obj;
    }
  }

  /**
   * Get a parameter which is a class.
   * 
   * @param key
   *          the key
   * @param defClass
   *          the default class
   * @param baseClass
   *          the base class used to check type consistency
   * @return the class
   * @param <T>
   *          the base type
   */
  @SuppressWarnings("unchecked")
  public final <T> Class<T> getClass(final String key,
      final Class<T> baseClass, final Class<? extends T> defClass) {
    return this.get(key, new ClassParser<>(baseClass),
        ((Class<T>) defClass));
  }

  /**
   * <p>
   * Get a parameter value which is an instance of a class identified by
   * the parameter. It is assumed that the class identified by the
   * parameter has a default parameter-less constructor.
   * </p>
   * <p>
   * If a non-{@code null} value is returned, it will automatically be
   * checked if it is an instance of
   * {@link org.logisticPlanning.utils.config.Configurable Configurable}
   * first. If so, its
   * {@link org.logisticPlanning.utils.config.Configurable#configure(Configuration)
   * configure} method will be invoked with this
   * {@link org.logisticPlanning.utils.config.Configuration configuration}
   * as parameter. This also holds if it is decided to return
   * {@code defInstance} .
   * </p>
   * 
   * @param key
   *          the key
   * @param defInstance
   *          the default instance, or {@code null}
   * @param defClass
   *          the default class, or {@code null}
   * @param baseClass
   *          the base class of the instance to return, used to check type
   *          consistency
   * @return the instance
   * @param <T>
   *          the type of object to return
   */
  @SuppressWarnings("unchecked")
  public final <T> T getInstance(final String key,
      final Class<T> baseClass, final Class<? extends T> defClass,
      final T defInstance) {
    final Class<?> c;
    final Object o;
    final T ret;

    c = this.getClass(key, baseClass, defClass);
    if (c == null) {
      ret = defInstance;
    } else {

      try {
        o = c.newInstance();
      } catch (final Throwable tt) {
        throw new IllegalArgumentException(key, tt);
      }

      if (o == null) {
        ret = defInstance;
      } else {
        if (baseClass != null) {
          ret = baseClass.cast(o);
        } else {
          ret = ((T) o);
        }
      }
    }

    if (ret != null) {
      if (ret instanceof Configurable) {
        ((Configurable) ret).configure(this);
      }
    }
    return ret;
  }

  /**
   * <p>
   * Get the value of a public static constant. Constants will not be
   * configured automatically. They are static and should be considered as
   * immutable anyway.
   * </p>
   * 
   * @param key
   *          the key
   * @param defInstance
   *          the default instance, or {@code null}
   * @param owningClass
   *          the class which should contain the constant
   * @param baseClass
   *          the base class of the constant to return, used to check type
   *          consistency
   * @return the constant
   * @param <T>
   *          the type of object to return
   */
  public final <T> T getConstant(final String key,
      final Class<?> owningClass, final Class<T> baseClass,
      final T defInstance) {
    final String s;

    s = this.getString(key, null);
    if (s == null) {
      return defInstance;
    }

    try {
      return ReflectionUtils.getStaticConstant(owningClass, s, baseClass);
    } catch (final Throwable t) {
      throw new IllegalArgumentException(key, t);
    }
  }

  /**
   * check a key
   * 
   * @param key
   *          the key
   * @return the key for matching
   */
  private static final String __checkKey(final String key) {
    final String k;

    k = TextUtils.prepare(key);
    if (k == null) {
      throw new IllegalArgumentException(//
          "Key must not be empty."); //$NON-NLS-1$
    }

    return k.toLowerCase();
  }

  /**
   * Get a file parameter
   * 
   * @param key
   *          the key
   * @param def
   *          the default file
   * @return the canonical version thereof
   */
  public final File getFile(final String key, final File def) {
    return this.get(key, FileParser.INSTANCE, def);
  }

  /**
   * Get a 8 bit signed integer (byte) parameter.
   * 
   * @param key
   *          the key identifying the parameter
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
   * @param def
   *          the default value
   * @return the parameter value, a byte parsed from the configuration data
   */
  public final byte getByte(final String key, final byte min,
      final byte max, final byte def) {
    final String k;
    final Object v;
    final java.lang.Number number;

    k = Configuration.__checkKey(key);

    synchronized (this.m_data) {
      v = this.m_data.get(k);

      if (v == null) {
        return def;
      }

      number = new ByteParser(min, max).parse(v);

      if (number != v) {
        this.m_data.put(key, v);
      }
    }

    return number.byteValue();
  }

  /**
   * Get a 16 bit signed integer (short) parameter.
   * 
   * @param key
   *          the key identifying the parameter
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
   * @param def
   *          the default value
   * @return the parameter value, a short parsed from the configuration
   *         data
   */
  public final short getShort(final String key, final short min,
      final short max, final short def) {
    final String k;
    final Object v;
    final java.lang.Number number;

    k = Configuration.__checkKey(key);

    synchronized (this.m_data) {
      v = this.m_data.get(k);

      if (v == null) {
        return def;
      }

      number = new ShortParser(min, max).parse(v);

      if (number != v) {
        this.m_data.put(key, v);
      }
    }

    return number.shortValue();
  }

  /**
   * Get a 32 bit signed integer (int) parameter.
   * 
   * @param key
   *          the key identifying the parameter
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
   * @param def
   *          the default value
   * @return the parameter value, a int parsed from the configuration data
   */
  public final int getInt(final String key, final int min, final int max,
      final int def) {
    final String k;
    final Object v;
    final java.lang.Number number;

    k = Configuration.__checkKey(key);

    synchronized (this.m_data) {
      v = this.m_data.get(k);

      if (v == null) {
        return def;
      }

      number = new IntParser(min, max).parse(v);

      if (number != v) {
        this.m_data.put(key, v);
      }
    }

    return number.intValue();
  }

  /**
   * Get a 64 bit signed integer (long) parameter.
   * 
   * @param key
   *          the key identifying the parameter
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
   * @param def
   *          the default value
   * @return the parameter value, a long parsed from the configuration data
   */
  public final long getLong(final String key, final long min,
      final long max, final long def) {
    final String k;
    final Object v;
    final java.lang.Number number;

    k = Configuration.__checkKey(key);

    synchronized (this.m_data) {
      v = this.m_data.get(k);

      if (v == null) {
        return def;
      }

      number = new LongParser(min, max).parse(v);

      if (number != v) {
        this.m_data.put(key, v);
      }
    }

    return number.longValue();
  }

  /**
   * Get a single-precision (32-bit) floating point number (float)
   * parameter.
   * 
   * @param key
   *          the key identifying the parameter
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
   * @param def
   *          the default value
   * @return the parameter value, a float parsed from the configuration
   *         data
   */
  public final float getFloat(final String key, final float min,
      final float max, final float def) {
    final String k;
    final Object v;
    final java.lang.Number number;

    k = Configuration.__checkKey(key);

    synchronized (this.m_data) {
      v = this.m_data.get(k);

      if (v == null) {
        return def;
      }

      number = new FloatParser(min, max).parse(v);

      if (number != v) {
        this.m_data.put(key, v);
      }
    }

    return number.floatValue();
  }

  /**
   * Get a double-precision (64-bit) floating point number (double)
   * parameter.
   * 
   * @param key
   *          the key identifying the parameter
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
   * @param def
   *          the default value
   * @return the parameter value, a double parsed from the configuration
   *         data
   */
  public final double getDouble(final String key, final double min,
      final double max, final double def) {
    final String k;
    final Object v;
    final java.lang.Number number;

    k = Configuration.__checkKey(key);

    synchronized (this.m_data) {
      v = this.m_data.get(k);

      if (v == null) {
        return def;
      }

      number = new DoubleParser(min, max).parse(v);

      if (number != v) {
        this.m_data.put(key, v);
      }
    }

    return number.doubleValue();
  }

  /**
   * Get a Boolean parameter.
   * 
   * @param key
   *          the key identifying the parameter
   * @param def
   *          the default value
   * @return the parameter value, a Boolean parsed from the configuration
   *         data
   */
  public final boolean getBoolean(final String key, final boolean def) {
    return this.get(key, BooleanParser.INSTANCE, Boolean.valueOf(def))
        .booleanValue();
  }

  /**
   * Get a parameter which is a string.
   * 
   * @param key
   *          the key
   * @param def
   *          the default value
   * @return the string
   */
  public final String getString(final String key, final String def) {
    return this.get(key, StringParser.INSTANCE, def);
  }

  /**
   * Get a parameter which is a list of strings.
   * 
   * @param key
   *          the key
   * @param def
   *          the default value
   * @return the string
   */
  public final List<String> getStrings(final String key,
      final List<String> def) {
    return this.get(key, ListParser.STRING_LIST_PARSER, def);
  }

  /**
   * get a logger
   * 
   * @param key
   *          the key
   * @param def
   *          the default logger
   * @return the log value
   */
  public final Logger getLogger(final String key, final Logger def) {
    return this.get(key, _LoggerParser.INSTANCE, def);
  }

  // / functions to store configuration values

  /**
   * Put a string parameter: Overwrite whatever is already stored.
   * 
   * @param key
   *          the key
   * @param value
   *          the value
   */
  public final void putString(final String key, final String value) {
    final String k;

    k = Configuration.__checkKey(key);

    synchronized (this.m_data) {
      if (value != null) {
        this.m_data.put(k, value);
      } else {
        this.m_data.remove(k);
      }
    }
  }

  /**
   * Put a string to the map. The string is considered to be in the form
   * {@code key=value} or {@code key:value} and may be preceeded by any
   * number of {@code -} or {@code /}-es. If the value part is missing
   * {@code "true"} is used as value.
   * 
   * @param s
   *          the string
   */
  public final void putCommandLine(final String s) {
    String t;
    int i, j;
    final int len;
    char ch;
    boolean canUseSlash;

    if (s == null) {
      return;
    }

    t = s.trim();
    len = t.length();
    if (len <= 0) {
      return;
    }

    canUseSlash = (File.separatorChar != '/');

    for (i = 0; i < len; i++) {
      ch = t.charAt(i);
      if ((ch == '-') || (canUseSlash && (ch == '/')) || (ch <= 32)) {
        continue;
      }

      for (j = i + 1; j < len; j++) {
        ch = t.charAt(j);
        if ((ch == ':') || (ch == '=')) {
          this.putString(t.substring(i, j), t.substring(j + 1).trim());
          return;
        }
      }

      this.putString(t.substring(i), Boolean.TRUE.toString());

      return;
    }
  }

  /**
   * Load command line arguments into a map
   * 
   * @param args
   *          the arguments
   */
  public final void putCommandLine(final String[] args) {
    if (args != null) {
      for (final String s : args) {
        this.putCommandLine(s);
      }
    }
  }

  /**
   * Store some information from a map
   * 
   * @param map
   *          the map
   */
  public final void putMap(final Map<?, ?> map) {
    if (map != null) {
      synchronized (this.m_data) {
        for (final Map.Entry<?, ?> e : map.entrySet()) {
          this.m_data.put(
              Configuration.__checkKey(String.valueOf(e.getKey())),
              e.getValue());
        }
      }
    }
  }

  /**
   * Store some information from a properties set
   * 
   * @param prop
   *          the properties
   */
  public final void putProperties(final Properties prop) {
    this.putMap(prop);
  }

  /**
   * Store some information from a properties set
   * 
   * @param file
   *          the properties file
   * @throws IOException
   *           if loading the file fails
   */
  public final void putPropertiesFile(final File file) throws IOException {
    Properties pr;

    pr = new Properties();
    try (FileReader fr = new FileReader(FileUtils.canonicalize(file))) {
      pr.load(fr);
    }

    this.putProperties(pr);
  }

  /**
   * Put the properties identified by a configuration file
   * 
   * @throws IOException
   *           if io fails
   */
  public final void putPropertiesFromConfigFile() throws IOException {
    File f;

    f = this.getFile(Configuration.PARAM_PROPERTY_FILE, null);
    if (f != null) {
      this.putPropertiesFile(f);
    }
  }

  /**
   * Configure this configuration from a set of command line parameters
   * 
   * @param args
   *          the command line arguments
   * @throws IOException
   *           if io fails
   */
  public final void configure(final String[] args) throws IOException {
    this.putCommandLine(args);
    this.putPropertiesFromConfigFile();
  }

  // / utility functions

  /**
   * Prepare a string and consider that it may be embedded into quotation
   * characters. This procedure removes trailing and leading quotation
   * marks. If the resulting string is empty, {@code null} is returned
   * 
   * @param s
   *          the string
   * @return the prepared version of {@code s}, {@code null} if it was
   *         empty
   */
  public static final String unpack(final String s) {
    final int len, a, b;
    final String t;

    if (s == null) {
      return null;
    }

    t = s.trim();
    len = t.length();
    if (len <= 0) {
      return null;
    }

    a = t.charAt(0);
    b = t.charAt(len - 1);
    if (((a == b) && ((a == 0x0022) || (a == 0x0027) || (a == 0x00B4)
        || (a == 0x0060) || (a == 0x00AB) || (a == 0x00BB)
        || (a == 0x2018) || (a == 0x2019) || (a == 0x201B)
        || (a == 0x201C) || (a == 0x201D) || (a == 0x201E)
        || (a == 0x201F) || (a == 0x2039) || (a == 0x203A)
        || (a == 0x301D) || (a == 0x301E) || (a == 0x301F)
        || (a == 0xFF02) || (a == 0xFF07)))
        || ((a == 0x0060) && (b == 0x00B4))
        || ((a == 0x00AB) && (b == 0x00BB))
        || ((a == 0x2018) && (b == 0x2019))
        || ((a == 0x201C) && (b == 0x201D))
        || ((a == 0x201E) && (b == 0x201F))
        || ((a == 0x301D) && (b == 0x301E))
        || ((a == 0x301E) && (b == 0x301D))
        || ((a == 0x301D) && (b == 0x301F))
        || ((a == 0x301E) && (b == 0x301F))
        || ((a == 0x301F) && (b == 0x301D))
        || ((a == 0x301F) && (b == 0x301E))) {
      return Configuration.unpack(t.substring(1, len - 1));
    }

    return t;
  }
}
