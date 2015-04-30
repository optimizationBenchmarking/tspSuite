package org.logisticPlanning.utils.document.spec;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.util.Map;

/**
 * A wrapper for graphics 2d objects. This wrapper allows us to forward all
 * calls to a different graphics object. Additionally, it provides the
 * interface {@link java.lang.AutoCloseable} which can be used to put all
 * work on an output graphics object into a {@code try...with} statement.
 * 
 * @param <GT>
 *          the proxy graphics type
 */
public abstract class ProxyGraphic<GT extends Graphics2D> extends Graphic {

  /** the real object */
  private final GT m_out;

  /**
   * instantiate
   * 
   * @param owner
   *          the owner
   * @param graphic
   *          the graphic to use
   * @throws IOException
   *           if io fails
   */
  @SuppressWarnings("unused")
  protected ProxyGraphic(final FigureBody owner, final GT graphic)
      throws IOException {
    super(owner);
    this.m_out = graphic;
  }

  /** {@inheritDoc} */
  @Override
  public void draw3DRect(final int x, final int y, final int width,
      final int height, final boolean raised) {
    this.m_out.draw3DRect(x, y, width, height, raised);
  }

  /** {@inheritDoc} */
  @Override
  public void fill3DRect(final int x, final int y, final int width,
      final int height, final boolean raised) {
    this.m_out.fill3DRect(x, y, width, height, raised);
  }

  /** {@inheritDoc} */
  @Override
  public void draw(final Shape s) {
    this.m_out.draw(s);
  }

  /** {@inheritDoc} */
  @Override
  public void drawImage(final BufferedImage img, final BufferedImageOp op,
      final int x, final int y) {
    this.m_out.drawImage(img, op, x, y);
  }

  /** {@inheritDoc} */
  @Override
  public void drawRenderedImage(final RenderedImage img,
      final AffineTransform xform) {
    this.m_out.drawRenderedImage(img, xform);
  }

  /** {@inheritDoc} */
  @Override
  public void drawRenderableImage(final RenderableImage img,
      final AffineTransform xform) {
    this.m_out.drawRenderableImage(img, xform);
  }

  /** {@inheritDoc} */
  @Override
  public void drawString(final String str, final int x, final int y) {
    this.m_out.drawString(str, x, y);
  }

  /** {@inheritDoc} */
  @Override
  public void drawString(final String str, final float x, final float y) {
    this.m_out.drawString(str, x, y);
  }

  /** {@inheritDoc} */
  @Override
  public void drawString(final AttributedCharacterIterator iterator,
      final int x, final int y) {
    this.m_out.drawString(iterator, x, y);
  }

  /** {@inheritDoc} */
  @Override
  public void drawString(final AttributedCharacterIterator iterator,
      final float x, final float y) {
    this.m_out.drawString(iterator, x, y);
  }

  /** {@inheritDoc} */
  @Override
  public void drawGlyphVector(final GlyphVector g, final float x,
      final float y) {
    this.m_out.drawGlyphVector(g, x, y);
  }

  /** {@inheritDoc} */
  @Override
  public void fill(final Shape s) {
    this.m_out.fill(s);
  }

  /** {@inheritDoc} */
  @Override
  public boolean hit(final Rectangle rect, final Shape s,
      final boolean onStroke) {
    return this.m_out.hit(rect, s, onStroke);
  }

  /** {@inheritDoc} */
  @Override
  public GraphicsConfiguration getDeviceConfiguration() {
    return this.m_out.getDeviceConfiguration();
  }

  /** {@inheritDoc} */
  @Override
  public void setComposite(final Composite comp) {
    this.m_out.setComposite(comp);
  }

  /** {@inheritDoc} */
  @Override
  public void setPaint(final Paint paint) {
    this.m_out.setPaint(paint);
  }

  /** {@inheritDoc} */
  @Override
  public void setStroke(final Stroke s) {
    this.m_out.setStroke(s);
  }

  /** {@inheritDoc} */
  @Override
  public void setRenderingHint(final Key hintKey, final Object hintValue) {
    this.m_out.setRenderingHint(hintKey, hintValue);
  }

  /** {@inheritDoc} */
  @Override
  public Object getRenderingHint(final Key hintKey) {
    return this.m_out.getRenderingHint(hintKey);
  }

  /** {@inheritDoc} */
  @Override
  public void setRenderingHints(final Map<?, ?> hints) {
    this.m_out.setRenderingHints(hints);
  }

  /** {@inheritDoc} */
  @Override
  public RenderingHints getRenderingHints() {
    return this.m_out.getRenderingHints();
  }

  /** {@inheritDoc} */
  @Override
  public void translate(final int x, final int y) {
    this.m_out.translate(x, y);
  }

  /** {@inheritDoc} */
  @Override
  public void translate(final double tx, final double ty) {
    this.m_out.translate(tx, ty);
  }

  /** {@inheritDoc} */
  @Override
  public void rotate(final double theta) {
    this.m_out.rotate(theta);
  }

  /** {@inheritDoc} */
  @Override
  public void rotate(final double theta, final double x, final double y) {
    this.m_out.rotate(theta, x, y);
  }

  /** {@inheritDoc} */
  @Override
  public void scale(final double sx, final double sy) {
    this.m_out.scale(sx, sy);
  }

  /** {@inheritDoc} */
  @Override
  public void shear(final double shx, final double shy) {
    this.m_out.shear(shx, shy);
  }

  /** {@inheritDoc} */
  @Override
  public void transform(final AffineTransform Tx) {
    this.m_out.transform(Tx);
  }

  /** {@inheritDoc} */
  @Override
  public void setTransform(final AffineTransform Tx) {
    this.m_out.setTransform(Tx);
  }

  /** {@inheritDoc} */
  @Override
  public AffineTransform getTransform() {
    return this.m_out.getTransform();
  }

  /** {@inheritDoc} */
  @Override
  public Paint getPaint() {
    return this.m_out.getPaint();
  }

  /** {@inheritDoc} */
  @Override
  public Composite getComposite() {
    return this.m_out.getComposite();
  }

  /** {@inheritDoc} */
  @Override
  public void setBackground(final Color color) {
    this.m_out.setBackground(color);
  }

  /** {@inheritDoc} */
  @Override
  public Color getBackground() {
    return this.m_out.getBackground();
  }

  /** {@inheritDoc} */
  @Override
  public Stroke getStroke() {
    return this.m_out.getStroke();
  }

  /** {@inheritDoc} */
  @Override
  public void clip(final Shape s) {
    this.m_out.clip(s);
  }

  /** {@inheritDoc} */
  @Override
  public FontRenderContext getFontRenderContext() {
    return this.m_out.getFontRenderContext();
  }

  /** {@inheritDoc} */
  @Override
  public Graphics create() {
    return this.m_out.create();
  }

  /** {@inheritDoc} */
  @Override
  public Graphics create(final int x, final int y, final int width,
      final int height) {
    return this.m_out.create(x, y, width, height);
  }

  /** {@inheritDoc} */
  @Override
  public Color getColor() {
    return this.m_out.getColor();
  }

  /** {@inheritDoc} */
  @Override
  public void setColor(final Color c) {
    this.m_out.setColor(c);
  }

  /** {@inheritDoc} */
  @Override
  public void setPaintMode() {
    this.m_out.setPaintMode();
  }

  /** {@inheritDoc} */
  @Override
  public void setXORMode(final Color c1) {
    this.m_out.setXORMode(c1);
  }

  /** {@inheritDoc} */
  @Override
  public Font getFont() {
    return this.m_out.getFont();
  }

  /** {@inheritDoc} */
  @Override
  public void setFont(final Font font) {
    this.m_out.setFont(font);
  }

  /** {@inheritDoc} */
  @Override
  public FontMetrics getFontMetrics() {
    return this.m_out.getFontMetrics();
  }

  /** {@inheritDoc} */
  @Override
  public FontMetrics getFontMetrics(final Font f) {
    return this.m_out.getFontMetrics(f);
  }

  /** {@inheritDoc} */
  @Override
  public Rectangle getClipBounds() {
    return this.m_out.getClipBounds();
  }

  /** {@inheritDoc} */
  @Override
  public void clipRect(final int x, final int y, final int width,
      final int height) {
    this.m_out.clipRect(x, y, width, height);
  }

  /** {@inheritDoc} */
  @Override
  public void setClip(final int x, final int y, final int width,
      final int height) {
    this.m_out.setClip(x, y, width, height);
  }

  /** {@inheritDoc} */
  @Override
  public Shape getClip() {
    return this.m_out.getClip();
  }

  /** {@inheritDoc} */
  @Override
  public void setClip(final Shape clip) {
    this.m_out.setClip(clip);
  }

  /** {@inheritDoc} */
  @Override
  public void copyArea(final int x, final int y, final int width,
      final int height, final int dx, final int dy) {
    this.m_out.copyArea(x, y, width, height, dx, dy);
  }

  /** {@inheritDoc} */
  @Override
  public void drawLine(final int x1, final int y1, final int x2,
      final int y2) {
    this.m_out.drawLine(x1, y1, x2, y2);
  }

  /** {@inheritDoc} */
  @Override
  public void fillRect(final int x, final int y, final int width,
      final int height) {
    this.m_out.fillRect(x, y, width, height);
  }

  /** {@inheritDoc} */
  @Override
  public void drawRect(final int x, final int y, final int width,
      final int height) {
    this.m_out.drawRect(x, y, width, height);
  }

  /** {@inheritDoc} */
  @Override
  public void clearRect(final int x, final int y, final int width,
      final int height) {
    this.m_out.clearRect(x, y, width, height);
  }

  /** {@inheritDoc} */
  @Override
  public void drawRoundRect(final int x, final int y, final int width,
      final int height, final int arcWidth, final int arcHeight) {
    this.m_out.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
  }

  /** {@inheritDoc} */
  @Override
  public void fillRoundRect(final int x, final int y, final int width,
      final int height, final int arcWidth, final int arcHeight) {
    this.m_out.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
  }

  /** {@inheritDoc} */
  @Override
  public void drawOval(final int x, final int y, final int width,
      final int height) {
    this.m_out.drawOval(x, y, width, height);
  }

  /** {@inheritDoc} */
  @Override
  public void fillOval(final int x, final int y, final int width,
      final int height) {
    this.m_out.fillOval(x, y, width, height);
  }

  /** {@inheritDoc} */
  @Override
  public void drawArc(final int x, final int y, final int width,
      final int height, final int startAngle, final int arcAngle) {
    this.m_out.drawArc(x, y, width, height, startAngle, arcAngle);
  }

  /** {@inheritDoc} */
  @Override
  public void fillArc(final int x, final int y, final int width,
      final int height, final int startAngle, final int arcAngle) {
    this.m_out.fillArc(x, y, width, height, startAngle, arcAngle);
  }

  /** {@inheritDoc} */
  @Override
  public void drawPolyline(final int xPoints[], final int yPoints[],
      final int nPoints) {
    this.m_out.drawPolyline(xPoints, yPoints, nPoints);
  }

  /** {@inheritDoc} */
  @Override
  public void drawPolygon(final int xPoints[], final int yPoints[],
      final int nPoints) {
    this.m_out.drawPolygon(xPoints, yPoints, nPoints);
  }

  /** {@inheritDoc} */
  @Override
  public void drawPolygon(final Polygon p) {
    this.m_out.drawPolygon(p);
  }

  /** {@inheritDoc} */
  @Override
  public void fillPolygon(final int xPoints[], final int yPoints[],
      final int nPoints) {
    this.m_out.fillPolygon(xPoints, yPoints, nPoints);
  }

  /** {@inheritDoc} */
  @Override
  public void fillPolygon(final Polygon p) {
    this.m_out.fillPolygon(p);
  }

  /** {@inheritDoc} */
  @Override
  public void drawChars(final char data[], final int offset,
      final int length, final int x, final int y) {
    this.m_out.drawChars(data, offset, length, x, y);
  }

  /** {@inheritDoc} */
  @Override
  public void drawBytes(final byte data[], final int offset,
      final int length, final int x, final int y) {
    this.m_out.drawBytes(data, offset, length, x, y);
  }

  /** {@inheritDoc} */
  @Override
  public boolean drawImage(final Image img, final int x, final int y,
      final ImageObserver observer) {
    return this.m_out.drawImage(img, x, y, observer);
  }

  /** {@inheritDoc} */
  @Override
  public boolean drawImage(final Image img, final int x, final int y,
      final int width, final int height, final ImageObserver observer) {
    return this.m_out.drawImage(img, x, y, width, height, observer);
  }

  /** {@inheritDoc} */
  @Override
  public boolean drawImage(final Image img, final int x, final int y,
      final Color bgcolor, final ImageObserver observer) {
    return this.m_out.drawImage(img, x, y, bgcolor, observer);
  }

  /** {@inheritDoc} */
  @Override
  public boolean drawImage(final Image img, final int x, final int y,
      final int width, final int height, final Color bgcolor,
      final ImageObserver observer) {
    return this.m_out.drawImage(img, x, y, width, height, bgcolor,
        observer);
  }

  /** {@inheritDoc} */
  @Override
  public boolean drawImage(final Image img, final int dx1, final int dy1,
      final int dx2, final int dy2, final int sx1, final int sy1,
      final int sx2, final int sy2, final ImageObserver observer) {
    return this.m_out.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2,
        sy2, observer);
  }

  /** {@inheritDoc} */
  @Override
  public boolean drawImage(final Image img, final int dx1, final int dy1,
      final int dx2, final int dy2, final int sx1, final int sy1,
      final int sx2, final int sy2, final Color bgcolor,
      final ImageObserver observer) {
    return this.m_out.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2,
        sy2, bgcolor, observer);
  }

  /** {@inheritDoc} */
  @Override
  public void dispose() {
    //
  }

  /** {@inheritDoc} */
  @SuppressWarnings("deprecation")
  @Override
  @Deprecated
  public Rectangle getClipRect() {
    return this.m_out.getClipRect();
  }

  /** {@inheritDoc} */
  @Override
  public boolean hitClip(final int x, final int y, final int width,
      final int height) {
    return this.m_out.hitClip(x, y, width, height);
  }

  /** {@inheritDoc} */
  @Override
  public Rectangle getClipBounds(final Rectangle r) {
    return this.m_out.getClipBounds(r);
  }

  /** {@inheritDoc} */
  @Override
  public boolean drawImage(final Image img, final AffineTransform xform,
      final ImageObserver obs) {
    return this.m_out.drawImage(img, xform, obs);
  }

  /** {@inheritDoc} */
  @Override
  public void addRenderingHints(final Map<?, ?> hints) {
    this.m_out.addRenderingHints(hints);

  }

  /**
   * close the graphics object
   * 
   * @param graphics
   *          the graphics object
   * @throws Throwable
   *           if something goes wrong
   */
  protected void closeInner(final GT graphics) throws Throwable {
    try {
      graphics.dispose();
    } finally {
      if (graphics instanceof AutoCloseable) {
        ((AutoCloseable) graphics).close();
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public void close() throws IOException {
    try {
      try {
        this.closeInner(this.m_out);
      } catch (final IOException i) {
        throw i;
      } catch (final Throwable t) {
        throw new IOException(t);
      }
    } finally {
      super.close();
    }
  }

}
