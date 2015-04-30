package test.junit.org.logisticPlanning.tsp.solving.operators;

import org.junit.Ignore;
import org.junit.Test;
import org.logisticPlanning.tsp.benchmarking.instances.Instance;

/**
 * Test a search operator for symmetric and asymmetric cases
 * 
 * @param <P>
 *          the product type
 */
@Ignore
public class AsymmetricOperatorTest<P> extends SymmetricOperatorTest<P> {

  /** instantiate */
  protected AsymmetricOperatorTest() {
    super();
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BR17}
   * if the inputs of the operator all are identical.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_BR17() throws Throwable {
    this.performObjectiveTest(Instance.BR17, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BR17}
   * if the inputs of the operator differ slightly.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_BR17()
      throws Throwable {
    this.performObjectiveTest(Instance.BR17, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BR17}
   * if the inputs of the operator differ much.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_BR17() throws Throwable {
    this.performObjectiveTest(Instance.BR17, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BR17}
   * if the inputs of the operator are outputs of previous applications.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_BR17() throws Throwable {
    this.performObjectiveTest(Instance.BR17, this.m_chain, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FT53}
   * if the inputs of the operator all are identical.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_FT53() throws Throwable {
    this.performObjectiveTest(Instance.FT53, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FT53}
   * if the inputs of the operator differ slightly.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_FT53()
      throws Throwable {
    this.performObjectiveTest(Instance.FT53, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FT53}
   * if the inputs of the operator differ much.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_FT53() throws Throwable {
    this.performObjectiveTest(Instance.FT53, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FT53}
   * if the inputs of the operator are outputs of previous applications.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_FT53() throws Throwable {
    this.performObjectiveTest(Instance.FT53, this.m_chain, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FT70}
   * if the inputs of the operator all are identical.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_FT70() throws Throwable {
    this.performObjectiveTest(Instance.FT70, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FT70}
   * if the inputs of the operator differ slightly.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_FT70()
      throws Throwable {
    this.performObjectiveTest(Instance.FT70, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FT70}
   * if the inputs of the operator differ much.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_FT70() throws Throwable {
    this.performObjectiveTest(Instance.FT70, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FT70}
   * if the inputs of the operator are outputs of previous applications.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_FT70() throws Throwable {
    this.performObjectiveTest(Instance.FT70, this.m_chain, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV33}
   * if the inputs of the operator all are identical.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_FTV33() throws Throwable {
    this.performObjectiveTest(Instance.FTV33, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV33}
   * if the inputs of the operator differ slightly.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_FTV33()
      throws Throwable {
    this.performObjectiveTest(Instance.FTV33, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV33}
   * if the inputs of the operator differ much.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_FTV33() throws Throwable {
    this.performObjectiveTest(Instance.FTV33, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV33}
   * if the inputs of the operator are outputs of previous applications.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_FTV33() throws Throwable {
    this.performObjectiveTest(Instance.FTV33, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV35}
   * if the inputs of the operator all are identical.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_FTV35() throws Throwable {
    this.performObjectiveTest(Instance.FTV35, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV35}
   * if the inputs of the operator differ slightly.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_FTV35()
      throws Throwable {
    this.performObjectiveTest(Instance.FTV35, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV35}
   * if the inputs of the operator differ much.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_FTV35() throws Throwable {
    this.performObjectiveTest(Instance.FTV35, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV35}
   * if the inputs of the operator are outputs of previous applications.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_FTV35() throws Throwable {
    this.performObjectiveTest(Instance.FTV35, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV38}
   * if the inputs of the operator all are identical.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_FTV38() throws Throwable {
    this.performObjectiveTest(Instance.FTV38, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV38}
   * if the inputs of the operator differ slightly.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_FTV38()
      throws Throwable {
    this.performObjectiveTest(Instance.FTV38, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV38}
   * if the inputs of the operator differ much.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_FTV38() throws Throwable {
    this.performObjectiveTest(Instance.FTV38, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV38}
   * if the inputs of the operator are outputs of previous applications.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_FTV38() throws Throwable {
    this.performObjectiveTest(Instance.FTV38, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV44}
   * if the inputs of the operator all are identical.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_FTV44() throws Throwable {
    this.performObjectiveTest(Instance.FTV44, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV44}
   * if the inputs of the operator differ slightly.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_FTV44()
      throws Throwable {
    this.performObjectiveTest(Instance.FTV44, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV44}
   * if the inputs of the operator differ much.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_FTV44() throws Throwable {
    this.performObjectiveTest(Instance.FTV44, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV44}
   * if the inputs of the operator are outputs of previous applications.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_FTV44() throws Throwable {
    this.performObjectiveTest(Instance.FTV44, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV47}
   * if the inputs of the operator all are identical.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_FTV47() throws Throwable {
    this.performObjectiveTest(Instance.FTV47, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV47}
   * if the inputs of the operator differ slightly.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_FTV47()
      throws Throwable {
    this.performObjectiveTest(Instance.FTV47, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV47}
   * if the inputs of the operator differ much.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_FTV47() throws Throwable {
    this.performObjectiveTest(Instance.FTV47, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV47}
   * if the inputs of the operator are outputs of previous applications.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_FTV47() throws Throwable {
    this.performObjectiveTest(Instance.FTV47, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV55}
   * if the inputs of the operator all are identical.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_FTV55() throws Throwable {
    this.performObjectiveTest(Instance.FTV55, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV55}
   * if the inputs of the operator differ slightly.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_FTV55()
      throws Throwable {
    this.performObjectiveTest(Instance.FTV55, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV55}
   * if the inputs of the operator differ much.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_FTV55() throws Throwable {
    this.performObjectiveTest(Instance.FTV55, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV55}
   * if the inputs of the operator are outputs of previous applications.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_FTV55() throws Throwable {
    this.performObjectiveTest(Instance.FTV55, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV64}
   * if the inputs of the operator all are identical.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_FTV64() throws Throwable {
    this.performObjectiveTest(Instance.FTV64, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV64}
   * if the inputs of the operator differ slightly.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_FTV64()
      throws Throwable {
    this.performObjectiveTest(Instance.FTV64, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV64}
   * if the inputs of the operator differ much.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_FTV64() throws Throwable {
    this.performObjectiveTest(Instance.FTV64, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV64}
   * if the inputs of the operator are outputs of previous applications.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_FTV64() throws Throwable {
    this.performObjectiveTest(Instance.FTV64, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV70}
   * if the inputs of the operator all are identical.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_FTV70() throws Throwable {
    this.performObjectiveTest(Instance.FTV70, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV70}
   * if the inputs of the operator differ slightly.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_FTV70()
      throws Throwable {
    this.performObjectiveTest(Instance.FTV70, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV70}
   * if the inputs of the operator differ much.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_FTV70() throws Throwable {
    this.performObjectiveTest(Instance.FTV70, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV70}
   * if the inputs of the operator are outputs of previous applications.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_FTV70() throws Throwable {
    this.performObjectiveTest(Instance.FTV70, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV170}
   * if the inputs of the operator all are identical.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_FTV170() throws Throwable {
    this.performObjectiveTest(Instance.FTV170, this.m_same,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV170}
   * if the inputs of the operator differ slightly.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_FTV170()
      throws Throwable {
    this.performObjectiveTest(Instance.FTV170, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV170}
   * if the inputs of the operator differ much.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_FTV170()
      throws Throwable {
    this.performObjectiveTest(Instance.FTV170, this.m_diff,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV170}
   * if the inputs of the operator are outputs of previous applications.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_FTV170() throws Throwable {
    this.performObjectiveTest(Instance.FTV170, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KRO124P}
   * if the inputs of the operator all are identical.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_KRO124P() throws Throwable {
    this.performObjectiveTest(Instance.KRO124P, this.m_same,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KRO124P}
   * if the inputs of the operator differ slightly.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_KRO124P()
      throws Throwable {
    this.performObjectiveTest(Instance.KRO124P, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KRO124P}
   * if the inputs of the operator differ much.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_KRO124P()
      throws Throwable {
    this.performObjectiveTest(Instance.KRO124P, this.m_diff,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KRO124P}
   * if the inputs of the operator are outputs of previous applications.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_KRO124P() throws Throwable {
    this.performObjectiveTest(Instance.KRO124P, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#P43}
   * if the inputs of the operator all are identical.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_P43() throws Throwable {
    this.performObjectiveTest(Instance.P43, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#P43}
   * if the inputs of the operator differ slightly.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_P43()
      throws Throwable {
    this.performObjectiveTest(Instance.P43, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#P43}
   * if the inputs of the operator differ much.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_P43() throws Throwable {
    this.performObjectiveTest(Instance.P43, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#P43}
   * if the inputs of the operator are outputs of previous applications.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_P43() throws Throwable {
    this.performObjectiveTest(Instance.P43, this.m_chain, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RY48P}
   * if the inputs of the operator all are identical.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_RY48P() throws Throwable {
    this.performObjectiveTest(Instance.RY48P, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RY48P}
   * if the inputs of the operator differ slightly.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_RY48P()
      throws Throwable {
    this.performObjectiveTest(Instance.RY48P, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RY48P}
   * if the inputs of the operator differ much.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_RY48P() throws Throwable {
    this.performObjectiveTest(Instance.RY48P, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RY48P}
   * if the inputs of the operator are outputs of previous applications.
   * 
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_RY48P() throws Throwable {
    this.performObjectiveTest(Instance.RY48P, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

}
