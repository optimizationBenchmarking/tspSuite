package org.logisticPlanning.tsp.evaluation.data.properties.ert;

import org.logisticPlanning.utils.math.data.point.Point;
import org.logisticPlanning.utils.utils.HashUtils;

/**
 * <p>
 * A point that holds one tuple of information about the Estimated Running
 * Time (ERT) to success&nbsp;[<a href="#cite_HAFR2012RPBBOBES"
 * style="font-weight:bold">1</a>, <a href="#cite_AH2005PEOAALSEA"
 * style="font-weight:bold">2</a>, <a href="#cite_P1997DEVTFOT2I"
 * style="font-weight:bold">3</a>] of an algorithm for a given problem.
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
public final class ERTPoint extends Point implements Comparable<ERTPoint> {
  /** the serial version uid */
  private static final long serialVersionUID = 1l;

  /** the relative threshold dimension */
  public static final int DIM_RELATIVE_THRESHOLD = 0;
  /** the threshold dimension */
  public static final int DIM_THRESHOLD = (ERTPoint.DIM_RELATIVE_THRESHOLD + 1);
  /** the ert dimension */
  public static final int DIM_ERT = (ERTPoint.DIM_THRESHOLD + 1);
  /** the successful counter dimension */
  public static final int DIM_SUCCESSFUL = (ERTPoint.DIM_ERT + 1);
  /** the total dimension of an ert point */
  public static final int ERT_DIMENSIONS = (ERTPoint.DIM_SUCCESSFUL + 1);

  /** the relative threshold */
  final double m_relThreshold;
  /** the threshold */
  final long m_threshold;
  /** the ert */
  final double m_ert;
  /** the number of successful runs */
  final int m_successful;

  /**
   * instantiate
   *
   * @param t
   *          the threshold
   * @param rt
   *          the relative threshold
   * @param ert
   *          the ert
   * @param succ
   *          the number of successful runs
   */
  ERTPoint(final long t, final double rt, final double ert, final int succ) {
    super();

    if ((rt < 0d) || (rt != rt)) {
      throw new IllegalArgumentException(//
          "Relative threshold must be larger or equal to zero, but is " + rt); //$NON-NLS-1$
    }
    if (t <= 0l) {
      throw new IllegalArgumentException(//
          "Threshold must be larger than zero, but is " + t); //$NON-NLS-1$
    }
    if ((ert < 0d) || (ert != ert)) {
      throw new IllegalArgumentException(//
          "ERT must be larger or equal to zero, but is " + ert); //$NON-NLS-1$
    }
    if (succ < 0) {
      throw new IllegalArgumentException(//
          "Number of successful runs must be larger or equal to zero, but is " + succ); //$NON-NLS-1$
    }

    this.m_relThreshold = rt;
    this.m_threshold = t;
    this.m_ert = ert;
    this.m_successful = succ;
  }

  /**
   * Get the relative threshold
   *
   * @return the relative threshold
   */
  public final double getRelThreshold() {
    return this.m_relThreshold;
  }

  /**
   * Get the threshold
   *
   * @return the threshold
   */
  public final double getThreshold() {
    return this.m_threshold;
  }

  /**
   * Get the ert
   *
   * @return the ert
   */
  public final double getERT() {
    return this.m_ert;
  }

  /**
   * Get the number of successful runs
   *
   * @return the number of successful runs
   */
  public final int getSuccessful() {
    return this.m_successful;
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return new StringBuilder().append("th: "). //$NON-NLS-1$
        append(this.m_threshold).append(", rth: ").//$NON-NLS-1$
        append(this.m_relThreshold).append(", ert: ").//$NON-NLS-1$
        append(this.m_ert).toString();
  }

  /** {@inheritDoc} */
  @Override
  public final double get(final int dimension) {
    switch (dimension) {
      case DIM_RELATIVE_THRESHOLD: {
        return this.m_relThreshold;
      }
      case DIM_THRESHOLD: {
        return this.m_threshold;
      }
      case DIM_ERT: {
        return this.m_ert;
      }
      case DIM_SUCCESSFUL: {
        return this.m_successful;
      }
      default: {
        throw new IndexOutOfBoundsException(//
            "Dimension not permitted " + dimension + //$NON-NLS-1$
                " for point " + this);//$NON-NLS-1$
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public final int dimension() {
    return ERTPoint.ERT_DIMENSIONS;
  }

  /** {@inheritDoc} */
  @Override
  public final int compareTo(final ERTPoint o) {
    int c;

    if (o == this) {
      return 0;
    }
    if (o == null) {
      return (-1);
    }

    c = Long.compare(this.m_threshold, o.m_threshold);
    if (c != 0) {
      return c;
    }

    c = Double.compare(this.m_relThreshold, o.m_relThreshold);
    if (c != 0) {
      return c;
    }

    c = Double.compare(this.m_ert, o.m_ert);
    if (c != 0) {
      return c;
    }

    return Integer.compare(this.m_successful, o.m_successful);
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equals(final Object o) {
    final ERTPoint p;

    if (o == this) {
      return true;
    }
    if (o instanceof ERTPoint) {
      p = ((ERTPoint) o);
      return ((this.m_threshold == p.m_threshold) && //
          (this.m_successful == p.m_successful) && //
          (this.m_relThreshold == p.m_relThreshold) && //
      (this.m_ert == p.m_ert));
    }

    return false;
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return HashUtils.combineHashes(//
        HashUtils.combineHashes(//
            HashUtils.hashCode(this.m_threshold),//
            HashUtils.hashCode(this.m_relThreshold)),//
        HashUtils.combineHashes(//
            HashUtils.hashCode(this.m_ert),//
            HashUtils.hashCode(this.m_threshold)));//
  }
}
