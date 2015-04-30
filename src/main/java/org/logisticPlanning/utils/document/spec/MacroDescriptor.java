package org.logisticPlanning.utils.document.spec;

import org.logisticPlanning.utils.NamedObject;
import org.logisticPlanning.utils.text.TextUtils;
import org.logisticPlanning.utils.text.transformations.NormalCharTransformer;

/**
 * The macro descriptor. Documents may have macros, i.e., shortcuts that
 * represent document elements. These macros can later be invoked at
 * different places in the document. Two macro descriptors are equal if
 * their normalized macro names are equal. Documents cannot contain two
 * equal macros.
 */
public class MacroDescriptor extends NamedObject {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the maximum allowed number of parameters for a macro */
  public static final int MAX_PARAMETERS = 9;

  /** the number of parameters */
  final int m_paramCount;

  /**
   * the placeholder string (for printing macros in other contexts/plain
   * text)
   */
  private final String m_placeHolder;

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
  public MacroDescriptor(final String name, final int paramCount,
      final String placeHolder) {
    super(MacroDescriptor.makeName(name));

    String p;

    if ((paramCount < 0) || (paramCount > MacroDescriptor.MAX_PARAMETERS)) {
      throw new IllegalArgumentException(//
          "The number of parameters of a macro is limited to 0 to MAX_PARAMETERS, but " + //$NON-NLS-1$
              paramCount + " was  supplied."); //$NON-NLS-1$
    }

    p = TextUtils.prepare(placeHolder);
    if (p == null) {
      throw new IllegalArgumentException(//
          "Macro place hold must not be empty.");//$NON-NLS-1$
    }

    this.m_paramCount = paramCount;
    this.m_placeHolder = p;
  }

  /**
   * Get the number of macro parameters
   * 
   * @return the number of macro parameters
   */
  public final int getParamCount() {
    return this.m_paramCount;
  }

  /**
   * Get the macro placeholder
   * 
   * @return the macro placeholder
   */
  public final String getPlaceholder() {
    return this.m_placeHolder;
  }

  /**
   * make an admissible macro name
   * 
   * @param name
   *          the name blueprint
   * @return the macro name
   */
  private static final String makeName(final String name) {
    String p;

    p = TextUtils.prepare(name);

    if (p == null) {
      throw new IllegalArgumentException(//
          "Macro name must not be empty."); //$NON-NLS-1$
    }

    p = TextUtils.prepare(NormalCharTransformer.INSTANCE.transform(p));
    if (p == null) {
      throw new IllegalArgumentException(//
          "Macro place must have a non-trivial normalized name.");//$NON-NLS-1$
    }

    return p;
  }

  /** {@inheritDoc} */
  @Override
  public final MacroDescriptor clone() {
    return this;
  }

  /**
   * validate the number of parameters
   * 
   * @param clazz
   *          the class
   * @param maxParams
   *          the highest number of supported parameters
   */
  public static final void validateMaxParams(final Class<?> clazz,
      final int maxParams) {
    if (MacroDescriptor.MAX_PARAMETERS > maxParams) {
      throw new UnsupportedOperationException(
          "API is inconsistent: " + clazz.getCanonicalName() + //$NON-NLS-1$
              " can only support macros with up to 9 parameters, but " //$NON-NLS-1$
              + MacroDescriptor.MAX_PARAMETERS + " are allowed by "//$NON-NLS-1$
              + MacroDescriptor.class.getCanonicalName());
    }
  }
}
