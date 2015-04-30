package org.logisticPlanning.tsp.evaluation.data.properties.instance;

import java.io.IOException;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.EPropertyType;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.data.Property;
import org.logisticPlanning.tsp.evaluation.data.RunSet;
import org.logisticPlanning.tsp.evaluation.data.conditions.InstanceSelectionCondition;
import org.logisticPlanning.utils.collections.conditions.Condition;
import org.logisticPlanning.utils.collections.conditions.ConstantCondition;
import org.logisticPlanning.utils.collections.lists.ArraySetView;
import org.logisticPlanning.utils.document.spec.Document;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.utils.HashUtils;

/**
 * A collection of instances for each experiment has some data.
 */
public final class SharedInstancesProperty extends
    Property<ExperimentSet, ArraySetView<Instance>> {

  /**
   * the globally shared instances of the that were not prematurely
   * terminated instance property
   */
  public static final SharedInstancesProperty NON_PREMATURE_SHARED = //
  new SharedInstancesProperty(InstancesProperty.ALL_INSTANCES,//
      ConstantCondition.TRUE,//
      InstanceSelectionUtils.ONE_RUN_MUST_BE_NOT_PREMATURLY_TERMINATED);

  /** the globally shared instance of the common instance property */
  public static final SharedInstancesProperty NON_EMPTY_SHARED = //
  new SharedInstancesProperty(InstancesProperty.ALL_INSTANCES,//
      ConstantCondition.TRUE,//
      InstanceSelectionUtils.ONE_RUN_MUST_BE_NOT_EMPTY);

  /** the conditions */
  private static final SharedInstancesProperty[] __ONE_MUST_HAVE_2 = //
  new SharedInstancesProperty[Accessor.ACCESSORS.size()];

  /** the source property */
  private final Property<? super ExperimentSet, ArraySetView<Instance>> m_source;

  /** the condition that must be {@code true} for at least one run set */
  private final Condition<? super RunSet> m_exists;

  /** the condition that must be {@code true} for all run sets */
  private final Condition<? super RunSet> m_forAll;

  /** the hash code */
  private final int m_hc;

  /**
   * Create the property
   * 
   * @param source
   *          the source property
   * @param exists
   *          the condition that must be {@code true} for at least one run
   *          set
   * @param forAll
   *          the condition the condition that must be {@code true} for all
   *          run sets
   */
  public SharedInstancesProperty(
      final Property<? super ExperimentSet, ArraySetView<Instance>> source,
      final Condition<? super RunSet> exists,
      final Condition<? super RunSet> forAll) {
    this(EPropertyType.TEMPORARILY_STORED, source, exists, forAll);
  }

  /**
   * Create the property
   * 
   * @param type
   *          the property type
   * @param source
   *          the source property
   * @param exists
   *          the condition that must be {@code true} for at least one run
   *          set
   * @param forAll
   *          the condition the condition that must be {@code true} for all
   *          run sets
   */
  public SharedInstancesProperty(
      final EPropertyType type,
      final Property<? super ExperimentSet, ArraySetView<Instance>> source,
      final Condition<? super RunSet> exists,
      final Condition<? super RunSet> forAll) {
    super(type);

    if ((source == null) || (exists == null) || (forAll == null)) {
      throw new IllegalArgumentException();
    }

    this.m_source = source;
    this.m_forAll = forAll;
    this.m_exists = exists;
    this.m_hc = HashUtils.combineHashes(//
        HashUtils.combineHashes(//
            HashUtils.hashCode(this.getClass()),//
            HashUtils.hashCode(this.m_forAll)),//
        HashUtils.hashCode(this.m_exists));
  }

  /** {@inheritDoc} */
  @Override
  public void initialize(final Header header) throws IOException {
    super.initialize(header);
    this.m_source.initialize(header);
  }

  /** {@inheritDoc} */
  @Override
  protected final ArraySetView<Instance> compute(
      final ExperimentSet dataset, final Document doc) {
    return this.m_source.get(dataset, doc).select(//
        new InstanceSelectionCondition(dataset, this.m_exists,
            this.m_forAll));
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equals(final Object o) {
    final SharedInstancesProperty sp;

    if (o == this) {
      return true;
    }

    if (o instanceof SharedInstancesProperty) {
      sp = ((SharedInstancesProperty) o);
      return ((sp.m_hc == this.m_hc) && //
          this.m_source.equals(sp.m_source) && //
          this.m_exists.equals(sp.m_exists) && //
      this.m_forAll.equals(sp.m_forAll));
    }
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return this.m_hc;
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
  public static final SharedInstancesProperty oneMustHave2PointsOfAccessor(
      final Accessor axs) {
    final int i;
    SharedInstancesProperty c;

    i = axs.ordinal();
    c = SharedInstancesProperty.__ONE_MUST_HAVE_2[i];
    if (c == null) {
      SharedInstancesProperty.__ONE_MUST_HAVE_2[i] = c = new SharedInstancesProperty(
          InstancesProperty.ALL_INSTANCES,//
          InstanceSelectionUtils.oneMustHave2PointsOfAccessor(axs),//
          InstanceSelectionUtils.ONE_RUN_MUST_BE_NOT_PREMATURLY_TERMINATED);
    }

    return c;
  }
}
