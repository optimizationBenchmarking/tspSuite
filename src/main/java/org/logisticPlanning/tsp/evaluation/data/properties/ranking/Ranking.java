package org.logisticPlanning.tsp.evaluation.data.properties.ranking;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;

import org.logisticPlanning.utils.collections.lists.ArrayListView;
import org.logisticPlanning.utils.collections.lists.ArraySetView;
import org.logisticPlanning.utils.utils.comparison.ComparisonUtils;

/**
 * <p>
 * A ranking is an assignment of the {@code double} numbers from the
 * inclusive range {@code 1} to {@code n} to a set of {@code n} objects.
 * The object that receives the lowest rank is best, the one with the
 * highest rank is worst. We apply a <em>fractional ranking</em>&nbsp;[<a
 * href="#cite_WIKIPEDIA2013RANKING" style="font-weight:bold">1</a>]. See
 * the documentation of class
 * {@link org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.eval.DescRanking
 * DescRanking} for more details.
 * </p>
 * <p>
 * Ranking is performed by first sorting the objects. The sorted objects
 * are then assigned ranks, from the lowest to the highest. It may be
 * possible that there are ties for a given rank. Let's say that the
 * {@link java.util.Comparator comparator} used for sorting and ranking
 * considers the elements at index {@code 4} and {@code 5} as equivalent
 * (returns {@code 0} when
 * {@link java.util.Comparator#compare(Object, Object)} comparing} them).
 * In this case, both objects will receive the same rank {@code 5.5} (since
 * the smallest rank is {@code 1} but the smallest index is {@code 0}).
 * Generally, if a sequence of {@code r} objects starting at index
 * {@code i} are equivalent, they will receive all receive the rank
 * {@code 1 + (i + (i + r - 1))/2}. This is actually the same method that
 * is used to rank identical values in the order-based non-parametric
 * statistics
 * {@link org.logisticPlanning.utils.math.statistics.tests.impl.TwoTailedMannWhitneyUTest
 * Mann-Whitney U test}.
 * </p>
 *
 * @param <T>
 *          the type of element being ranked <h2>References</h2>
 *          <ol>
 *          <li><div><span id="cite_WIKIPEDIA2013RANKING" /><span
 *          style="font-style:italic;font-family:cursive;"
 *          >&ldquo;Ranking,&rdquo;</span> (Website), August&nbsp;21, 2013,
 *          San Francisco, CA, USA: Wikimedia Foundation, Inc. and&nbsp;San
 *          Francisco, CA, USA: Wikimedia Foundation, Inc.. <div>link: [<a
 *          href="http://en.wikipedia.org/wiki/Ranking">1</a>]</div></div></li>
 *          </ol>
 */
public final class Ranking<T> extends ArraySetView<Rank<T>> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * instantiate
   *
   * @param ranks
   *          the ranks
   */
  private Ranking(final Rank<T>[] ranks) {
    super(ranks);
  }

  /**
   * Get the rank of a given object
   *
   * @param key
   *          the object
   * @return the associated rank
   */
  public final double getRank(final T key) {
    for (final Rank<T> r : this.m_data) {
      if (r.getKey() == key) {
        return r.getRank();
      }
    }
    throw new IllegalArgumentException(//
        "There is no rank for object '" + String.valueOf(key) //$NON-NLS-1$
            + '\'');
  }

  /**
   * Get the rank of the best object
   *
   * @return the rank of the best object
   */
  public final double getBestRank() {
    return this.m_data[0].getRank();
  }

  /**
   * Get the rank of the worst object
   *
   * @return the rank of the worst object
   */
  public final double getWorstRank() {
    return this.m_data[this.m_data.length].getRank();
  }

  /**
   * Get the set of the best objects, according to this ranking
   *
   * @return the set of the best objects, according to this ranking
   */
  @SuppressWarnings("unchecked")
  public final ArrayListView<T> getBest() {
    Object[] best, ret;
    final int s;
    final double b;
    Rank<T> k;
    int i;

    s = this.m_data.length;
    best = new Object[s];

    b = this.m_data[0].getRank();
    for (i = 0; i < s; i++) {
      k = this.m_data[i];
      if (k.getRank() > b) {
        break;
      }
      best[i] = k.getKey();
    }

    if (i < s) {
      ret = new Object[i];
      System.arraycopy(best, 0, ret, 0, i);
      best = null;
    } else {
      ret = best;
    }
    return ((ArrayListView<T>) (ArrayListView.makeArrayListView(ret)));
  }

  /**
   * Get the set of the worst objects, according to this ranking
   *
   * @return the set of the worst objects, according to this ranking
   */
  @SuppressWarnings("unchecked")
  public final ArrayListView<T> getWorst() {
    Object[] worst, ret;
    final int s;
    final double b;
    Rank<T> k;
    int i;

    s = this.m_data.length;
    worst = new Object[s];

    b = this.m_data[s - 1].getRank();
    for (i = s; (--i) >= 0;) {
      k = this.m_data[i];
      if (k.getRank() < b) {
        break;
      }
      worst[i] = k.getKey();
    }

    if (i >= 0) {
      i++;
      ret = new Object[s - i];
      System.arraycopy(worst, i, ret, 0, s - i);
      worst = null;
    } else {
      ret = worst;
    }
    return ((ArrayListView<T>) (ArrayListView.makeArrayListView(ret)));
  }

  /**
   * Check if this ranking is equivalent to another ranking.
   *
   * @param ranks
   *          the other ranking
   * @return {@code true} if this ranking represents exactly the same data
   *         as the other ranking, {@code false} otherwise
   */
  public final boolean isEquivalent(final Ranking<?> ranks) {
    int i;
    Rank<?> r1, r2;

    if (ranks == this) {
      return true;
    }
    if (ranks == null) {
      return false;
    }

    i = this.size();
    if (i != ranks.size()) {
      return false;
    }

    for (; (--i) >= 0;) {
      r1 = this.get(i);
      r2 = ranks.get(i);

      if (r1.getKey() != r2.getKey()) {
        return false;
      }
      if (r1.getRank() != r2.getRank()) {
        return false;
      }
    }

    return true;
  }

  /**
   * Assign the same rank to the elements from start to end
   *
   * @param ranks
   *          the ranks
   * @param start
   *          the start index
   * @param end
   *          the exclusive end index
   */
  private static final void __assignSameRank(final Rank<?>[] ranks,
      final int start, final int end) {
    final int endIncl;
    final double rank;
    int i;

    if (start >= end) {
      throw new IllegalArgumentException();
    }

    endIncl = (end - 1);
    if (endIncl > start) {
      rank = (1d + ((endIncl + start) * 0.5d));
    } else {
      rank = end;
    }

    for (i = start; i < end; i++) {
      ranks[i].setRank(rank);
    }
  }

  /**
   * check the data
   *
   * @param len
   *          the length
   */
  private static final void __checkLength(final int len) {
    if (len <= 0) {
      throw new IllegalArgumentException("Data cannot be empty."); //$NON-NLS-1$
    }
  }

  /**
   * Rank some elements in an array.
   *
   * @param comp
   *          the comparator
   * @param data
   *          the data to be ranked &ndash; <em>Warning:</em> This array
   *          will be sorted according to rank (best first, worst last)!
   * @return the ranking
   * @param <V>
   *          the rank object type
   * @param <K>
   *          the key type
   */
  @SafeVarargs
  public static final <K, V extends Rank<K>> Ranking<K> rank(
      final Comparator<? super V> comp, final V... data) {
    final int len;
    int start, i, r;
    V last, cur;

    Ranking.__checkLength(len = data.length);
    Arrays.sort(data, comp);

    start = 0;
    cur = data[0];
    for (i = 1; i < len; i++) {
      last = cur;
      cur = data[i];

      r = comp.compare(cur, last);
      if (r < 0) {
        throw new IllegalStateException();
      }
      if (r == 0) {
        continue;
      }

      Ranking.__assignSameRank(data, start, i);
      start = i;
    }

    Ranking.__assignSameRank(data, start, len);
    return new Ranking<>(data);
  }

  /**
   * Create a new (sorted) array with rankings for assignments of objects
   * to values
   *
   * @param comp
   *          the comparator
   * @param data
   *          the data &ndash; <em>Warning:</em> This array will be sorted
   *          according to rank (best first, worst last)!
   * @return the rankings, sorted by rank (best first, worst last)
   * @param <K>
   *          the key / object type
   * @param <V>
   *          the value type
   */
  @SuppressWarnings("unchecked")
  @SafeVarargs
  public static final <K, V> Ranking<K> rank(
      final Comparator<? super V> comp, final Map.Entry<K, V>... data) {
    final int len;
    final Rank<K>[] ranks;
    int start, i, r;
    Map.Entry<K, V> cur;
    V curV, lastV;

    Ranking.__checkLength(len = data.length);
    Arrays.sort(data, new _MapEntryComparator<K, V>(comp));

    ranks = new Rank[len];

    start = 0;
    cur = data[0];
    ranks[0] = new Rank<>(cur.getKey());
    curV = cur.getValue();

    for (i = 1; i < len; i++) {
      lastV = curV;
      cur = data[i];
      ranks[i] = new Rank<>(cur.getKey());
      curV = cur.getValue();

      r = comp.compare(curV, lastV);
      if (r < 0) {
        throw new IllegalStateException();
      }
      if (r == 0) {
        continue;
      }

      Ranking.__assignSameRank(ranks, start, i);
      start = i;
    }

    Ranking.__assignSameRank(ranks, start, len);
    return new Ranking<>(ranks);
  }

  /**
   * Create a new (sorted) array with rankings for assignments of objects
   * to values (which are comparable)
   *
   * @param data
   *          the data &ndash; <em>Warning:</em> This array will be sorted!
   * @return the rankings, sorted by rank (best first, worst last)
   * @param <K>
   *          the key / object type
   * @param <V>
   *          the value type
   */
  @SafeVarargs
  public static final <K, V extends Comparable<V>> Ranking<K> rank(
      final Map.Entry<K, V>... data) {
    return Ranking.rank(ComparisonUtils.COMPARABLE_COMPARATOR, data);
  }

  /**
   * Create a new (sorted) array with rankings for assignments of objects
   * to values
   *
   * @param comp
   *          the comparator
   * @param data
   *          the data
   * @return the rankings, sorted by rank (best first, worst last)
   * @param <K>
   *          the key / object type
   * @param <V>
   *          the value type
   */
  @SuppressWarnings("unchecked")
  public static final <K, V> Ranking<K> rank(
      final Comparator<? super V> comp,
      final Collection<Map.Entry<K, V>> data) {
    return Ranking.rank(comp, data.toArray(new Map.Entry[data.size()]));
  }
}
