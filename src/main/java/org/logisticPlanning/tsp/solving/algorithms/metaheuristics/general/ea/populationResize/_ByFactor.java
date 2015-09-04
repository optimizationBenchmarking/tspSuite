package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.populationResize;

import java.io.PrintStream;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.PopulationResizeStrategy;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;

/** a base class for strategies based on fixed factors */
class _ByFactor extends PopulationResizeStrategy {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the growth factor */
  public static final String PARAM_FACTOR = "factor"; //$NON-NLS-1$

  /**
   * the default factor to multiply the population size with when shrinking
   */
  public static final double DEFAULT_FACTOR = 0.75d;

  /** the factor by which the population size should grow */
  double m_factor;

  /**
   * the constructor
   *
   * @param name
   *          the name
   */
  _ByFactor(final String name) {
    super(name);
    this.m_factor = _ByFactor.DEFAULT_FACTOR;
  }

  /** {@inheritDoc} */
  @Override
  public final void configure(final Configuration config) {
    super.configure(config);
    this.m_factor = config.getDouble(_ByFactor.PARAM_FACTOR, 0d, 1d,
        this.m_factor);
  }

  /** {@inheritDoc} */
  @Override
  public final void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);
    Configurable.printKey(_ByFactor.PARAM_FACTOR, ps);
    ps.println(this.m_factor);
  }
}