package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

import org.logisticPlanning.utils.document.spec.bib.BibAuthors;

/**
 * The document header element.
 */
public class Header extends Element {

  /** in macros */
  private static final int STATE_IN_MACROS = (Element.STATE_NOTHING + 1);

  /** after macros */
  private static final int STATE_AFTER_MACROS = (Header.STATE_IN_MACROS + 1);

  /** in title */
  private static final int STATE_IN_TITLE = (Header.STATE_AFTER_MACROS + 1);

  /** after title */
  private static final int STATE_AFTER_TITLE = (Header.STATE_IN_TITLE + 1);

  /** in authors */
  private static final int STATE_IN_AUTHORS = (Header.STATE_AFTER_TITLE + 1);

  /** after authors */
  private static final int STATE_AFTER_AUTHORS = (Header.STATE_IN_AUTHORS + 1);

  /** in summary */
  private static final int STATE_IN_SUMMARY = (Header.STATE_AFTER_AUTHORS + 1);

  /** after summary */
  private static final int STATE_AFTER_SUMMARY = (Header.STATE_IN_SUMMARY + 1);

  /**
   * create the document element
   *
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  protected Header(final Document owner) throws IOException {
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
    this.m_owner.headerEnd(this);
  }

  /** {@inheritDoc} */
  @Override
  public final void close() throws IOException {
    if (this.checkDead(Header.STATE_AFTER_SUMMARY)) {
      this.doClose();
    }
  }

  /** {@inheritDoc} */
  @Override
  public boolean isMacroDefined(final MacroDescriptor desc) {
    return super.isMacroDefined(desc);
  }

  /** {@inheritDoc} */
  @Override
  protected Macro macroCreate(final Header owner,
      final MacroDescriptor desc) throws IOException {
    return super.macroCreate(owner, desc);
  }

  /** {@inheritDoc} */
  @Override
  protected void macroBegin(final Macro h) throws IOException,
  IllegalStateException {

    if ((this.m_state != Element.STATE_NOTHING)
        && (this.m_state != Header.STATE_AFTER_MACROS)) {
      throw new IllegalStateException(//
          "Macros can only be defined at the beginning of the header."); //$NON-NLS-1$
    }
    this.m_state = Header.STATE_IN_MACROS;

    super.macroBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void macroEnd(final Macro h) throws IOException,
  IllegalStateException {

    if (this.m_state != Header.STATE_IN_MACROS) {
      throw new IllegalStateException(//
          "Macros can only end after they have begun."); //$NON-NLS-1$
    }
    this.m_state = Header.STATE_AFTER_MACROS;

    super.macroEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public Macro macro(final MacroDescriptor desc) throws IOException,
  IllegalStateException {
    return super.macro(desc);
  }

  /** {@inheritDoc} */
  @Override
  protected Authors authorsCreate(final Header owner,
      final BibAuthors authors) throws IOException {
    return super.authorsCreate(owner, authors);
  }

  /** {@inheritDoc} */
  @Override
  protected void authorsBegin(final Authors h) throws IOException,
  IllegalStateException {

    if (this.m_state != Header.STATE_AFTER_TITLE) {
      throw new IllegalStateException(//
          "Authors cannot begin before title has been completed."); //$NON-NLS-1$
    }
    this.m_state = Header.STATE_IN_AUTHORS;

    super.authorsBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void authorsEnd(final Authors h) throws IOException,
  IllegalStateException {

    if (this.m_state != Header.STATE_IN_AUTHORS) {
      throw new IllegalStateException(//
          "Authors can only end after they have begun."); //$NON-NLS-1$
    }
    this.m_state = Header.STATE_AFTER_AUTHORS;

    super.authorsEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public void authors(final BibAuthors authors) throws IOException,
  IllegalStateException {
    super.authors(authors);
  }

  /** {@inheritDoc} */
  @Override
  protected Title titleCreate(final Header owner) throws IOException {
    return super.titleCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void titleBegin(final Title h) throws IOException,
  IllegalStateException {

    if ((this.m_state != Element.STATE_NOTHING)
        && (this.m_state != Header.STATE_AFTER_MACROS)) {
      throw new IllegalStateException(//
          "Title must be start of header or directly follow macros."); //$NON-NLS-1$
    }
    this.m_state = Header.STATE_IN_TITLE;

    super.titleBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void titleEnd(final Title h) throws IOException,
  IllegalStateException {

    if (this.m_state != Header.STATE_IN_TITLE) {
      throw new IllegalStateException(//
          "Title can only end after it has begun."); //$NON-NLS-1$
    }
    this.m_state = Header.STATE_AFTER_TITLE;

    super.titleEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public Title title() throws IOException, IllegalStateException {
    return super.title();
  }

  /** {@inheritDoc} */
  @Override
  protected Summary summaryCreate(final Header owner) throws IOException {
    return super.summaryCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void summaryBegin(final Summary h) throws IOException,
  IllegalStateException {

    if (this.m_state != Header.STATE_AFTER_AUTHORS) {
      throw new IllegalStateException(//
          "Summary can only begin after authors."); //$NON-NLS-1$
    }
    this.m_state = Header.STATE_IN_SUMMARY;

    super.summaryBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void summaryEnd(final Summary h) throws IOException,
  IllegalStateException {

    if (this.m_state != Header.STATE_IN_SUMMARY) {
      throw new IllegalStateException(//
          "Summary can only end after it has begun."); //$NON-NLS-1$
    }
    this.m_state = Header.STATE_AFTER_SUMMARY;

    super.summaryEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public Summary summary() throws IOException, IllegalStateException {
    return super.summary();
  }

}
