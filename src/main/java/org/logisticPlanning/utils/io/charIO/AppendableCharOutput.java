package org.logisticPlanning.utils.io.charIO;

import java.io.IOException;

import org.logisticPlanning.utils.text.CharArrayCharSequence;

/**
 * a character output towards an appendable
 */
public class AppendableCharOutput extends CharOutput {

  /** the appendable to write to */
  private Appendable m_out;

  /**
   * Create
   * 
   * @param out
   *          the output destination
   */
  public AppendableCharOutput(final Appendable out) {
    super();
    this.m_out = out;
  }

  /** {@inheritDoc} */
  @Override
  public final void write(final int data) throws IOException {
    this.m_out = this.m_out.append((char) data);
  }

  /** {@inheritDoc} */
  @Override
  public final void write(final char[] data, final int start, final int end)
      throws IOException {
    this.m_out = this.m_out.append(new CharArrayCharSequence(data, start,
        (end - start)));
  }

  /** {@inheritDoc} */
  @Override
  public final void write(final String data, final int start, final int end)
      throws IOException {
    this.m_out = this.m_out.append(data, start, end);
  }

  /** {@inheritDoc} */
  @Override
  public final void write(final CharSequence data, final int start,
      final int end) throws IOException {
    this.m_out = this.m_out.append(data, start, end);
  }
}
