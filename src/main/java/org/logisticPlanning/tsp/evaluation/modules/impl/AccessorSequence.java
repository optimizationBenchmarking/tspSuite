package org.logisticPlanning.tsp.evaluation.modules.impl;

import java.io.IOException;
import java.util.List;

import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.utils.document.spec.AbstractTextPlain;
import org.logisticPlanning.utils.document.spec.Sequence;

/**
 * A sequence for listing
 * {@link org.logisticPlanning.tsp.evaluation.data.Accessor accessors} .
 */
public final class AccessorSequence extends Sequence {

  /** the accessor set */
  private final List<Accessor> m_axss;

  /** should we print the short names? */
  private final boolean m_short;
  /** should we print the long names? */
  private final boolean m_long;
  /**
   * write the accessors in plural ({@code true}) or singular ({@code false}
   * )?
   */
  private final boolean m_plural;
  /** write the scales? */
  private final String m_scale;

  /** the output */
  private final AbstractTextPlain m_out;

  /**
   * Create the accessor sequence
   *
   * @param axss
   *          the accessors
   * @param shrt
   *          should we print the short names?
   * @param lng
   *          should we print the long names?
   * @param plural
   *          write the accessors in plural ({@code true}) or singular (
   *          {@code false})?
   * @param out
   *          the output
   */
  public AccessorSequence(final List<Accessor> axss, final boolean shrt,
      final boolean lng, final boolean plural, final AbstractTextPlain out) {
    this(axss, shrt, lng, plural, null, out);
  }

  /**
   * Create the accessor sequence
   *
   * @param axss
   *          the accessors
   * @param shrt
   *          should we print the short names?
   * @param lng
   *          should we print the long names?
   * @param scale
   *          the scale separator - write the scales? ({@code null} not
   * @param out
   *          the output
   */
  public AccessorSequence(final List<Accessor> axss, final boolean shrt,
      final boolean lng, final String scale, final AbstractTextPlain out) {
    this(axss, shrt, lng, true, scale, out);
  }

  /**
   * Create the accessor sequence
   *
   * @param axss
   *          the accessors
   * @param shrt
   *          should we print the short names?
   * @param lng
   *          should we print the long names?
   * @param plural
   *          write the accessors in plural ({@code true}) or singular (
   *          {@code false})?
   * @param scale
   *          write the scales? ({@code null} not
   * @param out
   *          the output
   */
  private AccessorSequence(final List<Accessor> axss, final boolean shrt,
      final boolean lng, final boolean plural, final String scale,
      final AbstractTextPlain out) {
    super();
    this.m_axss = axss;
    this.m_short = shrt;
    this.m_long = lng;
    this.m_out = out;
    this.m_scale = scale;
    this.m_plural = (plural || (this.m_scale != null));
  }

  /** {@inheritDoc} */
  @Override
  public final int length() {
    return this.m_axss.size();
  }

  /** {@inheritDoc} */
  @Override
  public void write(final int index) throws IOException {
    final boolean sc;
    Accessor axs;

    axs = this.m_axss.get(index);

    if (this.m_scale != null) {
      this.m_out.write("the "); //$NON-NLS-1$
    }

    if (this.m_long) {
      axs.writeLongName(this.m_out, this.m_plural);
    }
    if (this.m_short) {
      if (this.m_long) {
        this.m_out.writeChar(' ');
        this.m_out.writeChar('(');
      }
      axs.writeShortName(this.m_out, this.m_plural);
      if (this.m_long) {
        this.m_out.writeChar(')');
      }
    }

    if (this.m_scale != null) {
      sc = axs.isScaled();
      this.m_out.write(sc ? this.m_scale : //
          " does not need to be scaled "); //$NON-NLS-1$
      if (sc) {
        axs.writeScale(this.m_out);
      }
    }

  }
}
