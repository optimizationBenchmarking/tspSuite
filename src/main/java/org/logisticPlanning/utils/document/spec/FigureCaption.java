package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The figure caption element.
 */
public class FigureCaption extends AbstractTextComplex {

  /**
   * create the document element
   *
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  protected FigureCaption(final Figure owner) throws IOException {
    super(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.figureCaptionEnd(this);
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
  public Figure getOwner() {
    return ((Figure) (this.m_owner));
  }
}
