package org.logisticPlanning.utils.document.spec;

import org.logisticPlanning.utils.NamedObject;

/**
 * a table cell definition
 */
public class TableCellDef extends NamedObject {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** left-aligned cell */
  public static final int ALIGN_LEFT = 0;

  /** right-aligned cell */
  public static final int ALIGN_RIGHT = (TableCellDef.ALIGN_LEFT + 1);

  /** centered cell */
  public static final int ALIGN_CENTER = (TableCellDef.ALIGN_RIGHT + 1);

  /** distributed cell */
  public static final int ALIGN_DISTRIBUTE = (TableCellDef.ALIGN_CENTER + 1);
  /** vertical row */
  public static final int VERTICAL_ROW = (TableCellDef.ALIGN_DISTRIBUTE + 1);

  /** min type */
  private static final int MIN_TYPE = TableCellDef.ALIGN_LEFT;
  /** max type */
  private static final int MAX_TYPE = TableCellDef.VERTICAL_ROW;

  /** the cell type */
  private final int m_type;

  /** the relative width */
  private final double m_relWidth;

  /** the left-aligned cell */
  public static final TableCellDef LEFT = new TableCellDef(
      TableCellDef.ALIGN_LEFT, Double.NEGATIVE_INFINITY, "l"); //$NON-NLS-1$

  /** the right-aligned cell */
  public static final TableCellDef RIGHT = new TableCellDef(
      TableCellDef.ALIGN_RIGHT, Double.NEGATIVE_INFINITY, "r"); //$NON-NLS-1$

  /** the centered cell */
  public static final TableCellDef CENTERED = new TableCellDef(
      TableCellDef.ALIGN_CENTER, Double.NEGATIVE_INFINITY, "c"); //$NON-NLS-1$

  /** the distributed cell */
  public static final TableCellDef DISTRIBUTED = new TableCellDef(
      TableCellDef.ALIGN_DISTRIBUTE, Double.NEGATIVE_INFINITY, "d"); //$NON-NLS-1$
  /** the vertical row */
  public static final TableCellDef VERTICAL_LINE = new TableCellDef(
      TableCellDef.VERTICAL_ROW, Double.NEGATIVE_INFINITY, "|"); //$NON-NLS-1$

  /**
   * create the table cell definition
   *
   * @param name
   *          the name
   * @param type
   *          the cell type
   * @param relWidth
   *          the relative width, {@code <0} for not set
   */
  private TableCellDef(final int type, final double relWidth,
      final String name) {
    super(name);

    this.m_type = type;
    this.m_relWidth = (((relWidth > 0d) && (!(Double.isNaN(relWidth)))) ? relWidth
        : Double.NEGATIVE_INFINITY);
    if ((this.m_relWidth >= 0d) && (type == TableCellDef.VERTICAL_ROW)) {
      throw new IllegalArgumentException(//
          "Vertical line cannot have relative width.");//$NON-NLS-1$
    }
  }

  /**
   * resolve the table state
   *
   * @param state
   *          the state
   * @return the table cell definition
   */
  @SuppressWarnings("incomplete-switch")
  private static final TableCellDef resolve(final int state) {
    switch (state) {
      case ALIGN_LEFT: {
        return TableCellDef.LEFT;
      }

      case ALIGN_RIGHT: {
        return TableCellDef.RIGHT;
      }

      case ALIGN_CENTER: {
        return TableCellDef.CENTERED;
      }

    }
    return TableCellDef.DISTRIBUTED;
  }

  /**
   * @param type
   *          the cell type
   * @param relWidth
   *          the relative width, {@code <0} for not set
   * @return the table cell definition
   */
  public static final TableCellDef make(final int type,
      final double relWidth) {
    final TableCellDef bp;

    if ((type < TableCellDef.MIN_TYPE) || (type > TableCellDef.MAX_TYPE)) {
      throw new IllegalArgumentException("Invalid type constant."); //$NON-NLS-1$
    }

    bp = TableCellDef.resolve(type);
    if (Double.isNaN(relWidth) || (relWidth < 0d)) {
      return bp;
    }

    if (Math.abs(relWidth) <= 0d) {
      throw new IllegalArgumentException(//
          "Zero relative width not allowed."); //$NON-NLS-1$
    }

    if (relWidth >= Double.POSITIVE_INFINITY) {
      throw new IllegalArgumentException(//
          "Infinite relative width not allowed."); //$NON-NLS-1$
    }

    return new TableCellDef(type, relWidth, bp.name()
        + Double.toString(relWidth));
  }

  /**
   * get the type
   *
   * @return the type
   */
  public final int getType() {
    return this.m_type;
  }

  /**
   * Does this cell have a relative width?
   *
   * @return the relative width
   */
  public final boolean hasRelativeWidth() {
    return (this.m_relWidth > 0d);
  }

  /**
   * Get the relative width of this cell
   *
   * @return the relative width of this cell
   * @throws UnsupportedOperationException
   *           if {@link #hasRelativeWidth()} returns {@code false}
   */
  public final double getRelativeWidth() {
    final double d;

    d = this.m_relWidth;
    if (d <= 0d) {
      throw new UnsupportedOperationException(//
          "Table cell has no relative width.");//$NON-NLS-1$
    }
    return d;
  }

  /**
   * read resolve
   *
   * @return the resolved instance
   */
  private final Object readResolve() {
    if (this.m_relWidth < 0d) {
      return TableCellDef.resolve(this.m_type);
    }
    return this;
  }

  /**
   * write replace
   *
   * @return the resolved instance
   */
  private final Object writeReplace() {
    return this.readResolve();
  }
}
