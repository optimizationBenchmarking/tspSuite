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
import org.logisticPlanning.utils.math.statistics.EStatisticParameter;

/**
 * The results from&nbsp;[<a href="#cite_MF2001MAFTTSP"
 * style="font-weight:bold">1</a>]. <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_MF2001MAFTTSP" /><a
 * href="http://dag.informatik.uni-kl.de/people/merz/">Peter Merz</a>
 * and&nbsp;<a href=
 * "http://www.uni-marburg.de/fb12/verteilte_systeme/mitarbeiter/webfreisleb"
 * >Bernd Freisleben</a>: <span style="font-weight:bold">&ldquo;Memetic
 * Algorithms for the Traveling Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Complex Systems</span>
 * 13(4):297&ndash;345, 2001; published by Champaign, IL, USA: Complex
 * Systems Publications, Inc.. ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/08912513">0891-2513</a>. <div>links:
 * [<a href="http://www.complex-systems.com/pdf/13-4-1.pdf">1</a>]
 * and&nbsp;[<a
 * href="http://agva.informatik.uni-kl.de/papers/cs-tsp.ps.gz">2</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.6.4205">10.1
 * .1.6 .4205</a></div></div></li>
 * </ol>
 */
final class _MF2001MAFTTSP extends LiteratureResultPointSet {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * the result point set for paper&nbsp;[<a href="#cite_MF2001MAFTTSP"
   * style="font-weight:bold">1</a>]
   */
  static final LiteratureResultPointSet MF2001MAFTTSP = new _MF2001MAFTTSP();

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
              new BibAuthor("Peter", "Merz"),//$NON-NLS-1$//$NON-NLS-2$
              new BibAuthor("Bernd", "Freisleben")//$NON-NLS-1$//$NON-NLS-2$
          ),//
          "Memetic Algorithms for the Traveling Salesman Problem",//$NON-NLS-1$
          new BibDate(2001),//
          "Complex Systems",//$NON-NLS-1$;
          "13",//$NON-NLS-1$;
          "4",//$NON-NLS-1$;
          "297",//$NON-NLS-1$;
          "345",//$NON-NLS-1$;
          new URI("http://www.complex-systems.com/pdf/13-4-1.pdf"),//$NON-NLS-1$;
          null//
      );
    } catch (final URISyntaxException use) {
      throw new RuntimeException(use);
    }
  }

  /** create */
  private _MF2001MAFTTSP() {
    super("Memetic Algorithms",//$NON-NLS-1$
        "MAs",//$NON-NLS-1$
        new BibRecord[] { _MF2001MAFTTSP.__rec() },//
        new LiteratureResultPoint[] {//
        //
        //
            new LiteratureResultPoint(Instance.LIN318,//
                Accessor.RUNTIME, 8e3d,//
                Accessor.F, 42029d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.PCB442,//
                Accessor.RUNTIME, 68e3d,//
                Accessor.F, 50778d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(
                Instance.ATT532,//
                Accessor.RUNTIME,
                60e3d,//
                Accessor.F_RELATIVE, 0.013e-2d,
                EStatisticParameter.ARITHMETIC_MEAN),// //
            //
            new LiteratureResultPoint(Instance.ATT532,//
                Accessor.RUNTIME, 106e3d,//
                Accessor.F, 27686d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.RAT783,//
                Accessor.RUNTIME, 26e3d,//
                Accessor.F, 8806d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.PR1002,//
                Accessor.RUNTIME, 112e3d,//
                Accessor.F, 259045d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            //
            new LiteratureResultPoint(
                Instance.FL1577,//
                Accessor.RUNTIME,
                200e3d,//
                Accessor.F_RELATIVE, 0.174e-2d,
                EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(
                Instance.FL1577,//
                Accessor.RUNTIME,
                300e3d,//
                Accessor.F_RELATIVE, 0.027e-2d,
                EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(
                Instance.PR2392,//
                Accessor.RUNTIME,
                400e3d,//
                Accessor.F_RELATIVE, 0.020e-2d,
                EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.PR2392,//
                Accessor.RUNTIME, 2588e3d,//
                Accessor.F, 378032.6d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            //
            new LiteratureResultPoint(
                Instance.PCB3038,//
                Accessor.RUNTIME,
                800e3d,//
                Accessor.F_RELATIVE, 0.094e-2d,
                EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(
                Instance.PCB3038,//
                Accessor.RUNTIME,
                6955e3d,//
                Accessor.F_RELATIVE, 137702.6d,
                EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(
                Instance.FL3795,//
                Accessor.RUNTIME,
                7212e3d,//
                Accessor.F_RELATIVE, 28794.7d,
                EStatisticParameter.ARITHMETIC_MEAN),//
            //
            //
            //
            new LiteratureResultPoint(Instance.FNL4461,//
                Accessor.RUNTIME, 105e3d,//
                Accessor.F, 183762.7d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(
                Instance.PLA7397,//
                Accessor.RUNTIME,
                802e3d,//
                Accessor.F, 23328499.5d,
                EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.RL11849,//
                Accessor.RUNTIME, 417e3d,//
                Accessor.F, 931333.5d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(
                Instance.USA13509,//
                Accessor.RUNTIME,
                790e3d,//
                Accessor.F, 20186311.8d,
                EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.D18512,//
                Accessor.RUNTIME, 930e3d,//
                Accessor.F, 653474.3d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(
                Instance.PLA33810,//
                Accessor.RUNTIME,
                3443e3d,//
                Accessor.F, 66575838.8d,
                EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(
                Instance.PLA85900,//
                Accessor.RUNTIME,
                12314e3d,//
                Accessor.F, 143596390.7d,
                EStatisticParameter.ARITHMETIC_MEAN),//
            //
            //
            new LiteratureResultPoint(Instance.FNL4461,//
                Accessor.RUNTIME, 294e3d,//
                Accessor.F, 183366.3d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(
                Instance.PLA7397,//
                Accessor.RUNTIME,
                1860e3d,//
                Accessor.F, 23307621.7d,
                EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.RL11849,//
                Accessor.RUNTIME, 1006e3d,//
                Accessor.F, 928115.5d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(
                Instance.USA13509,//
                Accessor.RUNTIME,
                2422e3d,//
                Accessor.F, 20125182.2d,
                EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.D18512,//
                Accessor.RUNTIME, 2873e3d,//
                Accessor.F, 650803.2d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(
                Instance.PLA33810,//
                Accessor.RUNTIME,
                11523e3d,//
                Accessor.F, 66321344.7d,
                EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(
                Instance.PLA85900,//
                Accessor.RUNTIME,
                52180e3d,//
                Accessor.F, 142986675.5d,
                EStatisticParameter.ARITHMETIC_MEAN),//
            //
            //
            //
            new LiteratureResultPoint(Instance.FNL4461,//
                Accessor.RUNTIME, 742e3d,//
                Accessor.F, 183047.1d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(
                Instance.PLA7397,//
                Accessor.RUNTIME,
                3789e3d,//
                Accessor.F, 23294046.2d,
                EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.RL11849,//
                Accessor.RUNTIME, 2503e3d,//
                Accessor.F, 926253.7d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(
                Instance.USA13509,//
                Accessor.RUNTIME,
                6638e3d,//
                Accessor.F, 20057767.0d,
                EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.D18512,//
                Accessor.RUNTIME, 7451e3d,//
                Accessor.F, 649354.6d, EStatisticParameter.ARITHMETIC_MEAN),//
        //
        });
  }

  /** {@inheritDoc} */
  @Override
  public final void writeDescription(final AbstractTextComplex dest)
      throws IOException {
    dest.write("In "); //$NON-NLS-1$
    this.cite(ECitationMode.BY_ID_IN_SENTENCE, dest);
    dest.write(", several different "); //$NON-NLS-1$
    dest.write(this.getLongName());
    dest.write(" ("); //$NON-NLS-1$
    dest.write(this.getShortName());
    dest.write(") are introduced for TSPs, including different combinations of crossover operators and local search methods (such as Lin-Kernighan) with different setups. The algorithms were implemented in C++ and experiments were run on a Pentium III Processor (500 MHz) under Linux 2.2. We always use the best results from any combination if two measure points with similar signature are availble in the paper."); //$NON-NLS-1$
  }

}
