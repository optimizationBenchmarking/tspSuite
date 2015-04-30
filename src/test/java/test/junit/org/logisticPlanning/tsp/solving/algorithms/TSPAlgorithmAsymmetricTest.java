package test.junit.org.logisticPlanning.tsp.solving.algorithms;

import org.junit.Ignore;
import org.junit.Test;
import org.logisticPlanning.tsp.benchmarking.instances.Instance;

/**
 * Test a TSP algorithm on some selected symmetric and asymmetric TSP
 * instances. This class extends class
 * {@link test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest
 * TSPAlgorithmSymmetricTest} which provides test cases for symmetric
 * instances with a set of test cases for asymmetric TSP instances. These
 * additional tests require that the TSP algorithm to be tested can work in
 * situations where
 * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#distance(int,int) dist(i,j)}&ne;{@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#distance(int,int) dist(j,i)}</code>
 * for some {@code i}, {@code j}.
 */
@Ignore
public class TSPAlgorithmAsymmetricTest extends TSPAlgorithmSymmetricTest {

  /** instantiate */
  protected TSPAlgorithmAsymmetricTest() {
    super();
  }

  /**
   * Test whether solving works correctly on the asymmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BR17}.
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_BR17() throws Throwable {
    this.solve_Test(Instance.BR17);
  }

  /**
   * Test whether solving works correctly on the asymmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FT53}.
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_FT53() throws Throwable {
    this.solve_Test(Instance.FT53);
  }

  /**
   * Test whether solving works correctly on the asymmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FT70}.
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_FT70() throws Throwable {
    this.solve_Test(Instance.FT70);
  }

  /**
   * Test whether solving works correctly on the asymmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV33}
   * .
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_FTV33() throws Throwable {
    this.solve_Test(Instance.FTV33);
  }

  /**
   * Test whether solving works correctly on the asymmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV35}
   * .
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_FTV35() throws Throwable {
    this.solve_Test(Instance.FTV35);
  }

  /**
   * Test whether solving works correctly on the asymmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV38}
   * .
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_FTV38() throws Throwable {
    this.solve_Test(Instance.FTV38);
  }

  /**
   * Test whether solving works correctly on the asymmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV44}
   * .
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_FTV44() throws Throwable {
    this.solve_Test(Instance.FTV44);
  }

  /**
   * Test whether solving works correctly on the asymmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV47}
   * .
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_FTV47() throws Throwable {
    this.solve_Test(Instance.FTV47);
  }

  /**
   * Test whether solving works correctly on the asymmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV55}
   * .
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_FTV55() throws Throwable {
    this.solve_Test(Instance.FTV55);
  }

  /**
   * Test whether solving works correctly on the asymmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV64}
   * .
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_FTV64() throws Throwable {
    this.solve_Test(Instance.FTV64);
  }

  /**
   * Test whether solving works correctly on the asymmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV70}
   * .
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_FTV70() throws Throwable {
    this.solve_Test(Instance.FTV70);
  }

  /**
   * Test whether solving works correctly on the asymmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#P43}.
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_P43() throws Throwable {
    this.solve_Test(Instance.P43);
  }

  /**
   * Test whether solving works correctly on the asymmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RY48P}
   * .
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_RY48P() throws Throwable {
    this.solve_Test(Instance.RY48P);
  }

  /**
   * Test whether solving works correctly on the asymmetric instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RBG443}
   * .
   * 
   * @throws Throwable
   *           if I/O fails, or whatever
   */
  @Test(timeout = 3600000)
  public final void testSolveSymmetric_RBG443() throws Throwable {
    this.solve_Test(Instance.RBG443);
  }

}
