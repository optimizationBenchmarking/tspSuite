package org.logisticPlanning.utils.document.spec;

import org.logisticPlanning.utils.text.TextUtils;

/**
 * a single label
 */
public class SingleLabel extends _OwnedLabel {

  /** the label type */
  private final ELabelType m_type;

  /** the key */
  private final String m_key;

  /** has the label been put? */
  boolean m_written;

  /** the info string */
  String m_info;

  /**
   * Create a single label
   * 
   * @param type
   *          the label type
   * @param key
   *          the key
   */
  protected SingleLabel(final ELabelType type, final String key) {
    super();

    final String n;

    if (type == null) {
      throw new IllegalArgumentException();
    }

    n = TextUtils.prepare(key);
    if (n == null) {
      throw new IllegalArgumentException();
    }

    this.m_type = type;
    this.m_key = n;
  }

  /**
   * Has the label already been written?
   * 
   * @return {@code true} if the label has already been written,
   *         {@code false} otherwise
   */
  public final boolean hasBeenWritten() {
    return this.m_written;
  }

  /**
   * Get the label type
   * 
   * @return the label type
   */
  public final ELabelType getType() {
    return this.m_type;
  }

  /**
   * Get the label key
   * 
   * @return the label key
   */
  public final String getKey() {
    return this.m_key;
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    return (String.valueOf(this.m_type) + ':' + this.m_key);
  }

  /**
   * An information string which is stored when the label is written via
   * {@link org.logisticPlanning.utils.document.spec.Document#writeSingleLabel(SingleLabel, String)}
   * . Before the label is written, this function will throw a
   * {@link java.lang.IllegalStateException}
   * 
   * @return the information string
   */
  public final String getInfo() {
    if (!(this.m_written)) {
      throw new IllegalStateException("Label has not yet been written."); //$NON-NLS-1$
    }
    return this.m_info;
  }
}
