package org.logisticPlanning.tsp.evaluation.modules.impl.comparison.globalRanking;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.logisticPlanning.tsp.evaluation.Evaluator;
import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.data.properties.ranking.GlobalRanking;
import org.logisticPlanning.tsp.evaluation.data.properties.ranking.GlobalRankingProperty;
import org.logisticPlanning.tsp.evaluation.data.properties.ranking.Ranking;
import org.logisticPlanning.tsp.evaluation.modules.impl.comparison.RankedComparisonModule;
import org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.eval.DescRanking;
import org.logisticPlanning.tsp.evaluation.modules.spec.ComparisonModule;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.utils.document.spec.Emphasize;
import org.logisticPlanning.utils.document.spec.Enumeration;
import org.logisticPlanning.utils.document.spec.EnumerationItem;
import org.logisticPlanning.utils.document.spec.Label;
import org.logisticPlanning.utils.document.spec.SectionBody;
import org.logisticPlanning.utils.document.spec.SectionTitle;
import org.logisticPlanning.utils.math.statistics.EStatisticParameter;

/**
 * A globally aggregated ranking. We use a <em>fractional ranking</em>
 * &nbsp;[<a href="#cite_WIKIPEDIA2013RANKING"
 * style="font-weight:bold">1</a>]. Ranks are aggregated by ranking the
 * median ranks. This is simpler than other methods&nbsp;[<a
 * href="#cite_KS1998OPCATAAA" style="font-weight:bold">2</a>, <a
 * href="#cite_M2010TNRCRIINAIBTOSIN" style="font-weight:bold">3</a>] and
 * applicable to large amounts of data, plus it should be quite robust and
 * allow to pick up strong trends only. See the documentation of class
 * {@link org.logisticPlanning.tsp.evaluation.data.properties.ranking.Ranking
 * Ranking} for more details.<h2>References</h2>
 * <ol>
 * <li><div><span id="cite_WIKIPEDIA2013RANKING" /><span
 * style="font-style:italic;font-family:cursive;"
 * >&ldquo;Ranking,&rdquo;</span> (Website), August&nbsp;21, 2013, San
 * Francisco, CA, USA: Wikimedia Foundation, Inc. and&nbsp;San Francisco,
 * CA, USA: Wikimedia Foundation, Inc.. <div>link: [<a
 * href="http://en.wikipedia.org/wiki/Ranking">1</a>]</div></div></li>
 * <li><div><span id="cite_KS1998OPCATAAA" /><a href=
 * "http://academic.research.microsoft.com/Author/1577890/pekka-j-korhonen"
 * >Pekka J. Korhonen</a> and&nbsp;Aapo Siljam&#228;ki: <span
 * style="font-weight:bold">&ldquo;Ordinal Principal Component Analysis
 * Theory and an Application,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Computational Statistics
 * &amp; Data Analysis</span> 26(4):411&ndash;424, February&nbsp;6, 1998;
 * published by Essex, UK: Elsevier Science Publishers B.V.. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1016/S0167-9473(97)00038-8"
 * >10.1016/S0167-9473(97)00038-8</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/01679473">0167-9473</a></div></li>
 * <li><div><span id="cite_M2010TNRCRIINAIBTOSIN" /><a
 * href="http://www.geocities.ws/nehu_economics/biodata.htm">Sudhanshu
 * Kumar Mishra</a>: <span style="font-weight:bold">&ldquo;The Most
 * Representative Composite Rank Ordering of Multi-Attribute Objects by the
 * Particle Swarm Optimization Method,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Journal of Quantitative
 * Economics</span> 8(2):165&ndash;200, July&nbsp;2010; published by New
 * Delhi, India: Indian Econometric Society (TIES). ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/09711554">0971-1554</a>. <div>link:
 * [<a href
 * ="http://www.jqe.co.in/journals/JQE_v8_n2_2010_p11.pdf">1</a>]</
 * div></div></li>
 * </ol>
 */
public final class GlobalRankingSummary extends RankedComparisonModule {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the description of ranking */
  private final DescRanking m_rank;

  /**
   * create!
   *
   * @param owner
   *          the macro's owner
   */
  public GlobalRankingSummary(final Module owner) {
    super("GlobalRanking", owner, true);//$NON-NLS-1$

    this.m_rank = this.getRoot().findInstance(DescRanking.class);
    this.addDependency(this.m_rank);
  }

  /** {@inheritDoc} */
  @Override
  protected final void writeSectionTitle(final SectionTitle title,
      final ExperimentSet data) throws IOException {
    title.write("Global Aggregated Ranking"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected final void writeSectionBody(final SectionBody body,
      final ExperimentSet data) throws IOException {
    final GlobalRanking gr;
    final Logger logger;
    final Map.Entry<List<Label>, Ranking<Experiment>> ranks;
    final List<Label> labels;
    String s;

    gr = GlobalRankingProperty.INSTANCE.get(data, body.getDocument());

    logger = this.getLogger();
    if ((gr == null) || //
        ((labels = (ranks = gr.rank(EStatisticParameter.MEDIAN)).getKey())
            .isEmpty())) {
      s = Evaluator.WARNING_NO_DATA;
      logger.warning(s);
      body.write(s);
      return;
    }

    body.write(//
        "In each of the comparison sections (");//$NON-NLS-1$
    body.reference(labels.toArray(new Label[labels.size()]));
    body.write(//
        "), we ranked the experiments according to their performance, as discussed in ");//$NON-NLS-1$
    body.reference(this.m_rank.getLabel(data));

    body.write(//
        ". We now compute the median of the ranks for each experiment and then rank the experiments according to their median ranks. However, even when using robust statistics, the ");//$NON-NLS-1$
    try (Emphasize em = body.emphasize()) {
      em.write(//
          "outcome of this ranking strongly depends on the configuration of the evaluator");//$NON-NLS-1$
    }
    body.write(//
        ". The reason is that ");//$NON-NLS-1$

    try (Enumeration en = body.enumeration()) {
      try (EnumerationItem ei = en.item()) {
        ei.write(//
            " only the evaluation modules that are active and used will influence the ranking and");//$NON-NLS-1$
      }
      try (EnumerationItem ei = en.item()) {
        ei.write(//
            " some modules represent metrics that strongly correllate with each other.");//$NON-NLS-1$
      }
    }
    body.write(//
        "Even if all modules are used at once, we can still expect that the results are biased \u2012 they only represent a hint for a human researcher.");//$NON-NLS-1$

    body.writeLinebreak();

    body.write(//
        "Anyway, from the global ranking information");//$NON-NLS-1$

    this.summarizeRanks(ranks.getValue(), body, //
        "outperform the other methods in different disciplines");//$NON-NLS-1$

  }

  /** {@inheritDoc} */
  @Override
  public final int compareTo(final Module other) {
    if (other instanceof ComparisonModule) {
      return 1;
    }
    return super.compareTo(other);
  }

}
