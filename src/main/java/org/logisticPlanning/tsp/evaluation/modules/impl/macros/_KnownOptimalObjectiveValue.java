package org.logisticPlanning.tsp.evaluation.modules.impl.macros;

import java.io.IOException;

import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.utils.document.spec.EMathName;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.InlineMath;
import org.logisticPlanning.utils.document.spec.Macro;
import org.logisticPlanning.utils.document.spec.MacroDefinition;
import org.logisticPlanning.utils.document.spec.MathName;
import org.logisticPlanning.utils.document.spec.MathSuperscript;

/**
 * the objective value of the global optimum
 */
final class _KnownOptimalObjectiveValue extends MacroDefinition {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the optimality star */
  private static final char STAR = '\u22c6';

  /** the globally shared instance */
  static final MacroDefinition INSTANCE = new _KnownOptimalObjectiveValue();

  /** create */
  private _KnownOptimalObjectiveValue() {
    super("globalOptimumObjective", 0, //$NON-NLS-1$
        (Accessor.F.getShortName() + _KnownOptimalObjectiveValue.STAR));
  }

  /** {@inheritDoc} */
  @Override
  protected final void defineRequirements(final Header header)
      throws IOException {
    super.defineRequirements(header);
    Accessor.F.define(header);
  }

  /** {@inheritDoc} */
  @Override
  protected void doDefine(final Macro macro) throws IOException {
    try (InlineMath im = macro.inlineMath()) {
      Accessor.F.writeShortName(im);
      try (MathSuperscript s = im.mathSuperscript()) {
        try (MathName n = s.mathName(EMathName.SCALAR)) {
          n.writeChar(_KnownOptimalObjectiveValue.STAR);
        }
      }
    }
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance
   * {@link _KnownOptimalObjectiveValue#INSTANCE
   * _KnownOptimalObjectiveValue.INSTANCE} for serialization, i.e., when
   * the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   * 
   * @return the replacement instance (always
   *         {@link _KnownOptimalObjectiveValue#INSTANCE
   *         _KnownOptimalObjectiveValue.INSTANCE})
   */
  private final Object writeReplace() {
    return _KnownOptimalObjectiveValue.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance
   * {@link _KnownOptimalObjectiveValue#INSTANCE
   * _KnownOptimalObjectiveValue.INSTANCE} after serialization, i.e., when
   * the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   * 
   * @return the replacement instance (always
   *         {@link _KnownOptimalObjectiveValue#INSTANCE
   *         _KnownOptimalObjectiveValue.INSTANCE})
   */
  private final Object readResolve() {
    return _KnownOptimalObjectiveValue.INSTANCE;
  }

}
