package org.logisticPlanning.tsp.evaluation.modules.impl.macros;

import java.io.IOException;

import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.utils.document.spec.EMathName;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.InlineMath;
import org.logisticPlanning.utils.document.spec.Macro;
import org.logisticPlanning.utils.document.spec.MacroDefinition;
import org.logisticPlanning.utils.document.spec.MathName;
import org.logisticPlanning.utils.document.spec.MathSubscript;

/**
 * the best known objective value at a given time
 */
final class _RelativeBestObjectiveValueSoFar extends MacroDefinition {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  static final MacroDefinition INSTANCE = new _RelativeBestObjectiveValueSoFar();

  /** create */
  private _RelativeBestObjectiveValueSoFar() {
    super("bestRelativeObjectiveValueSoFar", 0, //$NON-NLS-1$
        (Accessor.F_RELATIVE.getShortName() + _BestObjectiveValueSoFar.BEST));
  }

  /** {@inheritDoc} */
  @Override
  protected final void defineRequirements(final Header header)
      throws IOException {
    super.defineRequirements(header);
    Accessor.F_RELATIVE.define(header);
  }

  /** {@inheritDoc} */
  @Override
  protected void doDefine(final Macro macro) throws IOException {
    try (InlineMath im = macro.inlineMath()) {
      Accessor.F_RELATIVE.writeShortName(im);
      try (MathSubscript s = im.mathSubscript()) {
        try (MathName n = s.mathName(EMathName.SCALAR)) {
          n.writeChar(_BestObjectiveValueSoFar.BEST);
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
   * with the singleton instance {@link _BestObjectiveValueSoFar#INSTANCE
   * _BestObjectiveValueSoFar.INSTANCE} for serialization, i.e., when the
   * instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   *
   * @return the replacement instance (always
   *         {@link _BestObjectiveValueSoFar#INSTANCE
   *         _BestObjectiveValueSoFar.INSTANCE})
   */
  private final Object writeReplace() {
    return _BestObjectiveValueSoFar.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link _BestObjectiveValueSoFar#INSTANCE
   * _BestObjectiveValueSoFar.INSTANCE} after serialization, i.e., when the
   * instance is read with {@link java.io.ObjectInputStream#readObject()}.
   *
   * @return the replacement instance (always
   *         {@link _BestObjectiveValueSoFar#INSTANCE
   *         _BestObjectiveValueSoFar.INSTANCE})
   */
  private final Object readResolve() {
    return _BestObjectiveValueSoFar.INSTANCE;
  }

}
