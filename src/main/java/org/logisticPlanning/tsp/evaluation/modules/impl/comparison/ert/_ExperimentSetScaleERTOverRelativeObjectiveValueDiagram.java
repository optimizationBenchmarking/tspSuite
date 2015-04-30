package org.logisticPlanning.tsp.evaluation.modules.impl.comparison.ert;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.logisticPlanning.tsp.evaluation.Evaluator;
import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.data.properties.ert.ERTSeriesProperty;
import org.logisticPlanning.tsp.evaluation.data.properties.ert.ExperimentERTProperty;
import org.logisticPlanning.tsp.evaluation.data.properties.instance.InstancesProperty;
import org.logisticPlanning.tsp.evaluation.data.properties.ranking.RankAggregate;
import org.logisticPlanning.tsp.evaluation.data.properties.ranking.Ranking;
import org.logisticPlanning.tsp.evaluation.data.properties.scale.AllScaleInstances;
import org.logisticPlanning.tsp.evaluation.data.properties.scale.AllScaleInstancesProperty;
import org.logisticPlanning.tsp.evaluation.data.properties.scale.SameScaleInstances;
import org.logisticPlanning.tsp.evaluation.modules.ModuleUtils;
import org.logisticPlanning.tsp.evaluation.modules.impl.macros.Macros;
import org.logisticPlanning.tsp.evaluation.modules.impl.single.ert.ERTUtils;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.utils.document.spec.Document;
import org.logisticPlanning.utils.document.spec.DocumentDimensions;
import org.logisticPlanning.utils.document.spec.EDefaultFigureSize;
import org.logisticPlanning.utils.document.spec.EMathOp;
import org.logisticPlanning.utils.document.spec.FigureBody;
import org.logisticPlanning.utils.document.spec.FigureSeries;
import org.logisticPlanning.utils.document.spec.FigureSeriesCaption;
import org.logisticPlanning.utils.document.spec.Graphic;
import org.logisticPlanning.utils.document.spec.InlineMath;
import org.logisticPlanning.utils.document.spec.Label;
import org.logisticPlanning.utils.document.spec.MacroInvocation;
import org.logisticPlanning.utils.document.spec.MacroParameter;
import org.logisticPlanning.utils.document.spec.MathOp;
import org.logisticPlanning.utils.document.spec.MathOpParam;
import org.logisticPlanning.utils.document.spec.SectionBody;
import org.logisticPlanning.utils.document.spec.SectionTitle;
import org.logisticPlanning.utils.document.spec.SubFigure;
import org.logisticPlanning.utils.document.spec.SubFigureCaption;
import org.logisticPlanning.utils.graphics.DoubleDimension;
import org.logisticPlanning.utils.graphics.chart.spec.ChartDriver;
import org.logisticPlanning.utils.graphics.chart.spec.ELegendType;
import org.logisticPlanning.utils.graphics.chart.spec.Line2D;
import org.logisticPlanning.utils.graphics.chart.spec.LineChart2D;
import org.logisticPlanning.utils.math.statistics.EStatisticParameter;

/**
 * <p>
 * We plot the medians of the Estimated Running Time (ERT) to
 * success&nbsp;[<a href="#cite_HAFR2012RPBBOBES"
 * style="font-weight:bold">1</a>, <a href="#cite_AH2005PEOAALSEA"
 * style="font-weight:bold">2</a>, <a href="#cite_P1997DEVTFOT2I"
 * style="font-weight:bold">3</a>] over the relative error aggregated over
 * all benchmark instances of the same scale. See the documentation of the
 * class
 * {@link org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.eval.DescERT
 * DescERT} for more information.
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_HAFR2012RPBBOBES" /><a
 * href="http://www.lri.fr/~hansen/">Nikolaus Hansen</a>, <a
 * href="http://www.lri.fr/~auger/">Anne Auger</a>, <a
 * href="http://www.researchgate.net/profile/Steffen_Finck/">Steffen
 * Finck</a>, and&nbsp;<a
 * href="https://tao.lri.fr/tiki-index.php?page=people">Raymond Ros</a>:
 * <span style="font-weight:bold">&ldquo;Real-Parameter Black-Box
 * Optimization Benchmarking: Experimental Setup,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * March&nbsp;24, 2012; published by Orsay, France: Universit&#233; Paris
 * Sud, Institut National de Recherche en Informatique et en Automatique
 * (INRIA) Futurs, &#201;quipe TAO. <div>link: [<a href=
 * "http://coco.lri.fr/BBOB-downloads/download11.05/bbobdocexperiment.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_AH2005PEOAALSEA" /><a
 * href="http://www.lri.fr/~auger/">Anne Auger</a> and&nbsp;<a
 * href="http://www.lri.fr/~hansen/">Nikolaus Hansen</a>: <span
 * style="font-weight:bold">&ldquo;Performance Evaluation of an Advanced
 * Local Search Evolutionary Algorithm,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the IEEE
 * Congress on Evolutionary Computation (CEC'05)</span>,
 * September&nbsp;2&ndash;5, 2005, Edinburgh, Scotland, UK, pages
 * 1777&ndash;1784, <a
 * href="http://www.macs.hw.ac.uk/staff-directory/david-wolfe-corne.htm"
 * >David Wolfe Corne</a>, <a
 * href="http://cs.adelaide.edu.au/~zbyszek/">Zbigniew Michalewicz</a>, <a
 * href="http://sc.snu.ac.kr/~rim/index.html">Robert Ian McKay</a>, <a
 * href="http://www.cs.vu.nl/~gusz/">&#193;goston E. Eiben</a> aka.
 * Gusz/Guszti, <a
 * href="http://www.natural-selection.com/people_dfogel.html">David B.
 * Fogel</a>, Carlos M. Fonseca, <a
 * href="https://www.ads.tuwien.ac.at/raidl/">G&#252;nther R. Raidl</a>, <a
 * href="http://vlab.ee.nus.edu.sg/~kctan/">Kay Chen Tan</a>, and&nbsp;Ali
 * M. S. Zalzala, editors, Piscataway, NJ, USA: IEEE Computer Society.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780393635">0-7803-9363-5</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780393639">978-0-7803
 * -9363-9</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CEC.2005.1554903">10.1109/CEC.2005
 * .1554903</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/62773008">62773008</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=-QIVAAAACAAJ">-QIVAAAACAAJ</a>;
 * INSPEC Accession Number:&nbsp;8715465. <div>link: [<a
 * href="http://www.lri.fr/~hansen/cec2005localcmaes.pdf">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.71.6248">10.1
 * .1.71 .6248</a></div></div></li>
 * <li><div><span id="cite_P1997DEVTFOT2I" />Kenneth V. Price: <span
 * style="font-weight:bold">&ldquo;Differential Evolution vs. the Functions
 * of the 2nd ICEO,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the IEEE
 * International Conference on Evolutionary Computation (CEC'97)</span>,
 * April&nbsp;13&ndash;16, 1997, Indianapolis, IN, USA, pages
 * 153&ndash;157, <a href="http://www.liacs.nl/~baeck/contact.htm">Thomas
 * B&#228;ck</a>, <a href="http://cs.adelaide.edu.au/~zbyszek/">Zbigniew
 * Michalewicz</a>, and&nbsp;<a href="http://www.cs.bham.ac.uk/~xin/">Xin
 * Yao</a> <span style="color:gray">[&#23002;&#26032;</span>], editors,
 * Piscataway, NJ, USA: IEEE Computer Society. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780339495">0-7803-3949-5</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780339491">978-0-7803
 * -3949-1</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/ICEC.1997.592287">10.1109/ICEC.1997
 * .592287</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=6JZVAAAAMAAJ">6JZVAAAAMAAJ</a>;
 * INSPEC Accession Number:&nbsp;5573043</div></li>
 * </ol>
 */
final class _ExperimentSetScaleERTOverRelativeObjectiveValueDiagram extends
    _ExperimentSetERTOverRelativeObjectiveValueDiagramBase {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the caption 3 */
  private static final String CAP_3A = //
  ", cut off at 0. The diagrams are aggregated over instance whose scale "; //$NON-NLS-1$
  /** the caption 3 */
  private static final String CAP_3B = //
  ". The diagrams are aggregated over instance whose scale "; //$NON-NLS-1$
  /** the caption 4 */
  private static final String CAP_4 = //
  " falls in the same power of "; //$NON-NLS-1$
  /** sub caption 3 */
  static final String SCAP_3 = " for "; //$NON-NLS-1$
  /** sub caption 4 */
  static final String SCAP_4 = " instance with "; //$NON-NLS-1$

  /** the y-axis caption part 1 */
  static final String Y_AXIS_1b = "lg(med " + //$NON-NLS-1$ 
      Macros.ERT.getPlaceholder();

  /** the property */
  private final AllScaleInstancesProperty<ExperimentSet> m_property;
  /** the accessor */
  private final Accessor m_axs;

  /**
   * create!
   * 
   * @param owner
   *          the macro's owner
   * @param property
   *          the property
   * @param axs
   *          the accessor
   * @param isActive
   *          should this module be active?
   */
  _ExperimentSetScaleERTOverRelativeObjectiveValueDiagram(
      final Module owner, final Accessor axs,
      final AllScaleInstancesProperty<ExperimentSet> property,
      final boolean isActive) {
    super(axs.getShortName() + "Scale" + property.getBase(), //$NON-NLS-1$
        owner, isActive);

    this.m_size = EDefaultFigureSize.THREE_IN_A_ROW;
    this.m_axs = axs;
    this.m_property = property;
  }

  /** {@inheritDoc} */
  @Override
  protected final void writeSectionTitle(final SectionTitle title,
      final ExperimentSet data) throws IOException {
    try (MacroInvocation mi = title.macroInvocation(Macros.ERTi)) {
      try (MacroParameter p = mi.macroParameter()) {
        this.m_axs.writeShortName(p);
      }
    }
    title.write(//
        " over Relative Objective Value Thresholds over Problem Scale "); //$NON-NLS-1$
    title.writeInt(this.m_property.getBase());
  }

  /** {@inheritDoc} */
  @SuppressWarnings("resource")
  @Override
  protected final void writeSectionBody(final SectionBody body,
      final ExperimentSet data) throws IOException {
    final ChartDriver drv;
    final Document doc;
    final Logger log;
    final DoubleDimension dim;
    final DocumentDimensions dims;
    final Label l;
    final ArrayList<Line2D> lines;
    final ArrayList<Map.Entry<Experiment, Object>> aucLines;
    final RankAggregate<Experiment> rag;
    final AllScaleInstances instances;
    final Ranking<Experiment> ranking;
    final ERTSeriesProperty ertp;
    boolean includeAxisLabels, includeLegend;
    LineChart2D chart;
    String s;
    int fc;

    doc = body.getDocument();

    instances = this.m_property.get(data, doc);
    if ((instances == null) || (instances.isEmpty())) {
      s = Evaluator.WARNING_NO_DATA;
      body.write(s);
      log = this.getLogger();
      if ((log != null) && (log.isLoggable(Level.WARNING))) {
        log.warning(s);
      }
      return;
    }

    drv = doc.getOwner().getDriver().getChartDriver();
    if (drv == null) {
      s = ModuleUtils.WARNING_NO_CHART_DRIVER;
      body.write(s);
      log = this.getLogger();
      if ((log != null) && (log.isLoggable(Level.WARNING))) {
        log.warning(s);
      }
      return;
    }

    dims = doc.getDimensions();
    dim = dims.getFigureDimensionsMM(this.m_size);

    includeAxisLabels = includeLegend = false;
    if (dim.contains(dims.getFigureDimensionsMM(//
        EDefaultFigureSize.TWO_IN_A_ROW))) {
      includeAxisLabels = true;
      includeLegend = true;
    }

    ertp = ERTSeriesProperty.forAccessor(this.m_axs);

    fc = 0;
    try (FigureSeries fs = body
        .figureSeries(
            Label.AUTO_LABEL,
            dim,//
            (_ExperimentSetERTOverRelativeObjectiveValueDiagramBase.CAP_1
                + Macros.F_THRESHOLD_RELATIVE.getPlaceholder()
                + _ExperimentSetERTOverRelativeObjectiveValueDiagramBase.CAP_2
                + Macros.ERT.getPlaceholder()
                + (this.m_axs.isTime() ? _ExperimentSetScaleERTOverRelativeObjectiveValueDiagram.CAP_3A
                    : _ExperimentSetScaleERTOverRelativeObjectiveValueDiagram.CAP_3B)
                + Macros.SCALE
                + _ExperimentSetScaleERTOverRelativeObjectiveValueDiagram.CAP_4 + '.'),//
            (_ExperimentSetERTOverRelativeObjectiveValueDiagramBase.SCAP_1 + _ExperimentSetERTOverRelativeObjectiveValueDiagramBase.SCAP_2),//
            true)) {

      try (FigureSeriesCaption cap = fs.figureSeriesCaption()) {
        cap.write(_ExperimentSetERTOverRelativeObjectiveValueDiagramBase.CAP_1);
        cap.macroInvoke(Macros.F_THRESHOLD_RELATIVE);
        cap.write(_ExperimentSetERTOverRelativeObjectiveValueDiagramBase.CAP_2);
        cap.macroInvoke(Macros.ERT);
        cap.write((this.m_axs.isTime() ? _ExperimentSetScaleERTOverRelativeObjectiveValueDiagram.CAP_3A
            : _ExperimentSetScaleERTOverRelativeObjectiveValueDiagram.CAP_3B));
        cap.macroInvoke(Macros.SCALE);
        cap.write(_ExperimentSetScaleERTOverRelativeObjectiveValueDiagram.CAP_4);
        cap.writeInt(this.m_property.getBase());
        cap.writeChar('.');
      }

      lines = new ArrayList<>();
      aucLines = new ArrayList<>();
      rag = new RankAggregate<>(data);
      for (final SameScaleInstances ssi : instances) {
        this._makeFigure(data,//
            new ExperimentERTProperty(//
                ertp, new InstancesProperty(ssi)), lines, doc, aucLines);

        if (lines.isEmpty()) {
          aucLines.clear();
          continue;
        }

        if (aucLines.size() == data.size()) {
          _ExperimentSetERTOverRelativeObjectiveValueDiagramBase
              ._aggregate(aucLines, rag);
          aucLines.clear();
        }

        if ((fc <= 0) && (!(includeLegend))) {

          try (SubFigure sf = fs.subFigure(null)) {

            try (SubFigureCaption cap = sf.subFigureCaption()) {
              cap.write("Legend."); //$NON-NLS-1$
            }

            try {
              try (FigureBody fb = sf
                  .figureBody(
                      this.makeURI(Module.GRAPHICS_FOLDER, Module.LEGEND),
                      dim)) {
                try (Graphic graph = fb.graphic()) {
                  chart = drv.createLineChart2D(ERTUtils.DEFAULT_AXIS_DEF);
                  chart.setLegendType(ELegendType.ONLY_LEGEND);
                  chart.addLines(lines);
                  chart
                      .setAxisTitleX(_ExperimentSetERTOverRelativeObjectiveValueDiagramBase.X_AXIS);
                  if (this.m_axs.isTime()) {
                    chart
                        .setAxisTitleY(_ExperimentSetERTOverRelativeObjectiveValueDiagramBase.Y_AXIS_1
                            + this.m_axs.getAxisString()
                            + _ExperimentSetERTOverRelativeObjectiveValueDiagramBase.Y_AXIS_2);
                  } else {
                    chart
                        .setAxisTitleY(_ExperimentSetScaleERTOverRelativeObjectiveValueDiagram.Y_AXIS_1b
                            + this.m_axs.getAxisString() + ')');
                  }
                  chart.paint(graph);
                  chart = null;
                }
              }

            } catch (final URISyntaxException use) {
              throw new IOException(use);
            }
          }

        }

        try (SubFigure sf = fs.subFigure(null)) {

          try (SubFigureCaption cap = sf.subFigureCaption()) {

            cap.write(_ExperimentSetERTOverRelativeObjectiveValueDiagramBase.SCAP_1);

            try (MacroInvocation mi = cap.macroInvocation(Macros.ERTi)) {
              try (MacroParameter p = mi.macroParameter()) {
                this.m_axs.writeShortName(p);
              }
            }

            if (this.m_axs.isScaled()) {
              cap.write(_ExperimentSetERTOverRelativeObjectiveValueDiagramBase.SCAP_2);
              this.m_axs.writeScale(cap);
            }

            cap.write(_ExperimentSetScaleERTOverRelativeObjectiveValueDiagram.SCAP_3);
            cap.writeInt(ssi.size());
            cap.write(_ExperimentSetScaleERTOverRelativeObjectiveValueDiagram.SCAP_4);

            try (InlineMath im = cap.inlineMath()) {

              try (MathOp mop1 = im.mathOp(EMathOp.CMP_LESS_OR_EQUAL)) {
                try (MathOpParam p1 = mop1.mathOpParam()) {
                  p1.writeInt(ssi.getLowerScaleBound());
                }
                try (MathOpParam p2 = mop1.mathOpParam()) {
                  try (MathOp mop2 = im.mathOp(EMathOp.CMP_LESS)) {
                    try (MathOpParam p3 = mop2.mathOpParam()) {
                      p3.macroInvoke(Macros.SCALE);
                    }
                    try (MathOpParam p4 = mop2.mathOpParam()) {
                      p4.writeInt(ssi.getUpperScaleBound() + 1);
                    }
                  }
                }
              }
            }
          }

          try {
            try (FigureBody fb = sf.figureBody(
                this.makeURI(Module.GRAPHICS_FOLDER, null, null,
                    ((ssi.getLowerScaleBound() + "_to_") + //$NON-NLS-1$
                    (ssi.getUpperScaleBound()))), dim)) {
              try (Graphic graph = fb.graphic()) {
                chart = drv.createLineChart2D(ERTUtils.DEFAULT_AXIS_DEF);
                chart.addLines(lines);
                fc++;
                chart.setLegendType(includeLegend ? ELegendType.PUT_LEGEND
                    : ELegendType.NO_LEGEND);
                if (includeAxisLabels) {
                  chart
                      .setAxisTitleX(_ExperimentSetERTOverRelativeObjectiveValueDiagramBase.X_AXIS);
                  if (this.m_axs.isTime()) {
                    chart
                        .setAxisTitleY(_ExperimentSetERTOverRelativeObjectiveValueDiagramBase.Y_AXIS_1
                            + this.m_axs.getAxisString()
                            + _ExperimentSetERTOverRelativeObjectiveValueDiagramBase.Y_AXIS_2);
                  } else {
                    chart
                        .setAxisTitleY(_ExperimentSetScaleERTOverRelativeObjectiveValueDiagram.Y_AXIS_1b
                            + this.m_axs.getAxisString() + ')');
                  }
                }

                chart.paint(graph);
                chart = null;
              }
            }

          } catch (final URISyntaxException use) {
            throw new IOException(use);
          }
        }

      }
      l = fs.getLabel();
    }

    body.write("In "); //$NON-NLS-1$
    body.reference(l);
    body.write(" we plot the estimated running time "); //$NON-NLS-1$
    try (MacroInvocation mi = body.macroInvocation(Macros.ERTi)) {
      try (MacroParameter p = mi.macroParameter()) {
        this.m_axs.writeShortName(p);
      }
    }
    body.write(" (i.e., in terms of "); //$NON-NLS-1$
    this.m_axs.writeLongName(body, true);
    body.write("), introduced in "); //$NON-NLS-1$
    body.reference(this.m_descERT.getLabel(data));
    body.write(" over the relative target objective values "); //$NON-NLS-1$
    body.macroInvoke(Macros.F_THRESHOLD_RELATIVE);
    body.write(//
    " for different time measures aggregated over groups of benchmark instances whose scale ");//$NON-NLS-1$
    body.macroInvoke(Macros.SCALE);
    body.write(//
    " is in the same power of ");//$NON-NLS-1$
    body.writeInt(this.m_property.getBase());
    body.writeChar('.');

    body.writeLinebreak();

    body.write("The x-axes in the sub-figures of "); //$NON-NLS-1$
    body.reference(l);
    body.write(" represent the relative target objective values "); //$NON-NLS-1$
    body.macroInvoke(Macros.F_THRESHOLD_RELATIVE);
    body.write(". The y-axes represent the median "); //$NON-NLS-1$
    try (MacroInvocation mi = body.macroInvocation(Macros.ERTi)) {
      try (MacroParameter p = mi.macroParameter()) {
        this.m_axs.writeShortName(p);
      }
    }
    body.write("s for the given "); //$NON-NLS-1$
    body.macroInvoke(Macros.F_THRESHOLD_RELATIVE);
    body.write("-values, aggregated over "); //$NON-NLS-1$
    body.writeInt(instances.size());
    body.write(//
    " groups, each of which containing for which containing problem instances with scales ");//$NON-NLS-1$
    body.macroInvoke(Macros.SCALE);
    body.write(//
    " that are in the same power of ");//$NON-NLS-1$
    body.writeInt(this.m_property.getBase());

    body.write(//
    ". Since the different problem instances have different scales which affect the running time, we scale the "); //$NON-NLS-1$
    body.macroInvoke(Macros.ERT);
    body.write("s before computing their medians. The scaling factor depends on the time measure in which the ERTs are specified and is discussed in detail in"); //$NON-NLS-1$
    body.reference(this.m_descTime.getLabel(data));
    body.write(//
    ". We plot the logarithms of these values in order to make the diagrams readable.");//$NON-NLS-1$

    if (this.m_axs.isTime()) {
      body.write(//
      " This has the disadvantage that values such as "); //$NON-NLS-1$
      body.writeInt(0);
      body.write(" (which could occur in real time measurements due to clock resolution) cannot be plotted. We therefore choose to represent all values less than "); //$NON-NLS-1$
      body.writeInt(1);
      body.write(" as "); //$NON-NLS-1$
      body.writeInt(0);
      body.write(" in the diagrams. This results in the drawback is that values between "); //$NON-NLS-1$
      body.writeInt(0);
      body.write(" and "); //$NON-NLS-1$
      body.writeInt(1);
      body.write(" will now also be represented as "); //$NON-NLS-1$
      body.writeInt(0);
      body.write(//
      ", which makes the diagrams potentially misleading for small time values / small-scale benchmark cases."); //$NON-NLS-1$
    }

    body.writeLinebreak();
    ranking = rag.rank(EStatisticParameter.MEDIAN);
    body.write(//
    "We now rank the methods according to their Area Under the Curve ("); //$NON-NLS-1$
    body.macroInvoke(Macros.AUC);
    body.write(", ");//$NON-NLS-1$
    body.reference(this.m_descAuc.getLabel(data));
    body.write(//
    "). Of course, smaller areas are better and we use the same scaling as in the diagrams. We then re-ranking the methods according to their median rank over all diagrams, as discussed in "); //$NON-NLS-1$
    body.reference(this.m_descRanking.getLabel(data));
    body.write(". From this, ");//$NON-NLS-1$
    this.summarizeRanks(ranking, body, "discover good solutions faster"); //$NON-NLS-1$
    this.propagateRanking(ranking, data, doc);
  }
}
