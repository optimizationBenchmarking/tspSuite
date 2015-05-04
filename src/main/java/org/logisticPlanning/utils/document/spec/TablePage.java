package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The table page element.
 */
public class TablePage extends AbstractLabeledElement {

  /** the page number */
  private final int m_pageNumber;

  /** the figure index */
  private final int m_tableIndex;

  /**
   * create the document element
   *
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  protected TablePage(final TableBody owner) throws IOException {
    super(owner, ((Table) (owner.m_owner)).m_label);

    final Table t;
    t = ((Table) (owner.m_owner));
    this.m_pageNumber = t.getCurrentPageIndex();
    this.m_tableIndex = t.getTableIndex();
  }

  /**
   * Get the table index of this page
   *
   * @return the table index of this page
   */
  public final int getTableIndex() {
    return this.m_tableIndex;
  }

  /**
   * Get the page number
   *
   * @return the page number
   */
  public final int getPageNumber() {
    return this.m_pageNumber;
  }

  /** {@inheritDoc} */
  @Override
  final ELabelType getLabelType() {
    return ELabelType.TABLE;
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.tablePageEnd(this);
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
  public TableBody getOwner() {
    return ((TableBody) (this.m_owner));
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
