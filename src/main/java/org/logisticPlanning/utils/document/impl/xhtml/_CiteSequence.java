package org.logisticPlanning.utils.document.impl.xhtml;

import java.io.IOException;

import org.logisticPlanning.utils.document.spec.BibReference;
import org.logisticPlanning.utils.document.spec.Sequence;

/**
 * the internal class for writing citations references
 */
final class _CiteSequence extends Sequence {

  /** the labels */
  private final BibReference[] m_refs;

  /** the document */
  private final XHTMLDocument m_doc;

  /** is this in name ? */
  private final boolean m_names;

  /**
   * create
   * 
   * @param refs
   *          the refs
   * @param doc
   *          the document
   * @param names
   *          print names
   */
  _CiteSequence(final BibReference[] refs, final XHTMLDocument doc,
      final boolean names) {
    super();
    this.m_refs = refs;
    this.m_doc = doc;
    this.m_names = names;
  }

  /** {@inheritDoc} */
  @Override
  public final int length() {
    return this.m_refs.length;
  }

  /** {@inheritDoc} */
  @Override
  public final void write(final int index) throws IOException {
    this.m_doc._printRef(this.m_names, this.m_refs[index]);
  }
}
