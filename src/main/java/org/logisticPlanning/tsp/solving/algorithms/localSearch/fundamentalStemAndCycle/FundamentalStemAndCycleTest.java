package org.logisticPlanning.tsp.solving.algorithms.localSearch.fundamentalStemAndCycle;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.fundamentalStemAndCycle.FundamentalStemAndCycle;
import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

public class FundamentalStemAndCycleTest extends TSPAlgorithmSymmetricTest {

	/** create */
	public FundamentalStemAndCycleTest() {
		super();
	}
	
	/** {@inheritDoc} */
	@Override
	protected FundamentalStemAndCycle createAlgorithm() {
		return new FundamentalStemAndCycle();
	}

}
