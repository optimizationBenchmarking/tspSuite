package org.logisticPlanning.tsp.solving.operators.permutation.localOpt;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;

/**
 * <p>
 * An implementation of the class
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.localOpt.LocalOptimizer}
 * for finding optimal path sub-sequences of length 6.
 * </p>
 * <p>
 * This code was automatically generated with the aim to be as efficient as
 * possible.
 * </p>
 */
public final class ExhaustivelyEnumeratingLocal6Optimizer
extends
org.logisticPlanning.tsp.solving.operators.permutation.localOpt.LocalOptimizer {

  /** the serial version uid */
  private static final long serialVersionUID = 0L;

  /**
   * the best improvement found: if
   * <code>{@link #m_best_delta} &gt; 0</code>, then there is an improved
   * sub-sequence stored in {@link #m_best_node_1} &hellip;
   * {@link #m_best_node_6}; otherwise these variables are not used
   */
  private long m_best_delta;

  // We cache all nodes related to the sub-sequence to be optimized.
  /** The node before the start index */
  private int m_original_node_beforeStart;
  /** The node originally at index 1 of the sub-sequence */
  private int m_original_node_1;
  /**
   * The best node found for index 1 of the sub-sequence, or empty/unused
   * if no improvement was found, i.e.,
   * <code>{@link #m_best_delta} &geq; 0</code>
   */
  private int m_best_node_1;
  /** The node originally at index 2 of the sub-sequence */
  private int m_original_node_2;
  /**
   * The best node found for index 2 of the sub-sequence, or empty/unused
   * if no improvement was found, i.e.,
   * <code>{@link #m_best_delta} &geq; 0</code>
   */
  private int m_best_node_2;
  /** The node originally at index 3 of the sub-sequence */
  private int m_original_node_3;
  /**
   * The best node found for index 3 of the sub-sequence, or empty/unused
   * if no improvement was found, i.e.,
   * <code>{@link #m_best_delta} &geq; 0</code>
   */
  private int m_best_node_3;
  /** The node originally at index 4 of the sub-sequence */
  private int m_original_node_4;
  /**
   * The best node found for index 4 of the sub-sequence, or empty/unused
   * if no improvement was found, i.e.,
   * <code>{@link #m_best_delta} &geq; 0</code>
   */
  private int m_best_node_4;
  /** The node originally at index 5 of the sub-sequence */
  private int m_original_node_5;
  /**
   * The best node found for index 5 of the sub-sequence, or empty/unused
   * if no improvement was found, i.e.,
   * <code>{@link #m_best_delta} &geq; 0</code>
   */
  private int m_best_node_5;
  /** The node originally at index 6 of the sub-sequence */
  private int m_original_node_6;
  /**
   * The best node found for index 6 of the sub-sequence, or empty/unused
   * if no improvement was found, i.e.,
   * <code>{@link #m_best_delta} &geq; 0</code>
   */
  private int m_best_node_6;
  /** The node after the start index */
  private int m_original_node_afterEnd;
  // All nodes related to the sub-sequence to be optimized can now be
  // cached.

  // We cache all distances related to the sub-sequence to be optimized.
  /**
   * The distance from {@link #m_original_node_beforeStart} to
   * {@link #m_original_node_1} as {@code long}, since we always need
   * {@code long} arithmetic anyway.
   */
  private long m_dist_beforeStart_1;
  /**
   * The distance from {@link #m_original_node_beforeStart} to
   * {@link #m_original_node_2} as {@code long}, since we always need
   * {@code long} arithmetic anyway.
   */
  private long m_dist_beforeStart_2;
  /**
   * The distance from {@link #m_original_node_beforeStart} to
   * {@link #m_original_node_3} as {@code long}, since we always need
   * {@code long} arithmetic anyway.
   */
  private long m_dist_beforeStart_3;
  /**
   * The distance from {@link #m_original_node_beforeStart} to
   * {@link #m_original_node_4} as {@code long}, since we always need
   * {@code long} arithmetic anyway.
   */
  private long m_dist_beforeStart_4;
  /**
   * The distance from {@link #m_original_node_beforeStart} to
   * {@link #m_original_node_5} as {@code long}, since we always need
   * {@code long} arithmetic anyway.
   */
  private long m_dist_beforeStart_5;
  /**
   * The distance from {@link #m_original_node_beforeStart} to
   * {@link #m_original_node_6} as {@code long}, since we always need
   * {@code long} arithmetic anyway.
   */
  private long m_dist_beforeStart_6;
  /**
   * The distance from {@link #m_original_node_1} to
   * {@link #m_original_node_2} as {@code long}, since we always need
   * {@code long} arithmetic anyway.
   */
  private long m_dist_1_2;
  /**
   * The distance from {@link #m_original_node_1} to
   * {@link #m_original_node_3} as {@code long}, since we always need
   * {@code long} arithmetic anyway.
   */
  private long m_dist_1_3;
  /**
   * The distance from {@link #m_original_node_1} to
   * {@link #m_original_node_4} as {@code long}, since we always need
   * {@code long} arithmetic anyway.
   */
  private long m_dist_1_4;
  /**
   * The distance from {@link #m_original_node_1} to
   * {@link #m_original_node_5} as {@code long}, since we always need
   * {@code long} arithmetic anyway.
   */
  private long m_dist_1_5;
  /**
   * The distance from {@link #m_original_node_1} to
   * {@link #m_original_node_6} as {@code long}, since we always need
   * {@code long} arithmetic anyway.
   */
  private long m_dist_1_6;
  /**
   * The distance from {@link #m_original_node_1} to
   * {@link #m_original_node_afterEnd} as {@code long}, since we always
   * need {@code long} arithmetic anyway.
   */
  private long m_dist_1_afterEnd;
  /**
   * The distance from {@link #m_original_node_2} to
   * {@link #m_original_node_3} as {@code long}, since we always need
   * {@code long} arithmetic anyway.
   */
  private long m_dist_2_3;
  /**
   * The distance from {@link #m_original_node_2} to
   * {@link #m_original_node_4} as {@code long}, since we always need
   * {@code long} arithmetic anyway.
   */
  private long m_dist_2_4;
  /**
   * The distance from {@link #m_original_node_2} to
   * {@link #m_original_node_5} as {@code long}, since we always need
   * {@code long} arithmetic anyway.
   */
  private long m_dist_2_5;
  /**
   * The distance from {@link #m_original_node_2} to
   * {@link #m_original_node_6} as {@code long}, since we always need
   * {@code long} arithmetic anyway.
   */
  private long m_dist_2_6;
  /**
   * The distance from {@link #m_original_node_2} to
   * {@link #m_original_node_afterEnd} as {@code long}, since we always
   * need {@code long} arithmetic anyway.
   */
  private long m_dist_2_afterEnd;
  /**
   * The distance from {@link #m_original_node_3} to
   * {@link #m_original_node_4} as {@code long}, since we always need
   * {@code long} arithmetic anyway.
   */
  private long m_dist_3_4;
  /**
   * The distance from {@link #m_original_node_3} to
   * {@link #m_original_node_5} as {@code long}, since we always need
   * {@code long} arithmetic anyway.
   */
  private long m_dist_3_5;
  /**
   * The distance from {@link #m_original_node_3} to
   * {@link #m_original_node_6} as {@code long}, since we always need
   * {@code long} arithmetic anyway.
   */
  private long m_dist_3_6;
  /**
   * The distance from {@link #m_original_node_3} to
   * {@link #m_original_node_afterEnd} as {@code long}, since we always
   * need {@code long} arithmetic anyway.
   */
  private long m_dist_3_afterEnd;
  /**
   * The distance from {@link #m_original_node_4} to
   * {@link #m_original_node_5} as {@code long}, since we always need
   * {@code long} arithmetic anyway.
   */
  private long m_dist_4_5;
  /**
   * The distance from {@link #m_original_node_4} to
   * {@link #m_original_node_6} as {@code long}, since we always need
   * {@code long} arithmetic anyway.
   */
  private long m_dist_4_6;
  /**
   * The distance from {@link #m_original_node_4} to
   * {@link #m_original_node_afterEnd} as {@code long}, since we always
   * need {@code long} arithmetic anyway.
   */
  private long m_dist_4_afterEnd;
  /**
   * The distance from {@link #m_original_node_5} to
   * {@link #m_original_node_6} as {@code long}, since we always need
   * {@code long} arithmetic anyway.
   */
  private long m_dist_5_6;
  /**
   * The distance from {@link #m_original_node_5} to
   * {@link #m_original_node_afterEnd} as {@code long}, since we always
   * need {@code long} arithmetic anyway.
   */
  private long m_dist_5_afterEnd;
  /**
   * The distance from {@link #m_original_node_6} to
   * {@link #m_original_node_afterEnd} as {@code long}, since we always
   * need {@code long} arithmetic anyway.
   */
  private long m_dist_6_afterEnd;

  // All distances related to the sub-sequence to be optimized can now be
  // cached.

  /**
   * The constructor of class
   * {@link ExhaustivelyEnumeratingLocal6Optimizer}
   */
  public ExhaustivelyEnumeratingLocal6Optimizer() {
    super(6);
  }

  /**
   * This is the main entry point of class
   * {@link ExhaustivelyEnumeratingLocal6Optimizer}. This method makes a
   * sub-sequence of length 6 inside {@code path}, starting right after
   * index {@code beforeStart}, optimal.
   *
   * @param path
   *          the candidate solution in path representation
   * @param beforeStart
   *          the starting index
   * @param dist
   *          the distance computer
   * @return the amount the length of the path has changed (negative if an
   *         improvement was found), or {@code 0L} if the path was not
   *         modified
   */
  @Override
  public final long apply(final int[] path, final int beforeStart,
      final ObjectiveFunction dist) {

    final int length = path.length; // the number of elements in path
    int i; // a multi-purpose counter

    this.m_best_delta = 0L;

    // We cache all related nodes in member variables.
    i = beforeStart; // set the start index
    this.m_original_node_beforeStart = path[i];
    if ((++i) >= length) {
      i = 0;
    }
    this.m_original_node_1 = path[i];
    if ((++i) >= length) {
      i = 0;
    }
    this.m_original_node_2 = path[i];
    if ((++i) >= length) {
      i = 0;
    }
    this.m_original_node_3 = path[i];
    if ((++i) >= length) {
      i = 0;
    }
    this.m_original_node_4 = path[i];
    if ((++i) >= length) {
      i = 0;
    }
    this.m_original_node_5 = path[i];
    if ((++i) >= length) {
      i = 0;
    }
    this.m_original_node_6 = path[i];
    if ((++i) >= length) {
      i = 0;
    }
    this.m_original_node_afterEnd = path[i];
    // Now all related nodes are cached in member variables.

    // We cache all distances related to the sub-sequence to be optimized.
    this.m_dist_beforeStart_1 = dist.distance(
        this.m_original_node_beforeStart, this.m_original_node_1);
    this.m_dist_beforeStart_2 = dist.distance(
        this.m_original_node_beforeStart, this.m_original_node_2);
    this.m_dist_beforeStart_3 = dist.distance(
        this.m_original_node_beforeStart, this.m_original_node_3);
    this.m_dist_beforeStart_4 = dist.distance(
        this.m_original_node_beforeStart, this.m_original_node_4);
    this.m_dist_beforeStart_5 = dist.distance(
        this.m_original_node_beforeStart, this.m_original_node_5);
    this.m_dist_beforeStart_6 = dist.distance(
        this.m_original_node_beforeStart, this.m_original_node_6);
    this.m_dist_1_2 = dist.distance(this.m_original_node_1,
        this.m_original_node_2);
    this.m_dist_1_3 = dist.distance(this.m_original_node_1,
        this.m_original_node_3);
    this.m_dist_1_4 = dist.distance(this.m_original_node_1,
        this.m_original_node_4);
    this.m_dist_1_5 = dist.distance(this.m_original_node_1,
        this.m_original_node_5);
    this.m_dist_1_6 = dist.distance(this.m_original_node_1,
        this.m_original_node_6);
    this.m_dist_1_afterEnd = dist.distance(this.m_original_node_1,
        this.m_original_node_afterEnd);
    this.m_dist_2_3 = dist.distance(this.m_original_node_2,
        this.m_original_node_3);
    this.m_dist_2_4 = dist.distance(this.m_original_node_2,
        this.m_original_node_4);
    this.m_dist_2_5 = dist.distance(this.m_original_node_2,
        this.m_original_node_5);
    this.m_dist_2_6 = dist.distance(this.m_original_node_2,
        this.m_original_node_6);
    this.m_dist_2_afterEnd = dist.distance(this.m_original_node_2,
        this.m_original_node_afterEnd);
    this.m_dist_3_4 = dist.distance(this.m_original_node_3,
        this.m_original_node_4);
    this.m_dist_3_5 = dist.distance(this.m_original_node_3,
        this.m_original_node_5);
    this.m_dist_3_6 = dist.distance(this.m_original_node_3,
        this.m_original_node_6);
    this.m_dist_3_afterEnd = dist.distance(this.m_original_node_3,
        this.m_original_node_afterEnd);
    this.m_dist_4_5 = dist.distance(this.m_original_node_4,
        this.m_original_node_5);
    this.m_dist_4_6 = dist.distance(this.m_original_node_4,
        this.m_original_node_6);
    this.m_dist_4_afterEnd = dist.distance(this.m_original_node_4,
        this.m_original_node_afterEnd);
    this.m_dist_5_6 = dist.distance(this.m_original_node_5,
        this.m_original_node_6);
    this.m_dist_5_afterEnd = dist.distance(this.m_original_node_5,
        this.m_original_node_afterEnd);
    this.m_dist_6_afterEnd = dist.distance(this.m_original_node_6,
        this.m_original_node_afterEnd);
    // All distances related to the sub-sequence to be optimized are now
    // cached.

    // Trace all paths starting at the canonical permutation.

    long current_delta;

    // Now processing permutation (1,2,3,4,6,5).
    current_delta = ((this.m_dist_4_6 + this.m_dist_5_afterEnd)
        - this.m_dist_4_5 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,2,3,4,6,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,2,3,4,6,5) by sequences of 2-opt moves.
    this.__trace_1(current_delta);
    // Finished processing permutation (1,2,3,4,6,5).

    // Now processing permutation (1,2,3,6,5,4).
    current_delta = ((this.m_dist_3_6 + this.m_dist_4_afterEnd)
        - this.m_dist_3_4 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,2,3,6,5,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,2,3,6,5,4) by sequences of 2-opt moves.
    this.__trace_9(current_delta);
    // Finished processing permutation (1,2,3,6,5,4).

    // Now processing permutation (1,2,3,5,4,6).
    current_delta = ((this.m_dist_3_5 + this.m_dist_4_6) - this.m_dist_3_4 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,2,3,5,4,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,2,3,5,4,6) by sequences of 2-opt moves.
    this.__trace_11(current_delta);
    // Finished processing permutation (1,2,3,5,4,6).

    // Now processing permutation (1,2,6,5,4,3).
    current_delta = ((this.m_dist_2_6 + this.m_dist_3_afterEnd)
        - this.m_dist_2_3 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,2,6,5,4,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,2,6,5,4,3) by sequences of 2-opt moves.
    this.__trace_44(current_delta);
    // Finished processing permutation (1,2,6,5,4,3).

    // Now processing permutation (1,2,5,4,3,6).
    current_delta = ((this.m_dist_2_5 + this.m_dist_3_6) - this.m_dist_2_3 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,2,5,4,3,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,2,5,4,3,6) by sequences of 2-opt moves.
    this.__trace_47(current_delta);
    // Finished processing permutation (1,2,5,4,3,6).

    // Now processing permutation (1,2,4,3,5,6).
    current_delta = ((this.m_dist_2_4 + this.m_dist_3_5) - this.m_dist_2_3 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,2,4,3,5,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,2,4,3,5,6) by sequences of 2-opt moves.
    this.__trace_59(current_delta);
    // Finished processing permutation (1,2,4,3,5,6).

    // Now processing permutation (1,5,4,3,2,6).
    current_delta = ((this.m_dist_1_5 + this.m_dist_2_6) - this.m_dist_1_2 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,5,4,3,2,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,5,4,3,2,6) by sequences of 2-opt moves.
    this.__trace_156(current_delta);
    // Finished processing permutation (1,5,4,3,2,6).

    // Now processing permutation (1,6,5,4,3,2).
    current_delta = ((this.m_dist_1_6 + this.m_dist_2_afterEnd)
        - this.m_dist_1_2 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,6,5,4,3,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,6,5,4,3,2) by sequences of 2-opt moves.
    this.__trace_160(current_delta);
    // Finished processing permutation (1,6,5,4,3,2).

    // Now processing permutation (1,4,3,2,5,6).
    current_delta = ((this.m_dist_1_4 + this.m_dist_2_5) - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,4,3,2,5,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,4,3,2,5,6) by sequences of 2-opt moves.
    this.__trace_179(current_delta);
    // Finished processing permutation (1,4,3,2,5,6).

    // Now processing permutation (1,3,2,4,5,6).
    current_delta = ((this.m_dist_1_3 + this.m_dist_2_4) - this.m_dist_1_2 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,3,2,4,5,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,3,2,4,5,6) by sequences of 2-opt moves.
    this.__trace_239(current_delta);
    // Finished processing permutation (1,3,2,4,5,6).

    // Now processing permutation (4,3,2,1,5,6).
    current_delta = ((this.m_dist_beforeStart_4 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,3,2,1,5,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (4,3,2,1,5,6) by sequences of 2-opt moves.
    this.__trace_360(current_delta);
    // Finished processing permutation (4,3,2,1,5,6).

    // Now processing permutation (5,4,3,2,1,6).
    current_delta = ((this.m_dist_beforeStart_5 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,4,3,2,1,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (5,4,3,2,1,6) by sequences of 2-opt moves.
    this.__trace_384(current_delta);
    // Finished processing permutation (5,4,3,2,1,6).

    // Now processing permutation (6,5,4,3,2,1).
    current_delta = ((this.m_dist_beforeStart_6 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,5,4,3,2,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (6,5,4,3,2,1) by sequences of 2-opt moves.
    this.__trace_389(current_delta);
    // Finished processing permutation (6,5,4,3,2,1).

    // Now processing permutation (3,2,1,4,5,6).
    current_delta = ((this.m_dist_beforeStart_3 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,2,1,4,5,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (3,2,1,4,5,6) by sequences of 2-opt moves.
    this.__trace_479(current_delta);
    // Finished processing permutation (3,2,1,4,5,6).

    // Now processing permutation (2,1,3,4,5,6).
    current_delta = ((this.m_dist_beforeStart_2 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,1,3,4,5,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (2,1,3,4,5,6) by sequences of 2-opt moves.
    this.__trace_719(current_delta);
    // Finished processing permutation (2,1,3,4,5,6).

    if (this.m_best_delta < 0L) {
      // A new, improved node sequence has been discovered and cached in
      // the m_best_node_* member variables. We now write it back!
      i = beforeStart;
      if ((++i) >= length) {
        i = 0;
      }
      if (this.m_best_node_1 > 0) {
        path[i] = this.m_best_node_1;
      }
      if ((++i) >= length) {
        i = 0;
      }
      if (this.m_best_node_2 > 0) {
        path[i] = this.m_best_node_2;
      }
      if ((++i) >= length) {
        i = 0;
      }
      if (this.m_best_node_3 > 0) {
        path[i] = this.m_best_node_3;
      }
      if ((++i) >= length) {
        i = 0;
      }
      if (this.m_best_node_4 > 0) {
        path[i] = this.m_best_node_4;
      }
      if ((++i) >= length) {
        i = 0;
      }
      if (this.m_best_node_5 > 0) {
        path[i] = this.m_best_node_5;
      }
      if ((++i) >= length) {
        i = 0;
      }
      if (this.m_best_node_6 > 0) {
        path[i] = this.m_best_node_6;
      }
      // The improved node sequence has been flushed.
    }

    return this.m_best_delta; // return the best improvement delta
    // discovered
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,2,3,4,6,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,2,3,4,6,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,2,3,4,6,5)
   */
  private final void __trace_1(final long parent_delta) {

    long current_delta;

    // Now processing permutation (1,2,5,6,4,3).
    current_delta = ((parent_delta + this.m_dist_2_5 + this.m_dist_3_afterEnd)
        - this.m_dist_2_3 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,2,5,6,4,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,2,5,6,4,3) by sequences of 2-opt moves.
    this.__trace_45(current_delta);
    // Finished processing permutation (1,2,5,6,4,3).

    // Now processing permutation (1,2,6,4,3,5).
    current_delta = ((parent_delta + this.m_dist_2_6 + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,2,6,4,3,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,2,6,4,3,5) by sequences of 2-opt moves.
    this.__trace_56(current_delta);
    // Finished processing permutation (1,2,6,4,3,5).

    // Now processing permutation (1,2,4,3,6,5).
    current_delta = ((parent_delta + this.m_dist_2_4 + this.m_dist_3_6)
        - this.m_dist_2_3 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,2,4,3,6,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,2,4,3,6,5) by sequences of 2-opt moves.
    this.__trace_58(current_delta);
    // Finished processing permutation (1,2,4,3,6,5).

    // Now processing permutation (1,5,6,4,3,2).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_afterEnd)
        - this.m_dist_1_2 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,5,6,4,3,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,5,6,4,3,2) by sequences of 2-opt moves.
    this.__trace_159(current_delta);
    // Finished processing permutation (1,5,6,4,3,2).

    // Now processing permutation (1,6,4,3,2,5).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,6,4,3,2,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,6,4,3,2,5) by sequences of 2-opt moves.
    this.__trace_175(current_delta);
    // Finished processing permutation (1,6,4,3,2,5).

    // Now processing permutation (1,4,3,2,6,5).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,4,3,2,6,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,4,3,2,6,5) by sequences of 2-opt moves.
    this.__trace_178(current_delta);
    // Finished processing permutation (1,4,3,2,6,5).

    // Now processing permutation (1,2,3,6,4,5).
    current_delta = ((parent_delta + this.m_dist_3_6 + this.m_dist_4_5)
        - this.m_dist_3_4 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,2,3,6,4,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,2,3,6,4,5) by sequences of 2-opt moves.
    this.__trace_2(current_delta);
    // Finished processing permutation (1,2,3,6,4,5).

    // Now processing permutation (1,3,2,4,6,5).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,3,2,4,6,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,3,2,4,6,5) by sequences of 2-opt moves.
    this.__trace_238(current_delta);
    // Finished processing permutation (1,3,2,4,6,5).

    // Now processing permutation (1,2,3,5,6,4).
    current_delta = ((parent_delta + this.m_dist_3_5 + this.m_dist_4_afterEnd)
        - this.m_dist_3_4 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,2,3,5,6,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,2,3,5,6,4) by sequences of 2-opt moves.
    this.__trace_10(current_delta);
    // Finished processing permutation (1,2,3,5,6,4).

    // Now processing permutation (4,3,2,1,6,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,3,2,1,6,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (4,3,2,1,6,5) by sequences of 2-opt moves.
    this.__trace_361(current_delta);
    // Finished processing permutation (4,3,2,1,6,5).

    // Now processing permutation (6,4,3,2,1,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,4,3,2,1,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (6,4,3,2,1,5) by sequences of 2-opt moves.
    this.__trace_365(current_delta);
    // Finished processing permutation (6,4,3,2,1,5).

    // Now processing permutation (5,6,4,3,2,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,6,4,3,2,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (5,6,4,3,2,1) by sequences of 2-opt moves.
    this.__trace_388(current_delta);
    // Finished processing permutation (5,6,4,3,2,1).

    // Now processing permutation (3,2,1,4,6,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,2,1,4,6,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (3,2,1,4,6,5) by sequences of 2-opt moves.
    this.__trace_478(current_delta);
    // Finished processing permutation (3,2,1,4,6,5).

    // Now processing permutation (2,1,3,4,6,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,1,3,4,6,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (2,1,3,4,6,5) by sequences of 2-opt moves.
    this.__trace_718(current_delta);
    // Finished processing permutation (2,1,3,4,6,5).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,2,3,6,5,4). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,2,3,6,5,4).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,2,3,6,5,4)
   */
  private final void __trace_9(final long parent_delta) {

    long current_delta;

    // Now processing permutation (1,2,4,5,6,3).
    current_delta = ((parent_delta + this.m_dist_2_4 + this.m_dist_3_afterEnd)
        - this.m_dist_2_3 - this.m_dist_4_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,2,4,5,6,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,2,4,5,6,3) by sequences of 2-opt moves.
    this.__trace_49(current_delta);
    // Finished processing permutation (1,2,4,5,6,3).

    // Now processing permutation (1,2,6,3,5,4).
    current_delta = ((parent_delta + this.m_dist_2_6 + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,2,6,3,5,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,2,6,3,5,4) by sequences of 2-opt moves.
    this.__trace_8(current_delta);
    // Finished processing permutation (1,2,6,3,5,4).

    // Now processing permutation (1,4,5,6,3,2).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_2_afterEnd)
        - this.m_dist_1_2 - this.m_dist_4_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,4,5,6,3,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,4,5,6,3,2) by sequences of 2-opt moves.
    this.__trace_165(current_delta);
    // Finished processing permutation (1,4,5,6,3,2).

    // Now processing permutation (1,5,6,3,2,4).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,5,6,3,2,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,5,6,3,2,4) by sequences of 2-opt moves.
    this.__trace_219(current_delta);
    // Finished processing permutation (1,5,6,3,2,4).

    // Now processing permutation (1,3,2,6,5,4).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_3_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,3,2,6,5,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,3,2,6,5,4) by sequences of 2-opt moves.
    this.__trace_230(current_delta);
    // Finished processing permutation (1,3,2,6,5,4).

    // Now processing permutation (1,6,3,2,5,4).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,6,3,2,5,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,6,3,2,5,4) by sequences of 2-opt moves.
    this.__trace_232(current_delta);
    // Finished processing permutation (1,6,3,2,5,4).

    // Now processing permutation (4,5,6,3,2,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_4_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,5,6,3,2,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (4,5,6,3,2,1) by sequences of 2-opt moves.
    this.__trace_380(current_delta);
    // Finished processing permutation (4,5,6,3,2,1).

    // Now processing permutation (5,6,3,2,1,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,6,3,2,1,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (5,6,3,2,1,4) by sequences of 2-opt moves.
    this.__trace_451(current_delta);
    // Finished processing permutation (5,6,3,2,1,4).

    // Now processing permutation (3,2,1,6,5,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_3_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,2,1,6,5,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (3,2,1,6,5,4) by sequences of 2-opt moves.
    this.__trace_470(current_delta);
    // Finished processing permutation (3,2,1,6,5,4).

    // Now processing permutation (6,3,2,1,5,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,3,2,1,5,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (6,3,2,1,5,4) by sequences of 2-opt moves.
    this.__trace_473(current_delta);
    // Finished processing permutation (6,3,2,1,5,4).

    // Now processing permutation (1,2,5,6,3,4).
    current_delta = ((parent_delta + this.m_dist_2_5 + this.m_dist_3_4)
        - this.m_dist_2_3 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,2,5,6,3,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,2,5,6,3,4) by sequences of 2-opt moves.
    this.__trace_14(current_delta);
    // Finished processing permutation (1,2,5,6,3,4).

    // Now processing permutation (2,1,3,6,5,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,1,3,6,5,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (2,1,3,6,5,4) by sequences of 2-opt moves.
    this.__trace_710(current_delta);
    // Finished processing permutation (2,1,3,6,5,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,2,3,5,4,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,2,3,5,4,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,2,3,5,4,6)
   */
  private final void __trace_11(final long parent_delta) {

    long current_delta;

    // Now processing permutation (1,2,4,5,3,6).
    current_delta = ((parent_delta + this.m_dist_2_4 + this.m_dist_3_6)
        - this.m_dist_2_3 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,2,4,5,3,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,2,4,5,3,6) by sequences of 2-opt moves.
    this.__trace_48(current_delta);
    // Finished processing permutation (1,2,4,5,3,6).

    // Now processing permutation (1,2,6,4,5,3).
    current_delta = ((parent_delta + this.m_dist_2_6 + this.m_dist_3_afterEnd)
        - this.m_dist_2_3 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,2,6,4,5,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,2,6,4,5,3) by sequences of 2-opt moves.
    this.__trace_51(current_delta);
    // Finished processing permutation (1,2,6,4,5,3).

    // Now processing permutation (1,6,4,5,3,2).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_2_afterEnd)
        - this.m_dist_1_2 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,6,4,5,3,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,6,4,5,3,2) by sequences of 2-opt moves.
    this.__trace_163(current_delta);
    // Finished processing permutation (1,6,4,5,3,2).

    // Now processing permutation (1,4,5,3,2,6).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,4,5,3,2,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,4,5,3,2,6) by sequences of 2-opt moves.
    this.__trace_167(current_delta);
    // Finished processing permutation (1,4,5,3,2,6).

    // Now processing permutation (1,5,3,2,4,6).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,5,3,2,4,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,5,3,2,4,6) by sequences of 2-opt moves.
    this.__trace_216(current_delta);
    // Finished processing permutation (1,5,3,2,4,6).

    // Now processing permutation (1,3,2,5,4,6).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,3,2,5,4,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,3,2,5,4,6) by sequences of 2-opt moves.
    this.__trace_228(current_delta);
    // Finished processing permutation (1,3,2,5,4,6).

    // Now processing permutation (6,4,5,3,2,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,4,5,3,2,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (6,4,5,3,2,1) by sequences of 2-opt moves.
    this.__trace_378(current_delta);
    // Finished processing permutation (6,4,5,3,2,1).

    // Now processing permutation (4,5,3,2,1,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,5,3,2,1,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (4,5,3,2,1,6) by sequences of 2-opt moves.
    this.__trace_383(current_delta);
    // Finished processing permutation (4,5,3,2,1,6).

    // Now processing permutation (1,2,5,3,4,6).
    current_delta = ((parent_delta + this.m_dist_2_5 + this.m_dist_3_4)
        - this.m_dist_2_3 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,2,5,3,4,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,2,5,3,4,6) by sequences of 2-opt moves.
    this.__trace_12(current_delta);
    // Finished processing permutation (1,2,5,3,4,6).

    // Now processing permutation (5,3,2,1,4,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,3,2,1,4,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (5,3,2,1,4,6) by sequences of 2-opt moves.
    this.__trace_455(current_delta);
    // Finished processing permutation (5,3,2,1,4,6).

    // Now processing permutation (3,2,1,5,4,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,2,1,5,4,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (3,2,1,5,4,6) by sequences of 2-opt moves.
    this.__trace_468(current_delta);
    // Finished processing permutation (3,2,1,5,4,6).

    // Now processing permutation (2,1,3,5,4,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,1,3,5,4,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (2,1,3,5,4,6) by sequences of 2-opt moves.
    this.__trace_708(current_delta);
    // Finished processing permutation (2,1,3,5,4,6).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,2,6,5,4,3). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,2,6,5,4,3).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,2,6,5,4,3)
   */
  private final void __trace_44(final long parent_delta) {

    long current_delta;

    // Now processing permutation (1,4,5,6,2,3).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_2_3)
        - this.m_dist_1_2 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,4,5,6,2,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,4,5,6,2,3) by sequences of 2-opt moves.
    this.__trace_74(current_delta);
    // Finished processing permutation (1,4,5,6,2,3).

    // Now processing permutation (1,3,4,5,6,2).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_afterEnd)
        - this.m_dist_1_2 - this.m_dist_3_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,3,4,5,6,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,3,4,5,6,2) by sequences of 2-opt moves.
    this.__trace_190(current_delta);
    // Finished processing permutation (1,3,4,5,6,2).

    // Now processing permutation (1,2,6,3,4,5).
    current_delta = ((parent_delta + this.m_dist_3_6 + this.m_dist_5_afterEnd)
        - this.m_dist_3_afterEnd - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,2,6,3,4,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,2,6,3,4,5) by sequences of 2-opt moves.
    this.__trace_3(current_delta);
    // Finished processing permutation (1,2,6,3,4,5).

    // Now processing permutation (3,4,5,6,2,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_3_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,4,5,6,2,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (3,4,5,6,2,1) by sequences of 2-opt moves.
    this.__trace_405(current_delta);
    // Finished processing permutation (3,4,5,6,2,1).

    // Now processing permutation (1,6,2,5,4,3).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,6,2,5,4,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,6,2,5,4,3) by sequences of 2-opt moves.
    this.__trace_43(current_delta);
    // Finished processing permutation (1,6,2,5,4,3).

    // Now processing permutation (1,5,6,2,4,3).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,5,6,2,4,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,5,6,2,4,3) by sequences of 2-opt moves.
    this.__trace_39(current_delta);
    // Finished processing permutation (1,5,6,2,4,3).

    // Now processing permutation (4,5,6,2,1,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,5,6,2,1,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (4,5,6,2,1,3) by sequences of 2-opt moves.
    this.__trace_620(current_delta);
    // Finished processing permutation (4,5,6,2,1,3).

    // Now processing permutation (2,1,6,5,4,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_2_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,1,6,5,4,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (2,1,6,5,4,3) by sequences of 2-opt moves.
    this.__trace_675(current_delta);
    // Finished processing permutation (2,1,6,5,4,3).

    // Now processing permutation (6,2,1,5,4,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,2,1,5,4,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (6,2,1,5,4,3) by sequences of 2-opt moves.
    this.__trace_677(current_delta);
    // Finished processing permutation (6,2,1,5,4,3).

    // Now processing permutation (5,6,2,1,4,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,6,2,1,4,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (5,6,2,1,4,3) by sequences of 2-opt moves.
    this.__trace_688(current_delta);
    // Finished processing permutation (5,6,2,1,4,3).

    // Now processing permutation (1,2,6,5,3,4).
    current_delta = ((parent_delta + this.m_dist_3_5 + this.m_dist_4_afterEnd)
        - this.m_dist_3_afterEnd - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,2,6,5,3,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,2,6,5,3,4) by sequences of 2-opt moves.

    // Now processing permutation (3,5,6,2,1,4).
    current_delta = ((current_delta + this.m_dist_beforeStart_3 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,5,6,2,1,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (3,5,6,2,1,4).
    // Finished processing permutation (1,2,6,5,3,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,2,5,4,3,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,2,5,4,3,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,2,5,4,3,6)
   */
  private final void __trace_47(final long parent_delta) {

    long current_delta;

    // Now processing permutation (1,4,5,2,3,6).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_2_3)
        - this.m_dist_1_2 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,4,5,2,3,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,4,5,2,3,6) by sequences of 2-opt moves.
    this.__trace_72(current_delta);
    // Finished processing permutation (1,4,5,2,3,6).

    // Now processing permutation (1,6,3,4,5,2).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_2_afterEnd)
        - this.m_dist_1_2 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,6,3,4,5,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,6,3,4,5,2) by sequences of 2-opt moves.
    this.__trace_187(current_delta);
    // Finished processing permutation (1,6,3,4,5,2).

    // Now processing permutation (1,3,4,5,2,6).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_3_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,3,4,5,2,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,3,4,5,2,6) by sequences of 2-opt moves.
    this.__trace_191(current_delta);
    // Finished processing permutation (1,3,4,5,2,6).

    // Now processing permutation (1,2,5,4,6,3).
    current_delta = ((parent_delta + this.m_dist_3_afterEnd + this.m_dist_4_6)
        - this.m_dist_3_4 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,2,5,4,6,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,2,5,4,6,3) by sequences of 2-opt moves.
    this.__trace_46(current_delta);
    // Finished processing permutation (1,2,5,4,6,3).

    // Now processing permutation (1,5,2,4,3,6).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,5,2,4,3,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,5,2,4,3,6) by sequences of 2-opt moves.
    this.__trace_36(current_delta);
    // Finished processing permutation (1,5,2,4,3,6).

    // Now processing permutation (6,3,4,5,2,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,3,4,5,2,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (6,3,4,5,2,1) by sequences of 2-opt moves.

    // Now processing permutation (6,3,4,1,2,5).
    current_delta = ((current_delta + this.m_dist_1_4 + this.m_dist_5_afterEnd)
        - this.m_dist_1_afterEnd - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,3,4,1,2,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (6,3,4,1,2,5).
    // Finished processing permutation (6,3,4,5,2,1).

    // Now processing permutation (3,4,5,2,1,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_3_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,4,5,2,1,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (3,4,5,2,1,6).

    // Now processing permutation (4,5,2,1,3,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,5,2,1,3,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (4,5,2,1,3,6) by sequences of 2-opt moves.
    this.__trace_623(current_delta);
    // Finished processing permutation (4,5,2,1,3,6).

    // Now processing permutation (2,1,5,4,3,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_2_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,1,5,4,3,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (2,1,5,4,3,6) by sequences of 2-opt moves.
    this.__trace_672(current_delta);
    // Finished processing permutation (2,1,5,4,3,6).

    // Now processing permutation (5,2,1,4,3,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,2,1,4,3,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (5,2,1,4,3,6) by sequences of 2-opt moves.
    this.__trace_684(current_delta);
    // Finished processing permutation (5,2,1,4,3,6).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,2,4,3,5,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,2,4,3,5,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,2,4,3,5,6)
   */
  private final void __trace_59(final long parent_delta) {

    long current_delta;

    // Now processing permutation (1,3,4,2,5,6).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,3,4,2,5,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,3,4,2,5,6) by sequences of 2-opt moves.
    this.__trace_180(current_delta);
    // Finished processing permutation (1,3,4,2,5,6).

    // Now processing permutation (1,6,5,3,4,2).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_2_afterEnd)
        - this.m_dist_1_2 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,6,5,3,4,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,6,5,3,4,2) by sequences of 2-opt moves.
    this.__trace_199(current_delta);
    // Finished processing permutation (1,6,5,3,4,2).

    // Now processing permutation (1,5,3,4,2,6).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,5,3,4,2,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,5,3,4,2,6) by sequences of 2-opt moves.
    this.__trace_203(current_delta);
    // Finished processing permutation (1,5,3,4,2,6).

    // Now processing permutation (1,2,4,6,5,3).
    current_delta = ((parent_delta + this.m_dist_3_afterEnd + this.m_dist_4_6)
        - this.m_dist_3_4 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,2,4,6,5,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,2,4,6,5,3) by sequences of 2-opt moves.
    this.__trace_50(current_delta);
    // Finished processing permutation (1,2,4,6,5,3).

    // Now processing permutation (6,5,3,4,2,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,5,3,4,2,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (6,5,3,4,2,1) by sequences of 2-opt moves.
    this.__trace_390(current_delta);
    // Finished processing permutation (6,5,3,4,2,1).

    // Now processing permutation (5,3,4,2,1,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,3,4,2,1,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (5,3,4,2,1,6) by sequences of 2-opt moves.
    this.__trace_395(current_delta);
    // Finished processing permutation (5,3,4,2,1,6).

    // Now processing permutation (3,4,2,1,5,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,4,2,1,5,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (3,4,2,1,5,6).

    // Now processing permutation (4,2,1,3,5,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,2,1,3,5,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (4,2,1,3,5,6) by sequences of 2-opt moves.
    this.__trace_600(current_delta);
    // Finished processing permutation (4,2,1,3,5,6).

    // Now processing permutation (1,4,2,3,5,6).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_2_3)
        - this.m_dist_1_2 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,4,2,3,5,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (1,4,2,3,5,6).

    // Now processing permutation (2,1,4,3,5,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_2_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,1,4,3,5,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (2,1,4,3,5,6) by sequences of 2-opt moves.
    this.__trace_660(current_delta);
    // Finished processing permutation (2,1,4,3,5,6).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,5,4,3,2,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,5,4,3,2,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,5,4,3,2,6)
   */
  private final void __trace_156(final long parent_delta) {

    long current_delta;

    // Now processing permutation (4,5,1,3,2,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,5,1,3,2,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (4,5,1,3,2,6) by sequences of 2-opt moves.
    this.__trace_143(current_delta);
    // Finished processing permutation (4,5,1,3,2,6).

    // Now processing permutation (5,1,4,3,2,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,1,4,3,2,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (5,1,4,3,2,6) by sequences of 2-opt moves.
    this.__trace_155(current_delta);
    // Finished processing permutation (5,1,4,3,2,6).

    // Now processing permutation (1,5,4,3,6,2).
    current_delta = ((parent_delta + this.m_dist_2_afterEnd + this.m_dist_3_6)
        - this.m_dist_2_3 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,5,4,3,6,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,5,4,3,6,2) by sequences of 2-opt moves.

    // Now processing permutation (3,4,5,1,6,2).
    current_delta = ((current_delta + this.m_dist_beforeStart_3 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_3_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,4,5,1,6,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (3,4,5,1,6,2).
    // Finished processing permutation (1,5,4,3,6,2).

    // Now processing permutation (1,6,2,3,4,5).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_5_afterEnd)
        - this.m_dist_1_5 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,6,2,3,4,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,6,2,3,4,5) by sequences of 2-opt moves.
    this.__trace_4(current_delta);
    // Finished processing permutation (1,6,2,3,4,5).

    // Now processing permutation (1,5,6,2,3,4).
    current_delta = ((parent_delta + this.m_dist_4_afterEnd + this.m_dist_5_6)
        - this.m_dist_4_5 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,5,6,2,3,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,5,6,2,3,4) by sequences of 2-opt moves.
    this.__trace_20(current_delta);
    // Finished processing permutation (1,5,6,2,3,4).

    // Now processing permutation (1,5,2,3,4,6).
    current_delta = ((parent_delta + this.m_dist_2_5 + this.m_dist_4_6)
        - this.m_dist_2_6 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,5,2,3,4,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,5,2,3,4,6) by sequences of 2-opt moves.

    // Now processing permutation (2,5,1,3,4,6).
    current_delta = ((current_delta + this.m_dist_beforeStart_2 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,5,1,3,4,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (2,5,1,3,4,6).
    // Finished processing permutation (1,5,2,3,4,6).

    // Now processing permutation (3,4,5,1,2,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_2)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,4,5,1,2,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (3,4,5,1,2,6).

    // Now processing permutation (1,5,4,6,2,3).
    current_delta = ((parent_delta + this.m_dist_3_afterEnd + this.m_dist_4_6)
        - this.m_dist_3_4 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,5,4,6,2,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,5,4,6,2,3) by sequences of 2-opt moves.

    // Now processing permutation (2,6,4,5,1,3).
    current_delta = ((current_delta + this.m_dist_beforeStart_2 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,6,4,5,1,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (2,6,4,5,1,3).
    // Finished processing permutation (1,5,4,6,2,3).

    // Now processing permutation (2,3,4,5,1,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_2_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,3,4,5,1,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (2,3,4,5,1,6).

    // Now processing permutation (6,2,3,4,5,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,2,3,4,5,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (6,2,3,4,5,1).

    // Now processing permutation (1,5,4,2,3,6).
    current_delta = ((parent_delta + this.m_dist_2_4 + this.m_dist_3_6)
        - this.m_dist_2_6 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,5,4,2,3,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (1,5,4,2,3,6).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,6,5,4,3,2). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,6,5,4,3,2).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,6,5,4,3,2)
   */
  private final void __trace_160(final long parent_delta) {

    long current_delta;

    // Now processing permutation (4,5,6,1,3,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,5,6,1,3,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (4,5,6,1,3,2) by sequences of 2-opt moves.
    this.__trace_140(current_delta);
    // Finished processing permutation (4,5,6,1,3,2).

    // Now processing permutation (5,6,1,4,3,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,6,1,4,3,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (5,6,1,4,3,2) by sequences of 2-opt moves.
    this.__trace_151(current_delta);
    // Finished processing permutation (5,6,1,4,3,2).

    // Now processing permutation (1,6,5,2,3,4).
    current_delta = ((parent_delta + this.m_dist_2_5 + this.m_dist_4_afterEnd)
        - this.m_dist_2_afterEnd - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,6,5,2,3,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,6,5,2,3,4) by sequences of 2-opt moves.
    this.__trace_19(current_delta);
    // Finished processing permutation (1,6,5,2,3,4).

    // Now processing permutation (6,1,5,4,3,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,1,5,4,3,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (6,1,5,4,3,2) by sequences of 2-opt moves.
    this.__trace_161(current_delta);
    // Finished processing permutation (6,1,5,4,3,2).

    // Now processing permutation (3,4,5,6,1,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_2)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,4,5,6,1,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (3,4,5,6,1,2).

    // Now processing permutation (1,6,5,4,2,3).
    current_delta = ((parent_delta + this.m_dist_2_4 + this.m_dist_3_afterEnd)
        - this.m_dist_2_afterEnd - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,6,5,4,2,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (1,6,5,4,2,3).

    // Now processing permutation (2,3,4,5,6,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_2_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,3,4,5,6,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (2,3,4,5,6,1).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,4,3,2,5,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,4,3,2,5,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,4,3,2,5,6)
   */
  private final void __trace_179(final long parent_delta) {

    long current_delta;

    // Now processing permutation (4,1,3,2,5,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,1,3,2,5,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (4,1,3,2,5,6) by sequences of 2-opt moves.
    this.__trace_120(current_delta);
    // Finished processing permutation (4,1,3,2,5,6).

    // Now processing permutation (1,4,3,5,2,6).
    current_delta = ((parent_delta + this.m_dist_2_6 + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,4,3,5,2,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,4,3,5,2,6) by sequences of 2-opt moves.

    // Now processing permutation (6,2,5,3,4,1).
    current_delta = ((current_delta + this.m_dist_beforeStart_6 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,2,5,3,4,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (6,2,5,3,4,1).
    // Finished processing permutation (1,4,3,5,2,6).

    // Now processing permutation (1,4,3,6,5,2).
    current_delta = ((parent_delta + this.m_dist_2_afterEnd + this.m_dist_3_6)
        - this.m_dist_2_3 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,4,3,6,5,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,4,3,6,5,2) by sequences of 2-opt moves.
    this.__trace_170(current_delta);
    // Finished processing permutation (1,4,3,6,5,2).

    // Now processing permutation (1,4,6,5,2,3).
    current_delta = ((parent_delta + this.m_dist_3_afterEnd + this.m_dist_4_6)
        - this.m_dist_3_4 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,4,6,5,2,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,4,6,5,2,3) by sequences of 2-opt moves.
    this.__trace_75(current_delta);
    // Finished processing permutation (1,4,6,5,2,3).

    // Now processing permutation (3,4,1,2,5,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_2)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,4,1,2,5,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (3,4,1,2,5,6).

    // Now processing permutation (6,5,2,3,4,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,5,2,3,4,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (6,5,2,3,4,1).

    // Now processing permutation (5,2,3,4,1,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,2,3,4,1,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (5,2,3,4,1,6).

    // Now processing permutation (2,3,4,1,5,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_2_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,3,4,1,5,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (2,3,4,1,5,6).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,3,2,4,5,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,3,2,4,5,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,3,2,4,5,6)
   */
  private final void __trace_239(final long parent_delta) {

    long current_delta;

    // Now processing permutation (1,3,5,4,2,6).
    current_delta = ((parent_delta + this.m_dist_2_6 + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,3,5,4,2,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,3,5,4,2,6) by sequences of 2-opt moves.
    this.__trace_192(current_delta);
    // Finished processing permutation (1,3,5,4,2,6).

    // Now processing permutation (1,3,6,5,4,2).
    current_delta = ((parent_delta + this.m_dist_2_afterEnd + this.m_dist_3_6)
        - this.m_dist_2_3 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,3,6,5,4,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,3,6,5,4,2) by sequences of 2-opt moves.
    this.__trace_195(current_delta);
    // Finished processing permutation (1,3,6,5,4,2).

    // Now processing permutation (3,1,2,4,5,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_2)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,1,2,4,5,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (3,1,2,4,5,6).

    // Now processing permutation (2,3,1,4,5,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_2_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,3,1,4,5,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (2,3,1,4,5,6).

    // Now processing permutation (6,5,4,2,3,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,5,4,2,3,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (6,5,4,2,3,1) by sequences of 2-opt moves.

    // Now processing permutation (6,1,3,2,4,5).
    current_delta = ((current_delta + this.m_dist_1_6 + this.m_dist_5_afterEnd)
        - this.m_dist_1_afterEnd - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,1,3,2,4,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (6,1,3,2,4,5).
    // Finished processing permutation (6,5,4,2,3,1).

    // Now processing permutation (5,4,2,3,1,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,4,2,3,1,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (5,4,2,3,1,6) by sequences of 2-opt moves.
    this.__trace_575(current_delta);
    // Finished processing permutation (5,4,2,3,1,6).

    // Now processing permutation (4,2,3,1,5,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,2,3,1,5,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (4,2,3,1,5,6).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (4,3,2,1,5,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (4,3,2,1,5,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (4,3,2,1,5,6)
   */
  private final void __trace_360(final long parent_delta) {

    long current_delta;

    // Now processing permutation (4,1,2,3,5,6).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_3_5)
        - this.m_dist_1_5 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,1,2,3,5,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (4,1,2,3,5,6).

    // Now processing permutation (4,5,1,2,3,6).
    current_delta = ((parent_delta + this.m_dist_3_6 + this.m_dist_4_5)
        - this.m_dist_3_4 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,5,1,2,3,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (4,5,1,2,3,6).

    // Now processing permutation (5,1,2,3,4,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_4_6)
        - this.m_dist_beforeStart_4 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,1,2,3,4,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (5,1,2,3,4,6).

    // Now processing permutation (4,6,5,1,2,3).
    current_delta = ((parent_delta + this.m_dist_3_afterEnd + this.m_dist_4_6)
        - this.m_dist_3_4 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,6,5,1,2,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (4,6,5,1,2,3) by sequences of 2-opt moves.

    // Now processing permutation (4,6,1,5,2,3).
    current_delta = ((current_delta + this.m_dist_1_6 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,6,1,5,2,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (4,6,1,5,2,3).
    // Finished processing permutation (4,6,5,1,2,3).

    // Now processing permutation (4,3,6,5,1,2).
    current_delta = ((parent_delta + this.m_dist_2_afterEnd + this.m_dist_3_6)
        - this.m_dist_2_3 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,3,6,5,1,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (4,3,6,5,1,2) by sequences of 2-opt moves.

    // Now processing permutation (4,6,3,5,1,2).
    current_delta = ((current_delta + this.m_dist_3_5 + this.m_dist_4_6)
        - this.m_dist_3_4 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,6,3,5,1,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (4,6,3,5,1,2).
    // Finished processing permutation (4,3,6,5,1,2).

    // Now processing permutation (4,3,5,1,2,6).
    current_delta = ((parent_delta + this.m_dist_2_6 + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,3,5,1,2,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (4,3,5,1,2,6) by sequences of 2-opt moves.

    // Now processing permutation (4,3,5,1,6,2).
    current_delta = ((current_delta + this.m_dist_1_6 + this.m_dist_2_afterEnd)
        - this.m_dist_1_2 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,3,5,1,6,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (4,3,5,1,6,2).
    // Finished processing permutation (4,3,5,1,2,6).

    // Now processing permutation (4,3,1,2,5,6).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_5)
        - this.m_dist_1_5 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,3,1,2,5,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (4,3,1,2,5,6).

    // Now processing permutation (4,3,2,6,5,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,3,2,6,5,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (4,3,2,6,5,1) by sequences of 2-opt moves.
    this.__trace_369(current_delta);
    // Finished processing permutation (4,3,2,6,5,1).

    // Now processing permutation (4,3,2,5,1,6).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,3,2,5,1,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (4,3,2,5,1,6).

    // Now processing permutation (6,5,1,2,3,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_4_afterEnd)
        - this.m_dist_beforeStart_4 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,5,1,2,3,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (6,5,1,2,3,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (5,4,3,2,1,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (5,4,3,2,1,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (5,4,3,2,1,6)
   */
  private final void __trace_384(final long parent_delta) {

    long current_delta;

    // Now processing permutation (6,1,2,3,4,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_5_afterEnd)
        - this.m_dist_beforeStart_5 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,1,2,3,4,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (6,1,2,3,4,5).

    // Now processing permutation (5,4,3,6,1,2).
    current_delta = ((parent_delta + this.m_dist_2_afterEnd + this.m_dist_3_6)
        - this.m_dist_2_3 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,4,3,6,1,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (5,4,3,6,1,2) by sequences of 2-opt moves.
    this.__trace_333(current_delta);
    // Finished processing permutation (5,4,3,6,1,2).

    // Now processing permutation (5,4,3,1,2,6).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_6)
        - this.m_dist_1_6 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,4,3,1,2,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (5,4,3,1,2,6).

    // Now processing permutation (5,4,3,2,6,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,4,3,2,6,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (5,4,3,2,6,1) by sequences of 2-opt moves.

    // Now processing permutation (5,4,6,2,3,1).
    current_delta = ((current_delta + this.m_dist_1_3 + this.m_dist_4_6)
        - this.m_dist_1_6 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,4,6,2,3,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (5,4,6,2,3,1).
    // Finished processing permutation (5,4,3,2,6,1).

    // Now processing permutation (5,4,6,1,2,3).
    current_delta = ((parent_delta + this.m_dist_3_afterEnd + this.m_dist_4_6)
        - this.m_dist_3_4 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,4,6,1,2,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (5,4,6,1,2,3) by sequences of 2-opt moves.

    // Now processing permutation (5,1,6,4,2,3).
    current_delta = ((current_delta + this.m_dist_1_5 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,1,6,4,2,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (5,1,6,4,2,3).
    // Finished processing permutation (5,4,6,1,2,3).

    // Now processing permutation (5,6,1,2,3,4).
    current_delta = ((parent_delta + this.m_dist_4_afterEnd + this.m_dist_5_6)
        - this.m_dist_4_5 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,6,1,2,3,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (5,6,1,2,3,4).

    // Now processing permutation (5,4,1,2,3,6).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_3_6)
        - this.m_dist_1_6 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,4,1,2,3,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (5,4,1,2,3,6).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (6,5,4,3,2,1). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (6,5,4,3,2,1).
   *
   * @param parent_delta
   *          the length change between the original tour and (6,5,4,3,2,1)
   */
  private final void __trace_389(final long parent_delta) {

    long current_delta;

    // Now processing permutation (6,5,4,1,2,3).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_3_afterEnd)
        - this.m_dist_1_afterEnd - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,5,4,1,2,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (6,5,4,1,2,3) by sequences of 2-opt moves.
    this.__trace_90(current_delta);
    // Finished processing permutation (6,5,4,1,2,3).

    // Now processing permutation (6,5,4,3,1,2).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_afterEnd)
        - this.m_dist_1_afterEnd - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,5,4,3,1,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (6,5,4,3,1,2) by sequences of 2-opt moves.

    // Now processing permutation (6,5,1,3,4,2).
    current_delta = ((current_delta + this.m_dist_1_5 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,5,1,3,4,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (6,5,1,3,4,2).
    // Finished processing permutation (6,5,4,3,1,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (3,2,1,4,5,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (3,2,1,4,5,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (3,2,1,4,5,6)
   */
  private final void __trace_479(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,6,5,4,1,2).
    current_delta = ((parent_delta + this.m_dist_2_afterEnd + this.m_dist_3_6)
        - this.m_dist_2_3 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,6,5,4,1,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (3,6,5,4,1,2) by sequences of 2-opt moves.
    this.__trace_319(current_delta);
    // Finished processing permutation (3,6,5,4,1,2).

    // Now processing permutation (3,5,4,1,2,6).
    current_delta = ((parent_delta + this.m_dist_2_6 + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,5,4,1,2,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (3,5,4,1,2,6) by sequences of 2-opt moves.

    // Now processing permutation (3,5,4,1,6,2).
    current_delta = ((current_delta + this.m_dist_1_6 + this.m_dist_2_afterEnd)
        - this.m_dist_1_2 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,5,4,1,6,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (3,5,4,1,6,2).
    // Finished processing permutation (3,5,4,1,2,6).

    // Now processing permutation (3,2,4,1,5,6).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,2,4,1,5,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (3,2,4,1,5,6) by sequences of 2-opt moves.

    // Now processing permutation (3,2,4,6,5,1).
    current_delta = ((current_delta + this.m_dist_1_afterEnd + this.m_dist_4_6)
        - this.m_dist_1_4 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,2,4,6,5,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (3,2,4,6,5,1).
    // Finished processing permutation (3,2,4,1,5,6).

    // Now processing permutation (3,2,5,4,1,6).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,2,5,4,1,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (3,2,5,4,1,6) by sequences of 2-opt moves.
    this.__trace_432(current_delta);
    // Finished processing permutation (3,2,5,4,1,6).

    // Now processing permutation (3,2,6,5,4,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,2,6,5,4,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (3,2,6,5,4,1) by sequences of 2-opt moves.
    this.__trace_435(current_delta);
    // Finished processing permutation (3,2,6,5,4,1).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (2,1,3,4,5,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (2,1,3,4,5,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (2,1,3,4,5,6)
   */
  private final void __trace_719(final long parent_delta) {

    long current_delta;

    // Now processing permutation (2,4,3,1,5,6).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,4,3,1,5,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (2,4,3,1,5,6) by sequences of 2-opt moves.
    this.__trace_540(current_delta);
    // Finished processing permutation (2,4,3,1,5,6).

    // Now processing permutation (2,6,5,4,3,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,6,5,4,3,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (2,6,5,4,3,1) by sequences of 2-opt moves.

    // Now processing permutation (5,6,2,4,3,1).
    current_delta = ((current_delta + this.m_dist_beforeStart_5 + this.m_dist_2_4)
        - this.m_dist_beforeStart_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,6,2,4,3,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (5,6,2,4,3,1).
    // Finished processing permutation (2,6,5,4,3,1).

    // Now processing permutation (2,5,4,3,1,6).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,5,4,3,1,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (2,5,4,3,1,6) by sequences of 2-opt moves.

    // Now processing permutation (2,5,4,6,1,3).
    current_delta = ((current_delta + this.m_dist_3_afterEnd + this.m_dist_4_6)
        - this.m_dist_3_4 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,5,4,6,1,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (2,5,4,6,1,3).
    // Finished processing permutation (2,5,4,3,1,6).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,2,5,6,4,3). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,2,5,6,4,3).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,2,5,6,4,3)
   */
  private final void __trace_45(final long parent_delta) {

    long current_delta;

    // Now processing permutation (1,3,4,6,5,2).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_afterEnd)
        - this.m_dist_1_2 - this.m_dist_3_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,3,4,6,5,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,3,4,6,5,2) by sequences of 2-opt moves.
    this.__trace_189(current_delta);
    // Finished processing permutation (1,3,4,6,5,2).

    // Now processing permutation (3,4,6,5,2,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_3_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,4,6,5,2,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (3,4,6,5,2,1) by sequences of 2-opt moves.
    this.__trace_404(current_delta);
    // Finished processing permutation (3,4,6,5,2,1).

    // Now processing permutation (1,5,2,6,4,3).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,5,2,6,4,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,5,2,6,4,3) by sequences of 2-opt moves.
    this.__trace_38(current_delta);
    // Finished processing permutation (1,5,2,6,4,3).

    // Now processing permutation (1,6,5,2,4,3).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,6,5,2,4,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,6,5,2,4,3) by sequences of 2-opt moves.
    this.__trace_40(current_delta);
    // Finished processing permutation (1,6,5,2,4,3).

    // Now processing permutation (4,6,5,2,1,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,6,5,2,1,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (4,6,5,2,1,3) by sequences of 2-opt moves.
    this.__trace_619(current_delta);
    // Finished processing permutation (4,6,5,2,1,3).

    // Now processing permutation (2,1,5,6,4,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_2_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,1,5,6,4,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (2,1,5,6,4,3).

    // Now processing permutation (5,2,1,6,4,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,2,1,6,4,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (5,2,1,6,4,3) by sequences of 2-opt moves.

    // Now processing permutation (5,2,4,6,1,3).
    current_delta = ((current_delta + this.m_dist_1_3 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,2,4,6,1,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (5,2,4,6,1,3).
    // Finished processing permutation (5,2,1,6,4,3).

    // Now processing permutation (6,5,2,1,4,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,5,2,1,4,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (6,5,2,1,4,3) by sequences of 2-opt moves.
    this.__trace_689(current_delta);
    // Finished processing permutation (6,5,2,1,4,3).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,2,6,4,3,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,2,6,4,3,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,2,6,4,3,5)
   */
  private final void __trace_56(final long parent_delta) {

    long current_delta;

    // Now processing permutation (1,3,4,6,2,5).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,3,4,6,2,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,3,4,6,2,5) by sequences of 2-opt moves.
    this.__trace_182(current_delta);
    // Finished processing permutation (1,3,4,6,2,5).

    // Now processing permutation (1,6,2,4,3,5).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,6,2,4,3,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,6,2,4,3,5) by sequences of 2-opt moves.
    this.__trace_55(current_delta);
    // Finished processing permutation (1,6,2,4,3,5).

    // Now processing permutation (1,5,3,4,6,2).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_afterEnd)
        - this.m_dist_1_2 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,5,3,4,6,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,5,3,4,6,2) by sequences of 2-opt moves.
    this.__trace_202(current_delta);
    // Finished processing permutation (1,5,3,4,6,2).

    // Now processing permutation (5,3,4,6,2,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,3,4,6,2,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (5,3,4,6,2,1) by sequences of 2-opt moves.
    this.__trace_393(current_delta);
    // Finished processing permutation (5,3,4,6,2,1).

    // Now processing permutation (1,2,4,6,3,5).
    current_delta = ((parent_delta + this.m_dist_2_4 + this.m_dist_3_6)
        - this.m_dist_2_6 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,2,4,6,3,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,2,4,6,3,5) by sequences of 2-opt moves.
    this.__trace_57(current_delta);
    // Finished processing permutation (1,2,4,6,3,5).

    // Now processing permutation (3,4,6,2,1,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,4,6,2,1,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (3,4,6,2,1,5) by sequences of 2-opt moves.
    this.__trace_416(current_delta);
    // Finished processing permutation (3,4,6,2,1,5).

    // Now processing permutation (4,6,2,1,3,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,6,2,1,3,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (4,6,2,1,3,5) by sequences of 2-opt moves.
    this.__trace_604(current_delta);
    // Finished processing permutation (4,6,2,1,3,5).

    // Now processing permutation (2,1,6,4,3,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_2_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,1,6,4,3,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (2,1,6,4,3,5) by sequences of 2-opt moves.
    this.__trace_663(current_delta);
    // Finished processing permutation (2,1,6,4,3,5).

    // Now processing permutation (6,2,1,4,3,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,2,1,4,3,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (6,2,1,4,3,5) by sequences of 2-opt moves.

    // Now processing permutation (6,2,4,1,3,5).
    current_delta = ((current_delta + this.m_dist_1_3 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,2,4,1,3,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (6,2,4,1,3,5).
    // Finished processing permutation (6,2,1,4,3,5).

    // Now processing permutation (1,4,6,2,3,5).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_2_3)
        - this.m_dist_1_2 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,4,6,2,3,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,4,6,2,3,5) by sequences of 2-opt moves.

    // Now processing permutation (4,1,6,2,3,5).
    current_delta = ((current_delta + this.m_dist_beforeStart_4 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,1,6,2,3,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (4,1,6,2,3,5).
    // Finished processing permutation (1,4,6,2,3,5).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,2,4,3,6,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,2,4,3,6,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,2,4,3,6,5)
   */
  private final void __trace_58(final long parent_delta) {

    long current_delta;

    // Now processing permutation (1,3,4,2,6,5).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_3_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,3,4,2,6,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,3,4,2,6,5) by sequences of 2-opt moves.
    this.__trace_181(current_delta);
    // Finished processing permutation (1,3,4,2,6,5).

    // Now processing permutation (1,6,3,4,2,5).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,6,3,4,2,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,6,3,4,2,5) by sequences of 2-opt moves.
    this.__trace_184(current_delta);
    // Finished processing permutation (1,6,3,4,2,5).

    // Now processing permutation (1,5,6,3,4,2).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_afterEnd)
        - this.m_dist_1_2 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,5,6,3,4,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (1,5,6,3,4,2).

    // Now processing permutation (5,6,3,4,2,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,6,3,4,2,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (5,6,3,4,2,1).

    // Now processing permutation (6,3,4,2,1,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,3,4,2,1,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (6,3,4,2,1,5) by sequences of 2-opt moves.
    this.__trace_414(current_delta);
    // Finished processing permutation (6,3,4,2,1,5).

    // Now processing permutation (3,4,2,1,6,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_3_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,4,2,1,6,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (3,4,2,1,6,5) by sequences of 2-opt moves.

    // Now processing permutation (3,6,1,2,4,5).
    current_delta = ((current_delta + this.m_dist_3_6 + this.m_dist_4_5)
        - this.m_dist_3_4 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,6,1,2,4,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (3,6,1,2,4,5).
    // Finished processing permutation (3,4,2,1,6,5).

    // Now processing permutation (4,2,1,3,6,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,2,1,3,6,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (4,2,1,3,6,5) by sequences of 2-opt moves.
    this.__trace_601(current_delta);
    // Finished processing permutation (4,2,1,3,6,5).

    // Now processing permutation (2,1,4,3,6,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_2_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,1,4,3,6,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (2,1,4,3,6,5) by sequences of 2-opt moves.

    // Now processing permutation (2,6,3,4,1,5).
    current_delta = ((current_delta + this.m_dist_1_5 + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,6,3,4,1,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (2,6,3,4,1,5).
    // Finished processing permutation (2,1,4,3,6,5).

    // Now processing permutation (1,4,2,3,6,5).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_2_3)
        - this.m_dist_1_2 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,4,2,3,6,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (1,4,2,3,6,5).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,5,6,4,3,2). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,5,6,4,3,2).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,5,6,4,3,2)
   */
  private final void __trace_159(final long parent_delta) {

    long current_delta;

    // Now processing permutation (1,4,6,5,3,2).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_3_5)
        - this.m_dist_1_5 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,4,6,5,3,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,4,6,5,3,2) by sequences of 2-opt moves.
    this.__trace_164(current_delta);
    // Finished processing permutation (1,4,6,5,3,2).

    // Now processing permutation (3,4,6,5,1,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_2)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,4,6,5,1,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (3,4,6,5,1,2) by sequences of 2-opt moves.
    this.__trace_315(current_delta);
    // Finished processing permutation (3,4,6,5,1,2).

    // Now processing permutation (1,5,6,4,2,3).
    current_delta = ((parent_delta + this.m_dist_2_4 + this.m_dist_3_afterEnd)
        - this.m_dist_2_afterEnd - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,5,6,4,2,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (1,5,6,4,2,3).

    // Now processing permutation (1,5,4,6,3,2).
    current_delta = ((parent_delta + this.m_dist_3_6 + this.m_dist_4_5)
        - this.m_dist_3_4 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,5,4,6,3,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,5,4,6,3,2) by sequences of 2-opt moves.
    this.__trace_158(current_delta);
    // Finished processing permutation (1,5,4,6,3,2).

    // Now processing permutation (4,6,5,1,3,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,6,5,1,3,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (4,6,5,1,3,2) by sequences of 2-opt moves.
    this.__trace_139(current_delta);
    // Finished processing permutation (4,6,5,1,3,2).

    // Now processing permutation (2,3,4,6,5,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_2_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,3,4,6,5,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (2,3,4,6,5,1).

    // Now processing permutation (6,5,1,4,3,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,5,1,4,3,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (6,5,1,4,3,2).

    // Now processing permutation (5,1,6,4,3,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,1,6,4,3,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (5,1,6,4,3,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,6,4,3,2,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,6,4,3,2,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,6,4,3,2,5)
   */
  private final void __trace_175(final long parent_delta) {

    long current_delta;

    // Now processing permutation (1,6,4,3,5,2).
    current_delta = ((parent_delta + this.m_dist_2_afterEnd + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,6,4,3,5,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,6,4,3,5,2) by sequences of 2-opt moves.
    this.__trace_172(current_delta);
    // Finished processing permutation (1,6,4,3,5,2).

    // Now processing permutation (1,6,4,5,2,3).
    current_delta = ((parent_delta + this.m_dist_3_afterEnd + this.m_dist_4_5)
        - this.m_dist_3_4 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,6,4,5,2,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (1,6,4,5,2,3).

    // Now processing permutation (3,4,6,1,2,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_2)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,4,6,1,2,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (3,4,6,1,2,5) by sequences of 2-opt moves.
    this.__trace_303(current_delta);
    // Finished processing permutation (3,4,6,1,2,5).

    // Now processing permutation (4,6,1,3,2,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,6,1,3,2,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (4,6,1,3,2,5) by sequences of 2-opt moves.
    this.__trace_124(current_delta);
    // Finished processing permutation (4,6,1,3,2,5).

    // Now processing permutation (6,1,4,3,2,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,1,4,3,2,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (6,1,4,3,2,5) by sequences of 2-opt moves.

    // Now processing permutation (6,1,4,2,3,5).
    current_delta = ((current_delta + this.m_dist_2_4 + this.m_dist_3_5)
        - this.m_dist_2_5 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,1,4,2,3,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (6,1,4,2,3,5).
    // Finished processing permutation (6,1,4,3,2,5).

    // Now processing permutation (1,4,6,3,2,5).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_3_6)
        - this.m_dist_1_6 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,4,6,3,2,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,4,6,3,2,5) by sequences of 2-opt moves.
    this.__trace_176(current_delta);
    // Finished processing permutation (1,4,6,3,2,5).

    // Now processing permutation (5,2,3,4,6,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,2,3,4,6,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (5,2,3,4,6,1) by sequences of 2-opt moves.

    // Now processing permutation (5,3,2,4,6,1).
    current_delta = ((current_delta + this.m_dist_2_4 + this.m_dist_3_5)
        - this.m_dist_2_5 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,3,2,4,6,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (5,3,2,4,6,1).
    // Finished processing permutation (5,2,3,4,6,1).

    // Now processing permutation (2,3,4,6,1,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_2_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,3,4,6,1,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (2,3,4,6,1,5) by sequences of 2-opt moves.
    this.__trace_537(current_delta);
    // Finished processing permutation (2,3,4,6,1,5).

    // Now processing permutation (1,6,4,2,3,5).
    current_delta = ((parent_delta + this.m_dist_2_4 + this.m_dist_3_5)
        - this.m_dist_2_5 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,6,4,2,3,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (1,6,4,2,3,5).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,4,3,2,6,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,4,3,2,6,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,4,3,2,6,5)
   */
  private final void __trace_178(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,4,1,2,6,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_2)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,4,1,2,6,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (3,4,1,2,6,5).

    // Now processing permutation (4,1,3,2,6,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,1,3,2,6,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (4,1,3,2,6,5) by sequences of 2-opt moves.
    this.__trace_121(current_delta);
    // Finished processing permutation (4,1,3,2,6,5).

    // Now processing permutation (5,6,2,3,4,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,6,2,3,4,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (5,6,2,3,4,1).

    // Now processing permutation (1,4,3,6,2,5).
    current_delta = ((parent_delta + this.m_dist_2_5 + this.m_dist_3_6)
        - this.m_dist_2_3 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,4,3,6,2,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (1,4,3,6,2,5).

    // Now processing permutation (6,2,3,4,1,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,2,3,4,1,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (6,2,3,4,1,5) by sequences of 2-opt moves.

    // Now processing permutation (6,2,3,5,1,4).
    current_delta = ((current_delta + this.m_dist_3_5 + this.m_dist_4_afterEnd)
        - this.m_dist_3_4 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,2,3,5,1,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (6,2,3,5,1,4).
    // Finished processing permutation (6,2,3,4,1,5).

    // Now processing permutation (2,3,4,1,6,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_2_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,3,4,1,6,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (2,3,4,1,6,5) by sequences of 2-opt moves.
    this.__trace_538(current_delta);
    // Finished processing permutation (2,3,4,1,6,5).

    // Now processing permutation (1,4,3,5,6,2).
    current_delta = ((parent_delta + this.m_dist_2_afterEnd + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,4,3,5,6,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (1,4,3,5,6,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,2,3,6,4,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,2,3,6,4,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,2,3,6,4,5)
   */
  private final void __trace_2(final long parent_delta) {

    long current_delta;

    // Now processing permutation (1,6,3,2,4,5).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,6,3,2,4,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,6,3,2,4,5) by sequences of 2-opt moves.

    // Now processing permutation (4,2,3,6,1,5).
    current_delta = ((current_delta + this.m_dist_beforeStart_4 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,2,3,6,1,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (4,2,3,6,1,5).
    // Finished processing permutation (1,6,3,2,4,5).

    // Now processing permutation (1,3,2,6,4,5).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_3_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,3,2,6,4,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (1,3,2,6,4,5).

    // Now processing permutation (4,6,3,2,1,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,6,3,2,1,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (4,6,3,2,1,5) by sequences of 2-opt moves.

    // Now processing permutation (4,6,3,2,5,1).
    current_delta = ((current_delta + this.m_dist_1_afterEnd + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,6,3,2,5,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (4,6,3,2,5,1).
    // Finished processing permutation (4,6,3,2,1,5).

    // Now processing permutation (5,4,6,3,2,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,4,6,3,2,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (5,4,6,3,2,1).

    // Now processing permutation (6,3,2,1,4,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,3,2,1,4,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (6,3,2,1,4,5).

    // Now processing permutation (3,2,1,6,4,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_3_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,2,1,6,4,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (3,2,1,6,4,5).

    // Now processing permutation (2,1,3,6,4,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,1,3,6,4,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (2,1,3,6,4,5) by sequences of 2-opt moves.
    this.__trace_717(current_delta);
    // Finished processing permutation (2,1,3,6,4,5).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,3,2,4,6,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,3,2,4,6,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,3,2,4,6,5)
   */
  private final void __trace_238(final long parent_delta) {

    long current_delta;

    // Now processing permutation (1,3,2,5,6,4).
    current_delta = ((parent_delta + this.m_dist_2_5 + this.m_dist_4_afterEnd)
        - this.m_dist_2_4 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,3,2,5,6,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (1,3,2,5,6,4).

    // Now processing permutation (3,1,2,4,6,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_2)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,1,2,4,6,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (3,1,2,4,6,5).

    // Now processing permutation (1,3,6,4,2,5).
    current_delta = ((parent_delta + this.m_dist_2_5 + this.m_dist_3_6)
        - this.m_dist_2_3 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,3,6,4,2,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,3,6,4,2,5) by sequences of 2-opt moves.
    this.__trace_183(current_delta);
    // Finished processing permutation (1,3,6,4,2,5).

    // Now processing permutation (2,3,1,4,6,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_2_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,3,1,4,6,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (2,3,1,4,6,5).

    // Now processing permutation (1,3,5,6,4,2).
    current_delta = ((parent_delta + this.m_dist_2_afterEnd + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,3,5,6,4,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,3,5,6,4,2) by sequences of 2-opt moves.
    this.__trace_194(current_delta);
    // Finished processing permutation (1,3,5,6,4,2).

    // Now processing permutation (5,6,4,2,3,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,6,4,2,3,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (5,6,4,2,3,1).

    // Now processing permutation (6,4,2,3,1,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,4,2,3,1,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (6,4,2,3,1,5) by sequences of 2-opt moves.

    // Now processing permutation (6,4,2,5,1,3).
    current_delta = ((current_delta + this.m_dist_2_5 + this.m_dist_3_afterEnd)
        - this.m_dist_2_3 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,4,2,5,1,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (6,4,2,5,1,3).
    // Finished processing permutation (6,4,2,3,1,5).

    // Now processing permutation (4,2,3,1,6,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,2,3,1,6,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (4,2,3,1,6,5) by sequences of 2-opt moves.

    // Now processing permutation (4,2,3,5,6,1).
    current_delta = ((current_delta + this.m_dist_1_afterEnd + this.m_dist_3_5)
        - this.m_dist_1_3 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,2,3,5,6,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (4,2,3,5,6,1).
    // Finished processing permutation (4,2,3,1,6,5).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,2,3,5,6,4). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,2,3,5,6,4).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,2,3,5,6,4)
   */
  private final void __trace_10(final long parent_delta) {

    long current_delta;

    // Now processing permutation (1,5,3,2,6,4).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,5,3,2,6,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,5,3,2,6,4) by sequences of 2-opt moves.

    // Now processing permutation (5,1,3,2,6,4).
    current_delta = ((current_delta + this.m_dist_beforeStart_5 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,1,3,2,6,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (5,1,3,2,6,4).
    // Finished processing permutation (1,5,3,2,6,4).

    // Now processing permutation (1,6,5,3,2,4).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,6,5,3,2,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,6,5,3,2,4) by sequences of 2-opt moves.

    // Now processing permutation (6,1,5,3,2,4).
    current_delta = ((current_delta + this.m_dist_beforeStart_6 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,1,5,3,2,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (6,1,5,3,2,4).
    // Finished processing permutation (1,6,5,3,2,4).

    // Now processing permutation (4,6,5,3,2,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_4_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,6,5,3,2,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (4,6,5,3,2,1) by sequences of 2-opt moves.

    // Now processing permutation (4,6,3,5,2,1).
    current_delta = ((current_delta + this.m_dist_2_5 + this.m_dist_3_6)
        - this.m_dist_2_3 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,6,3,5,2,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (4,6,3,5,2,1).
    // Finished processing permutation (4,6,5,3,2,1).

    // Now processing permutation (1,2,5,3,6,4).
    current_delta = ((parent_delta + this.m_dist_2_5 + this.m_dist_3_6)
        - this.m_dist_2_3 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,2,5,3,6,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,2,5,3,6,4) by sequences of 2-opt moves.
    this.__trace_13(current_delta);
    // Finished processing permutation (1,2,5,3,6,4).

    // Now processing permutation (6,5,3,2,1,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,5,3,2,1,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (6,5,3,2,1,4).

    // Now processing permutation (5,3,2,1,6,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,3,2,1,6,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (5,3,2,1,6,4) by sequences of 2-opt moves.

    // Now processing permutation (5,3,2,6,1,4).
    current_delta = ((current_delta + this.m_dist_1_4 + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,3,2,6,1,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (5,3,2,6,1,4).
    // Finished processing permutation (5,3,2,1,6,4).

    // Now processing permutation (3,2,1,5,6,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,2,1,5,6,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (3,2,1,5,6,4) by sequences of 2-opt moves.

    // Now processing permutation (3,2,5,1,6,4).
    current_delta = ((current_delta + this.m_dist_1_6 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,2,5,1,6,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (3,2,5,1,6,4).
    // Finished processing permutation (3,2,1,5,6,4).

    // Now processing permutation (2,1,3,5,6,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,1,3,5,6,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (2,1,3,5,6,4) by sequences of 2-opt moves.
    this.__trace_709(current_delta);
    // Finished processing permutation (2,1,3,5,6,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (4,3,2,1,6,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (4,3,2,1,6,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (4,3,2,1,6,5)
   */
  private final void __trace_361(final long parent_delta) {

    long current_delta;

    // Now processing permutation (4,3,5,6,1,2).
    current_delta = ((parent_delta + this.m_dist_2_afterEnd + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,3,5,6,1,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (4,3,5,6,1,2).

    // Now processing permutation (4,3,6,1,2,5).
    current_delta = ((parent_delta + this.m_dist_2_5 + this.m_dist_3_6)
        - this.m_dist_2_3 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,3,6,1,2,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (4,3,6,1,2,5).

    // Now processing permutation (4,3,1,2,6,5).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_6)
        - this.m_dist_1_6 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,3,1,2,6,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (4,3,1,2,6,5).

    // Now processing permutation (4,3,2,6,1,5).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,3,2,6,1,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (4,3,2,6,1,5).

    // Now processing permutation (4,3,2,5,6,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,3,2,5,6,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (4,3,2,5,6,1) by sequences of 2-opt moves.

    // Now processing permutation (4,3,5,2,6,1).
    current_delta = ((current_delta + this.m_dist_2_6 + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,3,5,2,6,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (4,3,5,2,6,1).
    // Finished processing permutation (4,3,2,5,6,1).

    // Now processing permutation (4,1,2,3,6,5).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_3_6)
        - this.m_dist_1_6 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,1,2,3,6,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (4,1,2,3,6,5).

    // Now processing permutation (4,6,1,2,3,5).
    current_delta = ((parent_delta + this.m_dist_3_5 + this.m_dist_4_6)
        - this.m_dist_3_4 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,6,1,2,3,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (4,6,1,2,3,5).

    // Now processing permutation (4,5,6,1,2,3).
    current_delta = ((parent_delta + this.m_dist_3_afterEnd + this.m_dist_4_5)
        - this.m_dist_3_4 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,5,6,1,2,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (4,5,6,1,2,3).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (6,4,3,2,1,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (6,4,3,2,1,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (6,4,3,2,1,5)
   */
  private final void __trace_365(final long parent_delta) {

    long current_delta;

    // Now processing permutation (6,4,3,5,1,2).
    current_delta = ((parent_delta + this.m_dist_2_afterEnd + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,4,3,5,1,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (6,4,3,5,1,2).

    // Now processing permutation (6,4,3,1,2,5).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_5)
        - this.m_dist_1_5 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,4,3,1,2,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (6,4,3,1,2,5).

    // Now processing permutation (6,4,3,2,5,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,4,3,2,5,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (6,4,3,2,5,1).

    // Now processing permutation (6,4,5,1,2,3).
    current_delta = ((parent_delta + this.m_dist_3_afterEnd + this.m_dist_4_5)
        - this.m_dist_3_4 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,4,5,1,2,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (6,4,5,1,2,3).

    // Now processing permutation (6,4,1,2,3,5).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_3_5)
        - this.m_dist_1_5 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,4,1,2,3,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (6,4,1,2,3,5).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (5,6,4,3,2,1). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (5,6,4,3,2,1).
   *
   * @param parent_delta
   *          the length change between the original tour and (5,6,4,3,2,1)
   */
  private final void __trace_388(final long parent_delta) {

    long current_delta;

    // Now processing permutation (5,6,4,1,2,3).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_3_afterEnd)
        - this.m_dist_1_afterEnd - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,6,4,1,2,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (5,6,4,1,2,3).

    // Now processing permutation (5,6,4,3,1,2).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_afterEnd)
        - this.m_dist_1_afterEnd - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,6,4,3,1,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (5,6,4,3,1,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (3,2,1,4,6,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (3,2,1,4,6,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (3,2,1,4,6,5)
   */
  private final void __trace_478(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,6,4,1,2,5).
    current_delta = ((parent_delta + this.m_dist_2_5 + this.m_dist_3_6)
        - this.m_dist_2_3 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,6,4,1,2,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (3,6,4,1,2,5) by sequences of 2-opt moves.

    // Now processing permutation (3,6,4,1,5,2).
    current_delta = ((current_delta + this.m_dist_1_5 + this.m_dist_2_afterEnd)
        - this.m_dist_1_2 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,6,4,1,5,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (3,6,4,1,5,2).
    // Finished processing permutation (3,6,4,1,2,5).

    // Now processing permutation (3,5,6,4,1,2).
    current_delta = ((parent_delta + this.m_dist_2_afterEnd + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,5,6,4,1,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (3,5,6,4,1,2).

    // Now processing permutation (3,2,4,1,6,5).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,2,4,1,6,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (3,2,4,1,6,5).

    // Now processing permutation (3,2,6,4,1,5).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,2,6,4,1,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (3,2,6,4,1,5) by sequences of 2-opt moves.

    // Now processing permutation (3,6,2,4,1,5).
    current_delta = ((current_delta + this.m_dist_2_4 + this.m_dist_3_6)
        - this.m_dist_2_3 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,6,2,4,1,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (3,6,2,4,1,5).
    // Finished processing permutation (3,2,6,4,1,5).

    // Now processing permutation (3,2,5,6,4,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,2,5,6,4,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (3,2,5,6,4,1) by sequences of 2-opt moves.
    this.__trace_434(current_delta);
    // Finished processing permutation (3,2,5,6,4,1).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (2,1,3,4,6,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (2,1,3,4,6,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (2,1,3,4,6,5)
   */
  private final void __trace_718(final long parent_delta) {

    long current_delta;

    // Now processing permutation (2,4,3,1,6,5).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,4,3,1,6,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (2,4,3,1,6,5).

    // Now processing permutation (2,6,4,3,1,5).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,6,4,3,1,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (2,6,4,3,1,5).

    // Now processing permutation (2,5,6,4,3,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,5,6,4,3,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (2,5,6,4,3,1).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,2,4,5,6,3). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,2,4,5,6,3).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,2,4,5,6,3)
   */
  private final void __trace_49(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,6,5,4,2,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_3_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,6,5,4,2,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (3,6,5,4,2,1) by sequences of 2-opt moves.

    // Now processing permutation (3,6,2,4,5,1).
    current_delta = ((current_delta + this.m_dist_1_5 + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,6,2,4,5,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (3,6,2,4,5,1).
    // Finished processing permutation (3,6,5,4,2,1).

    // Now processing permutation (1,4,2,5,6,3).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,4,2,5,6,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,4,2,5,6,3) by sequences of 2-opt moves.
    this.__trace_70(current_delta);
    // Finished processing permutation (1,4,2,5,6,3).

    // Now processing permutation (1,5,4,2,6,3).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,5,4,2,6,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,5,4,2,6,3) by sequences of 2-opt moves.
    this.__trace_82(current_delta);
    // Finished processing permutation (1,5,4,2,6,3).

    // Now processing permutation (4,2,1,5,6,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,2,1,5,6,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (4,2,1,5,6,3) by sequences of 2-opt moves.
    this.__trace_610(current_delta);
    // Finished processing permutation (4,2,1,5,6,3).

    // Now processing permutation (5,4,2,1,6,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,4,2,1,6,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (5,4,2,1,6,3) by sequences of 2-opt moves.

    // Now processing permutation (6,1,2,4,5,3).
    current_delta = ((current_delta + this.m_dist_beforeStart_6 + this.m_dist_3_5)
        - this.m_dist_beforeStart_5 - this.m_dist_3_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,1,2,4,5,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (6,1,2,4,5,3).
    // Finished processing permutation (5,4,2,1,6,3).

    // Now processing permutation (6,5,4,2,1,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,5,4,2,1,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (6,5,4,2,1,3).

    // Now processing permutation (2,1,4,5,6,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_2_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,1,4,5,6,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (2,1,4,5,6,3) by sequences of 2-opt moves.

    // Now processing permutation (2,5,4,1,6,3).
    current_delta = ((current_delta + this.m_dist_1_6 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,5,4,1,6,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (2,5,4,1,6,3).
    // Finished processing permutation (2,1,4,5,6,3).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,2,6,3,5,4). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,2,6,3,5,4).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,2,6,3,5,4)
   */
  private final void __trace_8(final long parent_delta) {

    long current_delta;

    // Now processing permutation (4,5,3,6,2,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_4_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,5,3,6,2,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (4,5,3,6,2,1) by sequences of 2-opt moves.
    this.__trace_381(current_delta);
    // Finished processing permutation (4,5,3,6,2,1).

    // Now processing permutation (1,4,5,3,6,2).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_2_afterEnd)
        - this.m_dist_1_2 - this.m_dist_4_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,4,5,3,6,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,4,5,3,6,2) by sequences of 2-opt moves.
    this.__trace_166(current_delta);
    // Finished processing permutation (1,4,5,3,6,2).

    // Now processing permutation (1,6,2,3,5,4).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_2_3)
        - this.m_dist_1_2 - this.m_dist_3_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,6,2,3,5,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,6,2,3,5,4) by sequences of 2-opt moves.

    // Now processing permutation (2,6,1,3,5,4).
    current_delta = ((current_delta + this.m_dist_beforeStart_2 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,6,1,3,5,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (2,6,1,3,5,4).
    // Finished processing permutation (1,6,2,3,5,4).

    // Now processing permutation (5,3,6,2,1,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,3,6,2,1,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (5,3,6,2,1,4) by sequences of 2-opt moves.
    this.__trace_452(current_delta);
    // Finished processing permutation (5,3,6,2,1,4).

    // Now processing permutation (3,6,2,1,5,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,6,2,1,5,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (3,6,2,1,5,4) by sequences of 2-opt moves.

    // Now processing permutation (3,6,2,5,1,4).
    current_delta = ((current_delta + this.m_dist_1_4 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,6,2,5,1,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (3,6,2,5,1,4).
    // Finished processing permutation (3,6,2,1,5,4).

    // Now processing permutation (1,3,6,2,5,4).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,3,6,2,5,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,3,6,2,5,4) by sequences of 2-opt moves.

    // Now processing permutation (3,1,6,2,5,4).
    current_delta = ((current_delta + this.m_dist_beforeStart_3 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_3_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,1,6,2,5,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (3,1,6,2,5,4).
    // Finished processing permutation (1,3,6,2,5,4).

    // Now processing permutation (1,5,3,6,2,4).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,5,3,6,2,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,5,3,6,2,4) by sequences of 2-opt moves.
    this.__trace_218(current_delta);
    // Finished processing permutation (1,5,3,6,2,4).

    // Now processing permutation (2,1,6,3,5,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_2_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,1,6,3,5,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (2,1,6,3,5,4) by sequences of 2-opt moves.
    this.__trace_711(current_delta);
    // Finished processing permutation (2,1,6,3,5,4).

    // Now processing permutation (6,2,1,3,5,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,2,1,3,5,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (6,2,1,3,5,4) by sequences of 2-opt moves.

    // Now processing permutation (6,2,5,3,1,4).
    current_delta = ((current_delta + this.m_dist_1_4 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,2,5,3,1,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (6,2,5,3,1,4).
    // Finished processing permutation (6,2,1,3,5,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,4,5,6,3,2). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,4,5,6,3,2).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,4,5,6,3,2)
   */
  private final void __trace_165(final long parent_delta) {

    long current_delta;

    // Now processing permutation (5,4,1,6,3,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,4,1,6,3,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (5,4,1,6,3,2) by sequences of 2-opt moves.

    // Now processing permutation (5,3,6,1,4,2).
    current_delta = ((current_delta + this.m_dist_2_4 + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,3,6,1,4,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (5,3,6,1,4,2).
    // Finished processing permutation (5,4,1,6,3,2).

    // Now processing permutation (2,3,6,5,4,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_2_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,3,6,5,4,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (2,3,6,5,4,1).

    // Now processing permutation (6,5,4,1,3,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,5,4,1,3,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (6,5,4,1,3,2) by sequences of 2-opt moves.

    // Now processing permutation (6,3,1,4,5,2).
    current_delta = ((current_delta + this.m_dist_2_5 + this.m_dist_3_6)
        - this.m_dist_2_3 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,3,1,4,5,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (6,3,1,4,5,2).
    // Finished processing permutation (6,5,4,1,3,2).

    // Now processing permutation (4,1,5,6,3,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,1,5,6,3,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (4,1,5,6,3,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,5,6,3,2,4). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,5,6,3,2,4).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,5,6,3,2,4)
   */
  private final void __trace_219(final long parent_delta) {

    long current_delta;

    // Now processing permutation (5,1,6,3,2,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,1,6,3,2,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (5,1,6,3,2,4) by sequences of 2-opt moves.

    // Now processing permutation (3,6,1,5,2,4).
    current_delta = ((current_delta + this.m_dist_beforeStart_3 + this.m_dist_2_5)
        - this.m_dist_beforeStart_5 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,6,1,5,2,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (3,6,1,5,2,4).
    // Finished processing permutation (5,1,6,3,2,4).

    // Now processing permutation (1,3,6,5,2,4).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_5)
        - this.m_dist_1_5 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,3,6,5,2,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,3,6,5,2,4) by sequences of 2-opt moves.
    this.__trace_224(current_delta);
    // Finished processing permutation (1,3,6,5,2,4).

    // Now processing permutation (2,3,6,5,1,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_2_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,3,6,5,1,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (2,3,6,5,1,4).

    // Now processing permutation (3,6,5,1,2,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_2)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,6,5,1,2,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (3,6,5,1,2,4) by sequences of 2-opt moves.

    // Now processing permutation (3,1,5,6,2,4).
    current_delta = ((current_delta + this.m_dist_1_3 + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_3_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,1,5,6,2,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (3,1,5,6,2,4).
    // Finished processing permutation (3,6,5,1,2,4).

    // Now processing permutation (4,2,3,6,5,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_4_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,2,3,6,5,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (4,2,3,6,5,1).

    // Now processing permutation (6,5,1,3,2,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,5,1,3,2,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (6,5,1,3,2,4).

    // Now processing permutation (1,5,2,3,6,4).
    current_delta = ((parent_delta + this.m_dist_2_5 + this.m_dist_4_6)
        - this.m_dist_2_4 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,5,2,3,6,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,5,2,3,6,4) by sequences of 2-opt moves.

    // Now processing permutation (2,5,1,3,6,4).
    current_delta = ((current_delta + this.m_dist_beforeStart_2 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,5,1,3,6,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (2,5,1,3,6,4).
    // Finished processing permutation (1,5,2,3,6,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,3,2,6,5,4). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,3,2,6,5,4).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,3,2,6,5,4)
   */
  private final void __trace_230(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,1,2,6,5,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_2)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,1,2,6,5,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (3,1,2,6,5,4).

    // Now processing permutation (6,2,3,1,5,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,2,3,1,5,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (6,2,3,1,5,4) by sequences of 2-opt moves.

    // Now processing permutation (6,2,5,1,3,4).
    current_delta = ((current_delta + this.m_dist_2_5 + this.m_dist_3_4)
        - this.m_dist_2_3 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,2,5,1,3,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (6,2,5,1,3,4).
    // Finished processing permutation (6,2,3,1,5,4).

    // Now processing permutation (2,3,1,6,5,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_2_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,3,1,6,5,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (2,3,1,6,5,4).

    // Now processing permutation (5,6,2,3,1,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,6,2,3,1,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (5,6,2,3,1,4).

    // Now processing permutation (1,3,5,6,2,4).
    current_delta = ((parent_delta + this.m_dist_2_4 + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,3,5,6,2,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,3,5,6,2,4) by sequences of 2-opt moves.

    // Now processing permutation (5,3,1,6,2,4).
    current_delta = ((current_delta + this.m_dist_beforeStart_5 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,3,1,6,2,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (5,3,1,6,2,4).
    // Finished processing permutation (1,3,5,6,2,4).

    // Now processing permutation (4,5,6,2,3,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_4_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,5,6,2,3,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (4,5,6,2,3,1).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,6,3,2,5,4). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,6,3,2,5,4).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,6,3,2,5,4)
   */
  private final void __trace_232(final long parent_delta) {

    long current_delta;

    // Now processing permutation (6,1,3,2,5,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,1,3,2,5,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (6,1,3,2,5,4) by sequences of 2-opt moves.

    // Now processing permutation (6,1,3,5,2,4).
    current_delta = ((current_delta + this.m_dist_2_4 + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,1,3,5,2,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (6,1,3,5,2,4).
    // Finished processing permutation (6,1,3,2,5,4).

    // Now processing permutation (1,6,3,5,2,4).
    current_delta = ((parent_delta + this.m_dist_2_4 + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,6,3,5,2,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,6,3,5,2,4) by sequences of 2-opt moves.

    // Now processing permutation (4,2,5,3,6,1).
    current_delta = ((current_delta + this.m_dist_beforeStart_4 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_4_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,2,5,3,6,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (4,2,5,3,6,1).
    // Finished processing permutation (1,6,3,5,2,4).

    // Now processing permutation (2,3,6,1,5,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_2_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,3,6,1,5,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (2,3,6,1,5,4) by sequences of 2-opt moves.

    // Now processing permutation (2,5,1,6,3,4).
    current_delta = ((current_delta + this.m_dist_2_5 + this.m_dist_3_4)
        - this.m_dist_2_3 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,5,1,6,3,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (2,5,1,6,3,4).
    // Finished processing permutation (2,3,6,1,5,4).

    // Now processing permutation (5,2,3,6,1,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,2,3,6,1,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (5,2,3,6,1,4).

    // Now processing permutation (4,5,2,3,6,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_4_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,5,2,3,6,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (4,5,2,3,6,1).

    // Now processing permutation (3,6,1,2,5,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_2)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,6,1,2,5,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (3,6,1,2,5,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (4,5,6,3,2,1). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (4,5,6,3,2,1).
   *
   * @param parent_delta
   *          the length change between the original tour and (4,5,6,3,2,1)
   */
  private final void __trace_380(final long parent_delta) {

    long current_delta;

    // Now processing permutation (4,5,6,3,1,2).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_afterEnd)
        - this.m_dist_1_afterEnd - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,5,6,3,1,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (4,5,6,3,1,2).

    // Now processing permutation (4,3,6,5,2,1).
    current_delta = ((parent_delta + this.m_dist_2_5 + this.m_dist_3_4)
        - this.m_dist_2_3 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,3,6,5,2,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (4,3,6,5,2,1).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (5,6,3,2,1,4). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (5,6,3,2,1,4).
   *
   * @param parent_delta
   *          the length change between the original tour and (5,6,3,2,1,4)
   */
  private final void __trace_451(final long parent_delta) {

    long current_delta;

    // Now processing permutation (5,6,3,4,1,2).
    current_delta = ((parent_delta + this.m_dist_2_afterEnd + this.m_dist_3_4)
        - this.m_dist_2_3 - this.m_dist_4_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,6,3,4,1,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (5,6,3,4,1,2).

    // Now processing permutation (5,1,2,3,6,4).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_4_6)
        - this.m_dist_1_4 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,1,2,3,6,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (5,1,2,3,6,4).

    // Now processing permutation (5,6,3,2,4,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,6,3,2,4,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (5,6,3,2,4,1).

    // Now processing permutation (3,6,5,2,1,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_2_5)
        - this.m_dist_beforeStart_5 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,6,5,2,1,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (3,6,5,2,1,4).

    // Now processing permutation (5,6,3,1,2,4).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_4)
        - this.m_dist_1_4 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,6,3,1,2,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (5,6,3,1,2,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (3,2,1,6,5,4). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (3,2,1,6,5,4).
   *
   * @param parent_delta
   *          the length change between the original tour and (3,2,1,6,5,4)
   */
  private final void __trace_470(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,2,4,5,6,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,2,4,5,6,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (3,2,4,5,6,1) by sequences of 2-opt moves.

    // Now processing permutation (3,5,4,2,6,1).
    current_delta = ((current_delta + this.m_dist_2_6 + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,5,4,2,6,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (3,5,4,2,6,1).
    // Finished processing permutation (3,2,4,5,6,1).

    // Now processing permutation (3,2,5,6,1,4).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,2,5,6,1,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (3,2,5,6,1,4) by sequences of 2-opt moves.

    // Now processing permutation (3,5,2,6,1,4).
    current_delta = ((current_delta + this.m_dist_2_6 + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,5,2,6,1,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (3,5,2,6,1,4).
    // Finished processing permutation (3,2,5,6,1,4).

    // Now processing permutation (3,2,6,1,5,4).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,2,6,1,5,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (3,2,6,1,5,4).

    // Now processing permutation (6,1,2,3,5,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_3_5)
        - this.m_dist_beforeStart_3 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,1,2,3,5,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (6,1,2,3,5,4) by sequences of 2-opt moves.

    // Now processing permutation (6,1,2,5,3,4).
    current_delta = ((current_delta + this.m_dist_2_5 + this.m_dist_3_4)
        - this.m_dist_2_3 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,1,2,5,3,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (6,1,2,5,3,4).
    // Finished processing permutation (6,1,2,3,5,4).

    // Now processing permutation (3,5,6,1,2,4).
    current_delta = ((parent_delta + this.m_dist_2_4 + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,5,6,1,2,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (3,5,6,1,2,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (6,3,2,1,5,4). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (6,3,2,1,5,4).
   *
   * @param parent_delta
   *          the length change between the original tour and (6,3,2,1,5,4)
   */
  private final void __trace_473(final long parent_delta) {

    long current_delta;

    // Now processing permutation (6,3,4,5,1,2).
    current_delta = ((parent_delta + this.m_dist_2_afterEnd + this.m_dist_3_4)
        - this.m_dist_2_3 - this.m_dist_4_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,3,4,5,1,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (6,3,4,5,1,2).

    // Now processing permutation (6,3,5,1,2,4).
    current_delta = ((parent_delta + this.m_dist_2_4 + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,3,5,1,2,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (6,3,5,1,2,4) by sequences of 2-opt moves.

    // Now processing permutation (6,3,5,1,4,2).
    current_delta = ((current_delta + this.m_dist_1_4 + this.m_dist_2_afterEnd)
        - this.m_dist_1_2 - this.m_dist_4_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,3,5,1,4,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (6,3,5,1,4,2).
    // Finished processing permutation (6,3,5,1,2,4).

    // Now processing permutation (6,3,2,4,5,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,3,2,4,5,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (6,3,2,4,5,1).

    // Now processing permutation (6,3,2,5,1,4).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,3,2,5,1,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (6,3,2,5,1,4).

    // Now processing permutation (6,3,1,2,5,4).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_5)
        - this.m_dist_1_5 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,3,1,2,5,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (6,3,1,2,5,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,2,5,6,3,4). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,2,5,6,3,4).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,2,5,6,3,4)
   */
  private final void __trace_14(final long parent_delta) {

    long current_delta;

    // Now processing permutation (1,5,2,6,3,4).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,5,2,6,3,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (1,5,2,6,3,4).

    // Now processing permutation (6,5,2,1,3,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,5,2,1,3,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (6,5,2,1,3,4).

    // Now processing permutation (5,2,1,6,3,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,2,1,6,3,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (5,2,1,6,3,4) by sequences of 2-opt moves.

    // Now processing permutation (5,2,6,1,3,4).
    current_delta = ((current_delta + this.m_dist_1_3 + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_3_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,2,6,1,3,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (5,2,6,1,3,4).
    // Finished processing permutation (5,2,1,6,3,4).

    // Now processing permutation (2,1,5,6,3,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_2_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,1,5,6,3,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (2,1,5,6,3,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (2,1,3,6,5,4). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (2,1,3,6,5,4).
   *
   * @param parent_delta
   *          the length change between the original tour and (2,1,3,6,5,4)
   */
  private final void __trace_710(final long parent_delta) {

    long current_delta;

    // Now processing permutation (2,6,3,1,5,4).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,6,3,1,5,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (2,6,3,1,5,4).

    // Now processing permutation (2,5,6,3,1,4).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,5,6,3,1,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (2,5,6,3,1,4).

    // Now processing permutation (2,4,5,6,3,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,4,5,6,3,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (2,4,5,6,3,1) by sequences of 2-opt moves.

    // Now processing permutation (5,4,2,6,3,1).
    current_delta = ((current_delta + this.m_dist_beforeStart_5 + this.m_dist_2_6)
        - this.m_dist_beforeStart_2 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,4,2,6,3,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (5,4,2,6,3,1).
    // Finished processing permutation (2,4,5,6,3,1).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,2,4,5,3,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,2,4,5,3,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,2,4,5,3,6)
   */
  private final void __trace_48(final long parent_delta) {

    long current_delta;

    // Now processing permutation (1,6,3,5,4,2).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_2_afterEnd)
        - this.m_dist_1_2 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,6,3,5,4,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,6,3,5,4,2) by sequences of 2-opt moves.
    this.__trace_196(current_delta);
    // Finished processing permutation (1,6,3,5,4,2).

    // Now processing permutation (3,5,4,2,1,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_3_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,5,4,2,1,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (3,5,4,2,1,6).

    // Now processing permutation (6,3,5,4,2,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,3,5,4,2,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (6,3,5,4,2,1) by sequences of 2-opt moves.

    // Now processing permutation (6,3,5,2,4,1).
    current_delta = ((current_delta + this.m_dist_1_4 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,3,5,2,4,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (6,3,5,2,4,1).
    // Finished processing permutation (6,3,5,4,2,1).

    // Now processing permutation (1,4,2,5,3,6).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,4,2,5,3,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,4,2,5,3,6) by sequences of 2-opt moves.

    // Now processing permutation (2,4,1,5,3,6).
    current_delta = ((current_delta + this.m_dist_beforeStart_2 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_2_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,4,1,5,3,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (2,4,1,5,3,6).
    // Finished processing permutation (1,4,2,5,3,6).

    // Now processing permutation (4,2,1,5,3,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,2,1,5,3,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (4,2,1,5,3,6).

    // Now processing permutation (5,4,2,1,3,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,4,2,1,3,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (5,4,2,1,3,6).

    // Now processing permutation (2,1,4,5,3,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_2_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,1,4,5,3,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (2,1,4,5,3,6).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,2,6,4,5,3). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,2,6,4,5,3).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,2,6,4,5,3)
   */
  private final void __trace_51(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,5,4,6,2,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_3_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,5,4,6,2,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (3,5,4,6,2,1).

    // Now processing permutation (1,4,6,2,5,3).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,4,6,2,5,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,4,6,2,5,3) by sequences of 2-opt moves.
    this.__trace_68(current_delta);
    // Finished processing permutation (1,4,6,2,5,3).

    // Now processing permutation (1,6,2,4,5,3).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,6,2,4,5,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,6,2,4,5,3) by sequences of 2-opt moves.
    this.__trace_52(current_delta);
    // Finished processing permutation (1,6,2,4,5,3).

    // Now processing permutation (1,3,5,4,6,2).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_afterEnd)
        - this.m_dist_1_2 - this.m_dist_3_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,3,5,4,6,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,3,5,4,6,2) by sequences of 2-opt moves.
    this.__trace_193(current_delta);
    // Finished processing permutation (1,3,5,4,6,2).

    // Now processing permutation (4,6,2,1,5,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,6,2,1,5,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (4,6,2,1,5,3).

    // Now processing permutation (5,4,6,2,1,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,4,6,2,1,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (5,4,6,2,1,3).

    // Now processing permutation (6,2,1,4,5,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,2,1,4,5,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (6,2,1,4,5,3) by sequences of 2-opt moves.
    this.__trace_666(current_delta);
    // Finished processing permutation (6,2,1,4,5,3).

    // Now processing permutation (2,1,6,4,5,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_2_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,1,6,4,5,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (2,1,6,4,5,3) by sequences of 2-opt moves.

    // Now processing permutation (2,4,6,1,5,3).
    current_delta = ((current_delta + this.m_dist_1_5 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,4,6,1,5,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (2,4,6,1,5,3).
    // Finished processing permutation (2,1,6,4,5,3).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,6,4,5,3,2). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,6,4,5,3,2).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,6,4,5,3,2)
   */
  private final void __trace_163(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,5,4,6,1,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_2)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,5,4,6,1,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (3,5,4,6,1,2) by sequences of 2-opt moves.
    this.__trace_321(current_delta);
    // Finished processing permutation (3,5,4,6,1,2).

    // Now processing permutation (4,6,1,5,3,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,6,1,5,3,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (4,6,1,5,3,2).

    // Now processing permutation (5,4,6,1,3,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,4,6,1,3,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (5,4,6,1,3,2).

    // Now processing permutation (2,3,5,4,6,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_2_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,3,5,4,6,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (2,3,5,4,6,1).

    // Now processing permutation (6,1,4,5,3,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,1,4,5,3,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (6,1,4,5,3,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,4,5,3,2,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,4,5,3,2,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,4,5,3,2,6)
   */
  private final void __trace_167(final long parent_delta) {

    long current_delta;

    // Now processing permutation (4,1,5,3,2,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,1,5,3,2,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (4,1,5,3,2,6).

    // Now processing permutation (6,2,3,5,4,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,2,3,5,4,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (6,2,3,5,4,1).

    // Now processing permutation (2,3,5,4,1,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_2_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,3,5,4,1,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (2,3,5,4,1,6).

    // Now processing permutation (5,4,1,3,2,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,4,1,3,2,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (5,4,1,3,2,6).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,5,3,2,4,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,5,3,2,4,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,5,3,2,4,6)
   */
  private final void __trace_216(final long parent_delta) {

    long current_delta;

    // Now processing permutation (1,3,5,2,4,6).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_5)
        - this.m_dist_1_5 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,3,5,2,4,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,3,5,2,4,6) by sequences of 2-opt moves.
    this.__trace_227(current_delta);
    // Finished processing permutation (1,3,5,2,4,6).

    // Now processing permutation (3,5,1,2,4,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_2)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,5,1,2,4,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (3,5,1,2,4,6).

    // Now processing permutation (5,1,3,2,4,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,1,3,2,4,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (5,1,3,2,4,6) by sequences of 2-opt moves.

    // Now processing permutation (5,1,3,6,4,2).
    current_delta = ((current_delta + this.m_dist_2_afterEnd + this.m_dist_3_6)
        - this.m_dist_2_3 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,1,3,6,4,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (5,1,3,6,4,2).
    // Finished processing permutation (5,1,3,2,4,6).

    // Now processing permutation (1,5,3,6,4,2).
    current_delta = ((parent_delta + this.m_dist_2_afterEnd + this.m_dist_3_6)
        - this.m_dist_2_3 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,5,3,6,4,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,5,3,6,4,2) by sequences of 2-opt moves.

    // Now processing permutation (2,4,6,3,5,1).
    current_delta = ((current_delta + this.m_dist_beforeStart_2 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_2_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,4,6,3,5,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (2,4,6,3,5,1).
    // Finished processing permutation (1,5,3,6,4,2).

    // Now processing permutation (2,3,5,1,4,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_2_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,3,5,1,4,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (2,3,5,1,4,6).

    // Now processing permutation (4,2,3,5,1,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,2,3,5,1,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (4,2,3,5,1,6).

    // Now processing permutation (6,4,2,3,5,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,4,2,3,5,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (6,4,2,3,5,1).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,3,2,5,4,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,3,2,5,4,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,3,2,5,4,6)
   */
  private final void __trace_228(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,1,2,5,4,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_2)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,1,2,5,4,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (3,1,2,5,4,6).

    // Now processing permutation (2,3,1,5,4,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_2_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,3,1,5,4,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (2,3,1,5,4,6).

    // Now processing permutation (5,2,3,1,4,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,2,3,1,4,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (5,2,3,1,4,6).

    // Now processing permutation (4,5,2,3,1,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,5,2,3,1,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (4,5,2,3,1,6).

    // Now processing permutation (6,4,5,2,3,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,4,5,2,3,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (6,4,5,2,3,1).

    // Now processing permutation (1,3,6,4,5,2).
    current_delta = ((parent_delta + this.m_dist_2_afterEnd + this.m_dist_3_6)
        - this.m_dist_2_3 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,3,6,4,5,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (1,3,6,4,5,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (6,4,5,3,2,1). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (6,4,5,3,2,1).
   *
   * @param parent_delta
   *          the length change between the original tour and (6,4,5,3,2,1)
   */
  private final void __trace_378(final long parent_delta) {

    long current_delta;

    // Now processing permutation (6,4,3,5,2,1).
    current_delta = ((parent_delta + this.m_dist_2_5 + this.m_dist_3_4)
        - this.m_dist_2_3 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,4,3,5,2,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (6,4,3,5,2,1).

    // Now processing permutation (6,4,5,3,1,2).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_afterEnd)
        - this.m_dist_1_afterEnd - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,4,5,3,1,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (6,4,5,3,1,2) by sequences of 2-opt moves.

    // Now processing permutation (6,4,1,3,5,2).
    current_delta = ((current_delta + this.m_dist_1_4 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,4,1,3,5,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (6,4,1,3,5,2).
    // Finished processing permutation (6,4,5,3,1,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (4,5,3,2,1,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (4,5,3,2,1,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (4,5,3,2,1,6)
   */
  private final void __trace_383(final long parent_delta) {

    long current_delta;

    // Now processing permutation (4,3,5,2,1,6).
    current_delta = ((parent_delta + this.m_dist_2_5 + this.m_dist_3_4)
        - this.m_dist_2_3 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,3,5,2,1,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (4,3,5,2,1,6).

    // Now processing permutation (4,5,3,2,6,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,5,3,2,6,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (4,5,3,2,6,1).

    // Now processing permutation (4,5,3,1,2,6).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_6)
        - this.m_dist_1_6 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,5,3,1,2,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (4,5,3,1,2,6).

    // Now processing permutation (4,5,3,6,1,2).
    current_delta = ((parent_delta + this.m_dist_2_afterEnd + this.m_dist_3_6)
        - this.m_dist_2_3 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,5,3,6,1,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (4,5,3,6,1,2) by sequences of 2-opt moves.

    // Now processing permutation (4,1,6,3,5,2).
    current_delta = ((current_delta + this.m_dist_1_4 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,1,6,3,5,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (4,1,6,3,5,2).
    // Finished processing permutation (4,5,3,6,1,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,2,5,3,4,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,2,5,3,4,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,2,5,3,4,6)
   */
  private final void __trace_12(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,5,2,1,4,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,5,2,1,4,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (3,5,2,1,4,6).

    // Now processing permutation (5,2,1,3,4,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,2,1,3,4,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (5,2,1,3,4,6).

    // Now processing permutation (2,1,5,3,4,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_2_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,1,5,3,4,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (2,1,5,3,4,6).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (5,3,2,1,4,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (5,3,2,1,4,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (5,3,2,1,4,6)
   */
  private final void __trace_455(final long parent_delta) {

    long current_delta;

    // Now processing permutation (5,3,2,4,1,6).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,3,2,4,1,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (5,3,2,4,1,6).

    // Now processing permutation (5,3,2,6,4,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,3,2,6,4,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (5,3,2,6,4,1).

    // Now processing permutation (5,3,1,2,4,6).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_4)
        - this.m_dist_1_4 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,3,1,2,4,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (5,3,1,2,4,6).

    // Now processing permutation (5,3,6,4,1,2).
    current_delta = ((parent_delta + this.m_dist_2_afterEnd + this.m_dist_3_6)
        - this.m_dist_2_3 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,3,6,4,1,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (5,3,6,4,1,2).

    // Now processing permutation (5,3,4,1,2,6).
    current_delta = ((parent_delta + this.m_dist_2_6 + this.m_dist_3_4)
        - this.m_dist_2_3 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,3,4,1,2,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (5,3,4,1,2,6).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (3,2,1,5,4,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (3,2,1,5,4,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (3,2,1,5,4,6)
   */
  private final void __trace_468(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,2,6,4,5,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,2,6,4,5,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (3,2,6,4,5,1).

    // Now processing permutation (3,2,4,5,1,6).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,2,4,5,1,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (3,2,4,5,1,6).

    // Now processing permutation (3,2,5,1,4,6).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,2,5,1,4,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (3,2,5,1,4,6).

    // Now processing permutation (3,6,4,5,1,2).
    current_delta = ((parent_delta + this.m_dist_2_afterEnd + this.m_dist_3_6)
        - this.m_dist_2_3 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,6,4,5,1,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (3,6,4,5,1,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (2,1,3,5,4,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (2,1,3,5,4,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (2,1,3,5,4,6)
   */
  private final void __trace_708(final long parent_delta) {

    long current_delta;

    // Now processing permutation (2,5,3,1,4,6).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,5,3,1,4,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (2,5,3,1,4,6) by sequences of 2-opt moves.

    // Now processing permutation (2,5,3,6,4,1).
    current_delta = ((current_delta + this.m_dist_1_afterEnd + this.m_dist_3_6)
        - this.m_dist_1_3 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,5,3,6,4,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (2,5,3,6,4,1).
    // Finished processing permutation (2,5,3,1,4,6).

    // Now processing permutation (2,4,5,3,1,6).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,4,5,3,1,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (2,4,5,3,1,6).

    // Now processing permutation (2,6,4,5,3,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,6,4,5,3,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (2,6,4,5,3,1).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,4,5,6,2,3). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,4,5,6,2,3).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,4,5,6,2,3)
   */
  private final void __trace_74(final long parent_delta) {

    long current_delta;

    // Now processing permutation (1,4,2,6,5,3).
    current_delta = ((parent_delta + this.m_dist_2_4 + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,4,2,6,5,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,4,2,6,5,3) by sequences of 2-opt moves.

    // Now processing permutation (2,4,1,6,5,3).
    current_delta = ((current_delta + this.m_dist_beforeStart_2 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_2_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,4,1,6,5,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (2,4,1,6,5,3).
    // Finished processing permutation (1,4,2,6,5,3).

    // Now processing permutation (5,4,1,6,2,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,4,1,6,2,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (5,4,1,6,2,3).

    // Now processing permutation (2,6,5,4,1,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,6,5,4,1,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (2,6,5,4,1,3).

    // Now processing permutation (4,1,5,6,2,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,1,5,6,2,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (4,1,5,6,2,3) by sequences of 2-opt moves.

    // Now processing permutation (4,1,5,2,6,3).
    current_delta = ((current_delta + this.m_dist_2_5 + this.m_dist_3_6)
        - this.m_dist_2_3 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,1,5,2,6,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (4,1,5,2,6,3).
    // Finished processing permutation (4,1,5,6,2,3).

    // Now processing permutation (1,4,5,2,6,3).
    current_delta = ((parent_delta + this.m_dist_2_5 + this.m_dist_3_6)
        - this.m_dist_2_3 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,4,5,2,6,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (1,4,5,2,6,3).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,3,4,5,6,2). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,3,4,5,6,2).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,3,4,5,6,2)
   */
  private final void __trace_190(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,1,4,5,6,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,1,4,5,6,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (3,1,4,5,6,2).

    // Now processing permutation (5,4,3,1,6,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,4,3,1,6,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (5,4,3,1,6,2).

    // Now processing permutation (4,3,1,5,6,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,3,1,5,6,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (4,3,1,5,6,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,2,6,3,4,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,2,6,3,4,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,2,6,3,4,5)
   */
  private final void __trace_3(final long parent_delta) {

    long current_delta;

    // Now processing permutation (4,3,6,2,1,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,3,6,2,1,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (4,3,6,2,1,5).

    // Now processing permutation (5,4,3,6,2,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,4,3,6,2,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (5,4,3,6,2,1).

    // Now processing permutation (1,3,6,2,4,5).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,3,6,2,4,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,3,6,2,4,5) by sequences of 2-opt moves.

    // Now processing permutation (3,1,6,2,4,5).
    current_delta = ((current_delta + this.m_dist_beforeStart_3 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_3_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,1,6,2,4,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (3,1,6,2,4,5).
    // Finished processing permutation (1,3,6,2,4,5).

    // Now processing permutation (3,6,2,1,4,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,6,2,1,4,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (3,6,2,1,4,5).

    // Now processing permutation (6,2,1,3,4,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,2,1,3,4,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (6,2,1,3,4,5).

    // Now processing permutation (2,1,6,3,4,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_2_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,1,6,3,4,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (2,1,6,3,4,5).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (3,4,5,6,2,1). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (3,4,5,6,2,1).
   *
   * @param parent_delta
   *          the length change between the original tour and (3,4,5,6,2,1)
   */
  private final void __trace_405(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,4,5,2,6,1).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,4,5,2,6,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (3,4,5,2,6,1).

    // Now processing permutation (3,4,2,6,5,1).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,4,2,6,5,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (3,4,2,6,5,1).

    // Now processing permutation (4,3,5,6,2,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_3_5)
        - this.m_dist_beforeStart_3 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,3,5,6,2,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (4,3,5,6,2,1).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,6,2,5,4,3). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,6,2,5,4,3).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,6,2,5,4,3)
   */
  private final void __trace_43(final long parent_delta) {

    long current_delta;

    // Now processing permutation (6,1,2,5,4,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_2)
        - this.m_dist_beforeStart_1 - this.m_dist_2_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,1,2,5,4,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (6,1,2,5,4,3).

    // Now processing permutation (1,6,2,5,3,4).
    current_delta = ((parent_delta + this.m_dist_3_5 + this.m_dist_4_afterEnd)
        - this.m_dist_3_afterEnd - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,6,2,5,3,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,6,2,5,3,4) by sequences of 2-opt moves.

    // Now processing permutation (2,6,1,5,3,4).
    current_delta = ((current_delta + this.m_dist_beforeStart_2 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_2_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,6,1,5,3,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (2,6,1,5,3,4).
    // Finished processing permutation (1,6,2,5,3,4).

    // Now processing permutation (4,5,2,6,1,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,5,2,6,1,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (4,5,2,6,1,3).

    // Now processing permutation (2,6,1,5,4,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_2_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,6,1,5,4,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (2,6,1,5,4,3).

    // Now processing permutation (5,2,6,1,4,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,2,6,1,4,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (5,2,6,1,4,3).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,5,6,2,4,3). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,5,6,2,4,3).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,5,6,2,4,3)
   */
  private final void __trace_39(final long parent_delta) {

    long current_delta;

    // Now processing permutation (5,1,6,2,4,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,1,6,2,4,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (5,1,6,2,4,3).

    // Now processing permutation (4,2,6,5,1,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,2,6,5,1,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (4,2,6,5,1,3).

    // Now processing permutation (6,5,1,2,4,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_2)
        - this.m_dist_beforeStart_1 - this.m_dist_2_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,5,1,2,4,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (6,5,1,2,4,3).

    // Now processing permutation (2,6,5,1,4,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_2_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,6,5,1,4,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (2,6,5,1,4,3).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (4,5,6,2,1,3). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (4,5,6,2,1,3).
   *
   * @param parent_delta
   *          the length change between the original tour and (4,5,6,2,1,3)
   */
  private final void __trace_620(final long parent_delta) {

    long current_delta;

    // Now processing permutation (4,1,2,6,5,3).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_3_5)
        - this.m_dist_1_3 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,1,2,6,5,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (4,1,2,6,5,3).

    // Now processing permutation (4,5,1,2,6,3).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_3_6)
        - this.m_dist_1_3 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,5,1,2,6,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (4,5,1,2,6,3).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (2,1,6,5,4,3). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (2,1,6,5,4,3).
   *
   * @param parent_delta
   *          the length change between the original tour and (2,1,6,5,4,3)
   */
  private final void __trace_675(final long parent_delta) {

    long current_delta;

    // Now processing permutation (2,4,5,6,1,3).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,4,5,6,1,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (2,4,5,6,1,3).

    // Now processing permutation (5,6,1,2,4,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_2_4)
        - this.m_dist_beforeStart_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,6,1,2,4,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (5,6,1,2,4,3).

    // Now processing permutation (2,5,6,1,4,3).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,5,6,1,4,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (2,5,6,1,4,3).

    // Now processing permutation (2,1,6,5,3,4).
    current_delta = ((parent_delta + this.m_dist_3_5 + this.m_dist_4_afterEnd)
        - this.m_dist_3_afterEnd - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,1,6,5,3,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (2,1,6,5,3,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (6,2,1,5,4,3). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (6,2,1,5,4,3).
   *
   * @param parent_delta
   *          the length change between the original tour and (6,2,1,5,4,3)
   */
  private final void __trace_677(final long parent_delta) {

    long current_delta;

    // Now processing permutation (5,1,2,6,4,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_4_6)
        - this.m_dist_beforeStart_6 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,1,2,6,4,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (5,1,2,6,4,3).

    // Now processing permutation (6,2,4,5,1,3).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,2,4,5,1,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (6,2,4,5,1,3).

    // Now processing permutation (6,2,5,1,4,3).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,2,5,1,4,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (6,2,5,1,4,3).

    // Now processing permutation (6,2,1,5,3,4).
    current_delta = ((parent_delta + this.m_dist_3_5 + this.m_dist_4_afterEnd)
        - this.m_dist_3_afterEnd - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,2,1,5,3,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (6,2,1,5,3,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (5,6,2,1,4,3). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (5,6,2,1,4,3).
   *
   * @param parent_delta
   *          the length change between the original tour and (5,6,2,1,4,3)
   */
  private final void __trace_688(final long parent_delta) {

    long current_delta;

    // Now processing permutation (5,4,1,2,6,3).
    current_delta = ((parent_delta + this.m_dist_3_6 + this.m_dist_4_5)
        - this.m_dist_3_4 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,4,1,2,6,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (5,4,1,2,6,3).

    // Now processing permutation (5,6,2,4,1,3).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,6,2,4,1,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (5,6,2,4,1,3).

    // Now processing permutation (5,6,2,1,3,4).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_4_afterEnd)
        - this.m_dist_1_4 - this.m_dist_3_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,6,2,1,3,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (5,6,2,1,3,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,4,5,2,3,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,4,5,2,3,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,4,5,2,3,6)
   */
  private final void __trace_72(final long parent_delta) {

    long current_delta;

    // Now processing permutation (6,3,2,5,4,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,3,2,5,4,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (6,3,2,5,4,1).

    // Now processing permutation (2,5,4,1,3,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,5,4,1,3,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (2,5,4,1,3,6).

    // Now processing permutation (4,1,5,2,3,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,1,5,2,3,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (4,1,5,2,3,6).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,6,3,4,5,2). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,6,3,4,5,2).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,6,3,4,5,2)
   */
  private final void __trace_187(final long parent_delta) {

    long current_delta;

    // Now processing permutation (4,3,6,1,5,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,3,6,1,5,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (4,3,6,1,5,2).

    // Now processing permutation (6,1,3,4,5,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,1,3,4,5,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (6,1,3,4,5,2).

    // Now processing permutation (2,5,4,3,6,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_2_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,5,4,3,6,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (2,5,4,3,6,1).

    // Now processing permutation (3,6,1,4,5,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,6,1,4,5,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (3,6,1,4,5,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,3,4,5,2,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,3,4,5,2,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,3,4,5,2,6)
   */
  private final void __trace_191(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,1,4,5,2,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,1,4,5,2,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (3,1,4,5,2,6).

    // Now processing permutation (4,3,1,5,2,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,3,1,5,2,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (4,3,1,5,2,6).

    // Now processing permutation (6,2,5,4,3,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,2,5,4,3,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (6,2,5,4,3,1).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,2,5,4,6,3). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,2,5,4,6,3).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,2,5,4,6,3)
   */
  private final void __trace_46(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,6,4,5,2,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_3_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,6,4,5,2,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (3,6,4,5,2,1) by sequences of 2-opt moves.

    // Now processing permutation (3,6,4,2,5,1).
    current_delta = ((current_delta + this.m_dist_1_5 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,6,4,2,5,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (3,6,4,2,5,1).
    // Finished processing permutation (3,6,4,5,2,1).

    // Now processing permutation (1,5,2,4,6,3).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,5,2,4,6,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,5,2,4,6,3) by sequences of 2-opt moves.

    // Now processing permutation (2,5,1,4,6,3).
    current_delta = ((current_delta + this.m_dist_beforeStart_2 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_2_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,5,1,4,6,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (2,5,1,4,6,3).
    // Finished processing permutation (1,5,2,4,6,3).

    // Now processing permutation (6,4,5,2,1,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,4,5,2,1,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (6,4,5,2,1,3).

    // Now processing permutation (4,5,2,1,6,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,5,2,1,6,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (4,5,2,1,6,3).

    // Now processing permutation (2,1,5,4,6,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_2_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,1,5,4,6,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (2,1,5,4,6,3).

    // Now processing permutation (5,2,1,4,6,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,2,1,4,6,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (5,2,1,4,6,3).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,5,2,4,3,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,5,2,4,3,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,5,2,4,3,6)
   */
  private final void __trace_36(final long parent_delta) {

    long current_delta;

    // Now processing permutation (6,3,4,2,5,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,3,4,2,5,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (6,3,4,2,5,1).

    // Now processing permutation (3,4,2,5,1,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_3_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,4,2,5,1,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (3,4,2,5,1,6).

    // Now processing permutation (4,2,5,1,3,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,2,5,1,3,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (4,2,5,1,3,6).

    // Now processing permutation (2,5,1,4,3,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_2_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,5,1,4,3,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (2,5,1,4,3,6).

    // Now processing permutation (5,1,2,4,3,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_2)
        - this.m_dist_beforeStart_1 - this.m_dist_2_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,1,2,4,3,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (5,1,2,4,3,6).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (4,5,2,1,3,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (4,5,2,1,3,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (4,5,2,1,3,6)
   */
  private final void __trace_623(final long parent_delta) {

    long current_delta;

    // Now processing permutation (4,1,2,5,3,6).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_3_5)
        - this.m_dist_1_3 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,1,2,5,3,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (4,1,2,5,3,6).

    // Now processing permutation (4,5,2,6,3,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,5,2,6,3,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (4,5,2,6,3,1).

    // Now processing permutation (4,6,3,1,2,5).
    current_delta = ((parent_delta + this.m_dist_4_6 + this.m_dist_5_afterEnd)
        - this.m_dist_4_5 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,6,3,1,2,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (4,6,3,1,2,5).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (2,1,5,4,3,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (2,1,5,4,3,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (2,1,5,4,3,6)
   */
  private final void __trace_672(final long parent_delta) {

    long current_delta;

    // Now processing permutation (2,6,3,4,5,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,6,3,4,5,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (2,6,3,4,5,1).

    // Now processing permutation (2,4,5,1,3,6).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,4,5,1,3,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (2,4,5,1,3,6).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (5,2,1,4,3,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (5,2,1,4,3,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (5,2,1,4,3,6)
   */
  private final void __trace_684(final long parent_delta) {

    long current_delta;

    // Now processing permutation (5,2,6,3,4,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,2,6,3,4,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (5,2,6,3,4,1).

    // Now processing permutation (5,2,4,1,3,6).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,2,4,1,3,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (5,2,4,1,3,6).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,3,4,2,5,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,3,4,2,5,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,3,4,2,5,6)
   */
  private final void __trace_180(final long parent_delta) {

    long current_delta;

    // Now processing permutation (5,2,4,3,1,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,2,4,3,1,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (5,2,4,3,1,6).

    // Now processing permutation (6,5,2,4,3,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,5,2,4,3,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (6,5,2,4,3,1).

    // Now processing permutation (3,1,4,2,5,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,1,4,2,5,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (3,1,4,2,5,6).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,6,5,3,4,2). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,6,5,3,4,2).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,6,5,3,4,2)
   */
  private final void __trace_199(final long parent_delta) {

    long current_delta;

    // Now processing permutation (6,1,5,3,4,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,1,5,3,4,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (6,1,5,3,4,2).

    // Now processing permutation (2,4,3,5,6,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_2_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,4,3,5,6,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (2,4,3,5,6,1).

    // Now processing permutation (5,6,1,3,4,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,6,1,3,4,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (5,6,1,3,4,2).

    // Now processing permutation (3,5,6,1,4,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,5,6,1,4,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (3,5,6,1,4,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,5,3,4,2,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,5,3,4,2,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,5,3,4,2,6)
   */
  private final void __trace_203(final long parent_delta) {

    long current_delta;

    // Now processing permutation (6,2,4,3,5,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,2,4,3,5,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (6,2,4,3,5,1).

    // Now processing permutation (2,4,3,5,1,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_2_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,4,3,5,1,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (2,4,3,5,1,6).

    // Now processing permutation (5,1,3,4,2,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,1,3,4,2,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (5,1,3,4,2,6).

    // Now processing permutation (3,5,1,4,2,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,5,1,4,2,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (3,5,1,4,2,6).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,2,4,6,5,3). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,2,4,6,5,3).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,2,4,6,5,3)
   */
  private final void __trace_50(final long parent_delta) {

    long current_delta;

    // Now processing permutation (1,6,4,2,5,3).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,6,4,2,5,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,6,4,2,5,3) by sequences of 2-opt moves.
    this.__trace_67(current_delta);
    // Finished processing permutation (1,6,4,2,5,3).

    // Now processing permutation (3,5,6,4,2,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_3_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,5,6,4,2,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (3,5,6,4,2,1).

    // Now processing permutation (6,4,2,1,5,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,4,2,1,5,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (6,4,2,1,5,3).

    // Now processing permutation (4,2,1,6,5,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,2,1,6,5,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (4,2,1,6,5,3).

    // Now processing permutation (5,6,4,2,1,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,6,4,2,1,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (5,6,4,2,1,3).

    // Now processing permutation (2,1,4,6,5,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_2_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,1,4,6,5,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (2,1,4,6,5,3).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (6,5,3,4,2,1). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (6,5,3,4,2,1).
   *
   * @param parent_delta
   *          the length change between the original tour and (6,5,3,4,2,1)
   */
  private final void __trace_390(final long parent_delta) {

    long current_delta;

    // Now processing permutation (6,5,3,2,4,1).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_2_3)
        - this.m_dist_1_2 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,5,3,2,4,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (6,5,3,2,4,1).

    // Now processing permutation (6,5,3,1,2,4).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_4_afterEnd)
        - this.m_dist_1_afterEnd - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,5,3,1,2,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (6,5,3,1,2,4).

    // Now processing permutation (6,5,3,4,1,2).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_2_afterEnd)
        - this.m_dist_1_afterEnd - this.m_dist_2_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,5,3,4,1,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (6,5,3,4,1,2).

    // Now processing permutation (6,1,2,4,3,5).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_5_afterEnd)
        - this.m_dist_1_afterEnd - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,1,2,4,3,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (6,1,2,4,3,5).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (5,3,4,2,1,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (5,3,4,2,1,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (5,3,4,2,1,6)
   */
  private final void __trace_395(final long parent_delta) {

    long current_delta;

    // Now processing permutation (5,3,4,6,1,2).
    current_delta = ((parent_delta + this.m_dist_2_afterEnd + this.m_dist_4_6)
        - this.m_dist_2_4 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,3,4,6,1,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (5,3,4,6,1,2).

    // Now processing permutation (5,3,6,1,2,4).
    current_delta = ((parent_delta + this.m_dist_3_6 + this.m_dist_4_afterEnd)
        - this.m_dist_3_4 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,3,6,1,2,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (5,3,6,1,2,4).

    // Now processing permutation (5,3,4,2,6,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,3,4,2,6,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (5,3,4,2,6,1).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (4,2,1,3,5,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (4,2,1,3,5,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (4,2,1,3,5,6)
   */
  private final void __trace_600(final long parent_delta) {

    long current_delta;

    // Now processing permutation (4,6,5,3,1,2).
    current_delta = ((parent_delta + this.m_dist_2_afterEnd + this.m_dist_4_6)
        - this.m_dist_2_4 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,6,5,3,1,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (4,6,5,3,1,2).

    // Now processing permutation (4,2,6,5,3,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,2,6,5,3,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (4,2,6,5,3,1).

    // Now processing permutation (4,2,5,3,1,6).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,2,5,3,1,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (4,2,5,3,1,6).

    // Now processing permutation (2,4,1,3,5,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_4)
        - this.m_dist_beforeStart_4 - this.m_dist_1_2);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,4,1,3,5,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (2,4,1,3,5,6).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (2,1,4,3,5,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (2,1,4,3,5,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (2,1,4,3,5,6)
   */
  private final void __trace_660(final long parent_delta) {

    long current_delta;

    // Now processing permutation (2,5,3,4,1,6).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,5,3,4,1,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (2,5,3,4,1,6).

    // Now processing permutation (2,6,5,3,4,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,6,5,3,4,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (2,6,5,3,4,1).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (4,5,1,3,2,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (4,5,1,3,2,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (4,5,1,3,2,6)
   */
  private final void __trace_143(final long parent_delta) {

    long current_delta;

    // Now processing permutation (4,5,1,3,6,2).
    current_delta = ((parent_delta + this.m_dist_2_afterEnd + this.m_dist_3_6)
        - this.m_dist_2_3 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,5,1,3,6,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (4,5,1,3,6,2).

    // Now processing permutation (4,6,2,3,1,5).
    current_delta = ((parent_delta + this.m_dist_4_6 + this.m_dist_5_afterEnd)
        - this.m_dist_4_5 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,6,2,3,1,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (4,6,2,3,1,5).

    // Now processing permutation (4,5,1,6,2,3).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_3_afterEnd)
        - this.m_dist_1_3 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,5,1,6,2,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (4,5,1,6,2,3).

    // Now processing permutation (3,1,5,4,2,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_2_4)
        - this.m_dist_beforeStart_4 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,1,5,4,2,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (3,1,5,4,2,6).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (5,1,4,3,2,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (5,1,4,3,2,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (5,1,4,3,2,6)
   */
  private final void __trace_155(final long parent_delta) {

    long current_delta;

    // Now processing permutation (5,1,4,3,6,2).
    current_delta = ((parent_delta + this.m_dist_2_afterEnd + this.m_dist_3_6)
        - this.m_dist_2_3 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,1,4,3,6,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (5,1,4,3,6,2).

    // Now processing permutation (5,1,4,2,3,6).
    current_delta = ((parent_delta + this.m_dist_2_4 + this.m_dist_3_6)
        - this.m_dist_2_6 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,1,4,2,3,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (5,1,4,2,3,6).

    // Now processing permutation (3,4,1,5,2,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_2_5)
        - this.m_dist_beforeStart_5 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,4,1,5,2,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (3,4,1,5,2,6).

    // Now processing permutation (5,1,6,2,3,4).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_4_afterEnd)
        - this.m_dist_1_4 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,1,6,2,3,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (5,1,6,2,3,4).

    // Now processing permutation (5,1,4,6,2,3).
    current_delta = ((parent_delta + this.m_dist_3_afterEnd + this.m_dist_4_6)
        - this.m_dist_3_4 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,1,4,6,2,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (5,1,4,6,2,3).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,6,2,3,4,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,6,2,3,4,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,6,2,3,4,5)
   */
  private final void __trace_4(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,2,6,1,4,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,2,6,1,4,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (3,2,6,1,4,5).

    // Now processing permutation (2,6,1,3,4,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,6,1,3,4,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (2,6,1,3,4,5).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,5,6,2,3,4). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,5,6,2,3,4).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,5,6,2,3,4)
   */
  private final void __trace_20(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,2,6,5,1,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,2,6,5,1,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (3,2,6,5,1,4).

    // Now processing permutation (2,6,5,1,3,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,6,5,1,3,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (2,6,5,1,3,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (4,5,6,1,3,2). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (4,5,6,1,3,2).
   *
   * @param parent_delta
   *          the length change between the original tour and (4,5,6,1,3,2)
   */
  private final void __trace_140(final long parent_delta) {

    long current_delta;

    // Now processing permutation (4,5,3,1,6,2).
    current_delta = ((parent_delta + this.m_dist_2_6 + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,5,3,1,6,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (4,5,3,1,6,2).

    // Now processing permutation (4,5,1,6,3,2).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_3_6)
        - this.m_dist_1_3 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,5,1,6,3,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (4,5,1,6,3,2).

    // Now processing permutation (3,1,6,5,4,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_2_4)
        - this.m_dist_beforeStart_4 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,1,6,5,4,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (3,1,6,5,4,2).

    // Now processing permutation (4,1,6,5,3,2).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_3_5)
        - this.m_dist_1_3 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,1,6,5,3,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (4,1,6,5,3,2).

    // Now processing permutation (4,3,1,6,5,2).
    current_delta = ((parent_delta + this.m_dist_2_5 + this.m_dist_3_4)
        - this.m_dist_2_3 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,3,1,6,5,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (4,3,1,6,5,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (5,6,1,4,3,2). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (5,6,1,4,3,2).
   *
   * @param parent_delta
   *          the length change between the original tour and (5,6,1,4,3,2)
   */
  private final void __trace_151(final long parent_delta) {

    long current_delta;

    // Now processing permutation (5,6,4,1,3,2).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_4_6)
        - this.m_dist_1_6 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,6,4,1,3,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (5,6,4,1,3,2).

    // Now processing permutation (5,3,4,1,6,2).
    current_delta = ((parent_delta + this.m_dist_2_6 + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,3,4,1,6,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (5,3,4,1,6,2).

    // Now processing permutation (3,4,1,6,5,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_2_5)
        - this.m_dist_beforeStart_5 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,4,1,6,5,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (3,4,1,6,5,2).

    // Now processing permutation (5,6,1,4,2,3).
    current_delta = ((parent_delta + this.m_dist_2_4 + this.m_dist_3_afterEnd)
        - this.m_dist_2_afterEnd - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,6,1,4,2,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (5,6,1,4,2,3).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,6,5,2,3,4). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,6,5,2,3,4).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,6,5,2,3,4)
   */
  private final void __trace_19(final long parent_delta) {

    long current_delta;

    // Now processing permutation (2,5,6,1,3,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,5,6,1,3,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (2,5,6,1,3,4).

    // Now processing permutation (6,1,5,2,3,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,1,5,2,3,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (6,1,5,2,3,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (6,1,5,4,3,2). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (6,1,5,4,3,2).
   *
   * @param parent_delta
   *          the length change between the original tour and (6,1,5,4,3,2)
   */
  private final void __trace_161(final long parent_delta) {

    long current_delta;

    // Now processing permutation (6,4,5,1,3,2).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_4_6)
        - this.m_dist_1_6 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,4,5,1,3,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (6,4,5,1,3,2).

    // Now processing permutation (6,1,5,4,2,3).
    current_delta = ((parent_delta + this.m_dist_2_4 + this.m_dist_3_afterEnd)
        - this.m_dist_2_afterEnd - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,1,5,4,2,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (6,1,5,4,2,3).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (4,1,3,2,5,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (4,1,3,2,5,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (4,1,3,2,5,6)
   */
  private final void __trace_120(final long parent_delta) {

    long current_delta;

    // Now processing permutation (4,1,3,6,5,2).
    current_delta = ((parent_delta + this.m_dist_2_afterEnd + this.m_dist_3_6)
        - this.m_dist_2_3 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,1,3,6,5,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (4,1,3,6,5,2).

    // Now processing permutation (6,5,2,3,1,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_4_afterEnd)
        - this.m_dist_beforeStart_4 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,5,2,3,1,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (6,5,2,3,1,4).

    // Now processing permutation (4,6,5,2,3,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_4_6)
        - this.m_dist_1_4 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,6,5,2,3,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (4,6,5,2,3,1).

    // Now processing permutation (4,1,3,5,2,6).
    current_delta = ((parent_delta + this.m_dist_2_6 + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,1,3,5,2,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (4,1,3,5,2,6).

    // Now processing permutation (4,1,6,5,2,3).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_3_afterEnd)
        - this.m_dist_1_3 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,1,6,5,2,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (4,1,6,5,2,3).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,4,3,6,5,2). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,4,3,6,5,2).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,4,3,6,5,2)
   */
  private final void __trace_170(final long parent_delta) {

    long current_delta;

    // Now processing permutation (2,5,6,3,4,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_2_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,5,6,3,4,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (2,5,6,3,4,1).

    // Now processing permutation (6,3,4,1,5,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,3,4,1,5,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (6,3,4,1,5,2).

    // Now processing permutation (1,4,6,3,5,2).
    current_delta = ((parent_delta + this.m_dist_3_5 + this.m_dist_4_6)
        - this.m_dist_3_4 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,4,6,3,5,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (1,4,6,3,5,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,4,6,5,2,3). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,4,6,5,2,3).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,4,6,5,2,3)
   */
  private final void __trace_75(final long parent_delta) {

    long current_delta;

    // Now processing permutation (6,4,1,5,2,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,4,1,5,2,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (6,4,1,5,2,3).

    // Now processing permutation (2,5,6,4,1,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,5,6,4,1,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (2,5,6,4,1,3).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,3,5,4,2,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,3,5,4,2,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,3,5,4,2,6)
   */
  private final void __trace_192(final long parent_delta) {

    long current_delta;

    // Now processing permutation (5,3,1,4,2,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,3,1,4,2,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (5,3,1,4,2,6).

    // Now processing permutation (6,2,4,5,3,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,2,4,5,3,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (6,2,4,5,3,1).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,3,6,5,4,2). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,3,6,5,4,2).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,3,6,5,4,2)
   */
  private final void __trace_195(final long parent_delta) {

    long current_delta;

    // Now processing permutation (5,6,3,1,4,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,6,3,1,4,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (5,6,3,1,4,2).

    // Now processing permutation (6,3,1,5,4,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,3,1,5,4,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (6,3,1,5,4,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (5,4,2,3,1,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (5,4,2,3,1,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (5,4,2,3,1,6)
   */
  private final void __trace_575(final long parent_delta) {

    long current_delta;

    // Now processing permutation (5,4,2,3,6,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_3_6)
        - this.m_dist_1_3 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,4,2,3,6,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (5,4,2,3,6,1).

    // Now processing permutation (5,4,2,6,1,3).
    current_delta = ((parent_delta + this.m_dist_2_6 + this.m_dist_3_afterEnd)
        - this.m_dist_2_3 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,4,2,6,1,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (5,4,2,6,1,3).

    // Now processing permutation (5,6,1,3,2,4).
    current_delta = ((parent_delta + this.m_dist_4_afterEnd + this.m_dist_5_6)
        - this.m_dist_4_5 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,6,1,3,2,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (5,6,1,3,2,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (4,3,2,6,5,1). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (4,3,2,6,5,1).
   *
   * @param parent_delta
   *          the length change between the original tour and (4,3,2,6,5,1)
   */
  private final void __trace_369(final long parent_delta) {

    long current_delta;

    // Now processing permutation (4,6,2,3,5,1).
    current_delta = ((parent_delta + this.m_dist_3_5 + this.m_dist_4_6)
        - this.m_dist_3_4 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,6,2,3,5,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (4,6,2,3,5,1).

    // Now processing permutation (4,3,6,2,5,1).
    current_delta = ((parent_delta + this.m_dist_2_5 + this.m_dist_3_6)
        - this.m_dist_2_3 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,3,6,2,5,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (4,3,6,2,5,1).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (5,4,3,6,1,2). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (5,4,3,6,1,2).
   *
   * @param parent_delta
   *          the length change between the original tour and (5,4,3,6,1,2)
   */
  private final void __trace_333(final long parent_delta) {

    long current_delta;

    // Now processing permutation (5,1,6,3,4,2).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,1,6,3,4,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (5,1,6,3,4,2).

    // Now processing permutation (5,4,6,3,1,2).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_4_6)
        - this.m_dist_1_6 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,4,6,3,1,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (5,4,6,3,1,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (6,5,4,1,2,3). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (6,5,4,1,2,3).
   *
   * @param parent_delta
   *          the length change between the original tour and (6,5,4,1,2,3)
   */
  private final void __trace_90(final long parent_delta) {

    long current_delta;

    // Now processing permutation (6,5,1,4,2,3).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,5,1,4,2,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (6,5,1,4,2,3).

    // Now processing permutation (6,1,4,5,2,3).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,1,4,5,2,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (6,1,4,5,2,3).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (3,6,5,4,1,2). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (3,6,5,4,1,2).
   *
   * @param parent_delta
   *          the length change between the original tour and (3,6,5,4,1,2)
   */
  private final void __trace_319(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,6,5,1,4,2).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,6,5,1,4,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (3,6,5,1,4,2).

    // Now processing permutation (6,3,5,4,1,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_3_5)
        - this.m_dist_beforeStart_3 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,3,5,4,1,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (6,3,5,4,1,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (3,2,5,4,1,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (3,2,5,4,1,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (3,2,5,4,1,6)
   */
  private final void __trace_432(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,2,5,4,6,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_4_6)
        - this.m_dist_1_4 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,2,5,4,6,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (3,2,5,4,6,1).

    // Now processing permutation (3,5,2,4,1,6).
    current_delta = ((parent_delta + this.m_dist_2_4 + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,5,2,4,1,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (3,5,2,4,1,6).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (3,2,6,5,4,1). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (3,2,6,5,4,1).
   *
   * @param parent_delta
   *          the length change between the original tour and (3,2,6,5,4,1)
   */
  private final void __trace_435(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,6,2,5,4,1).
    current_delta = ((parent_delta + this.m_dist_2_5 + this.m_dist_3_6)
        - this.m_dist_2_3 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,6,2,5,4,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (3,6,2,5,4,1).

    // Now processing permutation (3,5,6,2,4,1).
    current_delta = ((parent_delta + this.m_dist_2_4 + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,5,6,2,4,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (3,5,6,2,4,1).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (2,4,3,1,5,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (2,4,3,1,5,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (2,4,3,1,5,6)
   */
  private final void __trace_540(final long parent_delta) {

    long current_delta;

    // Now processing permutation (2,4,3,6,5,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_3_6)
        - this.m_dist_1_3 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,4,3,6,5,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (2,4,3,6,5,1).

    // Now processing permutation (2,4,6,5,1,3).
    current_delta = ((parent_delta + this.m_dist_3_afterEnd + this.m_dist_4_6)
        - this.m_dist_3_4 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,4,6,5,1,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (2,4,6,5,1,3).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,3,4,6,5,2). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,3,4,6,5,2).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,3,4,6,5,2)
   */
  private final void __trace_189(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,1,4,6,5,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,1,4,6,5,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (3,1,4,6,5,2).

    // Now processing permutation (6,4,3,1,5,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,4,3,1,5,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (6,4,3,1,5,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (3,4,6,5,2,1). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (3,4,6,5,2,1).
   *
   * @param parent_delta
   *          the length change between the original tour and (3,4,6,5,2,1)
   */
  private final void __trace_404(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,4,6,2,5,1).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,4,6,2,5,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (3,4,6,2,5,1) by sequences of 2-opt moves.

    // Now processing permutation (3,1,5,2,6,4).
    current_delta = ((current_delta + this.m_dist_1_3 + this.m_dist_4_afterEnd)
        - this.m_dist_1_afterEnd - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,1,5,2,6,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (3,1,5,2,6,4).
    // Finished processing permutation (3,4,6,2,5,1).

    // Now processing permutation (3,4,2,5,6,1).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,4,2,5,6,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (3,4,2,5,6,1).

    // Now processing permutation (3,1,2,5,6,4).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_4_afterEnd)
        - this.m_dist_1_afterEnd - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,1,2,5,6,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (3,1,2,5,6,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,5,2,6,4,3). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,5,2,6,4,3).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,5,2,6,4,3)
   */
  private final void __trace_38(final long parent_delta) {

    long current_delta;

    // Now processing permutation (4,6,2,5,1,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,6,2,5,1,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (4,6,2,5,1,3).

    // Now processing permutation (2,5,1,6,4,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_2_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,5,1,6,4,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (2,5,1,6,4,3).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,6,5,2,4,3). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,6,5,2,4,3).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,6,5,2,4,3)
   */
  private final void __trace_40(final long parent_delta) {

    long current_delta;

    // Now processing permutation (4,2,5,6,1,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,2,5,6,1,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (4,2,5,6,1,3).

    // Now processing permutation (6,1,5,2,4,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,1,5,2,4,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (6,1,5,2,4,3).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (4,6,5,2,1,3). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (4,6,5,2,1,3).
   *
   * @param parent_delta
   *          the length change between the original tour and (4,6,5,2,1,3)
   */
  private final void __trace_619(final long parent_delta) {

    long current_delta;

    // Now processing permutation (4,6,1,2,5,3).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_3_5)
        - this.m_dist_1_3 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,6,1,2,5,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (4,6,1,2,5,3).

    // Now processing permutation (4,1,2,5,6,3).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_3_6)
        - this.m_dist_1_3 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,1,2,5,6,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (4,1,2,5,6,3).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (6,5,2,1,4,3). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (6,5,2,1,4,3).
   *
   * @param parent_delta
   *          the length change between the original tour and (6,5,2,1,4,3)
   */
  private final void __trace_689(final long parent_delta) {

    long current_delta;

    // Now processing permutation (6,5,2,4,1,3).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,5,2,4,1,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (6,5,2,4,1,3).

    // Now processing permutation (6,4,1,2,5,3).
    current_delta = ((parent_delta + this.m_dist_3_5 + this.m_dist_4_6)
        - this.m_dist_3_4 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,4,1,2,5,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (6,4,1,2,5,3).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,3,4,6,2,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,3,4,6,2,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,3,4,6,2,5)
   */
  private final void __trace_182(final long parent_delta) {

    long current_delta;

    // Now processing permutation (5,2,6,4,3,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,2,6,4,3,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (5,2,6,4,3,1).

    // Now processing permutation (3,1,4,6,2,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,1,4,6,2,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (3,1,4,6,2,5).

    // Now processing permutation (4,3,1,6,2,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,3,1,6,2,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (4,3,1,6,2,5).

    // Now processing permutation (1,3,5,2,6,4).
    current_delta = ((parent_delta + this.m_dist_3_5 + this.m_dist_4_afterEnd)
        - this.m_dist_3_4 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,3,5,2,6,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (1,3,5,2,6,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,6,2,4,3,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,6,2,4,3,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,6,2,4,3,5)
   */
  private final void __trace_55(final long parent_delta) {

    long current_delta;

    // Now processing permutation (4,2,6,1,3,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,2,6,1,3,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (4,2,6,1,3,5).

    // Now processing permutation (2,6,1,4,3,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_2_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,6,1,4,3,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (2,6,1,4,3,5) by sequences of 2-opt moves.

    // Now processing permutation (2,4,1,6,3,5).
    current_delta = ((current_delta + this.m_dist_2_4 + this.m_dist_3_6)
        - this.m_dist_2_6 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,4,1,6,3,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (2,4,1,6,3,5).
    // Finished processing permutation (2,6,1,4,3,5).

    // Now processing permutation (3,4,2,6,1,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,4,2,6,1,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (3,4,2,6,1,5).

    // Now processing permutation (1,4,2,6,3,5).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_3_6)
        - this.m_dist_1_6 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,4,2,6,3,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (1,4,2,6,3,5).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,5,3,4,6,2). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,5,3,4,6,2).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,5,3,4,6,2)
   */
  private final void __trace_202(final long parent_delta) {

    long current_delta;

    // Now processing permutation (5,1,3,4,6,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,1,3,4,6,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (5,1,3,4,6,2).

    // Now processing permutation (2,6,4,3,5,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_2_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,6,4,3,5,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (2,6,4,3,5,1).

    // Now processing permutation (3,5,1,4,6,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,5,1,4,6,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (3,5,1,4,6,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (5,3,4,6,2,1). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (5,3,4,6,2,1).
   *
   * @param parent_delta
   *          the length change between the original tour and (5,3,4,6,2,1)
   */
  private final void __trace_393(final long parent_delta) {

    long current_delta;

    // Now processing permutation (5,3,6,4,2,1).
    current_delta = ((parent_delta + this.m_dist_2_4 + this.m_dist_3_6)
        - this.m_dist_2_6 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,3,6,4,2,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (5,3,6,4,2,1).

    // Now processing permutation (5,3,1,2,6,4).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_4_afterEnd)
        - this.m_dist_1_afterEnd - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,3,1,2,6,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (5,3,1,2,6,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,2,4,6,3,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,2,4,6,3,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,2,4,6,3,5)
   */
  private final void __trace_57(final long parent_delta) {

    long current_delta;

    // Now processing permutation (4,2,1,6,3,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,2,1,6,3,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (4,2,1,6,3,5).

    // Now processing permutation (6,4,2,1,3,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,4,2,1,3,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (6,4,2,1,3,5).

    // Now processing permutation (3,6,4,2,1,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,6,4,2,1,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (3,6,4,2,1,5).

    // Now processing permutation (2,1,4,6,3,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_2_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,1,4,6,3,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (2,1,4,6,3,5).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (3,4,6,2,1,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (3,4,6,2,1,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (3,4,6,2,1,5)
   */
  private final void __trace_416(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,5,1,2,6,4).
    current_delta = ((parent_delta + this.m_dist_3_5 + this.m_dist_4_afterEnd)
        - this.m_dist_3_4 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,5,1,2,6,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (3,5,1,2,6,4).

    // Now processing permutation (3,1,2,6,4,5).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_4_5)
        - this.m_dist_1_5 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,1,2,6,4,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (3,1,2,6,4,5).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (4,6,2,1,3,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (4,6,2,1,3,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (4,6,2,1,3,5)
   */
  private final void __trace_604(final long parent_delta) {

    long current_delta;

    // Now processing permutation (4,6,2,5,3,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,6,2,5,3,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (4,6,2,5,3,1).

    // Now processing permutation (4,1,2,6,3,5).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_3_6)
        - this.m_dist_1_3 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,1,2,6,3,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (4,1,2,6,3,5).

    // Now processing permutation (2,6,4,1,3,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_4)
        - this.m_dist_beforeStart_4 - this.m_dist_1_2);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,6,4,1,3,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (2,6,4,1,3,5).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (2,1,6,4,3,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (2,1,6,4,3,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (2,1,6,4,3,5)
   */
  private final void __trace_663(final long parent_delta) {

    long current_delta;

    // Now processing permutation (2,5,3,4,6,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,5,3,4,6,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (2,5,3,4,6,1).

    // Now processing permutation (2,4,6,1,3,5).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,4,6,1,3,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (2,4,6,1,3,5).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,3,4,2,6,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,3,4,2,6,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,3,4,2,6,5)
   */
  private final void __trace_181(final long parent_delta) {

    long current_delta;

    // Now processing permutation (6,2,4,3,1,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,2,4,3,1,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (6,2,4,3,1,5).

    // Now processing permutation (3,1,4,2,6,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,1,4,2,6,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (3,1,4,2,6,5).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,6,3,4,2,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,6,3,4,2,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,6,3,4,2,5)
   */
  private final void __trace_184(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,6,1,4,2,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,6,1,4,2,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (3,6,1,4,2,5).

    // Now processing permutation (5,2,4,3,6,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,2,4,3,6,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (5,2,4,3,6,1).

    // Now processing permutation (6,1,3,4,2,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,1,3,4,2,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (6,1,3,4,2,5).

    // Now processing permutation (2,4,3,6,1,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_2_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,4,3,6,1,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (2,4,3,6,1,5).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (6,3,4,2,1,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (6,3,4,2,1,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (6,3,4,2,1,5)
   */
  private final void __trace_414(final long parent_delta) {

    long current_delta;

    // Now processing permutation (6,3,1,2,4,5).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_4_5)
        - this.m_dist_1_5 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,3,1,2,4,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (6,3,1,2,4,5).

    // Now processing permutation (6,3,2,4,1,5).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_2_3)
        - this.m_dist_1_2 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,3,2,4,1,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (6,3,2,4,1,5).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (4,2,1,3,6,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (4,2,1,3,6,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (4,2,1,3,6,5)
   */
  private final void __trace_601(final long parent_delta) {

    long current_delta;

    // Now processing permutation (4,2,5,6,3,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,2,5,6,3,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (4,2,5,6,3,1).

    // Now processing permutation (4,2,6,3,1,5).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,2,6,3,1,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (4,2,6,3,1,5).

    // Now processing permutation (2,4,1,3,6,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_4)
        - this.m_dist_beforeStart_4 - this.m_dist_1_2);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,4,1,3,6,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (2,4,1,3,6,5).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,4,6,5,3,2). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,4,6,5,3,2).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,4,6,5,3,2)
   */
  private final void __trace_164(final long parent_delta) {

    long current_delta;

    // Now processing permutation (2,3,5,6,4,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_2_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,3,5,6,4,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (2,3,5,6,4,1).

    // Now processing permutation (6,4,1,5,3,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,4,1,5,3,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (6,4,1,5,3,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (3,4,6,5,1,2). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (3,4,6,5,1,2).
   *
   * @param parent_delta
   *          the length change between the original tour and (3,4,6,5,1,2)
   */
  private final void __trace_315(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,4,1,5,6,2).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,4,1,5,6,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (3,4,1,5,6,2).

    // Now processing permutation (3,1,5,6,4,2).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,1,5,6,4,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (3,1,5,6,4,2).

    // Now processing permutation (3,4,6,1,5,2).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,4,6,1,5,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (3,4,6,1,5,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,5,4,6,3,2). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,5,4,6,3,2).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,5,4,6,3,2)
   */
  private final void __trace_158(final long parent_delta) {

    long current_delta;

    // Now processing permutation (2,3,6,4,5,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_2_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,3,6,4,5,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (2,3,6,4,5,1).

    // Now processing permutation (5,1,4,6,3,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,1,4,6,3,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (5,1,4,6,3,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (4,6,5,1,3,2). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (4,6,5,1,3,2).
   *
   * @param parent_delta
   *          the length change between the original tour and (4,6,5,1,3,2)
   */
  private final void __trace_139(final long parent_delta) {

    long current_delta;

    // Now processing permutation (4,6,3,1,5,2).
    current_delta = ((parent_delta + this.m_dist_2_5 + this.m_dist_3_6)
        - this.m_dist_2_3 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,6,3,1,5,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (4,6,3,1,5,2).

    // Now processing permutation (2,3,1,5,6,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_4_afterEnd)
        - this.m_dist_beforeStart_4 - this.m_dist_2_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,3,1,5,6,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (2,3,1,5,6,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,6,4,3,5,2). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,6,4,3,5,2).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,6,4,3,5,2)
   */
  private final void __trace_172(final long parent_delta) {

    long current_delta;

    // Now processing permutation (6,1,4,3,5,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,1,4,3,5,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (6,1,4,3,5,2).

    // Now processing permutation (4,6,1,3,5,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,6,1,3,5,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (4,6,1,3,5,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (3,4,6,1,2,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (3,4,6,1,2,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (3,4,6,1,2,5)
   */
  private final void __trace_303(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,5,2,1,6,4).
    current_delta = ((parent_delta + this.m_dist_3_5 + this.m_dist_4_afterEnd)
        - this.m_dist_3_4 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,5,2,1,6,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (3,5,2,1,6,4).

    // Now processing permutation (3,1,6,4,2,5).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,1,6,4,2,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (3,1,6,4,2,5).

    // Now processing permutation (3,4,1,6,2,5).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,4,1,6,2,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (3,4,1,6,2,5).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (4,6,1,3,2,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (4,6,1,3,2,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (4,6,1,3,2,5)
   */
  private final void __trace_124(final long parent_delta) {

    long current_delta;

    // Now processing permutation (4,1,6,3,2,5).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_3_6)
        - this.m_dist_1_3 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,1,6,3,2,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (4,1,6,3,2,5).

    // Now processing permutation (6,4,1,3,2,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_4)
        - this.m_dist_beforeStart_4 - this.m_dist_1_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,4,1,3,2,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (6,4,1,3,2,5).

    // Now processing permutation (2,3,1,6,4,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_4_5)
        - this.m_dist_beforeStart_4 - this.m_dist_2_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,3,1,6,4,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (2,3,1,6,4,5).

    // Now processing permutation (5,2,3,1,6,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_4_afterEnd)
        - this.m_dist_beforeStart_4 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,2,3,1,6,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (5,2,3,1,6,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,4,6,3,2,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,4,6,3,2,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,4,6,3,2,5)
   */
  private final void __trace_176(final long parent_delta) {

    long current_delta;

    // Now processing permutation (2,3,6,4,1,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_2_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,3,6,4,1,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (2,3,6,4,1,5).

    // Now processing permutation (5,2,3,6,4,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,2,3,6,4,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (5,2,3,6,4,1).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (2,3,4,6,1,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (2,3,4,6,1,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (2,3,4,6,1,5)
   */
  private final void __trace_537(final long parent_delta) {

    long current_delta;

    // Now processing permutation (2,3,5,1,6,4).
    current_delta = ((parent_delta + this.m_dist_3_5 + this.m_dist_4_afterEnd)
        - this.m_dist_3_4 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,3,5,1,6,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (2,3,5,1,6,4).

    // Now processing permutation (3,2,4,6,1,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_2_4)
        - this.m_dist_beforeStart_2 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,2,4,6,1,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (3,2,4,6,1,5).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (4,1,3,2,6,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (4,1,3,2,6,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (4,1,3,2,6,5)
   */
  private final void __trace_121(final long parent_delta) {

    long current_delta;

    // Now processing permutation (6,2,3,1,4,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_4_5)
        - this.m_dist_beforeStart_4 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,2,3,1,4,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (6,2,3,1,4,5).

    // Now processing permutation (4,1,3,6,2,5).
    current_delta = ((parent_delta + this.m_dist_2_5 + this.m_dist_3_6)
        - this.m_dist_2_3 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,1,3,6,2,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (4,1,3,6,2,5).

    // Now processing permutation (4,1,3,5,6,2).
    current_delta = ((parent_delta + this.m_dist_2_afterEnd + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,1,3,5,6,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (4,1,3,5,6,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (2,3,4,1,6,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (2,3,4,1,6,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (2,3,4,1,6,5)
   */
  private final void __trace_538(final long parent_delta) {

    long current_delta;

    // Now processing permutation (2,3,6,1,4,5).
    current_delta = ((parent_delta + this.m_dist_3_6 + this.m_dist_4_5)
        - this.m_dist_3_4 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,3,6,1,4,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (2,3,6,1,4,5).

    // Now processing permutation (2,3,5,6,1,4).
    current_delta = ((parent_delta + this.m_dist_3_5 + this.m_dist_4_afterEnd)
        - this.m_dist_3_4 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,3,5,6,1,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (2,3,5,6,1,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (2,1,3,6,4,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (2,1,3,6,4,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (2,1,3,6,4,5)
   */
  private final void __trace_717(final long parent_delta) {

    long current_delta;

    // Now processing permutation (2,4,6,3,1,5).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,4,6,3,1,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (2,4,6,3,1,5).

    // Now processing permutation (2,6,3,1,4,5).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,6,3,1,4,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (2,6,3,1,4,5).

    // Now processing permutation (2,5,4,6,3,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,5,4,6,3,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (2,5,4,6,3,1).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,3,6,4,2,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,3,6,4,2,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,3,6,4,2,5)
   */
  private final void __trace_183(final long parent_delta) {

    long current_delta;

    // Now processing permutation (5,2,4,6,3,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,2,4,6,3,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (5,2,4,6,3,1).

    // Now processing permutation (6,3,1,4,2,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,3,1,4,2,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_5;
    }
    // Finished processing permutation (6,3,1,4,2,5).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,3,5,6,4,2). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,3,5,6,4,2).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,3,5,6,4,2)
   */
  private final void __trace_194(final long parent_delta) {

    long current_delta;

    // Now processing permutation (5,3,1,6,4,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,3,1,6,4,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (5,3,1,6,4,2).

    // Now processing permutation (6,5,3,1,4,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,5,3,1,4,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (6,5,3,1,4,2).

    // Now processing permutation (2,4,6,5,3,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_2_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,4,6,5,3,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (2,4,6,5,3,1).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,2,5,3,6,4). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,2,5,3,6,4).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,2,5,3,6,4)
   */
  private final void __trace_13(final long parent_delta) {

    long current_delta;

    // Now processing permutation (6,3,5,2,1,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,3,5,2,1,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (6,3,5,2,1,4).

    // Now processing permutation (5,2,1,3,6,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,2,1,3,6,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (5,2,1,3,6,4).

    // Now processing permutation (2,1,5,3,6,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_2_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,1,5,3,6,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (2,1,5,3,6,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (2,1,3,5,6,4). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (2,1,3,5,6,4).
   *
   * @param parent_delta
   *          the length change between the original tour and (2,1,3,5,6,4)
   */
  private final void __trace_709(final long parent_delta) {

    long current_delta;

    // Now processing permutation (2,5,3,1,6,4).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,5,3,1,6,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (2,5,3,1,6,4).

    // Now processing permutation (2,6,5,3,1,4).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_2_6)
        - this.m_dist_1_2 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,6,5,3,1,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (2,6,5,3,1,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (3,2,5,6,4,1). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (3,2,5,6,4,1).
   *
   * @param parent_delta
   *          the length change between the original tour and (3,2,5,6,4,1)
   */
  private final void __trace_434(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,6,5,2,4,1).
    current_delta = ((parent_delta + this.m_dist_2_4 + this.m_dist_3_6)
        - this.m_dist_2_3 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,6,5,2,4,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (3,6,5,2,4,1).

    // Now processing permutation (3,5,2,6,4,1).
    current_delta = ((parent_delta + this.m_dist_2_6 + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,5,2,6,4,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (3,5,2,6,4,1).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,4,2,5,6,3). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,4,2,5,6,3).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,4,2,5,6,3)
   */
  private final void __trace_70(final long parent_delta) {

    long current_delta;

    // Now processing permutation (2,4,1,5,6,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_2_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,4,1,5,6,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (2,4,1,5,6,3).

    // Now processing permutation (5,2,4,1,6,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,2,4,1,6,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (5,2,4,1,6,3).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,5,4,2,6,3). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,5,4,2,6,3).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,5,4,2,6,3)
   */
  private final void __trace_82(final long parent_delta) {

    long current_delta;

    // Now processing permutation (5,1,4,2,6,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,1,4,2,6,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (5,1,4,2,6,3).

    // Now processing permutation (2,4,5,1,6,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_2_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,4,5,1,6,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (2,4,5,1,6,3).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (4,2,1,5,6,3). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (4,2,1,5,6,3).
   *
   * @param parent_delta
   *          the length change between the original tour and (4,2,1,5,6,3)
   */
  private final void __trace_610(final long parent_delta) {

    long current_delta;

    // Now processing permutation (4,2,5,1,6,3).
    current_delta = ((parent_delta + this.m_dist_1_6 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,2,5,1,6,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (4,2,5,1,6,3).

    // Now processing permutation (5,1,2,4,6,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_4_6)
        - this.m_dist_beforeStart_4 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,1,2,4,6,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (5,1,2,4,6,3).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (4,5,3,6,2,1). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (4,5,3,6,2,1).
   *
   * @param parent_delta
   *          the length change between the original tour and (4,5,3,6,2,1)
   */
  private final void __trace_381(final long parent_delta) {

    long current_delta;

    // Now processing permutation (2,6,3,5,4,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_4)
        - this.m_dist_beforeStart_4 - this.m_dist_1_2);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,6,3,5,4,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (2,6,3,5,4,1).

    // Now processing permutation (4,2,6,3,5,1).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,2,6,3,5,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (4,2,6,3,5,1).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,4,5,3,6,2). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,4,5,3,6,2).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,4,5,3,6,2)
   */
  private final void __trace_166(final long parent_delta) {

    long current_delta;

    // Now processing permutation (4,1,5,3,6,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,1,5,3,6,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (4,1,5,3,6,2).

    // Now processing permutation (5,4,1,3,6,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,4,1,3,6,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (5,4,1,3,6,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (5,3,6,2,1,4). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (5,3,6,2,1,4).
   *
   * @param parent_delta
   *          the length change between the original tour and (5,3,6,2,1,4)
   */
  private final void __trace_452(final long parent_delta) {

    long current_delta;

    // Now processing permutation (2,6,3,5,1,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_5)
        - this.m_dist_beforeStart_5 - this.m_dist_1_2);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,6,3,5,1,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (2,6,3,5,1,4).

    // Now processing permutation (5,3,6,2,4,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,3,6,2,4,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (5,3,6,2,4,1).

    // Now processing permutation (5,1,2,6,3,4).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_3_4)
        - this.m_dist_1_4 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,1,2,6,3,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (5,1,2,6,3,4).

    // Now processing permutation (5,2,6,3,1,4).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,2,6,3,1,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (5,2,6,3,1,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,5,3,6,2,4). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,5,3,6,2,4).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,5,3,6,2,4)
   */
  private final void __trace_218(final long parent_delta) {

    long current_delta;

    // Now processing permutation (5,1,3,6,2,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,1,3,6,2,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (5,1,3,6,2,4).

    // Now processing permutation (3,5,1,6,2,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_3_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,5,1,6,2,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (3,5,1,6,2,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (2,1,6,3,5,4). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (2,1,6,3,5,4).
   *
   * @param parent_delta
   *          the length change between the original tour and (2,1,6,3,5,4)
   */
  private final void __trace_711(final long parent_delta) {

    long current_delta;

    // Now processing permutation (2,4,5,3,6,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,4,5,3,6,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (2,4,5,3,6,1).

    // Now processing permutation (2,5,3,6,1,4).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,5,3,6,1,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (2,5,3,6,1,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,3,6,5,2,4). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,3,6,5,2,4).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,3,6,5,2,4)
   */
  private final void __trace_224(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,1,6,5,2,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_3_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,1,6,5,2,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (3,1,6,5,2,4).

    // Now processing permutation (6,3,1,5,2,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_5_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,3,1,5,2,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_2;
      this.m_best_node_6 = this.m_original_node_4;
    }
    // Finished processing permutation (6,3,1,5,2,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,6,3,5,4,2). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,6,3,5,4,2).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,6,3,5,4,2)
   */
  private final void __trace_196(final long parent_delta) {

    long current_delta;

    // Now processing permutation (6,1,3,5,4,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,1,3,5,4,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (6,1,3,5,4,2).

    // Now processing permutation (3,6,1,5,4,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,6,1,5,4,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (3,6,1,5,4,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,4,6,2,5,3). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,4,6,2,5,3).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,4,6,2,5,3)
   */
  private final void __trace_68(final long parent_delta) {

    long current_delta;

    // Now processing permutation (5,2,6,4,1,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,2,6,4,1,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (5,2,6,4,1,3).

    // Now processing permutation (2,6,4,1,5,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_2_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,6,4,1,5,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (2,6,4,1,5,3).

    // Now processing permutation (4,1,6,2,5,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_6)
        - this.m_dist_beforeStart_1 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,1,6,2,5,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (4,1,6,2,5,3).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,6,2,4,5,3). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,6,2,4,5,3).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,6,2,4,5,3)
   */
  private final void __trace_52(final long parent_delta) {

    long current_delta;

    // Now processing permutation (2,6,1,4,5,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_2_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,6,1,4,5,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_6;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (2,6,1,4,5,3).

    // Now processing permutation (4,2,6,1,5,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,2,6,1,5,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (4,2,6,1,5,3).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,3,5,4,6,2). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,3,5,4,6,2).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,3,5,4,6,2)
   */
  private final void __trace_193(final long parent_delta) {

    long current_delta;

    // Now processing permutation (5,3,1,4,6,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,3,1,4,6,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (5,3,1,4,6,2).

    // Now processing permutation (3,1,5,4,6,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,1,5,4,6,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (3,1,5,4,6,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (6,2,1,4,5,3). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (6,2,1,4,5,3).
   *
   * @param parent_delta
   *          the length change between the original tour and (6,2,1,4,5,3)
   */
  private final void __trace_666(final long parent_delta) {

    long current_delta;

    // Now processing permutation (6,2,4,1,5,3).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,2,4,1,5,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (6,2,4,1,5,3).

    // Now processing permutation (6,2,5,4,1,3).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,2,5,4,1,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_1;
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (6,2,5,4,1,3).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (3,5,4,6,1,2). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (3,5,4,6,1,2).
   *
   * @param parent_delta
   *          the length change between the original tour and (3,5,4,6,1,2)
   */
  private final void __trace_321(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,5,1,6,4,2).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,5,1,6,4,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_6;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (3,5,1,6,4,2).

    // Now processing permutation (3,1,6,4,5,2).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,1,6,4,5,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_6;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_2;
    }
    // Finished processing permutation (3,1,6,4,5,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,3,5,2,4,6). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,3,5,2,4,6).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,3,5,2,4,6)
   */
  private final void __trace_227(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,1,5,2,4,6).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,1,5,2,4,6)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_4;
      this.m_best_node_6 = (-1);
    }
    // Finished processing permutation (3,1,5,2,4,6).

    // Now processing permutation (6,4,2,5,3,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_6_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,4,2,5,3,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_3;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (6,4,2,5,3,1).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,6,4,2,5,3). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,6,4,2,5,3).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,6,4,2,5,3)
   */
  private final void __trace_67(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,5,2,4,6,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_3_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,5,2,4,6,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_6;
      this.m_best_node_6 = this.m_original_node_1;
    }
    // Finished processing permutation (3,5,2,4,6,1).

    // Now processing permutation (6,1,4,2,5,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_6 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_6);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (6,1,4,2,5,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_6;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = (-1);
      this.m_best_node_6 = this.m_original_node_3;
    }
    // Finished processing permutation (6,1,4,2,5,3).
  }

  /** {@inheritDoc} */
  @Override
  public final ExhaustivelyEnumeratingLocal6Optimizer clone() {
    return ((ExhaustivelyEnumeratingLocal6Optimizer) (super.clone()));
  }

  /** {@inheritDoc} */
  @Override
  public final int getSubPathLength() {
    return 6;
  }

}
