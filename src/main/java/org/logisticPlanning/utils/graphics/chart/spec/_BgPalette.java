package org.logisticPlanning.utils.graphics.chart.spec;

import java.awt.Color;

import org.logisticPlanning.utils.graphics.ColorPalette;

/**
 * An internal palette for background data. This palette takes a color from
 * the foreground palette and tries to gray-ify it.
 */
final class _BgPalette extends ColorPalette {

  /** the owner */
  private final ChartPalette m_owner;

  /**
   * create the palette
   *
   * @param owner
   *          the owner
   * @param sync
   *          the synchronizer
   * @param maxColor
   *          the max color
   */
  _BgPalette(final ChartPalette owner, final Object sync,
      final int maxColor) {
    super(sync, maxColor, false);
    this.m_owner = owner;
  }

  /** {@inheritDoc} */
  @Override
  protected final Color calculateColor(final int index) {
    final Color c;
    c = this.m_owner.getForegroundDataColor(index);
    return new Color((0.55f + (c.getRed() * 1e-3f)), //
        (0.55f + (c.getGreen() * 1e-3f)), //
        (0.55f + (c.getBlue() * 1e-3f)));
  }
}
