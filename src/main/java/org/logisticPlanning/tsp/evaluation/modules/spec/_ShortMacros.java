package org.logisticPlanning.tsp.evaluation.modules.spec;

import java.util.HashMap;

import org.logisticPlanning.tsp.evaluation.data.EPropertyType;
import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.data.Property;
import org.logisticPlanning.utils.document.impl.utils.MacroShortcut;
import org.logisticPlanning.utils.document.spec.Document;
import org.logisticPlanning.utils.document.spec.MacroDefinition;

/**
 * The short macros property. This property stores the synthesized macro
 * descriptors for the short-name macros.
 */
final class _ShortMacros extends
    Property<ExperimentSet, HashMap<String, MacroDefinition>> {

  /** create */
  _ShortMacros() {
    super(EPropertyType.PERMANENTLY_STORED);
  }

  /** {@inheritDoc} */
  @Override
  protected final HashMap<String, MacroDefinition> compute(
      final ExperimentSet dataset, final Document doc) {
    final HashMap<String, MacroDefinition> map;
    Experiment ex;
    int i;
    String s;

    if (doc == null) {
      return null;
    }

    map = new HashMap<>();

    for (i = dataset.size(); (--i) >= 0;) {
      ex = dataset.get(i);

      s = ex.name();
      if (!(map.containsKey(s))) {
        map.put(s, new MacroShortcut((s + "Short"), ex.shortName()));//$NON-NLS-1$
      }
    }

    return map;
  }

}
