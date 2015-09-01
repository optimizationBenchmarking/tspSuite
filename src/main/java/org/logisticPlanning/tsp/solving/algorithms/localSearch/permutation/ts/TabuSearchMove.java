// TSP Suite, version 0.9.8
// Copyright (c) 2012-2014 Thomas Weise, http://www.it-weise.de/
// License : The GNU Lesser General Public License, version 3.0
// Project : TSP Suite, Version: 0.9.8, Target Platform: Java 1.7
// File :
// main.java.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.hc.UpdatingPermutationHillClimber.java
// Website : http://www.logisticPlanning.org/tsp
// Packaged: 2014-04-26 18:05:48 GMT+0800
//
//
package org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.ts;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.TSPModule;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.TSPLocalSearchAlgorithm;
import org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdateOperator;
import org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdateOperators;
import org.logisticPlanning.utils.collections.lists.ArrayListView;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;

import test.junit.org.logisticPlanning.tsp.benchmarking.instances.Instance_A280_Test;

/**
 * <p>
 * A simple tabu search, i.e., a local search, which in each step checks if the
 * best solution is already in a list called tabu list. If it is, then checks
 * the second best solution until one solution not in the tabu list found and
 * accepts this solution. And also, add each solution we get in each step into
 * the tabu list.
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdateOperator
 * updating operations}&nbsp;[<a href="#cite_RN2002AI"
 * style="font-weight:bold">1</a>, <a href="#cite_JCS2003HC"
 * style="font-weight:bold">2</a>, <a href="#cite_DHS2000HC"
 * style="font-weight:bold">3</a>] would yield the best improvement and then
 * applies this operator.
 * </p>
 * 
 * @author <em>Dan Xu</em>, Email:&nbsp;<a
 *         href="mailto:dandy@mail.ustc.edu.cn">dandy@mail.ustc.edu.cn</a>,
 *         Tel.:&nbsp;<a href="tel:+86 139 650 406 13">+86 139 650 406 13</a>;
 *         <a href="http://www.ustc.edu.cn/">University of Science and
 *         Technology of China (USTC)</a>
 *         [&#x4E2D;&#x56FD;&#x79D1;&#x5B66;&#x6280;&#x672F;&#x5927;&#x5B66;],
 *         (<a href=
 *         "https://en.wikipedia.org/wiki/University_of_Science_and_Technology_of_China"
 *         >wikipedia</a>); <a href="http://cs.ustc.edu.cn/">School of Computer
 *         Science and Technology (SCST)</a>
 *         [&#x8BA1;&#x7B97;&#x673A;&#x79D1;&#x5B66
 *         ;&#x4E0E;&#x6280;&#x672F;&#x5B66;&#x9662;]; <a
 *         href="http://ubri.ustc.edu.cn/">USTC-Birmingham Joint Research
 *         Institute in Intelligent Computation and Its Applications (UBRI)</a>;
 *         West Campus [&#x897F;&#x533A;]; Crossroad of Huangshan Road and Feixi
 *         Road
 *         [&#x9EC4;&#x5C71;&#x8DEF;/&#x80A5;&#x897F;&#x8DEF;&#x5341;&#x5B57
 *         ;&#x8DEF;&#x53E3;]; <a
 *         href="https://en.wikipedia.org/wiki/Hefei">Hefei</a>
 *         [&#x5408;&#x80A5;&#x5E02;] 230027; <a
 *         href="https://en.wikipedia.org/wiki/Anhui">Anhui</a>
 *         [&#x5B89;&#x5FBD;&#x7701;]; <a
 *         href="https://en.wikipedia.org/wiki/People%27s_Republic_of_China"
 *         >China</a> [&#x4E2D;&#x56FD;]
 */
public class TabuSearchMove extends TSPLocalSearchAlgorithm<int[]> {
	/** the serial version uid */
	private static final long serialVersionUID = 1L;

	/** the updating operators: {@value} */
	public static final String PARAM_UPDATING_OPERATORS = "updatingOperators"; //$NON-NLS-1$

	/** the update operations */
	private PermutationUpdateOperator[] m_ops;

	/** the length of tabu list: {@value} */
	public static final String PARAM_ABSOLUTE_LENGTH = "absolute"; //$NON-NLS-1$

	/** the absolute length of tabu list */
	private int absolute;

	/** the length of tabu list */
	private int tabuListLength;

	/** the length of tabu list: {@value} */
	public static final String PARAM_RATE = "rate"; //$NON-NLS-1$

	/** the length of tabu list */
	private int rate;

	/** the tabu list */
	private ArrayList<PairOfNode> tabuList;

	/** instantiate */
	public TabuSearchMove() {
		super("TabuSearchEx");//$NON-NLS-1$
		this.m_ops = PermutationUpdateOperators.OPERATORS_AND_COMPLEMENT;
		// this.tabuListLength = 10;
	}

	/**
	 * get the tabu list length
	 * 
	 * @return the tabu list length
	 */
	public int getTabuListLength() {
		return this.tabuListLength;
	}

	/**
	 * set the tabu list length
	 * 
	 * @param ptabuListLength
	 *            the tabu list length
	 */
	public void setTabuListLength(final int ptabuListLength) {
		this.tabuListLength = ptabuListLength;
	}

	/**
	 * Perform the tabu search
	 * 
	 * @param args
	 *            the command line arguments
	 */
	public final static void main(final String[] args) {
		TSPAlgorithmRunner.benchmark(//
				Instance.SYMMETRIC_INSTANCES , TabuSearchMove.class,//
				args);
	}

	/** {@inheritDoc} */
	@Override
	public void localSearch(final Individual<int[]> srcdst,
			final ObjectiveFunction f) {
		final int[] sol = srcdst.solution;
		PairOfNode pairOfEx = new PairOfNode();
		final int n;
		boolean foundImprov = true;
		int d, a, b, delta;
		int bestA, bestB;
		long value = srcdst.tourLength;

		Iterator<PairOfNode> xxxx;
		PermutationUpdateOperator best;

		n = f.n();

		while (foundImprov && (!(f.shouldTerminate()))) {
			foundImprov = false;
			bestA = 0;
			bestB = 1;
			delta = Integer.MAX_VALUE;
			best = null;
			for (a = 0; a < n - 2; a++) {
				pairOfEx.setA(sol[a]);
				for (b = a + 1; b < n - 1; b++) {
					pairOfEx.setB(sol[b]);
					if (!this.tabuList.isEmpty() && this.tabuList.contains(pairOfEx))
						continue;
					for (final PermutationUpdateOperator o : this.m_ops) {
						d = o.delta(sol, f, a, b);
						if (d < delta) {
							delta = d;
							bestA = a;
							bestB = b;
							best = o;
						}
					}
				}
			}
			if (best != null) {
				pairOfEx = new PairOfNode(sol[bestA], sol[bestB]);
				best.update(sol, bestA, bestB);
				if (!this.tabuList.isEmpty() && this.tabuList.size() >= this.tabuListLength) {
					xxxx = this.tabuList.iterator();
					if (xxxx.hasNext()) {
						xxxx.next();
						xxxx.remove();
					}
				}
				this.tabuList.add(pairOfEx.clone());
				value += delta;
				f.registerFEs(1, sol, value);
				if(delta < 0) foundImprov = true;
			}
			else f.registerFEs(1, null, value);
		}
	}

	/** {@inheritDoc} */
	@Override
	public final TabuSearchMove clone() {
		final TabuSearchMove res;
		final PermutationUpdateOperator[] ops;
		int i;

		res = ((TabuSearchMove) (super.clone()));
		res.m_ops = ops = res.m_ops.clone();
		for (i = ops.length; (--i) >= 0;) {
			ops[i] = ops[i].clone();
		}
		res.tabuList = null;// TODO: added

		return res;
	}

	/** {@inheritDoc} */
	@Override
	public void configure(final Configuration config) {
		super.configure(config);

		this.absolute = config.getInt(TabuSearchMove.PARAM_ABSOLUTE_LENGTH, 0,
				20000, this.absolute);
		this.rate = config.getInt(TabuSearchMove.PARAM_RATE, 0, 1000, this.rate);
	}

	/** {@inheritDoc} */
	@Override
	public void printConfiguration(final PrintStream ps) {
		super.printConfiguration(ps);

		Configurable.printKey(TabuSearchMove.PARAM_ABSOLUTE_LENGTH, ps);
		Configurable.printlnObject(this.tabuListLength, ps);
		Configurable.printKey(TabuSearchMove.PARAM_RATE, ps);
		Configurable.printlnObject(this.rate, ps);
		Configurable.printKey(TabuSearchMove.PARAM_UPDATING_OPERATORS, ps);
		Configurable.printlnObject(this.m_ops, ps);
	}

	/** {@inheritDoc} */
	@Override
	public void beginRun(final ObjectiveFunction f) {
		super.beginRun(f);
		TSPModule.invokeBeginRun(f, this.m_ops);
		if ((this.absolute + (int) (this.rate * Math.sqrt(f.n()))) < f.n()
				* (f.n() - 1))
			this.tabuListLength = this.absolute
					+ (int) (this.rate * Math.sqrt(f.n()));
		else
			this.tabuListLength = f.n();
		this.tabuList = new ArrayList<>(this.tabuListLength);// TODO: added
	}

	/** {@inheritDoc} */
	@Override
	public void endRun(final ObjectiveFunction f) {
		this.tabuList = null;// TODO: added
		try {
			TSPModule.invokeEndRun(f, this.m_ops);
		} finally {
			super.endRun(f);
		}
	}
}