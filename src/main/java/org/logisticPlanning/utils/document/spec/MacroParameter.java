package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The macro invocation parameter element.
 */
public class MacroParameter extends AbstractTextPlain {

  /** the index */
  private final int m_index;

  /**
   * create the document macro parameter element
   * 
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  protected MacroParameter(final MacroInvocation owner) throws IOException {
    super(owner);
    this.m_index = owner.nextParam();
  }

  /**
   * Get the macro parameter's index
   * 
   * @return the macro parameter's index
   */
  public final int getIndex() {
    return this.m_index;
  }

  /** {@inheritDoc} */
  @Override
  public MacroInvocation getOwner() {
    return ((MacroInvocation) (this.m_owner));
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.macroParameterEnd(this);
  }

  /** {@inheritDoc} */
  @Override
  public final void close() throws IOException {
    if (this.checkDead(Element.STATE_NOTHING)) {
      this.doClose();
    }
  }

}
