package org.logisticPlanning.utils.document.impl.xhtml;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Dimension2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;
import org.logisticPlanning.utils.document.spec.DocumentDriver;
import org.logisticPlanning.utils.document.spec.FigureBody;
import org.logisticPlanning.utils.document.spec.Graphic;
import org.logisticPlanning.utils.math.units.ELength;

/**
 * A <a href="http://en.wikipedia.org/wiki/Xhtml">XHTML</a>&nbsp;[<a
 * href="#cite_W3C2010XHTML" style="font-weight:bold">1</a>] driver.<h2>
 * References</h2>
 * <ol>
 * <li><div><span id="cite_W3C2010XHTML" /><a
 * href="http://www.altheim.com/murray/">Murray Altheim</a> and&nbsp;Shane
 * McCarron: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;XHTML&#8482; 1.1
 * &#8212; Module-based XHTML &#8212; Second Edition,&rdquo;</span>
 * November&nbsp;23, 2010, W3C Recommendation, MIT/CSAIL (USA), ERCIM
 * (France), Keio University (Japan): World Wide Web Consortium (W3C).
 * <div>link: [<a
 * href="http://www.w3.org/TR/2010/REC-xhtml11-20101123">1</a>]</div></div>
 * </li>
 * </ol>
 */
public class XHTMLDriver extends DocumentDriver {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the image dpi value parameter: {@value} */
  public static final String PARAM_IMAGE_DPI = "imageDPI"; //$NON-NLS-1$

  /** the default dpi: {@value} */
  public static final int DEFAULT_IMAGE_DPI = (3 * 72);

  /** the image dpi type: {@value} */
  public static final String PARAM_IMAGE_TYPE = "imageType"; //$NON-NLS-1$
  /** are we human readable?: {@value} */
  public static final String PARAM_HUMAN_READABLE = "humanReadable"; //$NON-NLS-1$

  /** the default image type */
  public static final EImageType DEFAULT_IMAGE_TYPE = EImageType.PNG;

  /** the image color model: {@value} */
  public static final String PARAM_IMAGE_COLOR_MODEL = "imageColorModel"; //$NON-NLS-1$

  /** the default image color model */
  public static final EImageColorModel DEFAULT_IMAGE_COLOR_MODEL = EImageColorModel.RBG_24_BIT;

  /** the dots per inch for the image */
  private int m_imageDPI;

  /** the image type */
  private EImageType m_imageType;
  /** the image color model */
  private EImageColorModel m_imageColorModel;

  /** should we put newline? */
  private boolean m_humanReadable;

  /** the internal synchronizer */
  private final Object m_synch;

  /** instantiate the driver */
  public XHTMLDriver() {
    this("XHTML"); //$NON-NLS-1$
  }

  /**
   * create the LaTeX driver
   *
   * @param name
   *          the driver's name
   */
  protected XHTMLDriver(final String name) {
    super(name);
    this.m_synch = new Object();
    this.m_imageDPI = XHTMLDriver.DEFAULT_IMAGE_DPI;
    this.m_imageType = XHTMLDriver.DEFAULT_IMAGE_TYPE;
    this.m_imageColorModel = XHTMLDriver.DEFAULT_IMAGE_COLOR_MODEL;
    this.m_humanReadable = false;
  }

  /**
   * Is this document human readable?
   *
   * @return {@code true} for human-readable output, {@code false} for
   *         condensed output
   */
  public final boolean isHumanReadable() {
    return this.m_humanReadable;
  }

  /**
   * Set whether the output should be human readable or not?
   *
   * @param read
   *          {@code true} if the output should be human readable,
   *          {@code false} otherwise
   */
  public final void setHumanReadable(final boolean read) {
    synchronized (this.m_synch) {
      this.m_humanReadable = read;
    }
  }

  /**
   * Get the dots per inch for images
   *
   * @return the dots per inch for images
   */
  public final int getImageDPI() {
    return this.m_imageDPI;
  }

  /**
   * set the image dpi
   *
   * @param dpi
   *          the image dpi
   */
  public final void setImageDPI(final int dpi) {
    if (dpi <= 0) {
      throw new IllegalArgumentException("Image DPI cannot be " + dpi); //$NON-NLS-1$
    }
    synchronized (this.m_synch) {
      this.m_imageDPI = dpi;
    }
  }

  /**
   * Get the image type
   *
   * @return the image type
   */
  public final EImageType getImageType() {
    return this.m_imageType;
  }

  /**
   * set the image type
   *
   * @param type
   *          the image type
   */
  public final void setImageType(final EImageType type) {
    if (type == null) {
      throw new IllegalArgumentException("Image type cannot be null."); //$NON-NLS-1$
    }
    synchronized (this.m_synch) {
      this.m_imageType = type;
    }
  }

  /**
   * Get the image color model
   *
   * @return the image colorModel
   */
  public final EImageColorModel getImageColorModel() {
    synchronized (this.m_synch) {
      return this.m_imageColorModel;
    }
  }

  /**
   * set the image color model
   *
   * @param colorModel
   *          the image colorModel
   */
  public final void setImageColorModel(final EImageColorModel colorModel) {
    if (colorModel == null) {
      throw new IllegalArgumentException(
          "Image color model cannot be null."); //$NON-NLS-1$
    }
    synchronized (this.m_synch) {
      this.m_imageColorModel = colorModel;
    }
  }

  /** {@inheritDoc} */
  @Override
  public void configure(final Configuration config) {
    super.configure(config);
    synchronized (this.m_synch) {

      this.m_imageDPI = config.getInt(XHTMLDriver.PARAM_IMAGE_DPI, 1,
          100000, this.m_imageDPI);

      this.m_imageType = config.getConstant(XHTMLDriver.PARAM_IMAGE_TYPE,
          EImageType.class, EImageType.class, this.m_imageType);

      this.m_imageColorModel = config.getConstant(
          XHTMLDriver.PARAM_IMAGE_COLOR_MODEL, EImageColorModel.class,
          EImageColorModel.class, this.m_imageColorModel);

      this.m_humanReadable = config.getBoolean(
          XHTMLDriver.PARAM_HUMAN_READABLE, this.m_humanReadable);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void printParameters(final PrintStream ps) {
    super.printParameters(ps);
    synchronized (this.m_synch) {
      Configurable.printKey(XHTMLDriver.PARAM_IMAGE_DPI, ps);
      ps.println("The dots per inch for the images"); //$NON-NLS-1$

      Configurable.printKey(XHTMLDriver.PARAM_IMAGE_TYPE, ps);
      ps.println("The image type"); //$NON-NLS-1$

      Configurable.printKey(XHTMLDriver.PARAM_IMAGE_COLOR_MODEL, ps);
      ps.println("The image color model"); //$NON-NLS-1$

      Configurable.printKey(XHTMLDriver.PARAM_HUMAN_READABLE, ps);
      ps.println("make human readable html"); //$NON-NLS-1$
    }
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    synchronized (this.m_synch) {
      Configurable.printKey(XHTMLDriver.PARAM_IMAGE_DPI, ps);
      ps.println(this.m_imageDPI);

      Configurable.printKey(XHTMLDriver.PARAM_IMAGE_TYPE, ps);
      Configurable.printlnObject(this.m_imageType, ps);

      Configurable.printKey(XHTMLDriver.PARAM_IMAGE_COLOR_MODEL, ps);
      Configurable.printlnObject(this.m_imageColorModel, ps);

      Configurable.printKey(XHTMLDriver.PARAM_HUMAN_READABLE, ps);
      ps.println(this.m_humanReadable);
    }
  }

  /** {@inheritDoc} */
  @Override
  public XHTMLContext createContext(final File dir) throws IOException {
    return new XHTMLContext(this, dir);
  }

  /**
   * Create an instance of the default html document driver
   *
   * @return an instance of the default html document driver
   */
  public static final XHTMLDriver createDefaultHTMLriver() {
    return new XHTMLDriver();
  }

  /** {@inheritDoc} */
  @Override
  protected Graphic graphicCreate(final FigureBody owner)
      throws IOException {
    final Dimension2D sizeMM;
    final int pixelWidth, pixelHeight, dpi, ptWidth, ptHeight, ofs, wid;
    final double mmWidth, mmHeight;

    final BufferedImage img;
    final Graphics2D graphic;
    final EImageColorModel m;

    sizeMM = owner.getSizeInMM();
    mmHeight = sizeMM.getHeight();
    mmWidth = sizeMM.getWidth();

    synchronized (this.m_synch) {
      dpi = this.m_imageDPI;
      m = this.m_imageColorModel;
    }

    pixelWidth = ((int) (0.5d + Math.ceil(dpi
        * ELength.convert(mmWidth, ELength.MM, ELength.INCH))));
    pixelHeight = ((int) (0.5d + Math.ceil(dpi
        * ELength.convert(mmHeight, ELength.MM, ELength.INCH))));
    ptWidth = ((int) (0.5d + ELength.convert(mmWidth, ELength.MM,
        ELength.PT)));
    ptHeight = ((int) (0.5d + ELength.convert(mmHeight, ELength.MM,
        ELength.PT)));

    img = new BufferedImage(pixelWidth, pixelHeight,
        m.getBufferedImageType());

    graphic = img.createGraphics();
    graphic.setBackground(Color.WHITE);
    graphic.setColor(Color.WHITE);

    // make sure that the figure is filled with white back ground
    wid = Integer.MAX_VALUE;
    ofs = (-(wid >>> 1));
    graphic.clearRect(ofs, ofs, wid, wid);
    // graphic.fillRect(ofs, ofs, wid, wid);
    graphic.setColor(Color.BLACK);

    graphic.scale((pixelWidth / ((double) ptWidth)),
        (pixelHeight / ((double) ptHeight)));

    graphic.setClip(0, 0, ptWidth, ptHeight);

    return new _RasterGraphic(((_RasterFigureBody) owner), img, graphic);
  }
}
