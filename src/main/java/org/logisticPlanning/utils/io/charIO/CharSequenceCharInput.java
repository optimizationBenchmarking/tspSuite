package org.logisticPlanning.utils.io.charIO;

import java.io.IOException;

/**
 * an accessor for CharSequences
 */
public final class CharSequenceCharInput extends CharInput {

  /** the data */
  private final CharSequence m_data;

  /**
   * create
   *
   * @param data
   *          the data
   */
  public CharSequenceCharInput(final CharSequence data) {
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
    if ((start <= 0) && (end >= this.m_data.length())) {
      return this.m_data.toString();
    }
    return this.m_data.subSequence(start, end).toString();
  }
}
