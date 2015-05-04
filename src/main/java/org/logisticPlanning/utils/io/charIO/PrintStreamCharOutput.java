package org.logisticPlanning.utils.io.charIO;

import java.io.PrintStream;

import org.logisticPlanning.utils.text.CharArrayCharSequence;

/**
 * a character output towards a print stream
 */
public class PrintStreamCharOutput extends CharOutput {

  /** the PrintStream to write to */
  private final PrintStream m_out;

  /**
   * Create
   *
   * @param out
   *          the output destination
   */
  public PrintStreamCharOutput(final PrintStream out) {
    super();
    this.m_out = out;
  }

  /** {@inheritDoc} */
  @Override
  public final void write(final int data) {
    this.m_out.print((char) data);
  }

  /** {@inheritDoc} */
  @Override
  public final void write(final char[] data, final int start, final int end) {
    this.m_out
    .append(new CharArrayCharSequence(data, start, (end - start)));
  }

  /** {@inheritDoc} */
  @Override
  public final void write(final String data, final int start, final int end) {
    this.m_out.append(data, start, end);
  }

  /** {@inheritDoc} */
  @Override
  public final void write(final CharSequence data, final int start,
      final int end) {
    this.m_out.append(data, start, end);
  }
}
