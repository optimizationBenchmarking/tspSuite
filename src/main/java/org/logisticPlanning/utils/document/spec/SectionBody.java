package org.logisticPlanning.utils.document.spec;

import java.awt.geom.Dimension2D;
import java.io.IOException;

/**
 * The section body element.
 */
public class SectionBody extends AbstractTextBlock {

  /**
   * create the document element
   *
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  protected SectionBody(final Element owner) throws IOException {
    super(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.sectionBodyEnd(this);
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
  public void writeLinebreak() throws IOException {
    super.writeLinebreak();
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
    super.sectionBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void sectionEnd(final Section h) throws IOException,
  IllegalStateException {
    super.sectionEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public Section section(final Label label) throws IOException,
  IllegalStateException {
    return super.section(label);
  }

  /** {@inheritDoc} */
  @Override
  protected Figure figureCreate(final SectionBody owner,
      final Label label, final boolean colspan) throws IOException {
    return super.figureCreate(owner, label, colspan);
  }

  /** {@inheritDoc} */
  @Override
  protected void figureBegin(final Figure h) throws IOException,
  IllegalStateException {
    super.figureBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void figureEnd(final Figure h) throws IOException,
  IllegalStateException {
    super.figureEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public Figure figure(final Label label, final boolean colspan)
      throws IOException, IllegalStateException {
    return super.figure(label, colspan);
  }

  /** {@inheritDoc} */
  @Override
  public Section getOwner() {
    return ((Section) (this.m_owner));
  }

  /** {@inheritDoc} */
  @Override
  protected FigureSeries figureSeriesCreate(final SectionBody owner,
      final Label label, final int cols, final int rows,
      final boolean colspan) throws IOException {
    return super.figureSeriesCreate(owner, label, cols, rows, colspan);
  }

  /** {@inheritDoc} */
  @Override
  protected void figureSeriesBegin(final FigureSeries h)
      throws IOException, IllegalStateException {
    super.figureSeriesBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void figureSeriesEnd(final FigureSeries h) throws IOException,
  IllegalStateException {
    super.figureSeriesEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public FigureSeries figureSeries(final Label label,
      final Dimension2D subFigureSizeInMM, final String captionTemplate,
      final String subCaptionTemplate, final boolean colspan)
          throws IOException, IllegalStateException {
    return super.figureSeries(label, subFigureSizeInMM, captionTemplate,
        subCaptionTemplate, colspan);
  }

  /** {@inheritDoc} */
  @Override
  public FigureSeries figureSeries(final Label label, final int cols,
      final int rows, final boolean colspan) throws IOException,
      IllegalStateException {
    return super.figureSeries(label, cols, rows, colspan);
  }

  /** {@inheritDoc} */
  @Override
  protected Table tableCreate(final SectionBody owner, final Label label,
      final int rowsPerPage, final boolean colspan,
      final TableCellDef[] def) throws IOException {
    return super.tableCreate(owner, label, rowsPerPage, colspan, def);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableBegin(final Table h) throws IOException,
  IllegalStateException {
    super.tableBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableEnd(final Table h) throws IOException,
  IllegalStateException {
    super.tableEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public Table table(final Label label, final String captionDraft,
      final boolean colspan, final TableCellDef... def)
          throws IOException, IllegalStateException {
    return super.table(label, captionDraft, colspan, def);
  }

  /** {@inheritDoc} */
  @Override
  public Table table(final Label label, final int rowsPerPage,
      final boolean colspan, final TableCellDef... def)
          throws IOException, IllegalStateException {
    return super.table(label, rowsPerPage, colspan, def);
  }

  /** {@inheritDoc} */
  @Override
  protected Equation equationCreate(final SectionBody owner,
      final Label label) throws IOException {
    return super.equationCreate(owner, label);
  }

  /** {@inheritDoc} */
  @Override
  protected void equationBegin(final Equation h) throws IOException,
  IllegalStateException {
    super.equationBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void equationEnd(final Equation h) throws IOException,
  IllegalStateException {
    super.equationEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public Equation equation(final Label label) throws IOException,
  IllegalStateException {
    return super.equation(label);
  }
}
