package org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.tests;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.eval.DescQualityMeasures;
import org.logisticPlanning.tsp.evaluation.modules.impl.macros.Macros;
import org.logisticPlanning.tsp.evaluation.modules.spec.DescriptionModule;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.utils.document.spec.ECitationMode;
import org.logisticPlanning.utils.document.spec.EMathOp;
import org.logisticPlanning.utils.document.spec.Enumeration;
import org.logisticPlanning.utils.document.spec.EnumerationItem;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.InlineMath;
import org.logisticPlanning.utils.document.spec.Itemization;
import org.logisticPlanning.utils.document.spec.ItemizationItem;
import org.logisticPlanning.utils.document.spec.Label;
import org.logisticPlanning.utils.document.spec.MacroInvocation;
import org.logisticPlanning.utils.document.spec.MacroParameter;
import org.logisticPlanning.utils.document.spec.MathOp;
import org.logisticPlanning.utils.document.spec.MathOpParam;
import org.logisticPlanning.utils.document.spec.NormalText;
import org.logisticPlanning.utils.document.spec.SectionBody;
import org.logisticPlanning.utils.document.spec.SectionTitle;
import org.logisticPlanning.utils.document.spec.bib.BibArticle;
import org.logisticPlanning.utils.document.spec.bib.BibAuthor;
import org.logisticPlanning.utils.document.spec.bib.BibAuthors;
import org.logisticPlanning.utils.document.spec.bib.BibBook;
import org.logisticPlanning.utils.document.spec.bib.BibDate;
import org.logisticPlanning.utils.document.spec.bib.BibWebsite;
import org.logisticPlanning.utils.document.spec.bib.EBibMonth;
import org.logisticPlanning.utils.math.statistics.tests.impl.TwoTailedMannWhitneyUTest;
import org.logisticPlanning.utils.math.statistics.tests.spec.MultivariateTest;

/**
 * A description for statistical tests.<h2>References</h2>
 * <ol>
 * <li><div><span id="cite_F1925SMFRW" />Sir Ronald Aylmer Fisher: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Statistical
 * Methods for Research Workers,&rdquo;</span> 1925&ndash;1970, London, UK:
 * Oliver and Boyd. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0050021702">0-05-002170-2</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780050021705">978-0-05-002170
 * -5</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/301525908">301525908</a>, <a
 * href="https://www.worldcat.org/oclc/119565">119565</a>, and&nbsp;<a
 * href="https://www.worldcat.org/oclc/264637064">264637064</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=38F0HQAACAAJ">38F0HQAACAAJ</a>,
 * <a
 * href="http://books.google.com/books?id=LGhkIAAACAAJ">LGhkIAAACAAJ</a>,
 * <a
 * href="http://books.google.com/books?id=YUNBAAAAIAAJ">YUNBAAAAIAAJ</a>,
 * <a
 * href="http://books.google.com/books?id=eUz-GQAACAAJ">eUz-GQAACAAJ</a>,
 * <a
 * href="http://books.google.com/books?id=skNBAAAAIAAJ">skNBAAAAIAAJ</a>,
 * <a
 * href="http://books.google.com/books?id=1FptAAAAMAAJ">1FptAAAAMAAJ</a>,
 * <a
 * href="http://books.google.com/books?id=GmNAAAAAIAAJ">GmNAAAAAIAAJ</a>,
 * <a
 * href="http://books.google.com/books?id=4MF0HQAACAAJ">4MF0HQAACAAJ</a>,
 * <a
 * href="http://books.google.com/books?id=g8UCGwAACAAJ">g8UCGwAACAAJ</a>,
 * and&nbsp;<a
 * href="http://books.google.com/books?id=GfAHIgAACAAJ">GfAHIgAACAAJ</a>.
 * <div>link: [<a
 * href="http://psychclassics.yorku.ca/Fisher/Methods/">1</a>]</div></div></li>
 * <li><div><span id="cite_L1975NSMBOR" />Erich Leo Lehmann: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Nonparametrics:
 * Statistical Methods Based on Ranks,&rdquo;</span> June&nbsp;1975,
 * Maidenhead, England, UK: McGraw-Hill Ltd.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0070370737">0070370737</a>, <a
 * href="https://www.worldcat.org/isbn/0387352120">0387352120</a>, <a
 * href="https://www.worldcat.org/isbn/9780070370739">978-0070370739</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780387352121">9780387352121</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=Wy8nAQAAIAAJ"
 * >Wy8nAQAAIAAJ</a></div></li>
 * <li><div><span id="cite_SC1988NSFTBS" />Sidney Siegel and&nbsp;N. John
 * Castellan Jr.: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Nonparametric
 * Statistics for The Behavioral Sciences,&rdquo;</span> 1956&ndash;1988,
 * Humanities/Social Sciences/Languages, New York, NY, USA: McGraw-Hill.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0070573573">0-07-057357-3</a>, <a
 * href="https://www.worldcat.org/isbn/070573434X">070573434X</a>, <a
 * href="https://www.worldcat.org/isbn/9780070573574">978-0070573574</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780705734349">9780705734349</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/255536918">255536918</a> and&nbsp;<a
 * href="https://www.worldcat.org/oclc/16131891">16131891</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=MO1lGwAACAAJ">MO1lGwAACAAJ</a>,
 * <a
 * href="http://books.google.com/books?id=ElwEAAAACAAJ">ElwEAAAACAAJ</a>,
 * <a
 * href="http://books.google.com/books?id=QYFCGgAACAAJ">QYFCGgAACAAJ</a>,
 * and&nbsp;<a
 * href="http://books.google.com/books?id=YmxEAAAAIAAJ">YmxEAAAAIAAJ
 * </a></div></li>
 * <li><div><span id="cite_SE20061STITIAAC" />David C. Stone and&nbsp;Jon
 * Ellis: <span style="font-style:italic;font-family:cursive;">&ldquo;Stats
 * Tutorial &#8211; Intro to Instrumental Analysis and
 * Calibration,&rdquo;</span> (Website), August&nbsp;23, 2006, Toronto, ON,
 * Canada: University of Toronto (UofT), Department of Chemistry.
 * <div>link: [<a href=
 * "http://www.chem.utoronto.ca/coursenotes/analsci/StatsTutorial/index.html"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_D1961MCAM" />Olive Jean Dunn: <span
 * style="font-weight:bold">&ldquo;Multiple Comparisons Among
 * Means,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Journal of the American
 * Statistical Association</span> 56(293):52&ndash;64, March&nbsp;1961;
 * published by Alexandria, VA, USA: American Statistical Association (ASA)
 * and&nbsp;London, UK: Taylor and Francis LLC. LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/sn99-23377">sn99-23377</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1080/01621459.1961.10482090"
 * >10.1080/01621459.1961.10482090</a>; JSTOR stable:&nbsp;<a
 * href="http://www.jstor.org/stable/2282330">2282330</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/1480864">1480864</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/01621459">0162-1459</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/1537274X">1537-274X</a>; further
 * information: [<a
 * href="http://www.amstat.org/PUBLICATIONS/jasa">1</a>]</div></li>
 * <li><div><span id="cite_D2006SCOCOMDS" />Janez Dem&#353;ar: <span
 * style="font-weight:bold">&ldquo;Statistical Comparisons of Classifiers
 * over Multiple Data Sets,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Journal of Machine
 * Learning Research (JMLR)</span> 7:1&ndash;30, January&nbsp;2006;
 * published by Cambridge, MA, USA: MIT Press. ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/15337928">1533-7928</a>. <div>link:
 * [<a href
 * ="http://jmlr.csail.mit.edu/papers/volume7/demsar06a/demsar06a.pdf"
 * >1</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.141.3142"
 * >10.1.1 .141.3142</a></div></div></li>
 * <li><div><span id="cite_GH2008AEOSCOCOMDSFAPC" />Salvador Garc&#237;a
 * and&nbsp;Francisco Herrera Triguero: <span
 * style="font-weight:bold">&ldquo;An Extension on &#8220;Statistical
 * Comparisons of Classifiers over Multiple Data Sets&#8221; for all
 * Pairwise Comparisons,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Journal of Machine
 * Learning Research (JMLR)</span> 9:2677&ndash;2694, December&nbsp;2008;
 * published by Cambridge, MA, USA: MIT Press. ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/15337928">1533-7928</a>. <div>links:
 * [<a href
 * ="http://jmlr.csail.mit.edu/papers/volume9/garcia08a/garcia08a.pdf"
 * >1</a>] and&nbsp;[<a
 * href="http://sci2s.ugr.es/publications/ficheros/2008-Garcia-JMLR.pdf"
 * >2</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.210.8062"
 * >10.1.1 .210.8062</a></div></div></li>
 * </ol>
 */
public final class DescTests extends DescriptionModule {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * the website&nbsp;[<a href="#cite_F1925SMFRW"
   * style="font-weight:bold">1</a>]
   */
  public static final BibBook F1925SMFRW;
  /**
   * the website&nbsp;[<a href="#cite_L1975NSMBOR"
   * style="font-weight:bold">2</a>]
   */
  static final BibBook L1975NSMBOR;
  /**
   * the website&nbsp;[<a href="#cite_SC1988NSFTBS"
   * style="font-weight:bold">3</a>]
   */
  static final BibBook SC1988NSFTBS;
  /**
   * the website&nbsp;[<a href="#cite_SE20061STITIAAC"
   * style="font-weight:bold">4</a>]
   */
  static final BibWebsite SE20061STITIAAC;
  /**
   * the article&nbsp;[<a href="#cite_D1961MCAM"
   * style="font-weight:bold">5</a>]
   */
  public static final BibArticle D1961MCAM;
  /**
   * the article&nbsp;[<a href="#cite_D2006SCOCOMDS"
   * style="font-weight:bold">6</a>]
   */
  static final BibArticle D2006SCOCOMDS;
  /**
   * the article&nbsp;[<a href="#cite_GH2008AEOSCOCOMDSFAPC"
   * style="font-weight:bold">7</a>]
   */
  static final BibArticle GH2008AEOSCOCOMDSFAPC;

  static {
    F1925SMFRW = new BibBook(new BibAuthors(//
        new BibAuthor("Ronald Aylmer", "Sir Fisher")//$NON-NLS-1$//$NON-NLS-2$
        ),//
        "Statistical Methods for Research Workers",//$NON-NLS-1$
        new BibDate(1925), //
        BibAuthors.EMPTY, "Oliver and Boyd",//$NON-NLS-1$
        "London, UK",//$NON-NLS-1$
        null, null, null, null, null);

    L1975NSMBOR = new BibBook(new BibAuthors(//
        new BibAuthor("Erich Leo", "Lehmann")//$NON-NLS-1$//$NON-NLS-2$
        ),//
        "Nonparametrics: Statistical Methods Based on Ranks",//$NON-NLS-1$
        new BibDate(1975, EBibMonth.JUNE), //
        BibAuthors.EMPTY, "McGraw-Hill Ltd.",//$NON-NLS-1$
        "Maidenhead, England, UK",//$NON-NLS-1$
        null, null, null, null, null);

    SC1988NSFTBS = new BibBook(new BibAuthors(//
        new BibAuthor("Sidney", "Siegel"),//$NON-NLS-1$//$NON-NLS-2$
        new BibAuthor("N. John", "Castellan Jr.")//$NON-NLS-1$//$NON-NLS-2$
        ),//
        "Nonparametric Statistics for The Behavioral Sciences",//$NON-NLS-1$
        new BibDate(1956), //
        BibAuthors.EMPTY, "McGraw-Hill",//$NON-NLS-1$
        "New York, NY, USA",//$NON-NLS-1$
        "Humanities/Social Sciences/Languages",//$NON-NLS-1$
        null, null, null, null);

    try {
      SE20061STITIAAC = new BibWebsite(
          new BibAuthors(//
              new BibAuthor("David C.", "Stone"),//$NON-NLS-1$//$NON-NLS-2$
              new BibAuthor("Jon", "Ellis")//$NON-NLS-1$//$NON-NLS-2$
              ),//
              "Stats Tutorial \u2012 Intro to Instrumental Analysis and Calibration",//$NON-NLS-1$
              new BibDate(2006, EBibMonth.AUGUST, 23),//
              "University of Toronto (UofT), Department of Chemistry",//$NON-NLS-1$
              "Toronto, ON, Canada",//$NON-NLS-1$
              new URI(
                  "http://www.chem.utoronto.ca/coursenotes/analsci/StatsTutorial/index.html")//$NON-NLS-1$
          );
    } catch (final URISyntaxException use) {
      throw new RuntimeException(use);
    }

    D1961MCAM = new BibArticle(new BibAuthors(//
        new BibAuthor("Olive Jean", "Dunn")//$NON-NLS-1$//$NON-NLS-2$
        ),//
        "Multiple Comparisons Among Means",//$NON-NLS-1$
        new BibDate(1961, EBibMonth.MARCH), //
        "Journal of the American Statistical Association",//$NON-NLS-1$
        "56",//$NON-NLS-1$
        "293",//$NON-NLS-1$
        "52",//$NON-NLS-1$
        "64",//$NON-NLS-1$
        null,//
        "10.1080/01621459.1961.10482090");//$NON-NLS-1$

    try {
      D2006SCOCOMDS = new BibArticle(
          new BibAuthors(//
              new BibAuthor("Janez", "Dem\u0161ar")//$NON-NLS-1$//$NON-NLS-2$
              ),//
              "Statistical Comparisons of Classifiers over Multiple Data Sets",//$NON-NLS-1$
              new BibDate(2006, EBibMonth.JANUARY), //
              "Journal of Machine Learning Research (JMLR)",//$NON-NLS-1$
              "7",//$NON-NLS-1$
              "Jan",//$NON-NLS-1$
              "1",//$NON-NLS-1$
              "30",//$NON-NLS-1$
              new URI(
                  "http://jmlr.csail.mit.edu/papers/volume7/demsar06a/demsar06a.pdf"),//$NON-NLS-1$
                  null);
    } catch (final URISyntaxException e) {
      throw new RuntimeException(e);
    }

    try {
      GH2008AEOSCOCOMDSFAPC = new BibArticle(
          new BibAuthors(//
              new BibAuthor("Salvador", "Garc\u00eda"),//$NON-NLS-1$//$NON-NLS-2$
              new BibAuthor("Francisco", "Herrera Triguero")//$NON-NLS-1$//$NON-NLS-2$
              ),//
              "An Extension on \u201cStatistical Comparisons of Classifiers over Multiple Data Sets\u201d for all Pairwise Comparisons",//$NON-NLS-1$
              new BibDate(2008, EBibMonth.DECEMBER), //
              "Journal of Machine Learning Research (JMLR)",//$NON-NLS-1$
              "9",//$NON-NLS-1$
              "Dec",//$NON-NLS-1$
              "2677",//$NON-NLS-1$
              "2694",//$NON-NLS-1$
              new URI(
                  "http://jmlr.csail.mit.edu/papers/volume9/garcia08a/garcia08a.pdf"),//$NON-NLS-1$
                  null);
    } catch (final URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  /** the quality measures */
  private final DescQualityMeasures m_qm;

  /**
   * create!
   *
   * @param owner
   *          the owner
   */
  public DescTests(final Module owner) {
    super("StatisticalTests", owner, false); //$NON-NLS-1$

    this.m_qm = this.getRoot().findInstance(DescQualityMeasures.class);
    this.addDependency(this.m_qm);
  }

  /** {@inheritDoc} */
  @Override
  protected void initialize(final Header header, final ExperimentSet data)
      throws IOException {//
    super.initialize(header, data);
    Macros.DATA_SAMPLE_I.define(header);
    Macros.TEST_MAX_ERROR_PROBABILITY.define(header);
    Macros.TEST_ERROR_PROBABILITY.define(header);
    Macros.TEST_COMPARISON_RESULT.define(header);
    Macros.F_BEST.define(header);
    Macros.F_THRESHOLD.define(header);
  }

  /** {@inheritDoc} */
  @SuppressWarnings("unused")
  @Override
  protected void createChildModules() {
    super.createChildModules();
    new _DescMannWhitneyU(this);
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSectionTitle(final SectionTitle title,
      final ExperimentSet data) throws IOException {
    title.write("Statistical Tests"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSectionBody(final SectionBody body,
      final ExperimentSet data) throws IOException {

    final Label lbl;

    body.write("Statistical tests"); //$NON-NLS-1$
    body.cite(ECitationMode.BY_ID_IN_SENTENCE, DescTests.F1925SMFRW,
        DescTests.L1975NSMBOR, DescTests.SC1988NSFTBS);
    body.write(//
        " are statistical methods to find out whether a hypothesis about one or multiple distributions (data sets, set of numbers, etc.) holds. For instance, we may observe that the elements (or their mean or median) of one data set "); //$NON-NLS-1$
    try (MacroInvocation mi = body.macroInvocation(Macros.DATA_SAMPLE_I)) {
      try (MacroParameter p = mi.macroParameter()) {
        p.writeInt(1);
      }
    }
    body.write(//
        " seem to be smaller than the elements (or their mean or median) of another data set "); //$NON-NLS-1$
    try (MacroInvocation mi = body.macroInvocation(Macros.DATA_SAMPLE_I)) {
      try (MacroParameter p = mi.macroParameter()) {
        p.writeInt(2);
      }
    }
    body.write(//
        ". If "); //$NON-NLS-1$
    try (MacroInvocation mi = body.macroInvocation(Macros.DATA_SAMPLE_I)) {
      try (MacroParameter p = mi.macroParameter()) {
        p.writeInt(1);
      }
    }
    body.write(" and "); //$NON-NLS-1$
    try (MacroInvocation mi = body.macroInvocation(Macros.DATA_SAMPLE_I)) {
      try (MacroParameter p = mi.macroParameter()) {
        p.writeInt(2);
      }
    }
    body.write(//
        " are results measured from a random experiment, there is a certain probability "); //$NON-NLS-1$
    body.macroInvoke(Macros.TEST_ERROR_PROBABILITY);
    body.write(//
        " that this observation just occured by chance, i.e., that our hypothesis (observation) is wrong."); //$NON-NLS-1$

    body.write(//
        "With a statistical test, we want to learn about (i.e., try to estimate) "); //$NON-NLS-1$
    body.macroInvoke(Macros.TEST_ERROR_PROBABILITY);
    body.write(//
        " in order to find out whether our observation is significant (small values of "); //$NON-NLS-1$
    body.macroInvoke(Macros.TEST_ERROR_PROBABILITY);
    body.write(//
        " close to 0) or insignificant (likely the result of randomness, high values of "); //$NON-NLS-1$
    body.macroInvoke(Macros.TEST_ERROR_PROBABILITY);
    body.write(//
        " close to 1)."); //$NON-NLS-1$

    body.writeLinebreak();

    body.write(//
        "Here, we use tests mainly to find out whether the values in a data set "); //$NON-NLS-1$
    try (MacroInvocation mi = body.macroInvocation(Macros.DATA_SAMPLE_I)) {
      try (MacroParameter p = mi.macroParameter()) {
        p.writeInt(1);
      }
    }
    body.write(//
        " tend to be larger or smaller than those in a data set "); //$NON-NLS-1$
    try (MacroInvocation mi = body.macroInvocation(Macros.DATA_SAMPLE_I)) {
      try (MacroParameter p = mi.macroParameter()) {
        p.writeInt(2);
      }
    }
    body.write(//
        ". The idea is that such values are usually either the best objective function values "); //$NON-NLS-1$
    body.macroInvoke(Macros.F_BEST);
    body.write(//
        " found by an algorithm within certain runtime limits or the runtime needed to obtain a certain goal solution quality "); //$NON-NLS-1$
    body.macroInvoke(Macros.F_THRESHOLD);

    lbl = this.m_qm.getLabel(data);
    if (lbl != null) {
      body.write(" (see "); //$NON-NLS-1$
      body.reference(lbl);
      body.writeChar(')');
    }

    body.write(//
        " and we want to know whether an algorithm produces significantly better results or is significantly faster."); //$NON-NLS-1$

    body.writeLinebreak();
    body.write(//
        "In this context, we make the simplified assumption that a test returns two pieces of information: "); //$NON-NLS-1$

    try (Enumeration enu = body.enumeration()) {
      try (EnumerationItem enui = enu.item()) {

        enui.write("A comparison result "); //$NON-NLS-1$
        enui.macroInvoke(Macros.TEST_COMPARISON_RESULT);
        enui.write(" which is"); //$NON-NLS-1$

        try (Itemization itm = enui.itemization()) {
          try (ItemizationItem itmi = itm.item()) {
            itmi.write("less than zero if the elements (numbers) in data set "); //$NON-NLS-1$

            try (MacroInvocation mi = itmi
                .macroInvocation(Macros.DATA_SAMPLE_I)) {
              try (MacroParameter p = mi.macroParameter()) {
                p.writeInt(1);
              }
            }

            itmi.write(" tend to be smaller than those in data set "); //$NON-NLS-1$

            try (MacroInvocation mi = itmi
                .macroInvocation(Macros.DATA_SAMPLE_I)) {
              try (MacroParameter p = mi.macroParameter()) {
                p.writeInt(2);
              }
            }

            itmi.write(", "); //$NON-NLS-1$
          }
          try (ItemizationItem itmi = itm.item()) {
            itmi.write(//
                "larger than zero if the elements of "); //$NON-NLS-1$
            try (MacroInvocation mi = itmi
                .macroInvocation(Macros.DATA_SAMPLE_I)) {
              try (MacroParameter p = mi.macroParameter()) {
                p.writeInt(1);
              }
            }
            itmi.write(" tend to be larger than those in "); //$NON-NLS-1$
            try (MacroInvocation mi = itmi
                .macroInvocation(Macros.DATA_SAMPLE_I)) {
              try (MacroParameter p = mi.macroParameter()) {
                p.writeInt(2);
              }
            }
            itmi.write(", and "); //$NON-NLS-1$
          }
          try (ItemizationItem itmi = itm.item()) {
            itmi.write(//
                "zero if making any such statement would be wrong with a probability "); //$NON-NLS-1$
            enui.macroInvoke(Macros.TEST_ERROR_PROBABILITY);
            itmi.write(//
                " larger or equal to a limit probability (significance level) "); //$NON-NLS-1$
            enui.macroInvoke(Macros.TEST_MAX_ERROR_PROBABILITY);
            itmi.writeChar('.');
          }
        }

      }
      try (EnumerationItem enui = enu.item()) {
        enui.write("The error probability "); //$NON-NLS-1$
        enui.macroInvoke(Macros.TEST_ERROR_PROBABILITY);
        enui.write(" itself, with which the statement indicated by "); //$NON-NLS-1$
        enui.macroInvoke(Macros.TEST_COMPARISON_RESULT);
        enui.write(//
            " is wrong, based on the data sets used for comparison. "); //$NON-NLS-1$

        enui.macroInvoke(Macros.TEST_ERROR_PROBABILITY);

        enui.write(//
            " generally is a value between 0 and 1. For the special case that "); //$NON-NLS-1$

        try (InlineMath mi = enui.inlineMath()) {
          try (MathOp mo = mi.mathOp(EMathOp.CMP_EQUALS)) {
            try (MathOpParam p1 = mo.mathOpParam()) {
              p1.macroInvoke(Macros.TEST_COMPARISON_RESULT);
            }
            try (MathOpParam p1 = mo.mathOpParam()) {
              p1.writeInt(0);
            }
          }
        }
        enui.write(", "); //$NON-NLS-1$
        enui.macroInvoke(Macros.TEST_ERROR_PROBABILITY);
        enui.write(" is defined to be 1."); //$NON-NLS-1$

      }
    }

    body.write(//
        "In other words, our tests are always two-tailed");//$NON-NLS-1$
    body.cite(ECitationMode.BY_ID_IN_SENTENCE, DescTests.SE20061STITIAAC);
    body.write(//
        ", i.e., make no assumption about which of the compared data sets is larger.");//$NON-NLS-1$

    body.writeLinebreak();

    body.write(//
        "There are two general classes of statistical tests: parametric and non-parametric tests. The parametric tests make the assumtion that the values in the data sets follow some general distribution such as the normal distribution. This makes it easier to make statements about ");//$NON-NLS-1$
    body.macroInvoke(Macros.TEST_ERROR_PROBABILITY);
    body.write(//
        " but these statemets may be wrong if the data samples do not follow the assumed distribution. Non-parametric tests, on the other hand, make very little assumptions about the nature of the data samples. They are more thus more conservative, i.e., usually lead to higher ");//$NON-NLS-1$
    body.macroInvoke(Macros.TEST_ERROR_PROBABILITY);
    body.write(//
        "-values, but also are more likely to lead to correct statements. In the context of optimization, we prefer the class of non-parametric tests, as it usually makes no sense to assume that the output or runtime of an optimization process would be, e.g., normally distributed. Because of this better robustness, we focus on using such tests.");//$NON-NLS-1$

    body.writeLinebreak();
    body.write(//
        "Generally, there are two methods to interpret the results of a statistical test:"); //$NON-NLS-1$

    try (Enumeration enu = body.enumeration()) {
      try (EnumerationItem enui = enu.item()) {

        enui.write("As stated before, usually an error threshold "); //$NON-NLS-1$
        enui.macroInvoke(Macros.TEST_MAX_ERROR_PROBABILITY);
        enui.write(" is defined and an observation "); //$NON-NLS-1$
        enui.macroInvoke(Macros.TEST_COMPARISON_RESULT);
        enui.write(" is accepted if "); //$NON-NLS-1$
        try (InlineMath mi = enui.inlineMath()) {
          try (MathOp mo = mi.mathOp(EMathOp.CMP_LESS)) {
            try (MathOpParam p1 = mo.mathOpParam()) {
              p1.macroInvoke(Macros.TEST_ERROR_PROBABILITY);
            }
            try (MathOpParam p1 = mo.mathOpParam()) {
              p1.macroInvoke(Macros.TEST_MAX_ERROR_PROBABILITY);
            }
          }
        }

        enui.write(". For example, if a comparison of a data set "); //$NON-NLS-1$
        try (MacroInvocation mi = enui
            .macroInvocation(Macros.DATA_SAMPLE_I)) {
          try (MacroParameter p = mi.macroParameter()) {
            p.writeInt(1);
          }
        }
        enui.write(" with a data set "); //$NON-NLS-1$
        try (MacroInvocation mi = enui
            .macroInvocation(Macros.DATA_SAMPLE_I)) {
          try (MacroParameter p = mi.macroParameter()) {
            p.writeInt(2);
          }
        }
        enui.write(" would yield that the elements in "); //$NON-NLS-1$
        try (MacroInvocation mi = enui
            .macroInvocation(Macros.DATA_SAMPLE_I)) {
          try (MacroParameter p = mi.macroParameter()) {
            p.writeInt(1);
          }
        }
        enui.write(" tend to be larger than those in "); //$NON-NLS-1$
        try (MacroInvocation mi = enui
            .macroInvocation(Macros.DATA_SAMPLE_I)) {
          try (MacroParameter p = mi.macroParameter()) {
            p.writeInt(2);
          }
        }
        enui.write(" with a probability "); //$NON-NLS-1$
        enui.macroInvoke(Macros.TEST_ERROR_PROBABILITY);
        enui.write(" to err of "); //$NON-NLS-1$
        enui.writeDouble(0.07d);
        enui.write(" under a given threshold of "); //$NON-NLS-1$
        try (InlineMath mi = enui.inlineMath()) {
          try (MathOp mo = mi.mathOp(EMathOp.CMP_LESS)) {
            try (MathOpParam p1 = mo.mathOpParam()) {
              p1.macroInvoke(Macros.TEST_MAX_ERROR_PROBABILITY);
            }
            try (MathOpParam p1 = mo.mathOpParam()) {
              p1.writeDouble(0.02d);
            }
          }
        }
        enui.write(//
            ", the observation would be rejected as insignificant. On the other hand, if "); //$NON-NLS-1$

        enui.macroInvoke(Macros.TEST_ERROR_PROBABILITY);
        enui.write(" was "); //$NON-NLS-1$
        enui.writeDouble(0.007d);
        enui.write(", one would state that the elements in "); //$NON-NLS-1$
        try (MacroInvocation mi = enui
            .macroInvocation(Macros.DATA_SAMPLE_I)) {
          try (MacroParameter p = mi.macroParameter()) {
            p.writeInt(2);
          }
        }
        enui.write(" significantly tend to be smaller than those in "); //$NON-NLS-1$
        try (MacroInvocation mi = enui
            .macroInvocation(Macros.DATA_SAMPLE_I)) {
          try (MacroParameter p = mi.macroParameter()) {
            p.writeInt(1);
          }
        }
        enui.writeChar('.');
      }
      try (EnumerationItem enui = enu.item()) {

        enui.write("The second possible interpretation looks at the "); //$NON-NLS-1$
        enui.macroInvoke(Macros.TEST_ERROR_PROBABILITY);
        enui.write(//
            "-values directly. The idea is that, on one hand, using error thresholds "); //$NON-NLS-1$

        enui.macroInvoke(Macros.TEST_MAX_ERROR_PROBABILITY);
        enui.write(//
            " does not add any more information (just condenses it to Boolean decisions). On the other hand, "); //$NON-NLS-1$
        enui.macroInvoke(Macros.TEST_ERROR_PROBABILITY);
        enui.write(//
            " tells us how confident we can be in the observation. The smaller "); //$NON-NLS-1$
        enui.macroInvoke(Macros.TEST_ERROR_PROBABILITY);
        enui.write(//
            " gets, the more likely the "); //$NON-NLS-1$
        enui.macroInvoke(Macros.TEST_COMPARISON_RESULT);
        enui.write(//
            " actually represents the observations we would make when running infinitely many experiments. We therefore could plot "); //$NON-NLS-1$
        enui.macroInvoke(Macros.TEST_ERROR_PROBABILITY);
        enui.write(//
            " in a diagram comparing two algorithms and see how it develops over time."); //$NON-NLS-1$
      }
    }

    if (data.size() > 2) {
      body.writeLinebreak();
      body.write("If ");//$NON-NLS-1$
      try (InlineMath im = body.inlineMath()) {
        try (MathOp mo = im.mathOp(EMathOp.CMP_GREATER)) {
          try (MathOpParam p1 = mo.mathOpParam()) {
            try (NormalText tn = p1.normalText()) {
              tn.writeChar('N');
            }
          }
          try (MathOpParam p2 = mo.mathOpParam()) {
            p2.writeInt(2);
          }
        }
      }
      body.write(" algorithms are compared, performing ");//$NON-NLS-1$
      try (InlineMath im = body.inlineMath()) {
        try (MathOp mo = im.mathOp(EMathOp.MUL)) {
          try (MathOpParam p1 = mo.mathOpParam()) {
            p1.writeDouble(0.5);
          }
          try (MathOpParam p2 = mo.mathOpParam()) {
            try (MathOp mo2 = p2.mathOp(EMathOp.MUL)) {
              try (MathOpParam p1 = mo.mathOpParam()) {
                try (NormalText tn = p1.normalText()) {
                  tn.writeChar('N');
                }
              }

              try (MathOpParam p1 = mo2.mathOpParam()) {
                try (MathOp mo3 = p1.mathOp(EMathOp.ENCLOSING_PARENTHESES)) {
                  try (MathOpParam p3 = mo3.mathOpParam()) {

                    try (MathOp mo4 = p3.mathOp(EMathOp.SUB)) {
                      try (MathOpParam p4 = mo4.mathOpParam()) {

                        try (NormalText tn = p4.normalText()) {
                          tn.writeChar('N');
                        }
                      }
                      try (MathOpParam p4 = mo4.mathOpParam()) {
                        p4.writeInt(1);
                      }
                    }
                  }
                }

              }
            }
          }
        }
      }

      body.write(//
          " tests directly is not advisable. Instead, additional provisions such as (at least) the conservative Bonferroni correction");//$NON-NLS-1$
      body.cite(ECitationMode.BY_ID_IN_SENTENCE, DescTests.D1961MCAM);
      body.write(//
          " or (better) more sophisticated tests together with post-hoc methods");//$NON-NLS-1$
      body.cite(ECitationMode.BY_ID_IN_SENTENCE, DescTests.D2006SCOCOMDS,
          DescTests.GH2008AEOSCOCOMDSFAPC);
      body.write(//
          " are needed. In the current implementation of our system, we apply the Bonferroni correction.");//$NON-NLS-1$
    }
    super.writeSectionBody(body, data);
  }

  /**
   * get the description class for the given test class
   *
   * @param clazz
   *          the test class
   * @return the description class
   */
  public static final Class<? extends TestDescription> forClass(
      final Class<? extends MultivariateTest> clazz) {

    if (clazz.equals(TwoTailedMannWhitneyUTest.class)) {
      return _DescMannWhitneyU.class;
    }

    throw new IllegalArgumentException(String.valueOf(clazz));
  }
}
