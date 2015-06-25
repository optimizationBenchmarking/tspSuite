package org.logisticPlanning.tsp.evaluation.data.properties.litComp.results;

import java.io.IOException;

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
import org.logisticPlanning.utils.document.spec.bib.BibAuthor;
import org.logisticPlanning.utils.document.spec.bib.BibAuthors;
import org.logisticPlanning.utils.document.spec.bib.BibDate;
import org.logisticPlanning.utils.document.spec.bib.BibInProceedings;
import org.logisticPlanning.utils.document.spec.bib.BibProceedings;
import org.logisticPlanning.utils.document.spec.bib.BibRecord;
import org.logisticPlanning.utils.document.spec.bib.EBibMonth;
import org.logisticPlanning.utils.math.statistics.EStatisticParameter;

/**
 * The results from&nbsp;[<a href="#cite_WY2004AHEAFTSP"
 * style="font-weight:bold">1</a>]: each initialization counts as 1 FE, a
 * crossover counts as 1 FE, a local search counts as 1 FE. 1 mutation
 * would count as 2 FEs, but the mutation rate was not specified in the
 * paper so we do not account for that. <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_WY2004AHEAFTSP" />Christopher M. White
 * and&nbsp;<a href="http://www.ece.okstate.edu/faculty/yen/">Gary G.
 * Yen</a>: <span style="font-weight:bold">&ldquo;A Hybrid Evolutionary
 * Algorithm for Traveling Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the IEEE
 * Congress on Evolutionary Computation (CEC'04)</span>,
 * June&nbsp;20&ndash;23, 2004, Portland, OR, USA, pages 1473&ndash;1478,
 * Los Alamitos, CA, USA: IEEE Computer Society Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780385152">0-7803-8515-2</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780385153">978-0-7803
 * -8515-3</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CEC.2004.1331070">10.1109/CEC.2004
 * .1331070</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=I8_5AAAACAAJ">I8_5AAAACAAJ</a>;
 * INSPEC Accession Number:&nbsp;8229101</div></li>
 * </ol>
 */
final class _WY2004AHEAFTSP extends LiteratureResultPointSet {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * the result point set for paper&nbsp;[<a href="#cite_WY2004AHEAFTSP"
   * style="font-weight:bold">1</a>]
   */
  static final LiteratureResultPointSet WY2004AHEAFTSP = new _WY2004AHEAFTSP();

  /** create */
  private _WY2004AHEAFTSP() {
    super(
        "Hybrid Genetic Algorithm using ANT-DPX crossover, 2-opt Local Search, and Nearest Neighbor initialization",//$NON-NLS-1$
        "Hybrid GA",//$NON-NLS-1$
        new BibRecord[] { new BibInProceedings(
            //
            new BibAuthors(//
                new BibAuthor("Christopher M.", "White"),//$NON-NLS-1$//$NON-NLS-2$
                new BibAuthor("Gary G.", "Yen")//$NON-NLS-1$//$NON-NLS-2$
            ),//
            "A Hybrid Evolutionary Algorithm for Traveling Salesman Problem",//$NON-NLS-1$
            new BibProceedings(//
                "CEC'04: Proceedings of the IEEE Congress on Evolutionary Computation, vol. 2",//$NON-NLS-1$
                new BibDate(2004, EBibMonth.JUNE, 20),//
                new BibDate(2004, EBibMonth.JUNE, 23),//
                BibAuthors.EMPTY,//
                "Portland, OR, USA",//$NON-NLS-1$
                "IEEE Computer Society Press",//$NON-NLS-1$
                "Los Alamitos, CA, USA",//$NON-NLS-1$
                null, null,//
                null, null//
            ),//
            "1473", "1478",//$NON-NLS-1$//$NON-NLS-2$
            null,//
            null,//
            "10.1109/CEC.2004.1331070"//$NON-NLS-1$
        ) },//
        new LiteratureResultPoint[] {//
        //
            new LiteratureResultPoint(Instance.EIL51,//
                Accessor.FE, _WY2004AHEAFTSP.__FEs(Instance.EIL51),//
                Accessor.F, 428.5d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.EIL51,//
                Accessor.FE, _WY2004AHEAFTSP.__FEs(Instance.EIL51),//
                Accessor.F, 428d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.KROA100,//
                Accessor.FE, _WY2004AHEAFTSP.__FEs(Instance.KROA100),//
                Accessor.F, 21285d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.KROA100,//
                Accessor.FE, _WY2004AHEAFTSP.__FEs(Instance.KROA100),//
                Accessor.F, 21285d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.D198,//
                Accessor.FE, _WY2004AHEAFTSP.__FEs(Instance.D198),//
                Accessor.F, 15839.5d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.D198,//
                Accessor.FE, _WY2004AHEAFTSP.__FEs(Instance.D198),//
                Accessor.F, 15797d, EStatisticParameter.MINIMUM),//
            //
            new LiteratureResultPoint(Instance.LIN318,//
                Accessor.FE, _WY2004AHEAFTSP.__FEs(Instance.LIN318),//
                Accessor.F, 42605.3d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.LIN318,//
                Accessor.FE, _WY2004AHEAFTSP.__FEs(Instance.LIN318),//
                Accessor.F, 42334d, EStatisticParameter.MINIMUM),//
        });
  }

  /**
   * calculate the FEs: population size is {@code n}, 1000 generations with
   * initialization, and in each generation, there are {@code n/2}
   * offspring from crossover which then undergo a local search.
   *
   * @param inst
   *          the instance
   * @return the FEs
   */
  private static final long __FEs(final Instance inst) {
    final int n;

    n = inst.n();

    return (n + (999 * ((n / 2) * 2)));
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
    dest.write(") is introduced. The algorithm starts by creating "); //$NON-NLS-1$
    dest.macroInvoke(Macros.SCALE);
    dest.write(" solutions with the nearest neighbor heuristic. In each generation, "); //$NON-NLS-1$
    try (InlineMath im = dest.inlineMath()) {
      try (MathOp mo = im.mathOp(EMathOp.DIV_INLINE)) {
        try (MathOpParam p1 = mo.mathOpParam()) {
          p1.macroInvoke(Macros.SCALE);
        }
        try (MathOpParam p2 = mo.mathOpParam()) {
          p2.writeInt(2);
        }
      }
    }
    dest.write(" new individuals are created by using the ANT-DPX crossover operation. Each of them then undergoes a 2-opt local search and, with a predefined probability, mutation, before replacing the worst individuals in the parent population. The algorithm was implemented in MATLAB and experiments were carried out for "); //$NON-NLS-1$
    dest.writeInt(1000);
    dest.write(" generations on a single processor lGHz Pentium 4 machine. Reported are mean and best solution quality over 10 runs per benchmark instance. As the exact mutation probability is not known, we do not account for it when estimating the number of "); //$NON-NLS-1$
    Accessor.FE.writeShortName(dest, true);
    dest.write(". The number of steps of a local search are also not known, so we assume "); //$NON-NLS-1$
    dest.writeInt(1);
    Accessor.FE.writeShortName(dest, false);
    dest.write(" per local search, plus one fro the crossover. In other words, the time estimation is in favour of "); //$NON-NLS-1$
    dest.write(this.getShortName());
    dest.writeChar('.');
  }

  /** {@inheritDoc} */
  @Override
  public final void initialize(final Header header) throws IOException {
    Accessor.FE.define(header);
    Macros.SCALE.define(header);
  }
}
