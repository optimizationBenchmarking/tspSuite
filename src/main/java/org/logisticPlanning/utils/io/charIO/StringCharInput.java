package org.logisticPlanning.utils.io.charIO;

import java.io.IOException;

/**
 * an accessor for strings
 */
public final class StringCharInput extends CharInput {

  /** the data */
  private final String m_data;

  /**
   * create
   * 
   * @param data
   *          the data
   */
  public StringCharInput(final String data) {
    super();
    this.m_data = data;
  }

  /** {@inheritDoc} */
  @Override
  public final int get(final int index) {
    return this.m_data.charAt(index);
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
    return this.m_data.substring(start, end);
  }
}
