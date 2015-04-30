package org.logisticPlanning.utils.math.statistics.tests.impl;

import java.util.Arrays;

import org.logisticPlanning.utils.math.data.collection.IDataCollection;
import org.logisticPlanning.utils.math.functions.stochastic.NormalCDF;
import org.logisticPlanning.utils.math.statistics.aggregates.StableSum;
import org.logisticPlanning.utils.math.statistics.tests.spec.BivariateTwoTailedTest;
import org.logisticPlanning.utils.math.statistics.tests.spec.MultivariateTest;
import org.logisticPlanning.utils.math.statistics.tests.spec.MultivariateTestResult;

/**
 * <p>
 * The Mann-Whitney U Test as described in&nbsp;[<a
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
 * TODO: This class needs to be tested.
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
public final class TwoTailedMannWhitneyUTest extends
    BivariateTwoTailedTest {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the double buffer */
  private transient double[] m_doubleBuf;
  /** the second double buffer */
  private transient double[] m_doubleBuf2;
  /** the rank buffer */
  private transient double[] m_rankBuf;

  /** the internal constructor */
  public TwoTailedMannWhitneyUTest() {
    super("MannWhitneyUTest"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected final void doTest(final MultivariateTestResult dest,
      final int dim, final IDataCollection a, final IDataCollection b,
      final int i, final int j) {
    final int l1, l2, l;
    final StableSum ss;
    double[] srt, data, ranks;
    double rs1, d, e, rs2, u, p;
    int ii, jj;

    l1 = a.size();
    l2 = b.size();
    l = (l1 + l2);

    srt = this.m_doubleBuf;
    if ((srt == null) || (srt.length < l)) {
      this.m_doubleBuf = srt = new double[l];
    }
    data = this.m_doubleBuf2;
    if ((data == null) || (data.length < l1)) {
      this.m_doubleBuf2 = data = new double[l1];
    }

    for (ii = l1; (--ii) >= 0;) {
      srt[ii] = data[ii] = a.get(ii, dim);
    }
    jj = l1;
    for (ii = l2; (--ii) >= 0;) {
      srt[jj++] = b.get(ii, dim);
    }
    Arrays.sort(srt, 0, l);

    ranks = this.m_rankBuf;
    if ((ranks == null) || (ranks.length < l)) {
      this.m_rankBuf = ranks = new double[l];
    }

    ii = 0;
    while (ii < l) {
      jj = ii;
      d = srt[ii];
      while ((jj < l)
          && ((d == (e = srt[jj])) || (Double.compare(d, e) == 0))) {
        jj++;
      }

      Arrays.fill(ranks, ii, jj, (ii + (((jj - ii) + 1) * 0.5d)));
      ii = jj;
    }

    ss = new StableSum();
    for (ii = l1; (--ii) >= 0;) {
      ss.visitDouble(ranks[Arrays.binarySearch(srt, 0, l, data[ii])]);
    }
    rs1 = ss.getResult();

    rs2 = (((0.5d * l) * (l + 1)) - rs1);
    u = Math.min(rs1 - ((0.5d * l1) * (l1 + 1)), rs2
        - ((0.5d * l2) * (l2 + 1)));

    p = (1d - NormalCDF.INSTANCE.compute((((0.5d * l1) * l2) - u)
        / Math.sqrt((((1d / 12d) * l1) * l2) * (l + 1))));

    MultivariateTest.setResult(dest, i, j,
        Double.compare((rs1 / l1), (rs2 / l2)), (p + p));
  }

  /** {@inheritDoc} */
  @Override
  public final TwoTailedMannWhitneyUTest clone() {
    TwoTailedMannWhitneyUTest r;

    r = ((TwoTailedMannWhitneyUTest) (super.clone()));
    r.m_doubleBuf = null;
    r.m_rankBuf = null;

    return r;
  }
}
