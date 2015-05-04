package org.logisticPlanning.utils.graphics.chart.spec;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Arrays;

import org.logisticPlanning.utils.graphics.ColorPalette;

/**
 * The base class for providing the styles for drawing charts. Instances of
 * this class provide all colors, strokes, and fonts to be used. It is
 * guaranteed that all method calls with the same parameters will return
 * the same results. This class implements some default behavior and
 * returns default data.
 */
public class ChartPalette {

  /** the default font for diagrams */
  private static final String DEFAULT_FONT = "SansSerif";//$NON-NLS-1$

  /** max colors */
  private static final int MAX_LIST_STUFF = 1024;

  /** the font choices */
  private static final String[] FONT_CHOICES = { "calibri",//$NON-NLS-1$
      "helvetica",//$NON-NLS-1$
      "arial",//$NON-NLS-1$
      "times new roman",//$NON-NLS-1$
      "cmr", //$NON-NLS-1$
      "cmr7" //$NON-NLS-1$
  };

  /** the default dashing */
  private static final float DEFAULT_DASH_WIDTH = 2f;

  /** the base font size */
  private static final int BASE_FONT_SIZE = 7;

  /** the characters that the font must know */
  private static final char[] FONT_TEST_SEQUENCE = { ' ', '0', '9', 'A',
      'a', 'Z', 'z', '-', '+', '*', '#', '/', '\\', '_', '>', '<', '@' };

  /** the synchronizer object */
  private final Object m_sync;

  /** the base font */
  private Font m_baseFont;

  /** the base stroke from which the others are derived */
  private Stroke m_baseStroke;

  /** the background color */
  private Color m_backgroundColor;

  /** the default foreground color */
  private Color m_foregroundColor;

  /** the default axis color */
  private Color m_axisColor;

  /** the stroke for the axes */
  private Stroke m_axisStroke;

  /** the axis title font */
  private Font m_axisTitleFont;

  /** the axis title color */
  private Color m_axisTitleColor;

  /** the axis tick font */
  private Font m_axisTickFont;
  /** the axis tick font */
  private Color m_axisTickColor;
  /** the stroke for the axis ticks */
  private Stroke m_axisTickStroke;
  /** do we paint axis ticks? */
  private int m_useAxisTicks;

  /** the legend font */
  private Font m_legendFont;

  /** the foreground data colors */
  private final ColorPalette m_foregroundDataColors;

  /** the stroke for the foreground data */
  private final ArrayList<Stroke> m_foregroundDataStrokes;

  /** the background data colors */
  private final ColorPalette m_backgroundDataColors;
  /** the stroke for the background data */
  private final ArrayList<Stroke> m_backgroundDataStrokes;

  /** the grid line color */
  private Color m_gridLineColor;
  /** the stroke for grid */
  private Stroke m_gridLineStroke;
  /** do we paint grid lines? */
  private int m_useGridLines;

  /** create */
  public ChartPalette() {
    super();
    this.m_foregroundDataStrokes = new ArrayList<>();
    this.m_backgroundDataStrokes = new ArrayList<>();
    this.m_sync = this.m_backgroundDataStrokes;
    this.m_foregroundDataColors = this.createForegroundDataColors();
    this.m_backgroundDataColors = this.createBackgroundDataColors();
  }

  /**
   * Create the palette for the foreground data colors
   *
   * @return the palette for the foreground data colors
   */
  protected ColorPalette createForegroundDataColors() {
    return new ColorPalette(this.m_sync, ChartPalette.MAX_LIST_STUFF, true);
  }

  /**
   * Create the palette for the background data colors
   *
   * @return the palette for the background data colors
   */
  protected ColorPalette createBackgroundDataColors() {
    return new _BgPalette(this, this.m_sync, ChartPalette.MAX_LIST_STUFF);
  }

  /**
   * calculate the base font
   *
   * @return the base font
   */
  protected Font calculateBaseFont() {
    String[] have;
    Font f;
    int idx;

    have = GraphicsEnvironment.getLocalGraphicsEnvironment()
        .getAvailableFontFamilyNames();

    if (have != null) {
      Arrays.sort(have);
      for (final String n : ChartPalette.FONT_CHOICES) {
        idx = Arrays.binarySearch(have, n, String.CASE_INSENSITIVE_ORDER);
        if (idx >= 0) {
          f = ChartPalette.__makeFont(have[idx]);
          if (ChartPalette.__checkFont(f)) {
            return f;
          }
        }
      }
    }

    return ChartPalette.__makeFont(ChartPalette.DEFAULT_FONT);
  }

  /**
   * make a font from a font name
   *
   * @param name
   *          the name
   * @return the font
   */
  private static final Font __makeFont(final String name) {
    return new Font(name, Font.PLAIN, ChartPalette.BASE_FONT_SIZE);
  }

  /**
   * check a font
   *
   * @param f
   *          the font
   * @return {@code true} if the font is ok, {@code false} otherwise
   */
  private static final boolean __checkFont(final Font f) {
    if (f == null) {
      return false;
    }
    try {

      if (f.getSize() <= 0) {
        return false;
      }

      for (final char ch : ChartPalette.FONT_TEST_SEQUENCE) {
        if (!(f.canDisplay(ch))) {
          return false;
        }
      }

      return true;
    } catch (final Throwable t) {
      return false;
    }

  }

  /**
   * Obtain the base font
   *
   * @return the base font
   */
  protected final Font getBaseFont() {
    if (this.m_baseFont == null) {
      synchronized (this.m_sync) {
        if (this.m_baseFont == null) {
          this.m_baseFont = this.calculateBaseFont();
        }
      }
    }
    return this.m_baseFont;
  }

  /**
   * Calculate the base stroke
   *
   * @return the base stroke
   */
  protected Stroke calculateBaseStroke() {
    return new BasicStroke(1f, BasicStroke.CAP_ROUND,
        BasicStroke.JOIN_BEVEL);
  }

  /**
   * Get the base stroke
   *
   * @return the base stroke
   */
  protected final Stroke getBaseStroke() {
    if (this.m_baseStroke == null) {
      synchronized (this.m_sync) {
        if (this.m_baseStroke == null) {
          this.m_baseStroke = this.calculateBaseStroke();
        }
      }
    }
    return this.m_baseStroke;
  }

  /**
   * get the background paint
   *
   * @return the background paint
   */
  protected Color calculateBackgroundColor() {
    return Color.WHITE;
  }

  /**
   * get the background paint
   *
   * @return the background paint
   */
  public final Color getBackgroundColor() {
    if (this.m_backgroundColor == null) {
      synchronized (this.m_sync) {
        if (this.m_backgroundColor == null) {
          this.m_backgroundColor = this.calculateBackgroundColor();
        }
      }
    }
    return this.m_backgroundColor;
  }

  /**
   * get the foreground paint
   *
   * @return the foreground paint
   */
  protected Color calculateForegroundColor() {
    return Color.BLACK;
  }

  /**
   * get the foreground paint, which is used by several other color getters
   * as default
   *
   * @return the foreground paint
   */
  protected final Color getForegroundColor() {
    if (this.m_foregroundColor == null) {
      synchronized (this.m_sync) {
        if (this.m_foregroundColor == null) {
          this.m_foregroundColor = this.calculateForegroundColor();
        }
      }
    }
    return this.m_foregroundColor;
  }

  /**
   * get the paint to use to paint the axis lines
   *
   * @return the color to use to paint axis lines
   */
  protected Color calculateAxisColor() {
    return this.getForegroundColor();
  }

  /**
   * get the paint to use to paint the axis lines
   *
   * @return the color to use to paint axis lines
   */
  public final Color getAxisColor() {
    if (this.m_axisColor == null) {
      synchronized (this.m_sync) {
        if (this.m_axisColor == null) {
          this.m_axisColor = this.calculateAxisColor();
        }
      }
    }
    return this.m_axisColor;
  }

  /**
   * Calculate the axis stroke
   *
   * @return the axis stroke
   */
  protected Stroke calculateAxisStroke() {
    return this.getBaseStroke();
  }

  /**
   * Get the axis stroke
   *
   * @return the axis stroke
   */
  public final Stroke getAxisStroke() {
    if (this.m_axisStroke == null) {
      synchronized (this.m_sync) {
        this.m_axisStroke = this.calculateAxisStroke();
      }
    }
    return this.m_axisStroke;
  }

  /**
   * get the font to use for the text on the axis ticks
   *
   * @return the font to use for the text on the axis ticks
   */
  protected Font calculateAxisTickFont() {
    return this.getBaseFont();
  }

  /**
   * get the font to use for the text on the axis ticks
   *
   * @return the font to use for the text on the axis ticks
   */
  public final Font getAxisTickFont() {
    if (this.m_axisTickFont == null) {
      synchronized (this.m_sync) {
        if (this.m_axisTickFont == null) {
          this.m_axisTickFont = this.calculateAxisTickFont();
        }
      }
    }
    return this.m_axisTickFont;
  }

  /**
   * Should we use/paint axis ticks or not?
   *
   * @return {@code true} if we use/paint axis ticks, {@code false}
   *         otherwise
   */
  protected boolean calculateUseAxisTicks() {
    return true;
  }

  /**
   * Should we use/paint axis ticks or not?
   *
   * @return {@code true} if we use/paint axis ticks, {@code false}
   *         otherwise
   */
  public final boolean useAxisTicks() {
    final boolean b;
    if (this.m_useAxisTicks == 0) {
      synchronized (this.m_sync) {
        if (this.m_useAxisTicks == 0) {
          b = this.calculateUseAxisTicks();
          this.m_useAxisTicks = (b ? 1 : (-1));
          return b;
        }
      }
    }
    return (this.m_useAxisTicks > 0);
  }

  /**
   * get the paint to use to paint the axis tick texts
   *
   * @return the color to use to paint axis tick texts
   */
  protected Color calculateAxisTickColor() {
    return this.getForegroundColor();
  }

  /**
   * get the paint to use to paint the axis tick texts
   *
   * @return the color to use to paint axis tick texts
   */
  public final Color getAxisTickColor() {
    if (this.m_axisTickColor == null) {
      synchronized (this.m_sync) {
        if (this.m_axisTickColor == null) {
          this.m_axisTickColor = this.calculateAxisTickColor();
        }
      }
    }

    return this.m_axisTickColor;
  }

  /**
   * Calculate the axis tick stroke
   *
   * @return the axis tick stroke
   */
  protected Stroke calculateAxisTickStroke() {
    final Stroke s;
    final BasicStroke b;

    s = this.getAxisStroke();
    if (s instanceof BasicStroke) {
      b = ((BasicStroke) s);

      return new BasicStroke(
          (b.getLineWidth() * 0.6666666666666666666666666f),//
          b.getEndCap(), b.getLineJoin(),
          (b.getMiterLimit() * 0.6666666666666666666666666f),//
          b.getDashArray(), b.getDashPhase());
    }

    return s;
  }

  /**
   * Get the axis tick stroke
   *
   * @return the axis tick stroke
   */
  public final Stroke getAxisTickStroke() {
    if (this.m_axisTickStroke == null) {
      synchronized (this.m_sync) {
        if (this.m_axisTickStroke == null) {
          this.m_axisTickStroke = this.calculateAxisTickStroke();
        }
      }
    }
    return this.m_axisTickStroke;
  }

  /**
   * get the paint to use to paint the axis title texts
   *
   * @return the color to use to paint axis title texts
   */
  protected Color calculateAxisTitleColor() {
    return this.getAxisTickColor();
  }

  /**
   * get the paint to use to paint the axis title texts
   *
   * @return the color to use to paint axis title texts
   */
  public Color getAxisTitleColor() {
    if (this.m_axisTitleColor == null) {
      synchronized (this.m_sync) {
        if (this.m_axisTitleColor == null) {
          this.m_axisTitleColor = this.calculateAxisTitleColor();
        }
      }
    }
    return this.m_axisTitleColor;
  }

  /**
   * get the font to use for the axis titles
   *
   * @return the font to use for the axis titles
   */
  protected Font calculateAxisTitleFont() {
    Font f;

    f = this.getBaseFont();
    if (f.isBold()) {
      return f.deriveFont(f.getSize2D() + 1f);
    }

    return f.deriveFont(Font.BOLD);
  }

  /**
   * get the font to use for the axis titles
   *
   * @return the font to use for the axis titles
   */
  public final Font getAxisTitleFont() {
    if (this.m_axisTitleFont == null) {
      synchronized (this.m_sync) {
        if (this.m_axisTitleFont == null) {
          this.m_axisTitleFont = this.calculateAxisTickFont();
        }
      }
    }
    return this.m_axisTitleFont;
  }

  /**
   * get the paint to use for the diagram data
   *
   * @param index
   *          the data index
   * @return the color to use
   */
  public final Color getForegroundDataColor(final int index) {
    return this.m_foregroundDataColors.get(index);
  }

  /**
   * check the stroke index
   *
   * @param index
   *          the stroke index
   */
  private final void checkStrokeIndex(final int index) {
    if (index < 0) {
      throw new IllegalArgumentException(//
          "Data stroke index must be positive, but is " + index); //$NON-NLS-1$
    }
    if (index >= ChartPalette.MAX_LIST_STUFF) {
      throw new IllegalArgumentException(//
          "There can be at most " + ChartPalette.MAX_LIST_STUFF + //$NON-NLS-1$
              " data strokes, but you ask for the stroke at index "//$NON-NLS-1$
              + index);
    }
  }

  /**
   * get the stroke to use for the diagram data
   *
   * @param index
   *          the data index
   * @return the stroke to use
   */
  protected Stroke calculateForegroundDataStroke(final int index) {
    this.checkStrokeIndex(index);
    return this.getBaseStroke();
  }

  /**
   * get the stroke to use for the diagram data
   *
   * @param index
   *          the data index
   * @return the stroke to use
   */
  public final Stroke getForegroundDataStroke(final int index) {
    final ArrayList<Stroke> l;
    Stroke c;

    this.checkStrokeIndex(index);

    l = this.m_foregroundDataStrokes;
    if (index < l.size()) {
      c = l.get(index);
      if (c != null) {
        return c;
      }
    }
    synchronized (this.m_sync) {
      while (index >= l.size()) {
        l.add(null);
      }
      c = l.get(index);
      if (c != null) {
        return c;
      }

      c = this.calculateForegroundDataStroke(index);
      l.set(index, c);
      return c;
    }
  }

  /**
   * get the stroke to use for the diagram data
   *
   * @param index
   *          the data index
   * @return the stroke to use
   */
  protected Stroke calculateBackgroundDataStroke(final int index) {
    final Stroke s;
    final BasicStroke b;

    this.checkStrokeIndex(index);

    if (index != 0) {
      return this.getBackgroundDataStroke(0);
    }

    s = this.getForegroundDataStroke(0);
    if (s instanceof BasicStroke) {
      b = ((BasicStroke) s);

      return new BasicStroke((b.getLineWidth() * 0.45f),//
          b.getEndCap(), b.getLineJoin(), (b.getMiterLimit() * 0.45f),//
          b.getDashArray(), b.getDashPhase());
    }

    return s;
  }

  /**
   * get the stroke to use for the diagram data
   *
   * @param index
   *          the data index
   * @return the stroke to use
   */
  public final Stroke getBackgroundDataStroke(final int index) {
    final ArrayList<Stroke> l;
    Stroke c;

    this.checkStrokeIndex(index);

    l = this.m_backgroundDataStrokes;
    if (index < l.size()) {
      c = l.get(index);
      if (c != null) {
        return c;
      }
    }
    synchronized (this.m_sync) {
      while (index >= l.size()) {
        l.add(null);
      }
      c = l.get(index);
      if (c != null) {
        return c;
      }

      c = this.calculateBackgroundDataStroke(index);
      l.set(index, c);
      return c;
    }
  }

  /**
   * get the paint to use for the diagram data
   *
   * @param index
   *          the data index
   * @return the color to use
   */
  public final Color getBackgroundDataColor(final int index) {
    return this.m_backgroundDataColors.get(index);
  }

  /**
   * get the font to use for the legend
   *
   * @return the font to use for the legend
   */
  protected Font calculateLegendFont() {
    return this.getBaseFont();
  }

  /**
   * get the font to use for the legend
   *
   * @return the font to use for the legend
   */
  public final Font getLegendFont() {
    if (this.m_legendFont == null) {
      synchronized (this.m_sync) {
        if (this.m_legendFont == null) {
          this.m_legendFont = this.calculateLegendFont();
        }
      }
    }
    return this.m_legendFont;
  }

  /**
   * get the paint to use to paint the grid line texts
   *
   * @return the color to use to paint grid line texts
   */
  protected Color calculateGridLineColor() {
    return this.getForegroundColor();
  }

  /**
   * get the paint to use to paint the grid line texts
   *
   * @return the color to use to paint grid line texts
   */
  public final Color getGridLineColor() {
    if (this.m_gridLineColor == null) {
      synchronized (this.m_sync) {
        if (this.m_gridLineColor == null) {
          this.m_gridLineColor = this.calculateGridLineColor();
        }
      }
    }

    return this.m_gridLineColor;
  }

  /**
   * Calculate the grid line stroke
   *
   * @return the grid line stroke
   */
  protected Stroke calculateGridLineStroke() {
    final Stroke s;
    final BasicStroke b;
    final float width, dash;

    s = this.getAxisStroke();
    if (s instanceof BasicStroke) {
      b = ((BasicStroke) s);
      width = b.getLineWidth();
      dash = (ChartPalette.DEFAULT_DASH_WIDTH * width);

      return new BasicStroke((width * 0.4f),//
          BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,//
          0f,//
          new float[] { dash, dash }, dash);
    }

    return s;
  }

  /**
   * Get the grid line stroke
   *
   * @return the grid line stroke
   */
  public final Stroke getGridLineStroke() {
    if (this.m_gridLineStroke == null) {
      synchronized (this.m_sync) {
        if (this.m_gridLineStroke == null) {
          this.m_gridLineStroke = this.calculateGridLineStroke();
        }
      }
    }
    return this.m_gridLineStroke;
  }

  /**
   * Should we use/paint grid lines or not?
   *
   * @return {@code true} if we use/paint grid lines, {@code false}
   *         otherwise
   */
  protected boolean calculateUseGridLines() {
    return true;
  }

  /**
   * Should we use/paint grid lines or not?
   *
   * @return {@code true} if we use/paint grid lines, {@code false}
   *         otherwise
   */
  public final boolean useGridLines() {
    final boolean b;
    if (this.m_useGridLines == 0) {
      synchronized (this.m_sync) {
        if (this.m_useGridLines == 0) {
          b = this.calculateUseGridLines();
          this.m_useGridLines = (b ? 1 : (-1));
          return b;
        }
      }
    }
    return (this.m_useGridLines > 0);
  }
}
