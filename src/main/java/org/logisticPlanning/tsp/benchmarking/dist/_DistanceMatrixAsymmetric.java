package org.logisticPlanning.tsp.benchmarking.dist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.logisticPlanning.utils.NumberReader;

/**
 * An asymmetric distance matrix. All unnecessary data elements are
 * omitted. The matrix is as compact as possible and uses a 1-dimensional
 * array of {@code int} as backing store. For an {@code n}-city TSP, it
 * will hold {@code ((n) * ((n) - 1))} integer values.
 * <p>
 * The backing store of this matrix is a one-dimensional array of 32 bit
 * signed integer (int) ({@code int}). Arrays are indexed with 32 bit
 * signed integer (int) ({@code int}s). Thus, the highest possible value of
 * the matrix dimension {@code n} is 46'341. Be aware that such a matrix
 * may have a high memory consumption for high values of {@code n}:
 * </p>
 * <table border="1">
 * <tr>
 * <td>{@code n=10}</td>
 * <td>{@code n=64}</td>
 * <td>{@code n=100}</td>
 * <td>{@code n=1'024}</td>
 * <td>{@code n=10'000}</td>
 * <td>{@code n=16'384}</td>
 * <td>{@code n=46'341}</td>
 * </tr>
 * <tr>
 * <td>360&nbsp;bytes</td>
 * <td>16&prime;128&nbsp;bytes &asymp; 16&nbsp;KiB</td>
 * <td>39&prime;600&nbsp;bytes &asymp; 39&nbsp;KiB</td>
 * <td>4&prime;190&prime;208&nbsp;bytes &asymp; 4&nbsp;MiB</td>
 * <td>399&prime;960&prime;000&nbsp;bytes &asymp; 382&nbsp;MiB</td>
 * <td>1&prime;073&prime;676&prime;288&nbsp;bytes &asymp;
 * 1&prime;024&nbsp;MiB</td>
 * <td>8&prime;589&prime;767&prime;760&nbsp;bytes &asymp; 8&nbsp;GiB</td>
 * </tr>
 * </table>
 * <p>
 * Thus, if {@code n} rises or you allocate many objects, you may get an
 * {@link java.lang.OutOfMemoryError}.
 * </p>
 * </p>
 */
class _DistanceMatrixAsymmetric extends _DistanceMatrix {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create the _DistanceMatrixAsymmetric
   * 
   * @param n
   *          the number of nodes
   */
  _DistanceMatrixAsymmetric(final int n) {
    super(n);
    if (n > 46341) {
      // The maximum size is calculated such that 32-bit integer index
      // computations
      // cannot overflow.
      throw new IllegalArgumentException( //
          "Matrix dimension cannot be higher than 46'341."); //$NON-NLS-1$
    }
    this.m_data = new int[((n) * ((n) - 1))];
  }

  /** {@inheritDoc} */
  @Override
  final void setDistance(final int a, final int b, final int dist) {

    if (a == b) {
      if (dist == 0) {
        return;
      }
      throw new IllegalArgumentException("Invalid index"); //$NON-NLS-1$
    }

    this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))] = dist;
  }

  /** {@inheritDoc} */
  @Override
  public final int distance(final int a, final int b) {

    if (a == b) {
      return 0;
    }

    return this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))];
  }

  /** {@inheritDoc} */
  @Override
  final void fillFrom(final DistanceComputer src) {
    final int[] m;
    int i, j, k;

    m = this.m_data;
    k = 0;

    for (i = 1; i <= this.m_n; i++) {
      for (j = 1; j <= this.m_n; j++) {
        if (i == j) {
          continue;
        }
        m[k++] = src.distance(i, j);
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void print(final PrintWriter out) {
    final int[] m;
    int i, j, k, f;

    super.print(out);
    _Constants.putTuple(_Constants.TYPE_STR, _Constants.TSP_STR, out);
    _Constants.putTuple(_Constants.EDGE_WEIGHT_FORMAT_STR,
        _Constants.FULL_MATRIX_STR, out);
    out.println(_Constants.EDGE_WEIGHT_SECTION_STR);

    f = k = 0;
    m = this.m_data;
    for (i = 1; i <= this.m_n; i++) {
      for (j = 1; j <= this.m_n; j++) {
        f++;
        out.print('\t');
        if (i == j) {
          out.print('0');
        } else {
          out.print(m[k++]);
        }
        if ((f % _DistanceMatrix.MAX_NUMBERS_PER_LINE) == 0) {
          out.println();
        }
      }
    }

    if ((f % _DistanceMatrix.MAX_NUMBERS_PER_LINE) != 0) {
      out.println();
    }
    out.println(_Constants.EOF_STR);
  }

  /** {@inheritDoc} */
  @Override
  @SuppressWarnings("incomplete-switch")
  final void load(final BufferedReader in) throws IOException {
    String s, t;
    int edgeType;
    final NumberReader nr;
    int i, j, v;

    edgeType = 0;
    outer: while ((s = in.readLine()) != null) {
      s = s.trim();
      if (s.length() <= 0) {
        continue;
      }

      i = s.indexOf(':');
      if (i > 0) {
        t = s.substring(0, i).trim();

        if (t.length() <= 0) {
          continue outer;
        }

        if (_Constants.EDGE_WEIGHT_FORMAT_STR.equalsIgnoreCase(t)) {
          t = s.substring(i + 1).trim();
          if (t.length() <= 0) {
            continue outer;
          }
          for (edgeType = _Constants.FORMATS.length; (--edgeType) >= 0;) {
            if (_Constants.FORMATS[edgeType].equalsIgnoreCase(t)) {
              continue outer;
            }
          }
          continue outer;
        }
      }

      if (_Constants.EDGE_WEIGHT_SECTION_STR.equalsIgnoreCase(s)) {
        nr = new NumberReader(in);
        switch (edgeType) {
          case _Constants.FULL_MATRIX: {
            for (i = 1; i <= this.m_n; i++) {
              for (j = 1; j <= this.m_n; j++) {
                v = nr.nextInt();
                if (i != j) {
                  this.setDistance(i, j, v);
                }
              }
            }
            return;
          }

          case _Constants.UPPER_COL:
          case _Constants.LOWER_ROW: {
            for (i = 1; i <= this.m_n; i++) {
              for (j = 1; j < i; j++) {
                v = nr.nextInt();
                this.setDistance(i, j, v);
                this.setDistance(j, i, v);
              }
            }
            return;
          }
          case _Constants.LOWER_COL:
          case _Constants.UPPER_ROW: {
            for (i = 1; i <= this.m_n; i++) {
              for (j = i + 1; j <= this.m_n; j++) {
                v = nr.nextInt();
                this.setDistance(i, j, v);
                this.setDistance(j, i, v);
              }
            }
            return;
          }
          case _Constants.UPPER_DIAG_COL:
          case _Constants.LOWER_DIAG_ROW: {
            for (i = 1; i <= this.m_n; i++) {
              for (j = 1; j <= i; j++) {
                v = nr.nextInt();
                if (i != j) {
                  this.setDistance(i, j, v);
                  this.setDistance(j, i, v);
                }
              }
            }
            return;
          }
          case _Constants.LOWER_DIAG_COL:
          case _Constants.UPPER_DIAG_ROW: {
            for (i = 1; i <= this.m_n; i++) {
              for (j = i; j <= this.m_n; j++) {
                v = nr.nextInt();
                if (i != j) {
                  this.setDistance(i, j, v);
                  this.setDistance(j, i, v);
                }
              }
            }
            return;
          }
        }

        return;
      }

      if (_Constants.EOF_STR.equalsIgnoreCase(s)) {
        return;
      }
    }
  }

}
