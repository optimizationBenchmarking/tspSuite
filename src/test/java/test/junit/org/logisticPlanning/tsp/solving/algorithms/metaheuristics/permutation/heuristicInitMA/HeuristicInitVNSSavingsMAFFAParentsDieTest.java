package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA;

import java.util.Random;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitVNSSavingsMAFFA;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the permutation-based heuristic init savings MA with VNS
 * mutation and ffa
 */
public class HeuristicInitVNSSavingsMAFFAParentsDieTest extends
TSPAlgorithmSymmetricTest {

  /** create */
  public HeuristicInitVNSSavingsMAFFAParentsDieTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitVNSSavingsMAFFA createAlgorithm() {
    HeuristicInitVNSSavingsMAFFA res;
    Random r;
    int mu, lambda;

    res = new HeuristicInitVNSSavingsMAFFA();
    res.setParentsSurvive(false);

    r = new Random();
    mu = (1 + r.nextInt(512));
    lambda = (mu + 1 + r.nextInt(512));
    res.setMu(mu);
    res.setLambda(lambda);

    return res;
  }
}
