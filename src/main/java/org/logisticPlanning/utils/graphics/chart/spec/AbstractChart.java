package org.logisticPlanning.utils.graphics.chart.spec;

import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * A chart is a {@link java.io.Closeable} object that is generated with a
 * reference to a chart driver and a graphics context. Information can be
 * added while this object is open. Once it gets
 * {@link java.io.Closeable#close() closed}, it will be painted into the
 * clip boundaries of the graphics context.
 */
public class AbstractChart {

  /** the driver */
  private final ChartDriver m_driver;

  /** has the diagram been compiled? */
  private boolean m_compiled;

  /**
   * Create a new abstract chart
   * 
   * @param driver
   *          the line chart's driver
   */
  protected AbstractChart(final ChartDriver driver) {
    super();

    if (driver == null) {
      throw new IllegalArgumentException(//
          "Graphics driver must not be null."); //$NON-NLS-1$
    }

    this.m_driver = driver;
  }

  /**
   * Get the chart driver
   * 
   * @return the line chart's driver
   */
  public ChartDriver getDriver() {
    return this.m_driver;
  }

  /**
   * Paint this chart
   * 
   * @param graph
   *          the graphics object to paint o.
   * @param bounds
   *          the boundaries for drawing into
   */
  protected void draw(final Graphics2D graph, final Rectangle bounds) {
    //
  }

  /**
   * This method is called exactly once for a given chart - before the
   * chart is painted the first time. After it has been called, no data may
   * be changed.
   */
  protected void compile() {
    //
  }

  /** check whether data may be changed */
  final void change() {
    if (this.m_compiled) {
      throw new IllegalStateException(//
          "Data must not be changed after compiling the chart."); //$NON-NLS-1$
    }
  }

  /**
   * Paint this chart
   * 
   * @param graph
   *          the graphics object to paint o.
   * @param bounds
   *          the boundaries for drawing into
   */
  public final void paint(final Graphics2D graph, final Rectangle bounds) {
    graph.setClip(bounds);
    if (!(this.m_compiled)) {
      this.m_compiled = true;
      this.compile();
    }
    this.draw(graph, bounds);
  }

  /**
   * Paint this chart on the given graphics context
   * 
   * @param graph
   *          graphics context
   */
  public final void paint(final Graphics2D graph) {
    Rectangle rect;

    rect = graph.getClipBounds();
    if (rect == null) {
      rect = graph.getDeviceConfiguration().getBounds();
      if (rect == null) {
        throw new IllegalStateException(//
            "Unable to determine drawing boundaries."); //$NON-NLS-1$
      }
    }

    this.paint(graph, rect);
  }
}
