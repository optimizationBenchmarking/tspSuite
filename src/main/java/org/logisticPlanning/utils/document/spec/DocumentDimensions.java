package org.logisticPlanning.utils.document.spec;

import org.logisticPlanning.utils.graphics.DoubleDimension;
import org.logisticPlanning.utils.math.MathConstants;

/**
 * This class allows us to access (approximate) size information about the
 * document.
 */
public class DocumentDimensions {

  /** the default instance */
  static final DocumentDimensions DEFAULT = new DocumentDimensions();

  /** no dimension of a figure must be less than this */
  private static final int MIN_FIGURE_SIZE_MM = 20;
  /** the default figure spacing */
  private static final double DEFAULT_FIGURE_SPACING_MM = 2d;

  /** the cache for dimension objects */
  private final DoubleDimension[] m_dims;

  /** instantiate */
  protected DocumentDimensions() {
    super();
    this.m_dims = new DoubleDimension[EDefaultFigureSize.FIGURE_SIZE_COUNT];
  }

  /**
   * The number of columns
   *
   * @return the number of columns
   */
  public int getColumnCount() {
    return 1;
  }

  /**
   * the page width in millimeters
   *
   * @return the page width in millimeters
   */
  public double getPageWidthMM() {
    final int i;
    final double w;

    i = this.getColumnCount();
    w = this.getColumnWidthMM();
    if (i <= 1) {
      return w;
    }
    return ((w * i) + ((i - 1) * 0.05d * w));
  }

  /**
   * the page height in millimeters
   *
   * @return the page height in millimeters
   */
  public double getPageHeightMM() {
    return 200d;
  }

  /**
   * the column width in millimeters
   *
   * @return the page width in millimeters
   */
  public double getColumnWidthMM() {
    return 120d;
  }

  /**
   * check whether an integer value is valid
   *
   * @param i
   *          the int
   * @return {@code true} if {@code i} is a valid dimension
   */
  private static final boolean __ci(final int i) {
    return ((i > 0) && (i < Integer.MAX_VALUE));
  }

  /**
   * check whether an double value is valid
   *
   * @param d
   *          the double
   * @return {@code true} if {@code i} is a valid dimension
   */
  private static final boolean __cd(final double d) {
    return ((d == d) && (d > 0d) && (d < Double.POSITIVE_INFINITY));
  }

  /**
   * Get the default dimension of the figure with the size {@code size}
   *
   * @param size
   *          the size definition
   * @return the default dimension of the large figure that spans over the
   *         whole page width / all columns
   */
  protected DoubleDimension calcFigureDimensionsMM(
      final EDefaultFigureSize size) {
    double maxWidth, maxHeight;
    int factX, factY, width, height, imW, imH;

    maxWidth = (size.spansAllColumns() ? (this.getPageWidthMM()) : (//
        this.getColumnWidthMM()));
    maxHeight = this.getPageHeightMM();

    width = height = -1;
    factX = size.getNX();
    factY = size.getNY();

    for (;;) {
      if ((factX > 0) && DocumentDimensions.__cd(maxWidth)) {
        if (factX <= 1) {
          width = ((int) maxWidth);
        } else {
          width = ((int) (Math
              .min(
                  (0.975d * maxWidth),//
                  ((maxWidth - ((factX - 1) * DocumentDimensions.DEFAULT_FIGURE_SPACING_MM)))) / factX));
        }
      }

      if ((factY > 0) && DocumentDimensions.__cd(maxHeight)) {
        if (factY <= 1) {
          height = ((int) (0.95d * maxHeight));
        } else {
          height = ((int) ((Math
              .min(
                  (0.88d * maxHeight),
                  Math.min(
                      (maxHeight - ((0.05d * (factY - 1)))),//
                      (maxHeight - (DocumentDimensions.DEFAULT_FIGURE_SPACING_MM * (factY - 1)))))) / factY));//

        }

        if (!(DocumentDimensions.__ci(width))) {
          width = ((int) (0.5d + (height / MathConstants.GOLDEN_RATIO)));
        }
      } else {
        if (DocumentDimensions.__ci(width)) {
          height = ((int) (0.5d + (MathConstants.GOLDEN_RATIO * width)));
        }
      }

      if (DocumentDimensions.__ci(width)
          && DocumentDimensions.__ci(height)) {
        break;
      }

      if (factX < 0) {
        factX = 1;
        continue;
      }
      if (factY < 0) {
        factY = 2;
        continue;
      }

      if (!(DocumentDimensions.__cd(maxWidth))) {
        if (DocumentDimensions.__cd(maxHeight)) {
          maxWidth = (maxHeight * MathConstants.GOLDEN_RATIO);
          continue;
        }
        maxWidth = 160d;// no width or height are known, assume 16cm as
        // width
        continue;
      }

      if (!(DocumentDimensions.__cd(maxHeight))) {
        maxHeight = (maxWidth / MathConstants.GOLDEN_RATIO);
        continue;
      }

      break;
    }

    imW = ((int) maxWidth);
    imH = ((int) maxHeight);

    if (!(DocumentDimensions.__ci(width))) {
      if (DocumentDimensions.__ci(height)) {
        width = ((int) (0.5d + (height * MathConstants.GOLDEN_RATIO)));
      } else {
        width = imW;
      }
    }
    if (!(DocumentDimensions.__ci(height))) {
      if (DocumentDimensions.__ci(width)) {
        height = ((int) (0.5d + (width / MathConstants.GOLDEN_RATIO)));
      } else {
        height = imH;
      }
    }

    if (DocumentDimensions.__ci(imW) && (imW < width)) {
      width = imW;
    }
    if (DocumentDimensions.__ci(imH) && (imH < height)) {
      height = imH;
    }

    return new DoubleDimension(//
        Math.max(DocumentDimensions.MIN_FIGURE_SIZE_MM, width),//
        Math.max(DocumentDimensions.MIN_FIGURE_SIZE_MM, height));
  }

  /**
   * Get the default dimension of the figure with the size {@code size}
   *
   * @param size
   *          the size definition
   * @return the default dimension of the large figure that spans over the
   *         whole page width / all columns
   */
  public final DoubleDimension getFigureDimensionsMM(
      final EDefaultFigureSize size) {
    final int i;
    DoubleDimension d;

    i = size.ordinal();
    if ((d = this.m_dims[i]) == null) {
      this.m_dims[i] = d = this.calcFigureDimensionsMM(size);
    }
    return d;
  }
}
