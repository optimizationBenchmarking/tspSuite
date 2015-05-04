package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA;

import java.util.Random;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitVNSSavingsMAFUSS;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the permutation-based heuristic init savings MA with VNS
 * mutation and FUSS
 */
public class HeuristicInitVNSSavingsMAFUSSParentsSurviveTest extends
TSPAlgorithmSymmetricTest {

  /** create */
  public HeuristicInitVNSSavingsMAFUSSParentsSurviveTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitVNSSavingsMAFUSS createAlgorithm() {
    HeuristicInitVNSSavingsMAFUSS res;
    Random r;
    int mu, lambda;

    res = new HeuristicInitVNSSavingsMAFUSS();
    res.setParentsSurvive(true);

    r = new Random();
    mu = (1 + r.nextInt(512));
    lambda = (mu + 1 + r.nextInt(512));
    res.setMu(mu);
    res.setLambda(lambda);

    return res;
  }
}
