package org.logisticPlanning.tsp.solving.operators.permutation.update;

import java.util.ArrayList;

import org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.ts._PairOfNode;

/**
 * A complement update operation.
 */
public final class ComplementUpdateOperator extends PermutationUpdateOperator {

	/** the serial version uid */
	private static final long serialVersionUID = 1L;

	/** the update operator */
	private final PermutationUpdateOperator m_u;

	/**
	 * create
	 *
	 * @param u
	 *            the operator to complement
	 */
	public ComplementUpdateOperator(final PermutationUpdateOperator u) {
		super("complementary_" + u.toString()); //$NON-NLS-1$
		this.m_u = u;
	}

	/** {@inheritDoc} */
	@Override
	public final int delta(final int[] perm, final DistanceComputer f,
			final int a, final int b) {
		return this.m_u.delta(perm, f, b, a);
	}

	/** {@inheritDoc} */
	@Override
	public final void update(final int[] perm, final int a, final int b) {
		this.m_u.update(perm, b, a);
	}

	/** {@inheritDoc} */
	@Override
	public final void revertUpdate(final int[] perm, final int a, final int b) {
		this.m_u.revertUpdate(perm, b, a);
	}

	/** {@inheritDoc} */
	@Override
	public final ArrayList<_PairOfNode> inComingEdges(final int[] perm, final int a,
			final int b) {
		return this.m_u.inComingEdges(perm, b, a);
	}

	/** {@inheritDoc} */
	@Override
	public final ArrayList<_PairOfNode> outComingEdges(final int[] perm, final int a,
			final int b) {
		return this.m_u.outComingEdges(perm, b, a);
	}

}
