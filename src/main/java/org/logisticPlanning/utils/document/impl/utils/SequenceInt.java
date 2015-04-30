package org.logisticPlanning.utils.document.impl.utils;

import java.io.IOException;
import java.text.NumberFormat;

import org.logisticPlanning.utils.document.spec.AbstractInlineElement;
import org.logisticPlanning.utils.document.spec.Sequence;
import org.logisticPlanning.utils.utils.EmptyUtils;

/**
 * Write a sequence of {@code int}s.
 */
public class SequenceInt extends Sequence {

  /** the number format, or {@code null} if writing dem {@code int}s raw */
  private final NumberFormat m_nf;

  /** the data to be written */
  private final int[] m_data;

  /** the destination */
  private final AbstractInlineElement m_dest;

  /**
   * create a sequence of doubles
   * 
   * @param dest
   *          the destination
   * @param nf
   *          the number format, or {@code null} if writing dem {@code int}
   *          s raw
   * @param data
   *          the data to be written
   */
  public SequenceInt(final AbstractInlineElement dest,
      final NumberFormat nf, final int... data) {
    super();
    this.m_nf = nf;
    this.m_data = ((data != null) ? data : EmptyUtils.EMPTY_INTS);
    this.m_dest = dest;
  }

  /** {@inheritDoc} */
  @Override
  public final int length() {
    return this.m_data.length;
  }

  /** {@inheritDoc} */
  @Override
  public final void write(final int index) throws IOException {
    if (this.m_nf != null) {
      this.m_dest.writeDouble(this.m_data[index], this.m_nf);
    } else {
      this.m_dest.writeDouble(this.m_data[index]);
    }
  }
}
