package org.logisticPlanning.tsp.evaluation.data.properties.litComp.results;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.properties.litComp.LiteratureResultPoint;
import org.logisticPlanning.tsp.evaluation.data.properties.litComp.LiteratureResultPointSet;
import org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.tsp.DescBenchmarks;
import org.logisticPlanning.utils.document.spec.AbstractTextComplex;
import org.logisticPlanning.utils.document.spec.ECitationMode;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.bib.BibAuthor;
import org.logisticPlanning.utils.document.spec.bib.BibAuthors;
import org.logisticPlanning.utils.document.spec.bib.BibDate;
import org.logisticPlanning.utils.document.spec.bib.BibInProceedings;
import org.logisticPlanning.utils.document.spec.bib.BibProceedings;
import org.logisticPlanning.utils.document.spec.bib.BibRecord;
import org.logisticPlanning.utils.document.spec.bib.EBibMonth;
import org.logisticPlanning.utils.math.statistics.EStatisticParameter;

/**
 * The results from&nbsp;[<a href="#cite_GM1998IOOFTT"
 * style="font-weight:bold">1</a>]. <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_GM1998IOOFTT" />Guo Tao and&nbsp;<a
 * href="http://cs.adelaide.edu.au/~zbyszek/">Zbigniew Michalewicz</a>:
 * <span style="font-weight:bold">&ldquo;Inver-over Operator for the
 * TSP,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the 5th
 * International Conference on Parallel Problem Solving from Nature (PPSN
 * V)</span>, September&nbsp;27&ndash;30, 1998, Amsterdam, The Netherlands,
 * pages 803&ndash;812, <a href="http://www.cs.vu.nl/~gusz/">&#193;goston
 * E. Eiben</a> aka. Gusz/Guszti, <a
 * href="http://www.liacs.nl/~baeck/contact.htm">Thomas B&#228;ck</a>, <a
 * href="http://www.lri.fr/~marc/">Marc Schoenauer</a>, and&nbsp;<a
 * href="https://en.wikipedia.org/wiki/Hans-Paul_Schwefel">Hans-Paul
 * Schwefel</a>, editors, volume 1498/1998 of Lecture Notes in Computer
 * Science (LNCS), Berlin, Germany: Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540650784">3-540-65078-4</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540650782">978-3-540-
 * 65078-2</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/BFb0056922">10.1007/BFb0056922</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/39739752">39739752</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=HLeU_1TkwTsC">HLeU_1TkwTsC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a>. <div>links:
 * [<a href="http://cs.adelaide.edu.au/~zbyszek/Papers/p44.pdf">1</a>]
 * and&nbsp;[<a href="http://lisas.de/~hakan/file/p44.pdf">2</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.94.7376"
 * >10.1.1.94.7376</a></div></div></li>
 * </ol>
 */
final class _GM1998IOOFTT extends LiteratureResultPointSet {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * the result point set for paper&nbsp;[<a href="#cite_GM1998IOOFTT"
   * style="font-weight:bold">1</a>]
   */
  static final LiteratureResultPointSet GM1998IOOFTT = new _GM1998IOOFTT();

  /**
   * get the bib record
   *
   * @return the bib record
   */
  private static final BibRecord __rec() {
    try {

      return new BibInProceedings(
          //
          new BibAuthors(//
              new BibAuthor("Guo", "Tao"),//$NON-NLS-1$//$NON-NLS-2$
              new BibAuthor("Zbigniew", "Michalewicz")//$NON-NLS-1$//$NON-NLS-2$
          ),//
          "Inver-over Operator for the TSP",//$NON-NLS-1$
          new BibProceedings(//
              "PPSN V: Proceedings of the 5th International Conference on Parallel Problem Solving from Nature",//$NON-NLS-1$
              new BibDate(1998, EBibMonth.AUGUST, 27),//
              new BibDate(1998, EBibMonth.AUGUST, 30), //
              new BibAuthors(//
                  new BibAuthor("\u00c1goston E.", "Eiben"),//$NON-NLS-1$//$NON-NLS-2$
                  new BibAuthor("Thomas", "B\u00e4ck"),//$NON-NLS-1$//$NON-NLS-2$
                  new BibAuthor("Marc", "Schoenauer"),//$NON-NLS-1$//$NON-NLS-2$
                  new BibAuthor("Hans-Paul", "Schwefel")//$NON-NLS-1$//$NON-NLS-2$
              ),//
              "Amsterdam, The Netherlands",//$NON-NLS-1$
              "Springer-Verlag GmbH",//$NON-NLS-1$
              "Berlin, Germany",//$NON-NLS-1$
              "Lecture Notes in Computer Science (LNCS)",//$NON-NLS-1$
              "1498/1998",//$NON-NLS-1$
              null,//
              "10.1007/BFb0056843"//$NON-NLS-1$
          ),//
          "803", "812",//$NON-NLS-1$//$NON-NLS-2$
          null,//
          new URI("http://cs.adelaide.edu.au/~zbyszek/Papers/p44.pdf"),//$NON-NLS-1$
          "10.1007/BFb0056922"//$NON-NLS-1$
      );

    } catch (final URISyntaxException use) {
      throw new RuntimeException(use);
    }
  }

  /** create */
  private _GM1998IOOFTT() {
    super("Evolutionary algorithm with the Inver-Over Operator",//$NON-NLS-1$
        "IO-GA",//$NON-NLS-1$
        new BibRecord[] { _GM1998IOOFTT.__rec() },//
        new LiteratureResultPoint[] {//

            //
            new LiteratureResultPoint(Instance.EIL51,//
                Accessor.FE, (399 * 100),//
                Accessor.F, 426d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.EIL51,//
                Accessor.RUNTIME, 1.09e3d,//
                Accessor.F, 426d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.EIL76,//
                Accessor.FE, (651 * 100),//
                Accessor.F, 538d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.EIL76,//
                Accessor.RUNTIME, 2.11e3d,//
                Accessor.F, 538d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.EIL101,//
                Accessor.FE, (2447 * 100),//
                Accessor.F, 629.2d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.EIL101,//
                Accessor.RUNTIME, 7.52e3d,//
                Accessor.F, 629.2d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.ST70,//
                Accessor.FE, (643 * 100),//
                Accessor.F, 675d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.ST70,//
                Accessor.RUNTIME, 1.98e3d,//
                Accessor.F, 675d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.KROA100,//
                Accessor.FE, (785 * 100),//
                Accessor.F, 21282d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.KROA100,//
                Accessor.RUNTIME, 2.94e3d,//
                Accessor.F, 21282d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.KROC100,//
                Accessor.FE, (874 * 100),//
                Accessor.F, 20749d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.KROC100,//
                Accessor.RUNTIME, 3.23e3d,//
                Accessor.F, 20749d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.KROD100,//
                Accessor.FE, (1221 * 100),//
                Accessor.F, 21294d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.KROD100,//
                Accessor.RUNTIME, 4.13e3d,//
                Accessor.F, 21294d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.LIN105,//
                Accessor.FE, (876 * 100),//
                Accessor.F, 14379d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.LIN105,//
                Accessor.RUNTIME, 3.34e3d,//
                Accessor.F, 14379d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.PCB442,//
                Accessor.FE, (23265 * 100),//
                Accessor.F, 51097.5d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.PCB442,//
                Accessor.RUNTIME, 172.21e3d,//
                Accessor.F, 51097.5d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.PR2392,//
                Accessor.FE, (126846 * 100),//
                Accessor.F, 388095d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.PR2392,//
                Accessor.RUNTIME, 5366.23e3d,//
                Accessor.F, 388095d, EStatisticParameter.ARITHMETIC_MEAN),//
        });
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
    dest.write(") is introduced. It is a Genetic Algorithm, which uses the special inver-operator that inverts (reverses) a sub-sequence of a solution by using knowledge obtained from the population. It is applied to 13 TSP instances, 11 of which are from TSPLIB"); //$NON-NLS-1$
    DescBenchmarks.citeTSPLib(ECitationMode.BY_AUTHORS_IN_SENTENCE, dest);
    dest.write(" and thus useful for us. The authors provide the mean results, runtime in seconds (on a Pentium Pro 180 machine), and algorithm iterations, for ten runs per problem instance. The population size of the algorithm is set to "); //$NON-NLS-1$
    dest.writeInt(100);
    dest.write(" and we thus simply multiply it with the consumed algorithm iterations to get the "); //$NON-NLS-1$
    Accessor.FE.writeShortName(dest, true);
    dest.writeChar('.');
  }

  /** {@inheritDoc} */
  @Override
  public final void initialize(final Header header) throws IOException {
    Accessor.FE.define(header);
  }
}
