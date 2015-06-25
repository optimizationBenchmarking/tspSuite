package org.logisticPlanning.tsp.evaluation.modules.impl.comparison;

import java.io.IOException;

import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.data.properties.ranking.GlobalRankingProperty;
import org.logisticPlanning.tsp.evaluation.data.properties.ranking.Ranking;
import org.logisticPlanning.tsp.evaluation.modules.impl.ExperimentSequence;
import org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.eval.DescRanking;
import org.logisticPlanning.tsp.evaluation.modules.spec.ComparisonModule;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.tsp.evaluation.modules.spec.RootModule;
import org.logisticPlanning.utils.collections.lists.ArrayListView;
import org.logisticPlanning.utils.document.spec.AbstractTextComplex;
import org.logisticPlanning.utils.document.spec.Document;
import org.logisticPlanning.utils.document.spec.ESequenceType;

/**
 * A comparison module that uses ranks.
 */
public class RankedComparisonModule extends ComparisonModule {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the ranking description */
  protected final DescRanking m_descRanking;

  /**
   * create
   *
   * @param name
   *          the evaluator name
   * @param owner
   *          the owning module
   * @param active
   *          is this module initially active or not?
   */
  protected RankedComparisonModule(final String name, final Module owner,
      final boolean active) {
    super(name, owner, active);
    this.m_descRanking = this.getRoot().findInstance(DescRanking.class);
    this.addDependency(this.m_descRanking);
  }

  /**
   * Write a summary on the given experiment ranking. This method will
   * write out a text beginning with &quot;
   * <code>&nbsp;we find that...</code> &quot;. This text should be
   * preceded by something like &quot;
   * <code>From the results plotted in Figures 1 to 10</code>&quot; in
   * order to form a complete sentence.
   *
   * @param ranks
   *          the comparison result
   * @param body
   *          the body
   * @param betterFeature
   *          The a textual description of the behavior of the better
   *          ranked algorithm, or {@code null} if no discussion of the
   *          benefits is necessary. This will be preceded by &quot;
   *          <code>The [best algorithm] tends to </code>&quot;
   * @throws IOException
   *           if i/o fails
   */
  protected final void summarizeRanks(final Ranking<Experiment> ranks,
      final AbstractTextComplex body, final String betterFeature)
      throws IOException {
    final RootModule root;
    ExperimentSequence ser;
    ArrayListView<Experiment> extreme;
    int extremeCount, total, other;

    root = this.getRoot();
    total = ranks.size();

    body.write(" we find that "); //$NON-NLS-1$
    extreme = ranks.getBest();
    extremeCount = extreme.size();

    if (extremeCount >= total) {
      body.write(" the results of this comparison are largely inconclusive, since the algorithms do not perform significantly different."); //$NON-NLS-1$
    } else {

      ser = new ExperimentSequence(root, extreme, true, false, body);
      body.writeSequence(ser, ESequenceType.AND, false);
      if (extremeCount > 1) {
        body.write(" perform about the as same and have the edge over the other "); //$NON-NLS-1$
      } else {
        body.write(" has the edge over the other "); //$NON-NLS-1$
      }

      body.writeIntInText((other = (total - extremeCount)), false);
      body.write(" method"); //$NON-NLS-1$
      if (other > 1) {
        body.writeChar('s');
      }
      if (betterFeature != null) {
        body.write(", i.e., ");//$NON-NLS-1$
        body.writeSequence(ser, ESequenceType.AND, false);
        body.write((extremeCount <= 1) ? " tends to " : //$NON-NLS-1$
            " tend to ");//$NON-NLS-1$
        body.write(betterFeature);
      }
      body.writeChar('.');
      body.writeChar(' ');

      extreme = ranks.getWorst();
      extremeCount = extreme.size();
      body.writeSequence(new ExperimentSequence(root, extreme, true,
          false, body), ESequenceType.AND, false);
      body.write((extremeCount > 1) ? //
      ", on the other hand, perform worst according to this ranking." : //$NON-NLS-1$
          ", on the other hand, performs worst according to this ranking."); //$NON-NLS-1$
    }

    body.write(" The complete ranking is ");//$NON-NLS-1$
    body.writeSequence(new RankSequence(root, ranks, true, body),//
        ESequenceType.AND, false);
    body.writeChar('.');
  }

  /**
   * Propagate ranking to the overall ranking statistic.
   *
   * @param ranking
   *          the ranking to propagate
   * @param data
   *          the experiment set
   * @param doc
   *          the document
   */
  protected final void propagateRanking(final Ranking<Experiment> ranking,
      final ExperimentSet data, final Document doc) {
    GlobalRankingProperty.INSTANCE.get(data, doc).aggregateRanks(
        this.getLabel(data), ranking);
  }
}
