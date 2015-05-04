package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The document macro element.
 */
public class Macro extends AbstractTextComplex {

  /** the macro description */
  private final MacroDescriptor m_desc;

  /**
   * create the document element
   *
   * @param owner
   *          the owning element
   * @param desc
   *          the macro description
   * @throws IOException
   *           if io fails
   */
  protected Macro(final Header owner, final MacroDescriptor desc)
      throws IOException {
    super(owner);

    if (desc == null) {
      throw new IllegalArgumentException(//
          "Macro descriptor cannot be null."); //$NON-NLS-1$
    }
    this.m_desc = desc;
  }

  /** {@inheritDoc} */
  @Override
  public Header getOwner() {
    return ((Header) (this.m_owner));
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.macroEnd(this);
  }

  /** {@inheritDoc} */
  @Override
  public final void close() throws IOException {
    if (this.checkDead(Element.STATE_NOTHING)) {
      this.doClose();
    }
  }

  /**
   * Get the macro descriptor
   *
   * @return the macro descriptor
   */
  public final MacroDescriptor getDescriptor() {
    return this.m_desc;
  }

  /** {@inheritDoc} */
  @Override
  public void macroParameter(final int id) throws IOException {
    if ((id <= 0) || (id > this.m_desc.m_paramCount)) {
      throw new IllegalArgumentException("Macro '" + //$NON-NLS-1$
          this.m_desc.name() + "' only has " + //$NON-NLS-1$
          this.m_desc.m_paramCount + " parameters, but parameter " + //$NON-NLS-1$
          id + " was accessed.");//$NON-NLS-1$
    }
    super.macroParameter(id);
  }
}
