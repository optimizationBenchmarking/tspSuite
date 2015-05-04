package org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.tsp;

import java.io.IOException;

import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.modules.impl.macros.Macros;
import org.logisticPlanning.tsp.evaluation.modules.spec.DescriptionModule;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.utils.document.spec.Emphasize;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.SectionBody;
import org.logisticPlanning.utils.document.spec.SectionTitle;

/**
 * A description of the relative objective measures.
 */
public final class DescRelativeObjectiveValues extends DescriptionModule {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create!
   *
   * @param owner
   *          the macro's owner
   */
  DescRelativeObjectiveValues(final Module owner) {
    super("relativeObjectiveValues", owner, false); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected void initialize(final Header header, final ExperimentSet data)
      throws IOException {//
    super.initialize(header, data);
    Accessor.F.define(header);
    Accessor.F_RELATIVE.define(header);
    Macros.F_BEST.define(header);
    Macros.F_THRESHOLD.define(header);
    Macros.F_BEST_RELATIVE.define(header);
    Macros.F_THRESHOLD_RELATIVE.define(header);
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSectionTitle(final SectionTitle title,
      final ExperimentSet data) throws IOException {
    title.write("Relative Objective Values"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSectionBody(final SectionBody body,
      final ExperimentSet data) throws IOException {

    body.write("Since the objective values ");//$NON-NLS-1$
    body.macroInvoke(Macros.F_OPTIMAL);
    body.write(//
        " of the known global optima tend are different in different benchmark cases, stating the objective value ");//$NON-NLS-1$
    body.macroInvoke(Macros.F_BEST);
    body.write(//
        " of the solution discovered during a search process or an absolute target objective value ");//$NON-NLS-1$
    body.macroInvoke(Macros.F_THRESHOLD);
    body.write(//
        " without also stating the benchmark cases' name makes no sense. This also makes results incomparable when they stem from different problems. However, this problem can easily be remedied by using ");//$NON-NLS-1$
    try (Emphasize em = body.emphasize()) {
      em.write("relative");//$NON-NLS-1$
    }
    body.write(//
        " values instead: from an objective value, first the objective value ");//$NON-NLS-1$
    body.macroInvoke(Macros.F_OPTIMAL);
    body.write(//
        " of the known global optimum is subtracted and the result is then divided by ");//$NON-NLS-1$
    body.macroInvoke(Macros.F_OPTIMAL);
    body.write(//
        ". This means that a relative objective value of ");//$NON-NLS-1$
    body.writeDouble(0.0d);
    body.write(//
        " represents the global optimum in any benchmark case, ");//$NON-NLS-1$
    body.writeDouble(0.1d);
    body.write(" means to be ");//$NON-NLS-1$
    body.writeInt(10);
    body.write(//
        "% off, and so on. Thus, we frequently use relative values ");//$NON-NLS-1$
    body.macroInvoke(Macros.F_BEST_RELATIVE);
    body.write(" and ");//$NON-NLS-1$
    body.macroInvoke(Macros.F_THRESHOLD_RELATIVE);
    body.write(//
        " instead of the absolute objective value ");//$NON-NLS-1$
    body.macroInvoke(Macros.F_BEST);
    body.write(//
        " of the best solution discovered so far or an absolute threshold tour length ");//$NON-NLS-1$
    body.macroInvoke(Macros.F_THRESHOLD);
    body.write(". Generally, we denote absolute objective values with ");//$NON-NLS-1$
    Accessor.F.writeShortName(body);
    body.write(" and relative objective values with ");//$NON-NLS-1$
    Accessor.F_RELATIVE.writeShortName(body);
    body.writeChar('.');

    super.writeSectionBody(body, data);
  }
}
