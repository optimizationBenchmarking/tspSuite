package org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.tsp;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.modules.impl.AccessorSequence;
import org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.eval.DescQualityMeasures;
import org.logisticPlanning.tsp.evaluation.modules.impl.macros.Macros;
import org.logisticPlanning.tsp.evaluation.modules.spec.DescriptionModule;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.utils.document.spec.ECitationMode;
import org.logisticPlanning.utils.document.spec.EMathOp;
import org.logisticPlanning.utils.document.spec.ESequenceType;
import org.logisticPlanning.utils.document.spec.Emphasize;
import org.logisticPlanning.utils.document.spec.Enumeration;
import org.logisticPlanning.utils.document.spec.EnumerationItem;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.InlineMath;
import org.logisticPlanning.utils.document.spec.MathOp;
import org.logisticPlanning.utils.document.spec.MathOpParam;
import org.logisticPlanning.utils.document.spec.SectionBody;
import org.logisticPlanning.utils.document.spec.SectionTitle;
import org.logisticPlanning.utils.document.spec.bib.BibAuthor;
import org.logisticPlanning.utils.document.spec.bib.BibAuthors;
import org.logisticPlanning.utils.document.spec.bib.BibDate;
import org.logisticPlanning.utils.document.spec.bib.BibInCollection;
import org.logisticPlanning.utils.document.spec.bib.BibInProceedings;
import org.logisticPlanning.utils.document.spec.bib.BibProceedings;
import org.logisticPlanning.utils.document.spec.bib.BibWebsite;
import org.logisticPlanning.utils.document.spec.bib.EBibMonth;

/**
 * A description of the time measures <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_JMG20088DICTTSP" /><a
 * href="https://en.wikipedia.org/wiki/David_S._Johnson">David Stifler
 * Johnson</a> and&nbsp;<a
 * href="https://www.amherst.edu/people/facstaff/lamcgeoch">Lyle A.
 * McGeoch</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;8th DIMACS
 * Implementation Challenge: The Traveling Salesman Problem,&rdquo;</span>
 * (Website), December&nbsp;12, 2008, Florham Park, NJ, USA: AT&amp;T Labs
 * Research &#8212; Leading Invention, Driving Innovation. <div>link: [<a
 * href="http://www2.research.att.com/~dsj/chtsp/">1</a>]</div></div></li>
 * <li><div><span id="cite_JMG2004EAOHFTS" /><a
 * href="https://en.wikipedia.org/wiki/David_S._Johnson">David Stifler
 * Johnson</a> and&nbsp;<a
 * href="https://www.amherst.edu/people/facstaff/lamcgeoch">Lyle A.
 * McGeoch</a>: <span style="font-weight:bold">&ldquo;Experimental Analysis
 * of Heuristics for the STSP,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">The Traveling Salesman
 * Problem and its Variations</span>, chapter 369&ndash;443, pages
 * 369&ndash;443, <a href=
 * "http://www.rhul.ac.uk/computerscience/staffdirectory/ggutin/home.aspx">
 * Gregory Z. Gutin</a> and&nbsp;<a
 * href="http://www.sfu.ca/~apunnen/">Abraham P. Punnen</a>, editors,
 * volume 12 of Combinatorial Optimization, Norwell, MA, USA: Kluwer
 * Academic Publishers. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0306482134">0-306-48213-4</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781402006647">978-1-4020
 * -0664-7</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/0-306-48213-4_9"
 * >10.1007/0-306-48213-4_9</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/55085594">55085594</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=TRYkPg_Xf20C">TRYkPg_Xf20C</a>.
 * <div>link: [<a
 * href="http://www2.research.att.com/~dsj/papers/stspchap.pdf">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.15.9438">10.1
 * .1.15 .9438</a></div></div></li>
 * <li><div><span id="cite_JGMCYZZ2004EAOHFTA" /><a
 * href="https://en.wikipedia.org/wiki/David_S._Johnson">David Stifler
 * Johnson</a>, <a href=
 * "http://www.rhul.ac.uk/computerscience/staffdirectory/ggutin/home.aspx"
 * >Gregory Z. Gutin</a>, <a
 * href="https://www.amherst.edu/people/facstaff/lamcgeoch">Lyle A.
 * McGeoch</a>, Anders Yeo, <a
 * href="http://www.cs.wustl.edu/~zhang/">Weixiong Zhang</a>,
 * and&nbsp;Alexei Zverovitch: <span
 * style="font-weight:bold">&ldquo;Experimental Analysis of Heuristics for
 * the ATSP,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">The Traveling Salesman
 * Problem and its Variations</span>, chapter 445&ndash;487, pages
 * 445&ndash;487, <a href=
 * "http://www.rhul.ac.uk/computerscience/staffdirectory/ggutin/home.aspx">
 * Gregory Z. Gutin</a> and&nbsp;<a
 * href="http://www.sfu.ca/~apunnen/">Abraham P. Punnen</a>, editors,
 * volume 12 of Combinatorial Optimization, Norwell, MA, USA: Kluwer
 * Academic Publishers. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0306482134">0-306-48213-4</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781402006647">978-1-4020
 * -0664-7</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/0-306-48213-4_10">10.1007/0-306-48213
 * -4_10</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/55085594">55085594</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=TRYkPg_Xf20C">TRYkPg_Xf20C</a>.
 * <div>link: [<a
 * href="http://www2.research.att.com/~dsj/papers/atspchap.pdf">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.139.4091">
 * 10.1.1 .139.4091</a></div></div></li>
 * <li><div><span id="cite_HS1998ELVAPAR" /><a
 * href="http://www.cs.ubc.ca/~hoos/">Holger H. Hoos</a> and&nbsp;<a
 * href="http://iridia.ulb.ac.be/~stuetzle/">Thomas St&#252;tzle</a>: <span
 * style="font-weight:bold">&ldquo;Evaluating Las Vegas Algorithms &#8210;
 * Pitfalls and Remedies,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the 14th
 * Conference on Uncertainty in Artificial Intelligence (UAI'98)</span>,
 * July&nbsp;24&ndash;26, 1998, Madison, WI, USA, pages 238&ndash;245,
 * Gregory F. Cooper and&nbsp;Serafin Moral, editors, San Francisco, CA,
 * USA: Morgan Kaufmann Publishers Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/155860555X">1-55860-555-X</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781558605558">978-1-55860
 * -555-8</a>. <div>link: [<a href=
 * "http://www.intellektik.informatik.tu-darmstadt.de/TR/1998/98-02.ps.Z"
 * >1</a>]</div></div></li>
 * </ol>
 */
public final class DescTimeMeasures extends DescriptionModule {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * the book&nbsp;[<a href="#cite_JMG20088DICTTSP"
   * style="font-weight:bold">1</a>]
   */
  private static final BibWebsite JMG20088DICTTSP;

  /**
   * the in-collection&nbsp;[<a href="#cite_JMG2004EAOHFTS"
   * style="font-weight:bold">2</a>]
   */
  static final BibInCollection JMG2004EAOHFTS;

  /**
   * the in-collection&nbsp;[<a href="#cite_JGMCYZZ2004EAOHFTA"
   * style="font-weight:bold">3</a>]
   */
  private static final BibInCollection JGMCYZZ2004EAOHFTA;

  /**
   * the in-proceedings&nbsp;[<a href="#cite_HS1998ELVAPAR"
   * style="font-weight:bold">4</a>]
   */
  public static final BibInProceedings HS1998ELVAPAR;

  /** David Stiffler Johnson &ndash; here presented as a constant */
  static final BibAuthor DSJ = new BibAuthor("David Stifler", "Johnson");//$NON-NLS-1$ //$NON-NLS-2$;

  /** Lyle A. McGeoch &ndash; here presented as a constant */
  static final BibAuthor LAMG = new BibAuthor("Lyle A.", "McGeoch");//$NON-NLS-1$ //$NON-NLS-2$

  /**
   * Holder H. Hoos &ndash; here presented as a constant (if I knew what
   * the &quot;H.&quot; stands for, I would write it out as well!)
   */
  public static final BibAuthor HOLGER_H_HOOS = new BibAuthor(
      "Holger H.", "Hoos");//$NON-NLS-1$ //$NON-NLS-2$

  static {
    final BibAuthors dl;

    dl = new BibAuthors(DescTimeMeasures.DSJ, DescTimeMeasures.LAMG);

    try {

      JMG20088DICTTSP = new BibWebsite(
          dl,//
          "8th DIMACS Implementation Challenge: The Traveling Salesman Problem",//$NON-NLS-1$
          new BibDate(2008, EBibMonth.DECEMBER, 12), //
          "AT&T Labs Research — Leading Invention, Driving Innovation",//$NON-NLS-1$
          "Florham Park, NJ, USA",//$NON-NLS-1$
          new URI("http://www2.research.att.com/~dsj/chtsp/"));//$NON-NLS-1$
    } catch (final URISyntaxException use) {
      throw new RuntimeException(use);
    }
    try {
      JGMCYZZ2004EAOHFTA = new BibInCollection(
          new BibAuthors(
          //
              DescTimeMeasures.DSJ, DescTSP.GG, DescTimeMeasures.LAMG,//
              new BibAuthor("Anders", "Yeo"),//$NON-NLS-1$ //$NON-NLS-2$
              new BibAuthor("Weixiong", "Zhang"),//$NON-NLS-1$ //$NON-NLS-2$
              new BibAuthor("Alexei", "Zverovitch")//$NON-NLS-1$ //$NON-NLS-2$           
          ),//
          "Experimental Analysis of Heuristics for the ATSP",//$NON-NLS-1$
          DescTSP.GP2004TTSPAIV,
          "445",//$NON-NLS-1$
          "487",//$NON-NLS-1$
          "10",//$NON-NLS-1$
          new URI("http://www2.research.att.com/~dsj/papers/atspchap.pdf"),//$NON-NLS-1$
          "10.1007/0-306-48213-4_10");//$NON-NLS-1$
    } catch (final URISyntaxException use) {
      throw new RuntimeException(use);
    }
    try {
      JMG2004EAOHFTS = new BibInCollection(
          dl,//
          "Experimental Analysis of Heuristics for the STSP",//$NON-NLS-1$
          DescTSP.GP2004TTSPAIV,
          "369",//$NON-NLS-1$
          "443",//$NON-NLS-1$
          "9",//$NON-NLS-1$
          new URI("http://www2.research.att.com/~dsj/papers/stspchap.pdf"),//$NON-NLS-1$
          "10.1007/0-306-48213-4_9");//$NON-NLS-1$

    } catch (final URISyntaxException use) {
      throw new RuntimeException(use);
    }

    try {
      HS1998ELVAPAR = new BibInProceedings(
          new BibAuthors(//
              DescTimeMeasures.HOLGER_H_HOOS,//
              new BibAuthor("Thomas", "St\u00fctzle")//$NON-NLS-1$ //$NON-NLS-2$           
          ),//
          "Evaluating Las Vegas Algorithms \u2012 Pitfalls and Remedies",//$NON-NLS-1$
          new BibProceedings(//
              "Proceedings of the 14th Conference on Uncertainty in Artificial Intelligence (UAI'93)",//$NON-NLS-1$
              new BibDate(1998, EBibMonth.JULY, 24),//
              new BibDate(1998, EBibMonth.JULY, 26),//
              new BibAuthors(//
                  new BibAuthor("Gregory F.", "Cooper"),//$NON-NLS-1$ //$NON-NLS-2$
                  new BibAuthor("Serafin", "Moral")//$NON-NLS-1$ //$NON-NLS-2$           
              ),//
              "Madison, WI, USA",//$NON-NLS-1$
              "Morgan Kaufmann Publishers Inc.",//$NON-NLS-1$
              "San Francisco, CA, USA",//$NON-NLS-1$
              null, null,//
              null, null//
          ),//
          "238", "245", null,//$NON-NLS-1$//$NON-NLS-2$   
          new URI(
              "http://www.intellektik.informatik.tu-darmstadt.de/TR/1998/98-02.ps.Z"),//$NON-NLS-1$
          null);
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
  DescTimeMeasures(final Module owner) {
    super("timeMeasures", owner, false); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected void initialize(final Header header, final ExperimentSet data)
      throws IOException {//

    super.initialize(header, data);

    Accessor.DE.define(header);
    Accessor.FE.define(header);
    Accessor.RUNTIME.define(header);
    Accessor.NORMALIZED_RUNTIME.define(header);
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSectionTitle(final SectionTitle title,
      final ExperimentSet data) throws IOException {
    title.write("Time Measures"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSectionBody(final SectionBody body,
      final ExperimentSet data) throws IOException {

    body.write("We want to explore, measuring, and compare the progress of algorithms over time. The first not-so-obvious question arising in this context is: What is "); //$NON-NLS-1$
    try (Emphasize emph = body.emphasize()) {
      emph.write("time");//$NON-NLS-1$
    }
    body.write("? Generally, there are four answers to this question:"); //$NON-NLS-1$

    try (Enumeration enu = body.enumeration()) {

      try (EnumerationItem enui = enu.item()) {
        enui.write("The ");//$NON-NLS-1$
        Accessor.RUNTIME.writeLongName(enui, true);
        enui.write(" (");//$NON-NLS-1$
        Accessor.RUNTIME.writeShortName(enui, true);
        enui.write(//
        "): This measure highly depends on the machine on which the algorithm is executed and can only be measured up to a certain granularity (e.g., 10ms), which may be inappropriate for small-scale problems. However, for experiments executed on the same machine, it also is somewhat fair and more or less correctly represents the respective algorithms overheads. Furthermore, many results from literature are accompanied with measurements of the runtime, see, e.g.,");//$NON-NLS-1$
        enui.cite(ECitationMode.BY_ID_IN_SENTENCE,
            DescTimeMeasures.JMG20088DICTTSP);
        enui.writeChar('.');
      }

      try (EnumerationItem enui = enu.item()) {

        enui.write("The ");//$NON-NLS-1$
        Accessor.FE.writeLongName(enui, true);
        enui.write(" (");//$NON-NLS-1$
        Accessor.FE.writeShortName(enui, true);
        enui.write(//
        "), i.e., the number of candidate solutions which have been tested by the optimization process. This measure is maybe the most commonly used one in optimization algorithm benchmarking");//$NON-NLS-1$
        enui.cite(ECitationMode.BY_ID_IN_SENTENCE,
            DescQualityMeasures.HAFR2012RPBBOBES,
            DescQualityMeasures.AH2005PEOAALSEA,
            DescQualityMeasures.TLSYW2010BFFTC2SSACOLSGO,
            DescQualityMeasures.TYW2012SSOECFLSGO);
        enui.write(//
        ". Its advantage is that it is independent of is independent of the underlying machine");//$NON-NLS-1$
        enui.cite(ECitationMode.BY_ID_IN_SENTENCE,
            DescTimeMeasures.HS1998ELVAPAR);
        enui.write(//
        ". However, when solving TSPs, it may not be entirely fair. Some algorithms may always create entirely new candidate solutions which need to be evaluated/traversed completely in order to compute their length. This then will takes ");//$NON-NLS-1$
        enui.macroInvoke(Macros.SCALE);
        enui.write(//
        " separate edge cost (node distance) evaluations, i.e., has complexity ");//$NON-NLS-1$
        try (InlineMath m = enui.inlineMath()) {
          try (MathOp o = m.mathOp(EMathOp.COMPLEX_BIG_O)) {
            try (MathOpParam p = o.mathOpParam()) {
              p.macroInvoke(Macros.SCALE);
            }
          }
        }
        enui.write(//
        ". Other algorithms may update candidate solutions by, e.g., swapping two nodes, which may only require ");//$NON-NLS-1$
        enui.writeIntInText(8, false);
        enui.write(//
        " distance evaluations, i.e., happen in ");//$NON-NLS-1$
        try (InlineMath m = enui.inlineMath()) {
          try (MathOp o = m.mathOp(EMathOp.COMPLEX_BIG_O)) {
            try (MathOpParam p = o.mathOpParam()) {
              p.writeInt(1);
            }
          }
        }
        enui.write(//
        ". The actual runtime of a function evaluation may therefore fastly differ. Additionally, we should consider that some TSP problems are over very large-scale, having ");//$NON-NLS-1$
        enui.writeIntInText(10000, false);
        enui.write(//
        " cities and more. This means that distance matrices cannot be used anymore, as they consume too much memory. Then, every distance calculation becomes more costly and it makes a big difference if an algorithm requires ");//$NON-NLS-1$
        enui.writeIntInText(8, false);
        enui.write(" or ");//$NON-NLS-1$
        enui.macroInvoke(Macros.SCALE);
        Accessor.DE.writeLongName(enui, true);
        enui.write(" to produce a new candidate solution. The number of ");//$NON-NLS-1$
        Accessor.FE.writeShortName(enui, true);
        enui.write(" alone may thus be misleading as time measure.");//$NON-NLS-1$
      }

      try (EnumerationItem enui = enu.item()) {
        enui.write("The third possible measure, the ");//$NON-NLS-1$
        Accessor.DE.writeLongName(enui, true);
        enui.write(" (");//$NON-NLS-1$
        Accessor.DE.writeShortName(enui, true);
        enui.write(//
        "), the number of invocations of the distance function, i.e., how often the distance between any two nodes was computed. This measure has the smallest granularity and circumvents the problem described above: It is machine-independent and unbiased in terms of the actual complexity of a ");//$NON-NLS-1$
        Accessor.FE.writeLongName(enui, false);
        enui.write(". It's drawback is that it is a measure of complexity, but not of ");//$NON-NLS-1$
        try (Emphasize emph = enui.emphasize()) {
          emph.write("time");//$NON-NLS-1$
        }
        enui.write(". Although it will correctly reflect the way an algorithm accesses the problem information, it cannot account for the algorithm's internal complexity. A TSP solver may, for instance, consume only few ");//$NON-NLS-1$
        Accessor.DE.writeShortName(enui, true);
        enui.write(" or ");//$NON-NLS-1$
        Accessor.FE.writeShortName(enui, true);
        enui.write(" to reach a good solution quality, but be remarkably slow since it runs several complex Machine Learning approaches at the same time, for instance. The number of ");//$NON-NLS-1$
        Accessor.DE.writeShortName(enui, true);
        enui.write(" alone may thus also not be sufficient to describe an algorithm's performance.");//$NON-NLS-1$
      }

      try (EnumerationItem enui = enu.item()) {
        enui.write("The ");//$NON-NLS-1$
        Accessor.NORMALIZED_RUNTIME.writeLongName(enui, true);
        enui.write(" (");//$NON-NLS-1$
        Accessor.NORMALIZED_RUNTIME.writeShortName(enui, true);
        enui.write(//
        ") tries to provide a measure of overall algorithm time progress which is unbiased by the underlying machine's performance. The general idea is to run a standardized algorithm on the machine of experiments prior to the actual experiments. The runtime of the TSP solvers in the real experiments can then be divided (normalized) with the priorly-measured runtime of the standardized algorithm. ");//$NON-NLS-1$
        enui.cite(ECitationMode.BY_ID_IN_SENTENCE,
            DescTimeMeasures.JMG20088DICTTSP,
            DescTimeMeasures.JMG2004EAOHFTS,
            DescTimeMeasures.JGMCYZZ2004EAOHFTA);
        enui.write(//
        " measure the runtime of an algorithm for a subset of their benchmark instances. In our experimental framework, a simple double-ended nearest neighbor heuristic ");//$NON-NLS-1$
        enui.cite(ECitationMode.BY_ID_IN_SENTENCE,
            DescTimeMeasures.JMG20088DICTTSP,
            DescTimeMeasures.JMG2004EAOHFTS,
            DescTimeMeasures.JGMCYZZ2004EAOHFTA);
        enui.write(//
        " is applied to each benchmark instance directly before solving it with an algorithm. It is run often enough to get a stable runtime measure which is then used to normalize the values of ");//$NON-NLS-1$
        Accessor.RUNTIME.writeShortName(enui, false);
        enui.write(" to ");//$NON-NLS-1$
        Accessor.NORMALIZED_RUNTIME.writeShortName(enui, false);
        enui.write(". Although this measure fixes the machine-bias of ");//$NON-NLS-1$
        Accessor.RUNTIME.writeShortName(enui, false);
        enui.write(", it only represents the runtime under the ");//$NON-NLS-1$
        try (Emphasize emph = enui.emphasize()) {
          emph.write("current");//$NON-NLS-1$
        }
        enui.write(" conditions. If the algorithms later are applied in a scenario with very slow distance computions, for example, an algorithm measured as slow may actually be the fastest choice.");//$NON-NLS-1$
      }
    }

    body.write(//
    "In our benchmark suite, we collect all four runtime metrics. In order to property compare and evaluate algorithms, we suggest to always use both at least one time measure (");//$NON-NLS-1$
    Accessor.RUNTIME.writeShortName(body, false);
    body.write(", ");//$NON-NLS-1$;
    Accessor.NORMALIZED_RUNTIME.writeShortName(body, false);
    body.write(//
    ") and at least one information access counting measure (");//$NON-NLS-1$
    Accessor.FE.writeShortName(body, false);
    body.write(", ");//$NON-NLS-1$;
    Accessor.DE.writeShortName(body, false);
    body.write("). The measures ");//$NON-NLS-1$
    Accessor.DE.writeShortName(body, false);
    body.write(" and ");//$NON-NLS-1$
    Accessor.NORMALIZED_RUNTIME.writeShortName(body, false);
    body.write(" are less biased and more precise than  ");//$NON-NLS-1$
    Accessor.FE.writeShortName(body, false);
    body.write(" and ");//$NON-NLS-1$
    Accessor.RUNTIME.writeShortName(body, false);
    body.write(", but many results in literature are reported in terms of ");//$NON-NLS-1$
    Accessor.RUNTIME.writeLongName(body, false);
    body.write(" or the ");//$NON-NLS-1$
    Accessor.FE.writeLongName(body, true);
    body.writeChar('.');

    body.writeLinebreak();

    body.write("When looking at problems of different sizes ");//$NON-NLS-1$
    body.macroInvoke(Macros.SCALE);
    body.write(", it makes sense to scale the measured runtimes by some factors, depending on the time measure we use: ");//$NON-NLS-1$

    body.writeSequence(new AccessorSequence(Accessor.TIME_MEASURES, true,
        true, //
        " can be scaled by ", body),//$NON-NLS-1$
        ESequenceType.AND, false);

    body.write(//
    ". This way, results become easier to read and to interpret. If the scaled number of consumed ");//$NON-NLS-1$
    Accessor.FE.writeShortName(body, true);
    body.write(" is ");//$NON-NLS-1$
    body.writeIntInText(10, false);
    body.write(//
    ", for instance, we know that the algorithm needed ");//$NON-NLS-1$
    body.writeIntInText(10, false);
    body.write(//
    " times the problem scale ");//$NON-NLS-1$
    body.macroInvoke(Macros.SCALE);
    body.writeChar(' ');
    Accessor.FE.writeLongName(body, true);
    body.write(//
    " to achieve its goal, without needing to know the problem or the actual value of ");//$NON-NLS-1$
    body.macroInvoke(Macros.SCALE);
    body.writeChar('.');

    super.writeSectionBody(body, data);
  }
}
