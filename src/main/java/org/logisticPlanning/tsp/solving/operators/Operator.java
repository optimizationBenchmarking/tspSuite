package org.logisticPlanning.tsp.solving.operators;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.TSPModule;

/**
 * <p>
 * The base class for all search operators that are used to solve TSPs: A
 * search operator takes a {@link #arity() number} of points in the search
 * space as input and
 * {@link #apply(Individual, ObjectiveFunction, Individual...) produces}
 * one point in the search space as output.
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
public class Operator<P> extends TSPModule {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the arity */
  private final int m_arity;

  /**
   * create
   *
   * @param name
   *          the name
   * @param arity
   *          the arity of the operator
   */
  protected Operator(final String name, final int arity) {
    super(name);
    this.m_arity = arity;
  }

  /**
   * get the operator's arity, i.e., the number of arguments it accepts
   *
   * @return the operator's arity
   */
  public final int arity() {
    return this.m_arity;
  }

  /**
   * <p>
   * Apply the operator and store the result in a destination individual
   * record {@code dest}. It is expected that the destination record is
   * either
   * {@link org.logisticPlanning.tsp.solving.Individual#Individual() new}
   * or {@link org.logisticPlanning.tsp.solving.Individual#clear() cleared}
   * .
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
   * @param parents
   *          the parents
   */
  @SuppressWarnings("unchecked")
  public void apply(final Individual<P> dest, final ObjectiveFunction f,
      final Individual<P>... parents) {
    final int l;

    if (parents != null) {
      l = parents.length;
      if (l > 0) {
        dest.assign(parents[f.getRandom().nextInt(l)]);
      }
    }
  }

}
