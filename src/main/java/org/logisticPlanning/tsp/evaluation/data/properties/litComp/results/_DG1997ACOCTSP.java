package org.logisticPlanning.tsp.evaluation.data.properties.litComp.results;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.properties.litComp.LiteratureResultPoint;
import org.logisticPlanning.tsp.evaluation.data.properties.litComp.LiteratureResultPointSet;
import org.logisticPlanning.utils.document.spec.AbstractTextComplex;
import org.logisticPlanning.utils.document.spec.ECitationMode;
import org.logisticPlanning.utils.document.spec.bib.BibArticle;
import org.logisticPlanning.utils.document.spec.bib.BibAuthor;
import org.logisticPlanning.utils.document.spec.bib.BibAuthors;
import org.logisticPlanning.utils.document.spec.bib.BibDate;
import org.logisticPlanning.utils.document.spec.bib.BibRecord;
import org.logisticPlanning.utils.document.spec.bib.EBibMonth;
import org.logisticPlanning.utils.math.statistics.EStatisticParameter;

/**
 * The results from&nbsp;[<a href="#cite_DG1997ACOCTSP"
 * style="font-weight:bold">1</a>]. <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_DG1997ACOCTSP" /><a
 * href="https://en.wikipedia.org/wiki/Marco_Dorigo">Marco Dorigo</a>
 * and&nbsp;<a href="http://www.idsia.ch/~luca/">Luca Maria
 * Gambardella</a>: <span style="font-weight:bold">&ldquo;Ant Colony
 * System: A Cooperative Learning Approach to the Traveling Salesman
 * Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">IEEE Transactions on
 * Evolutionary Computation (IEEE-EC)</span> 1(1):53&ndash;66,
 * April&nbsp;1997; published by Washington, DC, USA: IEEE Computer
 * Society. LCCN:&nbsp;<a href="http://lccn.loc.gov/97648327">97648327</a>;
 * doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/4235.585892">10.1109/4235.585892</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/1089778X">1089-778X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/19410026">1941-0026</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=ITEVF5"
 * >ITEVF5</a>; further information: [<a
 * href="http://www.ieee-cis.org/pubs/tec/">1</a>]. <div>link: [<a
 * href="http://www.idsia.ch/~luca/acs-ec97.pdf">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.49.7702">10.1
 * .1.49 .7702</a></div></div></li>
 * </ol>
 */
final class _DG1997ACOCTSP extends LiteratureResultPointSet {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * the result point set for paper&nbsp;[<a href="#cite_DG1997ACOCTSP"
   * style="font-weight:bold">1</a>]
   */
  static final LiteratureResultPointSet DG1997ACOCTSP = new _DG1997ACOCTSP();

  /** create */
  private _DG1997ACOCTSP() {
    super("Ant Colony System Plus Local Search",//$NON-NLS-1$
        "ACS+LS",//$NON-NLS-1$
        new BibRecord[] { _DG1997ACOCTSP.__lit() },//
        new LiteratureResultPoint[] {//
        // // pure ACS
        // new LiteratureResultPoint(Instance.KROA100,//
        // Accessor.FE, 4820d,//
        // Accessor.F, 21282d,
        // EStatisticParameter.ARITHMETIC_MEAN),//
        // //
        // new LiteratureResultPoint(Instance.D198,//
        // Accessor.FE, 585000d,//
        // Accessor.F, 15888d, EStatisticParameter.MINIMUM),//
        // ////
        // new LiteratureResultPoint(Instance.PCB442,//
        // Accessor.FE, 595000d,//
        // Accessor.F, 51268d, EStatisticParameter.MINIMUM),//
        // ////
        // new LiteratureResultPoint(Instance.ATT532,//
        // Accessor.FE, 830658d,//
        // Accessor.F, 28147d, EStatisticParameter.MINIMUM),//
        // ////
        // new LiteratureResultPoint(Instance.RAT783,//
        // Accessor.FE, 991276d,//
        // Accessor.F, 9015d, EStatisticParameter.MINIMUM),//
        // ////
        // new LiteratureResultPoint(Instance.FL1577,//
        // Accessor.FE, 942000d,//
        // Accessor.F, 22977d, EStatisticParameter.MINIMUM),//
        // //
        // ACS + 3-opt
        //
        // values of this instance are wrong...
        // new LiteratureResultPoint(Instance.P43,//
        // Accessor.RUNTIME, 1e3d,//
        // Accessor.F, 2810d, EStatisticParameter.MINIMUM),//
        // // //
        // new LiteratureResultPoint(Instance.P43,//
        // Accessor.RUNTIME, 2e3d,//
        // Accessor.F, 2810d,
        // EStatisticParameter.ARITHMETIC_MEAN),//
        //
        new LiteratureResultPoint(Instance.RY48P,//
            Accessor.RUNTIME, 2e3d,//
            Accessor.F, 14422d, EStatisticParameter.MINIMUM),//
            // //
            new LiteratureResultPoint(Instance.RY48P,//
                Accessor.RUNTIME, 19e3d,//
                Accessor.F, 14422d, EStatisticParameter.ARITHMETIC_MEAN),//
                //
                new LiteratureResultPoint(Instance.FT70,//
                    Accessor.RUNTIME, 3e3d,//
                    Accessor.F, 38673d, EStatisticParameter.MINIMUM),//
                    // //
                    new LiteratureResultPoint(Instance.FT70,//
                        Accessor.RUNTIME, 6e3d,//
                        Accessor.F, 38679.8d, EStatisticParameter.ARITHMETIC_MEAN),//
                        //
                        new LiteratureResultPoint(Instance.KRO124P,//
                            Accessor.RUNTIME, 3e3d,//
                            Accessor.F, 36230, EStatisticParameter.MINIMUM),//
                            // //
                            new LiteratureResultPoint(Instance.KRO124P,//
                                Accessor.RUNTIME, 25e3d,//
                                Accessor.F, 36230d, EStatisticParameter.ARITHMETIC_MEAN),//
                                //
                                new LiteratureResultPoint(Instance.FTV170,//
                                    Accessor.RUNTIME, 17e3d,//
                                    Accessor.F, 2755d, EStatisticParameter.MINIMUM),//
                                    // //
                                    new LiteratureResultPoint(Instance.FTV170,//
                                        Accessor.RUNTIME, 68e3d,//
                                        Accessor.F, 2755d, EStatisticParameter.ARITHMETIC_MEAN),//
                                        //
                                        //
                                        new LiteratureResultPoint(Instance.D198,//
                                            Accessor.RUNTIME, 16e3d,//
                                            Accessor.F, 15780d, EStatisticParameter.MINIMUM),//
                                            // //
                                            new LiteratureResultPoint(Instance.D198,//
                                                Accessor.RUNTIME, 238e3d,//
                                                Accessor.F, 15781.7d, EStatisticParameter.ARITHMETIC_MEAN),//
                                                //
                                                new LiteratureResultPoint(Instance.LIN318,//
                                                    Accessor.RUNTIME, 101e3d,//
                                                    Accessor.F, 42029d, EStatisticParameter.MINIMUM),//
                                                    // //
                                                    new LiteratureResultPoint(Instance.LIN318,//
                                                        Accessor.RUNTIME, 537e3d,//
                                                        Accessor.F, 42029d, EStatisticParameter.ARITHMETIC_MEAN),// //
                                                        //
                                                        new LiteratureResultPoint(Instance.ATT532,//
                                                            Accessor.RUNTIME, 133e3d,//
                                                            Accessor.F, 27693d, EStatisticParameter.MINIMUM),//
                                                            // //
                                                            new LiteratureResultPoint(Instance.ATT532,//
                                                                Accessor.RUNTIME, 810e3d,//
                                                                Accessor.F, 27718.2d, EStatisticParameter.ARITHMETIC_MEAN),// //
                                                                //
                                                                new LiteratureResultPoint(Instance.RAT783,//
                                                                    Accessor.RUNTIME, 1317e3d,//
                                                                    Accessor.F, 8818d, EStatisticParameter.MINIMUM),//
                                                                    // //
                                                                    new LiteratureResultPoint(Instance.RAT783,//
                                                                        Accessor.RUNTIME, 1280e3d,//
                                                                        Accessor.F, 8837.9d, EStatisticParameter.ARITHMETIC_MEAN),//

    });
  }

  /**
   * create the literature references
   *
   * @return the literature references
   */
  private static final BibRecord __lit() {
    try {
      return new BibArticle(
          //
          new BibAuthors(//
              new BibAuthor("Marco", "Dorigo"),//$NON-NLS-1$//$NON-NLS-2$
              new BibAuthor("Luca Maria", "Gambardella")//$NON-NLS-1$//$NON-NLS-2$
              ),//
              "Ant Colony System: A Cooperative Learning Approach to the Traveling Salesman Problem",//$NON-NLS-1$
              new BibDate(1997, EBibMonth.APRIL),//
              "IEEE Transactions on Evolutionary Computation",//$NON-NLS-1$
              "1",//$NON-NLS-1$
              "1",//$NON-NLS-1$
              "53",//$NON-NLS-1$
              "66",//$NON-NLS-1$
              new URI("http://www.idsia.ch/~luca/acs-ec97.pdf"),//$NON-NLS-1$
              "10.1109/4235.585892"//$NON-NLS-1$
          );
    } catch (final URISyntaxException use) {
      throw new RuntimeException(use);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void writeDescription(final AbstractTextComplex dest)
      throws IOException {
    dest.write("In "); //$NON-NLS-1$
    this.cite(ECitationMode.BY_ID_IN_SENTENCE, dest);
    dest.write(", an "); //$NON-NLS-1$
    dest.write(this.getLongName());
    dest.write(" ("); //$NON-NLS-1$
    dest.write(this.getShortName());
    dest.write(") is introduced. This method extends the well-known Ant Colony System with a restricted 3-opt local search (for symmetric problems, also 2-opt moves are considered) and uses candidate lists as well as don't-look bits to speed up the procedure. The asymmetric problem instances have been solved on a UN Ultra1 SPARC Station (167 Mhz), while experiments for symmetric problems were run on a SGI Challenge L server with eight 200 MHz CPU (using only 1 of them). Results are averaged over ten trials."); //$NON-NLS-1$
  }
}
