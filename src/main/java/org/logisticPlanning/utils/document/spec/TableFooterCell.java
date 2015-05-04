package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The table footer cell element.
 */
public class TableFooterCell extends AbstractTableCell {

  /**
   * create the document element
   *
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  protected TableFooterCell(final TableFooterRow owner) throws IOException {
    super(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.tableFooterCellEnd(this);
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
  public TableFooterRow getOwner() {
    return ((TableFooterRow) (this.m_owner));
  }

  /** {@inheritDoc} */
  @Override
  public final boolean isBodyCell() {
    return false;
  }
}
