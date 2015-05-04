package org.logisticPlanning.utils.document.impl.xhtml;

/**
 * The types of image supported by the
 * {@link org.logisticPlanning.utils.document.impl.xhtml.XHTMLDriver XHTML
 * driver}:
 * <ol>
 * <li><a
 * href="http://en.wikipedia.org/wiki/Portable_Network_Graphics">Portable
 * Network Graphics</a> ({@link #PNG PNG})&nbsp;[<a href="#cite_RFC2083"
 * style="font-weight:bold">1</a>]</li>
 * <li><a href="http://en.wikipedia.org/wiki/JPEG">Joint Photographic
 * Expert Group</a> ({@link #JPEG JPEG}) <a
 * href="http://en.wikipedia.org/wiki/JPEG_File_Interchange_Format">File
 * Interchange Format</a>&nbsp;[<a href="#cite_ISOIEC1091811994"
 * style="font-weight:bold">2</a>]</li>
 * <li><a href="http://en.wikipedia.org/wiki/GIF">Graphics Interchange
 * Format</a> ({@link #GIF GIF})&nbsp;[<a href="#cite_CI1990GIFV8"
 * style="font-weight:bold">3</a>]</li>
 * </ol>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_RFC2083" /><a
 * href="http://www.boutell.com/boutell/">Thomas Boutell</a>, et al.,
 * and&nbsp;Philadelphia, PA, USA: Boutell.Com, Inc.: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;PNG (Portable
 * Network Graphics) Specification Version 1.0,&rdquo;</span>
 * March&nbsp;1997, volume 2083 of Request for Comments (RFC), Network
 * Working Group. <div>links: [<a
 * href="http://tools.ietf.org/html/rfc2083">1</a>] and&nbsp;[<a
 * href="http://www.rfc-base.org/rfc-2083.html">2</a>]</div></div></li>
 * <li><div><span id="cite_ISOIEC1091811994" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;ISO/IEC
 * 10918-1:1994: Information Technology &#8211; Digital Compression and
 * Coding of Continuous-tone Still Images: Requirements and
 * Guidelines,&rdquo;</span> 1994, International Standard, Geneva,
 * Switzerland: International Electrotechnical Commission (IEC)
 * and&nbsp;Geneva, Switzerland: International Organization for
 * Standardization (ISO)</div></li>
 * <li><div><span id="cite_CI1990GIFV8" />Columbus, OH, USA: CompuServe
 * Incorporated: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Graphics
 * Interchange Format(sm), Version 89a, Programming
 * Reference,&rdquo;</span> (Website), July&nbsp;31, 1990, Columbus, OH,
 * USA: CompuServe Incorporated. <div>link: [<a
 * href="http://www.w3.org/Graphics/GIF/spec-gif89a.txt">1</a>]</div></div>
 * </li>
 * </ol>
 */
public enum EImageType {

  /**
   * a <a href="http://en.wikipedia.org/wiki/Portable_Network_Graphics">
   * Portable Network Graphics</a> (PNG)
   */
  PNG("png", null), //$NON-NLS-1$

  /**
   * an image in the <a href="http://en.wikipedia.org/wiki/JPEG">Joint
   * Photographic Expert Group</a> (JPEG) <a
   * href="http://en.wikipedia.org/wiki/JPEG_File_Interchange_Format">File
   * Interchange Format</a>
   */
  JPEG("jpeg", "jpg"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * an image in the <a href="http://en.wikipedia.org/wiki/GIF">Graphics
   * Interchange Format</a> (GIF)
   */
  GIF("gif", null);//$NON-NLS-1$

  /** the java type */
  private final String m_javaType;

  /** the file extension */
  private final String m_fileExt;

  /**
   * create
   *
   * @param javaType
   *          the java type
   * @param fileExt
   *          the file extension
   */
  private EImageType(final String javaType, final String fileExt) {
    this.m_javaType = javaType;
    this.m_fileExt = ((fileExt != null) ? fileExt : this.m_javaType);
  }

  /**
   * Get the java type, to be used with
   * {@link javax.imageio.ImageIO#write(java.awt.image.RenderedImage, String, java.io.File)}
   * .
   *
   * @return the java type
   */
  public final String getJavaType() {
    return this.m_javaType;
  }

  /**
   * Get the file extension to use (without the "{@code .}").
   *
   * @return the file extension to use, without the "{@code .}"
   */
  public final String getFileExtension() {
    return this.m_fileExt;
  }
}
