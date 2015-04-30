package org.logisticPlanning.utils.io.charIO;

import java.io.IOException;

/**
 * an accessor for character arrays
 */
public final class CharArrayCharInput extends CharInput {

  /** the data */
  private final char[] m_data;

  /**
   * create
   * 
   * @param data
   *          the data
   */
  public CharArrayCharInput(final char[] data) {
    super();
    this.m_data = data;
  }

  /**
   * Get the length of the data
   * 
   * @return the data length
   */
  public final int dataLength() {
    return this.m_data.length;
  }

  /** {@inheritDoc} */
  @Override
  public final int get(final int index) {
    return this.m_data[index];
  }

  /** {@inheritDoc} */
  @Override
  public final void write(final CharOutput out, final int start,
      final int end) throws IOException {
    out.write(this.m_data, start, end);
  }

  /** {@inheritDoc} */
  @Override
  public final String toString(final int start, final int end) {
    return String.valueOf(this.m_data, start, (end - start));
  }
}
