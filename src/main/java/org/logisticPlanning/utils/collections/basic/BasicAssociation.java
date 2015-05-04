package org.logisticPlanning.utils.collections.basic;

import java.io.Serializable;
import java.util.Map;

import org.logisticPlanning.utils.utils.HashUtils;
import org.logisticPlanning.utils.utils.comparison.ComparisonUtils;

/**
 * A basic implementation of the {@link java.util.Map.Entry} interface
 * which leaves all value-related information abstract but provides methods
 * for hash codes and equality checking.
 *
 * @param <K>
 *          the key type
 * @param <V>
 *          the value type
 */
public abstract class BasicAssociation<K, V> implements Map.Entry<K, V>,
    Serializable {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the key */
  private final K m_key;

  /**
   * create
   *
   * @param key
   *          the key
   */
  public BasicAssociation(final K key) {
    super();
    this.m_key = key;
  }

  /** {@inheritDoc} */
  @Override
  public final K getKey() {
    return this.m_key;
  }

  /** {@inheritDoc} */
  @Override
  public abstract V getValue();

  /** {@inheritDoc} */
  @Override
  public V setValue(final V value) {
    throw new UnsupportedOperationException();
  }

  /** {@inheritDoc} */
  @Override
  public int hashCode() {
    return HashUtils.combineHashes(//
        HashUtils.hashCode(this.m_key),//
        HashUtils.hashCode(this.getValue()));
  }

  /** {@inheritDoc} */
  @SuppressWarnings("rawtypes")
  @Override
  public boolean equals(final Object o) {
    final Map.Entry e;

    if (o == this) {
      return true;
    }

    if (o instanceof Map.Entry) {
      e = ((Map.Entry) o);
      return (ComparisonUtils.equals(this.m_key, e.getKey()) && //
      ComparisonUtils.equals(this.getValue(), e.getValue()));
    }

    return false;
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return String.valueOf(this.m_key) + '='
        + String.valueOf(this.getValue());
  }
}
