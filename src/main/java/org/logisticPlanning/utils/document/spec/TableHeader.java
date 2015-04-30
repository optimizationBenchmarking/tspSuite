package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The table header element.
 */
public class TableHeader extends AbstractTablePart {

  /**
   * create the document element
   * 
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  protected TableHeader(final Table owner) throws IOException {
    super(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.tableHeaderEnd(this);
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
  protected TableHeaderRow tableHeaderRowCreate(final TableHeader owner)
      throws IOException {
    return this.m_owner.tableHeaderRowCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableHeaderRowBegin(final TableHeaderRow h)
      throws IOException, IllegalStateException {
    this.m_owner.tableHeaderRowBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableHeaderRowEnd(final TableHeaderRow h)
      throws IOException, IllegalStateException {
    this.m_owner.tableHeaderRowEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public TableHeaderRow row() throws IOException, IllegalStateException {
    return this.m_owner.tableHeaderRow();
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
}
