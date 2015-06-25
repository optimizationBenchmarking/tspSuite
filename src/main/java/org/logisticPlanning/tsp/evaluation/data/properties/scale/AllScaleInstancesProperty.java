package org.logisticPlanning.tsp.evaluation.data.properties.scale;

import java.util.ArrayList;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.evaluation.data.DataSet;
import org.logisticPlanning.tsp.evaluation.data.EPropertyType;
import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.data.Property;
import org.logisticPlanning.tsp.evaluation.data.properties.instance.ExperimentInstancesProperty;
import org.logisticPlanning.tsp.evaluation.data.properties.instance.SharedInstancesProperty;
import org.logisticPlanning.utils.collections.lists.ArraySetView;
import org.logisticPlanning.utils.document.spec.Document;

/**
 * A property that returns the run sets of given scales for an experiment.
 *
 * @param <T>
 *          the data set type
 */
public final class AllScaleInstancesProperty<T extends DataSet<?>> extends
    Property<T, AllScaleInstances> {

  /**
   * the scale set with powers of 10 for the instances of a given
   * experiment
   */
  public static final AllScaleInstancesProperty<Experiment> EXPERIMENT_POWERS_OF_10 = //
  new AllScaleInstancesProperty<>(//
      EPropertyType.TEMPORARILY_STORED, //
      ExperimentInstancesProperty.NON_PREMATURE,//
      10);

  /**
   * the scale set with powers of 2 for the instances of a given experiment
   */
  public static final AllScaleInstancesProperty<Experiment> EXPERIMENT_POWERS_OF_2 = //
  new AllScaleInstancesProperty<>(//
      EPropertyType.TEMPORARILY_STORED, //
      ExperimentInstancesProperty.NON_PREMATURE,//
      2);

  /**
   * the scale set with powers of 10 for the instances of a given
   * experiment set
   */
  public static final AllScaleInstancesProperty<ExperimentSet> SHARED_POWERS_OF_10 = //
  new AllScaleInstancesProperty<>(//
      EPropertyType.TEMPORARILY_STORED, //
      SharedInstancesProperty.NON_EMPTY_SHARED,//
      10);

  /**
   * the scale set with powers of 2 for the instances of a given experiment
   * set
   */
  public static final AllScaleInstancesProperty<ExperimentSet> SHARED_POWERS_OF_2 = //
  new AllScaleInstancesProperty<>(//
      EPropertyType.TEMPORARILY_STORED, //
      SharedInstancesProperty.NON_EMPTY_SHARED,//
      2);

  /** the source instances property */
  private final Property<? super T, ArraySetView<Instance>> m_source;
  /** the base of the scale */
  private final int m_base;

  /**
   * Create the property
   *
   * @param source
   *          the instance source property
   * @param base
   *          the base
   * @param type
   *          the property type
   */
  public AllScaleInstancesProperty(final EPropertyType type,
      final Property<? super T, ArraySetView<Instance>> source,
      final int base) {
    super(type);

    if ((base <= 1) || (base > 10000)) {
      throw new IllegalArgumentException(//
          "Illegal base for scale sets " + base); //$NON-NLS-1$
    }

    if (source == null) {
      throw new IllegalArgumentException();
    }

    this.m_source = source;
    this.m_base = base;
  }

  /** {@inheritDoc} */
  @Override
  protected final AllScaleInstances compute(final T dataset,
      final Document doc) {
    final ArraySetView<Instance> insts;
    final ArrayList<SameScaleInstances> all;
    final ArrayList<Instance> same;
    final int base;
    int scale, lowestLimit, highestLimit, lowerLimit, upperLimit, currentLow, currentHigh, lowest, highest, currentDim, sze;

    insts = this.m_source.get(dataset, doc);

    all = new ArrayList<>();
    same = new ArrayList<>();

    base = this.m_base;
    lowerLimit = 0;
    upperLimit = (base - 1);
    highest = highestLimit = Integer.MIN_VALUE;
    lowest = lowestLimit = Integer.MAX_VALUE;

    for (scale = 0;; scale++) {

      currentLow = Integer.MAX_VALUE;
      currentHigh = Integer.MIN_VALUE;

      for (final Instance inst : insts) {

        currentDim = inst.n();
        if ((currentDim >= lowerLimit) && (currentDim <= upperLimit)) {
          same.add(inst);
          if (currentDim > currentHigh) {
            currentHigh = currentDim;
          }
          if (currentDim < currentLow) {
            currentLow = currentDim;
          }
        }
      }

      sze = same.size();
      if (sze > 0) {
        all.add(//
        new SameScaleInstances(lowerLimit, upperLimit, currentLow,
            currentHigh, same.toArray(new Instance[sze]), scale));
        same.clear();

        if (lowerLimit < lowestLimit) {
          lowestLimit = lowerLimit;
        }
        if (upperLimit > highestLimit) {
          highestLimit = upperLimit;
        }
        if (currentLow < lowest) {
          lowest = currentLow;
        }
        if (currentHigh < highest) {
          highest = currentHigh;
        }
      }

      if ((scale < 0) || (lowerLimit < 0) || (upperLimit <= 0)
          || (upperLimit >= Integer.MAX_VALUE)) {
        break;
      }
      lowerLimit = (upperLimit + 1);
      if (lowerLimit <= 0) {
        break;
      }
      upperLimit = ((lowerLimit * base) - 1);
      if (upperLimit <= lowerLimit) {
        upperLimit = Integer.MAX_VALUE;
      }
    }

    return new AllScaleInstances(lowestLimit, highestLimit, lowest,
        highest, all.toArray(new SameScaleInstances[all.size()]), base);
  }

  /**
   * Get the base of the scale set property
   *
   * @return the base of the scale set property
   */
  public final int getBase() {
    return this.m_base;
  }
}
