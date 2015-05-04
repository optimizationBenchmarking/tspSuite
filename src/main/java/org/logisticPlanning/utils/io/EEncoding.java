package org.logisticPlanning.utils.io;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.HashMap;

import org.logisticPlanning.utils.NamedObject;
import org.logisticPlanning.utils.text.TextUtils;

/**
 * The basic class to represent different text encodings
 */
public final class EEncoding extends NamedObject {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the unknown encoding */
  public static final EEncoding UNKNOWN = new EEncoding(//
      "unknown", null);//$NON-NLS-1$

  /** the text encoding */
  public static final EEncoding TEXT = new EEncoding(//
      "text", null);//$NON-NLS-1$

  /** the binary encoding: not text, but binary data */
  public static final EEncoding BINARY = new EEncoding(//
      "binary", null);//$NON-NLS-1$

  /** the ascii encoding */
  public static final EEncoding ASCII = new EEncoding(//
      "ASCII", null);//$NON-NLS-1$

  /** the windows codepage 1252 encoding */
  public static final EEncoding WINDOWS_1252 = new EEncoding(//
      "Cp1252", null);//$NON-NLS-1$

  /** the big 5 encoding */
  public static final EEncoding BIG_5 = new EEncoding(//
      "Big5", null);//$NON-NLS-1$

  /** the gbk encoding */
  public static final EEncoding GBK = new EEncoding(//
      "GBK", null);//$NON-NLS-1$

  /** the GB2312 encoding */
  public static final EEncoding GB2312 = new EEncoding(//
      "GB2312", null);//$NON-NLS-1$

  /** the utf-8 encoding */
  public static final EEncoding UTF_8 = new EEncoding(//
      "UTF-8", null);//$NON-NLS-1$

  /** the utf-16 big endian encoding */
  public static final EEncoding UTF_16_BE = new EEncoding(//
      "UTF-16BE", null);//$NON-NLS-1$

  /** the utf-16 little endian encoding */
  public static final EEncoding UTF_16_LE = new EEncoding(//
      "UTF-16LE", null);//$NON-NLS-1$

  /** the ISO-8859-1 encoding */
  public static final EEncoding ISO_8859_1 = new EEncoding(//
      "ISO-8859-1", null);//$NON-NLS-1$

  /** the default encoding */
  public static final EEncoding DEFAULT_TEXT_ENCODING = EEncoding.UTF_8;

  /** the internal hashmap */
  private static final HashMap<String, EEncoding> MAP = new HashMap<>();

  static {
    synchronized (EEncoding.MAP) {
      EEncoding.UNKNOWN.__putMap();
      EEncoding.TEXT.__putMap();

      EEncoding.ASCII.__putMap();
      EEncoding.__mapPut("us-ascii", EEncoding.ASCII);//$NON-NLS-1$
      EEncoding.__mapPut("us ascii", EEncoding.ASCII);//$NON-NLS-1$
      EEncoding.__mapPut("us_ascii", EEncoding.ASCII);//$NON-NLS-1$

      EEncoding.BIG_5.__putMap();
      EEncoding.__mapPut("big-5", EEncoding.BIG_5);//$NON-NLS-1$

      EEncoding.GBK.__putMap();

      EEncoding.GB2312.__putMap();
      EEncoding.__mapPut("euc_cn", EEncoding.GB2312);//$NON-NLS-1$
      EEncoding.__mapPut("euc cn", EEncoding.GB2312);//$NON-NLS-1$
      EEncoding.__mapPut("euccn", EEncoding.GB2312);//$NON-NLS-1$
      EEncoding.__mapPut("euc-cn", EEncoding.GB2312);//$NON-NLS-1$

      EEncoding.UTF_8.__putMap();
      EEncoding.__mapPut("utf8", EEncoding.UTF_8);//$NON-NLS-1$
      EEncoding.__mapPut("unicode", EEncoding.UTF_8);//$NON-NLS-1$
      EEncoding.__mapPut("iso/iec 10646-1:2000", EEncoding.UTF_8);//$NON-NLS-1$
      EEncoding.__mapPut("iso/iec 10646-1:1993", EEncoding.UTF_8);//$NON-NLS-1$
      EEncoding.__mapPut("iso/iec 10646-1", EEncoding.UTF_8);//$NON-NLS-1$
      EEncoding.__mapPut("iso 10646-1", EEncoding.UTF_8);//$NON-NLS-1$

      EEncoding.UTF_16_BE.__putMap();
      EEncoding.__mapPut("unicodebig", EEncoding.UTF_16_BE);//$NON-NLS-1$

      EEncoding.UTF_16_LE.__putMap();
      EEncoding.__mapPut("unicodelittle", EEncoding.UTF_16_LE);//$NON-NLS-1$

      EEncoding.ISO_8859_1.__putMap();
      EEncoding.__mapPut("iso 8859-1", EEncoding.ISO_8859_1); //$NON-NLS-1$
      EEncoding.__mapPut("8859-1", EEncoding.ISO_8859_1); //$NON-NLS-1$
      EEncoding.__mapPut("iso_8859-1:1987", EEncoding.ISO_8859_1); //$NON-NLS-1$
      EEncoding.__mapPut("iso_8859-1", EEncoding.ISO_8859_1); //$NON-NLS-1$
      EEncoding.__mapPut("iso-tr-100", EEncoding.ISO_8859_1); //$NON-NLS-1$
      EEncoding.__mapPut("csisolatin1", EEncoding.ISO_8859_1); //$NON-NLS-1$
      EEncoding.__mapPut("latin1", EEncoding.ISO_8859_1); //$NON-NLS-1$
      EEncoding.__mapPut("l1", EEncoding.ISO_8859_1); //$NON-NLS-1$
      EEncoding.__mapPut("ibm819", EEncoding.ISO_8859_1); //$NON-NLS-1$
      EEncoding.__mapPut("cp819", EEncoding.ISO_8859_1); //$NON-NLS-1$
      EEncoding.__mapPut("iso/iec 8859-1", EEncoding.ISO_8859_1); //$NON-NLS-1$

      EEncoding.WINDOWS_1252.__putMap();
      EEncoding.__mapPut("cp1252", EEncoding.WINDOWS_1252); //$NON-NLS-1$
      EEncoding.__mapPut("windows1252", EEncoding.WINDOWS_1252); //$NON-NLS-1$
      EEncoding.__mapPut("windows-1252", EEncoding.WINDOWS_1252); //$NON-NLS-1$
    }
  }

  /** the java name */
  private final String m_java;

  /**
   * Instantiate
   *
   * @param name
   *          the official standard name
   * @param java
   *          the name used in Java
   */
  private EEncoding(final String name, final String java) {
    super(name);

    String t;

    t = TextUtils.prepare(java);
    if (t == null) {
      t = this.name();
    }
    this.m_java = t;
  }

  /**
   * put an encoding into the map
   *
   * @param n
   *          the name
   * @param c
   *          the encoding
   */
  private static final void __mapPut(final String n, final EEncoding c) {
    EEncoding.MAP.put(n.toLowerCase(), c);
  }

  /**
   * get an entry from the map
   *
   * @param n
   *          the name
   * @return the entry
   */
  private static final EEncoding __mapGet(final String n) {
    return EEncoding.MAP.get(n.toLowerCase());
  }

  /** put into the map */
  private final void __putMap() {
    String s, k;

    s = this.getStandardName();
    EEncoding.__mapPut(s, this);

    k = this.getJavaName();
    if (k != s) {
      EEncoding.__mapPut(k, this);
    }
  }

  /**
   * Obtain the name to be used within a Java environment
   *
   * @return the name to be used within a Java environment
   */
  public final String getJavaName() {
    return this.m_java;
  }

  /**
   * Obtain the standardized name
   *
   * @return the standardized name
   */
  public final String getStandardName() {
    return this.name();
  }

  /**
   * Obtain a text encoding identified by a given string
   *
   * @param s
   *          the string
   * @return the corresponding encoding
   */
  public static final EEncoding parseString(final String s) {
    final String t;
    EEncoding e;

    t = TextUtils.prepare(s);
    if (t == null) {
      return EEncoding.UNKNOWN;
    }

    synchronized (EEncoding.MAP) {
      e = EEncoding.__mapGet(t);
      if (e != null) {
        return e;
      }

      Charset c;
      c = Charset.forName(s);
      if (c != null) {
        e = new EEncoding(c.name(), null);
        e.__putMap();
        return e;
      }
    }

    throw new IllegalArgumentException('\'' + s + //
        "' is not a valid encoding."); //$NON-NLS-1$
  }

  /**
   * Get the text encoding
   *
   * @param o
   *          the object
   * @return the encoding corresponding to that object
   */
  public static final EEncoding getEncoding(final Object o) {
    EEncoding te;

    if (o == null) {
      return EEncoding.UNKNOWN;
    }

    if (o instanceof EEncoding) {
      return ((EEncoding) o);
    }

    if (o instanceof Writer) {
      te = EEncoding.__getWriterEncoding(o);
      if ((te != null) && (te != EEncoding.UNKNOWN)) {
        return te;
      }
      return EEncoding.TEXT;
    }

    if (o instanceof Reader) {
      te = EEncoding.__getReaderEncoding(o);
      if ((te != null) && (te != EEncoding.UNKNOWN)) {
        return te;
      }
      return EEncoding.TEXT;
    }

    if (o instanceof OutputStream) {
      if (o instanceof PrintStream) {
        te = EEncoding.__getPrintStreamEncoding(o);
        if ((te != null) && (te != EEncoding.UNKNOWN)) {
          return te;
        }
        return EEncoding.TEXT;
      }
      return EEncoding.BINARY;
    }
    if (o instanceof InputStream) {
      return EEncoding.BINARY;
    }

    return EEncoding.UNKNOWN;
  }

  /**
   * Extract an encoding from a print stream
   *
   * @param wr
   *          The print stream to get the encoding of.
   * @return The historical name of this encoding, or possibly
   *         <code>null</code> if the stream has been closed
   */
  private static final EEncoding __getPrintStreamEncoding(final Object wr) {
    Field f;
    Class<?> c;
    EEncoding e;
    Object x;

    c = wr.getClass();
    if (c == null) {
      return null;
    }

    try {

      f = c.getField("charOut");//$NON-NLS-1$
      if (f != null) {
        x = f.get(wr);
        if (x != wr) {
          e = EEncoding.getEncoding(x);
          if ((e != null) && (e != EEncoding.UNKNOWN)
              && (e != EEncoding.TEXT)) {
            return e;
          }
        }
      }
    } catch (final Throwable t) {//
    }

    return null;

  }

  /**
   * Extract an encoding from a writer
   *
   * @param wr
   *          The writer to get the encoding of.
   * @return The historical name of this encoding, or possibly
   *         <code>null</code> if the stream has been closed
   */
  private static final EEncoding __getWriterEncoding(final Object wr) {
    Field f;
    Class<?> c;
    EEncoding e;
    Object x;

    if (wr instanceof OutputStreamWriter) {
      EEncoding.parseString(((OutputStreamWriter) wr).getEncoding());
    }

    c = wr.getClass();
    if (c == null) {
      return null;
    }

    try {

      f = c.getField("out");//$NON-NLS-1$
      if (f != null) {
        x = f.get(wr);
        if (x != wr) {
          e = EEncoding.getEncoding(x);
          if ((e != null) && (e != EEncoding.UNKNOWN)
              && (e != EEncoding.TEXT)) {
            return e;
          }
        }
      }
    } catch (final Throwable t) {//
    }

    try {
      f = c.getField("lock");//$NON-NLS-1$
      if (f != null) {
        x = f.get(wr);
        if (x != wr) {
          e = EEncoding.getEncoding(x);
          if ((e != null) && (e != EEncoding.UNKNOWN)
              && (e != EEncoding.TEXT)) {
            return e;
          }
        }
      }

    } catch (final Throwable t) {//
    }

    return null;
  }

  /**
   * Extract an encoding from a reader
   *
   * @param r
   *          The reader to get the encoding of.
   * @return The historical name of this encoding, or possibly
   *         <code>null</code> if the stream has been closed
   */
  private static final EEncoding __getReaderEncoding(final Object r) {
    Field f;
    Class<?> c;
    EEncoding e;
    Object x;

    if (r instanceof InputStreamReader) {
      EEncoding.parseString(((InputStreamReader) r).getEncoding());
    }

    c = r.getClass();
    if (c == null) {
      return null;
    }

    try {

      f = c.getField("out");//$NON-NLS-1$
      if (f != null) {
        x = f.get(r);
        if (x != r) {
          e = EEncoding.getEncoding(x);
          if ((e != null) && (e != EEncoding.UNKNOWN)
              && (e != EEncoding.TEXT)) {
            return e;
          }
        }
      }
    } catch (final Throwable t) {//
    }

    try {
      f = c.getField("lock");//$NON-NLS-1$
      if (f != null) {
        x = f.get(r);
        if (x != r) {
          e = EEncoding.getEncoding(x);
          if ((e != null) && (e != EEncoding.UNKNOWN)
              && (e != EEncoding.TEXT)) {
            return e;
          }
        }
      }

    } catch (final Throwable t) {//
    }
    return null;
  }

  /**
   * write replace
   *
   * @return the replacement
   */
  private final Object writeReplace() {
    Object o;
    String k, s;

    k = this.getStandardName();
    o = EEncoding.__mapGet(k);
    if (o != null) {
      return o;
    }

    s = this.getJavaName();
    if (s != k) {
      o = EEncoding.__mapGet(s);
      if (o != null) {
        return o;
      }
    }

    return this;
  }

  /**
   * read resolve
   *
   * @return the replacement
   */
  private final Object readResolve() {
    return this.writeReplace();
  }
}
