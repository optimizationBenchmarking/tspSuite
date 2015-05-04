package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA;

import java.util.Random;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitVNSSavingsMA;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the permutation-based heuristic init savings MA with VNS
 * mutation
 */
public class HeuristicInitVNSSavingsMAParentsDieTest extends
TSPAlgorithmSymmetricTest {

  /** create */
  public HeuristicInitVNSSavingsMAParentsDieTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitVNSSavingsMA createAlgorithm() {
    HeuristicInitVNSSavingsMA res;
    Random r;
    int mu, lambda;

    res = new HeuristicInitVNSSavingsMA();
    res.setParentsSurvive(false);

    r = new Random();
    mu = (1 + r.nextInt(512));
    lambda = (mu + 1 + r.nextInt(512));
    res.setMu(mu);
    res.setLambda(lambda);

    return res;
  }
}
