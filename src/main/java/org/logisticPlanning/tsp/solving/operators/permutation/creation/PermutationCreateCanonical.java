package org.logisticPlanning.tsp.solving.operators.permutation.creation;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.operators.NullaryOperator;

/**
 * The &quot;canonical permutation creator&quot; that always returns the
 * canonical permutation <code>(1, 2, 3, &hellip;, n)</code>.
 */
public class PermutationCreateCanonical extends NullaryOperator<int[]> {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared blueprint */
  private static int[] s_blueprint = new int[] { 1, 2, 3, 4, 5, 6, 7, 8,
    9, 10, 11, 12, 13, 14, 15, 16,//
  };

  /** the blueprint */
  private int[] m_blueprint;

  /** instantiate */
  public PermutationCreateCanonical() {
    this("canonicalPermutationCreator"); //$NON-NLS-1$
  }

  /**
   * instantiate
   *
   * @param name
   *          the name
   */
  PermutationCreateCanonical(final String name) {
    super(name);
    this.m_blueprint = PermutationCreateCanonical.s_blueprint;
  }

  /** {@inheritDoc} */
  @Override
  public void create(final Individual<int[]> dest,
      final ObjectiveFunction f) {
    final int n;
    int[] lb;

    dest.clearEvaluation();

    n = f.n();

    if ((dest.solution != null) && (dest.solution.length == n)) {
      PermutationCreateCanonical.makeCanonical(dest.solution);
    } else {
      lb = this.m_blueprint;
      if (lb.length != n) {
        this.m_blueprint = lb = PermutationCreateCanonical.canonical(n,
            false);
      }
      dest.solution = lb.clone();
    }
    dest.producer = this;
  }

  /**
   * Create a canonical permutation of the given dimension, i.e., a
   * permutation of the type {@code 1, 2, 3, 4, 5, 6, ..., n}.
   *
   * @param n
   *          the dimension, i.e., the number of elements in the
   *          permutation
   * @return the new canonical permutation
   */
  public static final int[] canonical(final int n) {
    return PermutationCreateCanonical.canonical(n, true);
  }

  /**
   * Create a canonical permutation of the given dimension, i.e., a
   * permutation of the type {@code 1, 2, 3, 4, 5, 6, ..., n}.
   *
   * @param n
   *          the dimension, i.e., the number of elements in the
   *          permutation
   * @param clone
   *          should the internal blueprint be cloned or can it be returned
   *          directly?
   * @return the new canonical permutation
   */
  private static final int[] canonical(final int n, final boolean clone) {
    int[] sb, g;
    int sl;

    sb = PermutationCreateCanonical.s_blueprint;
    sl = sb.length;

    if (sl <= n) {
      if (sl < n) {
        sb = new int[n];
        PermutationCreateCanonical.makeCanonical(sb);
        PermutationCreateCanonical.s_blueprint = sb;
      }
      return (clone ? sb.clone() : sb);
    }

    g = new int[n];
    System.arraycopy(sb, 0, g, 0, n);
    return g;
  }

  /**
   * Fill a tour array with node indices so it becomes the canonical tour
   * {@code 1, 2, 3, 4, 5, 6, ..., n}.
   *
   * @param nodes
   *          the nodes to fill with canonical values
   * @param n
   *          the number of elements in {@code node} to fill
   */
  public static final void makeCanonical(final int[] nodes, final int n) {
    int i;
    final int[] sb;

    sb = PermutationCreateCanonical.s_blueprint;
    i = Math.min(sb.length, n);
    System.arraycopy(sb, 0, nodes, 0, i);

    for (; i < n;) {
      nodes[i++] = i;
    }
  }

  /**
   * Fill a tour array with node indices so it becomes the canonical tour
   * {@code 1, 2, 3, 4, 5, 6, ..., n}.
   *
   * @param nodes
   *          the nodes to fill with canonical values
   */
  public static final void makeCanonical(final int[] nodes) {
    PermutationCreateCanonical.makeCanonical(nodes, nodes.length);
  }

  /**
   * Fill a tour array with the values of its indices:
   * {@code 0, 1, 2, 3, 4, 5, 6, ..., n-1}.
   *
   * @param nodes
   *          the nodes to fill with its indices
   * @param n
   *          the number of elements in {@code nodes} to fill
   */
  public static final void makeCanonicalZero(final int[] nodes, final int n) {
    int i;
    final int[] sb;

    if (n > 0) {
      nodes[0] = 0;

      sb = PermutationCreateCanonical.s_blueprint;
      i = Math.min(sb.length, n - 1);
      System.arraycopy(sb, 0, nodes, 1, i);

      for (; (++i) < n;) {
        nodes[i] = i;
      }
    }
  }

  /**
   * Fill a tour array with the values of its indices:
   * {@code 0, 1, 2, 3, 4, 5, 6, ..., n-1}.
   *
   * @param nodes
   *          the nodes to fill with its indices
   */
  public static final void makeCanonicalZero(final int[] nodes) {
    PermutationCreateCanonical.makeCanonicalZero(nodes, nodes.length);
  }
}
