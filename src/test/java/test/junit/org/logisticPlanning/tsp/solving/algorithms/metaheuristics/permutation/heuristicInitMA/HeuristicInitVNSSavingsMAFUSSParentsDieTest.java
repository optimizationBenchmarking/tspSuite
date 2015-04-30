package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA;

import java.util.Random;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitVNSSavingsMAFUSS;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the permutation-based heuristic init savings MA with VNS
 * mutation and FUSS
 */
public class HeuristicInitVNSSavingsMAFUSSParentsDieTest extends
    TSPAlgorithmSymmetricTest {

  /** create */
  public HeuristicInitVNSSavingsMAFUSSParentsDieTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitVNSSavingsMAFUSS createAlgorithm() {
    HeuristicInitVNSSavingsMAFUSS res;
    Random r;
    int mu, lambda;

    res = new HeuristicInitVNSSavingsMAFUSS();
    res.setParentsSurvive(false);

    r = new Random();
    mu = (1 + r.nextInt(512));
    lambda = (mu + 1 + r.nextInt(512));
    res.setMu(mu);
    res.setLambda(lambda);

    return res;
  }
}
