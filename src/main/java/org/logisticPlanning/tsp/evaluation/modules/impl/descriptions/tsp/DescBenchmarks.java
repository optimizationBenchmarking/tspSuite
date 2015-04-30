package org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.tsp;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.modules.impl.macros.Macros;
import org.logisticPlanning.tsp.evaluation.modules.spec.DescriptionModule;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.utils.document.spec.AbstractTextComplex;
import org.logisticPlanning.utils.document.spec.ECitationMode;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.SectionBody;
import org.logisticPlanning.utils.document.spec.SectionTitle;
import org.logisticPlanning.utils.document.spec.bib.BibArticle;
import org.logisticPlanning.utils.document.spec.bib.BibAuthor;
import org.logisticPlanning.utils.document.spec.bib.BibAuthors;
import org.logisticPlanning.utils.document.spec.bib.BibDate;
import org.logisticPlanning.utils.document.spec.bib.BibRecord;
import org.logisticPlanning.utils.document.spec.bib.BibTechReport;
import org.logisticPlanning.utils.document.spec.bib.BibWebsite;
import org.logisticPlanning.utils.document.spec.bib.EBibMonth;
import org.logisticPlanning.utils.document.spec.bib.EBibQuarter;

/**
 * A description of the benchmarks.<h2>References</h2>
 * <ol>
 * <li><div><span id="cite_R1995T9" /><a
 * href="http://comopt.ifi.uni-heidelberg.de/people/reinelt/">Gerhard
 * Reinelt</a>: <span style="font-weight:bold">&ldquo;TSPLIB
 * 95,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * 1995; published by Heidelberg, Germany: Universit&#228;t Heidelberg,
 * Institut f&#252;r Mathematik. <div>link: [<a
 * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/DOC.PS"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_DACO1995TSPLIB" /><a
 * href="http://comopt.ifi.uni-heidelberg.de/people/reinelt/">Gerhard
 * Reinelt</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;
 * TSPLIB,&rdquo;</span> (Website), 1995, Heidelberg, Germany: Office
 * Research Group Discrete Optimization, Ruprecht Karls University of
 * Heidelberg. <div>link: [<a
 * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_R1991ATSPL" /><a
 * href="http://comopt.ifi.uni-heidelberg.de/people/reinelt/">Gerhard
 * Reinelt</a>: <span style="font-weight:bold">&ldquo;TSPLIB &#8212; A
 * Traveling Salesman Problem Library,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">ORSA Journal on
 * Computing</span> 3(4):376&ndash;384, Fall&nbsp;1991; published by
 * Operations Research Society of America (ORSA). doi:&nbsp;<a
 * href="http://dx.doi.org/10.1287/ijoc.3.4.376">10.1287/ijoc.3.4.376</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/60628815">60628815</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/08991499">0899-1499</a></div></li>
 * <li><div><span id="cite_W2003ROCFTB" /><a
 * href="http://www2.isye.gatech.edu/~wcook/">William John Cook</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Results of
 * Concorde for TSPLib Benchmark,&rdquo;</span> (Website),
 * December&nbsp;2003, Atlanta, GA, USA: Georgia Institute of Technology,
 * H. Milton Stewart School of Industrial and Systems Engineering.
 * <div>link: [<a
 * href="http://www.tsp.gatech.edu/concorde/benchmarks/bench99.html"
 * >1</a>]</div></div></li>
 * </ol>
 */
public final class DescBenchmarks extends DescriptionModule {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * the technical report&nbsp;[<a href="#cite_R1995T9"
   * style="font-weight:bold">1</a>]
   */
  private static final BibTechReport R1995T9;
  /**
   * the website&nbsp;[<a href="#cite_R1995T9"
   * style="font-weight:bold">1</a>]
   */
  private static final BibWebsite DACO1995TSPLIB;
  /**
   * the artice&nbsp;[<a href="#cite_R1991ATSPL"
   * style="font-weight:bold">3</a>]
   */
  private static final BibArticle R1991ATSPL;

  /**
   * the website&nbsp;[<a href="#cite_W2003ROCFTB"
   * style="font-weight:bold">4</a>]
   */
  private static final BibWebsite W2003ROCFTB;

  static {

    BibAuthor gh;
    BibAuthors gha;

    gh = new BibAuthor("Gerhard", "Reinelt"); //$NON-NLS-1$//$NON-NLS-2$
    gha = new BibAuthors(gh);

    try {//
      R1995T9 = new BibTechReport(
          gha,//
          "TSPLIB 95",//$NON-NLS-1$
          new BibDate(1995), //
          "Universit\u00e4t Heidelberg, Institut f\u00fcr Mathematik",//$NON-NLS-1$
          "Heidelberg, Germany",//$NON-NLS-1$
          null,
          null,//
          new URI(
              "http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/DOC.PS"),//$NON-NLS-1$
          null);

      DACO1995TSPLIB = new BibWebsite(
          gha,//
          "TSPLIB",//$NON-NLS-1$
          new BibDate(1995), //
          "Office Research Group Discrete Optimization, Ruprecht Karls University of Heidelberg",//$NON-NLS-1$
          "Heidelberg, Germany",//$NON-NLS-1$
          new URI("http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"));//$NON-NLS-1$

      R1991ATSPL = new BibArticle(gha,//
          "TSPLIB \u2012 A Traveling Salesman Problem Library",//$NON-NLS-1$
          new BibDate(1991, EBibQuarter.FALL),//
          "ORSA Journal on Computing",//$NON-NLS-1$
          "3", "4",//$NON-NLS-1$//$NON-NLS-2$ 
          "376", "384",//$NON-NLS-1$//$NON-NLS-2$
          null, "10.1287/ijoc.3.4.376");//$NON-NLS-1$

      W2003ROCFTB = new BibWebsite(
          DescTSP.WJC_A,//
          "Results of Concorde for TSPLib Benchmark",//$NON-NLS-1$
          new BibDate(2003, EBibMonth.DECEMBER), //
          "Georgia Institute of Technology, H. Milton Stewart School of Industrial and Systems Engineering",//$NON-NLS-1$
          "Atlanta, GA, USA",//$NON-NLS-1$
          new URI(
              "http://www.tsp.gatech.edu/concorde/benchmarks/bench99.html"));//$NON-NLS-1$

    } catch (final URISyntaxException u) {
      throw new RuntimeException(u);
    }
  }

  /** the citation for tsp lib */
  private static final BibRecord[] CITES = { DescBenchmarks.R1995T9,
      DescBenchmarks.DACO1995TSPLIB, DescBenchmarks.R1991ATSPL };

  /**
   * create!
   * 
   * @param owner
   *          the macro's owner
   */
  DescBenchmarks(final Module owner) {
    super("benchmarks", owner, true); //$NON-NLS-1$
  }

  /**
   * cite the tsp lib
   * 
   * @param mode
   *          the citation mode
   * @param dest
   *          the dest
   * @throws IOException
   *           if i/o fails
   */
  public static final void citeTSPLib(final ECitationMode mode,
      final AbstractTextComplex dest) throws IOException {
    dest.cite(mode, DescBenchmarks.CITES);
  }

  /** {@inheritDoc} */
  @Override
  protected void initialize(final Header header, final ExperimentSet data)
      throws IOException {//
    super.initialize(header, data);
    Macros.SCALE.define(header);
    Macros.F_OPTIMAL.define(header);
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSectionTitle(final SectionTitle title,
      final ExperimentSet data) throws IOException {
    title.write("The Benchmark Cases"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSectionBody(final SectionBody body,
      final ExperimentSet data) throws IOException {

    body.write(//
    "For benchmarking TSP solving algorithms, we use the TSPLib benchmark suite"); //$NON-NLS-1$
    body.cite(ECitationMode.BY_ID_IN_SENTENCE, DescBenchmarks.R1995T9,
        DescBenchmarks.DACO1995TSPLIB, DescBenchmarks.R1991ATSPL);

    body.write(". This suite has "); //$NON-NLS-1$
    body.writeInt(Instance.SYMMETRIC_INSTANCES.size());
    body.write(" symmetric instances with dimensions "); //$NON-NLS-1$

    body.macroInvoke(Macros.SCALE);
    body.write(" ranging from "); //$NON-NLS-1$
    body.writeInt(Instance.SYMMETRIC_INSTANCES.first().n());
    body.write(" to "); //$NON-NLS-1$
    body.writeInt(Instance.SYMMETRIC_INSTANCES.last().n());

    body.write(" cities and "); //$NON-NLS-1$

    body.writeInt(Instance.ASYMMETRIC_INSTANCES.size());
    body.write(" asymmetric instances with dimensions "); //$NON-NLS-1$    
    body.macroInvoke(Macros.SCALE);
    body.write(" ranging from "); //$NON-NLS-1$
    body.writeInt(Instance.ASYMMETRIC_INSTANCES.first().n());
    body.write(" to "); //$NON-NLS-1$
    body.writeInt(Instance.ASYMMETRIC_INSTANCES.last().n());

    body.write(" cities. The global optima and their objective values "); //$NON-NLS-1$
    body.macroInvoke(Macros.F_OPTIMAL);
    body.write(" are all known"); //$NON-NLS-1$
    body.cite(ECitationMode.BY_ID_IN_SENTENCE, DescBenchmarks.W2003ROCFTB);
    body.write(//
    ", which makes it particularly easy to check how well an algorithm performs.");//$NON-NLS-1$

    super.writeSectionBody(body, data);
  }
}
