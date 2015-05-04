package org.logisticPlanning.tsp.solving.algorithms.localSearch;

import java.io.PrintStream;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.acceptance.AlwaysAccept;
import org.logisticPlanning.tsp.solving.operators.UnaryOperator;
import org.logisticPlanning.utils.config.Configuration;

/**
 * A mutation operator wrapping a {@link TSPLocalSearchAlgorithm local
 * search} routine. This mutator is designed for solutions in
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#evaluate(int[])
 * path representation}, but can be modified to deal with any data type
 * {@code P} , given the method {@link #copySolution(Object,Object)} is
 * implemented for {@code P}.
 *
 * @param <P>
 *          the product type
 * @since 0.9.8
 */
public class TSPLocalSearchBasedMutation<P> extends UnaryOperator<P> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * the local search algorithm used for mutation
   *
   * @serial a non-{@code null} instance of the algorithm
   */
  private TSPLocalSearchAlgorithm<P> m_algorithm;

  /**
   * Create the local search-based mutation operator for a given algorithm
   * passed in. This constructor will automatically
   * {@link TSPLocalSearchAlgorithm#setAcceptanceCriterion(AcceptanceCriterion)
   * set} the {@link TSPLocalSearchAlgorithm#getAcceptanceCriterion()
   * acceptance criterion} of the algorithm to
   * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.acceptance.AlwaysAccept#INSTANCE}
   * and also
   * {@link TSPLocalSearchAlgorithm#setLocalSearchTerminationCriterion(ELocalSearchTermination)
   * set} the
   * {@link TSPLocalSearchAlgorithm#getLocalSearchTerminationCriterion()
   * termination criterion} to
   * {@link ELocalSearchTermination#TERMINATE_IF_DIFFERENT}.
   *
   * @param algorithm
   *          the local search algorithm to delegate to
   */
  protected TSPLocalSearchBasedMutation(
      final TSPLocalSearchAlgorithm<P> algorithm) {
    super(algorithm.getParamPrefix() + "Mutator"); //$NON-NLS-1$
    this.m_algorithm = algorithm;
    this.m_algorithm.setLocalSearchTerminationCriterion(//
        ELocalSearchTermination.TERMINATE_IF_DIFFERENT);
    this.m_algorithm.setAcceptanceCriterion(AlwaysAccept.INSTANCE);
    this.m_algorithm.setLocalSearchMaxIterations(1000);
  }

  /** {@inheritDoc} */
  @Override
  @SuppressWarnings("unchecked")
  public TSPLocalSearchBasedMutation<P> clone() {
    final TSPLocalSearchBasedMutation<P> res;

    res = ((TSPLocalSearchBasedMutation<P>) (super.clone()));
    res.m_algorithm = ((TSPLocalSearchAlgorithm<P>) (res.m_algorithm
        .clone()));

    return res;
  }

  /**
   * copy a solution
   *
   * @param solution
   *          the solution
   * @param dest
   *          the destination, or {@code null} if none
   * @return the cloned copy
   */
  @SuppressWarnings("unchecked")
  protected P copySolution(final P solution, final P dest) {
    if (dest != null) {
      System.arraycopy(solution, 0, dest, 0, ((int[]) solution).length);
      return dest;
    }
    return ((P) ((int[]) solution).clone());
  }

  /**
   * Obtain the local search algorithm
   *
   * @return the local search algorithm
   */
  protected final TSPLocalSearchAlgorithm<P> getLocalSearchAlgorithm() {
    return this.m_algorithm;
  }

  /** {@inheritDoc} */
  @Override
  public final void mutate(final Individual<P> dest,
      final ObjectiveFunction f, final Individual<P> parent) {
    dest.solution = this.copySolution(parent.solution, dest.solution);
    dest.tourLength = parent.tourLength;
    dest.producer = parent.producer;
    dest.f = Individual.FITNESS_NOT_SET;
    this.m_algorithm.mainLoop(dest, f);
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    super.beginRun(f);
    this.m_algorithm.beginRun(f);
  }

  /** {@inheritDoc} */
  @Override
  public void endRun(final ObjectiveFunction f) {
    try {
      this.m_algorithm.endRun(f);
    } finally {
      super.endRun(f);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void configure(final Configuration config) {
    super.configure(config);
    this.m_algorithm.configure(config);
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);
    this.m_algorithm.printConfiguration(ps);
  }

  /** {@inheritDoc} */
  @Override
  public void printParameters(final PrintStream ps) {
    super.printParameters(ps);
    this.m_algorithm.printParameters(ps);
  }
}
