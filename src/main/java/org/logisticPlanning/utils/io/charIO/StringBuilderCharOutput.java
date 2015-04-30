package org.logisticPlanning.utils.io.charIO;

/**
 * a character output towards a StringBuilder
 */
public final class StringBuilderCharOutput extends CharOutput {

  /** the string builder to write to */
  private final StringBuilder m_out;

  /**
   * Create
   * 
   * @param out
   *          the output destination
   */
  public StringBuilderCharOutput(final StringBuilder out) {
    super();
    this.m_out = out;
  }

  /**
   * Get the string builder that is written to
   * 
   * @return the string builder that is written to
   */
  public final StringBuilder getStringBuilder() {
    return this.m_out;
  }

  /** {@inheritDoc} */
  @Override
  public final void write(final int data) {
    this.m_out.append((char) data);
  }

  /** {@inheritDoc} */
  @Override
  public final void write(final char[] data, final int start, final int end) {
    this.m_out.append(data, start, (end - start));
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
