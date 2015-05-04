package org.logisticPlanning.tsp.evaluation.data.properties;

import org.logisticPlanning.tsp.evaluation.data.DataSet;
import org.logisticPlanning.tsp.evaluation.data.EPropertyType;
import org.logisticPlanning.tsp.evaluation.data.Property;
import org.logisticPlanning.utils.document.spec.Document;
import org.logisticPlanning.utils.document.spec.ELabelType;
import org.logisticPlanning.utils.document.spec.Label;

/**
 * a property for labels
 */
public final class LabelProperty extends Property<DataSet<?>, Label> {

  /** the label type */
  private final ELabelType m_type;

  /**
   * create
   *
   * @param type
   *          the reference type
   */
  public LabelProperty(final ELabelType type) {
    super(EPropertyType.PERMANENTLY_STORED);
    this.m_type = type;
  }

  /** {@inheritDoc} */
  @Override
  protected final Label compute(final DataSet<?> dataset,
      final Document doc) {
    if (doc == null) {
      return null;
    }
    return doc.createLabel(this.m_type);
  }

}
