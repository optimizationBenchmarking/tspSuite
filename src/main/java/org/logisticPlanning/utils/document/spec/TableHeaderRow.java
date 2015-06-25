package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The table header row element.
 */
public class TableHeaderRow extends AbstractTableRow {

  /**
   * create the document element
   *
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  protected TableHeaderRow(final TableHeader owner) throws IOException {
    super(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.tableHeaderRowEnd(this);
  }

  /** {@inheritDoc} */
  @Override
  public final void close() throws IOException {
    if (this.checkDead(Element.STATE_NOTHING)) {
      this.doClose();
    }
  }

  /** {@inheritDoc} */
  @Override
  public final TableHeader getOwner() {
    return ((TableHeader) (this.m_owner));
  }

  /** {@inheritDoc} */
  @Override
  public boolean isBodyRow() {
    return false;
  }

  /** {@inheritDoc} */
  @Override
  protected TableHeaderCell tableHeaderCellCreate(
      final TableHeaderRow owner) throws IOException {
    return this.m_owner.tableHeaderCellCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableHeaderCellBegin(final TableHeaderCell h)
      throws IOException, IllegalStateException {
    this.m_owner.tableHeaderCellBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableHeaderCellEnd(final TableHeaderCell h)
      throws IOException, IllegalStateException {
    this.m_owner.tableHeaderCellEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public TableHeaderCell cell(final int cols, final int rows,
      final TableCellDef... def) throws IOException, IllegalStateException {
    return this.tableHeaderCell(cols, rows, def);
  }

  /** {@inheritDoc} */
  @Override
  public TableHeaderCell cell() throws IOException, IllegalStateException {
    return this.cell(1, 1);
  }
}
