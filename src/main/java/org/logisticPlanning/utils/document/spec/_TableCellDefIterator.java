package org.logisticPlanning.utils.document.spec;

import java.util.Iterator;

/**
 * an iterator for tableCell cell definitions
 */
final class _TableCellDefIterator implements Iterator<TableCellDef> {

  /** the tableCell */
  private final AbstractTableCell m_tableCell;

  /** the index */
  private int m_index;

  /**
   * instantiate
   *
   * @param tableCell
   *          the tableCell
   */
  _TableCellDefIterator(final AbstractTableCell tableCell) {
    super();
    this.m_tableCell = tableCell;
    this.m_index = tableCell.m_ofs;
  }

  /** {@inheritDoc} */
  @Override
  public final boolean hasNext() {
    return (this.m_index < (this.m_tableCell.m_ofs + this.m_tableCell.m_count));
  }

  /** {@inheritDoc} */
  @Override
  public final TableCellDef next() {
    return this.m_tableCell.m_def[this.m_index++];
  }

  /** {@inheritDoc} */
  @Override
  public final void remove() {
    throw new UnsupportedOperationException();
  }

}
