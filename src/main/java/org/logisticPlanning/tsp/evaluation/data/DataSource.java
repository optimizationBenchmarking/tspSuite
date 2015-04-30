package org.logisticPlanning.tsp.evaluation.data;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.logisticPlanning.tsp.evaluation.data.sources.FileDataSource;
import org.logisticPlanning.utils.config.LoggerOwner;

/**
 * A data source is an object that can provide data for evaluating
 * experiments
 */
public abstract class DataSource extends LoggerOwner {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the logger for data sources: {@value} */
  public static final String PARAM_DATASOURCE_LOGGER = "dataSourceLogger"; //$NON-NLS-1$

  /**
   * Instantiate the data source
   * 
   * @param name
   *          the data source
   */
  protected DataSource(final String name) {
    super(name, DataSource.PARAM_DATASOURCE_LOGGER);
  }

  /**
   * implement the data loading behavior
   * 
   * @return the experiment set
   * @throws IOException
   *           if io fails
   */
  protected abstract ExperimentSet doLoadData() throws IOException;

  /**
   * Load the data based on the configuration parameters.
   * 
   * @return the experiment set
   * @throws IOException
   *           if io fails
   */
  public final ExperimentSet loadData() throws IOException {
    final Logger logger;
    ExperimentSet es;

    logger = this.getLogger();
    if (((logger != null) && (logger.isLoggable(Level.INFO)))) {
      logger.info("Begin loading data."); //$NON-NLS-1$
    }

    es = null;
    try {
      es = this.doLoadData();
    } finally {
      if (((logger != null) && (logger.isLoggable(Level.INFO)))) {
        logger
            .info("Finished loading data " + //$NON-NLS-1$
                ((es != null) ? ("and found " + es.size() + //$NON-NLS-1$
                " experiments.") //$NON-NLS-1$
                    : "but found no experimental data (now throwing exception ^_^).")); //$NON-NLS-1$
      }
    }

    if (es == null) {
      throw new IOException("Data is empty.");//$NON-NLS-1$
    }

    return es;
  }

  /**
   * Create an instance of the default data source type
   * 
   * @return an instance of the default data source type
   */
  public static final DataSource createDefaultDataSource() {
    return new FileDataSource();
  }
}
