package org.logisticPlanning.tsp.evaluation.data.properties.ecdf;

import java.io.IOException;
import java.util.Arrays;

import org.logisticPlanning.tsp.benchmarking.objective.DataPoint;
import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.EPropertyType;
import org.logisticPlanning.tsp.evaluation.data.Property;
import org.logisticPlanning.tsp.evaluation.data.Run;
import org.logisticPlanning.tsp.evaluation.data.RunSet;
import org.logisticPlanning.utils.document.spec.Document;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.math.data.collection.ArrayDataCollectionView;
import org.logisticPlanning.utils.math.data.collection.IDataCollection;
import org.logisticPlanning.utils.utils.HashUtils;

/**
 * <p>
 * This property creates data collection representing the change of the the
 * Empirical (Cumulative) Distribution Function (ECDF)&nbsp;[<a
 * href="#cite_HAFR2012RPBBOBES" style="font-weight:bold">1</a>, <a
 * href="#cite_HS1998ELVAPAR" style="font-weight:bold">2</a>] ECDF over a
 * scaled time measure for a given run set.
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_HAFR2012RPBBOBES" /><a
 * href="http://www.lri.fr/~hansen/">Nikolaus Hansen</a>, <a
 * href="http://www.lri.fr/~auger/">Anne Auger</a>, <a
 * href="http://www.researchgate.net/profile/Steffen_Finck/">Steffen
 * Finck</a>, and&nbsp;<a
 * href="https://tao.lri.fr/tiki-index.php?page=people">Raymond Ros</a>:
 * <span style="font-weight:bold">&ldquo;Real-Parameter Black-Box
 * Optimization Benchmarking: Experimental Setup,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * March&nbsp;24, 2012; published by Orsay, France: Universit&#233; Paris
 * Sud, Institut National de Recherche en Informatique et en Automatique
 * (INRIA) Futurs, &#201;quipe TAO. <div>link: [<a href=
 * "http://coco.lri.fr/BBOB-downloads/download11.05/bbobdocexperiment.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_HS1998ELVAPAR" /><a
 * href="http://www.cs.ubc.ca/~hoos/">Holger H. Hoos</a> and&nbsp;<a
 * href="http://iridia.ulb.ac.be/~stuetzle/">Thomas St&#252;tzle</a>: <span
 * style="font-weight:bold">&ldquo;Evaluating Las Vegas Algorithms &#8210;
 * Pitfalls and Remedies,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the 14th
 * Conference on Uncertainty in Artificial Intelligence (UAI'98)</span>,
 * July&nbsp;24&ndash;26, 1998, Madison, WI, USA, pages 238&ndash;245,
 * Gregory F. Cooper and&nbsp;Serafin Moral, editors, San Francisco, CA,
 * USA: Morgan Kaufmann Publishers Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/155860555X">1-55860-555-X</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781558605558">978-1-55860
 * -555-8</a>. <div>link: [<a href=
 * "http://www.intellektik.informatik.tu-darmstadt.de/TR/1998/98-02.ps.Z"
 * >1</a>]</div></div></li>
 * </ol>
 */
public class RunSetECDFProperty extends Property<RunSet, IDataCollection> {

  /** the index of the scaled x coordinates */
  public static final int SCALED_X_DIM = 0;
  /** the index of the scaled x coordinates */
  public static final int SUCCESS_PROBABILITY_DIM = (RunSetECDFProperty.SCALED_X_DIM + 1);
  /** the total dimensions of the ecdf */
  public static final int ECDF_DIMENSIONS = (RunSetECDFProperty.SUCCESS_PROBABILITY_DIM + 1);

  /** the run set ECDF to success in terms of FEs */
  public static final RunSetECDFProperty FE_OPTIMUM = new RunSetECDFProperty(
      EPropertyType.TEMPORARILY_STORED, Accessor.FE, 0d);
  /** the run set ECDF to success in terms of DEs */
  public static final RunSetECDFProperty DE_OPTIMUM = new RunSetECDFProperty(
      EPropertyType.TEMPORARILY_STORED, Accessor.DE, 0d);
  /** the run set ECDF to success in terms of actual runtime */
  public static final RunSetECDFProperty RUNTIME_OPTIMUM = new RunSetECDFProperty(
      EPropertyType.TEMPORARILY_STORED, Accessor.RUNTIME, 0d);
  /** the run set ECDF to success in terms of normalized runtime */
  public static final RunSetECDFProperty NORMALIZED_RUNTIME_OPTIMUM = new RunSetECDFProperty(
      EPropertyType.TEMPORARILY_STORED, Accessor.NORMALIZED_RUNTIME, 0d);

  /** the accessor */
  private final Accessor m_axs;
  /** the goal */
  private final double m_goal;
  /** the hash code */
  private final int m_hc;

  /**
   * Create the ecdf for a given accessor (time measure) and goal objective
   * value
   *
   * @param type
   *          the property type
   * @param axs
   *          the accessor
   * @param goal
   *          the goal objective value
   */
  public RunSetECDFProperty(final EPropertyType type, final Accessor axs,
      final double goal) {
    super(type);

    if (axs.isObjective()) {
      throw new IllegalArgumentException(String.valueOf(axs));
    }
    if ((goal < 0d) || (goal > 1d)) {
      throw new IllegalArgumentException(goal + //
          " is invalid as a goal relative objective value."); //$NON-NLS-1$
    }

    this.m_axs = axs;
    this.m_goal = goal;
    this.m_hc = HashUtils.combineHashes(//
        HashUtils.combineHashes(//
            HashUtils.hashCode(this.m_axs),//
            HashUtils.hashCode(this.m_goal)),//
        HashUtils.hashCode(this.getClass()));
  }

  /**
   * Get the accessor used in this property
   *
   * @return the accessor used in this property
   */
  public final Accessor getAccessor() {
    return this.m_axs;
  }

  /**
   * Get the relative goal objective value used in this property
   *
   * @return the relative goal objective value used in this property
   */
  public final double getGoal() {
    return this.m_goal;
  }

  /**
   * Get the default instance of the ecdf for a given accessor (time
   * measure) and goal objective value
   *
   * @param axs
   *          the accessor
   * @param goal
   *          the goal objective value
   * @return the instance of the {@code RunSetECDFProperty}
   */
  public static final RunSetECDFProperty getInstance(final Accessor axs,
      final double goal) {

    if (goal <= 0d) {
      switch (axs) {
        case FE: {
          return RunSetECDFProperty.FE_OPTIMUM;
        }
        case DE: {
          return RunSetECDFProperty.DE_OPTIMUM;
        }
        case RUNTIME: {
          return RunSetECDFProperty.RUNTIME_OPTIMUM;
        }
        case NORMALIZED_RUNTIME: {
          return RunSetECDFProperty.NORMALIZED_RUNTIME_OPTIMUM;
        }
        default: {
          break;
        }
      }
    }

    return new RunSetECDFProperty(EPropertyType.NEVER_STORED, axs, goal);
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return this.m_hc;
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equals(final Object o) {
    RunSetECDFProperty epb;
    if (o == this) {
      return true;
    }
    if (o instanceof RunSetECDFProperty) {
      epb = ((RunSetECDFProperty) o);
      return ((this.m_axs == epb.m_axs) && (this.m_goal == epb.m_goal));
    }
    return false;
  }

  /** {@inheritDoc} */
  @Override
  protected final IDataCollection compute(final RunSet dataset,
      final Document doc) {
    final Accessor axs;
    final int size;
    final double goal, scaleDiv;
    final boolean isScaled;
    double[] reach, ecdf;
    DataPoint p;
    double min, max, x, last;
    int i, count, runSize, ecdfc;

    axs = this.m_axs;
    isScaled = axs.isScaled();
    if (isScaled) {
      scaleDiv = axs.calculateScale(dataset.getInstance().n());
    } else {
      scaleDiv = 1d;
    }
    goal = this.m_goal;

    size = dataset.size();

    reach = new double[size];
    min = Double.POSITIVE_INFINITY;
    max = Double.NEGATIVE_INFINITY;
    count = 0;
    for (final Run run : dataset) {
      runSize = run.size();
      if (runSize <= 0) {
        continue;
      }

      x = axs.fromPoint(run.first());
      if (x < min) {
        min = x;
      }
      if (x > max) {
        max = x;
      }
      x = axs.fromPoint(run.last());
      if (x < min) {
        min = x;
      }
      if (x > max) {
        max = x;
      }

      p = run.findRelBestF(goal);
      if (p != null) {
        reach[count++] = axs.fromPoint(p);
      }
    }

    Arrays.sort(reach, 0, count);
    ecdf = new double[(count + 2) * RunSetECDFProperty.ECDF_DIMENSIONS];

    ecdfc = 0;
    x = min;

    for (i = 0; i < count; i++) {
      last = x;
      x = reach[i];
      if (x > last) {
        // ecdf[ecdfc + RAW_X_DIM] = last;
        ecdf[ecdfc + RunSetECDFProperty.SCALED_X_DIM] = (isScaled ? (last / scaleDiv)
            : last);
        ecdf[ecdfc + RunSetECDFProperty.SUCCESS_PROBABILITY_DIM] = (i / ((double) size));
        ecdfc += RunSetECDFProperty.ECDF_DIMENSIONS;
      }
    }
    reach = null;

    // ecdf[ecdfc + RAW_X_DIM] = x;
    ecdf[ecdfc + RunSetECDFProperty.SCALED_X_DIM] = (isScaled ? (x / scaleDiv)
        : x);
    ecdf[ecdfc + RunSetECDFProperty.SUCCESS_PROBABILITY_DIM] = (last = (i / ((double) size)));
    ecdfc += RunSetECDFProperty.ECDF_DIMENSIONS;

    if (x < max) {
      // ecdf[ecdfc + RAW_X_DIM] = max;
      ecdf[ecdfc + RunSetECDFProperty.SCALED_X_DIM] = (isScaled ? (max / scaleDiv)
          : max);
      ecdf[ecdfc + RunSetECDFProperty.SUCCESS_PROBABILITY_DIM] = last;
      ecdfc += RunSetECDFProperty.ECDF_DIMENSIONS;
    }

    if (ecdfc < ecdf.length) {
      reach = new double[ecdfc];
      System.arraycopy(ecdf, 0, reach, 0, ecdfc);
      ecdf = reach;
    }

    return new ArrayDataCollectionView(ecdf,
        (ecdfc / RunSetECDFProperty.ECDF_DIMENSIONS),
        RunSetECDFProperty.ECDF_DIMENSIONS);
  }

  /** {@inheritDoc} */
  @Override
  public final void initialize(final Header header) throws IOException {
    super.initialize(header);
    this.m_axs.define(header);
  }
}
