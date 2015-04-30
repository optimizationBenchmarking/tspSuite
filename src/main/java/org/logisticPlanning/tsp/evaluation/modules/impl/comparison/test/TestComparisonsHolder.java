package org.logisticPlanning.tsp.evaluation.modules.impl.comparison.test;

import java.io.IOException;
import java.util.ArrayList;

import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.data.properties.ranking.RankAggregate;
import org.logisticPlanning.tsp.evaluation.data.properties.ranking.Ranking;
import org.logisticPlanning.tsp.evaluation.data.properties.test.TestComparisonProperty;
import org.logisticPlanning.tsp.evaluation.data.properties.test.TestComparisonResult;
import org.logisticPlanning.tsp.evaluation.modules.impl.comparison.RankedComparisonModule;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.tsp.evaluation.modules.spec.RootModule;
import org.logisticPlanning.utils.collections.lists.ArrayListView;
import org.logisticPlanning.utils.config.Configuration;
import org.logisticPlanning.utils.document.spec.Document;
import org.logisticPlanning.utils.document.spec.ELabelType;
import org.logisticPlanning.utils.document.spec.EMathOp;
import org.logisticPlanning.utils.document.spec.ESequenceType;
import org.logisticPlanning.utils.document.spec.InlineMath;
import org.logisticPlanning.utils.document.spec.Label;
import org.logisticPlanning.utils.document.spec.MathOp;
import org.logisticPlanning.utils.document.spec.MathOpParam;
import org.logisticPlanning.utils.document.spec.SectionBody;
import org.logisticPlanning.utils.document.spec.SectionTitle;
import org.logisticPlanning.utils.document.spec.Table;
import org.logisticPlanning.utils.document.spec.TableCaption;
import org.logisticPlanning.utils.document.spec.TableCellDef;
import org.logisticPlanning.utils.math.statistics.EStatisticParameter;

/**
 * This evaluator module will print tables comparing results of different
 * algorithms.
 */
public final class TestComparisonsHolder extends RankedComparisonModule {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** do we have contents? */
  private int m_hasContents;

  /**
   * create!
   * 
   * @param owner
   *          the macro's owner
   */
  public TestComparisonsHolder(final Module owner) {
    super("StatisticalComparisons", owner, false); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final void configure(final Configuration config) {
    super.configure(config);
    this.m_hasContents = 0;
  }

  /** {@inheritDoc} */
  @Override
  protected final boolean hasContent(final ExperimentSet data) {
    boolean has;
    switch (this.m_hasContents) {
      case (-1): {
        return false;
      }
      case 0: {
        has = false;
        for (final Module m : this.getActiveChildren()) {
          if (m instanceof _TestComparisons) {
            if (has) {
              this.m_hasContents = 1;
              return true;
            }
            has = true;
          }
        }
        this.m_hasContents = (-1);
        return false;
      }
      case 1: {
        return true;
      }
      default: {
        throw new IllegalStateException();
      }
    }
  }

  /** {@inheritDoc} */
  @SuppressWarnings("unused")
  @Override
  protected final void createChildModules() {

    new _TestComparisons(this,
        TestComparisonProperty.END_OF_RUN_F_002_MWU, true);
    new _TestComparisons(this,
        TestComparisonProperty.END_OF_RUN_F_005_MWU, false);

    new _TestComparisons(this, TestComparisonProperty.OPTIMUM_DE_002_MWU,
        true);
    new _TestComparisons(this, TestComparisonProperty.OPTIMUM_DE_005_MWU,
        false);
    new _TestComparisons(this, TestComparisonProperty.OPTIMUM_FE_002_MWU,
        true);
    new _TestComparisons(this, TestComparisonProperty.OPTIMUM_FE_005_MWU,
        false);
    new _TestComparisons(this,
        TestComparisonProperty.OPTIMUM_NORMALIZED_RUNTIME_002_MWU, true);
    new _TestComparisons(this,
        TestComparisonProperty.OPTIMUM_NORMALIZED_RUNTIME_005_MWU, false);

    new _TestComparisons(this,
        TestComparisonProperty.REL_F_001_DE_002_MWU, true);
    new _TestComparisons(this,
        TestComparisonProperty.REL_F_001_FE_002_MWU, true);
    new _TestComparisons(this,
        TestComparisonProperty.REL_F_001_NORMALIZED_RUNTIME_002_MWU, true);
  }

  /** {@inheritDoc} */
  @Override
  protected final void writeSectionTitle(final SectionTitle title,
      final ExperimentSet data) throws IOException {
    title.write("Statistical Tests"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @SuppressWarnings({ "resource" })
  @Override
  protected void writeSectionBody(final SectionBody body,
      final ExperimentSet data) throws IOException {
    final TestComparisonResult cr;
    final Document doc;
    final ArrayListView<Module> ch;
    final Label[] lab1, lab2;
    final int childC, expCount;
    final Label label;
    final TableCellDef[] def;
    final RootModule root;
    final RankAggregate<Experiment> agg;
    final Ranking<Experiment> median, ranking;
    final int cmp1on1;
    TestComparisonResult tcr;
    ArrayList<Label> labels1, labels2;
    _TestComparisons tt;
    int totalSubSec;
    boolean useMedian;

    expCount = data.size();
    if (expCount > 1) {
      ch = this.getActiveChildren();
      childC = ch.size();
      if (childC > 1) {

        if (this.hasContent(data)) {
          doc = body.getDocument();
          label = doc.createLabel(ELabelType.TABLE);

          // TODO XXX: The number of instances may actually vary if
          // test
          // comparison properties with different source instance
          // properties
          // are defined. This may need to be revised in future!!!
          // TODO XXX
          _TestComparisons._writeBodyText(this.getRoot(), body, data,
              true,//
              Double.NaN,//
              ((_TestComparisons) (ch.get(0))).m_property
                  .getSharedInstancesProperty().get(data, doc).size(),//
              label, this.m_descRanking.getLabel(data));

          cr = new TestComparisonResult(data);
          agg = new RankAggregate<>(data);
          labels1 = new ArrayList<>(childC);
          labels2 = new ArrayList<>(childC);
          totalSubSec = 0;
          for (final Module m : ch) {
            if (m instanceof _TestComparisons) {
              tt = ((_TestComparisons) m);
              tcr = tt.m_property.get(data, doc);
              cr.add(tcr);
              agg.aggregateRanks(tcr.getRankedScores());
              labels1.add(tt.getLabel(data));
              labels2.add(tt.m_tableLabel.get(data, doc));
              totalSubSec++;
            }
          }

          median = agg.rank(EStatisticParameter.MEDIAN);
          cmp1on1 = (cr.getWins(0, 1) + cr.getEvens(0, 1) + cr.getLosses(
              0, 1));
          if ((totalSubSec > 1) && (cmp1on1 > 0)) {
            root = this.getRoot();

            lab1 = labels1.toArray(new Label[labels1.size()]);
            labels1 = null;
            lab2 = labels2.toArray(new Label[labels2.size()]);
            labels2 = null;
            def = _TestComparisons._makeTableDef(expCount);

            try (Table tbl = body
                .table(
                    label, //
                    "Comparison results summed up over xxxxxxxxxxxxxxxxxxxxx ", //$NON-NLS-1$
                    (expCount > 3), def)) {

              try (TableCaption capt = tbl.tableCaption()) {

                capt.write("Comparison results summed up from ");//$NON-NLS-1$
                capt.reference(ESequenceType.FROM_TO, lab2);
                capt.writeChar('.');
              }

              _TestComparisons._makeTable(tbl, root, cr, data, median);
            }

            body.writeLinebreak();

            body.write("In ");//$NON-NLS-1$
            body.reference(label);
            body.write(" we present the summed-up results from all comparisons in ");//$NON-NLS-1$
            body.reference(ESequenceType.FROM_TO, lab1);
            body.write(" (");//$NON-NLS-1$
            body.reference(ESequenceType.FROM_TO, lab2);
            body.write("). In the ");//$NON-NLS-1$
            body.writeIntInText(totalSubSec, false);
            body.write(" following sub-sections, the ");//$NON-NLS-1$
            body.writeIntInText(expCount, false);
            body.write(" algorithms have, in total, undergone ");//$NON-NLS-1$

            try (InlineMath inm = body.inlineMath()) {
              try (MathOp mo1 = inm.mathOp(EMathOp.CMP_EQUALS)) {
                try (MathOpParam p1 = mo1.mathOpParam()) {
                  p1.writeInt(cmp1on1 * (expCount - 1));
                }
                try (MathOpParam p2 = mo1.mathOpParam()) {
                  try (MathOp op2 = p2.mathOp(EMathOp.MUL)) {
                    try (MathOpParam p3 = mo1.mathOpParam()) {
                      p3.writeInt(totalSubSec);
                    }
                    try (MathOpParam p4 = mo1.mathOpParam()) {
                      p4.writeInt((cmp1on1 * (expCount - 1)) / totalSubSec);
                    }
                  }
                }
              }
            }

            body.write(" comparisons each. When ranking the approaches according to their overall wins and losses,"); //$NON-NLS-1$
            ranking = cr.getRankedScores();
            useMedian = (!(median.isEquivalent(ranking)));
            if (!useMedian) {
              body.write(" as well as when ranking the algorithms according to their median ranks,"); //$NON-NLS-1$
            }
            this.summarizeRanks(ranking, body, null);
            if (useMedian) {
              body.writeLinebreak();
              body.write("When ranking the algorithms according to their median ranks in this section,"); //$NON-NLS-1$
              this.summarizeRanks(median, body, null);
            }
          }
        }
      }

      super.writeSectionBody(body, data);
    }
  }
}
