package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The table body cell element.
 */
public class TableBodyCell extends AbstractTableCell {

  /**
   * create the document element
   *
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  protected TableBodyCell(final TableBodyRow owner) throws IOException {
    super(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.tableBodyCellEnd(this);
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
  public TableBodyRow getOwner() {
    return ((TableBodyRow) (this.m_owner));
  }

  /** {@inheritDoc} */
  @Override
  public final boolean isBodyCell() {
    return false;
  }
}
