package org.logisticPlanning.tsp.evaluation.modules.impl.macros;

import org.logisticPlanning.utils.document.impl.utils.MacroMathConst;
import org.logisticPlanning.utils.document.spec.EMathName;
import org.logisticPlanning.utils.document.spec.MacroDefinition;

/**
 * the macro of the problem scale
 */
final class _Scale extends MacroMathConst {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  static final MacroDefinition INSTANCE = new _Scale();

  /** create */
  private _Scale() {
    super("scale", "n", EMathName.SCALAR); //$NON-NLS-2$ //$NON-NLS-1$
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link _Scale#INSTANCE _Scale.INSTANCE}
   * for serialization, i.e., when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   * 
   * @return the replacement instance (always {@link _Scale#INSTANCE
   *         _Scale.INSTANCE})
   */
  private final Object writeReplace() {
    return _Scale.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link _Scale#INSTANCE _Scale.INSTANCE}
   * after serialization, i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   * 
   * @return the replacement instance (always {@link _Scale#INSTANCE
   *         _Scale.INSTANCE})
   */
  private final Object readResolve() {
    return _Scale.INSTANCE;
  }

}
