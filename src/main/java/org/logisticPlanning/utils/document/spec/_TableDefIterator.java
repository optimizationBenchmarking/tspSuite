package org.logisticPlanning.utils.document.spec;

import java.util.Iterator;

/**
 * an iterator for table cell definitions
 */
final class _TableDefIterator implements Iterator<TableCellDef> {

  /** the table */
  private final Table m_table;

  /** the index */
  private int m_index;

  /**
   * instantiate
   * 
   * @param table
   *          the table
   */
  _TableDefIterator(final Table table) {
    super();
    this.m_table = table;
  }

  /** {@inheritDoc} */
  @Override
  public final boolean hasNext() {
    return (this.m_index < this.m_table.m_def.length);
  }

  /** {@inheritDoc} */
  @Override
  public final TableCellDef next() {
    return this.m_table.m_def[this.m_index++];
  }

  /** {@inheritDoc} */
  @Override
  public final void remove() {
    throw new UnsupportedOperationException();
  }

}
