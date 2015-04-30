package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA;

import java.util.Random;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitMultiNeighborhoodSavingsMAFFA;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the permutation-based heuristic init savings MA with
 * Multi-Neighborhood mutation and FFA
 */
public class HeuristicInitMultiNeighborhoodSavingsMAFFAParentsDieTest
    extends TSPAlgorithmSymmetricTest {

  /** create */
  public HeuristicInitMultiNeighborhoodSavingsMAFFAParentsDieTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitMultiNeighborhoodSavingsMAFFA createAlgorithm() {
    HeuristicInitMultiNeighborhoodSavingsMAFFA res;
    Random r;
    int mu, lambda;

    res = new HeuristicInitMultiNeighborhoodSavingsMAFFA();
    res.setParentsSurvive(false);

    r = new Random();
    mu = (1 + r.nextInt(512));
    lambda = (mu + 1 + r.nextInt(512));
    res.setMu(mu);
    res.setLambda(lambda);

    return res;
  }
}
