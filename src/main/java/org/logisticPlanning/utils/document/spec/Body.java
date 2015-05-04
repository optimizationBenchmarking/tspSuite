package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The document body element.
 */
public class Body extends Element {

  /** are we in a section */
  private static final int STATE_IN_SECTION = (Element.STATE_NOTHING + 1);

  /** are we after a section */
  private static final int STATE_AFTER_SECTION = (Body.STATE_IN_SECTION + 1);

  /**
   * create the document element
   *
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  protected Body(final Document owner) throws IOException {
    super(owner);
  }

  /** {@inheritDoc} */
  @Override
  public Document getOwner() {
    return this.m_document;
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.bodyEnd(this);
  }

  /** {@inheritDoc} */
  @Override
  public final void close() throws IOException {
    if (this.checkDead(Body.STATE_AFTER_SECTION)) {
      this.doClose();
    }
  }

  /** {@inheritDoc} */
  @Override
  protected Section sectionCreate(final Element owner, final Label label)
      throws IOException {
    return super.sectionCreate(owner, label);
  }

  /** {@inheritDoc} */
  @Override
  protected void sectionBegin(final Section h) throws IOException,
      IllegalStateException {
    if ((this.m_state != Element.STATE_NOTHING)
        && (this.m_state != Body.STATE_AFTER_SECTION)) {
      throw new IllegalStateException(//
          "Cannot begin a new section here."); //$NON-NLS-1$
    }
    this.m_state = Body.STATE_IN_SECTION;
    super.sectionBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void sectionEnd(final Section h) throws IOException,
      IllegalStateException {
    if (this.m_state != Body.STATE_IN_SECTION) {
      throw new IllegalStateException(//
          "Sections can only end after they have been begun."); //$NON-NLS-1$
    }
    this.m_state = Body.STATE_AFTER_SECTION;
    super.sectionEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public Section section(final Label label) throws IOException,
      IllegalStateException {
    return super.section(label);
  }
}
