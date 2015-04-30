package org.logisticPlanning.utils.document.impl.utils;

import java.io.IOException;

import org.logisticPlanning.utils.document.spec.Emphasize;
import org.logisticPlanning.utils.document.spec.Macro;
import org.logisticPlanning.utils.document.spec.MacroDefinition;

/**
 * the default macro for shortcuts
 */
public class MacroShortcut extends MacroDefinition {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create
   * 
   * @param name
   *          the macro's name
   * @param what
   *          the text that will be written
   */
  public MacroShortcut(final String name, final String what) {
    super(name, 0, what);
  }

  /** {@inheritDoc} */
  @Override
  protected void doDefine(final Macro macro) throws IOException {
    try (Emphasize em = macro.emphasize()) {
      em.write(this.getPlaceholder());
    }
  }
}
