package org.logisticPlanning.utils.document.spec;

import java.awt.geom.Dimension2D;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.NumberFormat;
import java.util.logging.Logger;

import org.logisticPlanning.utils.document.spec.bib.BibAuthors;
import org.logisticPlanning.utils.document.spec.bib.BibRecord;
import org.logisticPlanning.utils.text.EQuotes;

/**
 * The elements are the base class for all modules of a hierarchical
 * document.
 */
public abstract class Element extends _AbstractBase implements Closeable {

  /** nothing */
  static final int STATE_NOTHING = 0;

  /** dead */
  static final int STATE_DEAD = Integer.MAX_VALUE;

  /** the owning element */
  final Element m_owner;

  /** the document */
  Document m_document;

  /** the state */
  int m_state;

  /**
   * create the document element
   *
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  Element(final Element owner) throws IOException {
    super();

    this.m_state = Element.STATE_NOTHING;

    if (owner == null) {
      if (!(this instanceof Context)) {
        throw new IllegalArgumentException(//
            "Owning element must not be null."); //$NON-NLS-1$
      }
      this.m_document = null;
    } else {
      if (this instanceof Document) {
        this.m_document = ((Document) this);
      } else {
        this.m_document = ((owner.m_owner == null) ? ((Document) (owner))
            : owner.m_document);
      }
    }

    this.m_owner = owner;
  }

  /**
   * Get the owning element
   *
   * @return the owning element
   */
  public Element getOwner() {
    return this.m_owner;
  }

  /**
   * Get the document
   *
   * @return the document
   */
  public final Document getDocument() {
    return this.m_document;
  }

  //
  // text output routines
  //
  /** check whether writing is permitted */
  private final void checkWrite() {
    if (this != this.m_document.m_current) {
      throw new IllegalStateException(//
          "Only current element can be written to."); //$NON-NLS-1$
    }
  }

  /**
   * write a single character
   *
   * @param data
   *          the character
   * @throws IOException
   *           if io fails
   */
  void writeChar(final int data) throws IOException {
    this.checkWrite();
    this.m_document.writeChar(data);
  }

  /**
   * write given string
   *
   * @param data
   *          the string
   * @throws IOException
   *           if io fails
   */
  void write(final String data) throws IOException {
    this.checkWrite();
    this.m_document.write(data);
  }

  /**
   * write given string in a hyphenated fashion
   *
   * @param data
   *          the string
   * @throws IOException
   *           if io fails
   */
  void writeHyphenated(final String data) throws IOException {
    this.checkWrite();
    this.m_document.writeHyphenated(data);
  }

  /**
   * Writes a {@code boolean} value to this output stream.
   *
   * @param v
   *          the boolean to be written.
   * @throws IOException
   *           if an I/O error occurs.
   */
  void writeBoolean(final boolean v) throws IOException {
    this.checkWrite();
    this.m_document.writeBoolean(v);
  }

  /**
   * Writes an {@code int} value
   *
   * @param v
   *          the {@code int} value to be written.
   * @throws IOException
   *           if an I/O error occurs.
   */
  void writeInt(final int v) throws IOException {
    this.checkWrite();
    this.m_document.writeInt(v);
  }

  /**
   * Writes a {@code int} value in text, representing small values as text
   * and larger or negative values as numbers.
   *
   * @param v
   *          the {@code int} value to be written.
   * @param beginUpperCase
   *          should the first letter be upper case?
   * @throws IOException
   *           if an I/O error occurs.
   */
  void writeIntInText(final int v, final boolean beginUpperCase)
      throws IOException {
    this.checkWrite();
    this.m_document.writeIntInText(v, beginUpperCase);
  }

  /**
   * Writes a {@code long} value in text, representing small values as text
   * and larger or negative values as numbers.
   *
   * @param v
   *          the {@code long} value to be written.
   * @param beginUpperCase
   *          should the first letter be upper case?
   * @throws IOException
   *           if an I/O error occurs.
   */
  void writeLongInText(final long v, final boolean beginUpperCase)
      throws IOException {
    this.checkWrite();
    this.m_document.writeLongInText(v, beginUpperCase);
  }

  /**
   * Writes a {@code int} value
   *
   * @param v
   *          the {@code int} value to be written.
   * @param format
   *          the number format to use, or {@code null} for no specific
   *          format
   * @throws IOException
   *           if an I/O error occurs.
   */
  void writeInt(final int v, final NumberFormat format) throws IOException {
    this.checkWrite();
    this.m_document.writeInt(v, format);
  }

  /**
   * Writes a {@code long} value
   *
   * @param v
   *          the {@code long} value to be written.
   * @param format
   *          the number format to use, or {@code null} for no specific
   *          format
   * @throws IOException
   *           if an I/O error occurs.
   */
  void writeLong(final long v, final NumberFormat format)
      throws IOException {
    this.checkWrite();
    this.m_document.writeLong(v, format);
  }

  /**
   * Writes a {@code long} value
   *
   * @param v
   *          the {@code long} value to be written.
   * @throws IOException
   *           if an I/O error occurs.
   */
  void writeLong(final long v) throws IOException {
    this.checkWrite();
    this.m_document.writeLong(v);
  }

  /**
   * Writes a {@code double} value
   *
   * @param v
   *          the {@code double} value to be written.
   * @throws IOException
   *           if an I/O error occurs.
   */
  void writeDouble(final double v) throws IOException {
    this.checkWrite();
    this.m_document.writeDouble(v);
  }

  /**
   * Writes a {@code double} value
   *
   * @param v
   *          the {@code double} value to be written.
   * @param format
   *          the number format to use, or {@code null} for no specific
   *          format
   * @throws IOException
   *           if an I/O error occurs.
   */
  void writeDouble(final double v, final NumberFormat format)
      throws IOException {
    this.checkWrite();
    this.m_document.writeDouble(v, format);
  }

  /**
   * write a non-breaking space
   *
   * @throws IOException
   *           if io fails
   */
  void writeNoneBreakingSpace() throws IOException {
    this.checkWrite();
    this.m_document.writeNoneBreakingSpace();
  }

  /**
   * write a line break
   *
   * @throws IOException
   *           if io fails
   */
  void writeLinebreak() throws IOException {
    this.checkWrite();
    this.m_document.writeLinebreak();
  }

  /**
   * Write a reference to the given reference
   *
   * @param type
   *          the sequence type defining how the references should be
   *          connected
   * @param ref
   *          the reference to reference
   * @throws IOException
   *           if io fails
   */
  void reference(final ESequenceType type, final Label... ref)
      throws IOException {
    this.checkWrite();
    if (type == null) {
      throw new IllegalArgumentException(//
          "Sequence type must not be null."); //$NON-NLS-1$
    }
    this.m_document.reference(type, ref);
  }

  /**
   * Write a sequence of elements. Sequence is assumed to write
   * {@link org.logisticPlanning.utils.document.spec.Sequence#length()}
   * strings in its
   * {@link org.logisticPlanning.utils.document.spec.Sequence#write(int)}
   * methods. Between these strings, the document will automatically put
   * appropriate separators such as {@code "and"}, {@code ","}, etc.
   *
   * @param sequence
   *          the sequence
   * @param type
   *          the sequence type
   * @param connectLastElementWithNonBreakableSpace
   *          should the last element be connected to the last separator
   *          with a non-breakable space (or, otherwise, with a normal
   *          space)?
   * @throws IOException
   *           if io fails
   */
  void writeSequence(final Sequence sequence, final ESequenceType type,
      final boolean connectLastElementWithNonBreakableSpace)
      throws IOException {
    this.checkWrite();
    if (type == null) {
      throw new IllegalArgumentException(//
          "Sequence type must not be null."); //$NON-NLS-1$
    }
    this.m_document.writeSequence(sequence, type,
        connectLastElementWithNonBreakableSpace);
  }

  //
  // name routines
  //

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    return ((_AbstractBase._name(this) + '#') + System
        .identityHashCode(this));
  }

  /**
   * Resolve a relative name in form of a uri to a file.
   *
   * @param relativeName
   *          the relative name
   * @return an absolute file representing the relative uri
   * @throws IOException
   *           if io fails
   * @throws URISyntaxException
   *           if the uri has the wrong syntax
   */
  File resolveRelativeName(final URI relativeName) throws IOException,
      URISyntaxException {
    return this.m_document.resolveRelativeName(relativeName);
  }

  /**
   * Turn a file into a relative uri
   *
   * @param file
   *          the absolute file
   * @return the relative uri representing the absolute file
   * @throws IOException
   *           if io fails
   * @throws URISyntaxException
   *           if the uri has the wrong syntax
   */
  URI relativizeFile(final File file) throws IOException,
      URISyntaxException {
    return this.m_document.relativizeFile(file);
  }

  //
  // document hierarchy methods
  //

  /**
   * check if we can end here
   *
   * @param minState
   *          the minimum state in which the object must be
   * @return {@code true} if some closing action needs to be carried out,
   *         {@code false} otherwise
   */
  final boolean checkDead(final int minState) {
    final int s;

    s = this.m_state;
    this.m_state = Element.STATE_DEAD;
    if (s == Element.STATE_DEAD) {
      return false;
    }

    if (s < minState) {
      throw new IllegalStateException(this.toString() + //
          " cannot end here."); //$NON-NLS-1$
    }
    return true;
  }

  /**
   * carry out the closing
   *
   * @throws IOException
   *           if closing fails
   */
  protected abstract void doClose() throws IOException;

  /** {@inheritDoc} */
  @Override
  public abstract void close() throws IOException;

  /**
   * Create a new single label
   *
   * @param type
   *          the label type
   * @param key
   *          the label key
   * @return the new single label
   * @throws IOException
   *           if io fails
   */
  SingleLabel createSingleLabel(final ELabelType type, final String key)
      throws IOException {
    return this.m_document.createSingleLabel(type, key);
  }

  /**
   * write a single label to the output stream
   *
   * @param label
   *          the label
   * @param info
   *          an information string to put
   * @throws IOException
   *           if io fails
   */
  void writeSingleLabel(final SingleLabel label, final String info)
      throws IOException {
    this.m_document.writeSingleLabel(label, info);
  }

  /**
   * Create a header element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  Header headerCreate(final Document owner) throws IOException {
    return this.m_document.headerCreate(owner);
  }

  /**
   * Begin a header element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void headerBegin(final Header h) throws IOException,
      IllegalStateException {
    this.m_document.headerBegin(h);
  }

  /**
   * end a header element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void headerEnd(final Header h) throws IOException, IllegalStateException {
    this.m_document.headerEnd(h);
  }

  /**
   * Provide a header
   *
   * @return the header
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  Header header() throws IOException, IllegalStateException {
    return this.m_document.header();
  }

  /**
   * Create a body element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  Body bodyCreate(final Document owner) throws IOException {
    return this.m_document.bodyCreate(owner);
  }

  /**
   * Begin a body element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void bodyBegin(final Body h) throws IOException, IllegalStateException {
    this.m_document.bodyBegin(h);
  }

  /**
   * end a body element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void bodyEnd(final Body h) throws IOException, IllegalStateException {
    this.m_document.bodyEnd(h);
  }

  /**
   * Provide a body
   *
   * @return the body
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  Body body() throws IOException, IllegalStateException {
    return this.m_document.body();
  }

  /**
   * Create a authors element
   *
   * @param authors
   *          the bibliography authors
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  Authors authorsCreate(final Header owner, final BibAuthors authors)
      throws IOException {
    return this.m_document.authorsCreate(owner, authors);
  }

  /**
   * Begin a authors element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void authorsBegin(final Authors h) throws IOException,
      IllegalStateException {
    this.m_document.authorsBegin(h);
  }

  /**
   * end a authors element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void authorsEnd(final Authors h) throws IOException,
      IllegalStateException {
    this.m_document.authorsEnd(h);
  }

  /**
   * Provide a authors
   *
   * @param authors
   *          the bibliography authors
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void authors(final BibAuthors authors) throws IOException,
      IllegalStateException {
    this.m_document.authors(authors);
  }

  /**
   * Create a title element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  Title titleCreate(final Header owner) throws IOException {
    return this.m_document.titleCreate(owner);
  }

  /**
   * Begin a title element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void titleBegin(final Title h) throws IOException, IllegalStateException {
    this.m_document.titleBegin(h);
  }

  /**
   * end a title element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void titleEnd(final Title h) throws IOException, IllegalStateException {
    this.m_document.titleEnd(h);
  }

  /**
   * Provide a title
   *
   * @return the title
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  Title title() throws IOException, IllegalStateException {
    return this.m_document.title();
  }

  /**
   * Create a summary element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  Summary summaryCreate(final Header owner) throws IOException {
    return this.m_document.summaryCreate(owner);
  }

  /**
   * Begin a summary element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void summaryBegin(final Summary h) throws IOException,
      IllegalStateException {
    this.m_document.summaryBegin(h);
  }

  /**
   * end a summary element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void summaryEnd(final Summary h) throws IOException,
      IllegalStateException {
    this.m_document.summaryEnd(h);
  }

  /**
   * Provide a summary
   *
   * @return the summary
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  Summary summary() throws IOException, IllegalStateException {
    return this.m_document.summary();
  }

  /**
   * Create a section element
   *
   * @param owner
   *          the owner
   * @param label
   *          the label for the section, or {@code null} if none is
   *          required, or
   *          {@link org.logisticPlanning.utils.document.spec.Label#AUTO_LABEL}
   *          if one should be generated automatically
   * @return the element
   * @throws IOException
   *           if io fails
   */
  Section sectionCreate(final Element owner, final Label label)
      throws IOException {
    return this.m_document.sectionCreate(owner, label);
  }

  /**
   * Begin a section element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void sectionBegin(final Section h) throws IOException,
      IllegalStateException {
    this.m_document.sectionBegin(h);
  }

  /**
   * end a section element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void sectionEnd(final Section h) throws IOException,
      IllegalStateException {
    this.m_document.sectionEnd(h);
  }

  /**
   * Provide a section
   *
   * @param label
   *          the label for the section, or {@code null} if none is
   *          required, or
   *          {@link org.logisticPlanning.utils.document.spec.Label#AUTO_LABEL}
   *          if one should be generated automatically
   * @return the section
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  Section section(final Label label) throws IOException,
      IllegalStateException {
    return this.m_document.section(label);
  }

  /**
   * Create a section title element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  SectionTitle sectionTitleCreate(final Section owner) throws IOException {
    return this.m_document.sectionTitleCreate(owner);
  }

  /**
   * Begin a section title element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void sectionTitleBegin(final SectionTitle h) throws IOException,
      IllegalStateException {
    this.m_document.sectionTitleBegin(h);
  }

  /**
   * end a section title element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void sectionTitleEnd(final SectionTitle h) throws IOException,
      IllegalStateException {
    this.m_document.sectionTitleEnd(h);
  }

  /**
   * Provide a section title
   *
   * @return the section title
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  SectionTitle sectionTitle() throws IOException, IllegalStateException {
    return this.m_document.sectionTitle();
  }

  /**
   * Create a section body element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  SectionBody sectionBodyCreate(final Section owner) throws IOException {
    return this.m_document.sectionBodyCreate(owner);
  }

  /**
   * Begin a section body element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void sectionBodyBegin(final SectionBody h) throws IOException,
      IllegalStateException {
    this.m_document.sectionBodyBegin(h);
  }

  /**
   * end a section body element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void sectionBodyEnd(final SectionBody h) throws IOException,
      IllegalStateException {
    this.m_document.sectionBodyEnd(h);
  }

  /**
   * Provide a section body
   *
   * @return the section body
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  SectionBody sectionBody() throws IOException, IllegalStateException {
    return this.m_document.sectionBody();
  }

  /**
   * Create a figure element
   *
   * @param owner
   *          the owner
   * @param label
   *          the label for the figure, or {@code null} if none is
   *          required, or
   *          {@link org.logisticPlanning.utils.document.spec.Label#AUTO_LABEL}
   *          if one should be generated automatically
   * @param colspan
   *          {@code true} if this figure spans over all columns,
   *          {@code false} if it only uses a single columns
   * @return the element
   * @throws IOException
   *           if io fails
   */
  Figure figureCreate(final SectionBody owner, final Label label,
      final boolean colspan) throws IOException {
    return this.m_document.figureCreate(owner, label, colspan);
  }

  /**
   * Begin a figure element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void figureBegin(final Figure h) throws IOException,
      IllegalStateException {
    this.m_document.figureBegin(h);
  }

  /**
   * end a figure element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void figureEnd(final Figure h) throws IOException, IllegalStateException {
    this.m_document.figureEnd(h);
  }

  /**
   * Provide a figure
   *
   * @param label
   *          the label for the figure, or {@code null} if none is
   *          required, or
   *          {@link org.logisticPlanning.utils.document.spec.Label#AUTO_LABEL}
   *          if one should be generated automatically
   * @param colspan
   *          {@code true} if this figure spans over all columns,
   *          {@code false} if it only uses a single columns
   * @return the figure
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  Figure figure(final Label label, final boolean colspan)
      throws IOException, IllegalStateException {
    return this.m_document.figure(label, colspan);
  }

  /**
   * Create a figure caption element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  FigureCaption figureCaptionCreate(final Figure owner) throws IOException {
    return this.m_document.figureCaptionCreate(owner);
  }

  /**
   * Begin a figure caption element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void figureCaptionBegin(final FigureCaption h) throws IOException,
      IllegalStateException {
    this.m_document.figureCaptionBegin(h);
  }

  /**
   * end a figure caption element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void figureCaptionEnd(final FigureCaption h) throws IOException,
      IllegalStateException {
    this.m_document.figureCaptionEnd(h);
  }

  /**
   * Provide a figure caption
   *
   * @return the figure caption
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  FigureCaption figureCaption() throws IOException, IllegalStateException {
    return this.m_document.figureCaption();
  }

  /**
   * Create a figure body element
   *
   * @param relativeNameBase
   *          the uri denoting the basis for the relative name of this
   *          graphic
   * @param owner
   *          the owner
   * @param sizeInMM
   *          the size of the figure in millimeters
   * @return the element
   * @throws URISyntaxException
   *           if the uri is incorrectly formatted
   * @throws IOException
   *           if io fails
   */
  FigureBody figureBodyCreate(final Element owner,
      final URI relativeNameBase, final Dimension2D sizeInMM)
      throws IOException, URISyntaxException {
    return this.m_document.figureBodyCreate(owner, relativeNameBase,
        sizeInMM);
  }

  /**
   * Begin a figure body element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void figureBodyBegin(final FigureBody h) throws IOException,
      IllegalStateException {
    this.m_document.figureBodyBegin(h);
  }

  /**
   * end a figure body element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void figureBodyEnd(final FigureBody h) throws IOException,
      IllegalStateException {
    this.m_document.figureBodyEnd(h);
  }

  /**
   * Provide a figure body
   *
   * @param relativeNameBase
   *          the uri denoting the basis for the relative name of this
   *          graphic
   * @param sizeInMM
   *          the size of the figure in millimeters
   * @return the figure body
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   * @throws URISyntaxException
   *           if the uri is incorrectly formatted
   */
  FigureBody figureBody(final URI relativeNameBase,
      final Dimension2D sizeInMM) throws IOException,
      IllegalStateException, URISyntaxException {
    return this.m_document.figureBody(relativeNameBase, sizeInMM);
  }

  /**
   * Create a graphic element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  Graphic graphicCreate(final FigureBody owner) throws IOException {
    return this.m_document.graphicCreate(owner);
  }

  /**
   * Begin a graphic element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void graphicBegin(final Graphic h) throws IOException,
      IllegalStateException {
    this.m_document.graphicBegin(h);
  }

  /**
   * end a graphic element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void graphicEnd(final Graphic h) throws IOException,
      IllegalStateException {
    this.m_document.graphicEnd(h);
  }

  /**
   * Provide a graphic
   *
   * @return the graphic
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  Graphic graphic() throws IOException, IllegalStateException {
    return this.m_document.graphic();
  }

  /**
   * Create a figure series element
   *
   * @param label
   *          the label for the section, or {@code null} if none is
   *          required, or
   *          {@link org.logisticPlanning.utils.document.spec.Label#AUTO_LABEL}
   *          if one should be generated automatically
   * @param owner
   *          the owner
   * @param cols
   *          the number of columns
   * @param rows
   *          the number of rows
   * @param colspan
   *          {@code true} if this figure series spans over all columns,
   *          {@code false} if it only uses a single columns
   * @return the element
   * @throws IOException
   *           if io fails
   */
  FigureSeries figureSeriesCreate(final SectionBody owner,
      final Label label, final int cols, final int rows,
      final boolean colspan) throws IOException {
    return this.m_document.figureSeriesCreate(owner, label, cols, rows,
        colspan);
  }

  /**
   * Begin a figure series element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void figureSeriesBegin(final FigureSeries h) throws IOException,
      IllegalStateException {
    this.m_document.figureSeriesBegin(h);
  }

  /**
   * end a figure series element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void figureSeriesEnd(final FigureSeries h) throws IOException,
      IllegalStateException {
    this.m_document.figureSeriesEnd(h);
  }

  /**
   * Begin a figure series, i.e., a figure that contains a set of
   * sub-figures. The number of sub-figures is not yet known, but they are
   * arranged in rows and columns. Some document formats are page-based. In
   * that case, a figure series may automatically be divided into several
   * figures, each containing a number of sub-figures according to the row-
   * and column definition. It is tried to automatically approximate the
   * right number of rows and columns of the figure series based on the
   * size of the figures and the templates for the captions.
   *
   * @param label
   *          the label for the section, or {@code null} if none is
   *          required, or
   *          {@link org.logisticPlanning.utils.document.spec.Label#AUTO_LABEL}
   *          if one should be generated automatically
   * @param subFigureSizeInMM
   *          the size of the sub figures.
   * @param captionTemplate
   *          a string that holds text similar or identical to the figure
   *          caption
   * @param subCaptionTemplate
   *          a string that holds text similar or identical to the caption
   *          of the sub-figures
   * @param colspan
   *          {@code true} if this figure series spans over all columns,
   *          {@code false} if it only uses a single columns
   * @return the figure series
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  FigureSeries figureSeries(final Label label,
      final Dimension2D subFigureSizeInMM, final String captionTemplate,
      final String subCaptionTemplate, final boolean colspan)
      throws IOException, IllegalStateException {
    return this.m_document.figureSeries(label, subFigureSizeInMM,
        captionTemplate, subCaptionTemplate, colspan);
  }

  /**
   * Begin a figure series, i.e., a figure that contains a set of
   * sub-figures. The number of sub-figures is not yet known, but they are
   * arranged in rows and columns. Some document formats are page-based. In
   * that case, a figure series may automatically be divided into several
   * figures, each containing a number of sub-figures according to the row-
   * and column definition.
   *
   * @param label
   *          the label for the section, or {@code null} if none is
   *          required, or
   *          {@link org.logisticPlanning.utils.document.spec.Label#AUTO_LABEL}
   *          if one should be generated automatically
   * @param cols
   *          the number of columns
   * @param rows
   *          the number of rows
   * @return the figure series
   * @param colspan
   *          {@code true} if this figure series spans over all columns,
   *          {@code false} if it only uses a single columns
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  FigureSeries figureSeries(final Label label, final int cols,
      final int rows, final boolean colspan) throws IOException,
      IllegalStateException {
    return this.m_document.figureSeries(label, cols, rows, colspan);
  }

  /**
   * Create a figure series caption element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  FigureSeriesCaption figureSeriesCaptionCreate(final FigureSeries owner)
      throws IOException {
    return this.m_document.figureSeriesCaptionCreate(owner);
  }

  /**
   * Begin a figure series caption element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void figureSeriesCaptionBegin(final FigureSeriesCaption h)
      throws IOException, IllegalStateException {
    this.m_document.figureSeriesCaptionBegin(h);
  }

  /**
   * end a figure series caption element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void figureSeriesCaptionEnd(final FigureSeriesCaption h)
      throws IOException, IllegalStateException {
    this.m_document.figureSeriesCaptionEnd(h);
  }

  /**
   * Provide a figure series caption
   *
   * @return the figure series caption
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  FigureSeriesCaption figureSeriesCaption() throws IOException,
      IllegalStateException {
    return this.m_document.figureSeriesCaption();
  }

  /**
   * Create a figure page element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  FigureSeriesPage figureSeriesPageCreate(final FigureSeries owner)
      throws IOException {
    return this.m_document.figureSeriesPageCreate(owner);
  }

  /**
   * Begin a figure page element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void figureSeriesPageBegin(final FigureSeriesPage h) throws IOException,
      IllegalStateException {
    this.m_document.figureSeriesPageBegin(h);
  }

  /**
   * end a figure page element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void figureSeriesPageEnd(final FigureSeriesPage h) throws IOException,
      IllegalStateException {
    this.m_document.figureSeriesPageEnd(h);
  }

  /**
   * Provide a figure page
   *
   * @param pageNumber
   *          the page number
   * @return the figure page
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  FigureSeriesPage figureSeriesPage(final int pageNumber)
      throws IOException, IllegalStateException {
    return this.m_document.figureSeriesPage(pageNumber);
  }

  /**
   * Create a sub figure element
   *
   * @param owner
   *          the owner
   * @param label
   *          the label of this component
   * @param row
   *          the row in which this sub-figure appears
   * @param col
   *          the column in which this sub-figure appears
   * @return the element
   * @throws IOException
   *           if io fails
   */
  SubFigure subFigureCreate(final FigureSeriesPage owner,
      final Label label, final int row, final int col) throws IOException {
    return this.m_document.subFigureCreate(owner, label, row, col);
  }

  /**
   * Begin a sub figure element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void subFigureBegin(final SubFigure h) throws IOException,
      IllegalStateException {
    this.m_document.subFigureBegin(h);
  }

  /**
   * end a sub figure element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void subFigureEnd(final SubFigure h) throws IOException,
      IllegalStateException {
    this.m_document.subFigureEnd(h);
  }

  /**
   * Provide a sub figure
   *
   * @param label
   *          the label for the section, or {@code null} if none is
   *          required, or
   *          {@link org.logisticPlanning.utils.document.spec.Label#AUTO_LABEL}
   *          if one should be generated automatically
   * @return the figure series caption
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  SubFigure subFigure(final Label label) throws IOException {
    return this.m_document.subFigure(label);
  }

  /**
   * Create a sub-figure caption element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  SubFigureCaption subFigureCaptionCreate(final SubFigure owner)
      throws IOException {
    return this.m_document.subFigureCaptionCreate(owner);
  }

  /**
   * Begin a sub-figure caption element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void subFigureCaptionBegin(final SubFigureCaption h) throws IOException,
      IllegalStateException {
    this.m_document.subFigureCaptionBegin(h);
  }

  /**
   * end a sub-figure caption element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void subFigureCaptionEnd(final SubFigureCaption h) throws IOException,
      IllegalStateException {
    this.m_document.subFigureCaptionEnd(h);
  }

  /**
   * Provide a sub-figure caption
   *
   * @return the sub-figure caption
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  SubFigureCaption subFigureCaption() throws IOException,
      IllegalStateException {
    return this.m_document.subFigureCaption();
  }

  /**
   * Create a itemization element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  Itemization itemizationCreate(final AbstractTextBlock owner)
      throws IOException {
    return this.m_document.itemizationCreate(owner);
  }

  /**
   * Begin a itemization element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void itemizationBegin(final Itemization h) throws IOException,
      IllegalStateException {
    this.m_document.itemizationBegin(h);
  }

  /**
   * end a itemization element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void itemizationEnd(final Itemization h) throws IOException,
      IllegalStateException {
    this.m_document.itemizationEnd(h);
  }

  /**
   * Provide a itemization
   *
   * @return the itemization
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  Itemization itemization() throws IOException, IllegalStateException {
    return this.m_document.itemization();
  }

  /**
   * Create a itemizationItem element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  ItemizationItem itemizationItemCreate(final Itemization owner)
      throws IOException {
    return this.m_document.itemizationItemCreate(owner);
  }

  /**
   * Begin a itemization item element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void itemizationItemBegin(final ItemizationItem h) throws IOException,
      IllegalStateException {
    this.m_document.itemizationItemBegin(h);
  }

  /**
   * end a itemization item element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void itemizationItemEnd(final ItemizationItem h) throws IOException,
      IllegalStateException {
    this.m_document.itemizationItemEnd(h);
  }

  /**
   * Provide a itemization item
   *
   * @return the itemization item
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  ItemizationItem itemizationItem() throws IOException,
      IllegalStateException {
    return this.m_document.itemizationItem();
  }

  /**
   * Create a enumeration element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  Enumeration enumerationCreate(final AbstractTextBlock owner)
      throws IOException {
    return this.m_document.enumerationCreate(owner);
  }

  /**
   * Begin a enumeration element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void enumerationBegin(final Enumeration h) throws IOException,
      IllegalStateException {
    this.m_document.enumerationBegin(h);
  }

  /**
   * end a enumeration element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void enumerationEnd(final Enumeration h) throws IOException,
      IllegalStateException {
    this.m_document.enumerationEnd(h);
  }

  /**
   * Provide a enumeration
   *
   * @return the enumeration
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  Enumeration enumeration() throws IOException, IllegalStateException {
    return this.m_document.enumeration();
  }

  /**
   * Create a enumerationItem element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  EnumerationItem enumerationItemCreate(final Enumeration owner)
      throws IOException {
    return this.m_document.enumerationItemCreate(owner);
  }

  /**
   * Begin a enumeration item element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void enumerationItemBegin(final EnumerationItem h) throws IOException,
      IllegalStateException {
    this.m_document.enumerationItemBegin(h);
  }

  /**
   * end a enumeration item element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void enumerationItemEnd(final EnumerationItem h) throws IOException,
      IllegalStateException {
    this.m_document.enumerationItemEnd(h);
  }

  /**
   * Provide a enumeration item
   *
   * @return the enumeration item
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  EnumerationItem enumerationItem() throws IOException,
      IllegalStateException {
    return this.m_document.enumerationItem();
  }

  /**
   * Create a table element
   *
   * @param owner
   *          the owner
   * @param label
   *          the label of this component
   * @param def
   *          the table definition
   * @param rowsPerPage
   *          the rows per page
   * @param colspan
   *          {@code true} if this table spans over all columns,
   *          {@code false} if it only uses a single columns
   * @return the element
   * @throws IOException
   *           if io fails
   */
  Table tableCreate(final SectionBody owner, final Label label,
      final int rowsPerPage, final boolean colspan,
      final TableCellDef[] def) throws IOException {
    return this.m_document.tableCreate(owner, label, rowsPerPage, colspan,
        def);
  }

  /**
   * Begin a table element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void tableBegin(final Table h) throws IOException, IllegalStateException {
    this.m_document.tableBegin(h);
  }

  /**
   * end a table element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void tableEnd(final Table h) throws IOException, IllegalStateException {
    this.m_document.tableEnd(h);
  }

  /**
   * Begin a table following the given table definition which will
   * automatically be divided into tables with at most {@code rowsPerPage}
   * rows.
   *
   * @param label
   *          the label of this component
   * @param def
   *          the table definition
   * @param rowsPerPage
   *          the rows per page
   * @param colspan
   *          {@code true} if this table spans over all columns,
   *          {@code false} if it only uses a single columns
   * @return the table
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  Table table(final Label label, final int rowsPerPage,
      final boolean colspan, final TableCellDef... def)
      throws IOException, IllegalStateException {
    return this.m_document.table(label, rowsPerPage, colspan, def);
  }

  /**
   * Provide a table according to the given column definition. The space
   * that a single table needs is computed based on the key values of the
   * current document and the space requirement of the caption draft.
   *
   * @param label
   *          the label of this component
   * @param captionDraft
   *          the caption draft
   * @param def
   *          the table definition
   * @param colspan
   *          {@code true} if this table spans over all columns,
   *          {@code false} if it only uses a single columns
   * @return the table
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  Table table(final Label label, final String captionDraft,
      final boolean colspan, final TableCellDef... def)
      throws IOException, IllegalStateException {
    return this.m_document.table(label, captionDraft, colspan, def);
  }

  /**
   * Create a table caption element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  TableCaption tableCaptionCreate(final Table owner) throws IOException {
    return this.m_document.tableCaptionCreate(owner);
  }

  /**
   * Begin a table caption element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void tableCaptionBegin(final TableCaption h) throws IOException,
      IllegalStateException {
    this.m_document.tableCaptionBegin(h);
  }

  /**
   * end a table caption element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void tableCaptionEnd(final TableCaption h) throws IOException,
      IllegalStateException {
    this.m_document.tableCaptionEnd(h);
  }

  /**
   * Provide a table caption
   *
   * @return the tableCaption
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  TableCaption tableCaption() throws IOException, IllegalStateException {
    return this.m_document.tableCaption();
  }

  /**
   * Create a table header element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  TableHeader tableHeaderCreate(final Table owner) throws IOException {
    return this.m_document.tableHeaderCreate(owner);
  }

  /**
   * Begin a table header element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void tableHeaderBegin(final TableHeader h) throws IOException,
      IllegalStateException {
    this.m_document.tableHeaderBegin(h);
  }

  /**
   * end a table header element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void tableHeaderEnd(final TableHeader h) throws IOException,
      IllegalStateException {
    this.m_document.tableHeaderEnd(h);
  }

  /**
   * Provide a table header
   *
   * @return the tableHeader
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  TableHeader tableHeader() throws IOException, IllegalStateException {
    return this.m_document.tableHeader();
  }

  /**
   * Create a table footer element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  TableFooter tableFooterCreate(final Table owner) throws IOException {
    return this.m_document.tableFooterCreate(owner);
  }

  /**
   * Begin a table footer element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void tableFooterBegin(final TableFooter h) throws IOException,
      IllegalStateException {
    this.m_document.tableFooterBegin(h);
  }

  /**
   * end a table footer element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void tableFooterEnd(final TableFooter h) throws IOException,
      IllegalStateException {
    this.m_document.tableFooterEnd(h);
  }

  /**
   * Provide a table footer
   *
   * @return the tableFooter
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  TableFooter tableFooter() throws IOException, IllegalStateException {
    return this.m_document.tableFooter();
  }

  /**
   * Create a table body element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  TableBody tableBodyCreate(final Table owner) throws IOException {
    return this.m_document.tableBodyCreate(owner);
  }

  /**
   * Begin a table body element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void tableBodyBegin(final TableBody h) throws IOException,
      IllegalStateException {
    this.m_document.tableBodyBegin(h);
  }

  /**
   * end a table body element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void tableBodyEnd(final TableBody h) throws IOException,
      IllegalStateException {
    this.m_document.tableBodyEnd(h);
  }

  /**
   * Provide a table body
   *
   * @return the tableBody
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  TableBody tableBody() throws IOException, IllegalStateException {
    return this.m_document.tableBody();
  }

  /**
   * Create a table header row element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  TableHeaderRow tableHeaderRowCreate(final TableHeader owner)
      throws IOException {
    return this.m_document.tableHeaderRowCreate(owner);
  }

  /**
   * Begin a table header row element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void tableHeaderRowBegin(final TableHeaderRow h) throws IOException,
      IllegalStateException {
    this.m_document.tableHeaderRowBegin(h);
  }

  /**
   * end a table header row element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void tableHeaderRowEnd(final TableHeaderRow h) throws IOException,
      IllegalStateException {
    this.m_document.tableHeaderRowEnd(h);
  }

  /**
   * Provide a table header row
   *
   * @return the tableHeaderRow
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  TableHeaderRow tableHeaderRow() throws IOException,
      IllegalStateException {
    return this.m_document.tableHeaderRow();
  }

  /**
   * Create a table header cell element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  TableHeaderCell tableHeaderCellCreate(final TableHeaderRow owner)
      throws IOException {
    return this.m_document.tableHeaderCellCreate(owner);
  }

  /**
   * Begin a table header cell element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void tableHeaderCellBegin(final TableHeaderCell h) throws IOException,
      IllegalStateException {
    this.m_document.tableHeaderCellBegin(h);
  }

  /**
   * end a table header cell element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void tableHeaderCellEnd(final TableHeaderCell h) throws IOException,
      IllegalStateException {
    this.m_document.tableHeaderCellEnd(h);
  }

  /**
   * Provide a table header cell
   *
   * @param cols
   *          the number of columns occupied by the cell
   * @param rows
   *          the number of rows occupied by the cell
   * @param def
   *          the cell definition
   * @return the tableHeaderCell
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  TableHeaderCell tableHeaderCell(final int cols, final int rows,
      final TableCellDef... def) throws IOException, IllegalStateException {
    return this.m_document.tableHeaderCell(cols, rows, def);
  }

  /**
   * Create a table footer row element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  TableFooterRow tableFooterRowCreate(final TableFooter owner)
      throws IOException {
    return this.m_document.tableFooterRowCreate(owner);
  }

  /**
   * Begin a table footer row element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void tableFooterRowBegin(final TableFooterRow h) throws IOException,
      IllegalStateException {
    this.m_document.tableFooterRowBegin(h);
  }

  /**
   * end a table footer row element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void tableFooterRowEnd(final TableFooterRow h) throws IOException,
      IllegalStateException {
    this.m_document.tableFooterRowEnd(h);
  }

  /**
   * Provide a table footer row
   *
   * @return the tableFooterRow
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  TableFooterRow tableFooterRow() throws IOException,
      IllegalStateException {
    return this.m_document.tableFooterRow();
  }

  /**
   * Create a table footer cell element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  TableFooterCell tableFooterCellCreate(final TableFooterRow owner)
      throws IOException {
    return this.m_document.tableFooterCellCreate(owner);
  }

  /**
   * Begin a table footer cell element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void tableFooterCellBegin(final TableFooterCell h) throws IOException,
      IllegalStateException {
    this.m_document.tableFooterCellBegin(h);
  }

  /**
   * end a table footer cell element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void tableFooterCellEnd(final TableFooterCell h) throws IOException,
      IllegalStateException {
    this.m_document.tableFooterCellEnd(h);
  }

  /**
   * Provide a table footer cell
   *
   * @param cols
   *          the number of columns occupied by the cell
   * @param rows
   *          the number of rows occupied by the cell
   * @param def
   *          the cell definition
   * @return the tableFooterCell
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  TableFooterCell tableFooterCell(final int cols, final int rows,
      final TableCellDef... def) throws IOException, IllegalStateException {
    return this.m_document.tableFooterCell(cols, rows, def);
  }

  /**
   * Create a table page element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  TablePage tablePageCreate(final TableBody owner) throws IOException {
    return this.m_document.tablePageCreate(owner);
  }

  /**
   * Begin a table page element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void tablePageBegin(final TablePage h) throws IOException,
      IllegalStateException {
    this.m_document.tablePageBegin(h);
  }

  /**
   * end a table page element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void tablePageEnd(final TablePage h) throws IOException,
      IllegalStateException {
    this.m_document.tablePageEnd(h);
  }

  /**
   * Create a table body row element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  TableBodyRow tableBodyRowCreate(final TablePage owner)
      throws IOException {
    return this.m_document.tableBodyRowCreate(owner);
  }

  /**
   * Begin a table body row element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void tableBodyRowBegin(final TableBodyRow h) throws IOException,
      IllegalStateException {
    this.m_document.tableBodyRowBegin(h);
  }

  /**
   * end a table body row element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void tableBodyRowEnd(final TableBodyRow h) throws IOException,
      IllegalStateException {
    this.m_document.tableBodyRowEnd(h);
  }

  /**
   * Provide a table body row
   *
   * @return the tableBodyRow
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  TableBodyRow tableBodyRow() throws IOException, IllegalStateException {
    return this.m_document.tableBodyRow();
  }

  /**
   * Create a table body cell element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  TableBodyCell tableBodyCellCreate(final TableBodyRow owner)
      throws IOException {
    return this.m_document.tableBodyCellCreate(owner);
  }

  /**
   * Begin a table body cell element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void tableBodyCellBegin(final TableBodyCell h) throws IOException,
      IllegalStateException {
    this.m_document.tableBodyCellBegin(h);
  }

  /**
   * end a table body cell element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void tableBodyCellEnd(final TableBodyCell h) throws IOException,
      IllegalStateException {
    this.m_document.tableBodyCellEnd(h);
  }

  /**
   * Provide a table body cell
   *
   * @param cols
   *          the number of columns occupied by the cell
   * @param rows
   *          the number of rows occupied by the cell
   * @param def
   *          the cell definition
   * @return the tableBodyCell
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  TableBodyCell tableBodyCell(final int cols, final int rows,
      final TableCellDef... def) throws IOException, IllegalStateException {
    return this.m_document.tableBodyCell(cols, rows, def);
  }

  /**
   * Draw a horizontal line in the table
   *
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if drawing the line is not permitted here
   */
  void tableHorizontalLine() throws IOException, IllegalStateException {
    this.m_document.tableHorizontalLine();
  }

  /**
   * Create a subscript element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  Subscript subscriptCreate(final AbstractText owner) throws IOException {
    return this.m_document.subscriptCreate(owner);
  }

  /**
   * Begin a subscript element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void subscriptBegin(final Subscript h) throws IOException,
      IllegalStateException {
    this.m_document.subscriptBegin(h);
  }

  /**
   * end a subscript element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void subscriptEnd(final Subscript h) throws IOException,
      IllegalStateException {
    this.m_document.subscriptEnd(h);
  }

  /**
   * Provide a subscript
   *
   * @return the subscript
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  Subscript subscript() throws IOException, IllegalStateException {
    return this.m_document.subscript();
  }

  /**
   * Create a superscript element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  Superscript superscriptCreate(final AbstractText owner)
      throws IOException {
    return this.m_document.superscriptCreate(owner);
  }

  /**
   * Begin a superscript element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void superscriptBegin(final Superscript h) throws IOException,
      IllegalStateException {
    this.m_document.superscriptBegin(h);
  }

  /**
   * end a superscript element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void superscriptEnd(final Superscript h) throws IOException,
      IllegalStateException {
    this.m_document.superscriptEnd(h);
  }

  /**
   * Provide a superscript
   *
   * @return the superscript
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  Superscript superscript() throws IOException, IllegalStateException {
    return this.m_document.superscript();
  }

  /**
   * Create a emphasize element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  Emphasize emphasizeCreate(final AbstractText owner) throws IOException {
    return this.m_document.emphasizeCreate(owner);
  }

  /**
   * Begin a emphasize element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void emphasizeBegin(final Emphasize h) throws IOException,
      IllegalStateException {
    this.m_document.emphasizeBegin(h);
  }

  /**
   * end a emphasize element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void emphasizeEnd(final Emphasize h) throws IOException,
      IllegalStateException {
    this.m_document.emphasizeEnd(h);
  }

  /**
   * Provide a emphasize
   *
   * @return the emphasize
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  Emphasize emphasize() throws IOException, IllegalStateException {
    return this.m_document.emphasize();
  }

  /**
   * Create a in-quotes element
   *
   * @param owner
   *          the owner
   * @param quotes
   *          the quotes to use
   * @return the element
   * @throws IOException
   *           if io fails
   */
  InQuotes inQuotesCreate(final AbstractText owner, final EQuotes quotes)
      throws IOException {
    return this.m_document.inQuotesCreate(owner, quotes);
  }

  /**
   * Begin a in-quotes element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void inQuotesBegin(final InQuotes h) throws IOException,
      IllegalStateException {
    this.m_document.inQuotesBegin(h);
  }

  /**
   * end a in-quotes element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void inQuotesEnd(final InQuotes h) throws IOException,
      IllegalStateException {
    this.m_document.inQuotesEnd(h);
  }

  /**
   * Provide a in-quotes
   *
   * @param quotes
   *          the quotes to use
   * @return the in-quotes
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  InQuotes inQuotes(final EQuotes quotes) throws IOException,
      IllegalStateException {
    return this.m_document.inQuotes(quotes);
  }

  /**
   * Create a equation element
   *
   * @param owner
   *          the owner
   * @param label
   *          the label for the equation, or {@code null} if none is
   *          required, or
   *          {@link org.logisticPlanning.utils.document.spec.Label#AUTO_LABEL}
   *          if one should be generated automatically
   * @return the element
   * @throws IOException
   *           if io fails
   */
  Equation equationCreate(final SectionBody owner, final Label label)
      throws IOException {
    return this.m_document.equationCreate(owner, label);
  }

  /**
   * Begin a equation element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void equationBegin(final Equation h) throws IOException,
      IllegalStateException {
    this.m_document.equationBegin(h);
  }

  /**
   * end a equation element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void equationEnd(final Equation h) throws IOException,
      IllegalStateException {
    this.m_document.equationEnd(h);
  }

  /**
   * Provide a equation
   *
   * @param label
   *          the label for the equation, or {@code null} if none is
   *          required, or
   *          {@link org.logisticPlanning.utils.document.spec.Label#AUTO_LABEL}
   *          if one should be generated automatically
   * @return the equation
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  Equation equation(final Label label) throws IOException,
      IllegalStateException {
    return this.m_document.equation(label);
  }

  /**
   * Create a equation body element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  EquationBody equationBodyCreate(final Equation owner) throws IOException {
    return this.m_document.equationBodyCreate(owner);
  }

  /**
   * Begin a equation body element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void equationBodyBegin(final EquationBody h) throws IOException,
      IllegalStateException {
    this.m_document.equationBodyBegin(h);
  }

  /**
   * end a equation body element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void equationBodyEnd(final EquationBody h) throws IOException,
      IllegalStateException {
    this.m_document.equationBodyEnd(h);
  }

  /**
   * Provide a equation body
   *
   * @return the equationBody
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  EquationBody equationBody() throws IOException, IllegalStateException {
    return this.m_document.equationBody();
  }

  /**
   * Create a inline math element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  InlineMath inlineMathCreate(final AbstractTextComplex owner)
      throws IOException {
    return this.m_document.inlineMathCreate(owner);
  }

  /**
   * Begin a inline math element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void inlineMathBegin(final InlineMath h) throws IOException,
      IllegalStateException {
    this.m_document.inlineMathBegin(h);
  }

  /**
   * end a inline math element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void inlineMathEnd(final InlineMath h) throws IOException,
      IllegalStateException {
    this.m_document.inlineMathEnd(h);
  }

  /**
   * Provide a inline math
   *
   * @return the inlineMath
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  InlineMath inlineMath() throws IOException, IllegalStateException {
    return this.m_document.inlineMath();
  }

  /**
   * Create a normal text element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  NormalText normalTextCreate(final AbstractMathElement owner)
      throws IOException {
    return this.m_document.normalTextCreate(owner);
  }

  /**
   * Begin a normal text element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void normalTextBegin(final NormalText h) throws IOException,
      IllegalStateException {
    this.m_document.normalTextBegin(h);
  }

  /**
   * end a normal text element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void normalTextEnd(final NormalText h) throws IOException,
      IllegalStateException {
    this.m_document.normalTextEnd(h);
  }

  /**
   * Provide a normal text
   *
   * @return the normalText
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  NormalText normalText() throws IOException, IllegalStateException {
    return this.m_document.normalText();
  }

  /**
   * Create a math subscript element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  MathSubscript mathSubscriptCreate(final AbstractMathElement owner)
      throws IOException {
    return this.m_document.mathSubscriptCreate(owner);
  }

  /**
   * Begin a math subscript element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void mathSubscriptBegin(final MathSubscript h) throws IOException,
      IllegalStateException {
    this.m_document.mathSubscriptBegin(h);
  }

  /**
   * end a math subscript element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void mathSubscriptEnd(final MathSubscript h) throws IOException,
      IllegalStateException {
    this.m_document.mathSubscriptEnd(h);
  }

  /**
   * Provide a math subscript
   *
   * @return the math subscript
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  MathSubscript mathSubscript() throws IOException, IllegalStateException {
    return this.m_document.mathSubscript();
  }

  /**
   * Create a math superscript element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  MathSuperscript mathSuperscriptCreate(final AbstractMathElement owner)
      throws IOException {
    return this.m_document.mathSuperscriptCreate(owner);
  }

  /**
   * Begin a math superscript element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void mathSuperscriptBegin(final MathSuperscript h) throws IOException,
      IllegalStateException {
    this.m_document.mathSuperscriptBegin(h);
  }

  /**
   * end a math superscript element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void mathSuperscriptEnd(final MathSuperscript h) throws IOException,
      IllegalStateException {
    this.m_document.mathSuperscriptEnd(h);
  }

  /**
   * Provide a math superscript
   *
   * @return the mathSuperscript
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  MathSuperscript mathSuperscript() throws IOException,
      IllegalStateException {
    return this.m_document.mathSuperscript();
  }

  /**
   * Create a math name element
   *
   * @param owner
   *          the owner
   * @param type
   *          the math name type
   * @return the element
   * @throws IOException
   *           if io fails
   */
  MathName mathNameCreate(final AbstractMathElement owner,
      final EMathName type) throws IOException {
    return this.m_document.mathNameCreate(owner, type);
  }

  /**
   * Begin a math name element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void mathNameBegin(final MathName h) throws IOException,
      IllegalStateException {
    this.m_document.mathNameBegin(h);
  }

  /**
   * end a math name element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void mathNameEnd(final MathName h) throws IOException,
      IllegalStateException {
    this.m_document.mathNameEnd(h);
  }

  /**
   * Provide a math name
   *
   * @param type
   *          the math name type
   * @return the math name
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  MathName mathName(final EMathName type) throws IOException,
      IllegalStateException {
    return this.m_document.mathName(type);
  }

  /**
   * Create a math operation element
   *
   * @param owner
   *          the owner
   * @param type
   *          the math operation type
   * @return the element
   * @throws IOException
   *           if io fails
   */
  MathOp mathOpCreate(final AbstractMathElement owner, final EMathOp type)
      throws IOException {
    return this.m_document.mathOpCreate(owner, type);
  }

  /**
   * Begin a math operation element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void mathOpBegin(final MathOp h) throws IOException,
      IllegalStateException {
    this.m_document.mathOpBegin(h);
  }

  /**
   * end a math operation element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void mathOpEnd(final MathOp h) throws IOException, IllegalStateException {
    this.m_document.mathOpEnd(h);
  }

  /**
   * Provide a math operation
   *
   * @param type
   *          the math operation type
   * @return the math operation
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  MathOp mathOp(final EMathOp type) throws IOException,
      IllegalStateException {
    return this.m_document.mathOp(type);
  }

  /**
   * Create a math operation parameter element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  MathOpParam mathOpParamCreate(final MathOp owner) throws IOException {
    return this.m_document.mathOpParamCreate(owner);
  }

  /**
   * Begin a math operation parameter element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void mathOpParamBegin(final MathOpParam h) throws IOException,
      IllegalStateException {
    this.m_document.mathOpParamBegin(h);
  }

  /**
   * end a math operation parameter element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void mathOpParamEnd(final MathOpParam h) throws IOException,
      IllegalStateException {
    this.m_document.mathOpParamEnd(h);
  }

  /**
   * Provide a math operation parameter
   *
   * @return the math operation parameter
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  MathOpParam mathOpParam() throws IOException, IllegalStateException {
    return this.m_document.mathOpParam();
  }

  /**
   * Create a macro element
   *
   * @param owner
   *          the owner
   * @param desc
   *          the macro descriptor
   * @return the element
   * @throws IOException
   *           if io fails
   */
  Macro macroCreate(final Header owner, final MacroDescriptor desc)
      throws IOException {
    return this.m_document.macroCreate(owner, desc);
  }

  /**
   * Begin a macro element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void macroBegin(final Macro h) throws IOException, IllegalStateException {
    this.m_document.macroBegin(h);
  }

  /**
   * end a macro element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void macroEnd(final Macro h) throws IOException, IllegalStateException {
    this.m_document.macroEnd(h);
  }

  /**
   * Is the macro defined?
   *
   * @param desc
   *          the macro defined
   * @return {@code true} if the macro is already defined, {@code false}
   *         otherwise
   */
  boolean isMacroDefined(final MacroDescriptor desc) {
    return this.m_document.isMacroDefined(desc);
  }

  /**
   * Provide a macro
   *
   * @param desc
   *          the macro descriptor
   * @return the macro
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  Macro macro(final MacroDescriptor desc) throws IOException,
      IllegalStateException {
    return this.m_document.macro(desc);
  }

  /**
   * print a macro parameter's value
   *
   * @param id
   *          the macro parameter's value
   * @throws IOException
   *           if io fails
   */
  void macroParameter(final int id) throws IOException {
    this.m_document.macroParameter(id);
  }

  /**
   * Create a macro invocation element
   *
   * @param owner
   *          the owner
   * @param desc
   *          the macroInvocation descriptor
   * @return the element
   * @throws IOException
   *           if io fails
   */
  MacroInvocation macroInvocationCreate(final AbstractInlineElement owner,
      final MacroDescriptor desc) throws IOException {
    return this.m_document.macroInvocationCreate(owner, desc);
  }

  /**
   * Begin a macro invocation element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void macroInvocationBegin(final MacroInvocation h) throws IOException,
      IllegalStateException {
    this.m_document.macroInvocationBegin(h);
  }

  /**
   * end a macro invocation element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void macroInvocationEnd(final MacroInvocation h) throws IOException,
      IllegalStateException {
    this.m_document.macroInvocationEnd(h);
  }

  /**
   * Provide a macro invocation
   *
   * @param desc
   *          the macroInvocation descriptor
   * @return the macroInvocation
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  MacroInvocation macroInvocation(final MacroDescriptor desc)
      throws IOException, IllegalStateException {
    return this.m_document.macroInvocation(desc);
  }

  /**
   * Create a macro parameter element
   *
   * @param owner
   *          the owner
   * @return the element
   * @throws IOException
   *           if io fails
   */
  MacroParameter macroParameterCreate(final MacroInvocation owner)
      throws IOException {
    return this.m_document.macroParameterCreate(owner);
  }

  /**
   * Begin a macro parameter element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void macroParameterBegin(final MacroParameter h) throws IOException,
      IllegalStateException {
    this.m_document.macroParameterBegin(h);
  }

  /**
   * end a macro parameter element
   *
   * @param h
   *          the element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void macroParameterEnd(final MacroParameter h) throws IOException,
      IllegalStateException {
    this.m_document.macroParameterEnd(h);
  }

  /**
   * Provide a macro parameter
   *
   * @return the macroParameter
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  MacroParameter macroParameter() throws IOException,
      IllegalStateException {
    return this.m_document.macroParameter();
  }

  /**
   * Make a reference to a given bibliography record
   *
   * @param mode
   *          the citation mode
   * @param records
   *          the records
   * @throws IOException
   *           if io fails
   */
  void cite(final ECitationMode mode, final BibRecord... records)
      throws IOException {
    this.checkWrite();
    if (mode == null) {
      throw new IllegalArgumentException(//
          "Citation mode must not be null."); //$NON-NLS-1$
    }
    this.m_document.cite(mode, records);
  }

  /**
   * Create a document
   *
   * @param owner
   *          the owning context
   * @return the document
   * @throws IOException
   *           if io fails
   */
  Document documentCreate(final Context owner) throws IOException {
    final Context c;
    c = ((Context) (this.m_owner));
    return c.documentCreate(c);
  }

  /**
   * Begin a document
   *
   * @param d
   *          the document
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void documentBegin(final Document d) throws IOException,
      IllegalStateException {
    ((Context) (this.m_owner)).documentBegin(d);
  }

  /**
   * end a document element
   *
   * @param h
   *          the document element
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  void documentEnd(final Document h) throws IOException,
      IllegalStateException {
    ((Context) (this.m_owner)).documentEnd(h);
  }

  /**
   * Provide a document
   *
   * @return the document
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the document state is inconsistent
   */
  Document document() throws IOException, IllegalStateException {
    return ((Context) (this.m_owner)).document();
  }

  /**
   * Allocate a unique name. The name will only consist of uppercase
   * characters {@code 'A'} to {@code 'Z'}. This makes it suitable for any
   * kind of naming scheme, regardless whether it is case-sensitive or not,
   * and regardless whether it allows numbers or not.
   *
   * @return the unique name
   */
  protected String autoName() {
    return this.m_document.autoName();
  }

  /**
   * Get the logger, i.e., the destination to write log information to.
   * During the generation of a document, it is possible to provide
   * progress or debug information to a log.
   *
   * @return the logger
   */
  public Logger getLogger() {
    return this.m_document.m_owner.getLogger();
  }
}
