package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA;

import java.util.Random;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitVNSEdgeMARandomSelection;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the permutation-based heuristic init edge MA with VNS
 * mutation and random selection
 */
public class HeuristicInitVNSEdgeMARandomSelectionParentsDieTest extends
TSPAlgorithmSymmetricTest {

  /** create */
  public HeuristicInitVNSEdgeMARandomSelectionParentsDieTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitVNSEdgeMARandomSelection createAlgorithm() {
    HeuristicInitVNSEdgeMARandomSelection res;
    Random r;
    int mu, lambda;

    res = new HeuristicInitVNSEdgeMARandomSelection();
    res.setParentsSurvive(false);

    r = new Random();
    mu = (1 + r.nextInt(512));
    lambda = (mu + 1 + r.nextInt(512));
    res.setMu(mu);
    res.setLambda(lambda);

    return res;
  }
}
