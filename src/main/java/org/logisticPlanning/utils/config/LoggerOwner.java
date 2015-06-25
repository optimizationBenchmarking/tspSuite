package org.logisticPlanning.utils.config;

import java.io.PrintStream;
import java.util.logging.Logger;

import org.logisticPlanning.utils.text.TextUtils;

/**
 * The base class for all objects that have their own logger.
 */
public abstract class LoggerOwner extends Configurable {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the parameter identifying the global default logger: {@value} */
  public static final String PARAM_LOGGER = "logger"; //$NON-NLS-1$

  /** the logger parameter */
  private final String m_loggerParam;

  /** the logger */
  private Logger m_logger;

  /**
   * instantiate the driver
   *
   * @param name
   *          the driver's name
   * @param loggerParam
   *          the parameter identifying the logger
   */
  protected LoggerOwner(final String name, final String loggerParam) {
    super(name);

    String s;

    this.m_logger = null;

    s = TextUtils.prepare(loggerParam);
    this.m_loggerParam = (((s == null) || (s
        .equalsIgnoreCase(LoggerOwner.PARAM_LOGGER))) ? LoggerOwner.PARAM_LOGGER
        : s);
  }

  /**
   * Set the logger to use
   *
   * @param logger
   *          the logger to use
   */
  protected void setLogger(final Logger logger) {
    this.m_logger = logger;
  }

  /**
   * Get the logger to use
   *
   * @return the logger to use
   */
  protected Logger getLogger() {
    return this.m_logger;
  }

  /** {@inheritDoc} */
  @Override
  public void configure(final Configuration config) {
    final Logger def;

    super.configure(config);

    def = config.getLogger(LoggerOwner.PARAM_LOGGER, this.m_logger);
    if (this.m_loggerParam != LoggerOwner.PARAM_LOGGER) {
      this.m_logger = config.getLogger(this.m_loggerParam, def);
    } else {
      this.m_logger = def;
    }
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);
    Configurable.printKey(this.m_loggerParam, ps);
    if (this.m_logger != null) {
      ps.println(this.m_logger.getName());
    } else {
      ps.println((String) null);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void printParameters(final PrintStream ps) {
    super.printParameters(ps);
    Configurable.printKey(this.m_loggerParam, ps);
    ps.println("The logger to be used by " + this.name() + '.'); //$NON-NLS-1$
  }
}
