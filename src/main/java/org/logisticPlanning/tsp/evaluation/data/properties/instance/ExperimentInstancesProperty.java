package org.logisticPlanning.tsp.evaluation.data.properties.instance;

import java.util.ArrayList;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.EPropertyType;
import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.data.Property;
import org.logisticPlanning.tsp.evaluation.data.RunSet;
import org.logisticPlanning.utils.collections.conditions.Condition;
import org.logisticPlanning.utils.collections.lists.ArraySetView;
import org.logisticPlanning.utils.document.spec.Document;
import org.logisticPlanning.utils.utils.HashUtils;

/**
 * A property returning a list of instances of an experiment.
 */
public class ExperimentInstancesProperty extends
    Property<Experiment, ArraySetView<Instance>> {

  /** all non-empty instances */
  public static final ExperimentInstancesProperty NON_EMPTY = //
  new ExperimentInstancesProperty(EPropertyType.TEMPORARILY_STORED,
      InstanceSelectionUtils.ONE_RUN_MUST_BE_NOT_EMPTY);

  /** all non-premature instances */
  public static final ExperimentInstancesProperty NON_PREMATURE = //
  new ExperimentInstancesProperty(EPropertyType.TEMPORARILY_STORED,
      InstanceSelectionUtils.ONE_RUN_MUST_BE_NOT_PREMATURLY_TERMINATED);

  /** the conditions */
  private static final ExperimentInstancesProperty[] __ONE_MUST_HAVE_2 = //
  new ExperimentInstancesProperty[Accessor.ACCESSORS.size()];

  /** the instances to return */
  private final Condition<? super RunSet> m_cond;

  /** the hash code */
  private final int m_hc;

  /**
   * Create
   * 
   * @param type
   *          the property type
   * @param cond
   *          the condition
   */
  public ExperimentInstancesProperty(final EPropertyType type,
      final Condition<? super RunSet> cond) {
    super(type);

    if (cond == null) {
      throw new IllegalArgumentException();
    }
    this.m_cond = cond;

    this.m_hc = HashUtils.combineHashes(//
        HashUtils.hashCode(this.getClass()), this.m_cond.hashCode());
  }

  /** {@inheritDoc} */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  protected final ArraySetView<Instance> compute(final Experiment dataset,
      final Document doc) {
    final ArrayList<Instance> lst;
    int s;

    s = dataset.size();
    if (s <= 0) {
      return ((ArraySetView) (ArraySetView.EMPTY_SET_VIEW));
    }

    lst = new ArrayList<>(s);
    for (final RunSet rs : dataset) {
      if (this.m_cond.check(rs)) {
        lst.add(rs.getInstance());
      }
    }

    s = lst.size();
    if (s <= 0) {
      return ((ArraySetView) (ArraySetView.EMPTY_SET_VIEW));
    }
    return ArraySetView.makeArraySetView(lst.toArray(new Instance[s]),
        false);
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return this.m_hc;
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equals(final Object o) {
    ExperimentInstancesProperty p;

    if (o == this) {
      return true;
    }
    if (o instanceof ExperimentInstancesProperty) {
      p = ((ExperimentInstancesProperty) o);
      if (p.m_hc == this.m_hc) {
        return this.m_cond.equals(p.m_cond);
      }
    }

    return false;
  }

  /**
   * Get the condition for non-empty, non-prematurely killed run sets, at
   * least one run must have two points with different values for a given
   * {@link org.logisticPlanning.tsp.evaluation.data.Accessor accessor}.
   * 
   * @param axs
   *          the accessor
   * @return the condition
   */
  public static final ExperimentInstancesProperty oneMustHave2PointsOfAccessor(
      final Accessor axs) {
    final int i;
    ExperimentInstancesProperty c;

    i = axs.ordinal();
    c = ExperimentInstancesProperty.__ONE_MUST_HAVE_2[i];
    if (c == null) {
      ExperimentInstancesProperty.__ONE_MUST_HAVE_2[i] = c = //
      new ExperimentInstancesProperty(EPropertyType.TEMPORARILY_STORED,//
          InstanceSelectionUtils.oneMustHave2PointsOfAccessor(axs));
    }

    return c;
  }
}
