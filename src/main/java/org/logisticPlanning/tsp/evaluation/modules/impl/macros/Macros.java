package org.logisticPlanning.tsp.evaluation.modules.impl.macros;

import org.logisticPlanning.utils.document.impl.utils.MacroMathConst;
import org.logisticPlanning.utils.document.spec.EMathName;
import org.logisticPlanning.utils.document.spec.MacroDefinition;

/**
 * The set of commonly used macros
 */
public final class Macros {

  /** the scale macro */
  public static final MacroDefinition SCALE = _Scale.INSTANCE;

  /** the known globally optimal objective value */
  public static final MacroDefinition F_OPTIMAL = _KnownOptimalObjectiveValue.INSTANCE;

  /** the best objective value discovered so far */
  public static final MacroDefinition F_BEST = _BestObjectiveValueSoFar.INSTANCE;

  /** an objective function threshold */
  public static final MacroDefinition F_THRESHOLD = _ObjectiveFunctionThreshold.INSTANCE;

  /** a relative objective function threshold */
  public static final MacroDefinition F_THRESHOLD_RELATIVE = _RelativeObjectiveFunctionThreshold.INSTANCE;

  /** the best relative error/objective value */
  public static final MacroDefinition F_BEST_RELATIVE = _RelativeBestObjectiveValueSoFar.INSTANCE;

  /** the ith data sample */
  public static final MacroDefinition DATA_SAMPLE_I = _DataSample.INSTANCE;

  /** the maximum allowed error probability in a statistical test */
  public static final MacroDefinition TEST_MAX_ERROR_PROBABILITY = _StatisticalTestMaxErrorProbability.INSTANCE;

  /** the true error probability in a statistical test */
  public static final MacroDefinition TEST_ERROR_PROBABILITY = _StatisticalTestErrorProbability.INSTANCE;

  /** the test comparison result */
  public static final MacroDefinition TEST_COMPARISON_RESULT = _StatisticalTestComparisonResult.INSTANCE;

  /** the ert */
  public static final MacroMathConst ERT = new MacroMathConst("ert", null,//$NON-NLS-1$
      EMathName.SCALAR);

  /**
   * the 1-parameter macro for representing the ERT with subscript
   * parameter
   */
  public static final MacroDefinition ERTi = _ERTI.INSTANCE;

  /**
   * the 1-parameter macro for representing the ERT with subscript and
   * value parameter
   */
  public static final MacroDefinition ERTib = _ERTIB.INSTANCE;

  /** the ecdf */
  public static final MacroMathConst ECDF = new MacroMathConst(
      "ecdf", null,//$NON-NLS-1$
      EMathName.SCALAR);

  /** the AUC */
  public static final MacroMathConst AUC = new MacroMathConst("auc", null,//$NON-NLS-1$
      EMathName.SCALAR);

}
