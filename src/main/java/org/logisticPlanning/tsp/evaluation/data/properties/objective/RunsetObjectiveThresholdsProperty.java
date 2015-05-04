package org.logisticPlanning.tsp.evaluation.data.properties.objective;

import org.logisticPlanning.tsp.evaluation.data.EPropertyType;
import org.logisticPlanning.tsp.evaluation.data.Property;
import org.logisticPlanning.tsp.evaluation.data.RunSet;
import org.logisticPlanning.utils.document.spec.Document;

/**
 * the property for getting the thresholds for objective values per
 * instance for a given run set
 */
public final class RunsetObjectiveThresholdsProperty extends
    Property<RunSet, long[]> {

  /** the globally shared instance */
  public static final RunsetObjectiveThresholdsProperty INSTANCE = new RunsetObjectiveThresholdsProperty();

  /** the instance objective thresholds */
  private RunsetObjectiveThresholdsProperty() {
    super(EPropertyType.NEVER_STORED);
  }

  /** {@inheritDoc} */
  @Override
  protected final long[] compute(final RunSet dataset, final Document doc) {
    return ExperimentSetObjectiveThresholdsProperty.INSTANCE.get(
        dataset.getOwner().getOwner(), doc).getThresholds(
        dataset.getInstance());
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return RunsetObjectiveThresholdsProperty.class.hashCode();
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equals(final Object o) {
    return (o instanceof RunsetObjectiveThresholdsProperty);
  }
}
