package test.junit.org.logisticPlanning.tsp.solving.operators;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.operators.NullaryOperator;
import org.logisticPlanning.tsp.solving.operators.Operator;
import org.logisticPlanning.tsp.solving.operators.permutation.creation.PermutationCreateUniform;

import test.junit.org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunctionValidator;

/**
 * Test a search operator for symmetric test cases only
 *
 * @param <P>
 *          the product type
 */
@Ignore
public class SymmetricOperatorTest<P> extends ObjectiveFunctionValidator {

  /** the operator must not be null */
  static final String OPERATOR_NOT_NULL = "Operator must not be null.";//$NON-NLS-1$

  /** the operator */
  private Operator<P> m_op;

  /** the test for different elements */
  final _OperatorTestAllDifferent<P> m_diff;
  /** the test for same elements */
  final _OperatorTestAllSame<P> m_same;
  /** the test for similar elements */
  final _OperatorTestAllSimilar<P> m_similar;
  /** the test for chained applications */
  final _OperatorTestChaining<P> m_chain;

  /** instantiate */
  protected SymmetricOperatorTest() {
    super();

    this.m_diff = new _OperatorTestAllDifferent<>(this);
    this.m_same = new _OperatorTestAllSame<>(this);
    this.m_similar = new _OperatorTestAllSimilar<>(this);
    this.m_chain = new _OperatorTestChaining<>(this);
  }

  /**
   * Create the operator to be used
   *
   * @return the operator to be used
   */
  @SuppressWarnings("unchecked")
  protected Operator<P> createOperator() {
    return ((Operator<P>) (NullaryOperator.DUMMY));
  }

  /**
   * get the operator
   *
   * @return the operator
   */
  protected Operator<P> getOperator() {
    Operator<P> op;

    op = this.m_op;
    if (op == null) {
      this.m_op = op = this.createOperator();
    }

    return op;
  }

  /**
   * create a random instance
   *
   * @param dest
   *          the destination record
   * @param f
   *          the objective function
   */
  @SuppressWarnings("unchecked")
  protected void createRandomInstance(final Individual<P> dest,
      final ObjectiveFunction f) {
    dest.solution = ((P) (PermutationCreateUniform.create(f.n(),//
        f.getRandom())));
    dest.tourLength = (f.evaluate((int[]) (dest.solution)));
  }

  /**
   * Create a slightly modified copy of a given input
   *
   * @param f
   *          the objective function
   * @param dest
   *          the output
   * @param input
   *          the input instance
   */
  protected void slightlyModify(final Individual<P> dest,
      final ObjectiveFunction f, final Individual<P> input) {
    dest.assign(input);
  }

  /** Check that the operator is not null */
  @Test(timeout = 10800000)
  public void testOperatorNotNull() {
    Assert.assertNotNull("Operator must not be null.", //$NON-NLS-1$
        this.getOperator());
  }

  /**
   * process a newly generated solution
   *
   * @param ind
   *          the individual
   * @param inst
   *          the instance
   * @param f
   *          the objective function
   * @throws Throwable
   *           if something fails
   */
  protected void processNewSolution(final Individual<P> ind,
      final Instance inst, final ObjectiveFunction f) throws Throwable {
    //
  }

  /**
   * check the result of an operator's application
   *
   * @param result
   *          the result
   * @param f
   *          the objective function
   */
  protected void checkOperatorResult(final P result,
      final ObjectiveFunction f) {
    Assert.assertNotNull("Operator result must not be null.", result); //$NON-NLS-1$
  }

  /**
   * check the result of an operator's application
   *
   * @param result
   *          the result
   * @param f
   *          the objective function
   */
  protected void checkOperatorResult(final Individual<P> result,
      final ObjectiveFunction f) {
    Assert.assertNotNull("Individual record must not be null.", result); //$NON-NLS-1$
    Assert.assertNotNull(
        "Operator result must not be null.", result.solution); //$NON-NLS-1$
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#ATT48}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_ATT48() throws Throwable {
    this.performObjectiveTest(Instance.ATT48, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#ATT48}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_ATT48()
      throws Throwable {
    this.performObjectiveTest(Instance.ATT48, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#ATT48}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_ATT48() throws Throwable {
    this.performObjectiveTest(Instance.ATT48, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#ATT48}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_ATT48() throws Throwable {
    this.performObjectiveTest(Instance.ATT48, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BAYG29}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_BAYG29() throws Throwable {
    this.performObjectiveTest(Instance.BAYG29, this.m_same,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BAYG29}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_BAYG29()
      throws Throwable {
    this.performObjectiveTest(Instance.BAYG29, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BAYG29}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_BAYG29()
      throws Throwable {
    this.performObjectiveTest(Instance.BAYG29, this.m_diff,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BAYG29}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_BAYG29() throws Throwable {
    this.performObjectiveTest(Instance.BAYG29, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BAYS29}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_BAYS29() throws Throwable {
    this.performObjectiveTest(Instance.BAYS29, this.m_same,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BAYS29}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_BAYS29()
      throws Throwable {
    this.performObjectiveTest(Instance.BAYS29, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BAYS29}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_BAYS29()
      throws Throwable {
    this.performObjectiveTest(Instance.BAYS29, this.m_diff,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BAYS29}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_BAYS29() throws Throwable {
    this.performObjectiveTest(Instance.BAYS29, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BERLIN52}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_BERLIN52() throws Throwable {
    this.performObjectiveTest(Instance.BERLIN52, this.m_same,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BERLIN52}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_BERLIN52()
      throws Throwable {
    this.performObjectiveTest(Instance.BERLIN52, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BERLIN52}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_BERLIN52()
      throws Throwable {
    this.performObjectiveTest(Instance.BERLIN52, this.m_diff,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BERLIN52}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_BERLIN52() throws Throwable {
    this.performObjectiveTest(Instance.BERLIN52, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BRAZIL58}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_BRAZIL58() throws Throwable {
    this.performObjectiveTest(Instance.BRAZIL58, this.m_same,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BRAZIL58}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_BRAZIL58()
      throws Throwable {
    this.performObjectiveTest(Instance.BRAZIL58, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BRAZIL58}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_BRAZIL58()
      throws Throwable {
    this.performObjectiveTest(Instance.BRAZIL58, this.m_diff,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BRAZIL58}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_BRAZIL58() throws Throwable {
    this.performObjectiveTest(Instance.BRAZIL58, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BRG180}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_BRG180() throws Throwable {
    this.performObjectiveTest(Instance.BRG180, this.m_same,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BRG180}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_BRG180()
      throws Throwable {
    this.performObjectiveTest(Instance.BRG180, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BRG180}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_BRG180()
      throws Throwable {
    this.performObjectiveTest(Instance.BRG180, this.m_diff,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BRG180}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_BRG180() throws Throwable {
    this.performObjectiveTest(Instance.BRG180, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BURMA14}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_BURMA14() throws Throwable {
    this.performObjectiveTest(Instance.BURMA14, this.m_same,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BURMA14}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_BURMA14()
      throws Throwable {
    this.performObjectiveTest(Instance.BURMA14, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BURMA14}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_BURMA14()
      throws Throwable {
    this.performObjectiveTest(Instance.BURMA14, this.m_diff,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BURMA14}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_BURMA14() throws Throwable {
    this.performObjectiveTest(Instance.BURMA14, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#CH130}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_CH130() throws Throwable {
    this.performObjectiveTest(Instance.CH130, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#CH130}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_CH130()
      throws Throwable {
    this.performObjectiveTest(Instance.CH130, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#CH130}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_CH130() throws Throwable {
    this.performObjectiveTest(Instance.CH130, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#CH130}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_CH130() throws Throwable {
    this.performObjectiveTest(Instance.CH130, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#CH150}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_CH150() throws Throwable {
    this.performObjectiveTest(Instance.CH150, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#CH150}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_CH150()
      throws Throwable {
    this.performObjectiveTest(Instance.CH150, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#CH150}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_CH150() throws Throwable {
    this.performObjectiveTest(Instance.CH150, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#CH150}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_CH150() throws Throwable {
    this.performObjectiveTest(Instance.CH150, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#D198}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_D198() throws Throwable {
    this.performObjectiveTest(Instance.D198, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#D198}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_D198()
      throws Throwable {
    this.performObjectiveTest(Instance.D198, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#D198}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_D198() throws Throwable {
    this.performObjectiveTest(Instance.D198, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#D198}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_D198() throws Throwable {
    this.performObjectiveTest(Instance.D198, this.m_chain, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#EIL51}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_EIL51() throws Throwable {
    this.performObjectiveTest(Instance.EIL51, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#EIL51}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_EIL51()
      throws Throwable {
    this.performObjectiveTest(Instance.EIL51, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#EIL51}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_EIL51() throws Throwable {
    this.performObjectiveTest(Instance.EIL51, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#EIL51}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_EIL51() throws Throwable {
    this.performObjectiveTest(Instance.EIL51, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#EIL76}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_EIL76() throws Throwable {
    this.performObjectiveTest(Instance.EIL76, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#EIL76}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_EIL76()
      throws Throwable {
    this.performObjectiveTest(Instance.EIL76, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#EIL76}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_EIL76() throws Throwable {
    this.performObjectiveTest(Instance.EIL76, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#EIL76}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_EIL76() throws Throwable {
    this.performObjectiveTest(Instance.EIL76, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#EIL101}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_EIL101() throws Throwable {
    this.performObjectiveTest(Instance.EIL101, this.m_same,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#EIL101}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_EIL101()
      throws Throwable {
    this.performObjectiveTest(Instance.EIL101, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#EIL101}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_EIL101()
      throws Throwable {
    this.performObjectiveTest(Instance.EIL101, this.m_diff,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#EIL101}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_EIL101() throws Throwable {
    this.performObjectiveTest(Instance.EIL101, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FRI26}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_FRI26() throws Throwable {
    this.performObjectiveTest(Instance.FRI26, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FRI26}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_FRI26()
      throws Throwable {
    this.performObjectiveTest(Instance.FRI26, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FRI26}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_FRI26() throws Throwable {
    this.performObjectiveTest(Instance.FRI26, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FRI26}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_FRI26() throws Throwable {
    this.performObjectiveTest(Instance.FRI26, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR17}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_GR17() throws Throwable {
    this.performObjectiveTest(Instance.GR17, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR17}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_GR17()
      throws Throwable {
    this.performObjectiveTest(Instance.GR17, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR17}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_GR17() throws Throwable {
    this.performObjectiveTest(Instance.GR17, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR17}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_GR17() throws Throwable {
    this.performObjectiveTest(Instance.GR17, this.m_chain, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR21}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_GR21() throws Throwable {
    this.performObjectiveTest(Instance.GR21, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR21}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_GR21()
      throws Throwable {
    this.performObjectiveTest(Instance.GR21, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR21}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_GR21() throws Throwable {
    this.performObjectiveTest(Instance.GR21, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR21}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_GR21() throws Throwable {
    this.performObjectiveTest(Instance.GR21, this.m_chain, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR24}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_GR24() throws Throwable {
    this.performObjectiveTest(Instance.GR24, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR24}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_GR24()
      throws Throwable {
    this.performObjectiveTest(Instance.GR24, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR24}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_GR24() throws Throwable {
    this.performObjectiveTest(Instance.GR24, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR24}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_GR24() throws Throwable {
    this.performObjectiveTest(Instance.GR24, this.m_chain, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR48}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_GR48() throws Throwable {
    this.performObjectiveTest(Instance.GR48, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR48}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_GR48()
      throws Throwable {
    this.performObjectiveTest(Instance.GR48, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR48}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_GR48() throws Throwable {
    this.performObjectiveTest(Instance.GR48, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR48}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_GR48() throws Throwable {
    this.performObjectiveTest(Instance.GR48, this.m_chain, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR96}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_GR96() throws Throwable {
    this.performObjectiveTest(Instance.GR96, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR96}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_GR96()
      throws Throwable {
    this.performObjectiveTest(Instance.GR96, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR96}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_GR96() throws Throwable {
    this.performObjectiveTest(Instance.GR96, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR96}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_GR96() throws Throwable {
    this.performObjectiveTest(Instance.GR96, this.m_chain, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR120}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_GR120() throws Throwable {
    this.performObjectiveTest(Instance.GR120, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR120}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_GR120()
      throws Throwable {
    this.performObjectiveTest(Instance.GR120, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR120}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_GR120() throws Throwable {
    this.performObjectiveTest(Instance.GR120, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR120}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_GR120() throws Throwable {
    this.performObjectiveTest(Instance.GR120, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR137}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_GR137() throws Throwable {
    this.performObjectiveTest(Instance.GR137, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR137}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_GR137()
      throws Throwable {
    this.performObjectiveTest(Instance.GR137, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR137}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_GR137() throws Throwable {
    this.performObjectiveTest(Instance.GR137, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR137}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_GR137() throws Throwable {
    this.performObjectiveTest(Instance.GR137, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#HK48}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_HK48() throws Throwable {
    this.performObjectiveTest(Instance.HK48, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#HK48}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_HK48()
      throws Throwable {
    this.performObjectiveTest(Instance.HK48, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#HK48}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_HK48() throws Throwable {
    this.performObjectiveTest(Instance.HK48, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#HK48}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_HK48() throws Throwable {
    this.performObjectiveTest(Instance.HK48, this.m_chain, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROA100}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_KROA100() throws Throwable {
    this.performObjectiveTest(Instance.KROA100, this.m_same,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROA100}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_KROA100()
      throws Throwable {
    this.performObjectiveTest(Instance.KROA100, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROA100}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_KROA100()
      throws Throwable {
    this.performObjectiveTest(Instance.KROA100, this.m_diff,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROA100}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_KROA100() throws Throwable {
    this.performObjectiveTest(Instance.KROA100, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROB100}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_KROB100() throws Throwable {
    this.performObjectiveTest(Instance.KROB100, this.m_same,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROB100}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_KROB100()
      throws Throwable {
    this.performObjectiveTest(Instance.KROB100, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROB100}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_KROB100()
      throws Throwable {
    this.performObjectiveTest(Instance.KROB100, this.m_diff,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROB100}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_KROB100() throws Throwable {
    this.performObjectiveTest(Instance.KROB100, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROC100}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_KROC100() throws Throwable {
    this.performObjectiveTest(Instance.KROC100, this.m_same,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROC100}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_KROC100()
      throws Throwable {
    this.performObjectiveTest(Instance.KROC100, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROC100}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_KROC100()
      throws Throwable {
    this.performObjectiveTest(Instance.KROC100, this.m_diff,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROC100}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_KROC100() throws Throwable {
    this.performObjectiveTest(Instance.KROC100, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROD100}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_KROD100() throws Throwable {
    this.performObjectiveTest(Instance.KROD100, this.m_same,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROD100}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_KROD100()
      throws Throwable {
    this.performObjectiveTest(Instance.KROD100, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROD100}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_KROD100()
      throws Throwable {
    this.performObjectiveTest(Instance.KROD100, this.m_diff,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROD100}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_KROD100() throws Throwable {
    this.performObjectiveTest(Instance.KROD100, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROE100}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_KROE100() throws Throwable {
    this.performObjectiveTest(Instance.KROE100, this.m_same,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROE100}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_KROE100()
      throws Throwable {
    this.performObjectiveTest(Instance.KROE100, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROE100}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_KROE100()
      throws Throwable {
    this.performObjectiveTest(Instance.KROE100, this.m_diff,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROE100}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_KROE100() throws Throwable {
    this.performObjectiveTest(Instance.KROE100, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROA150}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_KROA150() throws Throwable {
    this.performObjectiveTest(Instance.KROA150, this.m_same,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROA150}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_KROA150()
      throws Throwable {
    this.performObjectiveTest(Instance.KROA150, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROA150}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_KROA150()
      throws Throwable {
    this.performObjectiveTest(Instance.KROA150, this.m_diff,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROA150}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_KROA150() throws Throwable {
    this.performObjectiveTest(Instance.KROA150, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROB150}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_KROB150() throws Throwable {
    this.performObjectiveTest(Instance.KROB150, this.m_same,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROB150}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_KROB150()
      throws Throwable {
    this.performObjectiveTest(Instance.KROB150, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROB150}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_KROB150()
      throws Throwable {
    this.performObjectiveTest(Instance.KROB150, this.m_diff,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROB150}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_KROB150() throws Throwable {
    this.performObjectiveTest(Instance.KROB150, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROA200}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_KROA200() throws Throwable {
    this.performObjectiveTest(Instance.KROA200, this.m_same,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROA200}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_KROA200()
      throws Throwable {
    this.performObjectiveTest(Instance.KROA200, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROA200}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_KROA200()
      throws Throwable {
    this.performObjectiveTest(Instance.KROA200, this.m_diff,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROA200}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_KROA200() throws Throwable {
    this.performObjectiveTest(Instance.KROA200, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROB200}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_KROB200() throws Throwable {
    this.performObjectiveTest(Instance.KROB200, this.m_same,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROB200}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_KROB200()
      throws Throwable {
    this.performObjectiveTest(Instance.KROB200, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROB200}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_KROB200()
      throws Throwable {
    this.performObjectiveTest(Instance.KROB200, this.m_diff,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROB200}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_KROB200() throws Throwable {
    this.performObjectiveTest(Instance.KROB200, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#LIN105}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_LIN105() throws Throwable {
    this.performObjectiveTest(Instance.LIN105, this.m_same,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#LIN105}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_LIN105()
      throws Throwable {
    this.performObjectiveTest(Instance.LIN105, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#LIN105}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_LIN105()
      throws Throwable {
    this.performObjectiveTest(Instance.LIN105, this.m_diff,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#LIN105}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_LIN105() throws Throwable {
    this.performObjectiveTest(Instance.LIN105, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR76}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_PR76() throws Throwable {
    this.performObjectiveTest(Instance.PR76, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR76}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_PR76()
      throws Throwable {
    this.performObjectiveTest(Instance.PR76, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR76}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_PR76() throws Throwable {
    this.performObjectiveTest(Instance.PR76, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR76}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_PR76() throws Throwable {
    this.performObjectiveTest(Instance.PR76, this.m_chain, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR107}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_PR107() throws Throwable {
    this.performObjectiveTest(Instance.PR107, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR107}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_PR107()
      throws Throwable {
    this.performObjectiveTest(Instance.PR107, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR107}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_PR107() throws Throwable {
    this.performObjectiveTest(Instance.PR107, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR107}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_PR107() throws Throwable {
    this.performObjectiveTest(Instance.PR107, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR124}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_PR124() throws Throwable {
    this.performObjectiveTest(Instance.PR124, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR124}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_PR124()
      throws Throwable {
    this.performObjectiveTest(Instance.PR124, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR124}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_PR124() throws Throwable {
    this.performObjectiveTest(Instance.PR124, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR124}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_PR124() throws Throwable {
    this.performObjectiveTest(Instance.PR124, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR136}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_PR136() throws Throwable {
    this.performObjectiveTest(Instance.PR136, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR136}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_PR136()
      throws Throwable {
    this.performObjectiveTest(Instance.PR136, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR136}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_PR136() throws Throwable {
    this.performObjectiveTest(Instance.PR136, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR136}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_PR136() throws Throwable {
    this.performObjectiveTest(Instance.PR136, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR144}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_PR144() throws Throwable {
    this.performObjectiveTest(Instance.PR144, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR144}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_PR144()
      throws Throwable {
    this.performObjectiveTest(Instance.PR144, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR144}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_PR144() throws Throwable {
    this.performObjectiveTest(Instance.PR144, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR144}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_PR144() throws Throwable {
    this.performObjectiveTest(Instance.PR144, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR152}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_PR152() throws Throwable {
    this.performObjectiveTest(Instance.PR152, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR152}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_PR152()
      throws Throwable {
    this.performObjectiveTest(Instance.PR152, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR152}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_PR152() throws Throwable {
    this.performObjectiveTest(Instance.PR152, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR152}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_PR152() throws Throwable {
    this.performObjectiveTest(Instance.PR152, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RAT99}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_RAT99() throws Throwable {
    this.performObjectiveTest(Instance.RAT99, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RAT99}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_RAT99()
      throws Throwable {
    this.performObjectiveTest(Instance.RAT99, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RAT99}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_RAT99() throws Throwable {
    this.performObjectiveTest(Instance.RAT99, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RAT99}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_RAT99() throws Throwable {
    this.performObjectiveTest(Instance.RAT99, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RAT195}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_RAT195() throws Throwable {
    this.performObjectiveTest(Instance.RAT195, this.m_same,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RAT195}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_RAT195()
      throws Throwable {
    this.performObjectiveTest(Instance.RAT195, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RAT195}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_RAT195()
      throws Throwable {
    this.performObjectiveTest(Instance.RAT195, this.m_diff,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RAT195}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_RAT195() throws Throwable {
    this.performObjectiveTest(Instance.RAT195, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RD100}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_RD100() throws Throwable {
    this.performObjectiveTest(Instance.RD100, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RD100}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_RD100()
      throws Throwable {
    this.performObjectiveTest(Instance.RD100, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RD100}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_RD100() throws Throwable {
    this.performObjectiveTest(Instance.RD100, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RD100}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_RD100() throws Throwable {
    this.performObjectiveTest(Instance.RD100, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#SI175}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_SI175() throws Throwable {
    this.performObjectiveTest(Instance.SI175, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#SI175}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_SI175()
      throws Throwable {
    this.performObjectiveTest(Instance.SI175, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#SI175}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_SI175() throws Throwable {
    this.performObjectiveTest(Instance.SI175, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#SI175}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_SI175() throws Throwable {
    this.performObjectiveTest(Instance.SI175, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#ST70}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_ST70() throws Throwable {
    this.performObjectiveTest(Instance.ST70, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#ST70}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_ST70()
      throws Throwable {
    this.performObjectiveTest(Instance.ST70, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#ST70}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_ST70() throws Throwable {
    this.performObjectiveTest(Instance.ST70, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#ST70}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_ST70() throws Throwable {
    this.performObjectiveTest(Instance.ST70, this.m_chain, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#SWISS42}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_SWISS42() throws Throwable {
    this.performObjectiveTest(Instance.SWISS42, this.m_same,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#SWISS42}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_SWISS42()
      throws Throwable {
    this.performObjectiveTest(Instance.SWISS42, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#SWISS42}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_SWISS42()
      throws Throwable {
    this.performObjectiveTest(Instance.SWISS42, this.m_diff,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#SWISS42}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_SWISS42() throws Throwable {
    this.performObjectiveTest(Instance.SWISS42, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#U159}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_U159() throws Throwable {
    this.performObjectiveTest(Instance.U159, this.m_same, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#U159}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_U159()
      throws Throwable {
    this.performObjectiveTest(Instance.U159, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#U159}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_U159() throws Throwable {
    this.performObjectiveTest(Instance.U159, this.m_diff, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#U159}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_U159() throws Throwable {
    this.performObjectiveTest(Instance.U159, this.m_chain, Long.MAX_VALUE,
        30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#ULYSSES16}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_ULYSSES16()
      throws Throwable {
    this.performObjectiveTest(Instance.ULYSSES16, this.m_same,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#ULYSSES16}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_ULYSSES16()
      throws Throwable {
    this.performObjectiveTest(Instance.ULYSSES16, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#ULYSSES16}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_ULYSSES16()
      throws Throwable {
    this.performObjectiveTest(Instance.ULYSSES16, this.m_diff,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#ULYSSES16}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_ULYSSES16()
      throws Throwable {
    this.performObjectiveTest(Instance.ULYSSES16, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#ULYSSES22}
   * if the inputs of the operator all are identical.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsAreSame_ULYSSES22()
      throws Throwable {
    this.performObjectiveTest(Instance.ULYSSES22, this.m_same,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#ULYSSES22}
   * if the inputs of the operator differ slightly.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferSlightly_ULYSSES22()
      throws Throwable {
    this.performObjectiveTest(Instance.ULYSSES22, this.m_similar,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#ULYSSES22}
   * if the inputs of the operator differ much.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsDifferMuch_ULYSSES22()
      throws Throwable {
    this.performObjectiveTest(Instance.ULYSSES22, this.m_diff,
        Long.MAX_VALUE, 30000, 100, false);
  }

  /**
   * Test whether solving works correctly on the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#ULYSSES22}
   * if the inputs of the operator are outputs of previous applications.
   *
   * @throws Throwable
   *           if io fails
   */
  @Test(timeout = 10800000)
  public final void testOperator_InputsChained_ULYSSES22()
      throws Throwable {
    this.performObjectiveTest(Instance.ULYSSES22, this.m_chain,
        Long.MAX_VALUE, 30000, 100, false);
  }

}
