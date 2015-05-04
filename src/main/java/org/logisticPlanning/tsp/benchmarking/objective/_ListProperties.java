package org.logisticPlanning.tsp.benchmarking.objective;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * <p>
 * This class is an internal class. Please do not instantiate it, use it by
 * yourself, or otherwise meddle with it.
 * </p>
 * <p>
 * With this unused internal class I list the properties to see what would
 * be an interesting addition to the log files. The class is not used in
 * the life system and here exists solely for documentation purposes.
 * </p>
 */
final class _ListProperties implements Comparable<_ListProperties> {

  /** the key */
  private final String m_key;

  /** the value */
  private final String m_val;

  /**
   * create
   *
   * @param k
   *          the key
   * @param v
   *          the value
   */
  _ListProperties(final Object k, final Object v) {
    super();
    this.m_key = String.valueOf(k);
    this.m_val = String.valueOf(v);
  }

  /**
   * make a list
   *
   * @param map
   *          the map
   * @return an ordered property list
   */
  private static final ArrayList<_ListProperties> makeList(
      final Map<?, ?> map) {
    final ArrayList<_ListProperties> l;

    l = new ArrayList<>();
    for (final Map.Entry<?, ?> e : map.entrySet()) {
      l.add(new _ListProperties(e.getKey(), e.getValue()));
    }
    Collections.sort(l);
    return l;
  }

  /**
   * The main method
   *
   * @param args
   *          ignored
   */
  public static void main(final String[] args) {
    for (final _ListProperties e : _ListProperties.makeList(System
        .getProperties())) {
      System.out.println(e.m_key + " - " + e.m_val); //$NON-NLS-1$
    }

    System.out.println("===========================================");//$NON-NLS-1$

    for (final _ListProperties e : _ListProperties.makeList(System
        .getenv())) {
      System.out.println(e.m_key + " - " + e.m_val); //$NON-NLS-1$
    }
  }

  /** {@inheritDoc} */
  @Override
  public final int compareTo(final _ListProperties o) {
    int s;
    if (o == this) {
      return 0;
    }
    if (o == null) {
      return (-1);
    }

    s = String.CASE_INSENSITIVE_ORDER.compare(this.m_key, o.m_key);
    if (s != 0) {
      return s;
    }
    return String.CASE_INSENSITIVE_ORDER.compare(this.m_val, o.m_val);
  }

}
