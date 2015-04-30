package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The table footer row element.
 */
public class TableFooterRow extends AbstractTableRow {

  /**
   * create the document element
   * 
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  protected TableFooterRow(final TableFooter owner) throws IOException {
    super(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.tableFooterRowEnd(this);
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
  public TableFooter getOwner() {
    return ((TableFooter) (this.m_owner));
  }

  /** {@inheritDoc} */
  @Override
  public final boolean isBodyRow() {
    return false;
  }

  /** {@inheritDoc} */
  @Override
  protected TableFooterCell tableFooterCellCreate(
      final TableFooterRow owner) throws IOException {
    return this.m_owner.tableFooterCellCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableFooterCellBegin(final TableFooterCell h)
      throws IOException, IllegalStateException {
    this.m_owner.tableFooterCellBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableFooterCellEnd(final TableFooterCell h)
      throws IOException, IllegalStateException {
    this.m_owner.tableFooterCellEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public TableFooterCell cell(final int cols, final int rows,
      final TableCellDef... def) throws IOException, IllegalStateException {
    return super.tableFooterCell(cols, rows, def);
  }

  /** {@inheritDoc} */
  @Override
  public TableFooterCell cell() throws IOException, IllegalStateException {
    return this.cell(1, 1);
  }
}
