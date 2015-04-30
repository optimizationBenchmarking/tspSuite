package org.logisticPlanning.tsp.solving.operators;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.utils.utils.CloneUtils;

/**
 * The base class for operators that can perturb a given solution.
 * 
 * @param <P>
 *          the product type
 * @since 0.9.8
 */
public abstract class PerturbationOperator<P> extends UnaryOperator<P> {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * Create a new pertubation operator
   * 
   * @param name
   *          the operator's name
   */
  protected PerturbationOperator(final String name) {
    super(name);
  }

  /**
   * This operation takes an existing individual record. It will take the
   * solution stored in
   * <code>{@link org.logisticPlanning.tsp.solving.Individual#solution srcdst.solution}</code>
   * and modify it directly without copying it first. It expects that
   * <code>{@link org.logisticPlanning.tsp.solving.Individual#tourLength srcdst.tourLength}</code>
   * contains the (valid) tour length of the original input
   * <code>{@link org.logisticPlanning.tsp.solving.Individual#solution srcdst.solution}</code>
   * and it will replace this value with the new
   * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#evaluate(int[])
   * tour length}.
   * 
   * @param srcdst
   *          the individual record holding the solution to be perturbed
   * @param f
   *          the objective function
   */
  public abstract void pertube(final Individual<P> srcdst,
      final ObjectiveFunction f);

  /** {@inheritDoc} */
  @Override
  public final void mutate(final Individual<P> dest,
      final ObjectiveFunction f, final Individual<P> parent) {
    dest.assign(parent);
    dest.solution = CloneUtils.deepClone(dest.solution);
    this.pertube(dest, f);
  }

}
