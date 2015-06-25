package org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.tests;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.modules.impl.macros.Macros;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.utils.document.spec.AbstractTextComplex;
import org.logisticPlanning.utils.document.spec.ECitationMode;
import org.logisticPlanning.utils.document.spec.MacroInvocation;
import org.logisticPlanning.utils.document.spec.MacroParameter;
import org.logisticPlanning.utils.document.spec.SectionBody;
import org.logisticPlanning.utils.document.spec.SectionTitle;
import org.logisticPlanning.utils.document.spec.bib.BibArticle;
import org.logisticPlanning.utils.document.spec.bib.BibAuthor;
import org.logisticPlanning.utils.document.spec.bib.BibAuthors;
import org.logisticPlanning.utils.document.spec.bib.BibDate;
import org.logisticPlanning.utils.document.spec.bib.BibWebsite;
import org.logisticPlanning.utils.document.spec.bib.EBibMonth;

/**
 * <p>
 * A description of the Mann-Whitney U Test as described in&nbsp;[<a
 * href="#cite_WIKIPEDIA2012MWU" style="font-weight:bold">1</a>, <a
 * href="#cite_MW1947OATOWOOTRVISLTTO" style="font-weight:bold">2</a>, <a
 * href="#cite_L1975NSMBOR" style="font-weight:bold">3</a>, <a
 * href="#cite_SC1988NSFTBS" style="font-weight:bold">4</a>, <a
 * href="#cite_DB1973AA6AGFTSDOTMWUS" style="font-weight:bold">5</a>, <a
 * href="#cite_N1988SPFCTDOENPTS" style="font-weight:bold">6</a>, <a
 * href="#cite_L2006VSMWT" style="font-weight:bold">7</a>, <a
 * href="#cite_H1984AEMSPFCTMUGUASD" style="font-weight:bold">8</a>].
 * </p>
 * <p>
 * TODO: Read&nbsp;[<a href="#cite_NK2009RAEOAFCTSOTMWT"
 * style="font-weight:bold">9</a>] carefully and adapt the algorithm in the
 * test, if necessary.
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_WIKIPEDIA2012MWU" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Mann&#8211;Whitney
 * U,&rdquo;</span> (Website), 2012, San Francisco, CA, USA: Wikimedia
 * Foundation, Inc.. <div>link: [<a
 * href="https://en.wikipedia.org/wiki/Mann%E2%80%93Whitney_U"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_MW1947OATOWOOTRVISLTTO" />Henry B. Mann
 * and&nbsp;Donald R. Whitney: <span style="font-weight:bold">&ldquo;On a
 * Test of whether One of Two Random Variables is Stochastically Larger
 * than the Other,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">The Annals of
 * Mathematical Statistics</span> 18(1):50&ndash;60, March&nbsp;1947;
 * published by Beachwood, OH, USA: Institute of Mathematical Statistics.
 * LCCN:&nbsp;<a href="http://lccn.loc.gov/sn98-23312">sn98-23312</a>;
 * doi:&nbsp;<a
 * href="http://dx.doi.org/10.1214/aoms/1177730491">10.1214/aoms
 * /1177730491</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/40280837">40280837</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/00034851">0003-4851</a>. <div>link:
 * [<a
 * href="http://projecteuclid.org/euclid.aoms/1177730491">1</a>]</div></
 * div></li>
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
 * <li><div><span id="cite_DB1973AA6AGFTSDOTMWUS" />L. C. Dinneen
 * and&nbsp;B. C. Blakesley: <span
 * style="font-weight:bold">&ldquo;Algorithm AS 62: A Generator for the
 * Sampling Distribution of the Mann-Whitney U Statistic,&rdquo;</span> in
 * <span style="font-style:italic;font-family:cursive;">Journal of the
 * Royal Statistical Society: Series C &#8210; Applied Statistics</span>
 * 22(2):269&ndash;273, 1973; published by Chichester, West Sussex, UK:
 * Blackwell Publishing for the Royal Statistical Society. LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/sn99-23413">sn99-23413</a>; JSTOR
 * stable:&nbsp;<a href="http://www.jstor.org/stable/2346934">2346934</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/42016866">42016866</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/00359254">0035-9254</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=APSTAG">
 * APSTAG</a></div></li>
 * <li><div><span id="cite_N1988SPFCTDOENPTS" />N. Neumann: <span
 * style="font-weight:bold">&ldquo;Some Procedures for Calculating the
 * Distributions of Elementary Non-parametric Test
 * Statistics,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Statistical Software
 * Newsletter (SSN)</span> 14(3), 1988</div></li>
 * <li><div><span id="cite_L2006VSMWT" />Richard Lowry: <span
 * style="font-weight:bold">&ldquo;VassarStats Mann-Whitney
 * Test,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">VassarStats: Web Site for
 * Statistical Computing</span>, self-published. <div>links: [<a
 * href="http://faculty.vassar.edu/lowry/utest.html">1</a>] and&nbsp;[<a
 * href
 * ="http://dogsbody.psych.mun.ca/VassarStats/utest.html">2</a>]</div></
 * div></li>
 * <li><div><span id="cite_H1984AEMSPFCTMUGUASD" />E. F. Harding: <span
 * style="font-weight:bold">&ldquo;An Efficient, Minimal-Storage Procedure
 * for Calculating the Mann-Whitney U, Generalized U and Similar
 * Distributions,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Journal of the Royal
 * Statistical Society: Series C &#8210; Applied Statistics</span>
 * 33(1):1&ndash;6, 1984; published by Chichester, West Sussex, UK:
 * Blackwell Publishing for the Royal Statistical Society. LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/sn99-23413">sn99-23413</a>; JSTOR
 * stable:&nbsp;<a href="http://www.jstor.org/stable/2347656">2347656</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/42016866">42016866</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/00359254">0035-9254</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=APSTAG">
 * APSTAG</a></div></li>
 * <li><div><span id="cite_NK2009RAEOAFCTSOTMWT" />Niranjan Nagarajan
 * and&nbsp;Uri Keich: <span style="font-weight:bold">&ldquo;Reliability
 * and Efficiency of Algorithms for Computing the Significance of the
 * Mann-Whitney Test,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Computational
 * Statistics</span> 24(4):605&ndash;622, December&nbsp;1, 2009; published
 * by Berlin/Heidelberg: Springer-Verlag. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/s00180-009-0148-x"
 * >10.1007/s00180-009-0148-x</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/09434062">0943-4062</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16139658">1613-9658</a>; further
 * information: [<a href="http://link.springer.com/journal/180">1</a>].
 * <div>link: [<a
 * href="http://www.cbcb.umd.edu/~niranjan/papers/NagarajanKeichCS09.pdf"
 * >1</a>]</div></div></li>
 * </ol>
 */
final class _DescMannWhitneyU extends TestDescription {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * the article&nbsp;[<a href="#cite_MW1947OATOWOOTRVISLTTO"
   * style="font-weight:bold">2</a>]
   */
  private static final BibArticle MW1947OATOWOOTRVISLTTO;
  /**
   * the article&nbsp;[<a href="#cite_DB1973AA6AGFTSDOTMWUS"
   * style="font-weight:bold">5</a>]
   */
  private static final BibArticle DB1973AA6AGFTSDOTMWUS;
  /**
   * the article&nbsp;[<a href="#cite_N1988SPFCTDOENPTS"
   * style="font-weight:bold">6</a>]
   */
  private static final BibArticle N1988SPFCTDOENPTS;
  /**
   * the website&nbsp;[<a href="#cite_L2006VSWFSC"
   * style="font-weight:bold">10</a>]
   */
  private static final BibWebsite L2006VSWFSC;
  /**
   * the article&nbsp;[<a href="#cite_H1984AEMSPFCTMUGUASD"
   * style="font-weight:bold">8</a>]
   */
  private static final BibArticle H1984AEMSPFCTMUGUASD;
  /**
   * the article&nbsp;[<a href="#cite_NK2009RAEOAFCTSOTMWT"
   * style="font-weight:bold">9</a>]
   */
  private static final BibArticle NK2009RAEOAFCTSOTMWT;

  static {
    try {
      MW1947OATOWOOTRVISLTTO = new BibArticle(
          new BibAuthors(//
              new BibAuthor("Henry B.", "Mann"),//$NON-NLS-1$//$NON-NLS-2$
              new BibAuthor("Donald R.", "Whitney")//$NON-NLS-1$//$NON-NLS-2$
          ),//
          "On a Test of whether One of Two Random Variables is Stochastically Larger than the Other",//$NON-NLS-1$
          new BibDate(1947, EBibMonth.MARCH), //
          "The Annals of Mathematical Statistics",//$NON-NLS-1$
          "18",//$NON-NLS-1$
          "1",//$NON-NLS-1$
          "50",//$NON-NLS-1$
          "60",//$NON-NLS-1$
          new URI("http://projecteuclid.org/euclid.aoms/1177730491"),//$NON-NLS-1$
          "10.1214/aoms/1177730491");//$NON-NLS-1$
    } catch (final URISyntaxException e) {
      throw new RuntimeException(e);
    }

    DB1973AA6AGFTSDOTMWUS = new BibArticle(
        new BibAuthors(//
            new BibAuthor("L. C.", "Dinneen"),//$NON-NLS-1$//$NON-NLS-2$
            new BibAuthor("B. C.", "Blakesley")//$NON-NLS-1$//$NON-NLS-2$
        ),//
        "Algorithm AS 62: A Generator for the Sampling Distribution of the Mann-Whitney U Statistic",//$NON-NLS-1$
        new BibDate(1973), //
        "Journal of the Royal Statistical Society: Series C ‒ Applied Statistics",//$NON-NLS-1$
        "22",//$NON-NLS-1$
        "2",//$NON-NLS-1$
        "269",//$NON-NLS-1$
        "273",//$NON-NLS-1$
        null, null);

    N1988SPFCTDOENPTS = new BibArticle(
        new BibAuthors(//
            new BibAuthor("N.", "Neumann")//$NON-NLS-1$//$NON-NLS-2$
        ),//
        "Some Procedures for Calculating the Distributions of Elementary Non-parametric Test Statistics",//$NON-NLS-1$
        new BibDate(1988), //
        "Statistical Software Newsletter",//$NON-NLS-1$
        "14",//$NON-NLS-1$
        "3",//$NON-NLS-1$
        null, null, null, null);

    try {
      L2006VSWFSC = new BibWebsite(new BibAuthors(//
          new BibAuthor("Richard", "Lowry")//$NON-NLS-1$//$NON-NLS-2$
          ),//
          "VassarStats: Web Site for Statistical Computing",//$NON-NLS-1$
          new BibDate(2006), //
          null, null, new URI(
              "http://faculty.vassar.edu/lowry/VassarStats.html")//$NON-NLS-1$
      );
    } catch (final URISyntaxException e) {
      throw new RuntimeException(e);
    }

    H1984AEMSPFCTMUGUASD = new BibArticle(
        new BibAuthors(//
            new BibAuthor("E. F.", "Harding")//$NON-NLS-1$//$NON-NLS-2$
        ),//
        "An Efficient, Minimal-Storage Procedure for Calculating the Mann-Whitney U, Generalized U and Similar Distributions",//$NON-NLS-1$
        new BibDate(1984), //
        "Journal of the Royal Statistical Society: Series C ‒ Applied Statistics",//$NON-NLS-1$
        "33",//$NON-NLS-1$
        "1",//$NON-NLS-1$
        "1",//$NON-NLS-1$
        "6",//$NON-NLS-1$
        null, null);

    try {
      NK2009RAEOAFCTSOTMWT = new BibArticle(
          new BibAuthors(//
              new BibAuthor("Niranjan", "Nagarajan"),//$NON-NLS-1$//$NON-NLS-2$
              new BibAuthor("Uri", "Keich")//$NON-NLS-1$//$NON-NLS-2$
          ),//
          "Reliability and Efficiency of Algorithms for Computing the Significance of the Mann-Whitney Test",//$NON-NLS-1$
          new BibDate(2009, EBibMonth.DECEMBER, 1), //
          "Computational Statistics",//$NON-NLS-1$
          "24",//$NON-NLS-1$
          "4",//$NON-NLS-1$
          "605",//$NON-NLS-1$
          "622",//$NON-NLS-1$
          new URI(
              "http://www.cbcb.umd.edu/~niranjan/papers/NagarajanKeichCS09.pdf"),//$NON-NLS-1$
          "10.1007/s00180-009-0148-x");//$NON-NLS-1$
    } catch (final URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * create!
   *
   * @param owner
   *          the owner
   */
  _DescMannWhitneyU(final Module owner) {
    super("MannWhitneyUTest", owner); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final String getName(final boolean plural) {
    return (plural ? "Mann-Whitney U Tests" : //$NON-NLS-1$
        "Mann-Whitney U Test"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected final void writeSectionTitle(final SectionTitle title,
      final ExperimentSet data) throws IOException {
    title.write(this.getName(false));
  }

  /** {@inheritDoc} */
  @Override
  public final void cite(final ECitationMode how,
      final AbstractTextComplex txt) throws IOException {
    txt.cite(how, _DescMannWhitneyU.MW1947OATOWOOTRVISLTTO,
        _DescMannWhitneyU.DB1973AA6AGFTSDOTMWUS,
        _DescMannWhitneyU.N1988SPFCTDOENPTS,
        _DescMannWhitneyU.L2006VSWFSC,
        _DescMannWhitneyU.H1984AEMSPFCTMUGUASD,
        _DescMannWhitneyU.NK2009RAEOAFCTSOTMWT);
  }

  /** {@inheritDoc} */
  @Override
  protected final void writeSectionBody(final SectionBody body,
      final ExperimentSet data) throws IOException {

    body.write("The "); //$NON-NLS-1$
    body.write(this.getName(true));
    this.cite(ECitationMode.BY_ID_IN_SENTENCE, body);

    body.write(" is a non-parametric statistical test"); //$NON-NLS-1$
    body.cite(ECitationMode.BY_ID_IN_SENTENCE, DescTests.L1975NSMBOR,
        DescTests.SC1988NSFTBS);
    body.write(" which compares two data sets "); //$NON-NLS-1$

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
    " and tells us whether one of the two data sets tends to have larger values than the other or whether there is no significant diference between "); //$NON-NLS-1$
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
    " (if applied in the two-tailed form "); //$NON-NLS-1$
    body.cite(ECitationMode.BY_ID_IN_SENTENCE, DescTests.SE20061STITIAAC);
    body.write(")."); //$NON-NLS-1$
    super.writeSectionBody(body, data);
  }

}
