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
import org.logisticPlanning.utils.document.spec.bib.EBibQuarter;
import org.logisticPlanning.utils.math.statistics.EStatisticParameter;

/**
 * The results from&nbsp;[<a href="#cite_ACR2003CLKFLTSP"
 * style="font-weight:bold">1</a>]. <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_ACR2003CLKFLTSP" /><a
 * href="http://www.research.att.com/people/Applegate_David_L">David Lee
 * Applegate</a>, <a href="http://www2.isye.gatech.edu/~wcook/">William
 * John Cook</a>, and&nbsp;Andr&#233; Rohe: <span
 * style="font-weight:bold">&ldquo;Chained Lin-Kernighan for Large
 * Traveling Salesman Problems,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">INFORMS Journal on
 * Computing (JOC)</span> 15(1):82&ndash;92, Winter&nbsp;2003; published by
 * Linthicum, ML, USA: Institute for Operations Research and the Management
 * Sciences (INFORMS) and&nbsp;Cambridge, MA, USA: HighWire Press (Stanford
 * University). ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/10919856">1091-9856</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15265528">1526-5528</a>. <div>links:
 * [<a href="http://www2.isye.gatech.edu/~wcook/papers/clk_ijoc.pdf">1</a>]
 * and&nbsp;[<a
 * href="http://joc.journal.informs.org/content/15/1/82.full.pdf">2</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.22.8342">10.1
 * .1.22 .8342</a></div></div></li>
 * </ol>
 */
final class _ACR2003CLKFLTSP extends LiteratureResultPointSet {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * the result point set for paper&nbsp;[<a href="#cite_ACR2003CLKFLTSP"
   * style="font-weight:bold">1</a>]
   */
  static final LiteratureResultPointSet ACR2003CLKFLTSP = new _ACR2003CLKFLTSP();

  /** create */
  private _ACR2003CLKFLTSP() {
    super("Chained Lin-Kernighan Algorithm",//$NON-NLS-1$
        "CLK",//$NON-NLS-1$
        new BibRecord[] { _ACR2003CLKFLTSP.__rec() },//
        new LiteratureResultPoint[] {//
        //
        new LiteratureResultPoint(Instance.RL11849,//
            Accessor.RUNTIME, (60e3d),//
            Accessor.F, _ACR2003CLKFLTSP.__value(Instance.RL11849,
                0.51d),//
                EStatisticParameter.ARITHMETIC_MEAN),//
                //
                new LiteratureResultPoint(Instance.RL11849,//
                    Accessor.RUNTIME, (600e3d),//
                    Accessor.F, _ACR2003CLKFLTSP.__value(Instance.RL11849,
                        0.29d),//
                        EStatisticParameter.ARITHMETIC_MEAN),//
                        //
                        new LiteratureResultPoint(Instance.RL11849,//
                            Accessor.RUNTIME, (3600e3d),//
                            Accessor.F, _ACR2003CLKFLTSP.__value(Instance.RL11849,
                                0.24d),//
                                EStatisticParameter.ARITHMETIC_MEAN),//
                                //
                                new LiteratureResultPoint(Instance.RL11849,//
                                    Accessor.RUNTIME, (14400e3d),//
                                    Accessor.F, _ACR2003CLKFLTSP.__value(Instance.RL11849,
                                        0.22d),//
                                        EStatisticParameter.ARITHMETIC_MEAN),//
                                        //
                                        new LiteratureResultPoint(Instance.RL11849,//
                                            Accessor.RUNTIME, (86400e3d),//
                                            Accessor.F, _ACR2003CLKFLTSP.__value(Instance.RL11849,
                                                0.19d),//
                                                EStatisticParameter.ARITHMETIC_MEAN),//
                                                //
                                                //
                                                //
                                                //
                                                new LiteratureResultPoint(Instance.USA13509,//
                                                    Accessor.RUNTIME, (60e3d),//
                                                    Accessor.F, _ACR2003CLKFLTSP.__value(Instance.USA13509,
                                                        0.45d),//
                                                        EStatisticParameter.ARITHMETIC_MEAN),//
                                                        //
                                                        new LiteratureResultPoint(Instance.USA13509,//
                                                            Accessor.RUNTIME, (600e3d),//
                                                            Accessor.F, _ACR2003CLKFLTSP.__value(Instance.USA13509,
                                                                0.22d),//
                                                                EStatisticParameter.ARITHMETIC_MEAN),//
                                                                //
                                                                new LiteratureResultPoint(Instance.USA13509,//
                                                                    Accessor.RUNTIME, (3600e3d),//
                                                                    Accessor.F, _ACR2003CLKFLTSP.__value(Instance.USA13509,
                                                                        0.15d),//
                                                                        EStatisticParameter.ARITHMETIC_MEAN),//
                                                                        //
                                                                        new LiteratureResultPoint(Instance.USA13509,//
                                                                            Accessor.RUNTIME, (14400e3d),//
                                                                            Accessor.F, _ACR2003CLKFLTSP.__value(Instance.USA13509,
                                                                                0.13d),//
                                                                                EStatisticParameter.ARITHMETIC_MEAN),//
                                                                                //
                                                                                new LiteratureResultPoint(Instance.USA13509,//
                                                                                    Accessor.RUNTIME, (86400e3d),//
                                                                                    Accessor.F, _ACR2003CLKFLTSP.__value(Instance.USA13509,
                                                                                        0.09d),//
                                                                                        EStatisticParameter.ARITHMETIC_MEAN),//
                                                                                        //
                                                                                        //
                                                                                        //
                                                                                        //
                                                                                        new LiteratureResultPoint(Instance.BRD14051,//
                                                                                            Accessor.RUNTIME, (60e3d),//
                                                                                            Accessor.F, _ACR2003CLKFLTSP.__value(Instance.BRD14051,
                                                                                                0.49d),//
                                                                                                EStatisticParameter.ARITHMETIC_MEAN),//
                                                                                                //
                                                                                                new LiteratureResultPoint(Instance.BRD14051,//
                                                                                                    Accessor.RUNTIME, (600e3d),//
                                                                                                    Accessor.F, _ACR2003CLKFLTSP.__value(Instance.BRD14051,
                                                                                                        0.16d),//
                                                                                                        EStatisticParameter.ARITHMETIC_MEAN),//
                                                                                                        //
                                                                                                        new LiteratureResultPoint(Instance.BRD14051,//
                                                                                                            Accessor.RUNTIME, (3600e3d),//
                                                                                                            Accessor.F, _ACR2003CLKFLTSP.__value(Instance.BRD14051,
                                                                                                                0.11d),//
                                                                                                                EStatisticParameter.ARITHMETIC_MEAN),//
                                                                                                                //
                                                                                                                new LiteratureResultPoint(Instance.BRD14051,//
                                                                                                                    Accessor.RUNTIME, (14400e3d),//
                                                                                                                    Accessor.F, _ACR2003CLKFLTSP.__value(Instance.BRD14051,
                                                                                                                        0.09d),//
                                                                                                                        EStatisticParameter.ARITHMETIC_MEAN),//
                                                                                                                        //
                                                                                                                        new LiteratureResultPoint(Instance.BRD14051,//
                                                                                                                            Accessor.RUNTIME, (86400e3d),//
                                                                                                                            Accessor.F, _ACR2003CLKFLTSP.__value(Instance.BRD14051,
                                                                                                                                0.07d),//
                                                                                                                                EStatisticParameter.ARITHMETIC_MEAN),//
                                                                                                                                //
                                                                                                                                //
                                                                                                                                //
                                                                                                                                //
                                                                                                                                new LiteratureResultPoint(Instance.D15112,//
                                                                                                                                    Accessor.RUNTIME, (60e3d),//
                                                                                                                                    Accessor.F, _ACR2003CLKFLTSP.__value(Instance.D15112,
                                                                                                                                        0.39d),//
                                                                                                                                        EStatisticParameter.ARITHMETIC_MEAN),//
                                                                                                                                        //
                                                                                                                                        new LiteratureResultPoint(Instance.D15112,//
                                                                                                                                            Accessor.RUNTIME, (600e3d),//
                                                                                                                                            Accessor.F, _ACR2003CLKFLTSP.__value(Instance.D15112,
                                                                                                                                                0.17d),//
                                                                                                                                                EStatisticParameter.ARITHMETIC_MEAN),//
                                                                                                                                                //
                                                                                                                                                new LiteratureResultPoint(Instance.D15112,//
                                                                                                                                                    Accessor.RUNTIME, (3600e3d),//
                                                                                                                                                    Accessor.F, _ACR2003CLKFLTSP.__value(Instance.D15112,
                                                                                                                                                        0.11d),//
                                                                                                                                                        EStatisticParameter.ARITHMETIC_MEAN),//
                                                                                                                                                        //
                                                                                                                                                        new LiteratureResultPoint(Instance.D15112,//
                                                                                                                                                            Accessor.RUNTIME, (14400e3d),//
                                                                                                                                                            Accessor.F, _ACR2003CLKFLTSP.__value(Instance.D15112,
                                                                                                                                                                0.07d),//
                                                                                                                                                                EStatisticParameter.ARITHMETIC_MEAN),//
                                                                                                                                                                //
                                                                                                                                                                new LiteratureResultPoint(Instance.D15112,//
                                                                                                                                                                    Accessor.RUNTIME, (86400e3d),//
                                                                                                                                                                    Accessor.F, _ACR2003CLKFLTSP.__value(Instance.D15112,
                                                                                                                                                                        0.06d),//
                                                                                                                                                                        EStatisticParameter.ARITHMETIC_MEAN),//
                                                                                                                                                                        //
                                                                                                                                                                        //
                                                                                                                                                                        //
                                                                                                                                                                        //
                                                                                                                                                                        new LiteratureResultPoint(Instance.D18512,//
                                                                                                                                                                            Accessor.RUNTIME, (60e3d),//
                                                                                                                                                                            Accessor.F, _ACR2003CLKFLTSP.__value(Instance.D18512,
                                                                                                                                                                                0.42d),//
                                                                                                                                                                                EStatisticParameter.ARITHMETIC_MEAN),//
                                                                                                                                                                                //
                                                                                                                                                                                new LiteratureResultPoint(Instance.D18512,//
                                                                                                                                                                                    Accessor.RUNTIME, (600e3d),//
                                                                                                                                                                                    Accessor.F, _ACR2003CLKFLTSP.__value(Instance.D18512,
                                                                                                                                                                                        0.18d),//
                                                                                                                                                                                        EStatisticParameter.ARITHMETIC_MEAN),//
                                                                                                                                                                                        //
                                                                                                                                                                                        new LiteratureResultPoint(Instance.D18512,//
                                                                                                                                                                                            Accessor.RUNTIME, (3600e3d),//
                                                                                                                                                                                            Accessor.F, _ACR2003CLKFLTSP.__value(Instance.D18512,
                                                                                                                                                                                                0.12d),//
                                                                                                                                                                                                EStatisticParameter.ARITHMETIC_MEAN),//
                                                                                                                                                                                                //
                                                                                                                                                                                                new LiteratureResultPoint(Instance.D18512,//
                                                                                                                                                                                                    Accessor.RUNTIME, (14400e3d),//
                                                                                                                                                                                                    Accessor.F, _ACR2003CLKFLTSP.__value(Instance.D18512,
                                                                                                                                                                                                        0.09d),//
                                                                                                                                                                                                        EStatisticParameter.ARITHMETIC_MEAN),//
                                                                                                                                                                                                        //
                                                                                                                                                                                                        new LiteratureResultPoint(Instance.D18512,//
                                                                                                                                                                                                            Accessor.RUNTIME, (86400e3d),//
                                                                                                                                                                                                            Accessor.F, _ACR2003CLKFLTSP.__value(Instance.D18512,
                                                                                                                                                                                                                0.07d),//
                                                                                                                                                                                                                EStatisticParameter.ARITHMETIC_MEAN),//
                                                                                                                                                                                                                //
                                                                                                                                                                                                                //
                                                                                                                                                                                                                //
                                                                                                                                                                                                                //
                                                                                                                                                                                                                new LiteratureResultPoint(Instance.PLA33810,//
                                                                                                                                                                                                                    Accessor.RUNTIME, (60e3d),//
                                                                                                                                                                                                                    Accessor.F, _ACR2003CLKFLTSP.__value(Instance.PLA33810,
                                                                                                                                                                                                                        0.73d),//
                                                                                                                                                                                                                        EStatisticParameter.ARITHMETIC_MEAN),//
                                                                                                                                                                                                                        //
                                                                                                                                                                                                                        new LiteratureResultPoint(Instance.PLA33810,//
                                                                                                                                                                                                                            Accessor.RUNTIME, (600e3d),//
                                                                                                                                                                                                                            Accessor.F, _ACR2003CLKFLTSP.__value(Instance.PLA33810,
                                                                                                                                                                                                                                0.41d),//
                                                                                                                                                                                                                                EStatisticParameter.ARITHMETIC_MEAN),//
                                                                                                                                                                                                                                //
                                                                                                                                                                                                                                new LiteratureResultPoint(Instance.PLA33810,//
                                                                                                                                                                                                                                    Accessor.RUNTIME, (3600e3d),//
                                                                                                                                                                                                                                    Accessor.F, _ACR2003CLKFLTSP.__value(Instance.PLA33810,
                                                                                                                                                                                                                                        0.30d),//
                                                                                                                                                                                                                                        EStatisticParameter.ARITHMETIC_MEAN),//
                                                                                                                                                                                                                                        //
                                                                                                                                                                                                                                        new LiteratureResultPoint(Instance.PLA33810,//
                                                                                                                                                                                                                                            Accessor.RUNTIME, (14400e3d),//
                                                                                                                                                                                                                                            Accessor.F, _ACR2003CLKFLTSP.__value(Instance.PLA33810,
                                                                                                                                                                                                                                                0.26d),//
                                                                                                                                                                                                                                                EStatisticParameter.ARITHMETIC_MEAN),//
                                                                                                                                                                                                                                                //
                                                                                                                                                                                                                                                new LiteratureResultPoint(Instance.PLA33810,//
                                                                                                                                                                                                                                                    Accessor.RUNTIME, (86400e3d),//
                                                                                                                                                                                                                                                    Accessor.F, _ACR2003CLKFLTSP.__value(Instance.PLA33810,
                                                                                                                                                                                                                                                        0.23d),//
                                                                                                                                                                                                                                                        EStatisticParameter.ARITHMETIC_MEAN),//
                                                                                                                                                                                                                                                        //
                                                                                                                                                                                                                                                        //
                                                                                                                                                                                                                                                        //
                                                                                                                                                                                                                                                        //
                                                                                                                                                                                                                                                        //
                                                                                                                                                                                                                                                        //
                                                                                                                                                                                                                                                        //
                                                                                                                                                                                                                                                        //
                                                                                                                                                                                                                                                        new LiteratureResultPoint(Instance.PLA85900,//
                                                                                                                                                                                                                                                            Accessor.RUNTIME, (60e3d),//
                                                                                                                                                                                                                                                            Accessor.F, _ACR2003CLKFLTSP.__value(Instance.PLA85900,
                                                                                                                                                                                                                                                                0.85d),//
                                                                                                                                                                                                                                                                EStatisticParameter.ARITHMETIC_MEAN),//
                                                                                                                                                                                                                                                                //
                                                                                                                                                                                                                                                                new LiteratureResultPoint(Instance.PLA85900,//
                                                                                                                                                                                                                                                                    Accessor.RUNTIME, (600e3d),//
                                                                                                                                                                                                                                                                    Accessor.F, _ACR2003CLKFLTSP.__value(Instance.PLA85900,
                                                                                                                                                                                                                                                                        0.34d),//
                                                                                                                                                                                                                                                                        EStatisticParameter.ARITHMETIC_MEAN),//
                                                                                                                                                                                                                                                                        //
                                                                                                                                                                                                                                                                        new LiteratureResultPoint(Instance.PLA85900,//
                                                                                                                                                                                                                                                                            Accessor.RUNTIME, (3600e3d),//
                                                                                                                                                                                                                                                                            Accessor.F, _ACR2003CLKFLTSP.__value(Instance.PLA85900,
                                                                                                                                                                                                                                                                                0.25d),//
                                                                                                                                                                                                                                                                                EStatisticParameter.ARITHMETIC_MEAN),//
                                                                                                                                                                                                                                                                                //
                                                                                                                                                                                                                                                                                new LiteratureResultPoint(Instance.PLA85900,//
                                                                                                                                                                                                                                                                                    Accessor.RUNTIME, (14400e3d),//
                                                                                                                                                                                                                                                                                    Accessor.F, _ACR2003CLKFLTSP.__value(Instance.PLA85900,
                                                                                                                                                                                                                                                                                        0.21d),//
                                                                                                                                                                                                                                                                                        EStatisticParameter.ARITHMETIC_MEAN),//
                                                                                                                                                                                                                                                                                        //
                                                                                                                                                                                                                                                                                        new LiteratureResultPoint(Instance.PLA85900,//
                                                                                                                                                                                                                                                                                            Accessor.RUNTIME, (86400e3d),//
                                                                                                                                                                                                                                                                                            Accessor.F, _ACR2003CLKFLTSP.__value(Instance.PLA85900,
                                                                                                                                                                                                                                                                                                0.16d),//
                                                                                                                                                                                                                                                                                                EStatisticParameter.ARITHMETIC_MEAN),//
                                                                                                                                                                                                                                                                                                //

    });
  }

  /**
   * calculate the value
   *
   * @param inst
   *          the instance
   * @param percent
   *          the percentage
   * @return the value
   */
  private static final double __value(final Instance inst,
      final double percent) {
    switch (inst.n()) {
      case 11849: {
        return (inst.optimum() * (1d + (percent * 1e-2d)));
      }
      case 13509: {
        return (inst.optimum() * (1d + (percent * 1e-2d)));
      }
      case 14051: {
        return (469374d * (1d + (percent * 1e-2d)));
      }
      case 15112: {
        return (inst.optimum() * (1d + (percent * 1e-2d)));
      }
      case 18512: {
        return (645198d * (1d + (percent * 1e-2d)));
      }
      case 33810: {
        return (66005185d * (1d + (percent * 1e-2d)));
      }
      case 85900: {
        return (142307500d * (1d + (percent * 1e-2d)));
      }
      default: {
        throw new IllegalArgumentException();
      }
    }
  }

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
              new BibAuthor("David L.", "Applegate"), //$NON-NLS-1$ //$NON-NLS-2$
              new BibAuthor("William J.", "Cook"),//$NON-NLS-1$ //$NON-NLS-2$
              new BibAuthor("Andr\u00e9", "Rohe")//$NON-NLS-1$ //$NON-NLS-2$
              ),//
              "Chained Lin-Kernighan for Large Traveling Salesman Problems",//$NON-NLS-1$
              new BibDate(2003, EBibQuarter.WINTER),//
              "INFORMS Journal on Computing",//$NON-NLS-1$
              "15",//$NON-NLS-1$
              "1",//$NON-NLS-1$
              "82",//$NON-NLS-1$
              "92",//$NON-NLS-1$
              new URI(
                  "http://joc.journal.informs.org/content/15/1/82.full.pdf"),//$NON-NLS-1$
                  null);
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
    dest.write(", the "); //$NON-NLS-1$
    dest.write(this.getLongName());
    dest.write(" ("); //$NON-NLS-1$
    dest.write(this.getShortName());
    dest.write(") for large TSP instances is introduced. The algorithm is initialized with Quick-Bor\u016fvka and the kicks are 50 step and 100 step random walks. Experiments are performed on a 300 MHz Pentium II workstation and the results are aggregated over five independent runs per benchmark."); //$NON-NLS-1$
  }
}
