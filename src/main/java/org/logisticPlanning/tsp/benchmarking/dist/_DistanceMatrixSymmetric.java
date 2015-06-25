package org.logisticPlanning.tsp.benchmarking.dist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.logisticPlanning.utils.NumberReader;

/**
 * <p>
 * A symmetric distance matrix. All unnecessary data elements are omitted.
 * The matrix is as compact as possible and uses a 1-dimensional array of
 * {@code int} as backing store. For an {@code n}-city TSP, it will hold
 * {@code ((int)((((long)(n)) * ((n) - 1)) >>> 1))} integer values.
 * <p>
 * The backing store of this matrix is a one-dimensional array of 32 bit
 * signed integer (int) ({@code int}). Arrays are indexed with 32 bit
 * signed integer (int) ({@code int}s). Thus, the highest possible value of
 * the matrix dimension {@code n} is 46'342. Be aware that such a matrix
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
 * <td>{@code n=46'342}</td>
 * </tr>
 * <tr>
 * <td>180&nbsp;bytes</td>
 * <td>8&prime;064&nbsp;bytes &asymp; 8&nbsp;KiB</td>
 * <td>19&prime;800&nbsp;bytes &asymp; 20&nbsp;KiB</td>
 * <td>2&prime;095&prime;104&nbsp;bytes &asymp; 2&nbsp;MiB</td>
 * <td>199&prime;980&prime;000&nbsp;bytes &asymp; 191&nbsp;MiB</td>
 * <td>536&prime;838&prime;144&nbsp;bytes &asymp; 512&nbsp;MiB</td>
 * <td>4&prime;295&prime;069&prime;244&nbsp;bytes &asymp; 5&nbsp;GiB</td>
 * </tr>
 * </table>
 * <p>
 * Thus, if {@code n} rises or you allocate many objects, you may get an
 * {@link java.lang.OutOfMemoryError}.
 * </p>
 * </p>
 */
final class _DistanceMatrixSymmetric extends _DistanceMatrix {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create the _DistanceMatrixSymmetric
   *
   * @param n
   *          the number of nodes
   */
  _DistanceMatrixSymmetric(final int n) {
    super(n);
    if (n > 46342) {
      // The maximum size is calculated such that 32-bit integer index
      // computations
      // cannot overflow.
      throw new IllegalArgumentException( //
          "Matrix dimension cannot be higher than 46'342."); //$NON-NLS-1$
    }
    this.m_data = new int[((int) ((((long) (n)) * ((n) - 1)) >>> 1))];
  }

  /** {@inheritDoc} */
  @Override
  final void setDistance(final int a, final int b, final int dist) {
    final int i, j;

    if (a > b) {
      i = a;
      j = b;
    } else {
      if (a == b) {
        if (dist == 0) {
          return;
        }
        throw new IllegalArgumentException("Invalid index"); //$NON-NLS-1$
      }
      i = b;
      j = a;
    }
    this.m_data[((((((i) - 1) * ((i) - 2)) >>> 1) + (j)) - 1)] = dist;
  }

  /** {@inheritDoc} */
  @Override
  public final int distance(final int a, final int b) {
    final int i, j;

    if (a > b) {
      i = a;
      j = b;
    } else {
      if (a == b) {
        return 0;
      }
      i = b;
      j = a;
    }
    return this.m_data[((((((i) - 1) * ((i) - 2)) >>> 1) + (j)) - 1)];
  }

  /** {@inheritDoc} */
  @Override
  final void fillFrom(final DistanceComputer src) {
    final int[] m;
    int i, j, k;

    m = this.m_data;
    i = 1;
    j = 1;

    for (k = 0; k < m.length; k++) {
      j++;
      if (j >= i) {
        i++;
        j = 1;
      }
      m[k] = src.distance(i, j);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void print(final PrintWriter out) {
    int f;

    super.print(out);

    _Constants.putTuple(_Constants.TYPE_STR, _Constants.TSP_STR, out);
    _Constants.putTuple(_Constants.EDGE_WEIGHT_FORMAT_STR,
        _Constants.LOWER_ROW_STR, out);
    out.println(_Constants.EDGE_WEIGHT_SECTION_STR);

    f = 0;
    for (final int z : this.m_data) {
      f++;
      out.print('\t');
      out.print(z);
      if ((f % _DistanceMatrix.MAX_NUMBERS_PER_LINE) == 0) {
        out.println();
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
                  if (j > i) {
                    this.setDistance(j, i, v);
                  } else {
                    if (this.distance(i, j) != v) {
                      throw new IOException("The matrix is not symmetric."); //$NON-NLS-1$
                    }
                  }
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
              }
            }
            return;
          }
          case _Constants.LOWER_COL:
          case _Constants.UPPER_ROW: {
            for (i = 1; i <= this.m_n; i++) {
              for (j = i + 1; j <= this.m_n; j++) {
                v = nr.nextInt();
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
