package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The figure series element.
 */
public class FigureSeries extends _MultiLabeledElement {
  /** in caption */
  private static final int STATE_IN_CAPTION = (Element.STATE_NOTHING + 1);

  /** after header */
  private static final int STATE_AFTER_CAPTION = (FigureSeries.STATE_IN_CAPTION + 1);

  /** in body */
  private static final int STATE_IN_BODY = (FigureSeries.STATE_AFTER_CAPTION + 1);

  /** after body */
  private static final int STATE_AFTER_BODY = (FigureSeries.STATE_IN_BODY + 1);

  /** the columns */
  final int m_cols;

  /** the rows */
  final int m_rows;

  /** the current page */
  private int m_page;

  /** the figure index */
  private int m_index;

  /** span over all columns? */
  private final boolean m_colspan;

  /**
   * create the document element
   *
   * @param owner
   *          the owning element
   * @param label
   *          the label of this component
   * @param cols
   *          the number of columns
   * @param rows
   *          the number of rows
   * @param colspan
   *          {@code true} if this figure series spans over all columns,
   *          {@code false} if it only uses a single columns
   * @throws IOException
   *           if io fails
   */
  protected FigureSeries(final SectionBody owner, final Label label,
      final int cols, final int rows, final boolean colspan)
      throws IOException {
    super(owner, label);

    if (cols < 1) {
      throw new IllegalArgumentException(//
          "Figure series must have at least 1 column.");//$NON-NLS-1$
    }
    if (rows < 1) {
      throw new IllegalArgumentException(//
          "Figure series must have at least 1 row.");//$NON-NLS-1$
    }
    this.m_cols = cols;
    this.m_rows = rows;
    this.m_index = this.m_document.nextFigure();
    this.m_colspan = colspan;
  }

  /**
   * Does this figure series span over all columns in a multi-column
   * document?
   *
   * @return {@code true} if this figure series spans over all columns,
   *         {@code false} if it only uses a single columns
   */
  public final boolean spansColumns() {
    return this.m_colspan;
  }

  /**
   * Get the figure index. Warning: This value may increase when the figure
   * pages increase.
   *
   * @return the figure index
   */
  public final int getFigureIndex() {
    return this.m_index;
  }

  /**
   * get the index of the next page
   *
   * @return the index of the next page
   */
  final int nextPage() {
    final int pi;

    pi = (++this.m_page);
    if (pi > 1) {
      this.m_index = this.m_document.nextFigure();
    }
    return pi;
  }

  /**
   * Get the number of columns
   *
   * @return the number of columns
   */
  public final int getColumnsPerRow() {
    return this.m_cols;
  }

  /**
   * Get the number of rows
   *
   * @return the number of rows
   */
  public final int getRowsPerPage() {
    return this.m_rows;
  }

  /** {@inheritDoc} */
  @Override
  final ELabelType getLabelType() {
    return ELabelType.FIGURE;
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    try {
      if (this.m_document.m_current.m_owner == this) {
        this.m_document.m_current.close();
      }
    } finally {
      this.m_owner.figureSeriesEnd(this);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void close() throws IOException {
    if (this.checkDead(FigureSeries.STATE_IN_BODY)) {
      this.doClose();
    }
  }

  /** {@inheritDoc} */
  @Override
  public SectionBody getOwner() {
    return ((SectionBody) (this.m_owner));
  }

  /** {@inheritDoc} */
  @Override
  protected FigureSeriesCaption figureSeriesCaptionCreate(
      final FigureSeries owner) throws IOException {
    return super.figureSeriesCaptionCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void figureSeriesCaptionBegin(final FigureSeriesCaption h)
      throws IOException, IllegalStateException {
    if (this.m_state != Element.STATE_NOTHING) {
      throw new IllegalStateException(//
          "Figure series caption can only begin at the start of the figure series."); //$NON-NLS-1$
    }
    this.m_state = FigureSeries.STATE_IN_CAPTION;
    super.figureSeriesCaptionBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void figureSeriesCaptionEnd(final FigureSeriesCaption h)
      throws IOException, IllegalStateException {
    if (this.m_state != FigureSeries.STATE_IN_CAPTION) {
      throw new IllegalStateException(//
          "Figure series caption can only end after it has begun."); //$NON-NLS-1$
    }
    this.m_state = FigureSeries.STATE_AFTER_CAPTION;
    super.figureSeriesCaptionEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public FigureSeriesCaption figureSeriesCaption() throws IOException,
      IllegalStateException {
    return super.figureSeriesCaption();
  }

  /** {@inheritDoc} */
  @Override
  protected FigureSeriesPage figureSeriesPageCreate(
      final FigureSeries owner) throws IOException {
    return super.figureSeriesPageCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void figureSeriesPageBegin(final FigureSeriesPage h)
      throws IOException, IllegalStateException {
    if ((this.m_state != FigureSeries.STATE_AFTER_BODY)
        && (this.m_state != FigureSeries.STATE_AFTER_CAPTION)) {
      throw new IllegalStateException(//
          "Figure series page can only begin after caption or after another figure series page."); //$NON-NLS-1$
    }
    this.m_state = FigureSeries.STATE_IN_BODY;
    super.figureSeriesPageBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void figureSeriesPageEnd(final FigureSeriesPage h)
      throws IOException, IllegalStateException {
    if (this.m_state != Element.STATE_DEAD) {
      if (this.m_state != FigureSeries.STATE_IN_BODY) {
        throw new IllegalStateException(//
            "Figure series page can only end after it has begun."); //$NON-NLS-1$
      }
      this.m_state = FigureSeries.STATE_AFTER_BODY;
    }
    super.figureSeriesPageEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  protected SubFigure subFigureCreate(final FigureSeriesPage owner,
      final Label label, final int row, final int col) throws IOException {
    return super.subFigureCreate(owner, label, row, col);
  }

  /** {@inheritDoc} */
  @Override
  protected void subFigureBegin(final SubFigure h) throws IOException,
      IllegalStateException {

    if (this.m_state != FigureSeries.STATE_IN_BODY) {
      throw new IllegalStateException(//
          "Sub-figure only allowed to begin in figure series page."); //$NON-NLS-1$
    }

    super.subFigureBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void subFigureEnd(final SubFigure h) throws IOException,
      IllegalStateException {

    if (this.m_state != FigureSeries.STATE_IN_BODY) {
      throw new IllegalStateException(//
          "Sub-figure only allowed to end in figure series page."); //$NON-NLS-1$
    }

    super.subFigureEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public SubFigure subFigure(final Label label) throws IOException {
    return super.subFigure(label);
  }
}
