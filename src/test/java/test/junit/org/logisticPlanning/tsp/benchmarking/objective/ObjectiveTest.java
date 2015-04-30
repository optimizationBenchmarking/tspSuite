package test.junit.org.logisticPlanning.tsp.benchmarking.objective;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.Benchmark;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;

/**
 * This interface allows us to easily devise tests under an objective
 * function environment.
 */
public class ObjectiveTest {

  /** instantiate */
  protected ObjectiveTest() {
    super();
  }

  /**
   * Called before each test
   * 
   * @param inst
   *          the problem instance
   * @param bm
   *          the benchmark
   * @param f
   *          the objective function
   * @throws Throwable
   *           if anything goes wrong
   */
  public void beforeTest(final Instance inst, final Benchmark bm,
      final ObjectiveFunction f) throws Throwable {
    //
  }

  /**
   * called before each run
   * 
   * @param inst
   *          the problem instance
   * @param bm
   *          the benchmark
   * @param f
   *          the objective function
   * @throws Throwable
   *           if anything goes wrong
   */
  public void beforeRun(final Instance inst, final Benchmark bm,
      final ObjectiveFunction f) throws Throwable {
    //
  }

  /**
   * conduct the test
   * 
   * @param inst
   *          the problem instance
   * @param bm
   *          the benchmark
   * @param f
   *          the objective function
   * @throws Throwable
   *           if anything goes wrong
   */
  public void run(final Instance inst, final Benchmark bm,
      final ObjectiveFunction f) throws Throwable {
    //
  }

  /**
   * called after each run
   * 
   * @param inst
   *          the problem instance
   * @param bm
   *          the benchmark
   * @param f
   *          the objective function
   * @throws Throwable
   *           if anything goes wrong
   */
  public void afterRun(final Instance inst, final Benchmark bm,
      final ObjectiveFunction f) throws Throwable {
    //
  }

  /**
   * called after each test
   * 
   * @param inst
   *          the problem instance
   * @param bm
   *          the benchmark
   * @param f
   *          the objective function
   * @throws Throwable
   *           if anything goes wrong
   */
  public void afterTest(final Instance inst, final Benchmark bm,
      final ObjectiveFunction f) throws Throwable {
    //
  }

  /**
   * is an initialization procedure necessary?
   * 
   * @return {@code true} if yes, {@code false} if not
   */
  public boolean isInitializationNecessary() {
    return false;
  }

  /**
   * called before each initialization procedure
   * 
   * @param inst
   *          the problem instance
   * @param bm
   *          the benchmark
   * @param f
   *          the objective function
   * @throws Throwable
   *           if anything goes wrong
   */
  public void beforeInitialization(final Instance inst,
      final Benchmark bm, final ObjectiveFunction f) throws Throwable {
    //
  }

  /**
   * conduct the initialization procedure
   * 
   * @param inst
   *          the problem instance
   * @param bm
   *          the benchmark
   * @param f
   *          the objective function
   * @throws Throwable
   *           if anything goes wrong
   */
  public void initialize(final Instance inst, final Benchmark bm,
      final ObjectiveFunction f) throws Throwable {
    //
  }

  /**
   * called after each initialization procedure
   * 
   * @param inst
   *          the problem instance
   * @param bm
   *          the benchmark
   * @param f
   *          the objective function
   * @throws Throwable
   *           if anything goes wrong
   */
  public void afterInitialization(final Instance inst, final Benchmark bm,
      final ObjectiveFunction f) throws Throwable {
    //
  }
}
