package org.logisticPlanning.utils.document.spec;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

/**
 * The table element.
 */
public class Table extends _MultiLabeledElement implements
Iterable<TableCellDef> {

  /** the caption state */
  private static final int STATE_CAPTION = (Element.STATE_NOTHING + 1);

  /** the caption state */
  private static final int STATE_AFTER_CAPTION = (Table.STATE_CAPTION + 1);

  /** the header state */
  private static final int STATE_HEADER = (Table.STATE_AFTER_CAPTION + 1);

  /** the after-header state */
  private static final int STATE_AFTER_HEADER = (Table.STATE_HEADER + 1);

  /** the footer state */
  private static final int STATE_FOOTER = (Table.STATE_AFTER_HEADER + 1);

  /** the after footer state */
  private static final int STATE_AFTER_FOOTER = (Table.STATE_FOOTER + 1);

  /** the body state */
  private static final int STATE_BODY = (Table.STATE_AFTER_FOOTER + 1);
  /** the after body state */
  private static final int STATE_AFTER_BODY = (Table.STATE_BODY + 1);

  /** the table definition */
  final TableCellDef[] m_def;

  /** the blocked columns */
  private final int[] m_blocked;

  /** the links between cell index and definitions */
  private final int[] m_cellToDef;

  /** the rows per page */
  private final int m_rowsPerPage;

  /** the current row index */
  private int m_row;

  /** the current row on the page */
  private int m_pageRow;

  /** the current column index */
  private int m_nextCol;

  /** the number of header rows that are reserved on each page */
  private int m_reservedRows;

  /** the page */
  private int m_page;

  /** the table index */
  private int m_index;

  /** span over all columns? */
  private final boolean m_colspan;

  /**
   * create the document element
   *
   * @param owner
   *          the owning element
   * @param label
   *          the label of this component
   * @param def
   *          the table definition
   * @param rowsPerPage
   *          the rows per page
   * @param colspan
   *          {@code true} if this table spans over all columns,
   *          {@code false} if it only uses a single columns
   * @throws IOException
   *           if io fails
   */
  protected Table(final SectionBody owner, final Label label,
      final int rowsPerPage, final boolean colspan,
      final TableCellDef... def) throws IOException {
    super(owner, label);

    int i, cc;

    if (def == null) {
      throw new IllegalArgumentException(//
          "Tabular definition must not be null.");//$NON-NLS-1$
    }

    if (def.length <= 0) {
      throw new IllegalArgumentException(//
          "Tabular definition must contain at least one column.");//$NON-NLS-1$
    }

    cc = 0;
    for (final TableCellDef d : def) {
      if (d == null) {
        throw new IllegalArgumentException(
            "Table cell definition must not be null.");//$NON-NLS-1$
      }
      if (d.getType() != TableCellDef.VERTICAL_ROW) {
        cc++;
      }
    }

    if (cc <= 0) {
      throw new IllegalArgumentException(//
          "Tabular definition must contain at least one column, but contains only vertical lines.");//$NON-NLS-1$
    }

    this.m_def = def;
    this.m_blocked = new int[cc];
    this.m_cellToDef = new int[cc];

    i = cc = 0;
    for (final TableCellDef d : def) {
      if (d.getType() != TableCellDef.VERTICAL_ROW) {
        this.m_cellToDef[i++] = cc;
      }
      cc++;
    }

    this.m_rowsPerPage = rowsPerPage;
    this.m_index = this.m_document.nextTable();
    this.m_colspan = colspan;
  }

  /**
   * Does this table span over all columns in a multi-column document?
   *
   * @return {@code true} if this table spans over all columns,
   *         {@code false} if it only uses a single columns
   */
  public final boolean spansColumns() {
    return this.m_colspan;
  }

  /**
   * Get the index of the current row
   *
   * @return the index of the current row
   */
  public final int getCurrentRowIndex() {
    return this.m_row;
  }

  /**
   * Get the index of the current page row
   *
   * @return the index of the current page row
   */
  public final int getCurrentPageRowIndex() {
    return this.m_pageRow;
  }

  /**
   * Get the table index. Warning: This value may increase when the table
   * pages increase.
   *
   * @return the figure index
   */
  public final int getTableIndex() {
    return this.m_index;
  }

  /** {@inheritDoc} */
  @Override
  final ELabelType getLabelType() {
    return ELabelType.TABLE;
  }

  /** {@inheritDoc} */
  @Override
  protected TableCaption tableCaptionCreate(final Table owner)
      throws IOException {
    return super.tableCaptionCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableCaptionBegin(final TableCaption h)
      throws IOException, IllegalStateException {

    if (this.m_state != Element.STATE_NOTHING) {
      throw new IllegalStateException(//
          "Table caption must be the first element in a table.");//$NON-NLS-1$
    }
    this.m_state = Table.STATE_CAPTION;

    super.tableCaptionBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableCaptionEnd(final TableCaption h) throws IOException,
  IllegalStateException {

    if (this.m_state != Table.STATE_CAPTION) {
      throw new IllegalStateException(//
          "Table caption must end after it has begun.");//$NON-NLS-1$
    }
    this.m_state = Table.STATE_AFTER_CAPTION;

    super.tableCaptionEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public TableCaption tableCaption() throws IOException,
  IllegalStateException {
    return super.tableCaption();
  }

  /**
   * Get the number of columns
   *
   * @return the number of columns
   */
  public final int getColumnCount() {
    return this.m_def.length;
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.tableEnd(this);
  }

  /** {@inheritDoc} */
  @Override
  public final void close() throws IOException {
    if (this.checkDead(Table.STATE_AFTER_BODY)) {
      this.doClose();
    }
  }

  /** check if a part can end */
  private final void checkEndPart() {
    // can only begin new page if no multi-row cell extends to the new row
    for (final int i : this.m_blocked) {
      if (i > this.m_row) {
        throw new IllegalStateException(//
            "Cannot end table part with open multi-row cell!"); //$NON-NLS-1$
      }
    }
  }

  /** flush a meta part */
  private final void flushMeta() {
    this.checkEndPart();
    Arrays.fill(this.m_blocked, 0);
    this.m_reservedRows += this.m_row;
    this.m_row = 0;
    this.m_pageRow = 0;
    this.m_nextCol = 0;
    this.m_page = 0;
  }

  /** {@inheritDoc} */
  @Override
  protected TableHeader tableHeaderCreate(final Table owner)
      throws IOException {
    return super.tableHeaderCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableHeaderBegin(final TableHeader h) throws IOException,
  IllegalStateException {

    if (this.m_state != Table.STATE_AFTER_CAPTION) {
      throw new IllegalStateException(//
          "Table header must come directly after table caption.");//$NON-NLS-1$
    }
    this.m_state = Table.STATE_HEADER;

    this.m_page = 1;

    super.tableHeaderBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableHeaderEnd(final TableHeader h) throws IOException,
  IllegalStateException {

    if (this.m_state != Table.STATE_HEADER) {
      throw new IllegalStateException(//
          "Table header can only end after it has begun."); //$NON-NLS-1$
    }
    this.m_state = Table.STATE_AFTER_HEADER;

    if (this.m_row <= 0) {
      throw new IllegalStateException(//
          "Table header must have at least one row.");//$NON-NLS-1$
    }

    try {
      super.tableHeaderEnd(h);
    } finally {
      this.flushMeta();
    }
  }

  /** {@inheritDoc} */
  @Override
  public TableHeader tableHeader() throws IOException,
  IllegalStateException {
    return super.tableHeader();
  }

  /** {@inheritDoc} */
  @Override
  protected TableFooter tableFooterCreate(final Table owner)
      throws IOException {
    return super.tableFooterCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableFooterBegin(final TableFooter h) throws IOException,
  IllegalStateException {

    if (this.m_state != Table.STATE_AFTER_HEADER) {
      throw new IllegalStateException(//
          "Table footer must directly follow the table header.");//$NON-NLS-1$
    }
    this.m_state = Table.STATE_FOOTER;

    this.m_page = 1;

    super.tableFooterBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableFooterEnd(final TableFooter h) throws IOException,
  IllegalStateException {

    if (this.m_state != Table.STATE_FOOTER) {
      throw new IllegalStateException(//
          "Table footer must end after it has begin.");//$NON-NLS-1$
    }
    this.m_state = Table.STATE_AFTER_FOOTER;

    try {
      super.tableFooterEnd(h);
    } finally {
      this.flushMeta();
    }
  }

  /** {@inheritDoc} */
  @Override
  public TableFooter tableFooter() throws IOException,
  IllegalStateException {
    return super.tableFooter();
  }

  /** {@inheritDoc} */
  @Override
  protected TableBody tableBodyCreate(final Table owner)
      throws IOException {
    return super.tableBodyCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableBodyBegin(final TableBody h) throws IOException,
  IllegalStateException {

    if (this.m_state == Table.STATE_AFTER_HEADER) {
      try (final TableFooter tf = this.tableFooter()) {
        // create an empty footer
      }
    }

    if (this.m_state != Table.STATE_AFTER_FOOTER) {
      throw new IllegalStateException(//
          "Table body must follow table footer.");//$NON-NLS-1$
    }
    this.m_state = Table.STATE_BODY;
    this.m_page = 1;

    super.tableBodyBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableBodyEnd(final TableBody h) throws IOException,
  IllegalStateException {

    if (this.m_state != Table.STATE_BODY) {
      throw new IllegalStateException(//
          "Table body must end after it has begun.");//$NON-NLS-1$
    }
    this.m_state = Table.STATE_AFTER_BODY;

    this.checkEndPart();

    if (this.m_row <= 0) {
      throw new IllegalStateException(//
          "Table body must have at least one row."); //$NON-NLS-1$
    }

    super.tableBodyEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public TableBody tableBody() throws IOException, IllegalStateException {
    return super.tableBody();
  }

  /**
   * begin a row
   *
   * @return {@code true} if a new page is needed, {@code false} if not
   */
  final boolean beginRow() {

    this.m_row++;
    this.m_pageRow++;
    this.m_nextCol = 0;

    if (this.m_state != Table.STATE_BODY) {
      // only in body state, we allow page breaks
      return false;
    }

    // no page break necessary: the total number of rows is not too high
    if (this.m_pageRow <= (this.m_rowsPerPage - this.m_reservedRows)) {
      return false;
    }

    // ok, a page-break should come as soon as possible
    // but it can only come if all multi-row cells have been closed
    for (final int i : this.m_blocked) {
      if (i >= this.m_row) {
        return false;// oh, still in multi-row environment
      }
    }

    // no multi-row environment is open, we can break the page
    this.m_pageRow = 1;
    this.m_page++;
    this.m_index = this.m_document.nextTable();
    return true;
  }

  /**
   * get the index of the current page
   *
   * @return the index of the current page
   */
  public final int getCurrentPageIndex() {
    return this.m_page;
  }

  /**
   * begin a table cell
   *
   * @param cell
   *          the table cell
   * @param def
   *          the cell's definition
   * @param cols
   *          the number of columns
   * @param rows
   *          the number of rows
   */
  final void beginCell(final AbstractTableCell cell,
      final TableCellDef[] def, final int cols, final int rows) {

    final int[] blocked;
    TableCellDef x;
    int cc, sc, ec, end, r, i;

    sc = this.m_nextCol;
    r = this.m_row;
    blocked = this.m_blocked;

    while (blocked[sc] >= r) {
      sc++;
      if (sc >= blocked.length) {
        throw new IllegalArgumentException(//
            "Start column of cell is outside table definition."); //$NON-NLS-1$
      }
    }

    ec = end = (sc + cols);
    if (ec > blocked.length) {
      throw new IllegalArgumentException(//
          "End column of cell is outside table definition."); //$NON-NLS-1$
    }

    for (; (--ec) >= sc;) {
      if (blocked[ec] >= r) {
        throw new IllegalArgumentException(//
            "Cell intersects with multi-row cell."); //$NON-NLS-1$
      }
    }

    cell.m_startRow = r;
    r = ((r + rows) - 1);
    cell.m_endRow = r;
    Arrays.fill(blocked, sc, end, r);
    this.m_nextCol = end;

    if ((def == null) || (def.length <= 0)) {
      if (((cols > 1) || (rows > 1))) {
        throw new IllegalArgumentException(//
            "Multi column or row cells must have a definition set."); //$NON-NLS-1$
      }
      cell.m_def = this.m_def;
      cell.m_ofs = this.m_cellToDef[sc];
      cell.m_count = (((end < blocked.length) ? this.m_cellToDef[end]
          : this.m_def.length) - cell.m_ofs);
      cell.m_treatAsMulti = false;
    } else {
      cell.m_def = def;
      cell.m_ofs = 0;
      cell.m_count = def.length;
      cell.m_treatAsMulti = true;
    }
    cell.m_startColumn = (sc + 1);
    cell.m_endColumn = end;

    if (cell.m_count <= 0) {
      throw new IllegalArgumentException(//
          "Number of cell definitions of a cell cannot be zero.");//$NON-NLS-1$
    }
    if (rows <= 0) {
      throw new IllegalArgumentException(//
          "Number of rows of a cell must be larger or equal to 1.");//$NON-NLS-1$
    }

    cc = 0;
    for (i = (cell.m_ofs + cell.m_count); (--i) >= cell.m_ofs;) {
      x = cell.m_def[i];
      if (x == null) {
        throw new IllegalArgumentException(//
            "Table cell definition must not be null.");//$NON-NLS-1$
      }
      if (x.getType() != TableCellDef.VERTICAL_ROW) {
        cc++;
      }
    }

    if (cc != 1) {
      throw new IllegalArgumentException(//
          "Table cell must have exactly one definition.");//$NON-NLS-1$
    }

    if (cols < 1) {
      throw new IllegalArgumentException(//
          "Table cell must contain at least one column.");//$NON-NLS-1$
    }

    // cell.m_cols = cols;
    // cell.m_rows = rows;
  }

  /**
   * Initialize the table row
   *
   * @param row
   *          the table row
   */
  private final void initRow(final AbstractTableRow row) {
    row.m_page = this.m_page;
    row.m_row = this.m_row;
    row.m_rowOnPage = this.m_pageRow;
  }

  /** {@inheritDoc} */
  @Override
  protected TableHeaderRow tableHeaderRowCreate(final TableHeader owner)
      throws IOException {
    return super.tableHeaderRowCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableHeaderRowBegin(final TableHeaderRow h)
      throws IOException, IllegalStateException {

    if (this.m_state != Table.STATE_HEADER) {
      throw new IllegalStateException(//
          "Header rows can only begin in table header."); //$NON-NLS-1$
    }

    if (this.beginRow()) {
      throw new IllegalStateException(//
          "Table page breaks can only happen in table body."); //$NON-NLS-1$
    }

    this.initRow(h);

    super.tableHeaderRowBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableHeaderRowEnd(final TableHeaderRow h)
      throws IOException, IllegalStateException {

    if (this.m_state != Table.STATE_HEADER) {
      throw new IllegalStateException(//
          "Header rows can only end in table header."); //$NON-NLS-1$
    }

    super.tableHeaderRowEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  protected TableHeaderCell tableHeaderCellCreate(
      final TableHeaderRow owner) throws IOException {
    return super.tableHeaderCellCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableHeaderCellBegin(final TableHeaderCell h)
      throws IOException, IllegalStateException {

    if (this.m_state != Table.STATE_HEADER) {
      throw new IllegalStateException(//
          "Header cells can only begin in table header."); //$NON-NLS-1$
    }

    super.tableHeaderCellBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableHeaderCellEnd(final TableHeaderCell h)
      throws IOException, IllegalStateException {

    if (this.m_state != Table.STATE_HEADER) {
      throw new IllegalStateException(//
          "Header cells can only end in table header."); //$NON-NLS-1$
    }

    super.tableHeaderCellEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  protected TableFooterRow tableFooterRowCreate(final TableFooter owner)
      throws IOException {
    return super.tableFooterRowCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableFooterRowBegin(final TableFooterRow h)
      throws IOException, IllegalStateException {

    if (this.m_state != Table.STATE_FOOTER) {
      throw new IllegalStateException(//
          "Footer rows can only begin in table footer."); //$NON-NLS-1$
    }

    if (this.beginRow()) {
      throw new IllegalStateException(//
          "Table page breaks can only happen in table body."); //$NON-NLS-1$
    }

    this.initRow(h);

    super.tableFooterRowBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableFooterRowEnd(final TableFooterRow h)
      throws IOException, IllegalStateException {

    if (this.m_state != Table.STATE_FOOTER) {
      throw new IllegalStateException(//
          "Footer rows can only end in table footer."); //$NON-NLS-1$
    }

    super.tableFooterRowEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  protected TableFooterCell tableFooterCellCreate(
      final TableFooterRow owner) throws IOException {
    return super.tableFooterCellCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableFooterCellBegin(final TableFooterCell h)
      throws IOException, IllegalStateException {

    if (this.m_state != Table.STATE_FOOTER) {
      throw new IllegalStateException(//
          "Footer cells can only begin in table footer."); //$NON-NLS-1$
    }

    super.tableFooterCellBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableFooterCellEnd(final TableFooterCell h)
      throws IOException, IllegalStateException {

    if (this.m_state != Table.STATE_FOOTER) {
      throw new IllegalStateException(//
          "Footer cells can only end in table footer."); //$NON-NLS-1$
    }

    super.tableFooterCellEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  protected TablePage tablePageCreate(final TableBody owner)
      throws IOException {
    return super.tablePageCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void tablePageBegin(final TablePage h) throws IOException,
  IllegalStateException {

    if (this.m_state != Table.STATE_BODY) {
      throw new IllegalStateException(//
          "Table pages can only begin in table body."); //$NON-NLS-1$
    }

    super.tablePageBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void tablePageEnd(final TablePage h) throws IOException,
  IllegalStateException {

    if (this.m_state != Table.STATE_BODY) {
      throw new IllegalStateException(//
          "Table pages can only end in table body."); //$NON-NLS-1$
    }

    super.tablePageEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  protected TableBodyRow tableBodyRowCreate(final TablePage owner)
      throws IOException {
    return super.tableBodyRowCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableBodyRowBegin(final TableBodyRow h)
      throws IOException, IllegalStateException {

    if (this.m_state != Table.STATE_BODY) {
      throw new IllegalStateException(//
          "Body rows can only begin in table body."); //$NON-NLS-1$
    }

    this.initRow(h);

    super.tableBodyRowBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableBodyRowEnd(final TableBodyRow h) throws IOException,
  IllegalStateException {

    if (this.m_state != Table.STATE_BODY) {
      throw new IllegalStateException(//
          "Body rows cells can only end in table body."); //$NON-NLS-1$
    }

    super.tableBodyRowEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  protected TableBodyCell tableBodyCellCreate(final TableBodyRow owner)
      throws IOException {
    return super.tableBodyCellCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableBodyCellBegin(final TableBodyCell h)
      throws IOException, IllegalStateException {

    if (this.m_state != Table.STATE_BODY) {
      throw new IllegalStateException(//
          "Body cells can only begin in table body."); //$NON-NLS-1$
    }

    super.tableBodyCellBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableBodyCellEnd(final TableBodyCell h)
      throws IOException, IllegalStateException {

    if (this.m_state != Table.STATE_BODY) {
      throw new IllegalStateException(//
          "Body cells can only end in table body."); //$NON-NLS-1$
    }

    super.tableBodyCellEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public final Iterator<TableCellDef> iterator() {
    return new _TableDefIterator(this);
  }
}
