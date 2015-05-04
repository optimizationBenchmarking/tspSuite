package org.logisticPlanning.tsp.evaluation.modules.impl.single.progress;

import java.io.PrintStream;

import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;

/**
 * The internal base class for statistic diagrams.
 */
class _ExperimentProgressBaseB extends _ExperimentProgressBaseA {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the approximate maximum points along the x-axis */
  int m_approxMaxPoints;

  /** the approximate maximum points along the x-axis parameter */
  private final String m_approxMaxPointsParam;

  /**
   * create!
   *
   * @param owner
   *          the macro's owner
   * @param name
   *          the name
   * @param isActive
   *          should this module be active?
   */
  _ExperimentProgressBaseB(final String name, final Module owner,
      final boolean isActive) {
    super(name, owner, isActive);

    this.m_approxMaxPointsParam = (this.name() + "ApproxMaxPoints");//$NON-NLS-1$
    this.m_approxMaxPoints = ProgressUtils.DEFAULT_MAX_POINTS;
  }

  /** {@inheritDoc} */
  @Override
  public final void configure(final Configuration config) {
    super.configure(config);

    this.m_approxMaxPoints = config.getInt(this.m_approxMaxPointsParam,
        -1, Integer.MAX_VALUE, this.m_approxMaxPoints);
  }

  /** {@inheritDoc} */
  @Override
  public final void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable.printKey(this.m_approxMaxPointsParam, ps);
    ps.println(this.m_approxMaxPoints);
  }

  /** {@inheritDoc} */
  @Override
  public final void printParameters(final PrintStream ps) {
    super.printParameters(ps);

    Configurable.printKey(this.m_approxMaxPointsParam, ps);
    ps.println("the approximate point limit when computing " + this.name()); //$NON-NLS-1$
  }

}
