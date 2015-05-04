package org.logisticPlanning.tsp.solving.algorithms.localSearch;

import java.io.PrintStream;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.TSPAlgorithm;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.acceptance.AcceptIfBetterOrEqualToBest;
import org.logisticPlanning.tsp.solving.operators.PerturbationOperator;
import org.logisticPlanning.tsp.solving.operators.permutation.creation.PermutationCreateUniform;
import org.logisticPlanning.tsp.solving.operators.permutation.perturbation.PathShufflePerturbation;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;
import org.logisticPlanning.utils.text.TextUtils;

/**
 * <p>
 * Stochastic Local Search (SLS) algorithms&nbsp;[<a
 * href="#cite_HS2005SLSFAA" style="font-weight:bold">1</a>] are algorithms
 * that try to improve an existing solution iteratively. They are amongst
 * the best known approaches for several problems. In particular, one of
 * the best algorithms for the TSP, the <a
 * href="http://en.wikipedia.org/wiki/Lin%E2%80%93Kernighan_heuristic"
 * >Lin-Kerninghan algorithm</a>&nbsp;[<a href="#cite_LK1973AEHAFTTSP"
 * style="font-weight:bold">2</a>, <a href="#cite_JMG2004EAOHFTS"
 * style="font-weight:bold">3</a>], belongs to that family.
 * </p>
 * <p>
 * This class has been developed as part of a consolidation process taking
 * place at version 0.9.8 of TSP Suite: So far, we have implemented several
 * different local searches, all using the same code for perturbing
 * solutions out of local optima and have the main loops of the same
 * structure. These algorithms have then basically be re-implemented with
 * slight modifications to turn them into
 * {@link org.logisticPlanning.tsp.solving.operators.UnaryOperator
 * unary/mutation} operators for creating hybrid algorithms, again using
 * copies of the same code for perturbation.
 * </p>
 * <p>
 * This is not necessary anymore: The code for perturbation has been
 * consolidated into a
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.perturbation.PathShufflePerturbation
 * base class} of the new class
 * {@link org.logisticPlanning.tsp.solving.operators.PerturbationOperator
 * perturbation operator}. Not only does this reduce the number of
 * identical code copies, it also turns the perturbation algorithm into a
 * parameter that can be changed. This, you now only need to override the
 * method {@link #TSPLocalSearchAlgorithm(String)} of
 * {@link TSPLocalSearchAlgorithm} which performs only the local search,
 * while perturbation and the main loop is dealt with. Additionally, only a
 * single implementation of your algorithm is required, as it can be
 * plugged into a specialized {@link TSPLocalSearchBasedMutation unary
 * search operator} directly. Hybridization with your local search thus
 * comes &quot;for free&quot;.
 * </p>
 * <p>
 * As a result, there now are two ways in which a SLS can be used:
 * </p>
 * <ol>
 * <li>As stand-alone optimization method, it can either begin with a
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.creation.PermutationCreateUniform#PermutationCreateUniform()
 * random solution} or be
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithmRunner#PARAM_DET_INIT_CLASS
 * initialized} with a
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.TSPHeuristic
 * heuristic} and then try to refine the solution. Whenever it runs into a
 * dead end or local optimum it cannot escape from anymore, it may
 * {@link org.logisticPlanning.tsp.solving.operators.PerturbationOperator
 * perturb}, i.e., randomize, its current solution and continue.</li>
 * <li>It may also be plugged into a
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics
 * metaheurstic} as
 * {@link org.logisticPlanning.tsp.solving.operators.UnaryOperator unary}
 * local search/mutation operator. This means to hybridize the
 * metaheuristic, to turn it into a Memetic Algorithm. For this purpose,
 * the class {@link TSPLocalSearchBasedMutation} has been designed which
 * conveniently wraps around a local search and turns it into a mutation
 * operator without requiring additional programming work.</li>
 * </ol>
 * <p>
 * This class is designed as direct base class for search algorithms
 * working on solutions in
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#evaluate(int[])
 * path representation}, but can be extended to any type {@code P}, given
 * that the methods
 * {@link #loadBestIntoIndividual(Individual, ObjectiveFunction)} and
 * {@link #loadPathIntoIndividual(Individual, int[], long)} are implemented
 * for {@code P}.
 * </p>
 *
 * @param <P>
 *          the final solution representation this algorithm works on
 * @since 0.9.8
 */
public abstract class TSPLocalSearchAlgorithm<P> extends TSPAlgorithm {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * the prefix for the parameter defining the perturbation operator to be
   * used
   *
   * @see #m_perturbation
   * @see #m_perturbationParam
   * @see #setPerturbationOperator(PerturbationOperator)
   */
  public static final String PARAM_PERTURBATION_OPERATOR_SUFFIX = "PerturbationOperator"; //$NON-NLS-1$

  /**
   * the prefix for the parameter defining the acceptance operator to be
   * used
   *
   * @see #m_acceptance
   * @see #m_acceptanceParam
   * @see #setAcceptanceCriterion(AcceptanceCriterion)
   */
  public static final String PARAM_ACCEPTANCE_CRITERION_SUFFIX = "AcceptanceCriterion"; //$NON-NLS-1$

  /**
   * the prefix for the parameter defining the termination criterion to be
   * used
   *
   * @see #m_termination
   * @see #m_terminationParam
   * @see #setLocalSearchTerminationCriterion(ELocalSearchTermination)
   */
  public static final String PARAM_LOCAL_SEARCH_TERMINATION_SUFFIX = "Termination"; //$NON-NLS-1$

  /**
   * the prefix for the parameter defining the maximum iterations, 0 for
   * infinite
   *
   * @see #m_maxIterations
   * @see #m_maxIterationsParam
   */
  public static final String PARAM_LOCAL_SEARCH_MAX_ITERATIONS_SUFFIX = "MaxIterations"; //$NON-NLS-1$

  /**
   * the perturbation operator
   *
   * @serial a non-{@code null} object instance
   */
  private PerturbationOperator<P> m_perturbation;

  /**
   * the acceptance criterion
   *
   * @serial a non-{@code null} object instance
   */
  private AcceptanceCriterion m_acceptance;

  /**
   * the local search termination
   *
   * @serial a non-{@code null} object instance
   */
  private ELocalSearchTermination m_termination;

  /**
   * the maximum number of iterations
   *
   * @serial a non-negative integer
   */
  private int m_maxIterations;

  /**
   * the parameter prefix
   *
   * @serial a non-{@code null}, non-empty {@link java.lang.String}
   *         instance
   */
  private final String m_paramPrefix;
  /**
   * the perturbation parameter
   *
   * @see #PARAM_PERTURBATION_OPERATOR_SUFFIX
   * @serial a non-{@code null}, non-empty {@link java.lang.String}
   *         instance
   */
  private final String m_perturbationParam;
  /**
   * the perturbation parameter
   *
   * @see #PARAM_ACCEPTANCE_CRITERION_SUFFIX
   * @serial a non-{@code null}, non-empty {@link java.lang.String}
   *         instance
   */
  private final String m_acceptanceParam;
  /**
   * the perturbation parameter
   *
   * @see #PARAM_LOCAL_SEARCH_TERMINATION_SUFFIX
   * @serial a non-{@code null}, non-empty {@link java.lang.String}
   *         instance
   */
  private final String m_terminationParam;

  /**
   * the maximum iterations parameter
   *
   * @see #PARAM_LOCAL_SEARCH_MAX_ITERATIONS_SUFFIX
   * @serial a non-{@code null}, non-empty {@link java.lang.String}
   *         instance
   */
  private final String m_maxIterationsParam;

  /**
   * instantiate the local search algorithm class
   *
   * @param name
   *          the name
   */
  protected TSPLocalSearchAlgorithm(final String name) {
    super(name);
    String prefix;

    this.m_perturbation = this.createPerturbationOperator();
    this.m_acceptance = AcceptIfBetterOrEqualToBest.INSTANCE;
    this.m_termination = ELocalSearchTermination.NEVER;
    this.m_maxIterations = 0;

    prefix = TextUtils.prepare(this.calculateParamPrefix(name));
    if (prefix == null) {
      prefix = "UnknownLocalSearch"; //$NON-NLS-1$
    }
    this.m_paramPrefix = prefix;

    this.m_perturbationParam = (prefix + TSPLocalSearchAlgorithm.PARAM_PERTURBATION_OPERATOR_SUFFIX);
    this.m_acceptanceParam = (prefix + TSPLocalSearchAlgorithm.PARAM_ACCEPTANCE_CRITERION_SUFFIX);
    this.m_terminationParam = (prefix + TSPLocalSearchAlgorithm.PARAM_LOCAL_SEARCH_TERMINATION_SUFFIX);
    this.m_maxIterationsParam = (prefix + TSPLocalSearchAlgorithm.PARAM_LOCAL_SEARCH_MAX_ITERATIONS_SUFFIX);
  }

  /**
   * Calculate the prefix to be used for the parameters of this local
   * search algorithm. This method is called only once, in the constructor.
   *
   * @param name
   *          the class name
   * @return the prefix to be used for the parameters of this local search
   *         algorithm
   */
  protected String calculateParamPrefix(final String name) {
    String prefix;
    int i;

    prefix = TextUtils.className(this.getClass());
    if (prefix != null) {
      i = prefix.lastIndexOf('.');
      if (i >= 0) {
        prefix = TextUtils.prepare(prefix.substring(i + 1));
      }
    }

    if (prefix == null) {
      return TextUtils.normalize(name);
    }

    return prefix;
  }

  /**
   * A prefix to be used for parameters of this algorithm
   *
   * @return the parameter prefix
   */
  protected final String getParamPrefix() {
    return this.m_paramPrefix;
  }

  /**
   * Create the perturbation operator to be used by this local search
   * method.
   *
   * @return the perturbation operator to be used by this local search
   *         method
   */
  @SuppressWarnings("unchecked")
  protected PerturbationOperator<P> createPerturbationOperator() {
    return ((PerturbationOperator<P>) (new PathShufflePerturbation()));
  }

  /**
   * This method is called to perform the local search. It receives a
   * candidate solution as input.
   *
   * @param f
   *          the objective function
   * @param srcdst
   *          the individual record to work on
   */
  public abstract void localSearch(final Individual<P> srcdst,
      final ObjectiveFunction f);

  /**
   * This method is called to perform the local search. It receives a
   * candidate solution as input.
   *
   * @param f
   *          the objective function
   * @param srcdst
   *          the individual record to work on
   */
  protected void mainLoop(final Individual<P> srcdst,
      final ObjectiveFunction f) {
    final long originalTourLength;
    final boolean checkIterationLimit;
    int iterations;

    originalTourLength = srcdst.tourLength;
    this.accept(srcdst, f);

    // If the solution has been produced by this algorithm, we should
    // perturb
    // it before optimization. Otherwise, we waste one round of
    // optimization
    // which would just lead to the same result.
    if (srcdst.producer == this) {
      this.m_perturbation.pertube(srcdst, f);
    }

    iterations = this.m_maxIterations;
    checkIterationLimit = (iterations > 0);

    // In the main loop, we apply the local search.
    looper: for (;;) {

      if (f.shouldTerminate()) {
        break looper;
      }
      // Apply the local search
      this.localSearch(srcdst, f);

      if (f.shouldTerminate()) {
        break looper;
      }
      if (this.m_termination.shouldTerminate(srcdst.tourLength,
          originalTourLength)) {
        // We should stop, maybe because we are used as mutation
        // operator.
        break looper;
      }

      if (checkIterationLimit && ((--iterations) <= 0)) {
        // We should stop, since we exhausted the maximum number of
        // iterations
        break looper;
      }

      // Will we accept the new solution?
      if (this.m_acceptance.shouldAccept(srcdst, f)) {
        this.accept(srcdst, f); // ok, we accept (probably do nothing)
      } else {
        // reject: overwrite solution with something else
        this.reject(srcdst, f);
      }

      // Perturb: change the solution in order to escape the local optimum
      this.m_perturbation.pertube(srcdst, f);
    }

    // Make sure that we are marked as the producer of this solution
    srcdst.producer = this;
  }

  /**
   * The newly generated solution has been accepted. The individual record
   * {@code srcdst} should not be modified, but may be copied into some
   * internal memory.
   *
   * @param srcdst
   *          the individual with the accepted solution
   * @param f
   *          the objective function
   */
  protected void accept(final Individual<P> srcdst,
      final ObjectiveFunction f) {
    //
  }

  /**
   * The newly generated solution has been rejected. The individual record
   * {@code srcdst} should be overwritten with, e.g., the best known
   * solution.
   *
   * @param srcdst
   *          the individual with the rejected solution, to be overwritten
   * @param f
   *          the objective function
   */
  protected void reject(final Individual<P> srcdst,
      final ObjectiveFunction f) {
    this.loadBestIntoIndividual(srcdst, f);
  }

  /**
   * Load an individual record from a solution in path representation.
   *
   * @param dest
   *          the destination individual record
   * @param path
   *          the solution in path representation
   * @param tourLength
   *          the tour length
   */
  @SuppressWarnings("unchecked")
  protected void loadPathIntoIndividual(final Individual<P> dest,
      final int[] path, final long tourLength) {
    dest.solution = ((P) (path));
    dest.tourLength = tourLength;
    dest.f = Individual.FITNESS_NOT_SET;
    dest.producer = null;
  }

  /**
   * Load an individual record with the best solution discovered by the
   * objective function.
   *
   * @param dest
   *          the destination individual record
   * @param f
   *          the objective function
   */
  @SuppressWarnings("unchecked")
  protected void loadBestIntoIndividual(final Individual<P> dest,
      final ObjectiveFunction f) {
    final int[] d;

    if (dest.solution == null) {
      dest.solution = ((P) (d = new int[f.n()]));
    } else {
      d = ((int[]) (dest.solution));
    }

    f.getCopyOfBest(d);
    dest.tourLength = f.getCurrentLogPoint().getBestF();
    dest.f = Individual.FITNESS_NOT_SET;
    dest.producer = null;
  }

  /** {@inheritDoc} */
  @Override
  public final void solve(final ObjectiveFunction f) {
    final Individual<P> ind;
    int[] path;

    ind = new Individual<>();
    if (f.getCurrentLogPoint().getConsumedFEs() > 0l) {
      this.loadBestIntoIndividual(ind, f);
    } else {
      path = PermutationCreateUniform.create(f.n(), f.getRandom());
      this.loadPathIntoIndividual(ind, path, f.evaluate(path));
      path = null;
    }
    this.mainLoop(ind, f);
  }

  /**
   * Get the perturbation operator
   *
   * @return the perturbation operator
   */
  public final PerturbationOperator<P> getPerturbationOperator() {
    return this.m_perturbation;
  }

  /**
   * Set the perturbation operator
   *
   * @param operator
   *          the perturbation operator
   * @throws IllegalArgumentException
   *           if operator is null
   */
  public final void setPerturbationOperator(
      final PerturbationOperator<P> operator) {
    if (operator == null) {
      throw new IllegalArgumentException();
    }
    this.m_perturbation = operator;
  }

  /**
   * Get the acceptance criterion
   *
   * @return the acceptance criterion
   */
  public final AcceptanceCriterion getAcceptanceCriterion() {
    return this.m_acceptance;
  }

  /**
   * Set the acceptance criterion
   *
   * @param criterion
   *          the acceptance criterion, or {@code null} for the default
   *          {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.acceptance.AcceptIfBetterOrEqualToBest#INSTANCE}
   *          .
   */
  public final void setAcceptanceCriterion(
      final AcceptanceCriterion criterion) {
    this.m_acceptance = ((criterion != null) ? criterion
        : AcceptIfBetterOrEqualToBest.INSTANCE);
  }

  /**
   * Get the additional termination criterion for this local search
   * algorithm. If the local search is used stand-alone, this should be
   * {@link ELocalSearchTermination#NEVER}, but if it is used inside a
   * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.TSPLocalSearchBasedMutation
   * mutation operator}, it can be any of the elements of
   * {@link ELocalSearchTermination}.
   *
   * @return the additional termination criterion
   */
  public final ELocalSearchTermination getLocalSearchTerminationCriterion() {
    return this.m_termination;
  }

  /**
   * Set the additional termination criterion for this local search
   * algorithm. If the local search is used stand-alone, this should be
   * {@link ELocalSearchTermination#NEVER}, but if it is used inside a
   * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.TSPLocalSearchBasedMutation
   * mutation operator}, it can be any of the elements of
   * {@link ELocalSearchTermination}.
   *
   * @param criterion
   *          the additional termination criterion, or {@code null} for
   *          {@link ELocalSearchTermination#NEVER}
   */
  public final void setLocalSearchTerminationCriterion(
      final ELocalSearchTermination criterion) {
    this.m_termination = ((criterion != null) ? criterion
        : ELocalSearchTermination.NEVER);
  }

  /**
   * Get the upper limit for the iteration number.
   *
   * @return the number of iterations the local search is allowed to
   *         perform, either in <code>1..(2<sup>31</sup>-1)</code> or
   *         {@code 0} for infinite
   */
  public final int getLocalSearchMaxIterations() {
    return this.m_maxIterations;
  }

  /**
   * Set the maximum number of iterations the local search is allowed to
   * perform.
   *
   * @param maxIterations
   *          the number of iterations the local search is allowed to
   *          perform, either in <code>1..(2<sup>31</sup>-1)</code> or
   *          {@code 0} for infinite
   */
  public final void setLocalSearchMaxIterations(final int maxIterations) {
    this.m_maxIterations = ((maxIterations > 0) ? maxIterations : 0);
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    super.beginRun(f);
    this.m_acceptance.beginRun(f);
    this.m_perturbation.beginRun(f);
  }

  /** {@inheritDoc} */
  @Override
  public void endRun(final ObjectiveFunction f) {
    try {
      this.m_perturbation.endRun(f);
    } finally {
      try {
        this.m_acceptance.endRun(f);
      } finally {
        super.endRun(f);
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public void configure(final Configuration config) {
    super.configure(config);

    this.setAcceptanceCriterion(config.getInstance(this.m_acceptanceParam,
        AcceptanceCriterion.class, AcceptIfBetterOrEqualToBest.class,
        this.m_acceptance));

    this.setPerturbationOperator(config.getInstance(
        this.m_perturbationParam, PerturbationOperator.class,
        PathShufflePerturbation.class, this.m_perturbation));

    this.setLocalSearchTerminationCriterion(config.getConstant(
        this.m_terminationParam, ELocalSearchTermination.class,
        ELocalSearchTermination.class, this.m_termination));

    this.setLocalSearchMaxIterations(config.getInt(
        this.m_maxIterationsParam, 0, Integer.MAX_VALUE,
        this.m_maxIterations));
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable.printKey(this.m_perturbationParam, ps);
    Configurable.printlnObject(this.m_perturbation, ps);

    Configurable.printKey(this.m_acceptanceParam, ps);
    Configurable.printlnObject(this.m_acceptance, ps);

    Configurable.printKey(this.m_terminationParam, ps);
    Configurable.printlnObject(this.m_termination, ps);

    Configurable.printKey(this.m_maxIterationsParam, ps);
    ps.println(this.m_maxIterations);
  }

  /** {@inheritDoc} */
  @Override
  public void printParameters(final PrintStream ps) {
    super.printParameters(ps);

    Configurable.printKey(this.m_perturbationParam, ps);
    ps.print("the perturbation operator randomizing/kicking the "); //$NON-NLS-1$
    ps.print(this.m_paramPrefix);
    ps.println(" out of local optima");//$NON-NLS-1$

    Configurable.printKey(this.m_acceptanceParam, ps);
    ps.print("the acceptance criterion for new solutions of the "); //$NON-NLS-1$
    ps.println(this.m_paramPrefix);

    Configurable.printKey(this.m_terminationParam, ps);
    ps.print("the additional termination criterion of the "); //$NON-NLS-1$
    ps.println(this.m_paramPrefix);

    Configurable.printKey(this.m_maxIterationsParam, ps);
    ps.print("the number of iterations the "); //$NON-NLS-1$
    ps.print(this.m_paramPrefix);
    ps.print(" can perform, 0 for infinite"); //$NON-NLS-1$
  }
}
