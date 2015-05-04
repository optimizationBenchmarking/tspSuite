package org.logisticPlanning.utils.document.impl.xhtml;

import java.io.IOException;

import org.logisticPlanning.utils.document.spec.ELabelType;
import org.logisticPlanning.utils.document.spec.Sequence;
import org.logisticPlanning.utils.document.spec.SingleLabel;
import org.logisticPlanning.utils.utils.comparison.ComparisonUtils;

/**
 * the internal class for writing references
 */
final class _RefSequence extends Sequence {

  /** the labels */
  private final SingleLabel[] m_labels;

  /** the document */
  private final XHTMLDocument m_doc;

  /** the last label type */
  private String m_last;

  /** is this in from-to mode? */
  private final boolean m_fromTo;

  /**
   * create
   *
   * @param labels
   *          the labels
   * @param doc
   *          the document
   * @param fromTo
   *          is this in from-to mode?
   */
  _RefSequence(final SingleLabel[] labels, final XHTMLDocument doc,
      final boolean fromTo) {
    super();
    this.m_labels = labels;
    this.m_doc = doc;
    this.m_fromTo = fromTo;
  }

  /** {@inheritDoc} */
  @Override
  public final int length() {
    return this.m_labels.length;
  }

  /** {@inheritDoc} */
  @Override
  public final void write(final int index) throws IOException {
    final SingleLabel l;
    final String v;
    final ELabelType z;
    final boolean plural;

    l = this.m_labels[index];
    z = l.getType();
    v = z.single();

    if (!(ComparisonUtils.equals(v, this.m_last))) {

      if (index < (this.m_labels.length - 1)) {
        plural = (ComparisonUtils.equals(this.m_labels[//
                                                       this.m_fromTo ? //
                                                           (this.m_labels.length - 1)
                                                           : //
                                                             (index + 1)].getType().single(), v));
      } else {
        plural = false;
      }

      this.m_doc.write(plural ? z.multiple() : v);
      this.m_doc.writeNoneBreakingSpace();

      this.m_last = v;
    }

    this.m_doc.writeSingleRef(l);
  }
}
