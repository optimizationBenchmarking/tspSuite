package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The sub figure caption element.
 */
public class SubFigureCaption extends AbstractTextComplex {

  /**
   * create the document element
   *
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  protected SubFigureCaption(final SubFigure owner) throws IOException {
    super(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.subFigureCaptionEnd(this);
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
  public SubFigure getOwner() {
    return ((SubFigure) (this.m_owner));
  }
}
