package org.logisticPlanning.tsp.solving.utils;

import java.io.IOException;
import java.io.StringWriter;

import org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer;
import org.logisticPlanning.utils.io.NullWriter;
import org.logisticPlanning.utils.text.TextUtils;

/**
 * A class that can validate solutions.
 *
 * @since 0.9.8
 */
public final class SolutionValidator {

  /** the error prefix */
  public static final String ERROR_PREFIX = "VERIFICATION_ERROR"; //$NON-NLS-1$
  /** the OK prefix */
  public static final String OK_PREFIX = "VERIFICATION_OK"; //$NON-NLS-1$

  /** the error prefix */
  private static final String __ERROR = ((SolutionValidator.ERROR_PREFIX + ':') + '\t');
  /** the error prefix */
  private static final String __OK = ((SolutionValidator.OK_PREFIX + ':') + '\t');

  /**
   * Validate a solution path representation.
   *
   * @param path
   *          the path
   * @param length
   *          the length of the path, {@code -1l} if not specified
   * @param dc
   *          the distance computer, {@code null} if not specified
   * @param dest
   *          the {@link java.lang.Appendable} to write the message to, or
   *          {@code null} if output is unnecessary
   * @return {@code true} if the path is a valid solution, {@code false}
   *         otherwise
   * @throws IOException
   *           if the output to {@code dest} fails
   */
  public static final boolean validatePath(final int[] path,
      final long length, final DistanceComputer dc, final Appendable dest)
          throws IOException {
    return SolutionValidator.__validatePath(path, length, -1, dc, dest);
  }

  /**
   * Validate a solution path representation and throw a
   * {@link java.lang.IllegalArgumentException} if the validation fails.
   *
   * @param path
   *          the path
   * @param length
   *          the length of the path, {@code -1l} if not specified
   * @param dc
   *          the distance computer, {@code null} if not specified
   * @throws IllegalArgumentException
   *           if the validation fails.
   */
  public static final void validatePath(final int[] path,
      final long length, final DistanceComputer dc) {
    boolean res;

    try {
      try (StringWriter sw = new StringWriter()) {
        res = false;
        try {
          res = SolutionValidator.validatePath(path, length, dc, sw);
        } catch (final IOException ioe) {// fairly impossible
          throw new RuntimeException(ioe);
        }

        if (!res) {
          throw new IllegalArgumentException(TextUtils.prepare(sw
              .toString()));
        }
      }
    } catch (final IOException ioe2) {// impossible
      throw new RuntimeException(ioe2);
    }
  }

  /**
   * Validate a solution path representation.
   *
   * @param path
   *          the path
   * @param n
   *          the number of cities, {@code -1l} if not specified
   * @param dest
   *          the {@link java.lang.Appendable} to write the message to, or
   *          {@code null} if output is unnecessary
   * @return {@code true} if the path is a valid solution, {@code false}
   *         otherwise
   * @throws IOException
   *           if the output to {@code dest} fails
   */
  public static final boolean validatePath(final int[] path, final int n,
      final Appendable dest) throws IOException {
    return SolutionValidator.__validatePath(path, -1, n, null, dest);
  }

  /**
   * Validate a solution path representation and throw a
   * {@link java.lang.IllegalArgumentException} if the validation fails.
   *
   * @param path
   *          the path
   * @param n
   *          the number of cities, {@code -1l} if not specified
   */
  public static final void validatePath(final int[] path, final int n) {

    boolean res;

    try {
      try (StringWriter sw = new StringWriter()) {
        res = false;
        try {
          res = SolutionValidator.validatePath(path, n, sw);
        } catch (final IOException ioe) {// fairly impossible
          throw new RuntimeException(ioe);
        }

        if (!res) {
          throw new IllegalArgumentException(TextUtils.prepare(sw
              .toString()));
        }
      }
    } catch (final IOException ioe2) {// impossible
      throw new RuntimeException(ioe2);
    }
  }

  /**
   * Validate a solution path representation.
   *
   * @param path
   *          the path
   * @param length
   *          the length of the path, {@code -1l} if not specified
   * @param n
   *          the number of cities, {@code -1l} if not specified
   * @param dc
   *          the distance computer, {@code null} if not specified
   * @param dest
   *          the {@link java.lang.Appendable} to write the message to, or
   *          {@code null} if output is unnecessary
   * @return {@code true} if the path is a valid solution, {@code false}
   *         otherwise
   * @throws IOException
   *           if the output to {@code dest} fails
   */
  private static final boolean __validatePath(final int[] path,
      final long length, final int n, final DistanceComputer dc,
      final Appendable dest) throws IOException {
    final boolean[] visited;
    final int len, hash;
    final Appendable useDest;
    DistanceComputer useDC;
    int prev, dist;
    long total;

    useDest = ((dest != null) ? dest : NullWriter.INSTANCE);

    if (path == null) {
      useDest.append("Path must not be null."); //$NON-NLS-1$
      return false;
    }

    len = path.length;
    if (n >= 0) {
      if (len != n) {
        useDest.append(SolutionValidator.__ERROR);
        useDest.append(//
            "Dimension of path must be the same as provided dimension. However, the provided dimension is ");//$NON-NLS-1$
        useDest.append(Integer.toString(n));
        useDest.append(" and the length of the path array is ");//$NON-NLS-1$
        useDest.append(Integer.toString(len));
        return false;
      }

      if (dc != null) {
        if (dc.n() != n) {
          useDest.append(SolutionValidator.__ERROR);
          useDest.append(//
              "Dimension of distance computer must be the same as provided dimension. However, the provided dimension is ");//$NON-NLS-1$
          useDest.append(Integer.toString(n));
          useDest
          .append(" and the dimension of the distance computer is "); //$NON-NLS-1$
          useDest.append(Integer.toString(dc.n()));
          return false;
        }
      }
    }

    useDC = _Distance0Computer.INSTANCE;
    if (dc != null) {
      if (len != dc.n()) {
        useDest.append(SolutionValidator.__ERROR);
        useDest.append(//
            "Dimension of path must be the same as distance computer dimension. However, the distance computer dimension is ");//$NON-NLS-1$
        useDest.append(Integer.toString(dc.n()));
        useDest.append(" and the length of the path array is ");//$NON-NLS-1$
        useDest.append(Integer.toString(len));
        return false;
      }

      if (length >= 0l) {
        useDC = dc;
      }
    } else {
      if (length >= 0l) {
        useDest.append(SolutionValidator.__ERROR);
        useDest.append("Solution length ");//$NON-NLS-1$
        useDest.append(Long.toString(length));
        useDest
        .append(" is given, but no distance computer to verify it.");//$NON-NLS-1$
        return false;
      }
    }

    prev = path[len - 1];
    if (SolutionValidator.__checkNode(prev, len, useDest)) {
      return false;
    }

    total = 0l;
    visited = new boolean[len + 1];
    for (final int cur : path) {
      if (SolutionValidator.__checkNode(cur, len, useDest)) {
        return false;
      }
      if (visited[cur]) {
        useDest.append(SolutionValidator.__ERROR);
        useDest.append("Node ");//$NON-NLS-1$
        useDest.append(Integer.toString(cur));
        useDest.append(" appears more than once in the solution.");//$NON-NLS-1$
        return false;
      }
      visited[cur] = true;
      dist = useDC.distance(prev, cur);
      if (dist < 0) {
        useDest.append(SolutionValidator.__ERROR);
        useDest.append("Distance computer [");//$NON-NLS-1$
        useDest.append(TextUtils.className(useDC.getClass()));
        useDest.append("] returns negative distance ");//$NON-NLS-1$
        useDest.append(Integer.toString(dist));
        useDest.append(" for edge (");//$NON-NLS-1$
        useDest.append(Integer.toString(prev));
        useDest.append(", ");//$NON-NLS-1$
        useDest.append(Integer.toString(cur));
        useDest.append(").");//$NON-NLS-1$
        return false;
      }
      total += dist;
      prev = cur;
    }

    hash = RepresentationUtils.pathHashCode(path);

    if ((dc != null) && (length >= 0l)) {
      if (length != total) {
        useDest.append(SolutionValidator.__ERROR);
        useDest.append("Solution length ");//$NON-NLS-1$
        useDest.append(Long.toString(length));
        useDest.append(" is different from computed length ");//$NON-NLS-1$
        useDest.append(Long.toString(total));
        useDest.append(" [hash");//$NON-NLS-1$
        useDest.append(Integer.toString(hash));
        useDest.append("].");//$NON-NLS-1$
        return false;
      }
    }

    useDest.append(SolutionValidator.__OK);
    useDest.append("The solution [hash ");//$NON-NLS-1$
    useDest.append(Integer.toString(hash));
    useDest.append("] with ");//$NON-NLS-1$
    useDest.append(Integer.toString(len));
    useDest.append(" nodes has been verified.");//$NON-NLS-1$
    if ((dc != null) && (length >= 0l) && (total == length)) {
      useDest.append(" Its length ");//$NON-NLS-1$
      useDest.append(Long.toString(length));
      useDest.append(" has been confirmed.");//$NON-NLS-1$
    }

    return true;
  }

  /**
   * Check a node id
   *
   * @param i
   *          the node id
   * @param n
   *          the dimension
   * @param dest
   *          the destination to write to
   * @return {@code true} if the node id {@code i} is invalid
   * @throws IOException
   *           if I/O to dest fails
   */
  private static final boolean __checkNode(final int i, final int n,
      final Appendable dest) throws IOException {
    if ((i <= 0) || (i > n)) {
      dest.append(SolutionValidator.__ERROR);
      dest.append("Node ids must be between 1 and ");//$NON-NLS-1$
      dest.append(Integer.toString(n));
      dest.append(", but node id "); //$NON-NLS-1$
      dest.append(Integer.toString(i));
      dest.append("detected."); //$NON-NLS-1$
      return true;
    }

    return false;
  }

  /** create */
  private SolutionValidator() {
    super();
  }
}
