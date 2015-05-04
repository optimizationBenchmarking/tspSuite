package org.logisticPlanning.tsp.evaluation.data.properties;

import org.logisticPlanning.tsp.benchmarking.objective.DataPoint;
import org.logisticPlanning.tsp.benchmarking.objective.LogPoint;
import org.logisticPlanning.tsp.evaluation.data.EPropertyType;
import org.logisticPlanning.tsp.evaluation.data.Property;
import org.logisticPlanning.tsp.evaluation.data.RunSet;
import org.logisticPlanning.utils.document.spec.Document;
import org.logisticPlanning.utils.graphics.chart.spec.ChartDriver;
import org.logisticPlanning.utils.math.data.collection.IDataCollection;
import org.logisticPlanning.utils.math.statistics.series.StatisticSeries;

/**
 * a statistic run is a run that represents the development of a run over
 * time.
 */
public final class StatisticRunProperty extends
    Property<RunSet, StatisticSeries> {

  /**
   * The default maximum number of points collected in statistic runs. By
   * default, we collect about 8 times as many points as will later be
   * visible in the charts. The chart-based point number reduction will be
   * distance-based (see
   * {@link org.logisticPlanning.utils.graphics.chart.spec.ChartDriver#getMaxPointsPerLine()}
   * ), the point reduction during the statistical run generation is
   * order-based (see
   * {@link org.logisticPlanning.utils.math.statistics.series.StatisticSeries}
   * ). Both methods together are intended to reduce the data volume.
   */
  public static final int DEFAULT_MAX_POINTS = (ChartDriver.DEFAULT_MAX_POINTS_PER_LINE << 3);

  /**
   * statistical runs summarizing the progress in terms of the real
   * objective value versus the exhausted function evaluations
   */
  public static final StatisticRunProperty FE_F = new StatisticRunProperty(
      EPropertyType.TEMPORARILY_STORED, LogPoint.FE_INDEX,
      LogPoint.F_INDEX, StatisticRunProperty.DEFAULT_MAX_POINTS);

  /**
   * statistical runs summarizing the progress in terms of the relativized
   * objective value versus the exhausted function evaluations
   */
  public static final StatisticRunProperty FE_REL_F = new StatisticRunProperty(
      EPropertyType.TEMPORARILY_STORED, LogPoint.FE_INDEX,
      DataPoint.RELATIVE_F_INDEX, StatisticRunProperty.DEFAULT_MAX_POINTS);

  /**
   * statistical runs summarizing the progress in terms of the relativized
   * objective value versus the exhausted distance evaluations
   */
  public static final StatisticRunProperty DE_REL_F = new StatisticRunProperty(
      EPropertyType.NEVER_STORED, LogPoint.DE_INDEX,
      DataPoint.RELATIVE_F_INDEX, StatisticRunProperty.DEFAULT_MAX_POINTS);

  /**
   * statistical runs summarizing the progress in terms of the relativized
   * objective value versus the exhausted runtime in milliseconds
   */
  public static final StatisticRunProperty TIME_REL_F = new StatisticRunProperty(
      EPropertyType.NEVER_STORED, LogPoint.TIME_INDEX,
      DataPoint.RELATIVE_F_INDEX, StatisticRunProperty.DEFAULT_MAX_POINTS);

  /**
   * statistical runs summarizing the progress in terms of the relativized
   * objective value versus the exhausted normalized runtime
   */
  public static final StatisticRunProperty NORMALIZED_TIME_REL_F = new StatisticRunProperty(
      EPropertyType.NEVER_STORED, DataPoint.NORMALIZED_TIME_INDEX,
      DataPoint.RELATIVE_F_INDEX, StatisticRunProperty.DEFAULT_MAX_POINTS);

  /** the x-dimension */
  private final int m_dimX;

  /** the y-dimension */
  private final int m_dimY;

  /** /** the approximate maximum points along the x-axis */
  private final int m_approxMaxPoints;

  /**
   * Create the property
   *
   * @param type
   *          the property type
   * @param dimX
   *          the x-dimension
   * @param dimY
   *          the y-dimension
   * @param approxMaxPoints
   *          the upper limit of the number of points generated in a
   *          statistic set
   */
  private StatisticRunProperty(final EPropertyType type, final int dimX,
      final int dimY, final int approxMaxPoints) {
    super(type);

    this.m_dimX = dimX;
    this.m_dimY = dimY;
    this.m_approxMaxPoints = approxMaxPoints;
  }

  /**
   * get the property for the given x- and y- dimension.
   *
   * @param dimX
   *          the x-dimension
   * @param dimY
   *          the y-dimension
   * @return the property
   */
  public static final StatisticRunProperty get(final int dimX,
      final int dimY) {
    return StatisticRunProperty.get(dimX, dimY,
        StatisticRunProperty.DEFAULT_MAX_POINTS);
  }

  /**
   * get the property for the given x- and y- dimension.
   *
   * @param dimX
   *          the x-dimension
   * @param dimY
   *          the y-dimension
   * @param approxMaxPoints
   *          the upper limit of the number of points generated in a
   *          statistic set
   * @return the property
   */
  public static final StatisticRunProperty get(final int dimX,
      final int dimY, final int approxMaxPoints) {

    if (approxMaxPoints == StatisticRunProperty.DEFAULT_MAX_POINTS) {
      if ((dimX == StatisticRunProperty.FE_F.m_dimX)
          && (dimY == StatisticRunProperty.FE_F.m_dimY)) {
        return StatisticRunProperty.FE_F;
      }

      if ((dimX == StatisticRunProperty.FE_REL_F.m_dimX)
          && (dimY == StatisticRunProperty.FE_REL_F.m_dimY)) {
        return StatisticRunProperty.FE_REL_F;
      }

      if ((dimX == StatisticRunProperty.DE_REL_F.m_dimX)
          && (dimY == StatisticRunProperty.DE_REL_F.m_dimY)) {
        return StatisticRunProperty.DE_REL_F;
      }

      if ((dimX == StatisticRunProperty.TIME_REL_F.m_dimX)
          && (dimY == StatisticRunProperty.TIME_REL_F.m_dimY)) {
        return StatisticRunProperty.TIME_REL_F;
      }

      if ((dimX == StatisticRunProperty.NORMALIZED_TIME_REL_F.m_dimX)
          && (dimY == StatisticRunProperty.NORMALIZED_TIME_REL_F.m_dimY)) {
        return StatisticRunProperty.NORMALIZED_TIME_REL_F;
      }

    }

    return new StatisticRunProperty(EPropertyType.NEVER_STORED, dimX,
        dimY, approxMaxPoints);
  }

  /** {@inheritDoc} */
  @Override
  protected final StatisticSeries compute(final RunSet dataset,
      final Document doc) {
    return new StatisticSeries(//
        dataset.toArray(new IDataCollection[dataset.size()]),//
        this.m_dimX, this.m_dimY, this.m_approxMaxPoints);
  }

  /**
   * Get the x dimension
   *
   * @return the x dimension
   */
  public final int getDimX() {
    return this.m_dimX;
  }

  /**
   * Get the y dimension
   *
   * @return the y dimension
   */
  public final int getDimY() {
    return this.m_dimY;
  }
}
