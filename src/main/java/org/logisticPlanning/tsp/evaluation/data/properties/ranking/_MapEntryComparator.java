package org.logisticPlanning.tsp.evaluation.data.properties.ranking;

import java.util.Comparator;
import java.util.Map;

/**
 * the internal class for sorting assignments
 *
 * @param <K>
 *          the key type
 * @param <V>
 *          the value type being compared
 */
final class _MapEntryComparator<K, V> implements
Comparator<Map.Entry<K, V>> {

  /** the comparator */
  private final Comparator<? super V> m_cmp;

  /**
   * compare
   *
   * @param cmp
   *          the comparator
   */
  _MapEntryComparator(final Comparator<? super V> cmp) {
    super();
    this.m_cmp = cmp;
  }

  /** {@inheritDoc} */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public final int compare(final Map.Entry<K, V> o1,
      final Map.Entry<K, V> o2) {
    final int r;
    final K k1, k2;

    if (o1 == o2) {
      return 0;
    }
    if (o1 == null) {
      return 1;
    }
    if (o2 == null) {
      return (-1);
    }

    r = this.m_cmp.compare(o1.getValue(), o2.getValue());
    if (r != 0) {
      return r;
    }

    k1 = o1.getKey();
    k2 = o2.getKey();

    if ((k1 == k2) || (k1 == null) || (k2 == null)) {
      throw new IllegalArgumentException(String.valueOf(k1) + ' '
          + String.valueOf(k2));
    }

    if (k1 instanceof Comparable) {
      try {
        return ((Comparable) (k1)).compareTo(k2);
      } catch (final ClassCastException cse) {
        //
      }
    }
    if (k2 instanceof Comparable) {
      try {
        return ((Comparable) (k2)).compareTo(k1);
      } catch (final ClassCastException cse) {
        //
      }
    }

    return 0;
  }

}
