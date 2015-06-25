package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * a macro definition is a macro descriptor that can define itself
 */
public abstract class MacroDefinition extends MacroDescriptor {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * Create a new macro descriptor.
   *
   * @param name
   *          the macro's name
   * @param paramCount
   *          the parameter counter
   * @param placeHolder
   *          the macro's placeholder
   */
  public MacroDefinition(final String name, final int paramCount,
      final String placeHolder) {
    super(name, paramCount, placeHolder);
  }

  /**
   * Define this macro
   *
   * @param macro
   *          the macro
   * @throws IOException
   *           if io fails
   */
  protected abstract void doDefine(final Macro macro) throws IOException;

  /**
   * Define all required macros
   *
   * @param header
   *          the header
   * @throws IOException
   *           if io fails
   */
  protected void defineRequirements(final Header header)
      throws IOException {
    //
  }

  /**
   * Define the macro, if it has not already been defined
   *
   * @param header
   *          the header into which it should be defined
   * @throws IOException
   *           the macro
   */
  public final void define(final Header header) throws IOException {
    if (header.isMacroDefined(this)) {
      return;
    }
    this.defineRequirements(header);
    try (Macro macro = header.macro(this)) {
      this.doDefine(macro);
    }
  }
}
