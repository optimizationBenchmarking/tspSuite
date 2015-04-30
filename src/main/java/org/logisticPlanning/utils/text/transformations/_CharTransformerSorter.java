package org.logisticPlanning.utils.text.transformations;

import java.util.Comparator;

/**
 * an internal sorter class
 */
final class _CharTransformerSorter implements Comparator<char[]> {

  /** the sorter */
  static final _CharTransformerSorter SORTER = new _CharTransformerSorter();

  /** create */
  private _CharTransformerSorter() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final int compare(final char[] o1, final char[] o2) {
    return Integer.compare(o1[0], o2[0]);
  }
}
