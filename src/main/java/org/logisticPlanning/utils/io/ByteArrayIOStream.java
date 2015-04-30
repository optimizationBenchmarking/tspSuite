package org.logisticPlanning.utils.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * A stream buffer is an output stream which can be turned into an input
 * stream.
 * 
 * @since 0.9.8
 */
public class ByteArrayIOStream extends ByteArrayOutputStream {

  /**
   * {@code true} if the output was turned to an input: afterwards, no
   * further output is allowed
   */
  private volatile boolean m_redirected;

  /** instantiate */
  public ByteArrayIOStream() {
    super();
  }

  /**
   * instantiate
   * 
   * @param size
   *          the size to be allocated
   */
  public ByteArrayIOStream(final int size) {
    super(size);
  }

  /**
   * Get an input stream which can directly read from the data previously
   * written to the output.
   * 
   * @return an input stream which can directly read the data which was
   *         written to the output before
   */
  public synchronized ByteArrayInputStream asInput() {
    this.m_redirected = true;
    return new ByteArrayInputStream(this.buf, 0, this.count);
  }

  /** {@inheritDoc} */
  @Override
  public synchronized void reset() {
    if (this.m_redirected) {
      throw new IllegalStateException(
          "You have already opened an input stream to read the written data, reset is no longer allowed"); //$NON-NLS-1$
    }
    super.reset();
  }
}
