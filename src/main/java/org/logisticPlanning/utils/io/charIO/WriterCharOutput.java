package org.logisticPlanning.utils.io.charIO;

import java.io.IOException;
import java.io.Writer;

/**
 * a character output towards a writer
 */
public class WriterCharOutput extends CharOutput {

  /** the writer to write to */
  private final Writer m_out;

  /**
   * Create
   * 
   * @param out
   *          the output destination
   */
  public WriterCharOutput(final Writer out) {
    super();
    this.m_out = out;
  }

  /**
   * Get the writer
   * 
   * @return the writer
   */
  public final Writer getWriter() {
    return this.m_out;
  }

  /** {@inheritDoc} */
  @Override
  public final void write(final int data) throws IOException {
    this.m_out.write(data);
  }

  /** {@inheritDoc} */
  @Override
  public final void write(final char[] data, final int start, final int end)
      throws IOException {
    this.m_out.write(data, start, (end - start));
  }

  /** {@inheritDoc} */
  @Override
  public final void write(final String data, final int start, final int end)
      throws IOException {
    this.m_out.write(data, start, (end - start));
  }

  /** {@inheritDoc} */
  @Override
  public final void write(final CharSequence data, final int start,
      final int end) throws IOException {
    this.m_out.append(data, start, end);
  }
}
