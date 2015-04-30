package org.logisticPlanning.utils.document.spec;

import java.awt.geom.Dimension2D;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.logisticPlanning.utils.document.spec.bib.BibAuthors;
import org.logisticPlanning.utils.document.spec.bib.BibRecord;
import org.logisticPlanning.utils.text.EQuotes;
import org.logisticPlanning.utils.text.TextUtils;

/**
 * The basic abstract document class. This class provides a lot of
 * protected methods that need to be overridden in order to implement the
 * concrete functionality of generating a document in a given format, be it
 * {@link org.logisticPlanning.utils.document.impl.latex.LaTeXDocument
 * LaTeX} or
 * {@link org.logisticPlanning.utils.document.impl.xhtml.XHTMLDocument
 * XHTML}.
 */
public class Document extends Element {

  /** in header */
  private static final int STATE_IN_HEADER = (Element.STATE_NOTHING + 1);

  /** after header */
  private static final int STATE_AFTER_HEADER = (Document.STATE_IN_HEADER + 1);

  /** in body */
  private static final int STATE_IN_BODY = (Document.STATE_AFTER_HEADER + 1);

  /** after body */
  private static final int STATE_AFTER_BODY = (Document.STATE_IN_BODY + 1);

  /** the current element */
  Element m_current;

  /** the section counter */
  private int m_sectionCounter;

  /** the figure counter */
  private int m_figureCounter;

  /** the table counter */
  private int m_tableCounter;

  /** the equation counter */
  private int m_equationCounter;

  /** the references */
  private final HashMap<BibRecord, BibReference> m_refs;

  /** the reference list */
  private final ArrayList<BibReference> m_refLst;

  /** the macros */
  private final HashSet<MacroDescriptor> m_macros;

  /**
   * instantiate
   * 
   * @param context
   *          the owning context
   * @throws IOException
   *           if io fails
   */
  protected Document(final Context context) throws IOException {
    super(context);
    this.m_current = this;
    this.m_refs = new HashMap<>();
    this.m_refLst = new ArrayList<>();
    this.m_macros = new HashSet<>();
  }

  /** {@inheritDoc} */
  @Override
  public Context getOwner() {
    return ((Context) (this.m_owner));
  }

  /**
   * get the id of the next section
   * 
   * @return the id of the next section
   */
  final int nextSection() {
    return (++this.m_sectionCounter);
  }

  /**
   * get the id of the next figure
   * 
   * @return the id of the next figure
   */
  final int nextFigure() {
    return (++this.m_figureCounter);
  }

  /**
   * get the id of the next equation
   * 
   * @return the id of the next equation
   */
  final int nextEquation() {
    return (++this.m_equationCounter);
  }

  /**
   * get the id of the next table
   * 
   * @return the id of the next table
   */
  final int nextTable() {
    return (++this.m_tableCounter);
  }

  /** {@inheritDoc} */
  @Override
  protected void writeChar(final int data) throws IOException {
    this.write(String.valueOf((char) data));
  }

  /** {@inheritDoc} */
  @Override
  protected void write(final String data) throws IOException {
    throw new UnsupportedOperationException();
  }

  /** {@inheritDoc} */
  @Override
  protected void writeHyphenated(final String data) throws IOException {
    this.write(data);
  }

  /** {@inheritDoc} */
  @Override
  protected void writeBoolean(final boolean v) throws IOException {
    this.write(String.valueOf(v));
  }

  /** {@inheritDoc} */
  @Override
  final void writeInt(final int v) throws IOException {
    this.writeInt(v, null);
  }

  /** {@inheritDoc} */
  @Override
  final void writeIntInText(final int v, final boolean beginUpperCase)
      throws IOException {
    String s;

    if ((v >= 0) && (v < TextUtils.SMALL_NUMBERS.size())) {
      s = TextUtils.SMALL_NUMBERS.get(v);
      if (beginUpperCase) {
        this.writeChar(Character.toUpperCase(s.charAt(0)));
        s = s.substring(1);
      }
      this.write(s);
    } else {
      this.writeInt(v);
    }
  }

  /** {@inheritDoc} */
  @Override
  final void writeLongInText(final long v, final boolean beginUpperCase)
      throws IOException {
    String s;

    if ((v >= 0) && (v < TextUtils.SMALL_NUMBERS.size())) {
      s = TextUtils.SMALL_NUMBERS.get((int) v);
      if (beginUpperCase) {
        this.writeChar(Character.toUpperCase(s.charAt(0)));
        s = s.substring(1);
      }
      this.write(s);
    } else {
      this.writeLong(v);
    }
  }

  /** {@inheritDoc} */
  @Override
  final void writeInt(final int v, final NumberFormat format)
      throws IOException {
    if (format != null) {
      this.writeFormattedNumber(format.format(v));
    } else {
      this.writeNumber(Integer.toString(v));
    }
  }

  /** {@inheritDoc} */
  @Override
  final void writeLong(final long v, final NumberFormat format)
      throws IOException {
    if (format != null) {
      this.writeFormattedNumber(format.format(v));
    } else {
      this.writeNumber(Long.toString(v));
    }
  }

  /** {@inheritDoc} */
  @Override
  final void writeLong(final long v) throws IOException {
    this.writeLong(v, null);
  }

  /** {@inheritDoc} */
  @Override
  final void writeDouble(final double v) throws IOException {
    this.writeDouble(v, null);
  }

  /**
   * print the positive or negative infinity
   * 
   * @param positive
   *          is the infinity value positive or negative
   * @throws IOException
   *           if io fails
   */
  protected void writeInfinity(final boolean positive) throws IOException {
    this.write(Double.toString(positive ? Double.POSITIVE_INFINITY
        : Double.NEGATIVE_INFINITY));
  }

  /**
   * print NaN
   * 
   * @throws IOException
   *           if io fails
   */
  protected void writeNaN() throws IOException {
    this.write(Double.toString(Double.NaN));
  }

  /**
   * write a number represented as string
   * 
   * @param number
   *          the number represented as string
   * @throws IOException
   *           if io fails
   */
  protected void writeNumber(final String number) throws IOException {
    this.writeFormattedNumber(number);
  }

  /**
   * write a formatted number represented as string
   * 
   * @param number
   *          the formatted number represented as string
   * @throws IOException
   *           if io fails
   */
  protected void writeFormattedNumber(final String number)
      throws IOException {
    this.write(number);
  }

  /** {@inheritDoc} */
  @Override
  final void writeDouble(final double v, final NumberFormat format)
      throws IOException {
    if (Double.isInfinite(v)) {
      this.writeInfinity(v >= 0d);
    } else {
      if (Double.isNaN(v)) {
        this.writeNaN();
      } else {
        if (format != null) {
          this.writeFormattedNumber(format.format(v));
        } else {
          this.writeNumber(TextUtils.toStringApprox(v));
        }
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void writeNoneBreakingSpace() throws IOException {
    this.writeChar(' ');
  }

  /** {@inheritDoc} */
  @Override
  protected void writeLinebreak() throws IOException {
    this.writeChar('\n');
  }

  /**
   * unpack a label
   * 
   * @param ref
   *          the label
   * @param lst
   *          the list
   */
  private static final void __unpack(final Label ref,
      final ArrayList<SingleLabel> lst) {
    if (ref instanceof SingleLabel) {
      lst.add((SingleLabel) ref);
      return;
    }
    if (ref instanceof _MultiLabel) {
      lst.addAll(((_MultiLabel) ref).m_labels);
      return;
    }
    if (ref instanceof _IndirectLabel) {
      Document.__unpack((((_IndirectLabel) ref).m_label), lst);
      return;
    }
    throw new IllegalArgumentException("Invalid label!"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  final void reference(final ESequenceType type, final Label... ref)
      throws IOException {
    final ArrayList<SingleLabel> lbls;
    final int s;

    if (ref == null) {
      throw new IllegalArgumentException(//
          "Label list must not be null."); //$NON-NLS-1$
    }

    lbls = new ArrayList<>();
    for (final Label r : ref) {
      Document.__unpack(r, lbls);
    }

    s = lbls.size();
    if (s <= 0) {
      throw new IllegalArgumentException(//
          "Label list must not be empty.");//$NON-NLS-1$
    }

    this.writeReference(type, lbls.toArray(new SingleLabel[s]));
  }

  //
  // labeling and naming
  //

  /** {@inheritDoc} */
  @Override
  protected File resolveRelativeName(final URI relativeName)
      throws IOException, URISyntaxException {
    return this.m_owner.resolveRelativeName(relativeName);
  }

  /** {@inheritDoc} */
  @Override
  protected URI relativizeFile(final File file) throws IOException,
      URISyntaxException {
    return this.m_owner.relativizeFile(file);
  }

  /** {@inheritDoc} */
  @Override
  protected final String autoName() {
    return this.m_owner.autoName();
  }

  /** {@inheritDoc} */
  @Override
  protected SingleLabel createSingleLabel(final ELabelType type,
      final String key) {
    return new SingleLabel(type, key);
  }

  /**
   * Create a new label for future use
   * 
   * @param type
   *          the label type
   * @return the label
   */
  public Label createLabel(final ELabelType type) {
    return new _IndirectLabel(
        this.createSingleLabel(type, this.autoName()));
  }

  /**
   * write a single label to the output stream
   * 
   * @param label
   *          the label
   * @throws IOException
   *           if io fails
   */
  @SuppressWarnings("unused")
  protected void doWriteSingleLabel(final SingleLabel label)
      throws IOException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected final void writeSingleLabel(final SingleLabel label,
      final String info) throws IOException {
    if (label.m_written) {
      throw new IllegalStateException("Label '" + label.getKey() + //$NON-NLS-1$
          "' has already been written!");//$NON-NLS-1$
    }
    label.m_written = true;
    label.m_info = info;
    this.doWriteSingleLabel(label);
  }

  /**
   * Write a reference to the given labels
   * 
   * @param type
   *          the way in which the references should be connected
   * @param labels
   *          the labels
   * @throws IOException
   *           if io fails
   */
  @SuppressWarnings("unused")
  protected void writeReference(final ESequenceType type,
      final SingleLabel[] labels) throws IOException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSequence(final Sequence sequence,
      final ESequenceType type,
      final boolean connectLastElementWithNonBreakableSpace)
      throws IOException {
    //
  }

  //
  // document elements
  //
  /**
   * end the given element
   * 
   * @param e
   *          the given element
   */
  private final void end(final Element e) {
    if ((this.m_current != e) || (e == this)) {
      throw new IllegalStateException(//
          "Can only close top-level element (" + //$NON-NLS-1$
              this.m_current + ") but tried to close " + //$NON-NLS-1$
              e);
    }
    this.m_current = e.m_owner;
  }

  /** {@inheritDoc} */
  @Override
  protected Header headerCreate(final Document owner) throws IOException {
    return new Header(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void headerBegin(final Header h) throws IOException,
      IllegalStateException {
    final Logger log;

    log = this.m_owner.getLogger();
    if ((log != null) && (log.isLoggable(Level.FINE))) {
      log.fine("Document header generation begins: " + this.toString());//$NON-NLS-1$
    }

    if (this.m_state != Element.STATE_NOTHING) {
      throw new IllegalStateException(//
          "Document header can only begin at the start of the document."); //$NON-NLS-1$
    }
    this.m_state = Document.STATE_IN_HEADER;
  }

  /** {@inheritDoc} */
  @Override
  protected void headerEnd(final Header h) throws IOException,
      IllegalStateException {
    final Logger log;

    if (this.m_state != Document.STATE_IN_HEADER) {
      throw new IllegalStateException(//
          "Document header can only end after it has begun."); //$NON-NLS-1$
    }
    this.m_state = Document.STATE_AFTER_HEADER;

    this.end(h);

    log = this.m_owner.getLogger();
    if ((log != null) && (log.isLoggable(Level.FINE))) {
      log.fine("Document header generation finished: " + this.toString());//$NON-NLS-1$
    }
  }

  /** {@inheritDoc} */
  @Override
  public Header header() throws IOException, IllegalStateException {
    final Element c;
    final Header h;

    c = this.m_current;
    h = c.headerCreate((Document) (c));
    c.headerBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected Body bodyCreate(final Document owner) throws IOException {
    return new Body(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void bodyBegin(final Body h) throws IOException,
      IllegalStateException {
    final Logger log;

    log = this.m_owner.getLogger();
    if ((log != null) && (log.isLoggable(Level.FINE))) {
      log.fine("Document body generation begins: " + this.toString());//$NON-NLS-1$
    }

    if (this.m_state != Document.STATE_AFTER_HEADER) {
      throw new IllegalStateException(//
          "Document body must follow document header."); //$NON-NLS-1$
    }
    this.m_state = Document.STATE_IN_BODY;
  }

  /** {@inheritDoc} */
  @Override
  protected void bodyEnd(final Body h) throws IOException,
      IllegalStateException {
    final Logger log;

    if (this.m_state != Document.STATE_IN_BODY) {
      throw new IllegalStateException(//
          "Document body can only end after it has begun."); //$NON-NLS-1$
    }
    this.m_state = Document.STATE_AFTER_BODY;

    this.end(h);

    log = this.m_owner.getLogger();
    if ((log != null) && (log.isLoggable(Level.FINE))) {
      log.fine("Document body generation finished: " + this.toString());//$NON-NLS-1$
    }
  }

  /** {@inheritDoc} */
  @Override
  public Body body() throws IOException, IllegalStateException {
    final Element c;
    final Body h;

    c = this.m_current;
    h = c.bodyCreate((Document) (c));
    c.bodyBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.documentEnd(this);
  }

  /** {@inheritDoc} */
  @Override
  public final void close() throws IOException {
    if (this.checkDead(Document.STATE_AFTER_BODY)) {
      if (this.m_current != this) {
        throw new IllegalStateException(//
            "Current element is not document element."); //$NON-NLS-1$
      }
      if (this.m_sectionCounter <= 0) {
        throw new IllegalStateException(//
            "Document must contain at least one section."); //$NON-NLS-1$
      }
      this.m_current = null;
      this.doClose();
    }
  }

  /** {@inheritDoc} */
  @Override
  protected Authors authorsCreate(final Header owner,
      final BibAuthors authors) throws IOException {
    return new Authors(owner, authors);
  }

  /** {@inheritDoc} */
  @Override
  protected void authorsBegin(final Authors h) throws IOException,
      IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void authorsEnd(final Authors h) throws IOException,
      IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final void authors(final BibAuthors authors) throws IOException,
      IllegalStateException {
    final Element c;

    c = this.m_current;

    try (final Authors h = c.authorsCreate(((Header) (c)), authors)) {
      c.authorsBegin(h);
      this.m_current = h;
    }
  }

  /** {@inheritDoc} */
  @Override
  protected Title titleCreate(final Header owner) throws IOException {
    return new Title(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void titleBegin(final Title h) throws IOException,
      IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void titleEnd(final Title h) throws IOException,
      IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final Title title() throws IOException, IllegalStateException {
    final Element c;
    final Title h;

    c = this.m_current;
    h = c.titleCreate((Header) (c));
    c.titleBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected Summary summaryCreate(final Header owner) throws IOException {
    return new Summary(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void summaryBegin(final Summary h) throws IOException,
      IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void summaryEnd(final Summary h) throws IOException,
      IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final Summary summary() throws IOException, IllegalStateException {
    final Element c;
    final Summary h;

    c = this.m_current;
    h = c.summaryCreate((Header) (c));
    c.summaryBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected Section sectionCreate(final Element owner, final Label label)
      throws IOException {
    return new Section(owner, label);
  }

  /** {@inheritDoc} */
  @Override
  protected void sectionBegin(final Section h) throws IOException,
      IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void sectionEnd(final Section h) throws IOException,
      IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final Section section(final Label label) throws IOException,
      IllegalStateException {
    final Element c;
    final Section h;

    c = this.m_current;
    h = c.sectionCreate(c, label);
    c.sectionBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected SectionTitle sectionTitleCreate(final Section owner)
      throws IOException {
    return new SectionTitle(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void sectionTitleBegin(final SectionTitle h)
      throws IOException, IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void sectionTitleEnd(final SectionTitle h) throws IOException,
      IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final SectionTitle sectionTitle() throws IOException,
      IllegalStateException {
    final Element c;
    final SectionTitle h;

    c = this.m_current;
    h = c.sectionTitleCreate((Section) c);
    c.sectionTitleBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected SectionBody sectionBodyCreate(final Section owner)
      throws IOException {
    return new SectionBody(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void sectionBodyBegin(final SectionBody h) throws IOException,
      IllegalStateException {//
  }

  /** {@inheritDoc} */
  @Override
  protected void sectionBodyEnd(final SectionBody h) throws IOException,
      IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final SectionBody sectionBody() throws IOException,
      IllegalStateException {
    final Element c;
    final SectionBody h;

    c = this.m_current;
    h = c.sectionBodyCreate((Section) c);
    c.sectionBodyBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected Figure figureCreate(final SectionBody owner,
      final Label label, final boolean colspan) throws IOException {
    return new Figure(owner, label, colspan);
  }

  /** {@inheritDoc} */
  @Override
  protected void figureBegin(final Figure h) throws IOException,
      IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void figureEnd(final Figure h) throws IOException,
      IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final Figure figure(final Label label, final boolean colspan)
      throws IOException, IllegalStateException {
    final Element c;
    final Figure h;

    c = this.m_current;
    h = c.figureCreate(((SectionBody) c), label, colspan);
    c.figureBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected FigureCaption figureCaptionCreate(final Figure owner)
      throws IOException {
    return new FigureCaption(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void figureCaptionBegin(final FigureCaption h)
      throws IOException, IllegalStateException {//
  }

  /** {@inheritDoc} */
  @Override
  protected void figureCaptionEnd(final FigureCaption h)
      throws IOException, IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final FigureCaption figureCaption() throws IOException,
      IllegalStateException {
    final Element c;
    final FigureCaption h;

    c = this.m_current;
    h = c.figureCaptionCreate(((Figure) c));
    c.figureCaptionBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected FigureBody figureBodyCreate(final Element owner,
      final URI relativeNameBase, final Dimension2D sizeInMM)
      throws IOException, URISyntaxException {
    return new FigureBody(owner, relativeNameBase, sizeInMM);
  }

  /** {@inheritDoc} */
  @Override
  protected void figureBodyBegin(final FigureBody h) throws IOException,
      IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void figureBodyEnd(final FigureBody h) throws IOException,
      IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final FigureBody figureBody(final URI relativeNameBase,
      final Dimension2D sizeInMM) throws IOException,
      IllegalStateException, URISyntaxException {

    final Element c;
    final FigureBody h;

    c = this.m_current;
    h = c.figureBodyCreate(c, relativeNameBase, sizeInMM);
    c.figureBodyBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected Graphic graphicCreate(final FigureBody owner)
      throws IOException {
    return this.m_owner.graphicCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void graphicBegin(final Graphic h) throws IOException,
      IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void graphicEnd(final Graphic h) throws IOException,
      IllegalStateException {//
  }

  /** {@inheritDoc} */
  @Override
  final Graphic graphic() throws IOException, IllegalStateException {
    final Element c;
    final Graphic h;

    c = this.m_current;
    h = c.graphicCreate(((FigureBody) c));
    c.graphicBegin(h);
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected FigureSeries figureSeriesCreate(final SectionBody owner,
      final Label label, final int cols, final int rows,
      final boolean colspan) throws IOException {
    return new FigureSeries(owner, label, cols, rows, colspan);
  }

  /** {@inheritDoc} */
  @Override
  protected void figureSeriesBegin(final FigureSeries h)
      throws IOException, IllegalStateException {//
  }

  /** {@inheritDoc} */
  @Override
  protected void figureSeriesEnd(final FigureSeries h) throws IOException,
      IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  protected FigureSeries figureSeries(final Label label,
      final Dimension2D subFigureSizeInMM, final String captionTemplate,
      final String subCaptionTemplate, final boolean colspan)
      throws IOException, IllegalStateException {
    final int x1, x2;
    final double docW, figW;
    final DocumentDimensions dim;

    dim = this.getDimensions();
    docW = (colspan ? dim.getPageWidthMM() : dim.getColumnWidthMM());
    figW = subFigureSizeInMM.getWidth();
    x1 = Math.max(1, ((int) (docW / figW)));
    x2 = Math
        .max(1, ((int) (((docW * (1d - (0.01d * (x1 - 1))))) / figW)));

    return this.figureSeries(label, x2, Integer.MAX_VALUE, colspan);
  }

  /** {@inheritDoc} */
  @Override
  protected final FigureSeries figureSeries(final Label label,
      final int cols, final int rows, final boolean colspan)
      throws IOException, IllegalStateException {

    final Element c;
    final FigureSeries h;

    c = this.m_current;
    h = c.figureSeriesCreate(((SectionBody) c),//
        ((label == null) ? Label.AUTO_LABEL : label), cols, rows, colspan);
    c.figureSeriesBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected FigureSeriesCaption figureSeriesCaptionCreate(
      final FigureSeries owner) throws IOException {
    return new FigureSeriesCaption(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void figureSeriesCaptionBegin(final FigureSeriesCaption h)
      throws IOException, IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void figureSeriesCaptionEnd(final FigureSeriesCaption h)
      throws IOException, IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final FigureSeriesCaption figureSeriesCaption() throws IOException,
      IllegalStateException {

    final Element c;
    final FigureSeriesCaption h;

    c = this.m_current;
    h = c.figureSeriesCaptionCreate(((FigureSeries) c));
    c.figureSeriesCaptionBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected FigureSeriesPage figureSeriesPageCreate(
      final FigureSeries owner) throws IOException {
    return new FigureSeriesPage(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void figureSeriesPageBegin(final FigureSeriesPage h)
      throws IOException, IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void figureSeriesPageEnd(final FigureSeriesPage h)
      throws IOException, IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  protected SubFigure subFigureCreate(final FigureSeriesPage owner,
      final Label label, final int row, final int col) throws IOException {
    return new SubFigure(owner, label, row, col);
  }

  /** {@inheritDoc} */
  @Override
  protected void subFigureBegin(final SubFigure h) throws IOException,
      IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void subFigureEnd(final SubFigure h) throws IOException,
      IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final SubFigure subFigure(final Label label) throws IOException {
    final Element cur;
    final FigureSeries fig;
    FigureSeriesPage page;
    SubFigure sfig;

    cur = this.m_current;

    makePage: {
      if (cur instanceof FigureSeriesPage) {
        checkPage: {
          fig = ((FigureSeries) (cur.m_owner));
          page = ((FigureSeriesPage) cur);

          if ((++page.m_col) > fig.m_cols) {
            page.m_col = 1;
            if ((++page.m_row) > fig.m_rows) {
              // fig.figureSeriesPageEnd(page);
              page.close();
              break checkPage;
            }
          }
          break makePage;
        }
      } else {
        fig = ((FigureSeries) (this.m_current));
      }

      page = fig.figureSeriesPageCreate(fig);
      fig.figureSeriesPageBegin(page);
      this.m_current = page;
    }

    sfig = page.subFigureCreate(page, label, page.m_row, page.m_col);
    page.subFigureBegin(sfig);
    this.m_current = sfig;
    return sfig;
  }

  /** {@inheritDoc} */
  @Override
  protected SubFigureCaption subFigureCaptionCreate(final SubFigure owner)
      throws IOException {
    return new SubFigureCaption(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void subFigureCaptionBegin(final SubFigureCaption h)
      throws IOException, IllegalStateException {//
  }

  /** {@inheritDoc} */
  @Override
  protected void subFigureCaptionEnd(final SubFigureCaption h)
      throws IOException, IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final SubFigureCaption subFigureCaption() throws IOException,
      IllegalStateException {
    final Element c;
    final SubFigureCaption h;

    c = this.m_current;
    h = c.subFigureCaptionCreate(((SubFigure) c));
    c.subFigureCaptionBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected Itemization itemizationCreate(final AbstractTextBlock owner)
      throws IOException {
    return new Itemization(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void itemizationBegin(final Itemization h) throws IOException,
      IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void itemizationEnd(final Itemization h) throws IOException,
      IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final Itemization itemization() throws IOException,
      IllegalStateException {
    final Element c;
    final Itemization h;

    c = this.m_current;
    h = c.itemizationCreate(((AbstractTextBlock) c));
    c.itemizationBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected ItemizationItem itemizationItemCreate(final Itemization owner)
      throws IOException {
    return new ItemizationItem(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void itemizationItemBegin(final ItemizationItem h)
      throws IOException, IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void itemizationItemEnd(final ItemizationItem h)
      throws IOException, IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final ItemizationItem itemizationItem() throws IOException,
      IllegalStateException {
    final Element c;
    final ItemizationItem h;

    c = this.m_current;
    h = c.itemizationItemCreate(((Itemization) c));
    c.itemizationItemBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected Enumeration enumerationCreate(final AbstractTextBlock owner)
      throws IOException {
    return new Enumeration(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void enumerationBegin(final Enumeration h) throws IOException,
      IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void enumerationEnd(final Enumeration h) throws IOException,
      IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final Enumeration enumeration() throws IOException,
      IllegalStateException {
    final Element c;
    final Enumeration h;

    c = this.m_current;
    h = c.enumerationCreate(((AbstractTextBlock) c));
    c.enumerationBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected EnumerationItem enumerationItemCreate(final Enumeration owner)
      throws IOException {
    return new EnumerationItem(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void enumerationItemBegin(final EnumerationItem h)
      throws IOException, IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void enumerationItemEnd(final EnumerationItem h)
      throws IOException, IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final EnumerationItem enumerationItem() throws IOException,
      IllegalStateException {
    final Element c;
    final EnumerationItem h;

    c = this.m_current;
    h = c.enumerationItemCreate(((Enumeration) c));
    c.enumerationItemBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected Table tableCreate(final SectionBody owner, final Label label,
      final int rowsPerPage, final boolean colspan,
      final TableCellDef[] def) throws IOException {
    return new Table(owner, label, rowsPerPage, colspan, def);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableBegin(final Table h) throws IOException,
      IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void tableEnd(final Table h) throws IOException,
      IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  protected Table table(final Label label, final String captionDraft,
      final boolean colspan, final TableCellDef... def)
      throws IOException, IllegalStateException {
    return this.table(label, Integer.MAX_VALUE, colspan, def);
  }

  /** {@inheritDoc} */
  @Override
  protected final Table table(final Label label, final int rowsPerPage,
      final boolean colspan, final TableCellDef... def)
      throws IOException, IllegalStateException {
    final Element c;
    final Table h;

    c = this.m_current;
    h = c.tableCreate(((SectionBody) c), label, rowsPerPage, colspan, def);
    c.tableBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected TableCaption tableCaptionCreate(final Table owner)
      throws IOException {
    return new TableCaption(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableCaptionBegin(final TableCaption h)
      throws IOException, IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void tableCaptionEnd(final TableCaption h) throws IOException,
      IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final TableCaption tableCaption() throws IOException,
      IllegalStateException {

    final Element c;
    final TableCaption h;

    c = this.m_current;
    h = c.tableCaptionCreate(((Table) c));
    c.tableCaptionBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected TableHeader tableHeaderCreate(final Table owner)
      throws IOException {
    return new TableHeader(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableHeaderBegin(final TableHeader h) throws IOException,
      IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void tableHeaderEnd(final TableHeader h) throws IOException,
      IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final TableHeader tableHeader() throws IOException,
      IllegalStateException {
    final Element c;
    final TableHeader h;

    c = this.m_current;
    h = c.tableHeaderCreate(((Table) c));
    c.tableHeaderBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected TableFooter tableFooterCreate(final Table owner)
      throws IOException {
    return new TableFooter(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableFooterBegin(final TableFooter h) throws IOException,
      IllegalStateException {//
  }

  /** {@inheritDoc} */
  @Override
  protected void tableFooterEnd(final TableFooter h) throws IOException,
      IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final TableFooter tableFooter() throws IOException,
      IllegalStateException {
    final Element c;
    final TableFooter h;

    c = this.m_current;
    h = c.tableFooterCreate(((Table) c));
    c.tableFooterBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected TableBody tableBodyCreate(final Table owner)
      throws IOException {
    return new TableBody(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableBodyBegin(final TableBody h) throws IOException,
      IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void tableBodyEnd(final TableBody h) throws IOException,
      IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final TableBody tableBody() throws IOException, IllegalStateException {
    final Element c;
    final TableBody h;

    c = this.m_current;
    h = c.tableBodyCreate(((Table) c));
    c.tableBodyBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected TableHeaderRow tableHeaderRowCreate(final TableHeader owner)
      throws IOException {
    return new TableHeaderRow(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableHeaderRowBegin(final TableHeaderRow h)
      throws IOException, IllegalStateException {//
  }

  /** {@inheritDoc} */
  @Override
  protected void tableHeaderRowEnd(final TableHeaderRow h)
      throws IOException, IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final TableHeaderRow tableHeaderRow() throws IOException,
      IllegalStateException {

    final Element c;
    final TableHeaderRow h;

    c = this.m_current;
    h = c.tableHeaderRowCreate(((TableHeader) c));
    c.tableHeaderRowBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected TableHeaderCell tableHeaderCellCreate(
      final TableHeaderRow owner) throws IOException {
    return new TableHeaderCell(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableHeaderCellBegin(final TableHeaderCell h)
      throws IOException, IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void tableHeaderCellEnd(final TableHeaderCell h)
      throws IOException, IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final TableHeaderCell tableHeaderCell(final int cols, final int rows,
      final TableCellDef... def) throws IOException, IllegalStateException {
    final Element c;
    final TableHeaderCell h;

    c = this.m_current;
    h = c.tableHeaderCellCreate(((TableHeaderRow) c));
    ((Table) (c.m_owner.m_owner)).beginCell(h, def, cols, rows);
    c.tableHeaderCellBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected TableFooterRow tableFooterRowCreate(final TableFooter owner)
      throws IOException {
    return new TableFooterRow(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableFooterRowBegin(final TableFooterRow h)
      throws IOException, IllegalStateException {//
  }

  /** {@inheritDoc} */
  @Override
  protected void tableFooterRowEnd(final TableFooterRow h)
      throws IOException, IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final TableFooterRow tableFooterRow() throws IOException,
      IllegalStateException {

    final Element c;
    final TableFooterRow h;

    c = this.m_current;
    h = c.tableFooterRowCreate(((TableFooter) c));
    c.tableFooterRowBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected TableFooterCell tableFooterCellCreate(
      final TableFooterRow owner) throws IOException {
    return new TableFooterCell(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableFooterCellBegin(final TableFooterCell h)
      throws IOException, IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void tableFooterCellEnd(final TableFooterCell h)
      throws IOException, IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final TableFooterCell tableFooterCell(final int cols, final int rows,
      final TableCellDef... def) throws IOException, IllegalStateException {
    final Element c;
    final TableFooterCell h;

    c = this.m_current;
    h = c.tableFooterCellCreate(((TableFooterRow) c));
    ((Table) (c.m_owner.m_owner)).beginCell(h, def, cols, rows);
    c.tableFooterCellBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected TablePage tablePageCreate(final TableBody owner)
      throws IOException {
    return new TablePage(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void tablePageBegin(final TablePage h) throws IOException,
      IllegalStateException {//
  }

  /** {@inheritDoc} */
  @Override
  protected void tablePageEnd(final TablePage h) throws IOException,
      IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  protected TableBodyRow tableBodyRowCreate(final TablePage owner)
      throws IOException {
    return new TableBodyRow(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableBodyRowBegin(final TableBodyRow h)
      throws IOException, IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void tableBodyRowEnd(final TableBodyRow h) throws IOException,
      IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final TableBodyRow tableBodyRow() throws IOException,
      IllegalStateException {
    final Element c;
    final TableBodyRow h;
    final Table tbl;
    final TableBody body;
    TablePage page;

    c = this.m_current;

    makePage: {
      if (c instanceof TablePage) {
        page = ((TablePage) c);
        body = ((TableBody) (page.m_owner));
        tbl = ((Table) (body.m_owner));
        if (tbl.beginRow()) {
          page.close();
        } else {
          break makePage;
        }
      } else {
        body = ((TableBody) (c));
        tbl = ((Table) (body.m_owner));
        tbl.beginRow();
      }

      page = body.tablePageCreate(body);
      body.tablePageBegin(page);
      this.m_current = page;
    }

    h = page.tableBodyRowCreate(page);
    page.tableBodyRowBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected TableBodyCell tableBodyCellCreate(final TableBodyRow owner)
      throws IOException {
    return new TableBodyCell(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableBodyCellBegin(final TableBodyCell h)
      throws IOException, IllegalStateException {//
  }

  /** {@inheritDoc} */
  @Override
  protected void tableBodyCellEnd(final TableBodyCell h)
      throws IOException, IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  protected TableBodyCell tableBodyCell(final int cols, final int rows,
      final TableCellDef... def) throws IOException, IllegalStateException {
    final Element c;
    final TableBodyCell h;

    c = this.m_current;
    h = c.tableBodyCellCreate(((TableBodyRow) c));
    ((Table) (c.m_owner.m_owner.m_owner)).beginCell(h, def, cols, rows);
    c.tableBodyCellBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected void tableHorizontalLine() throws IOException,
      IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected Subscript subscriptCreate(final AbstractText owner)
      throws IOException {
    return new Subscript(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void subscriptBegin(final Subscript h) throws IOException,
      IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void subscriptEnd(final Subscript h) throws IOException,
      IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final Subscript subscript() throws IOException, IllegalStateException {
    final Element c;
    final Subscript h;

    c = this.m_current;
    h = c.subscriptCreate(((AbstractText) c));
    c.subscriptBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected Superscript superscriptCreate(final AbstractText owner)
      throws IOException {
    return new Superscript(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void superscriptBegin(final Superscript h) throws IOException,
      IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void superscriptEnd(final Superscript h) throws IOException,
      IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final Superscript superscript() throws IOException,
      IllegalStateException {
    final Element c;
    final Superscript h;

    c = this.m_current;
    h = c.superscriptCreate(((AbstractText) c));
    c.superscriptBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected Emphasize emphasizeCreate(final AbstractText owner)
      throws IOException {
    return new Emphasize(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void emphasizeBegin(final Emphasize h) throws IOException,
      IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void emphasizeEnd(final Emphasize h) throws IOException,
      IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final Emphasize emphasize() throws IOException, IllegalStateException {
    final Element c;
    final Emphasize h;

    c = this.m_current;
    h = c.emphasizeCreate(((AbstractText) c));
    c.emphasizeBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected InQuotes inQuotesCreate(final AbstractText owner,
      final EQuotes quotes) throws IOException {
    return new InQuotes(owner, quotes);
  }

  /** {@inheritDoc} */
  @Override
  protected void inQuotesBegin(final InQuotes h) throws IOException,
      IllegalStateException {
    this.writeChar(h.getQuotes().getBegin());
  }

  /** {@inheritDoc} */
  @Override
  protected void inQuotesEnd(final InQuotes h) throws IOException,
      IllegalStateException {
    try {
      this.writeChar(h.getQuotes().getEnd());
    } finally {
      this.end(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  final InQuotes inQuotes(final EQuotes quotes) throws IOException,
      IllegalStateException {
    final Element c;
    final InQuotes h;

    c = this.m_current;
    h = c.inQuotesCreate(((AbstractText) c), quotes);
    c.inQuotesBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected Equation equationCreate(final SectionBody owner,
      final Label label) throws IOException {
    return new Equation(owner, label);
  }

  /** {@inheritDoc} */
  @Override
  protected void equationBegin(final Equation h) throws IOException,
      IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void equationEnd(final Equation h) throws IOException,
      IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final Equation equation(final Label label) throws IOException,
      IllegalStateException {
    final Element c;
    final Equation h;

    c = this.m_current;
    h = c.equationCreate(((SectionBody) c), label);
    c.equationBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected EquationBody equationBodyCreate(final Equation owner)
      throws IOException {
    return new EquationBody(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void equationBodyBegin(final EquationBody h)
      throws IOException, IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void equationBodyEnd(final EquationBody h) throws IOException,
      IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  protected EquationBody equationBody() throws IOException,
      IllegalStateException {
    final Element c;
    final EquationBody h;

    c = this.m_current;
    h = c.equationBodyCreate(((Equation) c));
    c.equationBodyBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected InlineMath inlineMathCreate(final AbstractTextComplex owner)
      throws IOException {
    return new InlineMath(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void inlineMathBegin(final InlineMath h) throws IOException,
      IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void inlineMathEnd(final InlineMath h) throws IOException,
      IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final InlineMath inlineMath() throws IOException, IllegalStateException {
    final Element c;
    final InlineMath h;

    c = this.m_current;
    h = c.inlineMathCreate(((AbstractTextComplex) c));
    c.inlineMathBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected NormalText normalTextCreate(final AbstractMathElement owner)
      throws IOException {
    return new NormalText(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void normalTextBegin(final NormalText h) throws IOException,
      IllegalStateException {//
  }

  /** {@inheritDoc} */
  @Override
  protected void normalTextEnd(final NormalText h) throws IOException,
      IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final NormalText normalText() throws IOException, IllegalStateException {
    final Element c;
    final NormalText h;

    c = this.m_current;
    h = c.normalTextCreate(((AbstractMathElement) c));
    c.normalTextBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected MathSubscript mathSubscriptCreate(
      final AbstractMathElement owner) throws IOException {
    return new MathSubscript(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void mathSubscriptBegin(final MathSubscript h)
      throws IOException, IllegalStateException {//
  }

  /** {@inheritDoc} */
  @Override
  protected void mathSubscriptEnd(final MathSubscript h)
      throws IOException, IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final MathSubscript mathSubscript() throws IOException,
      IllegalStateException {
    final Element c;
    final MathSubscript h;

    c = this.m_current;
    h = c.mathSubscriptCreate(((AbstractMathElement) c));
    c.mathSubscriptBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected MathSuperscript mathSuperscriptCreate(
      final AbstractMathElement owner) throws IOException {
    return new MathSuperscript(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void mathSuperscriptBegin(final MathSuperscript h)
      throws IOException, IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void mathSuperscriptEnd(final MathSuperscript h)
      throws IOException, IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final MathSuperscript mathSuperscript() throws IOException,
      IllegalStateException {
    final Element c;
    final MathSuperscript h;

    c = this.m_current;
    h = c.mathSuperscriptCreate(((AbstractMathElement) c));
    c.mathSuperscriptBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected MathName mathNameCreate(final AbstractMathElement owner,
      final EMathName type) throws IOException {
    return new MathName(owner, type);
  }

  /** {@inheritDoc} */
  @Override
  protected void mathNameBegin(final MathName h) throws IOException,
      IllegalStateException {//
  }

  /** {@inheritDoc} */
  @Override
  protected void mathNameEnd(final MathName h) throws IOException,
      IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final MathName mathName(final EMathName type) throws IOException,
      IllegalStateException {
    final Element c;
    final MathName h;

    c = this.m_current;
    h = c.mathNameCreate(((AbstractMathElement) c), type);
    c.mathNameBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected MathOp mathOpCreate(final AbstractMathElement owner,
      final EMathOp type) throws IOException {
    return new MathOp(owner, type);
  }

  /** {@inheritDoc} */
  @Override
  protected void mathOpBegin(final MathOp h) throws IOException,
      IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void mathOpEnd(final MathOp h) throws IOException,
      IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final MathOp mathOp(final EMathOp type) throws IOException,
      IllegalStateException {
    final Element c;
    final MathOp h;

    c = this.m_current;
    h = c.mathOpCreate(((AbstractMathElement) c), type);
    c.mathOpBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected MathOpParam mathOpParamCreate(final MathOp owner)
      throws IOException {
    return new MathOpParam(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void mathOpParamBegin(final MathOpParam h) throws IOException,
      IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void mathOpParamEnd(final MathOpParam h) throws IOException,
      IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final MathOpParam mathOpParam() throws IOException,
      IllegalStateException {
    final Element c;
    final MathOpParam h;

    c = this.m_current;
    h = c.mathOpParamCreate(((MathOp) c));
    c.mathOpParamBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected Macro macroCreate(final Header owner,
      final MacroDescriptor desc) throws IOException {
    return new Macro(owner, desc);
  }

  /** {@inheritDoc} */
  @Override
  protected void macroBegin(final Macro h) throws IOException,
      IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void macroEnd(final Macro h) throws IOException,
      IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final boolean isMacroDefined(final MacroDescriptor desc) {
    return this.m_macros.contains(desc);
  }

  /** {@inheritDoc} */
  @Override
  final Macro macro(final MacroDescriptor desc) throws IOException,
      IllegalStateException {
    final Element c;
    final Macro h;

    if (this.m_macros.contains(desc)) {
      throw new IllegalArgumentException("Macro '" + desc.name() + //$NON-NLS-1$
          "' already defined!");//$NON-NLS-1$
    }
    this.m_macros.add(desc);

    c = this.m_current;
    h = c.macroCreate(((Header) c), desc);
    c.macroBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected void macroParameter(final int id) throws IOException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected MacroInvocation macroInvocationCreate(
      final AbstractInlineElement owner, final MacroDescriptor desc)
      throws IOException {
    return new MacroInvocation(owner, desc);
  }

  /** {@inheritDoc} */
  @Override
  protected void macroInvocationBegin(final MacroInvocation h)
      throws IOException, IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void macroInvocationEnd(final MacroInvocation h)
      throws IOException, IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final MacroInvocation macroInvocation(final MacroDescriptor desc)
      throws IOException, IllegalStateException {
    final Element c;
    final MacroInvocation h;

    if (!(this.m_macros.contains(desc))) {
      throw new IllegalArgumentException("Macro '" + desc.name() + //$NON-NLS-1$
          "' is not defined!");//$NON-NLS-1$
    }

    c = this.m_current;
    h = c.macroInvocationCreate(((AbstractInlineElement) c), desc);
    c.macroInvocationBegin(h);
    this.m_current = h;
    return h;
  }

  /** {@inheritDoc} */
  @Override
  protected MacroParameter macroParameterCreate(final MacroInvocation owner)
      throws IOException {
    return new MacroParameter(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void macroParameterBegin(final MacroParameter h)
      throws IOException, IllegalStateException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected void macroParameterEnd(final MacroParameter h)
      throws IOException, IllegalStateException {
    this.end(h);
  }

  /** {@inheritDoc} */
  @Override
  final MacroParameter macroParameter() throws IOException,
      IllegalStateException {
    final Element c;
    final MacroParameter h;

    c = this.m_current;
    h = c.macroParameterCreate(((MacroInvocation) c));
    c.macroParameterBegin(h);
    this.m_current = h;
    return h;
  }

  /**
   * Create a new bibliographic reference holder
   * 
   * @param index
   *          the index
   * @param id
   *          the id
   * @param record
   *          the record
   * @return the reference holder
   */
  protected BibReference referenceCreate(final int index, final String id,
      final BibRecord record) {
    return new BibReference(index, id, record);
  }

  /**
   * Reference a set of bibliographic entries
   * 
   * @param mode
   *          the citation mode
   * @param references
   *          the references
   * @throws IOException
   *           if io fails
   */
  @SuppressWarnings("unused")
  protected void cite(final ECitationMode mode,
      final BibReference... references) throws IOException {
    //
  }

  /**
   * Get the list of all bibliographic references made so far
   * 
   * @return the references
   */
  protected BibReference[] getReferences() {
    return this.m_refLst.toArray(new BibReference[this.m_refLst.size()]);
  }

  /** {@inheritDoc} */
  @Override
  final void cite(final ECitationMode mode, final BibRecord... records)
      throws IOException {
    final int len;
    final BibReference[] lst;
    BibReference k;
    int i;

    if (records == null) {
      throw new IllegalArgumentException(//
          "Records must not be null."); //$NON-NLS-1$
    }

    len = records.length;
    if (len <= 0) {
      throw new IllegalArgumentException(//
          "There must be at least one record in a reference."); //$NON-NLS-1$      
    }

    if (mode == null) {
      throw new IllegalArgumentException(//
          "Citation mode must not be null."); //$NON-NLS-1$
    }

    lst = new BibReference[len];
    i = 0;
    for (final BibRecord x : records) {
      k = this.m_refs.get(x);
      if (k == null) {
        k = this.referenceCreate(//
            (this.m_refs.size() + 1),//
            this.autoName(), x);
        this.m_refs.put(x, k);
        this.m_refLst.add(k);
      }
      lst[i++] = k;
    }

    this.cite(mode, lst);
  }

  /**
   * Get an object holding information about the document dimensions
   * 
   * @return an object holding information about the document dimensions
   */
  public DocumentDimensions getDimensions() {
    return DocumentDimensions.DEFAULT;
  }
}
