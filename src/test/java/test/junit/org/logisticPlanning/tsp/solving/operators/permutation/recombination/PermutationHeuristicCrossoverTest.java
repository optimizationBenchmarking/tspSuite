package test.junit.org.logisticPlanning.tsp.solving.operators.permutation.recombination;

import org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationHeuristicCrossover;

import test.junit.org.logisticPlanning.tsp.solving.operators.permutation.PermutationOperatorTest;

/**
 * test the permutation heuristic recombination operator
 */
public class PermutationHeuristicCrossoverTest extends
    PermutationOperatorTest {

  /** instantiate */
  public PermutationHeuristicCrossoverTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationHeuristicCrossover createOperator() {
    return new PermutationHeuristicCrossover();
  }
}
