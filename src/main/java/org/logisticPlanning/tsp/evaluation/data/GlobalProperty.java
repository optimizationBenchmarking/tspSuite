package org.logisticPlanning.tsp.evaluation.data;

import org.logisticPlanning.utils.document.spec.Document;

/**
 * A property which returns the result of a property of the owner of a data
 * set when invoked on a data set. *
 * 
 * @param <DT>
 *          the data set type
 * @param <PT>
 *          the property result
 */
public final class GlobalProperty<DT extends DataSet<?>, PT> extends
    Property<DT, PT> {

  /** the global property to access */
  private final Property<DataSet<?>, PT> m_global;

  /**
   * create
   * 
   * @param global
   *          the global property
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public GlobalProperty(final Property<?, PT> global) {
    super(EPropertyType.NEVER_STORED);
    this.m_global = ((Property) global);
  }

  /** {@inheritDoc} */
  @SuppressWarnings("rawtypes")
  @Override
  protected final PT compute(final DT dataset, final Document doc) {
    return this.m_global.get(((DataSet) (((_OwnedSet) dataset).m_owner)),
        doc);
  }

}
