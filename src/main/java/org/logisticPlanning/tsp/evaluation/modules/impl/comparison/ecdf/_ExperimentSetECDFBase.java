package org.logisticPlanning.tsp.evaluation.modules.impl.comparison.ecdf;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;

import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.data.properties.auc.AreaUnderCurve;
import org.logisticPlanning.tsp.evaluation.data.properties.auc.AreaUnderCurveComparator;
import org.logisticPlanning.tsp.evaluation.data.properties.ranking.RankAggregate;
import org.logisticPlanning.tsp.evaluation.data.properties.ranking.Ranking;
import org.logisticPlanning.tsp.evaluation.modules.impl.comparison.RankedComparisonModule;
import org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.eval.DescAUC;
import org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.eval.DescECDF;
import org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.tsp.DescRelativeObjectiveValues;
import org.logisticPlanning.tsp.evaluation.modules.impl.macros.Macros;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;
import org.logisticPlanning.utils.document.spec.EDefaultFigureSize;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.SectionBody;
import org.logisticPlanning.utils.math.data.collection.IDataCollection;
import org.logisticPlanning.utils.math.data.iteration.DataCollectionIterator2D;
import org.logisticPlanning.utils.math.data.iteration.StairsKeepLeftIterator2D;
import org.logisticPlanning.utils.math.data.point.Point2D;
import org.logisticPlanning.utils.math.statistics.EStatisticParameter;

/**
 * <p>
 * The internal base class for Empirical (Cumulative) Distribution
 * Functions (ECDFs)&nbsp;[<a href="#cite_HAFR2012RPBBOBES"
 * style="font-weight:bold">1</a>, <a href="#cite_HS1998ELVAPAR"
 * style="font-weight:bold">2</a>] diagrams.
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
class _ExperimentSetECDFBase extends RankedComparisonModule {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the name prefix */
  static final String _NAME_PFX;

  static {
    String s;

    s = Macros.ECDF.getPlaceholder();
    _NAME_PFX = ("experimentSet" + //$NON-NLS-1$
        Character.toUpperCase(s.charAt(0)) + s.substring(1));
  }

  /** the figure size parameter */
  private final String m_sizeParam;
  /** the figure size */
  EDefaultFigureSize m_size;

  /** the auc description */
  private final DescAUC m_auc;

  /** the relative objective values */
  final DescRelativeObjectiveValues m_descRel;
  /** the ecdf description */
  final DescECDF m_ecdf;

  /**
   * create!
   * 
   * @param owner
   *          the macro's owner
   * @param name
   *          the name
   * @param isActive
   *          should this module be active?
   */
  _ExperimentSetECDFBase(final String name, final Module owner,
      final boolean isActive) {
    super(
        _ExperimentSetECDFBase._NAME_PFX + name + "Diagrams", owner, isActive); //$NON-NLS-1$

    this.m_sizeParam = (this.name() + Module.FIGURE_SIZE_PARAM);
    this.m_size = EDefaultFigureSize.FOUR_IN_A_ROW;

    this.m_auc = this.getRoot().findInstance(DescAUC.class);
    this.addDependency(this.m_auc);

    this.m_ecdf = this.getRoot().findInstance(DescECDF.class);
    this.addDependency(this.m_ecdf);

    this.m_descRel = this.getRoot().findInstance(
        DescRelativeObjectiveValues.class);
    this.addDependency(this.m_descRel);
  }

  /**
   * Write a description of the ranking result
   * 
   * @param body
   *          the body
   * @param data
   *          the experiment set
   * @param rag
   *          the rank aggregate
   * @throws IOException
   *           if i/o fails
   */
  void _doRanks(final SectionBody body, final ExperimentSet data,
      final RankAggregate<Experiment> rag) throws IOException {
    final Ranking<Experiment> ranking;
    ranking = rag.rank(EStatisticParameter.MEDIAN);

    body.write(//
    "For each algorithm and benchmark case, we compute the "); //$NON-NLS-1$
    body.macroInvoke(Macros.AUC);
    body.write(" discussed in "); //$NON-NLS-1$
    body.reference(this.m_auc.getLabel(data));
    body.write(//
    ". We extend the areas to the left by finding the smallest x-coordinate and adding a point with that coordinate and ");//$NON-NLS-1$
    body.writeInt(0);
    body.write(" as "); //$NON-NLS-1$
    body.macroInvoke(Macros.ECDF);
    body.write(//
    " to all curves. To the right, we chose the largest x-coordinate amongst all curves. Each curve is then extended by one point with that x-coordinate and the y-coordinate of its right-most point. This extension makes sense instead of cutting the curves off on the right since some algorithms may reach the optimum very quickly, which would make the comparison interval very small. We then compute "); //$NON-NLS-1$
    body.macroInvoke(Macros.AUC);
    body.write(//
    " for each algorithm and benchmark case, by using the exactly same scaling as in the figures (only the ranges of the axes may differ). For each benchmark case we then rank the methods according to their corresponding "); //$NON-NLS-1$
    body.macroInvoke(Macros.AUC);
    body.write(//
    " (larger areas are better). The methods are then re-ranked according to their median rank over all benchmark cases, as discussed in "); //$NON-NLS-1$
    body.reference(this.m_descRanking.getLabel(data));
    body.writeChar('.');

    body.writeLinebreak();
    body.write("As a result,"); //$NON-NLS-1$
    this.summarizeRanks(ranking,
        body,//
        "either more often achieve the target relative objective value, to achieve it faster, or both"); //$NON-NLS-1$
    this.propagateRanking(ranking, data, body.getDocument());
  }

  /**
   * Aggregate the experiment performance based on the area under the
   * curve. We find the smallest x-coordinate {@code s} for all data
   * collections and add point {@code (s, 0)} to all collections. We then
   * find the largest x-coordinate and simply extend the collections with
   * their last y-coordinate to that x-coordinate.
   * 
   * @param lst
   *          the list of assignments of experiments to data collections
   * @param agg
   *          the aggregated ranking
   */
  @SuppressWarnings("unchecked")
  static final void _aggregate(
      final ArrayList<Map.Entry<Experiment, Object>> lst,
      final RankAggregate<Experiment> agg) {
    double right, left, x;
    int s, i;
    IDataCollection c;
    Point2D l, r;

    left = Double.POSITIVE_INFINITY;
    right = Double.NEGATIVE_INFINITY;
    for (final Map.Entry<Experiment, Object> e : lst) {
      c = ((IDataCollection) (e.getValue()));

      s = c.size();
      for (i = s; (--i) >= 0;) {
        x = c.get(i, 0);
        if (x < Double.POSITIVE_INFINITY) {
          if (x > right) {
            right = x;
          }
          break;
        }
      }

      for (i = 0; i < s; i++) {
        x = c.get(i, 0);
        if (x > Double.NEGATIVE_INFINITY) {
          if (x < left) {
            left = x;
          }
          break;
        }
      }
    }

    for (final Map.Entry<Experiment, Object> e : lst) {
      c = ((IDataCollection) (e.getValue()));
      l = r = null;

      if (c.get(0, 0) > left) {
        l = new Point2D(left, 0d);
      }

      if (c.get(s = (c.size() - 1), 0) < right) {
        r = new Point2D(right, c.get(s, 1));
      }

      e.setValue(new AreaUnderCurve(new StairsKeepLeftIterator2D(//
          new DataCollectionIterator2D(l, c, r))));
    }

    agg.aggregateRanks(//
    Ranking.rank(AreaUnderCurveComparator.LARGER_IS_BETTER,//
        lst.toArray(new Map.Entry[lst.size()])));
  }

  /** {@inheritDoc} */
  @Override
  public void configure(final Configuration config) {
    super.configure(config);

    this.m_size = config.getConstant(this.m_sizeParam,
        EDefaultFigureSize.class, EDefaultFigureSize.class, this.m_size);

  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable.printKey(this.m_sizeParam, ps);
    Configurable.printlnObject(this.m_size, ps);

  }

  /** {@inheritDoc} */
  @Override
  public void printParameters(final PrintStream ps) {
    super.printParameters(ps);

    Configurable.printKey(this.m_sizeParam, ps);
    ps.println(Module.FIGURE_SIZE_PARAM_DESC_PREFIX + this.name());

  }

  /** {@inheritDoc} */
  @Override
  protected void initialize(final Header header, final ExperimentSet data)
      throws IOException {//
    super.initialize(header, data);
    Macros.AUC.define(header);
    Macros.ECDF.define(header);
    Macros.SCALE.define(header);
    Macros.F_OPTIMAL.define(header);
    Macros.F_BEST_RELATIVE.define(header);
    Macros.F_BEST.define(header);
    Macros.ECDF.define(header);
  }
}
