package org.logisticPlanning.tsp.evaluation.data.properties.litComp.results;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.properties.litComp.LiteratureResultPoint;
import org.logisticPlanning.tsp.evaluation.data.properties.litComp.LiteratureResultPointSet;
import org.logisticPlanning.tsp.evaluation.modules.impl.macros.Macros;
import org.logisticPlanning.utils.document.spec.AbstractTextComplex;
import org.logisticPlanning.utils.document.spec.ECitationMode;
import org.logisticPlanning.utils.document.spec.EMathOp;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.InlineMath;
import org.logisticPlanning.utils.document.spec.MathOp;
import org.logisticPlanning.utils.document.spec.MathOpParam;
import org.logisticPlanning.utils.document.spec.bib.BibArticle;
import org.logisticPlanning.utils.document.spec.bib.BibAuthor;
import org.logisticPlanning.utils.document.spec.bib.BibAuthors;
import org.logisticPlanning.utils.document.spec.bib.BibDate;
import org.logisticPlanning.utils.document.spec.bib.BibRecord;
import org.logisticPlanning.utils.document.spec.bib.EBibMonth;
import org.logisticPlanning.utils.math.statistics.EStatisticParameter;

/**
 * The results from&nbsp;[<a href="#cite_CHZ2012APBGAWBMARHFTTSP"
 * style="font-weight:bold">1</a>]. The consumed FEs are rounded down.<h2>
 * References</h2>
 * <ol>
 * <li><div><span id="cite_CHZ2012APBGAWBMARHFTTSP" />Pei-Chann Chang <span
 * style="color:gray">[&#24352;&#30334;&#26632;</span>], Wei-Hsiu Huang
 * <span style="color:gray">[&#40644;&#20255;&#20462;</span>],
 * and&nbsp;Zhen-Zhen Zhang <span
 * style="color:gray">[&#24352;&#30495;&#30495;</span>]: <span
 * style="font-weight:bold">&ldquo;A Puzzle-Based Genetic Algorithm with
 * Block Mining and Recombination Heuristic for the Traveling Salesman
 * Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Journal of Computer
 * Science and Technology (JCST)</span> 27(5):937&ndash;949,
 * September&nbsp;5, 2012; published by Beijing, China: Chinese Academy of
 * Sciences (CAS), Berlin, Germany: Springer-Verlag GmbH, and&nbsp;Beijing,
 * China: Science in China Press (SCP). doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/s11390-012-1275-3">10.1007
 * /s11390-012-1275-3</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/10009000">1000-9000</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/18604749">1860-4749</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=JCTEEM">
 * JCTEEM</a>. <div>link: [<a
 * href="http://jcst.ict.ac.cn:8080/jcst/EN/10.1007/s11390-012-1275-3"
 * >1</a>]</div></div></li>
 * </ol>
 */
final class _CHZ2012APBGAWBMARHFTTSP extends LiteratureResultPointSet {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * the result point set for paper&nbsp;[<a
   * href="#cite_CHZ2012APBGAWBMARHFTTSP" style="font-weight:bold">1</a>]
   */
  static final LiteratureResultPointSet CHZ2012APBGAWBMARHFTTSP = new _CHZ2012APBGAWBMARHFTTSP();

  /**
   * get the bib record
   *
   * @return the bib record
   */
  private static final BibRecord __rec() {
    try {
      return new BibArticle(
          //
          new BibAuthors(//
              new BibAuthor("Pei-Chann", "Chang"),//$NON-NLS-1$//$NON-NLS-2$
              new BibAuthor("Wei-Hsiu", "Huang"),//$NON-NLS-1$//$NON-NLS-2$
              new BibAuthor("Zhen-Zhen", "Zhang")//$NON-NLS-1$//$NON-NLS-2$
          ),//
          "A Puzzle-Based Genetic Algorithm with Block Mining and Recombination Heuristic for the Traveling Salesman Problem",//$NON-NLS-1$
          new BibDate(2012, EBibMonth.SEPTEMBER, 5),//
          "Journal of Computer Science and Technology",//$NON-NLS-1$
          "27", "5",//$NON-NLS-1$//$NON-NLS-2$
          "937", "949",//$NON-NLS-1$//$NON-NLS-2$
          new URI(
              "http://jcst.ict.ac.cn:8080/jcst/EN/10.1007/s11390-012-1275-3"),//$NON-NLS-1$
          "10.1007/s11390-012-1275-3");//$NON-NLS-1$
    } catch (final URISyntaxException use) {
      throw new RuntimeException(use);
    }
  }

  /** create */
  private _CHZ2012APBGAWBMARHFTTSP() {
    super(
        "Puzzle-Based Genetic Algorithm with Block Mining and Recombination Heuristic",//$NON-NLS-1$
        "p-ACGA",//$NON-NLS-1$
        new BibRecord[] { _CHZ2012APBGAWBMARHFTTSP.__rec() },//
        new LiteratureResultPoint[] {//

            //
            new LiteratureResultPoint(Instance.EIL51,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.EIL51),//
                Accessor.F, 430.3d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.EIL51,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.EIL51),//
                Accessor.F, 427d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.EIL76,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.EIL76),//
                Accessor.F, 548.4d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.EIL76,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.EIL76),//
                Accessor.F, 538d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.EIL101,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.EIL101),//
                Accessor.F, 641.5d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.EIL101,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.EIL101),//
                Accessor.F, 631d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.BERLIN52,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.BERLIN52),//
                Accessor.F, 7615.4d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.BERLIN52,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.BERLIN52),//
                Accessor.F, 7542d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.BIER127,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.BIER127),//
                Accessor.F, 120377.6d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.BIER127,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.BIER127),//
                Accessor.F, 118695d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.CH130,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.CH130),//
                Accessor.F, 6277.9d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.CH130,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.CH130),//
                Accessor.F, 6137d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.CH150,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.CH150),//
                Accessor.F, 6646.6d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.CH150,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.CH150),//
                Accessor.F, 6549d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.RD100,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.RD100),//
                Accessor.F, 8044.3d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.RD100,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.RD100),//
                Accessor.F, 7910d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.LIN105,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.LIN105),//
                Accessor.F, 14574.5d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.LIN105,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.LIN105),//
                Accessor.F, 14379d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.LIN318,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.LIN318),//
                Accessor.F, 43550.1d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.LIN318,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.LIN318),//
                Accessor.F, 42820d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.KROA100,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.KROA100),//
                Accessor.F, 21547.9d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.KROA100,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.KROA100),//
                Accessor.F, 21282d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.KROA150,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.KROA150),//
                Accessor.F, 27302.9d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.KROA150,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.KROA150),//
                Accessor.F, 26714d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.KROA200,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.KROA200),//
                Accessor.F, 30118.8d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.KROA200,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.KROA200),//
                Accessor.F, 29471d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.KROB100,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.KROB100),//
                Accessor.F, 22510.1d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.KROB100,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.KROB100),//
                Accessor.F, 22179d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.KROB150,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.KROB150),//
                Accessor.F, 26760.7d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.KROB150,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.KROB150),//
                Accessor.F, 26310d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.KROB200,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.KROB200),//
                Accessor.F, 30366.0d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.KROB200,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.KROB200),//
                Accessor.F, 29743d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.KROC100,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.KROC100),//
                Accessor.F, 21064.6d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.KROC100,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.KROC100),//
                Accessor.F, 20749d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.KROD100,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.KROD100),//
                Accessor.F, 21779.1d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.KROD100,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.KROD100),//
                Accessor.F, 21330d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.KROE100,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.KROE100),//
                Accessor.F, 22374.6d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.KROE100,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.KROE100),//
                Accessor.F, 22121d, EStatisticParameter.MINIMUM),//

            //
            new LiteratureResultPoint(Instance.PR299,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.PR299),//
                Accessor.F, 50019.1d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.PR299,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.PR299),//
                Accessor.F, 48995d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.PCB442,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.PCB442),//
                Accessor.F, 53719.8d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.PCB442,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.PCB442),//
                Accessor.F, 52263d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.RAT575,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.RAT575),//
                Accessor.F, 7152.0d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.RAT575,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.RAT575),//
                Accessor.F, 7081d, EStatisticParameter.MINIMUM),//

            //
            new LiteratureResultPoint(Instance.RAT783,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.RAT783),//
                Accessor.F, 9374.2d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.RAT783,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.RAT783),//
                Accessor.F, 9235d, EStatisticParameter.MINIMUM),//

            //
            new LiteratureResultPoint(Instance.PR1002,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.PR1002),//
                Accessor.F, 277906.7d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.PR1002,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.PR1002),//
                Accessor.F, 274828d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.PCB1173,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.PCB1173),//
                Accessor.F, 61184.0d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.PCB1173,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.PCB1173),//
                Accessor.F, 60910d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.RL1323,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.RL1323),//
                Accessor.F, 296999.4d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.RL1323,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.RL1323),//
                Accessor.F, 294547d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.FL1400,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.FL1400),//
                Accessor.F, 21117.8d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.FL1400,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.FL1400),//
                Accessor.F, 21037d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.D1655,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.D1655),//
                Accessor.F, 66078.4d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.D1655,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.D1655),//
                Accessor.F, 65484, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.PR2392,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.PR2392),//
                Accessor.F, 420618.7d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.PR2392,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.PR2392),//
                Accessor.F, 417300d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.PCB3038,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.PCB3038),//
                Accessor.F, 151321.3d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.PCB3038,//
                Accessor.FE,
                _CHZ2012APBGAWBMARHFTTSP.__FEs(Instance.PCB3038),//
                Accessor.F, 150535d, EStatisticParameter.MINIMUM),//

            //
            new LiteratureResultPoint(Instance.EIL51,//
                Accessor.RUNTIME, 48.1e3d,//
                Accessor.F, 430.3d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.EIL51,//
                Accessor.RUNTIME, 48.1e3d,//
                Accessor.F, 427d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.EIL76,//
                Accessor.RUNTIME, 98.9e3d,//
                Accessor.F, 548.4d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.EIL76,//
                Accessor.RUNTIME, 98.9e3d,//
                Accessor.F, 538d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.EIL101,//
                Accessor.RUNTIME, 166.9e3d,//
                Accessor.F, 641.5d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.EIL101,//
                Accessor.RUNTIME, 166.9e3d,//
                Accessor.F, 631d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.BERLIN52,//
                Accessor.RUNTIME, 40.7e3d,//
                Accessor.F, 7615.4d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.BERLIN52,//
                Accessor.RUNTIME, 40.7e3d,//
                Accessor.F, 7542d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.BIER127,//
                Accessor.RUNTIME, 246.0e3d,//
                Accessor.F, 120377.6d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.BIER127,//
                Accessor.RUNTIME, 246.0e3d,//
                Accessor.F, 118695d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.CH130,//
                Accessor.RUNTIME, 266.0e3d,//
                Accessor.F, 6277.9d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.CH130,//
                Accessor.RUNTIME, 266.0e3d,//
                Accessor.F, 6137d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.CH150,//
                Accessor.RUNTIME, 348.3e3d,//
                Accessor.F, 6646.6d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.CH150,//
                Accessor.RUNTIME, 348.3e3d,//
                Accessor.F, 6549d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.RD100,//
                Accessor.RUNTIME, 162.9e3d,//
                Accessor.F, 8044.3d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.RD100,//
                Accessor.RUNTIME, 162.9e3d,//
                Accessor.F, 7910d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.LIN105,//
                Accessor.RUNTIME, 49.0e3d,//
                Accessor.F, 14574.5d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.LIN105,//
                Accessor.RUNTIME, 49.0e3d,//
                Accessor.F, 14379d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.LIN318,//
                Accessor.RUNTIME, 420.3e3d,//
                Accessor.F, 43550.1d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.LIN318,//
                Accessor.RUNTIME, 420.3e3d,//
                Accessor.F, 42820d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.KROA100,//
                Accessor.RUNTIME, 45.0e3d,//
                Accessor.F, 21547.9d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.KROA100,//
                Accessor.RUNTIME, 45.0e3d,//
                Accessor.F, 21282d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.KROA150,//
                Accessor.RUNTIME, 96.2e3d,//
                Accessor.F, 27302.9d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.KROA150,//
                Accessor.RUNTIME, 96.2e3d,//
                Accessor.F, 26714d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.KROA200,//
                Accessor.RUNTIME, 167.7e3d,//
                Accessor.F, 30118.8d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.KROA200,//
                Accessor.RUNTIME, 167.7e3d,//
                Accessor.F, 29471d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.KROB100,//
                Accessor.RUNTIME, 45.0e3d,//
                Accessor.F, 22510.1d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.KROB100,//
                Accessor.RUNTIME, 45.0e3d,//
                Accessor.F, 22179d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.KROB150,//
                Accessor.RUNTIME, 96.3e3d,//
                Accessor.F, 26760.7d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.KROB150,//
                Accessor.RUNTIME, 96.3e3d,//
                Accessor.F, 26310d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.KROB200,//
                Accessor.RUNTIME, 167.9e3d,//
                Accessor.F, 30366.0d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.KROB200,//
                Accessor.RUNTIME, 167.9e3d,//
                Accessor.F, 29743d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.KROC100,//
                Accessor.RUNTIME, 45.0e3d,//
                Accessor.F, 21064.6d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.KROC100,//
                Accessor.RUNTIME, 45.0e3d,//
                Accessor.F, 20749d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.KROD100,//
                Accessor.RUNTIME, 45.0e3d,//
                Accessor.F, 21779.1d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.KROD100,//
                Accessor.RUNTIME, 45.0e3d,//
                Accessor.F, 21330d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.KROE100,//
                Accessor.RUNTIME, 45.0e3d,//
                Accessor.F, 22374.6d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.KROE100,//
                Accessor.RUNTIME, 45.0e3d,//
                Accessor.F, 22121d, EStatisticParameter.MINIMUM),//

            //
            new LiteratureResultPoint(Instance.PR299,//
                Accessor.RUNTIME, 372.2e3d,//
                Accessor.F, 50019.1d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.PR299,//
                Accessor.RUNTIME, 372.2e3d,//
                Accessor.F, 48995d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.PCB442,//
                Accessor.RUNTIME, 796.6e3d,//
                Accessor.F, 53719.8d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.PCB442,//
                Accessor.RUNTIME, 796.6e3d,//
                Accessor.F, 52263d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.RAT575,//
                Accessor.RUNTIME, 1382.4e3d,//
                Accessor.F, 7152.0d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.RAT575,//
                Accessor.RUNTIME, 1382.4e3d,//
                Accessor.F, 7081d, EStatisticParameter.MINIMUM),//

            //
            new LiteratureResultPoint(Instance.RAT783,//
                Accessor.RUNTIME, 2558.7e3d,//
                Accessor.F, 9374.2d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.RAT783,//
                Accessor.RUNTIME, 2558.7e3d,//
                Accessor.F, 9235d, EStatisticParameter.MINIMUM),//

            //
            new LiteratureResultPoint(Instance.PR1002,//
                Accessor.RUNTIME, 3865.0e3d,//
                Accessor.F, 277906.7d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.PR1002,//
                Accessor.RUNTIME, 3865.0e3d,//
                Accessor.F, 274828d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.PCB1173,//
                Accessor.RUNTIME, 5806.0e3d,//
                Accessor.F, 61184.0d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.PCB1173,//
                Accessor.RUNTIME, 5806.0e3d,//
                Accessor.F, 60910d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.RL1323,//
                Accessor.RUNTIME, 7101.2e3d,//
                Accessor.F, 296999.4d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.RL1323,//
                Accessor.RUNTIME, 7101.2e3d,//
                Accessor.F, 294547d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.FL1400,//
                Accessor.RUNTIME, 6531.4e3d,//
                Accessor.F, 21117.8d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.FL1400,//
                Accessor.RUNTIME, 6531.4e3d,//
                Accessor.F, 21037d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.D1655,//
                Accessor.RUNTIME, 8769.4e3d,//
                Accessor.F, 66078.4d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.D1655,//
                Accessor.RUNTIME, 8769.4e3d,//
                Accessor.F, 65484, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.PR2392,//
                Accessor.RUNTIME, 24754.3e3d,//
                Accessor.F, 420618.7d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.PR2392,//
                Accessor.RUNTIME, 24754.3e3d,//
                Accessor.F, 417300d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.PCB3038,//
                Accessor.RUNTIME, 30044.1e3d,//
                Accessor.F, 151321.3d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.PCB3038,//
                Accessor.RUNTIME, 30044.1e3d,//
                Accessor.F, 150535d, EStatisticParameter.MINIMUM),//

        });
  }

  /**
   * A conservative estimate of the number of FEs performed by the p-ACGA
   * algorithm. Equals population size*n*100 FEs, does not include any
   * overhead for "artificial chromosomes"
   *
   * @param inst
   *          the instance
   * @return the lower bound of used FEs
   */
  private static final long __FEs(final Instance inst) {
    return (inst.n() * 50 * 100);
  }

  /** {@inheritDoc} */
  @Override
  public final void writeDescription(final AbstractTextComplex dest)
      throws IOException {
    dest.write("In "); //$NON-NLS-1$
    this.cite(ECitationMode.BY_ID_IN_SENTENCE, dest);
    dest.write(", the "); //$NON-NLS-1$
    dest.write(this.getLongName());
    dest.write(" ("); //$NON-NLS-1$
    dest.write(this.getShortName());
    dest.write(") is introduced. It is a Genetic Algorithm using a heuristic based on ACO to extract patterns from chromosomes (solutions), which then are recombined to introduce new, artificial chromosomes into the population. The algorithm is implemented with Microsoft Visual C++ 2008 Express and run on a machine with Intel Core2 (1.86 GHz) and 2GiB of DDR2 800 memory. Its population size is "); //$NON-NLS-1$
    dest.writeInt(100);
    dest.write(" and it is run for "); //$NON-NLS-1$
    try (InlineMath im = dest.inlineMath()) {
      try (MathOp mo = im.mathOp(EMathOp.MUL)) {
        try (MathOpParam p1 = mo.mathOpParam()) {
          p1.writeInt(50);
        }
        try (MathOpParam p2 = mo.mathOpParam()) {
          p2.macroInvoke(Macros.SCALE);
        }
      }
    }
    dest.write(" generations. As the number of artificial chromosomes used and the injection frequency is sort of random, we only consider population size times iteration limit to infer the number of consumed "); //$NON-NLS-1$
    Accessor.FE.writeShortName(dest, true);
    dest.write(". Also, for some benchmark instances different results are reported, in which case we always chose those in favour of "); //$NON-NLS-1$
    dest.write(this.getShortName());
    dest.write(". The reported results are the runtime (in seconds), as well as mean and best results obtained from 30 executions per benchmark instance."); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final void initialize(final Header header) throws IOException {
    Accessor.FE.define(header);
    Macros.SCALE.define(header);
  }
}
