package org.logisticPlanning.utils;

import java.io.BufferedReader;
import java.io.IOException;

import org.logisticPlanning.utils.text.TextUtils;

/**
 * This class allows us to read numbers and Strings sequentially from a
 * text stream. It will not close the text stream.
 */
public final class NumberReader {

  /** the end-of-file-error */
  private static final String EOF_ERROR = ("End of file reached too early."); //$NON-NLS-1$

  /** the internal reader */
  private final BufferedReader m_br;

  /** the current string */
  private String m_cur;

  /** the length of the current string */
  private int m_len;

  /** the current index */
  private int m_idx;

  /**
   * instantiate
   *
   * @param br
   *          the reader
   */
  public NumberReader(final BufferedReader br) {
    super();
    this.m_br = br;
  }

  /**
   * Read the next string from the source.
   *
   * @return the next string, or {@code null} if an end criterion is met
   * @throws IOException
   *           if something fails
   */
  public final String nextString() throws IOException {
    String s, t;
    int l, start, end;

    s = this.m_cur;
    for (;;) {

      if (s == null) {
        this.m_cur = null;

        s = this.m_br.readLine();
        if (s == null) {
          return null;
        }

        s = s.trim();
        l = s.length();
        if (l <= 0) {
          continue;
        }

        if (Character.isAlphabetic(s.charAt(0))) {
          return null;
        }

        // if (_Constants.EOF_STR.equalsIgnoreCase(s)) {
        // return false;
        // }

        this.m_cur = s;
        this.m_len = l;
        this.m_idx = start = 0;
      } else {
        start = this.m_idx;
        l = this.m_len;
      }

      while ((start < l) && (s.charAt(start) <= ' ')) {
        start++;
      }
      if (start >= l) {
        this.m_cur = s = null;
        continue;
      }

      this.m_idx = end = start;
      while ((end < l) && (s.charAt(end) > ' ')) {
        end++;
      }

      this.m_idx = end;
      t = s.substring(start, end);
      if (t == null) {
        continue;
      }

      t = t.trim();
      if (t.length() <= 0) {
        continue;
      }
      return t;
    }
  }

  /**
   * Read the next 8 bit signed integer (byte) from the source.
   *
   * @return the next byte read from the source
   * @throws IOException
   *           an {@code IOException} if loading the next byte fails (maybe
   *           due to formatting errors) or if there is no such byte, i.e.,
   *           if the end of file was reached too early
   */
  public final byte nextByte() throws IOException {
    final String n;

    n = this.nextString();
    if (n == null) {
      throw new IOException(NumberReader.EOF_ERROR);
    }
    try {
      return TextUtils.parseByte(n);
    } catch (final Throwable t) {
      throw new IOException(t);
    }
  }

  /**
   * Read the next 16 bit signed integer (short) from the source.
   *
   * @return the next short read from the source
   * @throws IOException
   *           an {@code IOException} if loading the next short fails
   *           (maybe due to formatting errors) or if there is no such
   *           short, i.e., if the end of file was reached too early
   */
  public final short nextShort() throws IOException {
    final String n;

    n = this.nextString();
    if (n == null) {
      throw new IOException(NumberReader.EOF_ERROR);
    }
    try {
      return TextUtils.parseShort(n);
    } catch (final Throwable t) {
      throw new IOException(t);
    }
  }

  /**
   * Read the next 32 bit signed integer (int) from the source.
   *
   * @return the next int read from the source
   * @throws IOException
   *           an {@code IOException} if loading the next int fails (maybe
   *           due to formatting errors) or if there is no such int, i.e.,
   *           if the end of file was reached too early
   */
  public final int nextInt() throws IOException {
    final String n;

    n = this.nextString();
    if (n == null) {
      throw new IOException(NumberReader.EOF_ERROR);
    }
    try {
      return TextUtils.parseInt(n);
    } catch (final Throwable t) {
      throw new IOException(t);
    }
  }

  /**
   * Read the next 64 bit signed integer (long) from the source.
   *
   * @return the next long read from the source
   * @throws IOException
   *           an {@code IOException} if loading the next long fails (maybe
   *           due to formatting errors) or if there is no such long, i.e.,
   *           if the end of file was reached too early
   */
  public final long nextLong() throws IOException {
    final String n;

    n = this.nextString();
    if (n == null) {
      throw new IOException(NumberReader.EOF_ERROR);
    }
    try {
      return TextUtils.parseLong(n);
    } catch (final Throwable t) {
      throw new IOException(t);
    }
  }

  /**
   * Read the next single-precision (32-bit) floating point number (float)
   * from the source.
   *
   * @return the next float read from the source
   * @throws IOException
   *           an {@code IOException} if loading the next float fails
   *           (maybe due to formatting errors) or if there is no such
   *           float, i.e., if the end of file was reached too early
   */
  public final float nextFloat() throws IOException {
    final String n;

    n = this.nextString();
    if (n == null) {
      throw new IOException(NumberReader.EOF_ERROR);
    }
    try {
      return TextUtils.parseFloat(n);
    } catch (final Throwable t) {
      throw new IOException(t);
    }
  }

  /**
   * Read the next double-precision (64-bit) floating point number (double)
   * from the source.
   *
   * @return the next double read from the source
   * @throws IOException
   *           an {@code IOException} if loading the next double fails
   *           (maybe due to formatting errors) or if there is no such
   *           double, i.e., if the end of file was reached too early
   */
  public final double nextDouble() throws IOException {
    final String n;

    n = this.nextString();
    if (n == null) {
      throw new IOException(NumberReader.EOF_ERROR);
    }
    try {
      return TextUtils.parseDouble(n);
    } catch (final Throwable t) {
      throw new IOException(t);
    }
  }

  /**
   * Read the next Boolean (true/false) from the source.
   *
   * @return the next Boolean read from the source
   * @throws IOException
   *           an {@code IOException} if loading the next Boolean fails
   *           (maybe due to formatting errors) or if there is no such
   *           Boolean, i.e., if the end of file was reached too early
   */
  public final boolean nextBoolean() throws IOException {
    final String n;

    n = this.nextString();
    if (n == null) {
      throw new IOException(NumberReader.EOF_ERROR);
    }
    try {
      return TextUtils.parseBoolean(n);
    } catch (final Throwable t) {
      throw new IOException(t);
    }
  }
}
