package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.utils.NodeManager;
import org.logisticPlanning.tsp.solving.utils.candidates.CandidateEdgeNumber;
import org.logisticPlanning.tsp.solving.utils.candidates.CandidateSet;
import org.logisticPlanning.tsp.solving.utils.edgeData.EdgeNumber;

/**
 * This enumeration defines the augmentation method that is applied if no
 * candidate edge is available for choice.
 */
public enum EAugmentationMethod {

  /**
   * Augment the sample by choosing the edge with the highest histogram
   * value. This is the default method in the template-based EHBSA. Its
   * advantage is that it makes full use of all gathered histogram
   * information. Its disadvantage is that it will require a full
   * {@code n*(n-1)} matrix (asymmetric case) for the histogram.
   */
  AUGMENT_BY_HISTOGRAM() {

    /** {@inheritDoc} */
    @Override
    final EdgeNumber allocate(final int n, final boolean symmetric,
        final long minValue, final long maxValue,
        final boolean requiresFloats, final CandidateSet candidates,
        final EdgeNumber old) {
      return EdgeNumber.allocate(n, symmetric, minValue, maxValue,
          requiresFloats, old);
    }

    /** {@inheritDoc} */
    @Override
    final int augment(final int sourceNode, final NodeManager manager,
        final EdgeNumber model, final ObjectiveFunction f) {
      int i, maxHistogram, destinationNode, curTest, curHistogram;

      destinationNode = maxHistogram = Integer.MIN_VALUE;
      for (i = manager.size(); (--i) >= 0;) {
        curTest = manager.getByIndex(i);
        curHistogram = model.getInt(sourceNode, curTest);
        if (curHistogram > maxHistogram) {
          maxHistogram = curHistogram;
          destinationNode = curTest;
        }
      }

      return destinationNode;
    }
  },

  /**
   * Augment the sample by choosing the nearest node as next step. This is
   * an alternative method. Its advantage is that it needs less memory,
   * since only histogram values related to candidate edges need to be
   * stored. Its disadvantages are that it makes less use of histogram
   * information, requires higher runtime (access to model more complex),
   * and more distance evaluations.
   */
  AUGMENT_BY_DISTANCE() {

    /** {@inheritDoc} */
    @Override
    final EdgeNumber allocate(final int n, final boolean symmetric,
        final long minValue, final long maxValue,
        final boolean requiresFloats, final CandidateSet candidates,
        final EdgeNumber old) {
      return CandidateEdgeNumber.allocate(n, symmetric, minValue,
          maxValue, requiresFloats, candidates, old);
    }

    /** {@inheritDoc} */
    @Override
    final int augment(final int sourceNode, final NodeManager manager,
        final EdgeNumber model, final ObjectiveFunction f) {
      int i, minDist, destinationNode, curTest, curDist;

      destinationNode = Integer.MIN_VALUE;
      minDist = Integer.MAX_VALUE;
      for (i = manager.size(); (--i) >= 0;) {
        curTest = manager.getByIndex(i);
        curDist = f.distance(sourceNode, curTest);
        if (curDist < minDist) {
          minDist = curDist;
          destinationNode = curTest;
        }
      }

      return destinationNode;
    }
  },

  /**
   * Augment the sample by randomly choosing the next step. This is an
   * alternative method. Its advantage is that it needs less memory, since
   * only histogram values related to candidate edges need to be stored.
   * Its disadvantages are that it makes less use of histogram information
   * and requires higher runtime (access to model more complex).
   */
  AUGMENT_RANDOMLY() {

    /** {@inheritDoc} */
    @Override
    final EdgeNumber allocate(final int n, final boolean symmetric,
        final long minValue, final long maxValue,
        final boolean requiresFloats, final CandidateSet candidates,
        final EdgeNumber old) {
      return CandidateEdgeNumber.allocate(n, symmetric, minValue,
          maxValue, requiresFloats, candidates, old);
    }

    /** {@inheritDoc} */
    @Override
    final int augment(final int sourceNode, final NodeManager manager,
        final EdgeNumber model, final ObjectiveFunction f) {
      return manager.getByIndex(f.getRandom().nextInt(manager.size()));
    }
  }

  ;

  /**
   * <p>
   * Allocate an
   * {@link org.logisticPlanning.tsp.solving.utils.edgeData.EdgeNumber
   * EdgeNumber} instance (with all data elements set to 0) for storing the
   * edge histogram model.
   * 
   * @param n
   *          the number of nodes
   * @param symmetric
   *          is the instance symmetric?
   * @param minValue
   *          the minimum value to be stored, specified as 64 bit signed
   *          integer (long)
   * @param maxValue
   *          the maximum value to be stored, specified as 64 bit signed
   *          integer (long)
   * @param requiresFloats
   *          do we need to support floating point numbers?
   * @param candidates
   *          the candidate set
   * @param old
   *          the old edge byte instance that may be re-used
   * @return the
   *         {@link org.logisticPlanning.tsp.solving.utils.edgeData.EdgeNumber
   *         EdgeNumber} instance with the given features and all bits set
   *         to {@code 0}
   */
  abstract EdgeNumber allocate(final int n, final boolean symmetric,
      final long minValue, final long maxValue,
      final boolean requiresFloats, final CandidateSet candidates,
      final EdgeNumber old);

  /**
   * Find the next node to visit. This method is called when all nodes in
   * the candidate set have already been visited and we need to decide
   * which node to take instead.
   * 
   * @param sourceNode
   *          the current node
   * @param manager
   *          the node manager holding the nodes still available
   * @param model
   *          the edge histogram model
   * @param f
   *          the objective function
   * @return the id of the next node to visit
   */
  abstract int augment(final int sourceNode, final NodeManager manager,
      final EdgeNumber model, final ObjectiveFunction f);
}
