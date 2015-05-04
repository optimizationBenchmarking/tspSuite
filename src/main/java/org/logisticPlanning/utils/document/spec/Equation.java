package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The equation element.
 */
public class Equation extends AbstractLabeledElement {
  /** in body */
  private static final int STATE_IN_BODY = (Element.STATE_NOTHING + 1);

  /** after body */
  private static final int STATE_AFTER_BODY = (Equation.STATE_IN_BODY + 1);

  /** the equation index */
  private final int m_index;

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
  protected Equation(final SectionBody owner, final Label label)
      throws IOException {
    super(owner, label);
    this.m_index = this.m_document.nextEquation();
  }

  /**
   * Get the equation index
   *
   * @return the equation index
   */
  public final int getEquationIndex() {
    return this.m_index;
  }

  /** {@inheritDoc} */
  @Override
  final ELabelType getLabelType() {
    return ELabelType.EQUATION;
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.equationEnd(this);
  }

  /** {@inheritDoc} */
  @Override
  public final void close() throws IOException {
    if (this.checkDead(Equation.STATE_AFTER_BODY)) {
      this.doClose();
    }
  }

  /** {@inheritDoc} */
  @Override
  protected EquationBody equationBodyCreate(final Equation owner)
      throws IOException {
    return super.equationBodyCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void equationBodyBegin(final EquationBody h)
      throws IOException, IllegalStateException {

    if (this.m_state != Element.STATE_NOTHING) {
      throw new IllegalStateException(//
          "Equation body must occur exactly once in equation."); //$NON-NLS-1$
    }
    this.m_state = Equation.STATE_IN_BODY;

    super.equationBodyBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void equationBodyEnd(final EquationBody h) throws IOException,
  IllegalStateException {

    if (this.m_state != Equation.STATE_IN_BODY) {
      throw new IllegalStateException(//
          "Equation body end after it has begun."); //$NON-NLS-1$
    }
    this.m_state = Equation.STATE_AFTER_BODY;

    super.equationBodyEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public EquationBody equationBody() throws IOException,
  IllegalStateException {
    return super.equationBody();
  }

  /** {@inheritDoc} */
  @Override
  public SectionBody getOwner() {
    return ((SectionBody) (this.m_owner));
  }
}
