package org.logisticPlanning.utils.io;

import java.io.Writer;

/**
 * An writer that throws away all its input.
 * 
 * @since 0.9.8
 */
public final class NullWriter extends Writer {

  /** the globally shared instance */
  public static final NullWriter INSTANCE = new NullWriter();

  /** the null appendable */
  private NullWriter() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final NullWriter append(final CharSequence csq) {
    return NullWriter.INSTANCE;
  }

  /** {@inheritDoc} */
  @Override
  public final NullWriter append(final CharSequence csq, final int start,
      final int end) {
    return NullWriter.INSTANCE;
  }

  /** {@inheritDoc} */
  @Override
  public final NullWriter append(final char c) {
    return NullWriter.INSTANCE;
  }

  /** {@inheritDoc} */
  @Override
  public final void write(final int c) {//
  }

  /** {@inheritDoc} */
  @Override
  public final void write(final char cbuf[]) {//
  }

  /** {@inheritDoc} */
  @Override
  public final void write(final char cbuf[], final int off, final int len) {//

  }

  /** {@inheritDoc} */
  @Override
  public final void write(final String str) {//
  }

  /** {@inheritDoc} */
  @Override
  public final void write(final String str, final int off, final int len) {//
  }

  /** {@inheritDoc} */
  @Override
  public final void flush() {//

  }

  /** {@inheritDoc} */
  @Override
  public final void close() {
    //
  }
}
