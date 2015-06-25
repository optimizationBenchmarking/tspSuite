package org.logisticPlanning.tsp.evaluation.modules.impl.comparison;

import java.io.IOException;

import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.data.properties.ranking.Rank;
import org.logisticPlanning.tsp.evaluation.data.properties.ranking.Ranking;
import org.logisticPlanning.tsp.evaluation.modules.spec.RootModule;
import org.logisticPlanning.utils.document.spec.AbstractTextPlain;
import org.logisticPlanning.utils.document.spec.Sequence;

/**
 * A sequence for listing a ranking.
 */
public final class RankSequence extends Sequence {

  /** the ranking */
  private final Ranking<Experiment> m_ranking;

  /**
   * should we print the short names ({@code true}) or long names (
   * {@code false} )?
   */
  private final boolean m_short;

  /** the output */
  private final AbstractTextPlain m_out;

  /** the root module */
  private final RootModule m_root;

  /**
   * Create the rank sequence
   *
   * @param root
   *          the root module
   * @param ranking
   *          the ranking
   * @param shrt
   *          should we print the short names ({@code true}) or long names
   *          ( {@code false})?
   * @param out
   *          the output
   */
  public RankSequence(final RootModule root,
      final Ranking<Experiment> ranking, final boolean shrt,
      final AbstractTextPlain out) {
    super();
    this.m_root = root;
    this.m_ranking = ranking;
    this.m_short = shrt;
    this.m_out = out;
  }

  /** {@inheritDoc} */
  @Override
  public final int length() {
    return this.m_ranking.size();
  }

  /** {@inheritDoc} */
  @Override
  public void write(final int index) throws IOException {
    final Experiment exp;
    final Rank<Experiment> rank;

    rank = this.m_ranking.get(index);
    exp = rank.getKey();
    if (this.m_short) {
      this.m_out.macroInvoke(this.m_root.getShortName(exp));
    } else {
      this.m_out.macroInvoke(this.m_root.getLongName(exp));
    }

    if (index <= 0) {
      this.m_out.write(" (rank"); //$NON-NLS-1$
      this.m_out.writeNoneBreakingSpace();
    } else {
      this.m_out.writeChar(' ');
      this.m_out.writeChar('(');
    }
    this.m_out.writeDouble(rank.getRank());
    this.m_out.writeChar(')');
  }
}
