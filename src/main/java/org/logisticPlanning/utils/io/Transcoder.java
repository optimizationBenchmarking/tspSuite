package org.logisticPlanning.utils.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.channels.FileChannel;

/**
 * The transcoder loads data from a source and writes it to a destination.
 * During that proces, it may change the data format.
 */
public class Transcoder {

  /**
   * Copy data from a source to a destination. The streams may be closed
   * during this process.
   * 
   * @param source
   *          the source reader
   * @param dest
   *          the destination writer
   * @throws IOException
   *           if an i/o error occurs
   */
  public static final void copyBufferedCharStreams(
      final BufferedReader source, final BufferedWriter dest)
      throws IOException {
    String s;
    boolean nl;

    nl = false;
    while ((s = source.readLine()) != null) {
      if (nl) {
        dest.newLine();
      } else {
        nl = true;
      }
      dest.write(s);
    }
  }

  /**
   * Copy data from a source to a destination. The streams may be closed
   * during this process.
   * 
   * @param source
   *          the source reader
   * @param dest
   *          the destination writer
   * @throws IOException
   *           if an i/o error occurs
   */
  public static final void copyCharStreamToCharStream(final Reader source,
      final Writer dest) throws IOException {

    try (BufferedReader br = ((source instanceof BufferedReader) ? //
    ((BufferedReader) source)//
        : new BufferedReader(source))) {

      try (BufferedWriter bw = ((dest instanceof BufferedWriter) ? //
      ((BufferedWriter) dest)//
          : new BufferedWriter(dest))) {

        Transcoder.copyBufferedCharStreams(br, bw);

      }
    }
  }

  /**
   * Copy binary data from an input stream to an output stream.
   * 
   * @param source
   *          the source stream
   * @param dest
   *          the destination writer
   * @throws IOException
   *           if an i/o error occurs
   */
  public static final void copyBinaryStreamToBinaryStream(
      final InputStream source, final OutputStream dest)
      throws IOException {
    final byte[] buf;
    final int len;
    int r;

    len = Transcoder.__bufSize(source.available());
    buf = new byte[len];

    try {
      while ((r = source.read(buf, 0, len)) >= 0) {
        if (r > 0) {
          dest.write(buf, 0, r);
          dest.flush();
        }
      }
    } finally {
      dest.flush();
    }
  }

  /**
   * check two encodings
   * 
   * @param se
   *          the source encoding
   * @param de
   *          the dest encoding
   * @return {@code true} if the encodings indicate binary transmission,
   *         {@code false} for text-based
   */
  private static final boolean __checkEncodings(final EEncoding se,
      final EEncoding de) {
    final boolean binary;

    binary = (se == EEncoding.BINARY);

    if (binary ^ (de == EEncoding.BINARY)) {
      throw new IllegalArgumentException(//
          "Binary streams can only be copied to binary streams, but source encoding is '" //$NON-NLS-1$
              + se.getStandardName() + "' and destination encoding is '"//$NON-NLS-1$
              + de.getStandardName() + "'.");//$NON-NLS-1$
    }

    return binary;
  }

  /**
   * Copy data from an input stream to an output stream. The streams may be
   * closed during this process.
   * 
   * @param source
   *          the source stream
   * @param sourceEncoding
   *          the source encoding, {@code null} for
   *          {@link org.logisticPlanning.utils.io.EEncoding#BINARY}
   * @param dest
   *          the destination writer
   * @param destEncoding
   *          the destination encoding, {@code null} for the same as the
   *          source encoding.
   * @throws IOException
   *           if an i/o error occurs
   */
  public static final void copyStreamToStream(final InputStream source,
      final EEncoding sourceEncoding, final OutputStream dest,
      final EEncoding destEncoding) throws IOException {
    final EEncoding se, de;

    se = ((sourceEncoding != null) ? sourceEncoding : EEncoding.BINARY);
    de = ((destEncoding != null) ? destEncoding : se);

    if (Transcoder.__checkEncodings(se, de)) {
      Transcoder.copyBinaryStreamToBinaryStream(source, dest);
    } else {
      Transcoder.__copyText(source, se, dest, de);
    }
  }

  /**
   * Copy text data from an input stream to an output stream. The streams
   * may be closed during this process.
   * 
   * @param source
   *          the source stream
   * @param sourceEncoding
   *          the source encoding, {@code null} for
   *          {@link org.logisticPlanning.utils.io.EEncoding#BINARY}
   * @param dest
   *          the destination writer
   * @param destEncoding
   *          the destination encoding, {@code null} for the same as the
   *          source encoding.
   * @throws IOException
   *           if an i/o error occurs
   */
  private static final void __copyText(final InputStream source,
      final EEncoding sourceEncoding, final OutputStream dest,
      final EEncoding destEncoding) throws IOException {
    try (final Reader r = ((sourceEncoding == EEncoding.TEXT) ? //
    new InputStreamReader(source)//
        : new InputStreamReader(source, sourceEncoding.getJavaName()))) {
      try (final Writer w = ((destEncoding == EEncoding.TEXT) ? //
      new OutputStreamWriter(dest)//
          : new OutputStreamWriter(dest, destEncoding.getJavaName()))) {
        Transcoder.copyCharStreamToCharStream(r, w);
      }
    }
  }

  /**
   * Copy data from a file to another file.
   * 
   * @param source
   *          the source stream
   * @param sourceEncoding
   *          the source encoding, {@code null} for
   *          {@link org.logisticPlanning.utils.io.EEncoding#BINARY}
   * @param dest
   *          the destination writer
   * @param destEncoding
   *          the destination encoding, {@code null} for the same as the
   *          source encoding.
   * @throws IOException
   *           if an i/o error occurs
   */
  public static final void copyFileToFile(final File source,
      final EEncoding sourceEncoding, final File dest,
      final EEncoding destEncoding) throws IOException {
    final EEncoding se, de;

    se = ((sourceEncoding != null) ? sourceEncoding : EEncoding.BINARY);
    de = ((destEncoding != null) ? destEncoding : se);

    if (Transcoder.__checkEncodings(se, de)) {
      Transcoder.copyBinaryFileToBinaryFile(source, dest);
    } else {
      try (final InputStream i = new FileInputStream(source)) {
        try (final OutputStream o = new FileOutputStream(dest)) {
          Transcoder.__copyText(i, se, o, de);
        }
      }
    }
  }

  /**
   * Copy a file to another file
   * 
   * @param src
   *          the source file
   * @param dest
   *          the destination file
   * @throws IOException
   *           if something goes wrong
   */
  public static final void copyBinaryFileToBinaryFile(final File src,
      final File dest) throws IOException {

    try (FileInputStream fis = new FileInputStream(src)) {
      try (FileChannel in = fis.getChannel()) {
        try (FileOutputStream fos = new FileOutputStream(dest)) {
          try (FileChannel out = fos.getChannel()) {
            in.transferTo(0, in.size(), out);
          }
        }
      }
    }
  }

  /**
   * Copy data from a file to another file.
   * 
   * @param source
   *          the source stream
   * @param sourceEncoding
   *          the source encoding, {@code null} for
   *          {@link org.logisticPlanning.utils.io.EEncoding#BINARY}
   * @param dest
   *          the destination writer
   * @param destEncoding
   *          the destination encoding, {@code null} for the same as the
   *          source encoding.
   * @throws IOException
   *           if an i/o error occurs
   */
  public static final void copyStreamToFile(final InputStream source,
      final EEncoding sourceEncoding, final File dest,
      final EEncoding destEncoding) throws IOException {
    final EEncoding se, de;
    final boolean binary;

    se = ((sourceEncoding != null) ? sourceEncoding : EEncoding.BINARY);
    de = ((destEncoding != null) ? destEncoding : se);
    binary = (Transcoder.__checkEncodings(se, de));

    try (final OutputStream o = new FileOutputStream(dest)) {
      if (binary) {
        Transcoder.copyBinaryStreamToBinaryStream(source, o);
      } else {
        Transcoder.__copyText(source, se, o, de);
      }
    }
  }

  /**
   * Copy data from a resource to an output stream
   * 
   * @param owningClazz
   *          the owning class
   * @param resourceName
   *          the name of the resource
   * @param sourceEncoding
   *          the source encoding, {@code null} for
   *          {@link org.logisticPlanning.utils.io.EEncoding#BINARY}
   * @param dest
   *          the destination stream
   * @param destEncoding
   *          the destination encoding, {@code null} for the same as the
   *          source encoding.
   * @throws IOException
   *           if an i/o error occurs
   */
  public static final void copyResourceToStream(
      final Class<?> owningClazz, final String resourceName,
      final EEncoding sourceEncoding, final OutputStream dest,
      final EEncoding destEncoding) throws IOException {
    final EEncoding se, de;
    final boolean binary;

    se = ((sourceEncoding != null) ? sourceEncoding : EEncoding.BINARY);
    de = ((destEncoding != null) ? destEncoding : se);
    binary = (Transcoder.__checkEncodings(se, de));

    try (final InputStream is = owningClazz
        .getResourceAsStream(resourceName)) {
      if (binary) {
        Transcoder.copyBinaryStreamToBinaryStream(is, dest);
      } else {
        Transcoder.__copyText(is, se, dest, de);
      }
    }
  }

  /**
   * Copy data from a resource to a file
   * 
   * @param owningClazz
   *          the owning class
   * @param resourceName
   *          the name of the resource
   * @param sourceEncoding
   *          the source encoding, {@code null} for
   *          {@link org.logisticPlanning.utils.io.EEncoding#BINARY}
   * @param dest
   *          the destination file
   * @param destEncoding
   *          the destination encoding, {@code null} for the same as the
   *          source encoding.
   * @throws IOException
   *           if an i/o error occurs
   */
  public static final void copyResourceToFile(final Class<?> owningClazz,
      final String resourceName, final EEncoding sourceEncoding,
      final File dest, final EEncoding destEncoding) throws IOException {
    final EEncoding se, de;
    final boolean binary;

    se = ((sourceEncoding != null) ? sourceEncoding : EEncoding.BINARY);
    de = ((destEncoding != null) ? destEncoding : se);
    binary = (Transcoder.__checkEncodings(se, de));

    try (final InputStream is = owningClazz
        .getResourceAsStream(resourceName)) {
      try (final OutputStream os = new FileOutputStream(dest)) {
        if (binary) {
          Transcoder.copyBinaryStreamToBinaryStream(is, os);
        } else {
          Transcoder.__copyText(is, se, os, de);
        }
      }
    }
  }

  /**
   * calculate an appropriate buffer size
   * 
   * @param available
   *          the available items
   * @return the buffer size
   */
  private static final int __bufSize(final int available) {
    long l;
    int x;

    if (available > 8192) {
      l = ((Math.min((Runtime.getRuntime().freeMemory() / 64l),
          16l * available) + 0xfl) & 0xfffffff0l);
      if (l >= available) {
        return ((int) l);
      }
    }

    x = ((available + 0xf) & 0xfffffff0);
    if (x < 4096) {
      return 4096;
    }
    return x;
  }

}
