package org.logisticPlanning.tsp.evaluation.modules.impl.single.progress;

import java.io.IOException;
import java.io.PrintStream;

import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.modules.impl.macros.Macros;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.tsp.evaluation.modules.spec.SingleModule;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;
import org.logisticPlanning.utils.document.spec.EDefaultFigureSize;
import org.logisticPlanning.utils.document.spec.Header;

/**
 * The internal base class for statistic diagrams.
 */
class _ExperimentProgressBaseA extends SingleModule {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** caption 1 */
  static final String CAP_1 = "The performance of "; //$NON-NLS-1$

  /** the figure size parameter */
  private final String m_sizeParam;

  /** the figure size */
  EDefaultFigureSize m_size;

  /** print runs as background lines */
  boolean m_printRuns;

  /** the print runs as background line parameter */
  private final String m_printRunsParam;

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
  _ExperimentProgressBaseA(final String name, final Module owner,
      final boolean isActive) {
    super(name + "Diagrams", owner, isActive); //$NON-NLS-1$

    this.m_sizeParam = (this.name() + Module.FIGURE_SIZE_PARAM);
    this.m_size = EDefaultFigureSize.FOUR_IN_A_ROW;
    this.m_printRunsParam = (this.name() + "PrintRuns"); //$NON-NLS-1$
    this.m_printRuns = false;
  }

  /** {@inheritDoc} */
  @Override
  public void configure(final Configuration config) {
    super.configure(config);

    this.m_size = config.getConstant(this.m_sizeParam,
        EDefaultFigureSize.class, EDefaultFigureSize.class, this.m_size);
    this.m_printRuns = config.getBoolean(this.m_printRunsParam,
        this.m_printRuns);
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable.printKey(this.m_sizeParam, ps);
    Configurable.printlnObject(this.m_size, ps);

    Configurable.printKey(this.m_printRunsParam, ps);
    ps.println(this.m_printRuns);
  }

  /** {@inheritDoc} */
  @Override
  public void printParameters(final PrintStream ps) {
    super.printParameters(ps);

    Configurable.printKey(this.m_sizeParam, ps);
    ps.println(Module.FIGURE_SIZE_PARAM_DESC_PREFIX + this.name());

    Configurable.printKey(this.m_printRunsParam, ps);
    ps.println("print runs as background lines for " + this.name()); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected void initialize(final Header header, final Experiment data)
      throws IOException {//
    super.initialize(header, data);
    Macros.SCALE.define(header);
    Macros.F_OPTIMAL.define(header);
    Macros.F_BEST_RELATIVE.define(header);
    Macros.F_BEST.define(header);
  }
}
