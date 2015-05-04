package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA;

import java.util.Random;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitRNSEdgeMAFUSS;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the permutation-based heuristic init edge MA with RNS
 * mutation and FUSS
 */
public class HeuristicInitRNSEdgeMAFUSSParentsDieTest extends
TSPAlgorithmSymmetricTest {

  /** create */
  public HeuristicInitRNSEdgeMAFUSSParentsDieTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitRNSEdgeMAFUSS createAlgorithm() {
    HeuristicInitRNSEdgeMAFUSS res;
    Random r;
    int mu, lambda;

    res = new HeuristicInitRNSEdgeMAFUSS();
    res.setParentsSurvive(false);

    r = new Random();
    mu = (1 + r.nextInt(512));
    lambda = (mu + 1 + r.nextInt(512));
    res.setMu(mu);
    res.setLambda(lambda);

    return res;
  }
}
