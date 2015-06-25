package org.logisticPlanning.utils.math.units;

/**
 * The compiler is a helper class that can build the unit translation
 * table. It uses common fractions in order to avoid precision issues. It
 * will try to find a way to convert units in a without loss of precision.
 * It is programmed a bit inefficiently in terms of speed and memory
 * consumption, but it will do.
 */
final class _Compiler {

  /** the fractions */
  private final _Frac[][] m_frac;

  /**
   * create
   *
   * @param length
   *          the length
   */
  _Compiler(final int length) {
    super();

    this.m_frac = new _Frac[length][length];
  }

  /**
   * set the given value
   *
   * @param from
   *          the source unit
   * @param to
   *          the target unit
   * @param up
   *          the up
   * @param down
   *          the down
   */
  final void _set(final Enum<?> from, final Enum<?> to, final long up,
      final long down) {
    final int a, b;
    a = from.ordinal();
    b = to.ordinal();
    this.m_frac[a][b] = _Frac._make(up, down);
    this.m_frac[b][a] = _Frac._make(down, up);
  }

  /**
   * perform some compiling: this allows us to divide unit conversion rates
   * into different classes of precision. First enter all highly-precise
   * conversions, then pre-compile, then enter less precise conversions,
   * pre-compile, and repeat.
   */
  final void _precompile() {
    final _Frac[][] frac;
    final int len;
    _Frac f;
    int i, j, k;
    boolean found;

    frac = this.m_frac;
    len = frac.length;
    do {
      found = false;

      for (i = len; (--i) >= 0;) {
        inner: for (j = len; (--j) >= 0;) {

          if (i == j) {
            continue;
          }

          if (frac[i][j] == null) {

            for (k = len; (--k) >= 0;) {
              if ((k == i) || (k == j)) {
                continue;
              }

              if ((frac[i][k] != null) && (frac[k][j] != null)) {

                f = _Frac._mul(frac[i][k], frac[k][j]);
                if (f != null) {
                  frac[i][j] = f;
                  frac[j][i] = _Frac._inv(f);
                  found = true;
                  continue inner;
                }

              }

            }

          }

        }
      }

    } while (found);

  }

  /**
   * compile to a double array
   *
   * @return the double array
   */
  final double[][] _compile() {
    final _Frac[][] frac;
    final double[][] res;
    final int len;
    int i, j, k;
    boolean found;

    frac = this.m_frac;
    len = frac.length;
    this._precompile();

    // build matrix
    res = new double[len][len];
    for (i = len; (--i) >= 0;) {
      for (j = len; (--j) >= 0;) {
        if (i == j) {
          res[i][j] = 1d;
        } else {
          res[i][j] = _Frac._double(frac[i][j]);
        }
      }
    }

    // clean up remaining missing data, accept loss of precision
    do {
      found = false;

      for (i = len; (--i) >= 0;) {
        inner: for (j = len; (--j) >= 0;) {

          if (i == j) {
            continue;
          }

          if (res[i][j] <= 0d) {
            if (res[j][i] > 0d) {
              res[i][j] = (1d / res[j][i]);
              found = true;
              continue inner;
            }

            for (k = len; (--k) >= 0;) {
              if ((k == i) || (k == j)) {
                continue;
              }

              if ((res[i][k] > 0d) && (res[k][j] > 0d)) {
                res[i][j] = (res[i][k] * res[k][j]);
                found = true;
                continue inner;
              }

            }

          }

        }
      }

    } while (found);

    return res;
  }
}
