package org.logisticPlanning.utils.document.spec;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Logger;

import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;
import org.logisticPlanning.utils.config.LoggerOwner;
import org.logisticPlanning.utils.document.impl.latex.LaTeXDriver;
import org.logisticPlanning.utils.graphics.chart.spec.ChartDriver;
import org.logisticPlanning.utils.io.EEncoding;

/**
 * The base class for all document format drivers.
 */
public abstract class DocumentDriver extends LoggerOwner {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the parameter for the document logger: {@value} */
  public static final String PARAM_DOCUMENT_LOGGER = "documentLogger"; //$NON-NLS-1$
  /** the parameter for the chart driver: {@value} */
  public static final String PARAM_CHART_DRIVER = "chartDriver"; //$NON-NLS-1$

  /** the chart driver to use */
  private ChartDriver m_charts;

  /**
   * instantiate the driver
   * 
   * @param name
   *          the driver's name
   */
  protected DocumentDriver(final String name) {
    super(name, DocumentDriver.PARAM_DOCUMENT_LOGGER);
    this.m_charts = this.createDefaultChartDriver();
  }

  /**
   * get the preferred encoding to be used for text
   * 
   * @return the preferred encoding to be used for text files
   */
  public EEncoding getPreferedTextEncoding() {
    return EEncoding.ISO_8859_1;
  }

  /**
   * Create the default instance of the chart driver
   * 
   * @return the default instance of the chart driver
   */
  protected ChartDriver createDefaultChartDriver() {
    return ChartDriver.createDefaultChartDriver();
  }

  /**
   * Create a new context in which a document can be produced and written
   * to.
   * 
   * @param dir
   *          the directory
   * @return the new context
   * @throws IOException
   *           if io fails
   */
  public abstract Context createContext(final File dir) throws IOException;

  /** {@inheritDoc} */
  @Override
  public Logger getLogger() {
    return super.getLogger();
  }

  /**
   * Obtain the chart driver
   * 
   * @return the chart driver
   */
  public ChartDriver getChartDriver() {
    return this.m_charts;
  }

  /** {@inheritDoc} */
  @Override
  public void configure(final Configuration config) {
    super.configure(config);

    this.m_charts = config.getInstance(DocumentDriver.PARAM_CHART_DRIVER,
        ChartDriver.class, null, this.m_charts);
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable.printKey(DocumentDriver.PARAM_CHART_DRIVER, ps);
    Configurable.printlnObject(this.m_charts, ps);

  }

  /** {@inheritDoc} */
  @Override
  public void printParameters(final PrintStream ps) {
    super.printParameters(ps);

    Configurable.printKey(DocumentDriver.PARAM_CHART_DRIVER, ps);
    ps.println("The chart driver."); //$NON-NLS-1$
  }

  /**
   * Create an instance of the default document driver
   * 
   * @return an instance of the default document driver
   */
  public static final DocumentDriver createDefaultDocumentDriver() {
    return LaTeXDriver.createDefaultLaTeXDriver();
  }

  /**
   * Create the graphics object for a given figure body
   * 
   * @param owner
   *          the owning figure body
   * @return the graphics object
   * @throws IOException
   *           if io fails
   */
  protected abstract Graphic graphicCreate(final FigureBody owner)
      throws IOException;
}
