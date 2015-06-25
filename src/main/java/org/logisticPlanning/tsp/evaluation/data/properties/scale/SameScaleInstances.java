package org.logisticPlanning.tsp.evaluation.data.properties.scale;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;

/**
 * The set of instances of the same scale.
 */
public final class SameScaleInstances extends _ScaleSetBase<Instance> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;
  /** the scale */
  private final int m_scale;

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
   * @param scale
   *          the scale
   */
  SameScaleInstances(final int lowerScaleBound, final int upperScaleBound,
      final int lowestScale, final int highestScale, final Instance[] set,
      final int scale) {
    super(lowerScaleBound, upperScaleBound, lowestScale, highestScale, set);
    this.m_scale = scale;
  }

  /**
   * The base scale:
   * <code>{@link #getLowerScaleBound()}=base<sup>scale</sup></code> and
   * <code>{@link #getUpperScaleBound()}=base<sup>scale+1</sup>-1</code>
   *
   * @return the base scale
   */
  public final int getScale() {
    return this.m_scale;
  }
}
