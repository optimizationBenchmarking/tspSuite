package org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.eval;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.modules.impl.comparison.globalRanking.GlobalRankingSummary;
import org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.tests.DescTests;
import org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.tests.TestDescription;
import org.logisticPlanning.tsp.evaluation.modules.spec.DescriptionModule;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.utils.document.spec.AbstractTextComplex;
import org.logisticPlanning.utils.document.spec.ECitationMode;
import org.logisticPlanning.utils.document.spec.EMathName;
import org.logisticPlanning.utils.document.spec.EMathOp;
import org.logisticPlanning.utils.document.spec.Emphasize;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.InlineMath;
import org.logisticPlanning.utils.document.spec.MathName;
import org.logisticPlanning.utils.document.spec.MathOp;
import org.logisticPlanning.utils.document.spec.MathOpParam;
import org.logisticPlanning.utils.document.spec.SectionBody;
import org.logisticPlanning.utils.document.spec.SectionTitle;
import org.logisticPlanning.utils.document.spec.bib.BibArticle;
import org.logisticPlanning.utils.document.spec.bib.BibAuthor;
import org.logisticPlanning.utils.document.spec.bib.BibAuthors;
import org.logisticPlanning.utils.document.spec.bib.BibDate;
import org.logisticPlanning.utils.document.spec.bib.BibInProceedings;
import org.logisticPlanning.utils.document.spec.bib.BibProceedings;
import org.logisticPlanning.utils.document.spec.bib.BibWebsite;
import org.logisticPlanning.utils.document.spec.bib.EBibMonth;
import org.logisticPlanning.utils.math.statistics.tests.impl.TwoTailedMannWhitneyUTest;

/**
 * A description of a ranking approach. We use a
 * <em>fractional ranking</em> &nbsp;[<a href="#cite_WIKIPEDIA2013RANKING"
 * style="font-weight:bold">1</a>]. Ranks are aggregated by ranking the
 * median ranks. This is simpler than other methods&nbsp;[<a
 * href="#cite_KS1998OPCATAAA" style="font-weight:bold">2</a>, <a
 * href="#cite_M2010TNRCRIINAIBTOSIN" style="font-weight:bold">3</a>] and
 * applicable to large amounts of data, plus it should be quite robust and
 * allow to pick up strong trends only. See the documentation of class
 * {@link org.logisticPlanning.tsp.evaluation.data.properties.ranking.Ranking
 * Ranking} for more details.<h2>References</h2>
 * <ol>
 * <li><div><span id="cite_WIKIPEDIA2013RANKING" /><span
 * style="font-style:italic;font-family:cursive;"
 * >&ldquo;Ranking,&rdquo;</span> (Website), August&nbsp;21, 2013, San
 * Francisco, CA, USA: Wikimedia Foundation, Inc. and&nbsp;San Francisco,
 * CA, USA: Wikimedia Foundation, Inc.. <div>link: [<a
 * href="http://en.wikipedia.org/wiki/Ranking">1</a>]</div></div></li>
 * <li><div><span id="cite_KS1998OPCATAAA" /><a href=
 * "http://academic.research.microsoft.com/Author/1577890/pekka-j-korhonen"
 * >Pekka J. Korhonen</a> and&nbsp;Aapo Siljam&#228;ki: <span
 * style="font-weight:bold">&ldquo;Ordinal Principal Component Analysis
 * Theory and an Application,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Computational Statistics
 * &amp; Data Analysis</span> 26(4):411&ndash;424, February&nbsp;6, 1998;
 * published by Essex, UK: Elsevier Science Publishers B.V.. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1016/S0167-9473(97)00038-8"
 * >10.1016/S0167-9473(97)00038-8</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/01679473">0167-9473</a></div></li>
 * <li><div><span id="cite_M2010TNRCRIINAIBTOSIN" /><a
 * href="http://www.geocities.ws/nehu_economics/biodata.htm">Sudhanshu
 * Kumar Mishra</a>: <span style="font-weight:bold">&ldquo;The Most
 * Representative Composite Rank Ordering of Multi-Attribute Objects by the
 * Particle Swarm Optimization Method,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Journal of Quantitative
 * Economics</span> 8(2):165&ndash;200, July&nbsp;2010; published by New
 * Delhi, India: Indian Econometric Society (TIES). ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/09711554">0971-1554</a>. <div>link:
 * [<a href
 * ="http://www.jqe.co.in/journals/JQE_v8_n2_2010_p11.pdf">1</a>]</
 * div></div></li>
 * </ol>
 */
public final class DescRanking extends DescriptionModule {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * the wikipedia entry for ranking&nbsp;[<a
   * href="#cite_WIKIPEDIA2013RANKING" style="font-weight:bold">1</a>]
   */
  private static final BibWebsite WIKIPEDIA2013RANKING;

  /**
   * the article&nbsp;[<a href="#cite_KS1998OPCATAAA"
   * style="font-weight:bold">2</a>]
   */
  private static final BibArticle KS1998OPCATAAA;

  /**
   * the article&nbsp;[<a href="#cite_M2010TNRCRIINAIBTOSIN"
   * style="font-weight:bold">3</a>]
   */
  private static final BibArticle M2010TNRCRIINAIBTOSIN;

  /**
   * the conference paper&nbsp;[<a href="#cite_B1990BCAPRFM"
   * style="font-weight:bold">4</a>]
   */
  private static final BibInProceedings B1990BCAPRFM;

  /**
   * the article&nbsp;[<a href="#cite_M1956TMNSPMTSLOOCFPI"
   * style="font-weight:bold">5</a>]
   */
  private static final BibArticle M1956TMNSPMTSLOOCFPI;

  static {
    try {//
      WIKIPEDIA2013RANKING = new BibWebsite(//
          BibAuthors.EMPTY,//
          "WikiPedia Page on \u2018Ranking\u2019",//$NON-NLS-1$
          new BibDate(2013, EBibMonth.AUGUST, 21), //
          "Wikimedia Foundation, Inc.",//$NON-NLS-1$
          "San Francisco, CA, USA",//$NON-NLS-1$
          new URI("http://en.wikipedia.org/wiki/Ranking"));//$NON-NLS-1$
    } catch (final URISyntaxException use) {
      throw new RuntimeException(use);
    }

    KS1998OPCATAAA = new BibArticle(new BibAuthors(//
        new BibAuthor("Pekka J.", "Korhonen"),//$NON-NLS-1$//$NON-NLS-2$
        new BibAuthor("Aapo", "Siljam\u00e4ki")//$NON-NLS-1$//$NON-NLS-2$
        ),//
        "Ordinal Principal Component Analysis Theory and an Application",//$NON-NLS-1$
        new BibDate(1998, EBibMonth.FEBRUARY, 6), //
        "Computational Statistics & Data Analysis",//$NON-NLS-1$
        "26", "4",//$NON-NLS-1$ //$NON-NLS-2$
        "411", "424",//$NON-NLS-1$ //$NON-NLS-2$
        null, "10.1016/S0167-9473(97)00038-8");//$NON-NLS-1$

    try {//
      M2010TNRCRIINAIBTOSIN = new BibArticle(
          new BibAuthors(//
              new BibAuthor("Sudhanshu Kumar", "Mishra")//$NON-NLS-1$//$NON-NLS-2$
          ),//
          "The Most Representative Composite Rank Ordering of Multi-Attribute Objects by the Particle Swarm Optimization Method",//$NON-NLS-1$
          new BibDate(2010, EBibMonth.JULY), //
          "Journal of Quantitative Economics",//$NON-NLS-1$
          "8", "2",//$NON-NLS-1$ //$NON-NLS-2$
          "165", "200",//$NON-NLS-1$ //$NON-NLS-2$
          new URI("http://www.jqe.co.in/journals/JQE_v8_n2_2010_p11.pdf"),//$NON-NLS-1$
          null);
    } catch (final URISyntaxException use) {
      throw new RuntimeException(use);
    }

    try {//
      M1956TMNSPMTSLOOCFPI = new BibArticle(
          new BibAuthors(//
              new BibAuthor("George Armitage", "Miller")//$NON-NLS-1$//$NON-NLS-2$
          ),//
          "The Magical Number Seven, Plus or Minus Two: Some Limits on our Capacity for Processing Information",//$NON-NLS-1$
          new BibDate(1956, EBibMonth.MARCH), //
          "Psychological Review",//$NON-NLS-1$
          "63", "2",//$NON-NLS-1$ //$NON-NLS-2$
          "81", "97",//$NON-NLS-1$ //$NON-NLS-2$
          new URI("http://www.musanim.com/miller1956/"),//$NON-NLS-1$
          null);
    } catch (final URISyntaxException use) {
      throw new RuntimeException(use);
    }

    try {//
      B1990BCAPRFM = new BibInProceedings(
          //
          new BibAuthors(//
              new BibAuthor("Denis", "Bouyssou")//$NON-NLS-1$//$NON-NLS-2$
          ),//
          "Building Criteria: A Prerequisite For MCDA",//$NON-NLS-1$
          new BibProceedings(//
              "Selected Readings from the Third International Summer School on Multicriteria Decision Aid: Methods, Applications, and Software (MCDA'90)",//$NON-NLS-1$
              new BibDate(1998, EBibMonth.JULY, 23),//
              new BibDate(1998, EBibMonth.JULY, 27),//
              new BibAuthors(//
                  new BibAuthor("Carlos A.", "Bana e Costa")//$NON-NLS-1$//$NON-NLS-2$
              ),//
              "Monte Estoril, Lisbon, Portugal",//$NON-NLS-1$
              "Springer-Verlag GmbH",//$NON-NLS-1$
              "Berlin/Heidelberg, Germany",//$NON-NLS-1$
              null, null, null,//
              "10.1007/978-3-642-75935-2"),//$NON-NLS-1$
          "58",//$NON-NLS-1$
          "80",//$NON-NLS-1$
          null,
          new URI(
              "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.2.7628"),//$NON-NLS-1$
          "10.1007/978-3-642-75935-2_4");//$NON-NLS-1$
    } catch (final URISyntaxException use) {
      throw new RuntimeException(use);
    }
  }

  /**
   * create!
   *
   * @param owner
   *          the macro's owner
   */
  DescRanking(final Module owner) {
    super("ranking", owner, false); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected void initialize(final Header header, final ExperimentSet data)
      throws IOException {//
    super.initialize(header, data);
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSectionTitle(final SectionTitle title,
      final ExperimentSet data) throws IOException {
    title.write("Ranking and Aggregated Ranks"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSectionBody(final SectionBody body,
      final ExperimentSet data) throws IOException {
    final DescAUC auc;
    final Class<? extends TestDescription> mwuc;
    final TestDescription mwu;
    GlobalRankingSummary summary;

    body.write("During the evaluation procedure of the benchmark data, we gathere a lot of different information, draw diagrams that present the performance of algorithms from different angles, and provide tables with numerical results. However, the ability of the human mind to understand, remember, analyze, and compare many different things is limited");//$NON-NLS-1$
    body.cite(ECitationMode.BY_ID_IN_SENTENCE,
        DescRanking.M1956TMNSPMTSLOOCFPI, DescRanking.B1990BCAPRFM);
    body.write(". Therefore, a way to reduce this different information to a simple representation is necessary. A ");//$NON-NLS-1$
    try (Emphasize em = body.emphasize()) {
      em.write("ranking");//$NON-NLS-1$
    }
    body.write(" does exactly that.");//$NON-NLS-1$

    body.writeLinebreak();

    body.write("A ");//$NON-NLS-1$
    try (Emphasize em = body.emphasize()) {
      em.write("ranking");//$NON-NLS-1$
    }

    body.write(" is an order defined over a set of items");//$NON-NLS-1$
    body.cite(ECitationMode.BY_ID_IN_SENTENCE,
        DescRanking.WIKIPEDIA2013RANKING);

    body.write(//
    ". Rankings usually can represent as a mapping that assigns one number to each item of the set. The smaller the number, the ");//$NON-NLS-1$
    try (Emphasize em = body.emphasize()) {
      em.write("better");//$NON-NLS-1$
    }
    body.write(//
    " the rank. Generally, if we have ");//$NON-NLS-1$
    DescRanking.__mc(body, 'k');
    body.write(" objects, the ranks may go from ");//$NON-NLS-1$
    body.writeInt(1);
    body.write(" to ");//$NON-NLS-1$
    DescRanking.__mc(body, 'k');
    body.write(//
    ". Such a ranking can be used to reduce some complex information (such as a comparison of approaches based on the area under their corresponding performance curves");//$NON-NLS-1$
    auc = this.getRoot().findInstance(DescAUC.class);
    if ((auc != null) && (auc.isActive())) {
      body.write(", see ");//$NON-NLS-1$
      body.reference(auc.getLabel(data));
    }
    body.write(//
    ") to a simple, easy-to-understand, one-dimensional format.");//$NON-NLS-1$

    body.writeLinebreak();
    body.write(//
    "We will use rankings exactly for this purpose, and apply a ");//$NON-NLS-1$
    try (Emphasize em = body.emphasize()) {
      em.write("fractional");//$NON-NLS-1$
    }
    body.write(" method, a.k.a. ");//$NON-NLS-1$
    try (InlineMath im = body.inlineMath()) {
      try (MathOp mo = im.mathOp(EMathOp.TUPLE)) {
        try (MathOpParam p = mo.mathOpParam()) {
          p.writeInt(1);
        }
        try (MathOpParam p = mo.mathOpParam()) {
          p.writeDouble(2.5);
        }
        try (MathOpParam p = mo.mathOpParam()) {
          p.writeDouble(2.5);
        }
        try (MathOpParam p = mo.mathOpParam()) {
          p.writeInt(4);
        }
      }
    }
    body.write("-ranking");//$NON-NLS-1$
    body.cite(ECitationMode.BY_ID_IN_SENTENCE,
        DescRanking.WIKIPEDIA2013RANKING);
    body.write(//
    ". If two objects are identical according to the comparison/ranking/sorting criterion (or not comparable at all) and thus occupy the same rank, they receive the same rank. This rank is the arithmetic mean of their positions in the ordered list of objects");//$NON-NLS-1$
    body.cite(ECitationMode.BY_ID_IN_SENTENCE,
        DescRanking.WIKIPEDIA2013RANKING);

    mwuc = DescTests.forClass(TwoTailedMannWhitneyUTest.class);
    if (mwuc != null) {
      mwu = this.getRoot().findInstance(mwuc);
      if (mwu != null) {
        body.write(//
        ". This approach is similar to the method commonly used to deal with identical data in rank statistics such as the ");//$NON-NLS-1$
        mwu.writeName(body, false);
        mwu.cite(ECitationMode.BY_ID_IN_SENTENCE, body);
      }
    }
    body.writeChar('.');

    body.writeLinebreak();

    body.write(//
    "Since we evaluate and compare algorithms from different perspectives, it makes sense to aggregate different rankings, each representing a different aspect, to global rankings. This procedure happens on several levels and may involve hundreds of rankings. This makes the method unsuitable for several traditional or modern schemes from literature");//$NON-NLS-1$
    body.cite(ECitationMode.BY_ID_IN_SENTENCE, DescRanking.KS1998OPCATAAA,
        DescRanking.M2010TNRCRIINAIBTOSIN);
    body.write(//
    ". Instead, we will utilize a simple and robust approach: We first compute the median rank for each object, and then re-rank the objects according to their median ranks. This procedure can be repeated for different levels of evaluation: We may, e.g., compute one rank for each benchmark case for a given performance measure and then aggregate the ranks for that measure. This aggregated ranking may then become part of a global aggregated ranking");//$NON-NLS-1$
    summary = this.getRoot().findInstance(GlobalRankingSummary.class);
    if ((summary != null) && (summary.isActive())) {
      body.write(", such as the one presented in ");//$NON-NLS-1$
      body.reference(summary.getLabel(data));
    }
    body.writeChar('.');

    super.writeSectionBody(body, data);
  }

  /**
   * write a math char
   *
   * @param txt
   *          the destination
   * @param m
   *          the method name
   * @throws IOException
   *           if i/o fails
   */
  private static final void __mc(final AbstractTextComplex txt,
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
    if (other instanceof DescAUC) {
      return 1;
    }
    return super.compareTo(other);
  }
}
