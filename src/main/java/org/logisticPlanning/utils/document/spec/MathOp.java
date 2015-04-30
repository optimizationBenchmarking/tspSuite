package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The math operation.
 */
public class MathOp extends Element {

  /** in a param */
  private static final int STATE_IN_PARAM = (Element.STATE_NOTHING + 1);

  /** after a param */
  private static final int STATE_AFTER_PARAM = (MathOp.STATE_IN_PARAM + 1);

  /** the operation */
  private final EMathOp m_op;
  /** the current argument index */
  private int m_curArg;

  /**
   * create the text element
   * 
   * @param owner
   *          the owning element
   * @param op
   *          the math operator
   * @throws IOException
   *           if io fails
   */
  protected MathOp(final AbstractMathElement owner, final EMathOp op)
      throws IOException {
    super(owner);
    if (op == null) {
      throw new IllegalArgumentException(//
          "Math operator must not be null."); //$NON-NLS-1$
    }
    this.m_op = op;
  }

  /**
   * get the next argument index
   * 
   * @return the next argument index
   */
  final int nextArg() {
    int i;

    i = (++this.m_curArg);
    if (i > this.m_op.m_maxArgs) {
      throw new IllegalStateException("Operation '" + //$NON-NLS-1$
          this.m_op.m_op + "' can have at most " + i + //$NON-NLS-1$
          " arguments."); //$NON-NLS-1$
    }
    return i;
  }

  /**
   * Get the mathematical operator
   * 
   * @return the mathematical operator
   */
  public final EMathOp getOperator() {
    return this.m_op;
  }

  /** {@inheritDoc} */
  @Override
  public AbstractMathElement getOwner() {
    return ((AbstractMathElement) (this.m_owner));
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.mathOpEnd(this);
  }

  /** {@inheritDoc} */
  @Override
  public final void close() throws IOException {
    final int mi;

    mi = this.m_op.m_minArgs;
    if (this.checkDead((mi <= 0) ? Element.STATE_NOTHING
        : MathOp.STATE_AFTER_PARAM)) {

      if ((this.m_curArg > this.m_op.m_maxArgs) || (this.m_curArg < mi)) {
        throw new IllegalStateException("Operator '" + this.m_op.m_op + //$NON-NLS-1$
            "' requires between " + mi + //$NON-NLS-1$
            " and " + this.m_op.m_maxArgs + //$NON-NLS-1$
            " arguments, but only " + this.m_curArg + //$NON-NLS-1$
            " have been specified."); //$NON-NLS-1$
      }

      this.doClose();
    }
  }

  /** {@inheritDoc} */
  @Override
  protected MathOpParam mathOpParamCreate(final MathOp owner)
      throws IOException {
    return super.mathOpParamCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void mathOpParamBegin(final MathOpParam h) throws IOException,
      IllegalStateException {

    if ((this.m_state != Element.STATE_NOTHING)
        && (this.m_state != MathOp.STATE_AFTER_PARAM)) {
      throw new IllegalStateException(//
          "Math operation parameter not allowed here."); //$NON-NLS-1$
    }
    this.m_state = MathOp.STATE_IN_PARAM;

    super.mathOpParamBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void mathOpParamEnd(final MathOpParam h) throws IOException,
      IllegalStateException {

    if (this.m_state != MathOp.STATE_IN_PARAM) {
      throw new IllegalStateException(//
          "Math operation parameter must end only after it has begun."); //$NON-NLS-1$
    }
    this.m_state = MathOp.STATE_AFTER_PARAM;

    super.mathOpParamEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public MathOpParam mathOpParam() throws IOException,
      IllegalStateException {
    return super.mathOpParam();
  }
}
