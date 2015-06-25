package org.logisticPlanning.utils.document.spec;

import java.io.IOException;
import java.util.Iterator;

/**
 * The internal table cell element.
 */
public abstract class AbstractTableCell extends AbstractTextBlock
    implements Iterable<TableCellDef> {

  /** the offset */
  int m_ofs;

  /** the count */
  int m_count;

  /** the table cell definition */
  TableCellDef[] m_def;

  /** the start column */
  int m_startColumn;

  /** the end column */
  int m_endColumn;

  /** the start row */
  int m_startRow;

  /** the end row */
  int m_endRow;

  /** treat as multi-col */
  boolean m_treatAsMulti;

  /**
   * create the document element
   *
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  AbstractTableCell(final AbstractTableRow owner) throws IOException {
    super(owner);
  }

  /**
   * Get the number of columns
   *
   * @return the number of columns
   */
  public final int getColumnSpan() {
    return ((this.m_endColumn - this.m_startColumn) + 1);
  }

  /**
   * Get the number of rows
   *
   * @return the number of rows
   */
  public final int getRowSpan() {
    return ((this.m_endRow - this.m_startRow) + 1);
  }

  /**
   * Get the first column occupied by this cell
   *
   * @return the first column occupied by this cell
   */
  public final int getStartCol() {
    return this.m_startColumn;
  }

  /**
   * Get the last column occupied by this cell
   *
   * @return the last column occupied by this cell
   */
  public final int getEndCol() {
    return this.m_endColumn;
  }

  /**
   * Get the first row occupied by this cell
   *
   * @return the first row occupied by this cell
   */
  public final int getStartRow() {
    return this.m_startRow;
  }

  /**
   * Get the last row occupied by this cell
   *
   * @return the last row occupied by this cell
   */
  public final int getEndRow() {
    return this.m_endRow;
  }

  /**
   * Get the definition length
   *
   * @return the definition length
   */
  public final int getDefinitionLength() {
    return this.m_count;
  }

  /**
   * Get the definition element at index i
   *
   * @param i
   *          the index
   * @return the element
   */
  public final TableCellDef getDefinition(final int i) {
    return this.m_def[i + this.m_ofs];
  }

  /**
   * Treat this cell as multi-column or multi-row cell.
   *
   * @return {@code true} if the definition of this cell requires you to
   *         treat it as multi-column or multi-row cell. {@code false} if
   *         this is just a normal cell.
   */
  public final boolean treatAsMulti() {
    return this.m_treatAsMulti;
  }

  /**
   * Obtain the row in which this cell starts.
   *
   * @return the row in which this cell starts
   */
  @Override
  public AbstractTableRow getOwner() {
    return ((AbstractTableRow) (this.m_owner));
  }

  /**
   * Is this row a body cell ({@code true} will be returned) or a
   * header/footer cell ({@code false} will be returned)?
   *
   * @return {@code true} for body cells, {@code false} for header or
   *         footer cells
   */
  public abstract boolean isBodyCell();

  /** {@inheritDoc} */
  @Override
  public final Iterator<TableCellDef> iterator() {
    return new _TableCellDefIterator(this);
  }
}
