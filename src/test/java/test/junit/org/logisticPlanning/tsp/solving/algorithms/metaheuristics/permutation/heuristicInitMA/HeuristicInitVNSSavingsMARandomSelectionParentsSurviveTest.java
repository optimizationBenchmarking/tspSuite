package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA;

import java.util.Random;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitVNSSavingsMARandomSelection;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the permutation-based heuristic init savings MA with VNS
 * mutation and random selection
 */
public class HeuristicInitVNSSavingsMARandomSelectionParentsSurviveTest
    extends TSPAlgorithmSymmetricTest {

  /** create */
  public HeuristicInitVNSSavingsMARandomSelectionParentsSurviveTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitVNSSavingsMARandomSelection createAlgorithm() {
    HeuristicInitVNSSavingsMARandomSelection res;
    Random r;
    int mu, lambda;

    res = new HeuristicInitVNSSavingsMARandomSelection();
    res.setParentsSurvive(true);

    r = new Random();
    mu = (1 + r.nextInt(512));
    lambda = (mu + 1 + r.nextInt(512));
    res.setMu(mu);
    res.setLambda(lambda);

    return res;
  }
}
