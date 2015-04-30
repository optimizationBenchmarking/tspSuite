package org.logisticPlanning.tsp.solving;

import java.io.IOException;
import java.io.Serializable;

import org.logisticPlanning.utils.NamedObject;

/**
 * An individual is an record that holds a solution (or genotype), a tour
 * length, and a fitness value.
 * 
 * @param <S>
 *          the search space
 */
public class Individual<S> implements Serializable {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * this constants stands for an undefined tour length, see
   * {@link #tourLength}
   */
  public static final long TOUR_LENGTH_NOT_SET = Long.MAX_VALUE;

  /**
   * this constants stands for an undefined fitness, see {@link #f}
   */
  public static final double FITNESS_NOT_SET = Double.NaN;

  /**
   * the solution or genotype: The phenotype will always be an integer
   * array ( {@code int[]}) representing a permutation of nodes of the TSP
   * which defines the order in which the nodes will be visited. If
   * genotype=phenotype, then {@link #solution} will be exactly such a data
   * structure. If genotype!=phenotype, it may be anything, for instance, a
   * formula which describes how to change a solution to potentially make
   * it better, which is the case in our
   * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.developmental.devUpdatingEA.DevUpdatingEA
   * developmental permutation updating evolutionary algorithm}.
   * 
   * @serial an instance of the solution type
   */
  public S solution;

  /**
   * the tour length: The total length of the round trip tour visiting each
   * node of the TSP exactly once and returning to its origin. This value
   * is subject to minimization and finding the tour the smallest possible
   * such value for a TSP is the goal. A tour length is valid if it is
   * {@code >0} and <code>&lt;{@value #TOUR_LENGTH_NOT_SET}</code>.
   * 
   * @serial a positive {@code long} value indicating the tour length
   * @see org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#evaluate(int[])
   * @see org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#evaluateAdj(int[])
   * @see org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer#evaluate(int[])
   * @see org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer#evaluateAdj(int[])
   */
  public long tourLength;

  /**
   * the fitness: The fitness is the value that an optimization algorithm
   * is trying to minimize. This will usually be the same value as stored
   * in {@link #tourLength}, but may also be a value incorporating
   * additional information, such as penalties for already known solutions.
   * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.FitnessAssignmentProcess
   * Fitness assignment processes} in
   * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.EA
   * evolutionary algorithms} are examples for creating such fitness
   * values. A fitness value is valid if it is {@code >=0}.
   * 
   * @serial a {@code double} indicating the individual's fitness
   * @see org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.FitnessAssignmentProcess
   */
  public double f;

  /**
   * the search operation or heuristic that has produced the genotype
   * (solution): Search operators creating a new individual record will set
   * this field to point to them in order to enable us to accumulate
   * statistics about their performance or as a hint preventing them to do
   * the same thing two times (see, e.g., our
   * {@link org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationRNSMutation
   * permutation-based RNS mutation} operator)
   * 
   * @serial the search operator or procedure which has created this
   *         solution
   */
  public NamedObject producer;

  /** create a new solution record */
  public Individual() {
    super();
    this.clear();
  }

  /**
   * clear all evaluation data, i.e., the {@link #tourLength} and the
   * {@link #f fitness f} of this individual record.
   */
  public void clearEvaluation() {
    this.tourLength = Individual.TOUR_LENGTH_NOT_SET;
    this.f = Individual.FITNESS_NOT_SET;
  }

  /**
   * clear this individual record: delete all the information in it and set
   * the tour length and fitness values to infinity
   */
  public void clear() {
    this.clearEvaluation();
    this.solution = null;
    this.producer = null;
  }

  /**
   * assign this individual record to the values of another one
   * 
   * @param copy
   *          the record to copy
   */
  public void assign(final Individual<S> copy) {
    this.solution = copy.solution;
    this.tourLength = copy.tourLength;
    this.f = copy.f;
  }

  /**
   * append this individual's contents to an appendable
   * 
   * @param app
   *          the appendable
   * @return the new appendable
   * @throws IOException
   *           if io fails
   */
  public Appendable append(final Appendable app) throws IOException {
    return app.append("len: "). //$NON-NLS-1$
        append(Long.toString(this.tourLength)).//
        append(", f: "). //$NON-NLS-1$
        append(Double.toString(this.f)).append(", s: "). //$NON-NLS-1$
        append(String.valueOf(this.solution));
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    try {
      return (this.append(new StringBuilder()).toString());
    } catch (final Throwable t) {
      t.printStackTrace();
      return null;
    }
  }
}
