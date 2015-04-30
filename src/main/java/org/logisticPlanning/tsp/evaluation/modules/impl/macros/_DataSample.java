package org.logisticPlanning.tsp.evaluation.modules.impl.macros;

import java.io.IOException;

import org.logisticPlanning.utils.document.spec.EMathName;
import org.logisticPlanning.utils.document.spec.InlineMath;
import org.logisticPlanning.utils.document.spec.Macro;
import org.logisticPlanning.utils.document.spec.MacroDefinition;
import org.logisticPlanning.utils.document.spec.MathName;
import org.logisticPlanning.utils.document.spec.MathSubscript;

/**
 * a place holder for a data sample
 */
final class _DataSample extends MacroDefinition {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  static final MacroDefinition INSTANCE = new _DataSample();

  /** create */
  private _DataSample() {
    super("dataSample", 1, "Di"); //$NON-NLS-2$ //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected void doDefine(final Macro macro) throws IOException {
    try (InlineMath im = macro.inlineMath()) {
      try (MathName mn = im.mathName(EMathName.TUPLE)) {
        mn.writeChar('D');
      }
      try (MathSubscript s = im.mathSubscript()) {
        macro.macroParameter(1);
      }
    }
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link _DataSample#INSTANCE
   * _DataSample.INSTANCE} for serialization, i.e., when the instance is
   * written with {@link java.io.ObjectOutputStream#writeObject(Object)}.
   * 
   * @return the replacement instance (always {@link _DataSample#INSTANCE
   *         _DataSample.INSTANCE})
   */
  private final Object writeReplace() {
    return _DataSample.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link _DataSample#INSTANCE
   * _DataSample.INSTANCE} after serialization, i.e., when the instance is
   * read with {@link java.io.ObjectInputStream#readObject()}.
   * 
   * @return the replacement instance (always {@link _DataSample#INSTANCE
   *         _DataSample.INSTANCE})
   */
  private final Object readResolve() {
    return _DataSample.INSTANCE;
  }

}
