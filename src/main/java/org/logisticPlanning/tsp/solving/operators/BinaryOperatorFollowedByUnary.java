package org.logisticPlanning.tsp.solving.operators;

import java.io.PrintStream;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;

/**
 * <p>
 * This class performs a binary operation and, subsequently, an unary one.
 * In other words, you can, for instance specify that a
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationSavingsCrossover
 * PermutationSavingsCrossover} should be performed to obtain an offspring
 * solution from two parent solutions and that this offspring solution
 * should be the input for a
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationRNSMutation
 * PermutationRNSMutation} mutation operator. The result of that operator
 * is then the output of this
 * {@link org.logisticPlanning.tsp.solving.operators.BinaryOperatorFollowedByUnary
 * BinaryOperatorFollowedByUnary}.
 * </p>
 * <p>
 * Starting at version 0.9.8 of TSP Suite, we provide run-depending
 * initialization and finalization support for algorithms and algorithm
 * modules. The new class
 * {@link org.logisticPlanning.tsp.solving.TSPModule} is introduced as
 * base-class for all algorithm modules (such as
 * {@link org.logisticPlanning.tsp.solving.operators.Operator search
 * operators} or {@link org.logisticPlanning.tsp.solving.gpm.GPM
 * genotype-phenotype mappings}) and the
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithm TSP algorithms}
 * themselves. This new class provides two methods,
 * {@link org.logisticPlanning.tsp.solving.TSPModule#beginRun(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction)}
 * and
 * {@link org.logisticPlanning.tsp.solving.TSPModule#endRun(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction)}
 * which must be called before and after a run, respectively. Each
 * algorithm module must then invoke them recursively for all of its
 * sub-components. The new method
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithm#call(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction)}
 * now acts as a wrapper for
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithm#solve(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction)}
 * and invokes them. This allows for a more targeted allocation and
 * de-allocation of data structures for each run.
 * </p>
 * 
 * @param <P>
 *          the product type
 */
public final class BinaryOperatorFollowedByUnary<P> extends
    BinaryOperator<P> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the combined binary operator's binary operator: {@value} */
  public static final String PARAM_COMBINED_BINARY = "combinedBinary"; //$NON-NLS-1$

  /** the combined binary operator's unary operator: {@value} */
  public static final String PARAM_COMBINED_UNARY = "combinedUnary"; //$NON-NLS-1$

  /** the binary operator @serial serial field */
  private BinaryOperator<P> m_binary;

  /** the unary operator @serial serial field */
  private UnaryOperator<P> m_unary;

  /** the temporary individual */
  private transient Individual<P> m_temp;

  /**
   * Create
   * 
   * @param binary
   *          the binary operator
   * @param unary
   *          the unary operator
   */
  public BinaryOperatorFollowedByUnary(final BinaryOperator<P> binary,
      final UnaryOperator<P> unary) {
    super(binary.name() + "_followed_by_" + unary.name()); //$NON-NLS-1$
    this.m_binary = binary;
    this.m_unary = unary;
  }

  /** {@inheritDoc} */
  @Override
  public final void recombine(final Individual<P> dest,
      final ObjectiveFunction f, final Individual<P> parent1,
      final Individual<P> parent2) {
    Individual<P> temp;

    temp = this.m_temp;
    if (temp == null) {
      this.m_temp = temp = new Individual<>();
    } else {
      temp.clearEvaluation();
    }
    this.m_binary.recombine(temp, f, parent1, parent2);
    this.m_unary.mutate(dest, f, temp);
  }

  /** {@inheritDoc} */
  @Override
  public final BinaryOperatorFollowedByUnary<P> clone() {
    final BinaryOperatorFollowedByUnary<P> res;

    res = ((BinaryOperatorFollowedByUnary<P>) (super.clone()));
    res.m_temp = null;
    res.m_unary = ((UnaryOperator<P>) (res.m_unary.clone()));
    res.m_binary = ((BinaryOperator<P>) (res.m_binary.clone()));

    return res;
  }

  /** {@inheritDoc} */
  @Override
  public final void configure(final Configuration config) {
    super.configure(config);

    this.m_binary = config.getInstance(
        BinaryOperatorFollowedByUnary.PARAM_COMBINED_BINARY,
        BinaryOperator.class, null, this.m_binary);

    this.m_unary = config.getInstance(
        BinaryOperatorFollowedByUnary.PARAM_COMBINED_UNARY,
        UnaryOperator.class, null, this.m_unary);
  }

  /** {@inheritDoc} */
  @Override
  public final void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable.printKey(
        BinaryOperatorFollowedByUnary.PARAM_COMBINED_BINARY, ps);
    Configurable.printlnObject(this.m_binary, ps);

    Configurable.printKey(
        BinaryOperatorFollowedByUnary.PARAM_COMBINED_UNARY, ps);
    Configurable.printlnObject(this.m_unary, ps);
  }

  /** {@inheritDoc} */
  @Override
  public final void printParameters(final PrintStream ps) {
    super.printParameters(ps);

    Configurable.printKey(
        BinaryOperatorFollowedByUnary.PARAM_COMBINED_BINARY, ps);
    ps.println("the binary search operation"); //$NON-NLS-1$
    this.m_binary.printParameters(ps);

    Configurable.printKey(
        BinaryOperatorFollowedByUnary.PARAM_COMBINED_UNARY, ps);
    ps.println("the unary search operation"); //$NON-NLS-1$
    this.m_unary.printParameters(ps);
  }

  /**
   * Get the binary operator applied by this compound operator.
   * 
   * @return the binary operator applied by this compound operator.
   */
  public final BinaryOperator<P> getBinaryOperator() {
    return this.m_binary;
  }

  /**
   * Get the unary operator applied by this compound operator.
   * 
   * @return the unary operator applied by this compound operator.
   */
  public final UnaryOperator<P> getUnaryOperator() {
    return this.m_unary;
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    super.beginRun(f);
    this.m_binary.beginRun(f);
    this.m_unary.beginRun(f);
  }

  /** {@inheritDoc} */
  @Override
  public void endRun(final ObjectiveFunction f) {
    try {
      this.m_unary.endRun(f);
    } finally {
      try {
        this.m_binary.endRun(f);
      } finally {
        super.endRun(f);
      }
    }
  }
}
