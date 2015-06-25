package org.logisticPlanning.tsp.solving.operators;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;

/**
 * <p>
 * The base class for all nullary operators that are used to solve TSPs. A
 * nullary search operator has no parameter and produces one result.
 * Nullary search operators are usually used to create the first or initial
 * starting points of search algorithms. They should create points which
 * are either uniformly distributed in the search space (regardless how the
 * search space is composed) or according to some specific heuristic.
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
public class NullaryOperator<P> extends Operator<P> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** a dummy operator */
  public static final NullaryOperator<Object> DUMMY = new _DummyNullaryOperator();

  /**
   * create
   *
   * @param name
   *          the name of this object
   */
  protected NullaryOperator(final String name) {
    super(name, 0);
  }

  /**
   * <p>
   * Create a new candidate solution and store it in a destination
   * individual record {@code dest}. It is expected that the destination
   * record is either
   * {@link org.logisticPlanning.tsp.solving.Individual#Individual() new}
   * has its evaluation results
   * {@link org.logisticPlanning.tsp.solving.Individual#clearEvaluation()}
   * and that its contents can immediately be overridden.
   * </p>
   * <p>
   * The cleared destination record has its
   * {@link org.logisticPlanning.tsp.solving.Individual#tourLength
   * objective value} (the tour length) set to
   * {@link java.lang.Long#MAX_VALUE}. If this method maybe performs a
   * slight change in existing permutations that allow to compute the new
   * tour length more efficiently than what the
   * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#evaluate(int[])
   * objective function} does, it may set the
   * {@link org.logisticPlanning.tsp.solving.Individual#tourLength tour
   * length} directly and safe that computational effort.
   * </p>
   *
   * @param dest
   *          the destination individual
   * @param f
   *          the objective function
   */
  public void create(final Individual<P> dest, final ObjectiveFunction f) {
    dest.clear();
  }

  /** {@inheritDoc} */
  @Override
  @SuppressWarnings("unchecked")
  public final void apply(final Individual<P> dest,
      final ObjectiveFunction f, final Individual<P>... parents) {
    this.create(dest, f);
  }

}
