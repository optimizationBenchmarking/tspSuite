package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA;

import java.util.Random;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitRNSEdgeMARandomSelection;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the permutation-based heuristic init edge MA with RNS
 * mutation and random selection
 */
public class HeuristicInitRNSEdgeMARandomSelectionTest extends
TSPAlgorithmSymmetricTest {

  /** create */
  public HeuristicInitRNSEdgeMARandomSelectionTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitRNSEdgeMARandomSelection createAlgorithm() {
    HeuristicInitRNSEdgeMARandomSelection res;
    Random r;
    int mu, lambda;

    res = new HeuristicInitRNSEdgeMARandomSelection();
    res.setParentsSurvive(true);

    r = new Random();
    mu = (1 + r.nextInt(512));
    lambda = (mu + 1 + r.nextInt(512));
    res.setMu(mu);
    res.setLambda(lambda);

    return res;
  }
}
