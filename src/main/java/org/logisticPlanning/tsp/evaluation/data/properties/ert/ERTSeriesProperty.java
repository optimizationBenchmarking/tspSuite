package org.logisticPlanning.tsp.evaluation.data.properties.ert;

import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.EPropertyType;
import org.logisticPlanning.tsp.evaluation.data.Property;
import org.logisticPlanning.tsp.evaluation.data.RunSet;
import org.logisticPlanning.tsp.evaluation.data.properties.objective.RunsetObjectiveThresholdsProperty;
import org.logisticPlanning.utils.document.spec.Document;
import org.logisticPlanning.utils.utils.HashUtils;

/**
 * <p>
 * A class which can compute the Estimated Running Time (ERT) to
 * success&nbsp;[<a href="#cite_HAFR2012RPBBOBES"
 * style="font-weight:bold">1</a>, <a href="#cite_AH2005PEOAALSEA"
 * style="font-weight:bold">2</a>, <a href="#cite_P1997DEVTFOT2I"
 * style="font-weight:bold">3</a>] and store it as an array for several
 * different threshold values. This class stores the real, absolute
 * objective value thresholds as well.
 * </p>
 * <h2>References</h2>
 * <ol>
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
 * <li><div><span id="cite_P1997DEVTFOT2I" />Kenneth V. Price: <span
 * style="font-weight:bold">&ldquo;Differential Evolution vs. the Functions
 * of the 2nd ICEO,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the IEEE
 * International Conference on Evolutionary Computation (CEC'97)</span>,
 * April&nbsp;13&ndash;16, 1997, Indianapolis, IN, USA, pages
 * 153&ndash;157, <a href="http://www.liacs.nl/~baeck/contact.htm">Thomas
 * B&#228;ck</a>, <a href="http://cs.adelaide.edu.au/~zbyszek/">Zbigniew
 * Michalewicz</a>, and&nbsp;<a href="http://www.cs.bham.ac.uk/~xin/">Xin
 * Yao</a> <span style="color:gray">[&#23002;&#26032;</span>], editors,
 * Piscataway, NJ, USA: IEEE Computer Society. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780339495">0-7803-3949-5</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780339491">978-0-7803
 * -3949-1</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/ICEC.1997.592287">10.1109/ICEC.1997
 * .592287</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=6JZVAAAAMAAJ">6JZVAAAAMAAJ</a>;
 * INSPEC Accession Number:&nbsp;5573043</div></li>
 * </ol>
 */
public final class ERTSeriesProperty extends Property<RunSet, ERTSeries> {

  /**
   * the {@link org.logisticPlanning.tsp.evaluation.data.Accessor#DE
   * distance evaluation}-based series property
   */
  public static final ERTSeriesProperty DE_ERT_SERIES = //
  new ERTSeriesProperty(Accessor.DE);

  /**
   * the {@link org.logisticPlanning.tsp.evaluation.data.Accessor#FE
   * function evaluation}-based series property
   */
  public static final ERTSeriesProperty FE_ERT_SERIES = //
  new ERTSeriesProperty(Accessor.FE);

  /**
   * the {@link org.logisticPlanning.tsp.evaluation.data.Accessor#RUNTIME
   * runtime}-based series property
   */
  public static final ERTSeriesProperty RUNTIME_ERT_SERIES = //
  new ERTSeriesProperty(Accessor.RUNTIME);

  /**
   * the
   * {@link org.logisticPlanning.tsp.evaluation.data.Accessor#NORMALIZED_RUNTIME
   * normalized runtime}-based series property
   */
  public static final ERTSeriesProperty NORMALIZED_RUNTIME_ERT_SERIES = //
  new ERTSeriesProperty(Accessor.NORMALIZED_RUNTIME);

  /** the accessor */
  private final Accessor m_axs;

  /** the hash code */
  private final int m_hc;

  /**
   * create the ert series property
   *
   * @param axs
   *          the accessor
   */
  private ERTSeriesProperty(final Accessor axs) {
    super(EPropertyType.TEMPORARILY_STORED);

    ERTSeries._checkAccessor(axs);
    this.m_axs = axs;
    this.m_hc = HashUtils.combineHashes(//
        HashUtils.hashCode(this.getClass()),//
        HashUtils.hashCode(this.m_axs));
  }

  /** {@inheritDoc} */
  @Override
  protected final ERTSeries compute(final RunSet dataset,
      final Document doc) {
    return ERTSeries.makeERTSeries(dataset,//
        RunsetObjectiveThresholdsProperty.INSTANCE.get(dataset, doc),//
        this.m_axs);
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equals(final Object o) {
    return ((o == this) || ((o instanceof ERTSeriesProperty) && (((ERTSeriesProperty) o).m_axs == this.m_axs)));
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return this.m_hc;
  }

  /**
   * Get the ert serues property for the given accessor
   *
   * @param axs
   *          the accessor
   * @return the corresponding property
   */
  public static final ERTSeriesProperty forAccessor(final Accessor axs) {
    switch (axs) {
      case DE: {
        return ERTSeriesProperty.DE_ERT_SERIES;
      }
      case FE: {
        return ERTSeriesProperty.FE_ERT_SERIES;
      }
      case RUNTIME: {
        return ERTSeriesProperty.RUNTIME_ERT_SERIES;
      }
      case NORMALIZED_RUNTIME: {
        return ERTSeriesProperty.NORMALIZED_RUNTIME_ERT_SERIES;
      }
      default: {
        return new ERTSeriesProperty(axs);
      }
    }
  }
}
