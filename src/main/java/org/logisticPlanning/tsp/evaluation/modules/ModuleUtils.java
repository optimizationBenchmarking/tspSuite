package org.logisticPlanning.tsp.evaluation.modules;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * some utility methods to be used in evaluation modules
 */
public final class ModuleUtils {

  /** the warning to be issued if no chart driver was detected */
  public static final String WARNING_NO_CHART_DRIVER = "No chart driver detected, so we cannot paint any charts. This means this module cannot do anything useful and thus wil exit.";//$NON-NLS-1$

  /** the decimal format symbols */
  private static final DecimalFormatSymbols DECIMAL_SYMBOLS = //
      DecimalFormatSymbols.getInstance(Locale.US);

  /**
   * Create a new decimal format
   *
   * @param spec
   *          the specification
   * @return the format
   */
  public static final DecimalFormat createDecimalFormat(final String spec) {
    return new DecimalFormat(spec, ModuleUtils.DECIMAL_SYMBOLS);
  }
}
