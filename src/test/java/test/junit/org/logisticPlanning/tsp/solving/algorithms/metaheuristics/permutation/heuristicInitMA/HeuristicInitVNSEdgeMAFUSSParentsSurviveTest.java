package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA;

import java.util.Random;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitVNSEdgeMAFUSS;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the permutation-based heuristic init edge MA with VNS
 * mutation and FUSS
 */
public class HeuristicInitVNSEdgeMAFUSSParentsSurviveTest extends
TSPAlgorithmSymmetricTest {

  /** create */
  public HeuristicInitVNSEdgeMAFUSSParentsSurviveTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitVNSEdgeMAFUSS createAlgorithm() {
    HeuristicInitVNSEdgeMAFUSS res;
    Random r;
    int mu, lambda;

    res = new HeuristicInitVNSEdgeMAFUSS();
    res.setParentsSurvive(true);

    r = new Random();
    mu = (1 + r.nextInt(512));
    lambda = (mu + 1 + r.nextInt(512));
    res.setMu(mu);
    res.setLambda(lambda);

    return res;
  }
}
