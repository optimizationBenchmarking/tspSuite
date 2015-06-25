package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The internal table row element .
 */
public abstract class AbstractTableRow extends Element {

  /** the row */
  int m_row;

  /** the row on the page */
  int m_rowOnPage;

  /** the page index */
  int m_page;

  /**
   * create the document element
   *
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  AbstractTableRow(final Element owner) throws IOException {
    super(owner);
  }

  /**
   * Get the row index
   *
   * @return the row index
   */
  public final int getRowIndex() {
    return this.m_row;
  }

  /**
   * Get the row on page
   *
   * @return the row on the page
   */
  public final int getRowOnPage() {
    return this.m_rowOnPage;
  }

  /**
   * Get the page of this row
   *
   * @return the page of this row
   */
  public final int getPage() {
    return this.m_page;
  }

  /**
   * Is this row a body row ({@code true} will be returned) or a
   * header/footer row ({@code false} will be returned)?
   *
   * @return {@code true} for body rows, {@code false} for header or footer
   *         rows
   */
  public abstract boolean isBodyRow();

  /**
   * Open a new table cell
   *
   * @param cols
   *          the number of columns occupied by the cell
   * @param rows
   *          the number of rows occupied by the cell
   * @param def
   *          the cell definition
   * @return the handle to the new cell
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if it is not allowed to have a cell here
   */
  public abstract AbstractTableCell cell(final int cols, final int rows,
      final TableCellDef... def) throws IOException, IllegalStateException;

  /**
   * Open a new table cell
   *
   * @return the handle to the new cell
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if it is not allowed to have a cell here
   */
  public AbstractTableCell cell() throws IOException,
      IllegalStateException {
    return this.cell(1, 1);
  }
}
