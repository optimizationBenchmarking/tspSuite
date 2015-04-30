package org.logisticPlanning.utils.io;

import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

import org.logisticPlanning.utils.text.CharArrayCharSequence;

/**
 * A writer writing to any appendable.
 * 
 * @since 0.9.8
 */
public final class AppendableWriter extends Writer {

  /** the appendable to write to */
  private final Appendable m_dest;

  /**
   * create
   * 
   * @param dest
   *          the destination
   */
  public AppendableWriter(final Appendable dest) {
    super(dest);
    this.m_dest = dest;
  }

  /** {@inheritDoc} */
  @Override
  public final void write(final int c) throws IOException {
    synchronized (this.lock) {
      this.m_dest.append((char) c);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void write(final char cbuf[]) throws IOException {
    synchronized (this.lock) {
      this.m_dest.append(new CharArrayCharSequence(cbuf, 0, cbuf.length));
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void write(final char cbuf[], final int off, final int len)
      throws IOException {
    synchronized (this.lock) {
      this.m_dest.append(new CharArrayCharSequence(cbuf, off, len));
    }

  }

  /** {@inheritDoc} */
  @Override
  public final void write(final String str) throws IOException {
    synchronized (this.lock) {
      this.m_dest.append(str);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void write(final String str, final int off, final int len)
      throws IOException {
    synchronized (this.lock) {
      this.m_dest.append(str, off, (off + len));
    }
  }

  /** {@inheritDoc} */
  @Override
  public final AppendableWriter append(final CharSequence csq)
      throws IOException {
    synchronized (this.lock) {
      this.m_dest.append(csq);
    }
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public final AppendableWriter append(final CharSequence csq,
      final int start, final int end) throws IOException {
    synchronized (this.lock) {
      this.m_dest.append(csq, start, end);
    }
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public final AppendableWriter append(final char c) throws IOException {
    synchronized (this.lock) {
      this.m_dest.append(c);
    }
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public final void flush() throws IOException {
    if (this.m_dest instanceof Flushable) {
      ((Flushable) (this.m_dest)).flush();
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void close() throws IOException {
    if (this.m_dest instanceof AutoCloseable) {
      try {
        ((AutoCloseable) (this.m_dest)).close();
      } catch (final Exception e) {
        throw new IOException(e);
      }
    }
  }

}
