package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA;

import java.util.Random;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitRNSSavingsMAFUSS;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the permutation-based heuristic init savings MA with RNS
 * mutation and FUSS
 */
public class HeuristicInitRNSSavingsMAFUSSParentsSurviveTest extends
    TSPAlgorithmSymmetricTest {

  /** create */
  public HeuristicInitRNSSavingsMAFUSSParentsSurviveTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitRNSSavingsMAFUSS createAlgorithm() {
    HeuristicInitRNSSavingsMAFUSS res;
    Random r;
    int mu, lambda;

    res = new HeuristicInitRNSSavingsMAFUSS();
    res.setParentsSurvive(true);

    r = new Random();
    mu = (1 + r.nextInt(512));
    lambda = (mu + 1 + r.nextInt(512));
    res.setMu(mu);
    res.setLambda(lambda);

    return res;
  }
}
