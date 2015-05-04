package org.logisticPlanning.tsp.evaluation.data.properties.scale;

import java.io.IOException;

import org.logisticPlanning.tsp.evaluation.data.DataSet;
import org.logisticPlanning.tsp.evaluation.data.EPropertyType;
import org.logisticPlanning.tsp.evaluation.data.Property;
import org.logisticPlanning.tsp.evaluation.modules.impl.macros.Macros;
import org.logisticPlanning.utils.document.spec.Header;

/**
 * A property that returns the run sets of given scales for an experiment.
 *
 * @param <DT>
 *          the dataset type
 * @param <PT>
 *          the property result type
 */
abstract class _ScaleProperty<DT extends DataSet<?>, PT> extends
Property<DT, PT> {

  /** the base of the scale */
  final int m_base;

  /**
   * Create the property
   *
   * @param base
   *          the base
   * @param type
   *          the property type
   */
  _ScaleProperty(final int base, final EPropertyType type) {
    super(type);

    if ((base <= 1) || (base > 10000)) {
      throw new IllegalArgumentException(//
          "Illegal base for scale sets " + base); //$NON-NLS-1$
    }

    this.m_base = base;
  }

  /** {@inheritDoc} */
  @Override
  public void initialize(final Header header) throws IOException {
    super.initialize(header);
    Macros.SCALE.define(header);
  }

  /**
   * Get the base of the scale
   *
   * @return the base of the scale
   */
  public final int getBase() {
    return this.m_base;
  }

}
