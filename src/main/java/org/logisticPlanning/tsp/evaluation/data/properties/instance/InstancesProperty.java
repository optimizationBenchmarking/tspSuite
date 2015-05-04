package org.logisticPlanning.tsp.evaluation.data.properties.instance;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.evaluation.data.DataSet;
import org.logisticPlanning.tsp.evaluation.data.EPropertyType;
import org.logisticPlanning.tsp.evaluation.data.Property;
import org.logisticPlanning.utils.collections.lists.ArraySetView;
import org.logisticPlanning.utils.document.spec.Document;
import org.logisticPlanning.utils.utils.HashUtils;

/**
 * a property returning a fixed set of benchmark instances
 */
public class InstancesProperty extends
Property<DataSet<?>, ArraySetView<Instance>> {

  /** all instances */
  public static final InstancesProperty ALL_INSTANCES = new InstancesProperty(
      Instance.ALL_INSTANCES);

  /** the instances to return */
  private final ArraySetView<Instance> m_insts;

  /** the hash code */
  private final int m_hc;

  /**
   * Create
   *
   * @param insts
   *          the instances
   */
  public InstancesProperty(final ArraySetView<Instance> insts) {
    super(EPropertyType.NEVER_STORED);

    if (insts == null) {
      throw new IllegalArgumentException();
    }
    this.m_insts = insts;

    this.m_hc = HashUtils.combineHashes(//
        HashUtils.hashCode(this.getClass()), this.m_insts.hashCode());
  }

  /** {@inheritDoc} */
  @Override
  protected final ArraySetView<Instance> compute(final DataSet<?> dataset,
      final Document doc) {
    return this.m_insts;
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return this.m_hc;
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equals(final Object o) {
    InstancesProperty p;

    if (o == this) {
      return true;
    }
    if (o instanceof InstancesProperty) {
      p = ((InstancesProperty) o);
      if (p.m_hc == this.m_hc) {
        return this.m_insts.equals(p.m_insts);
      }
    }

    return false;
  }
}
