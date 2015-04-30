package org.logisticPlanning.utils.document.spec;

/**
 * A set of pre-defined figure sizes, defined relatively to the document
 * structure (page width, height, column count, ...).
 */
public enum EDefaultFigureSize {

  /** a full page image */
  FULL_PAGE(1, 1, true),

  /**
   * a big figure spanning the page width, but not necessarily the page
   * height
   */
  PAGE_WIDE(1, -1, true),

  /**
   * a big figure spanning a whole column, but not necessarily the whole
   * page height
   */
  COLUMN_WIDE(1, -1, false),

  /**
   * column-wide figure, about two of which may fit on one page under each
   * other
   */
  TWO_PER_PAGE(1, 2, true),

  /**
   * two figures of this size will fit in a column and about two rows
   * should fit on a page
   */
  TWO_BY_TWO(2, 2, true),

  /**
   * two figures of this size will fit in a column, but it is not specified
   * how many rows of such figures may fit on a page
   */
  TWO_IN_A_ROW(2, -1, true),

  /**
   * three figures of this size will fit in a column, but it is not
   * specified how many rows of such figures may fit on a page
   */
  THREE_IN_A_ROW(3, -1, true),

  /**
   * four figures of this size will fit in a column, but it is not
   * specified how many rows of such figures may fit on a page
   */
  FOUR_IN_A_ROW(4, -1, true),

  /**
   * five figures of this size will fit in a column, but it is not
   * specified how many rows of such figures may fit on a page
   */
  FIVE_IN_A_ROW(5, -1, true);

  /** the number of figure size */
  static final int FIGURE_SIZE_COUNT = (FIVE_IN_A_ROW.ordinal() + 1);

  /**
   * the number of figures of that size that should fit along the x-axis,
   * {@code -1} if unspecified
   */
  private final int m_nx;
  /**
   * the number of figures of that size that should fit along the y-axis,
   * {@code -1} if unspecified
   */
  private final int m_ny;
  /**
   * does the x-axis span over all columns (i.e., the complete page,
   * {@code true}), or does it only span a single columns ({@code false})?
   */
  private final boolean m_spansAllColumns;

  /**
   * create a default figure size
   * 
   * @param nx
   *          the number of figures of that size that should fit along the
   *          x-axis, {@code -1} if unspecified
   * @param ny
   *          the number of figures of that size that should fit along the
   *          y-axis, {@code -1} if unspecified
   * @param spansAllColumns
   *          does the x-axis span over all columns (i.e., the complete
   *          page, {@code true}), or does it only span a single columns (
   *          {@code false})?
   */
  private EDefaultFigureSize(final int nx, final int ny,
      final boolean spansAllColumns) {
    this.m_nx = nx;
    this.m_ny = ny;
    this.m_spansAllColumns = spansAllColumns;
  }

  /**
   * Get the number of figures of that size that should fit along the
   * x-axis, {@code -1} if unspecified
   * 
   * @return the number of figures of that size that should fit along the
   *         x-axis, {@code -1} if unspecified
   */
  public final int getNX() {
    return this.m_nx;
  }

  /**
   * Get the number of figures of that size that should fit along the
   * y-axis, {@code -1} if unspecified
   * 
   * @return the number of figures of that size that should fit along the
   *         y-axis, {@code -1} if unspecified
   */
  public final int getNY() {
    return this.m_ny;
  }

  /**
   * Get does the x-axis span over all columns (i.e., the complete page,
   * {@code true}), or does it only span a single columns ({@code false})?
   * 
   * @return does the x-axis span over all columns (i.e., the complete
   *         page, {@code true}), or does it only span a single columns (
   *         {@code false} )?
   */
  public final boolean spansAllColumns() {
    return this.m_spansAllColumns;
  }
}
