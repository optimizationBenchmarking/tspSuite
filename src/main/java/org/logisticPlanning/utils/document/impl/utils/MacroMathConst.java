package org.logisticPlanning.utils.document.impl.utils;

import java.io.IOException;

import org.logisticPlanning.utils.document.spec.EMathName;
import org.logisticPlanning.utils.document.spec.InlineMath;
import org.logisticPlanning.utils.document.spec.Macro;
import org.logisticPlanning.utils.document.spec.MacroDefinition;
import org.logisticPlanning.utils.document.spec.MathName;

/**
 * the default macro for mathematical constants
 */
public class MacroMathConst extends MacroDefinition {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the type */
  private final EMathName m_type;

  /**
   * create
   *
   * @param name
   *          the macro's name
   * @param what
   *          the text that will be written
   * @param type
   *          the type
   */
  public MacroMathConst(final String name, final String what,
      final EMathName type) {
    super(name, 0, ((what != null) ? what : name));
    this.m_type = type;
  }

  /** {@inheritDoc} */
  @Override
  protected void doDefine(final Macro macro) throws IOException {
    try (InlineMath em = macro.inlineMath()) {
      try (MathName mn = em.mathName(this.m_type)) {
        mn.write(this.getPlaceholder());
      }
    }
  }
}
