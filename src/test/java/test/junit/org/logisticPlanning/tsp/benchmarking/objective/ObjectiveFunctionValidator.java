package test.junit.org.logisticPlanning.tsp.benchmarking.objective;

import org.junit.Assert;
import org.junit.Ignore;
import org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer;
import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.Benchmark;
import org.logisticPlanning.tsp.benchmarking.objective.LogPoint;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.utils.NamedObject;

import test.junit.TempDir;
import test.junit.org.logisticPlanning.tsp.solving.utils.TourValidatorTestBase;

/**
 * This is not a test for the
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction
 * ObjectiveFunction} class, but a utility to verify if its results and
 * state match.
 */
@Ignore
public class ObjectiveFunctionValidator extends TourValidatorTestBase {

  /** the unknown state */
  protected static final int STATE_NOTHING = 0;

  /** the function has a finished init process */
  protected static final int STATE_HAS_FINISHED_INIT = 1;

  /** the function has a finished run */
  protected static final int STATE_HAS_FINISHED_RUN = 2;

  /** the tour */
  private transient int[] m_tour;

  /** the last instance */
  private transient Instance m_lastI;

  /** the last distance computer */
  private transient DistanceComputer m_lastDC;

  /** the dummy named object */
  protected final NamedObject m_dummy;

  /** instantiate */
  public ObjectiveFunctionValidator() {
    super();
    this.m_dummy = new NamedObject("testDummy"); //$NON-NLS-1$
  }

  /** the pre-defined FEDEG0 DE messages */
  private static final String[][] FEDEG0_CONSUMED_DE_MESSAGES = new String[][] {
    {
      "ERROR IN TEST STRUCTURE: This should never be reached.", //$NON-NLS-1$
      "Consumed DEs of initialization log point must be greater than zero since an initialization procedure has finished.", //$NON-NLS-1$
      "Consumed DEs of initialization log point must be greater than zero since a run has finished.", //$NON-NLS-1$
      "Consumed DEs of initialization log point must be greater than zero since both, an initialization procedure and a run, have finished.", //$NON-NLS-1$
    },
    {
      "ERROR IN TEST STRUCTURE: This should never be reached.", //$NON-NLS-1$
      "Consumed DEs of last-improvement log point must be greater than zero since an initialization procedure has finished.", //$NON-NLS-1$
      "Consumed DEs of last-improvement log point must be greater than zero since a run has finished.", //$NON-NLS-1$
      "Consumed DEs of last-improvement log point must be greater than zero since both, an initialization procedure and a run, have finished.", //$NON-NLS-1$
    },
    {
      "ERROR IN TEST STRUCTURE: This should never be reached.", //$NON-NLS-1$
      "Consumed DEs of current log point must be greater than zero since an initialization procedure has finished.", //$NON-NLS-1$
      "Consumed DEs of current log point must be greater than zero since a run has finished.", //$NON-NLS-1$
      "Consumed DEs of current log point must be greater than zero since both, an initialization procedure and a run, have finished.", //$NON-NLS-1$
    }, };

  /** the pre-defined FEDEG0 DE messages */
  private static final String[][] FEDEG0_CONSUMED_FE_MESSAGES = new String[][] {
    {
      "ERROR IN TEST STRUCTURE: This should never be reached.", //$NON-NLS-1$
      "Consumed FEs of initialization log point must be greater than zero since an initialization procedure has finished.", //$NON-NLS-1$
      "Consumed FEs of initialization log point must be greater than zero since a run has finished.", //$NON-NLS-1$
      "Consumed FEs of initialization log point must be greater than zero since both, an initialization procedure and a run, have finished.", //$NON-NLS-1$
    },
    {
      "ERROR IN TEST STRUCTURE: This should never be reached.", //$NON-NLS-1$
      "Consumed FEs of last-improvement log point must be greater than zero since an initialization procedure has finished.", //$NON-NLS-1$
      "Consumed FEs of last-improvement log point must be greater than zero since a run has finished.", //$NON-NLS-1$
      "Consumed FEs of last-improvement log point must be greater than zero since both, an initialization procedure and a run, have finished.", //$NON-NLS-1$
    },
    {
      "ERROR IN TEST STRUCTURE: This should never be reached.", //$NON-NLS-1$
      "Consumed FEs of current log point must be greater than zero since an initialization procedure has finished.", //$NON-NLS-1$
      "Consumed FEs of current log point must be greater than zero since a run has finished.", //$NON-NLS-1$
      "Consumed FEs of current log point must be greater than zero since both, an initialization procedure and a run, have finished.", //$NON-NLS-1$
    }, };

  /** the pre-defined FEDEG0 F messages */
  private static final String[][] FEDEG0_CONSUMED_F_MESSAGES = new String[][] {
    {
      "ERROR IN TEST STRUCTURE: This should never be reached.", //$NON-NLS-1$
      "Best tour length stored in the initialization log point must be less then Long.MAX_VALUE since an initialization procedure has finished.", //$NON-NLS-1$
      "Best tour length stored in the initialization log point must be less then Long.MAX_VALUE since a run has finished.", //$NON-NLS-1$
      "Best tour length stored in the initialization log point must be less then Long.MAX_VALUE since both, an initialization procedure and a run, have finished.", //$NON-NLS-1$
    },
    {
      "ERROR IN TEST STRUCTURE: This should never be reached.", //$NON-NLS-1$
      "Best tour length stored in the last-improvement log point must be less then Long.MAX_VALUE since an initialization procedure has finished.", //$NON-NLS-1$
      "Best tour length stored in the last-improvement log point must be less then Long.MAX_VALUE since a run has finished.", //$NON-NLS-1$
      "Best tour length stored in the last-improvement log point must be less then Long.MAX_VALUE since both, an initialization procedure and a run, have finished.", //$NON-NLS-1$
    },
    {
      "ERROR IN TEST STRUCTURE: This should never be reached.", //$NON-NLS-1$
      "Best tour length stored in the current log point must be less then Long.MAX_VALUE since an initialization procedure has finished.", //$NON-NLS-1$
      "Best tour length stored in the current log point must be less then Long.MAX_VALUE since a run has finished.", //$NON-NLS-1$
      "Best tour length stored in the current log point must be less then Long.MAX_VALUE since both, an initialization procedure and a run, have finished.", //$NON-NLS-1$
    }, };

  /**
   * Validate the state of an objective function. This class checks some
   * basic invariants that must always hold.
   *
   * @param i
   *          the instance of the benchmark problem, or {@code null} if
   *          unknown
   * @param f
   *          the objective function
   * @param state
   *          the state (see {@link #STATE_HAS_FINISHED_INIT},
   *          {@link #STATE_HAS_FINISHED_RUN}, {@link #STATE_NOTHING}
   * @throws Throwable
   *           if an error occurs
   */
  protected void validateObjectiveFunctionState(final Instance i,
      final ObjectiveFunction f, final int state) throws Throwable {
    final LogPoint init;
    final long initBestF, initDEs, initFEs, initTime;
    final LogPoint lastImprovement;
    final long lastImprovementBestF, lastImprovementDEs, lastImprovementFEs, lastImprovementTime;
    final LogPoint current;
    final long currentBestF, currentDEs, currentFEs, currentTime;
    final int n;
    final DistanceComputer dc;
    int[] tour;
    long sum;

    Assert.assertNotNull(//
        "Objective function must not be null.", //$NON-NLS-1$
        f);

    // test the single log points for validity

    // test the initialization log point
    init = f.getDeterministicInitializationLogPoint();

    Assert.assertNotNull(//
        "Must not be null: initialization log point.", //$NON-NLS-1$
        init);

    initDEs = init.getConsumedDEs();
    Assert
    .assertTrue(//
        "Consumed DEs of initialization log point must be greater or equal to zero.", //$NON-NLS-1$
        initDEs >= 0l);

    if ((state & (ObjectiveFunctionValidator.STATE_HAS_FINISHED_INIT)) != 0) {
      Assert
      .assertTrue(
          //
          ObjectiveFunctionValidator.FEDEG0_CONSUMED_DE_MESSAGES[0][state],
          initDEs > 0l);
    }

    initFEs = init.getConsumedFEs();
    Assert
    .assertTrue(//
        "Consumed FEs of the initialization log point must be greater or equal to zero.", //$NON-NLS-1$
        initFEs >= 0l);

    if ((state & (ObjectiveFunctionValidator.STATE_HAS_FINISHED_INIT)) != 0) {
      Assert
      .assertTrue(
          //
          ObjectiveFunctionValidator.FEDEG0_CONSUMED_FE_MESSAGES[0][state],
          initFEs > 0l);
    }

    initTime = init.getConsumedRuntime();
    Assert
    .assertTrue(//
        "Consumed runtime of the initialization log point must be greater or equal to zero.", //$NON-NLS-1$
        initTime >= 0l);

    Assert
    .assertTrue(//
        "Consumed runtime of the initialization log point must be less or equal to 1 years (31536000000 milliseconds) in order to be considered as sane.", //$NON-NLS-1$
        initTime <= 31536000000l);

    Assert
    .assertTrue(//
        "Consumed FEs of the initialization log point must be less or equal then the amount of consumed DEs.", //$NON-NLS-1$
        initFEs <= initDEs);

    initBestF = init.getBestF();
    Assert
    .assertTrue(//
        "Best tour length stored in the initialization log point must be greater or equal to zero.", //$NON-NLS-1$
        initBestF >= 0l);

    if ((state & (ObjectiveFunctionValidator.STATE_HAS_FINISHED_INIT)) != 0) {
      Assert.assertTrue(
          //
          ObjectiveFunctionValidator.FEDEG0_CONSUMED_F_MESSAGES[0][state],
          initBestF < java.lang.Long.MAX_VALUE);
    }

    if (i != null) {
      Assert
      .assertTrue(//
          "Best tour length stored in the initialization log point must be greater or equal to the globally optimal (shortest) tour length.", //$NON-NLS-1$
          initBestF >= i.optimum());
    }

    if (initFEs <= 0l) {
      Assert
      .assertTrue(//
          "No FE has been performed in the context of the initialization log point. The best tour length in initialization log point therefore cannot be better than Long.MAX_VALUE.",//$NON-NLS-1$
          initBestF >= Long.MAX_VALUE);
    } else {
      Assert
      .assertTrue(//
          "At least one FE has been performed in the context of the initialization log point. The best tour length in initialization log point therefore must be better than Long.MAX_VALUE.",//$NON-NLS-1$
          initBestF < Long.MAX_VALUE);
    }
    // test the last-improvement log point
    lastImprovement = f.getLastImprovementLogPoint();

    Assert.assertNotNull(//
        "Must not be null: last-improvement log point.", //$NON-NLS-1$
        lastImprovement);

    lastImprovementDEs = lastImprovement.getConsumedDEs();
    Assert
    .assertTrue(//
        "Consumed DEs of last-improvement log point must be greater or equal to zero.", //$NON-NLS-1$
        lastImprovementDEs >= 0l);

    if ((state & (ObjectiveFunctionValidator.STATE_HAS_FINISHED_INIT | ObjectiveFunctionValidator.STATE_HAS_FINISHED_RUN)) != 0) {
      Assert
      .assertTrue(
          //
          ObjectiveFunctionValidator.FEDEG0_CONSUMED_DE_MESSAGES[1][state],
          lastImprovementDEs > 0l);
    }

    lastImprovementFEs = lastImprovement.getConsumedFEs();
    Assert
    .assertTrue(//
        "Consumed FEs of the last-improvement log point must be greater or equal to zero.", //$NON-NLS-1$
        lastImprovementFEs >= 0l);

    if ((state & (ObjectiveFunctionValidator.STATE_HAS_FINISHED_INIT | ObjectiveFunctionValidator.STATE_HAS_FINISHED_RUN)) != 0) {
      Assert
      .assertTrue(
          //
          ObjectiveFunctionValidator.FEDEG0_CONSUMED_FE_MESSAGES[1][state],
          lastImprovementFEs > 0l);
    }

    lastImprovementTime = lastImprovement.getConsumedRuntime();
    Assert
    .assertTrue(//
        "Consumed runtime of the last-improvement log point must be greater or equal to zero.", //$NON-NLS-1$
        lastImprovementTime >= 0l);

    Assert
    .assertTrue(//
        "Consumed runtime of the last-improvement log point must be less or equal to 1 years (31536000000 milliseconds) in order to be considered as sane.", //$NON-NLS-1$
        lastImprovementTime <= 31536000000l);

    Assert
    .assertTrue(//
        "Consumed FEs of the last-improvement log point must be less or equal then the amount of consumed DEs.", //$NON-NLS-1$
        lastImprovementFEs <= lastImprovementDEs);

    lastImprovementBestF = lastImprovement.getBestF();
    Assert
    .assertTrue(//
        "Best tour length stored in the last-improvement log point must be greater or equal to zero.", //$NON-NLS-1$
        lastImprovementBestF >= 0l);

    if ((state & (ObjectiveFunctionValidator.STATE_HAS_FINISHED_INIT | ObjectiveFunctionValidator.STATE_HAS_FINISHED_RUN)) != 0) {
      Assert.assertTrue(
          //
          ObjectiveFunctionValidator.FEDEG0_CONSUMED_F_MESSAGES[1][state],
          lastImprovementBestF < java.lang.Long.MAX_VALUE);
    }

    if (i != null) {
      Assert
      .assertTrue(//
          "Best tour length stored in the last-improvement log point must be greater or equal to the globally optimal (shortest) tour length.", //$NON-NLS-1$
          lastImprovementBestF >= i.optimum());
    }

    if (lastImprovementFEs <= 0l) {
      Assert
      .assertTrue(//
          "No FE has been performed in the context of the last-improvement log point. The best tour length in last-improvement log point therefore cannot be better than Long.MAX_VALUE.",//$NON-NLS-1$
          lastImprovementBestF >= Long.MAX_VALUE);
    } else {
      Assert
      .assertTrue(//
          "At least one FE has been performed in the context of the last-improvement log point. The best tour length in last-improvement log point therefore must be better than Long.MAX_VALUE.",//$NON-NLS-1$
          lastImprovementBestF < Long.MAX_VALUE);
    }
    // test the current log point
    current = f.getCurrentLogPoint();

    Assert.assertNotNull(//
        "Must not be null: current log point.", //$NON-NLS-1$
        current);

    currentDEs = current.getConsumedDEs();
    Assert
    .assertTrue(//
        "Consumed DEs of current log point must be greater or equal to zero.", //$NON-NLS-1$
        currentDEs >= 0l);

    if ((state & (ObjectiveFunctionValidator.STATE_HAS_FINISHED_RUN)) != 0) {
      Assert
      .assertTrue(
          //
          ObjectiveFunctionValidator.FEDEG0_CONSUMED_DE_MESSAGES[2][state],
          currentDEs > 0l);
    }

    currentFEs = current.getConsumedFEs();
    Assert
    .assertTrue(//
        "Consumed FEs of the current log point must be greater or equal to zero.", //$NON-NLS-1$
        currentFEs >= 0l);

    if ((state & (ObjectiveFunctionValidator.STATE_HAS_FINISHED_RUN)) != 0) {
      Assert
      .assertTrue(
          //
          ObjectiveFunctionValidator.FEDEG0_CONSUMED_FE_MESSAGES[2][state],
          currentFEs > 0l);
    }

    currentTime = current.getConsumedRuntime();
    Assert
    .assertTrue(//
        "Consumed runtime of the current log point must be greater or equal to zero.", //$NON-NLS-1$
        currentTime >= 0l);

    Assert
    .assertTrue(//
        "Consumed runtime of the current log point must be less or equal to 1 years (31536000000 milliseconds) in order to be considered as sane.", //$NON-NLS-1$
        currentTime <= 31536000000l);

    Assert
    .assertTrue(//
        "Consumed FEs of the current log point must be less or equal then the amount of consumed DEs.", //$NON-NLS-1$
        currentFEs <= currentDEs);

    currentBestF = current.getBestF();
    Assert
    .assertTrue(//
        "Best tour length stored in the current log point must be greater or equal to zero.", //$NON-NLS-1$
        currentBestF >= 0l);

    if ((state & (ObjectiveFunctionValidator.STATE_HAS_FINISHED_RUN)) != 0) {
      Assert.assertTrue(
          //
          ObjectiveFunctionValidator.FEDEG0_CONSUMED_F_MESSAGES[2][state],
          currentBestF < java.lang.Long.MAX_VALUE);
    }

    if (i != null) {
      Assert
      .assertTrue(//
          "Best tour length stored in the current log point must be greater or equal to the globally optimal (shortest) tour length.", //$NON-NLS-1$
          currentBestF >= i.optimum());
    }

    if (currentFEs <= 0l) {
      Assert
      .assertTrue(//
          "No FE has been performed in the context of the current log point. The best tour length in current log point therefore cannot be better than Long.MAX_VALUE.",//$NON-NLS-1$
          currentBestF >= Long.MAX_VALUE);
    } else {
      Assert
      .assertTrue(//
          "At least one FE has been performed in the context of the current log point. The best tour length in current log point therefore must be better than Long.MAX_VALUE.",//$NON-NLS-1$
          currentBestF < Long.MAX_VALUE);
    }

    // test the relationship between the single log points

    // compare the states of the last-improvement log point and the
    // initialization log point
    // as the last-improvement log point is younger than the initialization
    // log point, certain relationships must hold

    Assert
    .assertTrue(//
        "Best tour length stored in the last-improvement log point must be shorter or equal to the best tour length stored in the initialization log point.", //$NON-NLS-1$
        lastImprovementBestF <= initBestF);

    // compare the states of the current log point and the initialization
    // log point
    // as the current log point is younger than the initialization log
    // point, certain relationships must hold

    Assert
    .assertTrue(//
        "Best tour length stored in the current log point must be shorter or equal to the best tour length stored in the initialization log point.", //$NON-NLS-1$
        currentBestF <= initBestF);

    Assert
    .assertTrue(//
        "The consumed DEs of the current log point must be greater or equal than the consumed DEs of the initialization log point ", //$NON-NLS-1$
        currentDEs >= initDEs);

    Assert
    .assertTrue(//
        "The consumed FEs of the current log point must be greater or equal than the consumed FEs of the initialization log point ", //$NON-NLS-1$
        currentFEs >= initFEs);

    Assert
    .assertTrue(//
        "The consumed runtime of the current log point must be greater or equal than the consumed runtime of the initialization log point ", //$NON-NLS-1$
        currentTime >= initTime);

    // compare the states of the current log point and the last-improvement
    // log point
    // as the current log point is younger than the last-improvement log
    // point, certain relationships must hold

    Assert
    .assertTrue(//
        "Best tour length stored in the current log point must be shorter or equal to the best tour length stored in the last-improvement log point.", //$NON-NLS-1$
        currentBestF <= lastImprovementBestF);

    Assert
    .assertTrue(//
        "The consumed DEs of the current log point must be greater or equal than the consumed DEs of the last-improvement log point ", //$NON-NLS-1$
        currentDEs >= lastImprovementDEs);

    Assert
    .assertTrue(//
        "The consumed FEs of the current log point must be greater or equal than the consumed FEs of the last-improvement log point ", //$NON-NLS-1$
        currentFEs >= lastImprovementFEs);

    Assert
    .assertTrue(//
        "The consumed runtime of the current log point must be greater or equal than the consumed runtime of the last-improvement log point ", //$NON-NLS-1$
        currentTime >= lastImprovementTime);

    // if some FEs have been performed, we can conduct additional tests
    if (currentFEs > 0l) {
      // compare best known distance to what a distance computer will
      // derive

      // if instance is different, delete distance computer
      if (i != this.m_lastI) {
        this.m_lastI = null;
        this.m_lastDC = null;
      }

      n = f.n();

      Assert.assertTrue(//
          "Dimension of objective function must be greater than 1.",//$NON-NLS-1$
          n > 1);

      if (i != null) {
        Assert.assertEquals(//
            "Dimension of objective function and instance must agree.",//$NON-NLS-1$
            n, i.n());

        if (this.m_lastDC != null) {
          // re-use distance computer
          dc = this.m_lastDC;
        } else {
          // load new distance computer without caching
          this.m_lastDC = dc = i.load(0);
        }

        Assert.assertEquals(//
            "Dimension of distance computer and instance must agree.",//$NON-NLS-1$
            n, dc.n());
      } else {
        // no distance computer provided, use f
        dc = f;
      }

      tour = this.m_tour;
      if ((tour == null) || (tour.length != n)) {
        this.m_tour = tour = new int[n];
      }

      f.getCopyOfBest(tour);

      Assert
      .assertEquals(//
          "The length of the best tour known to the algorithm must agree to the tour length calculated by the objective function and stored in the current log point.", //$NON-NLS-1$
          currentBestF, dc.evaluate(tour));

      sum = this.validatePath(tour, dc);

      Assert
      .assertEquals(//
          "The total distance calculated by the distance computer disagrees with the distance found by the objective function and the evaluation result of the distance computer.", //$NON-NLS-1$
          currentBestF, sum);
    }
  }

  /**
   * Apply a test to a given problem instance. This method implements the
   * framework logic to load instances, link their output to temporary
   * files, and run a certain test.
   *
   * @param inst
   *          the benchmark instance
   * @param test
   *          the test to run
   * @param maxRuns
   *          the number of runs to perform
   * @param maxFEsPerRun
   *          the maximum number of function evaluations per run
   * @param maxTimePerRun
   *          the maximum time per run
   * @param incrementState
   *          if this is {@code true}, then special testing code depending
   *          on changing states is executed. this means that it is
   *          expected that the tested entity behaves like an algorithm and
   *          evaluates {@code int[]}s.
   * @throws Throwable
   *           if something goes wrong
   */
  protected void performObjectiveTest(final Instance inst,
      final ObjectiveTest test, final long maxFEsPerRun,
      final long maxTimePerRun, final int maxRuns,
      final boolean incrementState) throws Throwable {
    final Benchmark bm;
    final ObjectiveFunction ff;
    int i, state, runState;

    Assert.assertNotNull("No instance given.", inst); //$NON-NLS-1$
    Assert.assertNotNull("No test given.", test); //$NON-NLS-1$

    try (final TempDir tempDir = new TempDir()) {
      bm = new Benchmark(tempDir.getDir(), inst);

      bm.setMaxDEs(Long.MAX_VALUE);
      bm.setMaxFEs(maxFEsPerRun);
      bm.setMaxRuntime(maxTimePerRun);

      ff = bm.createObjective();
      Assert.assertNotNull("Error creating benchmark function.", ff);//$NON-NLS-1$

      test.beforeTest(inst, bm, ff);
      try {
        state = ObjectiveFunctionValidator.STATE_NOTHING;
        if (test.isInitializationNecessary()) {
          test.beforeInitialization(inst, bm, ff);
          try {
            ff.beginDeterministicInitialization(this.m_dummy);
            try {
              this.validateObjectiveFunctionState(inst, ff, state);
              test.initialize(inst, bm, ff);
              this.validateObjectiveFunctionState(inst, ff, state);
            } finally {
              ff.endDeterministicInitialization();
            }
            if (incrementState) {
              state |= ObjectiveFunctionValidator.STATE_HAS_FINISHED_INIT;
              this.validateObjectiveFunctionState(inst, ff, state);
            }
          } finally {
            test.afterInitialization(inst, bm, ff);
          }
        }

        runState = state;
        if (incrementState) {
          runState |= ObjectiveFunctionValidator.STATE_HAS_FINISHED_RUN;
        }

        for (i = 0; i < maxRuns; i++) {
          test.beforeRun(inst, bm, ff);
          try {
            ff.beginRun(this.m_dummy);
            try {
              this.validateObjectiveFunctionState(inst, ff, state);
              test.run(inst, bm, ff);
              this.validateObjectiveFunctionState(inst, ff, runState);
            } finally {
              ff.endRun();
            }
          } finally {
            test.afterRun(inst, bm, ff);
          }
        }
      } finally {
        test.afterTest(inst, bm, ff);
      }
    }
  }

}
