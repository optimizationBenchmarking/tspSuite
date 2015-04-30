package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA;

import java.util.Random;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitRNSSavingsMARandomSelection;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the permutation-based heuristic init savings MA with RNS
 * mutation and random selection
 */
public class HeuristicInitRNSSavingsMARandomSelectionTest extends
    TSPAlgorithmSymmetricTest {

  /** create */
  public HeuristicInitRNSSavingsMARandomSelectionTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitRNSSavingsMARandomSelection createAlgorithm() {
    HeuristicInitRNSSavingsMARandomSelection res;
    Random r;
    int mu, lambda;

    res = new HeuristicInitRNSSavingsMARandomSelection();
    res.setParentsSurvive(true);

    r = new Random();
    mu = (1 + r.nextInt(512));
    lambda = (mu + 1 + r.nextInt(512));
    res.setMu(mu);
    res.setLambda(lambda);

    return res;
  }
}
