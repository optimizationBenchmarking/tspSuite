package org.logisticPlanning.utils.document.spec;

import java.awt.geom.Dimension2D;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * The figure element.
 */
public class Figure extends AbstractLabeledElement {

  /** in header */
  private static final int STATE_IN_CAPTION = (Element.STATE_NOTHING + 1);

  /** after heder */
  private static final int STATE_AFTER_CAPTION = (Figure.STATE_IN_CAPTION + 1);

  /** in body */
  private static final int STATE_IN_BODY = (Figure.STATE_AFTER_CAPTION + 1);

  /** after body */
  private static final int STATE_AFTER_BODY = (Figure.STATE_IN_BODY + 1);

  /** the figure index */
  private final int m_index;

  /** span over all columns? */
  private final boolean m_colspan;

  /**
   * create the document element
   * 
   * @param owner
   *          the owning element
   * @param label
   *          the label of this component
   * @param colspan
   *          {@code true} if this figure spans over all columns,
   *          {@code false} if it only uses a single columns
   * @throws IOException
   *           if io fails
   */
  protected Figure(final SectionBody owner, final Label label,
      final boolean colspan) throws IOException {
    super(owner, label);
    this.m_index = this.m_document.nextFigure();
    this.m_colspan = colspan;
  }

  /**
   * Does this figure span over all columns in a multi-column document?
   * 
   * @return {@code true} if this figure spans over all columns,
   *         {@code false} if it only uses a single columns
   */
  public final boolean spansColumns() {
    return this.m_colspan;
  }

  /**
   * Get the figure index
   * 
   * @return the figure index
   */
  public final int getFigureIndex() {
    return this.m_index;
  }

  /** {@inheritDoc} */
  @Override
  final ELabelType getLabelType() {
    return ELabelType.FIGURE;
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.figureEnd(this);
  }

  /** {@inheritDoc} */
  @Override
  public final void close() throws IOException {
    if (this.checkDead(Figure.STATE_AFTER_BODY)) {
      this.doClose();
    }
  }

  /** {@inheritDoc} */
  @Override
  protected FigureCaption figureCaptionCreate(final Figure owner)
      throws IOException {
    return super.figureCaptionCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void figureCaptionBegin(final FigureCaption h)
      throws IOException, IllegalStateException {
    if (this.m_state != Element.STATE_NOTHING) {
      throw new IllegalStateException(//
          "Figure caption can only begin at the start of the figure."); //$NON-NLS-1$
    }
    this.m_state = Figure.STATE_IN_CAPTION;
    super.figureCaptionBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void figureCaptionEnd(final FigureCaption h)
      throws IOException, IllegalStateException {
    if (this.m_state != Figure.STATE_IN_CAPTION) {
      throw new IllegalStateException(//
          "Figure caption can only end after it has begun."); //$NON-NLS-1$
    }
    this.m_state = Figure.STATE_AFTER_CAPTION;
    super.figureCaptionEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public FigureCaption figureCaption() throws IOException,
      IllegalStateException {
    return super.figureCaption();
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

    if (this.m_state != Figure.STATE_AFTER_CAPTION) {
      throw new IllegalStateException(//
          "Exactly one figure body must come directly after the figure caption."); //$NON-NLS-1$
    }
    this.m_state = Figure.STATE_IN_BODY;

    super.figureBodyBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void figureBodyEnd(final FigureBody h) throws IOException,
      IllegalStateException {

    if (this.m_state != Figure.STATE_IN_BODY) {
      throw new IllegalStateException(//
          "Figure body can only end after it begun."); //$NON-NLS-1$
    }
    this.m_state = Figure.STATE_AFTER_BODY;

    super.figureBodyEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public FigureBody figureBody(final URI relativeNameBase,
      final Dimension2D sizeInMM) throws IOException,
      IllegalStateException, URISyntaxException {
    return super.figureBody(relativeNameBase, sizeInMM);
  }

  /** {@inheritDoc} */
  @Override
  public SectionBody getOwner() {
    return ((SectionBody) (this.m_owner));
  }
}
