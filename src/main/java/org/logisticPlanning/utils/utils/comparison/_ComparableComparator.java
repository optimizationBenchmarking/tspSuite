package org.logisticPlanning.utils.utils.comparison;

/**
 * A comparator encapsulating comparable comparison routines
 */
@SuppressWarnings("rawtypes")
final class _ComparableComparator extends PreciseComparator<Comparable> {

  /** the globally shared instance of the comparable comparator */
  static final PreciseComparator<Comparable> INSTANCE = new _ComparableComparator();

  /** create */
  private _ComparableComparator() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected final EComparison doPreciseCompare(final Comparable a,
      final Comparable b) {
    final int r;

    r = a.compareTo(b);
    return ((r < 0) ? EComparison.LESS : ((r > 0) ? EComparison.GREATER
        : EComparison.EQUAL));
  }
}
