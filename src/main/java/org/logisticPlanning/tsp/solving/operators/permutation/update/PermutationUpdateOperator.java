package org.logisticPlanning.tsp.solving.operators.permutation.update;

import java.util.ArrayList;

import org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.operators.UnaryOperator;
import org.logisticPlanning.tsp.solving.utils.PairOfNode;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * An update operation can compute the change in objective value of a
 * candidate solution by only few invocations of the distance function and
 * then be applied requiring only few modifications to a candidate
 * solution.
 */
public class PermutationUpdateOperator extends UnaryOperator<int[]> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * a constant returned by
   * {@link #delta(int[], DistanceComputer, int, int)} if the application (
   * {@link #apply(Individual, ObjectiveFunction, Individual...)}) of an
   * update would have no effect.
   */
  public static final int NO_EFFECT = Integer.MAX_VALUE;

  /**
   * create
   *
   * @param name
   *          the operation name
   */
  protected PermutationUpdateOperator(final String name) {
    super(name);
  }

  /**
   * Compute the distance after applying the operation. If this method
   * returns the constant value {@link #NO_EFFECT}, this means that
   * applying the operator via
   * {@link #apply(Individual, ObjectiveFunction, Individual...)} would
   * have no effect, i.e., leave the solution unchanged. Please be very
   * careful to never add this result to a tour length directly without
   * checking it for equality to {@link #NO_EFFECT}.
   *
   * @param perm
   *          the permutation that may be updated
   * @param f
   *          the objective function
   * @param a
   *          the first index parameter
   * @param b
   *          the second index parameter
   * @return the change in distance
   */
  public int delta(final int[] perm, final DistanceComputer f, final int a,
      final int b) {
    return PermutationUpdateOperator.NO_EFFECT;
  }

  /**
   * Apply the operation to a permutation in-place
   *
   * @param perm
   *          the permutation to be changed
   * @param a
   *          the first index parameter
   * @param b
   *          the second index parameter
   */
  public void update(final int[] perm, final int a, final int b) {
    //
  }

  /**
   * Return the edges added by this operator, need to be implemented by the
   * subclasses
   *
   * @param perm
   *          the permutation to be changed
   * @param a
   *          the first index parameter
   * @param b
   *          the second index parameter
   * @return the incoming edges
   */
  public ArrayList<PairOfNode> inComingEdges(final int[] perm, final int a,
      final int b) {
    return null;
  }

  /**
   * Return the edges deleted by this operator, need to be implemented by
   * the subclasses
   *
   * @param perm
   *          the permutation to be changed
   * @param a
   *          the first index parameter
   * @param b
   *          the second index parameter
   * @return the outcoming edges
   */
  public ArrayList<PairOfNode> outComingEdges(final int[] perm,
      final int a, final int b) {
    return null;
  }

  /**
   * Revert the operation from a permutation in-place
   *
   * @param perm
   *          the permutation to be changed
   * @param a
   *          the first index parameter
   * @param b
   *          the second index parameter
   */
  public void revertUpdate(final int[] perm, final int a, final int b) {
    this.update(perm, a, b);
  }

  /** {@inheritDoc} */
  @Override
  public final void mutate(final Individual<int[]> dest,
      final ObjectiveFunction f, final Individual<int[]> parent) {
    int a, b, delta;
    long l;
    final int[] g;
    final Randomizer r;
    final boolean z;

    r = f.getRandom();

    g = parent.solution.clone();
    dest.solution = g;
    l = parent.tourLength;
    z = ((l > 0l) && (l < Long.MAX_VALUE));

    outer: for (;;) {
      a = r.nextInt(g.length);
      do {
        b = r.nextInt(g.length);
      } while (a == b);

      if (z) {
        delta = this.delta(g, f, a, b);
        if (delta == PermutationUpdateOperator.NO_EFFECT) {
          continue outer;
        }
        l += delta;
        dest.tourLength = l;
      }

      this.update(g, a, b);

      if (z) {
        f.registerFEs(1, g, l);
      }
      dest.producer = this;
      return;
    }

  }

  /** {@inheritDoc} */
  @Override
  public final PermutationUpdateOperator clone() {
    return this;
  }
}
