package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA;

import java.util.Random;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitMultiNeighborhoodSavingsMAFUSS;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the permutation-based heuristic init savings MA with
 * Multi-Neighborhood mutation and FUSS
 */
public class HeuristicInitMultiNeighborhoodSavingsMAFUSSParentsDieTest
extends TSPAlgorithmSymmetricTest {

  /** create */
  public HeuristicInitMultiNeighborhoodSavingsMAFUSSParentsDieTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitMultiNeighborhoodSavingsMAFUSS createAlgorithm() {
    HeuristicInitMultiNeighborhoodSavingsMAFUSS res;
    Random r;
    int mu, lambda;

    res = new HeuristicInitMultiNeighborhoodSavingsMAFUSS();
    res.setParentsSurvive(false);

    r = new Random();
    mu = (1 + r.nextInt(512));
    lambda = (mu + 1 + r.nextInt(512));
    res.setMu(mu);
    res.setLambda(lambda);

    return res;
  }
}
