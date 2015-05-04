package test.junit.org.logisticPlanning.tsp.solving.operators.permutation;

import org.junit.Assert;
import org.junit.Ignore;
import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.operators.permutation.creation.PermutationCreateUniform;

import test.junit.org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunctionValidator;
import test.junit.org.logisticPlanning.tsp.solving.operators.SymmetricOperatorTest;

/**
 * Test a permutation-base search operator.
 */
@Ignore
public class PermutationOperatorTest extends SymmetricOperatorTest<int[]> {

  /** instantiate */
  protected PermutationOperatorTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected void createRandomInstance(final Individual<int[]> dest,
      final ObjectiveFunction f) {
    dest.solution = PermutationCreateUniform.create(f.n(), f.getRandom());

    f.beginRun(this.m_dummy);
    try {
      dest.tourLength = f.evaluate(dest.solution);
    } finally {
      f.endRun();
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void checkOperatorResult(final Individual<int[]> result,
      final ObjectiveFunction f) {
    long l;

    super.checkOperatorResult(result, f);

    l = f.evaluate(result.solution);
    if (result.tourLength != Long.MAX_VALUE) {
      Assert
          .assertEquals(//
              "The operator computed a tour length, but it is different from the length computed by the objective function.", //$NON-NLS-1$
              result.tourLength, l);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void processNewSolution(final Individual<int[]> ind,
      final Instance inst, final ObjectiveFunction f) throws Throwable {
    f.beginRun(this.m_dummy);
    try {
      this.validateObjectiveFunctionState(inst, f,
          ObjectiveFunctionValidator.STATE_NOTHING);
      ind.tourLength = f.evaluate(ind.solution);
      this.validateObjectiveFunctionState(inst, f,
          ObjectiveFunctionValidator.STATE_HAS_FINISHED_RUN);
    } finally {
      f.endRun();
    }
  }
}
