package org.logisticPlanning.tsp.evaluation.data.properties.litComp;

import java.util.Arrays;

import org.logisticPlanning.utils.collections.lists.ArraySetView;

/**
 * A comparison set: a set of literature comparison results.
 * 
 * @param <T>
 *          the element type
 */
public abstract class LiteratureComparisonSet<T> extends ArraySetView<T> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the comparison result */
  final ESingleComparison m_cmp;

  /** the data */
  private final int[] m_results;

  /**
   * create!
   * 
   * @param points
   *          the points
   */
  LiteratureComparisonSet(final T[] points) {
    super(points);

    Arrays.sort(points);

    this.m_results = new int[ESingleComparison.ALL.length];
    this.getComparisonData(points, this.m_results);
    this.m_cmp = LiteratureComparisonSet.__compare(this.m_results,
        points.length);
  }

  /**
   * get the comparison data
   * 
   * @param array
   *          the array
   * @param count
   *          the array to receive the number of times each comparison
   *          result has appeared
   */
  abstract void getComparisonData(final T[] array, final int[] count);

  /**
   * the comparison result
   * 
   * @return the comparison result
   */
  public final ESingleComparison getComparisonResult() {
    return this.m_cmp;
  }

  /**
   * Obtain the comparison result that fits to the aggregated absolute
   * frequencies of the results. The result is obtained in a very
   * conservative way, worse results usually beat better results. This
   * function may return a comparison value that is actually not present.
   * 
   * @param data
   *          the count
   * @param sum
   *          the total sum
   * @return the result
   */
  private static final ESingleComparison __compare(final int[] data,
      final int sum) {
    final int limit;
    int[] count;
    int max, a;

    limit = ((sum >>> 1) + 1);

    // ok, does one observation have the majority?
    count = data;
    max = LiteratureComparisonSet.__max(count);
    if (count[max] >= limit) {
      return ESingleComparison.ALL[max];
    }

    // no result has the majority, but maybe one of "maybe" results
    // combined
    // with the hard ones?
    count = count.clone();
    LiteratureComparisonSet.__merge(count);
    max = LiteratureComparisonSet.__max(count);
    if (count[max] >= limit) {
      return ESingleComparison.ALL[max];
    }

    // ok, set slower==worse, "worse" beats "slower"
    a = ESingleComparison.SLOWER.ordinal();
    count[ESingleComparison.WORSE.ordinal()] += count[a];
    count[a] = 0;

    a = ESingleComparison.MAYBE_SLOWER.ordinal();
    count[ESingleComparison.MAYBE_WORSE.ordinal()] += count[a];
    count[a] = 0;
    LiteratureComparisonSet.__merge(count);
    if (count[max] >= limit) {
      return ESingleComparison.ALL[max];
    }

    // ok, set similar==better, "similar" beats "betters"
    a = ESingleComparison.BETTER.ordinal();
    count[ESingleComparison.SIMILAR.ordinal()] += count[a];
    count[a] = 0;

    a = ESingleComparison.MAYBE_BETTER.ordinal();
    count[ESingleComparison.MAYBE_SIMILAR.ordinal()] += count[a];
    count[a] = 0;
    LiteratureComparisonSet.__merge(count);
    if (count[max] >= limit) {
      return ESingleComparison.ALL[max];
    }

    // ok, set not_comparable==worse, "worse" beats "set not_comparable"
    a = ESingleComparison.NOT_COMPARABLE.ordinal();
    count[ESingleComparison.WORSE.ordinal()] += count[a];
    count[a] = 0;

    a = ESingleComparison.MAYBE_NOT_COMPARABLE.ordinal();
    count[ESingleComparison.MAYBE_WORSE.ordinal()] += count[a];
    count[a] = 0;
    LiteratureComparisonSet.__merge(count);
    if (count[max] >= limit) {
      return ESingleComparison.ALL[max];
    }

    // ok, we cannot decide, that's really odd ... let's go with similar
    if (count[ESingleComparison.SIMILAR.ordinal()] > count[ESingleComparison.MAYBE_SIMILAR
        .ordinal()]) {
      return ESingleComparison.SIMILAR;
    }
    return ESingleComparison.MAYBE_SIMILAR;
  }

  /**
   * get the max
   * 
   * @param count
   *          the counts
   * @return the max
   */
  private static final int __max(final int[] count) {
    int i, max;

    max = 0;
    for (i = count.length; (--i) >= 0;) {
      if (count[i] > count[max]) {
        max = i;
      }
    }
    return max;
  }

  /**
   * merge maybes
   * 
   * @param count
   *          the count
   */
  private static final void __merge(final int[] count) {
    int a, b;
    for (final ESingleComparison c : ESingleComparison.MAYBE) {
      a = c.ordinal();
      b = c.getOther().ordinal();

      if (count[b] > count[a]) {
        count[b] += count[a];
        count[a] = 0;
      } else {
        count[a] += count[b];
        count[b] = 0;
      }
    }
  }

  /**
   * Count the single comparison's occurrences
   * 
   * @param cmp
   *          the single comparison's
   * @return the number of occurrences of the single comparison
   */
  public final int countResult(final ESingleComparison cmp) {
    return this.m_results[cmp.ordinal()];
  }
}
