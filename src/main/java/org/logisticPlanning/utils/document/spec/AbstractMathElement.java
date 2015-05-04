package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The abstract base class for math elements.
 */
public abstract class AbstractMathElement extends AbstractInlineElement {

  /**
   * create the document element
   *
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  AbstractMathElement(final Element owner) throws IOException {
    super(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected NormalText normalTextCreate(final AbstractMathElement owner)
      throws IOException {
    return super.normalTextCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void normalTextBegin(final NormalText h) throws IOException,
      IllegalStateException {
    super.normalTextBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void normalTextEnd(final NormalText h) throws IOException,
      IllegalStateException {
    super.normalTextEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public NormalText normalText() throws IOException, IllegalStateException {
    return super.normalText();
  }

  /** {@inheritDoc} */
  @Override
  protected MathSubscript mathSubscriptCreate(
      final AbstractMathElement owner) throws IOException {
    return super.mathSubscriptCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void mathSubscriptBegin(final MathSubscript h)
      throws IOException, IllegalStateException {
    super.mathSubscriptBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void mathSubscriptEnd(final MathSubscript h)
      throws IOException, IllegalStateException {
    super.mathSubscriptEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public MathSubscript mathSubscript() throws IOException,
      IllegalStateException {
    return super.mathSubscript();
  }

  /** {@inheritDoc} */
  @Override
  protected MathSuperscript mathSuperscriptCreate(
      final AbstractMathElement owner) throws IOException {
    return super.mathSuperscriptCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void mathSuperscriptBegin(final MathSuperscript h)
      throws IOException, IllegalStateException {
    super.mathSuperscriptBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void mathSuperscriptEnd(final MathSuperscript h)
      throws IOException, IllegalStateException {
    super.mathSuperscriptEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public MathSuperscript mathSuperscript() throws IOException,
      IllegalStateException {
    return super.mathSuperscript();
  }

  /** {@inheritDoc} */
  @Override
  protected MathName mathNameCreate(final AbstractMathElement owner,
      final EMathName type) throws IOException {
    return super.mathNameCreate(owner, type);
  }

  /** {@inheritDoc} */
  @Override
  protected void mathNameBegin(final MathName h) throws IOException,
      IllegalStateException {
    super.mathNameBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void mathNameEnd(final MathName h) throws IOException,
      IllegalStateException {
    super.mathNameEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public MathName mathName(final EMathName type) throws IOException,
      IllegalStateException {
    return super.mathName(type);
  }

  /** {@inheritDoc} */
  @Override
  protected MathOp mathOpCreate(final AbstractMathElement owner,
      final EMathOp type) throws IOException {
    return super.mathOpCreate(owner, type);
  }

  /** {@inheritDoc} */
  @Override
  protected void mathOpBegin(final MathOp h) throws IOException,
      IllegalStateException {
    super.mathOpBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void mathOpEnd(final MathOp h) throws IOException,
      IllegalStateException {
    super.mathOpEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public MathOp mathOp(final EMathOp type) throws IOException,
      IllegalStateException {
    return super.mathOp(type);
  }
}
