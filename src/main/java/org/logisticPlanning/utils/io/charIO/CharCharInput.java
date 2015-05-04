package org.logisticPlanning.utils.io.charIO;

import java.io.IOException;

/**
 * an accessor for chars
 */
public final class CharCharInput extends CharInput {

  /** the data */
  private final int m_data;

  /**
   * create
   *
   * @param data
   *          the data
   */
  public CharCharInput(final int data) {
    super();
    this.m_data = data;
  }

  /** {@inheritDoc} */
  @Override
  public final int get(final int index) {
    return this.m_data;
  }

  /** {@inheritDoc} */
  @Override
  public final void write(final CharOutput out, final int start,
      final int end) throws IOException {
    out.write(this.m_data);
  }

  /** {@inheritDoc} */
  @Override
  public final String toString(final int start, final int end) {
    return String.valueOf((char) (this.m_data));
  }
}
