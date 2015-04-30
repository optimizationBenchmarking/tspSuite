package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA;

import java.util.Random;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitRNSEdgeMAFFA;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the permutation-based heuristic init edge MA with RNS
 * mutation and FFA
 */
public class HeuristicInitRNSEdgeMAFFAParentsDieTest extends
    TSPAlgorithmSymmetricTest {

  /** create */
  public HeuristicInitRNSEdgeMAFFAParentsDieTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitRNSEdgeMAFFA createAlgorithm() {
    HeuristicInitRNSEdgeMAFFA res;
    Random r;
    int mu, lambda;

    res = new HeuristicInitRNSEdgeMAFFA();
    res.setParentsSurvive(false);

    r = new Random();
    mu = (1 + r.nextInt(512));
    lambda = (mu + 1 + r.nextInt(512));
    res.setMu(mu);
    res.setLambda(lambda);

    return res;
  }
}
