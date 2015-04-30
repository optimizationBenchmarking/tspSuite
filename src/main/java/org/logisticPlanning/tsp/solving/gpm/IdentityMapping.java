package org.logisticPlanning.tsp.solving.gpm;

/**
 * The identity mapping is a
 * {@link org.logisticPlanning.tsp.solving.gpm.GPM genotype-phenotype
 * mapping} (GPM) which is used in cases where the search space and the
 * solution space are the same. In such a case, a phenotype (the result of
 * the
 * {@link org.logisticPlanning.tsp.solving.gpm.GPM#gpm(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction, org.logisticPlanning.tsp.solving.Individual)
 * GPM}) is the exactly same data structure and instance as its
 * corresponding genotype (the input of the
 * {@link org.logisticPlanning.tsp.solving.gpm.GPM#gpm(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction, org.logisticPlanning.tsp.solving.Individual)
 * GPM}). The method
 * {@link #gpm(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction, org.logisticPlanning.tsp.solving.Individual)
 * gpm} of the identiy mapping thus directly returns its input without
 * making any change. Therefore, there also needs to be one, globally
 * shared, {@link #INSTANCE instance} of this kind of genotype-phenotype
 * mapping.
 */
public final class IdentityMapping extends GPM<Object> {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final IdentityMapping INSTANCE = new IdentityMapping();

  /** instantiate */
  private IdentityMapping() {
    super("identityMapping"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final IdentityMapping clone() {
    return IdentityMapping.INSTANCE;
  }
}
