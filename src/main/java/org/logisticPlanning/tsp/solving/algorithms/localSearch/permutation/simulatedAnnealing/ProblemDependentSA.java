package org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.simulatedAnnealing;

import java.io.PrintStream;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.TSPLocalSearchAlgorithm;
import org.logisticPlanning.tsp.solving.operators.permutation.creation.PermutationCreateCanonical;
import org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdateOperator;
import org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Swap;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * An SA algorithm initializing its temperature based on the problem.
 * 
 * @author Jiahui Liu, jl4161@columbia.edu
 */
public final class ProblemDependentSA extends
    TSPLocalSearchAlgorithm<int[]> {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the parameter for the update operation: {@value} */
  public static final String PARAM_UPDATE_OPERATION = "updateOperation";//$NON-NLS-1$

  /** the parameter for the cooling rate: {@value} */
  public static final String COOLING_RATE = "coolingRate";//$NON-NLS-1$

  /** the parameter for the standard deviation multiplier: {@value} */
  public static final String STD_DEVIATION_MULTIPLIER = "stdDeviaitionMultiplier";//$NON-NLS-1$

  /** the parameter for the critical temperature: {@value} */
  public static final String CRITICAL_TEMPERATURE = "criticalTemp";//$NON-NLS-1$

  /** the parameter for the constant probability: {@value} */
  public static final String CONSTANT_PROBABILITY = "constantProbability";//$NON-NLS-1$

  /** the update */
  private PermutationUpdateOperator m_update;

  /** initial temperature */
  private double m_initialTemp;

  /** set cooling rate */
  private double m_coolingRate;

  /** theoretical value of constant probability */
  private double m_constProbability;

  /**
   * theoretical value of the critical temperature when constant
   * probability should start
   */
  private double m_criticalTemp;

  /** standard deviation multipler for initial temp */
  private int m_stdDevMultiplier;

  /** instantiate */
  public ProblemDependentSA() {
    super("ProblemDependentSA");//$NON-NLS-1$
    this.m_update = PermutationUpdate_Swap.INSTANCE;

    // Default cooling rate
    this.m_coolingRate = 0.99;
    // Default initial temp
    this.m_initialTemp = 10000;

    this.m_constProbability = 0.07;
    this.m_criticalTemp = 2;

  }

  /**
   * Perform the problem dependent simulated annealing algorithm
   *
   * @param args
   *          the command line arguments
   */
  public static void main(final String[] args) {
    TSPAlgorithmRunner
        .benchmark(
            org.logisticPlanning.tsp.benchmarking.instances.Instance.SYMMETRIC_INSTANCES,
            SimulatedAnnealing.class, args);
  }

  /** {@inheritDoc} */
  @Override
  public final void localSearch(final Individual<int[]> srcdst,
      final ObjectiveFunction f) {
    // Set initial temp
    double temp = this.m_initialTemp;

    final int n;
    final Randomizer r;
    final PermutationUpdateOperator op;
    int change;
    int pos1, pos2;

    r = f.getRandom();
    n = f.n();

    op = this.m_update;

    // loop until system has cooled
    while ((temp > 1) && (!(f.shouldTerminate()))) {
      // Get two random positions in the tour
      pos1 = r.nextInt(n);
      do {
        pos2 = r.nextInt(n);
      } while (pos1 == pos2);

      // calculate the change in tour
      change = op.delta(srcdst.solution, f, pos1, pos2);

      // calculate Metropolis probability
      final double metroProbability = this.__acceptMetropolisProb(change,
          temp);

      final double randomCheck = r.nextDouble();

      if ((metroProbability > randomCheck)
          || ((temp < this.m_criticalTemp) && (this.m_constProbability > randomCheck))) {
        // accept change under METROPOLIS criterion or CONSTANT criterion
        // apply change, register FE
        srcdst.tourLength += change;
        op.update(srcdst.solution, pos1, pos2);
        f.registerFE(srcdst.solution, srcdst.tourLength);
      }

      // Cool the system
      temp *= this.m_coolingRate;

    }
  }

  /**
   * Calculate the Metropolis acceptance probability
   *
   * @param change
   *          the delta
   * @param temperature
   *          the current temperature
   * @return the acceptance probability
   */
  private final double __acceptMetropolisProb(final double change,
      final double temperature) {
    // If the new solution is better, accept it
    if (change < 0) {
      return 1.0;
    }
    // If the new solution is worse, calculate an acceptance probability
    return Math.exp(-change / temperature);

  }

  /**
   * calculate initial temperature
   *
   * @param f
   *          the objective function
   * @param N
   *          the number of cities
   * @param stdDevMultiplier
   *          the multiplier
   * @return the initial temperature
   */
  private final static double __getInitialTemp(final ObjectiveFunction f,
      final int N, final int stdDevMultiplier) {
    final int n;
    final Randomizer r;

    int[] sol;
    long tourLength;
    long[] allTour;
    long tourSum = 0;

    // standard deviation of all tours
    double stdDevTour;
    double mean;
    double initialTemperature;

    r = f.getRandom();
    n = f.n();

    sol = new int[n];
    allTour = new long[N];

    // initialize permutation
    PermutationCreateCanonical.makeCanonical(sol, n);

    // calculate the sum of N randomly generated tours
    for (int i = 0; i < N; i++) {
      r.shuffle(sol, 0, n);
      // get the length of each permutation
      tourLength = f.evaluate(sol);
      allTour[i] = tourLength;
      tourSum += tourLength;
    }

    mean = tourSum / (double) N;

    long stdVal, stdSum = 0;

    // calculate standard deviation
    for (int j = 0; j < N; j++) {
      stdVal = (long) Math.pow((allTour[j] - mean), 2);
      stdSum += stdVal;
    }

    stdDevTour = Math.sqrt(stdSum / N);

    // calculate the initial temperature from the standard deviation
    initialTemperature = stdDevTour * stdDevMultiplier;

    return initialTemperature;

  }

  /** {@inheritDoc} */
  @Override
  public final ProblemDependentSA clone() {
    final ProblemDependentSA clo;
    clo = ((ProblemDependentSA) (super.clone()));
    clo.m_update = clo.m_update.clone();

    return clo;

  }

  /** {@inheritDoc} */
  @Override
  public final void beginRun(final ObjectiveFunction f) {
    super.beginRun(f);
    this.m_update.beginRun(f);

    // number of runs to decide initial temp
    final int N = 100;

    this.m_initialTemp = ProblemDependentSA.__getInitialTemp(f, N,
        this.m_stdDevMultiplier);

  }

  /** {@inheritDoc} */
  @Override
  public final void endRun(final ObjectiveFunction f) {
    try {
      this.m_update.endRun(f);
    } finally {
      super.endRun(f);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void configure(final Configuration config) {
    super.configure(config);

    this.m_update = config.getInstance(
        ProblemDependentSA.PARAM_UPDATE_OPERATION,
        PermutationUpdateOperator.class, null, this.m_update);

    this.m_stdDevMultiplier = config.getInt(
        ProblemDependentSA.STD_DEVIATION_MULTIPLIER, 0, 100,
        this.m_stdDevMultiplier);

    this.m_coolingRate = config.getDouble(ProblemDependentSA.COOLING_RATE,
        0, 1, this.m_coolingRate);

    this.m_criticalTemp = config.getDouble(
        ProblemDependentSA.CRITICAL_TEMPERATURE,
        this.m_initialTemp / 10000, this.m_initialTemp,
        this.m_criticalTemp);

    this.m_constProbability = config.getDouble(
        ProblemDependentSA.CONSTANT_PROBABILITY, 0, 0.1,
        this.m_constProbability);

  }

  /** {@inheritDoc} */
  @Override
  public final void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable.printKey(ProblemDependentSA.PARAM_UPDATE_OPERATION, ps);

    ps.println(this.m_update);

    Configurable.printKey(ProblemDependentSA.STD_DEVIATION_MULTIPLIER, ps);
    ps.println(this.m_stdDevMultiplier);

    Configurable.printKey(ProblemDependentSA.COOLING_RATE, ps);
    ps.println(this.m_coolingRate);

    Configurable.printKey(ProblemDependentSA.CRITICAL_TEMPERATURE, ps);
    ps.println(this.m_criticalTemp);

    Configurable.printKey(ProblemDependentSA.CONSTANT_PROBABILITY, ps);
    ps.println(this.m_constProbability);

  }

  /** {@inheritDoc} */
  @Override
  public final void printParameters(final PrintStream ps) {

    super.printParameters(ps);

    Configurable.printKey(ProblemDependentSA.PARAM_UPDATE_OPERATION, ps);
    ps.println("the update operation to use in the annealing."); //$NON-NLS-1$

    Configurable.printKey(ProblemDependentSA.STD_DEVIATION_MULTIPLIER, ps);
    ps.println("The standard deviation nultiplier used to determine "//$NON-NLS-1$
        + "initial temperature in the simulated annealing: ");//$NON-NLS-1$

    Configurable.printKey(ProblemDependentSA.COOLING_RATE, ps);
    ps.println("The cooling rate used in the simulated annealing"//$NON-NLS-1$
        + "(e.g.cooling rate = 0.99, "//$NON-NLS-1$
        + "then every time the temperature will be 0.99 *temperature : ");//$NON-NLS-1$

    Configurable.printKey(ProblemDependentSA.CRITICAL_TEMPERATURE, ps);
    ps.println("The ciritical temperature that divides "//$NON-NLS-1$
        + "between Metropolis acceptance probability and"//$NON-NLS-1$
        + "constant acceptance probability :");//$NON-NLS-1$

    Configurable.printKey(ProblemDependentSA.CONSTANT_PROBABILITY, ps);
    ps.println("The constant acceptance probability :");//$NON-NLS-1$
  }

}
