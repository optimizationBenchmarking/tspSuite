package org.logisticPlanning.tsp.evaluation.data.properties.litComp;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.utils.collections.lists.ArrayListView;
import org.logisticPlanning.utils.collections.lists.ArraySetView;
import org.logisticPlanning.utils.document.spec.AbstractTextComplex;
import org.logisticPlanning.utils.document.spec.ECitationMode;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.bib.BibRecord;
import org.logisticPlanning.utils.text.TextUtils;

/**
 * the literature result
 */
public abstract class LiteratureResultPointSet extends
ArraySetView<LiteratureResultPoint> implements
Comparable<LiteratureResultPointSet> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the long name */
  private final String m_longName;

  /** the short name */
  private final String m_shortName;

  /** the reference records */
  private final BibRecord[] m_ref;

  /**
   * the literature result
   *
   * @param longName
   *          the long name
   * @param shortName
   *          the short name
   * @param ref
   *          the references to literature
   * @param res
   *          the result point set
   */
  public LiteratureResultPointSet(final String longName,
      final String shortName, final BibRecord[] ref,
      final LiteratureResultPoint[] res) {
    super(res);

    if ((ref == null) || (ref.length <= 0)) {
      throw new IllegalArgumentException(//
          "References must not be empty."); //$NON-NLS-1$
    }

    if ((res == null) || (res.length <= 0)) {
      throw new IllegalArgumentException(//
          "Results must not be empty."); //$NON-NLS-1$
    }

    Arrays.sort(res);
    for (final LiteratureResultPoint p : res) {
      if (p.m_set != null) {
        throw new IllegalArgumentException(//
            "Result point can only have 1 owner: " + p); //$NON-NLS-1$
      }
      p.m_set = this;
    }

    this.m_longName = TextUtils.prepare(longName);
    if (this.m_longName == null) {
      throw new IllegalArgumentException(
          "Long name must not both be null."); //$NON-NLS-1$
    }
    this.m_shortName = TextUtils.prepare(shortName);
    if (this.m_shortName == null) {
      throw new IllegalArgumentException(
          "Short name must not both be null."); //$NON-NLS-1$
    }
    this.m_ref = ref;
  }

  /**
   * get the point list for the given instance
   *
   * @param inst
   *          the problem instance
   * @return the point list for the given instance
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public final List<LiteratureResultPoint> forInstance(final Instance inst) {
    final LiteratureResultPoint[] data;
    int low, high, mid, cmp;
    LiteratureResultPoint midVal;

    data = this.m_data;
    low = 0;
    high = (data.length - 1);

    while (low <= high) {
      mid = ((low + high) >>> 1);
      midVal = data[mid];
      cmp = midVal.m_inst.compareTo(inst);

      if (cmp < 0) {
        low = (mid + 1);
      } else {
        if (cmp > 0) {
          high = (mid - 1);
        } else {

          innerA: for (low = mid; (--low) > 0;) {
            if (data[low].m_inst != inst) {
              break innerA;
            }
          }
        innerB: for (high = mid; (++high) < data.length;) {
          if (data[high].m_inst != inst) {
            break innerB;
          }
        }

          return this.subList(low + 1, high);
        }
      }
    }

    return ((List) (ArrayListView.EMPTY_LIST_VIEW));
  }

  /**
   * write the description of the measurements
   *
   * @param dest
   *          the destination
   * @throws IOException
   *           if i/o fails
   */
  public abstract void writeDescription(final AbstractTextComplex dest)
      throws IOException;

  /**
   * Initialize this result point set
   *
   * @param header
   *          the header
   * @throws IOException
   *           the i/o exception if i/o fails
   */
  protected void initialize(final Header header) throws IOException {
    //
  }

  /**
   * Cite the literature from which the results are from
   *
   * @param cm
   *          the citation mode
   * @param txt
   *          the complex text where the citation is to be put
   * @throws IOException
   *           if i/o fails
   */
  public final void cite(final ECitationMode cm,
      final AbstractTextComplex txt) throws IOException {
    txt.cite(cm, this.m_ref);
  }

  /** {@inheritDoc} */
  @Override
  public final int compareTo(final LiteratureResultPointSet o) {
    final BibRecord[] bra, brb;
    final int l;
    int i, r;

    if (o == this) {
      return 0;
    }
    if (o == null) {
      return (-1);
    }

    bra = this.m_ref;
    brb = o.m_ref;
    l = Math.min(bra.length, brb.length);
    for (i = 0; i < l; i++) {
      r = bra[i].compareTo(brb[i]);
      if (r != 0) {
        return r;
      }
    }
    return Integer.compare(bra.length, brb.length);
  }

  /**
   * Get the short name
   *
   * @return the short name
   */
  public final String getShortName() {
    return this.m_shortName;
  }

  /**
   * Get the long name
   *
   * @return the long name
   */
  public final String getLongName() {
    return this.m_longName;
  }
}
