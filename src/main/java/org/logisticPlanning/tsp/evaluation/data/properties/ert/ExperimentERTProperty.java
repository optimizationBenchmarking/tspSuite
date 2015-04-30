package org.logisticPlanning.tsp.evaluation.data.properties.ert;

import java.io.IOException;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.evaluation.data.EPropertyType;
import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.data.Property;
import org.logisticPlanning.tsp.evaluation.data.RunSet;
import org.logisticPlanning.utils.collections.lists.ArraySetView;
import org.logisticPlanning.utils.document.spec.Document;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.math.data.collection.IDataCollection;
import org.logisticPlanning.utils.math.data.collection.SubListView;
import org.logisticPlanning.utils.math.data.collection.TransformedCollectionView;
import org.logisticPlanning.utils.math.functions.UnaryFunction;
import org.logisticPlanning.utils.math.functions.arithmetic.Identity;
import org.logisticPlanning.utils.math.functions.compound.FixedScale;
import org.logisticPlanning.utils.math.statistics.series.StatisticSeries;
import org.logisticPlanning.utils.utils.HashUtils;

/**
 * <p>
 * This property creates statistic series representing the Estimated
 * Running Time (ERT) to success&nbsp;[<a href="#cite_HAFR2012RPBBOBES"
 * style="font-weight:bold">1</a>, <a href="#cite_AH2005PEOAALSEA"
 * style="font-weight:bold">2</a>, <a href="#cite_P1997DEVTFOT2I"
 * style="font-weight:bold">3</a>] over relative objective value thresholds
 * for a given experiment, by aggregating single
 * {@link org.logisticPlanning.tsp.evaluation.data.properties.ert.ERTSeriesProperty
 * ERTSeriesProperties} over a set of benchmark instances.
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
public class ExperimentERTProperty extends
    Property<Experiment, StatisticSeries> {

  /** the dimensions */
  private static final int[] DIMS = { ERTPoint.DIM_RELATIVE_THRESHOLD,
      ERTPoint.DIM_ERT };

  /** the ert source property */
  private final ERTSeriesProperty m_ert;

  /** the instances property */
  private final Property<? super Experiment, ArraySetView<Instance>> m_insts;

  /** the hash code */
  private final int m_hc;

  /**
   * Create the property
   * 
   * @param ert
   *          the ert
   * @param insts
   *          the instances
   */
  public ExperimentERTProperty(final ERTSeriesProperty ert,
      final Property<? super Experiment, ArraySetView<Instance>> insts) {
    this(EPropertyType.NEVER_STORED, ert, insts);
  }

  /**
   * Create the property
   * 
   * @param type
   *          the property type
   * @param ert
   *          the ert
   * @param insts
   *          the instances
   */
  public ExperimentERTProperty(final EPropertyType type,
      final ERTSeriesProperty ert,
      final Property<? super Experiment, ArraySetView<Instance>> insts) {
    super(type);
    this.m_ert = ert;
    this.m_insts = insts;

    this.m_hc = HashUtils.combineHashes(//
        HashUtils.combineHashes(//
            HashUtils.hashCode(this.m_ert),//
            HashUtils.hashCode(this.m_insts)),//
        HashUtils.hashCode(this.getClass()));//
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return this.m_hc;
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equals(final Object o) {
    ExperimentERTProperty epb;
    if (o == this) {
      return true;
    }
    if (o instanceof ExperimentERTProperty) {
      epb = ((ExperimentERTProperty) o);
      return ((this.m_ert.equals(epb.m_ert)) && //
      (this.m_insts.equals(epb.m_insts)));
    }
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public final StatisticSeries compute(final Experiment dataset,
      final Document doc) {
    final ArraySetView<Instance> insts;
    final int size;
    ERTSeries ser;
    Instance inst;
    IDataCollection[] dest, t;
    IDataCollection col;
    long scale;
    int i;

    insts = this.m_insts.get(dataset, doc);
    size = insts.size();
    dest = new IDataCollection[size];
    i = 0;
    for (final RunSet rs : dataset) {
      inst = rs.getInstance();
      if (insts.contains(inst)) {
        ser = this.m_ert.get(rs, doc);
        scale = ser.getAccessor().calculateScale(inst.n());

        col = new SubListView(this.m_ert.get(rs, doc),
            ExperimentERTProperty.DIMS);
        if (scale != 1l) {
          col = new TransformedCollectionView(col,//
              new UnaryFunction[] { Identity.INSTANCE,
                  FixedScale.scale(Identity.INSTANCE, (1d / scale)) });
        }
        dest[i++] = col;
      }
    }

    if (i < dest.length) {
      t = new IDataCollection[i];
      System.arraycopy(dest, 0, t, 0, i);
      dest = t;
    }

    return new StatisticSeries(dest, 0, 1);
  }

  /** {@inheritDoc} */
  @Override
  public final void initialize(final Header header) throws IOException {
    super.initialize(header);
    this.m_ert.initialize(header);
    this.m_insts.initialize(header);
  }
}
