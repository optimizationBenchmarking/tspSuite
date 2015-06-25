package org.logisticPlanning.tsp.evaluation.modules.impl;

import java.io.IOException;
import java.util.List;

import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.modules.spec.RootModule;
import org.logisticPlanning.utils.document.spec.AbstractTextPlain;
import org.logisticPlanning.utils.document.spec.Sequence;

/**
 * A sequence for listing experiments.
 */
public final class ExperimentSequence extends Sequence {

  /** the experiment set */
  private final List<Experiment> m_exps;

  /** should we print the short names? */
  private final boolean m_short;
  /** should we print the long names? */
  private final boolean m_long;

  /** the output */
  private final AbstractTextPlain m_out;

  /** the root module */
  private final RootModule m_root;

  /**
   * Create the experiment sequence
   *
   * @param root
   *          the root module
   * @param exps
   *          the experiments
   * @param shrt
   *          should we print the short names?
   * @param lng
   *          should we print the long names?
   * @param out
   *          the output
   */
  public ExperimentSequence(final RootModule root,
      final List<Experiment> exps, final boolean shrt, final boolean lng,
      final AbstractTextPlain out) {
    super();
    this.m_root = root;
    this.m_exps = exps;
    this.m_short = shrt;
    this.m_long = lng;
    this.m_out = out;
  }

  /** {@inheritDoc} */
  @Override
  public final int length() {
    return this.m_exps.size();
  }

  /** {@inheritDoc} */
  @Override
  public void write(final int index) throws IOException {
    final Experiment exp;

    exp = this.m_exps.get(index);
    if (this.m_long) {
      this.m_out.macroInvoke(this.m_root.getLongName(exp));
    }
    if (this.m_short) {
      if (this.m_long) {
        this.m_out.writeChar(' ');
        this.m_out.writeChar('(');
      }
      this.m_out.macroInvoke(this.m_root.getShortName(exp));
      if (this.m_long) {
        this.m_out.writeChar(')');
      }
    }
  }
}
