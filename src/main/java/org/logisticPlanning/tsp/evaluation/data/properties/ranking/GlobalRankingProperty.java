package org.logisticPlanning.tsp.evaluation.data.properties.ranking;

import org.logisticPlanning.tsp.evaluation.data.EPropertyType;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.data.Property;
import org.logisticPlanning.utils.document.spec.Document;

/**
 * The property for global rankings.
 */
public final class GlobalRankingProperty extends
Property<ExperimentSet, GlobalRanking> {

  /** all non-empty instances */
  public static final GlobalRankingProperty INSTANCE = new GlobalRankingProperty();

  /** Create */
  private GlobalRankingProperty() {
    super(EPropertyType.PERMANENTLY_STORED);
  }

  /** {@inheritDoc} */
  @Override
  protected final GlobalRanking compute(final ExperimentSet dataset,
      final Document doc) {
    return new GlobalRanking(dataset);
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return GlobalRankingProperty.class.hashCode();
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equals(final Object o) {
    return ((o == this) || (o instanceof GlobalRankingProperty));
  }
}
