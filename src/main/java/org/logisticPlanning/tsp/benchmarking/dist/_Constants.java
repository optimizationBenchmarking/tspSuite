package org.logisticPlanning.tsp.benchmarking.dist;

import java.io.PrintWriter;

/**
 * <p>
 * This class is an internal class. Please do not instantiate it, use it by
 * yourself, or otherwise meddle with it.
 * </p>
 * <p>
 * In this class we provide some internal constants.
 * </p>
 */
class _Constants {

  /** the node coord section */
  static final String NODE_COORD_SECTION_STR = "NODE_COORD_SECTION"; //$NON-NLS-1$

  /** the edge weight type */
  static final String EDGE_WEIGHT_TYPE_STR = "EDGE_WEIGHT_TYPE";//$NON-NLS-1$

  /** the dimension string */
  static final String DIMENSION_STR = "DIMENSION";//$NON-NLS-1$

  /** 2d euclidean distance */
  static final String EUCLIDEAN_2D_STR = "EUC_2D";//$NON-NLS-1$

  /** 3d euclidean distance */
  static final String EUCLIDEAN_3D_STR = "EUC_3D";//$NON-NLS-1$

  /** 2d manhattan distance */
  static final String MANHATTAN_2D_STR = "MAN_2D";//$NON-NLS-1$

  /** 3d manhattan distance */
  static final String MANHATTAN_3D_STR = "MAN_3D";//$NON-NLS-1$

  /** 2d maximum distance */
  static final String MAXIMUM_2D_STR = "MAX_2D";//$NON-NLS-1$

  /** 3d maximum distance */
  static final String MAXIMUM_3D_STR = "MAX_3D";//$NON-NLS-1$

  /** 2d ceil */
  static final String EUCLIDEAN_CEIL_2D_STR = "CEIL_2D";//$NON-NLS-1$

  /** pseudo-euclidean distance */
  static final String EUCLIDEAN_PSEUDO_2D_STR = "ATT";//$NON-NLS-1$
  /** xray 1 distance */
  static final String XRAY_1_STR = "XRAY1";//$NON-NLS-1$

  /** geo distance */
  static final String GEO_STR = "GEO";//$NON-NLS-1$

  /** explicit distance */
  static final String EXPLICIT_STR = "EXPLICIT";//$NON-NLS-1$

  /** type */
  static final String TYPE_STR = "TYPE";//$NON-NLS-1$

  /** the tsp type */
  static final String TSP_STR = "TSP";//$NON-NLS-1$
  /** the atsp type */
  static final String ATSP_STR = "ATSP";//$NON-NLS-1$
  /** the eof string */
  static final String EOF_STR = "EOF";//$NON-NLS-1$
  /** edge weights */
  static final String EDGE_WEIGHT_SECTION_STR = "EDGE_WEIGHT_SECTION";//$NON-NLS-1$
  /** the function string */
  static final String FUNCTION_STR = "FUNCTION";//$NON-NLS-1$

  /** the edge weight format */
  static final String EDGE_WEIGHT_FORMAT_STR = "EDGE_WEIGHT_FORMAT";//$NON-NLS-1$
  /** the full matrix */
  static final String FULL_MATRIX_STR = "FULL_MATRIX";//$NON-NLS-1$
  /** lower row triangle */
  static final String LOWER_ROW_STR = "LOWER_ROW";//$NON-NLS-1$
  /** upper row triangle */
  static final String UPPER_ROW_STR = "UPPER_ROW";//$NON-NLS-1$
  /** lower row triangle+diagonal */
  static final String LOWER_DIAG_ROW_STR = "LOWER_DIAG_ROW";//$NON-NLS-1$
  /** upper row triangle+diagonal */
  static final String UPPER_DIAG_ROW_STR = "UPPER_DIAG_ROW";//$NON-NLS-1$
  /** lower column triangle */
  static final String LOWER_COL_STR = "LOWER_COL";//$NON-NLS-1$
  /** upper column triangle */
  static final String UPPER_COL_STR = "UPPER_COL";//$NON-NLS-1$
  /** lower column triangle+diagonal */
  static final String LOWER_DIAG_COL_STR = "LOWER_DIAG_COL";//$NON-NLS-1$
  /** upper column column+triangle */
  static final String UPPER_DIAG_COL_STR = "UPPER_DIAG_COL";//$NON-NLS-1$

  /** the edge weight format */
  static final int EDGE_WEIGHT_FORMAT = 0;
  /** the full matrix */
  static final int FULL_MATRIX = _Constants.EDGE_WEIGHT_FORMAT + 1;
  /** lower row triangle */
  static final int LOWER_ROW = _Constants.FULL_MATRIX + 1;
  /** upper row triangle */
  static final int UPPER_ROW = _Constants.LOWER_ROW + 1;
  /** lower row triangle+diagonal */
  static final int LOWER_DIAG_ROW = _Constants.UPPER_ROW + 1;
  /** upper row triangle+diagonal */
  static final int UPPER_DIAG_ROW = _Constants.LOWER_DIAG_ROW + 1;
  /** lower column triangle */
  static final int LOWER_COL = _Constants.UPPER_DIAG_ROW + 1;
  /** upper column triangle */
  static final int UPPER_COL = _Constants.LOWER_COL + 1;
  /** lower column triangle+diagonal */
  static final int LOWER_DIAG_COL = _Constants.UPPER_COL + 1;
  /** upper column column+triangle */
  static final int UPPER_DIAG_COL = _Constants.LOWER_DIAG_COL + 1;

  /** the format list */
  static final String[] FORMATS = new String[] {
    _Constants.EDGE_WEIGHT_FORMAT_STR, _Constants.FULL_MATRIX_STR,
    _Constants.LOWER_ROW_STR, _Constants.UPPER_ROW_STR,
    _Constants.LOWER_DIAG_ROW_STR, _Constants.UPPER_DIAG_ROW_STR,
    _Constants.LOWER_COL_STR, _Constants.UPPER_COL_STR,
    _Constants.LOWER_DIAG_COL_STR, _Constants.UPPER_DIAG_COL_STR };

  /** the separator */
  private static final char[] SEP = new char[] { ' ', ':', ' ' };

  /**
   * put a tuple
   *
   * @param key
   *          the key
   * @param value
   *          the value
   * @param pw
   *          the writer
   */
  static final void putTuple(final String key, final String value,
      final PrintWriter pw) {
    pw.print(key);
    pw.print(_Constants.SEP);
    pw.println(value);
  }
}
