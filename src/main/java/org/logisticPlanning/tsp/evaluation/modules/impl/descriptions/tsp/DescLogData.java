package org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.tsp;

import java.io.IOException;

import org.logisticPlanning.tsp.benchmarking.objective.Benchmark;
import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.modules.impl.macros.Macros;
import org.logisticPlanning.tsp.evaluation.modules.spec.DescriptionModule;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.utils.document.spec.EMathOp;
import org.logisticPlanning.utils.document.spec.ESequenceType;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.InlineMath;
import org.logisticPlanning.utils.document.spec.MathOp;
import org.logisticPlanning.utils.document.spec.MathOpParam;
import org.logisticPlanning.utils.document.spec.Section;
import org.logisticPlanning.utils.document.spec.SectionBody;
import org.logisticPlanning.utils.document.spec.SectionTitle;

/**
 * A description module that prints information about when a log point is
 * collected.
 */
public final class DescLogData extends DescriptionModule {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create!
   *
   * @param owner
   *          the macro's owner
   */
  DescLogData(final Module owner) {
    super("logData", owner, true); //$NON-NLS-1$

    this.addDependency(this.getRoot().findInstance(DescTimeMeasures.class));
    this.addDependency(this.getRoot().findInstance(
        DescRelativeObjectiveValues.class));
  }

  /** {@inheritDoc} */
  @Override
  protected void initialize(final Header header, final ExperimentSet data)
      throws IOException {//

    super.initialize(header, data);

    Accessor.DE.define(header);
    Accessor.FE.define(header);
    Accessor.F.define(header);
    Macros.F_THRESHOLD.define(header);
    Macros.F_BEST.define(header);
    Macros.F_OPTIMAL.define(header);
    Macros.F_BEST_RELATIVE.define(header);
    Macros.F_THRESHOLD_RELATIVE.define(header);
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSectionTitle(final SectionTitle title,
      final ExperimentSet data) throws IOException {
    title.write("Data Collection"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSectionBody(final SectionBody body,
      final ExperimentSet data) throws IOException {
    long[] ls;
    int start, end;

    try (Section sect = body.section(null)) {
      try (SectionTitle title = sect.sectionTitle()) {
        title.write("Log Points in Terms of "); //$NON-NLS-1$
        Accessor.DE.writeShortName(title, true);
        title.write(" and ");//$NON-NLS-1$
        Accessor.F.writeShortName(title, true);
      }

      try (SectionBody sb = sect.sectionBody()) {

        sb.write(//
            "In the log files, we collect a log point whenever the internal "); //$NON-NLS-1$
        Accessor.DE.writeShortName(sb, false);
        sb.write(" or "); //$NON-NLS-1$
        Accessor.FE.writeShortName(sb, false);
        sb.write(//
            " counter exceeds one of the following values (or any multiple of any of these values and "); //$NON-NLS-1$

        sb.macroInvoke(Macros.SCALE);
        sb.write(", ");//$NON-NLS-1$
        try (InlineMath im = sb.inlineMath()) {
          try (MathOp mo = im.mathOp(EMathOp.POW)) {
            try (MathOpParam p1 = mo.mathOpParam()) {
              p1.macroInvoke(Macros.SCALE);
            }
            try (MathOpParam p2 = mo.mathOpParam()) {
              p2.writeInt(2);
            }
          }
        }
        sb.write(", or ");//$NON-NLS-1$
        try (InlineMath im = sb.inlineMath()) {
          try (MathOp mo = im.mathOp(EMathOp.POW)) {
            try (MathOpParam p1 = mo.mathOpParam()) {
              p1.macroInvoke(Macros.SCALE);
            }
            try (MathOpParam p2 = mo.mathOpParam()) {
              p2.writeInt(3);
            }
          }
        }

        sb.write(") if there has been an improvement in "); //$NON-NLS-1$
        sb.macroInvoke(Macros.F_BEST);
        sb.write(" since the last log point: "); //$NON-NLS-1$

        ls = Benchmark.getLogFEsAndDEs(0, Long.MAX_VALUE, Long.MIN_VALUE);
        end = (ls.length - 1);

        for (start = 0; start <= end; start++) {
          if ((ls[start] >= 0l) && (ls[start] <= Long.MAX_VALUE)) {
            break;
          }
        }

        while ((start < end) && (ls[start] <= ls[start + 1])) {
          start++;
        }

        for (; end > start; end--) {
          if ((ls[end] >= 0l) && (ls[end] <= Long.MAX_VALUE)) {
            break;
          }
        }
        while ((end > 0) && (ls[end] >= ls[end - 1])) {
          end--;
        }

        sb.writeSequence(new _ThresholdSequence(ls, start, end, sb),
            ESequenceType.AND, true);
        sb.writeChar('.');
      }
    }

    try (Section sect = body.section(null)) {
      try (SectionTitle title = sect.sectionTitle()) {
        title.write("Log Points in Terms of the Objective Values "); //$NON-NLS-1$
        Accessor.F.writeShortName(title);
      }

      try (SectionBody sb = sect.sectionBody()) {

        sb.write(//
            "In order to define threshold objective values "); //$NON-NLS-1$
        sb.macroInvoke(Macros.F_THRESHOLD);
        sb.write(//
            " for the benchmark cases for collecting log points, we use the same number list given above. "); //$NON-NLS-1$

        sb.write(//
            "Here we collect a log point whenever objective value "); //$NON-NLS-1$
        sb.macroInvoke(Macros.F_BEST);

        sb.write(//
            " of the best solution encountered so far improves below any of these absolute values added to the objective value "); //$NON-NLS-1$
        sb.macroInvoke(Macros.F_OPTIMAL);
        sb.write(//
            " of the global optimum. Additionally, log points are taken when the relative objective value "); //$NON-NLS-1$
        sb.macroInvoke(Macros.F_BEST_RELATIVE);
        sb.write(" reaches a threshold "); //$NON-NLS-1$
        sb.macroInvoke(Macros.F_THRESHOLD_RELATIVE);
        sb.write(", which is a multiple of any of these values and "); //$NON-NLS-1$
        sb.writeDouble(Benchmark.MULTIPLIER_1);
        sb.write(" or "); //$NON-NLS-1$
        sb.writeDouble(Benchmark.MULTIPLIER_2);
        sb.writeChar('.');
      }
    }

    super.writeSectionBody(body, data);
  }
}
