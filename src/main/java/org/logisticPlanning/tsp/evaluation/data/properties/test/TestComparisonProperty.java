package org.logisticPlanning.tsp.evaluation.data.properties.test;

import java.io.IOException;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.DataPoint;
import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.EPropertyType;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.data.Property;
import org.logisticPlanning.tsp.evaluation.data.properties.instance.SharedInstancesProperty;
import org.logisticPlanning.tsp.evaluation.data.properties.limit.LimitDataCollectionProperty;
import org.logisticPlanning.utils.collections.lists.ArraySetView;
import org.logisticPlanning.utils.document.spec.AbstractTextPlain;
import org.logisticPlanning.utils.document.spec.Document;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.math.data.collection.IDataCollection;
import org.logisticPlanning.utils.math.statistics.tests.impl.TwoTailedMannWhitneyUTest;
import org.logisticPlanning.utils.math.statistics.tests.spec.MultivariateTest;
import org.logisticPlanning.utils.math.statistics.tests.spec.MultivariateTestResult;
import org.logisticPlanning.utils.utils.HashUtils;

/**
 * This evaluator will print the setup values of a given algorithm by using
 * the very conservative Bonferroni correction&nbsp;[<a
 * href="#cite_D1961MCAM" style="font-weight:bold">1</a>, <a
 * href="#cite_D2006SCOCOMDS" style="font-weight:bold">2</a>].
 */
public class TestComparisonProperty extends
    Property<ExperimentSet, TestComparisonResult> {

  /** the end-of-run mann-whitney u test 2% error threshold comparator */
  public static final TestComparisonProperty END_OF_RUN_F_002_MWU = //
  new TestComparisonProperty(
  //
      EPropertyType.TEMPORARILY_STORED,//
      SharedInstancesProperty.NON_EMPTY_SHARED,//
      LimitDataCollectionProperty.END_OF_RUN, //
      new TwoTailedMannWhitneyUTest(), Accessor.F, 0.02d, true);

  /** the end-of-run mann-whitney u test 5% error threshold comparator */
  public static final TestComparisonProperty END_OF_RUN_F_005_MWU = //
  new TestComparisonProperty(
  //
      EPropertyType.TEMPORARILY_STORED,//
      SharedInstancesProperty.NON_EMPTY_SHARED,//
      LimitDataCollectionProperty.END_OF_RUN,//
      new TwoTailedMannWhitneyUTest(), Accessor.F, 0.05d, true);

  /**
   * the end-of-run mann-whitney u test optimum FE error threshold
   * comparator
   */
  public static final TestComparisonProperty OPTIMUM_FE_002_MWU = //
  new TestComparisonProperty(
      //
      EPropertyType.TEMPORARILY_STORED,//
      SharedInstancesProperty.NON_EMPTY_SHARED,//
      LimitDataCollectionProperty.create(DataPoint.RELATIVE_F_INDEX, 0d),
      new TwoTailedMannWhitneyUTest(), Accessor.FE, 0.02d, true);

  /**
   * the end-of-run mann-whitney u test optimum FE error threshold
   * comparator
   */
  public static final TestComparisonProperty OPTIMUM_FE_005_MWU = //
  new TestComparisonProperty(
      //
      EPropertyType.TEMPORARILY_STORED,//
      SharedInstancesProperty.NON_EMPTY_SHARED,//
      LimitDataCollectionProperty.create(DataPoint.RELATIVE_F_INDEX, 0d),
      new TwoTailedMannWhitneyUTest(), Accessor.FE, 0.05d, true);

  /** the optimum mann-whitney u test optimum DE error threshold comparator */
  public static final TestComparisonProperty OPTIMUM_DE_002_MWU = //
  new TestComparisonProperty(
      //
      EPropertyType.TEMPORARILY_STORED,//
      SharedInstancesProperty.NON_EMPTY_SHARED,//
      LimitDataCollectionProperty.create(DataPoint.RELATIVE_F_INDEX, 0d),
      new TwoTailedMannWhitneyUTest(), Accessor.DE, 0.02d, true);

  /** the optimum mann-whitney u test optimum DE error threshold comparator */
  public static final TestComparisonProperty OPTIMUM_DE_005_MWU = //
  new TestComparisonProperty(
      //
      EPropertyType.TEMPORARILY_STORED,//
      SharedInstancesProperty.NON_EMPTY_SHARED,//
      LimitDataCollectionProperty.create(DataPoint.RELATIVE_F_INDEX, 0d),
      new TwoTailedMannWhitneyUTest(), Accessor.DE, 0.05d, true);

  /**
   * the optimum mann-whitney u test optimum normalized runtime error
   * threshold comparator
   */
  public static final TestComparisonProperty OPTIMUM_NORMALIZED_RUNTIME_002_MWU = //
  new TestComparisonProperty(
      //
      EPropertyType.TEMPORARILY_STORED,//
      SharedInstancesProperty.NON_EMPTY_SHARED,//
      LimitDataCollectionProperty.create(DataPoint.RELATIVE_F_INDEX, 0d),
      new TwoTailedMannWhitneyUTest(), Accessor.NORMALIZED_RUNTIME, 0.02d,
      true);

  /**
   * the optimum mann-whitney u test optimum normalized runtime error
   * threshold comparator
   */
  public static final TestComparisonProperty OPTIMUM_NORMALIZED_RUNTIME_005_MWU = //
  new TestComparisonProperty(
      //
      EPropertyType.TEMPORARILY_STORED,//
      SharedInstancesProperty.NON_EMPTY_SHARED,//
      LimitDataCollectionProperty.create(DataPoint.RELATIVE_F_INDEX, 0d),
      new TwoTailedMannWhitneyUTest(), Accessor.NORMALIZED_RUNTIME, 0.05d,
      true);

  /**
   * the 1%-off the optimum mann-whitney u test optimum FE error threshold
   * comparator
   */
  public static final TestComparisonProperty REL_F_001_FE_002_MWU = //
  new TestComparisonProperty(
      //
      EPropertyType.TEMPORARILY_STORED,//
      SharedInstancesProperty.NON_EMPTY_SHARED,//
      LimitDataCollectionProperty
          .create(DataPoint.RELATIVE_F_INDEX, 0.01d),
      new TwoTailedMannWhitneyUTest(), Accessor.FE, 0.02d, true);

  /**
   * the 1%-off the optimum mann-whitney u test optimum DE error threshold
   * comparator
   */
  public static final TestComparisonProperty REL_F_001_DE_002_MWU = //
  new TestComparisonProperty(
      //
      EPropertyType.TEMPORARILY_STORED,//
      SharedInstancesProperty.NON_EMPTY_SHARED,//
      LimitDataCollectionProperty
          .create(DataPoint.RELATIVE_F_INDEX, 0.01d),
      new TwoTailedMannWhitneyUTest(), Accessor.DE, 0.02d, true);
  /**
   * the 1%-off the optimum mann-whitney u test optimum normalized runtime
   * error threshold comparator
   */
  public static final TestComparisonProperty REL_F_001_NORMALIZED_RUNTIME_002_MWU = //
  new TestComparisonProperty(
      //
      EPropertyType.TEMPORARILY_STORED,//
      SharedInstancesProperty.NON_EMPTY_SHARED,//
      LimitDataCollectionProperty
          .create(DataPoint.RELATIVE_F_INDEX, 0.01d),
      new TwoTailedMannWhitneyUTest(), Accessor.NORMALIZED_RUNTIME, 0.02d,
      true);

  /**
   * a property for pre-selecting the instances over which we will do the
   * comparison
   */
  private final SharedInstancesProperty m_instances;
  /** the properties */
  private final LimitDataCollectionProperty m_limit;
  /** the test to be used */
  private final MultivariateTest m_test;
  /** the error threshold */
  private final double m_errorThreshold;
  /** the accessor */
  private final Accessor m_axs;
  /** is smaller better? */
  private final boolean m_smallerIsBetter;
  /** the hash code */
  private final int m_hc;

  /**
   * create!
   *
   * @param type
   *          the property type
   * @param instances
   *          the instance selection property
   * @param limit
   *          the limit property
   * @param test
   *          the test to be used
   * @param errorThreshold
   *          the error threshold
   * @param axs
   *          the accessor
   * @param smallerIsBetter
   *          is smaller better
   */
  public TestComparisonProperty(
      final EPropertyType type,//
      final SharedInstancesProperty instances,
      final LimitDataCollectionProperty limit, //
      final MultivariateTest test,//
      final Accessor axs, final double errorThreshold,
      final boolean smallerIsBetter) {
    super(type);

    if ((instances == null) || (limit == null) || (test == null)) {
      throw new IllegalArgumentException();
    }

    if ((errorThreshold < 0d) || (errorThreshold > 0.5d)) {
      throw new IllegalArgumentException(//
          "Error threshold cannot be " + errorThreshold); //$NON-NLS-1$
    }
    this.m_errorThreshold = errorThreshold;
    this.m_instances = instances;
    this.m_limit = limit;
    this.m_test = test;
    this.m_axs = axs;
    this.m_smallerIsBetter = smallerIsBetter;
    this.m_hc = HashUtils.combineHashes(
        //
        HashUtils.combineHashes(//
            HashUtils.combineHashes(//
                HashUtils.hashCode(this.getClass()),//
                HashUtils.hashCode(this.m_errorThreshold)),//
            HashUtils.combineHashes(//
                HashUtils.hashCode(this.m_limit),//
                HashUtils.hashCode(this.m_test))),//
        HashUtils.combineHashes(
            //
            HashUtils.combineHashes(//
                HashUtils.hashCode(this.m_axs),//
                HashUtils.hashCode(this.m_smallerIsBetter)),
            HashUtils.hashCode(this.m_instances)));
  }

  /**
   * Get the property that returns the shared instances
   *
   * @return the property that returns the shared instances
   */
  public final SharedInstancesProperty getSharedInstancesProperty() {
    return this.m_instances;
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return (((this.m_limit.toString() + this.m_test.toString()) + //
    this.m_errorThreshold) + this.m_axs.getShortName());
  }

  /** {@inheritDoc} */
  @Override
  public void initialize(final Header header) throws IOException {
    super.initialize(header);
    this.m_limit.initialize(header);
    this.m_instances.initialize(header);
    this.m_axs.define(header);
  }

  /**
   * write the short title
   *
   * @param out
   *          the destination
   * @throws IOException
   *           if i/o fails
   */
  public final void writeShortName(final AbstractTextPlain out)
      throws IOException {
    this.m_limit.writeShortName(out);
    out.writeChar(' ');
    this.m_axs.writeShortName(out);
    out.write(" Comparator"); //$NON-NLS-1$
  }

  /**
   * Get the test
   *
   * @return the test
   */
  public final MultivariateTest getTest() {
    return this.m_test;
  }

  /**
   * Get the error threshold
   *
   * @return the error threshold
   */
  public final double getErrorThreshold() {
    return this.m_errorThreshold;
  }

  /**
   * Get the compared dimension
   *
   * @return the compared dimension
   */
  public final Accessor getComparedDimension() {
    return this.m_axs;
  }

  /**
   * Get the limit data property
   *
   * @return the limit data property
   */
  public final LimitDataCollectionProperty getLimitProperty() {
    return this.m_limit;
  }

  /**
   * Are smaller values better?
   *
   * @return {@code true} if smaller values are better, {@code false} if
   *         larger values are better
   */
  public final boolean isSmallerBetter() {
    return this.m_smallerIsBetter;
  }

  /** {@inheritDoc} */
  @Override
  protected final TestComparisonResult compute(
      final ExperimentSet dataset, final Document doc) {
    final ArraySetView<Instance> insts;
    final int expCount;
    final IDataCollection[] cols;
    final TestComparisonResult cr;

    MultivariateTestResult tr;
    int i;

    if ((dataset == null) || ((expCount = dataset.size()) <= 0)) {
      return null;
    }

    insts = this.m_instances.get(dataset, doc);
    if ((insts == null) || (insts.isEmpty())) {
      return null;
    }

    cr = new TestComparisonResult(dataset);
    cols = new IDataCollection[expCount];
    tr = null;
    for (final Instance inst : insts) {
      for (i = expCount; (--i) >= 0;) {
        cols[i] = this.m_limit.get(dataset.get(i).forInstance(inst), doc);
      }
      tr = this.m_test.test(tr, this.m_axs.ordinal(), cols);
      cr.add(tr, this.m_smallerIsBetter,//
          (2d * this.m_errorThreshold) / ((tr.n() * (tr.n() - 1)))); // Bonferroni
      // correction
    }

    return cr;
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return this.m_hc;
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equals(final Object o) {
    final TestComparisonProperty prop;
    if (o == this) {
      return true;
    }
    if (o instanceof TestComparisonProperty) {
      prop = ((TestComparisonProperty) o);

      return ((this.m_hc == prop.m_hc) && //
          (this.m_smallerIsBetter == prop.m_smallerIsBetter) && //
          (this.m_axs == prop.m_axs) && //
          (this.m_errorThreshold == prop.m_errorThreshold) && //
          (this.m_limit.equals(prop.m_limit)) && //
          (this.m_instances.equals(prop.m_instances)) && //
      (this.m_test.equals(prop.m_test)));
    }
    return false;
  }
}
