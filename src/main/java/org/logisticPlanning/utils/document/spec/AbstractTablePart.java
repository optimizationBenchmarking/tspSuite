package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The table part.
 */
public abstract class AbstractTablePart extends Element {

  /**
   * create the document element
   *
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  AbstractTablePart(final Table owner) throws IOException {
    super(owner);
  }

  /** {@inheritDoc} */
  @Override
  public Table getOwner() {
    return ((Table) (this.m_owner));
  }

  /**
   * Begin a new table row
   *
   * @return the table row
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if starting a table row is not permitted here
   */
  public abstract AbstractTableRow row() throws IOException,
      IllegalStateException;

  /** {@inheritDoc} */
  @Override
  public void tableHorizontalLine() throws IOException,
      IllegalStateException {
    final Element e;
    e = this.m_document.m_current;
    if ((e != this)
        && (!((e instanceof TablePage) && (((TablePage) e).m_owner == this)))) {
      throw new IllegalStateException(//
          "Horizontal lines can only be drawn between table rows."); //$NON-NLS-1$
    }
    super.tableHorizontalLine();
  }

  /**
   * Get the index of the current row
   *
   * @return the index of the current row
   */
  public final int getCurrentRowIndex() {
    return ((Table) (this.m_owner)).getCurrentRowIndex();
  }

  /**
   * Get the index of the current page row
   *
   * @return the index of the current page row
   */
  public final int getCurrentPageRowIndex() {
    return ((Table) (this.m_owner)).getCurrentPageRowIndex();
  }

  /**
   * Get the index of the current page
   *
   * @return the index of the current page
   */
  public final int getCurrentPageIndex() {
    return ((Table) (this.m_owner)).getCurrentPageIndex();
  }
}
