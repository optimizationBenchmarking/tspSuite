package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The section title element.
 */
public class SectionTitle extends AbstractTextPlain {

  /**
   * create the document element
   * 
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  protected SectionTitle(final Section owner) throws IOException {
    super(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.sectionTitleEnd(this);
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
  public Section getOwner() {
    return ((Section) (this.m_owner));
  }

}
