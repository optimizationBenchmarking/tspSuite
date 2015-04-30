package org.logisticPlanning.utils.text;

/**
 * a character sequence based on a char array
 */
public final class CharArrayCharSequence implements CharSequence {

  /** the data */
  private final char[] m_data;

  /** the offset */
  private final int m_start;

  /** the length */
  private final int m_length;

  /**
   * Instantiate
   * 
   * @param data
   *          the data
   * @param start
   *          the start
   * @param length
   *          the length
   */
  public CharArrayCharSequence(final char[] data, final int start,
      final int length) {
    super();
    this.m_data = data;
    this.m_start = start;
    this.m_length = length;
  }

  /** {@inheritDoc} */
  @Override
  public final int length() {
    return this.m_length;
  }

  /** {@inheritDoc} */
  @Override
  public final char charAt(final int index) {
    return this.m_data[this.m_start + index];
  }

  /** {@inheritDoc} */
  @Override
  public final CharSequence subSequence(final int start, final int end) {
    return new CharArrayCharSequence(this.m_data, (this.m_start + start),
        (end - start));
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    return String.valueOf(this.m_data, this.m_start, this.m_length);
  }
}
