package org.logisticPlanning.utils.document.spec;

import org.logisticPlanning.utils.document.spec.bib.BibRecord;
import org.logisticPlanning.utils.text.TextUtils;
import org.logisticPlanning.utils.text.transformations.NormalCharTransformer;

/**
 * a holder for a bibliographic reference
 */
public class BibReference {

  /** the index of the reference */
  private final int m_index;

  /** an automatically assigned unique id */
  private final String m_id;

  /** the record */
  private final BibRecord m_record;

  /**
   * Create a bibliographic reference
   *
   * @param index
   *          the index
   * @param id
   *          the id
   * @param record
   *          the record
   */
  protected BibReference(final int index, final String id,
      final BibRecord record) {
    super();

    String s;

    if (index <= 0) {
      throw new IllegalArgumentException(//
          "Reference index cannot be " + index); //$NON-NLS-1$
    }

    if (record == null) {
      throw new IllegalArgumentException(//
          "Bibliography record cannot be null."); //$NON-NLS-1$
    }

    s = TextUtils.prepare(id);
    if (s == null) {
      throw new IllegalArgumentException(//
          "Bibliography id cannot be null."); //$NON-NLS-1$
    }

    s = TextUtils.prepare(NormalCharTransformer.INSTANCE.transform(s));
    if (s == null) {
      throw new IllegalArgumentException(//
          "Bibliography id must have non-empty normal name."); //$NON-NLS-1$
    }

    this.m_index = index;
    this.m_record = record;
    this.m_id = id;
  }

  /**
   * Get the index of this bibliographic entry
   *
   * @return the index of this bibliographic entry
   */
  public final int getIndex() {
    return this.m_index;
  }

  /**
   * Get the automatically assigned unique key of this reference
   *
   * @return the automatically assigned unique key of this reference
   */
  public final String getKey() {
    return this.m_id;
  }

  /**
   * Get the bibliographic record
   *
   * @return the bibliographic record
   */
  public final BibRecord getRecord() {
    return this.m_record;
  }
}
