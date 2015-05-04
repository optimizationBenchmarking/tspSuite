package org.logisticPlanning.tsp.evaluation.data.properties.ert;

import org.logisticPlanning.tsp.benchmarking.objective.Benchmark;
import org.logisticPlanning.tsp.benchmarking.objective.DataPoint;
import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.Run;
import org.logisticPlanning.tsp.evaluation.data.RunSet;
import org.logisticPlanning.utils.collections.lists.ArraySetView;
import org.logisticPlanning.utils.math.data.collection.IDataCollection;
import org.logisticPlanning.utils.math.statistics.aggregates.StableSum;

/**
 * <p>
 * A class which can compute the Estimated Running Time (ERT) to
 * success&nbsp;[<a href="#cite_HAFR2012RPBBOBES"
 * style="font-weight:bold">1</a>, <a href="#cite_AH2005PEOAALSEA"
 * style="font-weight:bold">2</a>, <a href="#cite_P1997DEVTFOT2I"
 * style="font-weight:bold">3</a>] and store it as an array for several
 * different threshold values. This class stores the real, absolute
 * objective value thresholds as well.
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
 * <li><div><span id="cite_AH2005PEOAALSEA" /><a
 * href="http://www.lri.fr/~auger/">Anne Auger</a> and&nbsp;<a
 * href="http://www.lri.fr/~hansen/">Nikolaus Hansen</a>: <span
 * style="font-weight:bold">&ldquo;Performance Evaluation of an Advanced
 * Local Search Evolutionary Algorithm,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the IEEE
 * Congress on Evolutionary Computation (CEC'05)</span>,
 * September&nbsp;2&ndash;5, 2005, Edinburgh, Scotland, UK, pages
 * 1777&ndash;1784, <a
 * href="http://www.macs.hw.ac.uk/staff-directory/david-wolfe-corne.htm"
 * >David Wolfe Corne</a>, <a
 * href="http://cs.adelaide.edu.au/~zbyszek/">Zbigniew Michalewicz</a>, <a
 * href="http://sc.snu.ac.kr/~rim/index.html">Robert Ian McKay</a>, <a
 * href="http://www.cs.vu.nl/~gusz/">&#193;goston E. Eiben</a> aka.
 * Gusz/Guszti, <a
 * href="http://www.natural-selection.com/people_dfogel.html">David B.
 * Fogel</a>, Carlos M. Fonseca, <a
 * href="https://www.ads.tuwien.ac.at/raidl/">G&#252;nther R. Raidl</a>, <a
 * href="http://vlab.ee.nus.edu.sg/~kctan/">Kay Chen Tan</a>, and&nbsp;Ali
 * M. S. Zalzala, editors, Piscataway, NJ, USA: IEEE Computer Society.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780393635">0-7803-9363-5</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780393639">978-0-7803
 * -9363-9</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CEC.2005.1554903">10.1109/CEC.2005
 * .1554903</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/62773008">62773008</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=-QIVAAAACAAJ">-QIVAAAACAAJ</a>;
 * INSPEC Accession Number:&nbsp;8715465. <div>link: [<a
 * href="http://www.lri.fr/~hansen/cec2005localcmaes.pdf">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.71.6248">10.1
 * .1.71 .6248</a></div></div></li>
 * <li><div><span id="cite_P1997DEVTFOT2I" />Kenneth V. Price: <span
 * style="font-weight:bold">&ldquo;Differential Evolution vs. the Functions
 * of the 2nd ICEO,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the IEEE
 * International Conference on Evolutionary Computation (CEC'97)</span>,
 * April&nbsp;13&ndash;16, 1997, Indianapolis, IN, USA, pages
 * 153&ndash;157, <a href="http://www.liacs.nl/~baeck/contact.htm">Thomas
 * B&#228;ck</a>, <a href="http://cs.adelaide.edu.au/~zbyszek/">Zbigniew
 * Michalewicz</a>, and&nbsp;<a href="http://www.cs.bham.ac.uk/~xin/">Xin
 * Yao</a> <span style="color:gray">[&#23002;&#26032;</span>], editors,
 * Piscataway, NJ, USA: IEEE Computer Society. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780339495">0-7803-3949-5</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780339491">978-0-7803
 * -3949-1</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/ICEC.1997.592287">10.1109/ICEC.1997
 * .592287</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=6JZVAAAAMAAJ">6JZVAAAAMAAJ</a>;
 * INSPEC Accession Number:&nbsp;5573043</div></li>
 * </ol>
 */
public final class ERTSeries extends ArraySetView<ERTPoint> implements
IDataCollection {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the empty ert point */
  private static final ERTPoint[] __EMPTY = new ERTPoint[0];

  /**
   * the total number of runs
   *
   * @serial an integer denoting the total number of runs
   */
  private final int m_total;

  /**
   * the accessor
   *
   * @serial the accessor object
   */
  private final Accessor m_axs;

  /**
   * instantiate
   *
   * @param data
   *          the data
   * @param total
   *          the total number of runs
   * @param axs
   *          the accessor
   */
  private ERTSeries(final ERTPoint[] data, final int total,
      final Accessor axs) {
    super(data);
    this.m_total = total;
    this.m_axs = axs;
  }

  /**
   * Get the total number of runs
   *
   * @return the total number of runs
   */
  public final int getRunCount() {
    return this.m_total;
  }

  /**
   * Get the accessor
   *
   * @return the accessor
   */
  public final Accessor getAccessor() {
    return this.m_axs;
  }

  /**
   * check the accessor
   *
   * @param axs
   *          the accessor
   */
  static final void _checkAccessor(final Accessor axs) {
    if (axs.isObjective()) {
      throw new IllegalArgumentException(//
          "Online time-related dimensions can be the basis of ERT computations, not objective-related ones, but supplied was " + axs); //$NON-NLS-1$
    }
  }

  /**
   * make an ert series
   *
   * @param rs
   *          the run set
   * @param thresholds
   *          the thresholds, or {@code null} to use the benchmark log
   *          thresholds
   * @param axs
   *          the accessor
   * @return the ert series
   */
  public static final ERTSeries makeERTSeries(final RunSet rs,
      final long[] thresholds, final Accessor axs) {
    final long[] thresholdsF;
    final long opt;
    final ERTPoint[] temp, res;
    final int total;
    int ti;

    ERTSeries._checkAccessor(axs);

    if ((rs == null) || ((total = rs.size()) <= 0)) {
      return new ERTSeries(ERTSeries.__EMPTY, 0, axs);
    }

    opt = rs.getInstance().optimum();
    thresholdsF = ((thresholds != null) ? thresholds : //
      Benchmark.getLogObjectiveValues(opt));

    temp = new ERTPoint[thresholdsF.length - 3];

    ti = ((axs.canAccessAsLong()) ? //
        ERTSeries.__makeERTSeriesL(rs, opt, thresholdsF, axs, temp)//
        : ERTSeries.__makeERTSeriesD(rs, opt, thresholdsF, axs, temp));

    if (ti == temp.length) {
      return new ERTSeries(temp, total, axs);
    }

    if (ti <= 0) {
      return new ERTSeries(ERTSeries.__EMPTY, total, axs);
    }

    res = new ERTPoint[ti];
    System.arraycopy(temp, 0, res, 0, ti);
    return new ERTSeries(res, total, axs);
  }

  /**
   * make an ert series by using {@code double} precision floating point
   * numbers
   *
   * @param rs
   *          the run set
   * @param thresholdsF
   *          the thresholds
   * @param opt
   *          the optimal value
   * @param axs
   *          the accessor
   * @param temp
   *          the buffer to write to
   * @return the number of collected points
   */
  private static final int __makeERTSeriesD(final RunSet rs,
      final long opt, final long[] thresholdsF, final Accessor axs,
      final ERTPoint[] temp) {
    final StableSum rt;
    int ti;
    DataPoint p;
    int succ, lastSucc;
    double rtV, lastRT;

    ti = 0;
    lastSucc = succ = -1;
    lastRT = rtV = 0d;
    rt = new StableSum();

    for (final long t : thresholdsF) {
      if ((t <= 0l) || (t >= Long.MAX_VALUE)) {
        continue;
      }

      lastSucc = succ;
      lastRT = rtV;

      succ = 0;
      rt.reset();
      for (final Run r : rs) {
        p = r.findBestF(t);
        if (p != null) {
          succ++;
        } else {
          p = r.last();
        }

        rt.visitDouble(axs.fromPoint(p));
      }

      rtV = rt.getResult();
      if ((lastSucc != succ) || (rtV != lastRT)) {
        temp[ti++] = new ERTPoint(t,//
            ((t - opt) / ((double) opt)),//
            (succ <= 0) ? (Double.POSITIVE_INFINITY) : (rtV / succ), succ);
      }
    }

    return ti;
  }

  /**
   * make an ert series by performing integer computations based on
   * {@code long} values
   *
   * @param rs
   *          the run set
   * @param thresholdsF
   *          the thresholds
   * @param opt
   *          the optimal value
   * @param axs
   *          the accessor
   * @param temp
   *          the buffer to write to
   * @return the number of collected points
   */
  private static final int __makeERTSeriesL(final RunSet rs,
      final long opt, final long[] thresholdsF, final Accessor axs,
      final ERTPoint[] temp) {
    final StableSum rtX;
    int ti, index;
    DataPoint p;
    int succ, lastSucc;
    long t, rt, lastRT, ch;
    double lastRTX, rtXV;

    ti = 0;
    lastSucc = succ = -1;
    lastRT = rt = 0l;
    lastRTX = rtXV = 0d;
    rtX = new StableSum();

    looper: for (index = 0; index < thresholdsF.length; index++) {
      t = thresholdsF[index];
      if ((t <= 0l) || (t >= Long.MAX_VALUE)) {
        continue;
      }

      lastSucc = succ;
      if (rt >= 0l) {
        lastRTX = lastRT = rt;
      } else {
        lastRTX = rtXV;
        lastRT = Math.round(Math.ceil(rtXV));
      }

      computeWithLongPrecision: {
        // first, we try to compute the result by using long precision
        // integers
        // if that fails, we will use double precision floating point
        // numbers
        succ = 0;
        rt = 0l;
        for (final Run r : rs) {
          p = r.findBestF(t);
          if (p != null) {
            succ++;
          } else {
            p = r.last();
          }

          ch = rt;
          rt += axs.fromPointLong(p);
          if (ch > rt) {// overflow: cannot use long
            break computeWithLongPrecision;
          }
        }

        if ((lastSucc != succ) || (rt != lastRT)) {
          temp[ti++] = new ERTPoint(t,//
              ((t - opt) / ((double) opt)),//
              (succ <= 0) ? (Double.POSITIVE_INFINITY)
                  : (rt / ((double) (succ))), succ);
        }

        // that went OK: no overflow -> next loop iteration
        continue looper;
      }

      // overflow! now compute with double precision floats, using stable
      // sums
      rt = (-1l);
      rtX.reset();
      succ = 0;

      for (final Run r : rs) {
        p = r.findBestF(t);
        if (p != null) {
          succ++;
        } else {
          p = r.last();
        }

        rtX.visitLong(axs.fromPointLong(p));
      }

      rtXV = rtX.getResult();
      if ((lastSucc != succ) || (rtXV != lastRTX)) {
        temp[ti++] = new ERTPoint(t,//
            ((t - opt) / ((double) opt)),//
            (succ <= 0) ? (Double.POSITIVE_INFINITY) : (rtXV / succ), succ);
      }
    }

    // ok, finished without overflow, perfect!
    return ti;
  }

  /**
   * Get the data point representing the information available at the given
   * best known objective value
   *
   * @param f
   *          the best known objective value
   * @return the data point representing the information available at the
   *         given best known objective value
   */
  public final ERTPoint forBestRelF(final double f) {
    final ERTPoint[] ps;
    ERTPoint midVal;
    int low, high, mid, cmp;

    ps = this.m_data;
    low = 0;
    high = (ps.length - 1);

    while (low <= high) {
      mid = ((low + high) >>> 1);
      midVal = ps[mid];
      cmp = java.lang.Double.compare(midVal.m_relThreshold, f);

      if (cmp < 0) {
        low = (mid + 1);
      } else {
        if (cmp > 0) {
          high = (mid - 1);
        } else {
          return midVal;
        }
      }
    }

    if (low <= 0) {
      return null;
    }

    return ps[low - 1];
  }

  /**
   * Get the data point representing the information available at the given
   * best known objective value
   *
   * @param f
   *          the best known objective value
   * @return the data point representing the information available at the
   *         given best known objective value
   */
  public final ERTPoint forBestF(final long f) {
    final ERTPoint[] ps;
    ERTPoint midVal;
    int low, high, mid, cmp;

    ps = this.m_data;
    low = 0;
    high = (ps.length - 1);

    while (low <= high) {
      mid = ((low + high) >>> 1);
      midVal = ps[mid];
      cmp = java.lang.Long.compare(midVal.m_threshold, f);

      if (cmp < 0) {
        low = (mid + 1);
      } else {
        if (cmp > 0) {
          high = (mid - 1);
        } else {
          return midVal;
        }
      }
    }

    if (low <= 0) {
      return null;
    }

    return ps[low - 1];
  }

  /** {@inheritDoc} */
  @Override
  public final int dimension() {
    return ERTPoint.ERT_DIMENSIONS;
  }

  /** {@inheritDoc} */
  @Override
  public final double get(final int point, final int dimension) {
    final ERTPoint p;

    p = this.m_data[point];

    switch (dimension) {
      case ERTPoint.DIM_RELATIVE_THRESHOLD: {
        return p.m_relThreshold;
      }
      case ERTPoint.DIM_THRESHOLD: {
        return p.m_threshold;
      }
      case ERTPoint.DIM_ERT: {
        return p.m_ert;
      }
      case ERTPoint.DIM_SUCCESSFUL: {
        return p.m_successful;
      }
      default: {
        return p.get(dimension);
      }
    }
  }

}
