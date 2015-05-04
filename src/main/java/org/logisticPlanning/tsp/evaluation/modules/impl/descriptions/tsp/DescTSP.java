package org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.tsp;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.modules.impl.macros.Macros;
import org.logisticPlanning.tsp.evaluation.modules.spec.DescriptionModule;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.utils.document.spec.ECitationMode;
import org.logisticPlanning.utils.document.spec.EMathName;
import org.logisticPlanning.utils.document.spec.Emphasize;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.InlineMath;
import org.logisticPlanning.utils.document.spec.MathName;
import org.logisticPlanning.utils.document.spec.SectionBody;
import org.logisticPlanning.utils.document.spec.SectionTitle;
import org.logisticPlanning.utils.document.spec.bib.BibAuthor;
import org.logisticPlanning.utils.document.spec.bib.BibAuthors;
import org.logisticPlanning.utils.document.spec.bib.BibBook;
import org.logisticPlanning.utils.document.spec.bib.BibDate;
import org.logisticPlanning.utils.document.spec.bib.EBibMonth;

/**
 * A description of the TSP. <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_ABCC2006TTSPACS" /><a
 * href="http://www.research.att.com/people/Applegate_David_L">David Lee
 * Applegate</a>, <a href="http://www.caam.rice.edu/~bixby/">Robert E.
 * Bixby</a>, <a href="http://users.encs.concordia.ca/~chvatal/">Va&#353;ek
 * Chv&#225;tal</a>, and&nbsp;<a
 * href="http://www2.isye.gatech.edu/~wcook/">William John Cook</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;The Traveling
 * Salesman Problem: A Computational Study,&rdquo;</span>
 * February&nbsp;2007, Princeton Series in Applied Mathematics, Princeton,
 * NJ, USA: Princeton University Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0691129932">0-691-12993-2</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780691129938">978-0-691-
 * 12993-8</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=nmF4rVNJMVsC">nmF4rVNJMVsC</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=vhsJbqomDuIC">vhsJbqomDuIC
 * </a></div></li>
 * <li><div><span id="cite_G2008TSP" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Traveling Salesman
 * Problem,&rdquo;</span> September&nbsp;2008, <a
 * href="http://www.stat.unipg.it/sigi/corsi.php?corso=49">Federico
 * Greco</a>, editor, Vienna, Austria: IN-TECH Education and Publishing.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/9789537619107">978-953-
 * 7619-10-7</a>. <div>links: [<a href=
 * "http://intechweb.org/downloadfinal.php?is=978-953-7619-10-7&amp;amp;type=B"
 * >1</a>] and&nbsp;[<a href=
 * "http://www.degraf.ufpr.br/docentes/paulo/publicacoes_arquivos/TravellingSalesmanProblem.pdf"
 * >2</a>]</div></div></li>
 * <li><div><span id="cite_LLKS1985TTSPAGTOCO" /><a
 * href="https://en.wikipedia.org/wiki/Eugene_Lawler">Eugene Leighton
 * (Gene) Lawler</a>, <a
 * href="https://en.wikipedia.org/wiki/Jan_Karel_Lenstra">Jan Karel
 * Lenstra</a>, <a
 * href="https://en.wikipedia.org/wiki/Alexander_Rinnooy_Kan">Alexander
 * Hendrik George Rinnooy Kan</a>, and&nbsp;<a
 * href="http://people.orie.cornell.edu/shmoys/">David B. Shmoys</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;The Traveling
 * Salesman Problem: A Guided Tour of Combinatorial
 * Optimization,&rdquo;</span> September&nbsp;1985, Estimation, Simulation,
 * and Control &#8211; Wiley-Interscience Series in Discrete Mathematics
 * and Optimization, Chichester, West Sussex, UK: Wiley Interscience.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0471904139">0-471-90413-9</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780471904137">978-0-471-
 * 90413-7</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/85003158">85003158</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/260186267">260186267</a>, <a
 * href="https://www.worldcat.org/oclc/11756468">11756468</a>, <a
 * href="https://www.worldcat.org/oclc/631997749">631997749</a>, <a
 * href="https://www.worldcat.org/oclc/468006920">468006920</a>, <a
 * href="https://www.worldcat.org/oclc/611882247">611882247</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/oclc/488801427">488801427</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=BXBGAAAAYAAJ">BXBGAAAAYAAJ</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=02528360X">02528360X</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=011848790">011848790</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=042459834">042459834</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=175957169">175957169</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=042637112">042637112</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=043364950">043364950</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=156527677">156527677</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=078586313">078586313</a>, and&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=190388226">190388226</a></div></li>
 * <li><div><span id="cite_GP2004TTSPAIV" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;The Traveling
 * Salesman Problem and its Variations,&rdquo;</span> 2002&ndash;2004, <a
 * href=
 * "http://www.rhul.ac.uk/computerscience/staffdirectory/ggutin/home.aspx"
 * >Gregory Z. Gutin</a> and&nbsp;<a
 * href="http://www.sfu.ca/~apunnen/">Abraham P. Punnen</a>, editors,
 * volume 12 of Combinatorial Optimization, Norwell, MA, USA: Kluwer
 * Academic Publishers. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0306482134">0-306-48213-4</a>, <a
 * href="https://www.worldcat.org/isbn/1402006640">1-4020-0664-0</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781402006647">978-1-4020
 * -0664-7</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/b101971">10.1007/b101971</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/55085594">55085594</a>, <a
 * href="https://www.worldcat.org/oclc/818988416">818988416</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/oclc/803682928">803682928</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=TRYkPg_Xf20C">TRYkPg_Xf20C</a>,
 * <a
 * href="http://books.google.com/books?id=mQ5RxzsuOXAC">mQ5RxzsuOXAC</a>,
 * <a
 * href="http://books.google.com/books?id=5RuNx2TIbIcC">5RuNx2TIbIcC</a>,
 * and&nbsp;<a
 * href="http://books.google.com/books?id=pfRSPwAACAAJ">pfRSPwAACAAJ
 * </a></div></li>
 * <li><div><span id="cite_C2011IPOTTSMATLOC" /><a
 * href="http://www2.isye.gatech.edu/~wcook/">William John Cook</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;In Pursuit of the
 * Traveling Salesman: Mathematics at the Limits of
 * Computation,&rdquo;</span> 2011, Princeton, NJ, USA: Princeton
 * University Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0691152705">0691152705</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780691152707">9780691152707</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=S3bxbr_-qhYC"
 * >S3bxbr_-qhYC</a></div></li>
 * </ol>
 */
public final class DescTSP extends DescriptionModule {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** william j cook */
  static final BibAuthor WJC = new BibAuthor("William John", "Cook"); //$NON-NLS-1$ //$NON-NLS-2$

  /** gregory gutin */
  static final BibAuthor GG = new BibAuthor("Gregory Z.", "Gutin"); //$NON-NLS-1$ //$NON-NLS-2$
  /** abraham p punnen */
  static final BibAuthor APP = new BibAuthor("Abraham P.", "Punnen");//$NON-NLS-1$ //$NON-NLS-2$

  /** william j cook */
  static final BibAuthors WJC_A = new BibAuthors(DescTSP.WJC);

  /**
   * the book&nbsp;[<a href="#cite_ABCC2006TTSPACS"
   * style="font-weight:bold">1</a>]
   */
  private static final BibBook ABCC2006TTSPACS;
  /**
   * the book&nbsp;[<a href="#cite_G2008TSP"
   * style="font-weight:bold">2</a>]
   */
  private static final BibBook G2008TSP;
  /**
   * the book&nbsp;[<a href="#cite_LLKS1985TTSPAGTOCO"
   * style="font-weight:bold">3</a>]
   */
  private static final BibBook LLKS1985TTSPAGTOCO;
  /**
   * the book&nbsp;[<a href="#cite_GP2004TTSPAIV"
   * style="font-weight:bold">4</a>]
   */
  static final BibBook GP2004TTSPAIV;
  /**
   * the book&nbsp;[<a href="#cite_C2011IPOTTSMATLOC"
   * style="font-weight:bold">5</a>]
   */
  private static final BibBook C2011IPOTTSMATLOC;

  static {

    ABCC2006TTSPACS = new BibBook(//
        new BibAuthors(//
            new BibAuthor("David Lee", "Applegate"), //$NON-NLS-1$ //$NON-NLS-2$
            new BibAuthor("Robert E.", "Bixby"),//$NON-NLS-1$ //$NON-NLS-2$
            new BibAuthor("Va\u0161ek", "Chv\u00e1tal"),//$NON-NLS-1$ //$NON-NLS-2$
            DescTSP.WJC),//
            "The Traveling Salesman Problem: A Computational Study",//$NON-NLS-1$
            new BibDate(2002, EBibMonth.FEBRUARY),//
            BibAuthors.EMPTY,//
            "Princeton University Press",//$NON-NLS-1$
            "Princeton, NJ, USA",//$NON-NLS-1$
            "Princeton Series in Applied Mathematics",//$NON-NLS-1$
            null, null, null, null);

    try {
      G2008TSP = new BibBook(
          //
          new BibAuthors(//
              new BibAuthor("Federico", "Greco") //$NON-NLS-1$ //$NON-NLS-2$
              ),//
              "Traveling Salesman Problem",//$NON-NLS-1$
              new BibDate(2008, EBibMonth.SEPTEMBER),//
              BibAuthors.EMPTY,//
              "IN-TECH Education and Publishing",//$NON-NLS-1$
              "Vienna, Austria",//$NON-NLS-1$
              null,
              null,
              null,//
              new URI(
                  "http://www.degraf.ufpr.br/docentes/paulo/publicacoes_arquivos/TravellingSalesmanProblem.pdf"),//$NON-NLS-1$
                  null);
    } catch (final URISyntaxException e) {
      throw new RuntimeException(e);
    }

    LLKS1985TTSPAGTOCO = new BibBook(
        //
        new BibAuthors(
            //
            new BibAuthor("Eugene Leighton (Gene)", "Lawler"), //$NON-NLS-1$ //$NON-NLS-2$
            new BibAuthor("Jan Karel", "Lenstra"),//$NON-NLS-1$ //$NON-NLS-2$
            new BibAuthor("Alexander Hendrik George", "Rinnooy Kan"),//$NON-NLS-1$ //$NON-NLS-2$
            new BibAuthor("David B.", "Shmoys") //$NON-NLS-1$ //$NON-NLS-2$
            ),//
            "The Traveling Salesman Problem: A Guided Tour of Combinatorial Optimization",//$NON-NLS-1$
            new BibDate(1989, EBibMonth.SEPTEMBER),//
            BibAuthors.EMPTY,//
            "Wiley Interscience",//$NON-NLS-1$
            "Chichester, West Sussex, UK",//$NON-NLS-1$
            "Wiley-Interscience Series in Discrete Mathematics;  Estimation, Simulation, and Control ‒ Wiley-Interscience Series in Discrete Mathematics and Optimization",//$NON-NLS-1$
            null, null, null, null);

    GP2004TTSPAIV = new BibBook(//
        new BibAuthors(DescTSP.GG, DescTSP.APP),//
        "The Traveling Salesman Problem and its Variations",//$NON-NLS-1$
        new BibDate(2002),//
        BibAuthors.EMPTY,//
        "Kluwer Academic Publishers",//$NON-NLS-1$
        "Norwell, MA, USA",//$NON-NLS-1$
        "Combinatorial Optimization",//$NON-NLS-1$
        "12",//$NON-NLS-1$
        null, null, null);

    C2011IPOTTSMATLOC = new BibBook(
        DescTSP.//
        WJC_A,//
        "In Pursuit of the Traveling Salesman: Mathematics at the Limits of Computation",//$NON-NLS-1$
        new BibDate(2011),//
        BibAuthors.EMPTY,//
        "Princeton University Press",//$NON-NLS-1$
        "Princeton, NJ, USA",//$NON-NLS-1$
        null, null, null, null, null);
  }

  /**
   * create!
   *
   * @param owner
   *          the owner
   */
  public DescTSP(final Module owner) {
    super("TSP", owner, false); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @SuppressWarnings("unused")
  @Override
  protected void createChildModules() {
    super.createChildModules();
    new DescBenchmarks(this);
    new DescRepresentations(this);
    new DescTimeMeasures(this);
    new DescRelativeObjectiveValues(this);
    new DescSystem(this);
    new DescLogData(this);
  }

  /** {@inheritDoc} */
  @Override
  protected void initialize(final Header header, final ExperimentSet data)
      throws IOException {//
    super.initialize(header, data);
    Macros.SCALE.define(header);
    Accessor.F.define(header);
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSectionTitle(final SectionTitle title,
      final ExperimentSet data) throws IOException {
    title.write("Traveling Salesman Problems"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSectionBody(final SectionBody body,
      final ExperimentSet data) throws IOException {

    body.write("The traveling salesman problem (TSP)");//$NON-NLS-1$
    body.cite(ECitationMode.BY_ID_IN_SENTENCE, DescTSP.ABCC2006TTSPACS,
        DescTSP.G2008TSP, DescTSP.LLKS1985TTSPAGTOCO,
        DescTSP.GP2004TTSPAIV, DescTSP.C2011IPOTTSMATLOC);
    body.write(//
        " is maybe the most well-known combinatorial optimization task. A TSP is defined as a fully-connected graph with ");//$NON-NLS-1$
    body.macroInvoke(Macros.SCALE);
    body.write(//
        " nodes, which are connected with edges that have integer-valued weights, the distances. A candidate solution of a TSP is a tour that visits each node in the graph exactly once and returns back to its starting node. The goal is to find the shortest such tour, i.e., the objective function "); //$NON-NLS-1$
    Accessor.F.writeShortName(body);
    body.write(//
        " corresponds to the total tour length/cost (including the trip back to the origin of the tour) and is subject to minimization. The optimization version of the TSP (which is the focus of this work) is "); //$NON-NLS-1$
    try (Emphasize em = body.emphasize()) {
      em.write("NP-hard");//$NON-NLS-1$
    }
    body.cite(ECitationMode.BY_ID_IN_SENTENCE, DescTSP.GP2004TTSPAIV);
    body.writeChar('.');
    body.writeLinebreak();

    body.write(//
        "We can distinguish symmetric TSPs, where the edges are undirected and have the same costs in both directions and asymmetric TSPs, where the edges are directed and traveling from node "); //$NON-NLS-1$

    try (InlineMath im = body.inlineMath()) {
      try (MathName mn = im.mathName(EMathName.SCALAR)) {
        mn.writeChar('A');
      }
    }

    body.write(" to node "); //$NON-NLS-1$

    try (InlineMath im = body.inlineMath()) {
      try (MathName mn = im.mathName(EMathName.SCALAR)) {
        mn.writeChar('B');
      }
    }

    body.write(//
        " may have a different cost than traveling from "); //$NON-NLS-1$

    try (InlineMath im = body.inlineMath()) {
      try (MathName mn = im.mathName(EMathName.SCALAR)) {
        mn.writeChar('B');
      }
    }

    body.write(" to "); //$NON-NLS-1$

    try (InlineMath im = body.inlineMath()) {
      try (MathName mn = im.mathName(EMathName.SCALAR)) {
        mn.writeChar('A');
      }
    }

    body.write(//
        ". Although our benchmarking framework focuses on symmetric TSPs, you can also use it to benchmark your algorithm for the asymmetric TSPLIB instances as well."); //$NON-NLS-1$

    super.writeSectionBody(body, data);
  }
}
