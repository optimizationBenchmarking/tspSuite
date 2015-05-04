package org.logisticPlanning.tsp.evaluation.data.properties.objective;

import org.logisticPlanning.tsp.evaluation.data.EPropertyType;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.data.Property;
import org.logisticPlanning.utils.document.spec.Document;

/**
 * the property for getting the thresholds for objective values per
 * instance
 */
public final class ExperimentSetObjectiveThresholdsProperty extends
Property<ExperimentSet, InstanceObjectiveThresholds> {

  /** the globally shared instance for experiment sets */
  public static final ExperimentSetObjectiveThresholdsProperty INSTANCE = new ExperimentSetObjectiveThresholdsProperty();

  /** the instance objective thresholds */
  private ExperimentSetObjectiveThresholdsProperty() {
    super(EPropertyType.TEMPORARILY_STORED);
  }

  /** {@inheritDoc} */
  @Override
  protected final InstanceObjectiveThresholds compute(
      final ExperimentSet dataset, final Document doc) {
    return new InstanceObjectiveThresholds();
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return ExperimentSetObjectiveThresholdsProperty.class.hashCode();
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equals(final Object o) {
    return (o instanceof ExperimentSetObjectiveThresholdsProperty);
  }
}
