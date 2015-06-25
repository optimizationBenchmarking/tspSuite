package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The mathematical name text.
 */
public class MathName extends AbstractTextPlain {

  /** the type */
  private final EMathName m_type;

  /**
   * create the text element
   *
   * @param owner
   *          the owning element
   * @param type
   *          the math name type
   * @throws IOException
   *           if io fails
   */
  protected MathName(final AbstractMathElement owner, final EMathName type)
      throws IOException {
    super(owner);

    if (type == null) {
      throw new IllegalArgumentException(//
          "Math name type must be specified."); //$NON-NLS-1$
    }
    this.m_type = type;
  }

  /**
   * Get the math name type
   *
   * @return the math name type
   */
  public final EMathName getType() {
    return this.m_type;
  }

  /** {@inheritDoc} */
  @Override
  public AbstractMathElement getOwner() {
    return ((AbstractMathElement) (this.m_owner));
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.mathNameEnd(this);
  }

  /** {@inheritDoc} */
  @Override
  public final void close() throws IOException {
    if (this.checkDead(Element.STATE_NOTHING)) {
      this.doClose();
    }
  }
}
