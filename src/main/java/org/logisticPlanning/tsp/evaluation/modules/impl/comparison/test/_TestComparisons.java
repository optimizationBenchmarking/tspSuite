package org.logisticPlanning.tsp.evaluation.modules.impl.comparison.test;

import java.io.IOException;
import java.text.DecimalFormat;

import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.data.properties.LabelProperty;
import org.logisticPlanning.tsp.evaluation.data.properties.limit.LimitDataCollectionProperty;
import org.logisticPlanning.tsp.evaluation.data.properties.ranking.Ranking;
import org.logisticPlanning.tsp.evaluation.data.properties.test.TestComparisonProperty;
import org.logisticPlanning.tsp.evaluation.data.properties.test.TestComparisonResult;
import org.logisticPlanning.tsp.evaluation.data.properties.test.TestScore;
import org.logisticPlanning.tsp.evaluation.modules.ModuleUtils;
import org.logisticPlanning.tsp.evaluation.modules.impl.ExperimentSequence;
import org.logisticPlanning.tsp.evaluation.modules.impl.comparison.RankedComparisonModule;
import org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.tests.DescTests;
import org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.tests.TestDescription;
import org.logisticPlanning.tsp.evaluation.modules.spec.RootModule;
import org.logisticPlanning.utils.document.spec.Document;
import org.logisticPlanning.utils.document.spec.ECitationMode;
import org.logisticPlanning.utils.document.spec.ELabelType;
import org.logisticPlanning.utils.document.spec.EMathOp;
import org.logisticPlanning.utils.document.spec.ESequenceType;
import org.logisticPlanning.utils.document.spec.Emphasize;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.InlineMath;
import org.logisticPlanning.utils.document.spec.Label;
import org.logisticPlanning.utils.document.spec.MathOp;
import org.logisticPlanning.utils.document.spec.MathOpParam;
import org.logisticPlanning.utils.document.spec.SectionBody;
import org.logisticPlanning.utils.document.spec.SectionTitle;
import org.logisticPlanning.utils.document.spec.Table;
import org.logisticPlanning.utils.document.spec.TableBody;
import org.logisticPlanning.utils.document.spec.TableBodyCell;
import org.logisticPlanning.utils.document.spec.TableBodyRow;
import org.logisticPlanning.utils.document.spec.TableCaption;
import org.logisticPlanning.utils.document.spec.TableCellDef;
import org.logisticPlanning.utils.document.spec.TableFooter;
import org.logisticPlanning.utils.document.spec.TableHeader;
import org.logisticPlanning.utils.document.spec.TableHeaderCell;
import org.logisticPlanning.utils.document.spec.TableHeaderRow;

/**
 * <p>
 * This evaluator will compares {@code m} algorithms according to some sort
 * of result with a statistical test and prints the results in a nice
 * table.
 * </p>
 */
public class _TestComparisons extends RankedComparisonModule {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the basic format to be used for ranks */
  private static final DecimalFormat RANK_FORMAT = //
  ModuleUtils.createDecimalFormat("0.0"); //$NON-NLS-1$

  /** the first caption */
  private static final String CAPT_1 = ("The results of "); //$NON-NLS-1$
  /** the secondcaption */
  private static final String CAPT_2 = (" with significance level "); //$NON-NLS-1$
  /** the third caption */
  private static final String CAPT_3 = (" comparing the "); //$NON-NLS-1$
  /** the fourth caption */
  private static final String CAPT_4 = (" that the different algorithms "); //$NON-NLS-1$
  /** the 5A caption */
  private static final String CAPT_5A = (" have achieved until "); //$NON-NLS-1$
  /** the 5b caption */
  private static final String CAPT_5B = (" need until "); //$NON-NLS-1$
  /** the 6A caption */
  private static final String CAPT_6A = (" is reached."); //$NON-NLS-1$
  /** the 6B caption */
  private static final String CAPT_6B = ("."); //$NON-NLS-1$

  /** the property */
  final TestComparisonProperty m_property;

  /** the table label */
  final LabelProperty m_tableLabel;

  /** the description */
  private final TestDescription m_desc;

  /**
   * create!
   *
   * @param owner
   *          the macro's owner
   * @param property
   *          the property
   * @param isActive
   *          should this module be active?
   */
  _TestComparisons(final TestComparisonsHolder owner,
      final TestComparisonProperty property, final boolean isActive) {
    super(("setup" + property.toString() + //$NON-NLS-1$
        "Comparator"), owner, isActive); //$NON-NLS-1$

    this.m_property = property;
    this.m_tableLabel = new LabelProperty(ELabelType.TABLE);

    this.m_desc = this.getRoot().findInstance(
        DescTests.forClass(this.m_property.getTest().getClass()));
    this.addDependency(this.m_desc);
  }

  /** {@inheritDoc} */
  @Override
  protected void initialize(final Header header, final ExperimentSet data)
      throws IOException {//
    super.initialize(header, data);
    this.m_property.initialize(header);
    this.m_tableLabel.initialize(header);
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSectionTitle(final SectionTitle title,
      final ExperimentSet data) throws IOException {
    this.m_property.writeShortName(title);
  }

  /**
   * make the table definition
   *
   * @param expCount
   *          the number of experiments
   * @return the table definition
   */
  static final TableCellDef[] _makeTableDef(final int expCount) {
    final TableCellDef[] defs;
    int i;

    i = 0;
    defs = new TableCellDef[(expCount << 1) + 3];
    defs[i++] = TableCellDef.VERTICAL_LINE;
    defs[i++] = TableCellDef.RIGHT;
    defs[i++] = TableCellDef.VERTICAL_LINE;
    do {
      defs[i++] = TableCellDef.CENTERED;
      defs[i++] = TableCellDef.VERTICAL_LINE;
    } while (i < defs.length);

    return defs;
  }

  /**
   * Make a table
   *
   * @param tbl
   *          the table
   * @param root
   *          the root module
   * @param cr
   *          the comparison result
   * @param data
   *          the data
   * @param median
   *          the ranking according to the median rank, or {@code null} if
   *          not needed
   * @throws IOException
   *           if i/o fails
   */
  static final void _makeTable(final Table tbl, final RootModule root,
      final TestComparisonResult cr, final ExperimentSet data,
      final Ranking<Experiment> median) throws IOException {
    final int expCount;
    final DecimalFormat rankFormat;
    double bestRank;
    double rank;
    TestScore ts;
    int i, j;

    rankFormat = ((DecimalFormat) (_TestComparisons.RANK_FORMAT.clone()));

    expCount = data.size();
    bestRank = cr.getRankedScores().get(0).getRank();

    try (TableHeader hrd = tbl.tableHeader()) {
      try (TableHeaderRow thr = hrd.row()) {
        // try (TableHeaderCell tc = thr.cell()) {
        // //
        // }
        try (TableHeaderCell thc = thr.cell(expCount + 1, 1,
            TableCellDef.VERTICAL_LINE, TableCellDef.CENTERED,
            TableCellDef.VERTICAL_LINE)) {
          thc.write("Compare A with B: wins:undecided:losses"); //$NON-NLS-1$
        }
      }
      hrd.tableHorizontalLine();
    }

    try (TableFooter tfd = tbl.tableFooter()) {
      //
    }

    try (TableBody bdy = tbl.tableBody()) {
      try (TableBodyRow trr = bdy.row()) {
        try (TableBodyCell tc = trr.cell(1, 1, TableCellDef.VERTICAL_LINE,
            TableCellDef.CENTERED, TableCellDef.VERTICAL_LINE)) {
          tc.write("\u2193A\u2193");//$NON-NLS-1$
          tc.writeNoneBreakingSpace();
          tc.writeNoneBreakingSpace();
          tc.writeNoneBreakingSpace();
          tc.write("B\u21c9");//$NON-NLS-1$
        }
        for (i = 0; i < expCount; i++) {
          try (TableBodyCell tc = trr.cell()) {
            try (Emphasize emph = tc.emphasize()) {
              tc.macroInvoke(root.getShortName(data.get(i)));
            }
          }
        }
      }
      bdy.tableHorizontalLine();

      for (i = 0; i < expCount; i++) {
        try (TableBodyRow trr = bdy.row()) {
          try (TableBodyCell tc = trr.cell()) {
            try (Emphasize emph = tc.emphasize()) {
              tc.macroInvoke(root.getShortName(data.get(i)));
            }
          }

          for (j = 0; j < expCount; j++) {
            try (TableBodyCell tc = trr.cell()) {
              if (i == j) {
                tc.writeChar('\u2014');
              } else {
                tc.writeInt(cr.getWins(i, j));
                tc.writeChar(':');
                tc.writeInt(cr.getEvens(i, j));
                tc.writeChar(':');
                tc.writeInt(cr.getLosses(i, j));
              }
            }
          }
        }
      }

      bdy.tableHorizontalLine();
      bdy.tableHorizontalLine();

      try (TableBodyRow trr = bdy.row()) {
        try (TableBodyCell tc = trr.cell()) {
          try (Emphasize emph = tc.emphasize()) {
            emph.write("Summary"); //$NON-NLS-1$
          }
        }

        for (i = 0; i < expCount; i++) {
          try (TableBodyCell tc = trr.cell()) {
            ts = cr.getScores().get(i);
            tc.writeInt(ts.getWins());
            tc.writeChar(':');
            tc.writeInt(ts.getEvens());
            tc.writeChar(':');
            tc.writeInt(ts.getLosses());
          }
        }

      }

      try (TableBodyRow trr = bdy.row()) {
        try (TableBodyCell tc = trr.cell()) {
          try (Emphasize emph = tc.emphasize()) {
            emph.write("Rank"); //$NON-NLS-1$
          }
        }

        for (i = 0; i < expCount; i++) {
          try (TableBodyCell tc = trr.cell()) {
            rank = cr.getScores().get(i).getRank();

            if (rank <= bestRank) {
              try (Emphasize emph = tc.emphasize()) {
                emph.writeDouble(rank, rankFormat);
              }
            } else {
              tc.writeDouble(rank, rankFormat);
            }
          }
        }

      }

      if (median != null) {
        bdy.tableHorizontalLine();
        bestRank = median.getBestRank();

        try (TableBodyRow trr = bdy.row()) {
          try (TableBodyCell tc = trr.cell()) {
            try (Emphasize emph = tc.emphasize()) {
              emph.write("Rank of Median Rank "); //$NON-NLS-1$$
            }
          }

          for (i = 0; i < expCount; i++) {
            try (TableBodyCell tc = trr.cell()) {

              rank = median.getRank(data.get(i));
              if (rank <= bestRank) {
                try (Emphasize emph = tc.emphasize()) {
                  emph.writeDouble(rank, rankFormat);
                }
              } else {
                tc.writeDouble(rank, rankFormat);
              }
            }
          }
        }
      }
    }
  }

  /**
   * write the body text describing the tables
   *
   * @param root
   *          the root module
   * @param body
   *          the body
   * @param data
   *          the data
   * @param isSummary
   *          is this a summary write-up or a single write up?
   * @param errorThreshold
   *          the error threshold, or {@link java.lang.Double#NaN NaN} if
   *          not set
   * @param instN
   *          the number of problem instances
   * @param except
   *          the exception table label
   * @param rankingLabel
   *          the label of the ranking section
   * @throws IOException
   *           if i/o fails
   */
  static final void _writeBodyText(final RootModule root,
      final SectionBody body, final ExperimentSet data,
      final boolean isSummary, final double errorThreshold,
      final int instN, final Label except, final Label rankingLabel)
      throws IOException {
    final int expCount, testN;

    expCount = data.size();

    body.write("We compare the algorithm in the row of the table");//$NON-NLS-1$
    if (isSummary) {
      body.writeChar('s');
      if (except != null) {
        body.write(" (except "); //$NON-NLS-1$
        body.reference(except);
        body.writeChar(')');
      }
    }
    body.write(" with the algorithms in the columns for each of the "); //$NON-NLS-1$
    body.writeIntInText(instN, false);
    body.write(" problem instances for which all algorithms have sufficient data. Since there are "); //$NON-NLS-1$
    body.writeIntInText(expCount, false);
    body.write(" different algorithms ("); //$NON-NLS-1$
    body.writeSequence(new ExperimentSequence(root, data, false, true,
        body), ESequenceType.AND, false);
    body.write(") and each is compared with every other one, this makes in total "); //$NON-NLS-1$

    testN = (instN * (expCount - 1));
    try (InlineMath inm = body.inlineMath()) {
      try (MathOp mo1 = inm.mathOp(EMathOp.CMP_EQUALS)) {
        try (MathOpParam p1 = mo1.mathOpParam()) {
          p1.writeInt(testN);
        }
        try (MathOpParam p2 = mo1.mathOpParam()) {

          try (MathOp mo2 = inm.mathOp(EMathOp.MUL)) {
            try (MathOpParam p3 = mo1.mathOpParam()) {
              p3.writeInt(instN);
            }
            try (MathOpParam p4 = mo1.mathOpParam()) {
              try (MathOp mo3 = inm.mathOp(EMathOp.ENCLOSING_PARENTHESES)) {
                try (MathOpParam p5 = mo3.mathOpParam()) {
                  try (MathOp mo4 = p5.mathOp(EMathOp.SUB)) {
                    try (MathOpParam p6 = mo4.mathOpParam()) {
                      p6.writeInt(expCount);
                    }
                    try (MathOpParam p7 = mo4.mathOpParam()) {
                      p7.writeInt(1);
                    }
                  }
                }
              }
            }
          }
        }
      }
    }

    body.write(" comparison for each algorithm"); //$NON-NLS-1$
    if (isSummary) {
      body.write(" in each of the tables belonging to the sub-sections of this section"); //$NON-NLS-1$
    }
    body.write(". We then print the number of significant wins, the number of comparisons that did not detect a significant difference "); //$NON-NLS-1$
    if ((!isSummary) && (errorThreshold == errorThreshold)) {
      body.write("(with error probability below or equal to ");//$NON-NLS-1$
      body.writeDouble(errorThreshold);
      if (data.size() > 2) {
        body.write(" and instance-based Bonferroni correction ");//$NON-NLS-1$
        body.cite(ECitationMode.BY_ID_IN_SENTENCE, DescTests.D1961MCAM);
      }
      body.write(") ");//$NON-NLS-1$
    }
    body.write("and the number of significant losses, separated by colons in the table cells. The bottom lines of the table holds the summed up values of each row in the column of the corresponding algorithms as well as their rank when sorted according to the comparison results (the smaller the better), as discussed in"); //$NON-NLS-1$
    body.reference(rankingLabel);
    body.writeChar('.');
  }

  /** {@inheritDoc} */
  @SuppressWarnings("resource")
  @Override
  protected void writeSectionBody(final SectionBody body,
      final ExperimentSet data) throws IOException {
    final int expCount;
    final TableCellDef[] defs;
    final Label label, dsc;
    final TestComparisonResult cr;
    final TestComparisonProperty prop;
    final Accessor axs;
    final LimitDataCollectionProperty srcprop;
    final Document doc;
    final RootModule root;
    final Ranking<Experiment> ranking;
    double pp1;
    int pp2;

    if (data == null) {
      return;
    }
    expCount = data.size();
    if (expCount <= 0) {
      return;
    }
    prop = this.m_property;
    doc = body.getDocument();
    cr = prop.get(data, doc);

    if (cr == null) {
      return;
    }

    root = this.getRoot();

    axs = prop.getComparedDimension();
    srcprop = prop.getLimitProperty();

    defs = _TestComparisons._makeTableDef(expCount);

    label = this.m_tableLabel.get(data, doc);

    try (Table tbl = body.table(
        label, //
        _TestComparisons.CAPT_1
            + this.m_desc.getName(true)
            + _TestComparisons.CAPT_2
            + String.valueOf(prop.getErrorThreshold())
            + _TestComparisons.CAPT_3
            + axs.getLongName(true)
            + _TestComparisons.CAPT_4
            + ((axs.isObjective()) ? _TestComparisons.CAPT_5A
                : _TestComparisons.CAPT_5B)
            + srcprop.getInTextDescription()
            + ((axs.isObjective()) ? _TestComparisons.CAPT_6A
                : _TestComparisons.CAPT_6B), (expCount > 3), defs)) {

      try (TableCaption capt = tbl.tableCaption()) {

        capt.write(_TestComparisons.CAPT_1);
        this.m_desc.writeName(capt, true);
        capt.write(_TestComparisons.CAPT_2);
        capt.writeDouble(prop.getErrorThreshold());
        capt.write(_TestComparisons.CAPT_3);
        axs.writeLongName(capt, true);
        capt.write(_TestComparisons.CAPT_4);
        if (axs.isObjective()) {
          capt.write(_TestComparisons.CAPT_5A);
        } else {
          capt.write(_TestComparisons.CAPT_5B);
        }
        prop.getLimitProperty().writeInTextDescription(capt);
        if (axs.isObjective()) {
          capt.write(_TestComparisons.CAPT_6A);
        } else {
          capt.write(_TestComparisons.CAPT_6B);
        }

      }

      _TestComparisons._makeTable(tbl, root, cr, data, null);
    }

    body.write("In "); //$NON-NLS-1$
    body.reference(label);
    body.write(" we present the results of "); //$NON-NLS-1$
    this.m_desc.writeName(body, true);
    dsc = this.m_desc.getLabel(data);
    if (dsc != null) {
      body.write(" (see "); //$NON-NLS-1$
      body.reference(dsc);
      body.writeChar(')');
    }
    body.write(_TestComparisons.CAPT_2);
    body.writeDouble(prop.getErrorThreshold());
    body.write(_TestComparisons.CAPT_3);
    axs.writeLongName(body, true);
    body.write(_TestComparisons.CAPT_4);
    if (axs.isObjective()) {
      body.write(_TestComparisons.CAPT_5A);
    } else {
      body.write(_TestComparisons.CAPT_5B);
    }
    srcprop.writeInTextDescription(body);
    if (axs.isObjective()) {
      body.write(_TestComparisons.CAPT_6A);
    } else {
      body.write(_TestComparisons.CAPT_6B);
    }

    body.writeLinebreak();

    if ((((TestComparisonsHolder) (this.getOwner())).hasContent(data))) {
      body.write("The significance level here is set to ");//$NON-NLS-1$
      body.writeDouble(prop.getErrorThreshold());
      body.write(", i.e., differences which may result from random fluctuation with a probability of at least ");//$NON-NLS-1$
      pp1 = (100d * prop.getErrorThreshold());
      pp2 = ((int) (0.5d + pp1));
      if (pp2 == pp1) {
        body.writeInt(pp2);
      } else {
        body.writeDouble(pp1);
      }
      body.write("% will be considered as insignificant."); //$NON-NLS-1$
    } else {
      _TestComparisons._writeBodyText(root, body, data, false,
          prop.getErrorThreshold(), //
          prop.getSharedInstancesProperty().get(data, doc).size(),//
          null,//
          this.m_descRanking.getLabel(data));

    }

    body.write(" From the results shown in "); //$NON-NLS-1$
    body.reference(label);
    ranking = cr.getRankedScores();
    this.summarizeRanks(ranking, body,
        (((axs == Accessor.F) || (axs == Accessor.F_RELATIVE)) ? //
        "produce better results" : //$NON-NLS-1$
            "converge faster")); //$NON-NLS-1$

    // propagate the information to the global ranking
    this.propagateRanking(ranking, data, doc);

    super.writeSectionBody(body, data);
  }
}
