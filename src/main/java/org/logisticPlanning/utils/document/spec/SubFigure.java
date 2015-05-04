package org.logisticPlanning.utils.document.spec;

import java.awt.geom.Dimension2D;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * The sub figure element.
 */
public class SubFigure extends AbstractLabeledElement {

  /** the row */
  private final int m_row;

  /** the column */
  private final int m_col;

  /**
   * create the document element
   *
   * @param owner
   *          the owning element
   * @param label
   *          the label of this component
   * @param row
   *          the row in which this sub-figure appears
   * @param col
   *          the column in which this sub-figure appears
   * @throws IOException
   *           if io fails
   */
  protected SubFigure(final FigureSeriesPage owner, final Label label,
      final int row, final int col) throws IOException {
    super(owner, label);

    this.m_row = row;
    this.m_col = col;
  }

  /**
   * get the row in which this sub-figure appears
   *
   * @return the row in which this sub-figure appears
   */
  public final int getRow() {
    return this.m_row;
  }

  /**
   * get the col in which this sub-figure appears, {@code 1} for first
   * column
   *
   * @return the col in which this sub-figure appears, {@code 1} for first
   *         column
   */
  public final int getCol() {
    return this.m_col;
  }

  /** {@inheritDoc} */
  @Override
  final ELabelType getLabelType() {
    return ELabelType.SUB_FIGURE;
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.subFigureEnd(this);
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
  public FigureSeriesPage getOwner() {
    return ((FigureSeriesPage) (this.m_owner));
  }

  /** {@inheritDoc} */
  @Override
  protected SubFigureCaption subFigureCaptionCreate(final SubFigure owner)
      throws IOException {
    return super.subFigureCaptionCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void subFigureCaptionBegin(final SubFigureCaption h)
      throws IOException, IllegalStateException {
    super.subFigureCaptionBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void subFigureCaptionEnd(final SubFigureCaption h)
      throws IOException, IllegalStateException {
    super.subFigureCaptionEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public SubFigureCaption subFigureCaption() throws IOException,
  IllegalStateException {
    return super.subFigureCaption();
  }

  /** {@inheritDoc} */
  @Override
  protected FigureBody figureBodyCreate(final Element owner,
      final URI relativeNameBase, final Dimension2D sizeInMM)
          throws IOException, URISyntaxException {
    return super.figureBodyCreate(owner, relativeNameBase, sizeInMM);
  }

  /** {@inheritDoc} */
  @Override
  protected void figureBodyBegin(final FigureBody h) throws IOException,
  IllegalStateException {
    super.figureBodyBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void figureBodyEnd(final FigureBody h) throws IOException,
  IllegalStateException {
    super.figureBodyEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public FigureBody figureBody(final URI relativeNameBase,
      final Dimension2D sizeInMM) throws IOException,
      IllegalStateException, URISyntaxException {
    return super.figureBody(relativeNameBase, sizeInMM);
  }
}
