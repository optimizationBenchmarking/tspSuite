package org.logisticPlanning.tsp.evaluation.data.properties.ecdf;

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
import org.logisticPlanning.utils.math.statistics.series.StatisticSeries;
import org.logisticPlanning.utils.utils.HashUtils;

/**
 * <p>
 * This property creates statistic series representing the change of the
 * the Empirical (Cumulative) Distribution Function (ECDF)&nbsp;[<a
 * href="#cite_HAFR2012RPBBOBES" style="font-weight:bold">1</a>, <a
 * href="#cite_HS1998ELVAPAR" style="font-weight:bold">2</a>] over a scaled
 * time measure for a given experiment, by aggregating single
 * {@link org.logisticPlanning.tsp.evaluation.data.properties.ecdf.RunSetECDFProperty
 * RunsetECDFProperties} over a set of benchmark instances.
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
public class ExperimentECDFProperty extends
Property<Experiment, StatisticSeries> {

  /** the ecdf source property */
  private final RunSetECDFProperty m_ecdf;

  /** the instances property */
  private final Property<? super Experiment, ArraySetView<Instance>> m_insts;

  /** the hash code */
  private final int m_hc;

  /**
   * Create the property
   *
   * @param ecdf
   *          the ecdf
   * @param insts
   *          the instances
   */
  public ExperimentECDFProperty(final RunSetECDFProperty ecdf,
      final Property<? super Experiment, ArraySetView<Instance>> insts) {
    this(EPropertyType.NEVER_STORED, ecdf, insts);
  }

  /**
   * Create the property
   *
   * @param type
   *          the property type
   * @param ecdf
   *          the ecdf
   * @param insts
   *          the instances
   */
  public ExperimentECDFProperty(final EPropertyType type,
      final RunSetECDFProperty ecdf,
      final Property<? super Experiment, ArraySetView<Instance>> insts) {
    super(type);
    this.m_ecdf = ecdf;
    this.m_insts = insts;

    this.m_hc = HashUtils.combineHashes(//
        HashUtils.combineHashes(//
            HashUtils.hashCode(this.m_ecdf),//
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
    ExperimentECDFProperty epb;
    if (o == this) {
      return true;
    }
    if (o instanceof ExperimentECDFProperty) {
      epb = ((ExperimentECDFProperty) o);
      return ((this.m_ecdf.equals(epb.m_ecdf)) && //
          (this.m_insts.equals(epb.m_insts)));
    }
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public final StatisticSeries compute(final Experiment dataset,
      final Document doc) {
    final ArraySetView<Instance> inst;
    final int size;
    IDataCollection[] dest, t;
    int i;

    inst = this.m_insts.get(dataset, doc);
    size = inst.size();
    dest = new IDataCollection[size];
    i = 0;
    for (final RunSet rs : dataset) {
      if (inst.contains(rs.getInstance())) {
        dest[i++] = this.m_ecdf.get(rs, doc);
      }
    }

    if (i < dest.length) {
      t = new IDataCollection[i];
      System.arraycopy(dest, 0, t, 0, i);
      dest = t;
    }

    return new StatisticSeries(dest,//
        RunSetECDFProperty.SCALED_X_DIM,//
        RunSetECDFProperty.SUCCESS_PROBABILITY_DIM);
  }

  /** {@inheritDoc} */
  @Override
  public final void initialize(final Header header) throws IOException {
    super.initialize(header);
    this.m_ecdf.initialize(header);
    this.m_insts.initialize(header);
  }
}
