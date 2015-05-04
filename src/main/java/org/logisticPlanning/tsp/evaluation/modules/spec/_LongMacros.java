package org.logisticPlanning.tsp.evaluation.modules.spec;

import java.util.HashMap;

import org.logisticPlanning.tsp.evaluation.data.EPropertyType;
import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.data.Property;
import org.logisticPlanning.utils.document.impl.utils.MacroText;
import org.logisticPlanning.utils.document.spec.Document;
import org.logisticPlanning.utils.document.spec.MacroDefinition;

/**
 * the long macros property. This property stores the synthesized macro
 * descriptors for the long-name macros.
 */
final class _LongMacros extends
    Property<ExperimentSet, HashMap<String, MacroDefinition>> {

  /** create */
  _LongMacros() {
    super(EPropertyType.PERMANENTLY_STORED);
  }

  /** {@inheritDoc} */
  @Override
  protected final HashMap<String, MacroDefinition> compute(
      final ExperimentSet dataset, final Document doc) {
    final HashMap<String, MacroDefinition> map;
    Experiment ex;
    String s;
    int i;

    if (doc == null) {
      return null;
    }

    map = new HashMap<>();

    for (i = dataset.size(); (--i) >= 0;) {
      ex = dataset.get(i);
      s = ex.name();
      if (!(map.containsKey(s))) {
        map.put(s, new MacroText((s + "Long"), ex.longName()));//$NON-NLS-1$
      }
    }

    return map;
  }

}
