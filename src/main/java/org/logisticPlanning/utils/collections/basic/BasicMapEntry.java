package org.logisticPlanning.utils.collections.basic;

/**
 * A basic implementation of the {@link java.util.Map.Entry} interface.
 *
 * @param <K>
 *          the key type
 * @param <V>
 *          the value type
 */
public class BasicMapEntry<K, V> extends BasicAssociation<K, V> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the value */
  private V m_value;

  /**
   * create
   *
   * @param key
   *          the key
   * @param value
   *          the value
   */
  public BasicMapEntry(final K key, final V value) {
    super(key);
    this.m_value = value;
  }

  /** {@inheritDoc} */
  @Override
  public final V getValue() {
    return this.m_value;
  }

  /** {@inheritDoc} */
  @Override
  public V setValue(final V value) {
    final V old;

    old = this.m_value;
    this.m_value = value;
    return old;
  }
}
