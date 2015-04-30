package org.logisticPlanning.tsp.evaluation.data.properties.ranking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.utils.collections.basic.BasicMapEntry;
import org.logisticPlanning.utils.document.spec.Label;
import org.logisticPlanning.utils.math.statistics.EStatisticParameter;

/**
 * A globally maintained ranking
 */
public class GlobalRanking {

  /** the internal aggregate */
  private final RankAggregate<Experiment> m_agg;

  /** the labels */
  private final ArrayList<Label> m_labels;

  /** the labels for the outside world */
  private final List<Label> m_lab;

  /**
   * create!
   * 
   * @param experiments
   *          the experiments
   */
  GlobalRanking(final ExperimentSet experiments) {
    super();
    this.m_agg = new RankAggregate<>(experiments);
    this.m_labels = new ArrayList<>();
    this.m_lab = Collections.unmodifiableList(this.m_labels);
  }

  /**
   * put the label
   * 
   * @param label
   *          the label
   */
  private final void __putLabel(final Label label) {
    if (label == null) {
      throw new IllegalArgumentException();
    }
    this.m_labels.add(label);
  }

  /**
   * Aggregate a given ranking
   * 
   * @param label
   *          the label of the section where the data is from
   * @param ranks
   *          the ranks
   * @param <V>
   *          the rank type
   */
  @SafeVarargs
  public final <V extends Rank<Experiment>> void aggregateRanks(
      final Label label, final V... ranks) {
    this.__putLabel(label);
    this.m_agg.aggregateRanks(ranks);
  }

  /**
   * Aggregate a given ranking
   * 
   * @param label
   *          the label of the section where the data is from
   * @param ranks
   *          the ranks
   * @param <V>
   *          the rank type
   */
  public final <V extends Rank<Experiment>> void aggregateRanks(
      final Label label, final Collection<V> ranks) {
    this.__putLabel(label);
    this.m_agg.aggregateRanks(ranks);
  }

  /**
   * Get a ranking according to the given statistic parameter over the
   * registered ranks...
   * 
   * @param param
   *          the parameter
   * @return the ranks
   */
  public final Map.Entry<List<Label>, Ranking<Experiment>> rank(
      final EStatisticParameter param) {
    return new BasicMapEntry<>(this.m_lab, this.m_agg.rank(param));
  }
}
