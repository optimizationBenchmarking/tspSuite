package org.logisticPlanning.tsp.evaluation.data.properties.ranking;

import java.util.Collection;
import java.util.Map;

import org.logisticPlanning.utils.collections.basic.BasicMapEntry;
import org.logisticPlanning.utils.collections.lists.ArraySetView;
import org.logisticPlanning.utils.math.statistics.EStatisticParameter;
import org.logisticPlanning.utils.math.statistics.IStatisticPoint;
import org.logisticPlanning.utils.math.statistics.StatisticInfoComparator;
import org.logisticPlanning.utils.math.statistics.aggregates.OrderStatisticInfo;

/**
 * A rank aggregate is used to combine several rankings of the same objects
 * to obtain a, e.g., a median ranking.. We apply
 * <em>fractional ranking</em> &nbsp;[<a href="#cite_WIKIPEDIA2013RANKING"
 * style="font-weight:bold">1</a>], as described in the documentation of
 * classes
 * {@link org.logisticPlanning.tsp.evaluation.data.properties.ranking.Ranking
 * Ranking} and
 * {@link org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.eval.DescRanking
 * DescRanking} in detail.
 * 
 * @param <T>
 *          the object type<h2>References</h2>
 *          <ol>
 *          <li><div><span id="cite_WIKIPEDIA2013RANKING" /><span
 *          style="font-style:italic;font-family:cursive;"
 *          >&ldquo;Ranking,&rdquo;</span> (Website), August&nbsp;21, 2013,
 *          San Francisco, CA, USA: Wikimedia Foundation, Inc. and&nbsp;San
 *          Francisco, CA, USA: Wikimedia Foundation, Inc.. <div>link: [<a
 *          href="http://en.wikipedia.org/wiki/Ranking">1</a>]</div></div></li>
 *          </ol>
 */
public final class RankAggregate<T> {

  /** the objects */
  private final ArraySetView<T> m_keys;

  /** the buffers */
  private final OrderStatisticInfo[] m_info;

  /**
   * create
   * 
   * @param keys
   *          the keys
   */
  public RankAggregate(final ArraySetView<T> keys) {
    super();

    int s;

    s = keys.size();
    if (s <= 0) {
      throw new IllegalArgumentException();
    }

    this.m_keys = keys;
    this.m_info = new OrderStatisticInfo[s];
    for (; (--s) >= 0;) {
      this.m_info[s] = new OrderStatisticInfo();
    }
  }

  /**
   * Aggregate a given ranking
   * 
   * @param ranks
   *          the ranks
   * @param <V>
   *          the rank type
   */
  @SafeVarargs
  public final <V extends Rank<T>> void aggregateRanks(final V... ranks) {
    for (final Rank<T> r : ranks) {
      this.m_info[this.m_keys.indexOf(r.getKey())]
          .visitDouble(r.getRank());
    }
  }

  /**
   * Aggregate a given ranking
   * 
   * @param ranks
   *          the ranks
   * @param <V>
   *          the rank type
   */
  public final <V extends Rank<T>> void aggregateRanks(
      final Collection<V> ranks) {
    for (final Rank<T> r : ranks) {
      this.m_info[this.m_keys.indexOf(r.getKey())]
          .visitDouble(r.getRank());
    }
  }

  /**
   * Get a ranking according to the given statistic parameter over the
   * registered ranks...
   * 
   * @param param
   *          the parameter
   * @return the ranks
   */
  @SuppressWarnings("unchecked")
  public final Ranking<T> rank(final EStatisticParameter param) {
    final Map.Entry<T, IStatisticPoint>[] rankMe;
    int s;

    s = this.m_info.length;
    rankMe = new Map.Entry[s];
    for (; (--s) >= 0;) {
      rankMe[s] = new BasicMapEntry<T, IStatisticPoint>(
          this.m_keys.get(s), this.m_info[s]);
    }

    return Ranking.rank(new StatisticInfoComparator(param), rankMe);
  }
}
