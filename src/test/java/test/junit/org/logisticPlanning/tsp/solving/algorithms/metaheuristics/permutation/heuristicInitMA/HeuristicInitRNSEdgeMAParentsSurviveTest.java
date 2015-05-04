package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA;

import java.util.Random;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitRNSEdgeMA;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the permutation-based heuristic init edge MA with RNS
 * mutation
 */
public class HeuristicInitRNSEdgeMAParentsSurviveTest extends
TSPAlgorithmSymmetricTest {

  /** create */
  public HeuristicInitRNSEdgeMAParentsSurviveTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitRNSEdgeMA createAlgorithm() {
    HeuristicInitRNSEdgeMA res;
    Random r;
    int mu, lambda;

    res = new HeuristicInitRNSEdgeMA();
    res.setParentsSurvive(true);

    r = new Random();
    mu = (1 + r.nextInt(512));
    lambda = (mu + 1 + r.nextInt(512));
    res.setMu(mu);
    res.setLambda(lambda);

    return res;
  }
}
