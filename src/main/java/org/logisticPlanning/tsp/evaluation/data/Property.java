package org.logisticPlanning.tsp.evaluation.data;

import java.io.IOException;

import org.logisticPlanning.utils.document.spec.Document;
import org.logisticPlanning.utils.document.spec.Header;

/**
 * A abstract class that can be extended to compute properties. Properties
 * are features of data sets that may be cached.
 *
 * @param <DT>
 *          the data set type
 * @param <PT>
 *          the property type
 */
public abstract class Property<DT extends DataSet<?>, PT> {

  /** the property type */
  final EPropertyType m_type;

  /**
   * create
   *
   * @param type
   *          the property type
   */
  protected Property(final EPropertyType type) {
    super();

    if (type == null) {
      throw new IllegalArgumentException(//
          "Property type must not be null."); //$NON-NLS-1$
    }
    this.m_type = type;
  }

  /**
   * Initialize this property
   *
   * @param header
   *          the header
   * @throws IOException
   *           if i/o fails
   */
  public void initialize(final Header header) throws IOException {
    //
  }

  /**
   * Compute the property
   *
   * @param dataset
   *          the data set
   * @param doc
   *          the document object into which the report should be written
   * @return the property value
   */
  protected abstract PT compute(final DT dataset, final Document doc);

  /**
   * Get the value of this property from the given data set {@code data}
   * and the document {@code doc}
   *
   * @param data
   *          the data set
   * @param doc
   *          the document object into which the report should be written
   * @return the property value
   */
  public final PT get(final DT data, final Document doc) {
    return ((data != null) ? data._getProperty(this, doc) : null);
  }

}
