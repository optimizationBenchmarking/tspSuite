package org.logisticPlanning.tsp.evaluation.data.properties.litComp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.EPropertyType;
import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.data.Property;
import org.logisticPlanning.tsp.evaluation.data.RunSet;
import org.logisticPlanning.tsp.evaluation.data.properties.StatisticRunProperty;
import org.logisticPlanning.tsp.evaluation.data.properties.litComp.results.LiteratureSurvey;
import org.logisticPlanning.utils.document.spec.Document;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.math.statistics.series.StatisticSeries;

/**
 * This property compares an experiment to results from literature.
 */
public final class LiteratureComparisonProperty extends
    Property<Experiment, LiteratureComparisonResult> {

  /** the globally shared instance */
  public static final LiteratureComparisonProperty INSTANCE = new LiteratureComparisonProperty();

  /** create */
  private LiteratureComparisonProperty() {
    super(EPropertyType.TEMPORARILY_STORED);
  }

  /** {@inheritDoc} */
  @Override
  public final void initialize(final Header header) throws IOException {
    LiteratureSurvey.RESULTS._initialize(header);
  }

  /** {@inheritDoc} */
  @SuppressWarnings("unchecked")
  @Override
  protected final LiteratureComparisonResult compute(
      final Experiment dataset, final Document doc) {
    final StatisticSeries[][] sers;
    final StatisticRunProperty[][] props;
    final ArrayList<LiteratureComparisonPoint>[] comps;
    final LiteratureResults src;
    final int acnt;
    int litIdx, dimX, dimY, sz;
    LiteratureResultPointSet literature;
    LiteratureComparisonPoint newP;
    List<LiteratureResultPoint> list;
    ArrayList<LiteratureComparisonPoint> comp;
    ArrayList<LiteratureComparisonPointSet> ps;
    ESingleComparison newC;
    StatisticRunProperty prop;
    StatisticSeries ser;

    src = LiteratureSurvey.RESULTS;
    acnt = Accessor.ACCESSORS.size();
    sers = new StatisticSeries[acnt][acnt];
    props = new StatisticRunProperty[acnt][acnt];
    comps = new ArrayList[src.size()];

    // for each experiment instance set in the experiment
    for (final RunSet runSet : dataset) {

      // for each literature result point set
      innerLoop: for (litIdx = src.size(); (--litIdx) >= 0;) {
        literature = src.get(litIdx);

        list = literature.forInstance(runSet.getInstance());
        if ((list == null) || (list.size() <= 0)) {
          continue innerLoop;
        }

        comp = comps[litIdx];
        if (comp == null) {
          comps[litIdx] = comp = new ArrayList<>();
        }

        for (final LiteratureResultPoint point : list) {
          dimX = point.m_timeDim.ordinal();
          dimY = point.m_resultDim.ordinal();

          // obtain the right series
          ser = sers[dimX][dimY];
          if (ser == null) {
            prop = props[dimX][dimY];
            if (prop == null) {
              props[dimX][dimY] = prop = StatisticRunProperty.get(dimX,
                  dimY, Integer.MAX_VALUE);
            }

            sers[dimX][dimY] = ser = prop.get(runSet, doc);
          }

          newP = new LiteratureComparisonPoint(point, ser);
          newC = newP.getComparisonResult();
          if ((newC != ESingleComparison.NOT_COMPARABLE)
              && (newC != ESingleComparison.MAYBE_NOT_COMPARABLE)) {
            comp.add(newP);
          }
        }
      }

      // clear all series
      for (final StatisticSeries[] s : sers) {
        Arrays.fill(s, null);
      }
    }

    // now aggregate
    ps = null;
    for (final ArrayList<LiteratureComparisonPoint> c : comps) {
      if ((c == null) || ((sz = c.size()) <= 0)) {
        continue;
      }
      if (ps == null) {
        ps = new ArrayList<>();
      }
      ps.add(new LiteratureComparisonPointSet(//
          c.get(0).getLiteratureResultPoint().m_set,//
          c.toArray(new LiteratureComparisonPoint[sz])));//
    }

    if ((ps == null) || ((sz = ps.size()) <= 0)) {
      return null;
    }
    return new LiteratureComparisonResult(
        ps.toArray(new LiteratureComparisonPointSet[sz]));
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equals(final Object o) {
    return (o instanceof LiteratureComparisonProperty);
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return LiteratureComparisonProperty.class.hashCode();
  }
}
