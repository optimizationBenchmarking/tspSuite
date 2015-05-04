package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The document section element.
 */
public class Section extends AbstractLabeledElement {

  /** in title */
  private static final int STATE_IN_TITLE = (Element.STATE_NOTHING + 1);

  /** after title */
  private static final int STATE_AFTER_TITLE = (Section.STATE_IN_TITLE + 1);

  /** in body */
  private static final int STATE_IN_BODY = (Section.STATE_AFTER_TITLE + 1);

  /** after body */
  private static final int STATE_AFTER_BODY = (Section.STATE_IN_BODY + 1);

  /** the section counter */
  private int m_sectionCounter;

  /** the section index */
  private final int m_index;

  /** the section depth */
  private final int m_depth;

  /**
   * create the document element
   *
   * @param owner
   *          the owning element
   * @param label
   *          the label of this component
   * @throws IOException
   *           if io fails
   */
  protected Section(final Element owner, final Label label)
      throws IOException {
    super(owner, label);

    Element k;
    Section v;

    seccer: {
      for (k = owner; k != null; k = k.m_owner) {
        if (k instanceof Section) {
          v = ((Section) k);
          this.m_index = v.nextSection();
          this.m_depth = (v.m_depth + 1);
          break seccer;
        }

        if (k instanceof Document) {
          this.m_index = ((Document) k).nextSection();
          this.m_depth = 1;
          break seccer;
        }
      }

      this.m_index = this.m_document.nextSection();
      this.m_depth = 1;
    }

    this.m_state = Element.STATE_NOTHING;
  }

  /**
   * Get the index of this section
   *
   * @return the index of this section
   */
  public final int getIndex() {
    return this.m_index;
  }

  /**
   * Get the section depth: {@code 1} for the top-level section
   *
   * @return the section depth
   */
  public final int getDepth() {
    return this.m_depth;
  }

  /**
   * Get the hierarchy of indexes
   *
   * @return the hierarchy of indexes
   */
  public final int[] getAllIndexes() {
    final int[] idx;
    int i;
    Element e;

    i = this.m_depth;
    idx = new int[i];
    for (e = this; e != null; e = e.m_owner) {
      if (e instanceof Section) {
        idx[--i] = ((Section) e).m_index;
        if (i <= 0) {
          return idx;
        }
      }
    }

    return idx;
  }

  /**
   * get the id of the next section
   *
   * @return the id of the next section
   */
  private final int nextSection() {
    return (++this.m_sectionCounter);
  }

  /** {@inheritDoc} */
  @Override
  final ELabelType getLabelType() {
    return ELabelType.SECTION;
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.sectionEnd(this);
  }

  /** {@inheritDoc} */
  @Override
  public final void close() throws IOException {
    if (this.checkDead(Section.STATE_AFTER_BODY)) {
      this.doClose();
    }
  }

  /** {@inheritDoc} */
  @Override
  protected SectionTitle sectionTitleCreate(final Section owner)
      throws IOException {
    return super.sectionTitleCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void sectionTitleBegin(final SectionTitle h)
      throws IOException, IllegalStateException {

    if (this.m_state != Element.STATE_NOTHING) {
      throw new IllegalStateException(//
          "Section title can only begin at start of section."); //$NON-NLS-1$
    }
    this.m_state = Section.STATE_IN_TITLE;

    super.sectionTitleBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void sectionTitleEnd(final SectionTitle h) throws IOException,
  IllegalStateException {

    if (this.m_state != Section.STATE_IN_TITLE) {
      throw new IllegalStateException(//
          "Section title can only end after it has begun."); //$NON-NLS-1$
    }
    this.m_state = Section.STATE_AFTER_TITLE;

    super.sectionTitleEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public SectionTitle sectionTitle() throws IOException,
  IllegalStateException {
    return super.sectionTitle();
  }

  /** {@inheritDoc} */
  @Override
  protected SectionBody sectionBodyCreate(final Section owner)
      throws IOException {
    return super.sectionBodyCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void sectionBodyBegin(final SectionBody h) throws IOException,
  IllegalStateException {

    if (this.m_state != Section.STATE_AFTER_TITLE) {
      throw new IllegalStateException(//
          "Section body can only begin after section title has ended."); //$NON-NLS-1$
    }
    this.m_state = Section.STATE_IN_BODY;

    super.sectionBodyBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void sectionBodyEnd(final SectionBody h) throws IOException,
  IllegalStateException {

    if (this.m_state != Section.STATE_IN_BODY) {
      throw new IllegalStateException(//
          "Section body can only end after it has begun."); //$NON-NLS-1$
    }
    this.m_state = Section.STATE_AFTER_BODY;

    super.sectionBodyEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public SectionBody sectionBody() throws IOException,
  IllegalStateException {
    return super.sectionBody();
  }

  /**
   * Open a new section, either as a sub-section or as a top-level section
   * in a document body.
   *
   * @param element
   *          the current element which should contain the new section
   * @param label
   *          the label to use for the section
   * @return the handle to the new section
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           If {@code element} does not permit opening a section. Only
   *           instances of
   *           {@link org.logisticPlanning.utils.document.spec.SectionBody}
   *           and {@link org.logisticPlanning.utils.document.spec.Body}
   *           allow opening new sections.
   */
  public static final Section section(final Element element,
      final Label label) throws IOException {
    if ((element instanceof SectionBody) || (element instanceof Body)) {
      return element.section(label);
    }
    throw new IllegalStateException(//
        "Cannot open a new section here! Element is instance of " //$NON-NLS-1$
        + element.getClass() + " but must be instance of " + //$NON-NLS-1$
        SectionBody.class + " or " + Body.class);//$NON-NLS-1$
  }
}
