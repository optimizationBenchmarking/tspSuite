package org.logisticPlanning.tsp.evaluation.data;

import org.logisticPlanning.utils.collections.basic.BasicMapEntry;
import org.logisticPlanning.utils.text.TextUtils;

/** a setup item */
public final class SetupItem extends BasicMapEntry<String, String>
    implements Comparable<SetupItem> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create
   * 
   * @param key
   *          the key
   * @param value
   *          the value
   */
  public SetupItem(final String key, final String value) {
    super(TextUtils.prepare(key), TextUtils.prepare(value));

    if ((this.getKey() == null) || (this.getValue() == null)) {
      throw new IllegalArgumentException(//
          ((("Setup item elements must not be empty, but are <" + key) + //$NON-NLS-1$
          ',') + value) + '>');
    }
  }

  /** {@inheritDoc} */
  @Override
  public final String setValue(final String value) {
    throw new UnsupportedOperationException();
  }

  /** {@inheritDoc} */
  @Override
  public final int compareTo(final SetupItem o) {
    int r;
    if (o == this) {
      return 0;
    }
    if (o == null) {
      return (-1);
    }

    r = this.getKey().compareTo(o.getKey());
    if (r != 0) {
      return r;
    }
    return this.getValue().compareTo(o.getValue());
  }

}
