package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The table body row element.
 */
public class TableBodyRow extends AbstractTableRow {

  /**
   * create the document element
   * 
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  protected TableBodyRow(final TablePage owner) throws IOException {
    super(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.tableBodyRowEnd(this);
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
  public final TablePage getOwner() {
    return ((TablePage) (this.m_owner));
  }

  /** {@inheritDoc} */
  @Override
  public boolean isBodyRow() {
    return false;
  }

  /** {@inheritDoc} */
  @Override
  protected TableBodyCell tableBodyCellCreate(final TableBodyRow owner)
      throws IOException {
    return this.m_owner.tableBodyCellCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableBodyCellBegin(final TableBodyCell h)
      throws IOException, IllegalStateException {
    this.m_owner.tableBodyCellBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableBodyCellEnd(final TableBodyCell h)
      throws IOException, IllegalStateException {
    this.m_owner.tableBodyCellEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public TableBodyCell cell(final int cols, final int rows,
      final TableCellDef... def) throws IOException, IllegalStateException {
    return super.tableBodyCell(cols, rows, def);
  }

  /** {@inheritDoc} */
  @Override
  public TableBodyCell cell() throws IOException, IllegalStateException {
    return this.cell(1, 1);
  }
}
