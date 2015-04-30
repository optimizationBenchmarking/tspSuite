package test.junit.org.logisticPlanning.tsp.solving.algorithms;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.solving.TSPAlgorithm;

import test.junit.org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunctionValidator;

/**
 * Test a tsp algorithm on some selected symmetric TSP instances.
 */
@Ignore
public class TSPAlgorithmSymmetricTest extends ObjectiveFunctionValidator {

  /** no algorithm given */
  static final String TSP_ALGO_NULL = "The tsp algorithm is null."; //$NON-NLS-1$

  /** the tsp algorithm */
  private TSPAlgorithm m_algo;

  /** the init algorithm */
  private TSPAlgorithm m_init;

  /** the internal algorithm test */
  final _AlgorithmTest m_test;

  /** instantiate */
  protected TSPAlgorithmSymmetricTest() {
    super();
    this.m_test = new _AlgorithmTest(this);
  }

  /**
   * create the tsp algorithm
   * 
   * @return the tsp algorithm
   */
  protected TSPAlgorithm createAlgorithm() {
    return null;
  }

  /**
   * create the tsp algorithm
   * 
   * @return the tsp algorithm
   */
  public final TSPAlgorithm getAlgorithm() {
    if (this.m_algo == null) {
      this.m_algo = this.createAlgorithm();
    }
    return this.m_algo;
  }

  /**
   * create the tsp algorithm
   * 
   * @return the tsp algorithm
   */
  protected TSPAlgorithm createInitAlgorithm() {
    return null;
  }

  /**
   * create the tsp algorithm
   * 
   * @return the tsp algorithm
   */
  public final TSPAlgorithm getInitAlgorithm() {
    if (this.m_init == null) {
      this.m_init = this.createInitAlgorithm();
    }
    return this.m_init;
  }

  /** check whether the optimization method is created correctly */
  @Test(timeout = 3600000)
  public final void testAlgorithmNotNull() {
    Assert.assertNotNull(TSPAlgorithmSymmetricTest.TSP_ALGO_NULL,
        this.getAlgorithm());
  }

  /**
   * Run the algorithm on a given benchmark instance. We perform one long
   * test (with at most 40s runtime), one medium test with at most 12s
   * runtime, and five short tests of at most 5s.
   * 
   * @param inst
   *          the instance
   * @throws Throwable
   *           if something goes wrong
   */
  final void solve_Test(final Instance inst) throws Throwable {
    final long n;

    n = inst.n();
    Assert.assertNotNull("No instance given.", inst); //$NON-NLS-1$

    // one very long test
    this.performObjectiveTest(inst, this.m_test,//
        (n * (n * (n * (n * 100l)))),//
        1000l * Math.min(n, 40l),//
        1, true);

    // one medium test
    this.performObjectiveTest(inst, this.m_test,//
        (n * (n * (n * (n * 100l)))),//
        1000l * 12l,//
        1, true);

    // 5 short tests
    this.performObjectiveTest(inst, this.m_test,//
        (n * (n * (n * (n * 100l)))),//
        1000l * 5l,//
        5, true);
  }

  /**
   * Test whether solving works correctly on the symmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#ATT48}
   * .
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_ATT48() throws Throwable {
    this.solve_Test(Instance.ATT48);
  }

  /**
   * Test whether solving works correctly on the symmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BAYG29}
   * .
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_BAYG29() throws Throwable {
    this.solve_Test(Instance.BAYG29);
  }

  /**
   * Test whether solving works correctly on the symmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BAYS29}
   * .
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_BAYS29() throws Throwable {
    this.solve_Test(Instance.BAYS29);
  }

  /**
   * Test whether solving works correctly on the symmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BERLIN52}
   * .
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_BERLIN52() throws Throwable {
    this.solve_Test(Instance.BERLIN52);
  }

  /**
   * Test whether solving works correctly on the symmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BRAZIL58}
   * .
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_BRAZIL58() throws Throwable {
    this.solve_Test(Instance.BRAZIL58);
  }

  /**
   * Test whether solving works correctly on the symmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BURMA14}
   * .
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_BURMA14() throws Throwable {
    this.solve_Test(Instance.BURMA14);
  }

  /**
   * Test whether solving works correctly on the symmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#DANTZIG42}
   * .
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_DANTZIG42() throws Throwable {
    this.solve_Test(Instance.DANTZIG42);
  }

  /**
   * Test whether solving works correctly on the symmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#EIL51}
   * .
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_EIL51() throws Throwable {
    this.solve_Test(Instance.EIL51);
  }

  /**
   * Test whether solving works correctly on the symmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#EIL76}
   * .
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_EIL76() throws Throwable {
    this.solve_Test(Instance.EIL76);
  }

  /**
   * Test whether solving works correctly on the symmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FRI26}
   * .
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_FRI26() throws Throwable {
    this.solve_Test(Instance.FRI26);
  }

  /**
   * Test whether solving works correctly on the symmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR17}.
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_GR17() throws Throwable {
    this.solve_Test(Instance.GR17);
  }

  /**
   * Test whether solving works correctly on the symmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR21}.
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_GR21() throws Throwable {
    this.solve_Test(Instance.GR21);
  }

  /**
   * Test whether solving works correctly on the symmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR24}.
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_GR24() throws Throwable {
    this.solve_Test(Instance.GR24);
  }

  /**
   * Test whether solving works correctly on the symmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR48}.
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_GR48() throws Throwable {
    this.solve_Test(Instance.GR48);
  }

  /**
   * Test whether solving works correctly on the symmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR96}.
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_GR96() throws Throwable {
    this.solve_Test(Instance.GR96);
  }

  /**
   * Test whether solving works correctly on the symmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#HK48}.
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_HK48() throws Throwable {
    this.solve_Test(Instance.HK48);
  }

  /**
   * Test whether solving works correctly on the symmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROA100}
   * .
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_KROA100() throws Throwable {
    this.solve_Test(Instance.KROA100);
  }

  /**
   * Test whether solving works correctly on the symmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROB100}
   * .
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_KROB100() throws Throwable {
    this.solve_Test(Instance.KROB100);
  }

  /**
   * Test whether solving works correctly on the symmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROC100}
   * .
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_KROC100() throws Throwable {
    this.solve_Test(Instance.KROC100);
  }

  /**
   * Test whether solving works correctly on the symmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROD100}
   * .
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_KROD100() throws Throwable {
    this.solve_Test(Instance.KROD100);
  }

  /**
   * Test whether solving works correctly on the symmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROE100}
   * .
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_KROE100() throws Throwable {
    this.solve_Test(Instance.KROE100);
  }

  /**
   * Test whether solving works correctly on the symmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR76}.
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_PR76() throws Throwable {
    this.solve_Test(Instance.PR76);
  }

  /**
   * Test whether solving works correctly on the symmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RAT99}
   * .
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_RAT99() throws Throwable {
    this.solve_Test(Instance.RAT99);
  }

  /**
   * Test whether solving works correctly on the symmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RD100}
   * .
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_RD100() throws Throwable {
    this.solve_Test(Instance.RD100);
  }

  /**
   * Test whether solving works correctly on the symmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#ST70}.
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_ST70() throws Throwable {
    this.solve_Test(Instance.ST70);
  }

  /**
   * Test whether solving works correctly on the symmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#SWISS42}
   * .
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_SWISS42() throws Throwable {
    this.solve_Test(Instance.SWISS42);
  }

  /**
   * Test whether solving works correctly on the symmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#ULYSSES16}
   * .
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_ULYSSES16() throws Throwable {
    this.solve_Test(Instance.ULYSSES16);
  }

  /**
   * Test whether solving works correctly on the symmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#ULYSSES22}
   * .
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_ULYSSES22() throws Throwable {
    this.solve_Test(Instance.ULYSSES22);
  }

  /**
   * Test whether solving works correctly on the symmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RL5934}
   * .
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_RL5934() throws Throwable {
    this.solve_Test(Instance.RL5934);
  }

}
