package org.logisticPlanning.tsp.evaluation.data.properties.test;

import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.data.properties.ranking.Rank;

/**
 * the test score of an experiment.
 */
public class TestScore extends Rank<Experiment> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the wins */
  int m_wins;

  /** the evens */
  int m_evens;

  /** the losses */
  int m_losses;

  /**
   * The experiment test score
   *
   * @param exp
   *          the experiment
   */
  public TestScore(final Experiment exp) {
    super(exp);
  }

  /** add a win */
  public final void addWin() {
    this.checkModify();
    this.m_wins++;
  }

  /** add a loss */
  public final void addLoss() {
    this.checkModify();
    this.m_losses++;
  }

  /** add a even */
  public final void addEven() {
    this.checkModify();
    this.m_evens++;
  }

  /**
   * add a test score
   *
   * @param score
   *          the test score to add
   */
  public final void add(final TestScore score) {
    this.checkModify();
    this.m_wins += score.m_wins;
    this.m_evens += score.m_evens;
    this.m_losses += score.m_losses;
  }

  /**
   * Get the wins
   *
   * @return the wins
   */
  public final int getWins() {
    return this.m_wins;
  }

  /**
   * Get the losses
   *
   * @return the losses
   */
  public final int getLosses() {
    return this.m_losses;
  }

  /**
   * Get the evens
   *
   * @return the evens
   */
  public final int getEvens() {
    return this.m_evens;
  }

}
