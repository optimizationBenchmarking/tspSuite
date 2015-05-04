package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The macro invocation element.
 */
public class MacroInvocation extends Element {

  /** in a param */
  private static final int STATE_IN_PARAM = (Element.STATE_NOTHING + 1);

  /** after a param */
  private static final int STATE_AFTER_PARAM = (MacroInvocation.STATE_IN_PARAM + 1);

  /** the macro description */
  private final MacroDescriptor m_desc;

  /** the parameter index */
  private int m_index;

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
  protected MacroInvocation(final AbstractInlineElement owner,
      final MacroDescriptor desc) throws IOException {
    super(owner);

    if (desc == null) {
      throw new IllegalArgumentException(//
          "Macro descriptor cannot be null."); //$NON-NLS-1$
    }
    this.m_desc = desc;
  }

  /**
   * get the next parameter index
   *
   * @return the next parameter index
   */
  final int nextParam() {
    final int i;

    i = (++this.m_index);
    if (i > this.m_desc.m_paramCount) {
      throw new IllegalStateException(//
          "Macro '" + this.m_desc.name() + //$NON-NLS-1$
              "' can only have up to "//$NON-NLS-1$
              + this.m_desc.m_paramCount + //
              " parameters."); //$NON-NLS-1$
    }

    return i;
  }

  /** {@inheritDoc} */
  @Override
  public AbstractInlineElement getOwner() {
    return ((AbstractInlineElement) (this.m_owner));
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.macroInvocationEnd(this);
  }

  /** {@inheritDoc} */
  @Override
  public final void close() throws IOException {
    final int i;
    i = this.m_desc.m_paramCount;
    if (this.checkDead((i > 0) ? MacroInvocation.STATE_AFTER_PARAM
        : Element.STATE_NOTHING)) {
      if (this.m_index != i) {
        throw new IllegalStateException("Macro '" + //$NON-NLS-1$
            this.m_desc.name() + "' must have exactly " + i + //$NON-NLS-1$
            " parameters, but has " + this.m_index);//$NON-NLS-1$
      }
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
  protected MacroParameter macroParameterCreate(final MacroInvocation owner)
      throws IOException {
    return super.macroParameterCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void macroParameterBegin(final MacroParameter h)
      throws IOException, IllegalStateException {

    if ((this.m_state != Element.STATE_NOTHING)
        && (this.m_state != MacroInvocation.STATE_AFTER_PARAM)) {
      throw new IllegalStateException(//
          "Macro parameter not allowed here."); //$NON-NLS-1$
    }
    this.m_state = MacroInvocation.STATE_IN_PARAM;

    super.macroParameterBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void macroParameterEnd(final MacroParameter h)
      throws IOException, IllegalStateException {

    if (this.m_state != MacroInvocation.STATE_IN_PARAM) {
      throw new IllegalStateException(//
          "Macro parameter must end only after it has begun."); //$NON-NLS-1$
    }
    this.m_state = MacroInvocation.STATE_AFTER_PARAM;
    super.macroParameterEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public MacroParameter macroParameter() throws IOException,
      IllegalStateException {
    return super.macroParameter();
  }

}
