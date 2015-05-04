package org.logisticPlanning.tsp.evaluation.modules.impl.macros;

import java.io.IOException;

import org.logisticPlanning.utils.document.spec.EMathName;
import org.logisticPlanning.utils.document.spec.InlineMath;
import org.logisticPlanning.utils.document.spec.Macro;
import org.logisticPlanning.utils.document.spec.MacroDefinition;
import org.logisticPlanning.utils.document.spec.MathName;

/**
 * The error probability in a statistical test
 */
final class _StatisticalTestErrorProbability extends MacroDefinition {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  static final MacroDefinition INSTANCE = new _StatisticalTestErrorProbability();

  /** create */
  private _StatisticalTestErrorProbability() {
    super("statisticalTestErrorProbability", 0, "p"); //$NON-NLS-2$ //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected void doDefine(final Macro macro) throws IOException {
    try (InlineMath im = macro.inlineMath()) {
      try (MathName mn = im.mathName(EMathName.SCALAR)) {
        mn.writeChar('p');
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
   * {@link _StatisticalTestErrorProbability#INSTANCE
   * _StatisticalTestErrorProbability.INSTANCE} for serialization, i.e.,
   * when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   *
   * @return the replacement instance (always
   *         {@link _StatisticalTestErrorProbability#INSTANCE
   *         _StatisticalTestErrorProbability.INSTANCE})
   */
  private final Object writeReplace() {
    return _StatisticalTestErrorProbability.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance
   * {@link _StatisticalTestErrorProbability#INSTANCE
   * _StatisticalTestErrorProbability.INSTANCE} after serialization, i.e.,
   * when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   *
   * @return the replacement instance (always
   *         {@link _StatisticalTestErrorProbability#INSTANCE
   *         _StatisticalTestErrorProbability.INSTANCE})
   */
  private final Object readResolve() {
    return _StatisticalTestErrorProbability.INSTANCE;
  }

}
