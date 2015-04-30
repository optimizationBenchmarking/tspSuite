package org.logisticPlanning.tsp.evaluation.data.properties.scale;

import java.util.Arrays;

/**
 * The set of instances of the same scale.
 */
public final class AllScaleInstances extends
    _ScaleSetBase<SameScaleInstances> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the base */
  private final int m_base;

  /**
   * Create a scale run set
   * 
   * @param lowerScaleBound
   *          the lower scale bound
   * @param upperScaleBound
   *          the upper scale bound
   * @param lowestScale
   *          the lowest actual scale
   * @param highestScale
   *          the highest actual scale
   * @param set
   *          the data set
   * @param base
   *          the base
   */
  AllScaleInstances(final int lowerScaleBound, final int upperScaleBound,
      final int lowestScale, final int highestScale,
      final SameScaleInstances[] set, final int base) {
    super(lowerScaleBound, upperScaleBound, lowestScale, highestScale, set);
    Arrays.sort(set);
    this.m_base = base;
  }

  /**
   * Get the base of the scale set property
   * 
   * @return the base of the scale set property
   */
  public final int getBase() {
    return this.m_base;
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    return ((("Scale " + this.m_base) + //$NON-NLS-1$
    ", ") + super.toString()); //$NON-NLS-1$
  }
}
