package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The figure page element.
 */
public class FigureSeriesPage extends AbstractLabeledElement {

  /** the page number */
  private final int m_pageNumber;

  /** the current column */
  int m_col;

  /** the current row, relative to the page begin */
  int m_row;

  /** the figure index */
  private final int m_figureIndex;

  /**
   * create the document element
   *
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  protected FigureSeriesPage(final FigureSeries owner) throws IOException {
    super(owner, owner.m_label);

    this.m_pageNumber = owner.nextPage();
    this.m_figureIndex = owner.getFigureIndex();
    this.m_col = 1;
    this.m_row = 1;
  }

  /**
   * Get the figure index of this page
   *
   * @return the figure index of this page
   */
  public final int getFigureIndex() {
    return this.m_figureIndex;
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
    return ELabelType.FIGURE;
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.figureSeriesPageEnd(this);
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
  public FigureSeries getOwner() {
    return ((FigureSeries) (this.m_owner));
  }

  /** {@inheritDoc} */
  @Override
  protected SubFigure subFigureCreate(final FigureSeriesPage owner,
      final Label label, final int row, final int col) throws IOException {
    return this.m_owner.subFigureCreate(owner, label, row, col);
  }

  /** {@inheritDoc} */
  @Override
  protected void subFigureBegin(final SubFigure h) throws IOException,
      IllegalStateException {
    this.m_owner.subFigureBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void subFigureEnd(final SubFigure h) throws IOException,
      IllegalStateException {
    this.m_owner.subFigureEnd(h);
  }

}
