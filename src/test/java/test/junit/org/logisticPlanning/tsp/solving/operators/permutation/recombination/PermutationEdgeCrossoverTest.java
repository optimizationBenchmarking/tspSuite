package test.junit.org.logisticPlanning.tsp.solving.operators.permutation.recombination;

import org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationEdgeCrossover;

import test.junit.org.logisticPlanning.tsp.solving.operators.permutation.PermutationOperatorTest;

/**
 * Test the permutation <a
 * href="https://en.wikipedia.org/wiki/Edge_recombination_operator">edge
 * recombination operator</a>
 */
public class PermutationEdgeCrossoverTest extends PermutationOperatorTest {

  /** instantiate */
  public PermutationEdgeCrossoverTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationEdgeCrossover createOperator() {
    return new PermutationEdgeCrossover();
  }
}
