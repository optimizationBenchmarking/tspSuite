package org.logisticPlanning.tsp.solving.algorithms.localSearch.ejectionchain;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

public class P_SecTest extends TSPAlgorithmSymmetricTest{

	/** create */
	public P_SecTest() {
		super();
	}
	
	/** {@inheritDoc} */
	@Override
	protected P_Sec createAlgorithm() {
		return new P_Sec();
	}

}
