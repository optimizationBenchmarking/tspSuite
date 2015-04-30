package org.logisticPlanning.utils.graphics.chart.spec;

import java.io.PrintStream;

import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;
import org.logisticPlanning.utils.graphics.chart.impl.jfree.JFreeChartDriver;
import org.logisticPlanning.utils.graphics.chart.spec.range.AxisRange2DDef;
import org.logisticPlanning.utils.math.MathConstants;

/**
 * A chart driver &ndash; the access point to the Chart API. <h2>Chart API
 * Package Structure</h2>
 * <p>
 * We develop a set of classes for painting charts in package
 * {@link org.logisticPlanning.utils.graphics.chart}. Like the
 * {@link org.logisticPlanning.utils.document document API}, the
 * {@link org.logisticPlanning.utils.graphics.chart chart API} is divided
 * into a {@link org.logisticPlanning.utils.graphics.chart.spec
 * specification} and an
 * {@link org.logisticPlanning.utils.graphics.chart.impl implementation}
 * package.
 * </p>
 * <p>
 * The specification contains the abstract base classes that can be used
 * for drawing charts. The main entry point for accessing charts is the
 * abstract class
 * {@link org.logisticPlanning.utils.graphics.chart.spec.ChartDriver
 * ChartDriver}. An chart API implementation will provide an implementation
 * of this class. Under its hood, this implementation then can provide the
 * specialized drawing and data processing utilities. Currently, the
 * following implementations of the chart API exist:
 * </p>
 * <ol>
 * <li>The implementation package
 * {@link org.logisticPlanning.utils.graphics.chart.impl.jfree jfree}
 * grounds the chart API to a <a
 * href="http://www.jfree.org/jfreechart/">JFreeChart</a>-fa&#xe7;ade.</li>
 * </ol>
 * <p>
 * The chart API makes heavy use of the
 * {@link org.logisticPlanning.utils.math.data.collection numeric
 * collections} and {@link org.logisticPlanning.utils.math.data.iteration
 * iteration} classes.
 * </p>
 * 
 * 
 @author <em><a href="http://www.it-weise.de/">Thomas Weise</a></em>,
 *         Email:&nbsp;<a
 *         href="mailto:tweise@ustc.edu.cn">tweise@ustc.edu.cn</a>
 */
public abstract class ChartDriver extends Configurable {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the default maximum number of points per line */
  public static final int DEFAULT_MAX_POINTS_PER_LINE = //
  ((int) (0.5d + Math.ceil(Math.hypot(1000d,
      Math.ceil(1000d * MathConstants.GOLDEN_RATIO)))));

  /** the parameter for the maximum number of points per line: {@value} */
  public static final String PARAM_MAX_POINTS_PER_LINE = "maxPointsPerLine"; //$NON-NLS-1$

  /** an approximate limit for the maximum points per line */
  private int m_maxPointsPerLine;

  /** the palette */
  private final ChartPalette m_palette;

  /**
   * create the chart driver
   * 
   * @param name
   *          the chart driver
   */
  protected ChartDriver(final String name) {
    super(name);
    this.m_maxPointsPerLine = ChartDriver.DEFAULT_MAX_POINTS_PER_LINE;
    this.m_palette = this.createPalette();
  }

  /**
   * Create the chart palette
   * 
   * @return the chart palette
   */
  protected ChartPalette createPalette() {
    return new ChartPalette();
  }

  /**
   * Get the chart palette
   * 
   * @return the chart palette
   */
  public final ChartPalette getPalette() {
    return this.m_palette;
  }

  /**
   * Get the approximate upper limit for the number of points per line
   * 
   * @return the approximate upper limit for the number of points per lint
   */
  public final int getMaxPointsPerLine() {
    return this.m_maxPointsPerLine;
  }

  /**
   * Set the approximate upper limit for the number of points per line
   * 
   * @param val
   *          the approximate upper limit for the number of points per line
   */
  public final void setMaxPointsPerLine(final int val) {
    this.m_maxPointsPerLine = ((val <= 0) ? ChartDriver.DEFAULT_MAX_POINTS_PER_LINE
        : val);
  }

  /** {@inheritDoc} */
  @Override
  public void configure(final Configuration config) {
    super.configure(config);
    this.m_maxPointsPerLine = config.getInt(
        ChartDriver.PARAM_MAX_POINTS_PER_LINE, 1, 1000000,
        this.m_maxPointsPerLine);
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);
    Configurable.printKey(ChartDriver.PARAM_MAX_POINTS_PER_LINE, ps);
    ps.println(this.m_maxPointsPerLine);
  }

  /** {@inheritDoc} */
  @Override
  public void printParameters(final PrintStream ps) {
    super.printParameters(ps);
    Configurable.printKey(ChartDriver.PARAM_MAX_POINTS_PER_LINE, ps);
    ps.println("approximate upper limit for the number of points per line"); //$NON-NLS-1$
  }

  /**
   * Create a 2D line chart
   * 
   * @param range
   *          the object used for computing the axis range
   * @return the line chart
   */
  public abstract LineChart2D createLineChart2D(final AxisRange2DDef range);

  /**
   * Create an instance of the default chart driver
   * 
   * @return an instance of the default chart driver
   */
  public static final ChartDriver createDefaultChartDriver() {
    return new JFreeChartDriver();
  }
}
