package org.logisticPlanning.tsp.solving.operators.permutation.localOpt;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;

/**
 * <p>
 * An implementation of the class
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.localOpt.LocalOptimizer}
 * for finding optimal path sub-sequences of length 5.
 * </p>
 * <p>
 * This code was automatically generated with the aim to be as efficient as
 * possible.
 * </p>
 */
public final class ExhaustivelyEnumeratingLocal5Optimizer
    extends
    org.logisticPlanning.tsp.solving.operators.permutation.localOpt.LocalOptimizer {

  /** the serial version uid */
  private static final long serialVersionUID = 0L;

  /**
   * the best improvement found: if
   * <code>{@link #m_best_delta} &gt; 0</code>, then there is an improved
   * sub-sequence stored in {@link #m_best_node_1} &hellip;
   * {@link #m_best_node_5}; otherwise these variables are not used
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
   * {@link #m_original_node_afterEnd} as {@code long}, since we always
   * need {@code long} arithmetic anyway.
   */
  private long m_dist_4_afterEnd;
  /**
   * The distance from {@link #m_original_node_5} to
   * {@link #m_original_node_afterEnd} as {@code long}, since we always
   * need {@code long} arithmetic anyway.
   */
  private long m_dist_5_afterEnd;

  // All distances related to the sub-sequence to be optimized can now be
  // cached.

  /**
   * The constructor of class
   * {@link ExhaustivelyEnumeratingLocal5Optimizer}
   */
  public ExhaustivelyEnumeratingLocal5Optimizer() {
    super(5);
  }

  /**
   * This is the main entry point of class
   * {@link ExhaustivelyEnumeratingLocal5Optimizer}. This method makes a
   * sub-sequence of length 5 inside {@code path}, starting right after
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
    this.m_dist_1_2 = dist.distance(this.m_original_node_1,
        this.m_original_node_2);
    this.m_dist_1_3 = dist.distance(this.m_original_node_1,
        this.m_original_node_3);
    this.m_dist_1_4 = dist.distance(this.m_original_node_1,
        this.m_original_node_4);
    this.m_dist_1_5 = dist.distance(this.m_original_node_1,
        this.m_original_node_5);
    this.m_dist_1_afterEnd = dist.distance(this.m_original_node_1,
        this.m_original_node_afterEnd);
    this.m_dist_2_3 = dist.distance(this.m_original_node_2,
        this.m_original_node_3);
    this.m_dist_2_4 = dist.distance(this.m_original_node_2,
        this.m_original_node_4);
    this.m_dist_2_5 = dist.distance(this.m_original_node_2,
        this.m_original_node_5);
    this.m_dist_2_afterEnd = dist.distance(this.m_original_node_2,
        this.m_original_node_afterEnd);
    this.m_dist_3_4 = dist.distance(this.m_original_node_3,
        this.m_original_node_4);
    this.m_dist_3_5 = dist.distance(this.m_original_node_3,
        this.m_original_node_5);
    this.m_dist_3_afterEnd = dist.distance(this.m_original_node_3,
        this.m_original_node_afterEnd);
    this.m_dist_4_5 = dist.distance(this.m_original_node_4,
        this.m_original_node_5);
    this.m_dist_4_afterEnd = dist.distance(this.m_original_node_4,
        this.m_original_node_afterEnd);
    this.m_dist_5_afterEnd = dist.distance(this.m_original_node_5,
        this.m_original_node_afterEnd);
    // All distances related to the sub-sequence to be optimized are now
    // cached.

    // Trace all paths starting at the canonical permutation.

    long current_delta;

    // Now processing permutation (1,2,3,5,4).
    current_delta = ((this.m_dist_3_5 + this.m_dist_4_afterEnd)
        - this.m_dist_3_4 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,2,3,5,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,2,3,5,4) by sequences of 2-opt moves.
    this.__trace_1(current_delta);
    // Finished processing permutation (1,2,3,5,4).

    // Now processing permutation (1,2,5,4,3).
    current_delta = ((this.m_dist_2_5 + this.m_dist_3_afterEnd)
        - this.m_dist_2_3 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,2,5,4,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,2,5,4,3) by sequences of 2-opt moves.
    this.__trace_7(current_delta);
    // Finished processing permutation (1,2,5,4,3).

    // Now processing permutation (1,2,4,3,5).
    current_delta = ((this.m_dist_2_4 + this.m_dist_3_5) - this.m_dist_2_3 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,2,4,3,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,2,4,3,5) by sequences of 2-opt moves.
    this.__trace_9(current_delta);
    // Finished processing permutation (1,2,4,3,5).

    // Now processing permutation (1,5,4,3,2).
    current_delta = ((this.m_dist_1_5 + this.m_dist_2_afterEnd)
        - this.m_dist_1_2 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,5,4,3,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,5,4,3,2) by sequences of 2-opt moves.
    this.__trace_26(current_delta);
    // Finished processing permutation (1,5,4,3,2).

    // Now processing permutation (1,4,3,2,5).
    current_delta = ((this.m_dist_1_4 + this.m_dist_2_5) - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,4,3,2,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,4,3,2,5) by sequences of 2-opt moves.
    this.__trace_29(current_delta);
    // Finished processing permutation (1,4,3,2,5).

    // Now processing permutation (1,3,2,4,5).
    current_delta = ((this.m_dist_1_3 + this.m_dist_2_4) - this.m_dist_1_2 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,3,2,4,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,3,2,4,5) by sequences of 2-opt moves.
    this.__trace_39(current_delta);
    // Finished processing permutation (1,3,2,4,5).

    // Now processing permutation (4,3,2,1,5).
    current_delta = ((this.m_dist_beforeStart_4 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,3,2,1,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (4,3,2,1,5) by sequences of 2-opt moves.
    this.__trace_60(current_delta);
    // Finished processing permutation (4,3,2,1,5).

    // Now processing permutation (5,4,3,2,1).
    current_delta = ((this.m_dist_beforeStart_5 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,4,3,2,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_1;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (5,4,3,2,1) by sequences of 2-opt moves.
    this.__trace_64(current_delta);
    // Finished processing permutation (5,4,3,2,1).

    // Now processing permutation (3,2,1,4,5).
    current_delta = ((this.m_dist_beforeStart_3 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,2,1,4,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (3,2,1,4,5) by sequences of 2-opt moves.
    this.__trace_79(current_delta);
    // Finished processing permutation (3,2,1,4,5).

    // Now processing permutation (2,1,3,4,5).
    current_delta = ((this.m_dist_beforeStart_2 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,1,3,4,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (2,1,3,4,5) by sequences of 2-opt moves.
    this.__trace_119(current_delta);
    // Finished processing permutation (2,1,3,4,5).

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
      // The improved node sequence has been flushed.
    }

    return this.m_best_delta; // return the best improvement delta
    // discovered
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,2,3,5,4). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,2,3,5,4).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,2,3,5,4)
   */
  private final void __trace_1(final long parent_delta) {

    long current_delta;

    // Now processing permutation (1,4,5,3,2).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_2_afterEnd)
        - this.m_dist_1_2 - this.m_dist_4_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,4,5,3,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,4,5,3,2) by sequences of 2-opt moves.
    this.__trace_27(current_delta);
    // Finished processing permutation (1,4,5,3,2).

    // Now processing permutation (1,5,3,2,4).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,5,3,2,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,5,3,2,4) by sequences of 2-opt moves.
    this.__trace_36(current_delta);
    // Finished processing permutation (1,5,3,2,4).

    // Now processing permutation (1,3,2,5,4).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,3,2,5,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,3,2,5,4) by sequences of 2-opt moves.
    this.__trace_38(current_delta);
    // Finished processing permutation (1,3,2,5,4).

    // Now processing permutation (1,2,5,3,4).
    current_delta = ((parent_delta + this.m_dist_2_5 + this.m_dist_3_4)
        - this.m_dist_2_3 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,2,5,3,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,2,5,3,4) by sequences of 2-opt moves.
    this.__trace_2(current_delta);
    // Finished processing permutation (1,2,5,3,4).

    // Now processing permutation (4,5,3,2,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_4_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,5,3,2,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_1;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (4,5,3,2,1) by sequences of 2-opt moves.
    this.__trace_63(current_delta);
    // Finished processing permutation (4,5,3,2,1).

    // Now processing permutation (1,2,4,5,3).
    current_delta = ((parent_delta + this.m_dist_2_4 + this.m_dist_3_afterEnd)
        - this.m_dist_2_3 - this.m_dist_4_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,2,4,5,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,2,4,5,3) by sequences of 2-opt moves.
    this.__trace_8(current_delta);
    // Finished processing permutation (1,2,4,5,3).

    // Now processing permutation (5,3,2,1,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,3,2,1,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (5,3,2,1,4) by sequences of 2-opt moves.
    this.__trace_75(current_delta);
    // Finished processing permutation (5,3,2,1,4).

    // Now processing permutation (3,2,1,5,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,2,1,5,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (3,2,1,5,4) by sequences of 2-opt moves.
    this.__trace_78(current_delta);
    // Finished processing permutation (3,2,1,5,4).

    // Now processing permutation (2,1,3,5,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,1,3,5,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (2,1,3,5,4) by sequences of 2-opt moves.
    this.__trace_118(current_delta);
    // Finished processing permutation (2,1,3,5,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,2,5,4,3). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,2,5,4,3).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,2,5,4,3)
   */
  private final void __trace_7(final long parent_delta) {

    long current_delta;

    // Now processing permutation (1,3,4,5,2).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_afterEnd)
        - this.m_dist_1_2 - this.m_dist_3_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,3,4,5,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,3,4,5,2) by sequences of 2-opt moves.
    this.__trace_31(current_delta);
    // Finished processing permutation (1,3,4,5,2).

    // Now processing permutation (1,4,5,2,3).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_2_3)
        - this.m_dist_1_2 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,4,5,2,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,4,5,2,3) by sequences of 2-opt moves.
    this.__trace_12(current_delta);
    // Finished processing permutation (1,4,5,2,3).

    // Now processing permutation (1,5,2,4,3).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,5,2,4,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,5,2,4,3) by sequences of 2-opt moves.
    this.__trace_6(current_delta);
    // Finished processing permutation (1,5,2,4,3).

    // Now processing permutation (3,4,5,2,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_3_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,4,5,2,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_1;
    }
    // Finished processing permutation (3,4,5,2,1).

    // Now processing permutation (4,5,2,1,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,5,2,1,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (4,5,2,1,3) by sequences of 2-opt moves.

    // Now processing permutation (4,1,2,5,3).
    current_delta = ((current_delta + this.m_dist_1_4 + this.m_dist_3_5)
        - this.m_dist_1_3 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,1,2,5,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_3;
    }
    // Finished processing permutation (4,1,2,5,3).
    // Finished processing permutation (4,5,2,1,3).

    // Now processing permutation (2,1,5,4,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_2_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,1,5,4,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (2,1,5,4,3) by sequences of 2-opt moves.

    // Now processing permutation (2,4,5,1,3).
    current_delta = ((current_delta + this.m_dist_1_3 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,4,5,1,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_3;
    }
    // Finished processing permutation (2,4,5,1,3).
    // Finished processing permutation (2,1,5,4,3).

    // Now processing permutation (5,2,1,4,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,2,1,4,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (5,2,1,4,3) by sequences of 2-opt moves.

    // Now processing permutation (5,2,4,1,3).
    current_delta = ((current_delta + this.m_dist_1_3 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,2,4,1,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_3;
    }
    // Finished processing permutation (5,2,4,1,3).
    // Finished processing permutation (5,2,1,4,3).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,2,4,3,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,2,4,3,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,2,4,3,5)
   */
  private final void __trace_9(final long parent_delta) {

    long current_delta;

    // Now processing permutation (1,3,4,2,5).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,3,4,2,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,3,4,2,5) by sequences of 2-opt moves.
    this.__trace_30(current_delta);
    // Finished processing permutation (1,3,4,2,5).

    // Now processing permutation (1,5,3,4,2).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_afterEnd)
        - this.m_dist_1_2 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,5,3,4,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,5,3,4,2) by sequences of 2-opt moves.
    this.__trace_33(current_delta);
    // Finished processing permutation (1,5,3,4,2).

    // Now processing permutation (5,3,4,2,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,3,4,2,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_1;
    }
    // Finished processing permutation (5,3,4,2,1).

    // Now processing permutation (3,4,2,1,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,4,2,1,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = (-1);
    }
    // Finished processing permutation (3,4,2,1,5).

    // Now processing permutation (4,2,1,3,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,2,1,3,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (4,2,1,3,5) by sequences of 2-opt moves.
    this.__trace_100(current_delta);
    // Finished processing permutation (4,2,1,3,5).

    // Now processing permutation (2,1,4,3,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_2_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,1,4,3,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (2,1,4,3,5) by sequences of 2-opt moves.

    // Now processing permutation (2,5,3,4,1).
    current_delta = ((current_delta + this.m_dist_1_afterEnd + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,5,3,4,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_1;
    }
    // Finished processing permutation (2,5,3,4,1).
    // Finished processing permutation (2,1,4,3,5).

    // Now processing permutation (1,4,2,3,5).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_2_3)
        - this.m_dist_1_2 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,4,2,3,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = (-1);
    }
    // Finished processing permutation (1,4,2,3,5).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,5,4,3,2). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,5,4,3,2).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,5,4,3,2)
   */
  private final void __trace_26(final long parent_delta) {

    long current_delta;

    // Now processing permutation (1,5,4,2,3).
    current_delta = ((parent_delta + this.m_dist_2_4 + this.m_dist_3_afterEnd)
        - this.m_dist_2_afterEnd - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,5,4,2,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_3;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,5,4,2,3) by sequences of 2-opt moves.

    // Now processing permutation (5,1,4,2,3).
    current_delta = ((current_delta + this.m_dist_beforeStart_5 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,1,4,2,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_3;
    }
    // Finished processing permutation (5,1,4,2,3).
    // Finished processing permutation (1,5,4,2,3).

    // Now processing permutation (3,4,5,1,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_2)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,4,5,1,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (3,4,5,1,2) by sequences of 2-opt moves.
    this.__trace_52(current_delta);
    // Finished processing permutation (3,4,5,1,2).

    // Now processing permutation (4,5,1,3,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,5,1,3,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_2;
    }
    // Finished processing permutation (4,5,1,3,2).

    // Now processing permutation (1,5,2,3,4).
    current_delta = ((parent_delta + this.m_dist_2_5 + this.m_dist_4_afterEnd)
        - this.m_dist_2_afterEnd - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,5,2,3,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,5,2,3,4) by sequences of 2-opt moves.

    // Now processing permutation (2,5,1,3,4).
    current_delta = ((current_delta + this.m_dist_beforeStart_2 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,5,1,3,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_4;
    }
    // Finished processing permutation (2,5,1,3,4).
    // Finished processing permutation (1,5,2,3,4).

    // Now processing permutation (2,3,4,5,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_2_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,3,4,5,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_1;
    }
    // Finished processing permutation (2,3,4,5,1).

    // Now processing permutation (5,1,4,3,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,1,4,3,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_2;
    }
    // Finished processing permutation (5,1,4,3,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,4,3,2,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,4,3,2,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,4,3,2,5)
   */
  private final void __trace_29(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,4,1,2,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_2)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,4,1,2,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = (-1);
    }
    // Finished processing permutation (3,4,1,2,5).

    // Now processing permutation (5,2,3,4,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,2,3,4,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_1;
    }
    // Finished processing permutation (5,2,3,4,1).

    // Now processing permutation (2,3,4,1,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_2_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,3,4,1,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = (-1);
    }
    // Finished processing permutation (2,3,4,1,5).

    // Now processing permutation (4,1,3,2,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,1,3,2,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = (-1);
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (4,1,3,2,5) by sequences of 2-opt moves.

    // Now processing permutation (4,1,3,5,2).
    current_delta = ((current_delta + this.m_dist_2_afterEnd + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,1,3,5,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_2;
    }
    // Finished processing permutation (4,1,3,5,2).
    // Finished processing permutation (4,1,3,2,5).

    // Now processing permutation (1,4,3,5,2).
    current_delta = ((parent_delta + this.m_dist_2_afterEnd + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,4,3,5,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_2;
    }
    // Finished processing permutation (1,4,3,5,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,3,2,4,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,3,2,4,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,3,2,4,5)
   */
  private final void __trace_39(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,1,2,4,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_2)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,1,2,4,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = (-1);
    }
    // Finished processing permutation (3,1,2,4,5).

    // Now processing permutation (2,3,1,4,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_2_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,3,1,4,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = (-1);
    }
    // Finished processing permutation (2,3,1,4,5).

    // Now processing permutation (5,4,2,3,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,4,2,3,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_1;
    }
    // Finished processing permutation (5,4,2,3,1).

    // Now processing permutation (4,2,3,1,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,2,3,1,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = (-1);
    }
    // Finished processing permutation (4,2,3,1,5).

    // Now processing permutation (1,3,5,4,2).
    current_delta = ((parent_delta + this.m_dist_2_afterEnd + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,3,5,4,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_2;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,3,5,4,2) by sequences of 2-opt moves.

    // Now processing permutation (5,3,1,4,2).
    current_delta = ((current_delta + this.m_dist_beforeStart_5 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,3,1,4,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_2;
    }
    // Finished processing permutation (5,3,1,4,2).
    // Finished processing permutation (1,3,5,4,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (4,3,2,1,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (4,3,2,1,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (4,3,2,1,5)
   */
  private final void __trace_60(final long parent_delta) {

    long current_delta;

    // Now processing permutation (4,3,5,1,2).
    current_delta = ((parent_delta + this.m_dist_2_afterEnd + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,3,5,1,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_2;
    }
    // Finished processing permutation (4,3,5,1,2).

    // Now processing permutation (4,3,1,2,5).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_5)
        - this.m_dist_1_5 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,3,1,2,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = (-1);
    }
    // Finished processing permutation (4,3,1,2,5).

    // Now processing permutation (4,3,2,5,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,3,2,5,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_1;
    }
    // Finished processing permutation (4,3,2,5,1).

    // Now processing permutation (4,5,1,2,3).
    current_delta = ((parent_delta + this.m_dist_3_afterEnd + this.m_dist_4_5)
        - this.m_dist_3_4 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,5,1,2,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_3;
    }
    // Finished processing permutation (4,5,1,2,3).

    // Now processing permutation (5,1,2,3,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_4_afterEnd)
        - this.m_dist_beforeStart_4 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,1,2,3,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_4;
    }
    // Finished processing permutation (5,1,2,3,4).

    // Now processing permutation (4,1,2,3,5).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_3_5)
        - this.m_dist_1_5 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,1,2,3,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = (-1);
    }
    // Finished processing permutation (4,1,2,3,5).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (5,4,3,2,1). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (5,4,3,2,1).
   *
   * @param parent_delta
   *          the length change between the original tour and (5,4,3,2,1)
   */
  private final void __trace_64(final long parent_delta) {

    long current_delta;

    // Now processing permutation (5,4,1,2,3).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_3_afterEnd)
        - this.m_dist_1_afterEnd - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,4,1,2,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_3;
    }
    // Finished processing permutation (5,4,1,2,3).

    // Now processing permutation (5,4,3,1,2).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_afterEnd)
        - this.m_dist_1_afterEnd - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,4,3,1,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_2;
    }
    // Finished processing permutation (5,4,3,1,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (3,2,1,4,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (3,2,1,4,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (3,2,1,4,5)
   */
  private final void __trace_79(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,5,4,1,2).
    current_delta = ((parent_delta + this.m_dist_2_afterEnd + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,5,4,1,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_2;
    }
    // Finished processing permutation (3,5,4,1,2).

    // Now processing permutation (3,2,4,1,5).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,2,4,1,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = (-1);
    }
    // Finished processing permutation (3,2,4,1,5).

    // Now processing permutation (3,2,5,4,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,2,5,4,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_1;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (3,2,5,4,1) by sequences of 2-opt moves.

    // Now processing permutation (3,5,2,4,1).
    current_delta = ((current_delta + this.m_dist_2_4 + this.m_dist_3_5)
        - this.m_dist_2_3 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,5,2,4,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_1;
    }
    // Finished processing permutation (3,5,2,4,1).
    // Finished processing permutation (3,2,5,4,1).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (2,1,3,4,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (2,1,3,4,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (2,1,3,4,5)
   */
  private final void __trace_119(final long parent_delta) {

    long current_delta;

    // Now processing permutation (2,4,3,1,5).
    current_delta = ((parent_delta + this.m_dist_1_5 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,4,3,1,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = (-1);
    }
    // Finished processing permutation (2,4,3,1,5).

    // Now processing permutation (2,5,4,3,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,5,4,3,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_1;
    }
    // Finished processing permutation (2,5,4,3,1).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,4,5,3,2). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,4,5,3,2).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,4,5,3,2)
   */
  private final void __trace_27(final long parent_delta) {

    long current_delta;

    // Now processing permutation (2,3,5,4,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_2_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,3,5,4,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_1;
    }
    // Finished processing permutation (2,3,5,4,1).

    // Now processing permutation (5,4,1,3,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,4,1,3,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_2;
    }
    // Finished processing permutation (5,4,1,3,2).

    // Now processing permutation (4,1,5,3,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,1,5,3,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_2;
    }
    // Finished processing permutation (4,1,5,3,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,5,3,2,4). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,5,3,2,4).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,5,3,2,4)
   */
  private final void __trace_36(final long parent_delta) {

    long current_delta;

    // Now processing permutation (1,3,5,2,4).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_5)
        - this.m_dist_1_5 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,3,5,2,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (1,3,5,2,4) by sequences of 2-opt moves.

    // Now processing permutation (3,1,5,2,4).
    current_delta = ((current_delta + this.m_dist_beforeStart_3 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,1,5,2,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_4;
    }
    // Finished processing permutation (3,1,5,2,4).
    // Finished processing permutation (1,3,5,2,4).

    // Now processing permutation (2,3,5,1,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_2_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,3,5,1,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_4;
    }
    // Trace the sub-set of permutations which efficiently can be reached
    // from (2,3,5,1,4) by sequences of 2-opt moves.

    // Now processing permutation (2,4,1,5,3).
    current_delta = ((current_delta + this.m_dist_2_4 + this.m_dist_3_afterEnd)
        - this.m_dist_2_3 - this.m_dist_4_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,4,1,5,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_3;
    }
    // Finished processing permutation (2,4,1,5,3).
    // Finished processing permutation (2,3,5,1,4).

    // Now processing permutation (5,1,3,2,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,1,3,2,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_4;
    }
    // Finished processing permutation (5,1,3,2,4).

    // Now processing permutation (4,2,3,5,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_4_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,2,3,5,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_1;
    }
    // Finished processing permutation (4,2,3,5,1).

    // Now processing permutation (3,5,1,2,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_2)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,5,1,2,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_4;
    }
    // Finished processing permutation (3,5,1,2,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,3,2,5,4). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,3,2,5,4).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,3,2,5,4)
   */
  private final void __trace_38(final long parent_delta) {

    long current_delta;

    // Now processing permutation (2,3,1,5,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_2_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,3,1,5,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_4;
    }
    // Finished processing permutation (2,3,1,5,4).

    // Now processing permutation (5,2,3,1,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,2,3,1,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_4;
    }
    // Finished processing permutation (5,2,3,1,4).

    // Now processing permutation (3,1,2,5,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_2)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,1,2,5,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_4;
    }
    // Finished processing permutation (3,1,2,5,4).

    // Now processing permutation (4,5,2,3,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_4_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,5,2,3,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_1;
    }
    // Finished processing permutation (4,5,2,3,1).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,2,5,3,4). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,2,5,3,4).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,2,5,3,4)
   */
  private final void __trace_2(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,5,2,1,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,5,2,1,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_4;
    }
    // Finished processing permutation (3,5,2,1,4).

    // Now processing permutation (4,3,5,2,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_4_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,3,5,2,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_1;
    }
    // Finished processing permutation (4,3,5,2,1).

    // Now processing permutation (5,2,1,3,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,2,1,3,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_4;
    }
    // Finished processing permutation (5,2,1,3,4).

    // Now processing permutation (2,1,5,3,4).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_2_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,1,5,3,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_4;
    }
    // Finished processing permutation (2,1,5,3,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (4,5,3,2,1). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (4,5,3,2,1).
   *
   * @param parent_delta
   *          the length change between the original tour and (4,5,3,2,1)
   */
  private final void __trace_63(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,5,4,2,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_2_4)
        - this.m_dist_beforeStart_4 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,5,4,2,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_1;
    }
    // Finished processing permutation (3,5,4,2,1).

    // Now processing permutation (4,5,3,1,2).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_afterEnd)
        - this.m_dist_1_afterEnd - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,5,3,1,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_2;
    }
    // Finished processing permutation (4,5,3,1,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,2,4,5,3). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,2,4,5,3).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,2,4,5,3)
   */
  private final void __trace_8(final long parent_delta) {

    long current_delta;

    // Now processing permutation (1,4,2,5,3).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (1,4,2,5,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = (-1);
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_3;
    }
    // Finished processing permutation (1,4,2,5,3).

    // Now processing permutation (4,2,1,5,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,2,1,5,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_3;
    }
    // Finished processing permutation (4,2,1,5,3).

    // Now processing permutation (5,4,2,1,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,4,2,1,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_3;
    }
    // Finished processing permutation (5,4,2,1,3).

    // Now processing permutation (2,1,4,5,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_2_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,1,4,5,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_3;
    }
    // Finished processing permutation (2,1,4,5,3).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (5,3,2,1,4). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (5,3,2,1,4).
   *
   * @param parent_delta
   *          the length change between the original tour and (5,3,2,1,4)
   */
  private final void __trace_75(final long parent_delta) {

    long current_delta;

    // Now processing permutation (5,3,2,4,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,3,2,4,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_1;
    }
    // Finished processing permutation (5,3,2,4,1).

    // Now processing permutation (5,3,4,1,2).
    current_delta = ((parent_delta + this.m_dist_2_afterEnd + this.m_dist_3_4)
        - this.m_dist_2_3 - this.m_dist_4_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,3,4,1,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_2;
    }
    // Finished processing permutation (5,3,4,1,2).

    // Now processing permutation (5,3,1,2,4).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_4)
        - this.m_dist_1_4 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,3,1,2,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_4;
    }
    // Finished processing permutation (5,3,1,2,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (3,2,1,5,4). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (3,2,1,5,4).
   *
   * @param parent_delta
   *          the length change between the original tour and (3,2,1,5,4)
   */
  private final void __trace_78(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,2,4,5,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,2,4,5,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_1;
    }
    // Finished processing permutation (3,2,4,5,1).

    // Now processing permutation (3,2,5,1,4).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,2,5,1,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_4;
    }
    // Finished processing permutation (3,2,5,1,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (2,1,3,5,4). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (2,1,3,5,4).
   *
   * @param parent_delta
   *          the length change between the original tour and (2,1,3,5,4)
   */
  private final void __trace_118(final long parent_delta) {

    long current_delta;

    // Now processing permutation (2,4,5,3,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_4_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,4,5,3,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_1;
    }
    // Finished processing permutation (2,4,5,3,1).

    // Now processing permutation (2,5,3,1,4).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,5,3,1,4)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_4;
    }
    // Finished processing permutation (2,5,3,1,4).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,3,4,5,2). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,3,4,5,2).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,3,4,5,2)
   */
  private final void __trace_31(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,1,4,5,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,1,4,5,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_2;
    }
    // Finished processing permutation (3,1,4,5,2).

    // Now processing permutation (4,3,1,5,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,3,1,5,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_3;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_2;
    }
    // Finished processing permutation (4,3,1,5,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,4,5,2,3). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,4,5,2,3).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,4,5,2,3)
   */
  private final void __trace_12(final long parent_delta) {

    long current_delta;

    // Now processing permutation (4,1,5,2,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_5)
        - this.m_dist_beforeStart_1 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,1,5,2,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = this.m_original_node_3;
    }
    // Finished processing permutation (4,1,5,2,3).

    // Now processing permutation (2,5,4,1,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_2_3);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,5,4,1,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_3;
    }
    // Finished processing permutation (2,5,4,1,3).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,5,2,4,3). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,5,2,4,3).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,5,2,4,3)
   */
  private final void __trace_6(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,4,2,5,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_3_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,4,2,5,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_1;
    }
    // Finished processing permutation (3,4,2,5,1).

    // Now processing permutation (4,2,5,1,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_4 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,2,5,1,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_1;
      this.m_best_node_5 = this.m_original_node_3;
    }
    // Finished processing permutation (4,2,5,1,3).

    // Now processing permutation (5,1,2,4,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_2)
        - this.m_dist_beforeStart_1 - this.m_dist_2_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,1,2,4,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_2;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_3;
    }
    // Finished processing permutation (5,1,2,4,3).

    // Now processing permutation (2,5,1,4,3).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_2_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,5,1,4,3)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_3;
    }
    // Finished processing permutation (2,5,1,4,3).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,3,4,2,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,3,4,2,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,3,4,2,5)
   */
  private final void __trace_30(final long parent_delta) {

    long current_delta;

    // Now processing permutation (5,2,4,3,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,2,4,3,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_1;
    }
    // Finished processing permutation (5,2,4,3,1).

    // Now processing permutation (3,1,4,2,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,1,4,2,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_4;
      this.m_best_node_4 = this.m_original_node_2;
      this.m_best_node_5 = (-1);
    }
    // Finished processing permutation (3,1,4,2,5).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (1,5,3,4,2). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (1,5,3,4,2).
   *
   * @param parent_delta
   *          the length change between the original tour and (1,5,3,4,2)
   */
  private final void __trace_33(final long parent_delta) {

    long current_delta;

    // Now processing permutation (2,4,3,5,1).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_afterEnd)
        - this.m_dist_beforeStart_1 - this.m_dist_2_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,4,3,5,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_1;
    }
    // Finished processing permutation (2,4,3,5,1).

    // Now processing permutation (5,1,3,4,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_5 + this.m_dist_1_3)
        - this.m_dist_beforeStart_1 - this.m_dist_3_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (5,1,3,4,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_5;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = (-1);
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_2;
    }
    // Finished processing permutation (5,1,3,4,2).

    // Now processing permutation (3,5,1,4,2).
    current_delta = ((parent_delta + this.m_dist_beforeStart_3 + this.m_dist_1_4)
        - this.m_dist_beforeStart_1 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,5,1,4,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_5;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_2;
    }
    // Finished processing permutation (3,5,1,4,2).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (4,2,1,3,5). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (4,2,1,3,5).
   *
   * @param parent_delta
   *          the length change between the original tour and (4,2,1,3,5)
   */
  private final void __trace_100(final long parent_delta) {

    long current_delta;

    // Now processing permutation (2,4,1,3,5).
    current_delta = ((parent_delta + this.m_dist_beforeStart_2 + this.m_dist_1_4)
        - this.m_dist_beforeStart_4 - this.m_dist_1_2);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (2,4,1,3,5)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_2;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = (-1);
    }
    // Finished processing permutation (2,4,1,3,5).

    // Now processing permutation (4,2,5,3,1).
    current_delta = ((parent_delta + this.m_dist_1_afterEnd + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_5_afterEnd);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (4,2,5,3,1)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_4;
      this.m_best_node_2 = (-1);
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = this.m_original_node_3;
      this.m_best_node_5 = this.m_original_node_1;
    }
    // Finished processing permutation (4,2,5,3,1).
  }

  /**
   * This function traces all permutations which can efficiently be reached
   * from (3,4,5,1,2). &quot;Efficiently&quot; means that there is no
   * shorter sequence of 2-opt moves starting at the canonical permutation
   * which yields the child-permutations traced here than the path going
   * over (3,4,5,1,2).
   *
   * @param parent_delta
   *          the length change between the original tour and (3,4,5,1,2)
   */
  private final void __trace_52(final long parent_delta) {

    long current_delta;

    // Now processing permutation (3,1,5,4,2).
    current_delta = ((parent_delta + this.m_dist_1_3 + this.m_dist_2_4)
        - this.m_dist_1_2 - this.m_dist_3_4);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,1,5,4,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_1;
      this.m_best_node_3 = this.m_original_node_5;
      this.m_best_node_4 = (-1);
      this.m_best_node_5 = this.m_original_node_2;
    }
    // Finished processing permutation (3,1,5,4,2).

    // Now processing permutation (3,4,1,5,2).
    current_delta = ((parent_delta + this.m_dist_1_4 + this.m_dist_2_5)
        - this.m_dist_1_2 - this.m_dist_4_5);
    if (current_delta < this.m_best_delta) {
      // A new best permutation has been discovered: (3,4,1,5,2)
      this.m_best_delta = current_delta;
      this.m_best_node_1 = this.m_original_node_3;
      this.m_best_node_2 = this.m_original_node_4;
      this.m_best_node_3 = this.m_original_node_1;
      this.m_best_node_4 = this.m_original_node_5;
      this.m_best_node_5 = this.m_original_node_2;
    }
    // Finished processing permutation (3,4,1,5,2).
  }

  /** {@inheritDoc} */
  @Override
  public final ExhaustivelyEnumeratingLocal5Optimizer clone() {
    return ((ExhaustivelyEnumeratingLocal5Optimizer) (super.clone()));
  }

  /** {@inheritDoc} */
  @Override
  public final int getSubPathLength() {
    return 5;
  }

}
