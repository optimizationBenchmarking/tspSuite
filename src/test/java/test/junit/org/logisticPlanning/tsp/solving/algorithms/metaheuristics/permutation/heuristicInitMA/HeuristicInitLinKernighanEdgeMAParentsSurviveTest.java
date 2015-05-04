package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA;

import java.util.Random;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitLinKernighanEdgeMA;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the permutation-based heuristic init edge MA with
 * Multi-Neighborhood mutation
 */
public class HeuristicInitLinKernighanEdgeMAParentsSurviveTest extends
    TSPAlgorithmSymmetricTest {

  /** create */
  public HeuristicInitLinKernighanEdgeMAParentsSurviveTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected HeuristicInitLinKernighanEdgeMA createAlgorithm() {
    HeuristicInitLinKernighanEdgeMA res;
    Random r;
    int mu, lambda;

    res = new HeuristicInitLinKernighanEdgeMA();
    res.setParentsSurvive(true);

    r = new Random();
    mu = (1 + r.nextInt(512));
    lambda = (mu + 1 + r.nextInt(512));
    res.setMu(mu);
    res.setLambda(lambda);

    return res;
  }
}
