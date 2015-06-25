package org.logisticPlanning.tsp.evaluation.modules.impl.single.litComp;

import java.io.IOException;

import org.logisticPlanning.tsp.evaluation.data.properties.litComp.LiteratureComparisonResult;
import org.logisticPlanning.tsp.evaluation.data.properties.litComp.LiteratureResultPointSet;
import org.logisticPlanning.utils.document.spec.AbstractTextComplex;
import org.logisticPlanning.utils.document.spec.ECitationMode;
import org.logisticPlanning.utils.document.spec.Sequence;

/**
 * the internal algorithm printer sequence
 */
final class _CmpAlgoSeq extends Sequence {

  /** the result */
  private final LiteratureComparisonResult m_rc;

  /** the text */
  private final AbstractTextComplex m_txt;

  /**
   * create
   *
   * @param rc
   *          the rc
   * @param txt
   *          the text
   */
  _CmpAlgoSeq(final LiteratureComparisonResult rc,
      final AbstractTextComplex txt) {
    super();
    this.m_rc = rc;
    this.m_txt = txt;
  }

  /** {@inheritDoc} */
  @Override
  public final int length() {
    return this.m_rc.size();
  }

  /** {@inheritDoc} */
  @Override
  public final void write(final int index) throws IOException {
    final LiteratureResultPointSet ps;

    ps = this.m_rc.get(index).getLiteratureResultPointSet();

    this.m_txt.write("the ");//$NON-NLS-1$
    this.m_txt.write(ps.getShortName());
    ps.cite(ECitationMode.BY_ID_IN_SENTENCE, this.m_txt);
  }
}
