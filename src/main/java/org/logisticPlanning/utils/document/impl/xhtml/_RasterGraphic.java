package org.logisticPlanning.utils.document.impl.xhtml;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.logisticPlanning.utils.document.spec.ProxyGraphic;

/**
 * the internal graphic representation
 */
final class _RasterGraphic extends ProxyGraphic<Graphics2D> {

  /** the internal buffered image */
  private final BufferedImage m_img;

  /**
   * instantiate
   *
   * @param owner
   *          the owner
   * @param img
   *          the buffered image
   * @param graphics
   *          the graphics
   * @throws IOException
   *           if i/o fails
   */
  _RasterGraphic(final _RasterFigureBody owner, final BufferedImage img,
      final Graphics2D graphics) throws IOException {
    super(owner, graphics);
    this.m_img = img;
  }

  /** {@inheritDoc} */
  @SuppressWarnings("resource")
  @Override
  protected final void closeInner(final Graphics2D graphics)
      throws Throwable {
    final _RasterFigureBody o;
    try {
      o = ((_RasterFigureBody) (this.getOwner()));
      o.m_file.getParentFile().mkdirs();
      ImageIO.write(this.m_img, o.m_type.getJavaType(), o.m_file);
    } finally {
      super.closeInner(graphics);
    }
  }
}
