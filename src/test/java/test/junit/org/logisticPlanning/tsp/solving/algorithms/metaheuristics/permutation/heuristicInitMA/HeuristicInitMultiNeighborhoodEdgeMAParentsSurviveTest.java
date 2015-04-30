package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA;

import java.util.Random;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitMultiNeighborhoodEdgeMA;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the permutation-based heuristic init edge MA with
 * Multi-Neighborhood mutation
 */
public class HeuristicInitMultiNeighborhoodEdgeMAParentsSurviveTest extends
    TSPAlgorithmSymmetricTest {

  /** create */
  public HeuristicInitMultiNeighborhoodEdgeMAParentsSurviveTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitMultiNeighborhoodEdgeMA createAlgorithm() {
    HeuristicInitMultiNeighborhoodEdgeMA res;
    Random r;
    int mu, lambda;

    res = new HeuristicInitMultiNeighborhoodEdgeMA();
    res.setParentsSurvive(true);

    r = new Random();
    mu = (1 + r.nextInt(512));
    lambda = (mu + 1 + r.nextInt(512));
    res.setMu(mu);
    res.setLambda(lambda);

    return res;
  }
}
