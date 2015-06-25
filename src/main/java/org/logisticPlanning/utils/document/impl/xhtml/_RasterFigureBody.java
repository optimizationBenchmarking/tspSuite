package org.logisticPlanning.utils.document.impl.xhtml;

import java.awt.geom.Dimension2D;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.logisticPlanning.utils.document.spec.Element;
import org.logisticPlanning.utils.document.spec.FigureBody;
import org.logisticPlanning.utils.io.FileUtils;

/**
 * a figure body for raster figures figures
 */
final class _RasterFigureBody extends FigureBody {

  /** the associated file */
  final File m_file;
  /** the image type */
  EImageType m_type;

  /**
   * create the figure body element
   *
   * @param relativeNameBase
   *          the uri denoting the basis for the relative name of this
   *          graphic. this name may be extended or modified by
   *          {@link #makeRelativeName(URI)}
   * @param owner
   *          the owning element
   * @param sizeInMM
   *          the size of the figure in millimeters
   * @throws URISyntaxException
   *           if the uri syntax is wrong
   * @throws IOException
   *           if io fails
   */
  _RasterFigureBody(final Element owner, final URI relativeNameBase,
      final Dimension2D sizeInMM) throws IOException, URISyntaxException {
    super(owner, relativeNameBase, sizeInMM);

    this.m_file = FileUtils.canonicalize(this.resolveRelativeName(this
        .getRelativeName()));
    if (this.m_file == null) {
      throw new IllegalArgumentException(//
          "Each figure body must be associated with a valid file."); //$NON-NLS-1$
    }
  }

  /** {@inheritDoc} */
  @Override
  protected final URI makeRelativeName(final URI relativeNameBase)
      throws URISyntaxException {
    if (this.m_type == null) {
      this.m_type = ((XHTMLDriver) (this.getDocument().getOwner()
          .getDriver())).getImageType();
    }
    return new URI(relativeNameBase.toString() + '.' + //
        this.m_type.getFileExtension());
  }

}
