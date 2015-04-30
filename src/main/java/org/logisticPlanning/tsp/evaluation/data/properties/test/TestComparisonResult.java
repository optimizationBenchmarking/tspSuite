package org.logisticPlanning.tsp.evaluation.data.properties.test;

import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.data.properties.ranking.Ranking;
import org.logisticPlanning.utils.collections.lists.ArrayListView;
import org.logisticPlanning.utils.math.statistics.tests.spec.MultivariateTestResult;

/**
 * This class aggregates the results of the comparison of several different
 * algorithms.</p>
 */
public class TestComparisonResult {

  /** the wins */
  private final int[][] m_wins;

  /** the evens */
  private final int[][] m_evens;

  /** the losses */
  private final int[][] m_losses;

  /** the score list */
  private final ArrayListView<TestScore> m_scores;

  /** the best scores */
  private Ranking<Experiment> m_ranks;

  /**
   * Create the comparison results record
   * 
   * @param es
   *          the experiment set
   */
  public TestComparisonResult(final ExperimentSet es) {
    super();

    final int size;
    final TestScore[] scores;
    int i;

    size = es.size();
    this.m_wins = new int[size][size];
    this.m_losses = new int[size][size];
    this.m_evens = new int[size][size];

    scores = new TestScore[size];
    for (i = size; (--i) >= 0;) {
      scores[i] = new TestScore(es.get(i));
    }
    this.m_scores = ArrayListView.makeArrayListView(scores);
    this.m_ranks = null;
  }

  /**
   * add a comparison result
   * 
   * @param cr
   *          the result to add
   */
  public final void add(final TestComparisonResult cr) {
    int i, j;

    for (i = this.m_scores.size(); (--i) >= 0;) {
      this.m_scores.get(i).add(cr.m_scores.get(i));

      for (j = this.m_scores.size(); (--j) >= 0;) {
        if (i == j) {
          continue;
        }

        this.m_wins[i][j] += cr.m_wins[i][j];
        this.m_evens[i][j] += cr.m_evens[i][j];
        this.m_losses[i][j] += cr.m_losses[i][j];
      }
    }

  }

  /**
   * add a test result
   * 
   * @param tr
   *          the test result
   * @param isSmallerBetter
   *          are smaller results better or worse?
   * @param errorProb
   *          the error probability / significance level
   */
  public final void add(final MultivariateTestResult tr,
      final boolean isSmallerBetter, final double errorProb) {
    final int sizeM1;
    int i, j, r;
    TestScore s;

    sizeM1 = (this.m_scores.size() - 1);

    for (i = sizeM1; i >= 0; i--) {
      s = this.m_scores.get(i);
      for (j = sizeM1; j >= 0; j--) {
        if (i == j) {
          continue;
        }

        r = tr.compare(i, j);
        if (r != 0) {
          if (tr.getErrorProbability(i, j) > errorProb) {
            r = 0;
          }
        }

        if (r == 0) {
          s.addEven();
          this.m_evens[i][j]++;
        } else {
          if ((r > 0) ^ isSmallerBetter) {
            s.addWin();
            this.m_wins[i][j]++;
          } else {
            s.addLoss();
            this.m_losses[i][j]++;
          }
        }

      }
    }

  }

  /**
   * Get the number of wins of algorithm {@code i} versus algorithm
   * {@code  j}
   * 
   * @param i
   *          the first algorithm
   * @param j
   *          the second algorithm
   * @return the number of wins
   */
  public final int getWins(final int i, final int j) {
    return ((i == j) ? 0 : this.m_wins[i][j]);
  }

  /**
   * Get the number of losses of algorithm {@code i} versus algorithm
   * {@code  j}
   * 
   * @param i
   *          the first algorithm
   * @param j
   *          the second algorithm
   * @return the number of losses
   */
  public final int getLosses(final int i, final int j) {
    return ((i == j) ? 0 : this.m_losses[i][j]);
  }

  /**
   * Get the number of evens of algorithm {@code i} versus algorithm
   * {@code  j}
   * 
   * @param i
   *          the first algorithm
   * @param j
   *          the second algorithm
   * @return the number of evens
   */
  public final int getEvens(final int i, final int j) {
    return ((i == j) ? 0 : this.m_evens[i][j]);
  }

  /** rank the results */
  private final void __rank() {
    if (this.m_ranks == null) {
      this.m_ranks = Ranking.rank(_TestScoreComparator.INSTANCE, //
          this.m_scores.toArray(new TestScore[this.m_scores.size()]));
    }
  }

  /**
   * Get the scores, sorted in the same order as the experiment
   * 
   * @return the scores, sorted in the same order as the experiment
   */
  public final ArrayListView<TestScore> getScores() {
    this.__rank();
    return this.m_scores;
  }

  /**
   * Get the ranked test scores, ordered by rank
   * 
   * @return the ranked test scores
   */
  public final Ranking<Experiment> getRankedScores() {
    this.__rank();
    return this.m_ranks;
  }
}
