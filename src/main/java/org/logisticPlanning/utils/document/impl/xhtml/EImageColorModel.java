package org.logisticPlanning.utils.document.impl.xhtml;

import java.awt.image.BufferedImage;

/**
 * the type of image supported by the xhtml driver
 */
public enum EImageColorModel {

  /** 1 bit black & white */
  BW_1_BIT(BufferedImage.TYPE_BYTE_BINARY),

  /** 8 bit gray scale */
  GRAY_8_BIT(BufferedImage.TYPE_BYTE_GRAY),

  /** 15 bit rgb */
  RGB_15_BIT(BufferedImage.TYPE_USHORT_555_RGB),

  /** 16 bit gray scale */
  GRAY_16_BIT(BufferedImage.TYPE_USHORT_GRAY),

  /** 16 bit rgb */
  RGB_16_BIT(BufferedImage.TYPE_USHORT_565_RGB),

  /** 24 bits for rbg */
  RBG_24_BIT(BufferedImage.TYPE_INT_RGB),

  /** 32 bits for rbg and alpha */
  RBGA_32_BIT(BufferedImage.TYPE_INT_ARGB);

  /**
   * the buffered image type, for use with
   * {@link java.awt.image.BufferedImage}
   */
  private final int m_biType;

  /**
   * Create the image color model
   *
   * @param biType
   *          the buffered image type, for use with
   *          {@link java.awt.image.BufferedImage}
   */
  private EImageColorModel(final int biType) {
    this.m_biType = biType;
  }

  /**
   * Get the color model type to be used when instantiating the
   * {@link java.awt.image.BufferedImage buffered image}
   *
   * @return the color model type to be used when instantiating the
   *         {@link java.awt.image.BufferedImage buffered image}
   */
  public final int getBufferedImageType() {
    return this.m_biType;
  }
}
