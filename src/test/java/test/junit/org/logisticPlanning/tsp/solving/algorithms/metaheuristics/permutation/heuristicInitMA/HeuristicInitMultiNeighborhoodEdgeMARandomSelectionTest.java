package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA;

import java.util.Random;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitMultiNeighborhoodEdgeMARandomSelection;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the permutation-based heuristic init edge MA with
 * Multi-Neighborhood mutation and random selection
 */
public class HeuristicInitMultiNeighborhoodEdgeMARandomSelectionTest
    extends TSPAlgorithmSymmetricTest {

  /** create */
  public HeuristicInitMultiNeighborhoodEdgeMARandomSelectionTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitMultiNeighborhoodEdgeMARandomSelection createAlgorithm() {
    HeuristicInitMultiNeighborhoodEdgeMARandomSelection res;
    Random r;
    int mu, lambda;

    res = new HeuristicInitMultiNeighborhoodEdgeMARandomSelection();
    res.setParentsSurvive(true);

    r = new Random();
    mu = (1 + r.nextInt(512));
    lambda = (mu + 1 + r.nextInt(512));
    res.setMu(mu);
    res.setLambda(lambda);

    return res;
  }
}
