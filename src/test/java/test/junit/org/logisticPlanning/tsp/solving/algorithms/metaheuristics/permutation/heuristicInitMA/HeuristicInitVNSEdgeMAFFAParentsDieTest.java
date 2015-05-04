package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA;

import java.util.Random;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitVNSEdgeMAFFA;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the permutation-based heuristic init edge MA with VNS
 * mutation and FFA
 */
public class HeuristicInitVNSEdgeMAFFAParentsDieTest extends
TSPAlgorithmSymmetricTest {

  /** create */
  public HeuristicInitVNSEdgeMAFFAParentsDieTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitVNSEdgeMAFFA createAlgorithm() {
    HeuristicInitVNSEdgeMAFFA res;
    Random r;
    int mu, lambda;

    res = new HeuristicInitVNSEdgeMAFFA();
    res.setParentsSurvive(false);

    r = new Random();
    mu = (1 + r.nextInt(512));
    lambda = (mu + 1 + r.nextInt(512));
    res.setMu(mu);
    res.setLambda(lambda);

    return res;
  }
}
