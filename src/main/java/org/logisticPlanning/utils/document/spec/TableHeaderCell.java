package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The table header cell element.
 */
public class TableHeaderCell extends AbstractTableCell {

  /**
   * create the document element
   * 
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  protected TableHeaderCell(final TableHeaderRow owner) throws IOException {
    super(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.tableHeaderCellEnd(this);
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
  public TableHeaderRow getOwner() {
    return ((TableHeaderRow) (this.m_owner));
  }

  /** {@inheritDoc} */
  @Override
  public final boolean isBodyCell() {
    return false;
  }
}
