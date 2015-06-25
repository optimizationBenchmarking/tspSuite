package org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.tsp;

import java.io.IOException;

import org.logisticPlanning.utils.document.spec.AbstractTextPlain;
import org.logisticPlanning.utils.document.spec.Sequence;

/**
 * A sequence for listing a log points.
 */
public final class _ThresholdSequence extends Sequence {

  /** the output */
  private final AbstractTextPlain m_out;

  /** the data */
  private final long[] m_data;

  /** the end */
  private final int m_end;

  /** the length */
  private final int m_length;

  /**
   * Create the log point threshold sequence
   *
   * @param data
   *          the data
   * @param start
   *          the start index
   * @param end
   *          the end index
   * @param out
   *          the output
   */
  public _ThresholdSequence(final long[] data, final int start,
      final int end, final AbstractTextPlain out) {
    super();
    this.m_data = data;
    this.m_end = end;
    this.m_length = ((end - start) + 1);
    this.m_out = out;
  }

  /** {@inheritDoc} */
  @Override
  public final int length() {
    return (this.m_length);
  }

  /** {@inheritDoc} */
  @Override
  public void write(final int index) throws IOException {
    this.m_out.writeLong(this.m_data[this.m_end - index]);
  }
}
