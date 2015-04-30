package org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.eval;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.data.properties.auc.AreaUnderCurve;
import org.logisticPlanning.tsp.evaluation.data.properties.auc.AreaUnderCurveComparator;
import org.logisticPlanning.tsp.evaluation.data.properties.ranking.Rank;
import org.logisticPlanning.tsp.evaluation.data.properties.ranking.Ranking;
import org.logisticPlanning.tsp.evaluation.modules.impl.macros.Macros;
import org.logisticPlanning.tsp.evaluation.modules.spec.DescriptionModule;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.tsp.evaluation.modules.spec.RootModule;
import org.logisticPlanning.utils.collections.basic.BasicMapEntry;
import org.logisticPlanning.utils.document.spec.AbstractTextComplex;
import org.logisticPlanning.utils.document.spec.ECitationMode;
import org.logisticPlanning.utils.document.spec.EDefaultFigureSize;
import org.logisticPlanning.utils.document.spec.EMathName;
import org.logisticPlanning.utils.document.spec.Emphasize;
import org.logisticPlanning.utils.document.spec.Enumeration;
import org.logisticPlanning.utils.document.spec.EnumerationItem;
import org.logisticPlanning.utils.document.spec.Figure;
import org.logisticPlanning.utils.document.spec.FigureBody;
import org.logisticPlanning.utils.document.spec.FigureCaption;
import org.logisticPlanning.utils.document.spec.Graphic;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.InlineMath;
import org.logisticPlanning.utils.document.spec.Label;
import org.logisticPlanning.utils.document.spec.MathName;
import org.logisticPlanning.utils.document.spec.SectionBody;
import org.logisticPlanning.utils.document.spec.SectionTitle;
import org.logisticPlanning.utils.document.spec.bib.BibArticle;
import org.logisticPlanning.utils.document.spec.bib.BibAuthor;
import org.logisticPlanning.utils.document.spec.bib.BibAuthors;
import org.logisticPlanning.utils.document.spec.bib.BibDate;
import org.logisticPlanning.utils.document.spec.bib.EBibMonth;
import org.logisticPlanning.utils.graphics.chart.spec.ELegendType;
import org.logisticPlanning.utils.graphics.chart.spec.ELineMode;
import org.logisticPlanning.utils.graphics.chart.spec.Line2D;
import org.logisticPlanning.utils.graphics.chart.spec.LineChart2D;
import org.logisticPlanning.utils.graphics.chart.spec.range.AxisRange2DDef;
import org.logisticPlanning.utils.graphics.chart.spec.range.FixedAxisEnd;
import org.logisticPlanning.utils.math.data.collection.ArrayDataCollectionView;
import org.logisticPlanning.utils.math.data.collection.IDataCollection;
import org.logisticPlanning.utils.math.data.iteration.DataCollectionIterator2D;

/**
 * A description of the Area Under the Curve (AUC), which we use for
 * comparing graphics. This measure stems from Machine Learning&nbsp;[<a
 * href="#cite_F2006AITRA" style="font-weight:bold">1</a>, <a
 * href="#cite_B1997TUOTAUTRCITEOMLA" style="font-weight:bold">2</a>], but
 * may be useful here too, with some modifications. <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_F2006AITRA" /><a
 * href="http://home.comcast.net/~tom.fawcett/public_html/index.html">Tom
 * Fawcett</a>: <span style="font-weight:bold">&ldquo;An Introduction to
 * ROC Analysis,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Pattern Recognition
 * Letters</span> 27(8):861&ndash;874, June&nbsp;2006; published by
 * Amsterdam, The Netherlands: Elsevier Science Publishers B.V.
 * and&nbsp;Amsterdam, The Netherlands: North-Holland Scientific Publishers
 * Ltd.. doi:&nbsp;<a href="http://dx.doi.org/10.1016/j.patrec.2005.10.010"
 * >10.1016/j.patrec.2005.10.010</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/01678655">0167-8655</a></div></li>
 * <li><div><span id="cite_B1997TUOTAUTRCITEOMLA" /><a
 * href="http://itee.uq.edu.au/~bradley/">Andrew P. Bradley</a>: <span
 * style="font-weight:bold">&ldquo;The Use of the Area Under the ROC Curve
 * in the Evaluation of Machine Learning Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Pattern
 * Recognition</span> 30(7):1145&ndash;1159, July&nbsp;1997; published by
 * Essex, UK: Elsevier Science Publishers B.V. and&nbsp;Oxford, UK:
 * Pergamon Press. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1016/S0031-3203(96)00142-2"
 * >10.1016/S0031-3203(96) 00142-2</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/00313203">0031-3203</a></div></li>
 * </ol>
 */
public final class DescAUC extends DescriptionModule {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * the article&nbsp;[<a href="#cite_F2006AITRA"
   * style="font-weight:bold">1</a>]
   */
  private static final BibArticle F2006AITRA = new BibArticle(//
      new BibAuthors(//
          new BibAuthor("Tom", "Fawcett")), //$NON-NLS-1$//$NON-NLS-2$
      "An Introduction to ROC Analysis",//$NON-NLS-1$
      new BibDate(2006, EBibMonth.JUNE),//
      "Pattern Recognition Letters",//$NON-NLS-1$
      "27", "8", //$NON-NLS-1$//$NON-NLS-2$
      "861", "874", //$NON-NLS-1$//$NON-NLS-2$
      null,//
      "10.1016/j.patrec.2005.10.010"//$NON-NLS-1$
  );
  /**
   * the technical report&nbsp;[<a href="#cite_B1997TUOTAUTRCITEOMLA"
   * style="font-weight:bold">2</a>]
   */
  private static final BibArticle B1997TUOTAUTRCITEOMLA = new BibArticle(
      //
      new BibAuthors(//
          new BibAuthor("Andrew P.", "Bradley")), //$NON-NLS-1$//$NON-NLS-2$
      "The Use of the Area Under the ROC Curve in the Evaluation of Machine Learning Algorithms",//$NON-NLS-1$
      new BibDate(1997, EBibMonth.JULY),//
      "Pattern Recognition",//$NON-NLS-1$
      "30", "7", //$NON-NLS-1$//$NON-NLS-2$
      "1145", "1159", //$NON-NLS-1$//$NON-NLS-2$
      null,//
      "10.1016/S0031-3203(96)00142-2"//$NON-NLS-1$
  );

  /** the ranking description */
  private final DescRanking m_rank;

  /**
   * create!
   * 
   * @param owner
   *          the macro's owner
   */
  DescAUC(final Module owner) {
    super("auc", owner, false); //$NON-NLS-1$

    this.m_rank = this.getRoot().findInstance(DescRanking.class);
    this.addDependency(this.m_rank);
  }

  /** {@inheritDoc} */
  @Override
  protected void initialize(final Header header, final ExperimentSet data)
      throws IOException {//
    super.initialize(header, data);
    Macros.AUC.define(header);
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSectionTitle(final SectionTitle title,
      final ExperimentSet data) throws IOException {
    title.write("Area Under the Curve"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @SuppressWarnings("unchecked")
  @Override
  protected void writeSectionBody(final SectionBody body,
      final ExperimentSet data) throws IOException {
    final IDataCollection A, B, C, D;
    final AreaUnderCurve aA, aB, aC, aD;
    final Map.Entry<Character, AreaUnderCurve> eA, eB, eC, eD;
    final Label label;
    final Ranking<Character> ranks;
    final RootModule root;
    DescERT ert;
    DescECDF ecdf;
    Rank<Character> r;
    LineChart2D lc;

    body.write(//
    "Many of the performance measures used in this work are actually of graphical nature, i.e., commonly represented as charts or line diagrams to be evaluated by a human researcher. The researcher will, for instance, analyze whether one curve in a diagram representing algorithm "); //$NON-NLS-1$
    DescAUC.__meth(body, 'A');
    body.write(//
    " tends to be lower than the curve corresponding to an algorithm "); //$NON-NLS-1$
    DescAUC.__meth(body, 'B');
    body.write(//
    ". If that is the case and smaller values indicate better performance"); //$NON-NLS-1$

    root = this.getRoot();
    ert = root.findInstance(DescERT.class);
    if ((ert != null) && (!(ert.isActive()))) {
      ert = null;
    }

    ecdf = root.findInstance(DescECDF.class);
    if ((ecdf != null) && (!(ecdf.isActive()))) {
      ecdf = null;
    }

    if (ert != null) {
      body.write(//
      " (as it is true in case of, e.g., the "); //$NON-NLS-1$
      body.macroInvoke(Macros.ERT);
      body.write(//
      " discussed in "); //$NON-NLS-1$
      body.reference(ert.getLabel(data));
      body.writeChar(')');
    }
    body.write(", then she would say "); //$NON-NLS-1$
    DescAUC.__meth(body, 'A');
    body.write(" is better than "); //$NON-NLS-1$
    DescAUC.__meth(body, 'B');

    body.write(//
    ". If larger values of the performance metric are better"); //$NON-NLS-1$
    if (ecdf != null) {
      body.write(//
      ", which is the case for the "); //$NON-NLS-1$
      body.macroInvoke(Macros.ECDF);
      body.write(//
      " discussed in "); //$NON-NLS-1$
      body.reference(ecdf.getLabel(data));
      body.write(", for instance"); //$NON-NLS-1$
    }
    body.write(//
    ", then it would be the other way around. An automated evaluation system can support the researcher in discovering such trends automatically. One way to test which curve tends to higher and which tends to be lower is well-known in the field of Machine Learning: The Area Under the Curve ("); //$NON-NLS-1$
    body.macroInvoke(Macros.AUC);
    body.write(") "); //$NON-NLS-1$
    body.cite(ECitationMode.BY_ID_IN_SENTENCE, DescAUC.F2006AITRA,
        DescAUC.B1997TUOTAUTRCITEOMLA);
    body.writeChar('.');

    A = new ArrayDataCollectionView(new double[] { 0d,
        Double.POSITIVE_INFINITY,//
        2d, 5d,//
        3d, 3d,//
        5d, 0d },//
        4, 2);
    B = new ArrayDataCollectionView(new double[] { 0d,
        Double.POSITIVE_INFINITY,//
        2d, Double.POSITIVE_INFINITY,//
        2d, 4d,//
        5d, 3d },//
        4, 2);
    C = new ArrayDataCollectionView(new double[] { 0d, 5d,//
        3d, 2d,//
        5d, 2d },//
        3, 2);
    D = new ArrayDataCollectionView(new double[] { 0d, 4d,//
        4d, 3d,//
        5d, 0d },//
        3, 2);

    try (Figure fig = body.figure(Label.AUTO_LABEL, false)) {
      try (FigureCaption cap = fig.figureCaption()) {
        cap.write("An example set of curves for computing the "); //$NON-NLS-1$
        cap.macroInvoke(Macros.AUC);
        cap.writeChar('.');
      }
      try (FigureBody fb = fig.figureBody(//
          new URI(Module.GRAPHICS_FOLDER + "/aucExample"), //$NON-NLS-1$
          body.getDocument().getDimensions().getFigureDimensionsMM(//
              EDefaultFigureSize.TWO_IN_A_ROW))) {
        try (Graphic graph = fb.graphic()) {
          lc = body.getDocument().getOwner().getDriver().//
              getChartDriver().createLineChart2D(//
                  new AxisRange2DDef(new FixedAxisEnd(0d),//
                      new FixedAxisEnd(5d), new FixedAxisEnd(0),//
                      new FixedAxisEnd(6)));
          lc.setLegendType(ELegendType.PUT_LEGEND);
          lc.setAxisTitleX("x-axis (e.g., time)"); //$NON-NLS-1$
          lc.setAxisTitleY("y-axis (performance)"); //$NON-NLS-1$
          lc.addLine(new Line2D("A", null, A, null, ELineMode.DIRECT));//$NON-NLS-1$
          lc.addLine(new Line2D("B", null, B, null, ELineMode.DIRECT));//$NON-NLS-1$
          lc.addLine(new Line2D("C", null, C, null, ELineMode.DIRECT));//$NON-NLS-1$
          lc.addLine(new Line2D("D", null, D, null, ELineMode.DIRECT));//$NON-NLS-1$
          lc.paint(graph);
          lc = null;
        }
      } catch (final URISyntaxException use) {
        throw new IOException(use);
      }
      label = fig.getLabel();
    }

    body.writeLinebreak();
    body.write("Different from the "); //$NON-NLS-1$
    body.macroInvoke(Macros.AUC);
    body.write(//
    " used in Machine Learning, the areas under the performance curve do not represent a classifier's utility. We use them only to determine if one graph tends to be larger than another one. Furthermore, different from Machine Learning, we have to deal with two major problems when determining the areas:"); //$NON-NLS-1$

    try (Enumeration en = body.enumeration()) {
      try (EnumerationItem ei = en.item()) {
        ei.write(//
        "Sometimes, a curve may go to infinity."); //$NON-NLS-1$        
        if (ert != null) {
          ei.write(//
          " The "); //$NON-NLS-1$
          ei.macroInvoke(Macros.ERT);
          ei.write(//
          " for a solution quality threshold, which was never reached by any run in a given benchmark scenario, is infinite, for example."); //$NON-NLS-1$
        }
      }

      try (EnumerationItem ei = en.item()) {
        ei.write(//
        "Some curves may cover a longer range on the x-axis than others, making the comparison unfair. If one run took significantly longer than another one, it will cover a longer range on a time axis, for instance."); //$NON-NLS-1$
      }
    }

    body.write(//
    "Luckily, we are not interested in the precise areas, but merely in which areas tend to be bigger. Thus, we can solve the first issue by dividing the curve into different segments:"); //$NON-NLS-1$

    try (Enumeration en = body.enumeration()) {
      try (EnumerationItem ei = en.item()) {
        ei.write(//
        "A segment where the performance measure (y-values) are finite and thus the areas under the curve are finite too. Such areas can be compared directly and in an ideal comparison situation, this segment encompasses the whole curve.");//$NON-NLS-1$
      }

      try (EnumerationItem ei = en.item()) {
        ei.write(//
        "An segment where the y-coordinates go to ");//$NON-NLS-1$
        ei.writeDouble(Double.POSITIVE_INFINITY);
        ei.write(//
        ". Here, instead of computing the area under the curve (which would be infinite), we only compute the range of the x-axis under the infinite area.");//$NON-NLS-1$
      }

      try (EnumerationItem ei = en.item()) {
        ei.write(//
        "An segment  where the y-coordinates go to ");//$NON-NLS-1$
        ei.writeDouble(Double.NEGATIVE_INFINITY);
        ei.write(//
        ". This case is treated analogously.");//$NON-NLS-1$
      }

      try (EnumerationItem ei = en.item()) {
        ei.write(//
        "A segment where the y-coordinates are undefined (");//$NON-NLS-1$
        ei.writeDouble(Double.NaN);
        ei.write(//
        "). This case is treated analogously.");//$NON-NLS-1$
      }
    }

    body.write("An ");//$NON-NLS-1$
    body.macroInvoke(Macros.AUC);
    body.write(//
    " which is positive infinite over three units along the x-axis, negative infinite over two units along the x-axis, undefined over four units along the x-axis, and has a area of a size of ten square units where both, the x and y values are finite, is denoted as ");//$NON-NLS-1$
    new AreaUnderCurve(10, 2, 3, 4).write(body);

    body.write(//
    ". When comparing two areas, we first compare the infinite and undefined segments. The area with the larger undefined segment loses each comparison regardless. For infinite segments, the default comparison order of the performance measure holds: If "); //$NON-NLS-1$
    try (Emphasize em = body.emphasize()) {
      em.write("smaller is better");//$NON-NLS-1$
    }
    body.write(//
    " holds, then the area with the larger negative-infinite or smaller positive-infinite area wins. A comparison is undecided if one has a smaller negative-infinite but larger positive-infinite area. Finally, if all infinite and/or undefined segments of two curves are the same (ideally "); //$NON-NLS-1$
    body.writeInt(0);
    body.write(//
    " the finite areas are compared directly."); //$NON-NLS-1$

    body.writeLinebreak();
    body.write(//
    "In order to deal with different ranges covered on the x-axis, we can either only look at those parts where data exists for all curves or, alternatively, extend the shorter curves to the x-coordinate of the end of the longest curve, using either its last value or infinity, where appropriate."); //$NON-NLS-1$

    body.writeLinebreak();
    body.write(//
    "In any case, the "); //$NON-NLS-1$
    body.macroInvoke(Macros.AUC);
    body.write(//
    " defined like this is, at most, an indicator \u2012 it is by no means an exact measure. It therefore should not be considered as a singular, absolute value. Instead, we will aggregates (medians) of "); //$NON-NLS-1$

    try (Emphasize em = body.emphasize()) {
      em.write("rankings");//$NON-NLS-1$
    }

    body.write(" as introduced in "); //$NON-NLS-1$
    body.reference(this.m_rank.getLabel(data));

    body.write(" of the "); //$NON-NLS-1$
    body.macroInvoke(Macros.AUC);
    body.write(//
    "s over many diagrams, in order to extract some more robust trends. Still, even such trends cannot replace a proper manual analysis and understanding of the charts and should just be viewed as one additional hint or indicator."); //$NON-NLS-1$

    body.writeLinebreak();
    body.write("In the example "); //$NON-NLS-1$
    body.reference(label);
    body.write(" we plot four data sets, "); //$NON-NLS-1$
    DescAUC.__meth(body, 'A');
    body.write(", "); //$NON-NLS-1$
    DescAUC.__meth(body, 'B');
    body.write(", "); //$NON-NLS-1$
    DescAUC.__meth(body, 'C');
    body.write(", and "); //$NON-NLS-1$
    DescAUC.__meth(body, 'D');

    aA = new AreaUnderCurve(new DataCollectionIterator2D(A));
    aB = new AreaUnderCurve(new DataCollectionIterator2D(B));
    aC = new AreaUnderCurve(new DataCollectionIterator2D(C));
    aD = new AreaUnderCurve(new DataCollectionIterator2D(D));

    body.write(" with the corresponding areas "); //$NON-NLS-1$

    aA.write(body);
    body.write(", "); //$NON-NLS-1$
    aB.write(body);
    body.write(", "); //$NON-NLS-1$
    aC.write(body);
    body.write(", and "); //$NON-NLS-1$
    aD.write(body);

    body.write(//
    ". If smaller performance measure (y) values would be better, these areas would be ranked as "); //$NON-NLS-1$

    eA = new BasicMapEntry<>(Character.valueOf('A'), aA);
    eB = new BasicMapEntry<>(Character.valueOf('B'), aB);
    eC = new BasicMapEntry<>(Character.valueOf('C'), aC);
    eD = new BasicMapEntry<>(Character.valueOf('D'), aD);
    ranks = Ranking.rank(AreaUnderCurveComparator.SMALLER_IS_BETTER,//
        new Map.Entry[] { eA, eB, eC, eD });

    r = ranks.get(0);
    DescAUC.__meth(body, r.getKey().charValue());
    body.write(" (rank ");//$NON-NLS-1$
    body.writeDouble(r.getRank());
    body.write("), ");//$NON-NLS-1$

    r = ranks.get(1);
    DescAUC.__meth(body, r.getKey().charValue());
    body.write(" (");//$NON-NLS-1$
    body.writeDouble(r.getRank());
    body.write("), ");//$NON-NLS-1$

    r = ranks.get(2);
    DescAUC.__meth(body, r.getKey().charValue());
    body.write(" (");//$NON-NLS-1$
    body.writeDouble(r.getRank());
    body.write("), and ");//$NON-NLS-1$

    r = ranks.get(3);
    DescAUC.__meth(body, r.getKey().charValue());
    body.write(" (");//$NON-NLS-1$
    body.writeDouble(r.getRank());
    body.write(").");//$NON-NLS-1$

    body.writeNoneBreakingSpace();

    body.write("In summary, using the "); //$NON-NLS-1$
    body.macroInvoke(Macros.AUC);
    body.write(//
    " is an automated way of comparing curves. It easily covers the trivial cases where one curve is always below or above another one (as in those cases, its area is necessarily smaller or larger), but also can provide at least some approximate insight in cases where curves cross each other, as it is the case in our example "); //$NON-NLS-1$
    body.reference(label);
    body.writeChar('.');

    super.writeSectionBody(body, data);
  }

  /**
   * write a method name
   * 
   * @param txt
   *          the destination
   * @param m
   *          the method name
   * @throws IOException
   *           if i/o fails
   */
  private static final void __meth(final AbstractTextComplex txt,
      final char m) throws IOException {
    try (InlineMath im = txt.inlineMath()) {
      try (MathName mn = im.mathName(EMathName.SCALAR)) {
        mn.writeChar(m);
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public int compareTo(final Module other) {
    if (other instanceof DescERT) {
      return 1;
    }
    if (other instanceof DescECDF) {
      return 1;
    }
    if (other instanceof DescRanking) {
      return (-1);
    }
    return super.compareTo(other);
  }
}
