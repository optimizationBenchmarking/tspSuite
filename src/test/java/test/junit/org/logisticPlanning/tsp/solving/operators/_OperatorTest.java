package test.junit.org.logisticPlanning.tsp.solving.operators;

import org.junit.Assert;
import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.Benchmark;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.operators.Operator;

import test.junit.org.logisticPlanning.tsp.benchmarking.objective.ObjectiveTest;

/**
 * the internal class to test an operator's behavior
 *
 * @param <P>
 *          the product type
 */
class _OperatorTest<P> extends ObjectiveTest {

  /** the owner */
  final SymmetricOperatorTest<P> m_owner;

  /** the parameters */
  Individual<P>[] m_params;

  /** the destination individual */
  Individual<P> m_dest;

  /**
   * instantiate
   *
   * @param owner
   *          the owner
   */
  _OperatorTest(final SymmetricOperatorTest<P> owner) {
    super();
    this.m_owner = owner;
    this.m_dest = new Individual<>();
  }

  /** {@inheritDoc} */
  @Override
  @SuppressWarnings("unchecked")
  public void beforeTest(final Instance inst, final Benchmark bm,
      final ObjectiveFunction f) throws Throwable {
    Operator<P> op;
    Individual<P>[] p;
    int i;

    op = this.m_owner.getOperator();
    Assert.assertNotNull(SymmetricOperatorTest.OPERATOR_NOT_NULL, op);

    op.beginRun(f);

    i = op.arity();
    p = this.m_params;
    if ((p == null) || (p.length != i)) {
      this.m_params = p = new Individual[i];
      for (; (--i) >= 0;) {
        p[i] = new Individual<>();
      }
    } else {
      for (; (--i) >= 0;) {
        p[i].clear();
      }
    }
    this.m_dest.clear();
  }

  /** {@inheritDoc} */
  @Override
  public void afterTest(final Instance inst, final Benchmark bm,
      final ObjectiveFunction f) throws Throwable {

    this.m_owner.getOperator().endRun(f);

    for (final Individual<P> p : this.m_params) {
      p.clear();
    }
    this.m_dest.clear();
  }

  /** {@inheritDoc} */
  @Override
  public void run(final Instance inst, final Benchmark bm,
      final ObjectiveFunction f) throws Throwable {
    Operator<P> op;

    op = this.m_owner.getOperator();
    Assert.assertNotNull(SymmetricOperatorTest.OPERATOR_NOT_NULL, op);

    this.m_dest.clear();
    op.apply(this.m_dest, f, this.m_params);

    this.m_owner.checkOperatorResult(this.m_dest, f);
  }
}
