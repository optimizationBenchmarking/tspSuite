package org.logisticPlanning.tsp.evaluation.modules.impl.single.litComp;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.data.properties.litComp.ESingleComparison;
import org.logisticPlanning.tsp.evaluation.data.properties.litComp.LiteratureComparisonPoint;
import org.logisticPlanning.tsp.evaluation.data.properties.litComp.LiteratureComparisonPointSet;
import org.logisticPlanning.tsp.evaluation.data.properties.litComp.LiteratureComparisonProperty;
import org.logisticPlanning.tsp.evaluation.data.properties.litComp.LiteratureComparisonResult;
import org.logisticPlanning.tsp.evaluation.data.properties.litComp.LiteratureComparisonSet;
import org.logisticPlanning.tsp.evaluation.data.properties.litComp.LiteratureResultPoint;
import org.logisticPlanning.tsp.evaluation.data.properties.litComp.LiteratureResultPointSet;
import org.logisticPlanning.tsp.evaluation.data.properties.litComp.ResPoint;
import org.logisticPlanning.tsp.evaluation.modules.ModuleUtils;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.tsp.evaluation.modules.spec.RootModule;
import org.logisticPlanning.tsp.evaluation.modules.spec.SingleModule;
import org.logisticPlanning.utils.document.spec.AbstractTextComplex;
import org.logisticPlanning.utils.document.spec.ESequenceType;
import org.logisticPlanning.utils.document.spec.Emphasize;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.Label;
import org.logisticPlanning.utils.document.spec.Section;
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
 * This evaluator will compare the results of a run set with literature.
 */
public class LiteratureComparison extends SingleModule {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the table cell definition */
  private static final TableCellDef[] TABLE_DEF = {
    TableCellDef.VERTICAL_LINE, TableCellDef.RIGHT,
    TableCellDef.VERTICAL_LINE, TableCellDef.LEFT,
    TableCellDef.VERTICAL_LINE, TableCellDef.LEFT,
    TableCellDef.VERTICAL_LINE, TableCellDef.LEFT,
    TableCellDef.VERTICAL_LINE };

  /** the formats */
  private static final NumberFormat[] FORMATS;
  static {

    FORMATS = new NumberFormat[Accessor.ACCESSORS.size()];

    LiteratureComparison.FORMATS[Accessor.DE.ordinal()] = //
        ModuleUtils.createDecimalFormat("0"); //$NON-NLS-1$
    LiteratureComparison.FORMATS[Accessor.FE.ordinal()] = //
        LiteratureComparison.FORMATS[Accessor.DE.ordinal()];
    LiteratureComparison.FORMATS[Accessor.RUNTIME.ordinal()] = //
        ModuleUtils.createDecimalFormat("0.00E0"); //$NON-NLS-1$
    LiteratureComparison.FORMATS[Accessor.F.ordinal()] = //
        ModuleUtils.createDecimalFormat("0.0"); //$NON-NLS-1$
    LiteratureComparison.FORMATS[Accessor.F_RELATIVE.ordinal()] = //
        LiteratureComparison.FORMATS[Accessor.RUNTIME.ordinal()];
    LiteratureComparison.FORMATS[Accessor.NORMALIZED_RUNTIME.ordinal()] = //
        LiteratureComparison.FORMATS[Accessor.RUNTIME.ordinal()];
  }

  /**
   * create!
   *
   * @param owner
   *          the macro's owner
   * @param isActive
   *          should this module be active?
   */
  public LiteratureComparison(final Module owner, final boolean isActive) {
    super("literatureComparison", owner, isActive); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSectionTitle(final SectionTitle title,
      final Experiment data) throws IOException {
    title.write("Comparison with Results from Literature"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected final void initialize(final Header header,
      final Experiment data) throws IOException {//
    super.initialize(header, data);
    LiteratureComparisonProperty.INSTANCE.initialize(header);
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSectionBody(final SectionBody body,
      final Experiment data) throws IOException {
    final LiteratureComparisonResult rc;
    final Logger log;
    final RootModule root;
    final ArrayList<ResPoint> list;
    final NumberFormat[] formats;
    ResPoint p;
    LiteratureResultPointSet lrps;
    LiteratureResultPoint lp;
    Instance cur, last;
    String s;

    formats = new NumberFormat[Accessor.ACCESSORS.size()];

    rc = LiteratureComparisonProperty.INSTANCE.get(data,
        body.getDocument());
    if ((rc == null) || (rc.size() <= 0)) {
      s = ("No comparison data found for run set " + data.name() + '!');//$NON-NLS-1$
      body.write(s);
      log = this.getLogger();
      if ((log != null) && (log.isLoggable(Level.WARNING))) {
        log.warning(s);
      }
      return;
    }
    root = this.getRoot();

    body.write("In this section we compare the ");//$NON-NLS-1$
    body.macroInvoke(root.getLongName(data));
    body.write(" (");//$NON-NLS-1$
    body.macroInvoke(root.getShortName(data));
    body.write(") to a couple of algorithms and results from literature, namely to ");//$NON-NLS-1$
    body.writeSequence(new _CmpAlgoSeq(rc, body), ESequenceType.AND, false);
    body.writeChar('.');

    this.__writeComparisonResult(rc, data, body);
    list = new ArrayList<>(3);

    for (final LiteratureComparisonPointSet lcps : rc) {
      try (Section sec = body.section(null)) {
        lrps = lcps.getLiteratureResultPointSet();
        try (SectionTitle tit = sec.sectionTitle()) {
          tit.write("Comparison with ");//$NON-NLS-1$
          tit.write(lrps.getShortName());
        }

        try (SectionBody bdy = sec.sectionBody()) {
          lrps.writeDescription(bdy);
          this.__writeComparisonResult(lcps, data, bdy);

          try (Table tbl = bdy.table(Label.AUTO_LABEL, //
              "Comparison of " + root.getShortName(data).getPlaceholder() + //$NON-NLS-1$
              " with " + lrps.getShortName(),//$NON-NLS-1$
              true, LiteratureComparison.TABLE_DEF)) {
            try (TableCaption cap = tbl.tableCaption()) {
              cap.write("Comparison of ");//$NON-NLS-1$
              cap.macroInvoke(root.getShortName(data));
              cap.write(" with ");//$NON-NLS-1$
              cap.write(lrps.getShortName());
            }

            try (TableHeader hdr = tbl.tableHeader()) {
              try (TableHeaderRow r = hdr.row()) {
                try (TableHeaderCell c = r.cell()) {
                  c.write("Instance");//$NON-NLS-1$
                }
                try (TableHeaderCell c = r.cell()) {
                  c.write(lrps.getShortName());
                }
                try (TableHeaderCell c = r.cell()) {
                  c.macroInvoke(root.getShortName(data));
                }
                try (TableHeaderCell c = r.cell()) {
                  c.write("Result");//$NON-NLS-1$
                }
              }
            }

            try (TableFooter ftr = tbl.tableFooter()) {//
            }

            try (TableBody tbdy = tbl.tableBody()) {
              cur = null;
              for (final LiteratureComparisonPoint point : lcps) {
                lp = point.getLiteratureResultPoint();
                last = cur;
                cur = lp.getInstance();
                if ((last != cur) && (last != null)) {
                  tbdy.tableHorizontalLine();
                }
                try (TableBodyRow tbr = tbdy.row()) {
                  try (TableBodyCell c = tbr.cell()) {
                    if (last != cur) {
                      c.write(cur.name());
                    }
                  }

                  try (TableBodyCell c = tbr.cell()) {
                    LiteratureComparison.__writePoint(c, lp.getData(), lp,
                        formats);
                  }

                  p = point.getQualityLowerBound();
                  if (p != null) {
                    list.add(p);
                  }
                  p = point.getTimeLowerBound();
                  if (p != null) {
                    list.add(p);
                  }
                  p = point.getTimeUpperBound();
                  if (p != null) {
                    list.add(p);
                  }

                  try (TableBodyCell c = tbr.cell()) {
                    if (!(list.isEmpty())) {
                      p = list.remove(0);
                      LiteratureComparison.__writePoint(c, p, lp, formats);
                    }
                  }

                  try (TableBodyCell c = tbr.cell()) {
                    c.write(point.getComparisonResult().getShortName());
                  }
                }

                while (!(list.isEmpty())) {
                  try (TableBodyRow tbr = tbdy.row()) {
                    try (TableBodyCell c = tbr.cell()) {//
                    }
                    try (TableBodyCell c = tbr.cell()) {//
                    }
                    try (TableBodyCell c = tbr.cell()) {//
                      LiteratureComparison.__writePoint(c, list.remove(0),
                          lp, formats);
                    }
                    try (TableBodyCell c = tbr.cell()) {//
                    }
                  }
                }

              }
            }
          }

        }
      }
    }

    super.writeSectionBody(body, data);
  }

  /**
   * write a point
   *
   * @param dest
   *          the dest
   * @param p
   *          the point
   * @param lit
   *          the literature point
   * @param formats
   *          the formats
   * @throws IOException
   *           if i/o fails
   */
  private static final void __writePoint(final AbstractTextComplex dest,
      final ResPoint p, final LiteratureResultPoint lit,
      final NumberFormat[] formats) throws IOException {
    Accessor dim;
    int d;

    dest.write(lit.getResultStatistic().getShortName());
    dest.writeChar(' ');

    dim = lit.getResultDimension();
    d = dim.ordinal();
    if (formats[d] == null) {
      if (LiteratureComparison.FORMATS[d] != null) {
        formats[d] = ((NumberFormat) (LiteratureComparison.FORMATS[d]
            .clone()));
      }
    }
    dim.writeValue(p.getQuality(), dest, formats[d]);
    dest.write(" after "); //$NON-NLS-1$

    dim = lit.getTimeDimension();
    d = dim.ordinal();
    if (formats[d] == null) {
      if (LiteratureComparison.FORMATS[d] != null) {
        formats[d] = ((NumberFormat) (LiteratureComparison.FORMATS[d]
            .clone()));
      }
    }
    dim.writeValue(p.getTime(), dest, formats[d]);
  }

  /**
   * Write a comparison
   *
   * @param set
   *          the result set
   * @param data
   *          the experiment
   * @param text
   *          the text
   * @throws IOException
   *           if io fails
   */
  private final void __writeComparisonResult(
      final LiteratureComparisonSet<?> set, final Experiment data,
      final SectionBody text) throws IOException {
    final RootModule root;
    final LiteratureComparisonPointSet lit;
    final ESingleComparison res, res2;
    int a, b;

    root = this.getRoot();

    if (set instanceof LiteratureComparisonPointSet) {
      lit = ((LiteratureComparisonPointSet) set);
    } else {
      lit = null;
    }

    text.writeLinebreak();

    if (lit == null) {
      text.write("Overall, compared to literature");//$NON-NLS-1$
    } else {
      text.write("Compared to the ");//$NON-NLS-1$
      text.write(lit.getLiteratureResultPointSet().getShortName());
    }
    text.write(" the ");//$NON-NLS-1$
    text.macroInvoke(root.getShortName(data));
    text.write(" tends to be ");//$NON-NLS-1$
    res = set.getComparisonResult();
    try (Emphasize em = text.emphasize()) {
      em.write(res.getShortName());
    }
    text.write(", which means that it "); //$NON-NLS-1$
    text.write(res.getLongName(true));
    text.write(". From the ");//$NON-NLS-1$
    text.writeLong(set.size());
    text.write(" comparison elements, "); //$NON-NLS-1$
    text.writeLong(set.countResult(res));
    text.write(" indicate "); //$NON-NLS-1$
    try (Emphasize em = text.emphasize()) {
      em.write(res.getShortName());
    }
    a = set.countResult(res.getOther());
    if (a > 0) {
      text.write(" and "); //$NON-NLS-1$
      text.writeLong(a);
      text.writeChar(' ');
      try (Emphasize em = text.emphasize()) {
        em.write(res.getOther().getShortName());
      }
    }

    switch (res) {
      case BETTER:
      case MAYBE_BETTER: {
        res2 = ESingleComparison.WORSE;
        break;
      }
      case WORSE:
      case MAYBE_WORSE: {
        res2 = ESingleComparison.BETTER;
        break;
      }
      default: {
        res2 = null;
      }
    }

    if (res2 != null) {
      a = set.countResult(res2);
      b = set.countResult(res2.getOther());

      text.write(" while ");//$NON-NLS-1$
      if ((a + b) <= 0) {
        text.write(" no comparison indicated ");//$NON-NLS-1$
        try (Emphasize em = text.emphasize()) {
          em.write(res2.getShortName());
        }
        text.write(" or "); //$NON-NLS-1$
        try (Emphasize em = text.emphasize()) {
          em.write(res2.getOther().getShortName());
        }
      } else {

        if (a > 0) {
          text.writeLong(a);
          text.write(" comparisons indicated ");//$NON-NLS-1$
          try (Emphasize em = text.emphasize()) {
            em.write(res2.getShortName());
          }
        }

        if (b > 0) {
          if (a > 0) {
            text.write(" and ");//$NON-NLS-1$
          }
          text.writeLong(b);
          text.write(" indicated ");//$NON-NLS-1$
          try (Emphasize em = text.emphasize()) {
            em.write(res2.getOther().getShortName());
          }
        }
      }
    }

    text.writeChar('.');
  }

}
