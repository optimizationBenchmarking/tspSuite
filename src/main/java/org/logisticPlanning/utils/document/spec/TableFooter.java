package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The table footer element.
 */
public class TableFooter extends AbstractTablePart {

  /**
   * create the document element
   *
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  protected TableFooter(final Table owner) throws IOException {
    super(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.tableFooterEnd(this);
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
  protected TableFooterRow tableFooterRowCreate(final TableFooter owner)
      throws IOException {
    return this.m_owner.tableFooterRowCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableFooterRowBegin(final TableFooterRow h)
      throws IOException, IllegalStateException {
    this.m_owner.tableFooterRowBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableFooterRowEnd(final TableFooterRow h)
      throws IOException, IllegalStateException {
    this.m_owner.tableFooterRowEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public TableFooterRow row() throws IOException, IllegalStateException {
    return this.m_owner.tableFooterRow();
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
}
