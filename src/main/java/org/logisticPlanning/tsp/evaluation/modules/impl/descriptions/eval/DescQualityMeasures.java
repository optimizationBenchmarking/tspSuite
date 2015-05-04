package org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.eval;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.modules.impl.macros.Macros;
import org.logisticPlanning.tsp.evaluation.modules.spec.DescriptionModule;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.utils.collections.lists.ArrayListView;
import org.logisticPlanning.utils.collections.lists.DoubleList;
import org.logisticPlanning.utils.document.spec.ECitationMode;
import org.logisticPlanning.utils.document.spec.EDefaultFigureSize;
import org.logisticPlanning.utils.document.spec.Emphasize;
import org.logisticPlanning.utils.document.spec.Enumeration;
import org.logisticPlanning.utils.document.spec.EnumerationItem;
import org.logisticPlanning.utils.document.spec.Figure;
import org.logisticPlanning.utils.document.spec.FigureBody;
import org.logisticPlanning.utils.document.spec.FigureCaption;
import org.logisticPlanning.utils.document.spec.Graphic;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.Label;
import org.logisticPlanning.utils.document.spec.SectionBody;
import org.logisticPlanning.utils.document.spec.SectionTitle;
import org.logisticPlanning.utils.document.spec.bib.BibAuthor;
import org.logisticPlanning.utils.document.spec.bib.BibAuthors;
import org.logisticPlanning.utils.document.spec.bib.BibDate;
import org.logisticPlanning.utils.document.spec.bib.BibInProceedings;
import org.logisticPlanning.utils.document.spec.bib.BibProceedings;
import org.logisticPlanning.utils.document.spec.bib.BibTechReport;
import org.logisticPlanning.utils.document.spec.bib.BibWebsite;
import org.logisticPlanning.utils.document.spec.bib.EBibMonth;
import org.logisticPlanning.utils.graphics.chart.spec.ELegendType;
import org.logisticPlanning.utils.graphics.chart.spec.ELineMode;
import org.logisticPlanning.utils.graphics.chart.spec.Line2D;
import org.logisticPlanning.utils.graphics.chart.spec.LineChart2D;
import org.logisticPlanning.utils.graphics.chart.spec.range.AxisRange2DDef;
import org.logisticPlanning.utils.graphics.chart.spec.range.FixedAxisEnd;
import org.logisticPlanning.utils.math.MathConstants;
import org.logisticPlanning.utils.math.data.collection.ArrayDataCollectionView;
import org.logisticPlanning.utils.math.data.collection.ListCollection;
import org.logisticPlanning.utils.math.data.point.Point2D;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * <p>
 * A description of the performance measures. There are two ways to define
 * where log points should be collected from a run of an anytime
 * algorithm&nbsp;[<a href="#cite_BD1989STDPP"
 * style="font-weight:bold">1</a>, <a href="#cite_BD1989STDPP2"
 * style="font-weight:bold">2</a>]:
 * </p>
 * <ol>
 * <li>We can define a target objective value and measure how much time the
 * algorithm needs to attain it&nbsp;[<a href="#cite_COCO2012CCCO"
 * style="font-weight:bold">3</a>, <a href="#cite_HAFR2012RPBBOBES"
 * style="font-weight:bold">4</a>, <a href="#cite_AH2005PEOAALSEA"
 * style="font-weight:bold">5</a>], (which equals to drawing a horizontal
 * limit line in a runtime/solution quality diagram), or</li>
 * <li>we can define a fixed time budged and measure that solution quality
 * that was achieved after when has been consumed (i.e., define a vertical
 * limit line in a runtime/solution quality diagram)&nbsp;[<a
 * href="#cite_TLSYW2010BFFTC2SSACOLSGO" style="font-weight:bold">6</a>, <a
 * href="#cite_TYW2012SSOECFLSGO" style="font-weight:bold">7</a>].</li>
 * </ol>
 * <p>
 * This description module inserts a corresponding discussion into the
 * output document, in which we come to the conclusion to simply do both,
 * multiple times.
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_BD1989STDPP" /><a href=
 * "http://www.adventiumlabs.com/about-us/staff/technical-staff/mark-boddys-personal-homepage"
 * >Mark S. Boddy</a> and&nbsp;<a
 * href="http://cs.brown.edu/people/tld/">Thomas L. Dean</a>: <span
 * style="font-weight:bold">&ldquo;Solving Time-Dependent Planning
 * Problems,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the 11th
 * International Joint Conference on Artificial Intelligence
 * (IJCAI'89)</span>, 2, August&nbsp;20&ndash;25, 1989, Detroit, MI, USA,
 * pages 979&ndash;984, Natesa Sastri Sridharan, editor, San Francisco, CA,
 * USA: Morgan Kaufmann Publishers Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1558600949">1-55860-094-9</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781558600942">9781558600942</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=5zIJSQAACAAJ">5zIJSQAACAAJ</a>.
 * <div>link: [<a
 * href="http://ijcai.org/Past%20Proceedings/IJCAI-89-VOL-2/PDF/021.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_BD1989STDPP2" /><a href=
 * "http://www.adventiumlabs.com/about-us/staff/technical-staff/mark-boddys-personal-homepage"
 * >Mark S. Boddy</a> and&nbsp;<a
 * href="http://cs.brown.edu/people/tld/">Thomas L. Dean</a>: <span
 * style="font-weight:bold">&ldquo;Solving Time-Dependent Planning
 * Problems,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;CS-89-03, February&nbsp;1989; published by Providence, RI,
 * USA: Brown University, Department of Computer Science. <div>link: [<a
 * href="ftp://ftp.cs.brown.edu/pub/techreports/89/cs89-03.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_COCO2012CCCO" /><a
 * href="http://www.lri.fr/~hansen/">Nikolaus Hansen</a>, <a
 * href="http://www.lri.fr/~auger/">Anne Auger</a>, <a
 * href="https://tao.lri.fr/tiki-index.php?page=people">Raymond Ros</a>,
 * and&nbsp;Dimo Brockhoff: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;COCO (COmparing
 * Continuous Optimisers),&rdquo;</span> (Website), October&nbsp;17, 2012,
 * Orsay, France: Universit&#233; Paris Sud, Institut National de Recherche
 * en Informatique et en Automatique (INRIA) Futurs, &#201;quipe TAO.
 * <div>link: [<a
 * href="http://coco.gforge.inria.fr/doku.php?id=start">1</a>]</div></div></li>
 * <li><div><span id="cite_HAFR2012RPBBOBES" /><a
 * href="http://www.lri.fr/~hansen/">Nikolaus Hansen</a>, <a
 * href="http://www.lri.fr/~auger/">Anne Auger</a>, <a
 * href="http://www.researchgate.net/profile/Steffen_Finck/">Steffen
 * Finck</a>, and&nbsp;<a
 * href="https://tao.lri.fr/tiki-index.php?page=people">Raymond Ros</a>:
 * <span style="font-weight:bold">&ldquo;Real-Parameter Black-Box
 * Optimization Benchmarking: Experimental Setup,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * March&nbsp;24, 2012; published by Orsay, France: Universit&#233; Paris
 * Sud, Institut National de Recherche en Informatique et en Automatique
 * (INRIA) Futurs, &#201;quipe TAO. <div>link: [<a href=
 * "http://coco.lri.fr/BBOB-downloads/download11.05/bbobdocexperiment.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_AH2005PEOAALSEA" /><a
 * href="http://www.lri.fr/~auger/">Anne Auger</a> and&nbsp;<a
 * href="http://www.lri.fr/~hansen/">Nikolaus Hansen</a>: <span
 * style="font-weight:bold">&ldquo;Performance Evaluation of an Advanced
 * Local Search Evolutionary Algorithm,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the IEEE
 * Congress on Evolutionary Computation (CEC'05)</span>,
 * September&nbsp;2&ndash;5, 2005, Edinburgh, Scotland, UK, pages
 * 1777&ndash;1784, <a
 * href="http://www.macs.hw.ac.uk/staff-directory/david-wolfe-corne.htm"
 * >David Wolfe Corne</a>, <a
 * href="http://cs.adelaide.edu.au/~zbyszek/">Zbigniew Michalewicz</a>, <a
 * href="http://sc.snu.ac.kr/~rim/index.html">Robert Ian McKay</a>, <a
 * href="http://www.cs.vu.nl/~gusz/">&#193;goston E. Eiben</a> aka.
 * Gusz/Guszti, <a
 * href="http://www.natural-selection.com/people_dfogel.html">David B.
 * Fogel</a>, Carlos M. Fonseca, <a
 * href="https://www.ads.tuwien.ac.at/raidl/">G&#252;nther R. Raidl</a>, <a
 * href="http://vlab.ee.nus.edu.sg/~kctan/">Kay Chen Tan</a>, and&nbsp;Ali
 * M. S. Zalzala, editors, Piscataway, NJ, USA: IEEE Computer Society.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780393635">0-7803-9363-5</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780393639">978-0-7803
 * -9363-9</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CEC.2005.1554903">10.1109/CEC.2005
 * .1554903</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/62773008">62773008</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=-QIVAAAACAAJ">-QIVAAAACAAJ</a>;
 * INSPEC Accession Number:&nbsp;8715465. <div>link: [<a
 * href="http://www.lri.fr/~hansen/cec2005localcmaes.pdf">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.71.6248">10.1
 * .1.71 .6248</a></div></div></li>
 * <li><div><span id="cite_TLSYW2010BFFTC2SSACOLSGO" /><a
 * href="http://staff.ustc.edu.cn/~ketang/">Ke Tang</a> <span
 * style="color:gray">[&#21776;&#29634;</span>], <a
 * href="http://goanna.cs.rmit.edu.au/~xiaodong/">Xiaodong Li</a> <span
 * style="color:gray">[&#26446;&#26195;&#19996;</span>], <a
 * href="http://www3.ntu.edu.sg/home/epnsugan/">Ponnuthurai Nagaratnam
 * Suganthan</a>, <a
 * href="http://www.cs.ecnu.edu.cn/~zhyyang/index.html">Zhenyu Yang</a>
 * <span style="color:gray">[&#26472;&#25391;&#23431;</span>], and&nbsp;<a
 * href="http://www.it-weise.de/">Thomas Weise</a> <span
 * style="color:gray">[&#27748;&#21355;&#24605;</span>]: <span
 * style="font-weight:bold">&ldquo;Benchmark Functions for the CEC'2010
 * Special Session and Competition on Large-Scale Global
 * Optimization,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * January&nbsp;8, 2010; published by Hefei, Anhui, China: University of
 * Science and Technology of China (USTC), School of Computer Science and
 * Technology, Nature Inspired Computation and Applications Laboratory
 * (NICAL). <div>link: [<a href=
 * "http://www.it-weise.de/documents/files/TLSYW2009BFFTCSSACOLSGO.pdf">
 * 1</a>]; source code: [<a href=
 * "http://www.it-weise.de/documents/files/TLSYW2009BFFTCSSACOLSGO.zip"
 * >1</a>], [<a
 * href="http://nical.ustc.edu.cn/wcci2010/lsgo_benchmark.zip">2</a>],
 * and&nbsp;[<a
 * href="https://github.com/qzcwx/CEC2010SS-LSGO-Benchmarks-CPP">3</
 * a>]</div></div></li>
 * <li><div><span id="cite_TYW2012SSOECFLSGO" /><a
 * href="http://staff.ustc.edu.cn/~ketang/">Ke Tang</a> <span
 * style="color:gray">[&#21776;&#29634;</span>], <a
 * href="http://www.cs.ecnu.edu.cn/~zhyyang/index.html">Zhenyu Yang</a>
 * <span style="color:gray">[&#26472;&#25391;&#23431;</span>], and&nbsp;<a
 * href="http://www.it-weise.de/">Thomas Weise</a> <span
 * style="color:gray">[&#27748;&#21355;&#24605;</span>]: <span
 * style="font-weight:bold">&ldquo;Special Session on Evolutionary
 * Computation for Large Scale Global Optimization at 2012 IEEE World
 * Congress on Computational Intelligence (CEC@WCCI-2012),&rdquo;</span>
 * <span style="font-style:italic;font-family:cursive;">Technical
 * Report</span> June&nbsp;14, 2012; published by Hefei, Anhui, China:
 * University of Science and Technology of China (USTC), School of Computer
 * Science and Technology, Nature Inspired Computation and Applications
 * Laboratory (NICAL). <div>link: [<a
 * href="http://www.it-weise.de/documents/files/TYW2012SSOECFLSGO.pdf"
 * >1</a>]; software: [<a
 * href="http://www.it-weise.de/documents/files/TYW2012SSOECFLSGO.zip"
 * >1</a>]</div></div></li>
 * </ol>
 */
public final class DescQualityMeasures extends DescriptionModule {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * the website&nbsp;[<a href="#cite_COCO2012CCCO"
   * style="font-weight:bold">3</a>]
   */
  static final BibWebsite COCO2012CCCO;
  /**
   * the technical report&nbsp;[<a href="#cite_HAFR2012RPBBOBES"
   * style="font-weight:bold">4</a>]
   */
  public static final BibTechReport HAFR2012RPBBOBES;
  /**
   * the in-proceedings&nbsp;[<a href="#cite_AH2005PEOAALSEA"
   * style="font-weight:bold">5</a>]
   */
  public static final BibInProceedings AH2005PEOAALSEA;
  /**
   * the technical report&nbsp;[<a href="#cite_TLSYW2010BFFTC2SSACOLSGO"
   * style="font-weight:bold">6</a>]
   */
  public static final BibTechReport TLSYW2010BFFTC2SSACOLSGO;
  /**
   * the technical report&nbsp;[<a href="#cite_TYW2012SSOECFLSGO"
   * style="font-weight:bold">7</a>]
   */
  public static final BibTechReport TYW2012SSOECFLSGO;

  /**
   * the technical report&nbsp;[<a href="#cite_BD1989STDPP2"
   * style="font-weight:bold">2</a>]
   */
  public static final BibTechReport BD1989STDPP2;
  /**
   * the in-proceedings&nbsp;[<a href="#cite_BD1989STDPP"
   * style="font-weight:bold">1</a>]
   */
  public static final BibInProceedings BD1989STDPP;

  static {

    final BibAuthor nh, aa, rr, sf, kt, xdl, pns, zyy, tw, tld, mb;
    final String tao, taoa, ustc, hefei;
    final BibProceedings PROC2005CEC;

    tao = "Universit\u00e9 Paris Sud, Institut National de Recherche en Informatique et en Automatique (INRIA) Futurs, \u00c9quipe TAO";//$NON-NLS-1$
    taoa = "Orsay, France";//$NON-NLS-1$

    ustc = "University of Science and Technology of China (USTC), School of Computer Science and Technology, Nature Inspired Computation and Applications Laboratory (NICAL)";//$NON-NLS-1$
    hefei = "Hefei, Anhui, China";//$NON-NLS-1$

    nh = new BibAuthor("Nikolaus", "Hansen"); //$NON-NLS-1$//$NON-NLS-2$
    aa = new BibAuthor("Anne", "Auger"); //$NON-NLS-1$//$NON-NLS-2$
    rr = new BibAuthor("Raymond", "Ros"); //$NON-NLS-1$//$NON-NLS-2$
    sf = new BibAuthor("Steffen", "Finck"); //$NON-NLS-1$//$NON-NLS-2$
    kt = new BibAuthor("Ke", "Tang");//$NON-NLS-1$//$NON-NLS-2$
    xdl = new BibAuthor("Xiaodong", "Li");//$NON-NLS-1$//$NON-NLS-2$
    pns = new BibAuthor("Ponnuthurai Nagaratnam", "Suganthan");//$NON-NLS-1$//$NON-NLS-2$
    zyy = new BibAuthor("Zhenyu", "Yang");//$NON-NLS-1$//$NON-NLS-2$
    tw = new BibAuthor("Thomas", "Weise");//$NON-NLS-1$//$NON-NLS-2$
    mb = new BibAuthor("Mark S.", "Boddy");//$NON-NLS-1$//$NON-NLS-2$
    tld = new BibAuthor("Thomas L.", "Dean");//$NON-NLS-1$//$NON-NLS-2$

    try {//
      COCO2012CCCO = new BibWebsite(new BibAuthors(nh, aa, rr),//
          "COCO (COmparing Continuous Optimisers)",//$NON-NLS-1$
          new BibDate(2012, EBibMonth.OCTOBER, 17), //
          tao, taoa, new URI("http://coco.gforge.inria.fr/"));//$NON-NLS-1$

    } catch (final URISyntaxException u) {
      throw new RuntimeException(u);
    }
    try {
      HAFR2012RPBBOBES = new BibTechReport(
          new BibAuthors(nh, aa, sf, rr),//
          "Real-Parameter Black-Box Optimization Benchmarking: Experimental Setup",//$NON-NLS-1$
          new BibDate(2012, EBibMonth.MARCH, 24), //
          tao,
          taoa,
          null,
          null,//
          new URI(
              "http://coco.lri.fr/BBOB-downloads/download11.05/bbobdocexperiment.pdf"),//$NON-NLS-1$
              null);
    } catch (final URISyntaxException u) {
      throw new RuntimeException(u);
    }

    PROC2005CEC = new BibProceedings(
        "Proceedings of the IEEE Congress on Evolutionary Computation (CEC'05), volume 2",//$NON-NLS-1$
        new BibDate(2005, EBibMonth.SEPTEMBER, 2), //
        new BibDate(2005, EBibMonth.SEPTEMBER, 5), //
        new BibAuthors(//
            new BibAuthor("David Wolfe", "Corne"),//$NON-NLS-1$//$NON-NLS-2$
            new BibAuthor("Zbigniew", "Michalewicz"),//$NON-NLS-1$//$NON-NLS-2$
            new BibAuthor("Robert Ian", "McKay"),//$NON-NLS-1$//$NON-NLS-2$
            new BibAuthor("\u00c1goston E.", "Eiben"),//$NON-NLS-1$//$NON-NLS-2$
            new BibAuthor("David B.", "Fogel"),//$NON-NLS-1$//$NON-NLS-2$
            new BibAuthor("Carlos M.", "Fonseca"),//$NON-NLS-1$//$NON-NLS-2$
            new BibAuthor("G\u00fcnther R.", "Raidl"),//$NON-NLS-1$//$NON-NLS-2$
            new BibAuthor("Kay Chen", "Tan"),//$NON-NLS-1$//$NON-NLS-2$
            new BibAuthor("Ali M. S.", "Zalzala")//$NON-NLS-1$//$NON-NLS-2$
            ),//
            "Edinburgh, Scotland, UK",//$NON-NLS-1$
            "IEEE Computer Society", "Piscataway, NJ, USA",//$NON-NLS-1$//$NON-NLS-2$
            null, null, null, null);

    try {
      AH2005PEOAALSEA = new BibInProceedings(
          new BibAuthors(aa, nh),//
          "Performance Evaluation of an Advanced Local Search Evolutionary Algorithm",//$NON-NLS-1$
          PROC2005CEC, "1777", "1784", null,//$NON-NLS-1$//$NON-NLS-2$
          new URI("http://www.lri.fr/~hansen/cec2005localcmaes.pdf"),//$NON-NLS-1$
          "10.1109/CEC.2005.1554903");//$NON-NLS-1$

    } catch (final URISyntaxException u) {
      throw new RuntimeException(u);
    }
    try {
      TLSYW2010BFFTC2SSACOLSGO = new BibTechReport(
          new BibAuthors(kt, xdl, pns, zyy, tw),//
          "Benchmark Functions for the CEC'2010 Special Session and Competition on Large-Scale Global Optimization",//$NON-NLS-1$
          new BibDate(2010, EBibMonth.JANUARY, 8), //
          ustc,
          hefei,//
          null,
          null,//
          new URI(
              "http://www.it-weise.de/documents/files/TLSYW2009BFFTCSSACOLSGO.zip"),//$NON-NLS-1$
              null);
    } catch (final URISyntaxException u) {
      throw new RuntimeException(u);
    }
    try {
      TYW2012SSOECFLSGO = new BibTechReport(
          new BibAuthors(kt, zyy, tw),//
          "Special Session on Evolutionary Computation for Large Scale Global Optimization at 2012 IEEE World Congress on Computational Intelligence (CEC@WCCI-2012)",//$NON-NLS-1$
          new BibDate(2012, EBibMonth.JUNE, 14), //
          ustc,
          hefei,//
          null,
          null,//
          new URI(
              "http://www.it-weise.de/documents/files/TYW2012SSOECFLSGO.pdf"),//$NON-NLS-1$
              null);

    } catch (final URISyntaxException u) {
      throw new RuntimeException(u);
    }

    try {
      BD1989STDPP2 = new BibTechReport(
          new BibAuthors(mb, tld),//
          "Solving Time-Dependent Planning Problems",//$NON-NLS-1$
          new BibDate(1989, EBibMonth.FEBRUARY), //
          "Brown University, Department of Computer Science",//$NON-NLS-1$
          "Providence, RI, USA",//$NON-NLS-1$
          null,
          "CS-89-03",//$NON-NLS-1$
          new URI("ftp://ftp.cs.brown.edu/pub/techreports/89/cs89-03.pdf"),//$NON-NLS-1$
          null);

    } catch (final URISyntaxException u) {
      throw new RuntimeException(u);
    }

    try {
      BD1989STDPP = new BibInProceedings(
          new BibAuthors(mb, tld),//
          "Solving Time-Dependent Planning Problems",//$NON-NLS-1$
          new BibProceedings(
              "Proceedings of the 11th International Joint Conference on Artificial Intelligence (IJCAI'89), Volume 2",//$NON-NLS-1$
              new BibDate(1989, EBibMonth.AUGUST, 20), //
              new BibDate(1989, EBibMonth.AUGUST, 25), //
              new BibAuthors(//
                  new BibAuthor("Natesa Sastri", "Sridharan")//$NON-NLS-1$//$NON-NLS-2$
                  ),//
                  "Detroit, MI, USA",//$NON-NLS-1$
                  "Morgan Kaufmann Publishers Inc.",//$NON-NLS-1$
                  "San Francisco, CA, USA",//$NON-NLS-1$
                  null,
                  null, //
                  new URI(
                      "http://dli.iiit.ac.in/ijcai/IJCAI-89-VOL2/CONTENT/content.htm"),//$NON-NLS-1$
                      null),
                      "979",//$NON-NLS-1$
                      "984",//$NON-NLS-1$
                      null, //
                      new URI(
                          "http://ijcai.org/Past%20Proceedings/IJCAI-89-VOL-2/PDF/021.pdf"),//$NON-NLS-1$
                          null);

    } catch (final URISyntaxException u) {
      throw new RuntimeException(u);
    }
  }

  /**
   * create!
   *
   * @param owner
   *          the macro's owner
   */
  DescQualityMeasures(final Module owner) {
    super("qualityMeasures", owner, false); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected void initialize(final Header header, final ExperimentSet data)
      throws IOException {//
    super.initialize(header, data);
    Macros.F_BEST.define(header);
    Macros.F_THRESHOLD.define(header);
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSectionTitle(final SectionTitle title,
      final ExperimentSet data) throws IOException {
    title.write("Quality Measures"); //$NON-NLS-1$
  }

  /**
   * create the next double
   *
   * @param r
   *          the randomizer
   * @return the double
   */
  private static final double __nextDbl(final Randomizer r) {
    double x;
    int i;

    for (;;) {
      x = 1d;

      for (i = r.nextInt(3); (--i) >= 0;) {
        x *= (r.nextBoolean() ? Math.abs(r.nextGaussian()) : r
            .nextDouble());
      }

      if (x < 0.1d) {
        return x;
      }
    }
  }

  /**
   * make a random data collection
   *
   * @return the collection
   */
  private static final Line2D[] __makeRandom() {
    final Randomizer r;
    final Line2D[] lines;
    final DoubleList list;
    double x, y;
    int i;

    r = new Randomizer();
    lines = new Line2D[5];
    list = new DoubleList();

    for (i = lines.length; (--i) >= 0;) {
      list.clear();
      x = 0d;
      y = (1d - (0.2 * r.nextDouble()));

      for (;;) {

        list.add(x);
        list.add(y);
        if ((y <= 0d) || (x >= 1)) {
          break;
        }

        x += DescQualityMeasures.__nextDbl(r);
        y -= DescQualityMeasures.__nextDbl(r);
      }

      lines[i] = new Line2D(("Run " + (i + 1)),//$NON-NLS-1$
          null,
          new ArrayDataCollectionView(list.toDataArray(),
              (list.size() >> 1), 2),//
              null,//
              (r.nextBoolean() ? ELineMode.DIRECT : ELineMode.STAIRS_KEEP_LEFT));
    }

    return lines;
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSectionBody(final SectionBody body,
      final ExperimentSet data) throws IOException {
    final Label label;
    LineChart2D lc;

    body.write(//
        "Like the developers of COCO/BBOB"); //$NON-NLS-1$
    body.cite(ECitationMode.BY_ID_IN_SENTENCE,
        DescQualityMeasures.COCO2012CCCO,
        DescQualityMeasures.HAFR2012RPBBOBES,
        DescQualityMeasures.AH2005PEOAALSEA);

    body.write(//
        ", a benchmark suite for numerical optimization, we assume that the optimization methods applied (to a TSP) are "); //$NON-NLS-1$

    try (Emphasize em = body.emphasize()) {
      em.write("anytime algorithms"); //$NON-NLS-1$
    }
    body.write(//
        ". Anytime algorithms can give an approximation of the solution of a problem (here: TSP) and an approximate quality "); //$NON-NLS-1$
    body.macroInvoke(Macros.F_BEST);
    body.write(") at almost any time during their run. "); //$NON-NLS-1$
    body.cite(ECitationMode.BY_ID_IN_SENTENCE,
        DescQualityMeasures.BD1989STDPP, DescQualityMeasures.BD1989STDPP2);
    body.write(". "); //$NON-NLS-1$

    try (Figure fig = body.figure(Label.AUTO_LABEL, false)) {
      try (FigureCaption cap = fig.figureCaption()) {
        cap.write(//
            "An example for several runs of an optimization algorithm and the concept of horizontal and vertical cuts"); //$NON-NLS-1$
        cap.cite(ECitationMode.BY_ID_IN_SENTENCE,
            DescQualityMeasures.HAFR2012RPBBOBES);
        cap.writeChar('.');
      }
      try (FigureBody fby = fig.figureBody(
          //
          new URI(Module.GRAPHICS_FOLDER + '/' + "examplesForCuts"),//$NON-NLS-1$
          body.getDocument().getDimensions()
          .getFigureDimensionsMM(EDefaultFigureSize.TWO_IN_A_ROW))) {

        try (Graphic graph = fby.graphic()) {
          lc = body.getDocument().getOwner().getDriver().getChartDriver()
              .createLineChart2D(//
                  new AxisRange2DDef(new FixedAxisEnd(0d),//
                      new FixedAxisEnd(1d),//
                      new FixedAxisEnd(0d),//
                      new FixedAxisEnd(1)));//
          lc.setLegendType(ELegendType.PUT_LEGEND);
          lc.setAxisTitleX("passed runtime");//$NON-NLS-1$
          lc.setAxisTitleY("best objective function value");//$NON-NLS-1$
          //
          lc.addLine(//
              new Line2D("horizontal view",//$NON-NLS-1$
                  null, new ListCollection(
                      //
                      ArrayListView.makeArrayListView(new Point2D[] {
                          new Point2D(0d, (1d - MathConstants.GOLDEN_RATIO)),//
                          new Point2D(1d, (1d - MathConstants.GOLDEN_RATIO)) //
                      }), 2), null, ELineMode.DIRECT));
          //

          lc.addLine(//
              new Line2D("vertical view",//$NON-NLS-1$
                  null, new ListCollection(
                      //
                      ArrayListView.makeArrayListView(new Point2D[] {
                          new Point2D(MathConstants.GOLDEN_RATIO, 0d),//
                          new Point2D(MathConstants.GOLDEN_RATIO, 1d) //
                      }), 2), null, ELineMode.DIRECT));
          lc.addLines(DescQualityMeasures.__makeRandom());

          lc.paint(graph);
        }

      } catch (final URISyntaxException use) {
        throw new IOException(use);
      }

      label = fig.getLabel();
    }

    body.write(//
        "This leaves two ways to measure the quality of an algorithm:"); //$NON-NLS-1$

    try (Enumeration enu = body.enumeration()) {
      try (EnumerationItem item = enu.item()) {
        item.write("The best solution quality "); //$NON-NLS-1$
        item.macroInvoke(Macros.F_BEST);
        item.write(//
            " discovered after a certain, fixed amount of runtime (vertical cut in objective-value over time diagrams such as "); //$NON-NLS-1$
        item.reference(label);
        item.write(").");//$NON-NLS-1$
      }

      try (EnumerationItem item = enu.item()) {
        item.write(//
            "The amount of runtime needed to discover a solution with an objective value "); //$NON-NLS-1$
        item.macroInvoke(Macros.F_BEST);
        item.write(//
            " which is at least as good (smaller or equal) as a given threshold objective value "); //$NON-NLS-1$
        item.macroInvoke(Macros.F_THRESHOLD);
        item.write(" (horizontal cut in objective-value over time diagrams such as "); //$NON-NLS-1$
        item.reference(label);
        item.write(").");//$NON-NLS-1$
      }
    }

    body.write(//
        "There exist arguments for and against either measure and both are used in various benchmark suits "); //$NON-NLS-1$
    body.cite(ECitationMode.BY_ID_IN_SENTENCE,
        DescQualityMeasures.HAFR2012RPBBOBES,
        DescQualityMeasures.AH2005PEOAALSEA,
        DescQualityMeasures.TLSYW2010BFFTC2SSACOLSGO,
        DescQualityMeasures.TYW2012SSOECFLSGO);
    body.writeChar('.');

    super.writeSectionBody(body, data);
  }
}
