package org.logisticPlanning.tsp.solving.operators.permutation.perturbation;

import java.io.PrintStream;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.operators.PerturbationOperator;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * A permutation operator which shuffles a path. This is basically the most
 * primitive thing that could be done: Picking a random sub-sequence of
 * solution in path representation and shuffle it. Obviously, this has
 * complexity <em>O(n)</em>.
 * 
 * @since 0.9.8
 */
public class PathShufflePerturbation extends PerturbationOperator<int[]> {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * the default minimum number of nodes to shuffle: {@value}
   * 
   * @see #m_minShuffle
   * @see #PARAM_MIN_SHUFFLE
   */
  public static final int DEFAULT_MIN_SHUFFLE = 4;

  /**
   * the parameter for the minimum number of nodes to shuffle: {@value}
   * 
   * @see #DEFAULT_MIN_SHUFFLE
   * @see #m_minShuffle
   */
  public static final String PARAM_MIN_SHUFFLE = "PertubationMinShuffle"; //$NON-NLS-1$

  /**
   * the default minimum number of nodes that should remain un-shuffled: *
   * * * * {@value}
   * 
   * @see #m_minLeaveAsIs
   * @see #PARAM_MIN_LEAVE_AS_IS_
   */
  public static final int DEFAULT_MIN_LEAVE_AS_IS = 2;

  /**
   * the parameter for the minimum number of nodes to leave as they are: *
   * * * * {@value}
   * 
   * @see #DEFAULT_MIN_LEAVE_AS_IS
   * @see #m_minLeaveAsIs
   */
  public static final String PARAM_MIN_LEAVE_AS_IS_ = "PertubationMinLeaveAsIs"; //$NON-NLS-1$

  /**
   * the minimum number of nodes to shuffle
   * 
   * @serial a positive integer
   * @see #DEFAULT_MIN_SHUFFLE
   * @see #PARAM_MIN_SHUFFLE
   */
  private int m_minShuffle;

  /**
   * the minimum number of nodes to leave as they are
   * 
   * @serial a positive integer
   * @see #DEFAULT_MIN_LEAVE_AS_IS
   * @see #PARAM_MIN_LEAVE_AS_IS_
   */
  private int m_minLeaveAsIs;

  /** create */
  public PathShufflePerturbation() {
    super("PathShufflePerturbation"); //$NON-NLS-1$
    this.m_minShuffle = PathShufflePerturbation.DEFAULT_MIN_SHUFFLE;
    this.m_minLeaveAsIs = PathShufflePerturbation.DEFAULT_MIN_LEAVE_AS_IS;
  }

  /** {@inheritDoc} */
  @Override
  public void pertube(final Individual<int[]> srcdst,
      final ObjectiveFunction f) {
    final int shuffleLength, minShuffle, minLeaveAsIs;
    final int[] solution;
    final Randomizer r;

    r = f.getRandom();
    solution = srcdst.solution;

    minLeaveAsIs = Math.max(2, (solution.length - this.m_minShuffle));
    minShuffle = Math.min(this.m_minShuffle,
        (solution.length - minLeaveAsIs));
    if (minShuffle <= 0) {
      return;
    }

    // shuffleLength is a random value in minShuffle...(n-minLeaveAsIs)
    shuffleLength = (r.nextInt(//
        (solution.length + 1) - minShuffle - minLeaveAsIs)//
    + minShuffle);

    // shuffle a sub-sequence of this length
    r.shuffle(solution, r.nextInt((solution.length - shuffleLength) + 1),
        shuffleLength);

    // and evaluate the shuffled solution
    srcdst.tourLength = f.evaluate(solution);
    srcdst.f = Individual.FITNESS_NOT_SET;
    srcdst.producer = this;
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable.printKey(PathShufflePerturbation.PARAM_MIN_SHUFFLE, ps);
    ps.println(this.m_minShuffle);

    Configurable.printKey(PathShufflePerturbation.PARAM_MIN_LEAVE_AS_IS_,
        ps);
    ps.println(this.m_minLeaveAsIs);
  }

  /** {@inheritDoc} */
  @Override
  public void printParameters(final PrintStream ps) {
    super.printParameters(ps);

    Configurable.printKey(PathShufflePerturbation.PARAM_MIN_SHUFFLE, ps);
    ps.println(//
    "the number of nodes to be shuffled at least during a pertubation"); //$NON-NLS-1$

    Configurable.printKey(PathShufflePerturbation.PARAM_MIN_LEAVE_AS_IS_,
        ps);
    ps.println(//
    "the minimum number of nodes to be left at their positions during pertubation"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public void configure(final Configuration config) {
    super.configure(config);

    this.m_minShuffle = config.getInt(
        PathShufflePerturbation.PARAM_MIN_SHUFFLE, 2, Integer.MAX_VALUE,
        this.m_minShuffle);
    this.m_minLeaveAsIs = config.getInt(
        PathShufflePerturbation.PARAM_MIN_LEAVE_AS_IS_, 2,
        Integer.MAX_VALUE, this.m_minLeaveAsIs);
  }

}
