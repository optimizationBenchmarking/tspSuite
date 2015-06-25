package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The table body element.
 */
public class TableBody extends AbstractTablePart {

  /**
   * create the document element
   *
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  protected TableBody(final Table owner) throws IOException {
    super(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    try {
      if (this.m_document.m_current.m_owner == this) {
        this.m_document.m_current.close();
      }
    } finally {
      this.m_owner.tableBodyEnd(this);
    }
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
  protected TablePage tablePageCreate(final TableBody owner)
      throws IOException {
    return this.m_owner.tablePageCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void tablePageBegin(final TablePage h) throws IOException,
      IllegalStateException {
    this.m_owner.tablePageBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void tablePageEnd(final TablePage h) throws IOException,
      IllegalStateException {
    this.m_owner.tablePageEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public TableBodyRow row() throws IOException, IllegalStateException {
    return this.m_owner.tableBodyRow();
  }

  /** {@inheritDoc} */
  @Override
  protected TableBodyRow tableBodyRowCreate(final TablePage owner)
      throws IOException {
    return this.m_owner.tableBodyRowCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableBodyRowBegin(final TableBodyRow h)
      throws IOException, IllegalStateException {
    this.m_owner.tableBodyRowBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableBodyRowEnd(final TableBodyRow h) throws IOException,
      IllegalStateException {
    this.m_owner.tableBodyRowEnd(h);
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

}
