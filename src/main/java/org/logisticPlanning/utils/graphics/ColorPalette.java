package org.logisticPlanning.utils.graphics;

import java.awt.Color;
import java.awt.color.ColorSpace;
import java.util.AbstractList;

import org.logisticPlanning.utils.utils.HashUtils;

/**
 * A base class for color palettes. A color palette provides a list of
 * colors which should obey to the following features:
 * <ol>
 * <li>No two colors in the list are equal.</li>
 * <li>Any two colors in the list are visually different.</li>
 * <li>Retrieving the color at the same index two times will yield the
 * identical {@link java.awt.Color} instance.</li>
 * <li>Color palettes with the same initial configuration return equal
 * colors for equal indexes.</li>
 * </ol>
 * The basic implementation here mixes a static color list with a
 * pseudo-random color generation method (always yielding the same colors).
 * It tries to prevent the random method to create colors which are too
 * similar and furthermore tries to prevent the generation of colors which
 * are too light or too dark.
 */
public class ColorPalette extends AbstractList<Color> {

  /** the colors to be used */
  private static final Color[] DATA_COLOR = { Color.BLUE,// // color 000
      Color.RED,// // color 001
      Color.GREEN,// // color 002
      Color.MAGENTA,// // color 003
      Color.CYAN,// // color 004
      Color.ORANGE,// // color 005
      new Color(128, 128, 0),// // color 006
      Color.PINK,// // color 007

      // color theme
      new Color(52, 191, 137),// // color 008
      new Color(139, 53, 51),// // color 009
      new Color(111, 135, 60),// // color 010
      new Color(90, 68, 116),// // color 011
      new Color(49, 124, 144),// // color 012
      new Color(181, 106, 44),// // color 013
      new Color(62, 106, 160),// // color 014
      new Color(162, 63, 60),// // color 015
      new Color(106, 81, 136),// // color 016
      new Color(58, 144, 167),// // color 017
      new Color(210, 124, 53),// // color 018
      new Color(71, 120, 179),// // color 019
      new Color(182, 72, 69),// // color 020
      new Color(145, 177, 80),// // color 021
      new Color(119, 91, 152),// // color 022
      new Color(66, 162, 187),// // color 023
      new Color(235, 139, 61),// // color 024
      new Color(110, 145, 194),// // color 025
      new Color(197, 110, 109),// // color 026
      new Color(165, 163, 116),// // color 027
      new Color(144, 124, 172),// // color 028
      new Color(107, 180, 202),// // color 029
      new Color(246, 160, 104),// // color 030
      new Color(156, 175, 208),// // color 031
      new Color(210, 156, 155),// // color 032
      new Color(188, 207, 159),// // color 033
      new Color(175, 163, 192),// // color 034
      new Color(245, 185, 52),// // color 035

  /*
   * new Color(60, 103, 154),// new Color(157, 61, 58),// new Color(125,
   * 152, 68),// new Color(102, 78, 131),// new Color(56, 140, 162),// new
   * Color(203, 120, 51),//
   */
  };

  /** the colors */
  private final Color[] m_colors;

  /** the object to synchronize on */
  private final Object m_sync;

  /** ensure that all colors are unique */
  private final boolean m_uniqueColors;

  /** the number of colors allocated so far */
  private int m_allocated;

  /**
   * create the color palette
   *
   * @param sync
   *          the synchronizer
   * @param maxColors
   *          the maximum colors
   * @param uniqueColors
   *          ensure that all colors are unique
   */
  public ColorPalette(final Object sync, final int maxColors,
      final boolean uniqueColors) {
    super();
    this.m_colors = new Color[((maxColors > 0) ? maxColors : 2048)];
    this.m_sync = ((sync != null) ? sync : this.m_colors);
    this.m_uniqueColors = uniqueColors;
  }

  /**
   * create the color palette
   *
   * @param maxColors
   *          the maximum colors
   * @param uniqueColors
   *          ensure that all colors are unique
   */
  public ColorPalette(final int maxColors, final boolean uniqueColors) {
    this(null, maxColors, uniqueColors);
  }

  /**
   * create the color palette
   *
   * @param maxColors
   *          the maximum colors
   */
  public ColorPalette(final int maxColors) {
    this(null, maxColors, true);
  }

  /** create the color palette */
  public ColorPalette() {
    this(-1);
  }

  /**
   * Calculate a new color.
   *
   * @param index
   *          the data index
   * @return the color to use
   */
  protected Color calculateColor(final int index) {
    final boolean dir;
    Color base, old;
    int count, r, g, b;

    base = ColorPalette.DATA_COLOR[index % ColorPalette.DATA_COLOR.length];
    count = (index / ColorPalette.DATA_COLOR.length);
    if (count <= 0) {
      return base;
    }

    dir = ((count & 1) != 0);
    for (count >>= 1; count >= 0; count--) {
      old = base;
      base = (dir ? base.darker() : base.brighter());
      r = base.getRed();
      g = base.getGreen();
      b = base.getBlue();
      if (Math.min(Math.min(Math.abs(r - g), Math.abs(r - b)),//
          Math.abs(b - g)) < 10) {
        return old;
      }
    }

    return base;
  }

  /**
   * Ensure that the color is visible and a true color: If the color is too
   * dark or too light, it will be replaced with a pseudo-random color
   * calculated from it. If it is neither too dark nor to light, it is
   * returned unchanged.
   *
   * @param c
   *          the color
   * @return the visible version of the color
   */
  protected Color ensureColorVisible(final Color c) {
    Color base;
    int r, g, b, z, s;

    base = c;
    z = 0;
    for (z = 0; z < 1000; z++) {
      r = base.getRed();
      g = base.getGreen();
      b = base.getBlue();
      s = (r + g + b);
      if ((Math.min(r, Math.min(g, b)) < 250) && //
          (Math.max(r, Math.max(g, b)) > 10) && //
          (s > 30) && //
          (s < 675)) {
        return base;
      }
      // ok, color is too bright or too dark
      base = new Color(//
          HashUtils.combineHashes(HashUtils.hashCode(base.getRGB()), z)//
          & 0xffffff);
    }

    return base;
  }

  /**
   * get the distance between two vectors
   *
   * @param c1
   *          the first color
   * @param c2
   *          the second color
   * @param a
   *          the first vector
   * @param b
   *          the second vector
   * @param cs
   *          the color space
   * @return the distance
   */
  private static final float __dist(final Color c1, final Color c2,
      final ColorSpace cs, final float[] a, final float[] b) {
    float sum, sumSqr, x, y, min, range;
    int i;

    c1.getColorComponents(cs, a);
    c2.getColorComponents(cs, b);

    sum = 0f;
    sumSqr = 0f;
    for (i = cs.getNumComponents(); (--i) >= 0;) {
      min = cs.getMinValue(i);
      range = (cs.getMaxValue(i) - min);
      x = ((a[i] - min) / range);
      y = ((b[i] - min) / range);
      x = Math.abs(x - y);
      sum += x;
      sumSqr += (x * x);
    }

    return Math.min(sum, sumSqr);
  }

  /**
   * Compute the distance between two colors.
   *
   * @param a
   *          the first color
   * @param b
   *          the second color
   * @return {@code Double.NEGATIVE_INFINITY} if the colors are identical,
   *         a distance value {@code >=0} otherwise
   */
  protected static final double colorDistance(final Color a, final Color b) {
    // final double r1, g1, b1, r2, g2, b2, y1, u1, v1, y2, u2, v2;
    // double dist, v, x1, x2, x3;
    float[] x, y;

    if (a.equals(b)) {
      return Double.NEGATIVE_INFINITY;
    }

    x = new float[6];
    y = new float[6];
    return Math.min(Math.min(Math.min(Math.min(
        //
        ColorPalette.__dist(a, b,
            ColorSpace.getInstance(ColorSpace.CS_sRGB), x, y),
        ColorPalette.__dist(a, b,
            ColorSpace.getInstance(ColorSpace.CS_LINEAR_RGB), x, y)),
        ColorPalette.__dist(a, b,
            ColorSpace.getInstance(ColorSpace.CS_PYCC), x, y)),
        ColorPalette.__dist(a, b,
            ColorSpace.getInstance(ColorSpace.CS_CIEXYZ), x, y)),
        ColorPalette.__dist(a, b,
            ColorSpace.getInstance(ColorSpace.CS_GRAY), x, y));

    // r1 = (a.getRed() / 255d);
    // g1 = (a.getGreen() / 255d);
    // b1 = (a.getBlue() / 255d);
    //
    // r2 = (b.getRed() / 255d);
    // g2 = (b.getGreen() / 255d);
    // b2 = (b.getBlue() / 255d);
    //
    // x1 = Math.abs(r1 - r2);
    // x2 = Math.abs(g1 - g2);
    // x3 = Math.abs(b1 - b2);
    // dist = Math.min((x1 + x2 + x3),//
    // Math.sqrt((x1 * x1) + (x2 * x2) + (x3 * x3)));
    //
    // if (x1 <= 0.05d) {
    // v = (x2 + x3);
    // if (x1 <= 0.01d) {
    // v *= 0.5d;
    // }
    // if (v < dist) {
    // dist = v;
    // }
    // }
    //
    // if (x2 <= 0.05d) {
    // v = (x1 + x3);
    // if (x2 <= 0.01d) {
    // v *= 0.5d;
    // }
    // if (v < dist) {
    // dist = v;
    // }
    // }
    //
    // if (x3 <= 0.05d) {
    // v = (x2 + x1);
    // if (x3 <= 0.01d) {
    // v *= 0.5d;
    // }
    // if (v < dist) {
    // dist = v;
    // }
    // }
    //
    // y1 = ((0.299d * r1) + (0.587d * g1) + (0.114d * b1));
    // u1 = ((b1 - y1) * 0.493d);
    // v1 = ((r1 - y1) * 0.877d);
    //
    // y2 = ((0.299d * r2) + (0.587d * g2) + (0.114d * b2));
    // u2 = ((b2 - y2) * 0.493d);
    // v2 = ((r2 - y2) * 0.877d);
    //
    // x1 = Math.abs(y1 - y2);
    // x2 = Math.abs(u1 - u2);
    // x3 = Math.abs(v1 - v2);
    // v = Math.min((x1 + x2 + x3),//
    // Math.sqrt((x1 * x1) + (x2 * x2) + (x3 * x3)));
    // if (v < dist) {
    // dist = v;
    // }
    //
    // return dist;
  }

  /**
   * Make a color unique. This is only called if the palette is supposed to
   * ensure color uniqueness.
   *
   * @param c
   *          the color
   * @param others
   *          the other colors
   * @param count
   *          the number of colors stored in {@code others} (starting at
   *          index {@code 0})
   * @return the unique color
   */
  protected Color makeColorUnique(final Color c, final Color[] others,
      final int count) {
    int i, it, a, add, h, j, ph, limit;
    Color col;
    double minDist;

    col = c;
    add = 1;
    minDist = 0.1d;
    limit = Math.max(0, (count - 6));
    outer: for (it = 0; it < 2000000; it++) {
      if ((it % 2345) == 0) {
        minDist *= 0.95d;
      }

      for (i = count; (--i) >= 0;) {
        if (ColorPalette.colorDistance(col, others[i]) <= ((i > limit) ? (2d * minDist)
            : minDist)) {
          ph = HashUtils.combineHashes(it,
              HashUtils.combineHashes(i, count));
          inner: for (j = 0; j < 10000; j++) {
            h = HashUtils.combineHashes(ph, j);
            add = (h % 7);
            if (add == 0) {
              add = ((h < 0) ? 1 : (-1));
            }
            if (add == 0) {
              add = 2;
            }

            switch (Math.abs(h) % 3) {

              case 0: {
                a = (col.getRed() + add);
                if ((a > 0) && (a < 255)) {
                  col = this.ensureColorVisible(new Color(a, col
                      .getGreen(), col.getBlue()));
                  continue outer;
                }
                continue inner;
              }

              case 1: {
                a = (col.getGreen() + add);
                if ((a > 0) && (a < 255)) {
                  col = this.ensureColorVisible(new Color(col.getRed(), a,
                      col.getBlue()));
                  continue outer;
                }
                continue inner;
              }

              default: {
                a = (col.getBlue() + add);
                if ((a > 0) && (a < 255)) {
                  col = this.ensureColorVisible(new Color(col.getRed(), //
                      col.getGreen(), a));
                  continue outer;
                }
                continue inner;
              }
            }

          }
        }
      }

      return col;
    }

    return c;
  }

  /**
   * get the color at a given index
   *
   * @param index
   *          the index
   * @return the color
   */
  @Override
  public final Color get(final int index) {
    int i;
    Color c;

    if (index < 0) {
      throw new IllegalArgumentException(//
          "Color index must be positive, but is " + index); //$NON-NLS-1$
    }
    if (index >= this.m_colors.length) {
      throw new IllegalArgumentException(//
          "There can be at most " + this.m_colors.length + //$NON-NLS-1$
              " colors, but you ask for the color at index " //$NON-NLS-1$
              + index);
    }

    // does the color exist?
    c = this.m_colors[index];
    if (c != null) {
      return c;
    }

    synchronized (this.m_sync) {
      // check again whether color exist..
      c = this.m_colors[index];
      if (c != null) {
        return c;
      }

      if (this.m_uniqueColors) {
        // ensure that all colors exist - necessary to ensure that the
        // palette
        // always has the same contents, regardless in which order
        // colors
        // are
        // queried
        for (i = 0; i < index; i++) {
          if (this.m_colors[i] == null) {
            this.get(i);
          }
        }
      }

      c = this.ensureColorVisible(this.calculateColor(index));

      if (this.m_uniqueColors) {
        c = this.makeColorUnique(c, this.m_colors, index);
      }

      this.m_colors[index] = c;
      if (index >= this.m_allocated) {
        this.m_allocated = (index + 1);
      }
      this.modCount++;
      return c;
    }
  }

  /** {@inheritDoc} */
  @Override
  public final int size() {
    return this.m_allocated;
  }

}
