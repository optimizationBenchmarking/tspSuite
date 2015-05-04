package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA;

import java.util.Random;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitMultiNeighborhoodEdgeMAFUSS;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the permutation-based heuristic init edge MA with
 * Multi-Neighborhood mutation and FUSS
 */
public class HeuristicInitMultiNeighborhoodEdgeMAFUSSParentsDieTest extends
TSPAlgorithmSymmetricTest {

  /** create */
  public HeuristicInitMultiNeighborhoodEdgeMAFUSSParentsDieTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitMultiNeighborhoodEdgeMAFUSS createAlgorithm() {
    HeuristicInitMultiNeighborhoodEdgeMAFUSS res;
    Random r;
    int mu, lambda;

    res = new HeuristicInitMultiNeighborhoodEdgeMAFUSS();
    res.setParentsSurvive(false);

    r = new Random();
    mu = (1 + r.nextInt(512));
    lambda = (mu + 1 + r.nextInt(512));
    res.setMu(mu);
    res.setLambda(lambda);

    return res;
  }
}
